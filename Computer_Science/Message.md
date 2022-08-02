# SMS

- 长度：140 byte之内
- 业务
  - 小区广播短消息业务
  - 点对点短消息业务
    - SMS MO（Short Message Mobile Originated）:MS->SC
    - SMS MT (Short Message Mobile Terminated)SC->MS
    - MS：Mobile Station
    - SC：Service Center
- 短信类型
  - SMS-DELIVER
  - SMS-DELIVER-REPORT
  - SMS-SUBMIT
  - SMS-SUBMIT-REPORT
  - SMS-STATUS-REPORT
  - SMS-COMMAND

- 短信发送流程：app->framework->ril->modem

## 发送流程

### app

1. 打开短信应用会进入ConversationListActivity，位于packages/apps/Messaging/src/com/android/messaging/ui/conversationlist/ConversationlistActivity.java

2. 创建新的消息，进入ConversationActivity，位于ui/conversation/ConversationActivity.java	

   - 在onCreate()中调用updateUiState()创建ConversationFragment对象

3. 在ConversationFragment的onCreateView获得ComposeMessageView并bind

   - ComposeMessageView：This view contains the UI required to generate and send messages.

   - ```java
     public View onCreateView(final LayoutInflater inflater, final ViewGroup container,final Bundle savedInstanceState) {
          mComposeMessageView = (ComposeMessageView)view.findViewById(R.id.message_compose_view_container);
          // Bind the compose message view to the DraftMessageData
          mComposeMessageView.bind(DataModel.get().createDraftMessageData(mBinding.getData().getConversationId()), this);
     }
     ```

4. 在ComposeMessageView的onFinishInflate()中获取发送按钮并绑定点击事件

   - ```java
     private ImageButton mSendButton;
     protected void onFinishInflate() {
         mSendButton = (ImageButton) findViewById(R.id.send_message_button);
         mSendButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(final View clickView) {
                 sendMessageInternal(true /* checkMessageSize */);
             }
         });
     }
     ```

   - 在sendMessageInternal（）中设置短信的内容和接收者，检查短信格式，根据结果执行相对应的操作

     - ```java
       //ConversationFragment实现了IComposeMessageViewHost
       private IComposeMessageViewHost mHost;
       private void sendMessageInternal(final boolean checkMessageSize) {
             LogUtil.i(LogUtil.BUGLE_TAG, "UI initiated message sending in conversation " +
                     mBinding.getData().getConversationId());
             // Check the host for pre-conditions about any action.
             if (mHost.isReadyForAction()) {
                 mInputManager.showHideSimSelector(false /* show */, true /* animate */);
                 //mBinding.getData()获得DraftMessageData对象，设置短信的内容
                 final String messageToSend = mComposeEditText.getText().toString();
                 mBinding.getData().setMessageText(messageToSend);
                 //设置短信的接收者
                 final String subject = mComposeSubjectText.getText().toString();
                 mBinding.getData().setMessageSubject(subject);
                 // Asynchronously check the draft against various requirements before sending.
                 mBinding.getData().checkDraftForAction(checkMessageSize,
                         mHost.getConversationSelfSubId(), new CheckDraftTaskCallback() {
                     @Override
                     public void onDraftChecked(DraftMessageData data, int result) {
                         mBinding.ensureBound(data);
                         switch (result) {
                             case CheckDraftForSendTask.RESULT_PASSED:
                                 // Continue sending after check succeeded.
                                 //将DraftMessageData对象变为MessageData对象
                                 final MessageData message = mBinding.getData().prepareMessageForSending(mBinding);
                                 if (message != null && message.hasContent()) {
                                     playSentSound();
                                     //使用ConversationFragment类的sendMessage()方法开始发送Message。
                                     mHost.sendMessage(message);
                                     hideSubjectEditor();
                                     if (AccessibilityUtil.isTouchExplorationEnabled(getContext())) {
                                         AccessibilityUtil.announceForAccessibilityCompat(
                                                 ComposeMessageView.this, null,
                                                 R.string.sending_message);
                                     }
                                 }
                                 break;
                         }
                     }
                 }, mBinding);
             } 
         }  
       ```

       - ```java
         /*首先创建DraftMessageData类的内部类对象CheckDraftForSendTask,它继承了SafeAsync Task;接着调用此对象的executeOnThreadPool方法触发重写父类的三个方法调用onPreExecute、dolnBackgroundTimed 和onPostExecute,这几个方法的处理逻辑是发送短信的前置条件判断，最终通过mCallback.onDraftChecked调用将判断结果发送给CheckDraftTaskCallback对象。*/
         public void checkDraftForAction(final boolean checkMessageSize, final int selfSubId,final CheckDraftTaskCallback callback, final Binding<DraftMessageData> binding) {
              new CheckDraftForSendTask(checkMessageSize, selfSubId, callback, binding).executeOnThreadPool((Void) null);
         }
         ```

5. 在ConversationFragment类的sendMessage()中，调用ConversationData的sendMessage()方法

   - ```java
     public void sendMessage(final MessageData message) {
         if (isReadyForAction()) {
             if (ensureKnownRecipients()) {
                 // Merge the caption text from attachments into the text body of the messages
                 message.consolidateText();
     			//获得ConversationData，调用它的sendMessage()方法
                 mBinding.getData().sendMessage(mBinding, message);
                 mComposeMessageView.resetMediaPickerState();
             } 
         } 
     }
     ```

6. 在ConversationData的sendMessage()中判断并根据用户的api和sub执行对应的insertNewMessage()方法。

  - ```java
    public void sendMessage(final BindingBase<ConversationData> binding,final MessageData message) {
        //boolean isAtLeastL_MR1()：True if the version of Android that we're running on is at least L MR1(API level 22)    
        if (!OsUtil.isAtLeastL_MR1() || message.getSelfId() == null) {
                InsertNewMessageAction.insertNewMessage(message);
            } else {
                final int systemDefaultSubId = PhoneUtils.getDefault().getDefaultSmsSubscriptionId();
                if (systemDefaultSubId != ParticipantData.DEFAULT_SELF_SUB_ID &&
                        mSelfParticipantsData.isDefaultSelf(message.getSelfId())) {
                    // Lock the sub selection to the system default SIM as soon as the user clicks on
                    // the send button to avoid races between this and when InsertNewMessageAction is
                    // actually executed on the data model thread, during which the user can potentially
                    // change the system default SIM in Settings.
                    InsertNewMessageAction.insertNewMessage(message, systemDefaultSubId);
                } else {
                    InsertNewMessageAction.insertNewMessage(message);
                }
            }
    ```

7. 在InsertNewMessageAction中执行insertNewMessage()

  - InsertNewMessageAction used to **convert a draft message to an outgoing message**. Its writes SMS messages to the telephony db, but SendMessageAction is responsible for inserting MMS message into the telephony DB. The latter also does the actual sending of the message in the background.The latter is also responsible for re-sending a failed message.

  - ```java
    //Insert message (no listener)
    public static void insertNewMessage(final MessageData message) {
        final InsertNewMessageAction action = new InsertNewMessageAction(message);
        action.start();
    }
    
    //根据Action 的处理机制，将以后台异步方式调用InsertNewMessageAction对象的executeAction().使用了Android的IntentService运行机制
    protected Object executeAction() {
        //...先将Message插入到数据库	
        ...
        ProcessPendingMessagesAction.scheduleProcessPendingMessagesAction(false, this);
        return message;
    }
    ```

8. 在ProcessPendingMessagesAction的scheduleProcessPendingMessagesAction()中

  - ```java
    public static void scheduleProcessPendingMessagesAction(final boolean failed,final Action processingAction) {
        LogUtil.i(TAG, "ProcessPendingMessagesAction: Scheduling pending messages"+(failed ? "(message failed)" : ""));
        // Can safely clear any pending alarms or connectivity events as either an action
        // is currently running or we will run now or register if pending actions possible.
        unregister();
    
        final boolean isDefaultSmsApp = PhoneUtils.getDefault().isDefaultSmsApp();
        boolean scheduleAlarm = false;
        // If message succeeded and if Bugle is default SMS app just carry on with next message
        if (!failed && isDefaultSmsApp) {
            // Clear retry attempt count as something just succeeded
            setRetry(0);
    
            // Lookup and queue next message for immediate processing by background worker
            //  iff there are no pending messages this will do nothing and return true.
            final ProcessPendingMessagesAction action = new ProcessPendingMessagesAction();
            if (action.queueActions(processingAction)) {
                if (LogUtil.isLoggable(TAG, LogUtil.VERBOSE)) {
                    if (processingAction.hasBackgroundActions()) {
                        LogUtil.v(TAG, "ProcessPendingMessagesAction: Action queued");
                    } else {
                        LogUtil.v(TAG, "ProcessPendingMessagesAction: No actions to queue");
                    }
                }
                // Have queued next action if needed, nothing more to do
                return;
            }
            // In case of error queuing schedule a retry
            scheduleAlarm = true;
            LogUtil.w(TAG, "ProcessPendingMessagesAction: Action failed to queue; retrying");
        }
    }
    ```

  - ```java
    /**
    Queue any pending actions
    return true if action queued (or no actions to queue) else false
    */
    private boolean queueActions(final Action processingAction) {
          final DatabaseWrapper db = DataModel.get().getDatabase();
          final long now = System.currentTimeMillis();
          boolean succeeded = true;
          final int subId = processingAction.actionParameters.getInt(KEY_SUB_ID, ParticipantData.DEFAULT_SELF_SUB_ID);
    
          LogUtil.i(TAG, "ProcessPendingMessagesAction: Start queueing for subId " + subId);
    
          final String selfId = ParticipantData.getParticipantId(db, subId);
          if (selfId == null) {
              // This could be happened before refreshing participant.
              LogUtil.w(TAG, "ProcessPendingMessagesAction: selfId is null");
              return false;
          }
          // Will queue no more than one message to send plus one message to download
          // This keeps outgoing messages "in order" but allow downloads to happen even if sending
          // gets blocked until messages time out. Manual resend bumps messages to head of queue.
          final String toSendMessageId = findNextMessageToSend(db, now, selfId);
          final String toDownloadMessageId = findNextMessageToDownload(db, now, selfId);
          if (toSendMessageId != null) {
              LogUtil.i(TAG, "ProcessPendingMessagesAction: Queueing message " + toSendMessageId
                      + " for sending");
              // This could queue nothing
              if (!SendMessageAction.queueForSendInBackground(toSendMessageId, processingAction)) {
                  LogUtil.w(TAG, "ProcessPendingMessagesAction: Failed to queue message "
                        + toSendMessageId + " for sending");
                  succeeded = false;
              }
          }
    }
    ```

9. 在SendMessageAction的queueForSendInBackground()中

  - Action used to send an outgoing message. It writes MMS messages to the telephony db.InsertNewMessageAction writes SMS messages to the telephony db). It also initiates the actual sending. It will all be used for re-sending a failed message.

  - ```java
    //Queue sending of existing message (can only be called during execute of action)
    static boolean queueForSendInBackground(final String messageId,final Action processingAction) {
        final SendMessageAction action = new SendMessageAction();
        return action.queueAction(messageId, processingAction);
    }
    
    //Read message from database and queue actual sending
    private boolean queueAction(final String messageId, final Action processingAction) {
        //从数据库中读取Message，如果数据库没有就不用发送了
        ...
        //根据读取到的协议是不是sms的
        final boolean isSms = (message.getProtocol() == MessageData.PROTOCOL_SMS);
        if (isSms) {
            //获得服务中心
            //BugleDatabaseOperations:manages updating our local database
            final String smsc = BugleDatabaseOperations.getSmsServiceCenterForConversation(db, conversationId);
            actionParameters.putString(KEY_SMS_SERVICE_CENTER, smsc);
    
            if (recipients.size() == 1) {
                final String recipient = recipients.get(0);
    
                actionParameters.putString(KEY_RECIPIENT, recipient);
                // Queue actual sending for SMS
                //Queues up background actions for background processing after the current action has completed its processing
                //将当前的发送短信的业务添加到队列中，轮到这条短信时会调用SendMessageAction 对象的doBackgroundWork()在后台执行耗时的异步任务
                processingAction.requestBackgroundWork(this);
    
                if (LogUtil.isLoggable(TAG, LogUtil.DEBUG)) {
                    LogUtil.d(TAG, "SendMessageAction: Queued SMS message " + messageId
                            + " for sending");
                }
                return true;
            }
        }
    }
    
    //Do work in a long running background worker thread.requestBackgroundWork() needs to be called for this method to be called
    protected Bundle doBackgroundWork() {
        final MessageData message = actionParameters.getParcelable(KEY_MESSAGE);
        final String messageId = actionParameters.getString(KEY_MESSAGE_ID);
        Uri messageUri = actionParameters.getParcelable(KEY_MESSAGE_URI);
        Uri updatedMessageUri = null;
        final boolean isSms = message.getProtocol() == MessageData.PROTOCOL_SMS;
        final int subId = actionParameters.getInt(KEY_SUB_ID, ParticipantData.DEFAULT_SELF_SUB_ID);
        final String subPhoneNumber = actionParameters.getString(KEY_SUB_PHONE_NUMBER);
    
        LogUtil.i(TAG, "SendMessageAction: Sending " + (isSms ? "SMS" : "MMS") + " message "
                + messageId + " in conversation " + message.getConversationId());
    
        int status;
        int rawStatus = MessageData.RAW_TELEPHONY_STATUS_UNDEFINED;
        int resultCode = MessageData.UNKNOWN_RESULT_CODE;
        if (isSms) {
            Assert.notNull(messageUri);
            final String recipient = actionParameters.getString(KEY_RECIPIENT);
            final String messageText = message.getMessageText();
            final String smsServiceCenter = actionParameters.getString(KEY_SMS_SERVICE_CENTER);
            final boolean deliveryReportRequired = MmsUtils.isDeliveryReportRequired(subId);
    
            status = MmsUtils.sendSmsMessage(recipient, messageText, messageUri, subId,
                    smsServiceCenter, deliveryReportRequired);
        }
    }
    ```

10. 在MmsUtils的sendSmsMessage()中

  - MmsUtils：Utils for sending sms/mms messages.

  - ```java
    public static int sendSmsMessage(final String recipient, final String messageText,final Uri requestUri, final int subId,
                final String smsServiceCenter, final boolean requireDeliveryReport) {
            final Context context = Factory.get().getApplicationContext();
            int status = MMS_REQUEST_MANUAL_RETRY;
            try {
                // Send a single message
                final SendResult result = SmsSender.sendMessage(
                        context,
                        subId,
                        recipient,
                        messageText,
                        smsServiceCenter,
                        requireDeliveryReport,
                        requestUri);
            } catch (final Exception e) {
                LogUtil.e(TAG, "MmsUtils: failed to send SMS " + e, e);
            }
            return status;
        }
    ```

11. 在SmsSender的sendMessage()中

   - SmsSender：Class that sends chat message via SMS

   - ```java
     public static SendResult sendMessage(final Context context,  final int subId, String dest,String message, final String serviceCenter, final boolean requireDeliveryReport,final Uri messageUri) throws SmsException {
         // Divide the input message by SMS length limit
         final SmsManager smsManager = PhoneUtils.get(subId).getSmsManager();
         final ArrayList<String> messages = smsManager.divideMessage(message);
         if (messages == null || messages.size() < 1) {
             throw new SmsException("SmsSender: fails to divide message");
         }
         // Prepare the send result, which collects the send status for each part
         final SendResult pendingResult = new SendResult(messages.size());
         sPendingMessageMap.put(messageUri, pendingResult);
         // Actually send the sms
         sendInternal(context, subId, dest, messages, serviceCenter, requireDeliveryReport, messageUri);
     }
     
     // Actually sending the message using SmsManager
     private static void sendInternal(final Context context, final int subId, String dest,final ArrayList<String> messages, final String serviceCenter,final boolean requireDeliveryReport, final Uri messageUri) throws SmsException {
         Assert.notNull(context);
         final SmsManager smsManager = PhoneUtils.get(subId).getSmsManager();
         final int messageCount = messages.size();
         final ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>(messageCount);
         final ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>(messageCount);
         for (int i = 0; i < messageCount; i++) {
             // Make pending intents different for each message part
             final int partId = (messageCount <= 1 ? 0 : i + 1);
             if (requireDeliveryReport && (i == (messageCount - 1))) {
                 // only care about the delivery status of the last part
                 //如果是最后一个部分使用deliveryIntents，MESSAGE_DELIVERED_ACTION
                 deliveryIntents.add(PendingIntent.getBroadcast(
                         context,
                         partId,
                         getSendStatusIntent(context, SendStatusReceiver.MESSAGE_DELIVERED_ACTION,
                                 messageUri, partId, subId),
                         0/*flag*/));
             } else {
                 deliveryIntents.add(null);
             }
             //如果不是最后一个部分，使用sentIntents，MESSAGE_SENT_ACTION
             sentIntents.add(PendingIntent.getBroadcast(
                     context,
                     partId,
                     getSendStatusIntent(context, SendStatusReceiver.MESSAGE_SENT_ACTION,
                             messageUri, partId, subId),
                     0/*flag*/));
         }
         if (sSendMultipartSmsAsSeparateMessages == null) {
             sSendMultipartSmsAsSeparateMessages = MmsConfig.get(subId)
                     .getSendMultipartSmsAsSeparateMessages();
         }
         try {
             if (sSendMultipartSmsAsSeparateMessages) {
                 // If multipart sms is not supported, send them as separate messages
                 for (int i = 0; i < messageCount; i++) {
                     smsManager.sendTextMessage(dest,
                             serviceCenter,
                             messages.get(i),
                             sentIntents.get(i),
                             deliveryIntents.get(i));
                 }
             } else {
                 smsManager.sendMultipartTextMessage(
                         dest, serviceCenter, messages, sentIntents, deliveryIntents);
             }
         } catch (final Exception e) {
             throw new SmsException("SmsSender: caught exception in sending " + e);
         }
     }
     ```

### Framework

1. SmsManager的sendTextMessage()中

   - SmsManager位于frameworks/base/telephony/java/android/telephony/java/android/telephony/SmsManager.java

   - ```java
     //Send a text based SMS
     public void sendTextMessage(String destinationAddress, String scAddress, String text,PendingIntent sentIntent, PendingIntent deliveryIntent) {
         sendTextMessageInternal(destinationAddress, scAddress, text, sentIntent, deliveryIntent,
                 true /* persistMessage*/);
     }
     private void sendTextMessageInternal(String destinationAddress, String scAddress,
             String text, PendingIntent sentIntent, PendingIntent deliveryIntent,
             boolean persistMessage) {
         try {
             //SmsController继承了ISmsImplBase，而ISmsImplBase继承了ISms.Stub
             ISms iccISms = getISmsServiceOrThrow();
             iccISms.sendTextForSubscriber(getSubscriptionId(), ActivityThread.currentPackageName(),
                     destinationAddress,
                     scAddress, text, sentIntent, deliveryIntent,
                     persistMessage);
         } catch (RemoteException ex) {
             // ignore it
         }
     }
     ```

2. SmsController的sendTextForSubscriber()中

   - 跨进程调用，从Messaging进程到phone进程

   - ISms.aidl位于frameworks/base/telephony/java/com/android/internal/telephony/ISms.aidl

   - UiccSmsController位于frameworks/opt/telephony/java/com/android/internal/telephony/SmsController.java

   - ```java
     public void sendTextForSubscriber(int subId, String callingPackage,String callingAttributionTag, String destAddr, String scAddr, String text,PendingIntent sentIntent, PendingIntent deliveryIntent,boolean persistMessageForNonDefaultSmsApp, long messageId) {
           if (callingPackage == null) {
               callingPackage = getCallingPackage();
           }
           if (!getSmsPermissions(subId).checkCallingCanSendText(persistMessageForNonDefaultSmsApp,
                   callingPackage, callingAttributionTag, "Sending SMS message")) {
               sendErrorInPendingIntent(sentIntent, SmsManager.RESULT_ERROR_GENERIC_FAILURE);
               return;
           }
           long token = Binder.clearCallingIdentity();
           //获得发送者的信息
           SubscriptionInfo info;
           try {
               info = getSubscriptionInfo(subId);
           } finally {
               Binder.restoreCallingIdentity(token);
           }
           if (isBluetoothSubscription(info)) {
               sendBluetoothText(info, destAddr, text, sentIntent, deliveryIntent);
           } else {
               //关键步骤
               sendIccText(subId, callingPackage, destAddr, scAddr, text, sentIntent, deliveryIntent,
                       persistMessageForNonDefaultSmsApp, messageId);
           }
     }
     private void sendIccText(int subId, String callingPackage, String destAddr,
                   String scAddr, String text, PendingIntent sentIntent, PendingIntent deliveryIntent,
                   boolean persistMessageForNonDefaultSmsApp, long messageId) {
           Rlog.d(LOG_TAG, "sendTextForSubscriber iccSmsIntMgr"+ " Subscription: " + subId + " id: " + messageId);
           IccSmsInterfaceManager iccSmsIntMgr = getIccSmsInterfaceManager(subId);
           if (iccSmsIntMgr != null) {
               //关键步骤
               iccSmsIntMgr.sendText(callingPackage, destAddr, scAddr, text, sentIntent,
                       deliveryIntent, persistMessageForNonDefaultSmsApp, messageId);
           } else {
               Rlog.e(LOG_TAG, "sendTextForSubscriber iccSmsIntMgr is null for"
                       + " Subscription: " + subId + " id: " + messageId);
               sendErrorInPendingIntent(sentIntent, SmsManager.RESULT_ERROR_GENERIC_FAILURE);
           }
     }
     ```

3. 在IccSmsInterfaceManager的sendText()中

  - IccSmsInterfaceManager to provide an inter-process communication to access Sms in Icc

  - Uicc:The universal integrated circuit card (UICC) is the smart card (integrated circuit card) used in mobile terminals in GSM and UMTS networks. The UICC ensures the integrity and security of all kinds of personal data, and it typically holds a few hundred kilobytes. The UICC's primary component is a SIM card.

    - UMTS：Universal Mobile Telecommunications System，a 3g technology

  - ```java
    public void sendText(String callingPackage, String destAddr, String scAddr,String text, PendingIntent sentIntent, PendingIntent deliveryIntent,boolean persistMessageForNonDefaultSmsApp) {
        //A permissions check
        //This method checks only if the calling package has the permission to send the sms.
        mPhone.getContext().enforceCallingPermission(Manifest.permission.SEND_SMS,"Sending SMS message");
        //关键步骤
        sendTextInternal(callingPackage, destAddr, scAddr, text, sentIntent, deliveryIntent,
            persistMessageForNonDefaultSmsApp);
    }
    
    //Send a text based SMS
    public SmsDispatchersController mDispatchersController;
    private void sendTextInternal(String callingPackage, String destAddr, String scAddr,String text, PendingIntent sentIntent, PendingIntent deliveryIntent,boolean persistMessageForNonDefaultSmsApp, int priority, boolean expectMore,
    int validityPeriod, boolean isForVvm, long messageId) {
          if (Rlog.isLoggable("SMS", Log.VERBOSE)) {
              log("sendText: destAddr=" + destAddr + " scAddr=" + scAddr
                      + " text='" + text + "' sentIntent=" + sentIntent + " deliveryIntent="
                      + deliveryIntent + " priority=" + priority + " expectMore=" + expectMore
                      + " validityPeriod=" + validityPeriod + " isForVVM=" + isForVvm
                      + " " + SmsController.formatCrossStackMessageId(messageId));
          }
          notifyIfOutgoingEmergencySms(destAddr);
          destAddr = filterDestAddress(destAddr);
          //关键步骤
          mDispatchersController.sendText(destAddr, scAddr, text, sentIntent, deliveryIntent,
                  null/*messageUri*/, callingPackage, persistMessageForNonDefaultSmsApp,
                  priority, expectMore, validityPeriod, isForVvm, messageId);
        }
    ```

4. 在SmsDispatchersController的sendText（）方法中

  - ```java
    //在SmsDispatchersController的构造方法中mCdmaDispatcher = new CdmaSMSDispatcher(phone, this);
    //在SmsDispatchersController的构造方法中mGsmDispatcher = new GsmSMSDispatcher(phone, this, mGsmInboundSmsHandler);
    private SMSDispatcher mCdmaDispatcher;mCdmaDispatcher = new GsmSMSDispatcher()
    private SMSDispatcher mGsmDispatcher;//在SmsDispatchersController的构造方法中mGsmDispatcher = new GsmSMSDispatcher()
    private ImsSmsDispatcher mImsSmsDispatcher;
    
    public void sendText(String destAddr, String scAddr, String text, PendingIntent sentIntent,
            PendingIntent deliveryIntent, Uri messageUri, String callingPkg, boolean persistMessage,
            int priority, boolean expectMore, int validityPeriod, boolean isForVvm,
            long messageId) {
        //根据不同的情况将sms分发掉
        if (mImsSmsDispatcher.isAvailable() || mImsSmsDispatcher.isEmergencySmsSupport(destAddr)) {
            mImsSmsDispatcher.sendText(destAddr, scAddr, text, sentIntent, deliveryIntent,
                    messageUri, callingPkg, persistMessage, priority, false /z*expectMore*/,
                    validityPeriod, isForVvm, messageId);
        } else {
            if (isCdmaMo()) {
                mCdmaDispatcher.sendText(destAddr, scAddr, text, sentIntent, deliveryIntent,messageUri, callingPkg, 		                     persistMessage, priority, expectMore,validityPeriod, isForVvm, messageId);
            } else {
                mGsmDispatcher.sendText(destAddr, scAddr, text, sentIntent, deliveryIntent,
                        messageUri, callingPkg, persistMessage, priority, expectMore,
                        validityPeriod, isForVvm, messageId);
            }
        }
     }
    ```

5. 在SMSDispatcher的sendText（）中

  - ```java
      public void sendText(String destAddr, String scAddr, String text,
                           PendingIntent sentIntent, PendingIntent deliveryIntent, Uri messageUri,
                           String callingPkg, boolean persistMessage, int priority,
                           boolean expectMore, int validityPeriod, boolean isForVvm,
                           long messageId) {
          Rlog.d(TAG, "sendText id: " + messageId);
          //根据获得的信息生成对应的pdu
          SmsMessageBase.SubmitPduBase pdu = getSubmitPdu(
                  scAddr, destAddr, text, (deliveryIntent != null), null, priority, validityPeriod);
          if (pdu != null) {
              //根据获得的信息生成对应的SmsTracker
              HashMap map = getSmsTrackerMap(destAddr, scAddr, text, pdu);
              SmsTracker tracker = getSmsTracker(callingPkg, map, sentIntent, deliveryIntent,
                      getFormat(), messageUri, expectMore, text, true /*isText*/,
                      persistMessage, priority, validityPeriod, isForVvm, messageId);
      
              if (!sendSmsByCarrierApp(false /* isDataSms */, tracker)) {
                  sendSubmitPdu(tracker);
              }
          } else {
              Rlog.e(TAG, "SmsDispatcher.sendText(): getSubmitPdu() returned null" + " id: "
                      + messageId);
              triggerSentIntentForFailure(sentIntent);
          }
      }
      
      /** Send a single SMS PDU. */
      @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
      private void sendSubmitPdu(SmsTracker tracker) {
          sendSubmitPdu(new SmsTracker[] {tracker});
      }
      
      /** Send a multi-part SMS PDU. Usually just calls sendRawPdu(). */
      private void sendSubmitPdu(SmsTracker[] trackers) {
          if (shouldBlockSmsForEcbm()) {
              Rlog.d(TAG, "Block SMS in Emergency Callback mode");
              handleSmsTrackersFailure(trackers, SmsManager.RESULT_SMS_BLOCKED_DURING_EMERGENCY,
                      NO_ERROR_CODE);
          } else {
              sendRawPdu(trackers);
          }
      }
      
      //Send a single or a multi-part SMS
      public void sendRawPdu(SmsTracker[] trackers) {
          //差错验证，获取包信息
          ...
          // checkDestination() returns true if the destination is not a premium short code or the
          // sending app is approved to send to short codes. Otherwise, a message is sent to our
          // handler with the SmsTracker to request user confirmation before sending.
          if (checkDestination(trackers)) {
              // check for excessive outgoing SMS usage by this app
              if (!mSmsDispatchersController
                      .getUsageMonitor()
                      .check(appInfo.packageName, trackers.length)) {
                  sendMessage(obtainMessage(EVENT_SEND_LIMIT_REACHED_CONFIRMATION, trackers));
                  return;
              }
      
              for (SmsTracker tracker : trackers) {
                  //判断授权开关是否开启
                  if (mSmsDispatchersController.getUsageMonitor().isSmsAuthorizationEnabled()) {
                      final SmsAuthorizationCallback callback = new SmsAuthorizationCallback() {
                          @Override
                          public void onAuthorizationResult(final boolean accepted) {
                              if (accepted) {
                                  sendSms(tracker);
                              } else {
                                  tracker.onFailed(mContext, SmsManager.RESULT_ERROR_GENERIC_FAILURE,
                                          SmsUsageMonitor.ERROR_CODE_BLOCKED);
                              }
                          }
                      };
                     mSmsDispatchersController.getUsageMonitor().authorizeOutgoingSms(tracker.mAppInfo,
                              tracker.mDestAddress,tracker.mFullMessageText, callback, this);
                  } else {
                      //没开启直接发送
                      //实际调用的是GsmSMSDispatcher的sendSms（）方法，
                      sendSms(tracker);
                  }
              }
          }
      		//如果是打给紧急号码，启用异步的紧急服务。
          if (mTelephonyManager.isEmergencyNumber(trackers[0].mDestAddress)) {
              new AsyncEmergencyContactNotifier(mContext).execute();
          }
      }
    ```

6. 在GsmSMSDispatcher的sendSms()中

  - ```java
    protected void sendSms(SmsTracker tracker) {
        int ss = mPhone.getServiceState().getState();
    
        Rlog.d(TAG, "sendSms: "
                + " isIms()=" + isIms()
                + " mRetryCount=" + tracker.mRetryCount
                + " mImsRetry=" + tracker.mImsRetry
                + " mMessageRef=" + tracker.mMessageRef
                + " mUsesImsServiceForIms=" + tracker.mUsesImsServiceForIms
                + " SS=" + ss
                + " " + SmsController.formatCrossStackMessageId(tracker.mMessageId));
    
        // if sms over IMS is not supported on data and voice is not available...
        if (!isIms() && ss != ServiceState.STATE_IN_SERVICE) {
        //In 5G case only Data Rat is reported.
            if(mPhone.getServiceState().getRilDataRadioTechnology()
                    != ServiceState.RIL_RADIO_TECHNOLOGY_NR) {
                tracker.onFailed(mContext, getNotInServiceError(ss), NO_ERROR_CODE);
                return;
            }
        }
    	//当发送完成，执行EVENT_SEND_SMS_COMPLETE消息回调
        Message reply = obtainMessage(EVENT_SEND_SMS_COMPLETE, tracker);
        HashMap<String, Object> map = tracker.getData();
        byte pdu[] = (byte[]) map.get("pdu");
        byte smsc[] = (byte[]) map.get("smsc");
        if (tracker.mRetryCount > 0) {
            // per TS 23.040 Section 9.2.3.6:  If TP-MTI SMS-SUBMIT (0x01) type
            //   TP-RD (bit 2) is 1 for retry
            //   and TP-MR is set to previously failed sms TP-MR
            if (((0x01 & pdu[0]) == 0x01)) {
                pdu[0] |= 0x04; // TP-RD
                pdu[1] = (byte) tracker.mMessageRef; // TP-MR
            }
        }
    
        // sms over gsm is used:
        //   if sms over IMS is not supported AND
        //   this is not a retry case after sms over IMS failed
        //     indicated by mImsRetry > 0 OR
        //   this tracker uses ImsSmsDispatcher to handle SMS over IMS. This dispatcher has received
        //     this message because the ImsSmsDispatcher has indicated that the message needs to
        //     fall back to sending over CS.
        if (0 == tracker.mImsRetry && !isIms() || tracker.mUsesImsServiceForIms) {
            if (tracker.mRetryCount == 0 && tracker.mExpectMore) {
                mCi.sendSMSExpectMore(IccUtils.bytesToHexString(smsc),
                        IccUtils.bytesToHexString(pdu), reply);
            } else {
                mCi.sendSMS(IccUtils.bytesToHexString(smsc),
                        IccUtils.bytesToHexString(pdu), reply);
            }
        } else {
            mCi.sendImsGsmSms(IccUtils.bytesToHexString(smsc),
                    IccUtils.bytesToHexString(pdu), tracker.mImsRetry,
                    tracker.mMessageRef, reply);
            // increment it here, so in case of SMS_FAIL_RETRY over IMS
            // next retry will be sent using IMS request again.
            tracker.mImsRetry++;
        }
    }
    ```

7. RIL的sendSMS()

   - ```java
      public void sendSMS(String smscPdu, String pdu, Message result) {
          获得radio代理
          IRadio radioProxy = getRadioProxy(result);
          if (radioProxy != null) {
              //注册消息
              RILRequest rr = obtainRequest(RIL_REQUEST_SEND_SMS, result,
                      mRILDefaultWorkSource);
      
              // Do not log function args for privacy
              if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
      
              GsmSmsMessage msg = constructGsmSendSmsRilRequest(smscPdu, pdu);
              if (mRadioVersion.greaterOrEqual(RADIO_HAL_VERSION_1_6)) {
                  try {
                      android.hardware.radio.V1_6.IRadio radioProxy16 =
                              (android.hardware.radio.V1_6.IRadio) radioProxy;
                      //通过rilc继续发送
                      radioProxy16.sendSms_1_6(rr.mSerial, msg);
                      mMetrics.writeRilSendSms(mPhoneId, rr.mSerial, SmsSession.Event.Tech.SMS_GSM,
                              SmsSession.Event.Format.SMS_FORMAT_3GPP,
                              getOutgoingSmsMessageId(result));
                  } catch (RemoteException | RuntimeException e) {
                      handleRadioProxyExceptionForRR(rr, "sendSMS", e);
                  }
              } else {
                  try {
                      radioProxy.sendSms(rr.mSerial, msg);
                      mMetrics.writeRilSendSms(mPhoneId, rr.mSerial, SmsSession.Event.Tech.SMS_GSM,
                              SmsSession.Event.Format.SMS_FORMAT_3GPP,
                              getOutgoingSmsMessageId(result));
                  } catch (RemoteException | RuntimeException e) {
                      handleRadioProxyExceptionForRR(rr, "sendSMS", e);
                  }
              }
          }
      }
      ```

### RIL

- 根据不同的radio版本发送sms，绑定RIL_REQUEST_SEND_SMS消息回调

## 接收流程

1. 接收到UNSOL_RESPONSE_NEW_SMS消息

   - hardware/ril/libril/ril_unsol_commands.h定义了接收的消息类型对应的处理方法
   - 具体的处理在hardware/ril/libril/ril_service.cpp中，通过IRadioIndication.newSms()处理

2. IRadioIndication.newSms()跨进程调用

   - IRadioIndication.hidl位于hardware/interfaces/radio/1.0/IRadioIndication.hal

   - IRadioIndication.hidl的实现在frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioIndication.java

   - newSms():Indicates when new SMS is received.

   - ```java
     public void newSms(int indicationType, ArrayList<Byte> pdu) {
         mRil.processIndication(indicationType);
     	//将ArrayList<Byte>转换为byte[]
         byte[] pduArray = RIL.arrayListToPrimitiveArray(pdu);
         if (RIL.RILJ_LOGD) mRil.unsljLog(RIL_UNSOL_RESPONSE_NEW_SMS);
     
         SmsMessageBase smsb = com.android.internal.telephony.gsm.SmsMessage.createFromPdu(pduArray);
         if (mRil.mGsmSmsRegistrant != null) {
             //protected Registrant mGsmSmsRegistrant;
             mRil.mGsmSmsRegistrant.notifyRegistrant(
                     new AsyncResult(null, smsb == null ? null : new SmsMessage(smsb), null));
         }
     }
     
     //mGsmSmsRegistrant的注册
         //在GsmInboundSmsHandler的构造方法中调用了ril的setOnNewGSmSms()
         protected GsmInboundSmsHandler(Context context, SmsStorageMonitor storageMonitor,
                 Phone phone) {
             super("GsmInboundSmsHandler", context, storageMonitor, phone);
         // MTK-END
             phone.mCi.setOnNewGsmSms(getHandler(), EVENT_NEW_SMS, null);
             mDataDownloadHandler = new UsimDataDownloadHandler(phone.mCi, phone.getPhoneId());
             mCellBroadcastServiceManager.enable();
     
             if (TEST_MODE) {
                 if (sTestBroadcastReceiver == null) {
                     sTestBroadcastReceiver = new GsmCbTestBroadcastReceiver();
                     IntentFilter filter = new IntentFilter();
                     filter.addAction(TEST_ACTION);
                     context.registerReceiver(sTestBroadcastReceiver, filter);
                 }
             }
         }
     
         //ril是CommandsInterface，BaseCommands实现了CommandsInterface接口
         //BaseCommands.setOnNewGsmSms（）
         public void setOnNewGsmSms(Handler h, int what, Object obj) {
             mGsmSmsRegistrant = new Registrant (h, what, obj);
         }
     ```

3. Registrant的notifyRegistrant()

   - 位于frameworks/base/core/java/android/os/Registrant.java

   - ```java
     public void notifyRegistrant(AsyncResult ar)
     {
         internalNotifyRegistrant (ar.result, ar.exception);
     }
     
     void internalNotifyRegistrant (Object result, Throwable exception)
     {
         Handler h = getHandler();
     
         if (h == null) {
             clear();
         } else {
             Message msg = Message.obtain();
     
             msg.what = what;
             msg.obj = new AsyncResult(userObj, result, exception);
             //发出消息
             h.sendMessage(msg);
         }
     }
     ```

4. GsmInboundSmsHandler响应消息请求

   - GsmInboundSmsHandler继承自InboundSmsHandler。InboundSmsHandler继承自StateMachine。InboundSmsHandler有五个状态，分别为default、startup、idle、delivering、waiting。接收到短信，从一开始的idle状态进入delivering状态。

     - DeliveringState：In the delivering state, the inbound SMS is processed and stored in the raw table.The message is acknowledged before we exit this state. If there is a message to broadcast,transition to **WaitingState** state to send the ordered broadcast and wait for the results. When all messages have been processed, the halting state will release the wakelock.

   - GsmInboundSmsHandler位于frameworks/opt/telephony/src/java/com/android/internal/telephony/gsm/GsmInboundSmsHandler.java

   - ```java
     //InboundSmsHandler的内部类DeliveringState的processMessage()响应EVENT_NEW_SMS消息
     public boolean processMessage(Message msg) {
         if (DBG) log("DeliveringState.processMessage: processing " + getWhatToString(msg.what));
         switch (msg.what) {
             case EVENT_NEW_SMS:
                 // handle new SMS from RIL
                 handleNewSms((AsyncResult) msg.obj);
                 sendMessage(EVENT_RETURN_TO_IDLE);
                 return HANDLED;
         }
     }
     
     private void handleNewSms(AsyncResult ar) {
         if (ar.exception != null) {
             loge("Exception processing incoming SMS: " + ar.exception);
             return;
         }
     
         int result;
         try {
             SmsMessage sms = (SmsMessage) ar.result;
             result = dispatchMessage(sms.mWrappedSmsMessage, SOURCE_NOT_INJECTED);
         } catch (RuntimeException ex) {
             loge("Exception dispatching message", ex);
             result = RESULT_SMS_DISPATCH_FAILURE;
         }
     
         // RESULT_OK means that the SMS will be acknowledged by special handling,
         // e.g. for SMS-PP data download. Any other result, we should ack here.
         if (result != Activity.RESULT_OK) {
             boolean handled = (result == Intents.RESULT_SMS_HANDLED);
             notifyAndAcknowledgeLastIncomingSms(handled, result, null);
         }
     }
     
     private int dispatchMessage(SmsMessageBase smsb, @SmsSource int smsSource) {
         int result = dispatchMessageRadioSpecific(smsb, smsSource);
     
         // In case of error, add to metrics. This is not required in case of success, as the
         // data will be tracked when the message is processed (processMessagePart).
         if (result != Intents.RESULT_SMS_HANDLED && result != Activity.RESULT_OK) {
             mMetrics.writeIncomingSmsError(mPhone.getPhoneId(), is3gpp2(), smsSource, result);
             mPhone.getSmsStats().onIncomingSmsError(is3gpp2(), smsSource, result);
         }
         return result;
     }
     ```

5. InboundSmsHandler的子类GsmInboundSmsHandler的dispatchMessageRadioSpecific()方法

   - ```java
     protected int dispatchMessageRadioSpecific(SmsMessageBase smsb, @SmsSource int smsSource) {
         SmsMessage sms = (SmsMessage) smsb;
         return dispatchNormalMessage(smsb, smsSource);
     }
     ```

6. InboundSmsHandler的内部类SmsBroadcastReceiver接收Intents.SMS_DELIVER_ACTION广播

   - ```java
     //InboundSmsHandler的dispatchNormalMessage()方法
     /*Dispatch a normal incoming SMS. This is called from dispatchMessageRadioSpecific.
     if no format-specific handling was required. Saves the PDU to the SMS provider raw table,creates an InboundSmsTracker, then sends it to the state machine as an EVENT_BROADCAST_SMS. Returns Intents#RESULT_SMS_HANDLED or an error value.
     */
     protected int dispatchNormalMessage(SmsMessageBase sms, @SmsSource int smsSource) {
         SmsHeader smsHeader = sms.getUserDataHeader();
         /*InboundSmsTracker:Tracker for an incoming SMS message ready to broadcast to listeners.This is similar to com.android.internal.telephony.SMSDispatcher.SmsTracker used for outgoing messages.*/
         InboundSmsTracker tracker;
     
         if ((smsHeader == null) || (smsHeader.concatRef == null)) {
             // Message is not concatenated.
             int destPort = -1;
             if (smsHeader != null && smsHeader.portAddrs != null) {
                 // The message was sent to a port.
                 destPort = smsHeader.portAddrs.destPort;
                 if (DBG) log("destination port: " + destPort);
             }
             tracker = TelephonyComponentFactory.getInstance()
                     .inject(InboundSmsTracker.class.getName())
                     .makeInboundSmsTracker(mContext, sms.getPdu(),
                             sms.getTimestampMillis(), destPort, is3gpp2(), false,
                             sms.getOriginatingAddress(), sms.getDisplayOriginatingAddress(),
                             sms.getMessageBody(), sms.getMessageClass() == MessageClass.CLASS_0,
                             mPhone.getSubId(), smsSource);
         } else {
             // Create a tracker for this message segment.
             SmsHeader.ConcatRef concatRef = smsHeader.concatRef;
             SmsHeader.PortAddrs portAddrs = smsHeader.portAddrs;
             int destPort = (portAddrs != null ? portAddrs.destPort : -1);
             tracker = TelephonyComponentFactory.getInstance()
                     .inject(InboundSmsTracker.class.getName())
                     .makeInboundSmsTracker(mContext, sms.getPdu(),
                             sms.getTimestampMillis(), destPort, is3gpp2(),
                             sms.getOriginatingAddress(), sms.getDisplayOriginatingAddress(),
                             concatRef.refNumber, concatRef.seqNumber, concatRef.msgCount, false,
                             sms.getMessageBody(), sms.getMessageClass() == MessageClass.CLASS_0,
                             mPhone.getSubId(), smsSource);
         }
     
         if (VDBG) log("created tracker: " + tracker);
     
         // de-duping is done only for text messages
         // destPort = -1 indicates text messages, otherwise it's a data sms
         return addTrackerToRawTableAndSendMessage(tracker,tracker.getDestPort() == -1 /* de-dup if text message */);
     }
     
     //Helper to add the tracker to the raw table and then send a message to broadcast it, if successful. Returns the SMS intent status to return to the SMSC.
     protected int addTrackerToRawTableAndSendMessage(InboundSmsTracker tracker, boolean deDup) {
         //记录数据库
         int result = addTrackerToRawTable(tracker, deDup);
         switch(result) {
             case Intents.RESULT_SMS_HANDLED:
                 sendMessage(EVENT_BROADCAST_SMS, tracker);
                 return Intents.RESULT_SMS_HANDLED;
     
             case Intents.RESULT_SMS_DUPLICATED:
                 return Intents.RESULT_SMS_HANDLED;
     
             default:
                 return result;
         }
     }
     
     //InboundSmsHandler的内部类DeliveringState的processMessage()响应EVENT_BROADCAST_SMS消息
     public boolean processMessage(Message msg) {
         if (DBG) log("DeliveringState.processMessage: processing " + getWhatToString(msg.what));
         switch (msg.what) {
             case EVENT_BROADCAST_SMS:
                 // if any broadcasts were sent, transition to waiting state
                 InboundSmsTracker inboundSmsTracker = (InboundSmsTracker) msg.obj;
                 if (processMessagePart(inboundSmsTracker)) {
                     sendMessage(obtainMessage(EVENT_UPDATE_TRACKER, msg.obj));
                     //mWaitingState主要是对新短信广播超时的处理
                     transitionTo(mWaitingState);
                 } else {
                     // if event is sent from SmsBroadcastUndelivered.broadcastSms(), and
                     // processMessagePart() returns false, the state machine will be stuck in
                     // DeliveringState until next message is received. Send message to
                     // transition to idle to avoid that so that wakelock can be released
                     log("DeliveringState.processMessage: EVENT_BROADCAST_SMS: No broadcast "
                             + "sent. Return to IdleState");
                     sendMessage(EVENT_RETURN_TO_IDLE);
                 }
                 return HANDLED;
         }
     }
     
     /*Process the inbound SMS segment. If the message is complete, send it as an ordered broadcast to interested receivers and return true. If the message is a segment of an incomplete multi-part SMS, return false.*/
     protected boolean processMessagePart(InboundSmsTracker tracker) {
     // MTK-END
         int messageCount = tracker.getMessageCount();
         byte[][] pdus;
         long[] timestamps;
         int destPort = tracker.getDestPort();
         boolean block = false;
         String address = tracker.getAddress();
         //根据messageCount执行相对应的single或multipart处理逻辑
         ...
         SmsBroadcastReceiver resultReceiver = tracker.getSmsBroadcastReceiver(this);
     
         // Always invoke SMS filters, even if the number ends up being blocked, to prevent
         // surprising bugs due to blocking numbers that happen to be used for visual voicemail SMS
         // or other carrier system messages.
         boolean filterInvoked = filterSms(
                 pdus, destPort, tracker, resultReceiver, true /* userUnlocked */, block);
     
         if (!filterInvoked) {
             // Block now if the filter wasn't invoked. Otherwise, it will be the responsibility of
             // the filter to delete the SMS once processing completes.
             if (block) {
                 deleteFromRawTable(tracker.getDeleteWhere(), tracker.getDeleteWhereArgs(),
                         DELETE_PERMANENTLY);
                 log("processMessagePart: returning false as the phone number is blocked",
                         tracker.getMessageId());
                 return false;
             }
     
             dispatchSmsDeliveryIntent(pdus, format, destPort, resultReceiver,
                     tracker.isClass0(), tracker.getSubId(), tracker.getMessageId());
         }
     
         return true;
     }
     
     // Creates and dispatches the intent to the default SMS app, appropriate port or via the AppSmsManager
     protected void dispatchSmsDeliveryIntent(byte[][] pdus, String format, int destPort,
             SmsBroadcastReceiver resultReceiver, boolean isClass0, int subId, long messageId) {
     // MTK-END
         Intent intent = new Intent();
         intent.putExtra("pdus", pdus);
         intent.putExtra("format", format);
         if (messageId != 0L) {
             intent.putExtra("messageId", messageId);
         }
     
         if (destPort == -1) {
             //设置action类型
             intent.setAction(Intents.SMS_DELIVER_ACTION);
             // Direct the intent to only the default SMS app. If we can't find a default SMS app
             // then sent it to all broadcast receivers.
             // We are deliberately delivering to the primary user's default SMS App.
             ComponentName componentName = SmsApplication.getDefaultSmsApplication(mContext, true);
             if (componentName != null) {
                 // Deliver SMS message only to this receiver.
                 intent.setComponent(componentName);
                 logWithLocalLog("Delivering SMS to: " + componentName.getPackageName()
                         + " " + componentName.getClassName(), messageId);
             } else {
                 intent.setComponent(null);
             }
     
             // Handle app specific sms messages.
             AppSmsManager appManager = mPhone.getAppSmsManager();
             if (appManager.handleSmsReceivedIntent(intent)) {
                 // The AppSmsManager handled this intent, we're done.
                 dropSms(resultReceiver);
                 return;
             }
         } else {
             intent.setAction(Intents.DATA_SMS_RECEIVED_ACTION);
             Uri uri = Uri.parse("sms://localhost:" + destPort);
             intent.setData(uri);
             intent.setComponent(null);
         }
     
         Bundle options = handleSmsWhitelisting(intent.getComponent(), isClass0);
         //调用sendOrderedBroadcastAsUser发出Intents.SMS_DELIVER_ACTION广播。
         dispatchIntent(intent, android.Manifest.permission.RECEIVE_SMS,
                 AppOpsManager.OPSTR_RECEIVE_SMS, options, resultReceiver, UserHandle.SYSTEM, subId);
         }
     }
     ```

7. InboundSmsHandler的SmsBroadcastReceiver接收Intents.SMS_DELIVER_ACTION广播

   - ```java
     public void onReceive(Context context, Intent intent) {
                 handleAction(intent, true);
     }
     private synchronized void handleAction(Intent intent, boolean onReceive) {
         String action = intent.getAction();
         int subId = intent.getIntExtra(SubscriptionManager.EXTRA_SUBSCRIPTION_INDEX,
                 SubscriptionManager.INVALID_SUBSCRIPTION_ID);
         if (action.equals(Intents.SMS_DELIVER_ACTION)) {
             // Now dispatch the notification only intent
             //将Intents.SMS_DELIVER_ACTION转换为Intents.SMS_RECEIVED_ACTION
             intent.setAction(Intents.SMS_RECEIVED_ACTION);
             // Allow registered broadcast receivers to get this intent even
             // when they are in the background.
             intent.setComponent(null);
             // All running users will be notified of the received sms.
             Bundle options = handleSmsWhitelisting(null, false /* bgActivityStartAllowed */);
     
             setWaitingForIntent(intent);
             //继续调用dispatchIntent发出第二次广播
             dispatchIntent(intent, android.Manifest.permission.RECEIVE_SMS,
                     AppOpsManager.OPSTR_RECEIVE_SMS,
                     options, this, UserHandle.ALL, subId);
         }
     }
     ```

8. 在android/packages/apps/Messaging/AndroidManifest.xml中SmsReceiver接收Intents.SMS_RECEIVED_ACTION广播

   - ```xml-dtd
     <receiver android:name=".receiver.SmsReceiver"
               android:enabled="false"
               android:exported="true"
               android:permission="android.permission.BROADCAST_SMS">
         <intent-filter android:priority="2147483647">
             <action android:name="android.provider.Telephony.SMS_RECEIVED" />
         </intent-filter>
         <intent-filter android:priority="2147483647">
             <action android:name="android.provider.Telephony.MMS_DOWNLOADED" />
         </intent-filter>
     </receiver>
     ```

   - SmsReceiver位于packages/apps/Messaging/src/com/android/messaging/receiver/SmsReceiver.java

   - ```java
     public void onReceive(final Context context, final Intent intent) {
         LogUtil.v(TAG, "SmsReceiver.onReceive " + intent);
         // On KLP+ we only take delivery of SMS messages in SmsDeliverReceiver.
         if (PhoneUtils.getDefault().isSmsEnabled()) {
             final String action = intent.getAction();
             if (OsUtil.isSecondaryUser() &&
                     (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(action) ||
                             // TODO: update this with the actual constant from Telephony
                             "android.provider.Telephony.MMS_DOWNLOADED".equals(action))) {
                 postNewMessageSecondaryUserNotification();
             } else if (!OsUtil.isAtLeastKLP()) {
                 deliverSmsIntent(context, intent);
             }
         }
     }
     
     public static void deliverSmsIntent(final Context context, final Intent intent) {
         //首先在intent 对象中获取pdu 和format 两个信息，最后调用SmsMessage.createFromPdu(pdu, format)创建SmsMessage对象。
         final android.telephony.SmsMessage[] messages = getMessagesFromIntent(intent);
     
         // Check messages for validity
         if (messages == null || messages.length < 1) {
             LogUtil.e(TAG, "processReceivedSms: null or zero or ignored message");
             return;
         }
     
         final int errorCode =
                 intent.getIntExtra(EXTRA_ERROR_CODE, SendStatusReceiver.NO_ERROR_CODE);
         // Always convert negative subIds into -1
         int subId = PhoneUtils.getDefault().getEffectiveIncomingSubIdFromSystem(
                 intent, EXTRA_SUB_ID);
         deliverSmsMessages(context, subId, errorCode, messages);
         if (MmsUtils.isDumpSmsEnabled()) {
             final String format = intent.getStringExtra("format");
             DebugUtils.dumpSms(messages[0].getTimestampMillis(), messages, format);
         }
     }
     
     public static void deliverSmsMessages(final Context context, final int subId,
             final int errorCode, final android.telephony.SmsMessage[] messages) {
         final ContentValues messageValues =
                 MmsUtils.parseReceivedSmsMessage(context, messages, errorCode);
     
         LogUtil.v(TAG, "SmsReceiver.deliverSmsMessages");
     
         final long nowInMillis =  System.currentTimeMillis();
         final long receivedTimestampMs = MmsUtils.getMessageDate(messages[0], nowInMillis);
     
         messageValues.put(Sms.Inbox.DATE, receivedTimestampMs);
         // Default to unread and unseen for us but ReceiveSmsMessageAction will override
         // seen for the telephony db.
         messageValues.put(Sms.Inbox.READ, 0);
         messageValues.put(Sms.Inbox.SEEN, 0);
         if (OsUtil.isAtLeastL_MR1()) {
             messageValues.put(Sms.SUBSCRIPTION_ID, subId);
         }
     
         if (messages[0].getMessageClass() == android.telephony.SmsMessage.MessageClass.CLASS_0 ||
                 DebugUtils.debugClassZeroSmsEnabled()) {
             Factory.get().getUIIntents().launchClassZeroActivity(context, messageValues);
         } else {
             final ReceiveSmsMessageAction action = new ReceiveSmsMessageAction(messageValues);
             //在后台激活executeAction()异步任务
             action.start();
         }
     }
     ```

9. ReceiveSmsMessageAction的executeAction()方法将新短信保存到数据库并通过Notification显示短信通知

   - ```java
     protected Object executeAction() {
         final Context context = Factory.get().getApplicationContext();
         final ContentValues messageValues = actionParameters.getParcelable(KEY_MESSAGE_VALUES);
         final DatabaseWrapper db = DataModel.get().getDatabase();
     
         // Get the SIM subscription ID
         Integer subId = messageValues.getAsInteger(Sms.SUBSCRIPTION_ID);
         // already in the conversation.
         if (!OsUtil.isSecondaryUser()) {
             final String text = messageValues.getAsString(Sms.BODY);
             final String subject = messageValues.getAsString(Sms.SUBJECT);
             final long sent = messageValues.getAsLong(Sms.DATE_SENT);
             final ParticipantData self = ParticipantData.getSelfParticipant(subId);
             final Integer pathPresent = messageValues.getAsInteger(Sms.REPLY_PATH_PRESENT);
             final String smsServiceCenter = messageValues.getAsString(Sms.SERVICE_CENTER);
             String conversationServiceCenter = null;
             // Only set service center if message REPLY_PATH_PRESENT = 1
             if (pathPresent != null && pathPresent == 1 && !TextUtils.isEmpty(smsServiceCenter)) {
                 conversationServiceCenter = smsServiceCenter;
             }
            
             db.beginTransaction();
             try {
                 final String participantId =
                         BugleDatabaseOperations.getOrCreateParticipantInTransaction(db, rawSender);
                 final String selfId =
                         BugleDatabaseOperations.getOrCreateParticipantInTransaction(db, self);
     
                 message = MessageData.createReceivedSmsMessage(messageUri, conversationId,
                         participantId, selfId, text, subject, sent, received, seen, read);
     			 //将新短信存入数据库
                 BugleDatabaseOperations.insertNewMessageInTransaction(db, message);
     			//更新短信会话消息
                 BugleDatabaseOperations.updateConversationMetadataInTransaction(db, conversationId,
                         message.getMessageId(), message.getReceivedTimeStamp(), blocked,
                         conversationServiceCenter, true /* shouldAutoSwitchSelfId */);
                 SyncManager.immediateSync();//TINNO ADD FOR UDCFAA-1295 BY HONGGJIANG.XIAO 20200611
                 final ParticipantData sender = ParticipantData.getFromId(db, participantId);
                 BugleActionToasts.onMessageReceived(conversationId, sender, message);
                 db.setTransactionSuccessful();
             } finally {
                 db.endTransaction();
             }
             LogUtil.i(TAG, "ReceiveSmsMessageAction: Received SMS message " + message.getMessageId()
                     + " in conversation " + message.getConversationId()
                     + ", uri = " + messageUri);
     
             actionParameters.putInt(KEY_SUB_ID, subId);
             ProcessPendingMessagesAction.scheduleProcessPendingMessagesAction(false, this);
         } else {
             if (LogUtil.isLoggable(TAG, LogUtil.DEBUG)) {
                 LogUtil.d(TAG, "ReceiveSmsMessageAction: Not inserting received SMS message for "
                         + "secondary user.");
             }
         }
         // Show a notification to let the user know a new message has arrived
         BugleNotifications.update(false/*silent*/, conversationId, BugleNotifications.UPDATE_ALL);
     
         MessagingContentProvider.notifyMessagesChanged(conversationId);
         MessagingContentProvider.notifyPartsChanged();
     
         return message;
     }
     ```


# WEA

- Wireless Emergency Alerts ( WEA，以前称为商业移动警报系统( Commercial Mobile Alert System ，CMAS )，在此之前称为个人本地化警报网络( Personal Localized Alerting Network ，PLAN)是美国的一个警报网络，旨在将紧急警报传播到移动设备，例如手机和寻呼机。机构能够使用综合公共警报和警告系统通过 WEA 和其他公共系统传播和协调紧急警报和警告信息。
- 背景
  - 法案：*Warning, Alert, and Response Network (WARN) Act* 
- CMAS 将允许联邦机构接受和汇总来自美国总统、国家气象局(NWS) 和紧急行动中心的警报，并将警报发送给参与的无线提供商，后者将通过小区广播方式将警报分发给兼容设备
- 类型
  - 美国总统或联邦紧急事务管理局(FEMA)行政长官发出的警报，Channel ID：4370
  - 涉及生命安全迫在眉睫威胁的警报
    - 极端威胁，Channel ID：4371、4372
    - 严重威胁，Channel ID：4373——4378
  - AMBER Alerts，Channel ID：4379
    - 美国失踪人口：广播紧急回应（America's Missing: Broadcasting Emergency Response，AMBER），通称安珀警报，是一个主要用于美国和加拿大的儿童失踪或绑架预警系统。当确认发生儿童绑架案件时，警务部门透过各种大众媒体向社会大众传播警报消息，以大范围搜寻失踪儿童。
- 其他alert的Channel ID
  - Require Monthly Test：4380
  - CMAS Exercise ：4381
  - Operator Defined Use ：4382
- WEA Handset Action Message（WHAM）
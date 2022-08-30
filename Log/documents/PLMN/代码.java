/*****************************
**     PNN-OPL读取过程      **
******************************/
    SIMRecords初始化过程中会读取EF_PNN和EF_OPL：
    mFh.loadEFLinearFixedAll(EF_PNN, obtainMessage(EVENT_GET_PNN_DONE));
    mFh.loadEFLinearFixedAll(EF_OPL, obtainMessage(EVENT_GET_ALL_OPL_DONE));
1、PNN解析
    PNN读取完毕后，通过parseEFpnn()方法解析：
    private void parseEFpnn(ArrayList messages) {
        int count = messages.size();
        mPnnNetworkNames = new ArrayList<OperatorName>(count);
        for (int i = 0; i < count; i++) {
            byte[] data = (byte[]) messages.get(i);
            SimTlv tlv = new SimTlv(data, 0, data.length);
            OperatorName opName = new OperatorName();
            //...解析后的PNN数据以OperatorName形式存到mPnnNetworkNames中
            mPnnNetworkNames.add(opName);
        }
    }
    解析后的PNN数据以OperatorName形式存到mPnnNetworkNames中。
2、OPL解析
    类似与PNN的读取，OPL读取后通过parseEFopl()解析：
    private void parseEFopl(ArrayList messages) {
        int count = messages.size();
        mOperatorList = new ArrayList<OplRecord>(count);
        for (int i = 0; i < count; i++) {
            byte[] data = (byte[]) messages.get(i);
            OplRecord oplRec = new OplRecord();
            //...解析过程
            mOperatorList.add(oplRec);
        }
    }
    解析后的OPL数据以OplRecord形式存在mOperatorList中。
3、EONS联合读取
    由于SIMRecords中存储了PNN、OPL的List，因此可以通过getEonsIfExist()方法获取当前的EONS计算出来的PLMN：
    public String getEonsIfExist(String plmn, int nLac, boolean bLongNameRequired) {
        int nPnnIndex = -1;
        //是否是HPLMN
        boolean isHPLMN = isHPlmn(plmn);

            //search EF_OPL using plmn & nLac
        for (int i = 0; i < mOperatorList.size(); i++) {
            OplRecord oplRec = mOperatorList.get(i);
            if (isMatchingPlmnForEfOpl(oplRec.sPlmn, plmn) && ((oplRec.nMinLAC == 0 && oplRec.nMaxLAC == 0xfffe) || (oplRec.nMinLAC <= nLac && oplRec.nMaxLAC >= nLac))) {
                //计算出当前LAC在OPL中的index
                nPnnIndex = oplRec.nPnnIndex; 
                break;
            }
        }

        String sEons = null;
        if (nPnnIndex >= 1) {
            //根据PNN的索引读取OPL
            OperatorName opName = mPnnNetworkNames.get(nPnnIndex - 1);
            if (bLongNameRequired) {
                //获取Long的EONS
                if (opName.sFullName != null) {
                    sEons = new String(opName.sFullName);
                } else if (opName.sShortName != null) {
                    sEons = new String(opName.sShortName);
                }
            } else if (!bLongNameRequired) {
                //获取Short的EONS
                if (opName.sShortName != null) {
                    sEons = new String(opName.sShortName);
                } else if (opName.sFullName != null) {
                    sEons = new String(opName.sFullName);
                }
            }
        }
        return sEons;
    }
/*****************************
**  Framework读取NITZ的方式 **
******************************/
    @RIL.java
    public String lookupOperatorNameFromNetwork(long subId, String numeric, boolean desireLongName) {
        int phoneId = SubscriptionManager.getPhoneId((int) subId);
        String nitzOperatorNumeric = null;
        String nitzOperatorName = null;

        //读取NITZ的属性值
        nitzOperatorNumeric = TelephonyManager.getTelephonyProperty(phoneId, TelephonyProperties.PROPERTY_NITZ_OPER_CODE, "");
        if ((numeric != null) && (numeric.equals(nitzOperatorNumeric))) {
            if (desireLongName == true) {
                nitzOperatorName = TelephonyManager.getTelephonyProperty(phoneId, TelephonyProperties.PROPERTY_NITZ_OPER_LNAME, "");
            } else {
                nitzOperatorName = TelephonyManager.getTelephonyProperty(phoneId, TelephonyProperties.PROPERTY_NITZ_OPER_SNAME, "");
            }
        }

        return nitzOperatorName;
    }
/*****************************
**    ROM配置文件读取过程   **
******************************/
    SpnOverride类负责配置文件的加载与读取。其构造方法中完成了配置文件的加载：
    SpnOverride () {
        mCarrierSpnMap = new HashMap<String, String>();
        loadSpnOverrides();
    }
    private void loadSpnOverrides() {
        FileReader spnReader;
        File spnFile = null;
        if ("OP09".equals(SystemProperties.get("ro.operator.optr", ""))) {
            //OP09的处理
            spnFile = new File(Environment.getRootDirectory(), "etc/spn-conf-op09.xml");
            if (!spnFile.exists()) {
                //PARTNER_SPN_OVERRIDE_PATH ="etc/spn-conf.xml";
                spnFile = new File(Environment.getRootDirectory(), PARTNER_SPN_OVERRIDE_PATH);
            }
        } else {
            //PARTNER_SPN_OVERRIDE_PATH ="etc/spn-conf.xml";
            spnFile = new File(Environment.getRootDirectory(), PARTNER_SPN_OVERRIDE_PATH);
        }

        try {
            spnReader = new FileReader(spnFile);
        } catch (FileNotFoundException e) {
            return;
        }

        try {
            //解析文件
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(spnReader);
            XmlUtils.beginDocument(parser, "spnOverrides");
            while (true) {
                XmlUtils.nextElement(parser);

                String name = parser.getName();
                if (!"spnOverride".equals(name)) {
                    break;
                }

                String numeric = parser.getAttributeValue(null, "numeric");
                String data    = parser.getAttributeValue(null, "spn");
                //存入mCarrierSpnMap中
                mCarrierSpnMap.put(numeric, data);
            }
            spnReader.close();
        } catch (XmlPullParserException e) {
            Rlog.w(LOG_TAG, "Exception in spn-conf parser " + e);
        } catch (IOException e) {
            Rlog.w(LOG_TAG, "Exception in spn-conf parser " + e);
        }
    }
    经过上面的解析，就把配置文件的内容存入mCarrierSpnMap中了
    boolean containsCarrier(String carrier) {
        return mCarrierSpnMap.containsKey(carrier);
    }
    String getSpn(String carrier) {
        return mCarrierSpnMap.get(carrier);
    }
    可以通过lookupOperatorName()方法从配置文件中读取当前的PLMN：
    public String lookupOperatorName(int subId, String numeric, boolean desireLongName, Context context) {
        //默认情况下PLMN就是利用MCC+MNC组合的，因此这个接口获取的PLMN一定不为空
        String operName = numeric;
        if (containsCarrier(numeric)) {
            //查找
            operName = getSpn(numeric);
        } else {
            Rlog.w(LOG_TAG, "Can't find long operator name for " + numeric);
        }
        return operName;
    }
/*****************************
**    PLMN获取过程          **
******************************/
    protected void handlePollStateResult (int what, AsyncResult ar) {
        case EVENT_POLL_STATE_OPERATOR: {
            String opNames[] = (String[])ar.result;
            //获取Long名称
            if (opNames != null && opNames.length >= 3) {
                String brandOverride = mUiccController.getUiccCard(getPhoneId()) != null ? mUiccController.getUiccCard(getPhoneId()).getOperatorBrandOverride():null;
                if (brandOverride != null) {
                    log("EVENT_POLL_STATE_OPERATOR: use brandOverride=" + brandOverride);
                    //客制化，通过setOperatorBrandOverride()方法强制覆盖SPN/PLMN
                    mNewSS.setOperatorName(brandOverride, brandOverride, opNames[2]);
                } else {
                    String strOperatorLong = null;
                    String strOperatorShort = null;
                    SpnOverride spnOverride = SpnOverride.getInstance();
                    //查询Modem接收到的NITZ中的PLMN名称
                    //在ril_nw.c将NITZ名称写入persist.radio.nitz_oper_lname和persist.radio.nitz_oper_sname
                    strOperatorLong = mCi.lookupOperatorNameFromNetwork(SubscriptionManager.getSubIdUsingPhoneId(mPhone.getPhoneId()), opNames[2], true);
                    if (strOperatorLong != null) {
                        //使用NITZ名称
                        log("EVENT_POLL_STATE_OPERATOR: OperatorLong use lookFromNetwork");
                    } else {
                        //读取配置文件
                        strOperatorLong = spnOverride.lookupOperatorName(SubscriptionManager.getSubIdUsingPhoneId(mPhone.getPhoneId()), opNames[2], true, mPhone.getContext());
                        if (strOperatorLong != null) {
                            //使用配置文件内容
                            log("EVENT_POLL_STATE_OPERATOR: OperatorLong use lookupOperatorName");
                            //扩展包中对45403/45404进行客制化
                            strOperatorLong = mServiceStateExt.updateOpAlphaLongForHK(strOperatorLong, opNames[2], mPhone.getPhoneId());
                        } else {
                            //使用Modem中读取的内容
                            log("EVENT_POLL_STATE_OPERATOR: OperatorLong use value from ril");
                            strOperatorLong = opNames[0];
                        }
                    }
                    //与Long相似，获取NITZ中Short名称
                    strOperatorShort = mCi.lookupOperatorNameFromNetwork(SubscriptionManager.getSubIdUsingPhoneId(mPhone.getPhoneId()),opNames[2], false);
                    if (strOperatorShort != null) {
                        log("EVENT_POLL_STATE_OPERATOR: OperatorShort use lookupOperatorNameFromNetwork");
                    } else {
                        //读取配置文件中Short名称
                        strOperatorShort = spnOverride.lookupOperatorName(SubscriptionManager.getSubIdUsingPhoneId(mPhone.getPhoneId()), opNames[2], false, mPhone.getContext());
                        if (strOperatorShort != null) {
                            log("EVENT_POLL_STATE_OPERATOR: OperatorShort use lookupOperatorName");
                        } else {
                            log("EVENT_POLL_STATE_OPERATOR: OperatorShort use value from ril");
                            strOperatorShort = opNames[1];
                        }
                    }
                    log("EVENT_POLL_STATE_OPERATOR: " + strOperatorLong + ", " + strOperatorShort);
                    mNewSS.setOperatorName (strOperatorLong, strOperatorShort, opNames[2]);
                }
                //如果PLMN发生改变，送出ACTION_LOCATED_PLMN_CHANGED【MTK自定义】的广播
                updateLocatedPlmn(opNames[2]);
            } else if (opNames != null && opNames.length == 1) {
                log("opNames:" + opNames[0] + " len=" + opNames[0].length());
                //设置PLMN为null
                mNewSS.setOperatorName(null, null, null); // to keep the original AOSP behavior, set null when not registered
                /* Do NOT update invalid PLMN value "000000" */
                if (opNames[0].length() >= 5 && !(opNames[0].equals("000000"))) {
                    updateLocatedPlmn(opNames[0]);
                } else {
                    updateLocatedPlmn(null);
                }
            }
            break;
        }
    }
/*****************************
** 锁屏界面SPN/PLMN更新过程 **
******************************/
    在3GPP中规定的运营商名称显示规则如下：
    1、名称可以为SPN或PLMN
    2、如果没有SPN文件，那么就显示PLMN
    3、若有SPN，并且注册的PLMN是HPLMN或者注册的PLMN在SIM卡文件EF_SPDI中，那么：
        (1)如果有SPN就要显示SPN
        (2)如果SPN的bit1 = 1, 则需要同时显示PLMN，如果SPN的bit1=0，则不需要同时显示PLMN
    4、若有SPN，注册的PLMN是Roaming PLMN且注册的PLMN也不在SIM卡文件EF_SPDI中，那么
        (1)显示PLMN
        (2)如果SPN的bit2=0，则需要同时显示SPN，如果SPN的bit2=1，则不需要同时显示SPN
    下面来看具体实现。
    @SIMRecords.java
	//android/frameworks/opt/telephony/src/java/com/android/internal/telephony/cdnr/CarrierDisplayNameResolver.java
    public int getDisplayRule(String plmn) {
        int rule;
        boolean bSpnActive = false;
        String spn = getServiceProviderName();
        // MTK-END
        if (mParentApp != null && mParentApp.getUiccCard() != null &&
                mParentApp.getUiccCard().getOperatorBrandOverride() != null) {
            //被强制客制化时，仅显示PLMN
            rule = SPN_RULE_SHOW_PLMN;
        } else if (!bSpnActive || TextUtils.isEmpty(spn) || spn.equals("") || mSpnDisplayCondition == -1) {
            //如果SPN为空，则显示PLMN(Rule 2)
            rule = SPN_RULE_SHOW_PLMN;
        } else if (isOnMatchingPlmn(plmn)) {
            //如果当前注册的PLMN为HPLMN或者注册的PLMN存在于SIM中的EF_SPDI字段内，则显示SPN(Rule 3.1)
            rule = SPN_RULE_SHOW_SPN;
            if ((mSpnDisplayCondition & 0x01) == 0x01) {
                //如果SPN的bit1=1，则需要同时显示SPN和PLMN(Rule 3.2)
                rule |= SPN_RULE_SHOW_PLMN;
            }
        } else {
            //如果注册的PLMN为Roaming PLMN，并且注册的PLMN不在EF_SPDI中，则显示PLMN(Rule 4.1)
            rule = SPN_RULE_SHOW_PLMN;
            if ((mSpnDisplayCondition & 0x02) == 0x00) {
                //如果注册的PLMN为Roaming PLMN，并且注册的PLMN不在EF_SPDI中，并且SPN的bit2=0，则要同时显示PLMN和SPN(Rule 4.2)
                rule |= SPN_RULE_SHOW_SPN;
            }
        }
        return rule;
    }
    @GsmServiceStateTracker.java
    protected void updateSpnDisplay(boolean forceUpdate) {
        //获取当前的显示规则
        int rule = (simRecords != null) ? simRecords.getDisplayRule( mSS.getOperatorNumeric()) : SIMRecords.SPN_RULE_SHOW_PLMN;
        String strNumPlmn = mSS.getOperatorNumeric();
        String spn = (simRecords != null) ? simRecords.getServiceProviderName() : "";
        String sEons = null;
        boolean showPlmn = false;
        String plmn = null;
        String mSimOperatorNumeric = (simRecords != null) ? simRecords.getOperatorNumeric() : "";
        try {
            //读取EONS，第一优先级作为PLMN
            sEons = (simRecords != null) ? simRecords.getEonsIfExist(mSS.getOperatorNumeric(), mCellLoc.getLac(), true) : null;
        } catch (RuntimeException ex) {
        }

        if (sEons != null) {
            plmn = sEons;
        } else if (strNumPlmn != null && strNumPlmn.equals(mSimOperatorNumeric)) {
            //如果当前是HPLMN，则此时的PLMN就是CPHS ONE的Long name或者Short name
            plmn = (simRecords != null) ? simRecords.getSIMCPHSOns() : "";
        }
        //至此，在锁屏界面显示的规则中，PLMN的所有方式全部尝试完毕！！！EONS>CPHS>NITZ>配置文件

        if (TextUtils.isEmpty(plmn)) {
            //没有EONS和CPHS时，把SPN当作PLMN
            plmn = mSS.getOperatorAlphaLong();
            if (TextUtils.isEmpty(plmn) || plmn.equals(mSS.getOperatorNumeric())) {
                plmn = mSS.getOperatorAlphaShort();
            }
        }

        //SPN是否显示
        boolean showSpn = !TextUtils.isEmpty(spn) && ((rule & SIMRecords.SPN_RULE_SHOW_SPN) == SIMRecords.SPN_RULE_SHOW_SPN);
        if (!SystemProperties.get("ro.mtk_bsp_package").equals("1")) {
            try {
                if (!mServiceStateExt.allowSpnDisplayed()) {
                    log("For CT test case don't show SPN.");
                    if (rule == (SIMRecords.SPN_RULE_SHOW_PLMN | SIMRecords.SPN_RULE_SHOW_SPN)) {
                        showSpn = false;
                        spn = null;
                    }
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(TelephonyIntents.SPN_STRINGS_UPDATED_ACTION);
        intent.putExtra(TelephonyIntents.EXTRA_SHOW_SPN, showSpn);
        intent.putExtra(TelephonyIntents.EXTRA_SPN, spn);
        intent.putExtra(TelephonyIntents.EXTRA_SHOW_PLMN, showPlmn);
        intent.putExtra(TelephonyIntents.EXTRA_PLMN, plmn);
    }
/*****************************
** 可用网络请求中的PLMN选择 **
******************************/
    private Object responseOperatorInfosWithAct(Parcel p) {
        String strings[] = (String []) responseStrings(p);
        ArrayList<OperatorInfo> ret;
        String lacStr = SystemProperties.get("gsm.cops.lac");
        boolean lacValid = false;
        int lacIndex = 0;

        ret = new ArrayList<OperatorInfo>(strings.length / 5);
        for (int i = 0 ; i < strings.length ; i += 5) {
            if (strings[i + 2] != null) {
                //查找ROM配置文件中的PLMN
                strings[i + 0] = SpnOverride.getInstance().lookupOperatorName(SubscriptionManager.getSubIdUsingPhoneId(mInstanceId), strings[i + 2], true, mContext);
                strings[i + 1] = SpnOverride.getInstance().lookupOperatorName(SubscriptionManager.getSubIdUsingPhoneId(mInstanceId), strings[i + 2], false, mContext);
            }

            String longName = null;
            String shortName = null;
            //查找NITZ中的PLMN
            longName = lookupOperatorNameFromNetwork(SubscriptionManager.getSubIdUsingPhoneId(mInstanceId), strings[i + 2], true);
            shortName = lookupOperatorNameFromNetwork(SubscriptionManager.getSubIdUsingPhoneId(mInstanceId), strings[i + 2], false);
            if (!TextUtils.isEmpty(longName)) {
                //如果NITZ不为空，就使用
                strings[i + 0] = longName;
            }
            if (!TextUtils.isEmpty(shortName)) {
                //如果NITZ不为空，就使用
                strings[i + 1] = shortName;
            }

            if ((lacValid == true) && (strings[i + 0] != null)) {
                int phoneId = mInstanceId;
                UiccController uiccController = UiccController.getInstance();
                SIMRecords simRecord = (SIMRecords) uiccController.getIccRecords(mInstanceId, UiccController.APP_FAM_3GPP);
                int lacValue = -1;
                String sEons = null;
                String lac = lacStr.substring(lacIndex, lacIndex + 4);
                if (lac != "") {
                    lacValue = Integer.parseInt(lac, 16);
                    lacIndex += 4;
                    if (lacValue != 0xfffe) {
                        //尝试查找EONS作为PLMN显示，优先级第一
                        sEons = simRecord.getEonsIfExist(strings[i + 2], lacValue, true);
                        if (sEons != null) {
                            //抛弃NITZ或者ROM
                            strings[i + 0] = sEons;
                        } else {
                            String mSimOperatorNumeric = simRecord.getOperatorNumeric();
                            if ((mSimOperatorNumeric != null) && (mSimOperatorNumeric.equals(strings[i + 2]))) {
                                //HPLMN时，尝试用CPHS代替NITZ或者ROM
                                String sCphsOns = null;
                                sCphsOns = simRecord.getSIMCPHSOns();
                                if (sCphsOns != null) {
                                    strings[i + 0] = sCphsOns;
                                }
                            }
                        }
                    } else {
                        Rlog.d(RILJ_LOG_TAG, "invalid lac ignored");
                    }
                }
            }
            /* ALPS01597054 Always show Act info(ex: "2G","3G","4G") for PLMN list result */
            //添加2G/3G/4G标识
            strings[i + 0] = strings[i + 0].concat(" " + strings[i + 4]);
            strings[i + 1] = strings[i + 1].concat(" " + strings[i + 4]);

            ret.add(
                    new OperatorInfo(
                        strings[i + 0],
                        strings[i + 1],
                        strings[i + 2],
                        strings[i + 3]));
        }
        return ret;
    }






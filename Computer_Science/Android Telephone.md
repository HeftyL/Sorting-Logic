# 序言

- 背景：Android Telephone作为Android手机中的核心模块，为手机提供了基础的通信能力，其逻辑处理、运行效率和稳定性是Android手机定制开发过程中的重点和难点
- Telephony模块作为一个智能手机的核心部件, 是区别移动OS与桌面OS的重要标志。
- 从接打电话、网络服务、数据上网三方面解析Telephony。
  - 深入解析通话流程、详解Telecom、详解TeleService、Voice Call语音通话模型、ServiceState 网络服务、Data Call移动数据业务、SMS & MMS业务、Radio Interface Layer.
- Android Telephony业务跨度大，涉及多个层之间的交互:应用层、系统框架层、HAL 硬件抽象层和BP Modem。

# 初识Android

## 智能手机的系统结构

- 手机分类
  - 功能手机( Feature Phone )：具有传统手机的基本功能，如打电话、发短信、照相等。
  - 智能手机( Smart Phone )：具有开放的操作系统、硬件和软件的可扩充性和支持第三方的二次开发。
- ![img](Android Telephone.assets/format,f_auto.jpeg)
  - 智能手机的基本硬件结构大多采用双处理器架构:主处理器和从处理器。
    - 主处理器运行开放式操作系统以及操作系统之上的各种应用，负责整个系统的控制;
    - 从处理器负责无线通信基本能力，主要包括DBB ( Digital Baseband,数字基带)和ABB( Analog Baseband,模拟基带), 完成语音信号和数字信号调制解调、信道编码解码和无线Modem控制。
    - 主处理器AP ( Application Processor,应用处理器),从处理器BP ( Baseband Processor,基带处理器)，它们之间通过串口、总线或USB等方式进行通信。

## Android系统架构

- Android 是一个分层的基于Linux Kernel的智能手机操作系统，共分为四层，从上到下依次是应用层( Applications )、应用框架层( Framework)、系统运行库层( Libraries )和核心层( Linux Kernel )
  - 系统运行库层
    - 系统运行库层包含了Android Runtime,其核心为Dalvik虚拟机。每一个Andraid应用程序都运行在Dalvik虚拟机之上，且每一个应用程序都有自己独立运行的进程空间; Dalvik 虚拟机只执行DEX可执行文件。DEX(Dalvik Executable)格式是专为Dalvik 设计的一种压缩格式，适合内存和处理器速度有限的系统。要生成DEX格式文件，首先通过Java程序编译生成class文件，然后通过Android提供的dx工具将class文件格式转换成DEX格式。
      - 特征
        - 每一个Android应用运行在-个Dalvik虚拟机实例中，而每一个虚拟机实例都是一个独立的进程空间。
        - 虚拟机的线程机制、内存分配和管理、Mutex (进程同步)等的实现都依赖底层Linux操作系统。
        - 所有Android应用的线程都对应一个Linux线程，因而虚拟机可以更多地使用Linux操作系统的线程调度和管理机制。
      - Dalvik虚拟机并不是按照Java虚拟机的规范来实现的，两者并不兼容;它们之间最大的不同在于Java虚拟机运行的是Java字节码，而Dalvik虚拟机运行的是其专有的文件格式DEX ( Dalvik Executable )文件。

## Android Telephony框架结构

- ![img](Android Telephone.assets/webp.webp)
  - Android Telephony的业务应用跨越AP和BP，AP与BP相互通信，符合前面介绍的智能手机的硬件基本结构。
  - Android系统在AP上运行，而Telephony运行在Linux Kernel之上的用户空间。
  - Android Telephony也采用了分层结构的设计,共跨越了三层:应用层、应用框架层和系统运行库层，与Android操作系统整体分层结构保持一致;
  - Android Telephony从上到下共分三层: Telephony 应用、Telephony 框架、RIL ( Radio Interface Layer,无线通信接口层，主要位于系统运行库层的HAL中)
  - BP SoftWare在BP上运行，主要负责实际的无线通信能力处理

### 系统运行库层的HAL

- HAL ( Hardware Abstraction Layer, 硬件抽象层)在Linux和Windows操作系统平台下有不同的实现方式。
  - Windows下的HAL位于操作系统的最底层，它直接操作物理硬件设备，用来隔离与不同硬件相关的信息，为上层的操作系统和设备驱动程序提供一个统一接口， 起到对硬件的抽象作用。
  - Linux下的HAL并不是位于操作系统的最底层，它位于操作系统核心层和驱动程序之上，是一个运行在用户空间中的服务程序。
- ![image-20220624163209086](Android Telephone.assets/image-20220624163209086.png)

- 作用
  - HAL是一个位于操作系统和驱动程序之上，运行在用户空间中的服务程序。其目的是对上层应用提供一个统一的查询硬件设备的接口。HAL所谓的抽象并不提供对硬件的实际操作，对硬件的操作仍然由具体的驱动程序来完成。
  - 将原本应该包括在Linux Kernel中的某些驱动关键处理逻辑，转移到了HAL层中，从而达到了不必开源的目的。避免使用linux kernel的GPL（General Public License），而使用Android的ASL(Apache Software License)

#### HAL的运行结构

- ![image-20220624164620165](Android Telephone.assets/image-20220624164620165.png)
  - 分类
    - 老式HAL结构：应用或框架通过so动态链接库调用从而达到对硬件驱动的访问。在so动态链接库里，实现了对驱动的访问逻辑处理。
    - 新式HAL结构：采用Stub代理方式调用。HAL Stub是一种代理概念，虽然Stub仍是以`*.so`的形式存在，但HAL已经将`*.so` 的具体实
      现隐藏了起来。Stub 向HAL提供operations方法，Runtime 通过Stub提供的so获取它的operations方法，并告知Runtime的callback方法。这样Runtime和Stub都有对方调用的方法，一个应用的请求通过Runtime调用Stub的operations方法，而Stub响应operations方法并完成后，再调用Runtime的callback方法返回。
      - ![image-20220624165135191](Android Telephone.assets/image-20220624165135191.png)
      - HAL Stub有一种包含关系， 即HAL里包含了很多的Stub。Runtime 只要说明请求类型，就可以取得并操作Stub对应的operations方法。其实现主要在hardware.c和hardware.h文件中。实质也是通过dlopen方法加载.so动态链接库,从而调用.so里的符号( symbol )实现。

# 搭建Android源代码编译调试环境

- 步骤
  1. 安装Ubuntu
  2. 安装JDK
  3. 更新Ubuntu系统工具包
  4. 编译Android源代码
  5. 下载AndroidStudio和AndroidSDK

## Android Debug Bridge (adb)

- Android 调试桥 (adb) 是一种功能多样的命令行工具，可让您与设备进行通信。adb 命令可用于执行各种设备操作（例如安装和调试应用），并提供对 Unix shell（可用来在设备上运行各种命令）的访问权限。它是一种客户端-服务器程序，组件：

  - **客户端**：用于发送命令。客户端在开发机器上运行。您可以通过发出 adb 命令从命令行终端调用客户端。

  - **守护程序 (adbd)**：用于在设备上运行命令。守护程序在每个设备上作为后台进程运行。

  - **服务器**：用于管理客户端与守护程序之间的通信。服务器在开发机器上作为后台进程运行。

- | 命令                        | 说明                |
  | --------------------------- | ------------------- |
  | adb logcat -vthreadtime     | 查看main日志的命令  |
  | adb logcat -vtime -b radio  | 查看radio日志的命令 |
  | adb logcat -vtime -b events | 查看event日志的命令 |

# 通话流程

## 前置

### 同步和异步

- Synchronous (同步)和Asynchronous (异步)的概念最早来自通信领域。
  - 通信的同步指客户端在发送请求后,必须要在服务端有回应后客户端才继续发送其他请求,所以这时所有请求将会在服务端得到同步，直到服务端返回请求。
  - 通信的异步:指客户端在发送请求后,不必等待服务端的回应就可以发送下一个请求,对所有的请求动作来说将会在服务端得到异步,这条请求的链路就像是一个请求队列,所有的请求动作在这里不会得到同步。

### Handler消息处理机制

- 每一个消息发送到主线程的消息队列中，消息队列遵循先进先出原则，发送消息并不会阻塞线程，而接收线程会阻塞线程。
- Handler允许发送并处理Message消息，Message对象通过主线程的MessageQueue消息队列相关联的Message和Runnable对象进行存取。每个Handler实例对Message消息发送和接收与对应主线程和主线程的消息队列有关。当创建一个新Handler时，Handler就属于当前主线程，主线程的MessageQueue消息队列也同步创建，即Handler会绑定到创建该Handler主线程/消息队列中。然后，Handler就可以通过主线程的消息队列发送和接收Message消息对象了。
- 特性
  - Android里没有全局Message Queue消息队列，每个Activity主线程都有一个独立的Message Queue消息队列，消息队列采用先进先出原 则。不同APK应用不能通过Handler进行Message通信，同一个APK应用中可通过Handler对象传递而进行Message通信。
  - 个Handler实例都会绑定到创建它的线程中（一般位于主线程，即Activity线程），但Handler实例均可在主线程或子线程中创建。
  - Handler发送消息使用Message Queue消息队列，每个Message发送到消息队列里面，遵循先进先出原则；发送消息采用异步方式不 
    会阻塞线程，而接收线程采用同步方式会阻塞线程，所以当Handler处理完一个Message对象后才会接着去取下一个消息进行处理。
- 作用
  - Handler主要作用是异步处理较费时的逻辑，优先将界面返回给用户，异步处理完成后再去更新用户界面。

### AIDL跨应用服务

- AIDL（Android Interface Definition Language, Android接口定义语 言）Android系统平台的接口定义语言与您可能已经使用过的其他IDLs接口定义语言相似。程序员可以利用AIDL自定义编程接口，在客户端和服务端之间实现进程间通信（IPC）。在Android平台上，一个进程通常不能访问另外一个进程的内存空间，因此，Android平台将这些跨进程访问的对象分解成操作系统能够识别的简单对象，并且为跨应用访问而特殊编排和整理这些对象。用于编排和整理这些对象的代码编写起来非常冗长，所以Android的AIDL提供了相关工具来自动生成这些代码供程序员使用。

### 广播

- 发送方仅需要将广播内容完成发送，而接收方过滤自己需要的广播信息和内容，然后进行处理，接收方信息是发送方不知道的。

## 流程分析

- Android Telephony主要的手机通信能力
  - Call（通话）
  - ServiceState（服务状态） 
  - DataConnection（数据连接） 
  - **S**hort **M**essage **S**ervice（SMS,短信）

### 拨号流程分析

#### 打开Nexus 6P手机的拨号盘

1. 首先使用adb devices 命令查看和确认手机与计算机连接是否成功，然后使用adb logcat命令查看Nexus 6P手机的运行日志，最后操作手机，点击Home界面最下面一排最左边带有电话图标的应用按钮，打开拨号界面。
2. ActivityManagerService将启动com.android.dialer包下的DialtactsActivity。system_ server, 即ActivityManagerService所在的系统进程; 通过`adb`
   `shell ps -ef`查看进程信息命令可以确认相关的进程信息

#### DialtactsActivity

- 可在Android Studio连续两次按下右边的Shift 按键, 打开Search Everywhere对话框，输入DialtactsActivity,在输入过程中有逐个英文字母匹配的过程,输入完成后便可完整匹配DialtactsActivity.java文件。

1. 快捷键Ctrl+F12快速打开当前类属性和方法列表浮动菜单
2. 在Android Studio快速匹配过程中，可使用* (匹配多个字符)进行模糊匹配，并且输入的字符不区分大小写。
3. DialpadFragment提供用户拨号的交互界面
4. CalllntentBuilder创建拨号请求的intent对象
5. TelecomManager继续传递拨号请求intent对象

#### lTelecomService接收拨号请求服务

- lTelecomService的实现类TelecomServicelmpl的placeCall 方法，响应Dialer 应用发起的跨进程服务接口调用。将发出一个定向广播，由Telecom应用中的PrimaryCallReceiver 对象接收。
- PrimaryCallReceiver的sendNewOutgoingCallIntent 方法，其调用过程是: sendNewOutgoingCallIntent→NewOutgoingCallntentBroadcaster.processIntent-→mCallsManager.placeOutgoingCall.这两个关键的处理逻辑最终是调用了CallsManager 对象的两个不同方法。
  - startOutgoingCall()：startOutgoingCall()将开始拨号前的准备工作
  - placeOutgoingCall():placeOutgoingCall将继续传递拨号请求，实现将拨号请求发送给BP Modem处理。

#### CallsManager的startOutgoingCall()

- CallsManager.startOutgoingCall:主要逻辑是创建、更新和保存Call 对象
  - 如果不是以前保存在mCalls列表的Call对象，调用addCall (call) 方法保存并触发增加Call对象的通知，CallsManager对象将保存多个Call 对象到mCalls 集合中，Call对象则设置Listener 对象为CallsManager,对象之间相互引用。而CallsManager对象通过mListeners发出onCallAdded消息
    回调。
    - 在onCallAdded()方法中实现绑定服务。首先，创建InCallServiceBindingConnection对象，创建该对象的同时将同步创建一个mServiceConnection对象，此对象为匿名的ServiceConnection类型，重写了onServiceConnected和onServiceDisconnected方法;接着，创建action为InCallService.SERVICE_ INTERFACE的intent对象，并更新了PhoneAccount和Call的一些关键信息;然后，调用Android 系统的bindServiceAsUser 方法绑定服务;最后是绑定服务成功以后的onConnected系统回调，将发起对InCallController.this.onConnected的调用
      - InCallController.this.onConnected()将之前保存的Call对象通过inCallService发送出去
    - Telecom应用中完成了第一次绑定服务和对应服务的接口调用。绑定的SERVICE INTERFACE定义为“android.telecom.InCallService"，InCallController 通过绑定服务的方式，开启拨号流程中的第二次跨进程访问，从Telecom应用的system_ server 进程再次回到Dialer应用的com.android.dialer进程。

#### IInCallService服务的响应过程

- InCallServicelmpl类继承于InCallService类，类代码文件在packages/apps/Dialer工程下，而InCallService类对应的代码文件则在framework 下，其服务接口的定义文件为: frameworks/base/telecomm/java/com/android/intermal/telecom/InCallService.aidl,主要定义了addCall setInCallAdapter、updateCall等接口方法。
- InCallController在拨号流程中，首先绑定服务，接着调用服务的setInCallAdapter、 addCall 和onCallXXChanged接口。
  - onBind服务被绑定的响应方法
    - onBind()的返回Intent是InCallServiceBinder，InCallServiceBinder实现了IInCallService.aidl的接口，这些接口通过发送Handler消息，将服务接收到的服务请求转化为异步处理方式
  - setInCallAdapter设置Adapter
    - setInCallAdapter接口的响应逻辑，主要是创建Phone对象和设置Phone对象的Listener属性。
  - addCall增加主动拨号Outgoing Call
    - 在Telecom应用中，首先会创建Call对象，Dialer应用中也会创建Call对象，但这两个Call对象的定义是不同的。
    - Call对象的创建与转换。从Telecom应用中创建com.android.server.telecom.Call,并通过此对象创建跨进程传递android.telecom.parcelableCall对象(支持序列化和反序列化，因此可以跨进程传递此对象)，而Dialer应用中是接收到parcelableCall对象后，通过此对象相关信息创建android.telecom.Call对象。
    - 调用fireCallAdded(call)方法，使用多个监听器完成通话界面的展示和更新

#### CallsManager的placeOutgoingCall()

- CallsManager 分别调用startOutgoingCall 和placeOutgoingCall。startOutgoingCall 方法将通过绑定服务和调用其服务接口，启动和更新Dialer应用中的InCallActivity, 展示出通话界面;但拨号请求并未发送到BP Modem处理。
- placeOutgoingCall关键调用过程： sendNewOutgoingCalIntent->NewOutgoing->CallntentBroadcaster. processIntent→mCallsManager.placeOutgoingCall->call.startCreateConnection→CreateConnectionProcessor.process→attemptNextPhoneAccount
  - 在CreateConnectionProcessor类中的定义是private ConnectionServiceWrapper mService服务,ConnectionServiceWrapper的createConnection 方法是拨号流程在Telecom应用中将发起第二次绑定服务的跨进程服务访问，绑定服务的服务对象为: SERVICE_INTERFACE，即“android.telecom.ConnectionService"
    - 流程
      - bind Service:创建intent的Action有一个比较隐含的设置， 在ConnectionServiceWrapper类的构造方法中调用了super构造方法，从而设置了绑定服务的对象为ConnectionService.SERVICE _INTERFACE。
      - addConnectionServiceAdapter:将传递实现IConnectionServiceAdapter. aidI接口Stub的跨进程访问binder对象。
      - createConnection:通过Call对象拨号请求相关信息创建ConnectionRequest对象，传递给packages/services/Telephony中对应的服务。
    - 拨号流程中，Telecom应用第一次跨进程服务调用，将与Call对象相关的拨号请求信息传递给了Dialer应用，去加载和展现通话界面;那么第二次跨进程服务调用，Call 拨号请求相关信息转换成了ConnectionRequest对象并传递给了TeleService 应用。TeleService 将接收到的ConnectionRequest请求相关信息传递给BP Modem来发起电话拨号请求。

#### IConnectionService服务的响应过程

- 根据AndroidManifest.xml中对android.telecom.ConnectionService 服务的定义，其服务的Java类为com.android.services.telephony.TelephonyConnectionService, 继承自android.telecom. ConnectionService抽象类。在frameworks/base工程下，代码文件为frameworks/base/telecomm/java/android/telecom/ConnectionService.java
- frameworks/base/telecomm/java/com/android/internal/telecom/lConnectionService.aidl文件作为IConnectionService服务的接口定义，主要定义了addConnectionServiceAdapter、createConnection、answer、hold 等接口。通过这些接口的名字，可以知道此服务主要提供了Call 状态管理的接口供
  Telecom应用调用，比如接听电话、保持呼叫、挂断电话等。
  1. onBind():TelephonyConnectionService继承于ConnectionService 类，并未重写父类的onBind 方法。onBind逻辑简单，返回了IConnectionService.Stub 类型的mBinder对象。
  2. addConnectionServiceAdapter() 设置Adapter：使用Handler的异步消息处理机制，将服务调用的同步方式转为异步方式处理, addConnect ionServiceAdapter服务接口将立即返回
  3. createConnection() 继续发送拨号请求：ConnectionService服务的接口createConnection 的响应逻辑仍然是通过mHandler将同步调用转为异步处理。mHandler发出MSG_ CREATE_ CONNECTION 消息,并在handleMessage中响应此方法，再调用父类的createConnection方法,createConnection方法利用onCreateXXXConnection 创建Connection 对象和通过mAdapter传递过来的Binder对象进行handleCreateConnectionComplete接口回调。
     1. Connection对象的创建过程，TelephonyConnectionService 重写了父类ConnectionService的onCreateOutgoing
        Connection方法，会判断是否是紧急电话，而且Connection连接失败将不是TelephonyConnection，从而不能打电话，如果成功会执行placeoutgoingConnection（）方法，placeoutgoingConnection方法中，如果phone不为空执行phone.dial()方法
     2. phone是com.android.interal.telephony.GsmCdmaPhone类型对象，其代码为frameworks/opt/telephony/src/java/com/android/internal/telephony/GsmCdmaPhone.java。phone的dial 方法的调用过程：diallnternal-→mCT.dial的调用过程，mCT即GsmCdmaCalITracker，dial方法中会使用mCi.dial方法，mCi即RIL对象，其Java代码是frameworks/optelephony/src/java/com/android/internal/telephony/RlL.java，这里将发出RIL的拨号请求。跟踪拨号流程已经到了HAL (硬件抽象层)，在这一层不同的芯片厂家将完成不同的实现，比如高通平台将RIL请求转为QMI消息与Modem交互，MTK平台则采用AT命令的方式与Modem交互。
        - Qualcomm messaging Interface(QMI):QMI是高通提供的一种多处理器进程间通信的功能接口，用于AP和BP侧的交互，通俗说法就是让终端设备TE（可以是手机，PDA，计算机）对高通BP侧的AMSS系统进行操作，如调用函数，读取数据，设置其中的NV项等。
        - 全球移动通讯系统（G lobal S ystem for M obile Communications），即GSM，又称泛欧数位式行动电话系统，是当前应用最为广泛的移动电话标准。全球超过200个国家和地区超过10亿人正在使用GSM电话。GSM标准的广泛使用使得在移动电话运营商之间签署“漫游协定”后用户的国际漫游变得很平常。GSM相较它以前的标准最大的不同是他的信令和语音信道都是数位的，因此GSM被看作是第二代（2G）移动电话系统。GSM标准当前由3GPP组织负责制定和维护。
        - 第三代移动通信技术，简称3G（英语：3rd-Generation），规范名称IMT-2000（International Mobile Telecommunications-2000），是指支持高速数据传输的蜂窝网络移动电话技术。3G服务能够同时发送声音（通话）及信息（电子邮件、即时通信等）。3G的代表特征是提供高速数据业务，速率一般在几百kbps以上，自从4G出来后3G逐渐淘汰。
        - 第四代移动通信技术（英语：The fourth generation of mobile phone mobile communication technology standards，缩写为4G），是3G之后的延伸。 IMT-Advanced的4G标准:高级长期演进技术（又译作长期演进技术升级版，英语：LTE-Advanced，简称LTE-A，在中国大陆称4G+）是长期演进技术（LTE）的提升版本，理论上网速度比3G快十倍以上，也是4G规范的国际高速无线通信标准。

#### TelecomAdapter接收消息回调

1. ConnectionServiceWrapper.Adapter 将接收TeleService应用的接口回调，将通过this调用ConnectionServiceWrapper对象的handleCreateConnectionComplete 方法，接着是mPendingResponses属性对象的handleCreateConnectionSuccess 方法调用，即CreateConnectionProcessor 对象，最后是mCallResponse.handleCreateConnectionSuccess对象，即Call对象的handleCreateConnectionSuccess方法响应TeleService应用的接口回调,会调用Listener的onSuccessfulOutgoingCall进行拨号流程处理
2. Call 类中有Listener 的接口定义，同时也定义了ListenerBase抽象类，它实现了Listener 接口。ListenerBase抽象类实现了Listener 接口的所有方法，并且这些方法都是空实现，没有具体逻辑。ListenerBase 抽象类有三个子类，分别是:CallsManager,InCallController 匿名内部类对象mCallListener,IncomingCallNotifier匿名内部类对象mCallListener。这三个类中，仅有CallsManager重写了父类ListenerBase的onSuccessfulOutgoingCall方法
3. Adapter的接口回调是将当前呼出的电话状态进行更新，更新为dialing,即正在拨号的状态，最终会调用IInCallService的接口去更新通话界面。

### 接听流程分析

- 可以理解为与主动拨号流程正好相反的过程，手机BP Modem侧接收到网络端的来电请求，消息从Modem发给RIL，RIL 再发给TeleService 应用,然后再传递给Telecom应用，最终Dialer 应用接收到来电请求，进行来电响铃(可选震动)和展示来电界面，通知手机用户有新的来电了。
- 流程
  1. Modem从网络端接收到来电，由RIL发出Call状态产生了变化的RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED消息通知， RIL.java发出mCallStateRegistrants.notifyRegistrants通知， CallTracker.java进行响应。
  2. GsmCallTracker.java进入handleMessage，响应EVENT_CALL_STATE_CHANGE，交给父类的pollCallsWhenSafe方法 查询当前Call List，首先创建EVENT_POLL_CALLS_RESULT类型的Handler消息，并向RIL发起getCurrentCalls当前Call List请求。
  3. RIL内部进行处理，向Modem发出执行CLCC查询当前Call List的AT命令，查询完成后，RIL.java向CallTracker发送EVENT_POLL_CALLS_RESULT的Handler消息。
  4. CallTracker进入handleMessage响应EVENT_POLL_CALLS_RESULT消息，进入handlePollCalls处理Call状态查询结果，判断如果是来电，则通过Phone对象发起mNewRingingConnectionRegistrants.notifyRegistrants的消息通知。
  5. CallManager进入handleMessage响应EVENT_NEW_RINGING_CONNECTION消息，通过mNewRingingConnectionRegistrants.notifyRegistrants发起消息，Telephony Frameworks层来电处理流程结束，接着进入Phone应用层。
  6. CallNotifier进入handleMessage响应PHONE_NEW_RINGING_CONNECTION消息，交给 onNewRingingConnection方法处理来电消息，根据来电的电话号码完成联系人查询、来电响铃和显示来电界面，进入Phone应用层。
  7. InCallScreen启动Activity，进入onCreate或onNewIntent方法，显示来电界面。


#### 模拟接受来电

1. RIL.java接收到Call状态变化消息后,CallTracker发起查询Call List操作,然后RIL执行AT+CLCC命令查询Modem数据,返回Call List数据给RIL.java。
2. 进入RIL.java查找UNSOL_ RESPONSE_ CALL_ STATE_ CHANGED消息的处理逻辑,responseToString()方法进行该消息的处理。在此方法中根据底层上报的
   response类型有两处针对switch ( response )逻辑处理,前面的逻辑是对数据进行收集和整理,而后面的逻辑是完成对应response的逻辑处理和消息通知。
3. 查询Call List操作不是由RIL.java接收到Call状态变化消息后直接发起的。RIL.java处理RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED消息 的逻辑中只有mCallStateRegistrants对外发出消息通知，mCallStateRegistrants为RegistrantList类型
   - RegistrantList消息处理机制包括两个重要的Java类：RegistrantList.java和Registrant.java。RegistrantList中使用的观察者模式为：RegistrantList为通知者， Registrant为观察者，RegistrantList通知者支持对通知者的增加（add/addUnique）、删除（remove），并且能够发出通知（notifyRegistrants）；而Registrant作为观察者，响应通知者发出的notifyRegistrants通知，并由其internalNotifyRegistrants方法响应通知者发出的通知。
     - notifyRegistrants方法调用后，找到对应的进行响应的Registrant 对象流程
       1. 查找RegistrantList对象注册观察者Registrant对象的方法， 在Android源代码中一般为registerForXXX方法，在此方法中调用RegistrantList对象的add/addUnique等注册观察者Registrant对象的方 法。
       2. 查找registerForXXX方法的调用方，重点关注其调用的形参，特别是形参1，多数情况为this或mHandler。
       3. 通过步骤的形参，找到的即是对象发出通知
     - mCallStateRegistrants.notifyRegistrants发出通知后，有两处可响应此通知，即GsmCallTracker和CdmaCallTracker两个类的handleMessage方法。在默认的Android虚拟设备上，仅有GsmCallTracker的handleMessage方法可响应。
       - 码分多址（英语：Code Division Multiple Access，即：CDMA）或分码多重进接、码分复存，是一种多址接入的无线通信技术。CDMA最早用于军用通信，但时至今日，已广泛应用到全球不同的民用通信中。在CDMA移动通信中，将语音频号转换为数字信号，给每组数据语音分组增加一个地址，进行扰码处理，然后将它发射到空中。CDMA最大的优点就是相同的带宽下可以容纳更多的呼叫，而且它还可以随语音传送数据信息。

#### GsmCallTracker响应RIL的Call状态变化通知

1. 进入GsmCallTracker的handleMessage方法，找到EVENT_CALL_STATE_CHANGE消息类型的处理方式，会调用pollCallsWhenSafe()方法，其实质为调用父类CallTracker查询Call List方法。RIL.java接收到Modem发出的Call状态变化后，由 CallTracker发起Call List的查询。
2. 在GsmCallTracker的 父类CallTracker类中实现的pollCallsWhenSafe方法通过RIL对象调用其getCurrentCalls方法

#### RIL.java的getCurrentCalls方法

1. 在getCurrentCalls方法中，会使用lastRelevantPoll Message消息对象封装RIL_REQUEST_GET_CURRENT_CALLS类型的RILRequest请求对象，然后发送此RILRequest对象；同样，在RIL.java类中，找到 RIL_REQUEST_GET_CURRENT_CALLS消息类型的其他三种处理方法processSolicited、retToString、requestToString，后面两种都是对RIL 消息类型日志的转换处理，真正的逻辑处理在processSolicited方法中，分为三个部分
   - 根据消息流水号获取RILRequest请求对象。
   - 根据RILRequest请求对象的mRequest请求类型，对数据进行收集和整理，返回Object的对象ret。
   - 根据RILRequest请求对象的mResult回调Message消息，加上整理好的ret数据对象，发起Handler消息通知。
2. 发出Handler消息通知后，会在CallTracker中的handleMessage方法中响应，并且它的消息类型为“EVENT_POLL_CALLS_RESULT”。

#### GsmCallTracker响应消息通知

1. 底层发送Call状态变化发出通知后，在Telephony Frameworks层最终由CallTracker的handlePollCalls方法进行处理
2. handlePollCalls方法根据RIL发出的Call List对象，判断Call的状态，并发出不同通知
   - 新来电通知（phone.notifyNewRingingConnection） 
   - 通话断开通知（conn.onDisconnect）
   - Call状态变化通知（phone.notifyPreciseCallStateChanged）

#### GSMPhone的通知方法notifyNewRingingConnection

- GSMPhone的notifyNewRingingConnection方法，调用了它父类PhoneBase的notifyNewRingingConnectionP方法。也用了RegistrantList观察者模式的消息处理机制，消息响应方为PhoneProxy和 CallManage，通过类的继承关系，能再排除PhoneProxy类，只有CallManage类中的mHandler子类进行响应。

#### CallManager响应来电消息通知

1. 进入CallManager类中mHandler的handleMessage方法，找到EVENT_NEW_RINGING_CONNECTION消息处理逻辑,如果当前正在拨号或是有多于一路的来电，则挂掉当前来电，否则发出消息通知
2. CallNotifier和InCallScreen中的 handleMessage方法响应消息通知。这两个Java代码均在Phone应用层，说明手机来电流程已经从RIL层跟踪到Phone应用层相关代码。
3. 总结
   1. RIL首先发出Call状态变化消息通知，CallTracker中handleMessage方法对此消息做出响应。
   2. CallTracker向RIL发出消息查询Call List状态列表，RIL内部处理 完成后返回CallList给CallTracker，最后CallTacker中的handlePollCalls方法处理Call List，该方法根据Call List状态列表提取出具体的Call状态变化，这里是“来电状态”。

#### CallNotifier响应来电通知

- Phone应用层中的CallNotifier和InCallScreen会响应Telephony Frameworks发出的来电消息通知，即在CallManager mNewRingingConnectionRegistrants.notifyRegistrants发出通知后， CallNotifier和InCallScreen中的Handler会响应此消息通知
- 流程
  1. CallNotifier中来电处理方法入口为onNewRingingConnection。
  2. CallNotifier处理来电流程最终目的是：来电响铃（振动，是否振动根据用户设置进行判断）和显示来电界面。
  3. CallNotifier在响铃和显示来电界面之前主要是根据来电号码查询Contacts联系人相关信息，如联系人名称、联系人电话号码类型、联 系人图片、联系人来电铃音等。其实，查询联系人相关信息也都是为了来电响铃和显示来电界面做一些准备。

#### InCallScreen展现来电界面

CallNotifier的showIncomingCall()方法更新通知栏和加载来电界面。而InCallScreen界面的展示过程，是在更新通知栏时由Notification加载了InCallScreen界面的展示。

### 通话状态更新消息上报流程

- 拨号成功后，对方接听了此路通话，那么通话界面将更新当前通话为通话中的状态，并开始通话计时，可以理解为Modem->RIL→TeleService→Telecom->Dialer, 一层一层上报通话状态为“通话中”的消息处理和发送过程
- 三个应用的Call信息传递
  1. TeleService应用首先接收到通话状态更新的消息，通过Telecom的Adapter服务设置不同的通话状态; 
  2. 接着Telecom应用更新Call状态;
  3. 最后Telecom调用IInCallService的updateCall接口更新Call状态。
- RegistrantList 消息处理
  - 在GsmCdmaPhone对象发出RegistrantList消息通知后,在TelephonyConnection对象的mHandler匿名内部类对象的handleMessage中响应MSG_ PRECISE_CALL_ STATE_ CHANGED类型的Handler消息，该Handler消息的注册入口在TelephonyConnection抽象类的两个子类GsmConnection
    和CdmaConnection的setC riginalConnection方法中实现注册MSG_ PRECISE_ CALL STATE_ CHANGED类型的Registrant,并在GsmConnection的构造方法中调用setOriginalConnection接口进行消息注册的初始化操作;而TelephonyConnection对象在TelephonyConnectionSerice类的onCreateOutgoingConnection和onCreatelncomingConnection方法中创建。
- TelephonyConnection 对象的Listener注册
  - 通过Listener对象的onStateChanged进行消息回调，Listener 对象是在ConnectionService的createConnection方法中，首先创建TelephonyConnection对象，然后调用addConnection 方法，设置当前类的私有内部类Connection.Listener 对象mConnectionListener为TelephonyConnection对象的Listener。
- IConnectionServiceAdapter接口汇总
  - IConnectionServiceAdapter的Stub接口实现在Telecom应用ConnectionServiceWrapper类的私有内部类Adapter 中，它主要由setActive、 setRinging、setDialing、 setAudioRoute 等设置Call相关状态信息的接口，以及onConnectionEvent、onRttInitiationSuccess、 onRemoteRttRequest等消息通知接口构成。
- IInCallService接口汇总
  - IInCallService的Stub接口实现在framework/base 下的InCallService 抽象类的私有内部类InCallServiceBinder中，它主要由setInCallAdapter. addCall updateCall等增加和更新Call对象相关的接口，以及onConnectionEvent、onCallAudioStateChanged 等消息通知接口构成。而在Dialer应用中，InCallServicelmpl继承了抽象类InCallService。

### 控制通话消息下发流程

- 在通话界面若想更改当前通话状态，比如挂断/接听当前接收来电，挂断/保持当前通话等操作，可以理解为是控制通话消息下发的过程，从Dialer- >Telecom- >TeleService→RIL→Modem,通话控制消息一层一层的下发，最终交给Modem处理具体的通话控制。
- 三个应用的控制消息传递
  1. Dialer应用展示的通话界面或来电界面均有控制通话状态请求的界面控件，通过滑动或是点击相
     关的控件，将触发通话状态控制，调用android.telecom.Call对象的hold方法;
  2. 在Dialer应用中，调用InCallAdapter的holdCall 服务接口完成第一次的跨进程服务接口调用，进入Telecom应用。
  3. Telecom应用首先更新Call状态，此处的Call 对象为Telecom应用内部定义的类com.android.server.telecom.Call,调用IConnectionService的hold服务接口，完成第二次的跨进程服务接口调用，进入到TeleService 应用。
  4. TeleService应用中经过层层方法调用，由CalTracker对象进行Call的hold操作，并将请求发给RIL对象，发出对应的RIL请求。
- lInCallAdapter接口汇总
  - lInCallAdapter的Stub接口实现在Telecom应用的InCallAdapter 类中，它主要由answerCall、rejectCall、playDtmfTone、 mergeConference 等接口构成;
  - InCallAdapter 对象则在InCallController对象绑定InCallService 成功后创建。
- IConnectionService 接口汇总
  - IConnectionService的Stub接口实现在frameworks/base下的ConnectionService抽象类的匿名内部类中，mBinder为其对象，它主要由addConnectionServiceAdapter、createConnection 等创建TelephonyConnection接口，以及answer、reject、hold、playDtmfTone 等控制通话状态的接口构成。
  - 在TeleService应用中，TelephonyConnectionService 继承了抽象类TelephonyConnection。

### Android通话模型

- ![image-20220628175628708](Android Telephone.assets/image-20220628175628708.png)

- 系统的分层
  - Dialer 应用是普通的Android App应用，其运行进程的用户信息和进程信息，也能说明此问题; 
  - Telecom 应用运行在system_ server 进程上，其进程用户名为system系统用户，说明它是运行在Android Framework框架层;
  - TeleService应用运行的进程名是com.android.phone，用户名是radio,承载着Telephony Call协议栈，它运行在Android Framework框架层;
  - RIL，它运行在HAL (硬件抽象层)。
- 交互方式
  - Dialer、Telecom、 TeleService 和RIL都是通过服务进行交互的。它们之间有箭头连接的都是通过Service跨进程的接口调用实现的。
  - Dialer与TeleService之间没有直接的消息传递,要通过Telecom进行消息中转，Telecom 与RIL之间同样没有直接的消息传递，要通过TeleService进行消息中转。
  - 通过服务进行跨进程接口调用实现消息的传递，服务接口调用本身就是同步的接口调用，在Service端的实现将转换为异步的方式处理，待消息处理完成后，再使用回调的接口传递消息处理的结果。
- 分解通话相关流程
  - 传递方向
    - 控制通话消息下发流程:应用层通过框架层向RIL发起通话管理和控制相关RIL请求，RIL转换成对应的消息发送给Modem执行，其中包括拨号、接听电话、拒接电话、保持、恢复通话等;
    - 通话状态更新消息上报流程: RIL 接收到Modem的通话状态变化通知，通过框架层向应用层发起通话状态变化通知，包括来电、电话接通、进入通话中等。

# Telecom

- 在Android Telephony通话模型中Telecom作为Dialer和TeleService的消息中转站，消息的处理任务非常繁重和复杂，而且是拨号和来电消息处理的关键入口
  - 相对Dialer应用而言，发送Call状态变化消息给InCallService服务，并接收Dialer应用发出的Call状态的控制消息。
  - 相对TeleService应用而言,Telecom应用继续传递接收到Dialer应用发出的Call状态的控制消息给IConnectionService服务,并接收TeleService应用发出的Call 状态变化消息传递给Dialer应用。
- Telecom交互模型
  - Telecom消息入口:ITelecomService、InCallAdapter 和IConnectionServiceAdapter三个服务作为三种不同类型的消息的入口。
  - Telecom消息出口:InCallController和ConnectionServiceWrapper两次绑定服务操作作为Telecom应用消息的出口。
  - 下发顺时针、上报逆时针消息机制：Telecom应用通过消息入口和出口的五个服务，承载两种类型的消息:控制通话下发的顺时针方向消息处理和控制.上报通话状态变化的逆时针方法消息处理。
- Listener 消息回调机制
- Call和CallsManager对象是Listener回调消息的交换中心，CallsManagerListener对象接收到CallsManager mListeners 消息回调，判断当前Call的
  属性和状态，将实现通话日志、通知栏信息同步、电源管理、耳机交互等通话扩展功能。

## Telecom包结构

- ![image-20220629102315902](Android Telephone.assets/image-20220629102315902.png)

- 代码库：明确代码库packages/services/Telecomm,注意Telecomm有两个m字符，而其编译出的应用文件名为Telecom.apk。
- 系统签名：packages/services/Telecomm代码库根据Android.mk编译脚本,将编译出Telecom.apk Android应用文件，并使用平台签名，可以保障获取到system用户权限并运行在system_server系统进程空间。
- Java 程序包名：Telecom应用统一使用了com.android.server.telecom 包名，此包名下包括解析通话流程时比较重要的类，如Call. CallsManager、ConnectionServiceWrapper和TelecomServicelmpl等。此包名下还有五个子包名: bluetooth、 alfitering、 components、 settings 和ui。
- Test工程：有两个测试工程testapps和tests,它们都有对应的Android.mk和AndroidManifest.xml,为了更加方便地编译和调试，已经将Android.mk文件改成Android.mk.bak文件，让我们单独编译当前模块时，不必再编译测试相关的工程。

## Telecom应用加载入口

- 对拨号流程和来电流程的解析和总结过程中，业务流程进入Telecom应用的入口是TelecomManager类的placeCall和addNewlncomingCall方法

### TelecomManager类核心逻辑分析

- Contex.TELECOM _SERVICE系统服务名"telecom”与服务定义的Action: androidtelecom.TelecomService的关联
  - TelecomManager对象的获取
    - TelecomManager类的from方法，将使用Context对象的getSystemService获取SystemServiceRegistry中保存的TelecomManager普通java对象。此对象的创建时机在System_server进程的启动过程中。
  - ITelecomService服务的加载过程
    - 在Android系统启动过程中，SystemServer 加载时将启动ITelecomService 系统服务，而TelecomLoaderService类中的connectToTelecom方法调用，将以绑定服务的方式绑定ITelecomService服务，在绑定服务成功的回调接口中，会将Binder服务对象添加到ServiceManager中，其服务名为
      "telecom”
  - TelecomManager对象和telecom系统服务均是在手机启动过程中，SystemServer加载时同步创建的。
    - TelecomManager类的from方法通过Context获取的是TelecomManager对象。
    - TelecomManager类的getTelecomService方法通过ServiceManager获取的是ITelecomService服务对象。

### ITelecomService的onBind过程

- ![image-20220629104653079](Android Telephone.assets/image-20220629104653079.png)

- AndroidManifest.xml应用配置文件中对android.telecom.ITelecomService服务的配置，可以找到com.android.server.telecom.components. TelecomService类，它就是Telecom应用的加载入口。此服务将在SystemServer系统启动过程中被加载。
- 流程
  - 创建TelecomSystem对象，初始化Telecom应用中的核心对象。
    - CallsManager和TelecomServicelmpl对象的创建。CallsManager对象创建时将同步创建CallsManagerListener对象，并注册Listener 消息通知。
      - 重点解析和区分Telecom应用中的核心Listener消息处理机制。
    - TelecomServicelmpl对象创建的同时，也将同步创建ITelecomService.Stub的匿名类对象。
  - 获取Binder对象并返回。
    - TelecomService类获取的Binder对象是TelecomServicelmpl对象的内部ITelecomService.Stub匿名对象，它们之间没有继承关系。先获取单例的TelecomSystem对象，再获取TelecomServicelmpl对象，最终获取到ITelecomService.Stub 类型的Binder对象。
- Telecom 加载入口：Telecom应用的加载入口是TelecomService类的onBind方法，它是一个Service类型，并且在AndroidManifest.xml中明确定义了它是一一个服务。
- TelecomService 和TelecomServicelmpl的关系：TelecomService服务通过TelecomServicelmpl的mBinderlmpl属性对象，承载了ITelecomService服务，它们之间本没有继承关系，通过TelecomSystem才有了一定的依赖关系。

### 拨号入口二.components.UserCallActivity

- ".components.UserCallActivity”的Activity 定义，该配置文件中有PrivilegedCallActivity 和EmergencyCallActivity两个别名Activity 定义。
- 老的Android版本中，Telecom应用通过此Activity 接收拨号Intent 请求。Dialer应用的拨号流程中，有一个分支就是使用context.startActivity 发出拨号请求的Intent 对象，此Activity响应onCreate系统调用，从而接收到拨号请求的intent对象。
- Intent.ACTION_ CALL 与Telecom应用的AndroidManifest.xml配置文件，对UserCallActivity的Action: "android.intent.action.CALL"已经成功匹配。

## Telecom交互模型

### frameworks/base/telecomm包结构

- ![image-20220629110309836](Android Telephone.assets/image-20220629110309836.png)

### 绑定 IInCallService 机制

- 在来电流程和拨号流程中，Telecom 应用有两次绑定服务操作，绑定InCallService 的过程将与Dialer应用中的服务交互，最终展示和更新通话界面
  - 绑定服务
  - setInCallAdapter
  - addCall
  - ![image-20220629111713554](Android Telephone.assets/image-20220629111713554.png)
  - InCallService.aidl 和lInCallAdapter .aidI接口实现
    - com.android.server. telecom.InCallAdapter实现了InCallAdapter.aidl 接口; 
    - android.telecom.InCallService抽象类的私有内部类InCallServiceBinder继承实现了InCallService.aidl 接口。
  - 两个 InCallAdapter
    - android.telecom.InCallAdapter在frameworks/base/telecomm包下定义，它是一个普通的 Java类，代理了lInCallAdapter mAdapter 对象的所有操作; 
    - com.android.server.telecom.InCallAdapter正是InCallAdapter服务接口的实现。它们之间的依赖关系可以理解为android.telecom.InCallAdapter对象通过InCallAdapter mAdapter对象发起跨进程的服务接口调用。
  - 区分运行空间
    - com.android.server.telecom.InCallAdapter和InCallController对象运行在Telecom应用进程空间。Telecom应用提供lInCallAdapter服务，InCallController 对象绑定InCallService时保存了lInCallService Binder 对象，从而使用此对象可以调用addCall/updateCall等接口，跨进程访问Dialer应用提供的InCallService 服务;
    - 除com.android.server.telecom.InCallAdapter和InCallController的其他类都运行在Dialer应用的进程空间。
- Telecom应用主动发起的绑定InCallService服务，其入口是InCallController类的onCallAdded()方法
  - ![image-20220629112502340](Android Telephone.assets/image-20220629112502340.png)
  - InCallController内部类：InCallController有六个内部类: InCallServiceConnection 、InCallServicelnfo、 InCallService、BindingConnection、EmergencyInCallServiceConnection、 CarSwappingInCallServiceConnection和NonUlInCallServiceConnectionCollection
  - CarSwappinglnCallServiceConnection的代理关系：通过调用setCarMode方法设置mlsCarMode和mCurrentConnection属性，在调用connect或
    disconnect方法时则调用mCurrentConnection 对象的对应方法，而mCurrentConnection是InCallServiceConnection对象类型，即InCallServiceBindingConnection对象。
  - 内部调用：InCallServiceBindingConnection对象的connect 方法绑定IInCallService，内部匿名mServiceConnection对象的onServiceConnected 方法，将响应绑定成功后的系统回调，最后通过InCallController.this.onConnected方式调用主类的onConnected方法。
  - InCallController 关键属性和方法：mInCallServices属性保存InCallService的Binder对象列表，onCallAdded 和bindToServices方法是绑定InCallService的入口，onConnected 方法是响应绑定成功后setInCallAdapter、addCall服务接口的调用入口。

### 绑定IConnectionService机制

- 将与TeleService应用中的服务交互，发出通话控制消息或是接收通话状态更新的消息
  - bind Service
  - addConnectionServiceAdapter
  - createConnection
- ![image-20220629113405852](Android Telephone.assets/image-20220629113405852.png)
  - ConnectionService.aidl 和IConnectionServiceAdapter .aidl接口实现
    - 抽象类ConnectionService的匿名内部类实现了ConnectionService.aidl 接口，而ConnectionService的mBinder属性对象将同步创建该匿名内部类对象。
    - 在Telecom应用中，ConnectionServiceWrapper类的私有内部类Adapter 继承实现了ConnectionService.aidl。
  - 区分运行空间
    - Telecom应用进程空间：ConnectionServiceWrapper 对象运行在Telecom 应用进程空间，由Telecom应用提供IConnectionServiceAdapter服务，而TeleService应用中的TelephonyConnectionService对象保存了IConnectionServiceAdapter Binder 对象，使用此对象可以调用setActive/setOnHold等接口,跨进程调用Telecom应用提供的IConnectionServiceAdapter 服务，发送通话状态变化的消息。
    - TeleService 应用进程空间：除了ConnectionServiceWrapper类，其他类均运行在TeleService应用的进程空间。

- Telecom应用主动发起的绑定IConnectionService 调用过程的入口是ConnectionServiceWrapper类的createConnection 方法
  - ![image-20220629114106304](Android Telephone.assets/image-20220629114106304.png)
  - ConnectionServiceWrapper 内部类及继承关系
    - ConnectionServiceWrapper是一个普通的Java类型，继承了抽象的ServiceBinder类，但是在Telecom应用中，此对象是通过ConnectionServiceRepository的getService方法获取
    - ConnectionServiceWrapper 有一个内部类Adapter ，它实现了lConnectionServiceAdapter服务接口，并且在方法调用过程中临时创建三个BindCallback的匿名对象。
  - Binder2
    - Binder2对象负责绑定IConnectionService 的所有处理逻辑，作为ConnectionServiceWrapper对象的mBinder属性，与ConnectionServiceWrapper对象同步创建。
    - ServiceBinderConnection对象的onServiceConnected方法将响应绑定IConnectionService成功后的回调，而回调过程将调用主类对象的setBinder和handleSuccessfulConnection两个方法。
  - 绑定服务回调逻辑
    - setBinder和handleSuccessfulConnection两个方法的实现都在ServiceBinder类中。
    - setBinder方法将保存绑定服务成功后的Binder对象,调用子类中实现的setServiceInterface 方法,最终调用服务的addConnectionServiceAdapter接口;
    - handleSuccessfulConnection方法则通过BindCallback对象进行回调，在创建Connection的过程中调用服务的createConnection方法。
  - ConnectionServiceWrapper 关键属性和方法
    - mServiceAction属性确定绑定服务的Action; mServiceInterface 属性保存IConnectionService的Binder对象; mAdapter 属性对象是IConnectionServiceAdapter服务Binder对象;
    - createConnection方法是绑定IConnectionService的程序入口，onServiceConnected 、setServiceInterface、onSuccess 等不同对象的方法是响应绑定服务成功后，调用服务addConnectionServiceAdapter、createConnection 接口的入口。

## 演进Telecom交互模型

- ![image-20220629134057343](Android Telephone.assets/image-20220629134057343.png)
  - 通话控制消息下发:Dialer应用接收用户操作后，通过InCallAdapter服务接口调用发出通话控制消息; Telecom 接收通话控制消息，经过处理后通过IConnectionService服务接口，将通话控制消息发送给TeleService 应用。
  - 通话状态变化消息上报:右边的模型说明了通话状态变化消息上报的过程，TeleService应用接收RIL消息后，通过IConnectionServiceAdapter服务接口调用发出通话状态变化消息; Telecom 接收通话状态变化消息，经过处理后通过IInCallService服务接口，将通话状态变化消息发送给Dialer应用。
  - ![image-20220629134553871](Android Telephone.assets/image-20220629134553871.png)
    - 消息顺时针方向传递为通话控制消息下发的流程。
    - 消息逆时针方向传递为通话状态变化消息上报的流程。
    - Telecom应用承载lInCallAdapter和IConnectionServiceAdapter两个Adapter服务;而Dialer和TeleService应用各自承载着InCallService和IConnectionService两个Service服务。
    - IInCallAdapter接收顺时针方向的通话控制消息; IConnectionServiceAdapter 接收逆时针方向的通话状态变化上报消息。
      - Telecom应用有三个关键入口: ITelecomService系统服务、lInCallAdapter 服务和IConnectionServiceAdapter服务。
      - ITelecomService提供了两个拨号入口: lInCallAdapter 提供通话控制相关接口，IConnectionServiceAdapter提供通话状态变化的消息上报接口。
    - InCallController由Telecom向Dialer应用发送逆时针方向的通话状态变化上报消息，ConnectionServiceWrapper由Telecom向TeleService应用发送顺时针方向的通话控制消息。

## 核心Listener回调消息处理

- 在Telecom应用中主要处理两种消息类型:顺时针方向下发的通话控制消息和逆时针方向上报的通话状态变化消息。而Listener消息回调承载着上报消息的业务处理逻辑，其应用场景是ConnectionServiceWrapper 的Adapter服务对象接收到TeleService应用的接口调用，通知当前Connection和Call的状态或属性发生的变化，再经过一系列的Listener消息回调处理，最终由InCallController创建ParcelableCall对象，使用IInCallService服务接口调用发送给Dialer应用。
- Telecom应用中消息回调的全貌
  - ![image-20220629150820007](Android Telephone.assets/image-20220629150820007.png)
    - 接收上报消息的入口：IConnectionServiceAdapter服务即Adapter对象。
    - 第一条消息回调通道：CreateConnectionResponse消息回调的过程是第一条消息回调通道，也就是在Connection 相关的接口调用过程中，IConnectionServiceAdapter 服务即Adapter 对象接收到上报消息，通过消息回调将消息发送到Call对象，再通过Call.Listener对象进行消息回调CallsManager对象和InCallController的内部匿名对象。
    - 第二条消息回调通道：Adapter对象接收到.上报消息，绕过了Call 对象的相关消息处理过程，直接使用ConnectionServiceWrapper对象的mCallsManager属性调用CallsManager对象的方法，再通过CallsManagerListener对象进行Listener消息回调，最后交给InCallController处理。

### CallsManagerlistener

- 在TelecomSystem初始化过程中创建CallsManager对象时，将同步创建CallsManagerListener对象，并增加到CallsManager对象的`Set<CallsManagerListener> mListeners`集合中;而通话的相关状态或属性发生改变时，CallsManager将遍历mListeners列表，进行onXXX的消息回调

- ![image-20220629140902353](Android Telephone.assets/image-20220629140902353.png)

  - CallsManagerListener 接口定义

    - CallsManagerListener接口定义在CallsManager类中，CallsManagerListenerBase类实现了此接口的所有方法，而且这些方法都没有任何的业务逻辑代码。模板模式。

  - CallsManagerlistenerBase子类

    - | 类名                                                         | 重写父类方法           | 关键业务                                                     |
      | ------------------------------------------------------------ | ---------------------- | ------------------------------------------------------------ |
      | IncomingCallNotifier                                         | onCallAdded            | 手机状态栏显示来电信息，并附加了接听和拒接操作入口           |
      |                                                              | onCallRemoved          |                                                              |
      |                                                              | onCallStateChanged     |                                                              |
      | RespondViaSmsManager                                         | onIncomingCallRejected | 拒接来电后，发送短信                                         |
      | CallAudioManager                                             | onCallStateChanged     | 根据通话状态设置Audio音频策略                                |
      |                                                              | onCallAdded            |                                                              |
      |                                                              | onCallRemoved          |                                                              |
      | HeadsetMediaButton                                           | onCallAdded            | 耳机按钮控制事件的响应，根据通话状态进行接听或是挂断电话操作 |
      |                                                              | onCallRemoved          |                                                              |
      |                                                              | onExternalCallChanged  |                                                              |
      | CalLogManager                                                | onCallStateChanged     | 记录通话曰志                                                 |
      | PhoneStateBroadcaster                                        | onCallStateChanged     | 根据telephony.registry 服务发起通话状态改变的注册消息回调和广播的发送 |
      |                                                              | onCallRemoved          |                                                              |
      |                                                              | onExternalCallChanged  |                                                              |
      |                                                              | onCallAdded            |                                                              |
      | MissedCallNotifierlmpl                                       |                        | 未重写父类CallsManagerListenerBase的任何方法，通过对象方法调用的方式在通知栏增加未接电话的提示 |
      | ProximitySensorManager                                       | onCallRemoved          | 距离感应器处理                                               |
      | StatusBarNotifier                                            | onCallRemoved          | 状态栏静音、扬声器状态同步                                   |
      | InCallWakeLockController                                     | onCallAdded            | 电源管理                                                     |
      |                                                              | onCallRemoved          |                                                              |
      |                                                              | onCallStateChanged     |                                                              |
      | InCallController                                             | onCallAdded            | 根据通话状态调用updateCall方法，创建ParcelableCall对象，通过InCallService服务传递给Dialer应用 |
      |                                                              | onCallRemoved          |                                                              |
      |                                                              | onCallStateChanged     |                                                              |
      | Anonymous in mCallsManagerListener in BluetoothPhoneServicelmpl | onCallAdded            | 蓝牙耳机的交互                                               |
      |                                                              | onCallStateChanged     |                                                              |
      |                                                              | onCallRemoved          |                                                              |

### Call.Listener

- 拨号流程或是来电流程中，都会创建com.android.server.telecom.Call对象，此类中定义了Listener接口，主要有onSuccessfulOutgoingCall、onFailedOutgoingCall、onSuccessullncomingCall、onFailedIncomingCall、onXXXChanged，都是以onXXX开头的，并且都传递Call对象的引用，当前Call类中的内部抽象类ListenerBase实现了Listener接口的所有方法，ListenerBase 实现的所有方法没有具体的代码逻辑。
- ![image-20220629143321844](Android Telephone.assets/image-20220629143321844.png)
  - Call.Listener接口定义
    - Call. Listener接口在Call类中定义，ListenerBase 抽象类实现了此接口的所有方法，并且这些方法都没有任何的业务逻辑代码，与CallsManagerListenerBase类的设计思想是一致的。
  - ListenerBase子类
    - Telecom应用中ListenerBase总共有三个子类: CallsManager和InCallController、 IncomingCallNotifier两个匿名内部类。
  - Call对象的mListeners
    - Call对象在CallsManager中创建，同时调用call.addListener(this)逻辑，将CallsManager对象作为Call对象的mListeners中的一员; 
    - InCallController和IncomingCallNotifier两个匿名内部类对象在TelecomSystem创建的过程中将同步创建，对象名均为: mCallListener; InCallController 在绑定IInCallService 成功后调用addCall方法的过程中，调用call.addL istener(mCalL istener)逻辑，将mCallL istener添加到Call对象的mL isteners中;同理，IncomingCallNotifier在Call变化的消息回调中，将mCallistener添加到Call 对象的mListeners中。
  - CallsManager
    - CallsManager作为Call.Listener接口的子类，由Call对象触发mListeners Call 对象变化的消息回调，CallsManager对象将通过自己的mListeners,继续发出Call 对象变化的消息回调，而这一-次的消息回调将接收并处理12个对象;因此，可以将CallsManager看作Call对象变化Listener消息回调的消息中转站，将Call.Listener和CallsManagerListener这两个Listener紧密联系在一起。

### CreateConnectionResponse

- 不论拨号流程还是来电流程，Telecom 在Call对象创建完成后，都将调用其startCreateConnection方法最终完成绑定lConnectionService服务相关的操作;在此过程中将涉及CreateConnectionResponse接口对象的创建和传递过程
  - CreateConnectionResponse接口定义了两个方法: handleCreateConnectionSuccess 和handleCreateConnectionFailure，它总共有两个子类: Call 和CreateConnectionProcessor, Call 和CreateConnectionProcessor都是CreateConnectionResponse接口对象。
  - CreateConnectionProcessor的mCallResponse属性是Call 对象,ConnectionServiceWrapper的mPendingResponses将保存CreateConnectionProcessor对象列表。

## 扩展CallsManager

- CallsManager承载了对Telecom应用中Call关键消息的处理
  - 响应 ITelecomService服务调用，完成两次绑定服务处理。
  - 响应InCallAdapter服务调用，完成通话控制消息转发。
  - 响应IConnectionServiceAdapter服务调用，完成通话状态变化消息转发。
- Telecom应用接收到外界的通话关键信息后，将统一汇总到CallsManager 中处理
  - ![image-20220629151810129](Android Telephone.assets/image-20220629151810129.png)

### 记录通话日志

- CallLogManager类负责记录通话日志，它重写了CallsManagerlistener父类的onCallStateChanged方法。在响应CallsManager Listener 消息回调时，通过判断通话的发起方和通话断开的DisconnectCause将通话日志分为呼出、呼入、拒绝和未接四类。在onCallStateChanged中再调用logCall()方法，此方法先将通话日志信息封装成AddCallArgs对象，再通过LogCallAsyncTask在后台执行android.provider.CallLog对象的addCall方法，完成通话日志SQLite数据库的写入操作。

### 耳机Hook事件

- HeadsetMediaButton类负责监听耳机Hook按键的事件,在事件消息的回调响应过程中，可接收到耳机Hook按键的长按或短按事件，将其交给CallsManager对象的onMediaButton方法处理，包括接听电话、拒接电话和通话静音三个操作。
- HeadsetMediaButton的匿名内部类对象mSessionCallback注册为Android系统MediaSession的Call Back对象，在主类HeadsetMediaButton的onCallAdded、onCallRemoved和onExtermalCallChanged方法的Listener回调响应过程中，通过Handler消息MSG_ MEDIA_ SESSION_ SET _ACTIVE的处理过程设置MediaSession的激活状态。在通话的过程中需要激活MediaSession 来接收耳机Hook按键的事件;通话断开以后则需要关闭MediaSession，不再接收耳机Hook按键的事件。

### 通知栏信息同步

- IncomingCallNotifier
  - IncomingCallNotifier类负责在手机状态栏显示或隐藏来电信息，通过onCallAdded、onCallRemoved、onCallSstateChanged方法的消息回调，在updatelncomingCall方法中实现显示或隐藏来电信息
- StatusBarNotifier
  - StatusBarNotifier类负责通知栏中通话静音和扬声器状态的同步显示，仅重写了父类CallsManagerListener的onCallRemoved方法，通话断开的时候调用notifyMute或notifySpeakerphone来取消通知栏通话静音和扬声器的信息显示。
  - StatusBarNotifier类响应onCallRemoved消息回调只能取消静音或扬声器通知栏图标的显示。在打开静音或是扬声器时，CallAudioRouteStateMachine 将通过StatusBarNotifier 对象直接调用notifyMute或notifySpeakerphone方法，在通知栏上显示对应的信息。

# TeleService

- 在通话业务的交互模型中，TeleService 负责与RILJ对象进行交互，完成通话管理
  - 顺时针方向的通话管理和控制消息
    - IConnectionService服务接收Telecom应用中转的通话管理和控制请求，将请求提交给Telephony模型处理，最终与RILJ对象进行交互，完成通话管理和控制。
  - 逆时针方向的通话变化消息上报
    - Telephony业务模型与RILJ对象交互，通过Telecom应用设置的Adapter跨进程Binder对象，发出通话信息或状态发生改变的跨进程消息上报。
- TeleService 加载过程
  - TeleService系统应用常驻内存, PhoneApp.onCreate是该应用的加载入口, com.android.phone进程中则运行着以GsmCdmaPhone对象为中心的Telephony业务模型;
  - TeleService加载过程概括如下:加载Telephony业务模型(创建以GsmCdmaPhone对象为中心的核心业务对象),完成PhoneAccount初始化注册操作，发布名字为phone、 isub 的两个系统服务。
- Telephony Phone业务模型
  - GsmCdmaPhone与三大Tracker: GsmCdmaCallTracker、 ServiceStateTracker、 DcTracker的Composition (组合)关系以及Facade Pattern (门面设计模式)的应用。GsmCdmaPhone 作为门面提供了Voice Call语音通话、ServiceState 网络服务和Data Call移动数据业务三大Telephony能力。
- PhoneAccount
  - PhoneAccount作为Parcelable类型，在Telecom和TeleService两个系统应用间跨进程传递,保障了通话相关请求的正常响应，过滤掉非法、异常或不支持的通话请求消息。

- TeleService 服务
  - TeleService系统应用在加载过程中同步加载和发布两个系统服务，服务名称分别是phone和isub。PhoneInterfaceManager实现了ITelephony.aidl 接口定义，包装了GsmCdmaPhone 对象的部分能力和属性，并通过提供的系统服务供第三方应用使用。
    SubscriptionController实现了ISub.aidl 接口定义，读取siminfo数据库表中的SIM卡相关信息，
    并通过提供的系统服务供第三方应用使用，掌握slotld、phoneld、subld 的关系与区别。


## 加载过程分析

### TeleService包结构

- ![image-20220629162035207](Android Telephone.assets/image-20220629162035207.png)
  - TeleService系统应用的编译文件Android.mk依赖telephony-common.jar包,即Telephony业务模型的实现。
  - com.android.phone:TeleService:系统应用基础包，主要包括phoneApp、PhoneInterfaceManager等Java代码文件。
  - com.android.services.telephony:与lonnectionService 服务实现相关的Java 代码，如TelephonyConnection、TelephonyConnectionService 等Java代码文件。
  - AndroidManifest配置信息
    - package="com.android.phone"唯一标识应 用程序及基础包名, TeleService 系统应用的运行进程名也是com.android.phone。
    - android:sharedUserld="android.uid.phone"设置TeleService 系统应用进程时使用的用户是android.uid.phone,即radio用户，可通过adb shell ps命令查看并验证com.android.phone进程的相关信息。
    - android:persistent属性设置为true,定义此Application应用常驻内存。如果进程异常退出或被人为“杀掉”，Android 系统机制会将此应用重新唤醒。
    - android:name属性设定为PhoneApp,再结合package属性定义，可知TeleService系统应用加载入口的Java类是com.android.phone.PhoneApp。PhoneApp继承于Application类，重写了onCreate方法。onCreate方法是TeleService 系统应用的加载入口。系统加载TeleService系统应用的com.android.phone进程时,方能发起两个onCreate方法调用，以避免其他应用或进程非法加载TeleService系统应用。在PhoneApp的onCreate方法中会调用PhoneGlobals.onCreate()和TelephonyGlobals.onCreate方法

### PhoneGlobals.onCreate()

- 创建Phone 对象
  - 调用PhoneFactory类的静态方法makeDefaultPhones创建GsmCdmaPhone对象，将同步创建和初始化Telephony业务模型的核心对象，比如RILJ和各种Tracker对象。
  - 为区分HAL层与Telephony Framework 层与RIL相关的信息，com.android.phone进程空间的com.android.internal.telephony.RIL对象，统一约定为RILJ。
- CallManager 的消息注册:CallManager向所有创建的GsmCdmaPhone对象注册相关消息(双卡或多卡)。
- 初始化 ITelephony服务
  - 通过PhoneInterfaceManager的init 方法创建ITelephony.Stub 类型的Binder 对象，再通过ServiceManager.addService的调用增加系统服务ITelephony。
- 其他操作
  包括TelephonyDebugService、NotificationMgr 等初始化操作,并注册了两个广播接收器mReceiver和mSipReceiver。

### TelephonyGlobals.onCreate()

- 初始化TTY:Text Telephones (TTY)即聋哑人电话，在手机插入专用设备后支持收发文本，但需网络支持。
- 加载PhoneAcount

## Telephony Phone

- TeleService系统应用的加载过程也可以理解为Telephony业务模型的加载过程，即以创建GsmCdmaPhone对象为中心，同步创建GsmCdmaCallTracker、ServiceStateTracker、DcTracker和RILJ等关键业务对象的过程，同时向RILJ对象注册HandlerMessage回调消息。
  - 加载入口：PhoneFactory.makeDefaultPhones作为Telephony业务模型的加载入口。
  - GsmCdmaPhone 对象创建：创建GsmCdmaPhone对象，将传入对RILJ、sPhoneNotifier. TelephonyComponentFactory等对象的引用，以及Phone类型的GSM或CDMA。
  - phoneld的隐藏逻辑：for循环中创建RILJ和Phone对象并传入循环下标值作为phoneld的参数。比如双卡双待numPhones取值2,将创建两个RILJ对象和两个Phone对象，phoneld为0代表SIM卡卡槽1对应的Telephony业务模型，phoneld 为1代表SIM卡卡槽2对应的Telephony业务模型。
  - GsmCdmaPhone 对象的消息注册：调用registerXXX方法向RILJ注册RegistrantList消息，向RILJ注册GsmCdmaPhone对象的Handler Message回调消息。
  - 创建Tracker对象
    在GsmCdmaPhone构造方法中，通过TelephonyComponentFactory类的makeXXXTracker方法，创建非常关键的三个对象: GsmCdmaCallTracker、ServiceStateTracker、DcTracker,它们将分别承载Telephony业务模型中非常重要的三个业务能力: Voice Call语音通话、ServiceState网络服务和Data Call移动数据业务。

### GsmCdmaPhone

- ![image-20220629171920047](Android Telephone.assets/image-20220629171920047.png)

  - GsmCdmaPhone 对象本质
    - Phone抽象类实现PhoneInterallnterface接口并继承Handler 类，通过重写handleMessage方法实现消息的异步处理。
  - Phone 抽象类的三个子类
    - SipPhone负责Sip网络电话业务
    - GsmCdmaPhone承载CS ( Circuit Switch,电路交换)域的电信业务
    - ImsPhone承载高清语音通话业务
  - phoneld
    - phoneld对应SIM卡卡槽承载的Telephony业务模型。
  - 关键属性对象
    - mCi是RILJ对象引用，mDcTracker是DcTracker对象引用，mCT是GsmCdmaCallTracker对象引用，mSST是ServiceStateTracker对象引用。

- GsmCdmaPhone对象关键属性

  - | 属性            | 类型                | 说明                   |
    | --------------- | ------------------- | ---------------------- |
    | mRilVersion     | int                 | RIL版本号              |
    | mImei           | String              | IMEI串号               |
    | mlmeiSv         | String              | IMEI串号的软件版本     |
    | mVmNumber       | String              | 语音信箱( Voice Mail ) |
    | mCi             | RIL                 | RIL的Java服务对象      |
    | mSST            | ServiceStateTracker | 服务状态跟踪者         |
    | mCT             | GsmCdmaCallTracker  | 通话跟踪者             |
    | mDcTracker      | DcTracker           | 移动数据跟踪者         |
    | mXXXRegistrants | RegistrantList      | 14个Observer列表       |
    | mNotifier       | PhoneNotifier       | 通知Phone状态变化      |

- GsmCdmaPhone对象关键方法

  - | 分类             | 方法/接口                                                    | 说明                                        |
    | ---------------- | ------------------------------------------------------------ | ------------------------------------------- |
    | 消息处理         | registerForXXX                                               | Handler消息注册                             |
    |                  | unregisterForXXX                                             | 取消Handler消息注册                         |
    |                  | notifyXXX                                                    | 发出Handler消息通知                         |
    | 通话控制相关接口 | dial、acceptCall、rejectCal..                                | 拨号、接听来电、拒接来电等控制和管理接口    |
    |                  | setRadioPower、updateServiceLocation、disableLocationUpdates | 开关飞行模式、设置位置服务                  |
    |                  | setDataRoamingEnabled、setDataEnabled                        | 漫游移动数据设置接口和移动数据开关接口      |
    | 获取信息接口     | getForegroundCall、getBackgroundCall、getRingingCall         | 获取Call通话管理对象                        |
    |                  | getServiceState、getCellLocation                             | 获取ServiceState 驻网服务管理对象和小区信息 |
    |                  | getDataActivityState、getDataRoamingEnabled、getDataEnabled、getActiveApnTypes... | 获取移动数据状态、APN等信息                 |

### Composition (组合)关系

- GsmCdmaPhone类的构造方法可体现它与mCi、mCT、mSST、mDcTracker等对象之间的关系,它们之间具有强组合( Composition)的逻辑关系。
  - RILJ和PhoneNotifier对象的创建在PhoneFactory中完成，GsmCdmaPhone 的构造方法中则通过supper调用，将这两个对象的引用传递给父类Phone的构造方法，进行mCi和mNotifier属性的初始化。
  - mCT、mSST、mDcTracker等对象均在GsmCdmaPhone的构造方法中同步完成创建，调用这些类的构造方法时传入了this，即GsmCdmaPhone对象。


### Facade Pattern

- 门面模式(Facade Pattern)：外部与一个子系统的通信必须通过一个统一的外观对象进行，为子系统中的一组接口提供一个一致的界面，外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。门面模式又称为外观模式，它是一种对象结构型模式。
  - GsmCdmaPhone 为Facade (门面)，而GsmCdmaCallTracker、ServiceStateTracker、 DcTracker、RILJ都作为内部的子系统
  - GsmCdmaPhone对象的关键方法主要有两类:控制管理接口和信息查询接口，这些接口实现逻辑有一个规律:mXXX.doXXX/mXXX.getXXX

### Handler消息处理机制

- GsmCdmaPhone类的父类是Phone抽象类，它不仅实现了PhoneInternallnterface 接口，而且继承了Handler类，是一个自定义的Handler消息处理类。

- GsmCdmaPhone类的Handler消息处理机制

  - 基本Handler消息注册和响应机制。

    - 调用mCi.registerForXXX方法，向RILJ对象注册单个的Handler消息
    - Handler对象handleMessage接收并响应Message消息
    - 在GsmCdmaPhone类的构造方法的调用过程中，即加载Telephony业务模型的过程中，以调用mCi.registerForXXX(this，what, null)的方式向 RILJ对象发起消息注册。在GsmCdmaPhone和Phone类中均重写了父类的handleMessage方法，从而响应RILJ对象发出的Handler消息回调通知。

  - RegistrantList封装的Handler消息运行机制。

    - 在Phone抽象类中，共定义了14个RegistrantList对象。针对这14个RegistrantList 对象，分别实现了registerForXXX和unregisterForXXX方法来完成多个Handler消息的注册和取消注册

    - RegistrantL ist对象需要正常运转Handler消息处理，不仅需要提供registerForXX 和unregisterForXXX方法完成Handler消息的注册和取消注册，还需要notifyXXX方法来发出多个注册的Handler通知。

    - 在GsmCdmaPhone对象中，这些RegistrantList 对象发出消息通知的方法的实现逻辑在Phone抽象类和GsmCdmaPhone类中均有不同程度的实现。notifyXXXP 消息通知方法，它们的名称最后都有一个大写的P字母作为方法名称的结尾，P是Parent的首字母。这些方法调用时，都是由其子类调用其父类的super.notifyXXXP对应的方法。notifyXXX通知消息的调用是在GsmCdmaPhone对象提供的通信管理和控制的方法中，调用notifyXXX方法来发出Handler消息的通知。比如，MMI拨号请求、输入PIN码、请求USSD码等一系列主动请求的处理逻辑。

    - | RegistrantList列表                 | 说明                           |
      | ---------------------------------- | ------------------------------ |
      | mPreciseCallStateRegistrants       | 通话状态变化消息通知           |
      | mHandoverRegistrants               | SRVCC通话切换消息通知          |
      | mNewRingingConnectionRegistrants   | 接收到新来电请求的消息通知     |
      | mIncomingRingRegistrants           | 来电响铃消息通知               |
      | mDisconnectRegistrants             | 通话连接断开消息通知           |
      | mServiceStateRegistrants           | 服务状态变化消息通知           |
      | mMmiCompleteRegistrants            | MMI执行完毕消息通知            |
      | mMmiRegistrants                    | 执行MMI消息通知                |
      | mUnknownConnectionRegistrants      | 出现未知连接消息通知           |
      | mSuppServiceFailedRegistrants      | 附加服务请求失败消息通知       |
      | mRadioOffOrNotAvailableRegistrants | Radio状态不可用消息通知        |
      | mSimRecordsL oadedRegistrants      | SIM卡加载完成消息通知          |
      | mVideoCapabilityChangedRegistrants | 视频通话能力变化消息通知       |
      | mEmergencyCallToggledRegistrants   | Emergency callcallback消息通知 |

      - SIM卡:用户身份模块（Subscriber Identity Module，SIM），通常称为“SIM卡”或“电话卡”，是主要用于存储用户身份识别数据、短信数据和电话号码的智能卡，ICCID：Integrate circuit card identity 集成电路卡识别码即SIM卡卡号，相当于手机号码的身份证。
      - MMI是指Man Machine Interface即人机界面,MMI是进行移动通信的人与提供移动通信服务的手机之间交往的界面。包括硬件和软件。

  - 创建的Message对象作为RILJ对象回调入口。

    - GsmCdmaPhone对象在与RILJ对象的交互过程中创建Message对象，作为RILJ对象的回调入口。使用这种方式不需要向RILJ注册Hanlder消息，其生命周期很短，仅在一次交互过程中有效。单次Handler消息处理的实现机制非常灵活和简单
    - GsmCdmaPhone对象提供的方法中有一些处理逻辑。
      1. 创建基于GsmCdmaPhone对象的Message对象，然后将此对象作为参数调用mCi对象的方法;
      2. RILJ对象处理完成后，通过Message对象进行回调;
      3. 在GsmCdmaPhone对象的handleMessage 方法中接收和响应Message对象发出的Handler消息。

## 扩展PhoneAccount

- PhoneAccount在通话流程中究竟起到什么样的作用需分析TeleService系统应用加载过程中调用的TelephonyGlobals.onCreate 方法，来完成PhoneAccount 初始化操作，即TelecomAccountRegistry.getInstance(mContext).setupOnBoot()涉及的业务逻辑。
- PhoneAccount是在TeleService系统应用中创建的，注册到Telecom系统应用。
- PhoneAcount的唯一标识 为PhoneAccountHandle。
- PhoneAccountHandle通过mld即ICCID与GsmCdmaPhone对象产生了唯一关联。
- PhoneAcount的主要功能是描述GsmCdmaPhone对象的通话能力，其中最关键的是capabilities和supported_uri。
- Telecom系统应用通过拨号请求信息匹配对应的PhoneAcount (能力匹配和IConnectionService服务匹配),过滤非法或不支持的拨号请求。
- TeleService系统应用接收createConnection 请求，通过PhoneAcount 找出关联的GsmCdmaPhone对象，支持对应的通话管理和控制请求。
- PhoneAcount在Telecom和TeleService两个系统应用间流转，保障了通话相关请求的正常响应，过滤掉非法或不支持的通话请求消息。

### PhoneAccount创建

- TelecomAccountRegistry完成PhoneAccount初始化业务逻辑关键步骤
  - 创建 TelecomAccountRegistry对象
    - TelecomAccountRegistry类提供了静态同步getInstance方法，创建并获取TelecomAccountRegistry对象。TelecomAccountRegistry的构造方法中通过Context获取了三个Manager对象: TelecomManager、 TelephonyManager 和SubscriptionManager，并同步创建mUserSwitchedReceiver、
      mOnSubscriptionsChangedListener、mPhoneStateListener三个内部匿名对象。
  - 调用setupOnBoot方法
    1. setupOnBoot方法注册了两个Listener 回调和一个广播接收器，使用了三个内部匿名对象:mOnSubscriptionsChangedListener、 mPhoneStateListener和mUserSwitchedReceiver作为监听回调的响应。这三个对象回调响应逻辑全部发起了tearDownAccounts和setupAccounts的方法调用,将当前服务状态保存在STATE_ IN_ SERVICE 中，当ServiceState已发生改变时，调用tearDownAccounts方法清空已注册的PhoneAccount,接着调用setupAccounts重新设置并注册新的PhoneAccount
    2. 在setupAccounts中，通过PhoneFactory获取Phone对象数组后，再通过Phone对象创建TelecomAccountRegistry类的内部类AccountEntry对象。
    3. AccountEntry类实现了PstnPhoneCapabilitiesNotifier.Listener 接口，它只有一个方法onVideoCapabilitiesChanged,在视频电话能力变化后将进行消息回调;在AccountEntry的构造方法中，registerPstnPhoneAccount()方法将创建PhoneAccount对象，在registerPstnPhoneAccount中：
       1. 同步创建PhoneAccountHandle对象。PhoneAccountHandle对象的id通过phone.getFulllccSerialNumber 获取，即当前SIM卡的ICCID; ComponentName对象的构造方法为: new ComponentName("com.android.phone", "com.android.services.telephony.TelephonyConnectionService")，即TeleService 系统应用中的IConnectionService服务; PhoneAccountHandle对象的构造方法this(componentName,id, Process.myUserHandle())， 用来获取com.android.phone进程的UserHandle，其为SYSTEM类型。
       2. 通过GsmCdmaPhone对象获取一些基础数据，如Subld、Slotld、 SubscriptionInfo 等信息。
       3. 通过取值capabilities配置信息计算能力。
       4. 根据前面获取的信息创建PhoneAccount对象。
       5. 使用TelecomManager调用registerPhoneAccount接口注册PhoneAccount。

### Telecom对PhoneAccount注册的响应

- Telecom系统应用中PhoneAccountRegistrar 类的addOrReplacePhoneAccount 方法将响应TeleService系统应用发起的注册PhoneAccount请求
- TeleService系统应用中创建的PhoneAccount对象，在Telecom系统应用中有两种表现形式:
  - 内存， 保存在PhoneAccountRegistrar对象的mState.accounts属性列表。
  - 存储，写入XML文件。

### PhoneAccount在拨号流程中的作用

- Telecom系统应用中响应的拨号请求处理逻辑，最关键的是CallsManager对象的startOutgoingCall和placeOutgoingCall方法调用，它们分别创建Call对象和发起Connection请求，这两个方法都涉及PhoneAccount对象的使用和传递。
  - startOutgoingCall
    - CallIntentProcessor. processOutgoingCallntent作为Telecom系统应用响应拨号请求的处理入口,通过Intent获取PhoneAccountHandle对象，并调用callsManager.startOutgoingCall方法创建Call对象，因传入的PhoneAccountHandle对象为NULL,连续两次的mPhoneAccountRegistrar.getPhoneAccount方法调用均返回了NULL,从而调用constructPossiblePhoneAccounts方法获取到由PhoneAccountRegistrar保存的已注册PhoneAccount 对象
    - 调用过程: constructPossiblePhoneAccounts→mPhoneAccountRegistrar.getCallCapablePhoneAccounts-→getPhoneAccountHandles→
      getPhoneAccountHandles->getPhoneAccounts。
    - startOutgoingCall中的逻辑根据拨号请求匹配到TeleService 注册的PhoneAccount对象，然后通过call.setTargetPhoneAccount调用，将PhoneAccount与Call产生了关联。
  - placeOutgoingCall
    - placeOutgoingCall发起的拨号请求的主要调用过程: placeOutgoingCall->call.startCreate->Connection->CreateConnectionProcessor.process->attemptNextPhoneAccount。
    - CreateConnectionProcessor. attemptNextPhoneAccount方法中有关于PhoneAccount的处理逻辑，mPhoneAccountRegistrar.phoneAccountRequiresBindPermission调用最终会通过PackageManager判断Telephony提供的IConnectionService是否可用
  - createConnection
    - Telecom首先匹配到已注册的PhoneAccount,然后通过PhoneAccount判断TeleService 提供的IConnectionService服务是否可用。在这两个条件都满足的请求下，createConnection继续发起拨号请求;否则调用notifyCallConnectionFailure发出拨号失败的通知。
    - 创建的ConnectionRequest对象包含了PhoneAccount 对象,调用IConnectionService服务接口createConnection传递PhoneAccount对象和ConnectionRequest对象。
  - IConnectionService
    - 在IConnectionService 的createConnection->onCreateOutgoingConnection方法中，经过getPhoneForAccount调用最终获取了GsmCdmaPhone对象。
    - GsmCdmaPhone与PhoneAccountHandle对象通过PhoneAccountHandle的mld即ICCID与GsmCdmaPhone对象进行的关联

## TeleService服务

- TeleService系统应用中的服务类型

  - 系统级服务：TeleService系统应用加载过程中将创建和发布的系统服务，如phone、 isub 等系统服务。Android系统平台中的所有应用，均可通过ServiceManager.getService的方式获取服务的Binder 对象，从而访问系统服务接口。

  - 应用级服务：即IConnectionService应用服务,通话业务中Telecom系统应用将通过绑定服务的方式访问TeleService系统应用提供的服务。

### phone 系统服务

- phone系统服务是在TeleService系统应用加载过程中创建和发布的

- 初始化调用过程如下:PhoneApp.onCreate→PhoneGlobals.onCreate- →PhoneInterfaceManager.init
- ServiceManager. addService()添加了PhoneInterfaceManager，它继承了ITelephony . Stub，实现机制：
  - 获取GsmCdmaPhone对象进行对应的操作。
  - GsmCdmaPhone对象的操作主要有两类:发出控制请求和查询相关属性，其主要的调用方式: phone.setXXX ( phone.XXX )或phone.getXXX。

### isub系统服务

- isub系统服务是在TeleService系统应用加载过程中创建和发布的

- 其初始化调用过程：PhoneApp.onCreate→PhoneGlobals.onCreate →PhoneFactory.makeDefaultPhones →makeDefaultPhone->SubscriptionController.init.
  - ServiceManager. addService()添加了SubscriptionController，SubscriptionController实现了ISub.aidl 定义的34个接口，主要是对SubscriptionInfo的管理和查询接口，以及在多SIM卡模式下的默认卡管理接口
    - SubscriptionInfo的主要属性有: mld、mlccld、mSimSlotIndex、mDisplayName、mDataRoaming、mMcc、mMnC。
    - SubscriptionController类有两个非常重要的私有方法: getSublInfo 和getSubInfoRecord。getSubInfo方法通过SubscriptionManager.CONTENT_URI即Uri.parse("contet:/telephony/siminfo")获取查询sQLite数据库的cursor, 再由getSubInfoRecord方法使用cursor查询到的数据创建SubscriptionInfo。
- isub 系统服务都是围绕siminfo数据库表来运行的,siminfo数据库的来源
  - SubscriptionInfoUpdater
    - 在PhoneFactory加载Telephony业务模型时，将初始化SubscriptionInfoUpdater, 主要是创建广播接收器，接收TelephonyIntents.ACTION_ SIM_ STATE_CHANGED和IccCardProxy.ACTION_INTERNAL_SIM_STATE_CHANGED两个广播。
  - 响应SIM卡状态变化广播
    - Telephony 运行的业务模型将通过IccCardProxy发送SIM卡状态变化广播。

- slotld、phoneld、 subld
  - slotld:代码中以simld、SimSlotIndex、 SlotIndex 等方式出现，保存在siminfo数据库的sim_id字段中，与phoneld一一对应 ;
  - phoneld: GsmCdmaPhone对象数组的下标;
  - subld: siminfo 数据库中的“id” 字段，即siminfo数据唯一标识。
  - phoneld 与subld的对应关系可体现在SubscriptionInfo对象的mSimSlotIndex与mld属性，对应siminfo数据库的sim_id与_id 字段。

### IConnectionService应用服务

- Telecom交互模型绑定IConnectionService服务的处理机制，IConnectionService 服务接收到createConnection请求后，通过ConnectionRequest对象获取相关信息创建TelephonyConnection对象，在完成通话相关操作后，通过TelephonyConnection对象的相关信息创建ParcelableConnection对象并返回给Telecom进程。
- ![image-20220630160855082](Android Telephone.assets/image-20220630160855082.png)
  - IConnectionService核心类图的代码归属于两个库: frameworks/base/telecomm( Android Framework )和packages/services/Telephony ( TeleService系统应用)。
  - TelephonyConnectionService继承抽象类ConnectionService,运行在TeleService系统应用空间，承载IConnectionService服务; TelephonyConnection 继承抽象类Connection,同样运行在TeleService系统应用空间。
- 区分Connection
  - ConnectionRequest和ParcelableConnection对象均实现了Parcelable 接口，可跨进程在Telecom和TeleService两个系统应用进程间传递。
  - ConnectionRequest对象在Telecom 系统应用中创建，传递到TeleService系统应用;
  - ParcelableConnection对象在TeleService系统应用中创建，传递到Telecom系统应用。
  - TelephonyConnection对象在TeleService系统应用中创建，作为普通Java 对象，仅在com.android.phone进程空间运行。
- TelephonyConnection 对象的创建过程
  - 拨号流程：首先使用ConnectionRequest 对象选择将要使用的GsmCdmaPhone对象，然后再使用GsmCdmaPhone对象和ConnectionRequest对象的PhoneAcountHandle、TelecomCalldl 和Address(URI)等属性创建TelephonyConnection对象。
  - 来电流程：与拨号流程类似
- setOriginalConnection()
  - TelephonyConnectionService在成功创建TelephonyConnection对象后，通过setOriginalConnection()调用将Telephony Voice Call业务模型关联在一起。
  - 拨号流程中由TelephonyConnectionService类的onCreateOutgoingConnection方法创建TelephonyConnection对象，再调用placeOutgoingConnection方法，使用GsmCdmaPhone对象继续发起拨号请求调用,拨号请求调用后，返回com.android.internal.telephony.Connection对象，TelephonyConnection对象调用setOriginalConnection()将Telephony Voice Call 语音通话模型的Connection对象与TelephonyConnection对象产生了关联。
  - 来电流程中由TelephonyConnectionService类的onCreatelncomingConnection方法首先获取com.android.internal.telephony.Connection对象，然后再创建TelephonyConnection 对象，然后再将Connection和TelephonyConnection关联。

- TelephonyConnection消息处理机制
  - Telecom系统应用中保存着Call对象，如果当前手机正好有一路通话，不论是进行通话保持或挂断电话，在Telecom系统应用中都是通过callldl 找到对应的Call 对象，然后跨进程调用TeleService系统应用提供的IConnectionService服务接口
  - TelephonyConnectionService.createConnection方法响应Telecom系统应用发起的**接口调用**,首先创建TelephonyConnection对象，然后调用addConnection方法保存Connection对象并设置相关的Listener
  - TelephonyConnectionService响应Telecom系统应用发起的**通话控制请求**，首先是通过allld获取Connection 对象，然后调用Connection对象的onXXX方法进行通话控制的调用
  - Telecom系统应用发起通话控制请求的参数是callld。TeleService系统应用ConnectionService通过alld找到TelephonyConnection对象，并且通过此对象继续传递Telecom系统应用发起的通话控制请求。
  - 当通话状态变化后TelephonyConnectionService 通知Telecom系统应用：在connection.addConnectionListener(mConnectionListener)， 即TelephonyConnection 与TelephonyConnectionService的mConnectionListener 属性产生的Listener 消息关联中使用TelephonyConnection对象获取对应的Callld,再通过mAdapter调用Telecom系统应用中的服务来设置当前通话的最新状态。

# Voice Call语音通话模型

- Voice Call业务以GsmCdmaCallTracker为中心。

## GsmCdmaCallTracker

- GsmCdmaPhone对象将语音通话业务交给GsmCdmaCallTracker对象来管理和维护
  - 查询语音通话状态
  - 提供语音通话控制能力
- ![image-20220630174949813](Android Telephone.assets/image-20220630174949813.png)
  - CalITracker抽象类是一个自定义的Handler 消息处理类，它实现了pollCallsWhenSafe 、handleRadioAvailable等重要方法。
  - CallTracker抽象类有两个子类: GsmCdmaCallTracker 和ImsPhoneCalTracker类，分别在CS域(Circuit Switch,电路交换)域和PS ( Packet Switch,分组交换)域完成通话能力管理和控制的处理逻辑。

### 类结构解析

- 关键属性
  - mCi:为RILJ对象，类型为CommandInterface，GsmCdmaCallTracker 对象与GsmCdmaPhone对象一样，通过mCi对象具备与RIL的交互能力。
  - mState属性:体现手机的通话状态，mRingingCall、 mForegroundCall、 mBackgroundCall对象体现了通话状态及基本的通话信息。
  - mConnections:类型为GsmCdmaConnection，最多能够保存MAX_ CONNECTIONS_GSM 或MAX_CONNECTIONS_CDMA个GsmCdmaConnection通话连接对象。
  - mPhone:为GsmCdmaPhone对象，GsmCdmaPhone 与GsmCdmaCallTracker对象可相互引用对方。
- 关键方法
  - 通话控制能力：GsmCdmaCallTracker类提供语音通话控制方法来完成语音通话控制请求，如拨号请求、接听来电、拒接来电等通话能力控制请求。
  - Handler 消息处理：GsmCdmaCallTracker对象接收到RILJ对象发出的Handler消息后，能及时更新和记录当前时间点通话状态以及通话的基本信息，或是继续向RILJ对象发出请求。

### Handler消息处理方式

- Handler消息注册机制
  - GsmCdmaCallTracker 对象会被动接收并响应RILJ对象发出的通话状态变化、无线通信模块可用状态、无线通信状态不可用模块三种Handler消息
  - EVENT_CALL_STATE_CHANGE通话状态变化的Handler消息，仅在GsmCdmaCalITracker类中响应;也就是说，仅有GsmCdmaCallTracker对象会
    接收和响应RILJ对象发出的通话状态变化通知。
- Handler消息响应机制
  - 三种通话状态变化都会调用pollCallsWhenSafe()方法，在此方法中，当Voice Call 状态和Radio 状态发生改变时，RILJ 对象会向GsmCdmaCallTracker对象发出这三个对应的Handler消息; GsmCdmaCallTracker对象接收到这三个类型的Handler消息后，最终调用mCi.getCurrentCalls方法，向RILJ对象查询当前Call List (通话列表)。

- Handle消息CallBack（回调）处理方式
  - GsmCdmaPhone对象发起的通话管理和控制操作,实际上都是通过调用mCT相关的方法来完成的。mCT即GsmCdmaCallTracker对象，GsmCdmaCallTracker的通话控制方法，实现的逻辑框架基本一致, 全部采用Handler消息的回调处理机制，直接向RILJ对象发起通话管理和控制请求
    - 步骤
      - 调用 obtainCompleteMessage方法创建Message对象。
      - 使用mCi对象向RILJ对象发起通话管理和控制相关方法调用。
    - GsmCdmaCallTracker和RILJ提供的通话管理和控制方法的名称保持一致。
    - RIL对象发起消息回调后，在CallITracker对象中的响应和处理方式不同，但最终都会调用operationComplete方法处理，从字面意思理解就是完成了通话管理和控制操作(GSM网络制式)。
    - 接收到非EVENT_OPERATION_COMPLETE类型Handler消息的情况下，增加了phone.notifySuppServiceFailed方法调用;这个方法发出通话服务失败的消息通知，如无法切换通话、无法进行多方通话等消息通知。

### 与RILJ对象的交互机制

- GsmCdmaCallTracker与RILJ 对象的交互完成了通话的控制，以及通话状态和通话基本信息的保存、更新，在Telephony Call通话模型中非常重要和关键。
- 交互方式
  - CallTracker对象主动发起
  - CallTracker对象被动接收

#### CallTracker对象主动发起

- 流程
  1. GsmCdmaCallTracker对象发起dial、acceptCall. rejectCall 等通话管理和控制请求方法调用时，会调用RILJ对象提供的对应通话管理和控制方法。
  2. RIL处理完GsmCdmaCallTracker对象发出的通话管理和控制请求后,由RILJ对象使用Message消息对象发出Handler消息通知，GsmCdmaCallTracker 对象中的handleMessage方法接收和响应此消息，至此，完成了第一次的Handler消息回调处理。
  3. 接下来GsmCdmaCallTracker对象会进行第二次的Handler 消息回调处理流程，调用operationComplete方法来查询当前最新的Call List列表
  4. RIL处理完GsmCdmaCallTracker对象发出的查询当前最新的Call List 列表的请求后，由RILJ对象使用Message消息对象发出Handler消息通知, GsmCdmaCallTracker对象中的handleMessage方法接收和响应此消息，至此，完成了第二次的Handler消息回调处理。

- ![image-20220701104440855](Android Telephone.assets/image-20220701104440855.png)
  1. ConnectionService和TelephonyConnection是TeleService 系统应用中的代码，运行在com.android.phone进程空间，用来接收Telecom系统应用发起的通话控制请求，并通过Telecom alld匹配到TelephonyConnection对象。
  2. TelephonyConnection通过getPhone方法，首先获取GsmCdmaConnection对象，然后获取到GsmCdmaPhone 对象，再调用GsmCdmaPhone对象的dial、acceptCall、rejectCall 等通话管理和控制方法。
  3. GsmCdmaPhone对象接收到这些方法调用请求后，使用mCT对象(即GsmCdmaCallTracker对象)继续调用对应的通话管理和控制方法
  4. GsmCdmaCallTracker对象首先调用obtainCompleteMessage创建Message消息对象接着调用RILJ对象中对应的通话控制方法，同时传递刚创建的Message消息对象作为回调参数。RILJ对象中的通话管理和控制方法都采用了异步方式的处理机制
  5. 在RIL中完成通话管理和控制后，通过RILJ对象发起EVENT_OPERATION_COMPLETE类型的Handler Callback消息回调。
  6. GsmCdmaCallTracker对象响应EVENT_ OPERATION_ COMPLETE类型的Handler消息,将调用operationComplete方法。
  7. operationComplete方法生成EVENT_ POLL_ CALLS_RESULT类型的Message消息对象，调用mCi. getCurrentCalls开始查询CallList列表。
  8. 在RIL中完成查询Call List列表后，发起EVENT_POLL_CALLS_ RESULT类型的HandlerCallback消息回调。
  9. GsmCdmaCallTracker对象响应EVENT_ POLL_ CALLS_ RESULT 类型的Handler消息,最终调用handlePollCalls方法。
- GsmCdmaCallTracker对象向RILJ对象发起两次请求，第一次发起管理或控制通话请求，第二次发起查询当前通话列表请求。RILJ对象处理这两次请求并发起两次处理结果的Callback消息回调，两次消息回调的Handler类型分别为EVENT_OPERATION_ COMPLETE和EVENT_POLL_CALLS_RESULT。

#### CallTracker对象被动接收

- GsmCdmaCallTracker对象的Handler 消息处理机制会被动接收和处理RILJ对象上报的三种类型的Handler消息，这三个消息与通话状态的关系非常密切
- ![image-20220701105313556](Android Telephone.assets/image-20220701105313556.png)
  1. TeleService 系统应用加载Telephony业务模型创建GsmCdmaCallTracker对象的同时，向RILJ对象注册EVENT_CALL_STATE_CHANGE等三个类型的Handler消息。
  2. RILJ层接收到Modem发出的通话状态变化消息,向GsmCdmaCallTracker对象发出EVENT_CALL_ STATE_ CHANGE类型的Handler消息通知
  3. GsmCdmaCallTracker对象调用pollCallsWhenSafe方法响应EVENT_CALL STATE_CHANGE类型的Message消息。pollCallsWhenSafe方法首先会生成EVENT_POLL_CALLS_RESULT类型的Handler消息对象，接着使用此对象作为参数发起mCi.getCurrentCalls调用，向RILJ对象发出查询最新Cal lList列表的消息
  4. 在RIL中完成查询CallList列表后，发起EVENT_POLL_CALLS_RESULT类型的HandlerCallback消息回调。GsmCdmaCallTracker对象最终调用handlePollCalls方法响应EVENT_ POLL_ CALLS_ RESULT类型的Handler消息。
- CallTracker对象与RILJ 对象之间的交互不论采用什么形式，所有处理逻辑都会汇总到handlePollCalls方法，它会根据RILJ对象返回的Call List列表，更新通话状态以及发出相关的消息通知。可以说CallTracker类中的核心处理逻辑都集中到了此方法中

## handlePollCalls()方法

- GsmCdmaCallTracker 对象主动发起的通话管理或控制请求和被动接收到RILJ对象发起的通话状态变化的消息回调，都会调用mCi.getCurrentCalls方法查询Call List 获取当前所有的通话连接，查询的结果将交给handlePollCalls 方法处理。handlePollCalls方法将根据RILJ对象上报的Call List列表对象，更新GsmCdmaCallTracker类的三个Call对象和mConnections通话连接列表
- handlePollCalls()方法处理逻辑
  - 准备阶段，获取Call List列表。
  - 更新通话状态及对应信息。更新GsmCdmaCallTracker对象中的mState、mConnections、mForegroundCall、mBackground-Call和mRingingCall等属性。
  - 继续传递消息。根据当前最新的通话状态，发出通话状态变化的消息通知。

### 准备阶段

- 接收到Message消息后，获取Call List对象列表，然后声明一些重要的变量。
- RILJ 对象发给GsmCdmaCallTracker对象Handler消息中的result属性，通过强制类型转换成List列表对象。保存的是DriverCall对象列表，是在RILJ对象中创建的。

### 更新通话相关信息

1. 获取最新的DriverCall List 对象列表后，紧接着遍历mConnections保存通话连接对象列表，再通过DriverCall List 中对应的DriverCall 对象，更新GsmCdmaCallTracker对象中的通话相关信息。

2. 循环获取 conn 对象和 dc 对象：根据i数组mConnections的下标取值获取GsmCdmaConnection对象，然后通过curDC索引值获取polledCalls列表中的DriverCall对象dc,匹配DriverCall对象是通过dc.index==i+1关系来完成的，如果无法匹配到DriverCall 对象，则进入下一次循环完成DriverCall 对象的匹配
   - 通过MAX_CONNECTIONS_GSM和MAX_CONNECTIONS_CDMA两个常量的定义，mConnections数组最多可保存19个或8个GsmCdmaConnection对象。在循环connections数组的过程中，不论获取的conn对象如何取值，都会完成19次或8次循环。
   - dc对象的index是从1开始递增的。
   
3. 通话状态的变化
   - 根据conn和dc这两个对象基本信息的组合关系，可得出四种通话状态的变化
     - 出现新的通话
     - 通话连接断开
     - 通话连接断开并且有新的来电
     - 通话状态发生变化
   
   - | 条件                                                         | 说明                                              |
     | ------------------------------------------------------------ | ------------------------------------------------- |
     | conn == null && dc != null                                   | 主动拨号后第一次查询到此通话连接 和接收到来电请求 |
     | conn != null && dc== null                                    | 通话连接已经断开                                  |
     | conn!= null && dc != null && !conn.compareTo(dc)&& isPhoneTypeGsm() | GSM网络下，通话连接断开的同时,接收到新的来电请求  |
     | conn != null && dc != null                                   | 通话状态发生了变化                                |
   
     

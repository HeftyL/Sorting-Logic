# SMS

- 简介：Short Message Service，简称SMS，是用户通过手机或其他电信终端直接发送或接收的文字或数字信息，内容以文本、数字或二进制非文本数据为主，目前，这种短消息的长度被限定在 140 byte之内 用户每次能接收和发送短信的字符数，是 160 个英文（7BIT）或数字字符，或者 70 个中文(16BIT)字符。

  - 长度：140 byte之内
  - 内容：文本、数字或二进制非文本数据为主

- 业务
  - 小区广播短消息业务

    - 无线紧急警报 WEA.

  - 点对点短消息业务
    - | **缩写** | **全称**                              | **说明**                                                     |
      | -------- | ------------------------------------- | ------------------------------------------------------------ |
      | SMS MO   | Short Message Mobile Originated       | MS->SC。是 由 移动 终 端 (MT)发 起 的短 消 息 ,描 述 GSM 系 统 中 MT 通 过 短消 息 中 心 (SMC, Short Message Center)向一个短消息实体(SME, Short Message Entity)发送短消息的能力。SME 可以是 MT,也可以是固网用户, 联网 PC 等. |
      | SMS MT   | Short Message Mobile Terminated       | SC->MS。SMSC向MS发送短信的业务;<br/>到达移动终端的短消息,描述GSM系统从SMC 向被呼移动终端转发短消息的能力。 |
      | MS       | Mobile-Station                        | 移动站台 负责系统交换管理，控制来自或发往其他电话或数据系统的通信 |
      | SME      | Short-Message-Entity                  | 短消息实体 负责接收和发送短消息。可以位于固话系统、移动基站或其他服务中心内 |
      | SMSC     | Short Message Service Center          | 短消息服务中心 负责在移动基站和SME之间中继、存储或转发短消息 |
      | DCS      | Data Coding Scheme                    | 数据编码方案                                                 |
      | SCTS     | Service Center Time Stamp             | 服务中心的时间戳                                             |
      | MSC      | Mobile-services Switching Centre      | exchange which performs switching functions for mobile stations located in a geographical area designated as the MSC area |
      | SMS-GMSC | Gateway MSC For Short Message Service | function of an MSC capable of receiving a short message from an SC, interrogating an HLR for routing information and SMS info, and delivering the short message to the VMSC or the SGSN of the recipient MS |
      | SMR      |                                       | Short Message Relay (entity)                                 |
      
    - ![[image-20230322163101973.png]]

      - 短信类型
        - SMS-DELIVER：conveying a short message from the SC to the MS;
        - SMS-DELIVER-REPORT
          - failure cause (if necessary);
          - information as part of a positive or negative acknowledgement to an SMS-DELIVER or SMS-STATUS- REPORT;
        - SMS-SUBMIT：conveying a short message from the MS to the SC;
          - SCA：service center address
          - MTI：message type indicator
          - RD：rejected duplicates
          - VPF：validity period format
          - RP：reply path
          - UDHI：user data header indicator
          - SRR：status report request
          - MR：message reference
          - DA：destination address
          - PID：protocol identifier
          - DCS：data code schema
          - VP：validity period
          - UDL：user data length
          - UD：user data
        - SMS-SUBMIT-REPORT
          - failure cause (if necessary);
          - information as part of a positive or negative acknowledgement to an SMS-SUBMIT or SMS- 
            COMMAND;
        - SMS-STATUS-REPORT：conveying a status report from the SC to the MS;
        - SMS-COMMAND： conveying a command from the MS to the SC.

- 层次结构

  - 概览：![[image-20230322152317602.png]]
    - SM‑AL：Short Message Application Layer
      SM‑LL：Short Message Lower Layers
      SM‑RL：Short Message Relay Layer
      SM‑RP：Short Message Relay Layer Protocol
      SM‑RS：Short Message Relay Service
      SM‑TL：Short Message Transfer Layer
      SM‑TP：Short Message Transfer Layer Protocol
      SM‑TS：Short Message Transfer Service 
      SSN：Sub‑System Number
      TPDU：Transfer protocol data unit

- 流程

  - MO![[image-20230322155829781.png]]
    - Acknowledgement![[image-20230322160019671.png]]

  - MT![[image-20230322155447956.png]]
    - Acknowledgement![[image-20230322155733881.png]]

- 短信发送流程：app->framework->ril->modem

- IMS:IP多媒体子系统（IP Multimedia Subsystem，IMS）或IP多媒体核心网络子系统（IP Multimedia Core Network Subsystem, IMCNS）是一个基于互联网协议提供多媒体业务的体系架构。传统移动电话使用类电路交换网络提供语音通话服务，而非使用计算机分组交换通信方式的网络。虽然已有很多方式在智能手机上提供网络电话与其他互联网多媒体服务，但并未形成行业标准，IMS则为此提供了一个标准化体系架构。IMS的最初的版本（3GPP Rel-5）主要是给出了一种基于GPRS来实现互联网协议多媒体业务的方法。在这个版本的基础上，3GPP、3GPP2以及TISPAN进行了进一步的更新，以支持GPRS之外，诸如WLAN、CDMA2000和固定电话线等其他接入方式。
  - 会话发起协议（Session Initiation Protocol，缩写SIP）:是一个由IETF MMUSIC工作组开发的协议，作为标准被提议用于创建，修改和终止包括视频，语音，即时通信，在线游戏和虚拟现实等多种多媒体元素在内的交互式用户会话。2000年11月，SIP被正式批准成为3GPP信号协议之一，并成为IMS体系结构的一个永久单元。SIP与H.323一样，是用于VoIP最主要的信令协议之一。
  - 富通信解决方案（Rich Communication Services，缩写：RCS）是由GSM协会发起的、旨在创建基于IP Multimedia Subsystem(IMS)基础上进一步丰富运营商通信服务的计划。
    - 强化的电话簿(Enhanced Phonebook): 增加联系人信息例如在线状态(presence)与服务探索(service discovery)。
    - 强化的消息(Enhanced Messaging): 增加多种的消息选择方案，例如聊天室、表情符号、位置分享与文件分享。
    - 丰富化的通话(Enriched Calls): 在通话过程中增加多媒体内容分享，像是影音通话或是屏幕分享等。

## 协议

- 技术规范(TS，Technical Specification)和技术报告(TR，Technical Report)
- 3GPP
  - 第三代合作伙伴计划( 3GPP ) 是许多标准组织的总称，这些标准组织为移动电信开发协议。
    - GSM和相关的2G和2.5G标准，包括GPRS和EDGE
    - UMTS和相关3G标准，包括HSPA和HSPA+
    - LTE和相关4G标准，包括LTE Advanced和LTE Advanced Pro
    - 5G NR和相关5G标准，包括5G-Advanced
    - 以接入独立方式开发的演进IP 多媒体子系统(IMS)
  - 成员
    - 组织伙伴（OP，Organizational Partner）
    - 市场代表伙伴（MRP，Market Representation Partners）
    - 个体会员（也称独立会员，IM，Individual Members）
  - 组织结构
    - 项目合作组（PCG，Project Cooperation Group）：主要负责3GPP总的管理、时间计划、工作分配、事务协调等
      - 技术规范组（TSG，Technology Standards Group）：TSG主要负责技术方面的工作。TSG可以根据工作需要，新建工作组。
        - 工作组 (WG，Work Group)：承担具体的任务。

### SM-TL服务

- 格式

  1. SMS-SUBMIT

     - | SCA          | PDU Type | MR   | DA   | PID  | DCS  | VP    | UDL  | UD    |
       | ------------ | -------- | ---- | ---- | ---- | ---- | ----- | ---- | ----- |
       | 1-12(octets) | 1        | 1    | 2-12 | 1    | 1    | 0,1,7 | 1    | 0-140 |

  2. SMS-DELIVER

     - | SCA  | PDUType | OA   | PID  | DCS  | SCTs | UDL  | UD    |
       | ---- | ------- | ---- | ---- | ---- | ---- | ---- | ----- |
       | 1-12 | 1       | 2-12 | 1    | 1    | 7    | 1    | 0-140 |

#### SCA(DA or OA)

- | 长度 | 1 Octet                                         | 1Octet  | 0~10 Octets          |
  | ---- | ----------------------------------------------- | ------- | -------------------- |
  | 定义 | Len                                             | Type    | Addr                 |
  | 说明 | SCA长度(不包括自身len，表示的是type+addr的长度) | SCA类型 | SCA地址              |
  | 举例 | 08                                              | 91      | 68 31 08 20 05 05 FO |
  
  - Each address field of the SM-TL consists of the following subfields: An AddressLength field of one octet, a TypeofAddress field of one octet, and one AddressValue field of variable length; 

#### TPDU

- SMS-SUBMIT type

  - Basic elements of the SMS-SUBMIT type:

  - | Abbr.   | Reference                     | p1)  | P2)   | Description                                                  |
    | ------- | ----------------------------- | ---- | ----- | ------------------------------------------------------------ |
    | TP-MTI  | TP-Message-Type-Indicator     | M    | 2b    | Parameter describing the message type.                       |
    | TP-RD   | TP-Reject-Duplicates          | M    | b     | Parameter indicating whether or not the SC shall accept an SMSSUBMIT for an SM still held in the SC which has the same TPMR and the same TPDA as a previously submitted SM from the same OA |
    | TP-VPF  | TP-Validity-Period-Format     | M    | 2b    | Parameter indicating whether or not the TPVP field is present. |
    | TP-RP   | TP-ReplyPath                  | M    | b     | Parameter indicating the request for Reply Path.             |
    | TP-UDHI | TP-User-Data-Header-Indicator | O    | b     | Parameter indicating that the TPUD field contains a Header.  |
    | TP-SRR  | TP-Status-Report-Request      | O    | b     | Parameter indicating if the MS is requesting a status report. |
    |         |                               |      |       |                                                              |
    | TP-MR   | TP-Message-Reference          | M    | I     | Parameter identifying the SMSSUBMIT.                         |
    | TP-DA   | TP-Destination-Address        | M    | 2~12o | Address of the destination SME.                              |
    | TP-PID  | TP-Protocol-Identifier        | M    | o     | Parameter identifying the above layer protocol, if any.      |
    | TP-DCS  | TP-Data-Coding-Scheme         | M    | o     | Parameter identifying the coding scheme within the TPUserData. |
    | TP-VP   | TP-Validity-Period            | O    | o/7o  | Parameter identifying the time from where the message is no longer valid. |
    | TP-UDL  | TP-User-Data-Length           | M    | I     | Parameter indicating the length of the TP User Data field to follow. |
    | TP-UD   | TP-User-Data                  | O    | 3)    |                                                              |

    - 1)Provision;	Mandatory (M) or Optional (O).
    - 2)Representation;	Integer (I), bit (b), 2 bits (2b), Octet (o), 7 octets (7o), 212 octets (212o).
    - 3):Dependent on the TP-DCS.
    - NOTE:	Any unused bits shall be set to zero by the sending entity and shall be ignored by the receiving entity.

- SMS-DELIVER type

  - | Abbr.   | Reference                     | p1)  | R2)  | Description                                                  |
    | ------- | ----------------------------- | ---- | ---- | ------------------------------------------------------------ |
    | TP-MTI  | TP-Message-Type-Indicator     | M    | 2b   | Parameter describing the message type.                       |
    | TP-MMS  | TP-More-Messages-to-Send      | M    | b    | Parameter indicating whether or not there are more messages to send |
    | TP-LP   | TP-Loop-Prevention            | O    | b    | Parameter indicating that SMS applications should inhibit forwarding or automatic message generation that could cause infinite looping. |
    | TP-RP   | TP-Reply-Path                 | M    | b    | Parameter indicating that Reply Path exists.                 |
    | TP-UDHI | TP-User-Data-Header-Indicator | O    | b    | Parameter indicating that the TPUD field contains a Header   |
    | TP-SRI  | TP-Status-Report-Indication   | O    | b    | Parameter indicating if the SME has requested a status report. |
    | TP-OA   | TP-Originating-Address        | M    | 212o | Address of the originating SME.                              |
    | TP-PID  | TP-Protocol-Identifier        | M    | o    | Parameter identifying the above layer protocol, if any.      |
    | TP-DCS  | TP-Data-Coding-Scheme         | M    | o    | Parameter identifying the coding scheme within the TP-User-Data. |
    | TP-SCTS | TP-Service-Centre-Time-Stamp  | M    | 7o   | Parameter identifying time when the SC received the message. |
    | TP-UDL  | TP-User-Data-Length           | M    | I    | Parameter indicating the length of the TPUserData field to follow. |
    | TP-UD   | TP-User-Data                  | O    | 3)   |                                                              |

    1. Provision;      Mandatory (M) or Optional (O).
    2. Representation; Integer (I), bit (b), 2 bits (2b), Octet (o), 7 octets (7o), 2- 12 octets (2- 12o).
    3. Dependent on the TP- DCS

- SMS-DELIVER-REPORT for RP-ERROR

  - | Abbr.   | Reference                      | P1)  | P2)   | Description                                                  |
    | ------- | ------------------------------ | ---- | ----- | ------------------------------------------------------------ |
    | TP-MTI  | TPMessageTypeIndicator         | M    | 2b    | Parameter describing the message type                        |
    | TP-UDHI | TP-User-Data-Header-Indication | O    | b     | Parameter indicating that the TP-UD field contains a Header  |
    | TP-FCS  | TP-Failure-Cause               | M    | I     | Parameter indicating the reason for SMSDELIVER failure       |
    | TP-PI   | TP-Parameter-Indicator         | M    | o     | Parameter indicating the presence of any of the optional parameters which follow |
    | TP-PID  | TP-Protocol-Identifier         | O    | o     | see clause 9.2.3.9                                           |
    | TP-DCS  | TP-Data-Coding-Scheme          | O    | o     | see clause 9.2.3.10                                          |
    | TP-UDL  | TP-User-Data-Length            | O    | o     | see clause 9.2.3.16                                          |
    | TP-UD   | TP-User-Data                   | O    | 3) 4) | see clause 9.2.3.24                                          |

    - 4) The TPUserData field in the SMS-DELIVE-RREPORT is only available for use by the MT.

- SMS-DELIVER-REPORT for RPACK

  - | Abbr    | Reference                      | P1)  | p2)   | Description                                                  |
    | ------- | ------------------------------ | ---- | ----- | ------------------------------------------------------------ |
    | TPMTI   | TP-Message-Type-Indicator      | M    | 2b    | Parameter describing the message type                        |
    | TP-UDHI | TP-User-Data-Header-Indication | O    | b     | Parameter indicating that the TP-UD field contains a Header  |
    | TP-PI   | TP-Parameter-Indicator         | M    | o     | Parameter indicating the presence of any of the optional parameters which follow |
    | TP-PID  | TP-Protocol-Identifier         | O    | o     | see clause 9.2.3.9                                           |
    | TP-DCS  | TP-Data-Coding-Scheme          | O    | o     | see clause 9.2.3.10                                          |
    | TP-UDL  | TP-User-Data-Length            | O    | o     | see clause 9.2.3.16                                          |
    | TP-UD   | TP-User-Data                   | O    | 3) 4) | see clause 9.2.3.24                                          |

    - 4)	The TPUserData field in the SMSDELIVERREPORT is only available for use by the MT.

  - | Bit No. | 7    | 6    | 5    | 4    | 3    | 2    | 1    | 0    |
    | ------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
    | 发送方  | RP   | UDHI | SRR  | VPF  | VPF  | RD   | MTI  | MTI  |
    | 接收方  | RP   | UDHI | SRI  | \    | \    | MMS  | MTI  | MTI  |

- 参数说明

  1. TP-RP:应答路径(Reply Path)
    - 0-未设置;
    - 1-设置
  2. TP-UDHI:用户数据头标识(User Data Header Indicator)
    - 0- 用户数据(UD)部分不包含头信息
    - 1- 用户数据(UD)开始部分包含用户头信息,长短信时会设置为1,UHD头信息一般占 UD 的 6 个字节
  3. TP-SRR:请求状态报告(Status Report Request)
    - 0- 不需要报告
    - 1- 需要报告
  4. TP-SRI:状态报告指示(Status Report Indication),此值仅被短消息中心(SMSC)设置
    - 0- 状态报告将不会返回给短消息实体(SME)
    - 1- 状态报告将返回给短消息实体(SME)
  5. TP-VPF:有效期格式(Validity Period Format)
    - 00-VP 段没有提供(长度为 0)
    - 01-保留
    - 10-VP 段以整形形式提供(相对的)
    - 11-VP 段以 8 位组的一半(semi-octet)形式提供(绝对的)
  6. TP-RD:拒绝复本(Reject Duplicate)
    - 0- 通知短消息服务中心(SMSC)接受一个 SMS-SUBMIT,即使该消息是先前已提交过的,并还存在于 SMSC 中未发送出去。MS重复的条件是:消息参考(MR)、接收方地址(DA)及发送方地址(OA)相同
    - 1- 通知 SMSC 拒绝一个重复的 SMS
  7. TP-MMS:有更多的信息需要发送时,此值被 SMSC 设置
    - 0- 在 SMSC 中有更多的信息等待 MS
    - 1- 在 SMSC 中没有更多的信息等待 MS
  8. TP-MTI:信息类型指示(Message Type Indicator)
    - 00-SMS-DELIVER (SMSC - MS)
    - 00-SMS- DELIVER REPORT (MS - SMSC)
    - 01-SMS- SUBMIT(MS - SMSC)
    - 01-SMS- SUBMIT REPORT (SMSC - MS)
    - 10- SMS- STATUS REPORT (SMSC - MS)
    - 10- SMS-COMMAND (MS - SMSC)
    - 11-保留
  9. TP-MR    信息参考(Message    Reference)
     - 表示MS给SC提交的TPDU的Reference   Number，用于SC区分MS提交的TPDU； 
       每提交一个SMS-SUBMIT或SMS-COMMAND,   MS给TP-MR递增  1;
  10. TP-DCS   数据编码方案(Data    Coding   Scheme)
  11. TP-VP    信息有效期(Validity    Period)
      - 根据TP-VPF    的设置其相应的格式（占用字节：0,1,7o）
  12. TP-SCTS    服务中心时间戳(Service   Center    Time    Stamp) 
      - SC收到短信的时间，占用7个字节。

#### TP-DCS 数据编码方案(Data    Coding   Scheme)

- ![[image-20230323000544236.png]]
- GSM 7 bit default alphabet
  - ![[image-20230323001146648.png]]
  - 一条短信UD内容为140 octets,7bit编码是一个字符用 7 个bit位来编，140*8bit/7bit=160 ，一页短信如果是 7bit的编码方式，可以存放 160 个字符
  - 长短信 7bit
    - 长短信包含UDH，一般为6 octets。故长短信的 7Bit编码一条消息里面最多可以发送 153 个字符.
      - 140-6    =    134 octets
      - （134*8）/    7    =   153    +    1bit
- GSM  7  bit  default  alphabet  extension  table
  - ![[image-20230323001705097.png]]
  - 扩展7bit字符表,编码特殊字符    ，每个字符用2个7bit来编码，可存80个 扩展7 bit字符。
  - 在编码的过程中，如果发现字符不在default table里，而是在extension table里，那就先加入一个0x1B，之后继续添加字符在extension table里的位置就可以了。同理，在解码的时候，如果发现0x1B，那就说明后面的字符在extension    table里面
- 8BIT/16BIT (UCS2)编码
  - UCS2 编码是一个字符用  16 个bit位来，一条非长短信是，可以存放  70  个字符。如果是长短信 ，最多可以存放  67 个unicode字符。

#### TP-UD 用户数据(User Data)

- UD包括UDH用户数据头＋SM短信内容，即：UD   = UDH + SM.
  - 根据TP-UDHI参数可以判断UD是否包含UDH
- 结构
  1. 7bit编码
     - ![[image-20230323095814354.png]]
       - 7BIT编码可能存在填充字节 Fill Bits。一条短信最长是 140 个byte，如果UDH占用6个byte，那SM 部分还剩134个byte，也就是134*8    =    1072   个bit位，如果用7bit编码，那就是 1072 / 7 =153  余上个 1bit   ，也就是 153 GSM characters 和1bit. 那多出来的这个  1    bit   就是    fill   bit，填充到SM的第一个byte的最后一个bit位，
  2. 8bit/16bit编码
     - ![[image-20230323095900815.png]]

##### 长短信（Concatenated   Short   Message）

- 普通短信UD区长度为 140 字节，超过 140 字节就得拆分成多条短信，拆分后的短信经过短信网关、短信中心的存储转发后到达终端的顺序可能和原发送顺序不同，这就使得接收方对于信息内容的理解出现困扰，1996 年颁布GSM03.40V4.13.0 中定义了长短信（Concatenated Short Message），所谓的长短信就 
  是由一组相互独立的不超过普通短信长度的子短信组成，在网络传输中被视为多条普通短信，而在终端上被合并显示。

- | PDU-Type | 普通短信       | 长短信                                  |
  | -------- | -------------- | --------------------------------------- |
  | TP-UDHI  | 0              | 1                                       |
  | TP-MMS   | 1              | 最后一条为1其余为0                      |
  | TP-UDL   | 短信内容长度   | UDH用户数据头长度＋SM拆分后短信内容长度 |
  | TP-UD    | 全部为短信内容 | UDH用户数据头＋SM拆分后短信内容         |

- UDH = UDHL+IE

  - | UDHL | IEl  | IEDL | IED           |             |            |
    | ---- | ---- | ---- | ------------- | ----------- | ---------- |
    |      |      |      | Reference No. | Maximum No. | curent No. |
    | 05   | 00   | 03   | C6            | 02          | 01         |

    1. IE用户数据头信息单元（Information    Element）

    2. UDHL用户数据头长度（User    Data   Header   Length）

       - 一个字节，表示用户数据头的字节数，不包含本字节，UDHL+1=整个用户数据头长度

    3. IEI信息单元标识（Information   Element    Identifier）

       1. 00：Concatenated    short   messages
       2. 08：Concatenated    short   message    [16-bit   reference    number]
          - 00 和  08 都表示长短信，区别在于  00 是用一个字节表示“参考编号”，而  08  用两个字节表示“参考编 
            号”

    4. IEDL信息单元数据长度（Length    of   Information    Element）

       - 一个字节，表示信息单元长度，也就是IED的字节数，不包含本字节

    5. 信息单元数据（Information Element   Data）,IEI为  00  或  08  时:

       - | 字段                              | 说明                       |
         | --------------------------------- | -------------------------- |
         | 参考编号（Reference    Number）   | 参考编号相同的为同一长短信 |
         | 总条数（Maximum   number）        | 长短信拆分后的总条数       |
         | 当前短信序号（Current    number） | 当前短信是长短信中的第几条 |

### SM‐RL服务

- The Short Message Relay Layer (SM‑RL) provides a service to the Short Message Transfer Layer (SM‑TL). This 
  service enables the SM‑TL to send Transfer Protocol Data Units (TPDUs) to its peer entity, receive TPDUs from 
  its peer entity and receive reports about earlier requests for TPDUs to be transferred.

- 分类

  - RP‑MO‑DATA for transferring a TPDU from MS to SC
  - RP‑MT‑DATA for transferring a TPDU from SC to MS
  - RP‑ACK for acknowledging an RP‑MO‑DATA, an RP‑MT‑DATA or an RP‑SM‑MEMORY‑AVAILABLE
  - RP‑ERROR for informing of an unsuccessful RP‑MO‑DATA or an RP‑MT‑DATA transfer attempt
  - RP‑ALERT‑SC for alerting the SC that the MS has recovered operation (information sent from the HLR to the SC)RP‑SM‑MEMORY‑AVAILABLE for notifying the network that the MS has memory available to accept one or more short messages (information sent from the MS to the HLR)
    - Home Location Register (HLR)：归属位置寄存器 (HLR) 是一个数据库，其中包含有关被授权使用全球移动通信系统 (GSM) 网络的用户的相关数据。存储在 HLR 中的一些信息包括每个订阅的国际移动用户标识 (IMSI) 和移动台国际用户目录号码 (MSISDN)。

- Messages for short message and notification transfer on SM‐RL

  - RP‐DATA：A phase 2 entity shall not reject a RP‑DATA message where both address elements have a length greater than 0.

    - RP‐DATA (Network to Mobile Station).This message is sent in MSC ‑> MS direction. The message is used to relay the TPDUs

    - RP‐DATA (Mobile Station to Network).This message is sent in MS ‑> MSC direction. The message is used to relay the TPDUs

    - | Information element    | Reference         | Presence | Format | Length        |
      | ---------------------- | ----------------- | -------- | ------ | ------------- |
      | RP Message Type        | Subclause 8.2.2   | M        | V      | 3 bits        |
      | RP Message Reference   | Subclause 8.2.3   | M        | V      | 1 octet       |
      | RP Originator Address  | Subclause 8.2.5.1 | M        | LV     | 1-12 octets   |
      | RP Destination Address | Subclause 8.2.5.2 | M        | LV     | 1 octet       |
      | RP User Data           | Subclause 8.2.5.3 | M        | LV     | <= 233 octets |

  - RP-SMMA:This message is sent by the mobile station to relay a notification to the network that the mobile has memory available to receive one or more short messages. 

  - RP-ACK:This message is sent between the MSC and the mobile station in both directions and used to relay the acknowledgement of a RPDATA or RPSMMA message reception. 

    - | IEI  | Information element  | Reference         | Presence | Format | Length        |
      | ---- | -------------------- | ----------------- | -------- | ------ | ------------- |
      |      | RP Message Type      | Subclause 8.2.2   | M        | V      | 3 bits        |
      |      | RP Message Reference | Subclause 8.2.3   | M        | V      | 1 octet       |
      | 41   | RP-User Data         | Subclause 8.2.5.3 | O        | TLV    | <= 234 octets |

  - RP-ERROR:This message is sent between the MSC and the mobile station in both directions and used to relay an error cause from an erroneous short message or notification transfer attempt.

    - | IEI  | Information element  | Reference         | Presence | Format | Length        |
      | ---- | -------------------- | ----------------- | -------- | ------ | ------------- |
      |      | RP Message Type      | Subclause 8.2.2   | M        | V      | 3 bits        |
      |      | RP Message Reference | Subclause 8.2.3   | M        | V      | 1 octet       |
      |      | RP Cause             | Subclause 8.2.5.4 | M        | LV     | 2 - 3 octets  |
      | 41   | RP User Data         | Subclause 8.2.5.3 | O        | TLV    | <= 234 octets |

## 发送流程

### packages

#### apps

1. 打开短信应用会进入ConversationListActivity，位于packages/apps/Messaging/src/com/android/messaging/ui/conversationlist/ConversationlistActivity.java

  - ConversationListActivity的布局文件是单独一个fragment：ConversationListFragment.java，位于packages/apps/Messaging/src/com/android/messaging/ui/conversationlist/ConversationListFragment.java‘

2. 在ConversationListFragment的布局文件中定义了新对话的按钮，在onCreateView()中，为右下角的新的对话绑定点击事件

  - ```xml-dtd
    <!--packages/apps/Messaging/res/layout/conversation_list_fragment.xml -->
    <ImageView
        style="@style/ConversationListFragmentStartNewButtonStyle"
        android:id="@+id/start_new_conversation_button"
        android:layout_width="@dimen/fab_size"
        android:layout_height="@dimen/fab_size"
        android:layout_gravity="bottom|end"
        android:layout_marginBottom="@dimen/fab_bottom_margin"
        android:paddingBottom="@dimen/fab_padding_bottom"
        android:background="@drawable/fab_new_message_bg"
        android:elevation="@dimen/fab_elevation"
        android:scaleType="center"
        android:src="@drawable/ic_add_white"
        android:stateListAnimator="@animator/fab_anim"
        android:contentDescription="@string/start_new_conversation"/>
    ```

  - ```java
    //packages/apps/Messaging/src/com/android/messaging/ui/conversationlist/ConversationListFragment.java
    private ImageView mStartNewConversationButton;
    private ConversationListFragmentHost mHost;
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                final Bundle savedInstanceState) {
        mStartNewConversationButton = (ImageView) rootView.findViewById(
                R.id.start_new_conversation_button);
        if (mArchiveMode) {
            mStartNewConversationButton.setVisibility(View.GONE);
        } else {
            mStartNewConversationButton.setVisibility(View.VISIBLE);
            mStartNewConversationButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View clickView) {
                    mHost.onCreateConversationClick();
                }
            });
        }
    }
    ```

3. ConversationListFragmentHost是一个接口，实现是AbstractConversationListActivity

  - AbstractConversationListActivity：Base class for many Conversation List activities. This will handle the common actions of multi select and common launching of intents.

  - ```java
    //packages/apps/Messaging/src/com/android/messaging/ui/conversationlist/AbstractConversationListActivity.java
    @Override
    public void onCreateConversationClick() {
        //Launch an activity to start a new conversation
        UIIntents.get().launchCreateNewConversationActivity(this, null);
    }
    ```

4. UIIntents是一个接口，UIIntentsImpl实现了它

  - UIIntentsImpl：A central repository of Intents used to start activities.

  - ```java
    //packages/apps/Messaging/src/com/android/messaging/ui/UIIntentsImpl.java
    @Override
    public void launchCreateNewConversationActivity(final Context context,
            final MessageData draft) {
        final Intent intent = getConversationActivityIntent(context, null, draft,
                false /* withCustomTransition */);
        context.startActivity(intent);
    }
    ```

  - ```java
    /**
     * Get an intent which takes you to a conversation
     */
    private Intent getConversationActivityIntent(final Context context,
            final String conversationId, final MessageData draft,
            final boolean withCustomTransition) {
        final Intent intent = new Intent(context, ConversationActivity.class);
    
        // Always try to reuse the same ConversationActivity in the current task so that we don't
        // have two conversation activities in the back stack.
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    
        // Otherwise we're starting a new conversation
        if (conversationId != null) {
            intent.putExtra(UI_INTENT_EXTRA_CONVERSATION_ID, conversationId);
        }
        if (draft != null) {
            intent.putExtra(UI_INTENT_EXTRA_DRAFT_DATA, draft);
    
            // If draft attachments came from an external content provider via a share intent, we
            // need to propagate the URI permissions through to ConversationActivity. This requires
            // putting the URIs into the ClipData (setData also works, but accepts only one URI).
            ClipData clipData = null;
            for (final MessagePartData partData : draft.getParts()) {
                if (partData.isAttachment()) {
                    final Uri uri = partData.getContentUri();
                    if (clipData == null) {
                        clipData = ClipData.newRawUri("Attachments", uri);
                    } else {
                        clipData.addItem(new ClipData.Item(uri));
                    }
                }
            }
            if (clipData != null) {
                intent.setClipData(clipData);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        }
        if (withCustomTransition) {
            intent.putExtra(UI_INTENT_EXTRA_WITH_CUSTOM_TRANSITION, true);
        }
    
        if (!(context instanceof Activity)) {
            // If the caller supplies an application context, and not an activity context, we must
            // include this flag
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return intent;
    }
    ```

5. 创建新的消息，进入ConversationActivity，位于ui/conversation/ConversationActivity.java	

   - 在onCreate()中调用updateUiState()创建ConversationFragment对象

   - ```java
     //packages/apps/Messaging/src/com/android/messaging/ui/conversation/ConversationActivity.java
     @Override
     protected void onCreate(final Bundle savedInstanceState) {
         // Don't animate UI state change for initial setup.
         updateUiState(false /* animate */);
     }
     
     private void updateUiState(final boolean animate) {
         // Set up the conversation fragment.
         if (needConversationFragment) {
             Assert.notNull(conversationId);
             if (conversationFragment == null) {
                 conversationFragment = new ConversationFragment();
                 fragmentTransaction.add(R.id.conversation_fragment_container,
                         conversationFragment, ConversationFragment.FRAGMENT_TAG);
             }
             final MessageData draftData = intent.getParcelableExtra(
                     UIIntents.UI_INTENT_EXTRA_DRAFT_DATA);
             if (!needContactPickerFragment) {
                 // Once the user has committed the audience,remove the draft data from the
                 // intent to prevent reuse
                 intent.removeExtra(UIIntents.UI_INTENT_EXTRA_DRAFT_DATA);
             }
             conversationFragment.setHost(this);
             conversationFragment.setConversationInfo(this, conversationId, draftData);
         } else if (conversationFragment != null) {
             // Don't save draft to DB when removing conversation fragment and switching to
             // contact picking mode.  The draft is intended for the new group.
             conversationFragment.suppressWriteDraft();
             fragmentTransaction.remove(conversationFragment);
         }
     }
     ```

6. 在ConversationFragment的onCreateView获得ComposeMessageView并bind

   - ConversationFragment的布局文件中id message_compose_view_container引用自compose_message_view.xml，在compose_message_view布局文件中包含send_message_button按钮

     - ```xml-dtd
       <!--packages/apps/Messaging/res/layout/conversation_fragment.xml-->
       <!-- Attachments to send, compose message view, media picker. -->
       <include layout="@layout/compose_message_view"
           android:id="@+id/message_compose_view_container"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"/>
       ```

     - ```xml-dtd
       <!--packages/apps/Messaging/res/layout/compose_message_view.xml-->
       <ImageButton
           android:id="@+id/send_message_button"
           android:layout_width="@dimen/conversation_message_contact_icon_size"
           android:layout_height="@dimen/conversation_message_contact_icon_size"
           android:src="@drawable/ic_send_light"
           android:background="@drawable/send_arrow_background"
           android:contentDescription="@string/sendButtonContentDescription"
           android:visibility="gone" />
       ```

   - ComposeMessageView：This view contains the UI required to generate and send messages.

     - ```java
       //packages/apps/Messaging/src/com/android/messaging/ui/conversation/ConversationFragment.java
       public View onCreateView(final LayoutInflater inflater, final ViewGroup container,final Bundle savedInstanceState) {
            mComposeMessageView = (ComposeMessageView)view.findViewById(R.id.message_compose_view_container);
            // Bind the compose message view to the DraftMessageData
            mComposeMessageView.bind(DataModel.get().createDraftMessageData(mBinding.getData().getConversationId()), this);
       }
       ```

7. 在ComposeMessageView的onFinishInflate()中获取发送按钮并绑定点击事件

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

   - 在sendMessageInternal()中设置短信的内容和接收者，检查短信格式，根据结果执行相对应的操作

     - ```java
       //packages/apps/Messaging/src/com/android/messaging/ui/conversation/ComposeMessageView.java
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
                             //检查通过，将DraftMessageData对象变为MessageData对象
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
         /*首先创建DraftMessageData类的内部类对象CheckDraftForSendTask,它继承了SafeAsyncTask;接着调用此对象的executeOnThreadPool()方法触发重写父类的三个方法调用onPreExecute、dolnBackgroundTimed 和onPostExecute,这几个方法的处理逻辑是发送短信的前置条件判断，最终通过mCallback.onDraftChecked调用将判断结果发送给CheckDraftTaskCallback对象。*/
         
         //packages/apps/Messaging/src/com/android/messaging/datamodel/data/DraftMessageData.java
         public void checkDraftForAction(final boolean checkMessageSize, final int selfSubId,final CheckDraftTaskCallback callback, final Binding<DraftMessageData> binding) {
              new CheckDraftForSendTask(checkMessageSize, selfSubId, callback, binding).executeOnThreadPool((Void) null);
         }
         
         public class CheckDraftForSendTask extends SafeAsyncTask<Void, Void, Integer> {
             public static final int RESULT_PASSED = 0;
             public static final int RESULT_HAS_PENDING_ATTACHMENTS = 1;
             public static final int RESULT_NO_SELF_PHONE_NUMBER_IN_GROUP_MMS = 2;
             public static final int RESULT_MESSAGE_OVER_LIMIT = 3;
             public static final int RESULT_VIDEO_ATTACHMENT_LIMIT_EXCEEDED = 4;
             public static final int RESULT_SIM_NOT_READY = 5;
             private final boolean mCheckMessageSize;
             private final int mSelfSubId;
             private final CheckDraftTaskCallback mCallback;
             private final String mBindingId;
             private final List<MessagePartData> mAttachmentsCopy;
             private int mPreExecuteResult = RESULT_PASSED;
         
             public CheckDraftForSendTask(final boolean checkMessageSize, final int selfSubId,
                     final CheckDraftTaskCallback callback, final Binding<DraftMessageData> binding) {
                 mCheckMessageSize = checkMessageSize;
                 mSelfSubId = selfSubId;
                 mCallback = callback;
                 mBindingId = binding.getBindingId();
                 // Obtain an immutable copy of the attachment list so we can operate on it in the
                 // background thread.
                 mAttachmentsCopy = new ArrayList<MessagePartData>(mAttachments);
         
                 mCheckDraftForSendTask = this;
             }
         
             @Override
             protected void onPreExecute() {
                 // Perform checking work that can happen on the main thread.
                 if (hasPendingAttachments()) {
                     mPreExecuteResult = RESULT_HAS_PENDING_ATTACHMENTS;
                     return;
                 }
                 if (getIsGroupMmsConversation()) {
                     try {
                         if (TextUtils.isEmpty(PhoneUtils.get(mSelfSubId).getSelfRawNumber(true))) {
                             mPreExecuteResult = RESULT_NO_SELF_PHONE_NUMBER_IN_GROUP_MMS;
                             return;
                         }
                     } catch (IllegalStateException e) {
                         // This happens when there is no active subscription, e.g. on Nova
                         // when the phone switches carrier.
                         mPreExecuteResult = RESULT_SIM_NOT_READY;
                         return;
                     }
                 }
                 if (getVideoAttachmentCount() > MmsUtils.MAX_VIDEO_ATTACHMENT_COUNT) {
                     mPreExecuteResult = RESULT_VIDEO_ATTACHMENT_LIMIT_EXCEEDED;
                     return;
                 }
             }
         
             @Override
             protected Integer doInBackgroundTimed(Void... params) {
                 if (mPreExecuteResult != RESULT_PASSED) {
                     return mPreExecuteResult;
                 }
         
                 if (mCheckMessageSize && getIsMessageOverLimit()) {
                     return RESULT_MESSAGE_OVER_LIMIT;
                 }
                 return RESULT_PASSED;
             }
         
             @Override
             protected void onPostExecute(Integer result) {
                 mCheckDraftForSendTask = null;
                 // Only call back if we are bound to the original binding.
                 if (isBound(mBindingId) && !isCancelled()) {
                     mCallback.onDraftChecked(DraftMessageData.this, result);
                 } else {
                     if (!isBound(mBindingId)) {
                         LogUtil.w(LogUtil.BUGLE_TAG, "Message can't be sent: draft not bound");
                     }
                     if (isCancelled()) {
                         LogUtil.w(LogUtil.BUGLE_TAG, "Message can't be sent: draft is cancelled");
                     }
                 }
             }
         
             @Override
             protected void onCancelled() {
                 mCheckDraftForSendTask = null;
             }
         
             /**
              * 1. Check if the draft message contains too many attachments to send
              * 2. Computes the minimum size that this message could be compressed/downsampled/encoded
              * before sending and check if it meets the carrier max size for sending.
              * @see MessagePartData#getMinimumSizeInBytesForSending()
              */
             @DoesNotRunOnMainThread
             private boolean getIsMessageOverLimit() {
                 Assert.isNotMainThread();
                 if (mAttachmentsCopy.size() > getAttachmentLimit()) {
                     return true;
                 }
         
                 // Aggregate the size from all the attachments.
                 long totalSize = 0;
                 for (final MessagePartData attachment : mAttachmentsCopy) {
                     totalSize += attachment.getMinimumSizeInBytesForSending();
                 }
                 return totalSize > MmsConfig.get(mSelfSubId).getMaxMessageSize();
             }
         }
         ```

8. 在ConversationFragment类的sendMessage()中，调用ConversationData的sendMessage()方法.将普通的文本和多媒体关联到一起

   - ```java
     //packages/apps/Messaging/src/com/android/messaging/ui/conversation/ConversationFragment.java
     
     // This binding keeps track of our associated ConversationData instance
     // A binding should have the lifetime of the owning component,
     //  don't recreate, unbind and bind if you need new data
     @VisibleForTesting
     final Binding<ConversationData> mBinding = BindingBase.createBinding(this);
     
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
     
     //packages/apps/Messaging/src/com/android/messaging/datamodel/data/MessageData.java
     /**
      * Takes all captions from attachments and adds them as a prefix to the first text part or
      * appends a text part
      */
     //packages/apps/Messaging/src/com/android/messaging/datamodel/data/MessageData.java
     public final void consolidateText() {
         final String separator = System.getProperty("line.separator");
         final StringBuilder captionText = new StringBuilder();
         //Represents a single message part. Messages consist of one or more parts which may contain either text or media.   
         MessagePartData firstTextPart = null;
         int firstTextPartIndex = -1;
         for (int i = 0; i < mParts.size(); i++) {
             final MessagePartData part = mParts.get(i);
             if (firstTextPart == null && !part.isAttachment()) {
                 firstTextPart = part;
                 firstTextPartIndex = i;
             }
             if (part.isAttachment() && !TextUtils.isEmpty(part.getText())) {
                 if (captionText.length() > 0) {
                     captionText.append(separator);
                 }
                 captionText.append(part.getText());
             }
         }
     
         if (captionText.length() == 0) {
             // Nothing to consolidate
             return;
         }
     
         if (firstTextPart == null) {
             addPart(MessagePartData.createTextMessagePart(captionText.toString()));
         } else {
             final String partText = firstTextPart.getText();
             if (partText.length() > 0) {
                 captionText.append(separator);
                 captionText.append(partText);
             }
             mParts.set(firstTextPartIndex,
                     MessagePartData.createTextMessagePart(captionText.toString()));
         }
     }
     ```

9. 在ConversationData的sendMessage()中判断并根据用户的api和sub执行对应的insertNewMessage()方法，将Message存入数据库。

  - ```java
    //packages/apps/Messaging/src/com/android/messaging/datamodel/data/ConversationData.java
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

10. 在InsertNewMessageAction中执行insertNewMessage()

  - InsertNewMessageAction used to **convert a draft message to an outgoing message**. Its writes SMS messages to the telephony db, but SendMessageAction is responsible for inserting MMS message into the telephony DB. The latter also does the actual sending of the message in the background.The latter is also responsible for re-sending a failed message.

  - ```java
    //Insert message (no listener)
    //packages/apps/Messaging/src/com/android/messaging/datamodel/action/InsertNewMessageAction.java
    public static void insertNewMessage(final MessageData message) {
        final InsertNewMessageAction action = new InsertNewMessageAction(message);
        action.start();
    }
    ```

    - ```java
      //ActionServicelmpl继承自JobIntentService,使用enqueueWork()将任务入队，在onHandleWork()中处理任务
      start方法调用过程：Action.start() -> DataModel.startActionService() ->ActionService.startAction() -> ActionServicelmpl.startAction() -> ActionServicelmpl.startServiceWithIntent（）->ActionServicelmpl.enqueueWork（） ->ActionServicelmpl.onHandleWork（）
      
      //packages/apps/Messaging/src/com/android/messaging/datamodel/action/ActionServiceImpl.java
       /**
       * Start action by sending intent to the service
       * @param action - action to start
       */
      protected static void startAction(final Action action) {
          final Intent intent = makeIntent(OP_START_ACTION);
          final Bundle actionBundle = new Bundle();
          actionBundle.putParcelable(BUNDLE_ACTION, action);
          intent.putExtra(EXTRA_ACTION_BUNDLE, actionBundle);
          action.markStart();
          startServiceWithIntent(intent);
      }
       /**
       * Queue intent to the ActionService.
       */
      private static void startServiceWithIntent(final Intent intent) {
          final Context context = Factory.get().getApplicationContext();
          final int opcode = intent.getIntExtra(EXTRA_OP_CODE, 0);
          intent.setClass(context, ActionServiceImpl.class);
          enqueueWork(context, intent);
      }
      
      public static void enqueueWork(Context context, Intent work) {
          enqueueWork(context, ActionServiceImpl.class, JOB_ID, work);
      }
      
      /**
       * {@inheritDoc}
       */
      @Override
      protected void onHandleWork(final Intent intent) {
          if (intent == null) {
              // Shouldn't happen but sometimes does following another crash.
              LogUtil.w(TAG, "ActionService.onHandleIntent: Called with null intent");
              return;
          }
          final int opcode = intent.getIntExtra(EXTRA_OP_CODE, 0);
      
          Action action;
          final Bundle actionBundle = intent.getBundleExtra(EXTRA_ACTION_BUNDLE);
          actionBundle.setClassLoader(getClassLoader());
          switch(opcode) {
              case OP_START_ACTION: {
                  action = (Action) actionBundle.getParcelable(BUNDLE_ACTION);
                  executeAction(action);
                  break;
              }
      
              case OP_RECEIVE_BACKGROUND_RESPONSE: {
                  action = (Action) actionBundle.getParcelable(BUNDLE_ACTION);
                  final Bundle response = intent.getBundleExtra(EXTRA_WORKER_RESPONSE);
                  processBackgroundResponse(action, response);
                  break;
              }
      
              case OP_RECEIVE_BACKGROUND_FAILURE: {
                  action = (Action) actionBundle.getParcelable(BUNDLE_ACTION);
                  processBackgroundFailure(action);
                  break;
              }
      
              default:
                  throw new RuntimeException("Unrecognized opcode in ActionServiceImpl");
          }
      
          action.sendBackgroundActions(mBackgroundWorker);
      }
      ```

11. 根据Action 的处理机制，将以后台异步方式调用InsertNewMessageAction对象的executeAction().使用了JobIntentService运行机制

   - ```java
     //packages/apps/Messaging/src/com/android/messaging/datamodel/action/InsertNewMessageAction.java
     /**
      * Add message to database in pending state and queue actual sending
      */
     @Override
     protected Object executeAction() {
         MessageData message = actionParameters.getParcelable(KEY_MESSAGE);
         if (message == null) {
             LogUtil.i(TAG, "InsertNewMessageAction: Creating MessageData with provided data");
             message = createMessage();
             if (message == null) {
                 LogUtil.w(TAG, "InsertNewMessageAction: Could not create MessageData");
                 return null;
             }
         }
         
         final DatabaseWrapper db = DataModel.get().getDatabase();
         final String conversationId = message.getConversationId();
     	
         //获取ParticipantData:A class that encapsulates all of the data for a specific participant in a conversation.
         final ParticipantData self = getSelf(db, conversationId, message);
         if (self == null) {
             return null;
         }
         message.bindSelfId(self.getId());
         // If the user taps the Send button before the conversation draft is created/loaded by
         // ReadDraftDataAction (maybe the action service thread was busy), the MessageData may not
         // have the participant id set. It should be equal to the self id, so we'll use that.
         if (message.getParticipantId() == null) {
             message.bindParticipantId(self.getId());
         }
     
         final long timestamp = System.currentTimeMillis();
         final ArrayList<String> recipients =
                 BugleDatabaseOperations.getRecipientsForConversation(db, conversationId);
         if (recipients.size() < 1) {
             LogUtil.w(TAG, "InsertNewMessageAction: message recipients is empty");
             return null;
         }
         final int subId = self.getSubId();
         LogUtil.i(TAG, "InsertNewMessageAction: inserting new message for subId " + subId);
         actionParameters.putInt(KEY_SUB_ID, subId);
     
         // TODO: Work out whether to send with SMS or MMS (taking into account recipients)?
         final boolean isSms = (message.getProtocol() == MessageData.PROTOCOL_SMS);
         if (isSms) {
             String sendingConversationId = conversationId;
             if (recipients.size() > 1) {
                 // Broadcast SMS - put message in "fake conversation" before farming out to real 1:1
                 final long laterTimestamp = timestamp + 1;
                 // Send a single message
                 insertBroadcastSmsMessage(conversationId, message, subId,
                         laterTimestamp, recipients);
     
                 sendingConversationId = null;
             }
     
             for (final String recipient : recipients) {
                 // Start actual sending
                 insertSendingSmsMessage(message, subId, recipient,
                         timestamp, sendingConversationId);
             }
     
             // Can now clear draft from conversation (deleting attachments if necessary)
             BugleDatabaseOperations.updateDraftMessageData(db, conversationId,
                     null /* message */, BugleDatabaseOperations.UPDATE_MODE_CLEAR_DRAFT);
         } else {
             final long timestampRoundedToSecond = 1000 * ((timestamp + 500) / 1000);
             // Write place holder message directly referencing parts from the draft
             final MessageData messageToSend = insertSendingMmsMessage(conversationId,
                     message, timestampRoundedToSecond);
     
             // Can now clear draft from conversation (preserving attachments which are now
             // referenced by messageToSend)
             BugleDatabaseOperations.updateDraftMessageData(db, conversationId,
                     messageToSend, BugleDatabaseOperations.UPDATE_MODE_CLEAR_DRAFT);
         }
         //MessagingContentProvider:A centralized provider for Uris exposed by Bugle.
         MessagingContentProvider.notifyConversationListChanged();
         ProcessPendingMessagesAction.scheduleProcessPendingMessagesAction(false, this);
     
         return message;
     }
     ```

     - ```java
       //packages/apps/Messaging/src/com/android/messaging/datamodel/action/InsertNewMessageAction.java
       数据库的获取
       final DatabaseWrapper db = DataModel.get().getDatabase();
       //packages/apps/Messaging/src/com/android/messaging/datamodel/DataModel.java
       //packages/apps/Messaging/src/com/android/messaging/datamodel/DataModelImpl.java
       //DataModelImpl继承了DataModel，实现了getDatabase()抽象方法
       private final DatabaseHelper mDatabaseHelper;
       
       @Override
       @DoesNotRunOnMainThread
       public DatabaseWrapper getDatabase() {
           // We prevent the main UI thread from accessing the database since we have to allow
           // public access to this class to enable sub-packages to access data.
           Assert.isNotMainThread();
           return mDatabaseHelper.getDatabase();
       }
       //DataModelImpl初始化的时候获得了mDatabaseHelper
       public DataModelImpl(final Context context) {
           super();
           mContext = context;
           mActionService = new ActionService();
           mDataModelWorker = new BackgroundWorker();
           mDatabaseHelper = DatabaseHelper.getInstance(context);
           mSyncManager = new SyncManager();
       }
       //packages/apps/Messaging/src/com/android/messaging/datamodel/DatabaseHelper.java
       public static final String DATABASE_NAME = "bugle_db";
       /**
        * Get a (singleton) instance of {@link DatabaseHelper}, creating one if there isn't one yet.
        * This is the only public method for getting a new instance of the class.
        * @param context Should be the application context (or something that will live for the
        * lifetime of the application).
        * @return The current (or a new) DatabaseHelper instance.
        */
       public static DatabaseHelper getInstance(final Context context) {
           synchronized (sLock) {
               if (sHelperInstance == null) {
                   sHelperInstance = new DatabaseHelper(context);
               }
               return sHelperInstance;
           }
       }
       /**
        * Private constructor, used from {@link #getInstance()}.
        * @param context Should be the application context (or something that will live for the
        * lifetime of the application).
        */
       private DatabaseHelper(final Context context) {
           super(context, DATABASE_NAME, null, getDatabaseVersion(context), null);
           mApplicationContext = context;
       }
       DATABASE_NAME字段是bugle_db，位于/data/data/com.google.android.apps.messaging/databases/bugle_db
       ///data/data/com.android.providers.telephony/databases/mmssms.db
       ```
       
     - ```java
       //packages/apps/Messaging/src/com/android/messaging/datamodel/action/InsertNewMessageAction.java
       private ParticipantData getSelf(
               final DatabaseWrapper db, final String conversationId, final MessageData message) {
           ParticipantData self;
           // Check if we are asked to bind to a non-default subId. This is directly passed in from
           // the UI thread so that the sub id may be locked as soon as the user clicks on the Send
           // button.
           final int requestedSubId = actionParameters.getInt(
                   KEY_SUB_ID, ParticipantData.DEFAULT_SELF_SUB_ID);
           if (requestedSubId != ParticipantData.DEFAULT_SELF_SUB_ID) {
               //从数据库获取或者创建ParticipantData
               self = BugleDatabaseOperations.getOrCreateSelf(db, requestedSubId);
           } else {
               String selfId = message.getSelfId();
               if (selfId == null) {
                   // The conversation draft provides no self id hint, meaning that 1) conversation
                   // self id was not loaded AND 2) the user didn't pick a SIM from the SIM selector.
                   // In this case, use the conversation's self id.
                   final ConversationListItemData conversation =
                           ConversationListItemData.getExistingConversation(db, conversationId);
                   if (conversation != null) {
                       selfId = conversation.getSelfId();
                   } else {
                       LogUtil.w(LogUtil.BUGLE_DATAMODEL_TAG, "Conversation " + conversationId +
                               "already deleted before sending draft message " +
                               message.getMessageId() + ". Aborting InsertNewMessageAction.");
                       return null;
                   }
               }
       
               // We do not use SubscriptionManager.DEFAULT_SUB_ID for sending a message, so we need
               // to bind the message to the system default subscription if it's unbound.
               final ParticipantData unboundSelf = BugleDatabaseOperations.getExistingParticipant(
                       db, selfId);
               if (unboundSelf.getSubId() == ParticipantData.DEFAULT_SELF_SUB_ID
                       && OsUtil.isAtLeastL_MR1()) {
                   final int defaultSubId = PhoneUtils.getDefault().getDefaultSmsSubscriptionId();
                   self = BugleDatabaseOperations.getOrCreateSelf(db, defaultSubId);
               } else {
                   self = unboundSelf;
               }
           }
           return self;
       }
       ```
     
     - ```java
       @DoesNotRunOnMainThread
       public static ParticipantData getOrCreateSelf(final DatabaseWrapper dbWrapper,
               final int subId) {
           Assert.isNotMainThread();
           ParticipantData participant = null;
           dbWrapper.beginTransaction();
           try {
               final ParticipantData shell = ParticipantData.getSelfParticipant(subId);
               final String participantId = getOrCreateParticipantInTransaction(dbWrapper, shell);
               participant = getExistingParticipant(dbWrapper, participantId);
               dbWrapper.setTransactionSuccessful();
           } finally {
               dbWrapper.endTransaction();
           }
           return participant;
       }
       ```
     
     - ```java
       @DoesNotRunOnMainThread
       public static String getOrCreateParticipantInTransaction(final DatabaseWrapper dbWrapper,
               final ParticipantData participant) {
           Assert.isNotMainThread();
           Assert.isTrue(dbWrapper.getDatabase().inTransaction());
           int subId = ParticipantData.OTHER_THAN_SELF_SUB_ID;
           String participantId = null;
           String canonicalRecipient = null;
           if (participant.isSelf()) {
               subId = participant.getSubId();
               canonicalRecipient = getCanonicalRecipientFromSubId(subId);
           } else {
               canonicalRecipient = participant.getNormalizedDestination();
           }
           Assert.notNull(canonicalRecipient);
           participantId = getParticipantId(dbWrapper, subId, canonicalRecipient);
       
           if (participantId != null) {
               return participantId;
           }
       
           if (!participant.isContactIdResolved()) {
               // Refresh participant's name and avatar with matching contact in CP2.
               ParticipantRefresh.refreshParticipant(dbWrapper, participant);
           }
       
           // Insert the participant into the participants table
           final ContentValues values = participant.toContentValues();
           final long participantRow = dbWrapper.insert(DatabaseHelper.PARTICIPANTS_TABLE, null,
                   values);
           participantId = Long.toString(participantRow);
           Assert.notNull(canonicalRecipient);
       
           synchronized (sNormalizedPhoneNumberToParticipantIdCache) {
               // Now that we've inserted it, add it to our cache
               sNormalizedPhoneNumberToParticipantIdCache.put(canonicalRecipient, participantId);
           }
       
           return participantId;
       }
       ```
     
     - ```java
       //packages/apps/Messaging/src/com/android/messaging/datamodel/action/InsertNewMessageAction.java
       /**
        * Insert SMS messaging into our database and telephony db.
        */
       private MessageData insertSendingSmsMessage(final MessageData content, final int subId,
               final String recipient, final long timestamp, final String sendingConversationId) {
           sLastSentMessageTimestamp = timestamp;
       
           final Context context = Factory.get().getApplicationContext();
       
           // Inform sync that message is being added at timestamp
           final SyncManager syncManager = DataModel.get().getSyncManager();
           syncManager.onNewMessageInserted(timestamp);
       
           final DatabaseWrapper db = DataModel.get().getDatabase();
       
           // Send a single message
           long threadId;
           String conversationId;
           if (sendingConversationId == null) {
               // For 1:1 message generated sending broadcast need to look up threadId+conversationId
               threadId = MmsUtils.getOrCreateSmsThreadId(context, recipient);
               conversationId = BugleDatabaseOperations.getOrCreateConversationFromRecipient(
                       db, threadId, false /* sender blocked */,
                       ParticipantData.getFromRawPhoneBySimLocale(recipient, subId));
           } else {
               // Otherwise just look up threadId
               threadId = BugleDatabaseOperations.getThreadId(db, sendingConversationId);
               conversationId = sendingConversationId;
           }
       
           final String messageText = content.getMessageText();
       
           // Insert message into telephony database sms message table
           final Uri messageUri = MmsUtils.insertSmsMessage(context,
                   Telephony.Sms.CONTENT_URI,
                   subId,
                   recipient,
                   messageText,
                   timestamp,
                   Telephony.Sms.STATUS_NONE,
                   Telephony.Sms.MESSAGE_TYPE_SENT, threadId);
       
           MessageData message = null;
           if (messageUri != null && !TextUtils.isEmpty(messageUri.toString())) {
               db.beginTransaction();
               try {
                   message = MessageData.createDraftSmsMessage(conversationId,
                           content.getSelfId(), messageText);
                   message.updateSendingMessage(conversationId, messageUri, timestamp);
       
                   BugleDatabaseOperations.insertNewMessageInTransaction(db, message);
       
                   // Do not update the conversation summary to reflect autogenerated 1:1 messages
                   if (sendingConversationId != null) {
                       BugleDatabaseOperations.updateConversationMetadataInTransaction(db,
                               conversationId, message.getMessageId(), timestamp,
                               false /* senderBlocked */, false /* shouldAutoSwitchSelfId */);
                   }
                   db.setTransactionSuccessful();
               } finally {
                   db.endTransaction();
               }
       
               if (LogUtil.isLoggable(TAG, LogUtil.DEBUG)) {
                   LogUtil.d(TAG, "InsertNewMessageAction: Inserted SMS message "
                           + message.getMessageId() + " (uri = " + message.getSmsMessageUri()
                           + ", timestamp = " + message.getReceivedTimeStamp() + ")");
               }
               MessagingContentProvider.notifyMessagesChanged(conversationId);
               MessagingContentProvider.notifyPartsChanged();
           } else {
               LogUtil.e(TAG, "InsertNewMessageAction: No uri for SMS inserted into telephony DB");
           }
       
           return message;
       }
       ```
       
       

12. 在ProcessPendingMessagesAction的scheduleProcessPendingMessagesAction()中

   - ProcessPendingMessagesAction:Action used to lookup any messages in the pending send/download state and either fail them or retry their action based on subscriptions. This action only initiates one retry at a time for both sending/downloading. Further retries should be triggered by successful sending/downloading of a message, network status change or exponential backoff timer.

   - ```java
     //packages/apps/Messaging/src/com/android/messaging/datamodel/action/ProcessPendingMessagesAction.java
     public static void scheduleProcessPendingMessagesAction(final boolean failed,
             final Action processingAction) {
         final int subId = processingAction.actionParameters
                 .getInt(KEY_SUB_ID, ParticipantData.DEFAULT_SELF_SUB_ID);
         LogUtil.i(TAG, "ProcessPendingMessagesAction: Scheduling pending messages"
                 + (failed ? "(message failed)" : "") + " for subId " + subId);
         // Can safely clear any pending alarms or connectivity events as either an action
         // is currently running or we will run now or register if pending actions possible.
         unregister(subId);
     
         final boolean isDefaultSmsApp = PhoneUtils.getDefault().isDefaultSmsApp();
         boolean scheduleAlarm = false;
         // If message succeeded and if Bugle is default SMS app just carry on with next message
         if (!failed && isDefaultSmsApp) {
             // Clear retry attempt count as something just succeeded
             setRetry(0, subId);
     
             // Lookup and queue next message for each sending/downloading for immediate processing
             // by background worker. If there are no pending messages, this will do nothing and
             // return true.
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
         if (getHavePendingMessages(subId) || scheduleAlarm) {
             // Still have a pending message that needs to be queued for processing
             final ConnectivityListener listener = new ConnectivityListener() {
                 @Override
                 public void onPhoneStateChanged(final int serviceState) {
                     if (serviceState == ServiceState.STATE_IN_SERVICE) {
                         LogUtil.i(TAG, "ProcessPendingMessagesAction: Now connected for subId "
                                 + subId + ", starting action");
     
                         // Clear any pending alarms or connectivity events but leave attempt count
                         // alone
                         unregister(subId);
     
                         // Start action
                         final ProcessPendingMessagesAction action =
                                 new ProcessPendingMessagesAction();
                         action.actionParameters.putInt(KEY_SUB_ID, subId);
                         action.start();
                     }
                 }
             };
             // Read and increment attempt number from shared prefs
             final int retryAttempt = getNextRetry(subId);
             register(listener, retryAttempt, subId);
         } else {
             // No more pending messages (presumably the message that failed has expired) or it
             // may be possible that a send and a download are already in process.
             // Clear retry attempt count.
             // TODO Might be premature if send and download in process...
             // but worst case means we try to send a bit more often.
             setRetry(0, subId);
             LogUtil.i(TAG, "ProcessPendingMessagesAction: No more pending messages");
         }
     }
     ```

   - ```java
     //packages/apps/Messaging/src/com/android/messaging/datamodel/action/ProcessPendingMessagesAction.java
     /**
      * Queue any pending actions
      *
      * @param actionState
      * @return true if action queued (or no actions to queue) else false
      */
     private boolean queueActions(final Action processingAction) {
         final DatabaseWrapper db = DataModel.get().getDatabase();
         final long now = System.currentTimeMillis();
         boolean succeeded = true;
         final int subId = processingAction.actionParameters
                 .getInt(KEY_SUB_ID, ParticipantData.DEFAULT_SELF_SUB_ID);
     
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
         if (toDownloadMessageId != null) {
             LogUtil.i(TAG, "ProcessPendingMessagesAction: Queueing message " + toDownloadMessageId
                     + " for download");
             // This could queue nothing
             if (!DownloadMmsAction.queueMmsForDownloadInBackground(toDownloadMessageId,
                     processingAction)) {
                 LogUtil.w(TAG, "ProcessPendingMessagesAction: Failed to queue message "
                         + toDownloadMessageId + " for download");
                 succeeded = false;
             }
         }
         if (toSendMessageId == null && toDownloadMessageId == null) {
             LogUtil.i(TAG, "ProcessPendingMessagesAction: No messages to send or download");
         }
         return succeeded;
     }
     ```

13. 在SendMessageAction的queueForSendInBackground()中

   - Action used to send an outgoing message. It writes MMS messages to the telephony db.InsertNewMessageAction writes SMS messages to the telephony db). It also initiates the actual sending. It will all be used for re-sending a failed message.

   - ```java
     //packages/apps/Messaging/src/com/android/messaging/datamodel/action/SendMessageAction.java
     //Queue sending of existing message (can only be called during execute of action)
     static boolean queueForSendInBackground(final String messageId,final Action processingAction) {
         final SendMessageAction action = new SendMessageAction();
         return action.queueAction(messageId, processingAction);
     }
     ```

   - ```java
     //packages/apps/Messaging/src/com/android/messaging/datamodel/action/SendMessageAction.java
     /**
      * Read message from database and queue actual sending
      */
     private boolean queueAction(final String messageId, final Action processingAction) {
         actionParameters.putString(KEY_MESSAGE_ID, messageId);
         final DatabaseWrapper db = DataModel.get().getDatabase();
     
         final MessageData message = BugleDatabaseOperations.readMessage(db, messageId);
         // Check message can be resent
         if (message != null && message.canSendMessage()) {
             final boolean isSms = message.getIsSms();
             long timestamp = System.currentTimeMillis();
             if (!isSms) {
                 // MMS expects timestamp rounded to nearest second
                 timestamp = 1000 * ((timestamp + 500) / 1000);
             }
     
             final ParticipantData self = BugleDatabaseOperations.getExistingParticipant(
                     db, message.getSelfId());
             final Uri messageUri = message.getSmsMessageUri();
             final String conversationId = message.getConversationId();
     
             // Update message status
             if (message.getYetToSend()) {
                 if (message.getReceivedTimeStamp() == message.getRetryStartTimestamp()) {
                     // Initial sending of message
                     message.markMessageSending(timestamp);
                 } else {
                     // Manual resend of message
                     message.markMessageManualResend(timestamp);
                 }
             } else {
                 // Automatic resend of message
                 message.markMessageResending(timestamp);
             }
             if (!updateMessageAndStatus(isSms, message, null /* messageUri */, false /*notify*/)) {
                 // If message is missing in the telephony database we don't need to send it
                 return false;
             }
     
             final ArrayList<String> recipients =
                     BugleDatabaseOperations.getRecipientsForConversation(db, conversationId);
     
             // Update action state with parameters needed for background sending
             actionParameters.putParcelable(KEY_MESSAGE_URI, messageUri);
             actionParameters.putParcelable(KEY_MESSAGE, message);
             actionParameters.putStringArrayList(KEY_RECIPIENTS, recipients);
             actionParameters.putInt(KEY_SUB_ID, self.getSubId());
             actionParameters.putString(KEY_SUB_PHONE_NUMBER, self.getNormalizedDestination());
     
             if (isSms) {
                 //获得服务中心
                 //BugleDatabaseOperations:manages updating our local database
                 final String smsc = BugleDatabaseOperations.getSmsServiceCenterForConversation(
                         db, conversationId);
                 actionParameters.putString(KEY_SMS_SERVICE_CENTER, smsc);
     
                 if (recipients.size() == 1) {
                     final String recipient = recipients.get(0);
     
                     actionParameters.putString(KEY_RECIPIENT, recipient);
                     // Queue actual sending for SMS
                     processingAction.requestBackgroundWork(this);
     
                     if (LogUtil.isLoggable(TAG, LogUtil.DEBUG)) {
                         LogUtil.d(TAG, "SendMessageAction: Queued SMS message " + messageId
                                 + " for sending");
                     }
                     return true;
                 } else {
                     LogUtil.wtf(TAG, "Trying to resend a broadcast SMS - not allowed");
                 }
             } else {
                 // Queue actual sending for MMS
                 processingAction.requestBackgroundWork(this);
     
                 if (LogUtil.isLoggable(TAG, LogUtil.DEBUG)) {
                     LogUtil.d(TAG, "SendMessageAction: Queued MMS message " + messageId
                             + " for sending");
                 }
                 return true;
             }
         }
     
         return false;
     }
     ```

   - ```java
     //packages/apps/Messaging/src/com/android/messaging/datamodel/action/Action.java    
      /**
      * Queues up background actions for background processing after the current action has
      * completed its processing ({@link #executeAction}, {@link processBackgroundCompletion}
      * or {@link #processBackgroundFailure}) on the Action thread.
      * @param backgroundAction
      */
     protected void requestBackgroundWork(final Action backgroundAction) {
         mBackgroundActions.add(backgroundAction);
     }
     ```

   - ```java
     //action机制，通过doBackgroundWork()处理requestBackgroundWork()的work，SendMessageAction重写了doBackgroundWork()方法
     //packages/apps/Messaging/src/com/android/messaging/datamodel/action/SendMessageAction.java
     /**
      * Do work in a long running background worker thread.
      * {@link #requestBackgroundWork} needs to be called for this method to
      * be called. {@link #processBackgroundFailure} will be called on the Action service thread
      * if this method throws {@link DataModelException}.
      * @return response that is to be passed to {@link #processBackgroundResponse}
      */
     /**
      * Send message on background worker thread
      */
     @Override
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
         } else {
             final Context context = Factory.get().getApplicationContext();
             final ArrayList<String> recipients =
                     actionParameters.getStringArrayList(KEY_RECIPIENTS);
             if (messageUri == null) {
                 final long timestamp = message.getReceivedTimeStamp();
     
                 // Inform sync that message has been added at local received timestamp
                 final SyncManager syncManager = DataModel.get().getSyncManager();
                 syncManager.onNewMessageInserted(timestamp);
     
                 // For MMS messages first need to write to telephony (resizing images if needed)
                 updatedMessageUri = MmsUtils.insertSendingMmsMessage(context, recipients,
                         message, subId, subPhoneNumber, timestamp);
                 if (updatedMessageUri != null) {
                     messageUri = updatedMessageUri;
                     // To prevent Sync seeing inconsistent state must write to DB on this thread
                     updateMessageUri(messageId, updatedMessageUri);
     
                     if (LogUtil.isLoggable(TAG, LogUtil.VERBOSE)) {
                         LogUtil.v(TAG, "SendMessageAction: Updated message " + messageId
                                 + " with new uri " + messageUri);
                     }
                  }
             }
             if (messageUri != null) {
                 // Actually send the MMS
                 final Bundle extras = new Bundle();
                 extras.putString(EXTRA_MESSAGE_ID, messageId);
                 extras.putParcelable(EXTRA_UPDATED_MESSAGE_URI, updatedMessageUri);
                 final MmsUtils.StatusPlusUri result = MmsUtils.sendMmsMessage(context, subId,
                         messageUri, extras);
                 if (result == MmsUtils.STATUS_PENDING) {
                     // Async send, so no status yet
                     LogUtil.d(TAG, "SendMessageAction: Sending MMS message " + messageId
                             + " asynchronously; waiting for callback to finish processing");
                     return null;
                 }
                 status = result.status;
                 rawStatus = result.rawStatus;
                 resultCode = result.resultCode;
             } else {
                 status = MmsUtils.MMS_REQUEST_MANUAL_RETRY;
             }
         }
         // When we fast-fail before calling the MMS lib APIs (e.g. airplane mode,
         // sending message is deleted).
         ProcessSentMessageAction.processMessageSentFastFailed(messageId, messageUri,
                 updatedMessageUri, subId, isSms, status, rawStatus, resultCode);
         return null;
     }
     
     /**
      * Process the success response from the background worker. Runs on action service thread.
      * @param response the response returned by {@link #doBackgroundWork}
      * @return result to be passed in to {@link ActionCompletedListener#onActionSucceeded}
      */
     @Override
     protected Object processBackgroundResponse(final Bundle response) {
         // Nothing to do here, post-send tasks handled by ProcessSentMessageAction
         return null;
     }
     
     /**
      * Update message status to reflect success or failure
      */
     /**
      * Called in case of failures when sending background actions. Runs on action service thread
      * @return result to be passed in to {@link ActionCompletedListener#onActionFailed}
      */
     @Override
     protected Object processBackgroundFailure() {
         final String messageId = actionParameters.getString(KEY_MESSAGE_ID);
         final MessageData message = actionParameters.getParcelable(KEY_MESSAGE);
         final boolean isSms = message.getProtocol() == MessageData.PROTOCOL_SMS;
         final int subId = actionParameters.getInt(KEY_SUB_ID, ParticipantData.DEFAULT_SELF_SUB_ID);
         final int resultCode = actionParameters.getInt(ProcessSentMessageAction.KEY_RESULT_CODE);
         final int httpStatusCode =
                 actionParameters.getInt(ProcessSentMessageAction.KEY_HTTP_STATUS_CODE);
     
         ProcessSentMessageAction.processResult(messageId, null /* updatedMessageUri */,
                 MmsUtils.MMS_REQUEST_MANUAL_RETRY, MessageData.RAW_TELEPHONY_STATUS_UNDEFINED,
                 isSms, this, subId, resultCode, httpStatusCode);
     
         return null;
     }
     ```

     

14. 在MmsUtils的sendSmsMessage()中

   - MmsUtils：Utils for sending sms/mms messages.

   - ```java
     //packages/apps/Messaging/src/com/android/messaging/sms/MmsUtils.java
     public static int sendSmsMessage(final String recipient, final String messageText,
             final Uri requestUri, final int subId,
             final String smsServiceCenter, final boolean requireDeliveryReport) {
         if (!isSmsDataAvailable(subId)) {
             LogUtil.w(TAG, "MmsUtils: can't send SMS without radio");
             return MMS_REQUEST_MANUAL_RETRY;
         }
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
             if (!result.hasPending()) {
                 // not timed out, check failures
                 final int failureLevel = result.getHighestFailureLevel();
                 switch (failureLevel) {
                     case SendResult.FAILURE_LEVEL_NONE:
                         status = MMS_REQUEST_SUCCEEDED;
                         break;
                     case SendResult.FAILURE_LEVEL_TEMPORARY:
                         status = MMS_REQUEST_AUTO_RETRY;
                         LogUtil.e(TAG, "MmsUtils: SMS temporary failure");
                         break;
                     case SendResult.FAILURE_LEVEL_PERMANENT:
                         LogUtil.e(TAG, "MmsUtils: SMS permanent failure");
                         break;
                 }
             } else {
                 // Timed out
                 LogUtil.e(TAG, "MmsUtils: sending SMS timed out");
             }
         } catch (final Exception e) {
             LogUtil.e(TAG, "MmsUtils: failed to send SMS " + e, e);
         }
         return status;
     }
     ```

15. 在SmsSender的sendMessage()中

   - SmsSender

     - Class that sends chat message via SMS
     
     - The interface emulates a blocking sending similar to making an HTTP request. It calls the SmsManager to send a (potentially multipart) message and waits on the sent status on each part. The waiting has a timeout so it won't wait forever. Once the sent status of all parts received, the call returns.A successful sending requires success status for all parts. Otherwise, we pick the highest level of failure as the error for the whole message, which is used to determine if we need to retry the sending.
   
   - ```java
     //packages/apps/Messaging/src/com/android/messaging/sms/SmsSender.java
     // This should be called from a RequestWriter queue thread
     public static SendResult sendMessage(final Context context, final int subId, String dest,
             String message, final String serviceCenter, final boolean requireDeliveryReport,
             final Uri messageUri) throws SmsException {
         if (LogUtil.isLoggable(TAG, LogUtil.VERBOSE)) {
             LogUtil.v(TAG, "SmsSender: sending message. " +
                     "dest=" + dest + " message=" + message +
                     " serviceCenter=" + serviceCenter +
                     " requireDeliveryReport=" + requireDeliveryReport +
                     " requestId=" + messageUri);
         }
         if (TextUtils.isEmpty(message)) {
             throw new SmsException("SmsSender: empty text message");
         }
         // Get the real dest and message for email or alias if dest is email or alias
         // Or sanitize the dest if dest is a number
         if (!TextUtils.isEmpty(MmsConfig.get(subId).getEmailGateway()) &&
                 (MmsSmsUtils.isEmailAddress(dest) || MmsSmsUtils.isAlias(dest, subId))) {
             // The original destination (email address) goes with the message
             message = dest + " " + message;
             // the new address is the email gateway #
             dest = MmsConfig.get(subId).getEmailGateway();
         } else {
             // remove spaces and dashes from destination number
             // (e.g. "801 555 1212" -> "8015551212")
             // (e.g. "+8211-123-4567" -> "+82111234567")
             dest = PhoneNumberUtils.stripSeparators(dest);
         }
         if (TextUtils.isEmpty(dest)) {
             throw new SmsException("SmsSender: empty destination address");
         }
         // Divide the input message by SMS length limit
         final SmsManager smsManager = PhoneUtils.get(subId).getSmsManager();
         //divideMessage():Divide a message text into several fragments, none bigger than the maximum SMS message size.
         final ArrayList<String> messages = smsManager.divideMessage(message);
         if (messages == null || messages.size() < 1) {
             throw new SmsException("SmsSender: fails to divide message");
         }
         // Prepare the send result, which collects the send status for each part
         final SendResult pendingResult = new SendResult(messages.size());
         /*    private static ConcurrentHashMap<Uri, SendResult> sPendingMessageMap =
                 new ConcurrentHashMap<Uri, SendResult>();
     	//A map for pending sms messages. The key is the random request UUID*/
         sPendingMessageMap.put(messageUri, pendingResult);
         // Actually send the sms
         sendInternal(
                 context, subId, dest, messages, serviceCenter, requireDeliveryReport, messageUri);
         // Wait for pending intent to come back
         synchronized (pendingResult) {
             final long smsSendTimeoutInMillis = BugleGservices.get().getLong(
                     BugleGservicesKeys.SMS_SEND_TIMEOUT_IN_MILLIS,
                     BugleGservicesKeys.SMS_SEND_TIMEOUT_IN_MILLIS_DEFAULT);
             final long beginTime = SystemClock.elapsedRealtime();
             long waitTime = smsSendTimeoutInMillis;
             // We could possibly be woken up while still pending
             // so make sure we wait the full timeout period unless
             // we have the send results of all parts.
             while (pendingResult.hasPending() && waitTime > 0) {
                 try {
                     pendingResult.wait(waitTime);
                 } catch (final InterruptedException e) {
                     LogUtil.e(TAG, "SmsSender: sending wait interrupted");
                 }
                 waitTime = smsSendTimeoutInMillis - (SystemClock.elapsedRealtime() - beginTime);
             }
         }
         // Either we timed out or have all the results (success or failure)
         sPendingMessageMap.remove(messageUri);
         if (LogUtil.isLoggable(TAG, LogUtil.VERBOSE)) {
             LogUtil.v(TAG, "SmsSender: sending completed. " +
                     "dest=" + dest + " message=" + message + " result=" + pendingResult);
         }
         return pendingResult;
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
                 //MESSAGE_SENT_ACTION = "com.android.messaging.receiver.SendStatusReceiver.MESSAGE_SENT";    	
                 deliveryIntents.add(PendingIntent.getBroadcast(
                         context,
                         partId,
                         getSendStatusIntent(context, SendStatusReceiver.MESSAGE_DELIVERED_ACTION,
                                 messageUri, partId, subId),
                         0/*flag*/));
             } else {
                 deliveryIntents.add(null);
             }
             //发送的状态，每个部分都要发送广播
             //MESSAGE_DELIVERED_ACTION ="com.android.messaging.receiver.SendStatusReceiver.MESSAGE_DELIVERED";
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
     //frameworks/base/telephony/java/android/telephony/SmsManager.java
     //Send a text based SMS
     public void sendTextMessage(String destinationAddress, String scAddress, String text,PendingIntent sentIntent, PendingIntent deliveryIntent) {
         sendTextMessageInternal(destinationAddress, scAddress, text, sentIntent, deliveryIntent,
                 true /* persistMessage*/);
     }
     ```

   - ```java
     private void sendTextMessageInternal(String destinationAddress, String scAddress,
             String text, PendingIntent sentIntent, PendingIntent deliveryIntent,
             boolean persistMessage, String packageName, String attributionTag, long messageId) {
         if (TextUtils.isEmpty(destinationAddress)) {
             throw new IllegalArgumentException("Invalid destinationAddress");
         }
     
         if (TextUtils.isEmpty(text)) {
             throw new IllegalArgumentException("Invalid message body");
         }
     
         // We will only show the SMS disambiguation dialog in the case that the message is being
         // persisted. This is for two reasons:
         // 1) Messages that are not persisted are sent by carrier/OEM apps for a specific
         //    subscription and require special permissions. These messages are usually not sent by
         //    the device user and should not have an SMS disambiguation dialog associated with them
         //    because the device user did not trigger them.
         // 2) The SMS disambiguation dialog ONLY checks to make sure that the user has the SEND_SMS
         //    permission. If we call resolveSubscriptionForOperation from a carrier/OEM app that has
         //    the correct MODIFY_PHONE_STATE or carrier permissions, but no SEND_SMS, it will throw
         //    an incorrect SecurityException.
         if (persistMessage) {
             resolveSubscriptionForOperation(new SubscriptionResolverResult() {
                 @Override
                 public void onSuccess(int subId) {
                     ISms iSms = getISmsServiceOrThrow();
                     try {
                         iSms.sendTextForSubscriber(subId, packageName, attributionTag,
                                 destinationAddress, scAddress, text, sentIntent, deliveryIntent,
                                 persistMessage, messageId);
                     } catch (RemoteException e) {
                         Log.e(TAG, "sendTextMessageInternal: Couldn't send SMS, exception - "
                                 + e.getMessage() + " " + formatCrossStackMessageId(messageId));
                         notifySmsError(sentIntent, RESULT_REMOTE_EXCEPTION);
                     }
                 }
     
                 @Override
                 public void onFailure() {
                     notifySmsError(sentIntent, RESULT_NO_DEFAULT_SMS_APP);
                 }
             });
         } else {
             // Not persisting the message, used by sendTextMessageWithoutPersisting() and is not
             // visible to the user.
             ISms iSms = getISmsServiceOrThrow();
             try {
                 iSms.sendTextForSubscriber(getSubscriptionId(), packageName, attributionTag,
                         destinationAddress, scAddress, text, sentIntent, deliveryIntent,
                         persistMessage, messageId);
             } catch (RemoteException e) {
                 Log.e(TAG, "sendTextMessageInternal (no persist): Couldn't send SMS, exception - "
                         + e.getMessage() + " " + formatCrossStackMessageId(messageId));
                 notifySmsError(sentIntent, RESULT_REMOTE_EXCEPTION);
             }
         }
     }
     
     ```

2. SmsController的sendTextForSubscriber()中

   - 跨进程调用，从Messaging进程到phone进程

   - ISms.aidl位于frameworks/base/telephony/java/com/android/internal/telephony/ISms.aidl

   -  ISmsImplBase 继承了 ISms.Stub， SmsController 继承了 ISmsImplBase

   - SmsController 位于frameworks/opt/telephony/src/java/com/android/internal/telephony/SmsController.java

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/SmsController.java
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
           SubscriptionInfo info;
           try {
               info = getSubscriptionInfo(subId);
           } finally {
               Binder.restoreCallingIdentity(token);
           }
           if (isBluetoothSubscription(info)) {
               sendBluetoothText(info, destAddr, text, sentIntent, deliveryIntent);
           } else {
               sendIccText(subId, callingPackage, destAddr, scAddr, text, sentIntent, deliveryIntent,
                       persistMessageForNonDefaultSmsApp, messageId);
           }
     }
     private void sendIccText(int subId, String callingPackage, String destAddr,
                   String scAddr, String text, PendingIntent sentIntent, PendingIntent deliveryIntent,
                   boolean persistMessageForNonDefaultSmsApp, long messageId) {
           Rlog.d(LOG_TAG, "sendTextForSubscriber iccSmsIntMgr"+ " Subscription: " + subId + " id: " + messageId);
           //Get sms interface manager object based on subscription.
           IccSmsInterfaceManager iccSmsIntMgr = getIccSmsInterfaceManager(subId);
           if (iccSmsIntMgr != null) {
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
    //frameworks/opt/telephony/src/java/com/android/internal/telephony/IccSmsInterfaceManager.java
    public void sendText(String callingPackage, String destAddr, String scAddr,String text, PendingIntent sentIntent, PendingIntent deliveryIntent,boolean persistMessageForNonDefaultSmsApp) {
        //A permissions check
        //This method checks only if the calling package has the permission to send the sms.
        mPhone.getContext().enforceCallingPermission(Manifest.permission.SEND_SMS,"Sending SMS message");
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
          mDispatchersController.sendText(destAddr, scAddr, text, sentIntent, deliveryIntent,
                  null/*messageUri*/, callingPackage, persistMessageForNonDefaultSmsApp,
                  priority, expectMore, validityPeriod, isForVvm, messageId);
        }
    ```

4. 在SmsDispatchersController的sendText（）方法中

  - ```java
    //frameworks/opt/telephony/src/java/com/android/internal/telephony/SmsDispatchersController.java
    //在SmsDispatchersController的构造方法中mCdmaDispatcher = new CdmaSMSDispatcher(phone, this);
    //在SmsDispatchersController的构造方法中mGsmDispatcher = new GsmSMSDispatcher(phone, this, mGsmInboundSmsHandler);
    private SMSDispatcher mCdmaDispatcher;
    private SMSDispatcher mGsmDispatcher;//在SmsDispatchersController的构造方法中mGsmDispatcher = new GsmSMSDispatcher()
    private ImsSmsDispatcher mImsSmsDispatcher;
    
    public void sendText(String destAddr, String scAddr, String text, PendingIntent sentIntent,
            PendingIntent deliveryIntent, Uri messageUri, String callingPkg, boolean persistMessage,
            int priority, boolean expectMore, int validityPeriod, boolean isForVvm,
            long messageId) {
        //使用不同的网络分发sms
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
    //frameworks/opt/telephony/src/java/com/android/internal/telephony/SMSDispatcher.java
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
            //Keeps track of an SMS that has been sent to the RIL, until it has successfully been sent, or we're done trying.
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
        //return true if MO SMS should be blocked for Emergency Callback Mode.
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

  - ```java
      //frameworks/opt/telephony/src/java/com/android/internal/telephony/SMSDispatcher.java
      @VisibleForTesting
      public void sendRawPdu(SmsTracker[] trackers) {
          @SmsManager.Result int error = SmsManager.RESULT_ERROR_NONE;
          PackageInfo appInfo = null;
          if (mSmsSendDisabled) {
              Rlog.e(TAG, "Device does not support sending sms.");
              error = SmsManager.RESULT_ERROR_NO_SERVICE;
          } else {
              for (SmsTracker tracker : trackers) {
                  if (tracker.getData().get(MAP_KEY_PDU) == null) {
                      Rlog.e(TAG, "Empty PDU");
                      error = SmsManager.RESULT_ERROR_NULL_PDU;
                      break;
                  }
              }
      
              if (error == SmsManager.RESULT_ERROR_NONE) {
                  UserHandle userHandle = UserHandle.of(trackers[0].mUserId);
                  PackageManager pm = mContext.createContextAsUser(userHandle, 0).getPackageManager();
      
                  try {
                      // Get package info via packagemanager
                      appInfo =
                              pm.getPackageInfo(
                                      trackers[0].getAppPackageName(),
                                      PackageManager.GET_SIGNATURES);
                  } catch (PackageManager.NameNotFoundException e) {
                      Rlog.e(TAG, "Can't get calling app package info: refusing to send SMS"
                              + " id: " + getMultiTrackermessageId(trackers));
                      error = SmsManager.RESULT_ERROR_GENERIC_FAILURE;
                  }
              }
          }
      
          if (error != SmsManager.RESULT_ERROR_NONE) {
              handleSmsTrackersFailure(trackers, error, NO_ERROR_CODE);
              return;
          }
      
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
                  sendSms(tracker);
              }
          }
      
          if (mTelephonyManager.isEmergencyNumber(trackers[0].mDestAddress)) {
              new AsyncEmergencyContactNotifier(mContext).execute();
          }
      }
      ```

6. 在GsmSMSDispatcher的sendSms()中

  - ```java
    //frameworks/opt/telephony/src/java/com/android/internal/telephony/gsm/GsmSMSDispatcher.java
    //在SmsDispatchersController的构造方法中mCdmaDispatcher = new CdmaSMSDispatcher(phone, this);
    //在SmsDispatchersController的构造方法中mGsmDispatcher = new GsmSMSDispatcher(phone, this, mGsmInboundSmsHandler);
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
          //获得radio代理
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

      - ```java
        RILRequest的消息获取
        //frameworks/opt/telephony/src/java/com/android/internal/telephony/RIL.java
        private RILRequest obtainRequest(int request, Message result, WorkSource workSource) {
            RILRequest rr = RILRequest.obtain(request, result, workSource);
            addRequest(rr);
            return rr;
        }
        //frameworks/opt/telephony/src/java/com/android/internal/telephony/RILRequest.java
        /**
         * Retrieves a new RILRequest instance from the pool and sets the clientId
         *
         * @param request RIL_REQUEST_*
         * @param result sent when operation completes
         * @param workSource WorkSource to track the client
         * @return a RILRequest instance from the pool.
         */
        // @VisibleForTesting
        public static RILRequest obtain(int request, Message result, WorkSource workSource) {
            RILRequest rr = obtain(request, result);
        
            if (workSource != null) {
                rr.mWorkSource = workSource;
                //Generate a String client ID from the WorkSource.
                rr.mClientId = rr.getWorkSourceClientId();
            } else {
                Rlog.e(LOG_TAG, "null workSource " + request);
            }
        
            return rr;
        }
        //frameworks/opt/telephony/src/java/com/android/internal/telephony/RIL.java
        SparseArray<RILRequest> mRequestList = new SparseArray<>();
        private void addRequest(RILRequest rr) {
            acquireWakeLock(rr, FOR_WAKELOCK);
            synchronized (mRequestList) {
                rr.mStartTimeMs = SystemClock.elapsedRealtime();
                mRequestList.append(rr.mSerial, rr);
            }
        }
        ```

8.  IRadio的sendSms_1_6()

   ```java
   //hardware/interfaces/radio/1.6/IRadio.hal
   向modem发起请求，在IRadioResponse.sendSmsResponse_1_6()方法中响应modem的返回的结果
   /**
    * Send an SMS message
    *
    * @param serial Serial number of request.
    * @param message GsmSmsMessage as defined in types.hal
    *
    * Response function is IRadioResponse.sendSmsResponse_1_6()
    *
    * Note this API is the same as the 1.0
    *
    * Based on the return error, caller decides to resend if sending sms
    * fails. RadioError:SMS_SEND_FAIL_RETRY means retry (i.e. error cause is 332)
    * and RadioError:GENERIC_FAILURE means no retry (i.e. error cause is 500)
    */
   oneway sendSms_1_6(int32_t serial, GsmSmsMessage message);
   ```

   - 普通版本的RIL_REQUEST_SEND_SMS消息处理

      - ```c++
         在ril_commands.h中定义了所有solicited类型，分别为solicited Request和solicited Response。
         //hardware/ril/libril/ril_commands.h
         {RIL_REQUEST_SEND_SMS, radio::sendSmsResponse},
         
         //hardware/ril/libril/ril_service.cpp
         //sp<IRadioResponse> mRadioResponse;
         int radio::sendSmsResponse(int slotId,
                                   int responseType, int serial, RIL_Errno e, void *response,
                                   size_t responseLen) {
         #if VDBG
             RLOGD("sendSmsResponse: serial %d", serial);
         #endif
         
             if (radioService[slotId]->mRadioResponse != NULL) {
                 RadioResponseInfo responseInfo = {};
                 SendSmsResult result = makeSendSmsResult(responseInfo, serial, responseType, e, response,
                         responseLen);
         
                 Return<void> retStatus = radioService[slotId]->mRadioResponse->sendSmsResponse(responseInfo,
                         result);
                 radioService[slotId]->checkReturnStatus(retStatus);
             } else {
                 RLOGE("sendSmsResponse: radioService[%d]->mRadioResponse == NULL", slotId);
             }
         
             return 0;
         }
         ```

9. RadioResponse的sendSmsResponse_1_6()方法

   - ```java
      //frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioResponse.java
      /**
       * @param responseInfo Response info struct containing response type, serial no. and error which
       *                     is defined in 1.6/types.hal
       * @param sms Response to sms sent as defined by SendSmsResult in types.hal
       */
      public void sendSmsResponse_1_6(android.hardware.radio.V1_6.RadioResponseInfo responseInfo,
              SendSmsResult sms) {
          responseSms_1_6(responseInfo, sms);
      }
      
      private void responseSms_1_6(android.hardware.radio.V1_6.RadioResponseInfo responseInfo,
              SendSmsResult sms) {
          RILRequest rr = mRil.processResponse_1_6(responseInfo);
      
          if (rr != null) {
              long messageId = RIL.getOutgoingSmsMessageId(rr.mResult);
              SmsResponse ret = new SmsResponse(sms.messageRef, sms.ackPDU, sms.errorCode, messageId);
              if (responseInfo.error == RadioError.NONE) {
                  sendMessageResponse(rr.mResult, ret);
              }
              mRil.processResponseDone_1_6(rr, responseInfo, ret);
          }
      }
      ```

      - ```java
        //frameworks/opt/telephony/src/java/com/android/internal/telephony/RIL.java
        /**
         * This is a helper function for V1_6.RadioResponseInfo to be called when a RadioResponse
         * callback is called. It takes care of acks, wakelocks, and finds and returns RILRequest
         * corresponding to the response if one is found.
         * @param responseInfo RadioResponseInfo received in response callback
         * @return RILRequest corresponding to the response
         */
        @VisibleForTesting
        public RILRequest processResponse_1_6(
                android.hardware.radio.V1_6.RadioResponseInfo responseInfo) {
            return processResponseInternal(RADIO_SERVICE, responseInfo.serial, responseInfo.error,
                    responseInfo.type);
        }
        private RILRequest processResponseInternal(int service, int serial, int error, int type) {
            RILRequest rr;
        
            if (type == RadioResponseType.SOLICITED_ACK) {
                synchronized (mRequestList) {
                    rr = mRequestList.get(serial);
                }
                if (rr == null) {
                    Rlog.w(RILJ_LOG_TAG, "Unexpected solicited ack response! sn: " + serial);
                } else {
                    decrementWakeLock(rr);
                    if (mRadioBugDetector != null) {
                        mRadioBugDetector.detectRadioBug(rr.mRequest, error);
                    }
                    if (RILJ_LOGD) {
                        riljLog(rr.serialString() + " Ack from " + serviceToString(service)
                                + " < " + RILUtils.requestToString(rr.mRequest));
                    }
                }
                return rr;
            }
        
            rr = findAndRemoveRequestFromList(serial);
            if (rr == null) {
                Rlog.e(RILJ_LOG_TAG, "processResponse: Unexpected response! serial: " + serial
                        + " ,error: " + error);
                return null;
            }
        
            // Time logging for RIL command and storing it in TelephonyHistogram.
            addToRilHistogram(rr);
            if (mRadioBugDetector != null) {
                mRadioBugDetector.detectRadioBug(rr.mRequest, error);
            }
            if (type == RadioResponseType.SOLICITED_ACK_EXP) {
                sendAck(service);
                if (RILJ_LOGD) {
                    riljLog("Response received from " + serviceToString(service) + " for "
                            + rr.serialString() + " " + RILUtils.requestToString(rr.mRequest)
                            + " Sending ack to ril.cpp");
                }
            } else {
                // ack sent for SOLICITED_ACK_EXP above; nothing to do for SOLICITED response
            }
        
            // Here and below fake RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED, see b/7255789.
            // This is needed otherwise we don't automatically transition to the main lock
            // screen when the pin or puk is entered incorrectly.
            switch (rr.mRequest) {
                case RIL_REQUEST_ENTER_SIM_PUK:
                case RIL_REQUEST_ENTER_SIM_PUK2:
                    if (mIccStatusChangedRegistrants != null) {
                        if (RILJ_LOGD) {
                            riljLog("ON enter sim puk fakeSimStatusChanged: reg count="
                                    + mIccStatusChangedRegistrants.size());
                        }
                        mIccStatusChangedRegistrants.notifyRegistrants();
                    }
                    break;
                case RIL_REQUEST_SHUTDOWN:
                    setRadioState(TelephonyManager.RADIO_POWER_UNAVAILABLE,
                            false /* forceNotifyRegistrants */);
                    break;
            }
        
            if (error != RadioError.NONE) {
                switch (rr.mRequest) {
                    case RIL_REQUEST_ENTER_SIM_PIN:
                    case RIL_REQUEST_ENTER_SIM_PIN2:
                    case RIL_REQUEST_CHANGE_SIM_PIN:
                    case RIL_REQUEST_CHANGE_SIM_PIN2:
                    case RIL_REQUEST_SET_FACILITY_LOCK:
                        if (mIccStatusChangedRegistrants != null) {
                            if (RILJ_LOGD) {
                                riljLog("ON some errors fakeSimStatusChanged: reg count="
                                        + mIccStatusChangedRegistrants.size());
                            }
                            mIccStatusChangedRegistrants.notifyRegistrants();
                        }
                        break;
        
                }
            } else {
                switch (rr.mRequest) {
                    case RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND:
                    if (mTestingEmergencyCall.getAndSet(false)) {
                        if (mEmergencyCallbackModeRegistrant != null) {
                            riljLog("testing emergency call, notify ECM Registrants");
                            mEmergencyCallbackModeRegistrant.notifyRegistrant();
                        }
                    }
                }
            }
            return rr;
        }
        ```

      - ```java
        Message是RILRequest的mresult对象，在GsmSMSDispatcher生成了Message，发送EVENT_SEND_SMS_COMPLETE消息，在父类SMSDispatcher响应
        //frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioResponse.java
        /**
         * Helper function to send response msg
         * @param msg Response message to be sent
         * @param ret Return object to be included in the response message
         */
        static void sendMessageResponse(Message msg, Object ret) {
            if (msg != null) {
                AsyncResult.forMessage(msg, ret, null);
                msg.sendToTarget();
            }
        }
        
        //frameworks/base/core/java/android/os/AsyncResult.java
        /** Saves and sets m.obj */
        @UnsupportedAppUsage
        public static AsyncResult forMessage(Message m, Object r, Throwable ex)
        {
            AsyncResult ret;
        
            ret = new AsyncResult (m.obj, r, ex);
        
            m.obj = ret; 
        
            return ret;
        }
        
        //frameworks/base/core/java/android/os/Message.java
        /**
         * Sends this Message to the Handler specified by {@link #getTarget}.
         * Throws a null pointer exception if this field has not been set.
         */
        public void sendToTarget() {
            target.sendMessage(this);
        }
        ```

10. SMSDispatcher的handleMessage处理EVENT_SEND_SMS_COMPLETE消息

   ```java
   //frameworks/opt/telephony/src/java/com/android/internal/telephony/SMSDispatcher.java
   /**
    * Handles events coming from the phone stack. Overridden from handler.
    *
    * @param msg the message to handle
    */
   @Override
   public void handleMessage(Message msg) {
       switch (msg.what) {
       case EVENT_SEND_SMS_COMPLETE:
           // An outbound SMS has been successfully transferred, or failed.
           handleSendComplete((AsyncResult) msg.obj);
           break;
   	...
       default:
           Rlog.e(TAG, "handleMessage() ignoring message of unexpected type " + msg.what);
       }
   }
   ```

## 接收流程

1. 接收到UNSOL_RESPONSE_NEW_SMS消息

   - hardware/ril/libril/ril_unsol_commands.h定义了接收的消息类型对应的处理方法

     - ```c++
       //hardware/ril/libril/ril_unsol_commands.h
       {RIL_UNSOL_RESPONSE_NEW_SMS, radio::newSmsInd, WAKE_PARTIAL},
       ```
   - 具体的处理在hardware/ril/libril/ril_service.cpp中，通过IRadioIndication.newSms()处理

     - ```c++
       //hardware/ril/libril/ril_service.cpp
       //sp<IRadioIndication> mRadioIndication;
       int radio::newSmsInd(int slotId, int indicationType,
                            int token, RIL_Errno e, void *response, size_t responseLen) {
           if (radioService[slotId] != NULL && radioService[slotId]->mRadioIndication != NULL) {
               if (response == NULL || responseLen == 0) {
                   RLOGE("newSmsInd: invalid response");
                   return 0;
               }
       
               uint8_t *bytes = convertHexStringToBytes(response, responseLen);
               if (bytes == NULL) {
                   RLOGE("newSmsInd: convertHexStringToBytes failed");
                   return 0;
               }
       
               hidl_vec<uint8_t> pdu;
               pdu.setToExternal(bytes, responseLen/2);
       #if VDBG
               RLOGD("newSmsInd");
       #endif
               Return<void> retStatus = radioService[slotId]->mRadioIndication->newSms(
                       convertIntToRadioIndicationType(indicationType), pdu);
               radioService[slotId]->checkReturnStatus(retStatus);
               free(bytes);
           } else {
               RLOGE("newSmsInd: radioService[%d]->mRadioIndication == NULL", slotId);
           }
       
           return 0;
       }
       ```

2. IRadioIndication.newSms()跨进程调用

   - IRadioIndication.hidl位于hardware/interfaces/radio/1.0/IRadioIndication.hal

   - IRadioIndication.hidl的实现在frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioIndication.java

   - newSms():Indicates when new SMS is received.

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioIndication.java
     public void newSms(int indicationType, ArrayList<Byte> pdu) {
         mRil.processIndication(indicationType);
     	//将ArrayList<Byte>转换为byte[]
         byte[] pduArray = RIL.arrayListToPrimitiveArray(pdu);
         if (RIL.RILJ_LOGD) mRil.unsljLog(RIL_UNSOL_RESPONSE_NEW_SMS);
     
         //SmsMessageBase:Base class declaring the specific methods and members for SmsMessage.
         //SmsMessage:A Short Message Service message.
         //createFromPdu（）：Create an SmsMessage from a raw PDU.
         SmsMessageBase smsb = com.android.internal.telephony.gsm.SmsMessage.createFromPdu(pduArray);
         if (mRil.mGsmSmsRegistrant != null) {
             //protected Registrant mGsmSmsRegistrant;
             mRil.mGsmSmsRegistrant.notifyRegistrant(
                     new AsyncResult(null, smsb == null ? null : new SmsMessage(smsb), null));
         }
     }
     ```
     
     - mGsmSmsRegistrant的注册
     
     - ```java
       在BaseCommands中初始化
       //frameworks/opt/telephony/src/java/com/android/internal/telephony/BaseCommands.java
       @Override
       public void setOnNewGsmSms(Handler h, int what, Object obj) {
           mGsmSmsRegistrant = new Registrant (h, what, obj);
       }
       
       在GsmInboundSmsHandler中调用 setOnNewGsmSms() ,GsmInboundSmsHandler随着phone一起启动。
       //frameworks/opt/telephony/src/java/com/android/internal/telephony/gsm/GsmInboundSmsHandler.java
       /**
        * Create a new GSM inbound SMS handler.
        */
       private GsmInboundSmsHandler(Context context, SmsStorageMonitor storageMonitor,
               Phone phone) {
           super("GsmInboundSmsHandler", context, storageMonitor, phone);
           phone.mCi.setOnNewGsmSms(getHandler(), EVENT_NEW_SMS, null);
           mDataDownloadHandler = new UsimDataDownloadHandler(phone.mCi, phone.getPhoneId());
           mCellBroadcastServiceManager.enable();
       
           if (TEST_MODE) {
               if (sTestBroadcastReceiver == null) {
                   sTestBroadcastReceiver = new GsmCbTestBroadcastReceiver();
                   IntentFilter filter = new IntentFilter();
                   filter.addAction(TEST_ACTION);
                   context.registerReceiver(sTestBroadcastReceiver, filter,
                           Context.RECEIVER_EXPORTED);
               }
           }
       }
       
       //frameworks/libs/modules-utils/java/com/android/internal/util/StateMachine.java
       private SmHandler mSmHandler;
       /**
        * @return Handler, maybe null if state machine has quit.
        */
       public final Handler getHandler() {
           return mSmHandler;
       }
       ```

3. Registrant的notifyRegistrant()方法

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
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
     //InboundSmsHandler的内部类DeliveringState的processMessage()响应EVENT_NEW_SMS消息
     @Override
     public boolean processMessage(Message msg) {
         if (DBG) log("DeliveringState.processMessage: processing " + getWhatToString(msg.what));
         switch (msg.what) {
             case EVENT_NEW_SMS:
                 // handle new SMS from RIL
                 handleNewSms((AsyncResult) msg.obj);
                 sendMessage(EVENT_RETURN_TO_IDLE);
                 return HANDLED;
     
             case EVENT_INJECT_SMS:
                 // handle new injected SMS
                 handleInjectSms((AsyncResult) msg.obj, msg.arg1 == 1 /* isOverIms */);
                 sendMessage(EVENT_RETURN_TO_IDLE);
                 return HANDLED;
     
             case EVENT_BROADCAST_SMS:
                 // if any broadcasts were sent, transition to waiting state
                 InboundSmsTracker inboundSmsTracker = (InboundSmsTracker) msg.obj;
                 if (processMessagePart(inboundSmsTracker)) {
                     sendMessage(obtainMessage(EVENT_UPDATE_TRACKER, msg.obj));
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
     
             case EVENT_RETURN_TO_IDLE:
                 // return to idle after processing all other messages
                 transitionTo(mIdleState);
                 return HANDLED;
     
             case EVENT_RELEASE_WAKELOCK:
                 mWakeLock.release();    // decrement wakelock from previous entry to Idle
                 if (!mWakeLock.isHeld()) {
                     // wakelock should still be held until 3 seconds after we enter Idle
                     loge("mWakeLock released while delivering/broadcasting!");
                 }
                 return HANDLED;
     
             case EVENT_UPDATE_TRACKER:
                 logd("process tracker message in DeliveringState " + msg.arg1);
                 return HANDLED;
     
             // we shouldn't get this message type in this state, log error and halt.
             case EVENT_BROADCAST_COMPLETE:
             case EVENT_START_ACCEPTING_SMS:
             default:
                 logeWithLocalLog("Unhandled msg in delivering state, msg.what = "
                         + getWhatToString(msg.what));
                 // let DefaultState handle these unexpected message types
                 return NOT_HANDLED;
         }
     }
     ```

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
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
     ```

5. InboundSmsHandler的dispatchMessage()方法

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
     /**
      * Process an SMS message from the RIL, calling subclass methods to handle 3GPP and
      * 3GPP2-specific message types.
      *
      * @param smsb the SmsMessageBase object from the RIL
      * @param smsSource the source of the SMS message
      * @return a result code from {@link android.provider.Telephony.Sms.Intents},
      *  or {@link Activity#RESULT_OK} for delayed acknowledgment to SMSC
      */
     private int dispatchMessage(SmsMessageBase smsb, @SmsSource int smsSource) {
         // If sms is null, there was a parsing error.
         if (smsb == null) {
             loge("dispatchSmsMessage: message is null");
             return RESULT_SMS_NULL_MESSAGE;
         }
     
         if (mSmsReceiveDisabled) {
             // Device doesn't support receiving SMS,
             log("Received short message on device which doesn't support "
                     + "receiving SMS. Ignored.");
             return Intents.RESULT_SMS_HANDLED;
         }
     
         // onlyCore indicates if the device is in cryptkeeper
         boolean onlyCore = false;
         try {
             onlyCore = IPackageManager.Stub.asInterface(ServiceManager.getService("package"))
                     .isOnlyCoreApps();
         } catch (RemoteException e) {
         }
         if (onlyCore) {
             // Device is unable to receive SMS in encrypted state
             log("Received a short message in encrypted state. Rejecting.");
             return Intents.RESULT_SMS_RECEIVED_WHILE_ENCRYPTED;
         }
     
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

6. InboundSmsHandler的子类GsmInboundSmsHandler的dispatchMessageRadioSpecific()方法

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/gsm/GsmInboundSmsHandler.java
     /**
      * Handle type zero, SMS-PP data download, and 3GPP/CPHS MWI type SMS. Normal SMS messages
      * are handled by {@link #dispatchNormalMessage} in parent class.
      *
      * @param smsb the SmsMessageBase object from the RIL
      * @param smsSource the source of the SMS message
      * @return a result code from {@link android.provider.Telephony.Sms.Intents},
      * or {@link Activity#RESULT_OK} for delayed acknowledgment to SMSC
      */
     @Override
     protected int dispatchMessageRadioSpecific(SmsMessageBase smsb, @SmsSource int smsSource) {
         SmsMessage sms = (SmsMessage) smsb;
     
         if (sms.isTypeZero()) {
             // Some carriers will send visual voicemail SMS as type zero.
             int destPort = -1;
             SmsHeader smsHeader = sms.getUserDataHeader();
             if (smsHeader != null && smsHeader.portAddrs != null) {
                 // The message was sent to a port.
                 destPort = smsHeader.portAddrs.destPort;
             }
             VisualVoicemailSmsFilter
                     .filter(mContext, new byte[][]{sms.getPdu()}, SmsConstants.FORMAT_3GPP,
                             destPort, mPhone.getSubId());
             // As per 3GPP TS 23.040 9.2.3.9, Type Zero messages should not be
             // Displayed/Stored/Notified. They should only be acknowledged.
             log("Received short message type 0, Don't display or store it. Send Ack");
             addSmsTypeZeroToMetrics(smsSource);
             return Intents.RESULT_SMS_HANDLED;
         }
     
         // Send SMS-PP data download messages to UICC. See 3GPP TS 31.111 section 7.1.1.
         if (sms.isUsimDataDownload()) {
             UsimServiceTable ust = mPhone.getUsimServiceTable();
             return mDataDownloadHandler.handleUsimDataDownload(ust, sms, smsSource);
         }
     
         boolean handled = false;
         if (sms.isMWISetMessage()) {
             updateMessageWaitingIndicator(sms.getNumOfVoicemails());
             handled = sms.isMwiDontStore();
             if (DBG) log("Received voice mail indicator set SMS shouldStore=" + !handled);
         } else if (sms.isMWIClearMessage()) {
             updateMessageWaitingIndicator(0);
             handled = sms.isMwiDontStore();
             if (DBG) log("Received voice mail indicator clear SMS shouldStore=" + !handled);
         }
         if (handled) {
             addVoicemailSmsToMetrics(smsSource);
             return Intents.RESULT_SMS_HANDLED;
         }
     
         if (!mStorageMonitor.isStorageAvailable() &&
                 sms.getMessageClass() != SmsConstants.MessageClass.CLASS_0) {
             // It's a storable message and there's no storage available.  Bail.
             // (See TS 23.038 for a description of class 0 messages.)
             return Intents.RESULT_SMS_OUT_OF_MEMORY;
         }
     
         return dispatchNormalMessage(smsb, smsSource);
     }
     ```

7. 保存pdu到sms provider，发送EVENT_BROADCAST_SMS消息

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
     /**
      * Dispatch a normal incoming SMS. This is called from {@link #dispatchMessageRadioSpecific}
      * if no format-specific handling was required. Saves the PDU to the SMS provider raw table,
      * creates an {@link InboundSmsTracker}, then sends it to the state machine as an
      * {@link #EVENT_BROADCAST_SMS}. Returns {@link Intents#RESULT_SMS_HANDLED} or an error value.
      *
      * @param sms the message to dispatch
      * @param smsSource the source of the SMS message
      * @return {@link Intents#RESULT_SMS_HANDLED} if the message was accepted, or an error status
      */
     @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
     protected int dispatchNormalMessage(SmsMessageBase sms, @SmsSource int smsSource) {
         SmsHeader smsHeader = sms.getUserDataHeader();
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
         return addTrackerToRawTableAndSendMessage(tracker,
                 tracker.getDestPort() == -1 /* de-dup if text message */);
     }
     ```

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
     //Helper to add the tracker to the raw table and then send a message to broadcast it, if successful. Returns the SMS intent status to return to the SMSC.
     protected int addTrackerToRawTableAndSendMessage(InboundSmsTracker tracker, boolean deDup) {
         //记录数据库
         int result = addTrackerToRawTable(tracker, deDup);
         switch(result) {
             case Intents.RESULT_SMS_HANDLED:
                  /**
                  * Enqueue a message to this state machine.
                  *
                  * Message is ignored if state machine has quit.
                  */
                 sendMessage(EVENT_BROADCAST_SMS, tracker);
                 return Intents.RESULT_SMS_HANDLED;
     
             case Intents.RESULT_SMS_DUPLICATED:
                 return Intents.RESULT_SMS_HANDLED;
     
             default:
                 return result;
         }
     }
     ```

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
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
     ```

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
     /**
      * Process the inbound SMS segment. If the message is complete, send it as an ordered
      * broadcast to interested receivers and return true. If the message is a segment of an
      * incomplete multi-part SMS, return false.
      * @param tracker the tracker containing the message segment to process
      * @return true if an ordered broadcast was sent; false if waiting for more message segments
      */
     @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
     private boolean processMessagePart(InboundSmsTracker tracker) {
         int messageCount = tracker.getMessageCount();
         byte[][] pdus;
         long[] timestamps;
         int destPort = tracker.getDestPort();
         boolean block = false;
         String address = tracker.getAddress();
     
         // Do not process when the message count is invalid.
         if (messageCount <= 0) {
             loge("processMessagePart: returning false due to invalid message count "
                     + messageCount, tracker.getMessageId());
             return false;
         }
     
         if (messageCount == 1) {
             // single-part message
             pdus = new byte[][]{tracker.getPdu()};
             timestamps = new long[]{tracker.getTimestamp()};
             block = BlockChecker.isBlocked(mContext, tracker.getDisplayAddress(), null);
         } else {
             // multi-part message
             Cursor cursor = null;
             try {
                 // used by several query selection arguments
                 String refNumber = Integer.toString(tracker.getReferenceNumber());
                 String count = Integer.toString(tracker.getMessageCount());
     
                 // query for all segments and broadcast message if we have all the parts
                 String[] whereArgs = {address, refNumber, count};
                 cursor = mResolver.query(sRawUri, PDU_SEQUENCE_PORT_PROJECTION,
                         tracker.getQueryForSegments(), whereArgs, null);
     
                 int cursorCount = cursor.getCount();
                 if (cursorCount < messageCount) {
                     // Wait for the other message parts to arrive. It's also possible for the last
                     // segment to arrive before processing the EVENT_BROADCAST_SMS for one of the
                     // earlier segments. In that case, the broadcast will be sent as soon as all
                     // segments are in the table, and any later EVENT_BROADCAST_SMS messages will
                     // get a row count of 0 and return.
                     log("processMessagePart: returning false. Only " + cursorCount + " of "
                             + messageCount + " segments " + " have arrived. refNumber: "
                             + refNumber, tracker.getMessageId());
                     return false;
                 }
     
                 // All the parts are in place, deal with them
                 pdus = new byte[messageCount][];
                 timestamps = new long[messageCount];
                 while (cursor.moveToNext()) {
                     // subtract offset to convert sequence to 0-based array index
                     int index = cursor.getInt(PDU_SEQUENCE_PORT_PROJECTION_INDEX_MAPPING
                             .get(SEQUENCE_COLUMN)) - tracker.getIndexOffset();
     
                     // The invalid PDUs can be received and stored in the raw table. The range
                     // check ensures the process not crash even if the seqNumber in the
                     // UserDataHeader is invalid.
                     if (index >= pdus.length || index < 0) {
                         loge(String.format(
                                 "processMessagePart: invalid seqNumber = %d, messageCount = %d",
                                 index + tracker.getIndexOffset(),
                                 messageCount),
                                 tracker.getMessageId());
                         continue;
                     }
     
                     pdus[index] = HexDump.hexStringToByteArray(cursor.getString(
                             PDU_SEQUENCE_PORT_PROJECTION_INDEX_MAPPING.get(PDU_COLUMN)));
     
                     // Read the destination port from the first segment (needed for CDMA WAP PDU).
                     // It's not a bad idea to prefer the port from the first segment in other cases.
                     if (index == 0 && !cursor.isNull(PDU_SEQUENCE_PORT_PROJECTION_INDEX_MAPPING
                             .get(DESTINATION_PORT_COLUMN))) {
                         int port = cursor.getInt(PDU_SEQUENCE_PORT_PROJECTION_INDEX_MAPPING
                                 .get(DESTINATION_PORT_COLUMN));
                         // strip format flags and convert to real port number, or -1
                         port = InboundSmsTracker.getRealDestPort(port);
                         if (port != -1) {
                             destPort = port;
                         }
                     }
     
                     timestamps[index] = cursor.getLong(
                             PDU_SEQUENCE_PORT_PROJECTION_INDEX_MAPPING.get(DATE_COLUMN));
     
                     // check if display address should be blocked or not
                     if (!block) {
                         // Depending on the nature of the gateway, the display origination address
                         // is either derived from the content of the SMS TP-OA field, or the TP-OA
                         // field contains a generic gateway address and the from address is added
                         // at the beginning in the message body. In that case only the first SMS
                         // (part of Multi-SMS) comes with the display originating address which
                         // could be used for block checking purpose.
                         block = BlockChecker.isBlocked(mContext,
                                 cursor.getString(PDU_SEQUENCE_PORT_PROJECTION_INDEX_MAPPING
                                         .get(DISPLAY_ADDRESS_COLUMN)), null);
                     }
                 }
                 log("processMessagePart: all " + messageCount + " segments "
                         + " received. refNumber: " + refNumber, tracker.getMessageId());
             } catch (SQLException e) {
                 loge("processMessagePart: Can't access multipart SMS database, "
                         + SmsController.formatCrossStackMessageId(tracker.getMessageId()), e);
                 return false;
             } finally {
                 if (cursor != null) {
                     cursor.close();
                 }
             }
         }
         final boolean isWapPush = (destPort == SmsHeader.PORT_WAP_PUSH);
         String format = tracker.getFormat();
     
         // Do not process null pdu(s). Check for that and return false in that case.
         List<byte[]> pduList = Arrays.asList(pdus);
         if (pduList.size() == 0 || pduList.contains(null)) {
             String errorMsg = "processMessagePart: returning false due to "
                     + (pduList.size() == 0 ? "pduList.size() == 0" : "pduList.contains(null)");
             logeWithLocalLog(errorMsg, tracker.getMessageId());
             mPhone.getSmsStats().onIncomingSmsError(
                     is3gpp2(), tracker.getSource(), RESULT_SMS_NULL_PDU);
             return false;
         }
     
         ByteArrayOutputStream output = new ByteArrayOutputStream();
         if (isWapPush) {
             for (byte[] pdu : pdus) {
                 // 3GPP needs to extract the User Data from the PDU; 3GPP2 has already done this
                 if (format == SmsConstants.FORMAT_3GPP) {
                     SmsMessage msg = SmsMessage.createFromPdu(pdu, SmsConstants.FORMAT_3GPP);
                     if (msg != null) {
                         pdu = msg.getUserData();
                     } else {
                         loge("processMessagePart: SmsMessage.createFromPdu returned null",
                                 tracker.getMessageId());
                         mMetrics.writeIncomingWapPush(mPhone.getPhoneId(), tracker.getSource(),
                                 SmsConstants.FORMAT_3GPP, timestamps, false,
                                 tracker.getMessageId());
                         mPhone.getSmsStats().onIncomingSmsWapPush(tracker.getSource(),
                                 messageCount, RESULT_SMS_NULL_MESSAGE, tracker.getMessageId());
                         return false;
                     }
                 }
                 output.write(pdu, 0, pdu.length);
             }
         }
     
         //设置广播接收器
         SmsBroadcastReceiver resultReceiver = tracker.getSmsBroadcastReceiver(this);
     
         if (!mUserManager.isUserUnlocked()) {
             log("processMessagePart: !isUserUnlocked; calling processMessagePartWithUserLocked. "
                     + "Port: " + destPort, tracker.getMessageId());
             return processMessagePartWithUserLocked(
                     tracker,
                     (isWapPush ? new byte[][] {output.toByteArray()} : pdus),
                     destPort,
                     resultReceiver,
                     block);
         }
     
         if (isWapPush) {
             int result = mWapPush.dispatchWapPdu(output.toByteArray(), resultReceiver,
                     this, address, tracker.getSubId(), tracker.getMessageId());
             if (DBG) {
                 log("processMessagePart: dispatchWapPdu() returned " + result,
                         tracker.getMessageId());
             }
             // Add result of WAP-PUSH into metrics. RESULT_SMS_HANDLED indicates that the WAP-PUSH
             // needs to be ignored, so treating it as a success case.
             boolean wapPushResult =
                     result == Activity.RESULT_OK || result == Intents.RESULT_SMS_HANDLED;
             mMetrics.writeIncomingWapPush(mPhone.getPhoneId(), tracker.getSource(),
                     format, timestamps, wapPushResult, tracker.getMessageId());
             mPhone.getSmsStats().onIncomingSmsWapPush(tracker.getSource(), messageCount,
                     result, tracker.getMessageId());
             // result is Activity.RESULT_OK if an ordered broadcast was sent
             if (result == Activity.RESULT_OK) {
                 return true;
             } else {
                 deleteFromRawTable(tracker.getDeleteWhere(), tracker.getDeleteWhereArgs(),
                         MARK_DELETED);
                 loge("processMessagePart: returning false as the ordered broadcast for WAP push "
                         + "was not sent", tracker.getMessageId());
                 return false;
             }
         }
         
         // All parts of SMS are received. Update metrics for incoming SMS.
         // The metrics are generated before SMS filters are invoked.
         // For messages composed by multiple parts, the metrics are generated considering the
         // characteristics of the last one.
         mMetrics.writeIncomingSmsSession(mPhone.getPhoneId(), tracker.getSource(),
                 format, timestamps, block, tracker.getMessageId());
         mPhone.getSmsStats().onIncomingSmsSuccess(is3gpp2(), tracker.getSource(),
                 messageCount, block, tracker.getMessageId());
     
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
     ```

     - WAP push message:WAP Push是一种 SMS 类型，用于以已知、简单和容易的方式访问 WAP 站点或页面，而无需在我们的手机浏览器中输入页面的地址（URL）。它的工作方式与旋律、音调等下载系统类似。通过短信。用户发送一条 SMS 并收到另一条带有欢迎消息和 URL 的回复。接受 SMS 时，浏览器会打开并将我们定向到我们请求的 WAP 地址。与在我们的电子邮件中收到包含网址的消息并单击它时非常相似。

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
     /**
      * Creates and dispatches the intent to the default SMS app, appropriate port or via the {@link
      * AppSmsManager}.
      *
      * @param pdus message pdus
      * @param format the message format, typically "3gpp" or "3gpp2"
      * @param destPort the destination port
      * @param resultReceiver the receiver handling the delivery result
      */
     private void dispatchSmsDeliveryIntent(byte[][] pdus, String format, int destPort,
             SmsBroadcastReceiver resultReceiver, boolean isClass0, int subId, long messageId) {
         Intent intent = new Intent();
         intent.putExtra("pdus", pdus);
         intent.putExtra("format", format);
         if (messageId != 0L) {
             intent.putExtra("messageId", messageId);
         }
     
         if (destPort == -1) {
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
         dispatchIntent(intent, android.Manifest.permission.RECEIVE_SMS,
                 AppOpsManager.OPSTR_RECEIVE_SMS, options, resultReceiver, UserHandle.SYSTEM, subId);
     }
     ```

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
     /**
      * Dispatch the intent with the specified permission, appOp, and result receiver, using
      * this state machine's handler thread to run the result receiver.
      *
      * @param intent the intent to broadcast
      * @param permission receivers are required to have this permission
      * @param appOp app op that is being performed when dispatching to a receiver
      * @param user user to deliver the intent to
      */
     @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
     public void dispatchIntent(Intent intent, String permission, String appOp,
             Bundle opts, SmsBroadcastReceiver resultReceiver, UserHandle user, int subId) {
         intent.addFlags(Intent.FLAG_RECEIVER_NO_ABORT);
         final String action = intent.getAction();
         if (Intents.SMS_DELIVER_ACTION.equals(action)
                 || Intents.SMS_RECEIVED_ACTION.equals(action)
                 || Intents.WAP_PUSH_DELIVER_ACTION.equals(action)
                 || Intents.WAP_PUSH_RECEIVED_ACTION.equals(action)) {
             // Some intents need to be delivered with high priority:
             // SMS_DELIVER, SMS_RECEIVED, WAP_PUSH_DELIVER, WAP_PUSH_RECEIVED
             // In some situations, like after boot up or system under load, normal
             // intent delivery could take a long time.
             // This flag should only be set for intents for visible, timely operations
             // which is true for the intents above.
             intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
         }
         SubscriptionManager.putPhoneIdAndSubIdExtra(intent, mPhone.getPhoneId());
     
         // override the subId value in the intent with the values from tracker as they can be
         // different, specifically if the message is coming from SmsBroadcastUndelivered
         if (SubscriptionManager.isValidSubscriptionId(subId)) {
             SubscriptionManager.putSubscriptionIdExtra(intent, subId);
         }
     
         if (user.equals(UserHandle.ALL)) {
             // Get a list of currently started users.
             int[] users = null;
             final List<UserHandle> userHandles = mUserManager.getUserHandles(false);
             final List<UserHandle> runningUserHandles = new ArrayList();
             for (UserHandle handle : userHandles) {
                 if (mUserManager.isUserRunning(handle)) {
                     runningUserHandles.add(handle);
                 } else {
                     if (handle.equals(UserHandle.SYSTEM)) {
                         logeWithLocalLog("dispatchIntent: SYSTEM user is not running",
                                 resultReceiver.mInboundSmsTracker.getMessageId());
                     }
                 }
             }
             if (runningUserHandles.isEmpty()) {
                 users = new int[] {user.getIdentifier()};
             } else {
                 users = new int[runningUserHandles.size()];
                 for (int i = 0; i < runningUserHandles.size(); i++) {
                     users[i] = runningUserHandles.get(i).getIdentifier();
                 }
             }
             // Deliver the broadcast only to those running users that are permitted
             // by user policy.
             for (int i = users.length - 1; i >= 0; i--) {
                 UserHandle targetUser = UserHandle.of(users[i]);
                 if (users[i] != UserHandle.SYSTEM.getIdentifier()) {
                     // Is the user not allowed to use SMS?
                     if (hasUserRestriction(UserManager.DISALLOW_SMS, targetUser)) {
                         continue;
                     }
                     // Skip unknown users and managed profiles as well
                     if (mUserManager.isManagedProfile(users[i])) {
                         continue;
                     }
                 }
                 // Only pass in the resultReceiver when the user SYSTEM is processed.
                 try {
                     if (users[i] == UserHandle.SYSTEM.getIdentifier()) {
                         resultReceiver.setWaitingForIntent(intent);
                     }
                     mContext.createPackageContextAsUser(mContext.getPackageName(), 0, targetUser)
                             .sendOrderedBroadcast(intent, Activity.RESULT_OK, permission, appOp,
                                     users[i] == UserHandle.SYSTEM.getIdentifier()
                                             ? resultReceiver : null, getHandler(),
                                     null /* initialData */, null /* initialExtras */, opts);
                 } catch (PackageManager.NameNotFoundException ignored) {
                 }
             }
         } else {
             try {
                 resultReceiver.setWaitingForIntent(intent);
                 mContext.createPackageContextAsUser(mContext.getPackageName(), 0, user)
                         .sendOrderedBroadcast(intent, Activity.RESULT_OK, permission, appOp,
                                 resultReceiver, getHandler(), null /* initialData */,
                                 null /* initialExtras */, opts);
             } catch (PackageManager.NameNotFoundException ignored) {
             }
         }
     }
     ```

8. InboundSmsHandler的SmsBroadcastReceiver接收Intents.SMS_DELIVER_ACTION广播

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
     //InboundSmsHandler的内部类SmsBroadcastReceiver的onReceive()
     @Override
     public void onReceive(Context context, Intent intent) {
         if (intent == null) {
             logeWithLocalLog("onReceive: received null intent, faking " + mWaitingForIntent,
                     mInboundSmsTracker.getMessageId());
             return;
         }
         handleAction(intent, true);
     }
     ```

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/InboundSmsHandler.java
     //InboundSmsHandler的内部类SmsBroadcastReceiver的handleAction()
     private synchronized void handleAction(@NonNull Intent intent, boolean onReceive) {
         String action = intent.getAction();
         if (mWaitingForIntent == null || !mWaitingForIntent.getAction().equals(action)) {
             logeWithLocalLog("handleAction: Received " + action + " when expecting "
                     + mWaitingForIntent == null ? "none" : mWaitingForIntent.getAction(),
                     mInboundSmsTracker.getMessageId());
             return;
         }
     
         if (onReceive) {
             int durationMillis = (int) (System.currentTimeMillis() - mBroadcastTimeMillis);
             if (durationMillis >= 5000) {
                 loge("Slow ordered broadcast completion time for " + action + ": "
                         + durationMillis + " ms");
             } else if (DBG) {
                 log("Ordered broadcast completed for " + action + " in: "
                         + durationMillis + " ms");
             }
         }
     
         int subId = intent.getIntExtra(SubscriptionManager.EXTRA_SUBSCRIPTION_INDEX,
                 SubscriptionManager.INVALID_SUBSCRIPTION_ID);
         if (action.equals(Intents.SMS_DELIVER_ACTION)) {
             // Now dispatch the notification only intent
             intent.setAction(Intents.SMS_RECEIVED_ACTION);
             // Allow registered broadcast receivers to get this intent even
             // when they are in the background.
             intent.setComponent(null);
             // All running users will be notified of the received sms.
             Bundle options = handleSmsWhitelisting(null, false /* bgActivityStartAllowed */);
     
             setWaitingForIntent(intent);
             dispatchIntent(intent, android.Manifest.permission.RECEIVE_SMS,
                     AppOpsManager.OPSTR_RECEIVE_SMS,
                     options, this, UserHandle.ALL, subId);
         } else if (action.equals(Intents.WAP_PUSH_DELIVER_ACTION)) {
             // Now dispatch the notification only intent
             intent.setAction(Intents.WAP_PUSH_RECEIVED_ACTION);
             intent.setComponent(null);
             // Only the primary user will receive notification of incoming mms.
             // That app will do the actual downloading of the mms.
             long duration = mPowerWhitelistManager.whitelistAppTemporarilyForEvent(
                     mContext.getPackageName(),
                     PowerWhitelistManager.EVENT_MMS,
                     REASON_EVENT_MMS,
                     "mms-broadcast");
             BroadcastOptions bopts = BroadcastOptions.makeBasic();
             bopts.setTemporaryAppAllowlist(duration,
                     TEMPORARY_ALLOWLIST_TYPE_FOREGROUND_SERVICE_ALLOWED,
                     REASON_EVENT_MMS,
                     "");
             Bundle options = bopts.toBundle();
     
             String mimeType = intent.getType();
     
             setWaitingForIntent(intent);
             dispatchIntent(intent, WapPushOverSms.getPermissionForType(mimeType),
                     WapPushOverSms.getAppOpsStringPermissionForIntent(mimeType), options, this,
                     UserHandle.SYSTEM, subId);
         } else {
             // Now that the intents have been deleted we can clean up the PDU data.
             if (!Intents.DATA_SMS_RECEIVED_ACTION.equals(action)
                     && !Intents.SMS_RECEIVED_ACTION.equals(action)
                     && !Intents.WAP_PUSH_RECEIVED_ACTION.equals(action)) {
                 loge("unexpected BroadcastReceiver action: " + action);
             }
     
             if (onReceive) {
                 int rc = getResultCode();
                 if ((rc != Activity.RESULT_OK) && (rc != Intents.RESULT_SMS_HANDLED)) {
                     loge("a broadcast receiver set the result code to " + rc
                             + ", deleting from raw table anyway!");
                 } else if (DBG) {
                     log("successful broadcast, deleting from raw table.");
                 }
             }
     
             deleteFromRawTable(mDeleteWhere, mDeleteWhereArgs, MARK_DELETED);
             mWaitingForIntent = null;
             removeMessages(EVENT_RECEIVER_TIMEOUT);
             sendMessage(EVENT_BROADCAST_COMPLETE);
         }
     }   
     ```

9. 在android/packages/apps/Messaging/AndroidManifest.xml中SmsDeliverReceiver接收Intents.SMS_DELIVER_ACTION广播

   - ```xml-dtd
      <!--packages/apps/Messaging/AndroidManifest.xml -->
      <receiver android:name=".receiver.SmsDeliverReceiver"
                android:exported="true"
                android:permission="android.permission.BROADCAST_SMS">
          <intent-filter>
              <action android:name="android.provider.Telephony.SMS_DELIVER" />
          </intent-filter>
      </receiver>
      ```

   - ```java
     //packages/apps/Messaging/src/com/android/messaging/receiver/SmsDeliverReceiver.java
     /**
      * Class that receives incoming SMS messages on KLP+ Devices.
      */
     public final class SmsDeliverReceiver extends BroadcastReceiver {
         @Override
         public void onReceive(final Context context, final Intent intent) {
             SmsReceiver.deliverSmsIntent(context, intent);
         }
     }
     ```

10. 在android/packages/apps/Messaging/AndroidManifest.xml中SmsReceiver接收Intents.SMS_RECEIVED_ACTION广播

   - ```xml-dtd
     <!--packages/apps/Messaging/AndroidManifest.xml -->
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
     //packages/apps/Messaging/src/com/android/messaging/receiver/SmsReceiver.java
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
     ```

11. 在SmsReceiver的deliverSmsIntent()

   - ```java
     private static final String EXTRA_ERROR_CODE = "errorCode";
     private static final String EXTRA_SUB_ID = "subscription";
     
     public static void deliverSmsIntent(final Context context, final Intent intent) {
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
     ```

   - ```java
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

   - ```java
      //packages/apps/Messaging/src/com/android/messaging/sms/MmsUtils.java
      /**
      * Parse values from a received sms message
      *
      * @param context
      * @param msgs The received sms message content
      * @param error The received sms error
      * @return Parsed values from the message
      */
     public static ContentValues parseReceivedSmsMessage(
             final Context context, final SmsMessage[] msgs, final int error) {
         final SmsMessage sms = msgs[0];
         final ContentValues values = new ContentValues();
     
         values.put(Sms.ADDRESS, sms.getDisplayOriginatingAddress());
         values.put(Sms.BODY, buildMessageBodyFromPdus(msgs));
         if (MmsUtils.hasSmsDateSentColumn()) {
             // TODO:: The boxing here seems unnecessary.
             values.put(Sms.DATE_SENT, Long.valueOf(sms.getTimestampMillis()));
         }
         values.put(Sms.PROTOCOL, sms.getProtocolIdentifier());
         if (sms.getPseudoSubject().length() > 0) {
             values.put(Sms.SUBJECT, sms.getPseudoSubject());
         }
         values.put(Sms.REPLY_PATH_PRESENT, sms.isReplyPathPresent() ? 1 : 0);
         values.put(Sms.SERVICE_CENTER, sms.getServiceCenterAddress());
         // Error code
         values.put(Sms.ERROR_CODE, error);
     
         return values;
     }
     ```

12. ReceiveSmsMessageAction的executeAction()方法将新短信保存到数据库并通过Notification显示短信通知

   - ```java
     @Override
     protected Object executeAction() {
         final Context context = Factory.get().getApplicationContext();
         final ContentValues messageValues = actionParameters.getParcelable(KEY_MESSAGE_VALUES);
         final DatabaseWrapper db = DataModel.get().getDatabase();
     
         // Get the SIM subscription ID
         Integer subId = messageValues.getAsInteger(Sms.SUBSCRIPTION_ID);
         if (subId == null) {
             subId = ParticipantData.DEFAULT_SELF_SUB_ID;
         }
         // Make sure we have a sender address
         String address = messageValues.getAsString(Sms.ADDRESS);
         if (TextUtils.isEmpty(address)) {
             LogUtil.w(TAG, "Received an SMS without an address; using unknown sender.");
             address = ParticipantData.getUnknownSenderDestination();
             messageValues.put(Sms.ADDRESS, address);
         }
         final ParticipantData rawSender = ParticipantData.getFromRawPhoneBySimLocale(
                 address, subId);
     
         // TODO: Should use local timestamp for this?
         final long received = messageValues.getAsLong(Sms.DATE);
         // Inform sync that message has been added at local received timestamp
         final SyncManager syncManager = DataModel.get().getSyncManager();
         syncManager.onNewMessageInserted(received);
     
         // Make sure we've got a thread id
         final long threadId = MmsSmsUtils.Threads.getOrCreateThreadId(context, address);
         messageValues.put(Sms.THREAD_ID, threadId);
         final boolean blocked = BugleDatabaseOperations.isBlockedDestination(
                 db, rawSender.getNormalizedDestination());
         final String conversationId = BugleDatabaseOperations.
                 getOrCreateConversationFromRecipient(db, threadId, blocked, rawSender);
     
         final boolean messageInFocusedConversation =
                 DataModel.get().isFocusedConversation(conversationId);
         final boolean messageInObservableConversation =
                 DataModel.get().isNewMessageObservable(conversationId);
     
         MessageData message = null;
         // Only the primary user gets to insert the message into the telephony db and into bugle's
         // db. The secondary user goes through this path, but skips doing the actual insert. It
         // goes through this path because it needs to compute messageInFocusedConversation in order
         // to calculate whether to skip the notification and play a soft sound if the user is
         // already in the conversation.
         if (!OsUtil.isSecondaryUser()) {
             final boolean read = messageValues.getAsBoolean(Sms.Inbox.READ)
                     || messageInFocusedConversation;
             // If you have read it you have seen it
             final boolean seen = read || messageInObservableConversation || blocked;
             messageValues.put(Sms.Inbox.READ, read ? Integer.valueOf(1) : Integer.valueOf(0));
     
             // incoming messages are marked as seen in the telephony db
             messageValues.put(Sms.Inbox.SEEN, 1);
     
             // Insert into telephony
             final Uri messageUri = context.getContentResolver().insert(Sms.Inbox.CONTENT_URI,
                     messageValues);
     
             if (messageUri != null) {
                 if (LogUtil.isLoggable(TAG, LogUtil.DEBUG)) {
                     LogUtil.d(TAG, "ReceiveSmsMessageAction: Inserted SMS message into telephony, "
                             + "uri = " + messageUri);
                 }
             } else {
                 LogUtil.e(TAG, "ReceiveSmsMessageAction: Failed to insert SMS into telephony!");
             }
     
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
     
                 BugleDatabaseOperations.insertNewMessageInTransaction(db, message);
     
                 BugleDatabaseOperations.updateConversationMetadataInTransaction(db, conversationId,
                         message.getMessageId(), message.getReceivedTimeStamp(), blocked,
                         conversationServiceCenter, true /* shouldAutoSwitchSelfId */);
     
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

## Wap Push

- 消息

  - ```java
    [NW->MS] [MT] IMCSMS__RP_DATA (rl_id=3)
        Message Content
            GSM A-I/F RP - RP-DATA (Network to MS)
                Message Type RP-DATA (Network to MS)
                RP-Message Reference
                    RP-Message Reference: 0x22 (34)
                RP-Originator Address - (8613800200500)
                    Length: 8
                    1... .... = Extension: No Extension
                    .001 .... = Type of number: International Number (0x1)
                    .... 0001 = Numbering plan identification: ISDN/Telephony Numbering (ITU-T Rec. E.164 / ITU-T Rec. E.163) (0x1)
                    Called Party BCD Number: 8613800200500
                RP-Destination Address
                    Length: 0
                RP-User Data
                    Length: 156
                    TPDU (not displayed)
            GSM SMS TPDU (GSM 03.40) SMS-DELIVER
                0... .... = TP-RP: TP Reply Path parameter is not set in this SMS SUBMIT/DELIVER
                .1.. .... = TP-UDHI: The beginning of the TP UD field contains a Header in addition to the short message
                ..0. .... = TP-SRI: A status report shall not be returned to the SME
                .... 0... = TP-LP: The message has not been forwarded and is not a spawned message
                .... .1.. = TP-MMS: No more messages are waiting for the MS in this SC
                .... ..00 = TP-MTI: SMS-DELIVER (0)
                TP-Originating-Address - ()
                    Length: 12 address digits
                    1... .... = Extension: No extension
                    .111 .... = Type of number: Reserved for extension (7)
                    .... 1111 = Numbering plan: Reserved for extension (15)
                    TP-OA Digits:
                TP-PID: 0
                    00.. .... = Defines formatting for subsequent bits: 0x0
                    ..0. .... = Telematic interworking: no telematic interworking, but SME-to-SME protocol
                    ...0 0000 = The SM-AL protocol being used between the SME and the MS: 0
                TP-DCS: 4
                    00.. .... = Coding Group Bits: General Data Coding indication (0)
                    ..0. .... = Text: Not compressed
                    ...0 .... = Message Class: Reserved, no message class
                    .... 01.. = Character Set: 8 bit data (0x1)
                    .... ..00 = Message Class: Class 0 (0x0)
                TP-Service-Centre-Time-Stamp
                    Year: 21
                    Month: 8
                    Day: 5
                    Hour: 10
                    Minutes: 38
                    Seconds: 36
                    Timezone: GMT + 8 hours 0 minutes
                TP-User-Data-Length: (137) depends on Data-Coding-Scheme
                TP-User-Data
                    User-Data Header
                        User Data Header Length: 6
                        IE: Application port addressing scheme, 16 bit address (SMS Control)
                            Information Element Identifier: 0x05
                            Length: 4
                            Destination port: UDP/TCP port numbers assigned by IANA without the need to refer to 3GPP (2948)
                            Originator port: UDP/TCP port numbers assigned by IANA without the need to refer to 3GPP (9200)
                    Wireless Session Protocol, Method: Push (0x06), Content-Type: application/vnd.wap.mms-message
                        Transaction ID: 0x00
                        PDU Type: Push (0x06)
                        Headers Length: 3
                        Content-Type
                            .011 1110 = Header name: Content-Range (62)
                        Content-Type: application/vnd.wap.mms-message
                        Headers
                            Integer lookup: X-Wap-Application-Id
                                .010 1111 = Header name: X-Wap-Application-ID (47)
                            X-Wap-Application-Id: x-wap-application:mms.ua
                            [Expert Info (Error/Malformed): Malformed header]
                                [Malformed header]
                                [Severity level: Error]
                                [Group: Malformed]
                    MMS Message Encapsulation, Type: m-notification-ind
                        X-Mms-Message-Type: m-notification-ind (0x82)
                        X-Mms-Transaction-ID: 12EA8C1F189A0000ACB0000201
                        X-Mms-MMS-Version: 1.0
                        X-Mms-Message-Class: Personal (0x80)
                        X-Mms-Message-Size: 30720
                        X-Mms-Expiry: 431999.000000000 seconds
                        X-Mms-Content-Location: http://63.55.66.55/servlets/mms?message-id=
                        From: 7863017572/TYPE=PLMN
    ```

    - TPDU：SMS-DELIVER
    - TP-User-Data->User-Data Header->Destination port: 2948
    - Wireless Session Protocol, Method: Push (0x06)或者0x07
    - Content-Type:application/vnd.wap.mms-message
    - PORT_WAP_PUSH_SECURE   2949

- log流程

  - ![[image-20230210100644648.png]]


## VM

- ![[image-20230323135931567.png]]

- ![[image-20230410100224374.png]]

- ![[image-20230410101005674.png]]

- androidt/vendor/mediatek/proprietary/packages/services/Telephony/src/com/android/phone/NotificationMgr.java

- androidt/frameworks/opt/telephony/src/java/com/android/internal/telephony/uicc/SIMRecords.java

- server来通知已读

  - ```
    04-14 10:14:50.811749  1788  1981 W SmsMessage: MWI in DCS for Vmail. DCS = 200 Dont store = true vmail count = 0
    04-14 10:14:50.813162  1788  2042 D MtkGsmInboundSmsHandler-1: DeliveringState.processMessage: processing EVENT_NEW_SMS
    04-14 10:14:50.814512  1788  2042 D Phone : setVoiceMessageCount: Storing Voice Mail Count = 0 for mVmCountKey = vm_count_key3 in preferences.
    ```

    - vm为0：一般来讲server会发来count为0的通知这时候DUT才会真正清理

## VVM

- vvm sms with port 5499

  ```
  //received vvm sms with port 5499
  
  1222352, 787712, 15235103, 15:54:44:938 2023/01/31, MOD_IMCSMS, , MOD_IMCSMS_BASELINE_TRACE_PEER_H, [NW->MS] [MT] IMCSMS__RP_DATA (rl_id=3)
  Message Content
  GSM A-I/F RP - RP-DATA (Network to MS)
  Message Type RP-DATA (Network to MS)
  RP-Message Reference
  RP-Message Reference: 0x72 (114)
  RP-Originator Address - (19703769769)
  Length: 7
  1... .... = Extension: No Extension
  .001 .... = Type of number: International Number (0x1)
  .... 0001 = Numbering plan identification: ISDN/Telephony Numbering (ITU-T Rec. E.164 / ITU-T Rec. E.163) (0x1)
  Called Party BCD Number: 19703769769
  RP-Destination Address
  Length: 0
  RP-User Data
  Length: 130
  TPDU (not displayed)
  GSM SMS TPDU (GSM 03.40) SMS-DELIVER
  0... .... = TP-RP: TP Reply Path parameter is not set in this SMS SUBMIT/DELIVER
  .1.. .... = TP-UDHI: The beginning of the TP UD field contains a Header in addition to the short message
  ..0. .... = TP-SRI: A status report shall not be returned to the SME
  .... 0... = TP-LP: The message has not been forwarded and is not a spawned message
  .... .1.. = TP-MMS: No more messages are waiting for the MS in this SC
  .... ..00 = TP-MTI: SMS-DELIVER (0)
  TP-Originating-Address - ()
  Length: 10 address digits
  1... .... = Extension: No extension
  .111 .... = Type of number: Reserved for extension (7)
  .... 1111 = Numbering plan: Reserved for extension (15)
  TP-OA Digits:
  TP-PID: 0
  00.. .... = Defines formatting for subsequent bits: 0x0
  ..0. .... = Telematic interworking: no telematic interworking, but SME-to-SME protocol
  ...0 0000 = The SM-AL protocol being used between the SME and the MS: 0
  TP-DCS: 0
  00.. .... = Coding Group Bits: General Data Coding indication (0)
  Special case, GSM 7 bit default alphabet
  TP-Service-Centre-Time-Stamp
  Year: 23
  Month: 1
  Day: 31
  Hour: 18
  Minutes: 54
  Seconds: 46
  Timezone: GMT - 5 hours 0 minutes
  TP-User-Data-Length: (127) depends on Data-Coding-Scheme
  TP-User-Data
  User-Data Header
  User Data Header Length: 6
  IE: Application port addressing scheme, 16 bit address (SMS Control)
  Information Element Identifier: 0x05
  Length: 4
  Destination port: UDP/TCP port numbers assigned by IANA without the need to refer to 3GPP (5499)
  Originator port: UDP/TCP port numbers assigned by IANA without the need to refer to 3GPP (0)
  SMS text [truncated]:
  
  TRACE_NAME, IMCSMS_MT_NW_TO_MS_PEER_MSG
  
  01-31 15:55:40.095536 4482 4828 D RILJ : [UNSL]< UNSOL_RESPONSE_NEW_SMS [PHONE0]
  01-31 15:55:40.097609 4482 4893 D MtkGsmInboundSmsHandler-0: DeliveringState.processMessage: processing EVENT_NEW_SMS
  01-31 15:55:40.098388 4482 4893 D MtkGsmInboundSmsHandler-0: destination port: 5499
  ```

- vm和vvm不是一个服务器，留言状态不会同步。

  

# WEA

- Wireless Emergency Alerts ( WEA，以前称为商业移动警报系统( Commercial Mobile Alert System ，CMAS )，在此之前称为个人本地化警报网络( Personal Localized Alerting Network ，PLAN)是美国的一个警报网络，旨在将紧急警报传播到移动设备，例如手机和寻呼机。机构能够使用综合公共警报和警告系统通过 WEA 和其他公共系统传播和协调紧急警报和警告信息。
- 背景
  - 法案：*Warning, Alert, and Response Network (WARN) Act* 
- CMAS 将允许联邦机构接受和汇总来自美国总统、国家气象局(NWS) 和紧急行动中心的警报，并将警报发送给参与的无线提供商，无线提供商将通过小区广播方式将警报分发给兼容设备
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
  - Public Safety:4396
  - Public Safety Spanish : 4397
  - State Local/Test : 4398
  - State Local/Test Spanish : 4399
  - WEA Handset Action Message(WHAM) : 4400
- WEA Versions and Supported Capabilities 
  - WEA 1.0 
    - Supports 90-character WEA text 
    - Three alert classes (National, AMBER, Imminent Threat) 
    -  Geo-fencing based upon best approximation to a polygon or circle contained within a WEA 

  - WEA 2.0 
    - Supports 90- and 360-characters 
    - Supports optional Spanish language 
    - In addition to three classes listed above, allows for Public Safety and WEA Test categories 

  - WEA 3.0 
    - Supports Device Based Geo-Fencing (DBGF), an application running on the handset that allows the device to know if it is within a polygon/circle, or at least 0.10 mile outside, in order to display the alert, warning or notification to the user. 
    - Note: At this time the effect on DBGF is not fully known if location services are disabled on the device. 

- WHAM 消息：WHAM(WEA Handset Action Message) 该消息的目的是对之前接收的在区域外不显示的WEA消息进行二次处理。
  - 当接收的WEA消息(比如4370)被判断在区域外时, 会被临时存储下来, 不显示给用户. 在需要时, 比如进入新的小区等(网络来进行控制), 服务器端会再次下发一条WHAM消息, Channel ID 为4400, 携带message id + serialNumber, 来触发手机对本地保存的区域外的未显示的消息重新执行DBGF流程, 看看是否在区域内了, 如果此时已经在区域内了, 就显示出来, 如果依然在区域外, 还是不显示. 

- NV配置：Non Volatile

## 接收流程

1. 接收到RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS

   - 位于hardware/ril/libril/ril_unsol_commands.h

   - ```c
     //hardware/ril/libril/ril_unsol_commands.h
     {RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS, radio::newBroadcastSmsInd, WAKE_PARTIAL},
     ```

     

   - 具体的处理在hardware/ril/libril/ril_service.cpp中，通过IRadioIndication.newBroadcastSms()处理

   - ```c++
     //hardware/ril/libril/ril_service.cpp
     //sp<IRadioIndication> mRadioIndication;
     int radio::newBroadcastSmsInd(int slotId,
                                   int indicationType, int token, RIL_Errno e, void *response,
                                   size_t responseLen) {
         if (radioService[slotId] != NULL && radioService[slotId]->mRadioIndication != NULL) {
             if (response == NULL || responseLen == 0) {
                 RLOGE("newBroadcastSmsInd: invalid response");
                 return 0;
             }
     
             hidl_vec<uint8_t> data;
             data.setToExternal((uint8_t *) response, responseLen);
     #if VDBG
             RLOGD("newBroadcastSmsInd");
     #endif
             //具体处理
             Return<void> retStatus = radioService[slotId]->mRadioIndication->newBroadcastSms(
                     convertIntToRadioIndicationType(indicationType), data);
             radioService[slotId]->checkReturnStatus(retStatus);
         } else {
             RLOGE("newBroadcastSmsInd: radioService[%d]->mRadioIndication == NULL", slotId);
         }
     
         return 0;
     }
     ```

2. IRadioIndication.newBroadcastSms()跨进程调用

   - IRadioIndication.hidl位于hardware/interfaces/radio/1.0/IRadioIndication.hal

   - IRadioIndication.hidl的实现在frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioIndication.java

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioIndication.java
     public void newBroadcastSms(int indicationType, ArrayList<Byte> data) {
         mRil.processIndication(indicationType);
     	
         //将ArrayList<Byte>转换为byte[]
         byte response[] = RIL.arrayListToPrimitiveArray(data);
         if (RIL.RILJ_LOGD) {
             mRil.unsljLogvRet(RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS,
                     IccUtils.bytesToHexString(response));
         }
     
         if (mRil.mGsmBroadcastSmsRegistrant != null) {
             
             mRil.mGsmBroadcastSmsRegistrant.notifyRegistrant(new AsyncResult(null, response, null));
         }
     }
     ```

3. 在CellBroadcastServiceManager注册mGsmBroadcastSmsRegistrant和响应EVENT_NEW_GSM_SMS_CB消息

   - CellBroadcastServiceManager：Manages a single binding to the CellBroadcastService from the platform. In mSIM cases callers should have one CellBroadcastServiceManager per phone, and the CellBroadcastServiceManager will handle the single binding.

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/CellBroadcastServiceManager.java
     /*Enable the CB module. The CellBroadcastService will be bound to and CB messages from the RIL will be forwarded to the module*/
     public void enable() {
         initCellBroadcastServiceModule();
     }
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/CellBroadcastServiceManager.java
     private void initCellBroadcastServiceModule() {
         mEnabled = true;
         if (sServiceConnection == null) {
             sServiceConnection = new CellBroadcastServiceConnection();
         }
         mCellBroadcastServicePackage = getCellBroadcastServicePackage();
         if (mCellBroadcastServicePackage != null) {
             //private Handler mModuleCellBroadcastHandler = null;
             mModuleCellBroadcastHandler = new Handler() {
                 @Override
                 public void handleMessage(@NonNull Message msg) {
                     if (!mEnabled) {
                         Log.d(TAG, "CB module is disabled.");
                         return;
                     }
                     if (sServiceConnection.mService == null) {
                         final String errorMessage = "sServiceConnection.mService is null, ignoring message.";
                         Log.d(TAG, errorMessage);
                         CellBroadcastStatsLog.write(CellBroadcastStatsLog.CB_MESSAGE_ERROR,
                                 CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_ERROR__TYPE__NO_CONNECTION_TO_CB_SERVICE,
                                 errorMessage);
                         return;
                     }
                     try {
                         ICellBroadcastService cellBroadcastService =
                                 ICellBroadcastService.Stub.asInterface(
                                         sServiceConnection.mService);
                         //响应EVENT_NEW_GSM_SMS_CB消息
                         if (msg.what == EVENT_NEW_GSM_SMS_CB消息) {
                             mLocalLog.log("GSM SMS CB for phone " + mPhone.getPhoneId());
                             CellBroadcastStatsLog.write(CellBroadcastStatsLog.CB_MESSAGE_REPORTED,
                                     CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_REPORTED__TYPE__GSM,
                                     CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_REPORTED__SOURCE__FRAMEWORK);
                             cellBroadcastService.handleGsmCellBroadcastSms(mPhone.getPhoneId(),
                                     (byte[]) ((AsyncResult) msg.obj).result);
                         } 
                     } catch (RemoteException e) {
                         final String errorMessage = "Failed to connect to default app: "
                                 + mCellBroadcastServicePackage + " err: " + e.toString();
                         Log.e(TAG, errorMessage);
                         mLocalLog.log(errorMessage);
                         CellBroadcastStatsLog.write(CellBroadcastStatsLog.CB_MESSAGE_ERROR,
                                 CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_ERROR__TYPE__NO_CONNECTION_TO_CB_SERVICE,
                                 errorMessage);
                         mContext.unbindService(sServiceConnection);
                         sServiceConnection = null;
                     }
                 }
             };
             //绑定EVENT_NEW_GSM_SMS_CB消息
             mPhone.mCi.setOnNewGsmBroadcastSms(mModuleCellBroadcastHandler, EVENT_NEW_GSM_SMS_CB,null);
         }
     }
     
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/BaseCommands.java
     //ril是CommandsInterface，BaseCommands实现了CommandsInterface接口
     //BaseCommands.setOnNewGsmBroadcastSms（）
     public void setOnNewGsmBroadcastSms(Handler h, int what, Object obj) {
         mGsmBroadcastSmsRegistrant = new Registrant (h, what, obj);
     }
     ```

4. Registrant的notifyRegistrant()方法

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

5. CellBroadcastService的内部类ICellBroadcastServiceWrapper的handleGsmCellBroadcastSms()方法

   - CellBroadcastService位于frameworks/base/telephony/java/android/telephony/CellBroadcastService.java。

   - ICellBroadcastService.aidl位于frameworks/base/telephony/java/android/telephony/ICellBroadcastService.aidl

   - CellBroadcastService的内部类ICellBroadcastServiceWrapper实现了ICellBroadcastService，而CellBroadcastService的构造方法创建了ICellBroadcastServiceWrapper对象

     - ```java
       private final ICellBroadcastService.Stub mStubWrapper;
       
       public CellBroadcastService() {
           mStubWrapper = new ICellBroadcastServiceWrapper();
       }
       ```

   - ICellBroadcastServiceWrapper的handleGsmCellBroadcastSms()

     - ```java
       //frameworks/base/telephony/java/android/telephony/CellBroadcastService.java
       /**
        * Handle a GSM cell broadcast SMS.
        *
        * @param slotIndex the index of the slot which received the broadcast.mPhone.getPhoneId()，使用哪张卡接收
        * @param message   the SMS message PDU
        */
       @Override
       public void handleGsmCellBroadcastSms(int slotIndex, byte[] message) {
           //抽象方法，实际是DefaultCellBroadcastService的onGsmCellBroadcastSms()方法
           CellBroadcastService.this.onGsmCellBroadcastSms(slotIndex, message);
       }
       ```

6. DefaultCellBroadcastService的onGsmCellBroadcastSms()方法

   - DefaultCellBroadcastService位于packages/modules/CellBroadcastService/src/com/android/cellbroadcastservice/DefaultCellBroadcastService.java

   - DefaultCellBroadcastService：The default implementation of CellBroadcastService, which is used for handling GSM and CDMA cell broadcast messages.

   - ```java
     private GsmCellBroadcastHandler mGsmCellBroadcastHandler;
     public void onGsmCellBroadcastSms(int slotIndex, byte[] message) {
         Log.d(TAG, "onGsmCellBroadcastSms received message on slotId=" + slotIndex);
         CellBroadcastStatsLog.write(CellBroadcastStatsLog.CB_MESSAGE_REPORTED,
                 CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_REPORTED__TYPE__GSM,
                 CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_REPORTED__SOURCE__CB_SERVICE);
         mGsmCellBroadcastHandler.onGsmCellBroadcastSms(slotIndex, message);
     }
     ```

7. GsmCellBroadcastHandler的onGsmCellBroadcastSms()方法

   - GsmCellBroadcastHandler：Handler for 3GPP format Cell Broadcasts. Parent class can also handle CDMA Cell Broadcasts.

   - ```java
     //Handle a GSM cell broadcast message passed from the telephony framework.
     public void onGsmCellBroadcastSms(int slotIndex, byte[] message) {
         sendMessage(EVENT_NEW_SMS_MESSAGE, slotIndex, -1, message);
     }
     ```

8. EVENT_NEW_SMS_MESSAGE的消息响应

   - GsmCellBroadcastHandler的父类是CellBroadcastHandler。CellBroadcastHandler的父类是WakeLockStateMachine。WakeLockStateMachine的父类是StateMachine

   - WakeLockStateMachine：Generic state machine for handling messages and waiting for ordered broadcasts to complete.Subclasses implement handleSmsMessage(), which returns true to transition into waiting state, or false to remain in idle state. The wakelock is acquired on exit from idle state,and is released a few seconds after returning to idle state, or immediately upon calling quit().

     - IdleState：Idle state delivers Cell Broadcasts to receivers. It acquires the wakelock, which is released when the broadcast completes.
     - WaitingState：Waiting state waits for the result receiver to be called for the current cell broadcast.In this state, any new cell broadcasts are deferred until we return to Idle state.

   - ```java
     //packages/modules/CellBroadcastService/src/com/android/cellbroadcastservice/WakeLockStateMachine.java
     //IdleState的processMessage()方法
     public boolean processMessage(Message msg) {
         switch (msg.what) {
             case EVENT_NEW_SMS_MESSAGE:
                 log("Idle: new cell broadcast message");
                 // transition to waiting state if we sent a broadcast
                 if (handleSmsMessage(msg)) {
                     transitionTo(mWaitingState);
                 }
                 return HANDLED;
     
             case EVENT_RELEASE_WAKE_LOCK:
                 log("Idle: release wakelock");
                 releaseWakeLock();
                 return HANDLED;
     
             case EVENT_BROADCAST_NOT_REQUIRED:
                 log("Idle: broadcast not required");
                 return HANDLED;
     
             default:
                 return NOT_HANDLED;
         }
     }
     ```

9. GsmCellBroadcastHandler的handleSmsMessage()

   - ```java
     //packages/modules/CellBroadcastService/src/com/android/cellbroadcastservice/GsmCellBroadcastHandler.java
     protected boolean handleSmsMessage(Message message) {
     	if (message.obj instanceof SmsCbMessage) {
             //GsmCellBroadcastHandler的父类为CellBroadcastHandler
                 return super.handleSmsMessage(message);
             }
     }
     ```

10. CellBroadcastHandler的handleSmsMessage

   - ```java
     //packages/modules/CellBroadcastService/src/com/android/cellbroadcastservice/CellBroadcastHandler.java
     /**
      * Handle Cell Broadcast messages from {@code CdmaInboundSmsHandler}.
      * 3GPP-format Cell Broadcast messages sent from radio are handled in the subclass.
      *
      * @param message the message to process
      * @return true if need to wait for geo-fencing or an ordered broadcast was sent.
      */
     @Override
     protected boolean handleSmsMessage(Message message) {
         if (message.obj instanceof SmsCbMessage) {
             if (!isDuplicate((SmsCbMessage) message.obj)) {
                 handleBroadcastSms((SmsCbMessage) message.obj);
                 return true;
             } else {
                 CellBroadcastStatsLog.write(CellBroadcastStatsLog.CB_MESSAGE_FILTERED,
                         CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_FILTERED__TYPE__CDMA,
                         CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_FILTERED__FILTER__DUPLICATE_MESSAGE);
             }
             return false;
         } else {
             final String errorMessage =
                     "handleSmsMessage got object of type: " + message.obj.getClass().getName();
             loge(errorMessage);
             CellBroadcastStatsLog.write(CellBroadcastStatsLog.CB_MESSAGE_ERROR,
                     CELL_BROADCAST_MESSAGE_ERROR__TYPE__UNEXPECTED_CDMA_MESSAGE_TYPE_FROM_FWK,
                     errorMessage);
             return false;
         }
     }
     
     //packages/modules/CellBroadcastService/src/com/android/cellbroadcastservice/CellBroadcastHandler.java
     //Dispatch a Cell Broadcast message to listeners.
     protected void handleBroadcastSms(SmsCbMessage message) {
         int slotIndex = message.getSlotIndex();
     
         // TODO: Database inserting can be time consuming, therefore this should be changed to
         // asynchronous.
         ContentValues cv = message.getContentValues();
         Uri uri = mContext.getContentResolver().insert(CellBroadcasts.CONTENT_URI, cv);
         //需要Geo_Fencing
         if (message.needGeoFencingCheck()) {
             int maximumWaitingTime = getMaxLocationWaitingTime(message);
             if (DBG) {
                 log("Requesting location for geo-fencing. serialNumber = "
                         + message.getSerialNumber() + ", maximumWaitingTime = "
                         + maximumWaitingTime);
             }
     
             CbSendMessageCalculator calculator =
                     mCbSendMessageCalculatorFactory.createNew(mContext, message.getGeometries());
             requestLocationUpdate(new LocationUpdateCallback() {
                 @Override
                 public void onLocationUpdate(@NonNull LatLng location,
                         float accuracy) {
                     if (VDBG) {
                         logd("onLocationUpdate: location=" + location
                                 + ", acc=" + accuracy + ". "  + getMessageString(message));
                     }
                     performGeoFencing(message, uri, calculator, location, slotIndex,
                             accuracy);
                     if (!isMessageInAmbiguousState(calculator)) {
                         cancelLocationRequest();
                     }
                 }
     
                 @Override
                 public void onLocationUnavailable() {
                     CellBroadcastHandler.this.onLocationUnavailable(
                             calculator, message, uri, slotIndex);
                 }
             }, maximumWaitingTime);
         } else {
             if (DBG) {
                 log("Broadcast the message directly because no geo-fencing required, "
                         + " needGeoFencing = " + message.needGeoFencingCheck() + ". "
                         + getMessageString(message));
             }
             broadcastMessage(message, uri, slotIndex);
         }
     }
     ```
     
   - ```java
     //packages/modules/CellBroadcastService/src/com/android/cellbroadcastservice/CellBroadcastHandler.java
     /**
      * Broadcast the {@code message} to the applications.
      * @param message a message need to broadcast
      * @param messageUri message's uri
      */
     protected void broadcastMessage(@NonNull SmsCbMessage message, @Nullable Uri messageUri,
             int slotIndex) {
         String msg;
         Intent intent;
         if (VDBG) {
             logd("broadcastMessage: " + getMessageString(message));
         }
         
         if (message.isEmergencyMessage()) {
             msg = "Dispatching emergency SMS CB, SmsCbMessage is: " + message;
             log(msg);
             mLocalLog.log(msg);
             //ACTION_SMS_EMERGENCY_CB_RECEIVED
             intent = new Intent(Telephony.Sms.Intents.ACTION_SMS_EMERGENCY_CB_RECEIVED);
             //Emergency alerts need to be delivered with high priority
             intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
     
             intent.putExtra(EXTRA_MESSAGE, message);
             putPhoneIdAndSubIdExtra(mContext, intent, slotIndex);
     
             if (IS_DEBUGGABLE) {
                 // Send additional broadcast intent to the specified package. This is only for sl4a
                 // automation tests.
                 String[] testPkgs = mContext.getResources().getStringArray(
                         R.array.test_cell_broadcast_receiver_packages);
                 if (testPkgs != null) {
                     Intent additionalIntent = new Intent(intent);
                     for (String pkg : testPkgs) {
                         additionalIntent.setPackage(pkg);
                         mContext.createContextAsUser(UserHandle.ALL, 0).sendOrderedBroadcast(
                                 intent, null, (Bundle) null, null, getHandler(),
                                 Activity.RESULT_OK, null, null);
     
                     }
                 }
             }
     
             List<String> pkgs = new ArrayList<>();
             pkgs.add(getDefaultCBRPackageName(mContext, intent));
             pkgs.addAll(Arrays.asList(mContext.getResources().getStringArray(
                     R.array.additional_cell_broadcast_receiver_packages)));
             if (pkgs != null) {
                 mReceiverCount.addAndGet(pkgs.size());
                 for (String pkg : pkgs) {
                     // Explicitly send the intent to all the configured cell broadcast receivers.
                     intent.setPackage(pkg);
                     mContext.createContextAsUser(UserHandle.ALL, 0).sendOrderedBroadcast(
                             intent, null, (Bundle) null, mOrderedBroadcastReceiver, getHandler(),
                             Activity.RESULT_OK, null, null);
                 }
             }
         } else {
             msg = "Dispatching SMS CB, SmsCbMessage is: " + message;
             log(msg);
             mLocalLog.log(msg);
             // Send implicit intent since there are various 3rd party carrier apps listen to
             // this intent.
     
             mReceiverCount.incrementAndGet();
             CellBroadcastIntents.sendSmsCbReceivedBroadcast(
                     mContext, UserHandle.ALL, message, mOrderedBroadcastReceiver, getHandler(),
                     Activity.RESULT_OK, slotIndex);
         }
     
         if (messageUri != null) {
             ContentValues cv = new ContentValues();
             cv.put(CellBroadcasts.MESSAGE_BROADCASTED, 1);
             mContext.getContentResolver().update(CellBroadcasts.CONTENT_URI, cv,
                     CellBroadcasts._ID + "=?", new String[] {messageUri.getLastPathSegment()});
         }
     }
     ```

11. CellBroadcastReceiver接受广播

    - 在packages/apps/CellBroadcastReceiver/AndroidManifest.xml中CellBroadcastReceiver接收ACTION_SMS_EMERGENCY_CB_RECEIVED

      - ```xml-dtd
        <receiver android:name="com.android.cellbroadcastreceiver.CellBroadcastReceiver"
            android:exported="true">
            <intent-filter>
                <action android:name="android.telephony.action.DEFAULT_SMS_SUBSCRIPTION_CHANGED" />
                <action android:name="android.telephony.action.CARRIER_CONFIG_CHANGED" />
                <action android:name="android.provider.action.SMS_EMERGENCY_CB_RECEIVED" />
                <action android:name="android.provider.Telephony.SMS_CB_RECEIVED" />
                <action android:name="android.cellbroadcastreceiver.START_CONFIG" />
                <action android:name="android.provider.Telephony.SMS_SERVICE_CATEGORY_PROGRAM_DATA_RECEIVED" />
                <action android:name="android.intent.action.LOCALE_CHANGED" />
                <action android:name="android.intent.action.SERVICE_STATE" />
                <action android:name="android.intent.action.BOOT_COMPLETED" />
                <action android:name="android.telephony.action.SIM_CARD_STATE_CHANGED" />
            </intent-filter>
            <intent-filter>
                <action android:name="android.telephony.action.SECRET_CODE" />
                <!-- CMAS: To toggle test mode for cell broadcast testing on userdebug build -->
                <data android:scheme="android_secret_code" android:host="2627" />
            </intent-filter>
        </receiver>
        ```

    - onReceive()

      - ```java
        //packages/apps/CellBroadcastReceiver/src/com/android/cellbroadcastreceiver/CellBroadcastReceiver.java
        public void onReceive(Context context, Intent intent) {
            if (DBG) log("onReceive " + intent);
        
            mContext = context.getApplicationContext();
            String action = intent.getAction();
            Resources res = getResourcesMethod();
        
            else if (Telephony.Sms.Intents.ACTION_SMS_EMERGENCY_CB_RECEIVED.equals(action) ||
                    Telephony.Sms.Intents.SMS_CB_RECEIVED_ACTION.equals(action)) {
                intent.setClass(mContext, CellBroadcastAlertService.class);
                mContext.startService(intent);
            }
        }
        ```

12. CellBroadcastAlertService的onStartCommand()

    - ```java
      //packages/apps/CellBroadcastReceiver/src/com/android/cellbroadcastreceiver/CellBroadcastAlertService.java
      public int onStartCommand(Intent intent, int flags, int startId) {
          mContext = getApplicationContext();
          String action = intent.getAction();
          Log.d(TAG, "onStartCommand: " + action);
          if (Telephony.Sms.Intents.ACTION_SMS_EMERGENCY_CB_RECEIVED.equals(action) ||
                  Telephony.Sms.Intents.SMS_CB_RECEIVED_ACTION.equals(action)) {
              handleCellBroadcastIntent(intent);
          } else if (SHOW_NEW_ALERT_ACTION.equals(action)) {
              if (UserHandle.myUserId() == ((ActivityManager) getSystemService(
                      Context.ACTIVITY_SERVICE)).getCurrentUser()) {
                  showNewAlert(intent);
              } else {
                  Log.d(TAG, "Not active user, ignore the alert display");
              }
          } else {
              Log.e(TAG, "Unrecognized intent action: " + action);
          }
          return START_NOT_STICKY;
      }
      ```

13. CellBroadcastAlertService的handleCellBroadcastIntent()

    - ```java
      //packages/apps/CellBroadcastReceiver/src/com/android/cellbroadcastreceiver/CellBroadcastAlertService.java
      private void handleCellBroadcastIntent(Intent intent) {
          Bundle extras = intent.getExtras();
          if (extras == null) {
              Log.e(TAG, "received SMS_CB_RECEIVED_ACTION with no extras!");
              return;
          }
      
          SmsCbMessage message = (SmsCbMessage) extras.get(EXTRA_MESSAGE);
      
          if (message == null) {
              Log.e(TAG, "received SMS_CB_RECEIVED_ACTION with no message extra");
              return;
          }
      
          if (message.getMessageFormat() == MESSAGE_FORMAT_3GPP) {
              CellBroadcastStatsLog.write(CellBroadcastStatsLog.CB_MESSAGE_REPORTED,
                      CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_REPORTED__TYPE__GSM,
                      CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_REPORTED__SOURCE__CB_RECEIVER_APP);
          } else if (message.getMessageFormat() == MESSAGE_FORMAT_3GPP2) {
              CellBroadcastStatsLog.write(CellBroadcastStatsLog.CB_MESSAGE_REPORTED,
                      CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_REPORTED__TYPE__CDMA,
                      CellBroadcastStatsLog.CELL_BROADCAST_MESSAGE_REPORTED__SOURCE__CB_RECEIVER_APP);
          }
      
          if (!shouldDisplayMessage(message)) {
              return;
          }
      
          final Intent alertIntent = new Intent(SHOW_NEW_ALERT_ACTION);
          alertIntent.setClass(this, CellBroadcastAlertService.class);
          alertIntent.putExtra(EXTRA_MESSAGE, message);
      
          // write to database on a background thread
          new CellBroadcastContentProvider.AsyncCellBroadcastTask(getContentResolver())
                  .execute((CellBroadcastContentProvider.CellBroadcastOperation) provider -> {
                      CellBroadcastChannelManager channelManager =
                              new CellBroadcastChannelManager(mContext, message.getSubscriptionId());
                      CellBroadcastChannelRange range = channelManager
                              .getCellBroadcastChannelRangeFromMessage(message);
                      // Check if the message was marked as do not display. Some channels
                      // are reserved for biz purpose where the msg should be routed as a data SMS
                      // rather than being displayed as pop-up or notification. However,
                      // per requirements those messages might also need to write to sms inbox...
                      boolean ret = false;
                      if (range != null && range.mDisplay == true) {
                          if (provider.insertNewBroadcast(message)) {
                              // new message, show the alert or notification on UI thread
                              // if not display..
                              startService(alertIntent);
                              // mark the message as displayed to the user.
                              markMessageDisplayed(message);
                              ret = true;
                          }
                      } else {
                          Log.d(TAG, "ignoring the alert due to configured channels was marked "
                                  + "as do not display");
                      }
                      if (CellBroadcastSettings.getResources(mContext, message.getSubscriptionId())
                              .getBoolean(R.bool.enable_write_alerts_to_sms_inbox)) {
                          if (CellBroadcastReceiver.isTestingMode(getApplicationContext())
                                  || (range != null && range.mWriteToSmsInbox)) {
                              provider.writeMessageToSmsInbox(message, mContext);
                          }
                      }
                      return ret;
                  });
      }
      ```

14. SHOW_NEW_ALERT_ACTION的处理逻辑是CellBroadcastAlertService的showNewAlert()根据

    - ```java
      //packages/apps/CellBroadcastReceiver/src/com/android/cellbroadcastreceiver/CellBroadcastAlertService.java
      private void showNewAlert(Intent intent) {
          Bundle extras = intent.getExtras();
          if (extras == null) {
              Log.e(TAG, "received SHOW_NEW_ALERT_ACTION with no extras!");
              return;
          }
      
          SmsCbMessage cbm = intent.getParcelableExtra(EXTRA_MESSAGE);
      
          if (cbm == null) {
              Log.e(TAG, "received SHOW_NEW_ALERT_ACTION with no message extra");
              return;
          }
      
          if (mTelephonyManager.getCallState() != TelephonyManager.CALL_STATE_IDLE
                  && CellBroadcastSettings.getResources(mContext, cbm.getSubscriptionId())
                  .getBoolean(R.bool.enable_alert_handling_during_call)) {
              Log.d(TAG, "CMAS received in dialing/during voicecall.");
              sRemindAfterCallFinish = true;
          }
      
          // Either shown the dialog, adding it to notification (non emergency, or delayed emergency),
          //CellBroadcastChannelManager handles the additional cell broadcast channels that carriers might enable through resources.
          CellBroadcastChannelManager channelManager = new CellBroadcastChannelManager(
                  mContext, cbm.getSubscriptionId());
          if (channelManager.isEmergencyMessage(cbm) && !sRemindAfterCallFinish) {
              // start alert sound / vibration / TTS and display full-screen alert
              openEmergencyAlertNotification(cbm);
              Resources res = CellBroadcastSettings.getResources(mContext, cbm.getSubscriptionId());
              // KR carriers mandate to always show notifications along with alert dialog.
              if (res.getBoolean(R.bool.show_alert_dialog_with_notification) ||
                      // to support emergency alert on companion devices use flag
                      // show_notification_if_connected_to_companion_devices instead.
                      (res.getBoolean(R.bool.show_notification_if_connected_to_companion_devices)
                              && isConnectedToCompanionDevices())) {
                  // add notification to the bar by passing the list of unread non-emergency
                  // cell broadcast messages. The notification should be of LOW_IMPORTANCE if the
                  // notification is shown together with full-screen dialog.
                  addToNotificationBar(cbm, CellBroadcastReceiverApp.addNewMessageToList(cbm),
                          this, false, true, shouldDisplayFullScreenMessage(cbm));
              }
          } else {
              // add notification to the bar by passing the list of unread non-emergency
              // cell broadcast messages
              ArrayList<SmsCbMessage> messageList = CellBroadcastReceiverApp
                      .addNewMessageToList(cbm);
              addToNotificationBar(cbm, messageList, this, false, true, false);
          }
      }
      ```

# PLMN和SPN

- PLMN(Public Land Mobile Network):当前SIM所驻留的网络
- SPN(Service Provider Name)：当前发行SIM卡的运营商的名称

## modem侧获取PLMN优先级

1. Eons(Enhanced Operator Name String)，也就是从SIM的EF_OPL和EF_PNN分区来读取Plmn Name
   - EF:Elementary File
   - EF_OPL中存放的是LAC和EF_PNN中的Record Identifier
   - EF_PNN中存放的是Network Name，也就是具体的Plmn Name
   - 如果注册上的网络是HPLMN，那么EF_OPL返回的Record Identifier就是1。
   - 如果不是HPLMN的话，就根据LAC在EF_OPL中寻找对应的Record Identifier，然后根据OPL 的Record Identifier，在PNN中找对应的Network Name。
     - Record Identifier是基于1的，而EF_PNN的记录是基于0的。也就是说， Record Identifier是1，那匹配的是EF_PNN中的第0条记录。
2. CPHS ONS(Common PCN Handset Specification Operator Name String)，该字串也是保存在SIM文件系统中
   - PCN:Personal Communication Network
   - 该取值要求当前手机注册到HPLMN网络，此时Modem将会先读取SIM中的CPHS ONS的长格式文件(6F14)，如果存在，则将其作为Plmn上报，否则的话读取短格式文件(6F18)，如果存在，则将其作为Plmn上报。
3. NITZ Operator Name
   - NITZ:Network Identity and Time Zone
   - 该名称是由当前注册的网络下发给手机的，如果该值存在，就会将该值作为Plmn 那么上报给AP。
4. 配置文件读取
   - 平台自身会提供从手机内存中根据当前注册的MCC MNC读取相应的Plmn Name，一般都是一个类似于apns-conf.xml的文件，在开机的时候被加载，这个方法在每个平台中也会不同。
5. MCC、MNC数字作为Plmn Name
   - 如果连ROM都没有找到当前Plmn对应的Name，那么就会把当前注册的Plmn所对应的MCC、 
     MNC数字作为当前的Plmn Name

## PLMN流程

1. TeleService 系统应用加载Telephony业务模型的过程中，同步完成 ServiceStateTracker 对象的创建，同时注册网络变化相关的Registration。

   - ServiceStateTracker 位于frameworks/opt/telephony/src/java/com/android/internal/telephony/ServiceStateTracker.java

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/ServiceStateTracker.java
     /**
      * A complete "service state" from our perspective is
      * composed of a handful of separate requests to the radio.
      *
      * We make all of these requests at once, but then abandon them
      * and start over again if the radio notifies us that some
      * event has changed
      */
     private void pollStateInternal(boolean modemTriggered) {
         mPollingContext = new int[1];
         mPollingContext[0] = 0;
     
         log("pollState: modemTriggered=" + modemTriggered);
     
         switch (mCi.getRadioState()) {
             case TelephonyManager.RADIO_POWER_UNAVAILABLE:
                 mNewSS.setStateOutOfService();
                 setSignalStrengthDefaultValues();
                 mLastNitzData = null;
                 mNitzState.handleNetworkUnavailable();
                 pollStateDone();
                 break;
     
             case TelephonyManager.RADIO_POWER_OFF:
                 mNewSS.setStateOff();
                 setSignalStrengthDefaultValues();
                 mLastNitzData = null;
                 mNitzState.handleNetworkUnavailable();
                 // don't poll when device is shutting down or the poll was not modemTrigged
                 // (they sent us new radio data) and current network is not IWLAN
                 if (mDeviceShuttingDown ||
                         (!modemTriggered && ServiceState.RIL_RADIO_TECHNOLOGY_IWLAN
                         != mSS.getRilDataRadioTechnology())) {
                     pollStateDone();
                     break;
                 }
     
             default:
                 // Issue all poll-related commands at once then count down the responses, which
                 // are allowed to arrive out-of-order
                 mPollingContext[0]++;
                 mCi.getOperator(obtainMessage(EVENT_POLL_STATE_OPERATOR, mPollingContext));
     
                 mPollingContext[0]++;
                 mRegStateManagers.get(AccessNetworkConstants.TRANSPORT_TYPE_WWAN)
                         .requestNetworkRegistrationInfo(NetworkRegistrationInfo.DOMAIN_PS,
                                 obtainMessage(EVENT_POLL_STATE_PS_CELLULAR_REGISTRATION,
                                         mPollingContext));
     
                 mPollingContext[0]++;
                 mRegStateManagers.get(AccessNetworkConstants.TRANSPORT_TYPE_WWAN)
                         .requestNetworkRegistrationInfo(NetworkRegistrationInfo.DOMAIN_CS,
                         obtainMessage(EVENT_POLL_STATE_CS_CELLULAR_REGISTRATION, mPollingContext));
     
                 if (mRegStateManagers.get(AccessNetworkConstants.TRANSPORT_TYPE_WLAN) != null) {
                     mPollingContext[0]++;
                     mRegStateManagers.get(AccessNetworkConstants.TRANSPORT_TYPE_WLAN)
                             .requestNetworkRegistrationInfo(NetworkRegistrationInfo.DOMAIN_PS,
                                     obtainMessage(EVENT_POLL_STATE_PS_IWLAN_REGISTRATION,
                                             mPollingContext));
                 }
     
                 if (mPhone.isPhoneTypeGsm()) {
                     mPollingContext[0]++;
                     mCi.getNetworkSelectionMode(obtainMessage(
                             EVENT_POLL_STATE_NETWORK_SELECTION_MODE, mPollingContext));
                 }
                 break;
         }
     }
     ```

2. RIL类的getOperator()创建RILRequest向modem发起请求

   - ```java
      //frameworks/opt/telephony/src/java/com/android/internal/telephony/RIL.java
      /**
       * response.obj.result is a String[3]
       * response.obj.result[0] is long alpha or null if unregistered
       * response.obj.result[1] is short alpha or null if unregistered
       * response.obj.result[2] is numeric or null if unregistered
       */
      @Override
      public void getOperator(Message result) {
          RadioNetworkProxy networkProxy = getRadioServiceProxy(RadioNetworkProxy.class, result);
          if (!networkProxy.isEmpty()) {
              RILRequest rr = obtainRequest(RIL_REQUEST_OPERATOR, result, mRILDefaultWorkSource);
      
              if (RILJ_LOGD) {
                  riljLog(rr.serialString() + "> " + RILUtils.requestToString(rr.mRequest));
              }
      
              try {
                  networkProxy.getOperator(rr.mSerial);
              } catch (RemoteException | RuntimeException e) {
                  handleRadioProxyExceptionForRR(NETWORK_SERVICE, "getOperator", e);
              }
          }
      }
      ```

   - ```java
      //hardware/ril/libril/ril_commands.h
      {RIL_REQUEST_OPERATOR, radio::getOperatorResponse},
      ```

   - ```c++
      //hardware/ril/libril/ril_service.cpp
      int radio::getOperatorResponse(int slotId,
                                    int responseType, int serial, RIL_Errno e, void *response,
                                    size_t responseLen) {
      #if VDBG
          RLOGD("getOperatorResponse: serial %d", serial);
      #endif
      
          if (radioService[slotId]->mRadioResponse != NULL) {
              RadioResponseInfo responseInfo = {};
              populateResponseInfo(responseInfo, serial, responseType, e);
              hidl_string longName;
              hidl_string shortName;
              hidl_string numeric;
              int numStrings = responseLen / sizeof(char *);
              if (response == NULL || numStrings != 3) {
                  RLOGE("getOperatorResponse Invalid response: NULL");
                  if (e == RIL_E_SUCCESS) responseInfo.error = RadioError::INVALID_RESPONSE;
      
              } else {
                  char **resp = (char **) response;
                  longName = convertCharPtrToHidlString(resp[0]);
                  shortName = convertCharPtrToHidlString(resp[1]);
                  numeric = convertCharPtrToHidlString(resp[2]);
              }
              Return<void> retStatus = radioService[slotId]->mRadioResponse->getOperatorResponse(
                      responseInfo, longName, shortName, numeric);
              radioService[slotId]->checkReturnStatus(retStatus);
          } else {
              RLOGE("getOperatorResponse: radioService[%d]->mRadioResponse == NULL",
                      slotId);
          }
      
          return 0;
      }
      ```

3. RadioResponse的getOperatorResponse()处理相对应的返回结果

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioResponse.java
     /**
      * @param responseInfo Response info struct containing response type, serial no. and error
      * @param longName is long alpha ONS or EONS or empty string if unregistered
      * @param shortName is short alpha ONS or EONS or empty string if unregistered
      * @param numeric is 5 or 6 digit numeric code (MCC + MNC) or empty string if unregistered
      */
     public void getOperatorResponse(RadioResponseInfo responseInfo,
             String longName, String shortName, String numeric) {
         responseStrings(responseInfo, longName, shortName, numeric);
     }
     ```

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioResponse.java
     private void responseStrings(RadioResponseInfo responseInfo, String ...str) {
         ArrayList<String> strings = new ArrayList<>();
         for (int i = 0; i < str.length; i++) {
             strings.add(str[i]);
         }
         responseStringArrayList(mRil, responseInfo, strings);
     }
     ```

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioResponse.java
     static void responseStringArrayList(RIL ril, RadioResponseInfo responseInfo,
             ArrayList<String> strings) {
         RILRequest rr = ril.processResponse(responseInfo);
     
         if (rr != null) {
             String[] ret = new String[strings.size()];
             for (int i = 0; i < strings.size(); i++) {
                 ret[i] = strings.get(i);
             }
             if (responseInfo.error == RadioError.NONE) {
                 sendMessageResponse(rr.mResult, ret);
             }
             ril.processResponseDone(rr, responseInfo, ret);
         }
     }
     ```

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/RadioResponse.java
     /**
      * Helper function to send response msg
      * @param msg Response message to be sent
      * @param ret Return object to be included in the response message
      */
     static void sendMessageResponse(Message msg, Object ret) {
         if (msg != null) {
             AsyncResult.forMessage(msg, ret, null);
             msg.sendToTarget();
         }
     }
     ```

4. 接收到RIL的返回数据时，ServiceStateTracker响应EVENT_POLL_STATE_OPERATOR消息

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/ServiceStateTracker.java
     public void handleMessage(Message msg) {
         AsyncResult ar;
         int[] ints;
         Message message;
     
         if (VDBG) log("received event " + msg.what);
         switch (msg.what) {
             case EVENT_POLL_STATE_OPERATOR:
                     ar = (AsyncResult) msg.obj;
                     handlePollStateResult(msg.what, ar);
                     break;
         }
     }
     protected void handlePollStateResultMessage(int what, AsyncResult ar) {
         int ints[];
         switch (what) {
             case EVENT_POLL_STATE_OPERATOR: {
                 if (mPhone.isPhoneTypeGsm()) {
                     String opNames[] = (String[]) ar.result;
     
                     if (opNames != null && opNames.length >= 3) {
                         //protected ServiceState mNewSS;
                         mNewSS.setOperatorAlphaLongRaw(opNames[0]);
                         mNewSS.setOperatorAlphaShortRaw(opNames[1]);
                         // FIXME: Giving brandOverride higher precedence, is this desired?
                         String brandOverride = getOperatorBrandOverride();
                         mCdnr.updateEfForBrandOverride(brandOverride);
                         if (brandOverride != null) {
                             log("EVENT_POLL_STATE_OPERATOR: use brandOverride=" + brandOverride);
                             mNewSS.setOperatorName(brandOverride, brandOverride, opNames[2]);
                         } else {
                             mNewSS.setOperatorName(opNames[0], opNames[1], opNames[2]);
                         }
                     }
                 } 
                 break;
             }
         }
     }
     
     protected String getOperatorBrandOverride() {
     // MTK-END
         UiccCard card = mPhone.getUiccCard();
         if (card == null) return null;
         UiccProfile profile = card.getUiccProfile();
         if (profile == null) return null;
         return profile.getOperatorBrandOverride();
     }
     ```

   - ```java
     //frameworks/opt/telephony/src/java/com/android/internal/telephony/ServiceStateTracker.java
     protected void handlePollStateResult(int what, AsyncResult ar) {
         // Ignore stale requests from last poll
         if (ar.userObj != mPollingContext) return;
     
         if (ar.exception != null) {
             CommandException.Error err = null;
     
             if (ar.exception instanceof IllegalStateException) {
                 log("handlePollStateResult exception " + ar.exception);
             }
     
             if (ar.exception instanceof CommandException) {
                 err = ((CommandException)(ar.exception)).getCommandError();
             }
     
             if (mCi.getRadioState() != TelephonyManager.RADIO_POWER_ON) {
                 log("handlePollStateResult: Invalid response due to radio off or unavailable. "
                         + "Set ServiceState to out of service.");
                 pollStateInternal(false);
                 return;
             }
     
             if (err == CommandException.Error.RADIO_NOT_AVAILABLE) {
                 loge("handlePollStateResult: RIL returned RADIO_NOT_AVAILABLE when radio is on.");
                 cancelPollState();
                 return;
             }
     
             if (err != CommandException.Error.OP_NOT_ALLOWED_BEFORE_REG_NW) {
                 loge("handlePollStateResult: RIL returned an error where it must succeed: "
                         + ar.exception);
             }
         } else try {
             handlePollStateResultMessage(what, ar);
         } catch (RuntimeException ex) {
             loge("Exception while polling service state. Probably malformed RIL response." + ex);
         }
     
         mPollingContext[0]--;
     
         if (mPollingContext[0] == 0) {
             mNewSS.setEmergencyOnly(mEmergencyOnly);
             combinePsRegistrationStates(mNewSS);
             updateOperatorNameForServiceState(mNewSS);
             if (mPhone.isPhoneTypeGsm()) {
                 updateRoamingState();
             } else {
                 boolean namMatch = false;
                 if (!isSidsAllZeros() && isHomeSid(mNewSS.getCdmaSystemId())) {
                     namMatch = true;
                 }
     
                 // Setting SS Roaming (general)
                 if (mIsSubscriptionFromRuim) {
                     boolean isRoamingBetweenOperators = isRoamingBetweenOperators(
                             mNewSS.getVoiceRoaming(), mNewSS);
                     if (isRoamingBetweenOperators != mNewSS.getVoiceRoaming()) {
                         log("isRoamingBetweenOperators=" + isRoamingBetweenOperators
                                 + ". Override CDMA voice roaming to " + isRoamingBetweenOperators);
                         mNewSS.setVoiceRoaming(isRoamingBetweenOperators);
                     }
                 }
                 /**
                  * For CDMA, voice and data should have the same roaming status.
                  * If voice is not in service, use TSB58 roaming indicator to set
                  * data roaming status. If TSB58 roaming indicator is not in the
                  * carrier-specified list of ERIs for home system then set roaming.
                  */
                 final int dataRat = getRilDataRadioTechnologyForWwan(mNewSS);
                 if (ServiceState.isCdma(dataRat)) {
                     final boolean isVoiceInService =
                             (mNewSS.getState() == ServiceState.STATE_IN_SERVICE);
                     if (isVoiceInService) {
                         boolean isVoiceRoaming = mNewSS.getVoiceRoaming();
                         if (mNewSS.getDataRoaming() != isVoiceRoaming) {
                             log("Data roaming != Voice roaming. Override data roaming to "
                                     + isVoiceRoaming);
                             mNewSS.setDataRoaming(isVoiceRoaming);
                         }
                     } else {
                         /**
                          * As per VoiceRegStateResult from radio types.hal the TSB58
                          * Roaming Indicator shall be sent if device is registered
                          * on a CDMA or EVDO system.
                          */
                         boolean isRoamIndForHomeSystem = isRoamIndForHomeSystem(mRoamingIndicator);
                         if (mNewSS.getDataRoaming() == isRoamIndForHomeSystem) {
                             log("isRoamIndForHomeSystem=" + isRoamIndForHomeSystem
                                     + ", override data roaming to " + !isRoamIndForHomeSystem);
                             mNewSS.setDataRoaming(!isRoamIndForHomeSystem);
                         }
                     }
                 }
     
                 // Setting SS CdmaRoamingIndicator and CdmaDefaultRoamingIndicator
                 mNewSS.setCdmaDefaultRoamingIndicator(mDefaultRoamingIndicator);
                 mNewSS.setCdmaRoamingIndicator(mRoamingIndicator);
                 boolean isPrlLoaded = true;
                 if (TextUtils.isEmpty(mPrlVersion)) {
                     isPrlLoaded = false;
                 }
                 if (!isPrlLoaded || (mNewSS.getRilVoiceRadioTechnology()
                         == ServiceState.RIL_RADIO_TECHNOLOGY_UNKNOWN)) {
                     log("Turn off roaming indicator if !isPrlLoaded or voice RAT is unknown");
                     mNewSS.setCdmaRoamingIndicator(EriInfo.ROAMING_INDICATOR_OFF);
                 } else if (!isSidsAllZeros()) {
                     if (!namMatch && !mIsInPrl) {
                         // Use default
                         mNewSS.setCdmaRoamingIndicator(mDefaultRoamingIndicator);
                     } else if (namMatch && !mIsInPrl) {
                         // TODO: remove when we handle roaming on LTE/NR on CDMA+LTE phones
                         if (ServiceState.isPsOnlyTech(mNewSS.getRilVoiceRadioTechnology())) {
                             log("Turn off roaming indicator as voice is LTE or NR");
                             mNewSS.setCdmaRoamingIndicator(EriInfo.ROAMING_INDICATOR_OFF);
                         } else {
                             mNewSS.setCdmaRoamingIndicator(EriInfo.ROAMING_INDICATOR_FLASH);
                         }
                     } else if (!namMatch && mIsInPrl) {
                         // Use the one from PRL/ERI
                         mNewSS.setCdmaRoamingIndicator(mRoamingIndicator);
                     } else {
                         // It means namMatch && mIsInPrl
                         if ((mRoamingIndicator <= 2)) {
                             mNewSS.setCdmaRoamingIndicator(EriInfo.ROAMING_INDICATOR_OFF);
                         } else {
                             // Use the one from PRL/ERI
                             mNewSS.setCdmaRoamingIndicator(mRoamingIndicator);
                         }
                     }
                 }
     
                 if (mEriManager != null) {
                     int roamingIndicator = mNewSS.getCdmaRoamingIndicator();
                     mNewSS.setCdmaEriIconIndex(mEriManager.getCdmaEriIconIndex(roamingIndicator,
                             mDefaultRoamingIndicator));
                     mNewSS.setCdmaEriIconMode(mEriManager.getCdmaEriIconMode(roamingIndicator,
                             mDefaultRoamingIndicator));
                 }
     
                 // NOTE: Some operator may require overriding mCdmaRoaming
                 // (set by the modem), depending on the mRoamingIndicator.
     
                 if (DBG) {
                     log("Set CDMA Roaming Indicator to: " + mNewSS.getCdmaRoamingIndicator()
                             + ". voiceRoaming = " + mNewSS.getVoiceRoaming()
                             + ". dataRoaming = " + mNewSS.getDataRoaming()
                             + ", isPrlLoaded = " + isPrlLoaded
                             + ". namMatch = " + namMatch + " , mIsInPrl = " + mIsInPrl
                             + ", mRoamingIndicator = " + mRoamingIndicator
                             + ", mDefaultRoamingIndicator= " + mDefaultRoamingIndicator);
                 }
             }
             pollStateDone();
         }
     
     }
     ```

5. ServiceStateTracker的handlePollStateResultMessage()中，将最新的plmn的long、short、numeric更新到ServiceState mNewSS中。

   - ```java
      //frameworks/opt/telephony/src/java/com/android/internal/telephony/ServiceStateTracker.java
      protected void handlePollStateResultMessage(int what, AsyncResult ar) {
          int ints[];
          switch (what) {
              case EVENT_POLL_STATE_OPERATOR: {
              if (mPhone.isPhoneTypeGsm()) {
                  String opNames[] = (String[]) ar.result;
      
                  if (opNames != null && opNames.length >= 3) {
                      mNewSS.setOperatorAlphaLongRaw(opNames[0]);
                      mNewSS.setOperatorAlphaShortRaw(opNames[1]);
                      // FIXME: Giving brandOverride higher precedence, is this desired?
                      String brandOverride = getOperatorBrandOverride();
                      mCdnr.updateEfForBrandOverride(brandOverride);
                      if (brandOverride != null) {
                          log("EVENT_POLL_STATE_OPERATOR: use brandOverride=" + brandOverride);
                          mNewSS.setOperatorName(brandOverride, brandOverride, opNames[2]);
                      } else {
                          mNewSS.setOperatorName(opNames[0], opNames[1], opNames[2]);
                      }
                  }
              } else {
                  String opNames[] = (String[])ar.result;
      
                  if (opNames != null && opNames.length >= 3) {
                      // TODO: Do we care about overriding in this case.
                      // If the NUMERIC field isn't valid use PROPERTY_CDMA_HOME_OPERATOR_NUMERIC
                      if ((opNames[2] == null) || (opNames[2].length() < 5)
                              || ("00000".equals(opNames[2]))) {
                          opNames[2] = SystemProperties.get(
                                  GsmCdmaPhone.PROPERTY_CDMA_HOME_OPERATOR_NUMERIC, "00000");
                          if (DBG) {
                              log("RIL_REQUEST_OPERATOR.response[2], the numeric, " +
                                      " is bad. Using SystemProperties '" +
                                      GsmCdmaPhone.PROPERTY_CDMA_HOME_OPERATOR_NUMERIC +
                                      "'= " + opNames[2]);
                          }
                      }
      
                      if (!mIsSubscriptionFromRuim) {
                          // NV device (as opposed to CSIM)
                          mNewSS.setOperatorName(opNames[0], opNames[1], opNames[2]);
                      } else {
                          String brandOverride = getOperatorBrandOverride();
                          mCdnr.updateEfForBrandOverride(brandOverride);
                          if (brandOverride != null) {
                              mNewSS.setOperatorName(brandOverride, brandOverride, opNames[2]);
                          } else {
                              mNewSS.setOperatorName(opNames[0], opNames[1], opNames[2]);
                          }
                      }
                  } else {
                      if (DBG) log("EVENT_POLL_STATE_OPERATOR_CDMA: error parsing opNames");
                  }
              }
              break;
          }      
      }
      ```

6. ServiceState的setOperatorName()

   - ```java
     public void setOperatorName(String longName, String shortName, String numeric) {
         mOperatorAlphaLong = longName;
         mOperatorAlphaShort = shortName;
         mOperatorNumeric = numeric;
     }
     ```

7. ServiceState提供了三个对应的getXXX()方法获取PLMN

   - ```java
     //frameworks/base/telephony/java/android/telephony/ServiceState.java
     
     //Get current registered operator name in long alphanumeric format.
     //In GSM/UMTS, long format can be up to 16 characters long.
     //In CDMA, returns the ERI text, if set. Otherwise, returns the ONS.
     public String getOperatorAlphaLong() {
         return mOperatorAlphaLong;
     }
     
     /**
      * Get current registered operator name in short alphanumeric format.
      *
      * In GSM/UMTS, short format can be up to 8 characters long.
      * @return short name of operator, null if unregistered or unknown
      */
     @RequiresPermission(anyOf = {
             android.Manifest.permission.ACCESS_FINE_LOCATION,
             android.Manifest.permission.ACCESS_COARSE_LOCATION
     })
     public String getOperatorAlphaShort() {
         return mOperatorAlphaShort;
     }
     
     //Get current registered operator numeric id.
     //In GSM/UMTS, numeric format is 3 digit country code plus 2 or 3 digit
     //network code.
     public String getOperatorNumeric() {
         return mOperatorNumeric;
     }
     ```

## SPN流程

- 数据的apn使用的是default 的

### 从SIM卡读取

### 从配置文件读取

# MMS

- 简介：彩信是WAP协议的主要应用之一，MMS承载于WAP之上，是以WAP无线应用协议为载体来传输消息，MMS终端使用传输协议WSP/HTTP与WAP 网关进行交互.

  - MMS：Mutimedia Message Service

  - WAP：Wireless Application Protocol

- Content Type：SMIL：Synchronized Multimedia Integration Language

- 作用：文字、图片、视频、音频

- 流程：MT->WAP网关(无线网络)->SMSC（Internet）->MT
  - 发送：MMS client与WAP 网关建立连接，通过WAP 将信息发送至MMSC，MMSC暂时保存彩信，并会给发送方一个确认消息。
  - 接收：MMSC接收到消息后，通过PUSH协议给接收方发送一条WAP PUSH 消息，这个消息通常是一条特殊短信，里面包含彩信的位置URL。接收方收到短信通知后，从中取出URL，然后通过标准的HTTP GET请求从MMS Proxy-Relay上获取彩信。

- ![[image-20220805141848052.png]]

- PDU结构

  - 由MMS Header和MMS Body组成，MMS头由多个域名和域值组成，由客户端指定。
    - 一个MMS PDU对应一种消息格式。不同类型的MMS PDU有不同的MMS Header ；
    - 大多数MMS PDU只含有MMS头，它们起到建立和维持通信的作用，只有在M-Send.req和M-Retrieve.conf PDU中才有消息体。

- |       域名        | 说明                                     | 编码 |
  | :---------------: | ---------------------------------------- | ---- |
  |        Bcc        | 密送者地址                               | 0x01 |
  |        Cc         | 抄送者地址                               | 0x02 |
  | Content-Location  | 短息通知时用于存放MM所在服务器的Uri地 址 | 0x03 |
  |   Content-Type    | 内容类型                                 | 0x04 |
  |       Date        | 日期                                     | 0x05 |
  |  Delivery-Report  | 状态报告（默认为不需要)                  | 0x06 |
  |   Delivery-Time   | 报告时间                                 | 0x07 |
  |      Expiry       | 有效期                                   | 0x08 |
  |       From        | 发送者信息                               | 0x09 |
  |   Message-Class   | 信息的类型(个人·广告·信息还是自动)       | 0x0A |
  |    Message-ID     | 信息ID，识别不同的彩信                   | 0x0B |
  |   Message-Type    | PDU类型                                  | 0x0c |
  |    MMs-Version    | 使用MMS协议的版本                        | 0x0D |
  |   Message-Size    | MM的大小                                 | 0x0E |
  |     Priority      | 优先级                                   | 0x0F |
  |    Read-Reply     | 是否需要阅读报告                         | 0x10 |
  |  Report-Allowed   | 是否允许报告                             | 0x11 |
  |  Response-Status  | 回复状态                                 | 0x12 |
  |   Response-Text   | 回复文本                                 | 0x13 |
  | Sender-Visibility | 发送者是否可见(即是否匿名发送)           | 0x14 |
  |      status       | 信息状态（是否立即接收，拒绝还是不支 持) | 0x15 |
  |      Subject      | 主题                                     | 0x16 |
  |        To         | 接收者地址                               | 0x17 |
  |  Transaction-ld   | 传输ID(用于网络控制，识别不同的传输)     | 0x18 |

## 架构

- ![[MMSCNA.png]]
  - The MMS Architecture is the set of standards used by the Multimedia Messaging Service in mobile networks. The standards are prepared by 3GPP.The standard consists of a number of interfaces between components found in the mobile network
    1. MM1: the interface between MMS User Agent and MMS Center (MMSC, the combination of the MMS Relay & Server). Delivered as HTTP over a packet switched data session.
       - MM1 is used in the following actions:
         1. The sender subscriber sends an MMS to the MMSC
         2. The MMSC notifies the recipient subscriber that they have an MMS waiting for retrieval
         3. The recipient subscriber retrieves the MMS from the MMSC
         4. The MMSC notifies the sender that the recipient has retrieved the message
         5. The recipient subscriber manages their mailbox in the MMSC (uploads MMS, deletes MMS, ...)
    2. MM2: the interface between MMS Relay and MMS Server.
    3. MM3: the interface between MMSC and other messaging systems. Using SMTP.
    4. MM4: the interface between MMSC and foreign network providers. Using SMTP.
    5. MM5: the interface between MMSC and HLR.
    6. MM6: the interface between MMSC and user databases.
    7. MM7: the interface between MMS Value-added service applications and MMSC. Typically Content Providers using HTTP / SOAP for delivery.
    8. MM8: the interface between MMSC and the billing systems.
    9. MM9: the interface between MMSC and an online charging system.
    10. MM10: the interface between MMSC and a message service control function.
    11. MM11: the interface between MMSC and an external transcoder.

​	

# Setting

## google message

- **CarrierConfig**：配置文件位于packages/apps/CarrierConfig/assets/xxx.xml
  - **key**对应着CarrierConfigManager.java的配置。
  - **carrier_list.textpb**：配置文件的名字来源，文件名和carrier_list中一一对应。
- CarrierConfig/res/xml/vendor.xml
- TelephonyManager.java

# SIM

- 查看SIM卡的信号强度：
  1. AP侧关键字：onSignalStrength ，Rsrp: >-105（<-110认为无信号或极差，更低则无意义）
  2. Modem侧：match items->b193或refilter items->log packets-LTE-ML1-[0xB193]LTE ML1 Serving Cell Meas Response
     Inst RSRP Rx[0] = -125.75 dBm
     Inst RSRP Rx[1] = -118.06 dBm
     Inst Measured RSRP = -118.06 dBm (可参考这个值，取上面两根天线中信号较好的)                                                                                                     
  3. RSRP（Reference Singal Receiving Power）：参考信号接收功率，用于衡量某扇区的参考信号的强度，在一定频域和时域上进行测量并滤波。可以用来估计UE离扇区的大概路损，LTE系统中测量的关键对象。在小区选择中起决定作用

# JIRA

## textile 标记语言

- 转义字符：`\`
  - `\*#\*#`->`*#*#`

## Workflow

### 配置类的问题

1. 先确定项目的carriers，再确定jira的运营商。

   - 例如：V790AE是tracfone的项目，jira的需求是TMO的。修改对应的配置就是tracfone-TMO的。为了防止测试使用TMO的实体卡，将TMO-US的也一并修改了。

2. 下载对应项目的代码

   - 编译选项为userdebug，相关命令在tinno wiki上查找
     - 刷机根据对应的平台以及对应的手机型号有区别，不清楚需提前询问。

   - 单编/单
     1. source build/envsetup.sh
     2. lunch （对应版本的选择在项目立项之初就已经确定，不清楚需提前询问）
     3. 编译命令
        - mmm：编译指定目录下的模块，不编译它所依赖的其它模块。
        - mma：编译当前目录下的模块及其依赖项。
        - mmma：编译指定路径下所有模块，并且包含依赖。
     4. adb install `apk/jar的路径`
     5. MTK
     
        - MTK Android S 单编译相关文档见如下链接：
           https://online.mediatek.com/QuickStart/QS00178#QSS01819
     
           例如: 编译system下面的某个模块，Command 如下：
           source build/envsetup.sh && export OUT_DIR=out_sys && lunch sys_mssi_64_ww-userdebug && mmma XXXX
           注意lunch项目的选择见目录device/mediatek/system/项目/ 下对应的项目文件名+variant，如下图所示：
     
           - ![[C:\Users\haifei.liao\AppData\Roaming\Typora\typora-user-images\image-20230208145712051.png]]
     6. Qualcomm
     
        - source build/envsetup.sh && export OUT_DIR=out_sys && lunch qssi-userdebug && mmma XXXX

3. 修改对应的CarrierConfig文件

   - **CarrierConfig**：配置文件位于packages/apps/CarrierConfig/assets/xxx.xml
     - **key**对应着**CarrierConfigManager**.java的配置。如果找不到对应的修改项，只能找google提交case。
       - 如果谷歌给了解决方法就按照google的方法来
     - **carrier_list.textpb**：配置文件的名字来源，文件名和carrier_list中一一对应。TelephonyManager.java的getSimCardOperatorName() 获取operator name。

4. 编译刷机

5. 验证

6. 提交代码到gerrit

   1. git add -A
   2. git commit -m “<类型><jira号><tag，name>”
   3. git push tinno HEAD:refs/for/`branch名字`

   - git 其他命令
     - git status
     - git diff .
     - git remote -v
     - git checkout .

# Log关键字

## 概述

- log分类
  - modem log
  - AP(mobile) log
    - radio log(framework)
    - main log (app)
  - Network log
- 流程
  1. modem log的发送流程，有没有发出去，如果没发出去，在哪里失败了。
     1. 如果根本就没到modem，查看ap log，确定是在app层还是framework层失败
  2. modem log的接收流程，有没有接收到，如果接收途中失败了，在哪里失败了。
     1. 如果modem接收到了，但app没有显示，查看ap log，确定是在app层还是framework层失败
- mainline 查看 ：home/project2/liaohaifei/share/U5690/LA.QSSI.12.0.r1/LINUX/android/vendor/partner_modules/build（使用gitk查看记录）
- gms的messages查看：/home/project2/liaohaifei/share/P800AE/LA.QSSI.12.0.r1/LINUX/android/vendor/gms/apps/Messages

## log抓取操作步骤

MTK

- `*#*#873733284#*#*`:进入engineering mode
  1. Log and Debugging->DebugLoggerUI

Qualcomm

- `*#*#5644464#*#*`进入logcollector

  - ticket id填入任意的8位都可以
    - 选择想要抓取的log

- MMS协议需要查看tcp dump log，请按以下复测建议抓取tcp dump log

  *#*#5644464#*#*进入logcollector->ticket id填入任意的8位，点击submit->开启tcpdump开关->滑到底，点击start抓取log

## common

- fail、failure、unsupported、error、Androidruntime

## WEA

- GsmCellBroadcastHandler
- AT+ECSCBCFG
- IccSmsInterfaceManager
- CellBroadcastReceiver

## SMS

- smsdispatch
- smssender/SendMessageAction
- ims_sms
- dispatchwapPdu()
- gid
- SEND_SMS
- NEW_SMS
- InboundSmsHandler/MtkGsmInboundSmsHandler/GsmInboundSmsHandler
- ImsSmsDispatcher
- ImsService
- RILC
- RILJ
- RCS_TAG
- MessagingApp
- BlockedNumbers
- ReceiveMmsMessageAction/ReceiveSmsMessageAction
- SmsService
- Done sending SMS message/Done sending MMS message
- SmsMessage

## APN

- apnsetting
- setup_data、setupdatacall、setup_data_call、DEACTIVATE_DATA_CALL
- data_registration
- getGroupIdLevel1_gid
- RmmDcEvent
- ifconfig
- Poll ServiceState
- REGISTRATION_STATE
- vendor.xml:http://192.168.160.28:8000/source/xref/MT6761_U_NA_DEV/androidu/device/mediatek/system/common/overlay/CarrierConfig/packages/apps/CarrierConfig/res/xml/vendor.xml

## VM&VVM

- androidt/vendor/mediatek/proprietary/packages/services/Telephony/src/com/android/phone/NotificationMgr.java
- updatemwi
- IMCSMS
- +EIMSCMT
- getVoiceMailNumber
- onVoicemailStatusFetched
- DialerVoicemailStatusQuery
- vmail count
- setVoiceMessageCount
- VvmSmsFilter
- VoicemailSettingsActivity

## MMS

- WAP_PUSH_DELIVER
- MmsService
- MmsSender
- RCS_TAG
- MmsManager
- Done sending SMS message/Done sending MMS message

## RCS

- bugle/BugleDataModel
- carrierservices
- capabilityCacheExpiration
- onConfigurationStatusChanged
- [ACS]
- dns contains config(wireshark)
- Single Registration
- BugleRcsEngine
- BugleRcsProvisioning
- RCS_TAG
- DUAL_REG
- SIPTX-IO
- [PRESENCE]
- AcsEventCallback:  errorCode[403],errorString[Forbidden]

## LTE

- UNSOL_RESPONSE_NETWORK_STATE_CHANGED
- ImsRegister
- REGISTRATION_STATE

## Call

- advancedCallEnabled、Volte

# 刷机

## Command

- Platform
  - mtk
    - flash
      - download agent
      - scatter 
    - Modem meta backup
  - Qualcomm
    - QFIL 
    - qstn
- *89#：跳过刷机后的引导
- 1. MTK

     - `*##873733284#*#*`:进入engineering mode
       1. Log and Debugging->DebugLoggerUI

     Qualcomm

     - `*#*#5644464#*#*`进入logcollector

       - ticket id填入任意的8位都可以
         - 选择想要抓取的log

     - MMS协议需要查看tcp dump log，请按以下复测建议抓取tcp dump log

       *#*#5644464#*#*进入logcollector->ticket id填入任意的8位，点击submit->开启tcpdump开关->滑到底，点击start抓取log
- `*#*#8#*#*`：进入硬件调试模式

  - ITEM TEST->Receiver : 测试扬声器

- `*#*#86436#*#*`:查看version information

## remount

1. enter Settings->System->About phone
2. Press "Build number" several times to enable Developer options

3. Enter Settings->System->Developer options and turn on OEM unlocking

   - OEM 解锁 是存储在系统的设置数据库中的，因此理论上，可以通过 ADB 命令来修改 Settings 数据库，从而启用该选项。

     1. ```
        //启用开发者选项
        adb shell settings put global development_settings_enabled 1
        
        //启用 OEM 解锁选项
        adb shell settings put global oem_unlock_allowed 1
        
        //确认设置是否成功
        adb shell settings get global oem_unlock_allowed
        ```

        

        

4. adb shell settings put global oem_unlock_allowed 1(直接使用命令将oem unlock打开)

5. adb reboot bootloader

6. wait device reboot to bootloader ,then run the cmd "sudo $(which fastboot) flashing unlock" or “fastboot flashing unlock” —— 有时候这个步骤需要执行两次才可以生效

7. Press volume up key to continue and then run "fastboot reboot" —— 有时候这个步骤需要执行volume down key才能生效

8. Wait for the handset to boot up and run cmds below:

     - ```
       adb root 
       adb disable-verity
       ```

8. run cmds below to reboot the device:

     - ```
       adb reboot
       ```

9. Wait for the handset to boot up and run cmds below:
   -  ```
      adb root
      adb remount
      ```
   
      

## SN Writer（Obsolete）

- imei：353587400014311 
- SN：PRVGNZHQ8H8PHM6X
- Barcode：MTKO00220830151117000037

1. SN_Setup.ini的Write Serial No. = True
2. 打开SN_Writer.exe，选择System Config
3. Write Option子菜单选择imei
4. DataBase File子菜单，MD1_DB和AP_DB，都来自刷机包flash的db目录
5. Save
6. 主菜单start
7. 手机关机，连接手机，写入imei和sn号。

## TestSystemPlatform

- password：234

1. 选择系统->系统模式->Single PC Mode，输入password确认。
2. 根据手机的平台，选择MTK/高通平台工具集->包装-写号操作，输入password确认。
3. 选择参数配置->关键参数配置，输入密码确认，选择需要刷入的参数写入。
4. 右侧输入框输入值，多个值按顺序用逗号隔开。
5. 点击start，插入手机，等待写入结束。

## APN文件 push到 手机

1. adb root;adb remount
2. adb push apns-conf.xml system/etc/
3. Settings--> Network&internet-->Mobile network-->Access Point Names--> Reset to default

# ADB指令

- adb shell dumpsys activity activities：查看activity栈
- adb shell dumpsys activity broadcasts：查看广播堆栈
- adb shell dumpsys package com.android.messaging：查看应用的信息
- adb shell pm path com.android.messaging：查看包的位置
- adb logcat -b all |Select-String androidruntime：查看开机 androidruntime log(windows,linux请手动将Select-String替换为grep等命令)
- PCAP log 抓取:adb shell tcpdump -i any -vv -s 0 -w /cache/tcpdump.pcap
  - 导出：adb pull /cache/tcpdump.pcap .
- adb pull /data/user_de/0/com.android.phone/files/ ./ ：导出carrierconfig
- adb shell setprop log.tag.MessagingApp V ：设置fontdo的messaging应用的debug log
- adb shell am start com.google.android.apps.messaging/.startchat.StartChatActivity:打开某个Activity
- adb shell screenrecord /sdcard/test.mp4：抓取MP4
- adb shell bugreportz：抓取bugreport
- adb logcat -v time -b all > log.txt：抓取整体log
- adb shell am startservice -n com.android.traffic/com.android.traffic.maniservice﻿﻿：启动service
- adb shell am broadcast -a android.intent.action.BOOT_COMPLETED﻿﻿：发送广播
- adb开启debuglogger

  1. Clear the old logs

     - ```
       adb shell am broadcast -a com.debug.loggerui.ADB_CMD --es cmd_name clear_all_logs -n com.debug.loggerui/.framework.LogReceiver
       ```

  2. Open the logs

     - ```
       adb shell am broadcast -a com.debug.loggerui.ADB_CMD --es cmd_name start --ei cmd_target 119 -n com.debug.loggerui/.framework.LogReceiver
       ```

  3. Restart device and run the case

  4. Close the logs

     - ```
       adb shell am broadcast -a com.debug.loggerui.ADB_CMD --es cmd_name stop --ei cmd_target 119 -n com.debug.loggerui/.framework.LogReceiver
       ```

  5. Pull out the log

     - ```
       adb pull data/debuglogger
       adb pull data/debuglogger/mobilelog
       ```
       
       
     
     


# iptables指令

- iptables -t nat -nvL 查看nat表

- 参数

  - | 选项                                                         | 说明                                                         |
    | ------------------------------------------------------------ | ------------------------------------------------------------ |
    | -t, --table                                                  | table 对指定的表 table 进行操作， table 必须是 raw， nat，filter，mangle 中的一个。如果不指定此选项，默认的是 filter 表。 |
    | -p                                                           | 指定要匹配的数据包协议类型                                   |
    | -s, --source [!] address[/mask]                              | 把指定的一个／一组地址作为源地址，按此规则进行过滤。当后面没有 mask 时，address 是一个地址，比如：192.168.1.1；当 mask 指定时，可以表示一组范围内的地址，比如：192.168.1.0/255.255.255.0   ,"!" 表示取反 |
    | -d, --destination [!] address[/mask]                         | 地址格式同上，但这里是指定地址为目的地址，按此进行过滤       |
    | -i, --in-interface [!] <网络接口name>                        | 指定数据包的来自来自网络接口，比如最常见的 eth0 。注意：它只对 INPUT，FORWARD，PREROUTING 这三个链起作用。如果没有指定此选项， 说明可以来自任何一个网络接口。同前面类似，"!" 表示取反 |
    | -o, --out-interface [!] <网络接口name>                       | 指定数据包出去的网络接口。只对 OUTPUT，FORWARD，POSTROUTING 三个链起作用 |
    | -L, --list [chain]                                           | 列出链 chain 上面的所有规则，如果没有指定链，列出表上所有链的所有规则 |
    | -A, --append chain rule-specification                        | 在指定链 chain 的末尾插入指定的规则，也就是说，这条规则会被放到最后，最后才会被执行。规则是由后面的匹配来指定 |
    | -I, --insert chain [rulenum] rule-specification              | 在链 chain 中的指定位置插入一条或多条规则。如果指定的规则号是1，则在链的头部插入。这也是默认的情况，如果没有指定规则号 |
    | -D, --delete chain rule-specification -D, --delete chain rulenum | 在指定的链 chain 中删除一个或多个指定规则                    |
    | -P, --policy chain target                                    | 为指定的链 chain 设置策略 target。注意，只有内置的链才允许有策略，用户自定义的是不允许的。 |
    | -F, --flush [chain]                                          | 清空指定链 chain 上面的所有规则。如果没有指定链，清空该表上所有链的所有规则。 |
    | -N, --new-chain chain                                        | 用指定的名字创建一个新的链。                                 |
    | -X, --delete-chain [chain]                                   | 删除指定的链，这个链必须没有被其它任何规则引用，而且这条上必须没有任何规则。如果没有指定链名，则会删除该表中所有非内置的链。 |
    | -E, --rename-chain old-chain new-chain                       | 用指定的新名字去重命名指定的链。这并不会对链内部造成任何影响。 |
    | -Z, --zero [chain]                                           | 把指定链，或者表中的所有链上的所有计数器清零。               |
    | -j, --jump target <指定目标>                                 | 即满足某条件时该执行什么样的动作。target 可以是内置的目标，比如 ACCEPT，也可以是用户自定义的链。 |
    |                                                              | ETC                                                          |
    
    



# ETC

- mifi密码查看
  - 目前WiFi AP 默认是打开的，如果大家要连接AP，可以这样查询密码 如这个密码是4pbtn47yxc5j4f6
    adb root
    adb shell cat /data/misc/apexdata/com.android.wifi/WifiConfigStoreSoftAp.xml
    - ![[企业微信截图_17356123437630.png]]
- ifconfig -s eth0 static 172.24.62.189 255.255.255.0 172.24.63.254
- 删除 androidt/out/target/product/B321MH/resign
  - ![[image-20250313214410814.png]]

- 服务器命令
  - 查看服务器的cpu占用:mpstat -P ALL 1
- 强行写入只读文件:`:w !sudo tee %`


# 版本控制

## repo

- Repo 是一个建立在 Git 之上的工具。Repo 帮助管理许多 Git 存储库，上传到修订控制系统，并使部分开发工作流程自动化。Repo 并不是要取代 Git，只是为了让使用 Git 更容易。repo 命令是一个可执行的 Python 脚本，您可以将其放在路径中的任何位置。

- 使用 Repo 需遵循的格式

  - ```java
    repo command options
    ```

  - 可选元素显示在方括号 [ ] 中。

- 帮助

  - 所有命令的摘要

    - ```
      repo help
      ```

  - 某个命令的详细信息

    - ```
      repo help command
      ```

  - 仅查看可用选项的列表

    - ```
      repo command --help
      ```

- repo init
  - `-u`：指定从中检索清单代码库的网址。常见清单位于 `https://android.googlesource.com/platform/manifest`。
  - `-m`：选择代码库中的清单文件。如果未选择清单名称，则默认为 `default.xml`。
  - `-b`：指定修订版本，即特定的 manifest-branch。

- repo sync

  - `-c`：只从服务端获取当前分支。
  - `-f`：即使某个项目同步失败，也继续同步其他项目。
  - `-j <num>`：设定并发数。默认 4 个并发。
  - 可以查看 .repo/manifest.xml 拉取一部分代码。

- repo forall -c "git clean -fd;git reset --hard" ：清除所有的改动。在repo sync出现问题的时候，请先使用这个命令进行清除，确保自己本地的环境被回退到没有改动的状态。

##git

# Case提交

## Qualcomm

1. 创建账号，进入网址：https://support-qualcomm.force.com/s/case/Case/Default
2. 创建case
   - ![[image-20230213160115479.png]]
3. 提交附件和添加邮件通知成员
   - ![[image-20230213160322318.png]]
4. 交流并解决问题。

# 系统配置

1. com.android.internal.R
   - 编译前：frameworks/base/core/res/res/目录
   - 编译后：out/target/common/R/com/android/internal/R.java
2. trunk
3. tinnofeature

# 查看软件版本

- mtk

  - debuglogger/mobilelog/APLog***/properties

- bugreport/bugreport***.txt

- 关键词：build.version

# 仪表log查看

- CMWmarsViewer
  - 文件类型：msglog、rsmsglog
  - 选择verdicts


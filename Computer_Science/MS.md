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
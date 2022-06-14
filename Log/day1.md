# 配置已完成：jdk，AndroidStudio，Git，邮箱

- jdk问题

  - 安装jdk1.8出现内部错误61003
    - 原因：电脑没有安装visual c++2015 redistributable
    - 解决办法：安装visual c++2015之后再次安装jdk安装正常。

- AndroidStudio问题

  - 安装AndroidSDK失败
    - 原因：需要访问google网络
    - 解决办法：
      1. 下载v2rayN代理工具和自身微服务器上的v2ray代理服务访问。
      2. 在打开v2rayN时，提示需要.net framework 4.8，
      3. 安装.net framework 4.8时，提示“安装未成功，已处理证书链,但是在不受信任提供程序信任的根证书中终止”，原因是计算机中没有相应的受信任证书，需要导入微软的证书`MicrosoftRootCertificateAuthority2011.cer`
      4. 导入证书之后，再次安装.net framework 4.8提示“时间戳签名和或证书无法验证或已损坏”，原因是系统版本太低，需要更新系统。
      5. 更新系统后成功安装.net framework 4.8,正常打开v2rayN，配置后，解决无法访问google的问题。

- git问题

  - git clone 远程仓库提示`Failed to connect to github.com port 443: Timed out`

    - 原因：代理没有配置对

    - 解决办法:

      - ```c
        //输入如下两端命令
        //server和端口根据自己的v2rayN配置修改
        git config --global http.proxy http://127.0.0.1:10809 
        git config --global http.proxy socks5://127.0.0.1:10808
        ```

# 疯狂Android讲义


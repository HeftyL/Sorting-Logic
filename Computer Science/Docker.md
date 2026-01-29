# Docker

## 容器生态系统

1. 容器核心技术
   1. 容器规范:容器不光是 Docker，还有其他容器，比如 CoreOS的rkt。为了保证容器生态的健康发展，保证不同容器之间能够兼容，包含 Docker、CoreOS、 Google在内的若干公司共同成立了一个叫 Open Container Initiative (OCI)的组织，其目的是制定开放的容器规范。
      - 目前OCI 发布了两个规范:runtime spec和 image format spec。有了这两个规范，不同组织和厂商开发的容器能够在不同的runtime 上运行。这样就保证了容器的可移植性和互操作性
      - ![image-20230728140932235](assets/image-20230728140932235.png)
   2. 容器run time:runtime是容器真正运行的地方。runtime需要跟操作系统 kernel 紧密协作，为容器提供运行环境。Java程序就好比是容器，JVM 则好比是 runtime，JVM为Java程序提供运行环境。同样的道理，容器只有在runtime中才能运行。
      1. lxc是Linux上老牌的容器runtime。Docker最初也是用lxc作为runtime。
      2. runc是 Docker自己开发的容器runtime，符合oci 规范，也是现在 Docker 的默认runtime。
      3. rkt是CoreOS开发的容器runtime，符合OCI 规范，因而能够运行Docker 的容器。
   3. 容器管理工具:光有runtime还不够，用户得有工具来管理容器。容器管理工具对内与runtime 交互，对外为用户提供interface，比如CLI。这就好比除了JVM，还得提供Java 命令让用户能够启停应用。
      1. lxd是 lxc对应的管理工具。
      2. runc的管理工具是docker engine。docker engine包含后台deamon和 cli两个部分。我们通常提到Docker，一般就是指的docker engine。
      3. rkt的管理工具是 rkt cli。
      4. ![image-20230728141316495](assets/image-20230728141316495.png)
   4. 容器定义工具:容器定义工具允许用户定义容器的内容和属性，这样容器就能够被保存、共享和重建
      1. docker image是 Docker容器的模板，runtime依据 docker image 创建容器。
      2. dockerfile是包含若干命令的文本文件，可以通过这些命令创建出 docker image。
      3. ACI (App Container Image)与 docker image类似，只不过它是由 CoreOS开发的rkt容器的image 格式。
   5. Registries:容器是通过image创建的，需要有一个仓库来统一存放image，这个仓库就叫做Registry。
      1. Docker Hub ( https://hub.docker.com)是 Docker 为公众提供的托管Registry，上面有很多现成的image，为Docker用户提供了极大的便利。
      2. Quay.io (https://quay.io/）是另一个公共托管Registry，提供与 Docker Hub类似的服务。
   6. 容器OS:容器OS是专门运行容器的操作系统。与常规OS 相比，容器OS通常体积更小，启动更快。因为是为容器定制的OS，通常它们运行容器的效率会更高。
2. 容器平台技术
   1. 容器编排引擎:基于容器的应用一般会采用微服务架构。在这种架构下，应用被划分为不同的组件，并以服务的形式运行在各自的容器中，通过API对外提供服务。为了保证应用的高可用，每个组件都可能会运行多个相同的容器。这些容器会组成集群，集群中的容器会根据业务需要被动态地创建、迁移和销毁。这样一个基于微服务架构的应用系统实际上是一个动态的可伸缩的系统。这对我们的部署环境提出了新的要求，我们需要有一种高效的方法来管理容器集群。而这，就是容器编排引擎要干的工作。所谓编排（orchestration），通常包括容器管理、调度、集群定义和服务发现等。通过容器编排引擎，容器被有机地组合成微服务应用,实现业务需求。
      1. docker swarm是Docker开发的容器编排引擎。
      2. kubernetes是 Google领导开发的开源容器编排引擎，同时支持 Docker和CoreOS容器。
      3. mesos是一个通用的集群资源调度平台，mesos与 marathon一起提供容器编排引擎功能。
   2. 容器管理平台:容器管理平台是架构在容器编排引擎之上的一个更为通用的平台。通常容器管理平台能够支持多种编排引擎，抽象了编排引擎的底层实现细节，为用户提供更方便的功能，比如application catalog和一键应用部署等。
   3. 基于容器的 PaaS:基于容器的 PaaS为微服务应用开发人员和公司提供了开发、部署和管理应用的平台，使用户不必关心底层基础设施而专注于应用的开发。
3. 容器支持技术
   1. 容器网络:容器的出现使网络拓扑变得更加动态和复杂。用户需要专门的解决方案来管理容器与容器、容器与其他实体之间的连通性和隔离性。
   2. 服务发现:动态变化是微服务应用的一大特点。当负载增加时，集群会自动创建新的容器;负载减小，多余的容器会被销毁。容器也会根据 host 的资源使用情况在不同host 中迁移，容器的IP和端口也会随之发生变化。在这种动态的环境下，必须要有一种机制让 client 能够知道如何访问容器提供的服务。这就是服务发现技术要完成的工作。服务发现会保存容器集群中所有微服务最新的信息，比如IP和端口，并对外提供 API,提供服务查询功能。
   3. 监控:监控对于基础架构非常重要，而容器的动态特征对监控提出更多挑战。
   4. 数据管理:容器经常会在不同的host之间迁移，如何保证持久化数据也能够动态迁移，是 Rex-Ray这类数据管理工具提供的能力
   5. 日志管理:日志为问题排查和事件管理提供了重要依据。
   6. 安全性

## 安装

地址：https://docs.docker.com/engine/install/centos/

### 安装步骤

- 确定你是CentOS7及以上版本 cat /etc/redhat-release
- 卸载旧版本
- yum安装gcc相关
  - CentOS7能上外网 	
  - yum -y install gcc
  - yum -y install gcc-c++
- 安装需要的软件包
  - 官网要求
  - yum install -y yum-utils
- 设置stable镜像仓库
  - 大坑
    - yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
    - 官网要求
  - 推荐
    - yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
- 更新yum软件包索引
  - yum makecache fast
- 安装DOCKER CE
  - yum -y install docker-ce docker-ce-cli containerd.io
- 启动docker
  - systemctl start docker
  - docker run -d -p 12345:12348 httpd

## docker核心概述

### 容器

- 容器是一种轻量级、可移植、自包含的软件打包技术，使应用程序可以在几乎任何地方以相同的方式运行。开发人员在自己笔记本上创建并测试好的容器，无须任何修改就能够在生产系统的虚拟机、物理服务器或公有云主机上运行。
- 容器与虚拟机
  - 容器由两部分组成:(1)应用程序本身;(2）依赖:比如应用程序需要的库或其他软件容器在Host 操作系统的用户空间中运行，与操作系统的其他进程隔离。这一点显著区别于的虚拟机。
  - 传统的虚拟化技术，比如 VMWare、KVM、Xen，目标是创建完整的虚拟机。为了运行应用，除了部署应用本身及其依赖（通常几十MB)，还得安装整个操作系统(几十GB)
- 为什么需要容器
  - 容器使软件具备了超强的可移植能力。
  - ![image-20230728160128275](assets/image-20230728160128275.png)
- docker架构
  - ![image-20230728160252919](assets/image-20230728160252919.png)
    1. Docker客户端
    2. Docker 服务器
    3. Docker 镜像：可将Docker镜像看成只读模板，通过它可以创建 Docker 容器。例如某个镜像可能包含一个Ubuntu操作系统、一个Apache HTTP Server以及用户开发的Web应用。
       - 镜像有多种生成方法:(1）从无到有开始创建镜像;(2）下载并使用别人创建好的现成的镜像;(3）在现有镜像上创建新的镜像。
       - 可以将镜像的内容和创建步骤描述在一个文本文件中，这个文件被称作 Dockerfile，通过执行`docker build<docker-file>`命令可以构建出 Docker镜像，后面我们会讨论。
    4. Docker 容器：Docker容器就是Docker镜像的运行实例。
       - 用户可以通过CLI (Docker）或是API启动、停止、移动或删除容器。可以这么认为，对于应用软件，镜像是软件生命周期的构建和打包阶段，而容器则是启动和运行阶段。
    5. Registry：Registry是存放 Docker镜像的仓库,Registry分私有和公有两种。

## 镜像

- 镜像是Docker容器的基石，容器是镜像的运行实例，有了镜像才能启动容器。

- base 镜像
  - 含义:(1)不依赖其他镜像，从 scratch构建;(2）其他镜像可以以之为基础进行扩展。所以，能称作 base镜像的通常都是各种 Linux 发行版的Docker镜像，比如Ubuntu、Debian、CentOS等。

- 镜像的分层结构：Docker支持通过扩展现有镜像，创建新的镜像。每安装一个软件，就在现有镜像的基础上增加一层。
- 可写的容器层（Copy-on-Write特性）：当容器启动时，一个新的可写层被加载到镜像的顶部。这一层通常被称作“容器层”,“容器层”之下的都叫“镜像层”。所有对容器的改动，无论添加、删除，还是修改文件都只会发生在容器层中。只有容器层是可写的,容器层下面的所有镜像层都是只读的。
  - ![image-20230728173724447](assets/image-20230728173724447.png)
  - 镜像层数量可能会很多，所有镜像层会联合在一起组成一个统一的文件系统。如果不同层中有一个相同路径的文件，比如 la，上层的/a会覆盖下层的la，也就是说用户只能访问到上层中的文件/a。在容器层中，用户看到的是一个叠加之后的文件系统。
    1. 添加文件。在容器中创建文件时,新文件被添加到容器层中。
    2. 读取文件。在容器中读取某个文件时，Docker会从上往下依次在各镜像层中查找此文件。一旦找到，打开并读入内存。
    3. 修改文件。在容器中修改已存在的文件时，Docker 会从上往下依次在各镜像层中查找此文件。一旦找到，立即将其复制到容器层，然后修改之。
    4. 删除文件。在容器中删除文件时，Docker也是从上往下依次在镜像层中查找此文件。找到后，会在容器层中记录下此删除操作。

### 构建镜像

#### 步骤

1. 从base镜像运行一个容器。
2. 执行一条指令，对容器做修改。
3. 执行类似docker commit的操作，生成一个新的镜像层。
4. Docker再基于刚刚提交的镜像运行一个新容器。
5. 重复2~4步，直到Dockerfile中的所有指令执行完毕。

#### 方法

1. docker commit
   1. 运行容器。
      - `docker run -it ubuntu`
        - -it参数的作用是以交互模式进入容器，并打开终端
   2. 修改容器。
   3. 将容器保存为新的镜像。
      - docker commit silly goldberg ubuntu-with-vi
        - silly _goldberg是 Docker为我们的容器随机分配的名字。
        - 新镜像命名为ubuntu-with-vi。
2. Dockerfile
   - ![image-20230729150646961](assets/image-20230729150646961.png)
     1. 当前目录为/root。
     2. Dockerfile 准备就绪。
     3. 运行 `docker build`命令，`-t`将新镜像命名为ubuntu-with-vi-dockerfile，命令末尾的`﹒`指表明build context为当前目录。Docker 默认会从 build context 中查找 Dockerfile 文件，我们也可以通过-f参数指定Dockerfile的位置。
     4. 从这步开始就是镜像真正的构建过程。首先 Docker 将 build context 中的所有文件发送给 Docker daemon。build context为镜像构建提供所需要的文件或目录。Dockerfile 中的ADD、COPY 等命令可以将build context 中的文件添加到镜像。此例中,build context为当前目录/root，该目录下的所有文件和子目录都会被发送给 Docker daemono。使用build context就得小心了，不要将多余文件放到 build context，特别不要把`/`、`/usr`作为build context，否则构建过程会相当缓慢甚至失败。
     5. Step 1:执行FROM,将 Ubuntu作为base镜像。Ubuntu镜像ID为f753707788c5。
     6. Step 2:执行RUN,安装 vim，具体步骤为⑦⑧⑨。
     7. 启动ID为 9f4d4166f7e3的临时容器，在容器中通过apt-get 安装 vimo安装成功后,将容器保存为镜像，其ID为35ca89798937。这一步底层使用的是类似 docker commit的命令。
     8. 删除临时容器9f4d4166f7e3。
     9. 镜像构建成功。

#### 查看镜像分层结构

- `docker history`会显示镜像的构建历史,也就是 Dockerfile的执行过程。

#### 镜像的缓存特性

- Docker会缓存已有镜像的镜像层，构建新镜像时，如果某镜像层已经存在，就直接使用,无须重新创建。
- 如果我们希望在构建镜像时不使用缓存,可以在 docker build命令中加上 `--no-cache`参数。Dockerfile中每一个指令都会创建一个镜像层，上层是依赖于下层的。无论什么时候，只要某一层发生变化,其上面所有层的缓存都会失效。也就是说,如果我们改变 Dockerfile指令的执行顺序,或者修改或添加指令，都会使缓存失效。

#### Dockerfile常用指令

- FROM：指定base镜像。
- MAINTAINER：设置镜像的作者，可以是任意字符串。
- COPY：将文件从build context复制到镜像。
  - 支持两种形式：COPY src dest与COPY ["src", "dest"]。
  - 注意: src只能指定build context中的文件或目录。
- ADD：与COPY类似，从build context复制文件到镜像。不同的是，如果src是归档文件(tar、zip、tgz、xz等），文件会被自动解压到dest。
- ENV：设置环境变量，环境变量可被后面的指令使用。
- EXPOSE：指定容器中的进程会监听某个端口，Docker可以将该端口暴露出来。
- VOLUME：将文件或目录声明为volume。
- WORKDIR：为后面的RUN、CMD、ENTRYPOINT、ADD或COPY指令设置镜像中的当前工作目录。
- RUN：在容器中运行指定的命令。
- CMD：容器启动时运行指定的命令。Dockerfile 中可以有多个CMD 指令，但只有最后一个生效。CMD可以被 docker run之后的参数替换。
- ENTRYPOINT:设置容器启动时运行的命令。Dockerfile中可以有多个 ENTRYPOINT 指令,但只有最后一个生效。CMD或docker run之后的参数会被当作参数传递给ENTRYPOINT。
- Dockerfile支持以“#”开头的注释。

### RUN vs CMD vs ENTRYPOINT

- RUN:执行命令并创建新的镜像层，RUN经常用于安装软件包。
- CMD:设置容器启动后默认执行的命令及其参数，但 CMD 能够被docker run后面跟的命令行参数替换。
- ENTRYPOINT:配置容器启动时运行的命令。

#### Shell 和 ExeC格式

- 可用两种方式指定 RUN、CMD和 ENTRYPONT 要运行的命令:Shell 格式和 Exec格式，二者在使用上有细微的区别。

  1. Shell格式;

     - ```shell
       <instruction> <command>
       ```

  2. Exec格式

     - ```
       <instruction> ["executable", "param1", "param2",]
       ```

- CMD和 ENTRYPOINT 推荐使用Exec格式，因为指令可读性更强，更容易理解。RUN则两种格式都可以。

#### RUN

#### CMD

- CMD 指令允许用户指定容器的默认执行的命令。此命令会在容器启动且 docker run没有指定其他命令时运行。
  - 如果docker run 指定了其他命令，CMD指定的默认命令将被忽略。
  - 如果 Dockerfile 中有多个CMD指令，只有最后一个 CMD有效。

#### ENTRYPOINT

- ENTRYPOINT指令可让容器以应用程序或者服务的形式运行。ENTRYPOINT看上去与CMD很像，它们都可以指定要执行的命令及其参数。不同的地方在于 ENTRYPOINT 不会被忽略，一定会被执行，即使运行docker run时指定了其他命令。

- ENTRYPOINT的 Exec格式用于设置要执行的命令及其参数，同时可通过CMD 提供额外的参数。ENTRYPOINT 中的参数始终会被使用，而 CMD 的额外参数可以在容器启动时动态替换掉。

  - ```
    ENTRYPOINT ["/bin/echo", "Hello"] CMD [ "world"]
    ```

- ENTRYPOINT 的 Shell格式会忽略任何CMD 或 docker run提供的参数。

### 分发镜像

- 使用方式
  - 用相同的Dockerfile在其他host构建镜像。
  - 将镜像上传到公共 Registry（比如 Docker Hub）,Host直接下载使用。
  - 搭建私有的 Registry供本地Host使用。

#### 为镜像命名

- ```
  [image name]= [repository]:[tag]
  docker build -t ubuntu-with-vi:2.3
  ```

  - 一个特定镜像的名字由两部分组成:repository和 tag。
  - 如果执行 docker build时没有指定 tag，会使用默认值 latest。

- tag命名默认规范

  1. myimage:1始终指向1这个分支中最新的镜像。
  2. myimage:1.9始终指向1.9.x中最新的镜像。
  3. myimage:latest始终指向所有版本中最新的镜像。
  4. 如果想使用特定版本，可以选择 myimage:1.9.1、myimage:1.9.2或myimage:2.0.0。

## 容器

### 运行容器

- docker ps -a或 docker container ls -a:显示所有状态的容器
- 容器长期运行:`docker run ubuntu /bin/bash -c "while true ;do sleep 1; done"`
- 加上参数-d以后台方式启动容器
- 设置docker开机启动:`sudo systemctl enable docker`
- 设置容器随Docker启动而自启:`docker update --restart=always xxx`(xxx为容器名或容器id）

### 进入容器

- docker attach:通过docker attach可以attach 到容器启动命令的终端
  - 可通过Ctrl+p，然后 Ctrl+q组合键退出attach 终端。
- docker exec:`docker exec -it <container> bash |sh`
  - -it以交互模式打开 pseudo-TTY，执行bash，其结果就是打开了一个 bash终端。
- attach vs exec
  - attach：直接进入容器启动命令的终端，不会启动新的进程。
  - exec：在容器中打开新的终端，并且可以启动新的进程。
  - 如果想直接在终端中查看启动命令的输出，使用attach；其他情况下使用exec。
    - 如果只是为了查看启动命令的输出,可以使用 docker logs命令,加-f参数的作用与 tail -f类似，能够持续打印输出。

### stop/start/restart 容器

- 容器在 docker host中实际上是一个进程，docker stop命令本质上是向该进程发送一个SIGTERM信号。如果想快速停止容器,可使用 docker kill 命令，其作用是向容器进程发送SIGKILL信号
- docker start会保留容器的第一次启动时的所有参数。
- docker restart可以重启容器，其作用就是依次执行docker stop和 docker start。
- 容器可能会因某种错误而停止运行。对于服务类容器，我们通常希望在这种情况下容器能够自动重启。启动容器时设置--restart 就可以达到这个效果，`--restart=always`意味着无论容器因何种原因退出（包括正常退出)，都立即重启;该参数的形式还可以是-restart=on-failure:3，意思是如果启动进程退出代码非0，则重启容器，最多重启3次。

### pause / unpause 容器

### 删除容器

- docker rm
- docker rm -v $(docker ps -ag -f status-exited):批量删除已退出的容器

### 生命周期

- ![img](assets/event_state.png)

## 网络

### none网络

- 顾名思义，none网络就是什么都没有的网络。挂在这个网络下的容器除了lo，没有其他任何网卡。容器创建时，可以通过--network=none指定使用none网络
- 封闭意味着隔离，一些对安全性要求高并且不需要联网的应用可以使用none网络。

### host网络

- 连接到 host网络的容器共享 Docker host 的网络栈，容器的网络配置与host完全一样。可以通过--network=host指定使用 host 网络
- 直接使用 Docker host 的网络最大的好处就是性能，如果容器对网络传输效率有较高要求，则可以选择 host网络。当然不便之处就是牺牲一些灵活性，比如要考虑端口冲突问题,Docker host 上已经使用的端口就不能再用了。

### bridge 网络

- Docker 安装时会创建一个命名为docker0的 Linux bridge。如果不指定--network，创建的容器默认都会挂到docker0上

### user-defined网络

- 除了none、host、bridge这三个自动创建的网络，用户也可以根据业务需要创建_user-defined网络。
- Docker提供三种user-defined 网络驱动:bridge、overlay和macvlan。overlay和 macvlan用于创建跨主机的网络.
  - `docker network create --driver bridge mynet`:可通过 bridge驱动创建类似前面默认的 bridge网络
    - 指定--subnet和 --gateway参数,可以指定ip网段
  - `docker network connect 网卡名 容器id`:可以将两个容器连接在一起

### 容器间通信

- IP通信:两个容器要能通信,必须要有属于同一个网络的网卡。满足这个条件后，容器就可以通过IP交互了。具体做法是在容器创建时通过--network 指定相应的网络，或者通过 docker network connect 将现有容器加入到指定网络。

#### Docker DNS Server

- 通过IP访问容器虽然满足了通信的需求,但还是不够灵活。因为在部署应用之前可能无法确定IP，部署之后再指定要访问的IP会比较麻烦。对于这个问题，可以通过docker自带的 DNS 服务解决。
- 从Docker 1.10版本开始，docker daemon实现了一个内嵌的 DNS server，使容器可以直接通过“容器名”通信。方法很简单，只要在启动时用--name为容器命名就可以了。
  - `docker run -it --network=my net2 --name=pbox1 busybox docker run -it--network-my net2 --name=bbox2 busybox`
- 使用 docker DNS有个限制:只能在 user-defined网络中使用。也就是说，默认的 bridge网络是无法使用 DNS的。

#### joined 容器

- joined容器是另一种实现容器间通信的方式。
- joined容器非常特别，它可以使两个或多个容器共享一个网络栈，共享网卡和配置信息,joined 容器之间可以通过127.0.0.1直接通信。
  1. 先创建一个httpd容器，名字为 web1。:`docker run -d -it --name-web1 httpd`
  2. 然后创建busybox容器并通过--network=container:web1指定 joined 容器为 web1:`docker run-it -network-container: web1 busybox`
     1.  busybox和 webl的网卡 mac地址与IP完全一样,它们共享了相同的网络栈。busybox可以直接用127.0.0.1访问 web1的 http服务
- joined容器非常适合以下场景:
  - 不同容器中的程序希望通过loopback高效快速地通信，比如 Web Server 与 AppServero
  - 希望监控其他容器的网络流量,比如运行在独立容器中的网络监控程序。

### 将容器与外部世界连接

#### 容器访问外部世界

#### 外部世界访问容器

## 容器

### storage driver

- 容器由最上面一个可写的容器层，以及若干只读的镜像层组成，容器的数据就存放在这些层中。这样的分层结构最大的特性是Copy-on-Write:
  - 新数据会直接存放在最上面的容器层。
  - 修改现有数据会先从镜像层将数据复制到容器层，修改后的数据直接保存在容器层中，镜像层保持不变。
  - 如果多个层中有命名相同的文件，用户只能看到最上面那层中的文件。
    - 分层结构使镜像和容器的创建、共享以及分发变得非常高效，而这些都要归功于Dockerstorage driver。正是 storage driver 实现了多层数据的堆叠并为用户提供一个单一的合并之后的统一视图。
    - Docker支持多种storage driver，有AUFS、Device Mapper、Btrfs、OverlayFS、VFS 和ZFS。它们都能实现分层的架构，同时又有各自的特性。
      - 优先使用 Linux发行版默认的 storage driver。
- 使用场景：对于某些容器，直接将数据放在由 storage driver 维护的层中是很好的选择，比如那些无状态的应用。无状态意味着容器没有需要持久化的数据，随时可以从镜像直接创建

### Data Volume

- Data Volume本质上是 Docker Host 文件系统中的目录或文件，能够直接被mount 到容器的文件系统中。

- 在选择数据层（镜像层、容器层）和volume来存放数据时，可以考虑以下场景：

  1. Database软件 vs Database数据：

     - 对于Database软件，通常会将其作为镜像层的一部分进行安装和配置。这样可以确保每次创建新容器时都有相同的数据库软件环境。

     - 而对于Database数据，建议将其存储在volume中，这样即使容器被销毁，数据仍然可以被保留。可以将volume挂载到容器的特定路径，使得数据库软件可以读写该路径下的数据。
  2.  Web应用 vs 应用产生的日志：

     - 对于Web应用，可以将应用代码和静态文件等放在镜像层中。这样可以确保每次创建新容器时都有相同的应用代码。

     - 而对于应用产生的日志，建议将其存储在volume中，这样可以方便地查看和管理日志文件。可以将volume挂载到容器的特定路径，使得应用可以将日志写入该路径。

  3. 数据分析软件 vs input/output 数据：

     - 对于数据分析软件，通常会将其作为镜像层的一部分进行安装和配置。这样可以确保每次创建新容器时都有相同的数据分析软件环境。

     - 而对于input/output数据，可以将其存储在volume中，这样可以方便地输入和输出数据。可以将volume挂载到容器的特定路径，使得数据分析软件可以读取和写入该路径下的数据。

  4. Apache Server vs 静态HTML文件：

     - 对于Apache Server，可以将其作为镜像层的一部分进行安装和配置。这样可以确保每次创建新容器时都有相同的Apache Server环境。

     - 而对于静态HTML文件，可以将其作为容器层的一部分，或者存储在volume中。如果静态HTML文件需要频繁更新或者由多个容器共享，建议将其存储在volume中，并将volume挂载到容器的特定路径。

- 总之，在选择数据层和volume来存放数据时，关键是根据数据的特性和需求进行灵活的选择。镜像层适合存放不经常变动的应用代码和软件环境，而volume适合存放需要持久保存、频繁变动或与多个容器共享的数据。

- 在具体的使用上，docker 提供了两种类型的 volume: bind mount和 docker managedvolume。

- 因为 volume 实际上是 docker host 文件系统的一部分，所以 volume 的容量取决于文件系统当前未使用的空间，目前还没有方法设置 volume的容量。

#### bind mount

- -v的格式为`<host path>:<container path>`。
- 使用 bind mount单个文件的场景是:只需要向容器添加文件,不希望覆盖整个目录。在上面的例子中,我们将 html文件加到 apache中，同时也保留了容器原有的数据。
- 使用单一文件有一点要注意:host 中的源文件必须要存在，不然会当作一个新目录 bindmount给容器。
- bind mount 的使用直观高效，易于理解，但它也有不足的地方: bind mount需要指定host文件系统的特定路径，这就限制了容器的可移植性，当需要将容器迁移到其他 host，而该host没有要mount的数据或者数据不在相同的路径时,操作会失败。

#### docker managed volume

- docker managed volume与 bind mount 在使用上的最大区别是不需要指定mount源，指明mount point就行了。
  - source的位置可以在在容器的配置信息中找到,执行docker inspect命令:`docker inspect` 
  - 每当容器申请mount docker manged volume时，docker 都会在 /var/lib/docker/volumes下生成一个目录，这个目录就是mount源
  - 如果mount point指向的是已有目录，原有数据会被复制到 volume中。

### 数据共享

- 数据共享是 volume的关键特性
- volume 如何在容器与host之间、容器与容器之间共享数据。

#### 容器与host 共享数据

- 对于 bind mount是非常明确的:直接将要共享的目录mount到容器。
- docker managed volume就要麻烦点。由于volume位于host 中的目录，是在容器启动时才生成，所以需要将共享数据复制到volume 中。
  - docker cp可以在容器和 host 之间复制数据，当然我们也可以直接通过 Linux 的 cp命令复制到/var/lib/docker/volumes/xxx。

#### 容器之间共享数据

- 第一种方法是将共享数据放在bind mount中，然后将其 mount到多个容器。

- volume container：volume container是专门为其他容器提供 volume 的容器。它提供的卷可以是 bind mount,也可以是 docker managed volume。

  - ```
    docker create --name vc_data
    -V ~/htdocs:/usr/local/apache2/htdocs \
    -v /other/useful/tools \
    busybox
    ```

  - 其他容器可以通过`--volumes-from`使用vc_data这个 volume container

  - 特点

    - 与bind mount相比，不必为每一个容器指定 host path，所有 path都在volumecontainer中定义好了，容器只需与 volurne container关联，实现了容器与host 的解耦。
    - 使用 volume container 的容器，其 mount point是一致的,有利于配置的规范和标准化，但也带来一定的局限,使用时需要综合考虑。
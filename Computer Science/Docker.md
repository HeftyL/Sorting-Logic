# Docker

- 解决了运行环境和配置问题的软件容器，
- 方便做持续集成并有助于整体发布的容器虚拟化技术。

- 官网 docker：http://www.docker.com

- 仓库 Docker Hub: https://hub.docker.com/
- Docker是一个Client-Server结构的系统，Docker守护进程运行在主机上，然后通过Socket连接从客户端访问，守护进程从客户端接受命令并管理运行在主机上的容器。容器，是一个运行时环境，就是我们前面说到的集装箱。可以对比mysq|演示对比讲解

## 基本组成

- 镜像(image)：Docker镜像（Image）就是一个只读的模板。镜像可以用来创建Docker容器，一个镜像可以创建很多容器。

- 容器(container):Docker利用容器〈Container〉独立运行的一个或一组应用，应用程序或服务运行在容器里面，容器就类似于一个虚拟化的运行环境，容器是用镜像创建的运行实例。就像是Java中的类和实例对象一样，镜像是静态的定义，容器是镜像运行时的实体。容器为镜像提供了一个标准的和隔离的运行环境,它可以被启动、开始、停止、删除。每个容器都是相互隔离的、保证安全的平台
- 仓库(repository):仓库（Repository）是集中存放镜俊文件的场所。类似于Maven仓库，存放各种jar包的地方;github仓库，存放各种git项目的地方;Docker公司提供的官方registry被称为Docker Hub，存放各种镜像模板的地方。

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
# 序言

## 背景

- 早期对于项目所依赖的项目不涉及到项目管理方的说明包时，并采用的事情：拷贝三个地方，然后在目录下，有势，没有管理库容易发生冲突。
- Ant：2000年发布，纯java编写
- Maven：2004年发布，采用pom.xml管理项目 
- Gradle：2012年，google推出的项目管理工具

## 优势

灵活性：让项目等对 Maven 进行修改，提供工具的 API 能力，我们有或定制的制造过 
程。
粒度性：源的编译，资源的编译，都是一个修改的任务，我们可以将任务来达到精确的控 
制上。
扩展性：Gradle 支持插件机制，所以我们可以复用这些插件，就如同复用库一样简单方 
便。
兼容性：Gradle 不仅自身功能强大，而且它还能兼容所有的Maven、Ant功能，也就是说， 
Gradle 吸取了所有构建工具的长处。
Spring源码采用Gradle进行管理 

## 劣势

每一个版本都较上一次有非常大的改动，没有做较好向上兼容 
学习成本高，groovy脚本语言

## 介绍

- Gradle 是一种开源自动化工具，受环境影响，受制造工具的影响，集拉德莱尔的语言也不大成， 相对而言，一个全球范围内的Maven 的规范配置，多款Maven 的规格配置更灵活，可以使用 （特定领域语言，如 Groovy）编写编写，执行 DSL 精悍。

### 概念

- 构建<Badge text="build" />
- 仓库<Badge text="repository" />
- 坐标:确定一个jar包在仓库中的位置: org.spring springframework 5.1.15I

## 安装配置

- 环境：windows、java8，Gradle的运行需要JAVA_HOME坏境变量，该环境变量应指向JDK安装路径。
  - 新建GRADLE_HOME环境变量，将gradle根目录配置其值
  - 在path中加入项 %GRADLE_HOME%\bin ，类似于JDK或Maven的配置 
  - 打开CMD，执行 gradle -v ，成功输出版本则表示安装配置完成
- 官网下载地址：https://gradle.org/releases/
- 版本
  - binary-only版本(-bin后缀)：只有可执行文件
  - complete版本(-all后缀)：除了可执行文件还包含Gradle的源码和源码文档说明
- 目录结构
  - bin：包含gradle的命令-gradle
  - docs：包含用户手册、DSL参考指南、API文档
  - lib：包含gradle核心，以及它依赖的jar包
  - media:主要包含 Gradle的一些图标。
  - sample:样例。
  - ==src==: Gradle源代码,仅供参考使用。
- wrapper的方式进行构建

# Gradle Hello Word

- Gradle中2大对象：`Project`和`Task`。
  - 一个构件脚本就是一个`project`，任何一个`Gradle`构建都是由一个或多个`projec`t组成，大家可以把一个`project`比作一个`pom模块`或一个`jar`，每一个`project`都是一个`groovy`脚本文件。
  - `task`顾名思义就是任务，它是`Gradle`中最小的执行单元，类似于一个`method`或`function`函数，如编译、打包、生成`javadoc`等，一个`project`中会有多个`tasks`

- 步骤

  - ```groovy
    1.新建build.gradle文件（名字不能为其他否则无法执行）
    2.在其内部输入
    	task say{
        println "hello world"
    	}
    3.在cmd环境下执行gradle -q say回车查看结果
    	-q :输出QUIET级别及其之上的日志信息，只显示错误日志
    	-i :输出INFO级别及其之上的日志信息
    	-d :输出DEBUG级别及其之上的日志信息
    ```

  - 每一个task都是[`org.gradle.api.DefaultTask`](https://docs.gradle.org/current/dsl/org.gradle.api.DefaultTask.html)类型，在构建脚本中我们可以直接使用属性名使用该类中的相关属性，在底层`Groovy`会为你调用相关的`getter`和`setter`方法去访问这些属性

    - ```groovy
      task say{
          println "hello world"
          doFirst{
              println "ready"
          }
          doLast{
              println "thanks"
          }
      }
      ```

      

# 项目结构

- ├─build.gradle                        ①
  ├─gradlew                             ②
  ├─gradlew.bat						  ③
  ├─settings.gradle                     ④
  ├─gradle                              ⑤
  │  └─wrapper                          
  │      ├─ gradle-wrapper.jar          
  │      ├─ gradle-wrapper.properties   
  └─src                                 ⑥
      ├─main                            
      └─test
  1. 项目自动编译的时候要读取的配置文件。比如指定项目的依赖包等。`build.grade`有两个，一个是全局的，一个是在模块里面。全局的build.grade主要设置的是声明仓库源，gradle的版本号说明等。
  2. linux下的gradle环境脚本，可以执行gradle指令，如：./gradlew build
  3. windows下的gradle环境，可以执行gradle指令
  4. 包含必要的一些设置，例如，任务或项目之间的依懒关系等，无论有多少个子模块，该文件只会有一个，且一定在根项目中
  5. 包含wrapper文件夹及其2个子文件，作用是：可以自动安装gradle环境
  6. 程序源码
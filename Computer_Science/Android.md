# Android应用和开发环境

## Android的简介

- Android是由Andy Ruby创立的一个手机操作系统，后被Google收购。
- 目标：Google希望能与各方共同建立一个标准化、开放式的移动电话软件平台，从而在移动产业内形成一个开放式的操作平台。

## Android9.x平台特性

- Android的底层建立在linux之上，主要由操作系统、中间件、用户界面和应用软件4层组成
  - 设计思路：采用一种被称为软件叠层(Software Stack)的方式进行构建。
  - 作用
    - 使得层与层之间互相分离，明确各层的分工
    - 保证了层与层的低耦合，当下层的层内或层下发生改变时，上层应用程序无需任何改变
  - 和网络以及java web的后端一样都使用了分层的思想去解决问题。

- Android系统主要由6部分组成。**序号从小到大，越来越底层。**
  1. 系统App层
     - Android系统将包含一系列核心App，如电话拨号，联系人。
     - 普通开发者开发的各种Android程序，都属于这一层
  2. Java API框架层
     - 提供了大量API供开发者使用
     - 调用原生C/C++库的服务。
  3. 原生C/C++库
     - Android包含一套被不同组件所使用的C/C++库的集合。一般来说，Android应用开发者不能直接调用这套C/C++库集，而是通过它上面的JavaAPI框架调用这些库
     - SQLlite：供所有应用程序使用的功能强大的轻量级关系数据库
     - WebKit
     - OpenMAX
     - Libc
     - Media Framework
     - SGL
     - OpenGL ES
  4. Android运行时
     - 由Android核心库和ART（Android runtime）组成
  5. 硬件抽象层（HAL）
     - 主要提供了对linux内核的封装，向上提供蓝牙、摄像头等设备的编程接口，向下隐藏底层的实现细节。
  6. Linux内核
     - 提供了安全性，内存管理、进程管理、网络协议栈和驱动模型等核心服务。

## Gradle自动化构建

- Gradle的运行需要Java_Home环境变量
- 使用gradle 4.10.2

### Gradle文件结构

- bin：包含gradle的命令-gradle
- docs：包含用户手册、DSL参考指南、API文档
- lib：包含Gradle核心，以及它依赖的JAR包
- media：主要包含Gradle的一些图标
- sample：样例
- src：Gradle源代码

### Gradle项目结构

- Gradle构建文件的默认名字为build.gradle
- project:存放整个项目的全部资源
  - src:分类存放各种源文件、资源文件
    - main：主要存放与项目有关的源文件和资源
      - java：存放与项目相关的资源
      - resource：存放与项目相关的资源
    - test：主要存放与测试有关的源文件和资源
      - java：存放与测试相关的资源
      - resource：存放与测试相关的资源
  - build：存放编译后的class文件、资源的文件夹，该文件夹与src具有对应关系
  - libs：存放第三方JAR包的文件夹
  - build.gradle：gradle构建文件

#### 构建文件

- Gradle采用**领域对象模型**的概念来组织构建文件，在整个构建文件中涉及最核心的API
- Project：代表项目，通常一份构建文件代表一个项目。Project包含大量属性和方法
- TaskContainer：任务容器。每个Project都会维护一个TaskContainer类型的tasks属性。Project和TaskContainer由一一对应的关系
- Task：代表Gradle要执行的一个任务。Task允许指定它依赖的任务、该任务的类型，也可以通过configure（）方法配置任务。它还提供doFirst（）、doLast（）方法添加Action。
  - Action对象和Closure对象都可以代表Action。
  - Closure代表一个闭包，所以Action实际上就代表一个代码块

##### Task创建

- 调用Project的task（）方法创建Task
- 调用TaskContainer的create（）方法创建Task

###### Task指定如下3个常用属性

- dependsOn：指定该Task所依赖的其他Task

- type:指定该task的类型

- 代码块：通过传入的代码块参数配置Task

  - ```groovy
    task hello{
        println "hello world"
    }
    //使用
    gradle hello
    ```

### 属性定义

1. 为已有的属性指定属性值

   - Project、Task都是Gradle提供的API，他们本身具有内置属性，因此可以在构建文件中为这些属性指定属性值

   - project的常用属性

     - name：项目的名字

     - path：项目的绝对路径

     - description：项目的描述信息

     - buildDir：项目的构建结果存放路径

     - version：项目的版本号

     - ```groovy
       version = 1.0
       description = "project的属性"
       task hello{
           description = "task的属性"
           println "hello world"
       }
       ```

2. 通过ext添加属性

   - Gradle中所有实现了ExtensionAware接口的API都可通过ext来添加属性

   - ```groovy
     ext.prop1 = "111"
     ext{
         prop2 = "222"
     }
     task hello{
         prop3 = "333"
         println "hello world"
     }
     ```

3. 通过-P选项添加属性

4. 通过JVM参数添加属性

### 增量式构建

- 背景：如果每次执行任务都没有造成任何改变，那么重复执行就没有意义

- 如何判断是否产生变化？

  - Gradle将每个任务当做一个黑盒子：只要该任务的输入部分和输出部分都没有改变，Gradle就认为该任务的执行没有造成任何改变

    - 输入：Gradle的task使用inputs属性来代表任务的输入

      - 是一个TaskInput的对象
      - 可以设置文件、文件集、目录、属性等
    - 输出：Gradle的task使用outputs属性来代表任务的输出
  
      - 该属性是一个TaskOutputs类型的对象
      - 可以设置文件、文件集、目录、属性等
    - ```groovy
      task hello{
        	//def 定义属性的关键字
        	def sourceTxt = fileTree("source")
        	def dest = file('dist.txt')
          inputs.dir sourceTxt
          outputs.file dest
          println "hello world"
      }
      ```
  

### Gradle插件

- 应用插件就相当于引入了该插件包含的所有任务类型、任务和属性等，使得Gradle可执行插件中预先定好的任务

- ```groovy
  //使用java插件
  apply plugin: 'java'
  //查看构建文件支持的所有任务
  gradle tasks --all
  ```

#### java插件

- 任务
  - assemble:装配整个项目
  - build：装配并测试该项目
  - buildDependents：装配并测试该项目，以及该项目的依赖项目
  - classes：装配该项目的所有类
  - clean：删除构建目录的所有内容。属于Delete类型的任务
  - jar：将项目的所有类打包成JAR包
  - init:执行构建的初始化操作
  - wrapper:生成Gradle包装文件
  - compileJava：编译项目的所有主java源文件
  - processResources：处理该项目的所有主资源

- java插件要求将项目源代码和资源都放在src目录下，将构建生成的字节码文件和资源放在build目录下，插件会自动管理该目录下的两类源代码和资源

  - main：项目的主代码和资源
  - test：项目的测试代码和资源

- 可以在构建文件中通过sourceSets方法改变项目代码、资源的存储路径，第三方或额外依赖的源代码也是通过sourceSets方法配置

  - ```groovy
    sourceSets{
      	testlib
    }
    ```

    - 可以将testlib的java源代码放在src/testlib/java目录下，资源放在src/testlib/resources目录下

#### 依赖管理

- 背景：在构建java项目时通常都要依赖一个或多个框架

- 步骤

  1. 为Gradle配置仓库。配置仓库的目的是告诉Gradle到哪里下载JAR包

     - ```groovy
       //定义仓库，在构建文件中
       repositories{
         //使用Maven默认的中央仓库
         mavenCentral()
       }
       ```

  2. 为不同的组配置依赖JAR包。配置依赖JAR包的目的是告诉Gradle要下载哪些JAR包

     - 组：相当于一个文件夹，里面放不同的jar，谁要用就直接复制这个文件夹，而不需要一个一个jar的去复制

       - ```groovy
         configurations{
           group1
         }
         ```

     - 使用dependencies来为依赖组配置JAR包

       - group：JAR所属的组织名

       - name：JAR名称

       - version：JAR版本

       - ```groovy
         dependencies{
           group1 group:'commons-logging',name:'commons-logging',version:'1.2'
           //或者
           group1 'commons-logging:commons-logging:1.2'
         }
         ```

     - java插件提供的组

       - implementation:主项目源代码(src/main/java）所依赖的组。
       - compileOnly:主项目源代码（ src/main/java）编译时才依赖的组。
       - runtimeOnly:主项目源代码(src/main/java）运行时才依赖的组。
       - testImplementation:项目测试代码(src/test/java）所依赖的组。
       - testCompileOnly:项目测试代码（src/test/java）编译时才依赖的组。
       - testRuntimeOnly:项目测试代码（src/test/java）运行时才依赖的组。
       - archives:项目打包时依赖的组。

### 自定义任务

- 自定义任务就是一个实现Task接口的Groovy类,该接口定义了大量抽象方法需要实现，因此一般自定义任务类会继承DefaultTask基类。自定义任务的Groovy类可以自定义多个方法，这些方法可作为Action使用。
- 使用了@TaskAction修饰的方法将会自动添加为该任务的，反之需要使用doLast或doFirst方法手动将它添加成Action

## Android环境搭建

1. 安装Android Studio
2. 安装Android SDK
   - 目录结构
     - build-tools:存放不同版本的 Android项目编译工具。
     - docs:存放Android SDK开发文件和API文档等。
     - emulator:存放Android自带的各种模拟器程序。
     - extras:存放Google提供的USB驱动、Intel提供的硬件加速等附加工具包。
     - licenses:存放 Android的相关授权文档。
     - patcher:存放Android 的一些兼容性补丁。
     - platforms:存放不同版本的Android系统。刚解压缩时该目录为空。
     - platform-tools:存放 Android平台相关工具。
     - skins:存放针对不同Android模拟器的皮肤。
     - sources:存放不同版本的Android系统的源代码。
     - system-images:存放不同Android平台针对不同CPU架构提供的系统镜像。这些系统镜像用于启动、运行 Android模拟器。
     - tools:存放大量的Android开发、调试工具。

### 第一个Android应用

- Android应用程序建立在应用程序框架之上，所以Android编程就是面向应用程序框架 API编程——这种开发方式与编写普通的 Java或Kotlin应用程序并没有太大的区别，只是Android新增了一些API而已。
- 步骤
  1. 建立一个Android项目或Android模块
  2. 在XML布局文件中定义应用程序的用户界面
     - Android 把用户界面放在XML文档中定义，就可以让XML文档专门负责用户UI设置，而Java程序则专门负责业务实现这样可以**降低程序的耦合性**
     - res/layout存放Android的应用用户界面，类似于html
       - RelativeLayout：它代表一个相对布局
       - UI控件
         - Button:代表一个普通按钮。
         - TextView:代表一个文本框。
           - `android:id`:**指定该控件的唯一标识**，在Java或Kotlin程序中可通过findViewByIld("id")来获取指定的 Android界面组件。
           - `android:layout width`:**指定该界面组件的宽度**。如果该属性值为match parent，则说明该组件与其父容器具有相同的宽度;如果该属性值为 wrap content，则说明该组件的宽度取决于它的内容——能包裹它的内容即可。
           - `android:layout height`:**指定该界面组件的高度**。如果该属性值为match parent，则说明该组件与其父容器具有相同的高度;如果该属性值为 wrap content，则说明该组件的高度取决于它的内容——能包裹它的内容即可。
  3. 在Java或Kotlin代码中编写业务实现
     - main/java存放Android的业务逻辑

## Android应用结构分析

- app
  - build:存放该项目的构建结果
  - libs:存放该项目依赖的第三方类库
  - src：存放源代码和资源
    - androidTest
    - main
      - java（存放Java或Kotlin源文件)
      - res（存放资源）
        - drawable
        - layout
        - mipmap-xxx
        - values
      - AndroidManifest.xml
    - test
  - build.gradle (Gradle构建文件)
  - .gitignore（该文件配置Git工具忽略管理哪些文件)
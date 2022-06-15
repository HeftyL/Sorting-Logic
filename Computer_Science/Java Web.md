# Java Web

## 网页开发基础

### html

### css

### JavaScript

## XML

- XML（Extensible Markup Language）
- 作用
  - 用来保存数据，而且这些数据具有自我描述性
  - 它还可以做为项目或者模块的配置文件
  - 还可以做为网络传输数据的格式（现在  JSON  为主）
- html   和    XML    注释    一样    : <!--html    注释 -->
- xml  中的元素（标签）分成    单标签<标签名/>和双标签<标签名><标签名/>

- 可扩展标记语言，标准通用标记语言的子集，简称XML。是一种用于标记电子文件使其具有结构性的标记语言。

	- 所谓可扩展，指的是用户可以按照XML规则自定义标记
- XML和HTML的比较

	- 1.HTML被设计出是用来显示数据的，XML被设计出来是为了传输和存储数据的
  2.HTML不区分大小写，而XML是严格区分大小写的
  3.HTML可以有多个根元素，而格式良好的XML有且只能有哦一个根元素
  4.HTML

### 命名规则

- 名称可以含字母、数字以及其他的字符
- 名称不能以数字或者标点符号开始
- 名称不能包含空格

### xml 属性

- xml 的标签属性和 html 的标签属性是非常类似的，属性可以提供元素的额外信息
- 在标签上可以书写属性：
  - 一个标签上可以书写多个属性。每个属性的值必须使用    引号    引起来。 的规则和标签的书写规则一致。

### 语法规则

- 所有 XML 元素都须有关闭标签（也就是闭合）

- XML    标签对大小写敏感

- XML    必须正确地嵌套

- XML    文档必须有根元素

  - 根元素就是顶级元素，没有父标签的元素，叫顶级元素。根元素是没有父标签的顶级元素，而且是唯一一个才行。

- XML    的属性值须加引号

- XML  中的特殊字符

  - 文本区域（CDATA 区）

  - CDATA 语法可以告诉 xml 解析器，我 CDATA 里的文本内容，只是纯文本，不需要 xml 语法解析

    - ```xml
      <![CDATA[   这里可以把你输入的字符原样显示，不会解析  xml ]]>
      ```

### xml 解析

- 不管是  html 文件还是  xml  文件它们都是标记型文档，都可以使用  w3c  组织制定的  dom 技术来解析。
-   JDK  提供了 xml 解析技术  DOM  和  Sax  
  - dom  解析技术是  W3C  组织制定的，而所有的编程语言都对这个解析技术使用了自己语言的特点进行实现。Java 对  dom  技术解析标记也做了实现。
  - sun  公司在  JDK5  版本对    dom  解析技术进行升级：SAX（    Simple    API    for    XML   ）SAX  解析，它跟  W3C  制定的解析不太一样。它是以类似事件机制通过回调告诉用户当前正在解析的内容。 它是一行一行的读取  xml  文件进行解析的。不会创建大量的  dom  对象。所以它在解析  xml 的时候，在内存的使用上。和性能上。都优于  Dom  解析。
  - 第三方的解析
  - jdom    在  dom 基础上进行了封装    
  - dom4j    又对  jdom    进行了封装。
  - pull    主要用在  Android    手机开发，跟  sax 非常类似都是事件机制解析  xml  文件。

#### dom4j 解析

- 步骤：

  - 第一步： 先加载 xml 文件创建 Document 对象 

  - 第二步：通过 Document 对象拿到根元素对象

  - 第三步：通过根元素.elelemts(标签名);    可以返回一个集合，这个集合里放着。所有你指定的标签名的元素对象 

  - 第四步：找到你想要修改、删除的子元素，进行相应在的操作

  - 第五步，保存到硬盘上

  - ```java
    /*
    * dom4j 获取  Documet对象 */
    @Test
    public void getDocument() throws DocumentException {
    // 要创建一个  Document对象，需要我们先创建一个  SAXReader对象 
      SAXReader reader = new SAXReader();
    // 这个对象用于读取 xml文件，然后返回一个 Document。 
      Document document = reader.read("src/books.xml"); 
      // 打印到控制台，看看是否创建成功
    System.out.println(document); 
    }
    // 第二步，通过  Document对象。拿到  XML的根元素对象
    Element root = document.getRootElement();
    // 打印测试
    // Element.asXML() 它将当前元素转换成为  String对象 // System.out.println( root.asXML() );
    //     第三步，通过根元素对象。获取所有的  book 标签对象
    // Element.elements(标签名)它可以拿到当前元素下的指定的子元素的集合 
    List<Element> books = root.elements("book");
    //     第四步，遍历每个  book标签对象。然后获取到  book标签对象内的每一个元素， 
    for (Element book : books) {
    // 测试
    // System.out.println(book.asXML()); // 拿到  book下面的  name元素对象
    Element nameElement = book.element("name"); // 拿到  book下面的  price元素对象
    Element priceElement = book.element("price"); // 拿到  book下面的  author元素对象
    Element authorElement = book.element("author");
    // 再通过  getText() 方法拿到起始标签和结束标签之间的文本内容 
      System.out.println("书名" + nameElement.getText() + " , 价格:"+ priceElement.getText() + ", 作者：" + authorElement.getText());
    	}
    }
    ```

    



##Tocat

- Javaweb是指，所有通过Java语言编写可以通过浏览器访问的程序的总称，叫JavaWeb。JavaWeb是基于请求和响应来开发的。

- 请求

  - 请求是指客户端给服务器发送数据，叫请求Request。

- 相应

  - 响应是指服务器给客户端回传数据，叫响应Response。
- 请求和响应是成对出现的，有请求就有响应。

### Web 资源

- web资源按实现的技术和呈现的效果的不同，又分为静态资源和动态资源两种。
- 静态资源:html、css、js、txt、 mp4视频, jpg图片
- 动态资源: jsp页面、Servlet程序

### 常用的Web服务器

- Tomcat:由Apache组织提供的一种web服务器，提供对 jsp和Servlet 的支持。它是一种轻量级的javaWeb容器（服务器》，也是当前应用最广的JavaWeb服务器（免费〉。

	- 目录介绍

		- bin

			- 专门用来存放Tomcat服务器的可执行程序

		- conf

			- 专门用来存放Tocmat服务器的配置文件

		- lib

			- 专门用来存放Tomcat服务器的jar包

		- logs

			- 专门用来存放Tomcat服务器运行时输出的日记信息

		- temp

			- 专门用来存放Tomcdat运行时产生的悔时数据

		- webapps

			- 专门用来存放部署的web工程。

		- work

			- 是Tomcat工作时的目录，用来存放Tomcat运行时jsp翻译为servlet的源码，和session钝化的目录。
- Jboss:是一个遵从JavaEE规范的、开放源代码的、纯Java的EB服务器，它支持所有的JavaEE规范《免费〉。
- GlassFish: 由oracle公司开发的一款JavaWeb服务器，是一款强健的商业服务器，达到产品级质量(应用很少)·
- Resin :是cAUCHO公司的产品，是一个非常流行的服务器，对servlet和JSP提供了良好的支持，性能也比较优良，resin自身采用JAA语言开发(收费，应用比较多）。
- webLogic:是oracle公司的产品，是目前应用最广泛的web服务器，支持JavaEE规范，而且不断的完善以适应新的开发要求，适合大型项目（收费，用的不多，适合大公司〉。

### 修改 端口号

找到  Tomcat  目录下的  conf 目录，找到  server.xml  配置文件。

找到Connector标签，修改port属性为你需要的端口号。端口号范国:1- 65535，修改完端口号，一定要重启Tomcat服务器才能生效

### 部署

- 第一种只需要把  web 工程的目录拷贝到  Tomcat 的  webapps  目录下 即可。

  - 访问：只需要在浏览器中输入访问地址格式如下：http://ip:port/工程名/目录下/文件名

- 第二种

- 找到  Tomcat  下的  conf 目录\Catalina\localhost\   下,创建一个xml配置文件，文件名一般与项目名相同。

- abc.xml  配置文件内容

  ```xml
  <!-- Context  表示一个工程上下文path  表示工程的访问路径:/abc docBase  表示你的工程目录在哪里-->
  <Context path="/abc" docBase="E:\book" />
  <!--访问这个工程的路径如下:http://ip:port/abc/就表示访问E:\book  目录-->
  ```

  

## Servlet（Server Applet）

### 简介

- Servlet是JavaEE规范之一。规范就是接口
- Servlet就JavaWeb三大组件之一。三大组件分别是:Servlet程序、Filter过滤器、Listener监听器。
- Servlet是运行在服务器上的一个java小程序，它可以接收客户端发送过来的请求，并响应数据给客户端。

### 生命周期

- 1、执行  Servlet  构造器方法
- 2、执行  init  初始化方法
  - 第一、二步，是在第一次访问，的时候创建 Servlet 程序会调用。
- 3、执行  service  方法
  - 第三步，每次访问都会调用。
- 4、执行  destroy  销毁方法
  - 第四步，在  web  工程停止的时候调用。

### /的不同意义

- 在  web  中    /   斜杠    是一种绝对路径。
  - /   斜杠    如果被浏览器解析，得到的地址是：http://ip:port/
- / 斜杠 如果被服务器解析，得到的地址是：http://ip:port/工程路径

### 使用

- 实现Servlet接口

- 配置web.xml

  - ```xml
    - < ! -- servlet标签给Tomcat配置ServLet程序-->
      <servlet>
      < ! --servLet-name标签servLet程序起一个别名（一般是类名）-->
      <servlet-name>Helloservlet</servlet-name>
      <!--servlet-cLass是ServLet程序的全类名-->
      <servlet-class>com.atguigu.servlet.HelloServlet</servlet-class>
       //设置初始化参数，可以通过ServletConfig. getInitParameter(String name)获得值
      <init-param>
              <param-name>user</param-name>
              <param-value>root</param-value>
       </init-param>
      </servlet>
    - < ! --servlet-mapping标签给servlet程序配置访问地址-->
      <servlet-mapping>
      < ! --servlet-name标签的作用是告诉服务器，我当前配置的地址给哪个ServLet程序使用-->
      <servlet-name>HelloServlet</servlet-name>
      < ! - -url-pattern标签配置访问地址<br/>
          /斜杠在服务器解析的时候，表示地址为:http://ip : port/工程路径
          / helLo表示地址为: http: //ip : port/工程路径/heLLo           -->
      <url-pattern>/hello</url-pattern>
      </servlet-mapping>
    ```

### 软件包

#### javax.servlet

- 所包含的接口：RequestDispatcher；Servlet；ServletConfig；ServletContext；ServletRequest；ServletResponse；SingleThreadModel。
- 所包含的类：GenericServlet；ServletInputStream；ServletOutputStream；ServletException；UnavailableException。

  - GenericServlet

    - HttpServlet

#### public interface Servlet

- | 方法                                                         | 说明                                                         |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | public void init(ServletConfig config);                      | Servlet引擎会在Servlet实例化之后，置入服务之前精确地调用init方法。在调用service方法之前，init方法必须成功退出。如果init方法抛出一个ServletException，你不能将这个Servlet置入服务中，如果init方法在超时范围内没完成，我们也可以假定这个Servlet是不具备功能的，也不能置入服务中。 |
  | public void service(ServletRequest request, ServletResponse response) ; | Servlet引擎调用这个方法以允许Servlet响应请求。这个方法在Servlet未成功初始化之前无法调用。在Servlet被初始化之前，Servlet引擎能够封锁未决的请求。 在一个Servlet对象被卸载后，直到一个新的Servelt被初始化，Servlet引擎不能调用这个方法 |
  | public void destroy();                                       | 当一个Servlet被从服务中去除时，Servlet引擎调用这个方法。在这个对象的service方法所有线程未全部退出或者没被引擎认为发生超时操作时，destroy方法不能被调用。 |

#####  interface ServletConfig

- | 方法                                 | 说明                                                         |
  | ------------------------------------ | ------------------------------------------------------------ |
  | String getServletName()              | 返回此 servlet 实例的名称。该名称可以通过服务器管理提供，在 Web 应用程序部署描述符中分配，或者对于未注册（因此未命名）的 servlet 实例，它将是 servlet 的类名。 |
  | String getInitParameter(String name) | 获取具有给定名称的初始化参数的值。                           |
  | ServletContext getServletContext()   | 返回ServletContext对调用者正在执行的 的引用。                |


###### interface ServletContext

- 1、ServletContext是一个接口,它表示 Servlet 上下文对象
  2、一个web工程，只有一个 ServletContext对象实例。
  3、ServletContext对象是一个域对象。

  - 域对象,是可以像 Map一样存取数据的对象,叫域对象。这里的域指的是存取数据的操作范围。

- | 方法                                 | 说明                                       |
  | ------------------------------------ | ------------------------------------------ |
  | String getInitParameter(String name) | 获取web.xml中配置的上下文参数context-param |
  | String getContextPath()              | 获取当前的工程路径,格式:/工程路径          |
  | String getRealPath(String path)      | 获取工程部署后在服务器硬盘上的绝对路径     |

  - String getRealPath(String path)

  	- 例如，如果path等于/index.html，则此方法将返回服务器文件系统上的绝对文件路径，向其发送 http://<host>:<port>/<contextPath>/index 形式的请求。 html 将被映射，其中<contextPath>对应于此 ServletContext 的上下文路径。
  	- 返回的真实路径将采用适合运行 servlet 容器的计算机和操作系统的格式，包括正确的路径分隔符。
  	- 必须考虑 捆绑在应用程序的 /WEB-INF/lib目录中的 JAR 文件的/META-INF/resources目录中的资源，前提是容器已从包含它们的 JAR 文件中解压它们，在这种情况下，解压位置的路径必须被退回。
  	- null如果 servlet 容器无法将给定的虚拟路径转换为 真实路径，则此方法返回。

  - 4、像Map一样存取数据

#### javax.servlet.http

- 所包含的接口：HttpServletRequest；HttpServletResponse；HttpSession；HttpSessionBindingListener；HttpSessionContext。
- 所包含的类：Cookie；HttpServlet；HttpSessionBindingEvent；HttpUtils。

##### public interface HttpServletRequest

- servlet 容器创建一个HttpServletRequest 对象并将其作为参数传递给 servlet 的服务方法（doGet、doPost等）。

- 扩展ServletRequest接口以提供 HTTP servlet 的请求信息。

- 

- | 方法                                  | 说明                                                         |
  | ------------------------------------- | ------------------------------------------------------------ |
  | getRequestURI()                       | 获取请求的统一资源标志符                                     |
  | getRequestURL()                       | 获取请求的统一资源定位符（绝对路径〉                         |
  | getRemoteHost()                       | 获取客户端的ip地址                                           |
  | getHeader()                           | 获取请求头                                                   |
  | getParameter()                        | 获取请求的参数                                               |
  | getParameterValues()                  | 获取请求的参数〈多个值的时候使用）                           |
  | getMethod()                           | 获取请求的方式GET或POST                                      |
  | setAttribute( key , value);           | 设置域数据                                                   |
  | getAttribute(key );                   | 获取域数据                                                   |
  | void setCharacterEncoding(String env) | 覆盖此请求正文中使用的字符编码的名称。必须在读取请求参数或使用 getReader() 读取输入之前调用此方法。否则，它没有效果。 |
  | getRequestDispatcher(String path)     | 获得请求转发对象                                             |

  - getRequestDispatcher()

    - void forward(ServletRequest request,ServletResponse response)
       - 将来自 servlet 的请求转发到服务器上的另一个资源（servlet、JSP 文件或 HTML 文件）。此方法允许一个 servlet 对请求进行初步处理，并允许另一个资源生成响应。
       - 对于RequestDispatcher获得的 via getRequestDispatcher()，ServletRequest 对象的路径元素和参数进行了调整以匹配目标资源的路径。
       - forward应该在响应提交给客户端之前调用（在刷新响应主体输出之前）。如果响应已经被提交，这个方法会抛出一个IllegalStateException. 响应缓冲区中未提交的输出在转发之前会自动清除。
       - 请求和响应参数必须是与传递给调用 servlet 服务方法的对象相同的对象，或者是包装它们 的一个ServletRequestWrapper或多个 类的子类。ServletResponseWrapper
       - 此方法将给定请求的调度程序类型设置为 DispatcherType.FORWARD.
    - 1、浏览器地址栏没有变化:
      2、他们是一次请求
      3、他们共享Request域中的数据
      4、可以转发到WEB-INF目录下
      5、不可以访问工程以外的资源
  - base html标签
    - base标签可以设置当前文件里的相对路径工作时，针对哪一个路径进行跳转。
    - 通过设置href属性指定base的路径。

##### public interface HttpServletResponse

- 扩展ServletResponse接口以在发送响应时提供特定于 HTTP 的功能。例如，它具有访问 HTTP 标头和 cookie 的方法。

- servlet 容器创建一个HttpServletResponse对象并将其作为参数传递给 servlet 的服务方法（doGet、doPost等）。

- | 方法                    | 说明                         |
  | ----------------------- | ---------------------------- |
  | getOutputStream();      | 常用于下载（传递二进制数据） |
  | getWriter();            | 常用于回传字符串（常用）     |
  | sendRedirect(String s） | 请求重定向                   |

  - 响应的乱码解决

    - ```java
      resp.setContentType("text/html; charset=UTF-8");
      ```

  - sendRedirect(String s）

  - 特点（与请求转发相反）

    - 1、浏览器地址栏会发生变化
    2、两次请求
    3、不共享Request域中数据
    4、不能访问WEB-INF下的资源
    5、可以访问工程外的资源

### Http协议

- 所谓HTTP协议，就是指，客户端和服务器之间通信时，发送的数据，需要遵守的规则，叫HTTP协议。

	- 协议是指双方，或多方,相互约定好,大家都需要遵守的规则,叫协议。
	- HTTP协议中的数据又叫报文。

- 请求的HTTP协议格式

	- 客户端给服务器发送数据叫请求。
	- 服务器给客户端回传数据叫响应。
	- 请求又分为GET请求,和 POST请求两种

		- GET请求

			- 
GET /06_servlet/a. html HTTP/1.1
Accept: application/x-ms-application,image/ jpeg，application/xaml+xml，imaga/gif，image/ pjpeg,applicationl e-ns-ha，,**Accept-Language: zh-CN
User-Agent: Mozilla/4.0(compatible;MSIE 8.0;Windows NT 6.1; Winb4; xo4;Trident/4.0; .NET CLR 2.0.50727 8L2 .NEI LR3.5.30729;.NET CLR 3.0.30729; Media Center PC 6.0)
Accept-Encoding: gzip, deflateHost: localhost:8080
Connection: Keep-Alive
UA-CPU: AMD64

				- 请求行

					- 请求的方式
					- 请求的资源路径[+?+请求参数]
					- 请求的协议的版本号

				- 请求头

					- key：value
					- Accept:告诉服务器，客户端可以接收的数据类型
					- Accept-Language:告诉服务器客户端可以接收的语言类型

						- zh_CN  中文中国
						- en_Us  英文美国

					- User-Agent:就是浏览器的信息
					- Accept-Encoding:告诉服务器，客户端可以接收的数据编码（压缩）格式Host:表示谤求的服务器ip和端口号
					- Connection:告诉服务器谤求连接如何处理

						- Keep-Alive告诉服务器回传数据不要马上关闭，保持一小段时间的连接
						- Closed马上关闭

			- 1、form标签method=get
2、a标签
3、link标签引入css
4、Script标签引入js文件
5、img标签引入图片
6、iframe 引入 html页面
7、在浏览器地址栏中输入地址后敲回车

		- Post请求

			- POST /06_servlet/hello3 HTTP/1.1
Accept: application/x-Tms-application， image/ jpeg, application/xanml+xml，image/gif，image/pjpeg， application/x-Tns-xbap，*/*Referer: http:/ /localhost: 8080/06_servlet7a. htmlAccept: application/x-Tms-application， image/ jpeg, application/xanml+xml，image/gif，image/pjpeg， application/x-Tns-xbap，*/*Referer: http:/ /localhost: 8080/06_servlet7a. html
Accept-Language: zh-CN
User-Agent: Mozilla/4.0(compat ible; MSIE 8.0;Windows NT 6.1;Win64; xo4;Trident/4.0;.NET GLR 2.0.50727; SLO2;.NET LR 3.5.30729;.NET CLR 3.0.30729; Media Center PC 6. 0)
Gontent-Type: application/ x-www-form-urlencodedUA-CPU: AMD64
Accept-Encoding: gzip,deflate
Host: localhost:8080
Content-Length: 26
Connection: Keep-Alive
Cache-Control : no-cache

action=login&username=root

	请求行
	
			请求的方式 
			请求的资源路径
			请求的协议的版本号
	请求头
	
			key：value
			Accept:表示客户端可以接收的数据类型
			Accept-Language:表示客户端可以接收的语言类型
			Referer:表示谤求发起时，浏览器地址栏中的地址（从哪来)
			User-Agent:表示浏览器的信息
			Content-Type:表示发送的数据的类型
			application/ x-www-form-urlencoded
			表示提交的数据格式是:name=value&name=value,然后对其进行url编码
			url编码是把非英文内容转换为:%xx%xx
			multipart/form-data：表示以多段的形式提交数据给服务器（以流的形式提交，用于上传)
			Content-Lnegth:表示发送的数据的长度
			Cache-Control表示如何控制缓存no-cache不缓存Ⅰ
			空行
			请求体
				 发送给服务器的数据

- 响应

	- 格式

		- 1、响应行

			- 响应的协议和版本号
		响应状态码
		响应状态描述符

		- 2、响应头

			- key : value

				- 不同的响应头，有其不同含义

			- MIME(Multipurpose Internet Mail Extensions)

				- 是http协议中的数据类型
				- 常见的MIME类型(通用型)：
		超文本标记语言文本 .html text/html
		xml文档 .xml text/xml
		XHTML文档 .xhtml application/xhtml+xml
		普通文本 .txt text/plain
		RTF文本 .rtf application/rtf
		PDF文档 .pdf application/pdf
		Microsoft Word文件 .word application/msword
		PNG图像 .png image/png
		GIF图形 .gif image/gif
		JPEG图形 .jpeg,.jpg image/jpeg
		au声音文件 .au audio/basic
		MIDI音乐文件 mid,.midi audio/midi,audio/x-midi
		RealAudio音乐文件 .ra, .ram audio/x-pn-realaudio
		MPEG文件 .mpg,.mpeg video/mpeg
		AVI文件 .avi video/x-msvideo
		GZIP文件 .gz application/x-gzip
		TAR文件 .tar application/x-tar
		任意的二进制数据 application/octet-stream

		- 空行
		- 3、响应体--->>>就是回传给客户端的数据

## JSP（JavaServer Pages）

### 简介

- jsp的主要作用是代替Servlet程序回传 html页面的数据。

  - 因为servlet程序回传 html页面数据是一件非常繁锁的事情。开发成本和维护成本都极高。
- 当我们第一次访问jsp页面的时候。Tomcat服务器会帮我们把jsp页面翻译成为一个java源文件。并且对它进行编译成为.class字节码程序

  - jsp的本质就是servlet

### 语法

#### page属性

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
```

| 属性         | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| language     | 所支持的 语言                                                |
| contentType  | jsp返回的数据类型是什么                                      |
| pageEncoding | 当前jsp页面文件本身的字符集                                  |
| import       | 用于导包，导类                                               |
| autoFlush    | write的缓存满了是否自动刷新                                  |
| buffer       | write的缓存区大小                                            |
| errorPage    | 表示错误后自动跳转转去的路径                                 |
| isErrorPage  | 设置当前jsp页面是否是错误信息页面。默认是false，如果是true可获取异常信息 |
| session      | 是否创建httpsession对象，默认是true                          |
| extends      | jsp翻译出来的java程序，默认继承谁                            |

#### 三种脚本

##### 声明脚本

```jsp
<%!java代码 %>
```

- 可以定义全局变量。
- 定义  static  静态代码块
- 定义方法
- 定义内部类
- 几乎可以写在类的内部写的代码，都可以通过声明脚本来实现

##### 表达式脚本（重点）

```jsp
<%=表达式        %>
```

- 表达式脚本    用于向页面输出内容。
- 表达式脚本    翻译到  Servlet  程序的  service  方法中    以    out.print()    打印输出 out    是  jsp  的一个内置对象，用于生成  html 的源代码
- 注意：表达式不要以分号结尾，否则会报错 
- 表达式脚本可以输出任意类型。
  - 输出整型
  - 输出浮点型
  - 输出字符串
  - 输出对象

##### 代码脚本

```jsp
<%        java  代码        %>
```

- 代码脚本里可以书写任意的  java 语句。 
- 代码脚本的内容都会被翻译到  service  方法中。
- 所以  service  方法中可以写的  java 代码，都可以书写到代码脚本中

### 注释

```
//   单行  java 注释

/*多行  java 代码注释 */
单行注释和多行注释能在翻译后的 java 源代码中看见。

<%-- jsp  注释    --%>
jsp  注释在翻译的时候会直接被忽略掉

<!-- html  注释    --> 
html 的注释会被翻译到  java 代码中输出到  html  页面中查看
```

### 内置对象

| 名称        | 类型                | 说明                                                         |
| ----------- | ------------------- | ------------------------------------------------------------ |
| request     | HttpServletRequest  | 请求对象，可以获取请求信息                                   |
| response    | HttpServletResponse | 响应对象。可以设置响应信息                                   |
| pageContext | PageContext         | 当前页面上下文对象。可以在当前上下文保存属性信息             |
| session     | HttpSession         | 会话对象。可以获取会话信息                                   |
| exception   | Throwable           | 异常对象只有在  jsp  页面的  page   指令中设置    isErrorPage="true" 的时候才会存在 |
| application | ServletContext      | 可以获取整个工程的一些信息。                                 |
| config      | ServletConfig       | 可以获取  Servlet  的配置信息                                |
| out         | JspWriter           | 输出流。                                                     |
| page        | Object              | 表示当前  Servlet  对象实例（无用，用它不如使用  this  对象） |

- out  输 出 流     和     response.getwriter() 输出流
  - 所有jsp中out输出流的内容都必须要先flush写入到Response的writer对象的缓冲区中。才能最终输出到客户端(浏览器)
  - jsp的out输出永远是追加到Response的writer缓冲区的末尾
- out的writer和print方法
  - writer方法将参数转换为对应的char保存到缓冲区
  - print方法将所有的参数都转换为string再存到缓冲区
  - 建议使用print

- 九大内置对象，都是我们可以在【代码脚本】中或【表达式脚本】中直接使用的对 象。

##### 四大域对象

| 域对象      | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| request     | 可以保存数据在同一个  request  对象中使用。经常用于在转发的时候传递数据 |
| pageContext | 可以保存数据在同一个 jsp 页面中使用                          |
| session     | 可以保存在一个会话中使用                                     |
| application | 就是  ServletContext  对象（整个web工程都有效,只要web工程不停止，数据都在） |

使用顺序pageContext->request ->session->application

### 标签

- 静态包含

  - ```jsp
    <%@ include file="" %>很常用
    ```

  - 静态包含是把包含的页面内容原封装不动的输出到包含的位置。

- 动态包含

  - ```jsp
    <jsp:include page=""></jsp:include>  很少用
    ```

  - 动态包含会把包含的  jsp  页面单独翻译成  servlet  文件，然后在执行到时候再调用翻译的  servlet  程序。并把 计算的结果返回。

  - 动态包含是在执行的时候，才会加载。所以叫动态包含。

- 页面转发

  - ```jsp
    <jsp:forward page=""></jsp:forward>
    <jsp:forward   转发功能相当于
    request.getRequestDispatcher("/xxxx.jsp").forward(request, response); 的功能。
    ```

## Cookie

### 简介

- 1、Cookie翻译过来是饼干的意思。
2、Cookie是服务器通知客户端保存键值对的一种技术。
3、客户端有了Cookie后,每次请求都发送给服务器。
4、每个Cookie 的大小不能超过4kb

### 操作

```java
//1创建Cookie 对象
Cookie cookie = new Cookie( "key4", "value4" );
//2通知客户端保存Cookie,如果不存在相同的key就添加，存在就修改
//也可以找到cookie再调用  setValue()方法赋于新的  Cookie  值。
resp.addCookie(cookie);
服务器获取Cookie
  通知客户端保存修改
req.getCookies() //Cookie[]
```

#### 声明周期控制

- setMaxAge()
  - 正数，表示在指定的秒数后过期
  - 负数，表示浏览器一关，Cookie  就会被删除（默认值是-1） 
  - 零，表示马上删除  Cookie

#### 设置有效路径  Path

- Cookie  的  path 属性可以有效的过滤哪些  Cookie  可以发送给服务器。哪些不发。 path 属性是通过请求的地址来进行有效的过滤。

- ```
  CookieA               path=/工程路径 
  CookieB               path=/工程路径/abc
  请求地址如下：
  http://ip:port/工程路径/a.html
  CookieA   发送 
  CookieB   不发送
  http://ip:port/工程路径/abc/a.html
  CookieA 发送 
  CookieB 发送
  ```

- setPath(String path)

  - 修改path

## Session

- Session  就一个接口（HttpSession）。

- Session  就是会话。它是用来维护一个客户端和服务器之间关联的一种技术。

- 每个客户端都有自己的一个  Session  会话。

- Session  技术，底层其实是基于  Cookie  技术来实现的。

- Session  会话中，我们经常用来保存用户登录之后的信息。

- | 方法                                      | 说明                                                         |
  | ----------------------------------------- | ------------------------------------------------------------ |
  | request.getSession()                      | 第一次调用是：创建  Session  会话，之后调用都是：获取前面创建好的  Session  会话对象。 |
  | boolean isNew();                          | 判断到底是不是刚创建出来的（新的）                           |
  | getId()                                   | 得到  Session  的会话  id  值。                              |
  | 域数据的存取                              |                                                              |
  | setAttribute（String name, Object value） | 存session数据，key，value的形式                              |
  | getAttribute（String name）               | 获得session数据                                              |
  | 生命周期控制                              |                                                              |
  | setMaxInactiveInterval(int interval)      | 设置  Session  的超时时间（以秒为单位），超过指定的时长，Session 就会被销毁。值为正数的时候，设定  Session  的超时时长。 负数表示永不超时（极少使用） |
  | int getMaxInactiveInterval()              | 获取  Session  的超时时间，默认的超时时间长为  30  分钟      |


## Filter

- Filter 过滤器它是 JavaWeb 的三大组件之一。三大组件分别是：Servlet 程序、Listener 监听器、Filter 过滤器
- Filter 过滤器它是  JavaEE  的规范。也就是接口
- Filter 过滤器它的作用是：拦截请求，过滤响应。
- Filter 过滤器它只关心请求的地址是否匹配，不关心请求的资源是否存在！！！

### 使用步骤



```java
//1、编写一个类去实现  Filter  接口
public class AdminFilter implements Filter { 
  /*** doFilter */
// 2、实现过滤方法  doFilter()
@Override
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest; 
  HttpSession session = httpServletRequest.getSession();
	Object user = session.getAttribute("user"); //        null
	if (user == null) {
	servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
	return; 
  } else {
						filterChain.doFilter(servletRequest,servletResponse); 
  			}
		} 
}
```



3、到  web.xml  中去配置  Filter 的拦截路径

```xml
<!--  filter 标签用于配置一个Filter  过滤器      -->
<filter>
	<!-- 给   filter  起一个别名     -->
	<filter-name>AdminFilter</filter-name> 
	<!-- 配置   filter  的全类名     -->
	<filter-class>com.atguigu.filter.AdminFilter</filter-class> 
</filter>
<filter-mapping>
	<filter-name>AdminFilter</filter-name> 
	<!--url-pattern-->
	<url-pattern>/admin/*</url-pattern>
</filter-mapping>
```

### 生命周期

- 1、构造器方法
- 2、init  初始化方法
  - 第  1，2  步，在  web 工程启动的时候执行（Filter  已经创建）
- 3、doFilter 过滤方法
  - 第  3  步，每次拦截到请求，就会执行
- 4、destroy 销毁
  - 第  4  步，停止  web  工程的时候，就会执行（停止  web  工程，也会销毁  Filter  过滤器）

### FilterConfig 类

- FilterConfig  类见名知义，它是  Filter  过滤器的配置文件类。

- Tomcat 每次创建  Filter  的时候，也会同时创建一个  FilterConfig  类，这里包含了  Filter 配置文件的配置信息。 FilterConfig  类的作用是获取  filter 过滤器的配置内容

  - 获取  Filter  的名称    filter-name 的内容

  - 获取在  Filter 中配置的  init-param  初始化参数

  - 获取  ServletContext  对象

  - ```java
    public void init(FilterConfig filterConfig) throws ServletException {
    	System.out.println("filter-name的值是：" + filterConfig.getFilterName());
    	System.out.println("初始化参数  username的值是："+filterConfig.getInitParameter("username")); 
    	System.out.println("初始化参数  url的值是：" + filterConfig.getInitParameter("url"));
    	System.out.println(filterConfig.getServletContext()); }
    }
    ```

  - ```xml
    web.xml配置
    <filter>
    	<filter-name>AdminFilter</filter-name> 
      <filter-class>com.atguigu.filter.AdminFilter</filter-class> 
      <init-param>
    		<param-name>username</param-name> 
      	<param-value>root</param-value>
    	</init-param> 
      <init-param>
    		<param-name>url</param-name>
    		<param-value>jdbc:mysql://localhost3306/test</param-value> 
      </init-param>
    </filter>
    ```

## Listener

### 概述

* Listener 表示监听器，是 JavaWeb 三大组件(Servlet、Filter、Listener)之一。

* 监听器可以监听就是在 `application`，`session`，`request` 三个对象创建、销毁或者往其中添加修改删除属性时自动执行代码的功能组件。

  request 和 session 我们学习过。而 `application` 是 `ServletContext` 类型的对象。`ServletContext` 代表整个web应用，在服务器启动的时候，tomcat会自动创建该对象。在服务器关闭时会自动销毁该对象。

### 分类

| 监听器分类     | 名称                            | 作用                                                 |
| -------------- | ------------------------------- | ---------------------------------------------------- |
| ServletContext | ServletContextListener          | 用来监听 ServletContext 域对象的创建和销毁的监听器； |
|                | servletContextAttributeListener | 对ServletContext对象中属性的监听（增删改属性)        |
| session监听    | HttpSessionListener             | 对Session对象的整体状态的监听（创建、销毁)           |
|                | HttpSessionAttributeListener    | 对Session对象中的属性监听（增删改属性)               |
|                | HttpsessionBindingListener      | 监听对象于Session的绑定和解除                        |
|                | HttpSessionActivationListener   | 对Session数据的钝化和活化的监听                      |
| Request监听    | servletRequestListener          | 对Request对象进行监听（创建、销毁)                   |
|                | ServletRequestAttributeListener | 对Request对象中属性的监听（增删改属性)               |

这里面只有 `ServletContextListener` 这个监听器后期我们会接触到，`ServletContextListener` 是用来监听 `ServletContext` 对象的创建和销毁。

`ServletContextListener` 接口中有以下两个方法

* `void contextInitialized(ServletContextEvent sce)`：`ServletContext` 对象被创建了会自动执行的方法
* `void contextDestroyed(ServletContextEvent sce)`：`ServletContext` 对象被销毁时会自动执行的方法

- ServletContextListener` 监听器

  * 定义一个类，实现`ServletContextListener` 接口

  * 重写所有的抽象方法

  * 使用 `@WebListener` 进行配置


代码如下：

```java
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //加载资源
        System.out.println("ContextLoaderListener...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //释放资源
    }
}
```

启动服务器，就可以在启动的日志信息中看到 `contextInitialized()` 方法输出的内容，同时也说明了 `ServletContext` 对象在服务器启动的时候被创建了。


# SSM

## 介绍

### SSM:spring+springmvc+mybatis

- spring体系

	- Spring是分层的Java SE/EE应用full-stack轻量级开源框架，以IoC和AOP为内核。

		- loC ( Inverse Of Control :反转控制)
		- AOP ( Aspect Oriented Programming :面向切面编程)

	- 提供了展现层SpringMVC和持久层Spring JDBCTemplate 以及业务层事务管理等众多的企业级应用技术，还能整合开源世界众多著名的第三方框架和类库，逐渐成为使用最多的JavaEE企业应用开源框架。

		- 分层设计思路

			- 三层架构

				- DAO(Data Access Object) 

					- 主要是对非原始数据（数据库或者文本文件等存放数据的形式）的操作层，而不是指原始数据，也就是说，是对数据库的操作，而不是数据，具体为业务逻辑层或表示层提供数据服务。

				- service

					- 主要是针对具体的问题的操作，也可以理解成对数据层的操作，对数据业务逻辑处理，如果说数据层是积木，那逻辑层就是对这些积木的搭建。

				- controller

					- 主要表示WEB方式。如果逻辑层相当强大和完善，无论表现层如何定义和更改，逻辑层都能完善地提供服务。

			- MVC

				- model

					- M即model模型是指模型表示业务规则。在MVC的三个部件中，模型拥有最多的处理任务。被模型返回的数据是中立的，模型与数据格式无关，这样一个模型能为多个视图提供数据，由于应用于模型的代码只需写一次就可以被多个视图重用，所以减少了代码的重复性。

				- View

					- V即View视图是指用户看到并与之交互的界面。比如由html元素组成的网页界面，或者软件的客户端界面。MVC的好处之一在于它能为应用程序处理很多不同的视图。在视图中其实没有真正的处理发生，它只是作为一种输出数据并允许用户操作的方式。

				- controller

					- C即controller控制器是指控制器接受用户的输入并调用模型和视图去完成用户的需求，控制器本身不输出任何东西和做任何处理。它只是接收请求并决定调用哪个模型构件去处理请求，然后再确定用哪个视图来显示返回的数据。

	- EJB（Enterprise Java Beans）

		- EJB (Enterprise Java Beans) 是基于分布式事务处理的企业级应用程序的组件。Sun公司发布的文档中对EJB的定义是：EJB是用于开发和部署多层结构的、分布式的、面向对象的Java应用系统的跨平台的构件体系结构。

			- 分布式事务(Distributed Transaction)

				- 指事务的参与者、支持事务的服务器、资源服务器以及事务管理器分别位于不同的分布式系统的不同节点之上

	- 优势

		- 方便解耦，简化开发

			- 通过Spring提供的loC容器，可以将对象间的依赖关系交由Spring进行控制，避免硬编码所造成的过度耦合。用户也不必再为单例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用。

		- AOP编程的支持

			- 通过Spring的AOP功能，方便进行面向切面编程，许多不容易用传统OOP实现的功能可以通过AOP轻松实现。

		- 声明式事务的支持

			- 可以将我们从单调烦闷的事务管理代码中解脱出来，通过声明式方式灵活的进行事务管理，提高开发效率和质量。

		- 方便程序的测试

			- 可以用非容器依赖的编程方式进行几乎所有的测试工作，测试不再是昂贵的操作，而是随手可做的事情。

		- 方便集成各种优秀框架

			- Spring对各种优秀框架(Struts、Hibernate、Hessian、Quartz等）的支持。

		- 降低JavaEE API的使用难度

			- Spring对JavaEE API(如JDBC、JavaMail、远程调用等）进行了薄薄的封装层，使这些API的使用难度大为降低。

		- Java源码是经典学习范例

			- Spring的源代码设计精妙、结构清晰、匠心独用，处处体现着大师对Java设计模式灵活运用以及对Java技术的高深造诣。它的源代码无意是Java技术的最佳实践的范例。

	- Spring Framework Runtime

		-  Data Access/Integration（数据访问／集成）

			- 数据访问/集成层包括 JDBC、ORM、OXM、JMS 和 Transactions 模块，具体介绍如下。
			- JDBC 模块：提供了一个 JDBC 的抽象层，大幅度减少了在开发过程中对数据库操作的编码。
			- ORM 模块：对流行的对象关系映射 API，包括 JPA、JDO、Hibernate 和 iBatis 提供了的集成层。
			- OXM 模块：提供了一个支持对象/XML 映射的抽象层实现，如 JAXB、Castor、XMLBeans、JiBX 和 XStream。
			- JMS 模块：指 Java 消息服务，包含的功能为生产和消费的信息。
			- Transactions 事务模块：支持编程和声明式事务管理实现特殊接口类，并为所有的 POJO。

		- Web 模块

			- Spring 的 Web 层包括 Web、Servlet、Struts 和 Portlet 组件，具体介绍如下。
			- Web 模块：提供了基本的 Web 开发集成特性，例如多文件上传功能、使用的 Servlet 监听器的 IoC 容器初始化以及 Web 应用上下文。
			- Servlet模块：包括 Spring 模型—视图—控制器（MVC）实现 Web 应用程序。
			- Struts 模块：包含支持类内的 Spring 应用程序，集成了经典的 Struts Web 层。
			- Portlet 模块：提供了在 Portlet 环境中使用 MV C实现，类似 Web-Servlet 模块的功能。

		- Core Container（核心容器）

			- Spring 的核心容器是其他模块建立的基础，由 Beans 模块、Core 核心模块、Context 上下文模块和 Expression Language 表达式语言模块组成，具体介绍如下。
			- Beans 模块：提供了 BeanFactory，是工厂模式的经典实现，Spring 将管理对象称为 Bean。
			- Context 上下文模块：建立在核心和 Beans 模块的基础之上，它是访问定义和配置任何对象的媒介。ApplicationContext 接口是上下文模块的焦点。
			- Core 核心模块：提供了 Spring 框架的基本组成部分，包括 IoC 和 DI 功能。
			- Expression Language 模块：是运行时查询和操作对象图的强大的表达式语言。

		- Spring的其他模块还有 AOP、Aspects、Instrumentation 以及 Test 模块，具体介绍如下。

			- AOP 模块：提供了面向切面编程实现，允许定义方法拦截器和切入点，将代码按照功能进行分离，以降低耦合性。
			- Aspects 模块：提供与 AspectJ 的集成，是一个功能强大且成熟的面向切面编程（AOP）框架。
			- Instrumentation 模块：提供了类工具的支持和类加载器的实现，可以在特定的应用服务器中使用。
			- Test 模块：支持 Spring 组件，使用 JUnit 或 TestNG 框架的测试。

## Spring

### IOC

- 控制反转，把创建对象过程交给Spring进行管理
- 目的：解耦合

#### 底层原理

xml 解析、工厂模式、反射

```java
IOC过程
第一步xm1配置文件,配置创建的对象
<bean id=" user” class="User"></bean> //进一步降低耦合度
第二步有service类和dao类，创建工厂类
第三步解析xml文件，获得配置的全类名，通过Class.forname（）获得类，再通过Class实例的newInstance（）获得对象，将对象返回给调用者。
class UserFactory {
public static UserDao get Dao() {
	String classValue = class属性值; //1 xml解析
	Class clazz = Class. forName (classValue); //2 通过反射创建对象
	return (UserDao) clazz. newInstance() ;
	}
}
```

##### BeanFactory 接口

- IOC 思想基于  IOC 容器完成，IOC 容器底层就是对象工厂
- Spring 提供  IOC 容器实现两种方式：（两个接口）
  - BeanFactory：IOC 容器基本实现，是  Spring 内部的使用接口，不提供开发人员进行使用
    - 加载配置文件时候不会创建对象，在获取对象（使用）才去创建对象
  - ApplicationContext：BeanFactory 接口的子接口，提供更多更强大的功能，一般由开发人 员进行使用
    - 加载配置文件时候就会把在配置文件对象进行创建
    - FileSystemXmlApplicationContext实现类
      - 使用时传入文件所在的路径，从盘符开始
    - ClassPathXmlApplicationContext 
      - 传入文件所在的类路径，classpath指的是类路径，也就是编译之后的target文件夹下的WEB-INF/class文件夹
      - resources文件夹存放的是各种配置文件，当项目被编译时resources下的所有配置文件均被放在 WEB-INF/class文件夹下。也就是类路径下。
    - AnnotationConfigApplicationContext
    
      - 当使用注解配置容器对象时，需要使用此类来创建spring容器。它用来读取注解。
  

#### Spring配置文件（XML）

Bean标签

- 用于配置对象交由Spring来创建。

- 基本配置

  - 默认情况下它调用的是类中的无参构造函数，如果没有无参构造函数则不能创建成功
  - 基本属性:

  	- id: Bean实例在Spring容器中的唯一标识
  	
  	- class: Bean的全限定名称
  	
  	- ```xml
  	  <bean id="user" class="User"></bean>
  	  ```

##### 范围配置

- scope:指对象的作用范围

- ```xml
  <bean id="user" class="User" scope="singleton"></bean>
  ```

  - 值

  	- singleton 

  		- 默认值，单例的

  			- Bean的实例化个数:1个
  			- Bean的实例化时机:当Spring核心文件被加载时，实例化配置的Bean实例Bean的生命周期:

  				- 对象创建:当应用加载，创建容器时，对象就被创建了
  				- 对象运行:只要容器在，对象一直活着
  				- 对象销毁:当应用卸载，销毁容器时，对象就被销毁了

  	- prototype

  		- 多例的

  			- Bean的实例化个数:多个
  			- Bean的实例化时机:当调用getBean(方法时实例化Bean

  				- 对象创建:当使用对象时，创建新的对象实例
  				- 对象运行:只要对象在使用中，就一直活着
  				- 对象销毁:当对象长时间不用时，被Java的垃圾回收器回收了

  	- request

  		- WEB项目中，Spring创建一个Bean 的对象，将对象存入到request域中

  	- session

  		- WEB项目中，Spring创建一个 Bean 的对象，将对象存入到session域中

  	- global session

  		- WEB 项目中，应用在Portlet 环境，如果没有Portlet环境那么globalSession相当于session

##### 生命周期配置

- ```xml
  <bean id="user" class="User" init-method="add" destroy-method="add"></bean>
  ```

- init-method:指定类中的初始化方法名称

- destroy-method:指定类中销毁方法名称

- bean实例化

  - 无参构造方法实例化心

  - 工厂静态方法实例化

    - 对于静态方法需要设置

    	- factory-method=“静态方法名”
    	
    	- ```xml
    	  <bean id="user" class="User" factory-method="get"></bean>
    	  ```

  - 工厂实例方法实例化

  	- 对于动态方法需要设置

  		- factory-bean=“beanid” 
  		  factory-method=“实例方法名”
  		
  		- ```xml
  		  <bean id="factory" class="com.itheima.factory.DynamicFactory"></bean>
  		  <bean id="userDao" factory-bean="factory" factory-method="getUserDao" />
  		  ```
  		
  		  

##### 依赖注入

###### 引用数据类型

- 概念

	- 依赖注入(Dependency Injection) :它是Spring框架核心IOC的具体实现。
	- 在编写程序时，通过控制反转，把对象的创建交给了Spring，但是代码中不可能出现没有依赖的情况。IOC解耦只是降低他们的依赖关系，但不会消除。例如:业务层仍会调用持久层的方法。
	- 那这种业务层和持久层的依赖关系，在使用Spring之后，就让Spring来维护了。简单的说，就是坐等框架把持久层对象传入业务层，而不用我们自己去获取。

- 方式

  - set方法

    - class

    	- 在当前类中设置被依赖的类作为属性，给属性添加set方法

    - spring-xml

      - ```xml
        <bean id="id1" c1ass="类1的全限定名"></bean>
        <bean id="id2" class="类2的全限定名">
          <property name="属性名" ref="id1"</property>
        </bean>
        ```

        

    - P命名空间

    	- P命名空间注入本质也是set方法注入，但比起上述的set方法注入更加方便，主要体现在配置文件中
    	- 首先，需要引入P命名空间:

    	  - ```xml
    	    xmlns:p="http://www.springframework.org/schema/p"
    	    ```

    	- 其次，需要修改注入方式
    	
    		- ```xml
    		  <bean id="id2" class="类2的全限定名" p:属性名-ref="id1" />
    		  ```

  - 构造方法

    - class

    	- 在当前类中设置被依赖的类作为属性1，创建当前类的构造方法，在构造方法给属性1赋值

    - Xml

      - ```xml
        <bean id="id2" class="类2的全限定名">
        		<constructor-arg name="属性1" ref="id1"</constructor-arg>
        </bean>
        ```

        

###### 普通数据类型

- 类似于引用数据类型，但不使用ref，而使用value

- ```xml
  <bean id="id1" class="类1的全限定名">
  		<property name="name" value="张三"></property> 
    	<property name="age" value="15"></property>
  </bean>
  ```

###### 集合数据类型

- list

  - list，不使用ref，而是在<property></property>中加入<list>标签，再添加多个value标签，如果是引用则需指定value-type

  - ```xml
    <bean id="id1" class="类1的全限定名">
    	<property name="strList">
    		<list>                    <list value-type="User">
    			<value>aaa</value>
          <value>bbb</value> 
          <value>ccc</value>
    		</list> 
      </property>
    </bean>
    ```

    

- map

	- list，不使用ref，而是在<property></property>中加入<map>标签，再添加多个entry标签，entry包括key和value属性
	
	- ```xml
	  <bean id="id1" class="类1的全限定名">
	  	<property name="userMap">
	  		<map>                                     可以指明key-type 和value type表示引用。
	  			<entry key="user1" value-ref="u1"/>     使用key-ref和value-ref表示引用。
	        <entry key="user2" value-ref="u2"/>
	  		</map> 
	    </property>
	  </bean>
	  ```
	
	  

##### 分模块开发

- 引入其他配置文件

- 实际开发中，Spring的配置内容非常多，这就导致Spring配置很繁杂且体积很大，所以，可以将部分配置拆解到其他配置文件中，而在Spring主配置文件通过import标签进行加载

- ```xml
  <import resource="applicationcontext-xxx. xml" />
  ```

##### 配置数据源

- 数据源(连接池)是提高程序性能如出现的
  事先实例化数据源，初始化部分连接资源
  使用连接资源时从数据源中获取
  使用完毕后将连接资源归还给数据源

- 数据源的开发步骤

	- 导入数据源的坐标和数据库驱动坐标
	- 创建数据源对象
	- 设置数据源的基本连接数据
	- 使用数据源获取连接资源和归还连接资源

- ```java
  @Test
  //测试手动创建druid 数据源
  public void test2() throws Exception {
      DruidDataSource dataSource = new DruidDataSource() ;
      cataSource.setDriverClassName("com. mysq1. jdbc.Driver");
      dataSource.setUr1("jdbc : mysq1:/l localhost:3306/test") ;
      cataSource.setUsername("root");
      cataSource.setPassword("root");
      DruidPooledConnection connection = dataSource.getConnection();
      System.out.println(connection);
      connection.close();
  }
  ```

- Spring 配置

- ```xml
  抽取jdbc配置文件
  applicationContext.xml加载jdbc.properties配置文件获得连接信息。 首先，需要引入context命名空间和约束路径：
  	命名空间：xmlns:context="http://www.springframework.org/schema/context"
  	约束路径：xsi:schemaLocation="http://www.springframework.org/schema/context 		  
  															http://www.springframework.org/schema/context/spring-context.xsd"
  //加载配置文件
  <context:property-placeholder location="classpath:jdbc.properties"/> 
  使用配置文件
  <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
  	<property name="driverClass" value="${jdbc.driver}"/> 
    <property name="jdbcUrl" value="${jdbc.url}"/>
    <property name="user" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/> 
  </bean>
  ```

#### 注解开发

- Spring是轻代码而重配置的框架，配置比较繁重，影响开发效率，所以注解开发是一种趋势，注解代替xml配置文件可以简化配置，提高开发效率。

  - 使用注解进行开发时，需要在applicationContext.xml中配置组件扫描，作用是指定哪个包及其子包下的Bean需要进行扫描以便识别使用注解配置的类、字段和方法。

  - ```xml
    <context:component-scan base-package=" 包名1"></context: component-scan> //注解扫描，扫描包名1和子包下的注解
    ```

| 注解                     | 说明                                                         |
| ------------------------ | ------------------------------------------------------------ |
| **原始注解**             |                                                              |
| 类实例化                 |                                                              |
| @Component               | 使用在类上用于实例化Bean                                     |
| @Controller              | 使用在web层类上用于实例化Bean                                |
| @Service                 | 使用在service层类上用于实例化Bean                            |
| @Repository              | 使用在dao层类上用于实例化Bean                                |
| 依赖注入                 |                                                              |
| @Autowired               | 使用在字段上用于根据类型依赖注                               |
| @Qualifier               | 结合@Autowired—起使用用于根据名称进行依赖注入                |
| @Resource                | 相当于@Autowired+@Qualifier，按照名称进行注入                |
| 普通属性                 |                                                              |
| @Value                   | 注入普通属性                                                 |
| 范围配置                 |                                                              |
| @Scope                   | 标注Bean的作用范围                                           |
| 生命周期配置             |                                                              |
| @PostConstruct           | 使用在方法上标注该方法是Bean的初始化方法                     |
| @PreDestroy              | 使用在方法上标注该方法是Bean的销毁方法                       |
| **新注解**               |                                                              |
| 配置类                   |                                                              |
| @Configuration           | 用于指定当前类是一个Spring配置类，当创建容器时会从该类上加载注解 |
| 非自定义的Bean的配置     |                                                              |
| @Bean                    | 使用在方法上，标注将该方法的返回 值存储 到Spring 容器中      |
| 加载properties文件的配置 |                                                              |
| @PropertySource          | 用于加载.properties文件中的配置                              |
| 组件扫描                 |                                                              |
| @ComponentScan           | 用于指定Spring在初始化容器时要扫描的包。                     |
| 引入其他文件             |                                                              |
| @lmport                  | 用于导入其他配置类                                           |

    ```java
    @Configuration
    @ComponentScan("com.itheima")
    @PropertySource("classpath:jdbc.properties")
    @Import({DataSourceConfiguration.class})
    public class SpringConfiguration {
    	@Value("${jdbc.driver}") 
      private String driver;
      
      @Value("${jdbc.url}") 
      private String url;
      
    	@Value("${jdbc.username}") 
      private String username; 
      
      @Value("${jdbc.password}") 
      private String password;
      
      @Bean(name="dataSource")
    	public DataSource getDataSource() throws PropertyVetoException {
    		ComboPooledDataSource dataSource = new ComboPooledDataSource();
    		dataSource.setDriverClass(driver);
    		dataSource.setJdbcUrl(url);
    		dataSource.setUser(username);
    		dataSource.setPassword(password);
    		return dataSource; 
      }
    }
    ```

##### 集成Junit

- 步骤

	- 导入spring集成Junit的坐标
	
	- 使用@Runwith注解替换原来的运行期
	
	- 使用@contextconfiguration指定配置文件或配置类使用
	
	- @Autowired注入需要测试的对象
	
	- 创建测试方法进行测试
	
	- ```java
	  @RunWith(SpringJUnit4ClassRunner.class)
	  @ContextConfiguration(classes = {SpringConfiguration.class}) 
	  public class SpringJunitTest {
	  	@Autowired
	  	private UserService userService;
	  }
	  ```
	
	  

##### 集成Web环境

- ApplicationContext获取方式

	- 应用上下文对象是通过new ClasspathXmIApplicationContext(spring配置文件)方式获取的，但是每次从容器中获得Bean时都要编写newClasspathXmlApplicationContext(spring配置文件)，这样的弊端是配置文件加载多次，应用上下文对象创建多次。
	- 在Web项目中，可以使用ServletContextListener监听Web应用的启动，我们可以在Web应用启动时，就加载Spring的配置文件，创建应用上下文对象ApplicationContext，在将其存储到最大的域servletContext域中，这样就可以在任意位置从域中获得应用上下文ApplicationContext对象了。

- 步骤

	- 配置ContextLoaderListener监听器
	
	- 使用WebApplicationContextUtils获得应用上下文
	
	- ```xml
	  1.在pom.xml导入Spring集成web的坐标
	  <dependency>
	  	<groupId>org.springframework</groupId> 
	    <artifactId>spring-web</artifactId> 
	    <version>5.0.5.RELEASE</version>
	  </dependency>
	  2.在web.xml中配置监听器和配置文件的路径，作为servletcontext的属性
	  <!--Spring 的监听器-->
	  <listener>
	    <listener-class>org.springframework.web.context.ContextLoaderListener </listener-class>
	  </listener>
	  <context-param>
	  	<param-name>contextConfigLocation</param-name>
	  	<param-value>classpath:applicationContext.xml</param-value> 
	  </context-param>
	  3.通过工具获得应用上下文对象
	  ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext); 
	  Object obj = applicationContext.getBean("id");
	  ```
	
	  

### AOP

#### 介绍

- AOP为Aspect Oriented Programming的缩写，意思为面向切面编程，是通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。

- 面向切面，不修改源代码进行功能增强

- AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的-个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

	- OOP：Object Oriented Programming

- 作用:在程序运行期间，在不修改源码的情况下对方法进行功能增强

- 优势:减少重复代码，提高开发效率，并且便于维护


#### 概念

- 代理模式

  - 代理模式是指，为其他对象提供一种代理以控制对这个对象的访问。在某些情况下，一个对象不适合或者不能直接引用另一个对象，而代理对象可以在客户类和目标对象之间起到中介的作用。
  - 使用代理模式的作用

    - 功能增强:在你原有的功能上，增加了额外的功能。新增加的功能，叫做功能增强。
    - 控制访问:代理类不让你访问目标，例如商家不让用户访问厂家。

- Spring的AOP实现底层就是对上面的动态代理的代码进行了封装，封装后我们只需要对需要关注的部分进行代码编写，并通过配置的方式完成指定目标的方法增强。

- 

- | 术语              | 说明                                                         |

| ----------------- | ------------------------------------------------------------ |
| Target(目标对象)  | 代理的目标对象                                               |
| Joinpoint(连接点) | 所谓连接点是指那些被拦截到的点。在spring中,这些点指的是方法，因为spring只支持方法类型的连接点 |
| Pointcut(切入点)  | 所谓切入点是指我们要对哪些Joinpoint进行拦截的定义            |
| Advice(通知/增强) | 所谓通知是指拦截到Joinpoint之后所要做的事情就是通知          |
| Weaving (织入)    | 是指把增强应用到目标对象来创建新的代理对象的过程。spring采用动态代理织入，而AspectJ采用编译期织入和类装载期织入 |
| Aspect(切面)      | 是切入点和通知(引介)的结合                                   |
| Proxy (代理)      | 一个类被AOP织入增强后，就产生一个结果代理类                  |

#### 实现代理的方式

###### 静态代理

- 代理类是自己手工实现的，自己创建一个java类，表示代理类。
- 同时你所要代理的目标类是确定的。
- 优点

	- 实现简单
	- 容易理解

- 缺点

	- 当目标类增加了，代理类可能也需要成倍的增加。代理类数量过多。
	- 当你的接口中功能增加了，或者修改了，会影响众多的实现类，广厂家类，代理都需要修改。影响比较多。

- 代理类完成的功能:

	- 目标类中方法的调用
	- 功能增强

###### 动态代理

- 介绍

  - 在静态代理中目标类很多时候，可以使用动态代理，避免静态代理的缺点。
  - 动态代理中目标类即使很多，1）代理类数量可以很少，）当你修改了接口中的方法时，不会影响代理类。
  - 在程序执行过程中，使用idk的反射机制，创建代理类对象，并动态的指定要代理目标类。
  - 换句话说:动态代理是一种创建java对象的能力，让你不用创建raoBao类，就能创建代理类对象。

- 常用的动态代理技术

	- JDK代理:基于接口的动态代理技术

	  - 反射包java.lang.reflect ，里面有三个类: InvocationHandler ，Method，Proxy.

	  	- Method method:目标类中的方法，jdk提供method对象的0bject[] args:目标类中方法的参数，jdk提供的。
	  	- InvocationHandler 

	  	  - public Object invoke (0bject proxy,Method method，object[] args)

	  	  	- 参数:

	  	  		- object proxy : jdk创建的代理对象，无需赋值。
	  	  		- invoke () :表示代理对象要执行的功能代码。你的代理类要完成的功能就写在invoke ()方法中。
	  	  		- 0bject[] args:目标类中方法的参数，jdk提供的。

	  	  - 使用

	  	  	- 1.创建类实现接口InvocationHandler
	  	  	
	  	  	- 2.重写invoke (）方法，把原来静态代理中代理类要完成的功能，写在这
	  	  	
	  	  	- ```java
	  	  	  //1创建目标类接口
	  	  	  public interface TargetInterface {
	  	  	  	public void method(); 
	  	  	  }
	  	  	  //2目标类实现接口
	  	  	  public class Target implements TargetInterface {
	  	  	  	@Override
	  	  	  	public void method() {
	  	  	  	System.out.println("Target running...."); }
	  	  	  }
	  	  	  // 3 动态代理
	  	  	  Target target = new Target(); //创建目标对象 
	  	  	  //创建代理对象
	  	  	  TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(target.getClass() .getClassLoader(),
	  	  	                                                                   target.getClass().getInterfaces(),
	  	  	                                                                   new InvocationHandler() {
	  	  	  		@Override
	  	  	  		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	  	  	  			System.out.println("前置增强代码..."); 
	  	  	        Object invoke = method.invoke(target, args); 
	  	  	        System.out.println("后置增强代码..."); 
	  	  	        return invoke;
	  	  	  		} 	
	  	  	                                                                   }
	  	  	  );
	  	  	  proxy.method();
	  	  	  ```
	  	  	
	  	  	  
	  	
	  	- 反射，Method类，表示方法。类中的方法。通过vethod可以执行某个方法。
	  	
	  		- method . invoke (目标对象，方法的参数)
	  	
	  			- 说明:method.invoke ()）就是用来执行目标方法的
	  	
	  	- 3) Proxy类:核心的对象，创建代理对象。之前创建对象都是new类的构造方法()
	  	
	  		- public static object newProxyInstance (classLoader loader,Class<?>[]interfaces ,InvocationHandler h)
	  	
	  			- 1. ClassLoader loader类加载器，负责向内存中加载对象的。使用反射获取对象的classLoader类a, a.getcalss ().getclassLoader()，目标对象的类加载器
	  			- 2. class<?>[] interfaces:接口，目标对象实现的接口，也是反射获取的。
	  			- 3. InvocationHandler h :我们自己写的，代理类要完成的功能。
	  			- 返回值:就是代理对象
	
	- cglib代理:基于父类的动态代理技术
	
		- cglib是第三方的工具库，创建代理对象。
		
		- cglib的原理是继承cglib通过继承目标类，创建它的子类，在子类中重写父类中同名的方法，实现功能的修改。
	
		- 因为cglib是继承，重写方法，所以要求目标类不能是final的，方法也不能是final的。cglis的要求目标类比较宽松，只要能继承就可以了。cglib在很多的框架中使用，
		
		  - mybatis , spring框架中都有使用。
		
		- ```java
		  //1.创建目标类
		  public class Target {
		  	public void method() {
		  	System.out.println("Target running...."); }
		  }
		  //2.配置动态代理
		  Target target = new Target(); //创建目标对象  
		  Enhancer enhancer = new Enhancer();   // 创建增强器
		  enhancer.setSuperclass(Target.class); // 设置父类
		  enhancer.setCallback(new MethodInterceptor() { //设置回调
		  @Override
		  	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		  		System.out.println("前置代码增强....");
		  		Object invoke = method.invoke(target, objects); System.out.println("后置代码增强....");
		  		return invoke; 
		    }
		  });
		  Target proxy = (Target) enhancer.create(); //创建代理对象
		  //3.测试               ,
		  proxy.method();
		  ```

#### 使用

- 1.需要编写的内容

	- 编写核心业务代码（目标类的目标方法)编写切面类，切面类中有通知(增强功能方法)
	- 在配置文件中，配置织入关系，即将哪些通知与哪些连接点进行结合

- 2.AOP技术实现的内容

	- Spring框架监控切入点方法的执行。一旦监控到切入点方法被运行，使用代理机制，动态创建目标对象的代理对象，根据通知类别，在代理对象的对应位置，将通知对应的功能织入，完成完整的代码逻辑运行。

- 3.AOP底层使用哪种代理方式

	- 在spring中，框架会根据目标类是否实现了接口来决定采用哪种动态代理的方式

##### 基于XML的开发

###### 步骤

1. 导入 AOP 相关坐标
2. 创建目标接口和目标类（内部有切点）
3. 创建切面类（内部有增强方法）
4. 将目标类和切面类的对象创建权交给 spring
5. 在 applicationContext.xml 中配置织入关系
6. 测试代码

- ```xml
  //1. 在pom.xml导入 AOP 相关坐标
  //context
  <dependency>
  	<groupId>org.springframework</groupId> 
    <artifactId>spring-context</artifactId> 
    <version>5.0.5.RELEASE</version>
  </dependency>
  //aspectj
  <dependency>
  	<groupId>org.aspectj</groupId>
  	<artifactId>aspectjweaver</artifactId> 
    <version>1.8.13</version>
  </dependency>
  ```

- ```java
  //2.创建目标接口
  public interface TargetInterface {
  	public void method(); 
  }
  //3.创建目标类，实现目标接口
  public class Target implements TargetInterface {
  	@Override
  	public void method() {
  	System.out.println("Target running...."); 
    }
  }
  //4.创建切面类（内部有增强方法）
  public class MyAspect { //前置增强方法
  	public void before(){
  	System.out.println("前置代码增强.....");
  	}
  }
  ```

- ```xml
  //5.在applicationContext.xml中，将目标类和切面类的对象创建权交给 spring
  <bean id="target" class="com.aop.Target"></bean>
  <bean id="myAspect" class="com.aop.MyAspect"></bean>
  //6 导入aop命名空间
  <beans xmlns="http://www.springframework.org/schema/beans"
  			 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:aop="http://www.springframework.org/schema/aop"  //切面
         xsi:schemaLocation="http://www.springframework.org/schema/context
                             http://www.springframework.org/schema/context/spring-context.xsd
                             http://www.springframework.org/schema/aop  //切面
                             http://www.springframework.org/schema/aop/spring-aop.xsd //切面
                             http://www.springframework.org/schema/beans
                             http://www.springframework.org/schema/beans/spring-beans.xsd">
  </beans>
  //7. 配置织入关系 
  <aop:config>
    //设置切面对象
    <aop:aspect ref="myAspect"> 
      //设置前置增强，分别设置，增强方法和切入点
      <aop:before method="before" pointcut="execution(public void com.aop.Target.method())"></aop:before>
  	</aop:aspect> 
  </aop:config>
  //8. 
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:applicationContext.xml") 
  public class AopTest {
  	@Autowired
  	private TargetInterface target; @Test
  	public void test1(){
  	target.method(); 
  	}
  }
  ```

  

###### 通知的类型

```xml
<aop:通知类型 method=“切面类中方法名” pointcut=“切点表达式"></aop:通知类型>
```

| 名称         | 标签                    | 说明                                                         |
| ------------ | ----------------------- | ------------------------------------------------------------ |
| 前置通知     | `<aop:before>`          | 用于配置前置通知。指定增强的方法在切入点方法之前执行         |
| 后置通知     | `<aop:after-returning>` | 用于配置后置通知。指定增强的方法在切入点方法之后执行         |
| 环绕通`知`   | `<aop:around>`          | 用于配置环绕通知。指定增强的方法在切入点方法之前和之后都 执行 |
| 异常抛出通知 | `<aop:throwing>`        | 用于配置异常抛出通知。指定增强的方法在出现异常时执行         |
| 最终通知     | `<aop:after`>           | 用于配置最终通知。无论增强方式执行是否有异常都会执行         |

###### 切点表达式

- execution([修饰符]返回值类型包名.类名.方法名(参数))

- 访问修饰符可以省略

- 返回值类型、包名、类名、方法名可以使用星号*  代表任意

- 包名与类名之间一个点 . 代表当前包下的类，两个点 .. 表示当前包及其子包下的类

- 参数列表可以使用两个点 .. 表示任意个数，任意类型的参数列表 例如

  - ```xml
    execution(public void com.itheima.aop.Target.method())
    execution(void com.itheima.aop.Target.*(..))
    execution(* com.itheima.aop.*.*(..))
    execution(* com.itheima.aop..*.*(..))
    execution(* *..*.*(..))
    ```

-  切点表达式的抽取

  - 当多个增强的切点表达式相同时，可以将切点表达式进行抽取，在增强中使用 pointcut-ref 属性代替 pointcut 属性来引用抽 取后的切点表达式。

  - ```
    <aop:config>
      //设置切面对象
      <aop:aspect ref="myAspect"> 
      	<aop:pointcut id="myPointcut" expression="execution(* com.itheima.aop.*.*(..))"/>
        //设置前置增强，分别设置，增强方法和切入点
        <aop:before method="before" pointcut-ref="myPointcut"></aop:before>
    	</aop:aspect> 
    </aop:config>
    ```

    

##### 基于注解的开发

###### 步骤

- 创建目标接口和目标类（内部有切点)

- 创建切面类(内部有增强方法)

- 将目标类和切面类的对象创建权交给spring

- 在切面类中使用注解配置织入关系

- ```java
  //2.创建目标接口
  public interface TargetInterface {
  	public void method(); 
  }
  //3.创建目标类，实现目标接口
  public class Target implements TargetInterface {
  	@Override
  	public void method() {
  	System.out.println("Target running...."); 
    }
  }
  //4.创建切面类（内部有增强方法）
  public class MyAspect { //前置增强方法
  	public void before(){
  	System.out.println("前置代码增强.....");
  	} 
  }
  ```

- ```java
  //5.对目标类和切面类使用@component注解，将创建权交给spring
  //6.在切面类中使用注解配置织入关系
  @Component("myAspect") 
  @Aspect
  public class MyAspect {
  		@Before("execution(* com.aop.*.*(..))") 
      public void before(){
  			System.out.println("前置代码增强.....");
  		}
  }
  ```
  
- ```xml
  //7.在applicationContext.xml中开启组件扫描和 AOP 的自动代理
  //组件扫描
  <context:component-scan base-package="com.aop"/>
  //aop的自动代理
  <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
  ```

注解

- 通知的配置语法:@通知注解(“切点表达式")

- 

- | 名称           | 注解            | 说明                                                         |

| -------------- | --------------- | ------------------------------------------------------------ |
| 前置通知       | @Before         | 用于配置前置通知。指定增强的方法在切入点方法之前执行         |
| 后置通知       | @AfterReturning | 用于配置后置通知。指定增强的方法在切入点方法之后执行         |
| 环绕通`知`     | @Around         | 用于配置环绕通知。指定增强的方法在切入点方法之前和之后都 执行 |
| 异常抛出通知   | @AfterThrowing  | 用于配置异常抛出通知。指定增强的方法在出现异常时执行         |
| 最终通知       | @After          | 用于配置最终通知。无论增强方式执行是否有异常都会执行         |
| 切点表达式抽取 | @Pointcut       | 抽取方式是在切面内定义方法，在该方法上使用@Pointcut注解然后在在增强注解中进行引用 |

- 在配置文件中开启组件扫描和AOP的自动代理     

### 声明式事务

事务的四个特性：原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation）、持续性（Durability）或永久性（Permanence）

#### 编程式

##### PlatformTransactionManager

| 方法                                                         | 说明               |
| ------------------------------------------------------------ | ------------------ |
| TransactionStatus getTransaction(TransactionDefination defination) | 获取事务的状态信息 |
| void commit(TransactionStatus status)                        | 提交事务           |
| void rollback(TransactionStatus status)                      | 回滚事务           |

- PlatformTransactionManager 是接口类型，不同的      Dao 层技术则有不同的实现类，例如：Dao 层技术是jdbc 或    mybatis 时：org.springframework.jdbc.datasource.DataSourceTransactionManager；Dao 层技术是hibernate时：org.springframework.orm.hibernate5.HibernateTransactionManager

##### TransactionDefinition

| 方法                         | 说明               |
| ---------------------------- | ------------------ |
| int getIsolationLevel()      | 获得事务的隔离级别 |
| int getPropogationBehavior() | 获得事务的传播行为 |
| int getTimeout()             | 获 得超时 时间     |
| boolean isReadOnly()         | 是否只读           |

###### 事务隔离级别

- 设置隔离级别，可以解决事务并发产生的问题，如脏读、不可重复读和虚读，更新丢失。

  - 更新丢失：两个事务都同时更新一行数据，一个事务对数据的更新把另一个事务对数据的更新覆盖了。这是因为系统没有执行任何的锁操作，因此并发事务并没有被隔离开来。
  - 脏读：一个事务读取到了另一个事务未提交的数据操作结果。这是相当危险的，因为很可能所有的操作都被回滚。
  - 不可重复读（Non-repeatable Reads）：一个事务对同一行数据重复读取两次，但是却得到了不同的结果。
  - 情况：
    - （1） 虚读：事务T1读取某一数据后，事务T2对其做了修改，当事务T1再次读该数据时得到与前一次不同的值。
    - （2） 幻读（Phantom Reads）：事务在操作过程中进行两次查询，第二次查询的结果包含了第一次查询中未出现的数据或者缺少了第一次查询中出现的数据（这里并不要求两次查询的SQL语句相同）。这是因为在两次查询过程中有另外一个事务插入数据造成的。

- | 级别                       | 说明                                                         |

| -------------------------- | ------------------------------------------------------------ |
| ISOLATION_DEFAULT          | PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别,除了default ，其它几个Spring事务隔离级别与JDBC事务隔离级别相对应 |
| ISOLATION_READ_UNCOMMITTED | 也称为读未提交（Read Uncommitted）：允许脏读取，但不允许更新丢失。如果一个事务已经开始写数据，则另外一个事务则不允许同时进行写操作，但允许其他事务读此行数据。该隔离级别可以通过“排他写锁”实现。 |
| ISOLATION_READ_COMMITTED   | 也称为读提交（Read Committed）：允许不可重复读取，但不允许脏读取。这可以通过“瞬间共享读锁”和“排他写锁”实现。读取数据的事务允许其他事务继续访问该行数据，但是未提交的写事务将会禁止其他事务访问该行。 |
| ISOLATION_REPEATABLE_READ  | 可重复读取（Repeatable Read）：禁止不可重复读取和脏读取，但是有时可能出现幻读数据。这可以通过“共享读锁”和“排他写锁”实现。读取数据的事务将会禁止写事务（但允许读事务），写事务则禁止任何其他事务。 |
| ISOLATION_SERIALIZABLE     | 序列化（Serializable）：提供严格的事务隔离。它要求事务序列化执行，事务只能一个接着一个地执行，不能并发执行。仅仅通过“行级锁”是无法实现事务序列化的，必须通过其他机制保证新插入的数据不会被刚执行查询操作的事务访问到。 |

- 隔离级别越高，越能保证数据的完整性和一致性，但是对并发性能的影响也越大。对于多数应用程序，可以优先考虑把数据库系统的隔离级别设为Read Committed。它能够避免脏读取，而且具有较好的并发性能。尽管它会导致不可重复读、幻读和第二类丢失更新这些并发问题，在可能出现这类问题的个别场合，可以由应用程序采用悲观锁或乐观锁来控制。

###### 事务传播行为

| 行为          | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| REQUIRED      | 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。一般的选择（默认值） |
| SUPPORTS      | 支持当前事务，如果当前没有事务，就以非事务方式执行（没有事务） |
| MANDATORY     | 使用当前的事务，如果当前没有事务，就抛出异常                 |
| REQUERS_NEW   | 新建事务，如果当前在事务中，把当前事务挂起。                 |
| NOT_SUPPORTED | 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起     |
| NEVER         | 以非事务方式运行，如果当前存在事务，抛出异常                 |
| NESTED        | 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行  REQUIRED 类似的操作 |
| 其他          |                                                              |
| timeout       | 默认值是-1，没有超时限制。如果有，以秒为单位进行设置         |
| read-only     | 建议查询时设置为只读                                         |

##### TransactionStatus

| 方法                       | 说明           |
| -------------------------- | -------------- |
| boolean hasSavepoint()     | 是否存储回滚点 |
| boolean isCompleted()      | 事务是否完成   |
| boolean isNewTransaction() | 是否是新事务   |
| boolean isRollbackOnly()   | 事务是否回滚   |

#### 声明式

- Spring的声明式事务顾名思义就是采用声明的方式来处理事务。这里所说的声明，就是指在配置文件中声明，用在Spring配置文件中声明式的处理事务来代替代码式的处理事务。
- 声明式事务处理的作用

  - 事务管理不侵入开发的组件。具体来说，业务逻辑对象就不会意识到正在事务管理之中，事实上也应该如此，因为事务管理是属于系统层面的服务，而不是业务逻辑的一部分，如果想要改变事务管理策划的话，也只需要在定义文件中重新配置即可

- Spring 声明式事务控制底层就是AOP。

##### 基于xml

- ```xml
  //1. 引入tx命名空间
  <beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:context="http://www.springframework.org/schema/context"
  xmlns:aop="http://www.springframework.org/schema/aop"
  xmlns:tx="http://www.springframework.org/schema/tx"        //事务管理
  xsi:schemaLocation="
  http://www.springframework.org/schema/context
  http://www.springframework.org/schema/context/spring-context.xsd
  http://www.springframework.org/schema/aop
  http://www.springframework.org/schema/aop/spring-aop.xsd
  http://www.springframework.org/schema/tx                   //事务管理
  http://www.springframework.org/schema/tx/spring-tx.xsd    //事务管理
  http://www.springframework.org/schema/beans
  http://www.springframework.org/schema/beans/spring-beans.xsd">
  
    //2. 配置数据源
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
  		<property name="driverClass" value="com.mysq1. jdbc.Driver" >
  		<property name="jdbcUr1" value="jdbc:mysq1://1ocalhost:3306/test" />
      <property name="user" value="root" >
      <property name="password" value="root" />
    </bean>
    //3. 配置事务增强
       //平台事务管理器
    <bean id="transactionManager"class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
      <property name="dataSource" ref="dataSource"></property>
  	</bean>
     //事务增强配置
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
  		<tx:attributes>
  			<tx:method name="*"/> 
      </tx:attributes>
  	</tx:advice>
        //4. 配置事务  AOP 织入
    <aop:config>
  		<aop:pointcut id="myPointcut" expression="execution(* com.itheima.service.impl.*.*(..))"/>
  		<aop:advisor advice-ref="txAdvice" pointcut-ref="myPointcut"></aop:advisor> 
    </aop:config>
  </beans>
  ```

###### 事务增强配置

```xml
<tx:advice id="txAdvice" transaction-manager="transactionManager">
	<tx:attributes>
		<tx:method name="transfer" isolation="REPEATABLE_READ" propagation="REQUIRED" timeout="-1" read-only="false"/>
	</tx:attributes>
</tx:advice>
```

- name：切点方法名称，对名称进行限制，pointcut则是声明有哪些连接点可以被加强的，取他们的并集
- isolation:事务的隔离级别
- propogation：事务的传播行为
- timeout：超时时间
- read-only：是否只读

##### 基于注解

- 步骤

  - ```java
    //1. 编写  AccoutDao
    @Repository("accountDao")
    public class AccountDaoImpl implements AccountDao {
    	@Autowired
    	private JdbcTemplate jdbcTemplate;
      
    	public void out(String outMan, double money) {
    		jdbcTemplate.update("update account set money=money-? where name=?",money,outMan);
    	}
      
    	public void in(String inMan, double money) {
    		jdbcTemplate.update("update account set money=money+? where name=?",money,inMan);
    	}
    }
    //2. 编写  AccoutService
    @Service("accountService") 
    @Transactional
    public class AccountServiceImpl implements AccountService {
    	@Autowired
    	private AccountDao accountDao;
      
    	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    	public void transfer(String outMan, String inMan, double money) { 
        accountDao.out(outMan,money);
    		int i = 1/0;
    		accountDao.in(inMan,money); 
      }
    }
    ```

  - ```xml
    //3. 编写  applicationContext.xml 配置文件
    	//配置数据源
      <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    		<property name="driverClass" value="com.mysq1. jdbc.Driver" >
    		<property name="jdbcUr1" value="jdbc:mysq1://1ocalhost:3306/test" />
        <property name="user" value="root" >
        <property name="password" value="root" />
      </bean>
         //平台事务管理器
      <bean id="transactionManager"class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    	</bean>
          //组件扫描
    <context:component-scan base-package="com"/>
          //事务的注解驱动
    <tx:annotation-driven/>
    ```

    

- 使用@Transactional在需要进行事务控制的类或是方法上修饰，注解可用的属性同xml配置方式，例如隔离级别、传播行为等。
- 注解使用在类上，那么该类下的所有方法都使用同一套注解参数配置。使用在方法上，不同的方法可以采用不同的事务参数配置。
- Xml配置文件中要开启事务的注解驱动`<tx : annotation-driven />`

## SpringMVC

-  SpringMVC是一种基于Java的实现MVC设计模型的请求驱动类型的轻量级Web框架，属于SpringFrameWork的后续产品，已经融合在Spring Web Flow中。

- SpringMVC已经成为目前最主流的MVC框架之一，并且随着Spring3.0的发布，全面超越Struts2，成为最优秀的MVC框架。它通过一套注解，让一个简单的Java类成为处理请求的控制器，而无须实现任何接口。同时它还支持RESTful编程风格的请求。

- 需求:客户端发起请求，服务器端接收请求，执行逻辑并进行视图跳转。

### 步骤

- 导入SpringMVC相关坐标

- 配置SpringMVC核心控制器DispathcerServlet

- 创建Controller类和视图页面

- 使用注解配置Controller类中业务方法的映射地址

- 配置SpringMVC核心文件spring-mvc.xml

- 客户端发起请求测试

- ```xml
  1.pom.xml导入Spring、SpringMVC、servlet和jsp的坐标
  <!--Spring --> 
  <dependency>
  	<groupId>org.springframework</groupId> 
    <artifactId>spring-context</artifactId> 
    <version>5.0.5.RELEASE</version>
  </dependency>
  <!--SpringMVC                --> 
  <dependency>
  	<groupId>org.springframework</groupId> 
    <artifactId>spring-webmvc</artifactId> 
    <version>5.0.5.RELEASE</version>
  </dependency>
  <!--Servlet                --> 
  <dependency>
  	<groupId>javax.servlet</groupId>
  	<artifactId>servlet-api</artifactId> 
    <version>2.5</version>
  </dependency> 
  <!--Jsp --> 
  <dependency>
  	<groupId>javax.servlet.jsp</groupId> 
    <artifactId>jsp-api</artifactId>
  	<version>2.0</version> 
  </dependency>
  2.在 web.xml配置SpringMVC的核心控制器
  <servlet>
  	<servlet-name>DispatcherServlet</servlet-name>
  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class> 
    <init-param>
  		<param-name>contextConfigLocation</param-name>
  		<param-value>classpath:spring-mvc.xml</param-value> //配置xml的路径
    </init-param>
  	<load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
  	<servlet-name>DispatcherServlet</servlet-name> 
    <url-pattern>/</url-pattern> //处理所有的请求
  </servlet-mapping>
  ```

  ```java
  3 创建视图页面index.jsp
  4 创建Controller和业务方法
  @Controller
  public class QuickController {
    @RequestMapping("/quick")
  	public String quickMethod(){
  	System.out.println("quickMethod running.....");
  	return "index"; 
    }
  }
  ```

  ```xml
  5 创建spring-mvc.xml
  <beans xmlns="http://www.springframework.org/schema/beans"
  			 xmlns:mvc="http://www.springframework.org/schema/mvc"
  			 xmlns:context="http://www.springframework.org/schema/context"
  			 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  			 xsi:schemaLocation="http://www.springframework.org/schema/beans
  			 http://www.springframework.org/schema/beans/spring-beans.xsd
  			 http://www.springframework.org/schema/mvc
  			 http://www.springframework.org/schema/mvc/spring-mvc.xsd
  			 http://www.springframework.org/schema/context
         http://www.springframework.org/schema/context/spring-context.xsd">
    //配置注解扫描
    <context:component-scan base-package="包的全限定名"/> 
  </beans>
  6.访问测试地址
  ```

  

### SpringMVC组件解析

![[C:\Users\15271\AppData\Roaming\Typora\typora-user-images\image-20220430192222854.png]]

#### SpringMVC的执行流程

1. 用户发送请求至前端控制器DispatcherServlet。
2. DispatcherServlet收到请求调用HandlerMapping处理器映射器。
3. 处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果 有则生成)一并返回给DispatcherServlet。
4. DispatcherServlet调用HandlerAdapter处理器适配器。
5. HandlerAdapter经过适配调用具体的处理器(Controller，也叫后端控制器)。
6. Controller执行完成返回ModelAndView。
7. HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet。
8. DispatcherServlet将ModelAndView传给ViewReslover视图解析器。
9. ViewReslover解析后返回具体View。
10. DispatcherServlet根据View进行渲染视图（即将模型数据填充至视图中）。DispatcherServlet响应用户。

#### 组件解析器

- 前端控制器：DispatcherServlet

  - 用户请求到达前端控制器，它就相当于  MVC 模式中的  C，DispatcherServlet 是整个流程控制的中心，由 它调用其它组件处理用户的请求，DispatcherServlet 的存在降低了组件之间的耦合性。

- 处理器映射器：HandlerMapping

  - HandlerMapping 负责根据用户请求找到 Handler 即处理器，SpringMVC 提供了不同的映射器实现不同的 映射方式，例如：配置文件方式，实现接口方式，注解方式等。

- 处理器适配器：HandlerAdapter

  - 通过  HandlerAdapter 对处理器进行执行，这是适配器模式的应用，通过扩展适配器可以对更多类型的处理 器进行执行。

- 处理器：Handler

  - 它就是我们开发中要编写的具体业务控制器。由  DispatcherServlet 把用户请求转发到  Handler。由 Handler 对具体的用户请求进行处理。

- 视图解析器：View Resolver

  - 属性注入修改视图的的前后缀

  - ```xml
    //配置内部资源视图解析器
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"> 
      <property name="prefix" value="/WEB-INF/views/"></property>
    	<property name="suffix" value=".jsp"></property> 
    </bean>
    ```

  - View Resolver 负责将处理结果生成  View 视图，View Resolver 首先根据逻辑视图名解析成物理视图名，即 具体的页面地址，再生成  View 视图对象，最后对  View 进行渲染将处理结果通过页面展示给用户。

- 视图：View

  - SpringMVC 框架提供了很多的 View 视图类型的支持，包括：jstlView、freemarkerView、pdfView等。最 常用的视图就是 jsp。一般情况下需要通过页面标签或页面模版技术将模型数据通过页面展示给用户，需要由程 序员根据业务需求开发具体的页面

### 注解解析

| 注解            | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| @RequestMapping | 用于建立请求URL和处理请求方法之间的对应关系                  |
| @Controller     | 用于指明Controller                                           |
| @ResponseBody   | 将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据 |
| @CrossOrigin    | 配置跨域请求，两个参数origins： 允许可访问的域列表；maxAge:准备响应前的缓存持续的最大时间（以秒为单位） |
| @RequestParam   | 用于将请求参数区数据映射到功能处理方法的参数上。不一致时通过 注解的value属性进行指定，没有对应数据则不会请求成功 |
| @RequestBody    | 用来将请求参数绑定到request body中，通过HttpMessageConverter封装为具体的JavaBean |
| @PathVariable   | 映射URL绑定的占位符                                          |
| @RequestHeader  | 使用@RequestHeader可以获得请求头信息。value属性：请求头的名称；equired：是否必须携带此请求头 |
| @CookieValue    | 使用@CookieValue可以获得指定Cookie的值。value属性：请求头的名称；equired：是否必须携带此请求头 |

#### @requestmapping

- 用于建立请求URL和处理请求方法之间的对应关系
- 位置:

	- 类上，请求URL的第一级访问目录。此处不写的话，就相当于应用的根目录
	- 方法上，请求URL的第二级访问目录，与类上的使用@ReqquestMapping标注的一级目录一起组成访问虚拟路径

- 属性:

	- value:用于指定请求的URL。它和path属性的作用是一样的method:用于指定请求的方式
	- params:用于指定限制请求参数的条件。它支持简单的表达式。要求请求参数的key和value必须和配置的一模一样

		- params = {"accountName"}，表示请求参数必须有accountName
		- params = {"moeny!100"}，表示请求参数中money不能是100

### 数据响应

#### 页面跳转

##### 直接返回字符串

- 此种方式会将返回的字符串与视图解析器的前后缀拼接后跳转。

- ```xml
  //1.配置controller
  @RequestMapping("/quick") 
  public String quickMethod(){ 
  return "index"; 
  }
  ```

- ```xml
  //2.配置内部资源视图解析器
  <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"> 
    <property name="prefix" value="/WEB-INF/views/"></property>
  	<property name="suffix" value=".jsp"></property> 
  </bean>
  ```

- 转发资源地址：/WEB-INF/views/index.jsp

- 返回带有前缀的字符串：

  - 转发：forward:/WEB-INF/views/index.jsp 
  - 重定向：redirect:/index.jsp

##### ModelAndView对象返回

- 在方法的参数中填写，由spring注入

  - ModelAndView
  - Model
  - View
  - HttpServletRequest

- ```java
  @RequestMapping("/quick2")
  public ModelAndView quickMethod2(){
  	ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("redirect:index.jsp");
    return modelAndView;
  }
  @RequestMapping("/quick3")
  public ModelAndView quickMethod3(){
  	ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("forward:/WEB-INF/views/index.jsp"); 
    return modelAndView;
  }
  ```

  

#### 回写数据

##### 直接返回字符串

- 通过SpringMVC框架注入的response对象，使用response.getWriter().print(“hello world”) 回写数 据，此时不需要视图跳转，业务方法返回值为void。

- ```java
  @RequestMapping("/quick4")
  public void quickMethod4(HttpServletResponse response) throws IOException {
  	response.getWriter().print("hello world"); 
  }
  ```

- @ResponseBody

  - 将需要回写的字符串直接返回，但此时需要通过@ResponseBody注解告知SpringMIVC框架，方法返回的字符串不是跳转是直接在http响应体中返回。

  - ```java
    @RequestMapping("/quick5") 
    @ResponseBody
    public String quickMethod5() throws IOException {
    	return "hello springMVC!!!"; 
    }
    ```

    

##### 传入json格式字符串

- jackson

  - 导入坐标jackson

  - ```xml
    <!-- jackson-- > 
    <dependency>
    	<groupId>com.fasterxml.jackson.core</groupId> 
    	<artifactId>jackson-core</artifactId>
    	<version>2.9.0</version> 
    </dependency>
    <dependency>
    	<groupId>com.fasterxml.jackson.core</groupId> 
    	<artifactId>jackson-databind</artifactId>
    	<version>2.9.0</version> </dependency>
    </dependency>
    	<groupId>com.fasterxml.jackson.core</groupId> 
    	<artifactId>jackson-annotations</artifactId> 
    	<version>2.9.0</version>
    </dependency>
    ```

    

  - 通过jackson转换json格式字符串，回写字符串。

  - ```java
    @RequestMapping("/quick7") 
    @ResponseBody
    public String quickMethod7() throws IOException {
    	User user = new User(); 
      user.setUsername("zhangsan"); 
      user.setAge(18);
    	ObjectMapper objectMapper = new ObjectMapper(); 
      String s = objectMapper.writeValueAsString(user); 
      return s;
    }
    ```

    

##### 返回对象或集合

- mvc的注解驱动

- ```xml
  在Spring-mvc.xml配置
  <beans xmlns="http://www.springframework.org/schema/beans"
  			 xmlns:mvc="http://www.springframework.org/schema/mvc"
  			 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  			 xsi:schemaLocation="http://www.springframework.org/schema/beans
  			 										 http://www.springframework.org/schema/beans/spring-beans.xsd
  			                     http://www.springframework.org/schema/mvc
  			                     http://www.springframework.org/schema/mvc/spring-mvc.xsd">
  <mvc:annotation-driven/>
  </beans>
  ```

- ```
  在 SpringMVC 的各个组件中，处理器映射器、处理器适配器、视图解析器称为 SpringMVC 的三大组件。
  使用<mvc:annotation-driven>自动加载 RequestMappingHandlerMapping（处理映射器）和 RequestMappingHandlerAdapter（ 处 理 适 配 器 ），可用在Spring-xml.xml配置文件中使用<mvc:annotation-driven>替代注解处理器和适配器的配置。
  同时使用<mvc:annotation-driven>默认底层就会集成jackson进行对象或集合的json格式字符串的转换。
  ```

  



### 数据请求

- 客户端请求参数的格式是: name=value&name=value......
- 服务器端要获得请求的参数，有时还需要进行数据的封装

	- 参数的类型

		- 基本
		- POJO
		- 数组
		- 集合


#### 获得参数类型

- 获得基本类型参数

  - Controller中的业务方法的参数名称要与请求参数的name一致，参数值会自动映射匹配。

  - ```java
    //请求：http://localhost:8080/itheima_springmvc1/quick9?username=zhangsan&age=12
    
    @RequestMapping("/quick9") 
    @ResponseBody
    public void quickMethod9(String username,int age) throws IOException { 
      System.out.println(username);
    	System.out.println(age); 
    }
    ```

- 获得POJO类型参数

  - Controller中的业务方法的POJO参数的属性名与请求参数的name一致，参数值会自动映射匹配。

  - ```java
    //请求:http://localhost:8080/itheima_springmvc1/quick10?username=zhangsan&age=12
    
    public class User {
    	private String username; 
      private int age; getter/setter…
    }
    
    @RequestMapping("/quick10") 
    @ResponseBody
    public void quickMethod10(User user) throws IOException {
      System.out.println(user); 
    }
    ```

    

- 获得数组类型参数

  - Controller中的业务方法数组名称与请求参数的name一致，参数值会自动映射匹配。

  - ```java
    //请求:http://localhost:8080/itheima_springmvc1/quick11?strs=111&strs=222&strs=333
    @RequestMapping("/quick11") 
    @ResponseBody
    public void quickMethod11(String[] strs) throws IOException {
    	System.out.println(Arrays.asList(strs)); 
    }
    ```

    

- 获得集合类型参数

	- 当使用ajax提交时，可以指定contentType为json形式，那么在方法参数位置使用@RequestBody可以直接接收集合数据而无需使用POJO进行包装。
	
	- ```javascript
	  <script>
	  	//模拟数据
	  	var userList = new Array();
	  	userList.push({username: "zhangsan",age: "20"}); 
	  	userList.push({username: "lisi",age: "20"});
	  	$.ajax({
	  		type: "POST",
	  		url: "/quick13", 
	    	data: JSON.stringify(userList),
	    	contentType : 'application/json;charset=utf-8' 
	    });
	  </script>
	  ```
	
	- ```java
	  @RequestMapping("/quick13") 
	  @ResponseBody
	  public void quickMethod13(@RequestBody List<User> userList) throws IOException {
	  	System.out.println(userList); 
	  }
	  ```
	
	  

- 开放对资源的访间

  - <mvc:resources marping=" /js/**" location=" / js/" l>
  - <mvc: default-servlet-handler/>
  - spring找匹配地址，如果找不到就交由原生的tomcat寻找


#### 请求数据乱码问题

- ```xml
  <filter>
  	<filter-name>encoding</filter-name>
  	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  	<init-param>
   		<param-name>encoding</param-name>
   		<param-value>utf-8</param-value>
  	</init-param>
  </filter>
  <filter-mapping>
  	<filter-name>encoding</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
  ```

  

#### 参数绑定

- @requestParam

	- value:与请求参数名称

		- 当请求的参数名称与Controller的业务方法参数名称不一致时，就需要通过@RequestParam注解显式的绑定。

			- 只有value参数时可以将value=“name1”简写成“name1”

	- required:此在指定的请求参数是否必须包括，默认是true，提交时如果没有此参数则报错
	- defaultValue:当没有指定请求参数时，则使用指定的默认值赋值

- 获得Restful风格的参数

  - Restful是一种软件架构风格、设计风格，而不是标准，只是提供了一组设计原则和约束条件。主要用于客户端和服务器交互类的软件，基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存机制等

- Restful风格的请求是使用“url+请求方式”表示一次请求目的的

  - GET:用于获取资源
  - POST:用于新建资源
  - PUT:用于更新资源
  - DELETE:用于删除资源
  - 获得Restful风格的参数

  	- url地址/user/1中的1就是要获得的请求参数，在SpringMC中可以使用占位符进行参数绑定。地址/user/1可以写成/user/[id}，占位符{id}对应的就是1的值。在业务方法中我们可以使用@PathVariable注解进行占位符的匹配获取工作。

- 获得请求头

  - .@RequestHeader

  	- 使用@RequestHeader可以获得请求头信息，相当于web阶段学习的request.getHeader(name）
  	- value:请求头的名称
  	- required:是否必须携带此请求头

  - @CookieValue

  	- 使用@CookieValue可以获得指定Cookie的值

  		- 属性

  			- value:指定cookie的名称
  			- required:是否必须携带此cookie


#### 文件上传

- 文件上传客户端三要素

	- 表单项type= “file"
	- 表单的提交方式是post
	- ·表单的enctype属性是多部分表单形式，及enctype=“multipart/form-data"

- 文件上传原理

	- 当form表单修改为多部分表单时,request.getParameter()将失效。
	- enctype= “application/x-www-form-urlencoded”时，form表单的正文内容格式是:key=value&key=value&key=value
	- 当form表单的enctype取值为Mutilpart/form-data时，请求正文内容就变成多部分形式

		- 

### Jdbc teamplate

### 拦截器

- Spring MVC 的拦截器类似于  Servlet  开发中的过滤器  Filter，用于对处理器进行预处理和后处理。 
- 将拦截器按一定的顺序联结成一条链，这条链称为拦截器链（Interceptor Chain）。在访问被拦截的方 法或字段时，拦截器链中的拦截器就会按其之前定义的顺序被调用。拦截器也是AOP思想的具体实现。

####  拦截器和过滤器区别

- | 区别     | 过滤器（Filter）                                             | 拦截器（Interceptor）                                        |

| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 使用范围 | 是  servlet  规范中的一部分，任何 Java Web 工程都可以使用    | 是  SpringMVC  框架自己的，只有使用了 SpringMVC 框架的工程才能用 |
| 拦截范围 | 在  url-pattern  中配置了/*之后， 可以对所有要访问的资源拦截 | 在<mvc:mapping path=“”/>中配置了/**之 后，也可以多所有资源进行拦截，但 是可以 通 过<mvc:exclude-mapping  path=“”/>标签 排除不需要拦截的资源 |

#### 步骤

- 创建拦截器类实现HandlerInterceptor接口

- 配置拦截器

- 测试拦截器的拦截效果

- ```java
  //1.创建拦截器类实现HandlerInterceptor接口
  public class MyHandlerInterceptor1 implements HandlerInterceptor {
  	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
  		System.out.println("preHandle running..."); return true;
  	}
    
  	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView 	         modelAndView) {
  		System.out.println("postHandle running..."); 
    }
    
  	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) { 				System.out.println("afterCompletion running...");
  	}
  }
  ```

- ```xml
  2 配置拦截器
  <mvc:interceptors>
  	<mvc:interceptor>
  		<mvc:mapping path="/**"/>
  		<mvc:exclude-mapping path="/user"/>
  		<bean class="com.itheima.interceptor.MyHandlerInterceptor1"/> 
    </mvc:interceptor>
  </mvc:interceptors>
  ```

- ```java
  3 测试拦截器的拦截效果
  @RequestMapping("/quick23") 
  @ResponseBody
  public ModelAndView quickMethod23() throws IOException, ParseException { 
    System.out.println("目标方法执行....");
  	ModelAndView modelAndView = new ModelAndView(); 
    modelAndView.addObject("name","zhangsan"); 
    modelAndView.setViewName("index");
  	return modelAndView; 
  }
  ```

#### 方法

| 方法              | 说明                                                         |
| ----------------- | ------------------------------------------------------------ |
| preHandle()       | 方法将在请求处理之前进行调用，该 方法的 返回值 是布尔 值Boolean类型的 ， 当它返回为false  时，表示请求结束，后续的Interceptor  和Controller  都不会 再执行；当返回值为true  时就会继续调用下一个Interceptor  的preHandle 方 法 |
| postHandle()      | 该方法是在当前请求进行处理之后被 调用， 前提是preHandle  方法的返回值为 true 时才能被调用，且它会在DispatcherServlet  进行视图返回渲染之前被调 用，所以我们可以在这个方法中对Controller  处理之后的ModelAndView  对象 进行操作 |
| afterCompletion() | 该方法将在整个请求结束之后，也就 是在DispatcherServlet  渲染了对应的视图 之后执行，前提是preHandle  方法的返回值为true  时才能被调用 |

### 异常处理

- 系统中异常包括两类：预期异常和运行时异常RuntimeException，前者通过捕获异常从而获取异常信息，后 者主要通过规范代码开发、测试等手段减少运行时异常的发生。
- 系统的Dao、Service、Controller出现都通过throws Exception向上抛出，最后由SpringMVC前端控制器交 由异常处理器进行异常处理
  - ![[C:\Users\15271\AppData\Roaming\Typora\typora-user-images\image-20220430224825045.png]]

- 方式

  - 使用Spring MVC提供的简单异常处理器SimpleMappingExceptionResolver

  - 实现Spring的异常处理接口HandlerExceptionResolver 自定义自己的异常处理器

#### SimpleMappingExceptionResolver

- SpringMVC已经定义好了该类型转换器，在使用时可以根据项目情况进行相应异常与视图的映射配置

- ```
  <!-- 配置简单映射异常处理器 -->
  <bean class=“org.springframework.web.servlet.handler.SimpleMappingExceptionResolver”> 
  	<property name=“defaultErrorView” value=“error”/>   默认错误视图
  	<property name=“exceptionMappings”>
  		<map>
  															异常类型                      错误视图
  		<entry key="com.itheima.exception.MyException" value="error"/>
      <entry key="java.lang.ClassCastException" value="error"/>
  		</map> 
    </property>
  </bean>
  ```

#### HandlerExceptionResolver

- ```java
  1.创建异常处理器类实现HandlerExceptionResolver
  public class MyExceptionResolver implements HandlerExceptionResolver {
  	@Override
  	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
  		ModelAndView modelAndView = new ModelAndView(); 
      modelAndView.setViewName("exceptionPage"); 
      return modelAndView;
  	}
  }
  ```

- ```xml
  2 配置异常处理器
  <bean id="exceptionResolver" class="com.itheima.exception.MyExceptionResolver"/>
  ```

- 3 编写异常页面

- ```
  4 测试异常跳转
  ```

  

## MyBatis

### 原始jdbc

- 原始jdbc开发存在的问题

	- 数据库连接创建、释放频繁造成系统资源浪费从而影响系统性能
	- sql语句在代码中硬编码，造成代码不易维护，实际应用sql变化的可能较大，sql变动需要改变java代码。
	- 查询操作时，需要手动将结果集中的数据手动封装到实体中。插入操作时，需要手动将实体的数据设置到sql语句的占位符位置

- 应对上述问题给出的解决方案:

	- 使用数据库连接池初始化连接资源
	- 将sql语句抽取到xml配置文件中
	- 使用反射、内省等底层技术，自动将实体与表进行属性与字段的自动映射

### 介绍

- mybatis是一个优秀的基于java的持久层框架，它内部封装了jdbc，使开发者只需要关注sql语句本身，而不需要花费精力去处理加载驱动、创建连接、创建statement等繁杂的过程。
- mybatis通过xml或注解的方式将要执行的各种statement配置起来，并通过java对象和statement中sql的动态参数进行映射生成最终执行的sql语句。
- 最后mybatis框架执行sql并将结果映射为java对象并返回。采用ORM思想解决了实体和数据库映射的问题，对jdbc进行了封装，屏蔽了jdbc api底层访问细节，使我们不用与jdbc api打交道，就可以完成对数据库的持久化操作。

###基于xml

#### 步骤

- 添加MyBatis的坐标
- 创建user数据表
- 编写User实体类
- 编写映射文件UserMapper.xml
- 编写核心文件SqlMapConfig.xml
- 编写测试类

#### 增删改查

##### 查询

- ```java
  //1.导入MyBatis、mysql驱动、单元测试坐标和日志坐标
  //2. 创建user数据表
  //3. 编写User实体
  public class User {
  	private int id;
  	private String username;
  	private String password; 
  	//省略get个 set方法
  }
  ```

- ```xml-dtd
  //4. 编写UserMapper.xml映射文件
  <?xml version="1.0" encoding="UTF-8" ?> 
  //映射文件DTD约束头
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  //namespace:命名空间，与下面语句的id一起组成查询的标识
  //<mapper> 根标签
  <mapper namespace="userMapper">
  	//<select>查询操作，可选的还有 insert、update、 delete
  	//id:语句的id标识，与上面的命名空 间一起组成查询的标识
  	//resultType:查询结果对应的实体类型
  	<select id="findAll" resultType="com.domain.User">
  	//sql语句
  	select * from User 
  	</select>
  </mapper>
  //5. 编写MyBatis核心SqlMapConfig.xml文件
  <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN“ "http://mybatis.org/dtd/mybatis-3-config.dtd"> 	<configuration>
  	<environments default="development">
  		<environment id="development"> 
    		<transactionManager type="JDBC"/> 
    		<dataSource type="POOLED">
  				<property name="driver" value="com.mysql.jdbc.Driver"/>
  				<property name="url" value="jdbc:mysql:///test"/>
   				<property name="username" value="root"/>
  				<property name="password" value="root"/>
  			</dataSource> 
    	</environment>
  	</environments>
  	<mappers>
    	<mapper resource="com/itheima/mapper/UserMapper.xml"/> 
    </mappers> 
   </configuration>
  ```

- ```java
  //6 编写测试代码
  //加载核心配置文件
  InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml"); 
  //        获得sqlSession工厂对象
  SqlSessionFactory  sqlSessionFactory  = new SqlSessionFactoryBuilder().build(resourceAsStream); 
  //         获得sqlSession对象
  SqlSession sqlSession  = sqlSessionFactory.openSession();
  //       执sql 语句 
  List<User> userList = sqlSession.selectList("userMapper.findAll"); 
  //打印结果
  System.out.println(userList); 
  //释放资源
  sqlSession.close();
  ```

##### 插入

- 插入语句使用insert标签

- 在映射文件中使用parameterType属性指定要插入的数据类型

- Sql语句中使用#{实体属性名}方式引用实体中的属性值

- 插入操作使用的API是sqlSession.insert(“命名空间.id”,实体对象);

- 插入操作涉及数据库数据变化，所以要使用sqlSession对象显示的提交事务， 即sqlSession.commit()

- ```xml-dtd
  //1. 编写UserMapper.xml映射文件
  <mapper namespace="userMapper">
  	<update id="update" parameterType="com.domain.User">
  		update user set username=#{username},password=#{password}  where id=#{id} 
  	</update>
  </mapper>
  ```

- ```java
  //2. 编写修改实体User的代码
  InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml"); 
  SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream); 
  SqlSession sqlSession  = sqlSessionFactory.openSession();
  int update = sqlSession.update("userMapper.update", user); 
  System.out.println(update);
  sqlSession.commit(); 
  sqlSession.close();
  ```

##### 修改

- 修改语句使用update标签

- 修改操作使用的API是sqlSession.update(“命名空间.id”,实体对象);

- 修改操作涉及数据库数据变化，所以要使用sqlSession对象显示的提交事务， 即sqlSession.commit()

- ```xml-dtd
  <update id="update" parameterType="com.domain.User">
  	update user set username=#{username},password=#{password} where id=#{id} 
  </update>
  ```

- ```java
  sqlSession.update("userMapper.update", user);
  ```

#####删除

- 删除语句使用delete标签

- Sql语句中使用#{任意字符串}方式引用传递的单个参数

- 删除操作使用的API是sqlSession.delete(“命名空间.id”,Object);

- 删除操作涉及数据库数据变化，所以要使用sqlSession对象显示的提交事务， 即sqlSession.commit()

- ```xml
  <delete id="delete" parameterType="java.lang.Integer">
  	delete from user where id=#{id} 
  </delete>
  ```

- ```java
  sqlSession.delete("userMapper.delete",3);
  ```

#### 代理开发

- 采用Mybatis的代理开发方式实现DAO层的开发，这种方式是我们后面进入企业的主流。

- Mapper接口开发方法只需要程序员编写Mapper接口(相当于Dao接口)，由Mybatis框架根据接口定义创建接口的动态代理对象，代理对象的方法体同上边Dao接口实现类方法。

- Mapper接口开发需要遵循的规范

  - 1、Mapper.xml文件中的namespace与mapper接口的全限定名相同
  - 2、Mapper接口方法名和Mapper.xml中定义的每个statement的id相同
  - 3、Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql的parameterType的类型相同
  - 4、Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同
  - ![[C:\Users\15271\AppData\Roaming\Typora\typora-user-images\image-20220502230652929.png]]

- ```java
  @Test
  public void testProxyDao() throws IOException {
  	InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml"); 
    SqlSessionFactory sqlSessionFactory  = new SqlSessionFactoryBuilder().build(resourceAsStream); 
    SqlSession sqlSession  = sqlSessionFactory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class); 
    User user = userMapper.findById(1);
  	System.out.println(user); sqlSession.close();
  }
  ```

#### 动态sql

Mybatis 的映射文件中，前面我们的  SQL 都是比较简单的，有些时候业务逻辑复杂时，我们的  SQL是动态变化的， 此时在前面的学习中我们的  SQL 就不能满足要求了。

##### `<if>`

- 我们根据实体类的不同取值，使用不同的  SQL语句来进行查询。比如在  id如果不为空时可以根据id查询，如果 username 不同空时还要加入用户名作为条件。这种情况在我们的多条件组合查询中经常会碰到。

- ```xml-dtd
  <select id="findByCondition" parameterType="user" resultType="user"> 
  	select * from User
  	<where>
  		<if test="id!=0">
  		and id=#{id} 
  		</if>
  		<if test="username!=null">
  		and username=#{username} 
  		</if>
  	</where> 
  </select>
  ```

##### `<foreach>`

- 循环执行sql的拼接操作，例如：SELECT * FROM USER WHERE id IN (1,2,5)。

- ```xml-dtd
  <select id="findByIds" parameterType="list" resultType="user"> 
  select * from User
  	<where>
  		<foreach collection="array" open="id in(" close=")" item="id" separator=",">
  			#{id} 
  		</foreach>
  	</where> 
  </select>
  ```

- | 属性       | 说明                                      |

| ---------- | ----------------------------------------- |
| collection | 代表要遍历的集合元素，注意编写时不要写#{} |
| open       | 代表语句的开始部分                        |
| close      | 代表结束部分                              |
| item       | 代表遍历集合的每个元素，生成的变量名      |
| sperator   | 代表分隔符                                |

##### SQL片段抽取

- Sql 中可将重复的  sql 提取出来，使用时用  include 引用即可，最终达到  sql 重用的目的

- ```xml-dtd
  <sql id="selectUser" select * from User</sql>
  <select id="findById" parameterType="int" resultType="user">
  	<include refid="selectUser"></include> where id=#{id} 
  </select>
  <select id="findByIds" parameterType="list" resultType="user"> 
  	<include refid="selectUser"></include>
  	<where>
  		<foreach collection="array" open="id in(" close=")" item="id" separator=",">
  			#{id} 
  		</foreach>
  	</where> 
  </select>
  ```

#### 多表操作

##### 一对一查询

- 用户表和订单表的关系为，一个用户有多个订单，一个订单只从属于一个用户 

- 需求：查询一个订单，与此同时查询出该订单所属的用户

- ```java
  //1.创建实体
  public class Order {
  	private int id;
  	private Date ordertime;
  	private double total;
  	//代表当前订单从属于哪一个客户
  	private User user; 
  }
  public class User {
  	private int id;
  	private String username;
  	private String password;
  	private Date birthday;
  }
  //2.创建OrderMapper接口
  public interface OrderMapper {
  	List<Order> findAll(); 
  }
  ```

- ```xml
  //3.配置OrderMapper.xml
  <mapper namespace="com.mapper.OrderMapper">
  	<resultMap id="orderMap" type="com.domain.Order">
  		<result column="uid" property="user.id"></result>
  		<result column="username" property="user.username"></result> 
      <result column="password" property="user.password"></result> 		
      <result column="birthday" property="user.birthday"></result>
  	</resultMap>
  	<select id="findAll" resultMap="orderMap">
  		select * from orders o,user u where o.uid=u.id 
    </select>
  </mapper>
  //resultMap还可以使用association标签
  <resultMap id="orderMap" type="com.itheima.domain.Order">
  	<result property="id" column="id"></result>
  	<result property="ordertime" column="ordertime"></result> 
    <result property="total" column="total"></result>
  	<association property="user" javaType="com.domain.User">
  		<result column="uid" property="id"></result>
  		<result column="username" property="username"></result> 
      <result column="password" property="password"></result> 
      <result column="birthday" property="birthday"></result>
  	</association> 
  </resultMap>
  ```

##### 一对多查询

- 用户表和订单表的关系为，一个用户有多个订单，一个订单只从属于一个用户

- 需求：查询一个用户，与此同时查询出该用户具有的订单

- ```java
  //1.修改User实体
  public class User {
  	private int id;
  	private String username;
  	private String password;
  	private Date birthday;
  	//代表当前用户具备哪些订单
  	private List<Order> orderList; 
  	}
  //2.创建UserMapper接口
  public interface UserMapper {
  	List<User> findAll(); 
  }
  ```

- ```xml-dtd
  //3.配置UserMapper.xml
  <mapper namespace="com.mapper.UserMapper">
  	<resultMap id="userMap" type="com.domain.User">
  		<result column="id" property="id"></result>
  		<result column="username" property="username"></result> 
  		<result column="password" property="password"></result> 
  		<result column="birthday" property="birthday"></result>
  		<collection property="orderList" ofType="com.domain.Order">
  			<result column="oid" property="id"></result>
  			<result column="ordertime" property="ordertime"></result> 
  			<result column="total" property="total"></result>
  		</collection> 
  	</resultMap>
  	<select id="findAll" resultMap="userMap">
  		select *,o.id oid from user u left join orders o on u.id=o.uid 
  	</select>
  </mapper>
  ```

##### 多对多查询

- 用户表和角色表的关系为，一个用户有多个角色，一个角色被多个用户使用

- 多对多查询的需求：查询用户同时查询出该用户的所有角色

- ```java
  //1. 创建Role实体，修改User实体
  public class User {
  	private int id;
  	private String username;
  	private String password;
  	private Date birthday;
  	//代表当前用户具备哪些订单 
  	private List<Order> orderList; 
    //代表当前用户具备哪些角色
  	private List<Role> roleList; 
  }
  public class Role {
  	private int id; 
    private String rolename;
  }
  //2.添加UserMapper接口方法
  List<User> findAllUserAndRole();
  ```

- ```xml-dtd
  //3.配置UserMapper.xml
  <resultMap id="userRoleMap" type="com.domain.User">
  	<result column="id" property="id"></result>
  	<result column="username" property="username"></result> 
  	<result column="password" property="password"></result> 
  	<result column="birthday" property="birthday"></result>
  	<collection property="roleList" ofType="com.itheima.domain.Role">
  		<result column="rid" property="id"></result>
  		<result column="rolename" property="rolename"></result> 
  	</collection>
  </resultMap>
  <select id="findAllUserAndRole" resultMap="userRoleMap">
  	select u.*,r.*,r.id rid from user u left join user_role ur on u.id=ur.user_id inner join role r on ur.role_id=r.id 
  </select>
  ```

  

### 核心配置文件

#### 层级关系

- configuration配置
  - properties属性

  - settings设置
  - typeAliases类型别名 
  - typeHandlers类型处理器
  - objectFactory对象工厂
  - plugins插件
  - environnents环境
    - environrmnent 环境变量
      - dataSource数据源
      - transactionManager 事务管理器
  - databaseaProvider数据库厂商标识
  - mappers映射器

#### environments标签

- 数据库环境的配置，支持多环境配置

- ```xml-dtd
  	<environments default="development">    //environments default：指定默认的环境名称 
  		<environment id="development">        //environment id：指定当前的环境名称
    		<transactionManager type="JDBC"/>   //transactionManager type：指定事务管理类型是JDBC
    		<dataSource type="POOLED">          //dataSource type：指定当前数据源类型是连接池
  				<property name="driver" value="com.mysql.jdbc.Driver"/>
  				<property name="url" value="jdbc:mysql:///test"/>
   				<property name="username" value="root"/>
  				<property name="password" value="root"/>
  			</dataSource> 
    	</environment>
  	</environments>
  ```

##### 事务管理器（transactionManager）

- 类型
- JDBC：这个配置就是直接使用了JDBC 的提交和回滚设置，它依赖于从数据源得到的连接来管理事务作用域。
- MANAGED：这个配置几乎没做什么。它从来不提交或回滚一个连接，而是让容器来管理事务的整个生命周期（比如 JEE 应用服务器的上下文）。 默认情况下它会关闭连接，然而一些容器并不希望这样，因此需要将 closeConnection 属性设置 为 false 来阻止它默认的关闭行为。

##### 数据源（dataSource）

- 类型
- UNPOOLED：这个数据源的实现只是每次被请求时打开和关闭连接。
- POOLED：这种数据源的实现利用“池”的概念将 JDBC 连接对象组织起来。
- JNDI：这个数据源的实现是为了能在如 EJB 或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置 一个 JNDI 上下文的引用。

#### mapper标签

- 该标签的作用是加载映射的
- 加载方式
  - 使用相对于类路径的资源引用，例如：`<mapper resource="org/mybatis/builder/AuthorMapper.xml"/>`
  - 使用完全限定资源定位符（URL），例如：`<mapper url="file:///var/mappers/AuthorMapper.xml"/>`
  - 使用映射器接口实现类的完全限定类名，例如：`<mapper class="org.mybatis.builder.AuthorMapper"/>`
  - 将包内的映射器接口实现全部注册为映射器，例如：`<package name="org.mybatis.builder"/>`

####  Properties标签

- 实际开发中，习惯将数据源的配置信息单独抽取成一个properties文件，该标签可以加载额外配置的properties文件
- `< properties resource="jdbc. properties"></ properties>`

#### typeAliases标签

- 类型别名是为Java 类型设置一个短的名字。

##### 自定义别名

- ```xml-dtd
  <!--原来的类型名称配置resultType，使用类全限定名称-->
  <select id="findAll" resultType="com.domain.User">
  select * from User
  </select>
  
  <!--配置typeAliases，为com.domain.User定义别名为user-->
  <typeAliases>
  	<typeAlias type="com.domain.User“ alias="user"></typeAlias> 
  </typeAliases>
  <!--user为别名-->
  <select id="findAll" resultType="user">
  select * from User
  </select>
  ```

##### 框架已配置别名

| 别名    | 数据类型 |
| ------- | -------- |
| string  | String   |
| long    | Long     |
| int     | Integer  |
| double  | Double   |
| boolean | Boolean  |

#### typeHandlers标签

- 无论是  MyBatis 在预处理语句（PreparedStatement）中设置一个参数时，还是从结果集中取出一个值时，  都会用 类型处理器将获取的值以合适的方式转换成  Java 类型。
- 默认的类型处理器（截取部分）。
  - ![[C:\Users\15271\AppData\Roaming\Typora\typora-user-images\image-20220502232158668.png]]
- 使用方法：实现org.apache.ibatis.type.TypeHandler 接口，  或继承一个很便利的类  org.apache.ibatis.type.BaseTypeHandler，  然 后可以选择性地将它映射到一个JDBC类型。

##### 步骤

1. 定义转换类继承类`BaseTypeHandler<T>`
2. 覆盖4个未实现的方法，其中setNonNullParameter为java程序设置数据到数据库的回调方法，getNullableResult 为查询时  mysql的字符串类型转换成  java的Type类型的方法
3. 在MyBatis核心配置文件中进行注册
4. 测试转换是否正确

```java
需求：一个Java中的Date数据类型，将之存到数据库的时候存成一 个1970年至今的毫秒数，取出来时转换成java的Date，即java的Date与数据库的varchar毫秒值之间转换。
//1.定义转换类继承类`BaseTypeHandler<T>`
public class MyDateTypeHandler extends BaseTypeHandler<Date> {
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType type) {
		preparedStatement.setString(i,date.getTime()+""); 
  }
	public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return new Date(resultSet.getLong(s)); 
  }
	public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return new Date(resultSet.getLong(i)); 
  }
	public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return callableStatement.getDate(i); 
  }
}
```

```
2.注册标签
<typeHandlers>
	<typeHandler handler="com.typeHandlers.MyDateTypeHandler"></typeHandler> 
</typeHandlers>
3.测试
```

#### plugins标签

- MyBatis可以使用第三方的插件来对功能进行扩展，分页助手PageHelper是将分页的复杂操作进行封装，使用简单的方式即 可获得分页的相关数据

##### 步骤

- 导入通用PageHelper的坐标

- 在mybatis核心配置文件中配置PageHelper插件

- 测试分页数据获取

- ```xml-dtd
  //1.导入通用PageHelper的坐标
  //2.在mybatis核心配置文件中配置PageHelper插件
  //注意：分页助手的插件配置在通用mapper之前
  <plugin interceptor="com.github.pagehelper.PageHelper">
  	//指定方言
  	<property name="dialect" value="mysql"/> 
  </plugin>
  ```

- ```java
  测试分页数据获取
  @Test
  public void testPageHelper(){
  	//设置分页参数
  	PageHelper.startPage(1,2);
  	List<User> select = userMapper2.select(null); for(User user : select){
  	System.out.println(user); 
    //其他分页的数据
  	PageInfo<User> pageInfo = new PageInfo<User>(select);
  	System.out.println("总条数："+pageInfo.getTotal());
  	System.out.println("总页数："+pageInfo.getPages());
  	System.out.println("当前页："+pageInfo.getPageNum());
  	System.out.println("每 页 显 示 长 度："+pageInfo.getPageSize());
  	System.out.println("是否第一页："+pageInfo.isIsFirstPage());
  	System.out.println("是 否 最 后 一 页："+pageInfo.isIsLastPage());
    }
  }
  ```

  



### API

#### SqlSessionFactoryBuilder

- SqlSession工厂构建器

- SqlSessionFactory build(InputStream inputStream)

  - 通过加载mybatis的核心文件的输入流的形式构建一个SqlSessionFactory对象

  - ```java
    String resource = "org/mybatis/builder/mybatis-config.xml"; 
    InputStream inputStream = Resources.getResourceAsStream(resource); 
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); 
    SqlSessionFactory factory = builder.build(inputStream);
    ```

  - Resources 工具类在 org.apache.ibatis.io 包中。Resources 类帮助你从类路径下、文件系统或 一个 web URL 中加载资源文件。

#### SqlSessionFactory

- SqlSession工厂对象

- | 方法                            | 说明                                                         |

| ------------------------------- | ------------------------------------------------------------ |
| openSession()                   | 会默认开启一个事务，但事务不会自动提交，也就意味着需要手动提 交该事务，更新操作数据才会持久化到数据库中 |
| openSession(boolean autoCommit) | 参数为是否自动提交，如果设置为true，那么不需要手动提交事务   |

- ```java
  //加载核心配置文件
  InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml"); 
  //        获得sqlSession工厂对象
  SqlSessionFactory  sqlSessionFactory  = new SqlSessionFactoryBuilder().build(resourceAsStream); 
  //         获得sqlSession对象
  SqlSession sqlSession  = sqlSessionFactory.openSession();
  ```

#### SqlSession

- SqlSession 实例在  MyBatis 中是非常强大的一个类。在这里你会看到所有执行语句、提交或回滚事务和获取映射器实例的方法。
- SqlSession是一个会话，相当于JDBC中的一个Connection对象，是整个Mybatis运行的核心。
- SqlSession接口提供了查询，插入，更新，删除方法，Mybatis中所有的数据库交互都由SqlSession来完成，分析和理解
- SqlSession的运行原理是学习整个Mybatis的重点。

| 方法                                                       | 说明                      |
| ---------------------------------------------------------- | ------------------------- |
| sql                                                        |                           |
| <T> T selectOne(String statement, Object parameter)        | sql查询，返回一个对象     |
| <E> List<E> selectList(String statement, Object parameter) | sql查询，返回一个List对象 |
| int insert(String statement, Object parameter)             | sql插入，返回影响行数     |
| int update(String statement, Object parameter)             | sql修改，返回影响行数     |
| int delete(String statement, Object parameter)             | sql删除，返回影响行数     |
| 事务                                                       |                           |
| void commit()                                              | 事务的提交                |
| void rollback()                                            | 事务的回滚                |

### 代理开发

- 采用Mybatis的代理开发方式实现DAO层的开发，这种方式是我们后面进入企业的主流。
- Mapper接口开发方法只需要程序员编写Mapper接口(相当于Dao接口)，由Mybatis框架根据接口定义创建接口的动态代理对象，代理对象的方法体同上边Dao接口实现类方法。
- Mapper接口开发需要遵循的规范

	- 1、Mapper.xml文件中的namespace与mapper接口的全限定名相同
	- 2、Mapper接口方法名和Mapper.xml中定义的每个statement的id相同
	- 3、Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql的parameterType的类型相同
	- 4、Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同

- 使用

	- UserMapper mapper = sqlSession. getMapper(UserMapper.class) ;
List<User> all = mapper. f indAl1();

### 基于注解

- | 注解     | 说明                                  |

| -------- | ------------------------------------- |
| @Select  | 实现查询                              |
| @lnsert  | 实现新增                              |
| @Update  | 实现更新                              |
| @Delete  | 实现删除                              |
| @Result  | 实现结果集封装                        |
| @Results | 可以与@Result一起使用，封装多个结果集 |
| @One     | 实现一对一结果集封装                  |
| @Many    | 实现一对多结果集封装                  |

  增删改查

  ```xml-dtd
  //1.修改MyBatis的核心配置文件，我们使用了注解替代的映射文件，所以我们只需要加载使用了注解的Mapper接口即可
  <mappers>
  	<!--扫描使用注解的类-->
  	<mapper class="com.mapper.UserMapper"></mapper> 
  </mappers>
  //或者指定扫描包含映射关系的接口所在的包也可以
  <mappers>
  	<!--扫描使用注解的类所在的包-->
  	<package name="com.itheima.mapper"></package> 
  </mappers>
  //2.在对应的接口方法上使用注解，实现简单的增删改查
  ```

#### 复杂映射开发

- 实现复杂关系映射之前我们可以在映射文件中通过配置<resultMap>来实现，使用注解开发后，我们可以使用@Results注解 ，@Result注解，@One注解，@Many注解组合完成复杂关系的配置

- | 注解     | 说明                                                         |

| -------- | ------------------------------------------------------------ |
| @Results | 代替的是标签<resultMap>该注解中可以使用单个@Result注解，也可以使用@Result集 合。使用格式：@Results（{@Result（），@Result（）}）或@Results（@Result（）） |
| @Resut   | 代替了<id>标签和<result>标签 @Result中属性介绍:<br />column：数据库的列名 <br />property：需要装配的属性名<br/>one：需要使用的@One 注解（@Result（one=@One）（））） <br />many：需要使用的@Many 注解（@Result（many=@many）（））） |
| @One     | 代替了<assocation> 标签，是多表查询的关键，在注解中 用来指 定子查 询返回 单一对 象。 @One注解属性介绍：<br/>select: 指定用来多表查询的    sqlmapper<br/>使用格式：@Result(column="  ",property="",one=@One(select="")) |
| @Many    | 代替了<collection>标签,  是是多表查询的关键，在注解中用来 指定子 查询返 回对象 集合。 <br />使用格式：@Result(property="",column="",many=@Many(select="")) |

- ```java
  //1. 创建Role实体，User实体,Order实体
  public class User {
  	private int id;
  	private String username;
  	private String password;
  	private Date birthday;
  	//代表当前用户具备哪些订单 
  	private List<Order> orderList; 
    //代表当前用户具备哪些角色
  	private List<Role> roleList; 
  }
  public class Role {
  	private int id; 
    private String rolename;
  }
  public class Order {
  	private int id;
  	private Date ordertime;
  	private double total;
  	//代表当前订单从属于哪一个客户
  	private User user; 
  }
  ```

#### 一对一

![[C:\Users\15271\AppData\Roaming\Typora\typora-user-images\image-20220503002855304.png]]

#### 一对多

![[C:\Users\15271\AppData\Roaming\Typora\typora-user-images\image-20220503003005128.png]]



#### 多对多

![[C:\Users\15271\AppData\Roaming\Typora\typora-user-images\image-20220503003037432.png]]

### 缓存机制

#### 一级缓存

- 一级缓存是SqlSession级别的，通过同一个SqlSession查询的数据会被缓存，下次查询相同的数据，就会从缓存中直接获取，不会从数据库重新访问
- 使一级缓存失效的四种情况:
  - 不同的SqlSession对应不同的—级缓存
  - 同一个SqlSession但是查询条件不同
  - 同一个SqlSession两次查询相同：
    - 两次查询期间执行了任何一次增删改操作
    - 两次查询期间手动清空了缓存

#### 二级缓存

- 二级缓存是SqlSessionFactory级别，通过同一个SqlSessionFactory创建的SqlSession查询的结果会被缓存;此后若再次执行相同的查询语句，结果就会从缓存中获取
- 二级缓存开启的条件:
  - 在核心配置文件中，设置全局配置属性cacheEnabled="true"，默认为true，不需要设置
  - 在映射文件中设置标签`<cache />`
  - 二级缓存必须在SqlSession关闭或提交之后有效
  - 查询的数据所转换的实体类类型必须实现序列化的接口
- 使二级缓存失效的情况:
  - 两次查询之间执行了任意的增删改，会使—级和二级缓存同时失效
缓存同时失效

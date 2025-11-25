# SpringBoot

## 基础篇

### 介绍

- SpringBoot是由Pivotal团队提供的全新框架，其设计目的是用来简化Spring应用的初始搭建以及开发过程
- spring程序缺点

	- 依赖设置繁琐
	- 配置繁琐

- SpringBoot程序优点

	- 起步依赖（简化依赖配置)
	- 自动配置（简化常用工程相关配置)
	- 辅助功能（内置服务器，....)

### 入门解析

- parent

	- SpringBoot中常见项目名称，定义了当前项目使用的所有依赖坐标，以达到减少依赖配置的目的parent

		- 减少依赖配置

- starter

	- 所有SpringBoot项目要继承的项目，定义了若干个坐标版本号（依赖管理，而非依赖)，以达到减少依赖冲突的目的

		- 减少依赖冲突

	- spring-boot-starter-parent各版本间存在着诸多坐标版本不同

- 引导类

	- SpringBoot工程提供引导类用来启动程序
	- SpringBoot工程启动后创建并初始化Spring容器

- 内嵌tomcat

	- 内嵌Tomcat服务器是SpringBoot辅助功能之一
	- 内嵌Tomcat工作原理是将Tomcat服务器作为对象运行，并
	- 将该对象交给Spring容器管理
	- 变更内嵌服务器思想是去除现有服务器，添加全新的服务器

		- 显示层（view）

			- 控制层（Controller）
			- 服务层（service）
			- 数据库操作层（Repository）
			- 实体层（Entity）

### REST开发

- REST (Representational State Transfer)，表现形式状态转换
- 传统风格资源描述形式

	- http:/ /localhost/user/getById?id=1http: / / localhost/user/ saveUser
	- REST风格描述形式

		- http: / / localhost/user/1http: / / localhost/user

	- 优点:

		- 隐藏资源的访问行为，无法通过地址得知对资源是何种操作
		- 书写简化

	- 按照REST风格访问资源时使用行为动作区分对资源进行了何种操作

		- http: / / localhost/users

			- 查询全部用户信息get

		- http: / / localhost/users/1

			- 查询指定用户信息get

		- http: / / localhost/users

			- 添加用户信息post

		- http : / / localhost/users

			- 修改用户信息put

		- http: / / localhost/users/1

			- 删除用户信息delete

	- 注解

		- @RequestParam 

			- @RequestParam用于接收url地址传参或表单传参

		- @PathVariable

			- @PathVariable用于接收路径参数，使用{参数名称}描述路径参数应用

		- @RequestBody

			- @RequestBody用于接收json数据

		- @RestController

			- 类型:类注解
			- 位置:基于SpringMVC的RESTful开发控制器类定义上方
			- 作用:设置当前控制器类为RESTful风格，等同于@Controller与@ResponseBody两个注解组合功能

		- @GetMapping
			@PostMapping 
			@PutMapping
			@DeleteMapping

			- 类型:方法注解
			- 基于SpringMVC的RESTful开发控制器方法定义上方
			- 作用:设置当前控制器方法请求访问路径与请求动作，每种对应一个请求动作，例如@GetMapping对应GET请求

### 基础配置

- 属性配置

  - ```yaml
    server
    	port: 80 #修改服务器端口
    	#关闭运行日志图标（banner）
    	spring.main.banner-mode=off
    	#设置日志相关
    	logging.level.root=debug
    	
    ```

  - 官方文档中参考文档可以查看所有可配置的属性

- 配置文件分类

  - properties（传统格式/默认格式）
  - yml（主流格式）
  - yaml

- 冲突加载优先级

  - properties>yml>yaml

- yaml文件

  - 介绍

  	- YAML (YAML Ain't Markup Language) ,一种数据序列化格式
  	- 容易阅读
  	- 容易与脚本语言交互
  	- 以数据为核心，重数据轻格式YANL文件扩展名
  	- .yml(主流).yaml

  - 语法规则

  	- 大小写敏感
  	
  	- 属性层级关系使用多行描述，每行结尾使用冒号结束
  	
  	- 使用缩进表示层级关系，同层级左侧对齐，只允许使用空格（不允许使用Tab键)
  	
  	- 属性值前面添加空格（属性名与属性值之间使用冒号+空格作为分隔)
  	
  	- `#表示注释`
  	
  	- 数组表示方式：在属性名书写位置的下方使用减号作为数据开始符号，每行书写一个数据，减号与数据间空格分隔
  	
  	  - ```yaml
  	    subject:
  	    	- Java
  	    	- 前端
  	    	- 大数据 
  	    enterprise:
  	    	name: itcast 
  	    	age: 16 
  	    	subject:
  	    		- Java 
  	    		- 前端 
  	    		- 大数据
  	    likes: [王者荣耀,刺激战场] #数组书写缩略格式
  	    users: #对象数组格式
  	    	- name: Tom
  	    	age: 4
  	    	- name: Jerry
  	    	age: 5
  	    users: #对象数组格式二
  	    	-
  	    	name: Tom 
  	    	age: 4
  	    	-
  	    	name: Jerry 
  	    	age: 5
  	    users2: [ { name:Tom , age:4 } , { name:Jerry , age:5 } ] #对象数组缩略格式
  	    ```
  	
  	    

- yaml数据读取

  - 使用@Value读取单个数据，属性名引用方式：${一级属性名.二级属性名……}

    - 使用@Value配合SpEL读取单个数据
    -  如果数据存在多层级，依次书写层级名称即可

  - 在配置文件中可以使用属性名引用方式引用属性,使用${key}获得对应的值，在配置文件里也可以使用

  - 使用引号包裹的字符串,其中的转义字符可以生效

  - 封装全部数据到Environment对象

    - 使用Environment对象封装全部配置信息

    - 使用@Autowired自动装配数据到Environment对象中

    - ```java
      @Autowired
      private Environment env;
      ```

  - 自定义对象封装指定数据

  - 使用@ConfigurationProperties注解绑定配置信息到封装类中

  - 封装类需要定义为Spring管理的bean，否则无法进行属性注入

  - ```java
    enterprise:
    	name: itcast 
      age: 16
    	tel: 4006184000 
      subject:
    		- Java 
        - 前端
        - 大数据
          
    @Component
    @ConfigurationProperties(prefix = "enterprise")
    	public class Enterprise {
      private String name;
    	private Integer age;
    	private String[] subject; 
    }
    
    @RestController
    @RequestMapping("/books") 
    public class BookController {
    	@Autowired
    	private Enterprise enterprise; 
    }
    ```

    


### 整合第三方技术

- 整合MyBatis

### JPA

- 介绍

	- 为什么要用Spring Data

		- 统一持久层，提升开发效率，降低了学习成本

	- JPA全称Java Persistence API(2019年重新命名为Jakarta Persistence APl)，是Sun官方提出的一种ORM规范。O:Object R:Relational Mrmapping作用

		- 1.简化持久化操作的开发工作:让开发者从繁琐的JDBC和SQL代码中解脱出来，直接面向对象持久化操作。
		- 2.Sun希望持久化技术能够统一，实现天下归一:如果你是基于JPA进行持久化你可以随意切换数据库。
		- 规范

			- ORM映射元数据: JPA支持XML和注解两种元数据的形式，元数据描述对象和表之间的映射关系，框架据此将实体对象持久化到数据库表中;如:@Entity、@Table .@Id与@column等注解。

		- JPA的API:用来操作实体对象，执行CRUD操作，框架在后台替我们完成所有的事情，开发者从繁琐的JDBC和SQL代码中解脱出来。

			- 如: entityManager.merge (T t);

		- JPQL查询语言:通过面向对象而非面向数据库的查询语言查询数据，避免程序的SQL语句紧密耦合。

			- 如: from Student s where s.name = ?

	- jpa和jdbc

		- 相同

			- 1.都跟数据库操作有关，JPA是JDBC的升华，升级版。
			- 2.JDBC和JPA都是一组规范接口
			- 3.都是由SUN官方推出的

		- 不同

			- 1.JDBC是由各个关系型数据库实现的，JPA是由ORM框架实现
			- 2.JDBC使用SQL语句和数据库通信。JPA用面向对象方式，通过ORM框架来生成SQL，进行操作。
			- 3.JPA在JDBC之上的，JPA也要依赖JDBC才能操作数据库。

## 应用篇

## 原理篇

## 番外篇


# Java

## Java开发入门

### 介绍

Java是一门面向对象的编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程

### 技术平台

- JavaSE（Java Platform Standard Edition）
- JavaEE（Java Platform Enterprise Edition）
- Java ME（Java Platform Micro Edition）

### java特点

- 简单性
- 面向对象性
- 跨平台性
- 支持多线程

### 名词

- jdk：Java Development Kit
- jvm：Java virtual machine
- jre：java runtime environment

### 环境变量

#### path

path是用来搜索所执行的可执行文件路径的，如果执行的可执行文件不在当前目录下，那就会依次搜索path中设置的路径

#### classpath

classpath是指定你在程序中所使用的类（.class）文件所在的位置

### 运行机制

1.将后缀名为.java源程序进行编译，生成后缀名为.classde 字节码文件
2.java虚拟机将字节码文件进行解释运行，并将结果显示出来

## Java编程基础

### 基本格式

- 修饰符 class 类名{
  程序代码
  }

	- 注意
  1.java中的程序代码都必须放在一个类中
  2.java中的程序代码可分为结构定义语句和功能执行语句，其中结构定义语句用于声明一个类或方法，功能执行语句用于实现具体的功能。
  3.通常都会使用一种良好的格式进行排版
  4.java语言严格区分大小写

### 注释

- 单行注释 //

- 多行注释 /* */

- 文档注释 /** */

### 关键字

- 关键字是编程语言里事先定义好并赋予了特殊含义的单词

	- 注意：
1.所有的关键字都是小写
2.程序中的标识符不能以关键字命名
3.const和goto是保留字关键字，虽然在java中还没有任何意义，但在程序中不能用来做为自定义的标识符。
4.true和goto是保留字关键字，虽然在Java中还没有任何意义，但在程序中不能用来作为自定义的标识符

### 标识符

- 在编程过程中，经常需要在程序中定义一些符号来标记一些名称，如包名、类名、方法名、参数名、变量名等，这些符号被称为标识符

	- 标识符由数字、字母、下划线和美元符号组成，但不能以数字开头，也不能是java中的关键字

### 常量

- 整形常量

	- 整形常量是整数类型的数据，有二进制，八进制，十进制和十六进制4种表示形式
	- 浮点型常量
	- 字符常量
	- 字符串常量
	- 布尔常量
	- null常量

		- null常量只有一个值null，表示对象的引用为空。

### 变量

#### 介绍

在程序运行其间，随时可能产生一些临时数据，应用程序会将这些数据保存在一些内存单元中，每个内存单元都用一个标识符来标识。这些内存单元被称之为变量，定义的标识符就是变量名，内存单元中储存的数据就是变量的值

#### 数据类型

- 基本数据类型

	- 数值型

		- 数值型

			- byte
				short
				int
				long

				- 在位long类型变量复制时如果超出int型取值范围需要加L，未超过则不用

		- 浮点型

			- float double

	- 字符型

		- char

	- 布尔类型

		- boolean

- 引用数据类型

	- 类（class）
接口（interface）
数值
枚举（enum）
注解（annotation）

- 类型转换

  - 自动类型转换

  	- 也叫隐式类型转换，指的是两种数据类型在转换的过程中不需要显式地进行声明
  	- 条件
  1.两种数据类型彼此兼容
  2.目标类型的取值范围大于源类型的取值范围

  - 强制类型转换

  	- 也叫显式类型转换，指的是两种数据类型的转换需要进行显示的声明
  	- 格式：（强制的类型名）表达式

### 运算符

- 表达式：是由数字、运算符、变量等以能求得数值的有意义排列方法所得的组合
- 算术运算符：+ - * / %

	- 浮点数精度问题：浮点数值的最高精确度是17位小数，但在进行算术计算时其精确度远远不如整数。不能直接拿浮点数进行比较
	- 递增++和递减--

		- 前置

			- 先自加1再返回值

		- 后置

			- 先返回值再自加1

- 关系运算符

	- 关系运算符是两个数据进行比较时所使用的运算符
	- > < <= >= == !=

- 逻辑运算符

	- 逻辑运算符是用来进行布尔值运算的运算符，其返回值也是布尔值。常用于多个条件的判断
	- && || ！

- 赋值运算符

	- 把变量赋值给变量的运算符
	- =
+= -=
*= /= %= 乘、除、取模后再赋值

- 优先级

	- 1 小括号
2 一元运算符 ++ -- ！
3 算术运算符
4 关系运算符
5 相等运算符
6逻辑运算符
7 赋值运算符
8 逗号运算符

### 流程控制

在一个程序的执行过程中，各条代码的执行顺序对程序的结果是有直接影响的，很多时候我们都要通过控制代码的执行顺序来实现我们要完成的功能

#### 顺序结构

- 没有特定的语法结构，程序会按照代码的先后顺序，依次执行。

#### 分支结构

- 根据不同的条件，执行不同的路径代码（执行代码多选的过程），从而得到不同的结果
- 三元运算符

	- 条件表达式 ？表达式1：表达式2

- if分支

	- if（条件表达式）
{
 代码块
}
else if（条件表达式）
{
代码块
}
else
{
代码块
}

- switch分支

	- switch（表达式）
{
case value1：
  执行语句1；
    break；
case value2：
  执行语句2；
    break；
default：
  执行语句3
}
	- 注意：
1.表达式经常写成变量
2.case里面值匹配的时候是全等
3.break如果当前的case里面没有break，不会退出switch，直到遇到一个break或执行完剩下的整个break

#### 循环结构

- 被重复执行的语句被称之为循环体，是否继续重复执行，取决于循环的终止条件。由循环体及循环的终止条件组成的语句，被称之为循环语句
- for

	- for（初试化变量；终止条件；变化量）{
循环体
}

- while

	- for（条件表达式）{
	-   循环体
	- }

- do while

	- do
{
循环体
}
while（条件表达式）

- continue和break

### 方法

- 方法就是一段可以重复调用的代码
- 声明

	- 修饰符 返回值类型 方法名（）{
}

- 重载

	- 重载(overloading) 是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。

### 数组

- 定义

	- 数据类型[] 数组名 = new 数据类型[整数]

### 异常

#### 介绍

在Java语言中，将程序执行中发生的不正常情况称为“异常”(开发过程中的语法错误和逻辑错误不是异常)

#### 分类

- Error:Java虚拟机无法解决的严重问题。如:JVM系统内部错误、资源耗尽等严重情况。比如:StackOverflowError和OutOfMemoryError。一般不编写针对性的代码进行处理。
- Exception:其它因编程错误或偶然的外在因素导致的一般性问题，可以使用针对性的代码进行处理。

	- 空指针访问
	- 试图读取不存在的文件
	- 网络连接中断
	- 数组角标越界
	- 编译时异常
	- 运行时异常

#### 继承关系

- Throwable

	- Error
	- Exception

		- 编译时异常

			- ClassNotFoundExeeption
			- CloneNotSupportedException
			- IOException

				- EOFException
				- FileNotFoundException
				- UnknovnHostException

		- 运行时异常

			- RuntimeException

				- AurithmeticException
				- ClassCastException

					- 类型转换异常

				- illegalArgumentException
				- illegalStateException
				- lndexOutOfBoundsException
				- NoSuchElementException
				- NullPointerException
				- NumberFormatException
				- InputMismatchException

#### 操作

- Try{
  }catch{
  }finally{}

- throws

- throw

  - ```java
    throw new Exception("xxxxxxx");
    ```

    

## 面向对象

### 介绍

面向对象(Object Oriented)是软件开发方法，一种编程范式。面向对象的概念和应用已超越了程序设计和软件开发，扩展到如数据库系统、交互式界面、应用结构、应用平台、分布式系统、网络管理结构、CAD技术、人工智能等领域。面向对象是一种对现实世界理解和抽象的方法，是计算机编程技术发展到一定阶段后的产物。

### 特性

- 封装性
- 继承性
- 多态性

### 类和对象

- 类

	- 类是对象的抽象，它用于描述一组对象的共同特征和行为

		- 属性
		- 方法

- 构造方法

	- 定义
1.方法名与类名相同
2在方法名的前面没有返回值类型的声明
3.在方法中不能使用return语句返回一个值，但是可以单独写return语句作为方法的结束
	- 重载

- 对象

	- 对象是类的实体，用于表示现实中该类事物的个体

		- 创建

			- 类名 对象名称=new 类名（）；

- this关键字

	- 指代当前对象，用于在方法中访问对象的其他成员
	- 常用用法

		- 1.通过this关键字可以明确地去访问一个类的成员变量，解决与局部变量名称冲突问题
2.通过this关键字调用成员方法
3.可以在一个构造方法中使用“this（[参数],......）”

			- 在使用this调用类的构造方法时，注意
1.只能在构造方法中使用this调用其他的构造方法，不能在成员方法中使用
2，在构造方法中，使用this调用构造方法的语句必须位于第一行，且只能出现一次

- static关键字

	- 它用于修饰类的成员，如成员变量、成员方法和代码块

		- 声明为static的变量称为静态变量或类变量。可以直接通过类名引用静态变量，也可以通过实例名来引用静态变量，但最好采用前者，因为后者容易混淆静态变量和一般变量。静态变量是跟类相关联的，类的所有实例共同拥有一个静态变量。
		- 声明为static的方法称为静态方法或类方法。静态方法可以直接调用静态方法，访问静态变量，但是不能直接访问实例变量和实例方法。静态方法中不能使用this关键字，因为静态方法不属于任何一个实例。

			- 在一个静态方法中只能访问用static修饰的成员，原因在于没有被static修饰的成员需要先创建对象才能访问，而静态方法在被调用时可以不创建任何对象

- 内部类

	- 成员内部类

		- 成员内部类，是一种可以访问外部类的私有成员或属性。

	- 静态内部类

		- 静态内部类
静态内部类定义在类中，任何方法外，用static定义。
静态内部类只能访问外部类的静态成员。
生成（new）一个静态内部类不需要外部类成员：这是静态内部类和成员内部类的区别。静态内部类的对象可以直接生成：
Outer.Inner in=new Outer.Inner()；
而不需要通过生成外部类对象来生成。这样实际上使静态内部类成为了一个顶级类。可以定义私有静态内部类。

	- 方法内部类

		- 在方法中定义内部类
方法中的内部类在jdk1.8版本之前如果要访问方法的参数和局部变量需要使用final修饰变量和参数。

### 继承

- 在一个现有类的基础上去构建一个新的类，构建出来的新类被称为子类，现有类被称为父类，子类会自动拥有父类所有可继承的属性和方法

	- extends

		- 注意
1.java只支持单继承，不允许多继承
2.多个类可以继承一个父类
3.在java中，多层继承是可以的，一个类的父亲可以再去继承另外的父类
4.在java中，子类和父类是一种相对概念，一个类可以是某个类的父亲的同时，也可以是另一个类的子类

- 重写父类方法

	- 在继承关系中，子类会自动继承父类中定义的方法，但有时在子类中需要对继承的方法进行一些修改，即对父类的方法进行重写

		- 子类重写的方法应与父类中被重写的方法具有相同的方法名，参数列表以及返回值类型

- super关键字

	- 当子类重写了父类的方法后，子类对象将无法访问父类被重写的方法，super则可以用于访问父类的成员

		- super.成员变量
super.成员方法([参数1]，......)
		- 在父类定义了有参的构造函数而，子类的构造方法不使用super([参数1]，......)时会出错，因为此时子类会默认调用父类无参的构造方法，而父类没有定义

			- 解决办法：
1为父类添加无参的构造方法
2.使用super([参数1]，......)调用父类的有参构造方法

### final关键字

- final修饰的类不能被继承
- final修饰的方法不能被子类重写
- final修饰的变量（成员变量和局部变量是常量，只能赋值一次）

### 抽象类

- 抽象方法：java允许在定义方法时不写方法体。不包含方法体的方法为抽象方法，使用abstract关键字修饰

	- 当一个类中包含了抽象方法，该类必须使用abstract关键字修饰，使用abstract关键字修饰的类为抽象类

		- 注意：
1.包含抽象方法的类必须声明为抽象类，但抽象类可以不包含任何抽象方法
2.抽象类不可以实例化，如需调用，则需要创建一个子类，在子类中将抽象类中的抽象方法进行实现，使用extends

- 接口：如果一个抽象类中的所有方法都是抽象的，则可以将这个类用接口来定义，使用interface修饰类

	- [修饰符] class <类名> [extends <超类名>] [implement <接口1>,<接口2>,......]
	- 1.一个接口可以有多个父接口，他们之间用逗号隔开。java使用接口的目的是为了克服继承的限制
	2.接口中的变量默认使用“public static void”修饰
	3.接口中定义的方法默认使用“public abstract”来修饰
	4.如果接口声明为public，则接口中的变量和方法全部为public
	5.接口无法实例化，需要定义一个类，并使用implement关键字实现接口中所有的方法
	- jdk8新特性

		- 接口中可以定义静态方法，使用static锈蚀
			接口中可以定义默认方法，使用default修饰

			- 接口定义的静态方法只能用接口调用

				- 因为一个类可以实现多个接口。如果2个接口具有相同的静态方法，它们都将被继承，编译器就不知道要调用哪个接口。
				- 默认方法可以被继承，如果继承了多个接口，多个接口都定义了多个同样的默认方法，实现类需要重写默认方法不然会报错。

					- 如果还想调用接口中的默认方法
			使用接口.super.默认方法调用

			- 如果子类(或实现类)继承的父类和实现的接口中声明了同名同参数的默认方法，那么子类在没有重写此方法的情况下，默认调用的是父类中的同名同参数的方法。-->类优先原则

### 多态

- 在同一个方法中，这种由于参数类型不同而导致执行效果各异的现象就是多态
- 对象的类型转换

	- 向上转型

		- 将子类对象当做父类使用时不需要任何显式地声明，需要注意是，此时不能通过父类变量去调用子类中的特有方法

	- 向下转型

		- 将父类型当做子类型使用的情况，在java的语言环境中被称为“向下转型”，转型可能出错classcastexception

			- 使用instanceof关键字可以判断一个对象是否为某个类（或接口）的实例或者子类实例
语法格式：对象（或者对象引用变量）instanceof 类（或接口）

## Java 常用类

### String类

#### 介绍

- 是常量，一旦创建，其内容和长度都无法修改，如果需要对一个字符串进行修改，则只能创建新的字符串

- String:字符串，使用一对""引起来表示。

- String声明为finaL的，不可被继承

- String实现了SerializabLe接口:表示字符串是支持序列化的。

- 实现了Comparable接口:表示String可以比较大小

- String内部定义了final char[] value用于存储字符串数据4.String:代表不可变的字符序列。简称:不可变性。
  - 当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值
  - 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值
  - 当调用string的replace()方法修改指定字符或字符串时，也需要重新指定内存区域
- 通过字面量的方式（区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池中。
- 6.字符串常量池中是不会存储相同内容的字符串的。

#### 初始化

- 不使用构造方法（存储在字符串常量池中）：String str = “abc”
- 使用构造方法（存储在堆中）
  - String str = new String（）：创建内容为空的字符串
  - String str = new String（String value）：根据指定的字符串创建对象
  - String str = new String（char[] value）：根据指定的字符数组创建对象
  - string str = new String(char[] a,int startIndex,int count);

```java
string s1 = "hello";
string s2 = "world" ;
string s3 = "he1lo" + "world";
string s4 = s1 + "world";
string s5 = s1 + s2;
string s6 = (s1 + s2).intern();
System.out.print1n(s3==s4);//false
System.out.print1n(s3==s5);//false
System.out.print1n(s4==s5);//false
System.out.println(s3==s6) ;//true
//常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
//只要其中有一个是变量，结果就在堆中
//如果拼接的结果调用intern()方法，返回值就在常量池中
```



#### 方法

| 方法名                                         | 说明                                                         |
| ---------------------------------------------- | ------------------------------------------------------------ |
| int length()                                   | 返回字符串的长度                                             |
| char charAt(int index)                         | 返回某索引处的字符                                           |
| boolean isEmpty()                              | 判断是否是空字符串                                           |
| String toLowerCase()                           | 使用默认语言环境，将 String 中的所有字符转换为小写           |
| String toUpperCase()                           | 使用默认语言环境，将 String 中的所有字符转换为大写           |
| String trim()                                  | 返回字符串的副本，忽略前导空白和尾部空白                     |
| boolean equals(Object obj)                     | 比较字符串的内容是否相同                                     |
| boolean equalslgnoreCase(String anotherString) | 与equals方法类似，忽略大小写                                 |
| String concat(String str)                      | 将指定字符串连接到此字符串的结尾。等价于用加号               |
| int compareTo(String anotrlierString)          | 比较两个字符串的大小                                         |
| String substring(int beginIndex)               | 返回一个新的字符串，它是此字符串的从beginIndex开始截取到最后的一个子字符串。 |
| String substring(int beginIndex, int endIndex) | 返回一个新字符串，它是此字符串从beginIndex开始截取到endIndex(不包含)的一个子字符串。 |
| boolean endsWith(String suffix)                | 测试此字符串是否以指定的后缀结束                             |
| boolean startsWith(String prefix)              | 测试此字符串是否以指定的前缀开始                             |
| boolean startsWith(String prefix, int toffset) | 测试此字符串从指定索引开始的子字符串是否以指定前缀开始       |
| boolean contains(CharSequence s)               | 当且仅当此字符串包含指定的char值序列时，返回 true            |
| int indexOf(String str)                        | 返回指定子字符串在此字符串中第一次出现处的索引               |
| int indexOf(String str, int fromIndex)         | 返回指定子字付串在此子付甲中弟一次出现处的索引，从指定的索引开始 |
| int lastIndexOf(String str)                    | 返回指定子字符串在此字符串中最石边出现处的索引               |
| int lastIndexOf(String str, int fromIndex)     | 返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索 |

- 
  String与基本数据类型、包装类之间的转换。
  - string -->基本数据类型、包装类:调用包装类的静态方法: parsexxx( str)
  - 基本数据类型、包装类-->string:调用string重载的valueof(xxx)

- String 与char[]之间的转换
  - String -->char[:调用Sstring 的toCharArray()
  - char[] --> string:调用string的构造器

- String 与byte[]之间的转换
  - 编码: String --> byte[]:调用string的getBytes()
  - 解码: byte[-->string:调用String的构造器
  - 说明:解码时，要求解码使用的字符集必须与编码时使用的字符集一致，否则会出现乱码。

#### StringBuffer、StringBuilder

| 方法                                                 | 说明                                         |
| ---------------------------------------------------- | -------------------------------------------- |
| StringBuffer append(xxx)                             | 提供了很多的append()方法，用于进行字符串拼接 |
| StringBuffer delete(int start,int end)               | 删除指定位置的内容                           |
| StringBuffer replace(int start, int end, String str) | 把[start,end)位置替换为strStringBuffer       |
| insert(int offset,xxx)                               | 在指定位置插入XXX                            |
| StringBuffer reverse()                               | 把当前字符序列逆转                           |



- String、StringBuffer、StringBuilder三者的异同?

  - 相同:底层使用char[]存储

  - 不同

  - String:不可变的字符序列;

  - StringBuffer:可变的字符序列;线程安全的，效率低;

  - StringBuiLder:可变的字符序列; jdk5.0新增的，线程不安全的，效率高;

  - 源码分析:

    ```java
    String str = new String();//char[] value = new char[0];
    string str1 = new String("abc" );//char[] value = new char[]{ 'a' , 'b' , ' c'};
    StringBuffer sb1 = new StringBuffer();//char[] vaLue = new char[16];底层创建了一个长度16的字符数组
    sb1.append( 'a'); //value[0] = 'a ';
    sb1.append( 'b '); //value[1] = 'b ';
    StringBuffer sb2 = new StringBuffer(" abc" ); //char[] value = new char [ " abc".Length() +16]
    //问题1.System.out.println(sb);//3
    //扩容问题:如果要添加的数据底层数组盛不下了，那就需要扩容底层的数组。
    //默认情况下，扩容为原来容量的2倍(向左移一位)＋2，同时将原有数组中的元素复制到新的数组中。
    //指导意义:开发中建议大家使用: StringBuffer(int capacity)或stringBuffer(int capacity)
    ```

    

#### 正则表达式(Regular Expression)

- 元字符

	- 元字符（Metacharacter），指SHELL直译器或正则表达式（regex）引擎等计算机程序中具有特殊意义的字符。
	- 在POSIX扩展正则表达式里[1]，定义了14个元字符，它们被作为一般的字符使用时，必须要通过“转义”（前面加一个反斜杠“\”）来去除他们本身的特殊意义，这些元字符包括：

开和闭方括号："["和"]"
反斜线："\"
脱字符："^"
美元符号："$"
句号/点："."
竖线/管道符："|"
问号："?"
星号："*"
加号："+"
开和闭 花括号："{"和"}"
开和闭 小括号："("和")"[2][3]
		- 注意：
1.多数正则表达式实现使用单个反斜杠转义特殊字符，以便能使用这些字符本身，但是MySQL要求两个反斜杠（Java自己解释一个，正则表达式库解释另一个）。
2.java匹配是贪婪匹配，默认匹配多的
		-                   元字符

			-   字符匹配符
	
				- []
				- [^]
				- -
				- .
				- \\d
				- \\D
				- \\w
				- \\W
				- \\s
				- \\S
				- 可接受的字符列表
				- 不接受的字符列表 示例
				- 连字符 
				- 匹配除\n以外的任意一个字符
				- 匹配单个数字字符相当于[0-9]
				- 匹配单个非数字字符相当于[^0-9]
				- 平匹配单个数字、大写、小写字符和下划线，相当于[0-9a-zA-Z_]
				- 平匹配单个非数字、大写、小写字符和下划线，相当于[^0-9a-zA-Z_]
				- 匹配任何空白字符(空格，制表符)
				- 匹配任何非空白字符(空格，制表符)，和\\s相反
				- [efgh]
				- [^efgh] 
				-  a-z
				- e..h
				- \\d\\d
				- \\D
				- 子主题 27
				- 子主题 31
				- 子主题 35
				- 子主题 39
				- 匹配e、f、g、h中的任意一个字符
				-  除e、f、g、h外任意一个字符，包括数字和特殊符号
				- 任意单个小写字母
				- 以e开头h结尾中间包含两个任意字符
				- 包含两个数字的字符串
				- 包含两个非数字的字符串
				- 子主题 28
				- 子主题 32
				- 子主题 36
				- 子主题 40
	
			- 限定符（用于指定前面的字符和组合项连续出现多少次）
	
				- 子主题 8
				- 子主题 12
				- 当?紧跟在任何一个其他限制符 (*, +, ?, {n}, {n,}, {n,m}) 后面时，匹配模式是非贪婪的。非贪婪模式尽可能少的匹配所搜索的字符串，而默认的贪婪模式则尽可能多的匹配所搜索的字符串。例如，对于字符串 "oooo"，'o+?' 将匹配单个 "o"，而 'o+' 将匹配所有 'o'。
				- 有abcd中字母组成的任意长度为3的字符串
				- 由abcd中字符组成的任意长度不小于3的字符串
				- 由abcd中字母组成的任意长度不小于3，不大于5的字符串
				- 子主题 7
				- 子主题 11
				- 子主题 11
				- [abcd]{3}
				- [abcd]{3,}
				- [abcd]{3,5}
				- 指定字符重复0次或n次无要求
				- 指定字符重复一次或n次（至少一次）
				- 指定字符重复0次或1次（最多一次）
				- 只能输入n个字符
				- 指定至少n个匹配
				- 指定至少n个但不多于m个匹配
				- *
				- +
				- ？
				- {n}
				- {n，}
				- {n，m}
	
			- 选择匹配符
	
				- 子主题 4
				- ab|cd
				- 匹配 | 之前或之后的表达式
				- |
	
			- 分组组合和反向引用符
	
				- 子主题 4
				- 子主题 11
				- 子主题 12
				- 子主题 22
				- 子主题 23
				- 子主题 24
				- 子主题 3
				- 子主题 9
				- 子主题 10
				- 子主题 19
				- 子主题 20
				- 子主题 21
				- 其后跟着的字母不区分大小写
				- 非命名捕获。捕获匹配的子字符串。编号为零的第一个捕获是由整个正则表达式模式匹配的文本，其他捕获结果则根据左括号的顺序从1开始自动编号
				- 命名捕获。将匹配到的子字符串捕获到一个组名词或编号名称中。用于name的字符串不能包含任何标点符号，并且不能以数字开头。可以使用单引号代替尖括号,列如（？'name'）
				- 匹配pattern但不捕获该匹配的子表达式，即它是一个非捕获匹配，不存储供以后使用的匹配。这对于用“or”字符（|）组合模式部件的情况很有用。例如，“industry|industries”可以写成“industr(?:y|ies)”
				- 它是一个非捕获匹配。例如，“Windows（？=95|98|NT|2000）”匹配“Windows 2000”中的“Windows”,但不匹配“Windows 3.1”中的“Windows”
				- （?=pattern）的取反
它是一个非捕获匹配。例如，“Windows（？！95|98|NT|2000）”不匹配“Windows 2000”中的“Windows”,而匹配“Windows 3.1”中的“Windows”
				- (?i)
				- (pattern)
				- （?<name>pattern）
				- （?:pattern）
				- （?=pattern）
				- （?!pattern）

			- 特殊字符
			- 定位符
	
				- 以至少一个数字开头，后接任意个小写字母的字符串
				- 以至少一个数字开头后接练字符，并以至少一个小写字母结尾的字符串
				- 字符串的边界指定是子串间有空格，或者是目标字符串的结尾位置
				- 与\\b的含义刚刚相反
				- ^[0-9]+[a-z]*
				- ^[0-9]+[a-z]*
				- str\\b
				- str\\B
				- 指定起始字符
				- 指定结束字符
				- 匹配目标字符串的边界
				- 匹配目标字符串的非边界
				- ^
				- $
				- \\b
				- \\B
	
	- 底层实现
	
		- match.find()
	
			- 1.根据指定的规则，定位满足条件的字符串
2.找到后，将字符串的开始的索引记录到match对象的属性 int[] groups中
groups[0]:匹配到的字符的开始坐标，
groups[1]:匹配到的字符的结束坐标+1（string截取是[num1,num2)）
3.oldLast：匹配到的字符的结束坐标+1，即下次执行，从oldLast下标开始匹配

		- matche.group(0)
	
	- 创建模式对象[即正则表达式对象]：
Pattern pattern = new Pattern（regStr）;

		- regStr：匹配模式
	
	- 创建匹配器：
Match match = pattern.match(content);

		- 被匹配的内容

### 时间

| 方法                       | 说明                                                         |
| -------------------------- | ------------------------------------------------------------ |
| System.currentTimeMiLlis() | 返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差。称为时间戳 |
|                            |                                                              |
|                            |                                                              |

System类中的:

### Runtime类和System类

### 包装类

- byte Byte
  char Character
  int Integer
  short Short
  long Long
  float Float
  double Double
  boolean Boolean

  - 自动装箱和自动拆箱，可以让基础数据类型和对应的包装类互相之间赋值，而不需要使用构造器和xxxvalue（）来转换

### Comparator接口

借助包装类的compareto实现接口的compareTo方法

### Comparable接口

### Math

### BigInteger

### BigDecimal

## 枚举类

```java
//自定义枚举类
enum Season1{
//1.提供当前枚举类的对象
  SPRING(“春天","春暖花开")，
  SUMMER(“夏天",“夏日炎炎")， 
	AUTUINN("秋天","秋高气爽"),
  WINTER("冬天","冰天雪地");
//1.声明Season对象的属性: private finaL修饰
  private final string seasonName;
	private final string seasonDesc;
//2.私有化类的构造器,并给对象属性赋值
	private Season1(String seasonName,string seasonDesc){
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}
//其他诉求1:获取枚举类对象的属性使用get方法
```

| 方法名              | 说明                                                         |
| ------------------- | ------------------------------------------------------------ |
| values()            | 返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。 |
| value0f(String str) | 可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对象 |
| toString()          | 返回当前枚举类对象常量的名称                                 |

- 使用enum关键字定义的枚举类实现接口的情况
  - 情况一:实现接口，在enum类中实现抽象方法
  - 情况二:让枚举类的对象分别实现接口中的抽象方法

## Annotation注解

- 说明
  - jdk 5.新增的功能
  - Annotation 其实就是代码里的特殊标记，这些标记可以在编译，类加载，运行时被读取，程序员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。
  - 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。在JavaEE/Android中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁琐代码和XML配置等。
  - 框架=注解+反射+设计模式

| 注解              | 说明                                                         |
| ----------------- | ------------------------------------------------------------ |
| @author           | 标明开发该类模块的作者，多个作者之间使用,分割                |
| @version          | 标明该类模块的版本                                           |
| @see              | 参考转向，也就是相关主题                                     |
| @since            | 从哪个版本开始增加的                                         |
| @param            | 对方法中某参数的说明，如果没有参数就不能写                   |
| @return           | 对方法返回值的说明，如果方法的返回值类型是void就不能写       |
| @exception        | 对方法可能抛出的异常进行说明，如果方法没有用throws显式抛出的异常就不能写 |
| @Override         | 限定重写父类方法,该注解只能用于方法                          |
| @Deprecated       | 用于表示所修饰的元素(类,方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择 |
| @suppressWarnings | 抑制编译器警告                                               |

- 自定义注解:
  - 参照@Suppresswarnings定义注解声明为:@interface
  - 内部定义成员，通常使用value表示
  - 可以指定成员的默认值，使用defauLt定义
  - 如果自定义注解没有成员，表明是一个标识作用。
  - 自定义注解必须配上注解的信息处理流程(使用反射)才有意义.

- 元注解:
  - 对现有的注解进行解释说明的注解
  - 分类
    - Retention:指定所修饰的 Annotation 的生命周期: SOURCE\CLASS（默认行为)\RUNTIME
      - 只有声明为RUNTIME生命周期的注解，才能通过反射获取。
    - Target:用于指定被修饰的Annotation能用于修饰哪些程序元素
    - Documented:表示所修饰的注解在被javadoc解析时，保留下来。
    - Inherited:被它修饰的Annotation将具有继承性。

- 1.8新增注解

- 可重复注解: 在MyAnnotation上声明@Repeatable，成员值为MyAnnotations.cLass
  - MyAnnotation的元注解和MyAnnotations相同。
- 类型注解:
- ELementType.TYPE_PARANETER表示该注解能写在类型变量的声明语句中（如:泛型声明)。
- ELementType.TYPE_USE表示该注解能写在使用类型的任何语句中。

## 集合类

### 背景

- 数组存储多个数据方面的缺点:

  - 一旦初始化以后，其长度就不可修改。
  - 数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，同时效率不高。

  - 获取数组中实际元素的个数的需求，数组没有现成的属性或方法可用

  - 数组存储数据的特点:有序、可重复。对于无序、不可重复的需求，不能满足。

### 组成

- 单列集合

	- Collection

		- List

			- ArrayList
			- LinkedList
			- Vector

		- Set

			- HashSet

				- LinkedHashSet

			- TreeSet

- 双列集合

	- Map

		- HashTable

			- Properties

		- HashMap

			- LinkedHashMap

		- TreeMap

### Collection

Collection是所有单列集合的父接口，用于存储一系列符合某种规则的元素

| 方法                               | 说明                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| add(object e)                      | 将元素e添加到集合中                                          |
| size()                             | 获取添加的元素的个数                                         |
| addAll(Collection coll1)           | 将coll1集合中的元素添加到当前的集合中                        |
| isEmpty()                          | 判断当前集合是否为空                                         |
| void clear()                       | 清空集合                                                     |
| boolean contains(Object obj)       | 通过元素的equals方法来判断是否是同一个对象                   |
| boolean containsAll(Collection c)  | 也是调用元素的equals方法来比较的。拿两个集合的元素挨个比较。 |
| boolean remove(Object obj)         | 通过元素的equals方法判断是否是要删除的那个元素。只会删除找到的第一个元素 |
| boolean removeAll(Collection coll) | 取当前集合的差集取两个集合的交集                             |
| boolean retainAll(Collection c)    | 把交集的结果存在当前集合中，不影响c                          |
| boolean equals(Object obj)         | 集合是否相等                                                 |
| Object[] toArray()                 | 转成对象数组                                                 |
| hashCode()                         | 获取集合对象的哈希值                                         |
| iterator()                         | 返回迭代器对象，用于集合遍历                                 |

```java
//生成迭代器
Iterator iterator = coll.iterator();
//使用迭代器遍历
while(iterator.hasNext()){
	system.out. print1n(iterator.ndxt());
  iterator.remove();删除集合元素
}
```



#### List

存储有序的、可重复的数据。-->“动态”数组,替换原有的数组

向list中添加的数据，其所在的类一定要重写equals()

##### ArrayList

- 内部封装了一个长度可变的数组对象，当存入的元素超过数组长度时，ArrayList会在内存中分配一个更大的数组来存储这些元素，可将ArrayList看做一个长度可变的数组
- 类似顺序表，适合查询，但不适合做大量的增删操作
- 作为List接口的主要实现类;线程不安全的，效率高;底层使用object[] elementDatq存储
- 源码分析
  - jdk7的空参构造器默认创建长度为10的Object[]数组，当添加导致底层eLementData数组容量不够时，则扩容。默认情况下，扩容为原来的容量的1.5倍（原来的长度+原来的长度向右移动一位），同时需要将原有数组中的数据复制到新的数组中。
  - jdk8的空参构造器初始化是{}，当第一次调用add操作的时候，底层才创建了长度为10的Object[] 数组，添加和扩容与jdk8无异。
  - jdk7中的ArrayList的对象的创建类似士单例的饿汉式，而jdk8中的ArrayList的对家的创建类似于单例的懒汉式，延迟了数组的创建，节省内容

##### LinkedList

- 内部维护了一个双向循环链表，链表中的每一个元素都使用引用的方式来记住它的前一个元素和后一个元素，从而可以将所有的元素彼此连接起来

- 不适合查询，适合做大量的增删操作

  

##### Vector

- 作为List接口的古老实现类;线程安全的，效率低;底层使用Object[]存储
- 默认创建为10的Object[]数组，扩容时为两倍

| 方法                                       | 说明                                      |
| ------------------------------------------ | ----------------------------------------- |
| void add(int index, Object ele)            | 在index位置插入ele元素                    |
| boolean addAll(int index, Collection eles) | 从index位置开始将eles中的所有元素添加进来 |
| Object get(int index)                      | 获取指定index位置的元素                   |
| int indexOf(Object obj)                    | 返回obj在集合中首次出现的位置             |
| int lastlndexOf(Object obj)                | 返回obj在当前集合中末次出现的位置         |
| Object remove(int index)                   | 移除指定index位置的元素，并返回此元素     |
| Object set(int index, Object ele)          | 设置指定index位置的元素为ele              |
| List subList(int fromIndex, int tolndex)   | 返回从fromIndex到tolndex                  |



#### Set

- 元素无序，不可重复
- 向Set中添加的数据，其所在的类一定要重写hashcode()和equals()，重写的hashcode()和equals()尽可能保证一致：相等的对象必须具有相等的散列

##### HashSet

- 说明
  - 作为Set接口的主要实现类;线程不安全的;可以存储nuLL值
  - 无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值决定的。
  - 不可重复性:保证添加的元素按照equals()判断时，不能返回true.即:相同的元素只能添加一个。
- 添加元素
  - 我们向HashSet中添加元素a,首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置（即为:索引位置），判断数组此位置上是否已经有元素:
    - 如果此位置上没有其他元素，则元素a添加成功。
    - 如果此位置上有其他元素b(或以链表形式存在的多个元素），则比较元素a与元素b的hash值:
      - 如果hash值不相同，则元素α添加成功。元素a与已经存在指定索引位置上数据以链表的方式存储。
        - jdk 7 ∶元素a放到数组中，指向原来的元素。
        - jdk 8 ∶原来的元素在数组中，指向元素d
      - 如果hash值相同，进而需要调用元素a所在类的equLas()方法:
        - equals()返回true,元素α添加失败
        - equals()返回false,则元素a添加成功。元素a与已经存在指定索引位置上数据以链表的方式存储。

###### LinkedHashSet

- 作为HashSet的子类;遍历其内部数据时，可以按照添加的顺序遍历
- 在添加数据的同时，每个数据还维护了两个引用，记录此数据的前一个元素和后一个元素
  - 对于频繁的遍历操作，LinkedHashSet优于HashSet

##### TreeSet

- 必须是相同的对象
- 可以按照添加对象的指定属性，进行排序。
- 自然排序中，比较两个对象是否相同的标准为: compareTo()返回0.不再是equals()方法

### Map

- Map接口是一种双列集合，它的每个元素都包含一个键对象key和值对象value，键和值对象之间存在一种对应关系，称为映射。只要指定了key，就能确定唯一的value

  - 键唯一，如果相同，则覆盖原来的值
  - hashMap的key所在的类要重写equaLs( )和hashcode()，TreeMap重写Compile或CompileTo方法。hashMap的value所在的类要重写equaLs( )
  - Map中的key:无序的、不可重复的，使用Set存储所有的key
  - Map中的value:无序的、可重复的，使用coLlection存储所有的value一个键值对: key-value构成了一个Entry对象。
  - Map中的entry:无序的、不可重复的，使用Set存储所有的entry

| 方法                                  | 说明                                                         |
| ------------------------------------- | ------------------------------------------------------------ |
| void put（Object key，Object value）  | 将指定的值与此映射中的指定键关联（可选操作）                 |
| Object get（Object key）              | 返回指定键所映射的值：如果此映射不包含该键映射的值，则返回null |
| void putAll(Map m)                    | 将m中的所有key-value对存放到当前map中                        |
| Object remove(Object key)             | 移除指定key的key-value对，并返回value                        |
| void clear()                          | 清空当前map中的所有数据                                      |
| boolean containsKey（Object key）     | 如果此映射包含指定键的映射关系，则返回true                   |
| boolean containsValue（Object value） | 如果此映射将一个或多个键映射到指定值，则返回true             |
| int size()                            | 返回map中key-value对的个数                                   |
| boolean isEmpty()                     | 判断当前map是否为空                                          |
| boolean equals(Object obj)            | 判断当前map和参数对象obj是否相等                             |
| Set keySet()                          | 返回所有key构成的Set集合                                     |
| Collection values()                   | 返回所有value构成的Collection集合                            |
| Set entrySet()                        | 返回所有key-value对构成的Set集合                             |

#### hashMap

- 元素无序

- 作为Map的主要实现类;线程不安全的，效率高;存储null的key和value
- HashMap的底层实现原理?以jdk7为例说明:
  - HashMap map = new HashMap():在实例化以后，底层创建了长度是16的一维数组Entry[] table....可能已经执行过多次put. . .
  - map.put( key1, value1):首先，调用key1所在类的hashCode()计算key1哈希值，此哈希值经过某种算法计算以后，得到在Entry数组中的存放位置
    - 如果此位置上的数据为空，此时的key1-value1添加成功。----1
    - 如果此位置上的数据不为空，(意味着此位置上存在一个或多个数据(以链表形式存在)),比较key1和已经存在的一个或多个数据的哈希值:
      - 如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功。  ----2
      - 如果key1的哈希值和已经存在的某一个数据(key2-value2)的哈希值相同，继续比较:调用key1所在类的equals(key2)
        - 如果equals()返回faLse:此时key1-value1添加成功。----3
        - 如果equals()返回true:使用value1替换相同key 的vaLue值。
  - 关于2和3:此时key1-vaLue1和原来的数据以链表的方式存储。
  - 在不断的添加过程中，会涉及到扩容问题，当超出临界值且要存放的位置非空，默认的扩容方式:扩容为原来容量的2倍，并将原有的数据复剧过来。
- jdk8相较于jdk7在底层实现方面的不同:
- new HashMap():底层没有创建一个长度为16的数组
- jdk 8底层的数组是:Node[].而非Entry[ ]
- 首次调用put()方法时，底层创建长度为16的数组
- jdk7底层结构只有:数组+链表。jdk8中底层结构:数组+链表+红黑树。
  - 当数组的某一个索引位置上的元素以链表形式存在的数据个数>8且当前数组的长度〉64时,此时此索引位置上的所有数据改为使用红黑树存储。
  - DEFAULT_INITIAL_CAPACITY : HashMap的默认容量，16
  - DEFAULT_LOAD_FAcroR: HashMap的默认如载因子:0.75
  - threshold:扩容的临界值,=容量\*填充因子:16\*0.75 =>12
  - TREETFY_THRESHOLD: 表中链表长度大于该默认值，转化为红黑树:8
  - MIN_TREEIFY_CAPACITY:表中的Node被树化时最小的hash表容量:64

##### LinkedhashMap

- 内部使用双向链表维护元素，元素有序
- hashMap的子类
- 在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素对于频繁的遍历操作，此类执行效率高于HashMap 。

#### Treemap

保证按照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序表示相同

#### Hashtable

- 作为古老的实现类;线程安全的，效率低;不能存储null的key和value
- Properties

	- 主要用来存储应用的配置项

### Collections

- Collections堤一个操作 Set、List利l Map等集合的工具类
- Collections 中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法
- Collections类中提供了多个synchronizedXxx()方法，该方法可使将指定集合包装成线程 同步的集合，从而可以解决多线程并发访问集合时的线程安全问题

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| reverse(List)                                                | 反转List中元素的顺序                                         |
| shuffle(List)                                                | 对List集合元素进行随机排序                                   |
| sort(List)                                                   | 根据元素的自然顺序对指定List集合元素按升序排序               |
| sort(List，Comparator)                                       | 根据指定的Comparator产生的顺序对 List集合元素进行排序>swap(List，int,int):将指定list集合中的i处元素和j处元素进行交换 |
| Object max(Collection)                                       | 根据元素的自然顺序，返回给定集合中的最大元素                 |
| Object max(Collection，Comparator)                           | 根据Comparator指定的顺序，返回给定集合中的最大元素           |
| Object min(Collection)                                       | 根据元素的自然顺序，返回给定集合中的最小元素                 |
| Object min(Collection，Comparator)                           | 根据Comparator指定的顺序，返回给定集合中的最小元素           |
| int frequency(Collection，Object)                            | 返回指定集合中指定元素的出现次数                             |
| void copy(List dest,List src)                                | 将src中的内容复制到dest中                                    |
| boolean replaceAlIl(List list，Object oldVal，Object newVal) | 使用新值替换List对象的所有旧值                               |

```java
//报异常: Indexout0fBoundsException("Source does not fit in dest")
//List dest = new ArrayList( );
List dest = Arrays.asList(new object[list.size()]);
collections.copy(dest,list);
```



## 泛型

- 背景：对象存入集合后，集合会“忘记”这个对象的类型，取出时当做Object，程序无法确定一个结合中的元素到底是什么类型
- 形式：<类型名1，类型名2，>
- 限定方法操作的数据类型，解决了抛出异常的问题，便于及准确的发现问题
- 说明
  - 所谓泛型，就是允许在定义类、接口时通过一个标识表示类中某个属性的类型或者是某个方法的返回值及参数类型。这个类型参数将在使用时（例如,继承或实现这个接口，用这个类型声明变量、创建对象时）确定（即传入实际的类型参数，也称为类型实参）。
  - 从JDK1.5以后，Java引入了“参数化类型（ Parameterized type)”的概念允许我们在创建集合时再指定集合元素的类型，正如: List<String>，这表明该List只能保存字符串类型的对象。
  - JDK1.5改写了集合框架中的全部接口和类，为这些接口、类增加了泛型支持,从而可以在声明集合变量、创建集合对象时传入类型实参。
  - 如果实例化时，没有指明泛型的类型。默认类型为java.Lang.0bject类型。
  - 泛型不同的引用不能相互赋值。
  - 泛型在实例化的时候被确定，所以静态方法中不能使用类的泛型。
  - 泛型方法，可以声明为静态的。原因:泛型参数是在调用方法时确定的。并非在实例化类时确定。
  - 异常类不能声明为泛型
  - 类A是类B的父类，G<A>和G<B>二者不具备子父类关系，二者是并列关系。


```java
//泛型方法:在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。
//换句话说，泛型方法所属的类是不是泛型类都没有关系。
public <E> List<E> test(E[] arr){
  
}
```

### 通配符？

- 类A是类B的父类，G<A>和G<B>是没有关系的，二者共同的父类是:G<?>

- 读取List<?>的对象list中的元素时，永远是安全的，因为不管list的真实类型 是什么，它包含的都是Object。
- 写入list中的元素时，不行。因为我们不知道c的元素类型，我们不能向其中 添加对象。
  -  唯一的例外是null，它是所有类型的成员。
- 注意
  - 注意点1：编译错误：不能用在泛型方法声明上，返回值类型前面<>不能使用?
  - 注意点2：编译错误：不能用在泛型类的声明上
  - 注意点3：编译错误：不能用在创建对象上，右边属于创建集合对象

#### 限制

- <? extends Number>     (无穷小  , Number]
  - 只允许泛型为Number及Number子类的引用调用
  - 通配符指定上限
    - 上限extends：使用时指定的类型必须是继承某个类，或者实现某个接口，即<=
- <? extends Comparable>
  - 只允许泛型为Number及Number父类的引用调用
  - 通配符指定下限
    - 下限super：使用时指定的类型不能小于操作的类，即>=

## IO流

- 将通过不同输入输出设备之间的数据传输抽象表述为“流”，程序允许通过流的方式和输入输出设备通信
- I/O是Input/Output的缩写，I/O技术是非常实用的技术，用于处理设备之间的数据传输。如读/写文件，网络通讯等。
- Java程序中，对于数据的输入/输出操作以“流(stream)”的方式进行。

### File类

- 该类封装了一个路径，提供了一系列方法用于操作该路径所指向的文件

| public File(String pathname)            | 以pathname为路径创建File对象，可以是绝对路径或者相对路径，如果pathname是相对路径，则默认的当前路径在系统属性user.dir中存储。 |
| --------------------------------------- | ------------------------------------------------------------ |
| public File(String parent,String child) | 根据指定的一个字符串类型的父路径和一个字符串类型的子路径（包括文件名称）创建一个File对象 |
| File（File Parent，String child）       | 根据指定的File类的父路径和字符串类型的子路径（包括文件名称）创建一个File对象 |
| boolean exists():                       | 判断目录或文件是否存在                                       |
| boolean createNewFile()                 | 创建新文件，成功返回true，失败返回false                      |
| String getName()                        | 获得文件的名字                                               |
| String getAbsolutePath()                | 获得文件的绝对路径                                           |
| String getParent()                      | 获得父路径                                                   |
| boolean canRead()                       | 是否能读                                                     |
| boolean canWrite()                      | 是否能写                                                     |
| boolean isFile()                        | 是否是文件                                                   |
| boolean isDirectory()                   | 是否是目录                                                   |
| boolean isAbsolute()                    | 判断File对象对应的文件或目录是否是绝对路径                   |
| long lastModified()                     | 返回1970年1月1日0时0分0秒到文件最后修改时间的毫秒数          |
| long length()                           | 文件的长度                                                   |
| String[] list()                         | 取出当前File对象所代表目录下的所有子文件和目录               |
| File[] listFiles()                      | 返回一个包含File对象所有子文件和目录的File数组               |

- String[] list()

  - 1.调用list()方法传入FilenameFilter文件过滤器对象
  - 2.取出当前File对象所代表目录下的所有子文件和目录
  - 3.对于每一个子目录或文件，都会调用文件过滤器对象的accept(File dir,String name)方法，并把代表当前目录的File对象以及这个子目录或文件的名字作为参数dir和name传递给方法

### 流

- 分类
  - 按流向：输入流和输出流
  - 按操作单位：字节流和字符流
  - 流的角色：节点流和处理流

| 抽象类       | 字节流(节点流)   | 缓存流(处理流)       |
| ------------ | ---------------- | -------------------- |
| InputStream  | FileinputStream  | BufferedInputStream  |
| outputStream | FileoutputStream | BufferedoutputStream |
| Reader       | FileReader       | BufferedReader       |
| Writer       | Filewriter       | Bufferedwriter       |

- 操作数据
  - 字节流

    - 所有文件都是以二进制（字节）形式存在的，IO流中针对字节的输入输出提供了一系列的流，统称为字节流
    - java.io.InputStream

    	- 1.int read（）：从输入流读取一个8个的字节，把它转换为0~255之间的整数，并返回这个整数
    2.int read（btye[] b）：从输入流读取若干字节，把他们保存的参数b指定的字节数组中，返回的整数表示读取的字节的数目
    3.int read（byte[] b，int off,int len）：从输入流读取若干字节，把它们保存到参数b指定的字节数组中，off指定字节数组开始保存数据的起始下表，len表示读取的字节数目
    4.void close()：关闭此输入流并释放与该流关联的所有系统资源
    	- InputStream

    		- ByteArrayInputStream
    		- FileInputSteam
    		- FilterInputStream

    			- 字节文件读写流
    			- BufferedInputStream

    				- 字节缓冲流
    				- 内部定义了一个大小为8192的字节数组。
    		当调用read()或者write()方法读写数据时，首先将读写的数据存入定义好的字节数组，然后将字节数组的数据一次性读写到文件中

    			- DataInputStream

    		- PipedInputStream
    		- SequenceInputStream
    		- ObjectInputStream

    - java.io.OutputStream

    	- void write(int b)：向输出流写入一个字节
    	void write (byte[] b)：把参数b指定的字节数组的所有字节写到输出流
    	void write(byte[] b, int off,int len)将指定byte数组从偏移量off开始的len个字符写入输出流
    	void flush()：刷新此输出流并强制写出所有缓冲的输出字节
    	void close()：关闭此输出流并释放与该流关联的所有系统资源
    	- OutputStream

    		- ByteArrayoutputStream
    		- FileOutputSteam
    		- FilterOutputStream

    			- 指定的文件不存在，会先创建文件。如果文件存在，则会首先清空文件中的内容，再进行写入
    			- BufferedOutputStream
    			- DataOutputStream

    		- PipedOutputStream
    		- ObjectOutputStream
  - 字符流

    - java.io.Reader

      - 字符输入流，用于从某个源设备读取字符
      - Reader

      	- BufferedReader

      		- readLine():该方法用于一次读取一行文本
      		- LineNumberReader

      	- CharArrayReader
      	- InputStreamReader

      		- 将一个字节输入入流转换为字符输入流
      		- FileReader

      			- 对文件进行操作

      	- PipedReader

    - java.io.Writer

      - 字符输出流，用于向某个目标设备写入字符
      - Writer

        - BufferedWriter
        - CharArrayWriter
        - InputStreamWriter

        	- 将一个字节输出流转换成字符输出流
        	- FileWriter

        		- 指定的文件不存在，会先创建文件。如果文件存在，则会首先清空文件中的内容，再进行写入
        - PipedWriter
        - PrintWriter
  - 缓冲流
  - 数据流

#### 对象流

- 用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可 以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
- ObjectInputStream和OjbectOutputSteam
- 序列化：用ObjectOutputStream类保存基本类型数据或对象的机制
- 反序列化：用ObjectInputStream类读取基本类型数据或对象的机制
- ObjectOutputStream和ObjectInputStream不能序列化static和transient修饰的成员变量
- 序列化要求：
  - 如果需要让某个对象支持序列化机制，则必须让对象所属的类及其属性是可 序列化的，为了让某个类是可序列化的，该类必须实现Serializable和Externalizable接口，否则，会抛出NotSerializableException异常
  - 凡是实现Serializable接口的类都有一个表示序列化版本标识符的静态变量：
    - private static final long serialVersionUID;
    - serialVersionUID用来表明类的不同版本间的兼容性。简言之，其目的是以序列化对象 进行版本控制，有关各版本反序列化时是否兼容。
    - 如果类没有显示定义这个静态常量，它的值是Java运行时环境根据类的内部细节自 动生成的。若类的实例变量做了修改，serialVersionUID 可能发生变化。故建议， 显式声明。
  - 其内容属性也必须是可序列化的，基本数据类型默认支持序列化

### NIO

Path、Paths和Files类

### 字符集

- ASCII:美国标准信息交换码。
- 用一个字节的7位可以表示。IS08859-1:拉丁码表。欧洲码表用一个字节的8位表示。(l
- GB2312:中国的中文编码表。最多两个字节编码所有字符
- GBK:中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
- Unicode:国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示
- UTF-8:变长的编码方式，可用1-4个字节来表示一个字符。

## 网络编程

- IP和端口号

- 网络协议
  - 传输层协议
    - 传输控制协议TCP(Transmission Control Protocol)
      - 使用TCP协议前，须先建立TCP连接，形成传输数据通道
      -  传输前，采用“三次握手”方式，点对点通信，是可靠的
      -   TCP协议进行通信的两个应用进程：客户端、服务端。
      -   在连接中可进行大数据量的传输
      -   传输完毕，需释放已建立的连接，效率低
    - 用户数据报协议UDP(User Datagram Protocol)
      - 将数据、源、目的封装成数据包，不需要建立连接
      - 每个数据报的大小限制在64K内
      - 发送不管对方是否准备好，接收方收到也不确认，故是不可靠的
      - 可以广播发送
      - 发送数据结束时无需释放资源，开销小，速度快

## 反射

### Reflection

- Reflection（反射）是被视为动态语言的关键，反射机制允许程序在执行期 借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内 部属性及方法。

- 加载完类之后，在堆内存的方法区中就产生了一个Class类型的对象（一个 类只有一个Class对象），这个对象就包含了完整的类的结构信息。我们可 以通过这个对象看到类的结构。这个对象就像一面镜子，透过这个镜子看 到类的结构，所以，我们形象的称之为：反射。
- 是指在运行时去获取一个类的变量和方法信息。然后通过获取到的信息来创建对象，调用方法的一种机制。由于这种动态性，可以极大的增强程序的灵活性，程序不用在编译期就完成确定，在运行期仍然可以扩展

### 类加载器

- 类加载

	- 当程序要使用某个类时，如果该类还未被加载到内存中，则系统会通过类的加载，类的连接，类的初始化这三个步骤来对类进行初始化。如果不出现意外情况，VM将会连续完成做三个步骤，所以有时也把这三个步骤统称为类加载或者类初始化

		- 类的加载

			- 就是指将class文件读入内存，并为之创建一个java.lang.Class对象任何类被使用时，系统都会为之建立一个java.lang.Class对象

		- 类的连接

			- 验证阶段:用于检验被加载的类是否有正确的内部结构，并和其他类协调一致准
			- 备阶段:负责为类的类变量分配内存，并设置默认初始化值
			- 解析阶段:将类的二进制数据中的符号引用替换为直接引用

		- 类的初始化

			- .在该阶段，主要就是对类变量进行初始化
			- 类的初始化步骤

				- 假如类还未被加载和连接，则程序先加载并连接该类
				- 假如该类的直接父类还未被初始化，则先初始化其直接父类
				- 假如类中有初始化语句，则系统依次执行这些初始化语句

			- 类的初始化时机:

				- 创建类的实例l调用类的类方法
				- 访问类或者接口的类变量，或者为该类变量赋值
				- 使用反射方式来强制创建某个类或接口对应的java.lang.Class对象初始化某个类的子类
				- 直接使用java.exe命令来运行某个主类

- 类加载器的作用

	- 负责将.class文件加载到内存中，并为之生成对应的java.lang.Class对象

		- 虽然我们不用过分关心类加载机制，但是了解这个机制我们就能更好的理解程序的运行

- JVM的类加载机制

	- 全盘负责:就是当一个类加载器负责加载某个Class时，该Class所依赖的和引用的其他Class也将由该类加载器负责载入，除非显式使用另外一个类加载器来载入
	- 父类委托:就是当一个类加载器负责加载某个Class时，先让父类加载器试图加载该Class，只有在父类加载器无法加载该类时才尝试从自己的类路径中加载该类
	- 缓存机制∶保证所有加载过的Class都会被缓存，当程序需要使用某个Class对象时，类加载器先从缓存区中搜索该Class，只有当缓存区中不存在该Class对象时，系统才会读取该类对应的二进制数据，并将其转换成Class对象，存储到缓存区

- ClassLoader:是负责加载类的对象

	

	- Java运行时内置类加载器
	
		- Bootstrap class loader:它是虚拟机的内置类加载器，通常表示为null，并且没有父null
		- Platform class loader:平台类加载器可以看到所有平台类，平台类包括由平台类加载器或其祖先定义的Java SE平台API,其实现类和JDK特定的运行时类
		- System class loader:它也被称为应用程序类加载器，与平台类加载器不同。系统类加载器通常用于定义应用程序类路径，模块路径和JDK特定工具上的类
	- ClassLoader中的两个方法
	
		- static ClassLoader getSystemClassLoader():返回用于委派的系统类加载器
		- ClassLoader getParent():返回父类加载器进行委派

### Class

#### 介绍

- 在Object类中定义了public final Class getClass()，此方法 将被所有子类继承，方法返回值的类型是一个Class类， 此类是Java反射的源头，实际上所谓反射 从程序的运行结果来看也很好理解，即： 可以通过对象反射求出类的名称。
- 对象照镜子后可以得到的信息：某个类的属性、方法和构造器、某个类到底实现了哪些接 口。对于每个类而言，JRE 都为其保留一个不变的 Class 类型的对象。一个 Class 对象包含 了特定某个结构(class/interface/enum/annotation/primitive type/void/[])的有关信息。
- Class本身也是一个类
- Class 对象只能由系统建立对象
- 一个加载的类在  JVM 中只会有一个Class实例
- 一个Class对象对应的是一个加载到JVM中的一个.class文件
- 每个类的实例都会记得自己是由哪个  Class 实例所生成
- 通过Class可以完整地得到一个类中的所有被加载的结构
- Class类是Reflection的根源，针对任何你想动态加载、运行的类，唯有先获得相应的 Class对象

| 方法名                                           | 说明                                                         |
| ------------------------------------------------ | ------------------------------------------------------------ |
| static Class  forName(String name)               | 返回指定类名  name 的  Class 对象                            |
| Object newInstance()                             | 调用缺省构造函数，返回该Class对象的一个实例                  |
| getName()                                        | 返回此Class对象所表示的实体（类、接口、数组类、基本类型 或void）名称 |
| Class [] getInterfaces()                         | 获取当前Class对象的接口                                      |
| ClassLoader getClassLoader()                     | 返回该类的类加载器                                           |
| Class getSuperclass()                            | 返回表示此Class所表示的实体的超类的Class                     |
| Constructor[] getConstructors()                  | 返回一个包含某些Constructor对象的数组                        |
| Field[] getDeclaredFields()                      | 返回Field对象的一个数组                                      |
| Method getMethod(String name,Class … paramTypes) | 返回一个Method对象，此对象的形参类型为paramType              |

#### 获取Class类的实例

- 若已知具体的类，通过类的class属性获取，该方法最为安全可靠， 程序性能最高
  - 实例：Class clazz = String.class;
- 已知某个类的实例，调用该实例的getClass()方法获取Class对象
  - 实例：Class clazz = “str”.getClass();
- 已知一个类的全类名，且该类在类路径下，可通过Class类的静态方 法forName()获取，可能抛出ClassNotFoundException
  - 实例：Class clazz = Class.forName(“java.lang.String”);
- ClassLoader cl = this.getClass().getClassLoader();
- Class clazz4 = cl.loadClass(“类的全类名”);

### 动态代理

- 使用一个代理将对象包装起来, 然后用该代理对象取代原始对象。任何对原 始对象的调用都要通过代理。代理对象决定是否以及何时将方法调用转到原 始对象上。
- 动态代理是指客户通过代理类来调用其它对象的方法，并且是在程序运行时 根据需要动态创建目标类的代理对象。

## JDBC

### 介绍

JDBC全称是Java数据库连接（Java Database Connectivity），它是一套用于执行SQL语句的Java API。应用程序可通过这套API连接到关系数据库，并使用SQL语句来完成对数据库中数据的查询、新增、更新和删除等操作

不同数据库在内部处理数据的方式不同，如果直接使用数据库产商的访问接口操作数据库，可移植性就会变得很差，JDBC要求厂商按照一定的规范提供驱动，在程序中由JDBC和具体的数据库驱动联系，提高代码的通用性

### 常用API

- Driver接口

	- DriverManager类

		- static void registerDriver（Driver driver）：该方法用于向DriverManager中注册给定的JDBC驱动程序
static Connection getConnection（String url，String user，String pwd）：该方法用于建立和数据库的连接，并返回表示连接的Connection对象

- Connection接口

	- DatabaseMetaData_getMetaData():该方法用于返回表示数据库的元数据的DatabaseMetaData对象
Statement createStatement（）：用于创建一个Statement对象来将SQL对象发送到数据库
Prepared Statement prepareStatement（String sql）：用于创建一个PreparedStatement对象来将参数化的SQL语句发送到数据库
CallableStatement prepareCall（String sql）用于创建一个CallableStatement对象来调用数据库存储过程

- Statement接口

	- boolean execute(String sql)用于执行各种SQL语句，该方法返回一个boolean类型的值，如果为true，表示所执行的SQL语句有查询结果，可通过Statement的getResultSet（）方法获得查询结果
int executeQuery（）：用于执行insert、update和delete语句。该方法返回一个int类型的值，表示数据库中受该SQL语句影响的记录条数
ResultSet executeQuery（String sql）：用于执行SQL中的select语句，该方法返回一个表示查询结果的ResultSet对象
	- PreparedStatement接口

		- PreparedStatement是Statement的子接口，用于执行预编译的SQL语句。该接口扩展了带有参数SQL语句的执行操作，应用该接口的SQL语句可以使用占位符“？”来代替其参数，然后通过setXXX（）方法为SQL语句参数赋值
		- 常用方法

			- int executeUpdate（）：在此PreparedStatement对象中执行SQL语句，该语句必须是一个DML语句或者是无返回内容的sql语句，如DDL语句
ResultSet executeQuery（）：在此PreparedStatement对象中执行SQL查询，该方法返回的是ResultSet对象
void setInt（int parameterIndex，int x）：将指定参数设置为给定的int值
void setFloat（int parameterIndex，Float x）：将指定参数设置为给定的Float 值
void setString（int parameterIndex，String x）：将指定参数设置为给定的String 值
void setDate（int parameterIndex，Date x）：将指定参数设置为给定的Date 值
void addBatch（）：将一组参数添加到此PreparedStatement对象的批处理命令中
void setCharacterStream（int parameterIndex，java.io.Reader reader， int length）：将指定的输入流写入数据库的文本字段
void seBinaryStream（int parameterIndex，java.io.inputStream x， int length）：将指定的输入流写入数据库的文本字段

- ResultSet接口

	- String getString（int columnindex）：通过index获得指定字段的stirng类型的值
String getString（int columnName）：通过字段名获得指定字段的stirng类型的值
int getInt（int columnName）：通过字段名获得指定字段的int 类型的值
int getint （int columnindex）：通过index获得指定字段的int 类型的值
Date getDate（int columnindex）：通过index获得指定字段的Date类型的值
Date getDate（int columnName）：通过字段名获得指定字段的Date类型的值
boolean next（）：将游标从当前位置向下移一行
boolean absolute（int row）：将游标移动到此ResultSet对象的指定行
void afterLast（）：将游标移动到此ResultSet对象的末尾，最后一行之后
void beforeFirst（）：将游标移动到此ResultSet对象的开头，第一行之前
boolean previous（）：将游标移动到此ResultSet对象的上一行
boolean last（）：将游标移动到此ResultSet独享的租后一行

## 多线程

### 程序、进程和线程

- 程序(progran)是为完成特定任务、用某种语言编写的一组指令的集合。即指一段静态的代码，静态对象。
- 进程(process)是程序的一次执行过程，或是正在运行的一个程序。是一个动态的过程：有它自身的产生、存在和消亡的过程。——生命周期
  - 程序是静态的，进程是动态的
  - 进程作为资源分配的单位，系统在运行时会为每个进程分配不同的内存区域
- 线程(thread)，进程可进一步细化为线程，是一个程序内部的一条执行路径。
  - 若一个进程同一时间并行执行多个线程，就是支持多线程的
  - 线程作为调度和执行的单位，每个线程拥有独立的运行栈和程序计数器(pc)，线程切换的开销小
  - 一个进程中的多个线程共享相同的内存单元/内存地址空间→它们从同一堆中分配对家，可以访问相同的变量和对象。这就使得线程间通信更简便、高效。但多个线程操作共享的系统资源可能就会带来安全的隐患。
  - 一个Java应用程序java.exe，其实至少有三个线程:main()主线程，gc垃圾回收线程，异常处理线程。当然如果发生异常，会影响主线程。
- 并行与并发
  - 并行:多个CPU同时执行多个任务。比如:多个人同时做不同的事。
  - 并发:一个CPU(采用时间片)同时执行多个任务。比如:秒杀、多个人做同一件事。

### Thread类

每个线程都是通过某个特定Thread对象的run()方法来完成操作的，经常把run()方法的主体称为线程体
通过该Thread对象的start()方法来启动这个线程，而非直接调用run()

#### 创建

```java
//1．创建一个继承于Thread类的子类
class MyThread extends class MyThread extends Thread {
//2．重写Thread类的run()@override
	public void run() {
	}
}
public static void main(string[] args) {
	//3．创建Thread类的子类的对象
	MyThread t1 = new MyThread();
	//4.通过此对象调用start()
   t1.start();
}
```

#### 方法

| 方法名                        | 说明                                                         |
| :---------------------------- | :----------------------------------------------------------- |
| void start()                  | 启动线程，并执行对象的run()方法run():线程在被调度时执行的操作 |
| run()                         | 通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中 |
| String getName()              | 返回线程的名称                                               |
| void setName(String name)     | 设置该线程名称                                               |
| static Thread currentThread() | 返回当前线程。在Thread子类中就是this，通常用于主线程和Runnable实现类 |
| yield()                       | 释放当前cpu的执行权                                          |
| join()                        | 在线程α中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才结束阻塞状态 |
| stop()                        | 已过时。当执行此方法时，强制结束当前线程。                   |
| sleep(Long millitime)         | 让当前线程*睡眠'指定的millitime毫秒。在指定的millitime毫秒时间内，当前线程是阻塞状态。 |
| isAlive()                     | 判断当前线程是否存洁                                         |
| getPriority()                 | 返回线程优先值                                               |
| setPriority(int newPriority)  | 改变线程的优先级                                             |
| wait()                        | 一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器。   |
| notify（）                    | 一旦执行此方法，就会唤醒被wait的一个线程。如果有多个线程被wait，就唤醒优先级高的那个。 |

#### 优先级

同优先级线程组成先进先出队列（先到先服务），使用时间片策略。

对高优先级，使用优先调度的抢占式策略

- 注意
  - 线程创建时继承父线程的优先级
  - 低优先级只是获得调度的概率低，并非一定是在高优先级线程之后才被调用

### Runnable接口

```java
//1．创建一个实现了Runnable接口的类
class MThread implements Runnable{
//2．实现类去实现Runnable中的抽象方法: run()
	public void run() {
		system.out.println(i);
		}
	}
}
public static void main(string[] args) {
	//l3.创建实现类的对象
	MThread mThread = new MThread();
	//4、将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
  Thread t1 = new Thread(mThread);
	//5．通过Thread类的对象调用start()
  t1.start();
}
```

### 创建线程的两种方式的比较

开发中优先选择实现Runnable接口的方式原因:

1. 实现的方式没有类的单继承性的局限性
2. 实现的方式更适合来处理多个线程有共享数据的情况。

联系: public class Thread implements Runnable
相同点:两种方式都需要重写run(),将线程要执行的逻辑声明在run()中。

### Callable接口

```java
//1.创建一个实现Callable的实现类
class NumThread implements Callable{
//2.实现caLL方法，将此线程需要执行的操作声明在calL()中@Override
	public object call( ) throws Exception {
		方法体
	}
}
public static void main(String[] args) {
	//3.创建callabLe接口实现类的对象
	NumThread numThread = new NumThread();
	//4.将此callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
  FutureTask futureTask = new FutureTask(numThread);
	//5.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并start()
	new Thread(futureTask).start();
	//6.get()返回值即为FutureTask构造器参数callable实现类重写的calL()的返回值。
  object sum = futureTask.get();
}
```

- 优点
  - caLL()可以有返回值的。
  - caLL()可以抛出异常，被外面的操作捕获，获取异常的信息
  - callable是支持泛型的

### 线程池

```java
public static void main(string[] args) {
	//1．提供指定线程数量的线程池
	ExecutorService service = Executors.newFixedThreadPool( nThreads: 10);
  //2.执行指定的线程的操作。需要提供实现Runnable接口或callable接口实现类的对象
	service.execute(new NumberThread());//适合适用于Runnable
	service.submit(Callable callable);//适合使用于Callable
  //3.关闭连接池
	service.shutdown() ;I
}
```

- 好处:
  - 1.提高响应速度（(减少了创建新线程的时间)
  - 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建)
  - 3.便于线程管理ThreadPoolExecutor
    - corePoolsize:核心池的大小
    - maximumPoolsize:最大线程数
    - keepAliveTime:线程没有任务时最多保持多长时间后会终止

### 生命周期

- 新建:当一个Thread类或其子类的对象被声明并创建时，新生的线程对象处于新建状态
- 就绪:处于新建状态的线程被start()后，将进入线程队列等待CPU时间片，此时它已具备了运行的条件，只是没分配到CPU资源
- 运行:当就绪的线程被调度并获得CPU资源时,便进入运行状态，run()方法定义了线程的操作和功能
- 阻塞:在某种特殊情况下，被人为挂起或执行输入输出操作时，让出 CPU并临时中止自己的执行，进入阻塞状态
- 死亡:线程完成了它的全部工作或线程被提前强制性地中止或出现异常导致结束

### 同步

解决线程的安全问题

#### synchronized

##### 同步代码块

```java
synchronized(同步监视器){
//需要被同步的代码
}
```

说明:

1. 操作共享数据的代码，即为需要被同步的代码
2. 共享数据:多个线程共同操作的变量。比如: ticket就是共享数据。
3. 同步监视器，俗称:锁。任何一个类的对象，都可以充当锁。类也是对象
   - 要求:多个线程必须要共用同一把锁。

##### 同步方法

```java
//同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
synchronized 方法名（）{ //不是静态的同步方法的同步锁为this
  //需要被同步的代码
}
static synchronized 方法名（）{ //静态的同步方法的同步锁为类本身
  //需要被同步的代码
}
```

#### ReentrantLock

```java
//1.实例化ReentrantLock
private ReentrantLock lock = new ReentrantLock();
//2.使用lock（）方法启用同步锁
lock.lock();
//3.使用unlock（）方法关闭同步锁
lock.unlock();
```

- synchronized 与Lock的异同

  - 相同:二者都可以解决线程安全问题
  - 不同: 
  
    - synchronized机制在执行完相应的同步代码以后，自动的释放同步监视器
  - Lock需要手动的启动同步(Lock())，同时结束同步也需要手动的实现(unlock( )

- 说明
  - wait()，notify() ， notifyALL()三个方法必须使用在同步代码块或同步方法中。
  - wait()，notify()，notifyALL()三个方法的调用者必须是同步代码块或同步方法中的同步监视器，否则，会出现ILlegalMonitorstateException异常
  - wait()，notify()，notifyALl()三个方法是定义在java.Lang.object类中。
- sLeep()和wait()的异同
  - 相同：一旦执行方法，都可以使得当前的线程进入阻塞状态。
  - 相同
    - 两个方法声明的位置不同: Thread类中声明sLeep() , object类中声明wait()
    - 调用的要求不同: sleep()可以在任何需要的场景下调用。wait()必须使用在同步代码块中
    - 关于是否释放同步监视器:如果两个方法都使用在同步代码块或同步方法中，sLeep()不释放同步监视器，wait()释放同步监视器

## java8新特性

### lambda表达式

- （）-> {}：左侧参数，右侧方法体
左边：
参数可以省略类型：类型推断
只有一个参数时可以省略（）
->：箭头运算符
只有一条语句时，可以省略{}和return

- 本质上是一接口个实例
  接口需要是函数式接口

  - 函数式接口：只有一个抽象方法的接口是抽象方法

    - Consumer<T> :对类型T的对象应用操作，包括方法，void accept（T t）；
    Supplier<T>:返回类型为T的对象，包含方法：T get()
    Function <T,R> 对类型为T的对象应用操作，并返回结果。结果是R类型的对象。包含方法：R apply(T t)
    Predicate<T> 确定类型为T的对象是否满足某约束，并返回boolean值，包含方法boolean test(T t)

#### 语法

- 语法格式一：无参，无返回值

  - Runnable r1 =O -> {System.out.printIn("Hello Lambda!");};

- 语法格式二：Lambda 需要一个参数，但是没有返回值。

  - Consumer<String> con = (String str)-> {System.out.println(str)};

- 语法格式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”

  - Consumer<String> con = (str) -> {System.out.println(str);};

- 语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略

  - Consumer<String> con = str -> {System.out.println(str);};

- 语法格式五：Lambda 需要两个或以上的参数，多条执行语句，并且可以有返回值

  - ```java
    Comparator<Integer> com = (x，y)->{
    	System.out.printIn("实现函数式接口方法!");
      return Integer.compare(x,y);
    }
    ```

    

- 语法格式六：当  Lambda 体只有一条语句时，return 与大括号若有，都可以省略

  - Comparator<Integer> com = (x,y)-> Integer.compare(x, y);

### 函数式接口

- 只包含一个抽象方法的接口，称为函数式接口。
- 你可以通过 Lambda 表达式来创建该接口的对象。（若 Lambda 表达式 抛出一个受检异常(即：非运行时异常)，那么该异常需要在目标接口的抽 象方法上进行声明）。
- 我们可以在一个接口上使用 @FunctionalInterface 注解，这样做可以检 查它是否是一个函数式接口。同时 javadoc 也会包含一条声明，说明这个 接口是一个函数式接口。

| 函数式接口                                             | 参数类型            | 返回类型            | 用途                                                         |
| ------------------------------------------------------ | ------------------- | ------------------- | ------------------------------------------------------------ |
| Consumer<T> 消费型接口                                 | T                   | void                | 对类型为T的对象应用操作，包含方法void accept(T t)            |
| Supplier<T> 供给型接口                                 | 无                  | T                   | 返回类型为T的对象，包含方法：T get()                         |
| Function<T, R> 函数型接口                              | T                   | R                   | 对类型为T的对象应用操作，并返回结果。结 果是R类型的对象。包含方法：R apply(T t) |
| Predicate<T> 断定型接口                                | T                   | boolean             | 确定类型为T的对象是否满足某约束，并返回 boolean 值。包含方法：boolean test(T t) |
| BiFunction<T, U, R>                                    | T, U                | R                   | 对类型为   T,  U 参数应用操作，返回   R 类型的结 果。包含方法为：  R apply(T t, U u); |
| UnaryOperator<T> (Function子接口)                      | T                   | T                   | 对类型为T的对象进行一元运算，并返回T类型的 结果。包含方法为：T apply(T t); |
| BinaryOperator<T> (BiFunction 子接口)                  | T, T                | T                   | 对类型为T的对象进行二元运算，并返回T类型的 结果。包含方法为：    T apply(T t1, T t2); |
| BiConsumer<T, U>                                       | T, U                | void                | 对类型为T, U 参数应用操作。 包含方法为：    void accept(T t, U u) |
| BiPredicate<T,U>                                       | T,U                 | boolean             | 包含方法为：    boolean test(T t,U u)                        |
| ToIntFunction<T> ToLongFunction<T> ToDoubleFunction<T> | T                   | int long<br/>double | 分别计算int、long、double值的函数                            |
| IntFunction<R> LongFunction<R> DoubleFunction<R>       | int long<br/>double | R                   | 参数分别为int、long、double 类型的函数                       |



### 方法、构造器和数组引用

- 方法

  - 当要传递给lambda体的操作，已经有实现的方法了，可以使用方法引用
  要求：函数式接口中的抽象方法的形参列表和返回值类型与方法引用单独方法的形参列表和返回值类型相同（适用于1和2）
  - 实现函数式接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致！
  	- 形式：
  	对象::实例方法名 1
  	类::静态方法名  2
  	类：实例方法名  3
- 构造器

  - 类名：：new

  	- 函数式接口中的抽象方法的形参列表和构造器的形参列表一致。抽象方法的返回值类型即为构造器所属的类的类型
- 数组

  - 类名[]：：new

### Stream API

- StreamAPI（java.util.stream）把真正的函数式编程风格引入java中
Stream是java8中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作
Stream是数据渠道，用于操作数据源所生成的元素序列
- 注意：
1.Stream自己不会存储元素。
2.Stream不会改变源对象。相反。他们会返回一个持有结果的新Stream。
3.Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
- 操作步骤
1.创建Stream
2.中间操作
3终止操作

	- 创建Stream

		- 集合：
default Stream<E> stream() ：返回一个顺序流
default Stream<E> parallelStream():返回一个并行流
数组：
static Stream<T> stream(T[] array):返回一个流
Stream的of()
Stream.of(T...values):返回一个流

	- 中间操作

		- 筛选与切片

			- filter（Predicate p）接受lambda，从流中筛选出符合条件的元素
			- limit(long maxSize):截断流，使其元素不超过给定的数量
			- skip(long maxSize):跳过元素，返回一个扔掉前n个元素的流。若流中元素不足n个，则返回空流
			- distinct（）筛选，通过流生成元素的hashCode()和equals()去除重复元素

		- 映射

			- map(Function f)：接受一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的流
mapToDouble，mapToInt，mapToLong
flatMap（Function f）接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流

		- 排序

			- sorted（）：产生一个新流，按照自然顺序排序
sorted（Comparator com）：产生一个新流，其中按比较器顺序排序

	- 终止操作

		- 匹配与查找

			- allMatch（Predicate p）：检查是否匹配所有元素
anyMatch（Predicate p）：检查是否至少匹配一个元素
noneMatch（predicate p）检查是否没有匹配所有元素
findFirst（）：返回第一元素
findAny（）：返回当前流中的任意元素
			- 遍历

				- forEach（Consumer cons）：内部迭代

			- 统计

				- count（）：返回流中元素的个数
max（Comparator c）：返回流中最大值
min（Comparator c）：返回流中最小值

		- 归约

			- reduce(T iden,BinaryOperator b):可以将流中的元素反复结合起来，得到一个值.返回T
reduce(BinaryOperator b):可以将流中元素反复结合起来，得到一个值。返回Optional<T>

		- 收集

			- collector(Collector c)：将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法

				- Collectors方法
toList()：把流中元素收集到List
toSet（）：把流中元素收集到Set
toCollection（）：把流中元素收集到创建的集合
counting（）计算流中元素的个数
summingInt（）:对流中元素的整数属性求和
averagingInt（）：计算流中元素Integer属性的平均值
summarizingInt()：收集流中Integer属性的统计值。如：平均值

## Idea

### Idea常用配置

- Appearance & Behavior

	- appearance

		- 设置主题，IntelliJ，Darcula，Windows

			- riaway.com：主题设置

- Editor

	- General

		- change font size with ctrl+Mouse wheel：ctrl+左键切换字体大小
		- Auto Import

			- Insert import on paste：选择Always
Add unambiguous imports on the fly 打钩
Optimize imports on the fly 打钩

		- Appearance

			- show method separators：打钩

		- Code Completion

			- Match case： 选择All letters

		- Editor Tabs

			- Show tabs in：选择Multiple rows

	- Code Editor

		- show quick documentation on mouse move 打钩

	- Font

		- 设置默认的字体、大小和行间距

	- File Encodings

		- 设置为UTF-8

- Build，Execution，Deployment

	- Compiler

		- Buildproject automatically 打钩
Compile independent modules in parallel 打钩

- 常用的快捷键的使用

	- 执行(run)：alt+r
提示补全    (Class Name Completion)：alt+/
单行注释：ctrl + /
多行注释：ctrl + shift + /
向下复制一行    (Duplicate Lines)：ctrl+alt+down
删除一行或选中行    (delete line)：ctrl+d
向下移动行(move statement down)：alt+down
向上移动行(move statement up)：alt+up
向下开始新的一行(start new line)：shift+enter
向上开始新的一行    (Start New Line before current)：ctrl+shift+enter
如何查看源码    (class)：ctrl  +  选 中 指 定 的 结 构     或 ctrl + shift + t
万能解错/生成返回值变量：alt + enter
退回到前一个编辑的页面    (back)：alt + left
进入到下一个编辑的页面(针对于上条) (forward)     ：alt + right
查看继承关系(type hierarchy)：F4
格式化代码(reformat code)：ctrl+shift+F
提示方法参数类型(Parameter Info)：ctrl+alt+/
复制代码：ctrl + c
撤销：ctrl + z
反撤销：ctrl + y
剪切：ctrl + x
粘贴：ctrl + v
保存：ctrl + s
全选：ctrl + a
选中数行，整体往后移动：tab
选中数行，整体往前移动：shift + tab
查看类的结构：类似于  eclipse 的  outline：ctrl+o
重构：修改变量名与方法名(rename)：alt+shift+r
大写转小写/小写转大写(toggle case)：ctrl+shift+y
- 生成构造器/get/set/toString ：alt +shift + s
		查看文档说明(quick documentation)：F2
	收起所有的方法(collapse all)：alt + shift + c
	打开所有方法(expand all)：alt+shift+x
	打开代码所在硬盘文件夹(show in explorer)：ctrl+shift+x
	生成 try-catch 等(surround with)：alt+shift+z
	局部变量抽取为成员变量(introduce field)：alt+shift+f
	查找/替换(当前)：ctrl+f
	查找(全局)：ctrl+h
	查找文件：double Shift
	查看类的继承结构图(Show UML Diagram) :ctrl + shift + u
	查看方法的多层重写结构(method hierarchy):ctrl+alt+h
	添加到收藏(add to favorites):ctrl+alt+f
	抽取方法(Extract Method):alt+shift+m
	打开最近修改的文件(Recently Files):ctrl+E
	关闭当前打开的代码栏(close):ctrl + w
	关闭打开的所有代码栏(close all):ctrl + shift + w
	快速搜索类中的错误(next highlighted error):ctrl + shift + q
	选择要粘贴的内容(Show in Explorer):ctrl+shift+v
	查找方法在哪里被调用(Call Hierarchy):ctrl+shift+h


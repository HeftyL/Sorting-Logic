# 绪论

程序 = 数据结构+算法

## 数据结构

- 背景：早期计算机主要用于数值计算，现在计算机主要用于非数值计算，包括处理字符、表格和图像等具有一定结构的数据
  - 数据内容存在着某种关联，只有分清楚数据的内在联系，合理地组织数据，才能对他们进行有效的处理，设计出高效的算法
- 目的
  - 合理的组织数据，高效的处理数据
  - 如何把现实世界的问题信息化，将信息存进计算机。同时还要实现对数据结构的基本操作
- 定义：定义了数据的逻辑结构、数据的运算。也就是定义了一个数据结构。

- 实现：确定了存储结构，才能实现数据结构
- 步骤
  1. 定义逻辑结构（(数据元素之间的关系）
  2. 定义数据的运算（针对现实需求,应该对这种逻辑结构进行什么样的运算)
  3. 确定某种存储结构，实现数据结构，并实现一些对数据结构的基本运算

### 基本概念

- 数据
  - 数据是信息的载体，是描述客观事物属性的数、字符及所有能输入到计算机中并被计算机程序识别和处理的符号的集合。数据是计算机程序加工的原料。
- 数据元素、数据项
  - 数据元素是数据的基本单位，通常作为一个整体进行考虑和处理
  - 一个数据元素可由若干数据项组成，数据项是构成数据元素的不可分割的最小单位。
- 数据结构、数据对象
  - 数据结构是相互之间存在一种或多种特定关系的数据元素的集合。
  - 数据对象是具有相同性质的数据元素的集合，是数据一个子集。
- 数据类型、抽象数据类型
  - 数据类型是一个值的集合和定义在此集合上的一组操作的总称。
    - 原子类型。其值不可再分的数据类型。
    - 结构类型。其值可以再分解为若干成分（分量）的数据类型。
  - 抽象数据类型(Abstract Data Type，ADT）是抽象数据组织及与之相关的操作。
    - ADT用数学化的语言定义数据的逻辑结构、定义运算。与具体的实现无关。
    - 定义一个ADT，就是定义了数据的逻辑结构、数据的运算。也就是定义了一个数据结构。

### 三要素

- 逻辑结构
  - 线性结构
    - 数据元素之间是一对一的关系。
    - 除了第一个元素，所有元素都有唯一前驱;除了最后一个元素，所有元素都有唯一后继
    - 数组、队列、栈、广义表
  - 非线性结构
    - 树
      - 数据元素之间是一对多的关系
    - 图
      - 数据元素之间是多对多的关系
- 物理结构（存储结构）
  - 确定一种存储结构，就意味着在计算机中表示出数据的逻·辑结构。存储结构不同，也会导致运算的具体实现不同。确定了存储结构，才能实现数据结构
  - 数据的存储结构会影响存储空间分配的方便程度
  - 数据的存储结构会影响对数据运算的速度
  - 顺序存储
    - 顺序存储。把逻辑上相邻的元素存储在物理位置上也相邻的存储单元中，元素之间的关系由存储单元的邻接关系来体现。
  - 非顺序存储
    - 链式存储
      - 链式存储。逻辑上相邻的元素在物理位置上可以不相邻，借助指示元素存储地址的指针来表示元素之间的逻辑关系。
    - 索引存储
      - 索引存储。在存储元素信息的同时，还建立附加的索引表。索引表中的每项称为索引项，索引项的一般形式是（关键字，地址)
    - 散列存储
      - 散列存储。根据元素的关键字直接计算出该元素的存储地址，又称哈希（Hash）存储
- 数据的运算
  - 数据的运算--施加在数据上的运算包括运算的定义和实现。运算的定义是针对逻辑结构的，指出运算的功能;运算的实现是针对存储结构的，指出运算的具体操作步骤。

## 算法

### 目的

- 如何处理数据结构，以解决实际问题

### 特性

1. 有穷性。一个算法必须总在执行有穷步之后结束，且每一步都可在有穷时间内完成。
   - 注意：算法必须是有穷的，而程序可以是无穷的
2. 确定性。算法中每条指令必须有确切的含义，对于相同的输入只能得出相同的输出。
3. 可行性。算法中描述的操作都可以通过已经实现的基本运算执行有限次来实现。
4. 输入。一个算法有零个或多个输入，这些输入取自于某个特定的对象的集合。
5. 输出。一个算法有一个或多个输出，这些输出是与输入有着某种特定关系的量。

### “好”算法的特质

- 正确性。算法应能够正确地解决求解问题。

- 可读性。算法应具有良好的可读性，以帮助人们理解。
  - 注:算法可以用伪代码描述，甚至用文字描述，重要的是要“无歧义“地描述出解决问题的步骤
- 健壮性。输入非法数据时，算法能适当地做出反应或进行处理，而不会产生莫名其妙的输出结果。
- 高效率与低存储量需求
  - 执行速度快。
  - 时间复杂度低。
  - 空间复杂度低

### 效率的度量

#### 时间复杂度

- 问题规模足够大时就会暴露出问题

![image-20220506212202207](C:\Users\15271\AppData\Roaming\Typora\typora-user-images\image-20220506212202207.png)

- 最坏时间复杂度
- 平均时间复杂度
- 最好时间复杂度

#### 空间复杂度

# 线性表

![image-20220506220113292](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220506220113292.png)



## 定义（逻辑结构）

-  线性表Linear List
- 线性表是具有相同数据类型的n（n≥0）个数据元素的有限序列，其中n为表长，当n = 0时线 性表是一个空表。
  - 相同数据类型：每个数据元素所 占空间一样大
  - 序列：有次序
- 若用L命名线性表，则其一般表示为L = (a1, a2, … , ai, ai+1, … , an)
- 概念：
  - ai 是线性表中的“第i个”元素在线性表中的位序
  -  a1 是表头元素；an 是表尾元素。
  - 除第一个元素外，每个元素有且仅有一个直接前驱；除最后一个元素外，每个元素有且仅 有一个直接后继

## 基本操作（运算）

### 目的

- 团队合作编程，你定义的数据结构能让别人能够很方便的使用（封装）

- 将常用的操作/运算封装成函数，避免重复工作，降低出错风险

- | 分类 | 方法名              | 作用                                                        |
  | ---- | ------------------- | ----------------------------------------------------------- |
  | 创建 | InitList(&L)        | 初始化表。构造一个空的线性表L，分配内存空间。               |
  | 销毁 | DestroyList(&L)     | 销毁操作。销毁线性表，并释放线性表L所占用的内存空间。       |
  | 插入 | ListInsert(&L,i,e)  | 插入操作。在表L中的第i个位置上插入指定元素e。               |
  | 删除 | ListDelete(&L,i,&e) | 删除操作。删除表L中第i个位置的元素，并用e返回删除元素的值。 |
  | 查找 | LocateElem(L,e)     | 按值查找操作。在表L中查找具有给定关键字值的元素。           |
  |      | GetElem(L,i)        | 按位查找操作。获取表L中第i个位置的元素的值。                |
  | 其他 | Length(L)           | 求表长。返回线性表L的长度，即L中数据元素的个数。            |
  |      | PrintList(L)        | 输出操作。按前后顺序输出线性表L的所有元素值。               |
  |      | Empty(L)            | 判空操作。若L为空表，则返回true，否则返回false。            |

- 帮助
  - 对数据的操作（记忆思路） —— 创销、增删改查
  - C语言函数的定义 ——      <返回值类型>  函数名 (<参数1类型> 参数1，<参数2类型> 参数2，……)
  - 实际开发中，可根据实际需求定义其他的基本操作
  - 函数名和参数的形式、命名都可改变（Reference：严蔚敏版《数据结构》） 
  - 什么时候要传入引用“&” —— 对参数的修改结果需要“带回来”

## 实现(物理结构)

### 顺序表

- 用顺序存储的方式实现线性表
  - 顺序存储。把逻辑上相邻的元素存储在物理 位置上也相邻的存储单元中，元素之间的关系由存储单元的邻接关系来体现。
- 包括两个部分，实现逻辑结构和基本操作
- ![image-20220508210607859](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508210607859.png)

#### 定义

![image-20220508210446294](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508210446294.png)

![image-20220507213838148](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507213838148.png)

##### 静态分配

![image-20220507214000406](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507214000406.png)

![image-20220507214042196](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507214042196.png)

###### 注意

![image-20220507214244407](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507214244407.png)

![image-20220507214340277](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507214340277.png)

##### 动态分配

![image-20220507215456757](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507215456757.png)

![image-20220507215538238](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507215538238.png)

##### 特点

- 随机访问，，即可以在 O(1) 时间内找到第 i 个元素。
  - 代码实现：data[i-1]; 静态分配、动态分配都一样
- 存储密度高，每个节点只存储数据元素
- 拓展容量不方便（即便采用动态分配的方式实现，拓展长度的时间复杂度也比较高） 
- 插入、删除操作不方便，需要移动大量元素

#### 插入删除

![image-20220507235537982](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507235537982.png)

##### 插入

![image-20220508201730922](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508201730922.png)

![image-20220508201814832](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508201814832.png)

![image-20220508201841062](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508201841062.png)

##### 插入时间复杂度

![image-20220508202002539](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202002539.png)

##### 删除

![image-20220508202035204](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202035204.png)

![image-20220508202105821](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202105821.png)

##### 删除时间复杂度

![image-20220508202150440](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202150440.png)

#### 查找

![image-20220508202244170](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202244170.png)

##### 按位查找

![image-20220508202344202](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202344202.png)

![image-20220508202430170](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202430170.png)

![image-20220508202520920](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202520920.png)

![image-20220508202540979](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202540979.png)

##### 按位查找时间复杂度

![image-20220508202625108](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202625108.png)

##### 按值查找

![image-20220508202638569](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202638569.png)

![image-20220508202731913](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202731913.png)

![image-20220508202757799](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202757799.png)

##### 按值查找时间复杂度

![image-20220508202949908](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202949908.png)

### 单链表

- 首元结点、头结点、头指针
  - 首元结点是指链表中存储第一个数据元素a1的节点
  - 头结点是在首元结点之前附设的一个结点，其指针域指向首元结点。头结点的数据域可以不存储任何信息，也可以存储与数据元素类型相同的其他附加信息。例如，当数据元素为整数型时，头结点的数据域中科存放该线性表的长度。
    - 作用
      1. 便于首元结点的处理
         - 增加了头结点后，首元结点的地址保存在头结点（即其“前驱”结点）的指针域中，则对链表的第一数据元素的操作与其他数据元素相同，无需进行特殊处理。
      2. 便于空表和非空表的统一处理
         - 当链表不设头结点时，假设L为单链表的头指针，它应该指向首元结点，则当单链表长度n为0的空表时，L指针为空，判定空表的条件为`L==NUll`，而单链表长度n不为空时，除首元结点外的其他结点的判空条件是`L->nxet==null`
         - 当链表设头结点时,无论链表是否为空，头指针都是指向头结点的非空指针，判空条件都是`L->nxet==null`
  - 头指针是指指向链表的第一结点的指正。若链表设有头结点，则头指针所指结点为线性表的头结点；若链表不设头结点，则头指针所指结点为该线性表的首元结点

![image-20220508210700977](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508210700977.png)

#### 定义

![image-20220508210754968](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508210754968.png)

![image-20220508210856980](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508210856980.png)

![image-20220508210915989](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508210915989.png)

![image-20220508212652739](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508212652739.png)

![image-20220508212733687](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508212733687.png)

##### 不带头结点

![image-20220508212809438](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508212809438.png)

##### 带头结点

![image-20220508213232084](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508213232084.png)

##### 创建多个结点

![image-20220511234725741](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511234725741.png)



###### 尾插法















#### 插入和删除

![image-20220508213509108](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508213509108.png)

![image-20220511202907121](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511202907121.png)

##### 按位序插入

###### 带头结点

![image-20220511203102998](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511203102998.png)

![image-20220511203211019](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511203211019.png)

###### 不带头结点

![image-20220511203433030](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511203433030.png)

![image-20220511203504029](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511203504029.png)

##### 指定结点插入

###### 后插

![image-20220511203927787](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511203927787.png)

###### 前插

![image-20220511204132650](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204132650.png)

![image-20220511204153450](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204153450.png)

![image-20220511204318660](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204318660.png)

##### 按位序删除

![image-20220511204428720](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204428720.png)

![image-20220511204449141](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204449141.png)



##### 指定结点删除

![image-20220511204640228](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204640228.png)

![	](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204723920.png)

##### 局限性

![image-20220511204742329](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204742329.png)

#### 查找

![image-20220511233932160](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511233932160.png)

##### 按位查找

![image-20220511234109630](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511234109630.png)

##### 按值查找

![image-20220511234239820](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511234239820.png)

##### 表长

![image-20220511234312273](E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511234312273.png)

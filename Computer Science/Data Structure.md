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

![[C:\Users\15271\AppData\Roaming\Typora\typora-user-images\image-20220506212202207.png]]

- 最坏时间复杂度
- 平均时间复杂度
- 最好时间复杂度

#### 空间复杂度

# 线性表

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220506220113292.png]]



## 定义（逻辑结构）

-  线性表Linear List
-  线性表是具有相同数据类型的n（n≥0）个数据元素的有限序列，其中n为表长，当n = 0时线 性表是一个空表。
  - 相同数据类型：每个数据元素所 占空间一样大
  - 序列：有次序
-  若用L命名线性表，则其一般表示为L = (a1, a2, … , ai, ai+1, … , an)
-  概念：
  - ai 是线性表中的“第i个”元素在线性表中的位序
  -  a1 是表头元素；an 是表尾元素。
  - 除第一个元素外，每个元素有且仅有一个直接前驱；除最后一个元素外，每个元素有且仅 有一个直接后继
-  特性
   -  表 中 元 素 个 数有限
   -  表 中 元 素 具 有 逻 辑 上 的 顺 序 性 ， 在 序 列 中 各 个 元 素 排 序 有 其先后次序 表 中 元 素 都 是数据元素，每 个 元 素 都 是 单 个 元 素
   -  表 中 元 素 的数据类型都相同，这 意 味 着 每 个 元 素 占 有 相 同 大 小 的 存 储 空 间 表 中 元 素 具 有抽象性，即 讨 论 元 素 间 一 对 一 的 逻 辑 关 系 ， 而 不 考 虑 元 素究竟 表示的内容
   -  线 性 表 是 一 种逻辑结构，表 示 元 素 之 间 一 对 一 相 邻 的 关 系


## 基本操作（运算）

### 目的

- 团队合作编程，你定义的数据结构能让别人能够很方便的使用（封装）

- 将常用的操作/运算封装成函数，避免重复工作，降低出错风险

- 顺序存储

  - 插入需要先判定是否已满
  - 删除和查找需要先判定是否为空

- 链式存储

  - 删除和查找需要先判定是否为空

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
- ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508210607859.png]]

#### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508210446294.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507213838148.png]]

##### 静态分配

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507214000406.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507214042196.png]]

###### 注意

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507214244407.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507214340277.png]]

##### 动态分配

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507215456757.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507215538238.png]]

##### 特点

- 随机访问，，即可以在 O(1) 时间内找到第 i 个元素。
  - 代码实现：data[i-1]; 静态分配、动态分配都一样
- 存储密度高，每个节点只存储数据元素
- 拓展容量不方便（即便采用动态分配的方式实现，拓展长度的时间复杂度也比较高） 
- 插入、删除操作不方便，需要移动大量元素

#### 插入删除

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220507235537982.png]]

##### 插入

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508201730922.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508201814832.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508201841062.png]]

##### 插入时间复杂度

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202002539.png]]

##### 删除

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202035204.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202105821.png]]

##### 删除时间复杂度

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202150440.png]]

#### 查找

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202244170.png]]

##### 按位查找

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202344202.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202430170.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202520920.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202540979.png]]

##### 按位查找时间复杂度

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202625108.png]]

##### 按值查找

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202638569.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202731913.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202757799.png]]

##### 按值查找时间复杂度

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508202949908.png]]

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

![[Data Structure.assets\image-20220508210700977.png]]

#### 定义

![[Data Structure.assets\image-20220508210754968.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508210856980.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508210915989.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508212652739.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508212733687.png]]

##### 不带头结点

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508212809438.png]]

##### 带头结点

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508213232084.png]]

##### 创建多个结点

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511234725741.png]]



###### 尾插法















#### 插入和删除

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220508213509108.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511202907121.png]]

##### 按位序插入

###### 带头结点

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511203102998.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511203211019.png]]

###### 不带头结点

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511203433030.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511203504029.png]]

##### 指定结点插入

###### 后插

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511203927787.png]]

###### 前插

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204132650.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204153450.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204318660.png]]

##### 按位序删除

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204428720.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204449141.png]]



##### 指定结点删除

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204640228.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204723920.png]]

##### 局限性

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511204742329.png]]

#### 查找

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511233932160.png]]

##### 按位查找

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511234109630.png]]

##### 按值查找

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511234239820.png]]

##### 表长

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220511234312273.png]]

### 双链表

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513211024897.png]]

#### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513211059061.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513211216396.png]]

#### 插入

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513211321946.png]]

#### 删除

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513211410537.png]]

#### 遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513211512356.png]]

### 循环链表

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513210528371.png]]

#### 循环单链表

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513210604526.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513210657538.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513210728966.png]]

#### 循环双链表

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513210828097.png]]

##### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513211648599.png]]

##### 插入

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513211716517.png]]

##### 删除

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513211728745.png]]

### 静态链表

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513211844817.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513213009906.png]]

##### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513213046017.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513213130919.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513213149576.png]]

##### 基本操作

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513213242219.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513213257127.png]]

##### 总结

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513213332906.png]]

### 顺序表VS链表

#### 存取方式

- 顺表序表 可以实现顺序地存址取和随机存顺取
- 链表 只能实现顺序存取

#### 逻辑结构&物理结构

- 都属于线性表，都是线性结构
- 顺序表逻辑相邻物理上也相邻，通过相邻表示逻辑关系
- 单链表逻辑相邻物理上不一定相邻，通过指针表示逻辑关系

#### 基本操作

- 插入&删除
  - 单链表为O(1)(节点指针已知)；O(n)(节点指针未知)，但操作时只需修改指针 
  - 顺序表为O(n)且需要大量移动元素

- 查找 
  - 按值查找中单链表和顺序表（无序）都为O(n) 
  - 按序查找中单链表为O(n)，顺序表为O(1)

#### 内存空间

- 顺序存储 无论静态分配还是非静态都需要预先分配合适的内存空间 静态分配时预分配空间太大回造成浪费，太小会造成溢出 动态分配时虽不会溢出但是扩充需要大量移动元素，操作效率低
- 链式存储 在需要时分配结点空间即可，高效方便，但指针要使用额外空间

#### 选择

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513220638301.png]]

### 应用

#### 线性表的合并和倒置

#### 有序表的合并



# 栈和队列

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513222011020.png]]

## 栈

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513223103619.png]]

### 定义（逻辑结构）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513223219074.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513223248811.png]]

### 基本操作（运算）

- 顺序存储
  - 插入需要先判定是否已满
  - 删除和查找需要先判定是否为空
- 链式存储
  - 删除和查找需要先判定是否为空

- | 分类 | 方法名           | 作用                                                        |

| ---- | ---------------- | ----------------------------------------------------------- |
| 创建 | InitStack(&S)    | 初始化栈。构造一个空栈 S，分配内存空间。                    |
| 销毁 | DestroyStack(&S) | 销毁栈。销毁并释放栈 S 所占用的内存空间。                   |
| 插入 | Push(&S,x)       | 进栈，若栈S未满，则将x加入使之成为新栈顶。                  |
| 删除 | Pop(&S,&x)       | 出栈，若栈S非空，则弹出栈顶元素，并用x返回。                |
| 查找 | GetTop(S, &x)    | 读栈顶元素。若栈 S 非空，则用 x 返回栈顶元素                |
| 其他 | StackEmpty(S)    | 判断一个栈 S 是否为空。若S为空，则返回true，否则返回false。 |

### 实现（物理结构）

#### 顺序栈

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513230004590.png]]

##### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513230120343.png]]

##### 进栈（插入）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513230237211.png]]

##### 出栈（删除）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513230342112.png]]

##### 读栈顶元素（查询）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513230445622.png]]

##### 栈顶指针为第一个元素时

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513230815640.png]]

##### 共享栈

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513230858931.png]]

#### 链栈

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513231102352.png]]

##### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513231237259.png]]

##### 进栈（插入）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513231444829.png]]

##### 出栈（删除）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220513231508010.png]]

##### 获得栈顶元素（查询）

### 应用

#### hanoi塔

#### 括号匹配

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517185320037.png]]

#### 表达式求值

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517185626200.png]]

##### 中转后

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517185827321.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517185930144.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517190336300.png]]

##### 中转前

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517190038562.png]]



![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517190115000.png]]

## 队列

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515164229235.png]]

### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515164055438.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515164136154.png]]

### 基本操作

- 顺序存储
  - 插入需要先判定是否已满
  - 删除和查找需要先判定是否为空
- 链式存储
  - 删除和查找需要先判定是否为空

- | 分类 | 方法名           | 作用                                           |

| ---- | ---------------- | ---------------------------------------------- |
| 创建 | InitQueue(&Q)    | 初始化队列，构造一个空队列Q。                  |
| 销毁 | DestroyQueue(&Q) | 销毁队列。销毁并释放队列Q所占用的内存空间。    |
| 插入 | EnQueue(&Q,x)    | 入队，若队列Q未满，将x加入，使之成为新的队尾。 |
| 删除 | DeQueue(&Q,&x)   | 出队，若队列Q非空，删除队头元素，并用x返回。   |
| 查找 | GetHead(Q,&x)    | 读队头元素，若队列Q非空，则将队头元素赋值给x。 |
| 其他 | QueueEmpty(Q)    | 判队列空，若队列Q为空返回true，否则返回false。 |

### 实现

#### 顺序队列

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515164813126.png]]

##### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515173056005.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515180323405.png]]

##### 入栈（插入）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515180614993.png]]

##### 出栈（删除）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515180642212.png]]

##### 读栈头元素（查找）

##### 判空和判满

- | rear指向                     | 方式                                      | 初始化                        | 队满                                                        | 队空                          |

| ---------------------------- | ----------------------------------------- | ----------------------------- | ----------------------------------------------------------- | ----------------------------- |
| rear指向队尾元素的后一个位置 | 浪费最后一个空间                          | `Q.rear=Q.front=0;`           | `(Q.rear+1)%MaxSize==Q.front`                               | Q.rear==Q.front               |
|                              | 不浪费最后一个空间（结构体增加 int size） | `rear=front=0； size = 0;`    | `size==MaxSize`（插入成功 size++； 删除成功 size--;）       | `size==0`                     |
|                              | 不浪费最后一个空间（结构体增加 int tag）  | `rear=front=0； tag = 0;`     | front==rear && tag == 1（ 删除成功tag=0； 插入成功tag=1；） | front==rear &&front tag == 0  |
| rear指向队尾元素             | 浪费最后一个空间                          | `Q.front=0; Q.rear=MaxSize-1` | `(Q.rear+2)%MaxSize==Q.front`                               | `(Q.rear+1)%MaxSize==Q.front` |

###### rear指向队尾元素的后一个位置

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515182726448.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515182853923.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515183006974.png]]

###### rear指向队尾元素

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515183134501.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515183319161.png]]

#### 链队列

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515183642981.png]]

##### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515183756523.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515183810070.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515184124347.png]]

##### 入队

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515184231140.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515184252529.png]]

##### 出队

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515184849022.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515185002870.png]]

##### 查对头元素

##### 双端队列

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515190443232.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515190518322.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220515190528398.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517184715784.png]]

### 应用

#### 树的层次遍历

#### 图的广度优先遍历

#### 操作系统先来先服务

# 串和数组

## 串

- 串是内容受限的线性表
  - 串的数据对象限定为字符集（如中文字符、英文字符、数字字符、标点字符等）
  - 串的基本操作，如增删改查等通常以子串为操作对象

- ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517193124129.png]]

### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517193210160.png]]

### 基本操作

- 假设有串T=“”，S=”iPhone 11 Pro Max?”，W=“Pro”

| 分类 | 方法名                    | 作用                                                         |
| ---- | ------------------------- | ------------------------------------------------------------ |
| 销毁 | DestroyString(&S)         | 销毁串。将串S销毁（回收存储空间）。                          |
| 插入 | EnQueue(&Q,x)             | 入队，若队列Q未满，将x加入，使之成为新的队尾。               |
|      | StrCopy(&T,S)             | 复制操作。由串S复制得到串T。                                 |
|      | Concat(&T,S1,S2)          | 串联接。用T返回由S1和S2联接而成的新串。                      |
| 修改 | StrAssign(&T,chars)       | 赋值操作。把串T赋值为chars。                                 |
|      | ClearString(&S)           | 清空操作。将S清为空串。                                      |
| 删除 | DeQueue(&Q,&x)            | 出队，若队列Q非空，删除队头元素，并用x返回。                 |
| 查找 | Index(S,T)                | 定位操作。若主串S中存在与串T值相同的子串，则返回它在主串S中第一次出现的 位置；否则函数值为0。 |
|      | SubString(&Sub,S,pos,len) | 求子串。用Sub返回串S的第pos个字符起长度为len的子串。         |
| 其他 | StrEmpty(S)               | 判空操作。若S为空串，则返回TRUE，否则返回FALSE。             |
|      | StrLength(S)              | 求串长。返回串S的元素个数。                                  |
|      | StrCompare(S,T)           | 比较操作。若S>T，则返回值>0；若S=T，则返回值=0；若S<T，则返回值<0。 |

### 实现

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517194145682.png]]

#### 顺序存储

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517194240679.png]]

##### 求子串

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517194521440.png]]

##### 比较

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517194539870.png]]

##### 定位

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517194610601.png]]

##### 应用

###### 模式匹配

- 串的模式匹配：在主串中找到与模式串相同的子串，并返回其所在位置

- BF算法：BF：Brute-Force算法。
  - ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220527220328622.png]]
  - ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220527220537153.png]]
  - ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220527220746247.png]]
  - ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220527221241356.png]]
  - ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220527221300017.png]]
  - ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220527221318945.png]]

- KMP算法：KMP：由Knuth、Morris和Pratt同时设计实现，取三人首字母命名。
  - 

#### 链式存储

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517194425409.png]]

## 数组

### 定义

- 数组 是由n (n>=1)个相同类型的数据元素构成的有限序列，每个数 据元素称为一个数组元素，每个元素受n个线性关系的约束，每个元 素在n个线性关系中的序号称为该元素的下标，并称该数组为n维数 组。
- 数组是线性表的推广！
- 数组的维度和维界不可变
  - 数组一旦被定义，其维度和维界不可变，数组除初始化和销 毁外，只有存取元素和修改元素的操作

### 物理结构

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517191520318.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517191609649.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517191617789.png]]

### 应用

#### 特殊矩阵压缩存储

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517190700420.png]]

# 树与二叉树

- 非线性结构

## 基本术语

### 度

- 结点的度：结点拥有的子树数称为结点的度。
- 树的度：树的各结点的度的最大值。

### 层次（深度）

- 结点的层次（深度）：从根开始定义起，根为第一层，根的孩子为第二层。树中任一结点的层次等于其双亲结点的层次加1。
- 数的层次（深度）：数中结点的最大层次称为树的深度或高度。

### 结点

- 结点：树中的一个独立单元。包含一个数据元素及若干指向其子树的分支。

- 根结点：没有前驱结点的结点叫做根结点，每棵树有且只有一个根结点。

#### 度是否为0

- 叶子结点（终端结点）：度为0的节点称为叶子或终端结点
- 分支结点（非终端结点）：度不为0的节点称为分支或非终端结点

#### 相对关系

- 祖先结点：从根节点到该节点所经分支分支上的所有结点
  - 子孙结点：以某结点为根的子树中的任一结点都称为该结点的子结点
- 双亲结点（父节点）：结点的根称为双亲结点
  - 孩子结点：结点的子树的根称为该节点的孩子
  - 兄弟结点：同一个双亲的孩子之间互称兄弟结点
  - 堂兄弟结点：双亲在同一层的结点互为堂兄弟结点



## 定义和性质

### 树的定义

- ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517202131507.png]]

- 有序树——逻辑上看，树中结点的各子树从左至右是有次序的，不能互换 

- 无序树——逻辑上看，树中结点的各子树从左至右是无次序的，可以互换

### 树的性质

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517210952503.png]]

- 结点数=总度数+1

- 度为m的树、m叉树的区别

| 树的度——各结点的度的最大值        | m叉树——每个结点最多只能有m个孩子的树 |
| --------------------------------- | ------------------------------------ |
| 度为m的树                         | m叉树                                |
| 任意结点的度 ≤ m（最多m个孩子）   | 任意结点的度 ≤ m（最多m个孩子）      |
| 至少有一个结点度 = m（有m个孩子） | 允许所有结点的度都 ＜ m              |
| 一定是非空树，至少有m+1个结点     | 可以是空树                           |

- ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517211041872.png]]

- ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517211100533.png]]

- ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517211756242.png]]

- ![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517211808128.png]]

### 二叉树的定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517211956713.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517211837953.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517211913622.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517212343994.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517212424242.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517212449395.png]]

### 二叉树的性质

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517212705325.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517212724132.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517212853025.png]]

### 完全二叉树的性质

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517212934665.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517213023953.png]]



### 森林

森林是m（m≥0）棵互不相交的树的集合

## 基本操作

## 实现

- 从特殊（二叉树）到一般（树、森林）。

### 二叉树的顺序存储

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517221507514.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517221537822.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517221605225.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517221848961.png]]

### 二叉树的链式存储

#### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517221625595.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517221733230.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517221753914.png]]

#### 深度遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517222056033.png]]

##### 介绍

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517222230441.png]]

##### 方式

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517222308011.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517222328252.png]]

##### 代码实现

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517222412780.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517222424691.png]]	

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517222439889.png]]

##### 树的深度

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517222711670.png]]

#### 层序遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517224247565.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517224306939.png]]

#### 构建二叉树

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220517233504611.png]]

#### 线索二叉树

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191133013.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518190007084.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518190039392.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518190056001.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518190228251.png]]

##### 线索化

###### 普通版

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518190540442.png]]

###### 中序线索化

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518190704137.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518190751206.png]]

###### 先序线索化

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518190938365.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518190953305.png]]

###### 后序线索化

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191100528.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191112045.png]]

##### 找前驱/后继

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191327753.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191404252.png]]

###### 中序

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191541941.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191607034.png]]

###### 先序

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191733713.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191744053.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191800423.png]]

###### 后序

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518191811654.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518192301142.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220518192322611.png]]

### 树的存储结构

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519161455979.png]]

#### 双亲表示法（顺序存储）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519161725859.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519161844682.png]]

#### 孩子表示法（顺序+链表）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519162004511.png]]

#### 孩子兄弟表示法（链式存储）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519162014786.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519162215472.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519162231168.png]]

#### 树和森林的转换

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519162348777.png]]

#### 树的遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519181822364.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519183439441.png]]

##### 先根遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519181919425.png]]

##### 后根遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519182050506.png]]

##### 层序遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519182833946.png]]

#### 森林的遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519183450932.png]]

##### 先序遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519183117855.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519183143647.png]]

##### 中序遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519183355486.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519183408337.png]]

## 应用

### 二叉排序树

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519185247124.png]]

#### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519185339363.png]]

#### 查找

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519185549676.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519185716423.png]]

#### 插入

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519185832864.png]]

#### 构建

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519190101817.png]]

#### 删除

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519190714524.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519190904262.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519190915881.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519191004474.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519191334781.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519191610603.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519191625956.png]]

#### 查找效率分析

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519191754145.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519191922792.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519192017101.png]]

### 平衡二叉树

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519193436015.png]]

#### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519193808204.png]]

#### 插入

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519194028983.png]]

#### 调整最小不平衡子树

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519194149332.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519204111561.png]]

##### LL

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519194331172.png]]

##### RR

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519194344522.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519194403697.png]]

##### LR

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519194414803.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519194505863.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519203510634.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519203819083.png]]

##### RL

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519203853461.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519203927150.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519204025421.png]]

#### 查找效率分析

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519204705037.png]]

### 哈夫曼树

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519205100393.png]]

#### 带权路径长度

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519205720524.png]]

#### 定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519205928303.png]]

#### 构造

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519210038810.png]]

#### 哈夫曼编码

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220519212803771.png]]

# 图

## 定义和基本概念

### 图的定义

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528170532797.png]]

### 基本概念

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528170447920.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528171055010.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528171159430.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528171401181.png]]

- 入度：ID in degree
- 出度：OD out degree
- 度：TD total degree

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528171544716.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528171616488.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528174519568.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528174538610.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528174622985.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528174656789.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528174737550.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528174829774.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528174916209.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528174935445.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528174948508.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528175024716.png]]

## 基本操作

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221230203.png]]

## 实现

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528203532317.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528211948730.png]]

### 邻接矩阵

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528204349582.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528203710642.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528203759021.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528204000602.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528204103560.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528204202163.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528204236771.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528204319201.png]]

### 邻接表

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528210247172.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528210747412.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528210908876.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528211204833.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528211515675.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528211530435.png]]

### 十字链表、邻接多重表

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528211702310.png]]

#### 十字链表（有向图）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528212040152.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528212110019.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528212118841.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528215442804.png]]

#### 邻接多重表（无向图）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528212210082.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528220703030.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528220803071.png]]

#### 十字链表vs邻接多重表

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528220845089.png]]

### 基本操作

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221429616.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221453769.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221501820.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221511358.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221521138.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221536298.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221544268.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221553668.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221602068.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221621658.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221634869.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221644310.png]]	

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221654589.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221709793.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528221719930.png]]

### 图的遍历

#### 广度优先遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528222551380.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528222707819.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528222835209.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528222935050.png]]	![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528223102749.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528223118016.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528223130744.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528223155343.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528224929840.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528225001399.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528225017681.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528223214389.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220528223233641.png]]

#### 深度优先遍历

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529152549449.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529152609436.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529152715136.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529152819173.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529152837403.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529152856061.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529152924009.png]]

### 应用

#### 最小生成树

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529153828719.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529153937830.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529154019048.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529154032749.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529154122021.png]]

##### Prim算法

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529154215407.png]]

##### Kruskal算法

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529154546856.png]]

prim VS kruskal

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529154653948.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529155701019.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529155726616.png]]

#### 最短路径

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529160643051.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529160812827.png]]

##### BFS算法（单源最短路径无权图）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529161634305.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529161726280.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529162111318.png]]

##### dijkstra算法（单源最短路径带权图）

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529162241637.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529162345247.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531191506872.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531191527992.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531191546972.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531191956119.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531193037169.png]]

##### Floyd算法

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531193151926.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531193218319.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531204723480.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531204802529.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531204823093.png]]

#### 有向无环图

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531213000796.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531213019670.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531213042026.png]]

#### 拓扑排序

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531220135781.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531213233654.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531213355778.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531215608743.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531215637155.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531215719550.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531215933151.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531220003570.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531220110585.png]]

#### 关键路径

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531220334161.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531220355571.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531223906005.png]]



![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531223959857.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531224132018.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531224025845.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531224035814.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220531224055490.png]]

# 查找

## 线性表的查找

## 树表的查找

### B树

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529164858259.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529165449774.png]]

![[E:\My Documents\xmind\Computer_Science\Data Structure.assets\image-20220529165652633.png]]

## 散列表的查找

#  基础操作

## 线性表

- ![[image-20231110162402034.png]]

## 栈

- ![[image-20231110162458990.png]]
]]

# Unity

## 基础

### 工具

- 面板

	- Project

		- 存放游戏的所有资源
		- 与项目中资源文件夹Assets对应

	- Hierarchy

		- 显示当前场景中所有游戏对象的层级关系
		- 包含当前场景的游戏对象（GameObject），其中一些是资源文件的实例

	- Scene

		- 提供设计游戏界面的可视化面板
		- 常用快捷键

			- 按下鼠标滚轮拖动场景，滑动滚轮缩放场景
			- 鼠标右键旋转场景
			- 按住右键同时按下W/S/A/D/Q/E键可实现场景漫游
			- 在Scene面板选中物体后按F键，或在Hierarchy面板双击物体，可将物体设置为场景视图的中心
			- 按住Alt键同时通过鼠标左键围绕某物体旋转场景，鼠标右键缩放场景

	- Game

		- 预览游戏运行后的界面

	- Inspector

		- 显示当前选定游戏对象附加的组件及其属性信息

- 工具条

	- 变换工具

		- Q：移动场景
		- W：移动物体
		- E：旋转物体
		- R：缩放物体
		- 顶点吸附：选择物体按住V键，确定顶点后再拖拽到目标物体的某个顶点上

			- 先松V键

	- 变换切换

		- 改变游戏对象的轴心点

			- center：设置轴心点再物体中心
			- pivot：使用物体本身的轴心

		- 改变物体的坐标

			- global：世界坐标
			- local：自身坐标

	- 播放控件

		- 从左到右依次为预览游戏，暂停游戏，逐帧播放

	- 视图

		- iso：正交观察模式
		- Persp：透视观察模式

### 基础概念

- 坐标

	- x红色，y绿色，z蓝色
	- 世界坐标：整个场景的固定坐标，不随物体旋转而改变
	- 本地坐标：物体自身坐标，随旋转而改变

- 场景

	- 一组相关联的游戏对象的集合，通常游戏中每个关卡就是一个场景，用于展现当前关卡中的所有物体

- 物体

	- 运行时出现在场景中的游戏物体。是一种容器，可以挂载组件。
在Hierarchy面板中，将一个物体拖拽到另一个物体中。子物体将继承父物体的移动，旋转和缩放属性，但子物体不影响父物体

- 组件

	- 游戏对象的功能模块。
	- 每个组件都是一个类的实例
	- Transform变换组件：确定物体位置、旋转、缩放比
Mesh Filter网格过滤器：用于从资源中获取网格信息
Mesh Renderer网格渲染器：从网格过滤器中获得几何形状，再根据变化组件定义的位置进行渲染。
网格过滤器和网格渲染器联合使用，使模型显示到屏幕上

### 材质（Material）

- 简介

	- Material

		- 材质：物体的质地，指色彩、纹理、光滑度、透明度、反射率、折射率、发光度等。实际就是Shader的实例
		- Shader着色器：专门用来 渲染3D图形的技术，可以使纹理以某种方式展现。实际就是一段嵌入到渲染管线中的程序，可以控制GPU运算图像效果的算法
		- Texture纹理：附加到物体表面的贴图

	- 纹理、着色器与材质的关系

		- Shader

			- Material

				- Texture
				- Color

	- 物理着色器

		- Physically Based Shading，PBS，就是遵从物理学的能量守恒定律，可以创建出在不同光照环境下都接近真实的效果

- 属性

	- Rendering Mode

		- Opaque：不透明
	Cutout：去掉透明通道
	Fade：淡入淡出
	Transparent：透明

	- Main Maps
	- 物体轮廓

		- 创建材质OutLine
		- 指定着色器BasicOutline
		- 指定纹理Texture
		- 设置到物体中

### 照相机（camera）

- 介绍

	- 附加了摄像机Camera组件的游戏对象
	- 向玩家捕获和显示世界的设备
	- 场景中摄像机的数量不受限制

- 组件

	- Transform
	- Camera：向玩家捕获和显示世界

		- ClearFlags 清除标识：决定屏幕的空白部分如何处理

			- SkyBox天空盒：空白部分显示天空盒图案

				- 6 Sided

					- Tint Color色彩
					- Exposure亮度
					- Rotation旋转

				- Procedural
				- Cubemap
				- 设置天空盒

					- 设置摄像机ClearFlags属性为Skybox
					- 摄像机添加组件Skybox
					- Window->Lighting->Environment Lighting->Skybox可作为反射源将天空色彩反射到场景中物体

			- Solid Color纯色：空白部分显示背景颜色
			- DepthOnly仅深度：画中画效果，小画面摄像机选择该项可清除屏幕空部分信息只保留物体颜色信息
			- Don‘t Clear不清楚：不清楚任何颜色或深度缓存

		- Background背景：所有元素绘制后，没有天空盒的情况下，剩余屏幕的颜色
		- Culling Mask （选择遮罩层）：选择要照射的层Layer
		- Projection投射方式

			- Perspective透视：透视图，物体具有近大远小效果
			- Orthographic正交：摄像机会均匀地渲染物体，没有透视，通常小地图使用。

		- Size大小：摄像机视角的大小
		- Field of view 视野（透视模式）：设置相机视野的远近距离
		- Clipping planes裁剪面：相机开始和结束渲染的距离

			- Near近：绘制的最近点
			- Far远：绘制的最远点

		- ViewPort Rect视口矩形：标明这台相机视图将会在屏幕上绘制的屏幕坐标

			- x：摄像机视图的开始水平位置
			- Y：摄像机视图的开始垂直位置
			- W宽度：摄像机输出在屏幕上的宽度
			- H：摄像机输出在屏幕上的高度

		- Depth深度：相机在渲染顺序上的位置。具有较低深度的摄像机将在较高深度的摄像机之前渲染

	- Audio Listener（音频监听器：接受场景输入的音频源Audio Source 并通过计算机的扬声器播放声音）

### InstantOc

- 渲染管线

	- 顶点处理

		- 接收模型顶点数据
		- 坐标系转换

	- 图元装配

		- 组装面：连接相邻的顶点，绘制为三角面

	- 光栅化

		- 计算三角面上的像素，并为后面着色阶段提供合理的插值参数

	- 像素处理

		- 对每个像素区域进行着色
		- 写入缓存中

	- 缓存

		- 帧缓存：存储每个像素的色彩，即渲染后的图像。帧缓存常常在显存中，显卡不断读取并输出到屏幕中

- Occlusion Culling

	- 即时遮挡剔除
	- 步骤
	- 属性

- LOD

	- 多细节层次
	- 步骤
	- 属性

## C#基础

### .net

- 简介

	- .net

		- 微软新一代多语言的开发平台，用于构建和运行应用程序

	- csharp
	- 版本
	- mono

		- Novell公司支持在其他操作系统下开发.net程序的框架
		- Unity借助Mono实现跨平台，核心是.net framework 框架

- .net Framework

	- 简介

		- .net 程序开发和运行的环境
		- 组成

			- 公共语言运行时CLR

				- Common Language Runtime
				- 程序的运行环境，负责内存分配、垃圾收集、安全检查等工作

			- 类库

	- CLS

		- Common Language Specification（公共语言规范）
		- 定义了.net平台上运行的语言必须支持的规范，用以避免不同语言特性产生的错误，实现语言间互操作

	- .net程序编译过程

		- 源代码->CLS编译->CIL（通用中间语言）->CLR编译->机器码
		- CIL：Common Intermediate Language

### 类型系统

- 值类型

	- 简单类型

		- 有符号整型：、short、int、long
		- 无符号整型：、ushort、uint、ulong
		- Unicode 字符：，表示 UTF-16 代码单元
		- IEEE 二进制浮点：double
		- 高精度十进制浮点数：
		- 布尔值：bool，表示布尔值（true 或 false）

	- 枚举类型

		- enum E {...} 格式的用户定义类型。 enum 类型是一种包含已命名常量的独特类型。 每个 enum 类型都有一个基础类型（必须是八种整型类型之一）。 enum 类型的值集与基础类型的值集相同。

	- 结构类型

		- 格式为 struct S {...} 的用户定义类型

	- 可以为null单独值类型

		- 值为 null 的其他所有值类型的扩展

	- 元组值类型

		- 格式为 (T1, T2, ...) 的用户定义类型

- 引用类型

	- 类类型

		- 其他所有类型的最终基类：object
		- Unicode 字符串：，表示 UTF-16 代码单元序列
		- 格式为 class C {...} 的用户定义类型

	- 接口类型

		- 格式为 interface I {...} 的用户定义类型

	- 数组类型

		- 一维、多维和交错。 例如：int[]、int[,] 和 int[][]

	- 委托类型

		- 格式为 delegate int D(...) 的用户定义类型

### 运算符

- 算术运算符，将对数值操作数执行算术运算
- 比较运算符，将比较数值操作数
- 布尔逻辑运算符，将对 操作数执行逻辑运算
- 位运算符和移位运算符，将对整数类型的操作数执行位运算或移位运算
- 相等运算符，将检查其操作数是否相等

### 表达式

- Swith表达式

### 流程控制

- 在一个程序的执行过程中，各条代码的执行顺序对程序的结果是有直接影响的，很多时候我们都要通过控制代码的执行顺序来实现我们要完成的功能
- 顺序结构

	- 没有特定的语法结构，程序会按照代码的先后顺序，依次执行。

- 分支结构

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

- 循环结构

	- 被重复执行的语句被称之为循环体，是否继续重复执行，取决于循环的终止条件。由循环体及循环的终止条件组成的语句，被称之为循环语句
	- for

		- for（初试化变量；终止条件；变化量）{
  循环体
}

	- foreach（String item in array）{
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

- 函数
- 数组
- 数据类型
- 类和对象
- 结构

## 脚本Script

### 脚本介绍

- 脚本

	- 介绍

		- 脚本是附加在游戏物体上用于定义游戏对象行为指定的代码，需要继承自MonoBehaviour类
		- 文件名与类名必须一致
		- Unity支持两种高级编程语言：C#、JavaScript、BooScript

	- 语法结构
	- 编译过程

		- 源代码->（CLS）->CIL->Mono Runtime （CLR）->机器码

- 开发工具

	- 修改路径：Edit-Preference-External Tools -External Script Editor
	- MonoDevelop
	- visual Studio
	- Console

- 脚本生命周期

	- Unity脚本从唤醒到销毁的过程
	- 消息：当满足某种条件 Unity引擎自动调用的函数
	- 初始阶段

		- Awake（）唤醒：

			- 当物体载入时立即调用1次；常用于在游戏开始前进行初试haul，可以判断当满足某种条件执行此脚本this.enable =true

		- OnEnable（）当可用

			- 每当脚本对象启用时调用

		- Start（）

			- 物体载入且脚本对象启用时被调用一次。常用于数据或游戏逻辑初始化，执行时机晚于Awake

	- 物理阶段

		- FixedUpdate（）

			- 脚本启用后，固定时间被调用，适用于对游戏对象做物理操作，例如移动等
			- 设置更新频率：“Edit”->"Project Setting"-"Time"->"Fixed Timestep"值，默认为0.02s

		- OnCollisionXXX（）

			- 当满足碰撞条件时调用

		- OnTriggerXXX（）

			- 当满足触发条件时调用

	- 输入事件

		- OnMouseEnter（）

			- 鼠标移入到当前Collider时调用

		- OnMouseOver（）

			- 鼠标经过当前Collider时调用

		- OnMouseExit（）

			- 鼠标离开当前Collider时调用

		- OnMouseDown（）

			- 鼠标按下当前Collider时调用

		- OnMouseUp（）

			- 鼠标在当前Collider抬起时调用

	- 游戏逻辑

		- Update（）

			- 脚本启用后，每次渲染场景时调用，频率与设备性能及渲染量有关

		- LateUpdate（）

			- 在Update函数被调用后执行，适用于跟随逻辑

	- 场景渲染

		- OnBecameVisble（）

			- 当Mesh Render在任何相机上可见时调用

		- OnBecameInvisible（）

			- 当Mesh Render在任何相机上都不可见时调用

	- 结束阶段

		- OnDisable（）

			- 对象变为不可用或附属游戏对象非激活状态时此函数被调用

		- OnDestroy（）

			- 当脚本销毁或附属的游戏对象被销毁时被调用

		- OnApplicationQuit（）

			- 应用程序退出时被调用

- 调试

	- Unity编辑器
	- VS
	- MonoDevelop

### 常用API

##### unity && C# 方括号声明

| 声明             | 标记类型 | 说明                                          | 用法举例                                                     |
| ---------------- | -------- | --------------------------------------------- | ------------------------------------------------------------ |
| RequireComponent | 类       | 添加组件到 game object 上，且该组件不能删除。 | [RequireComponent(typeof(Animator))]<br/>public class TestOne : MonoBehaviour <br/>  {<br/>} |
| HideInInspector  | 成员属性 | 在Inspector 面板中隐藏public的属性。          | [HideInInspector]<br/>public int Blood = 10;                 |
| SerializeField   | 成员属性 | 在Inspector 面板中显示private的属性。         | [SerializeField]<br/>private int Blood = 10;                 |
| range            | 成员属性 | 在 Inspector 面板中显示一个滑动条。           | [Range(float min, float max)]<br/>public int Blood = 10;     |

#### Component

- public Component GetComponent (Type type)

	- 如果 GameObject 附加了type类型的组件，则返回 type 的组件，type如果没有，则返回 null。还将返回禁用的组件。
	
	- Component.GetComponent 将返回找到的第一个组件并且顺序未定义。如果您希望有多个相同类型的组件，请改用 Component.GetComponents，并循环通过返回的组件测试某些唯一属性。
	
	
	- 要在不同的 GameObject 上获取组件，请使用GameObject.Find获取对另一个 GameObject 的引用，然后在另一个 GameObject 上使用GameObject.GetComponent。
	

```c#
using UnityEngine;

public class ScriptExample : MonoBehaviour
{
    void Start()
    {
        // Disable the spring on the HingeJoint Component.
        HingeJoint hinge = GetComponent<HingeJoint>();
        hinge.useSpring = false;
    }
}
```

- public Component GetComponentInChildren (Type t);

  - 使用深度首次搜索返回 GameObject 或其任何子项中类型为 type 的组件。

  - 仅当在活动 GameObject 上发现组件时才返回该组件。

```c#
using UnityEngine;
public class Example : MonoBehaviour

    void Start()
    {
        HingeJoint hinge = GetComponentInChildren<HingeJoint>();
        hinge.useSpring = false;
    }
}
```

- GetComponentInParent
  - 返回 GameObject 或其任何父项中类型为 type 的组件。

#### Transform

##### 属性

- position

  - 	世界空间中的变换位置。
- localPosition

  - 相对于父变换的变换位置。
- rotation

  - 一个 Quaternion，用于存储变换在世界空间中的旋转。
- parent

  - 变换的父级。
- forward

  - 返回一个标准化矢量，它表示世界空间中变换的蓝轴。
- localScale

  - 相对于 GameObjects 父对象的变换缩放。
- lossyScale

  - 对象的全局缩放。（只读）
- root
  - 返回层级视图中最顶层的变换。

##### 方法

- public void Translate (Vector3 translation, Space relativeTo= Space.Self);

	- 根据 translation 的方向和距离移动变换。
	
	- 如果 relativeTo 被省略或设置为 Space.Self，则会相对于变换的本地轴来应用该移动。（在场景视图中选择对象时显示的 X、Y 和 Z 轴。） 如果 relativeTo 为 Space.World，则相对于世界坐标系应用该移动。
	

```c#
using UnityEngine;
using System.Collections;

public class ExampleClass : MonoBehaviour
{
    void Update()
    {
        // Move the object forward along its z axis 1 unit/second.
        transform.Translate(0, 0, Time.deltaTime);

        // Move the object upward in world space 1 unit/second.
        transform.Translate(0, Time.deltaTime, 0, Space.World);
    }
}
```
- public void RotateAround (Vector3 point, Vector3 axis, float angle);

  - 将变换围绕穿过世界坐标中的 point 的 axis 旋转 angle 度。

  - 这会修改变换的位置和旋转。

```c#
using UnityEngine;

//Attach this script to a GameObject to rotate around the target position.
public class Example : MonoBehaviour
{
    //Assign a GameObject in the Inspector to rotate around
    public GameObject target;

    void Update()
    {
        // Spin the object around the target at 20 degrees/second.
        transform.RotateAround(target.transform.position, Vector3.up, 20 * Time.deltaTime);
    }
}
```

#### GameObject

Unity 场景中所有实体的基类。

注意：GameObject 类中的很多变量已被删除。例如， 要访问 csharp 中的 GameObject.renderer，请改用 GetComponent<Renderer>()。

| 属性              | 说明                                                         |
| ----------------- | ------------------------------------------------------------ |
| activeInHierarchy | 定义 GameObject 在 Scene 中是否处于活动状态。                |
| activeSelf        | 此 GameObject 的本地活动状态。（只读）                       |
| isStatic          | 获取并设置 GameObject 的 StaticEditorFlags。                 |
| layer             | 该游戏对象所在的层。                                         |
| scene             | 该 GameObject 所属的场景。                                   |
| sceneCullingMask  | Unity 用于确定在哪个场景中渲染该 GameObject 的场景剔除遮罩。 |
| tag               | 此游戏对象的标签。                                           |
| transform         | 附加到此 GameObject 的 Transform。                           |

#### Object

#### Time


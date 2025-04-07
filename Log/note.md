# 概述

- 疯狂Android讲义（168页-229页，第四章深入了解Activity和Fragment）
  - 第二章
    1. 对话框组件的剩余部分
    2. 菜单
    3. 活动条
  - 第三章
    1. 基于监听的事件处理
    2. 基于回调的事件处理
    3. 系统设置响应
    4. 消息传递机制
    5. 异步任务
  - 第四章
    - Activity的配置、启动、关闭
    - Activity间的数据交换，Intent的bundle

# 疯狂Android第二章

## 对话框dialog剩余部分

### 对话框风格的窗口

- ```xml
  <!-在AndroidManifest.xml文件中指定该窗口以对话框风格显示->
  <activity android:name=".MainActivity" android: theme="@android:style/Theme.Material.Dialog"></activity>
  ```

### PopupWindow

- PopupWindow可以创建类似对话框风格的窗口
- 使用流程
  1. 调用 PopupWindow的构造器创建PopupWindow对象。
  2. 调用PopupWindow的showAsDropDown(View v)将PopupWindow作为V组件的下拉组件显示出来;或调用PopupWindow的showAtLocation()方法将PopupWindow在指定位置显示出来。

### DatePickerDialog、TimePickerDialog

- 类似于picker，只是用对话框显示出来
- 使用流程
  1. 通过构造器创建DatePickerDialog、TimePickerDialog实例，调用它们的show0方法即可将日期选择对话框、时间选择对话框显示出来。
  2. 为DatePickerDialog 、TimePickerDialog 绑定监听器，这样可以保证用户通过DatePickerDialog、TimePickerDialog 选择日期、时间时触发监听器，从而通过监听器来获取用户所选择的日期、时间。
- 注意：选择日期对话框、选择时间对话框只是供用户来选择日期、时间的，对Android的系统日期、时间没有任何影响。

### ProgressDialog

- ProgressDialog代表了进度对话框，程序只要创建ProgressDialog 实例，并将它显示出来就是一个进度对话框。（已被Android 8 弃用，deprecated）

##菜单Menu

- 关系
  - Menu：选项菜单不支持勾选标记，并且只显示菜单的“浓缩(condensed) ”标题。add()方法用于添加菜单项; addSubMenu()用于添加子菜单。
    - SubMenu:它代表 一个子菜单。可以包含1~N个MenuItem (形成菜单项)。不支持菜单项图标，不支持嵌套子菜单。可以设置菜单头的图标和标题，用view设置菜单头
    - ContextMenu:它代表一个上下文菜单。可以包含1~N个Menultem (形成菜单项)。不支持菜单快捷键和图标。
- 添加子菜单和菜单
  1. 重写Activity的onCreateOptionsMenu(Menu menu)方法，在该方法里调用Menu对象的方法来添加菜单项或子菜单。
     - 如果希望所创建的菜单项是单选菜单项或多选菜单项，则可以调用Menultem的setCheckable(boolean checkable)来设置该菜单项是否可以被勾选。默认是多选菜单项。
     - 如果希望将一组菜单里的菜单项都设为可勾选的菜单项使用setGroupCheckable(int group, boolean checkable, boolean exclusive)来设置group组里的所有菜单项是否可勾选:如果将exclusive 设为true,那么它们将是一组单选菜单项。
  2. 如果希望应用程序能响应菜单项的单击事件，那么重写Activity 的onOptionsItemSelected(Menultem mi)方法即可。
     - 使用监听器可以为每个事件单独设置监听菜单事件。setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener menultemClickListener)
- 点击菜单项跳转其他Activity：调用Menultem 的setIntent(Intent intent)方法

### ContextMenu

- Android 用ContextMenu来代表上下文菜单。因为ContextMenu继承了Menu,因此程序可用相同的方法为它添加菜单项。
- 开发上下文菜单与开发选项菜单的区别:开发上下文菜单不是重写onCreateOptionsMenu(Menu menu)方法，而是重写onCreateContextMenu (ContextMenu menu, View source, ContextMenu.ContextMenuInfo menulnfo)方法。
  - source：参数代表触发上下文菜单的组件。
- 使用步骤
  1. 重写Activity的onCreateContextMenu(ContextMenu menu, View source, ContextMenu.ContextMenuInfo menuInfo)方法。
  2. 调用Activity的registerForContextMenu(View view)方法为view组件注册上下文菜单。
  3. 如果希望应用程序能为菜单项提供响应，则可以重写onContextItemSelected(Menultem mi)方法，或为指定菜单项绑定事件监听器。

### 使用XML定义菜单

- 菜单资源文件通常应该放在\res\menu目录下，菜单资源的根元素通常是<men.../>, <men.../>元素无须指定任何属性。
  - <item..>元素:定义菜单项。<ite.../>元素用于定义菜单项，<iterm...> 元素又可包含<men.../>元素，位于<ite.../>元素内部的<men.../>就代表了子菜单。
    - 可以指定id，icon，title等
  - <group..>子元素:将多个iten.../>定义的菜单项包装成一个菜单组。用于控制整组菜单的行为，该元素常用属性：
    - checkableBehavior: 指定该组菜单的选择行为。可指定为none (不可选)、all (多选)和single (单选)三个值。
    - menuCategory: 对菜单进行分类，指定菜单的优先级。有效值为container、system、secondary和alternative。
    - visible:指定该组菜单是否可见。
    - enable:指定该组菜单是否可用。
- 使用菜单：重写Activity的onCreateOptionsMenu (用于创建选项菜单)、onCreateContextMenu (用于创建上下文菜单)方法，在这些方法中调用MenuInflater对象的inflate、方法加载指定资源对应的菜单即可。

### 使用PopupMenu创建弹出式菜单

- PopupMenu代表弹出式菜单，它会在指定组件上弹出PopupMenu,在默认情况下，PopupMenu会显示在该组件的下方或者上方。
- 使用流程
  1. 调用PopupMenu(Context context, View anchor)构造器创建下拉菜单，anchor 代表要激发该弹出菜单的组件。
  2. 调用MenuInflater的inflate(方法将菜单资源填充到PopupMenu中。
  3. 调用PopupMenu的show(方法显示弹出式菜单。

## 活动条ActionBar

- ActionBar位于屏幕的顶部。ActionBar 可显示应用的图标和Activity 标题，ActionBar 的右边还可以显示活动项( Action Item)。

- 功能

  - 显示选项菜单的菜单项(将菜单项显示成Action Item)。
  - 使用程序图标作为返回Home主屏或向上的导航操作。
  - 提供交互式View作为Action View。
  - 提供基于Tab的导航方式，可用于切换多个Fragment。
  - 提供基于下拉的导航方式。

- ActionBar关闭：安卓不低于3.0的版本都自动打开ActionBar，关闭

  - ```xml
    <application android: icon="@drawable/ic launcher" android: theme= "@android: style/Theme.Material.NoActionBar" android: label="@string/app name">
    </application>
    ```

  - ActionBar提供show（）和hide（）显示和隐藏ActionBar

- ActionBar显示选项菜单项：

  - setShowAsAction(int actionEnum):该方法设置是否将该菜单项显示在ActionBar上，作为Action Item。
  - android:showAsAction：XML属性设置
  - 如果ActionBar显示空间不足，有Menu按键的按Menu就可以看到，没有Menu按键提供三个的图标，点击可看到剩余的选项菜单项。

- 启用程序图标导航

  - ActionBar方法

    - | 方法                                            | 说明                                                         |
      | ----------------------------------------------- | ------------------------------------------------------------ |
      | setDisplayHomeAsUpEnabled(boolean showHomeAsUp) | 设置是否将应用程序图标转变成可点击的图标，并在图标上添加一个向左的箭头。 |
      | setDisplayOptions(int options)                  | 通过传入int 类型常量来控制该ActionBar的显示选项。            |
      | setDisplayShowHomeEnabled(boolean showHome)     | 设置是否显示应用程序图标。                                   |

- 添加Action View

  - 在ActionBar上除可以显示普通的Action Item 之外，还可以显示普通的UI组件。
    1. 定义Action Item时使用android:actionViewClass属性指定Action View的实现类。
    2. 定义Action Item时使用android:actionI ayout属性指定Action View对应的视图资源。

# 事件机制

- 当用户在程序界面上执行各种操作时，应用程序必须为用户动作提供响应动作，这种响应动作就需要通过事件处理来完成。

- 方式
  - 基于回调的事件处理
    - 做法：为Android界面组件绑定特定的事件监听器
  - 基于监听的事件处理
    - 做法：重写Android组件特定的回调方法，或者重写Activity的回调方法。

## 基于监听的事件处理

- ![image-20220617133426871](note.assets/image-20220617133426871.png)

- 处理模型对象
  - Event Source (事件源) ;事件发生的场所，通常就是各个组件，例如按钮、窗口、菜单等。
  - Event (事件) :事件封装了界面组件上发生的特定事情(通常就是一次用户操作)。如果程序需要获得界面组件上所发生事件的相关信息，一般通过Event对象来取得。
  - EventListener(事件监听器);负责监听事件源所发生的事件，并对各种事件做出相应的响应。

- 基于监听的事件处理机制是一种==委派式(Delegation) 事件处理方式==:普通组件(事件源)将整个事件处理委托给特定的对象(事件监听器);当该事件源发生指定的事件时，就通知所委托的事件监听器，由事件监听器来处理这个事件。
  - 每个组件均可以针对特定的事件指定一个事件监听器
  - 每个事件监听器也可监听一个或多个事件源。
  - 同一个事件源上可能发生多种事件，委派式事件处理方式可以把事件源上所有可能发生的事件分别授权给不同的事件监听器来处理;同时也可以让一类事件都使用同一个事件监听器来处理。
- 使用流程
  1. 获取普通界面组件(事件源)，也就是被监听的对象。
  2. 实现事件监听器类，该监听器类是一个特殊的类，必须实现一个XxxListener接口。
  3. 调用事件源的setXxxListener方法将事件监听器对象注册给普通组件(事件源)。

### 事件和事件监听器

- 事件监听器：**实现了特定接口的实例**

- **Android对事件监听模型做了进一步简化**:如果事件源触发的事件足够简单，事件里封装的信息比较有限，那就无须封装事件对象，将事件对象传入事件监听器。

- 但对于**键盘事件、屏幕触碰**事件等，此时**程序需要获取事件发生的详细信息**。例如，键盘事件需要获取是哪个键触发的事件;触摸屏事件需要获取事件发生的位置等，对于这种包含更多信息的事件，Android 同样会将事件信息封装成XxxEvent对象，并把该对象作为参数传入事件处理器。

- 在基于监听的事件处理模型中，事件监听器必须实现事件监听器接口，Android为不同的界面组件提供了不同的监听器接口，这些接口通常以内部类的形式存在。

  - View类的内部接口

    - | 接口                             | 说明                                           |
      | -------------------------------- | ---------------------------------------------- |
      | View.OnClickListener             | 单击事件的事件监听器必须实现的接口。           |
      | View.OnCreateContextMenuListener | 创建上下文菜单事件的事件监听器必须实现的接口。 |
      | View.onFocusChangeListener       | 焦点改变事件的事件监听器必须实现的接口。       |
      | View.OnKeyListener               | 按键事件的事件监听器必须实现的接口。           |
      | View.OnIongClickListener         | 长按事件的事件监听器必须实现的接口。           |
      | View.OnTouchListener             | 触摸事件的事件监听器必须实现的接口。           |

- **事件监听器的形式**

  - 内部类形式:将事件监听器类定义成当前类的内部类。
  - 外部类形式:将事件监听器类定义成一个外部类。
  - Activity本身作为事件监听器类:让Activity本身实现监听器接口，并实现事件处理方法。
  - Lambda表达式或匿名内部类形式:使用Lambda表达式或匿名内部类创建事件监听器对象。

#### 内部类作为事件监听器类

- 优点

  - 使用内部类可以在当前类中复用该监听器类。

  - 监听器类是外部类的内部类，所以可以自由访问外部类的所有界面组件。

#### 外部类作为事件监听器类

- 优点：如果某个事件监听器需要被多个GUI界面所共享，而且主要是完成某种业务逻辑的实现，则可以考虑使用外部类形式来定义事件监听器类。
- 缺点
  - 事件监听器通常属于特定的GUI界面，定义成外部类不利于提高程序的内聚性。
  - 外部类形式的事件监听器不能自由访问创建GUI界面的类中的组件，编程不够简洁。

#### Activity本身作为事件监听器类

- 优点：形式非常简洁
- 缺点：可能造成程序结构混乱，Activity的主要职责应该是完成界面初始化工作，但此时还需包含事件处理器方法，违背了设计时的本意，从而引起混乱。

#### Lambda表达式作为事件监听器类

- 优点：大部分时候，事件处理器都没有什么复用价值(可复用代码通常都被抽象成了业务逻辑方法)，因此大部分事件监听器只是临时使用一次，所以使用Lambda表达式形式的事件监听器更合适

#### 直接绑定到标签

- 优点：简单，直接在界面布局文件中为指定标签绑定事件处理方法。

## 基于回调的事件处理

- 对于基于回调的事件处理模型来说，事件源与事件监听器是统一的，或者说事件监听器完全消失了。当用户在GUI组件上激发某个事件时，组件自己特定的方法将会负责处理该事件。

- 使用回调机制类处理GUI组件上所发生的事件，需要为该组件提供对应的事件处理方法，这种事件处理方法通常都是系统预先定义好的，因此通常需要继承GUI组件类，并通过重写该类的事件处理方法来实现。

  - 为了实现回调机制的事件处理，Android为所有GUI组件都提供了一些事件处理的回调方法

  - View类的事件处理回调方法

    - | 方法                                                | 说明                                         |
      | --------------------------------------------------- | -------------------------------------------- |
      | boolean onKeyDown(int keyCode, KeyEvent event)      | 当用户在该组件上按下某个按键时触发该方法。   |
      | boolean onKeyLongPress(int keyCode, KeyEvent event) | 当用户在该组件上长按某个按键时触发该方法。   |
      | boolean onKeyShortcut(int keyCode, KeyEvent event)  | 当一个键盘快捷键事件发生时触发该方法。       |
      | boolean onKeyUp(int keyCode, KeyEvent event)        | 当用户在该组件上松开某个按键时触发该方法。   |
      | boolean onTouchEvent(MotionEvent event)             | 当用户在该组件上触发触摸屏事件时触发该方法。 |
      | boolean onTrackballEvent(MotionEvent event)         | 当用户在该组件上触发轨迹球事件时触发该方法。 |

- 使用流程

  1. 自定义View，自定义View时重写该View的事件处理方法
  2. 在界面布局文件中使用这个自定义View

- 基于监听和回调的事件处理的区别

  - 对于基于监听的事件处理模型来说，事件源和事件监听器是**分离**的，当事件源上发生特定事件时，该事件交给事件监听器负责处理。
  - 对于基于回调的事件处理模型来说，事件源和事件监听器是**统一**的，当事件源发生特定事件时，该事件还是由事件源本身负责处理。

### 基于回调的事件传播

- 几乎所有基于回调的事件处理方法都有一个boolean类型的返回值，该返回值用于标识该处理方法是否能完全处理该事件。
  - 如果处理事件的回调方法返回true, 表明该处理方法已完全处理该事件，该事件不会传播出去。
  - 如果处理事件的回调方法返回false,表明该处理方法并未完全处理该事件，该事件会传播出去。
- 对于基于回调的事件传播而言，某组件上所发生的事件不仅会激发该组件上的回调方法，也会触发该组件所在Activity的回调方法，只要事件能传播到该Activity。
- 传播顺序
  - 如果让任何一个事件处理方法返回了true,那么该事件将不会继续向外传播。
  - 当该组件上发生触碰事件时
    1. 触发该组件绑定的事件监听器
    2. 触发该组件提供的事件回调方法
    3. 传播到该组件所在的Activity

## 响应系统设置的事件

- 背景：有时候可能需要让应用程序随系统设置而进行调整

### Configuration 类

- Configuration类专门用于描述手机设备上的配置信息，这些配置信息既包括用户特定的配置项，也包括系统的动态设备配置。

- ```java
  //获得Activity的Configuration对象
  Configuration cfg = getResources().getConfiguration();
  ```

  - Configuration对象的常用属性

    - | 属性               | 说明                                                         |
      | ------------------ | ------------------------------------------------------------ |
      | float fontScale    | 获取当前用户设置的字体的缩放因子。                           |
      | int keyboard       | 获取当前设备所关联的键盘类型。该属性可能返回KEYBOARD NOKEYS、KEYBOARD QWERTY (普通电脑键盘)、KEYBOARD 12KEY (只有12个键的小键盘)等属性值。 |
      | int keyboardHidden | 该属性返回一个boolean值用于标识当前键盘是否可用。该属性不仅会判断系统的硬件键盘，也会判断系统的软键盘(位于屏幕上)。如果该系统的硬件键盘不可用，但软键盘可用，该属性也会返回KEYBOARDHIDDEN NO;只有当两个键盘都不可用时才返回KEYBOARDHIDDEN YES。 |
      | Locale locale      | 获取用户当前的Locale。                                       |
      | int mcc            | 获取移动信号的国家码。                                       |
      | int mnc            | 获取移动信号的网络码。                                       |
      | int navigation     | 判断系统上方向导航设备的类型。该属性可能返回NAVIGATION_ NONAV(无导航)、NAVIGATION DPAD (DPAD导航)、NAVIGATION_ TRACKBALL (轨迹球导航)、NAVIGATION WHEEL (滚轮导航)等属性值。 |
      | int orientation    | 获取系统屏幕的方向，该属性可能返回ORIENTATION LANDSCAPE (横向屏幕)、ORIENTATION PORTRAIT (竖向屏幕)、ORIENTATION SQUARE (方形屏幕)等属性值。 |
      | int touchscreen    | 获取系统触摸屏的触摸方式。该属性可能返回TOUCHSCREEN NOTOUCH ( 无触摸屏)、TOUCHSCREEN STYLUS ( 触摸笔式的触摸屏)、TOUCHSCREEN FINGER (接受手指的触摸屏)等属性值。 |

### 响应系统设置更改

- 基于回调的事件处理方法：重写Activity 的onConfigurationChanged(Configuration newConfig)方法， 当系统设置发生更改时，该方法会被自动触发。
- 为了让该Activity能监听更改的事件，需要在配置该Activity时指定android:configChanges属性

## Handler消息传递机制

- 背景：出于性能优化考虑，Android 的UI 操作并不是线程安全的。为了解决这个问题，Android 制定了一条简单的规则:只允许UI线程修改Activity 里的UI组件。Android平台只允许UI线程修改Activity里的UI组件，这样就会导致新启动的线程无法动态改变界面组件的属性值。

### Handler类

- 作用

  - 在新启动的线程中发送消息。
  - 在主线程中获取、处理消息。

- **新启动的线程何时发送消息呢?主线程何时去获取并处理消息呢?**

  - 为了让主线程能“适时”地处理新启动的线程所发送的消息，显然只能通过回调的方式来实现一一开发者只要重写Handler类中处理消息的方法，当新启动的线程发送消息时，消息会发送到与之关联的MessageQueue, 而Handler 会不断地从MessageQueue中获取并处理消息一一这将 导致Handler类中处理消息的方法被回调。

- | 方法                                                | 说明                                                         |
  | --------------------------------------------------- | ------------------------------------------------------------ |
  | handleMessage(Message msg)                          | 处理消息的方法。该方法通常用于被重写。                       |
  | hasMessages(intwhat)                                | 检查消息队列中是否包含what属性为指定值的消息。               |
  | hasMessages(int what, Object object)                | 检查消息队列中是否包含what属性为指定值且object属性为指定对象的消息。 |
  | Message obtainMessage()                             | 获取消息。                                                   |
  | sendEmptyMessage(int what)                          | 发送空消息。                                                 |
  | sendEmptyMessageDelayed(int what, long delayMillis) | 指定多少毫秒之后发送空消息。                                 |
  | sendMessage(Message msg)                            | 立即发送消息。                                               |
  | sendMessageDelayed(Message msg, long delayMillis)   | 指定多少毫秒之后发送消息。                                   |

### 	Handler、Loop、MessageQueue的工作原理

- 介绍
  - Message: Handler 接收和处理的消息对象。
  - Looper:每个线程只能拥有一个Looper.它的loop方法负责读取MessageQueue中的消息，读到信息之后就把消息交给发送该消息的Handler进行处理。
  - MessageQueue:消息队列，它采用先进先出的方式来管理Message。程序创建Looper对象时，会在它的构造器中创建MessageQueue对象。
  - Handler:它的作用有两个，即发送消息和处理消息，程序使用Handler发送消息，由Handler发送的消息必须被送到指定的MessageQueue。
    - 如果希望Handler正常工作，必须在当前线程中有一个Looper对象。为了保证当前线程中有Looper对象，可以分如下两种情况处理。
      - 在主UI线程中，系统已经初始化了一一个Looper对象，因此程序直接创建Handler即可，然后就可通过Handler来发送消息、处理消息了。
      - 程序员自己启动的子线程，必须自己创建一一个Looper对象，并启动它。创建Looper对象调用它的prepare(）方法即可。
        - prepare(）方法保证一个线程只会有一个Looper对象。
- 作用
  - Looper:每个线程只有一一个 Looper,它负责管理MessageQueue,会不断地从MessageQueue中取出消息，并将消息分给对应的Handler处理。
  - MessageQueue:由Looper负责管理。它采用先进先出的方式来管理Message。
  - Handler:它能把消息发送给Looper管理的MessageQueue,并负责处理Looper分给它的消息。

- 线程中使用Handler的流程
  1. 调用Looper的prepare()方法为当前线程创建Looper对象，创建Looper对象时，它的构造器会创建与之配套的MessageQueue。
  2. 有了Looper之后，创建Handler子类的实例，重写handleMessage()方法， 该方法负责处理来自其他线程的消息。
  3. 调用Looper的loop()方法启动Looper。

- ANR:Application Not Responding

## 异步任务( AsyncTask )

- 背景

  - 获取网络数据之后，新线程不允许直接更新UI组件。
    - 解决方案。
      - 使用Hanlder实现线程之间的通信。
      - Activity.runOnUiThread(Runnable).
      - View.post(Runnable)。
      - View.postDelayed(Runnable, long)。

- 作用：异步任务(AsyncTask)则可进一步简化这种操作。相对来说AsyncTask更轻量级- -些，适用于简单的异步处
  理，不需要借助线程和Handler即可实现。

- Async Task<Params, Progress, Result>是一个抽象类，通常用于被继承，继承AsyncTask时需要泛型参数。

  - Params: 启动任务执行的输入参数的类型。
  - Progress: 后台任务完成的进度值的类型。
  - Result: 后台执行任务完成后返回结果的类型。

- 使用流程

  1. 创建AsyncTask的子类,并为三个泛型参数指定类型。如果某个泛型参数不需要指定类型，则可将它指定为Void。

  2. 根据需要，实现AsyncTask的如下方法。

     - | 方法                                 | 说明                                                         |
       | ------------------------------------ | ------------------------------------------------------------ |
       | doInBackground(Param..)              | 重写该方法就是后台线程将要完成的任务。该方法可以调用publishProgress(Progress.. values)方法更新任务的执行进度。 |
       | onProgressUpdate(Progress... values) | 在doInBackground0方法中调用publishProgress(）方法更新任务的执行进度后，将会触发该方法。 |
       | onPreExecute()                       | 该方法将在执行后台耗时操作前被调用。通常该方法用于完成一~些初始化的准备工作，比如在界面上显示进度条等。 |
       | onPostExecute(Result result)         | 当doInBackground()完成后，系统会自动调用onPostExecute(）方法，并将doInBackground0方法的返回值传给该方法。 |

  3. 调用AsyncTask子类的实例的execute(Params... params)开始执行耗时任务。

  4. 规则

     - 必须在UI线程中创建AsyncTask的实例。
     - 必须在UI线程中调用AsyncTask的execute(方法。
     - AsyncTask的onPreExecute( 、onPostExecute(Result result) 、doInBackground (Param...params)、onProgressUpdate(rogres... values)方法， 不应该由程序员代码调用，而是由Android系统负责调用。
     - 每个AsyncTask只能被执行一-次，多次调用将会引发异常。

# 深入了解Activity和Fragment

- Activity 充当了应用与用户互动的入口点。可以将 Activity 实现为 Activity 类的子类。
- Android应用的多个Activity组成Activity 栈，当前活动的Activity位于栈顶。
- 当Activity处于Android应用中运行时，同样受系统控制，有其自身的生命周期

## Activity

- Activity是Android应用中最重要、最常见的应用组件(此处的组件是**粗粒度的系统组成部分**，并非指界面控件: widget)。 

- ![image-20220617165657046](note.assets/image-20220617165657046.png)

- 当一个Activity 类定义出来之后，这个Activity类何时被实例化、它所包含的方法何时被调用，这些都不是由开发者决定的，都应该由Android系统来决定。
- 创建一个 Activity 也需要实现一个或多个方法，其中最常见的就是实现onCreate(Bundle status)方法，该方法将会在Activity创建时被回调，该方法调用Activity 的setContentView(View view)方法来显示要展示的View。为了管理应用程序界面中的各组件，调用Activity的findViewByld(int id)方法来获取程序界面中的组件。

### ListActivity

- 如果应用程序界面只包括列表，则可以让应用程序继承ListActivity。

#### LauncherActivity

- LauncherActivity**继承了ListActivity**, 因此它本质上也是一个开发列表界面的Activity, 但它开发出来的列表界面与普通列表界面有所不同。它开发出来的列表界面中的每个列表项都对应于一个Intent,因此当用户单击不同的列表项时，应用程序会自动启动对应的Activity。

### 配置Activity

- Android应用要求所有应用程序组件( Activity、Service、 ContentProvider、 BroadcastReceiver)都必须显式进行配置。

- 只要为<application..>元素添加<activity...>子元素即可配置Activity。

  - 配置Activity 时通常指定属性

    - | 属性       | 说明                                                         |
      | ---------- | ------------------------------------------------------------ |
      | name       | 指定该Activity的实现类的类名                                 |
      | icon       | 指定该Activity对应的图标。                                   |
      | label      | 指定该Activity的标签。                                       |
      | exported   | 指定该Activity是否允许被其他应用调用。如果将该属性设为true,那么该Activity将可以被其他应用调用。 |
      | launchMode | 指定该Activity 的加载模式，该属性支持standard、singleTop、 singleTask 和singleInstance |

  - 在配置Activity时通常还需要指定一个或多个<intent-filter../>元素，该元素用于指定该Activity可响应的Intent。

### 启动、关闭Activity

- Activity启动其他Activity

  - | 方法                                                   | 说明                                                         |
    | ------------------------------------------------------ | ------------------------------------------------------------ |
    | startActivity(Intent intent)                           | 启动其他Activity。                                           |
    | startActivityForResult(Intent intent, int requestCode) | 以指定的请求码( requestCode)启动  Activity,而且程序将会获取新启动的Activity 返回的结果(通过重写onActivityResult()方法来获取)。 |

    - 启动Activity时可指定一个requestCode 参数，该参数代表了启动Activity的请求码。这个请求码的值由开发者根据业务自行设置，用于标识请求来源。

- 关闭Activity

  - | 方法                            | 说明                                                         |
    | ------------------------------- | ------------------------------------------------------------ |
    | finish()                        | 结束当前Activity。                                           |
    | finishActivity(int requestCode) | 结束以startActivityForResult(Intent intent, int requestCode)方法启动的Activity. |

### Bundle交换数据

- Activity之间进行数据交换使用“信使": Intent, 主要将需要交换的数据放入Intent 中。

  - 使用getIntent()方法就可以获得启动该Activity的Intent

- Intent数据交换方法

  - | 方法                             | 说明                                     |
    | -------------------------------- | ---------------------------------------- |
    | putExtras(Bundle data)           | 向Intent中放入需要“携带”的数据包。       |
    | Bundle getExtras()               | 取出Intent中所“携带”的数据包。           |
    | putExtra(String name, Xxx value) | 向Intent中按key-value 对的形式存入数据。 |
    | getXxxExtra(String name)         | 从Intent中按key取出指定类型的数据。      |

  - Bundle数据交换方法

    - | 方法                                           | 说明                                        |
      | ---------------------------------------------- | ------------------------------------------- |
      | putXxx(String key，Xxx data)                   | 向Bundle中放入Int、Long 等各种类型的数据。  |
      | putSerializable(String key, Serializable data) | 向Bundle中放入一个可序列化的对象。          |
      | getXxx(String key)                             | 从Bundle 中取出Int、Long 等各种类型的数据。 |
      | getSerializable(String key, Serializable data) | 从Bundle中取出一一个可序列化的对象。        |

```
单调性 奇偶性 周期性 有界性 奇函数如果在0点有定义,必须得有定义,那么他的函数值就一定等于0
```

route ip dhcp

| 名称                  | 类型   | 读写执行权限 | 描述                                                         | 默认值            | 支持度 |
| --------------------- | ------ | ------------ | ------------------------------------------------------------ | ----------------- | ------ |
| DHCPv4                | Object | RWP          | The Dynamic Host Configuration Protocol (DHCP) IPv4 object RFC2131. This entire object applies to IPv4 only. It contains the Client, Server, and Relay objects. | –                 | Y      |
| ClientNumberOfEntries | uint32 | RW           | The number of entries in the Client table.                   | default:          | Y      |
| DHCPv4.Client.{i}     | Object | RWP          | This object contains DHCP client settings for an associated IP Interface indicated by Interface. | –                 | Y      |
| IfName                | string | RWP          | ifname read from /etc/config/network will not be used when IP.Interface is available | default:          |        |
| Enable                | bool   | RWP          | Enables or disables the DHCP Client entry.                   | default: false    |        |
| Alias                 | string | RW           | A non-volatile unique key used to reference this instance. Alias provides a mechanism for a Controller to label this instance for future reference. | default:          |        |
| Interface             | string | RWP          | The value MUST be the Path Name of a row in the IP.Interface table. If the referenced object is deleted, the parameter value MUST be set to an empty string. The IP Interface associated with the Client entry. | default:          |        |
| Status                | string | R            | The status of this table entry. Enumeration of: - Disabled - Enabled - Error_Misconfigured | default: Disabled |        |
| DHCPStatus            | string | R            | The DHCP Client status as defined in RFC2131. Enumeration of: - Init - Selecting - Requesting - Rebinding - Bound - Renewing | default: Init     |        |
| IPAddress             | string | R            | IPv4 Address option received from the DHCP Server. An empty string when Status is not equal to Bound. | default:          |        |
| SubnetMask            | string | R            | Subnet mask option received from the DHCP Server. An empty string when Status is not equal to Bound. Value is information received via DHCP Option 1. | default:          |        |
| IPRouters             | string | R            | Comma-separated list (maximum list length 256) of IPv4Addresses. Items represent IP Router IPv4 Address(es) received from the DHCP server. An empty string when Status is not equal to Bound. Value is information received via DHCP Options 3, 33 or 121. | default:          |        |

lim f(x) =a 任意的e>0 存在X>0 当x>X的时候,恒有 |f(x) - a|<e

```
lan_mac=$(mtd_get_mac_ascii nvram lanmac)
wan_mac=$(mtd_get_mac_ascii nvram wanmac)
lan_mac=$(mtd_get_mac_ascii nvram lanmac)
cat /var/etc/environment
	[ -n "$wan_macaddr" ] && ucidef_set_interface_macaddr "wan" "$wan_macaddr"
	ifconfig eth0 hw ether 00:0C:29:0A:77:6E
	
	macaddr_random() {
	local randsrc=$(get_mac_binary /dev/urandom 0)

	echo "$(macaddr_unsetbit_mc "$(macaddr_setbit_la "${randsrc}")")"
}

openwrt-intel_x86-lgm-PRPL_MB_URX-641_pon.dtb
openwrt-intel_x86-lgm-PRPL_MB_URX-641_wav700_pon_fullimage.img
```

| Device.Ethernet.Interface.{i}. | object(0:) | R    | Ethernet interface table (a stackable interface object as described in [[Section 4.2/TR-181i2](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#R.TR-181i2)]). This table models physical Ethernet ports, but in terms of the interface stack it only models the PHY and Connection Access Method of the Ethernet interface MAC. A [*Link*](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#D.Device:2.Device.Ethernet.Link.) is also required to model a full Ethernet device.At most one entry in this table can exist with a given value for [*Alias*](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#D.Device:2.Device.Ethernet.Interface.Alias), or with a given value for [*Name*](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#D.Device:2.Device.Ethernet.Interface.Name). |
| ------------------------------ | ---------- | ---- | ------------------------------------------------------------ |
| Device.Ethernet.Link.{i}.      | object(0:) | W    | Ethernet link layer table (a stackable interface object as described in [[Section 4.2/TR-181i2](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#R.TR-181i2)]). Table entries model the Logical Link Control (LLC) layer. It is expected that an *Ethernet Link* interface can be stacked above any lower-layer interface object capable of carrying Ethernet frames.At most one entry in this table can exist with a given value for [*Alias*](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#D.Device:2.Device.Ethernet.Link.Alias), or with a given value for [*Name*](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#D.Device:2.Device.Ethernet.Link.Name), or with a given value for [*MACAddress*](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#D.Device:2.Device.Ethernet.Link.MACAddress). On creation of a new table entry, the Agent MUST (if not supplied by the Controller on creation) choose initial values for [*Alias*](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#D.Device:2.Device.Ethernet.Link.Alias), [*Name*](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#D.Device:2.Device.Ethernet.Link.Name) and [*MACAddress*](https://usp-data-models.broadband-forum.org/tr-181-2-18-1-usp.html#D.Device:2.Device.Ethernet.Link.MACAddress) such that the new entry does not conflict with any existing entries. |

```
IPv6：
1.IPV6 邻居发现协议ND
	地址解析:NS 发送和NA 响应
	重复地址检测:NS 发送和NA 响应
	前缀和默认路由发现:RS 和 RA
2.IPV4 V6过渡技术dslite 6in4 6to4 6rd()
3.对比学习ipv4和ipv6在协议,服务作用的联系和区别.

```

- tr181 High Level API接口验证:
  1. Device.IP:包括Interface, ActivePort, Diagnostics
  2. IP:包括Interface, ActivePort
  3. Device.Routing:包括Route 信息, Babel, and RIP 协议
- XGSPON 产测指令 lan相关接口确认

```shell
# Hardware MAC address can be setup in variety of ways by manufacturer. Following code tries to find a good match.
# - some manufacturer set it in /proc/environment/ethaddr
# - some manufacturer sets it on kernel command line
# - Assume first physical interface assigned in br-lan has a proper MAC Address setup
echo "111/proc/environment/ethaddr start " >> /tmp/log/test.txt
if [ -f "/proc/environment/ethaddr" ]; then
    HWMACADDRESS=$(cat /proc/environment/ethaddr  | tr [a-f] [A-F])
    echo "111/proc/environment/ethaddr HWMACADDRESS MAC Address: $HWMACADDRESS" >> /tmp/log/test.txt
elif grep -q ethaddr /proc/cmdline; then
    HWMACADDRESS=$(awk 'BEGIN{RS=" ";FS="="} $1 == "ethaddr" {print $2}' /proc/cmdline  | tr [a-f] [A-F])
    echo "111/proc/cmdline222 HWMACADDRESS MAC Address: $HWMACADDRESS" >> /tmp/log/test.txt

    # 打印 lan_mac 和 wan_mac 地址信息
    br_lan="$(cat /sys/class/net/br-lan/address | tr [a-f] [A-F])" 2>/dev/null
    echo "111ucidef_set_interface_macaddr222 default LAN MAC Address: $br_lan" >> /tmp/log/test.txt


elif [ -f "/etc/networklayout.json" ]; then
    LANIFNAME=$(jsonfilter -i "/etc/networklayout.json" -e '@.Bridges.Lan.Ports[0].Name')
    LANIFNAMEADDR=$(cat /sys/class/net/${LANIFNAME}/address | tr [a-f] [A-F])
    echo "111/etc/networklayout.json HWMACADDRESS MAC Address: $LANIFNAMEADDR" >> /tmp/log/test.txt

    if [ -n "$LANIFNAMEADDR" ] ; then
        HWMACADDRESS=$LANIFNAMEADDR
        echo "111/etc/networklayout.json set HWMACADDRESS=$LANIFNAMEADDR MAC Address: $HWMACADDRESS" >> /tmp/log/test.txt
    fi
fi

br_lan="$(cat /sys/class/net/br-lan/address | tr [a-f] [A-F])" 2>/dev/null
echo "ucidef_set_interface_macaddr111 default LAN MAC Address: $br_lan" >> /tmp/log/test.txt

wan_ifname="$(cat /proc/device-tree/wan/ifname)" 2>/dev/null

wanmac="$(cat /sys/class/net/$wan_ifname/address)" 2>/dev/null

echo "WAN MAC111 Address: wanmac = $wanmac" >> /tmp/log/test.txt
```

```
本周进展：

XGSPON 产测指令 lan相关接口确认
web ui接口确认

下周计划:
IPv6：
1.IPV6 邻居发现协议ND
	地址解析:NS 发送和NA 响应
	重复地址检测:NS 发送和NA 响应
	前缀和默认路由发现:RS 和 RA
2.IPV4 V6过渡技术dslite 6in4 6to4 6rd()
3.XGSPON 产测指令 lan相关接口确认
4.Zigbee 技术预研

```

```


（1）掌握程序的基本结构；变量与数据类型；变量声明与赋值。熟练掌握C语言程序的基本结构；掌握基本变量的声明与访问。
（2）掌握关系运算符与关系表达式；枚举法的思路；循环结构；分支结构。理解C语言最基本的运算符与关系表达式的概念；熟练掌握运算符与关系表达式的最基本使用；理解枚举法的基本思想；熟练掌握枚举法的最基本使用；理解循环结构；熟练掌握循环结构的最基本使用（for语句与while语句）；理解分支结构；熟练掌握分支结构的最基本使用。
（3）掌握数组与一维数组的基本使用；筛法与排序法；结构与结构数组；二维数组。理解数组的基本概念；熟练掌握一维数组的基本使用；掌握二维数组的基本使用；理解筛法与排序法；掌握筛选法与排序法的最基本的使用。理解结构的基本概念；熟练掌握结构的基本使用；掌握结构数组的基本使用。
（4）掌握函数的基本概念、使用与应用；递推；递归。理解函数的基本概念；熟练掌握函数的定义、声明、调用、返回的基本操作；了解递推数列的定义与算法的程序实现；了解递归及其实现的基本思路与方法。
（5）掌握指针的基本概念与基本使用；指针与数组；字符串及其处理；指针与结构；指针与结构数组；引用的概念与应用；利用引用来传递参数；几种参数传递方式的比较。理解指针的基本概念；掌握指针的基本使用；掌握指针与数组（一维）的基本使用；掌握字符串的基本使用；了解指针与结构；了解指针与结构数组；理解引用概念；掌握引用基本使用；了解利用引用传递参数；掌握几种参数传递方式的比较。
（6）掌握流的基本概念与常用输入输出流格式控制；文件流；理解I/O流、文件流的基本概念；熟练掌握I/O流与文件流的基本使用；了解I/O流与文件流的格式控制。


（1）了解数据结构和算法的形成和发展及在计算学科中所处的地位，并掌握数据结构和算法的基本概念和术语、算法描述及算法分析的基本方法。
（2）熟练掌握顺序线性表和链式线性表（重点单链表）及其基本操作，以及简单应用；顺序存储和链式存储的优缺点。
（3）掌握栈和队列的结构特征、顺序存储结构、基本操作及其简单应用；讲述栈与递归的关系，掌握核心概念——递归。
（4）掌握数组的定义及地址公式、特殊矩阵的存储方式及地址公式、稀疏矩阵的三元组存储方式及基本运算；了解广义表的基本概念。
（5）掌握树及二叉树的基本概念、基本性质和存储结构；熟练掌握二叉树的各种遍历（前序、中序、后序、层次）和恢复；掌握树、森林与二叉树的关系，及其相互转换方法；熟练掌握哈夫曼树及其编码的算法。
（6）掌握图的基本概念和存储结构（邻接矩阵、邻接表），掌握图的基本类型与运算（连通图、有向无环图、图的遍历）及各类典型应用（最小生成树、拓扑排序、关键路径、最短路径）。
（7）掌握各类存储结构的查找算法（顺序查找、折半查找、分块查找、二叉排序树查找），理解静态查找与动态查找的区别，掌握哈希表的基本思想、函数构造、冲突处理方法及查找）。
（8）掌握内部排序的基本概念及其常用排序方法（插入排序、二分插入排序、希尔排序、冒泡排序、快速排序、选择排序、堆排序、归并排序、基数排序），掌握各种不同排序方法的适用场合。
```

```
1.prplos 功能实现
	1.上网设置	Dynamic DNS 如果这个要整,我们该怎么来整理这块的内容
	2.系统管理	https,ssh
	3.诊断方式	镜像端口
2.MIFI feature确认
	1.Blacklist/White list(MAC filtering)
	2.Firewall(Port block/white list, port forwarding,)
```

```
localhost;127.*;10.*;172.16.*;172.17.*;172.18.*;172.19.*;172.20.*;172.21.*;172.22.*;172.23.*;172.24.*;172.25.*;172.26.*;172.27.*;172.28.*;172.29.*;172.30.*;172.31.*;192.168.*;localhost;127.*;10.*;172.16.*;172.17.*;172.18.*;172.19.*;172.20.*;172.21.*;172.22.*;172.23.*;172.24.*;172.25.*;172.26.*;172.27.*;172.28.*;172.29.*;172.30.*;172.31.*;192.168.*;*.tinno.com;*.dajitec.com;*.techmahindra.com;*.moxueyuan.com;*.baidu.com;docs.qq.com;*.csdn.net;tinnohr.wjx.cn;hefty.nets.hk;43.153.171.136;*.parawikis.com;*.qq.com;*.weixinbridge.com
```

```
        "Device.DynamicDNS.Client.1.": {
                "HostnameNumberOfEntries": 0,
                "Username": "",
                "Password": "",
                "Interface": "",
                "Status": "Error_Misconfigured",
                "LastError": "MISCONFIGURATION_ERROR",
                "Enable": false,
                "Alias": "cpe-Client-1",
                "Server": ""
        },
155200+42000+405000 = 602200

```

```
adb shell am start -W -n com.android.settings/.homepage.SettingsHomepageActivity

# 1. 将所有访问 192.168.0.1:80 的流量重定向到 127.0.0.1:80
iptables -t nat -A PREROUTING -d 192.168.0.1 -p tcp --dport 80 -j DNAT --to-destination 127.0.0.1:80

# 2. ACL 规则：允许来源为 192.168.0.100 的主机访问 192.168.0.1:80
iptables -A INPUT -p tcp -s 192.168.0.100 -d 192.168.0.1 --dport 80 -j ACCEPT

# 3. 丢弃其他所有访问 192.168.0.1:80 的流量
iptables -A INPUT -p tcp -d 192.168.0.1 --dport 80 -j DROP

```

```
FAILED: out_sys/soong/.intermediates/system/sepolicy/plat_sepolicy.cil/android_common/plat_sepolicy.cil
out_sys/host/linux-x86/bin/checkpolicy -C -M -c 30 -o out_sys/soong/.intermediates/system/sepolicy/plat_sepolicy.cil/android_common/plat_sepolicy.cil out_sys/soong/.intermediates/system/sepolicy/plat_sepolicy.conf/android_common/plat_sepolicy.conf && cat system/sepolicy/private/technical_debt.cil >>  out_sys/soong/.intermediates/system/sepolicy/plat_sepolicy.cil/android_common/plat_sepolicy.cil && out_sys/host/linux-x86/bin/secilc -m -M true -G -c 30 out_sys/soong/.intermediates/system/sepolicy/plat_sepolicy.cil/android_common/plat_sepolicy.cil -o /dev/null -f /dev/null # hash of input list: b2fb36d73cb820c27449c816f63a602f6e273fc6f61bf79de6eaee837802b968
neverallow check failed at out_sys/soong/.intermediates/system/sepolicy/plat_sepolicy.cil/android_common/plat_sepolicy.cil:14633 from system/sepolicy/private/domain.te:1167
  (neverallow base_typeattr_223 base_typeattr_421 (file (entrypoint)))
    <root>
    allow at out_sys/soong/.intermediates/system/sepolicy/plat_sepolicy.cil/android_common/plat_sepolicy.cil:16694
      (allow fxjmotnitor fxjmotnitor_exec (file (read getattr map execute open entrypoint)))
    <root>
    allow at out_sys/soong/.intermediates/system/sepolicy/plat_sepolicy.cil/android_common/plat_sepolicy.cil:16698
      (allow fxjmotnitor shell_exec (file (read getattr entrypoint)))

Failed to generate binary
Failed to build policydb
[  2% 137/4825 42m23s remaining] //system/sepolicy:product_sepolicy.conf Transform policy to conf: product_sepolicy.conf
[  2% 138/4825 42m23s remaining] //system/sepolicy:system_ext_sepolicy.conf Transform policy to conf: system_ext_sepolicy.conf
[  2% 139/4825 41m58s remaining] //system/sepolicy:plat_pub_policy.cil Building cil for plat_pub_policy.cil
[  2% 140/4825 39m32s remaining] //frameworks/base:framework-minus-apex-headers turbine
FAILED: out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/turbine/framework.jar
prebuilts/jdk/jdk21/linux-x86/bin/java -XX:OnError="cat hs_err_pid%p.log" -XX:CICompilerCount=6 -XX:+UseDynamicNumberOfGCThreads -jar out_sys/host/linux-x86/framework/turbine.jar --output out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/turbine/framework.jar.tmp --sources @out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/turbine/framework.jar.rsp  --source_jars out_sys/soong/.intermediates/frameworks/av/media/audio/aconfig/android.media.audio-aconfig-java/android_common/gen/android.media.audio-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/av/media/audio/aconfig/android.media.audiopolicy-aconfig-java/android_common/gen/android.media.audiopolicy-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/av/media/audio/aconfig/android.media.midi-aconfig-java/android_common/gen/android.media.midi-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/av/media/audio/aconfig/android.media.soundtrigger-aconfig-java/android_common/gen/android.media.soundtrigger-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/av/media/aconfig/aconfig_mediacodec_flags_java_lib/android_common/gen/aconfig_mediacodec_flags_java_lib.srcjar out_sys/soong/.intermediates/frameworks/base/android.adaptiveauth.flags-aconfig-java/android_common/gen/android.adaptiveauth.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.app.contextualsearch.flags-aconfig-java/android_common/gen/android.app.contextualsearch.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.app.flags-aconfig-java/android_common/gen/android.app.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.app.ondeviceintelligence-aconfig-java/android_common/gen/android.app.ondeviceintelligence-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.app.smartspace.flags-aconfig-java/android_common/gen/android.app.smartspace.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.app.usage.flags-aconfig-java/android_common/gen/android.app.usage.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.app.wearable.flags-aconfig-java/android_common/gen/android.app.wearable.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.appwidget.flags-aconfig-java/android_common/gen/android.appwidget.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.chre.flags-aconfig-java/android_common/gen/android.chre.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.companion.flags-aconfig-java/android_common/gen/android.companion.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.companion.virtual.flags-aconfig-java/android_common/gen/android.companion.virtual.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.companion.virtualdevice.flags-aconfig-java/android_common/gen/android.companion.virtualdevice.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.content.flags-aconfig-java/android_common/gen/android.content.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.content.pm.flags-aconfig-java/android_common/gen/android.content.pm.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.content.res.flags-aconfig-java/android_common/gen/android.content.res.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.crashrecovery.flags-aconfig-java/android_common/gen/android.crashrecovery.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.credentials.flags-aconfig-java/android_common/gen/android.credentials.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.database.sqlite-aconfig-java/android_common/gen/android.database.sqlite-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.hardware.biometrics.flags-aconfig-java/android_common/gen/android.hardware.biometrics.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.hardware.devicestate.feature.flags-aconfig-java/android_common/gen/android.hardware.devicestate.feature.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.hardware.flags-aconfig-java/android_common/gen/android.hardware.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.hardware.radio.flags-aconfig-java/android_common/gen/android.hardware.radio.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.hardware.usb.flags-aconfig-java/android_common/gen/android.hardware.usb.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.location.flags-aconfig-java/android_common/gen/android.location.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/av/media/aconfig/android.media.codec-aconfig-java/android_common/gen/android.media.codec-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.media.tv.flags-aconfig-java/android_common/gen/android.media.tv.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.multiuser.flags-aconfig-java/android_common/gen/android.multiuser.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.net.platform.flags-aconfig-java/android_common/gen/android.net.platform.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.net.vcn.flags-aconfig-java/android_common/gen/android.net.vcn.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.net.wifi.flags-aconfig-java/android_common/gen/android.net.wifi.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.nfc.flags-aconfig-java/android_common/gen/android.nfc.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.os.flags-aconfig-java/android_common/gen/android.os.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.os.vibrator.flags-aconfig-java/android_common/gen/android.os.vibrator.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.permission.flags-aconfig-java/android_common/gen/android.permission.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.provider.flags-aconfig-java/android_common/gen/android.provider.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.security.flags-aconfig-java/android_common/gen/android.security.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.server.app.flags-aconfig-java/android_common/gen/android.server.app.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.service.autofill.flags-aconfig-java/android_common/gen/android.service.autofill.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.service.chooser.flags-aconfig-java/android_common/gen/android.service.chooser.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.service.controls.flags-aconfig-java/android_common/gen/android.service.controls.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.service.dreams.flags-aconfig-java/android_common/gen/android.service.dreams.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.service.notification.flags-aconfig-java/android_common/gen/android.service.notification.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.service.appprediction.flags-aconfig-java/android_common/gen/android.service.appprediction.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.service.voice.flags-aconfig-java/android_common/gen/android.service.voice.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.speech.flags-aconfig-java/android_common/gen/android.speech.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.systemserver.flags-aconfig-java/android_common/gen/android.systemserver.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.tracing.flags-aconfig-java/android_common/gen/android.tracing.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.view.accessibility.flags-aconfig-java/android_common/gen/android.view.accessibility.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.view.contentcapture.flags-aconfig-java/android_common/gen/android.view.contentcapture.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.view.contentprotection.flags-aconfig-java/android_common/gen/android.view.contentprotection.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.view.flags-aconfig-java/android_common/gen/android.view.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.view.inputmethod.flags-aconfig-java/android_common/gen/android.view.inputmethod.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.webkit.flags-aconfig-java/android_common/gen/android.webkit.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/android.widget.flags-aconfig-java/android_common/gen/android.widget.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/backstage_power_flags_lib/android_common/gen/backstage_power_flags_lib.srcjar out_sys/soong/.intermediates/frameworks/base/backup_flags_lib/android_common/gen/backup_flags_lib.srcjar out_sys/soong/.intermediates/frameworks/base/camera_platform_flags_core_java_lib/android_common/gen/camera_platform_flags_core_java_lib.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.hardware.input-aconfig-java/android_common/gen/com.android.hardware.input-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.input.flags-aconfig-java/android_common/gen/com.android.input.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.internal.compat.flags-aconfig-java/android_common/gen/com.android.internal.compat.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.internal.foldables.flags-aconfig-java/android_common/gen/com.android.internal.foldables.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.internal.os.flags-aconfig-java/android_common/gen/com.android.internal.os.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.internal.pm.pkg.component.flags-aconfig-java/android_common/gen/com.android.internal.pm.pkg.component.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.media.flags.bettertogether-aconfig-java/android_common/gen/com.android.media.flags.bettertogether-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.media.flags.editing-aconfig-java/android_common/gen/com.android.media.flags.editing-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.media.flags.performance-aconfig-java/android_common/gen/com.android.media.flags.performance-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.media.flags.projection-aconfig-java/android_common/gen/com.android.media.flags.projection-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.net.thread.platform.flags-aconfig-java/android_common/gen/com.android.net.thread.platform.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.server.contextualsearch.flags-java/android_common/gen/com.android.server.contextualsearch.flags-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.server.flags.services-aconfig-java/android_common/gen/com.android.server.flags.services-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.text.flags-aconfig-java/android_common/gen/com.android.text.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/com.android.window.flags.window-aconfig-java/android_common/gen/com.android.window.flags.window-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/device_policy_aconfig_flags_lib/android_common/gen/device_policy_aconfig_flags_lib.srcjar out_sys/soong/.intermediates/frameworks/base/display_flags_lib/android_common/gen/display_flags_lib.srcjar out_sys/soong/.intermediates/frameworks/base/dropbox_flags_lib/android_common/gen/dropbox_flags_lib.srcjar out_sys/soong/.intermediates/frameworks/base/framework-jobscheduler-job.flags-aconfig-java/android_common/gen/framework-jobscheduler-job.flags-aconfig-java.srcjar out_sys/soong/.intermediates/frameworks/base/framework_graphics_flags_java_lib/android_common/gen/framework_graphics_flags_java_lib.srcjar out_sys/soong/.intermediates/frameworks/base/hwui_flags_java_lib/android_common/gen/hwui_flags_java_lib.srcjar out_sys/soong/.intermediates/frameworks/base/power_flags_lib/android_common/gen/power_flags_lib.srcjar out_sys/soong/.intermediates/packages/modules/AdServices/sdksandbox/flags/sdk_sandbox_flags_lib/android_common/gen/sdk_sandbox_flags_lib.srcjar out_sys/soong/.intermediates/frameworks/base/surfaceflinger_flags_java_lib/android_common/gen/surfaceflinger_flags_java_lib.srcjar out_sys/soong/.intermediates/frameworks/base/telecom_flags_core_java_lib/android_common/gen/telecom_flags_core_java_lib.srcjar out_sys/soong/.intermediates/frameworks/base/telephony_flags_core_java_lib/android_common/gen/telephony_flags_core_java_lib.srcjar out_sys/soong/.intermediates/frameworks/base/core/res/framework-res/android_common/gen/android/R.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/system/server_configurable_flags/aconfigd/aconfigd.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/proto/src/ipconnectivity.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/app/app_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/app/media_output_enum.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/app/remoteprovisioner_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/app/settings_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/app/tvsettings_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/app/wearsettings_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/app/job/job_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/app/wearservices/wearservices_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/display/display_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/adservices/common/adservices_api_metrics_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/adservices/common/adservices_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/adservices/enrollment/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/adservices/fledge/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/adservices/measurement/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/anr/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/apex/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/appsearch/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/autofill/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/contexthub/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/corenetworking/connectivity/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/corenetworking/platform/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/debug/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/devicepolicy/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/dnd/dnd_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/federatedcompute/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/hardware/biometrics/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/hardware/sensor/assist/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/healthfitness/api/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/healthfitness/ui/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/hotword/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/input/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/jank/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/media/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/media/audio/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/media/codec/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/media/drm/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/media/editing/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/media/hdr/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/media/midi/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/media/outputswitcher/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/mms/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/nearby/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/net/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/neuralnetworks/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/nfc/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/os/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/photopicker/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/pdf/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/server/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/server/display/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/server/job/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/server/location/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/service/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/service/procstats_enum.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/accessibility/accessibility_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/docsui/docsui_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/hdmi/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/ike/ike.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/intelligence/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/location/location_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/mediametrics/mediametrics.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/mediaprovider/mediaprovider_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/mobiledatadownload/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/otaupdate/updateengine_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/privacysignals/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/safetycenter/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/storage/storage_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/sysui/notification_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/tls/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/tv/tif_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/wm/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/system/security/keystore2/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/telecomm/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/telephony/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/telephony/qns/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/telephony/satellite/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/telephony/security/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/transparency/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/uwb/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/view/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/view/inputmethod/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/wear/connectivity/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/wear/media/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/wear/modes/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/wifi/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/server/connectivity/data_stall_event.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/devicepolicy/device_policy.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/devicepolicy/device_policy_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/dnsresolver/dns_resolver.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/launcher/launcher.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/connectivity/network_stack.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/connectivity/connectivity_service.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/bluetooth/a2dp/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/bluetooth/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/bluetooth/hci/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/bluetooth/hfp/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/bluetooth/le/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/bluetooth/rfcomm/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/bluetooth/smp/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/bluetooth/leaudio/enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/style/style_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/connectivity/tethering.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/enums/stats/textclassifier/textclassifier_enums.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/proto_logging/stats/message/mediametrics_message.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/system/core/debuggerd/proto/tombstone.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/privacy.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/section.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/typedef.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/activitymanager.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/alarmmanager.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/appexitinfo.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/appstartinfo.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/location_time_zone_manager.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/notification.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/notification_channel.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/notification_channel_group.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/notificationmanager.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/pendingintent.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/profilerinfo.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/statusbarmanager.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/time_zone_detector.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/app/window_configuration.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/companion/context_sync_message.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/companion/telecom.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/content/activityinfo.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/content/clipdata.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/content/clipdescription.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/content/component_name.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/content/configuration.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/content/featureinfo.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/content/intent.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/content/locale.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/content/locusid.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/content/package_item_info.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/graphics/pixelformat.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/graphics/point.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/graphics/rect.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/hardware/sensorprivacy.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/hardware/location/context_hub_info.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/input/keyboard_configured.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/inputmethodservice/inputmethodservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/inputmethodservice/softinputwindow.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/internal/binder_latency.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/internal/locallog.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/internal/powerprofile.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/internal/processstats.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/internal/protolog.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/media/audioattributes.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/net/network.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/net/networkcapabilities.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/net/networkrequest.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/nfc/aid_group.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/nfc/apdu_service_info.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/nfc/card_emulation.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/nfc/ndef.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/nfc/nfc_fservice_info.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/nfc/nfc_service.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/appbackgroundrestrictioninfo.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/appbatterystats.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/backtrace.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/batterystats.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/batterytype.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/batteryusagestats.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/bundle.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/cpu_usage.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/cpufreq.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/cpuinfo.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/data.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/header.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/incident.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/kernelwake.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/looper.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/message.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/messagequeue.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/metadata.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/pagetypeinfo.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/patternmatcher.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/persistablebundle.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/powermanager.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/procrank.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/ps.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/statsdata.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/system_properties.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/tombstone.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/os/worksource.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/providers/settings.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/providers/settings/common.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/providers/settings/config.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/providers/settings/generation.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/providers/settings/global.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/providers/settings/secure.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/providers/settings/system.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/accessibility.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/accessibilitytrace.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/activitymanagerservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/animationadapter.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/apphibernationservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/appstatetracker.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/background_install_control.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/biometrics.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/blobstoremanagerservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/bluetooth_manager_service.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/face.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/fingerprint.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/intentresolver.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/jobscheduler.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/notificationhistory.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/peopleservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/powermanagerservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/powerstatsservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/statlogger.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/surfaceanimator.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/syncstorageengine.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/usagestatsservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/usagestatsservice_v2.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/windowcontainerthumbnail.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/windowmanagerservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/windowmanagertrace.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/windowmanagertransitiontrace.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/wirelesschargerdetector.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/alarm/alarmmanagerservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/inputmethod/inputmethodmanagerservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/location/context_hub.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/server/vibrator/vibratormanagerservice.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/adb.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/appwidget.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/battery.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/batterystats.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/diskstats.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/dropbox.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/graphicsstats.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/network_watchlist.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/notification.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/package.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/print.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/procstats.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/restricted_image.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/runtime.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/sensor_service.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/service/usb.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/util/common.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/util/event_log_tags.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/util/log.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/util/quotatracker.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/util/textdump.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/display.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/displaycutout.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/displayinfo.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/imefocuscontroller.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/imeinsetssourceconsumer.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/insetsanimationcontrolimpl.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/insetscontroller.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/insetssource.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/insetssourceconsumer.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/insetssourcecontrol.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/insetsstate.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/remote_animation_target.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/surface.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/surfacecontrol.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/viewrootimpl.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/windowlayoutparams.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/inputmethod/editorinfo.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/inputmethod/inputconnection.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/inputmethod/inputmethodeditortrace.srcjar out_sys/soong/.intermediates/frameworks/base/framework-javastream-protos/gen/gensrcs/frameworks/base/core/proto/android/view/inputmethod/inputmethodmanager.srcjar out_sys/soong/.intermediates/hardware/interfaces/audio/7.0/config/audio_policy_configuration_V7_0/gen/java/android_audio_policy_configuration_V7_0_xsdcgen.srcjar out_sys/soong/.intermediates/system/apex/apexd/apex-info-list/gen/java/com_android_apex_xsdcgen.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl0.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl1.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl2.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl3.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl4.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl5.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl6.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl7.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl8.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl9.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl10.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl11.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl12.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl13.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl14.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl15.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl16.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl17.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl18.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl19.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl20.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl21.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl22.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl23.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl24.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl25.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl26.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl27.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl28.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl29.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl30.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl31.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl32.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl33.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl34.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl35.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl36.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl37.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl38.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl39.srcjar out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/gen/aidl/aidl40.srcjar --javacopts -Xmaxerrs 9999999 -encoding UTF-8 -sourcepath "" -g -XDskipDuplicateBridges=true -XDstringConcat=inline -J--add-modules=jdk.compiler -J--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED -J--add-exports=jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED -J--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED -J--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED -g:source,lines -Xlint:-dep-ann -source 17 -target 17 -- --system out_sys/soong/.intermediates/build/soong/java/core-libraries/legacy-core-platform-api-stubs-system-modules/android_common/system --classpath out_sys/soong/.intermediates/frameworks/libs/modules-utils/java/aconfig-annotations-lib/android_common/turbine-combined/aconfig-annotations-lib.jar out_sys/soong/.intermediates/prebuilts/sdk/current/androidx/m2repository/androidx/annotation/annotation-jvm/1.9.0-alpha01/androidx.annotation_annotation/android_common/combined/androidx.annotation_annotation.jar out_sys/soong/.intermediates/tools/platform-compat/java/android/compat/annotation/app-compat-annotations/android_common/turbine-combined/app-compat-annotations.jar out_sys/soong/.intermediates/frameworks/base/ext/android_common/turbine-combined/ext.jar out_sys/soong/.intermediates/frameworks/base/api/framework-updatable-stubs-module_libs_api/android_common/74863456f64fd0ab20b0b2e2dffba8a0/turbine-combined/framework-updatable-stubs-module_libs_api.jar out_sys/soong/.intermediates/tools/platform-compat/java/android/compat/annotation/unsupportedappusage/android_common/turbine-combined/unsupportedappusage.jar out_sys/soong/.intermediates/hardware/interfaces/soundtrigger/aidl/android.hardware.soundtrigger3-V2-java/android_common/turbine-combined/android.hardware.soundtrigger3-V2-java.jar out_sys/soong/.intermediates/hardware/interfaces/common/fmq/aidl/android.hardware.common.fmq-V1-java/android_common/turbine-combined/android.hardware.common.fmq-V1-java.jar out_sys/soong/.intermediates/external/bouncycastle/bouncycastle-repackaged-unbundled/android_common/turbine-combined/bouncycastle-repackaged-unbundled.jar out_sys/soong/.intermediates/frameworks/base/core/sysprop/com.android.sysprop.foldlockbehavior/android_common/turbine-combined/com.android.sysprop.foldlockbehavior.jar out_sys/soong/.intermediates/frameworks/base/core/sysprop/com.android.sysprop.view/android_common/turbine-combined/com.android.sysprop.view.jar out_sys/soong/.intermediates/frameworks/base/framework-internal-utils/android_common/turbine-combined/framework-internal-utils.jar out_sys/soong/.intermediates/frameworks/base/mime/mimemap/android_common/turbine-combined/mimemap.jar out_sys/soong/.intermediates/frameworks/av/av-types-aidl-java/android_common/turbine-combined/av-types-aidl-java.jar out_sys/soong/.intermediates/frameworks/base/media/java/android/media/tv/tunerresourcemanager/tv_tuner_resource_manager_aidl_interface-java/android_common/turbine-combined/tv_tuner_resource_manager_aidl_interface-java.jar out_sys/soong/.intermediates/frameworks/base/media/soundtrigger_middleware-aidl-java/android_common/turbine-combined/soundtrigger_middleware-aidl-java.jar out_sys/soong/.intermediates/frameworks/libs/modules-utils/java/com/android/modules/utils/modules-utils-binary-xml/android_common/turbine-combined/modules-utils-binary-xml.jar out_sys/soong/.intermediates/frameworks/libs/modules-utils/java/com/android/modules/utils/build/modules-utils-build/android_common/turbine-combined/modules-utils-build.jar out_sys/soong/.intermediates/frameworks/libs/modules-utils/java/com/android/internal/util/modules-utils-fastxmlserializer/android_common/turbine-combined/modules-utils-fastxmlserializer.jar out_sys/soong/.intermediates/frameworks/libs/modules-utils/java/com/android/internal/util/modules-utils-preconditions/android_common/turbine-combined/modules-utils-preconditions.jar out_sys/soong/.intermediates/frameworks/libs/modules-utils/java/com/android/internal/util/modules-utils-statemachine/android_common/turbine-combined/modules-utils-statemachine.jar out_sys/soong/.intermediates/frameworks/libs/modules-utils/java/com/android/modules/utils/modules-utils-synchronous-result-receiver/android_common/turbine-combined/modules-utils-synchronous-result-receiver.jar out_sys/soong/.intermediates/frameworks/libs/modules-utils/java/com/android/modules/utils/modules-utils-os/android_common/turbine-combined/modules-utils-os.jar out_sys/soong/.intermediates/frameworks/libs/modules-utils/java/com/android/internal/logging/modules-utils-uieventlogger-interface/android_common/turbine-combined/modules-utils-uieventlogger-interface.jar out_sys/soong/.intermediates/frameworks/native/libs/permission/framework-permission-aidl-java/android_common/turbine-combined/framework-permission-aidl-java.jar out_sys/soong/.intermediates/frameworks/av/media/libaudioclient/spatializer-aidl-java/android_common/turbine-combined/spatializer-aidl-java.jar out_sys/soong/.intermediates/frameworks/av/media/libaudioclient/audiopolicy-aidl-java/android_common/turbine-combined/audiopolicy-aidl-java.jar out_sys/soong/.intermediates/frameworks/av/media/libaudioclient/sounddose-aidl-java/android_common/turbine-combined/sounddose-aidl-java.jar out_sys/soong/.intermediates/frameworks/libs/modules-utils/java/com/android/modules/expresslog/modules-utils-expresslog/android_common/74863456f64fd0ab20b0b2e2dffba8a0/turbine-combined/modules-utils-expresslog.jar out_sys/soong/.intermediates/frameworks/base/perfetto_trace_javastream_protos_jarjar/android_common/turbine-jarjar/perfetto_trace_javastream_protos_jarjar.jar out_sys/soong/.intermediates/build/make/tools/aconfig/aconfig_protos/libaconfig_java_proto_nano/android_common/turbine-jarjar/libaconfig_java_proto_nano.jar && (for o in out_sys/soong/.intermediates/frameworks/base/framework-minus-apex-headers/android_common/18c59a87d3904d958e1de08024dea9f9/turbine/framework.jar; do if cmp -s ${o}.tmp ${o} ; then rm ${o}.tmp ; else mv ${o}.tmp ${o} ; fi; done )
frameworks/base/core/java/android/app/FXJNETManager.java:11: error: could not resolve List
 public void addNetworkRestriction(List<String> ipName,int type) {
```

```
iptables -w 10 -A oem_in -s 127.0.0.1 -j DROP
iptables -L -v -n --line-numbers
Dear MTK:

是的,这个重新编译之后,check命令是可以正常执行的,而且在对应的chain里能看到对应的规则.

目前需要获取我们设置之后的内容,所以还有一个问题是iptables的get操作,好像没看到这块有,目前看到的是状态码的response,如果需要获得iptables命令的输出,比如iptables -t nat -L -v -n,有api或者可供参考的地方吗?请MTK帮忙check一下.

thanks
Dear MTK,

command 127退出了,不确定是什么地方,我使用了下面几种方式,都是127退出码,看上去这个退出码是没找到命令,请MTK check一下



//退出码127

12-24 03:01:58.506 E/NetdagentFirewall( 1033): Command exited with status 127



//使用的三种命令,都返回127

1.const char *command = "iptables -t nat -nvL";

2.const char *command = "sh -c \"/system/bin/iptables -t nat -nvL\"";
3.const char *command = "/system/bin/iptables -t nat -nvL";

networkmanagerservice

networkpolicyservice
所以我也一直在准备,希望到时候不止灵魂合二为一,身体也能并驾齐驱
```

```
%populate {
    object DynamicDNS {
        object 'Client' {
            instance add(1, 'cpe-Client-1', 'Alias' = "cpe-Client-1") {
                parameter 'Username' = "123" {
                    userflags  %upc_changed, %usersetting;
                }
                parameter 'Password' = "123" {
                    userflags  %upc_changed, %usersetting;
                }
                parameter 'Interface' = "" {
                    userflags  %upc_changed, %usersetting;
                }
                parameter 'Status' = "Error_Misconfigured";
                parameter 'LastError' = "MISCONFIGURATION_ERROR";
                parameter 'Enable' = true {
                    userflags  %upc_changed, %usersetting;
                }
                parameter 'Alias';
                parameter 'Server' = "Device.DynamicDNS.Server.13" {
                    userflags  %upc_changed, %usersetting;
                }
                object 'Hostname' {
                }
            }
        }        
    }
}
```

```
     @Override
     // return 0 for success
     public int setPortForwardingRules(String packageName, String rules) {
         return 0;
     }
     @Override
     // return 0 for success
     public String getPortForwardingRules() {

         return "";
     }
     @Override
     public void setNetworkFilteringRules(String packageName, String rules){

     }
     @Override
     public String getNetworkFilteringRules(){
        return "";
     }
     @Override
     public int setLanConfig(String config){
        return 0;
     }
     @Override
     public String getLanConfig(){
        return "";
     }

     @Override
     public int setReserveIpConfig(String packageName, String config){
        return 0;
     }
```

```
老滚5 怪物猎人崛起 美国末日 消失的 风花雪月 塞尔达 宝可梦 刺客信条 黑暗之魂3

gta 三部曲 文明6 怪物猎人:崛起 女神异闻录5r 马里奥奥德赛 

最终幻想7 
本周进展：
1.prplos 功能实现:
	1.上网设置	Dynamic DNS check对应的代码实现.  ubus 命令功能实现对应属性设置,向上层提	 供对应的ubus接口. 还需要环境来具体验证
	2.系统管理	1.ssh ubus 命令能实现对应属性设置,向上层提供对应的ubus接口命令
2.https 需要check对应的 lighttpd/uhttpd功能,在https上的实现
	3.诊断方式	镜像端口 check Firewall.Level和NAT.PortMapping的实现情况

2.MIFI feature确认:  netdagent 实现iptables 的set功能,目前验证可以正常set.对应iptables的get规则还需要编译验证.
	1.Blacklist/White list(MAC filtering) 
	2.Firewall(Port block/white list, port forwarding)
3.DHCP  主要开始处理lan ip的修改,
下周计划:
1.prplos 功能实现:
	1.上网设置	Dynamic DNS
	2.系统管理	https,ssh
	3.诊断方式	镜像端口
2.MIFI feature确认:
	1.Blacklist/White list(MAC filtering) 
	2.Firewall(Port block/white list, port forwarding)
vendor/tinno/common/frameworks/base/core/java/com/tinno/provider/
/share/B321MH/androidv/packages/modules/NetworkStack/src/android/net/dhcp
/share/B321MH/androidv/vendor/mediatek/proprietary/packages/services/Telephony/src/com/android/phone
```

```

```


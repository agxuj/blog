

Broadcast是一种广泛运用的在应用程序之间传输信息的机制。而BroadcastReceiver是对发送出来的 Broadcast进行过滤接受并响应的一类组件。

下面将详细的阐述如何发送Broadcast和使用BroadcastReceiver过滤接收的过程：

# 简要使用说明

首先在需要发送信息的地方，把要发送的信息和用于过滤的信息(如Action、Category)装入一个Intent对象，然后通过调用 sendOrderBroadcast()或sendStickyBroadcast()方法，把 Intent对象以广播方式发送出去。

当Intent发送以后，所有已经注册的BroadcastReceiver会检查注册时的IntentFilter是否与发送的Intent相匹配，若匹配则就会调用BroadcastReceiver的onReceive()方法。所以当我们定义一个BroadcastReceiver的时候，都需要实现onReceive()方法。

# 注册
注册BroadcastReceiver有两种方式:

## 静态注册

在AndroidManifest.xml中用标签生命注册，并在标签内用标签设置过滤器。

`````
<receiver android:name="myRecevice">    //继承BroadcastReceiver，重写onReceiver方法
　　<intent-filter>    
　　　　<action android:name="com.dragon.net"></action> //使用过滤器，接收指定action广播
　　</intent-filter>
</receiver>
`````

优点：不受其他组件生命周期影响，即使应用程序被关闭，也能接收广播。

缺点：耗电，占内存。

适用场景：需要时刻监听的广播。

事实上在，google因为电池优化的原因从7.0开始已经对静态注册做出了一些限制（权限），并在8.0使大部分静态注册失效了，极少数例如ACTION_BOOT_COMPLETED, ACTION_HEADSET_PLUG, ACTION_CONNECTION_STATE_CHANGED 等广播依旧有效。可以说，未来使用动态注册已经是一种趋势，不过在兼容低版本时，静态注册依旧好用。

**Android 8.0静态广播失效解决办法**
[Android 8.0行为变更文档地址](https://developer.android.google.cn/about/versions/oreo/android-8.0-changes)


## 动态注册
`````
IntentFilter intentFilter = new IntentFilter();
intentFilter.addAction(String);                     //为BroadcastReceiver指定action，使之用于接收同action的广播
registerReceiver(BroadcastReceiver,intentFilter);
`````

优点：灵活，不耗电，易控，省内存

缺点：<span style="color:red">需要手动注销</span>

适用场景：需要特定时候监听的广播

# 发送广播
`````
Intent intent = new Intent(actionString);           //指定广播目标Action
intent.putExtra("msg", "hi,我通过广播发送消息了");     //并且可通过Intent携带消息
context.sendBroadcast(intent);                      //发送广播消息
`````

# 广播类型

## 有序广播
有序广播 是一种分先后广播接收器的广播，广播接收者的优先级越高，越先接收广播。优先级高的广播先收到广播，收到广播后可以修改广播的内容，也可以拦截广播不让广播向下传递。

## 无序广播
无序广播 指所有与之匹配的广播接收者都能收到广播，没有先后顺序，直到没有广播接收者接收广播为止才会停止广播的传递。

## sticky广播
已经被弃用
 

# 参考

[Android中BroadCastReceiver使用（整理）](https://www.cnblogs.com/jico/articles/1838293.html)

[BroadcastReceiver](https://www.jianshu.com/p/7b44d983c01a)

[BroadcastReceiver详解以及应用](https://blog.csdn.net/huiblog/article/details/53234544)
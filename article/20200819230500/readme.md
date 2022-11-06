<h1 style="font-size: 2.5em;"> Android Broadcast</h1>
 



Broadcast是一种广泛运用的在应用程序之间传输信息的机制。�?�BroadcastReceiver是对发�?�出来的 Broadcast进行过滤接受并响应的�?类组件�??

下面将详细的阐述如何发�?�Broadcast和使用BroadcastReceiver过滤接收的过程：

# �?要使用说�?

首先在需要发送信息的地方，把要发送的信息和用于过滤的信息(如Action、Category)装入�?个Intent对象，然后�?�过调用 sendOrderBroadcast()或sendStickyBroadcast()方法，把 Intent对象以广播方式发送出去�??

当Intent发�?�以后，�?有已经注册的BroadcastReceiver会检查注册时的IntentFilter是否与发送的Intent相匹配，若匹配则就会调用BroadcastReceiver的onReceive()方法。所以当我们定义�?个BroadcastReceiver的时候，都需要实现onReceive()方法�?

# 注册
注册BroadcastReceiver有两种方�?:

## 静�?�注�?

在AndroidManifest.xml中用标签生命注册，并在标签内用标签设置过滤器�?

`````
<receiver android:name="myRecevice">    //继承BroadcastReceiver，重写onReceiver方法
�?�?<intent-filter>    
�?�?�?�?<action android:name="com.dragon.net"></action> //使用过滤器，接收指定action广播
�?�?</intent-filter>
</receiver>
`````

优点：不受其他组件生命周期影响，即使应用程序被关闭，也能接收广播�?

缺点：�?�电，占内存�?

适用场景：需要时刻监听的广播�?

事实上在，google因为电池优化的原因从7.0�?始已经对静�?�注册做出了�?些限制（权限），并在8.0使大部分静�?�注册失效了，极少数例如ACTION_BOOT_COMPLETED, ACTION_HEADSET_PLUG, ACTION_CONNECTION_STATE_CHANGED 等广播依旧有效�?�可以说，未来使用动态注册已经是�?种趋势，不过在兼容低版本时，静�?�注册依旧好用�??

**Android 8.0静�?�广播失效解决办�?**
[Android 8.0行为变更文档地址](https://developer.android.google.cn/about/versions/oreo/android-8.0-changes)


## 动�?�注�?
`````
IntentFilter intentFilter = new IntentFilter();
intentFilter.addAction(String);                     //为BroadcastReceiver指定action，使之用于接收同action的广�?
registerReceiver(BroadcastReceiver,intentFilter);
`````

优点：灵活，不�?�电，易控，省内�?

缺点�?<span style="color:red">�?要手动注�?</span>

适用场景：需要特定时候监听的广播

# 发�?�广�?
`````
Intent intent = new Intent(actionString);           //指定广播目标Action
intent.putExtra("msg", "hi,我�?�过广播发�?�消息了");     //并且可�?�过Intent携带消息
context.sendBroadcast(intent);                      //发�?�广播消�?
`````

# 广播类型

## 有序广播
有序广播 是一种分先后广播接收器的广播，广播接收�?�的优先级越高，越先接收广播。优先级高的广播先收到广播，收到广播后可以修改广播的内容，也可以拦截广播不让广播向下传�?��??

## 无序广播
无序广播 指所有与之匹配的广播接收者都能收到广播，没有先后顺序，直到没有广播接收�?�接收广播为止才会停止广播的传�?��??

## sticky广播
已经被弃�?
 

# 参�??

[Android中BroadCastReceiver使用（整理）](https://www.cnblogs.com/jico/articles/1838293.html)

[BroadcastReceiver](https://www.jianshu.com/p/7b44d983c01a)

[BroadcastReceiver详解以及应用](https://blog.csdn.net/huiblog/article/details/53234544)
<h1 style="font-size: 2.5em;"> Android LocalBroadcast</h1>
 


# 基本概念
由于之前的广播都是全局的，所有应用程序都可以接收到，这样就很容易会引起安全性的问题，比如说我们发送一些携带关键性数据的广播有可能被其他的应用程序截获，或者其他的程序不停地向我们的广播接收器里发送各种垃圾广播。

为了能够简单地解决广播的安全性问题，Android引入了一套本地广播机制，使用这个机制发出的广播只能够在应用程序的内部进行传递，并且广播接收器也只能接收来自应用程序发出的广播，这样所有的安全性问题就都不存在了。

# 简单使用

## 发送

`````
LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
Intent intent = new Intent("com.example.broadcast");
localBroadcastManager.sendBroadcast(intent);
`````

## 接收

`````
private LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
private LocalReceiver localReceiver;

protected void onCreate(Bundle savedInstanceState) {
    //注册本地接收器
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("com.example.broadcast");
    localReceiver = new LocalReceiver();
    localBroadcastManager.registerReceiver(localReceiver,intentFilter);
}

protected void onDestroy() {
    //取消注册本地接收器
    localBroadcastManager.unregisterReceiver(localReceiver);
}

private class LocalReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"收到本地广播",Toast.LENGTH_SHORT).show();
    }
}
`````

# 注意事项

1. 本地广播无法通过静态注册来接收，相比起系统全局广播更加高效
1. 在广播中启动activity的话，需要为intent加入FLAG_ACTIVITY_NEW_TASK的标记，不然会报错，因为需要一个栈来存放新打开的activity。
1. 广播中弹出AlertDialog的话，需要设置对话框的类型为:TYPE_SYSTEM_ALERT不然是无法弹出的。

# 参考

[Android学习总结（七）———— 本地广播](https://www.cnblogs.com/nylcy/p/6510819.html)
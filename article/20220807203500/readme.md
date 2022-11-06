<h1 style="font-size: 2.5em;"> Android ����㲥</h1>
 

# 指定某一个应用允许接�?
intent 指定包名Intent.setPackage设置广播仅对相同包名的有�?

`````
Intent intent = new Intent();             
intent.setPackage("应用包名");
intent.setAction("消息action");            
sendBroadcast(intent);
`````

# 指定某一个receiver允许接收
`````
Intent intent = new Intent();
intent. setComponent(newComponentName("包名", "Receiver类名"));
intent.setAction("消息action");
sendBroadcast(intent);
`````

# 指定多个应用有权接收(指定权限)

## sender app

`````
//AndroidManifest.xml
<permission android:description="@string/XXX" 
	android:label="XXXX" 
	android:name="com.test.permission" 
	android:protectionLevel="signature" />//如果发�?�和接收都在同一应用内，�?要去掉�??
`````

`````
sendBroadcast("com.android.XXX_ACTION","com.test.permission");
`````

## receiver app

`````
//AndroidManifest.xml
<uses-permission android:name="com.test.permission" />
`````
静�?�注�?
`````
<receiver android:name=".XXXReceiver" android:permission="com.test.permission">
    <intent-filter>
        <action android:name="com.android.XXX_ACTION" />
    </intent-filter>
</receiver>
`````
动�?�注�?
`````
registerReceiver(receiver, "com.test.permission", null);
`````

如果在AndroidManifest中声明了权限，在使用registerReceiver方法时，可以不传入权限名称�??

# ssp匹配
接收或发送定向广播需要用到android:ssp属�?�用于匹配URI，ssp代码“scheme-specific part”，意�?�是代表的东西都在scheme之后出现，如URI是�?�http://example.com.a”，可以分成scheme部分"http"和ssp部分"//example.com.a"。这里需要特别注意例子中的ssp部分是包�?"//"的�?? 

1. android:ssp全匹�?
1. android:sspPrefix前缀匹配
1. android:sspPattern模式匹配

## sender app
``````
Intent intent = new Intent();
intent.setAction(RECEIVE_MSG_ACTION);
Uri data = Uri.parse("msg://com.example.ssp1");
intent.setData(data);
sendBroadcast(intent);
``````
`````
Intent intent = new Intent(RECEIVE_MSG_ACTION, Uri.parse("msg:com.example.ssp2"));
sendBroadcast(intent);
`````

## receiver app

`````
<receiver android:name="com.example.sspreceiver.PackageAddReceiver">
    <intent-filter >
        <action android:name="android.intent.action.PACKAGE_ADDED"/>
        <data android:scheme="package"
            android:ssp="com.tencent.mm"/>
    </intent-filter>
</receiver>
`````

`````
IntentFilter filter = new IntentFilter();
filter.addAction(Intent.ACTION_PACKAGE_ADDED);
filter.addDataScheme("package");
filter.addDataSchemeSpecificPart("com.tencent.mm", PatternMatcher.PATTERN_LITERAL);
`````


# 参�??

[Android之定向广播](https://blog.csdn.net/zhangyongfeiyong/article/details/51853521)

[定向广播的几种方式](https://blog.csdn.net/qiebobobo/article/details/46915177)

[android广播指定权限](https://blog.csdn.net/weixin_34122604/article/details/92349568)

[解决安卓Android 8.0以上的静态广播无法注册](https://blog.csdn.net/qq_39326574/article/details/96310112)

[Background execution not allowed 广播无法接收问题的解决方法](https://blog.csdn.net/wangwei890702/article/details/99644607)

[Calling a method in the system process without a qualified user: android解决方案](https://blog.csdn.net/love_xsq/article/details/50392093)

[Android无法收到指定广播的问题�?�结](https://www.cnblogs.com/scarecrow-blog/p/6080884.html)

[Android 8.0的平台上，应用不能对大部分的广播进行静�?�注册](https://www.cnblogs.com/endv/p/11695262.html)

[Android 9.0静�?�广播收不到](https://blog.csdn.net/wkathy/article/details/103380081)

[Android 8.0以上无法收到静�?�广播消息](https://www.jianshu.com/p/8dda5df736c7)

[在Android8.0上突破隐式广播的限制](https://www.jianshu.com/p/5283ebc225d5?utm_source=oschina-app)




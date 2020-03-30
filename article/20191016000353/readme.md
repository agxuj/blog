# Android 网络调试 OkHttp+Stetho+Chrome

<script src="../js/index.js"></script>
<div id="content"></div>





## Gradle 引入依赖
`````
implementation 'com.squareup.okhttp3:okhttp:3.10.0'
implementation 'com.facebook.stetho:stetho:1.5.1'
implementation 'com.facebook.stetho:stetho-okhttp3:1.5.1'
`````

## Application 中初始化
`````
@Override
public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
}
`````

## OkHttp 中添加拦截器

`````
static {
    OkHttpClient.Builder builder = new OkHttpClient
            .Builder()
            .addNetworkInterceptor(new StethoInterceptor());
    okHttpClient = builder.build();
}
`````

## Chrome 中打开调试

    在chrome中的网址栏输入：chrome://inspect/

    点击inspect
    
    
# 参考

[OkHttp+Stetho+Chrome调试android网络部分（原创）](http://www.itboth.com/d/63If6n/android)
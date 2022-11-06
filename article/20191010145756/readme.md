<h1 style="font-size: 2.5em;"> Android ����ʽ������</h1>
 



## �?4.4�?19）以�?

`````
<style name="Base.Theme.AppCompat.Light">
    <item name="android:windowTranslucentNavigation">true</item>
    <item name="android:windowTranslucentStatus">true</item>
</style>
`````

## �?5.0�?21）以�?
`````
<style name="Base.Theme.AppCompat.Light">
    <!-- true: status栏会有一层阴影；false: status栏没有阴�?-->
    <item name="android:windowTranslucentStatus">false</item>
    <!-- 使底部导航栏透明 -->
    <item name="android:windowTranslucentNavigation">true</item>
    <!-- 给status栏设置�?�明颜色 -->
    <item name="android:statusBarColor">@android:color/transparent</item>
</style>
`````

## �?6.0�?23）以上：
`````
<style name="Base.Theme.AppCompat.Light">
    <!-- true: status栏会有一层阴影；false: status栏没有阴�?;当该项为true时，则name="android:windowLightStatusBar"设置无效，永远为白色 -->
    <item name="android:windowTranslucentStatus">false</item>
    <!-- 使底部导航栏透明 -->
    <item name="android:windowTranslucentNavigation">true</item>
    <!-- 给status栏设置�?�明颜色 -->
    <item name="android:statusBarColor">@android:color/transparent</item>
    <!-- true: status栏的图标和文字为黑色；false: sstatus栏的图标和文字为白色 -->
    <item name="android:windowLightStatusBar">true</item>
</style>
`````

# Reference 
[安卓实现沉浸式状态栏（�?�过theme主题进行设置）](https://blog.csdn.net/sunshine199221/article/details/61919890)
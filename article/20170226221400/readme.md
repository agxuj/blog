<h1 style="font-size: 2.5em;"> Android ����������</h1>
 



Reference:[关于onConfigurationChanged以及onConfigurationChanged 未被调用的问题](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2012/1106/516.html)

�? 改变屏幕方向、弹出软件盘、隐藏软键盘 时，
如果设置以下属�?�，会直接调用onCreate方法中的onConfigurationChanged方法，�?�不是重新执行onCreate方法�?
如果没有设置以下属�?�，会重新调用onCreate方法了�??

`````
<activity
�?�?android:name=".MainActivity"
�?�?android:configChanges="orientation|keyboardHidden" />
 
//当sdk大于13，则以下代码才有效，增加了screenSize
 
<activity
�?�?android:name=".MainActivity"
�?�?android:configChanges="orientation|keyboardHidden|screenSize" />
`````

**getActivity().setRequestedOrientation()用来设置是否启用横竖屏传感器**

有情景如下：

单Activity＋多Fragment架构中，只容许某个Fragment可做旋转，其余不做旋转，则可用以下代码处理�??

以下代码逻辑写在Fragment中�??

`````

@Override
public void onConfigurationChanged(Configuration newConfig) {
�?�?super.onConfigurationChanged(newConfig);
�?�?if (newConfig == null) return;
 
�?�?if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
�?�?�?�?screenLandscape();
�?�?} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
�?�?�?�?screenLortrait();
�?�?}
}

@Override
public void onResume() {
�?�?super.onResume();
 
�?�?//启用横竖屏传感器,自动切换横竖屏状�?
�?�?getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
}

@Override
public void onPause() {
    super.onPause();
 
    //禁用横竖屏传感器,使应用处于竖屏状�?
    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
}

@Override
public void onHiddenChanged(boolean hidden) {
�?�?//根据fragment是否隐藏,设定是否启用横竖屏传感器
�?�?//如果fragment显示,则启用横竖屏传感�?
�?�?//如果fragment隐藏,则关闭横竖屏传感�?
�?�?getActivity().setRequestedOrientation(//通过程序改变屏幕显示的方�?
�?�?hidden ? ActivityInfo.SCREEN_ORIENTATION_NOSENSOR : ActivityInfo.SCREEN_ORIENTATION_SENSOR);
}

`````

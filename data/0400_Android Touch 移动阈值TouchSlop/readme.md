# 简介
TouchSlop是一个系统常量，用于表示系统能够识别的被认为是滑动的最小距离，也就是说当在屏幕上滑动的距离小于这个值时，系统不认为这是滑动操作。这个值和设备有关，手机生产商可以自行设置该值。通过该值，可以过滤掉一些滑动距离太小的操作等，从而提高用户体验。该值保存在文件frameworks/base/core/res/res/values/config.xml中，如下所示：

`````
<!-- 
Base "touch slop" value used by ViewConfiguration as a
movement threshold where scrolling should begin. 
默认情况下，该值一般都是8dp。 
-->
<dimen name="config_viewConfigurationTouchSlop">8dp</dimen>
`````


# 获取
`````
mTouchSlop = res.getDimensionPixelSize(com.android.internal.R.dimen.config_viewConfigurationTouchSlop);
`````

# 参考
[【朝花夕拾】Android自定义View篇之（十）移动阈值TouchSlop及滑动追踪VelocityTracker](https://www.cnblogs.com/andy-songwei/p/11193433.html)
<h1 style="font-size: 2.5em;"> Android drawable ��ǩ</h1>
 


# selector            
    设置不同状�?�下不同的drawable
# [layer-list](https://www.jianshu.com/p/9e6d03ab7ac9)          
    android中的layer-list就是用来多个图层堆叠显示的�??
# level-list          
    Android 布局中imageview的src不仅可以设置�?张图片或者一个颜色，还可以设置一个level-list（和前面文章说的那个selector有某种程度上很相似），直接上�?个代码把�?

`````
<?xml version="1.0" encoding="utf-8"?>
<level-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@mipmap/a" android:maxLevel="1" android:minLevel="1" />
    <item android:drawable="@mipmap/b" android:maxLevel="2" android:minLevel="2" />
    <item android:drawable="@mipmap/c" android:maxLevel="3" android:minLevel="3" />
</level-list>

`````
# item
    layer-list, level-list的子标签
# color
`````
<color android:color="#ff00ff00"/>
`````
# [shape](https://www.cnblogs.com/MianActivity/p/5867776.html)
    用于定义�?些形状的样式，经常用于在Android�?发中控制控件的背�?
# [rotate](https://blog.csdn.net/wenwen091100304/article/details/53428249), [scale](https://blog.csdn.net/wenwen091100304/article/details/53428249), [Aplha](https://blog.csdn.net/classmate_l/article/details/83541631), transition
    动画标签
# [adaptive-icon, maskable-icon](https://www.jianshu.com/p/c7af54a361a2)    
    自�?�应图标主要用于在Launcher上可以根据不同的配置显示不同形状的图标，可以显示圆形方形等形状�?�（Android O�?
# animated-rotate     
    �?单的图片旋转动画
# [animated-selector](https://blog.csdn.net/wangsen927/article/details/94781945)
    animated-selector状�?�改变的动画效果
# [animated-vector](https://blog.csdn.net/u011106915/article/details/77502856)     
    Android5.0版本增加的SVG动画animated-vector,使用时sdk版本大于等于21 
# animation-list      
    逐帧动画
# bitmap              
    XML Bitmap是定义在XML文件当中，指向一个位图文件的资源。这样就为原生的位图文件起了�?个别名�??
# clip                
    获取canvas对象，可以在View的onDraw方法中获取或用SurfaceView用getHolder.lockCanvas()获取
# [inset](https://blog.csdn.net/qq_40963076/article/details/99698178)               
    这个标签的作用主要就是为了增加图片边�?
# nine-patch
    android:src 必填项，必须指定点九类型的图�? xxx.9.png
    其他属�?�与bitmap完全�?样，不复述了�?
# ripple              
    当它被设置为�?个控件的background属�?�时，控件在按下时，即会显示水波效果
# vector              
    SVG是一种矢量图格式，是Scalable Vector Graphics三个单词的首字母缩写。在xml文件中的标签�?<vector>，画出的图形可以像一般的图片资源使用

# 参�??

[你都知道么？Android�?21种drawable标签大全](https://cloud.tencent.com/developer/article/1924262)




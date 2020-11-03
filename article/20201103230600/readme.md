<h1 style="font-size: 2.5em;"> Android Touch 判断横竖向滑动</h1>
 

# 简介
`````
xDiff * 0.5f > yDiff
//表明是横向滑动
`````

斜率小于0.5时，则是横（竖）向移动，斜率是什么呢？高中数学可知，在第一象限中，越靠近y轴的直线，斜率越大，越靠近x轴直线斜率越小，先看简单图示：

<img src="image/1.webp"/>
 

# 参考
[ViewPager源码分析（2）：滑动及冲突处理](https://www.jianshu.com/p/ea5de4925b36)
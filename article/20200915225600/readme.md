<h1 style="font-size: 2.5em;"> Android 补间动画</h1>
 

# 公共属性

//动画持续时间
scaleAnimation.setDuration(2000);
//如果设置为true，控件动画结束时，将保持动画最后时的状态
scaleAnimation.setFillAfter(true);
//如果设置为true,控件动画结束时，还原到开始动画前的状态
scaleAnimation.setFillBefore(false);
//重复次数
scaleAnimation.setRepeatCount(2);
//重复类型，有reverse和restart两个值，reverse表示倒序回放，restart表示重新放一遍，必须与repeatCount一起使用
scaleAnimation.setRepeatMode(Animation.RESTART);

# 缩放动画（ScaleAnimation）
```````
//fromXScale    起始的X方向上相对自身的缩放比例，浮点值，比如1.0代表自身无变化，0.5代表起始时缩小一倍，2.0代表放大一倍；
//toXScale      结尾的X方向上相对自身的缩放比例，浮点值；
//fromYScale    起始的Y方向上相对自身的缩放比例，浮点值
//toYScal       结尾的Y方向上相对自身的缩放比例，浮点值
scaleAnimation = new ScaleAnimation(0, 1.4f, 0, 1.4f);
//动画持续时间
scaleAnimation.setDuration(2000);
tvDemo.startAnimation(scaleAnimation);
```````

# 位移动画（TranslateAnimation）
```````
//fromXDelta    起始点X轴坐标，可以是数值、百分数、百分数p 三种样式，同scale
//fromYDelta    起始点Y轴从标，可以是数值、百分数、百分数p 三种样式
//toXDelta      结束点X轴坐标
//toYDelta      结束点Y轴坐标   
translateAnimation = new TranslateAnimation(0, 100, 0, 100);
translateAnimation.setDuration(2000);
tvDemo.startAnimation(translateAnimation);
```````

# 旋转动画（RotateAnimation）
```````
rotateAnimation = new RotateAnimation(0, 720, 0, 0);
rotateAnimation.setDuration(2000);
tvDemo.startAnimation(rotateAnimation);
```````

# 透明度动画（AlphaAnimation）
```````
//fromAlpha         动画开始的透明度，从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
//toAlpha           动画结束时的透明度，也是从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
alphaAnimation = new AlphaAnimation(0, 1);
alphaAnimation.setDuration(2000);
tvDemo.startAnimation(alphaAnimation);
```````

# 动画集合（AnimationSet）
```````
rotateAnimation = new RotateAnimation(0, -720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
rotateAnimation.setDuration(2000);

translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0, TranslateAnimation.RELATIVE_TO_PARENT, 0.5f, TranslateAnimation.RELATIVE_TO_PARENT, 0, TranslateAnimation.RELATIVE_TO_PARENT, 0.5f);
translateAnimation.setDuration(2000);

scaleAnimation = new ScaleAnimation(0, 1.4f, 0, 1.4f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
scaleAnimation.setDuration(2000);

alphaAnimation = new AlphaAnimation(0, 1);
alphaAnimation.setDuration(2000);

animationSet = new AnimationSet(true);
animationSet.addAnimation(rotateAnimation);
animationSet.addAnimation(translateAnimation);
animationSet.addAnimation(scaleAnimation);
animationSet.addAnimation(alphaAnimation);
animationSet.setDuration(4000);
animationSet.setFillAfter(true);

tvDemo.startAnimation(animationSet);
```````

# 参考
[【Android 动画】动画详解之补间动画（一）](https://www.jianshu.com/p/abeca56da5e4)
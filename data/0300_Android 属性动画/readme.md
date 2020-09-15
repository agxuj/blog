# ValueAnimator

## ofInt
`````
//设置动画 始 & 末值
//如下则0平滑过渡到3
ValueAnimator animator = ValueAnimator.ofInt(0,3);
//如下传入多个参数，效果则为0->5,5->3,3->10
//ValueAnimator animator = ValueAnimator.ofInt(0,5,3,10);

//设置动画的基础属性
animator.setDuration(5000);//播放时长
animator.setStartDelay(300);//延迟播放
animator.setRepeatCount(0);//重放次数
animator.setRepeatMode(ValueAnimator.RESTART);
//重放模式
//ValueAnimator.START：正序
//ValueAnimator.REVERSE：倒序

//设置更新监听
//值 改变一次，该方法就执行一次
animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        //获取改变后的值
        int currentValue = (int) animation.getAnimatedValue();
        //输出改变后的值
        Log.d("1111", "onAnimationUpdate: " + currentValue);
                        
        //改变后的值发赋值给对象的属性值
        view.setproperty(currentValue);
                        
        //刷新视图
        view.requestLayout();
    }
});
//启动动画
animator.start();
`````

## ofFloat
作用：将初始值 以浮点型数值的形式 过渡到结束值
估值器：内置FloatEvaluator估值器
具体使用：和ValueAnimator.ofInt()及其类似。
 
## ofObject
作用：将初始值 以对象的形式 过渡到结束值
估值器：Android 不提供，需要自定义**估值器**

### TypeEvaluator 估值器实现示例
`````
public class IntEvaluator implements TypeEvaluator<Integer> {

    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int)(startInt + fraction * (endValue - startInt));
    }
}
`````

# ObjectAnimator

* 属性动画重要的类
* 原理：控制 值 的变化，之后 自动 赋给对象的属性，从而实现动画
* 与ValueAnimator对比
    * ValueAnimator的子类
    * ValueAnimator只是对 值 进行平滑的动画过渡；ObjectAnimator直接对 任意对象的任意属性 进行动画操作，如View的alpha属性
    * ValueAnimator需要我们为对象属性手动赋值；ObjectAnimator会为对象属性自动赋值

`````
ObjectAnimator animator = ObjectAnimator.ofFloat(Object object, String property, float ....values);  
//ObjectAnimator animator = ObjectAnimator.ofInt(Object object, String property, int ....values);  
//ObjectAnimator animator = ObjectAnimator.ofObject(Object object, String property, TypeEvaluator evaluator,Object....values);  

// 以ofFloat为例 参数说明：
// Object object：需要操作的对象
// String property：需要操作的对象的属性
// float ....values：动画初始值 & 结束值（不固定长度）
// 若是两个参数a,b，则动画效果则是从属性的a值到b值
// 若是三个参数a,b,c，则则动画效果则是从属性的a值到b值再到c值
// 以此类推
// 至于如何从初始值 过渡到 结束值，同样是由估值器决定，此处ObjectAnimator.ofFloat（）是有系统内置的浮点型估值器FloatEvaluator，同ValueAnimator讲解

//动画基本属性
anim.setDuration(500); 
anim.setStartDelay(500);
anim.setRepeatCount(0);
anim.setRepeatMode(ValueAnimator.RESTART);

animator.start();  
// 启动动画
`````
propert可以设置常见的透明度、平移、缩放、旋转外，还可以设置**任意属性值**，只要属性设置了set() & get() 方法


# AnimatorSet 组合动画


设置基础属性
`````
AnimatorSet.play(Animator anim) //播放当前动画
AnimatorSet.after(long delay) //将现有动画延迟x毫秒后执行
AnimatorSet.with(Animator anim) //将现有动画和传入的动画同时执行
AnimatorSet.after(Animator anim) //将现有动画插入到传入的动画之后执行
AnimatorSet.before(Animator anim) //将现有动画插入到传入的动画之前执行
`````

简单实用
`````
ObjectAnimator a1 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f);  
ObjectAnimator a2 = ObjectAnimator.ofFloat(view, "translationY", 0f, viewWidth);  
......
AnimatorSet animSet = new AnimatorSet();  
animSet.setDuration(5000);  
animSet.setInterpolator(new LinearInterpolator());   
//animSet.playTogether(a1, a2, ...); //两个动画同时执行  
animSet.play(a1).after(a2); //先后执行
......//其他组合方式
animSet.start();  
`````

# 监听动画
Animator类提供addListener()方法，说明其子类都可以使用该方法(关于继承关系，前面我们提到过了) 使用方法:
`````
anim.addListener(new AnimatorListener() {
    @Override
    public void onAnimationStart(Animation animation) {
        //动画开始时执行
    }
      
    @Override
    public void onAnimationRepeat(Animation animation) {
        //动画重复时执行
    }

    @Override
    public void onAnimationCancel()(Animation animation) {
        //动画取消时执行
    }
    
    @Override
    public void onAnimationEnd(Animation animation) {
        //动画结束时执行
    }
});
`````

# ViewPropertyAnimator

示例：
`````
// 使用解析
View.animate().xxx().xxx();
// ViewPropertyAnimator的功能建立在animate()上
// 调用animate()方法返回值是一个ViewPropertyAnimator对象,之后的调用的所有方法都是通过该实例完成
// 调用该实例的各种方法来实现动画效果
// ViewPropertyAnimator所有接口方法都使用连缀语法来设计，每个方法的返回值都是它自身的实例
// 因此调用完一个方法后可直接连缀调用另一方法,即可通过一行代码就完成所有动画效果
        
// 以下是例子
mButton = (Button) findViewById(R.id.Button);
// 创建动画作用对象：此处以Button为例

mButton.animate().alpha(0f);
// 单个动画设置:将按钮变成透明状态 
mButton.animate().alpha(0f).setDuration(5000).setInterpolator(new BounceInterpolator());
// 单个动画效果设置 & 参数设置 
mButton.animate().alpha(0f).x(500).y(500);
// 组合动画:将按钮变成透明状态再移动到(500,500)处

`````

# TimeAnimator

继承自 ValueAnimator

TimeAnimator:提供了一个简单的回调机制，通过 TimeAnimator.TimeListener，在动画的每一帧处通知你。这个动画器没有时间，插值或是对象值设定。回调监听器为每一帧动画接受信息，包括总运行时间和从前一帧到现在的运行时间.
`````
TimeAnimator animator = new TimeAnimator();
animator.setTimeListener(new TimeAnimator.TimeListener() {
    @Override
    public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {

    }
});
`````


# 参考
[Android TimeAnimator && TimeListener翻译](https://www.cnblogs.com/tony-yang-flutter/p/3581364.html)

[Android 属性动画(Property Animation) 使用详解](https://www.jianshu.com/p/a480ca619dd9)
# 简介
android系统中的每个View的子类都具有下面三个和TouchEvent处理密切相关的方法：

`````
//用来分发TouchEvent
public boolean dispatchTouchEvent(MotionEvent ev)  

//用来拦截TouchEvent
public boolean onInterceptTouchEvent(MotionEvent ev) 

//用来处理TouchEvent
public boolean onTouchEvent(MotionEvent ev) 
`````

当TouchEvent发生时，首先Activity将TouchEvent传递给最顶层的View，TouchEvent到达最顶层 view 的 dispatchTouchEvent.

然后由 dispatchTouchEvent 方法进行分发，如果dispatchTouchEvent返回true ，则交给这个view的onTouchEvent处理，如果dispatchTouchEvent返回 false ，则交给这个 view 的 interceptTouchEvent 方法来决定是否要拦截这个事件.

如果 interceptTouchEvent 返回 true，也就是拦截掉了，则交给它的 onTouchEvent 来处理，如果 interceptTouchEvent 返回 false ，那么就传递给子 view ，由子 view 的 dispatchTouchEvent 再来开始这个事件的分发。

如果事件传递到某一层的子 view 的 onTouchEvent 上了，这个方法返回了 false ，那么这个事件会从这个 view 往上传递，都是 onTouchEvent 来接收。

而如果传递到最上面的 onTouchEvent 也返回 false 的话，这个事件就会“消失”，而且接收不到下一次事件。

# dispatchTouchEvent() 源码分析

## Activity
Activty中的源码如下
`````
public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            onUserInteraction(); // 空方法
    }
    if (getWindow().superDispatchTouchEvent(ev)) {
            /*
            PhoneWindow类中实现：
            @Override
            public boolean superDispatchTouchEvent(MotionEvent event) {
                return mDecor.superDispatchTouchEvent(event);
            }
            DecorView里的实现：
            public boolean superDispatchTouchEvent(MotionEvent event) {
                return super.dispatchTouchEvent(event);
            }
            调用的是父类FrameLayout的方法
             */
            return true;
    }
    return onTouchEvent(ev);
}
`````

上来是调用了Window类的superDispatchTouchEvent()，然后是走到了DecorView的superDispatchTouchEvent()，DecorView则调用了父类FrameLayout的dispatchTouchEvent()方法，而FrameLayout没有覆写之，所以走的是ViewGroup的dispatchTouchEvent()。

而且ViewGroup的dispatchTouchEvent()返回false，才会调用Activity自己的onTouchEvent()

## ViewGroup
源码如下，比较长，做了精简
`````
@Override
public boolean dispatchTouchEvent(MotionEvent ev) {
    //省略...
}
`````

代码不短，只看跟触摸事件有关而且常见的。

首先如果是down事件，就清空touchTarget链表，因为down事件是触摸事件流的起点，在resetTouchState()方法里面，也将mFirstTouchTarget置为null

mFirstTouchTarget表示处理down事件的子view的target，不为null，则表示有子view处理这个事件流，为null，就交给ViewGroup自己处理

说明一下，处理down事件也就是处理这个事件流，不处理down事件就被认为不处理这个事件流，不过即便处理down事件，子view也不见得能接收所有事件，因为ViewGroup可以拦截后续事件

而后进行判断，如果是down事件或者有子view处理这个事件流，就调用ViewGroup.onInterceptTouchEvent()方法判断ViewGroup是否拦截事件

不拦截还则罢了，拦截的话，如果拦截的是down事件，mFirstTouchTarget为null，整个事件流归ViewGroup处理；拦截的不是down事件，比如move事件，此时mFirstTouchTarget不是null，当前事件归子view管，但子view接收到的却变成了cancel事件，这次事件流之后的事件全归了ViewGroup

然后在不是cancel事件也没有拦截的情况下，只处理down事件，根据坐标找到被点击的子view后，先尝试找到之前确定过的，处理这次事件流的子view的target(但在down事件的情况下，这里似乎一般都是null)，然后调用子view的dispatchTouchEvent()方法确定子view是不是要处理down，是的话，找到目标，调用addTouchTarget()方法给mFirstTouchTarget赋值，并把返回值赋给newTouchTarget，此刻，两者才不是null

所以在down的时候，newTouchTarget和mFirstTouchTarget要空都空，要不空都不是空

找到目标后，进行分发。

这里分发的就不一定是down事件了，如果mFirstTouchTarget是null，就只能分发给ViewGroup自己；如果不是null，就分发给mFirstTouchTarget对应的子view

如果ViewGroup拦截了down之后的事件，会导致cancelChild为true，但处理当前事件时，mFirstTouchTarget不是null，所以还是会分发给target.child，只不过这次分发的成了cancel事件。然后由于predecessor是null，导致mFirstTouchTarget被赋值成了后继(null)，所以待到下一个事件来的时候，直接走了上一段说的判断分支，也就是mFirstTouchTarget是null的情况，不会调用自己的onInterceptTouchEvent()(两个条件down事件和mFirstTouchTarget为null都不成立)，然后分发给自己，而不是子view

## View

public boolean dispatchTouchEvent(MotionEvent event) {
    //省略...
}

结论onTouchListener的onTouch()比onTouchEvent()优先级要高


# onInterceptTouchEvent() 源码分析

这个方法只在ViewGroup中有。

`````
public boolean onInterceptTouchEvent(MotionEvent ev) {
    if (ev.isFromSource(InputDevice.SOURCE_MOUSE) // 一般都不是从鼠标过来的，所以默认这个判断不成立，返回false
            && ev.getAction() == MotionEvent.ACTION_DOWN
            && ev.isButtonPressed(MotionEvent.BUTTON_PRIMARY)
            && isOnScrollbarThumb(ev.getX(), ev.getY())) {
        return true;
    }
    return false;
}
`````

看来默认返回false，因为一般都不是鼠标过来的事件，返回true的if判断直接不成立，事件分发的最后一个方法，参见文章安卓事件分发学习之onTouchEvent方法


# onTouchEvent() 源码分析

## View
`````
public boolean onTouchEvent(MotionEvent event) {
    return false;
}
`````

代码虽长，逻辑却算不得复杂,得出来的最重要的几个结论就是：

1. onClick、onLongClick优先级是小于onTouchEvent()的，同时结合前一篇文章，可以得到优先级如下：onTouch()、onTouchEvent()、onLongClick()、onClick()

1. onClick是在action_up里执行的，onLongClick是在action_down里执行的。而且如果onLongClick返回true，onClick不会执行

1. 有焦点的时候是不会执行onClick的

## Activity
````` 
public boolean onTouchEvent(MotionEvent event) {
    if (mWindow.shouldCloseOnTouch(this, event)) {
        finish();
        return true;
    }
    return false;
}
`````
这里返回true或false已经无所谓了，反正事件流也到头了。


# 参考
[Android dispatchTouchEvent介绍](https://mobile.51cto.com/abased-374715.htm)

[安卓事件分发学习之dispatchTouchEvent方法](https://blog.csdn.net/qq_37475168/article/details/80520372)
[安卓事件分发学习之onInterceptTouchEvent方法](https://blog.csdn.net/qq_37475168/article/details/80556748)
[安卓事件分发学习之onTouchEvent方法](https://blog.csdn.net/qq_37475168/article/details/80556832)
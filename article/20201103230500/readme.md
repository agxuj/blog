<h1 style="font-size: 2.5em;"> Android Touch requestDisallowInterceptTouchEvent()</h1>
 

# 简介
requestDisallowInterceptTouchEvent 是 ViewGroup 类中的一个公用方法，参数是一个 boolean 值，官方介绍如下:

Called when a child does not want this parent and its ancestors to intercept touch events with ViewGroup.onInterceptTouchEvent(MotionEvent).

This parent should pass this call onto its parents. This parent must obey this request for the duration of the touch (that is, only clear the flag after this parent has received an up or a cancel.

android系统中，一次点击事件是从父view传递到子view中，每一层的view可以决定是否拦截并处理点击事件或者传递到下一层，如果子view不处理点击事件，则该事件会传递会父view，由父view去决定是否处理该点击事件。在子view可以通过设置此方法去告诉父view不要拦截并处理点击事件，父view应该接受这个请求直到此次点击事件结束。

# 使用

实际的应用中，可以在子view的ontouch事件中注入父ViewGroup的实例，并调用requestDisallowInterceptTouchEvent去阻止父view拦截点击事件

`````
public boolean onTouch(View v, MotionEvent event) {
    ViewGroup viewGroup = (ViewGroup) v.getParent();
    switch (event.getAction()) {
    case MotionEvent.ACTION_MOVE:
        viewGroup.requestDisallowInterceptTouchEvent(true);
        break;
    case MotionEvent.ACTION_UP:
    case MotionEvent.ACTION_CANCEL:
        viewGroup .requestDisallowInterceptTouchEvent(false);
        break;
    }
}
`````

# 参考
[关于ViewGroup中requestDisallowInterceptTouchEvent的用法](https://www.cnblogs.com/txlbupt/p/4371290.html)
# 介绍

逐帧动画(Frame-by-frame Animations),就是通过一个接一个的加载一系列的Drawble资源来创建动画，实际上就是类似于放电影一样，按顺序播放一系列的图像，以达到动画效果。其中，AnimationDrawable是绘制动画的基础。

# API

## XML相关节点

``````
<animation-list xmlns:android="http://schemas.android.com/apk/res/android" android:oneshot="true">

    <item android:drawable="@drawable/rocket_thrust1" android:duration="200" />

</animation-list>
``````

* animation-list : 根节点，里面有一个或者多个item节点组成
* android:oneshot="true" : 表示是否只播放一次，true表示只会播放一次，false表示一直循环播放
* item : 声明动画中每帧，其排序即为每帧的播放先后顺序
* android:drawable="@drawable/XX" : 动画每帧资源
* android:duration="200" : 每帧持续时间

## AnimationDrawable

* isOneShot() : 是否循环播放
* isRunning() : 动画是否在播放
* addFrame(Drawable frame, int duration) : 添加一帧，并设置该帧显示的持续时间
* inflate(Resources r, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) : 通过xml资源添加动画，并设置相关属性和Stle
* setOneShot(boolean oneShot) : 设置是否循环播放
* setVisible(boolean visible, boolean restart) : 设置是否可见
* start() : 开始动画
* stop() : 暂停动画

# 动画实例
## XML实例
1. 在res/drawable目录下一个文件anim_loading.xml
    ```````
    <animation-list xmlns:android="http://schemas.android.com/apk/res/android" android:oneshot="false">

        <item
            android:drawable="@drawable/ic_1"
            android:duration="200" />

        <item
            android:drawable="@drawable/ic_2"
            android:duration="200" />

        <item
            android:drawable="@drawable/ic_3"
            android:duration="200" />

        <item
            android:drawable="@drawable/ic_4"
            android:duration="200" />

        <item
            android:drawable="@drawable/ic_5"
            android:duration="200" />

        <item
            android:drawable="@drawable/ic_6"
            android:duration="200" />

        <item
            android:drawable="@drawable/ic_7"
            android:duration="200" />

        <item
            android:drawable="@drawable/ic_8"
            android:duration="200" />

        <item
            android:drawable="@drawable/ic_9"
            android:duration="200" />

        <item
            android:drawable="@drawable/ic_10"
            android:duration="200" />

        <item
            android:drawable="@drawable/ic_11"
            android:duration="200" />
    </animation-list>
    ```````
1. 用ImageView控件作为动画载体来显示动画
    ```````
    <ImageView
        android:id="@+id/iv_frame"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:src="@drawable/anim_loading"/>
    ```````

1. 控制动画开始结束
    ```````
    private void doXmlStart() {
        AnimationDrawable animationDrawable = (AnimationDrawable) ivFrame.getDrawable();

        if (animationDrawable != null) {
            animationDrawable.start();
        }
    }

    private void doXmlStop() {
        AnimationDrawable animationDrawable = (AnimationDrawable) ivFrame.getDrawable();
        if (null != animationDrawable && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }
    ```````

## 代码控制

```````
private void doCodeStart() {
    mAnimationDrawable = new AnimationDrawable();

    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_11), 200);
    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_10), 200);
    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_9), 200);
    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_8), 200);
    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_7), 200);
    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_6), 200);
    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_5), 200);
    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_4), 200);
    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_3), 200);
    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_2), 200);
    mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_1), 200);

    mAnimationDrawable.setOneShot(false);

    ivFrame.setImageDrawable(mAnimationDrawable);
    mAnimationDrawable.start();
}

private void doCodeStop() {
    if (null != mAnimationDrawable && mAnimationDrawable.isRunning()) {
        mAnimationDrawable.stop();
    }
}
```````

# 结束语

Frame Animation（逐帧动画）相对来说比较简单，但是在实际开发中使用的频率还是比较高的。实际开发过程中，如果复杂而且帧数比较多的动画不太建议使用逐帧动画，一方面是因为会造成OOM，另一方面会显得很卡。

# 参考
[Android 动画之帧动画](https://blog.csdn.net/IO_Field/article/details/52984329)
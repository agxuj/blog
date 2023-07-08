1. onSaveInstanceState 状态保存
1. onRestoreInstanceState 状态恢复

Activity的销毁一般分为两种情况：

1. 当用户按返回按钮或你的Activity通过调用finish()销毁时，这属于正常销毁，此时是不需要恢复状态的，因为下次回来又是重新创建新的实例。
1. 如果Activity当前被停止或长期未使用，或者前台Activity需要更多资源以致系统必须关闭后台进程恢复内存，系统也可能会销毁Activity，这属于非正常销毁，尽管Activity实例被销毁，但系统会保存其状态，这样，如果用户导航回该Activity，系统会使用保存了该Activity被销毁时的状态数据来创建Activity的新实例。

屏幕旋转、键盘可用性改变、语言改变、模式(如黑/白)切换都可以归结为第二种情况；值得一提的是，如果需要模拟这种情况的Activity销毁，可以打开开发者选项，选择不保留活动（英文为Do not keep activities），即可模拟内存不足时的系统行为。


需要注意的是CheckBox和Switch这类控件在状态恢复时会调用setCheck方法，而setCheck方法会触发onCheckChangedListener.
如果在onCheckChangedListener.onChanged执行了更新数据操作，那时不妥的。

可以通过重写onRestoreInstanceState解决这一问题。

# 参考
[Android的状态保存和恢复](https://www.jianshu.com/p/90cf59f22f40)
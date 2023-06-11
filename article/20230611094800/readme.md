<h1 style="font-size: 2.5em;"> Android SoftInputMode 介绍</h1>
 

该系列主要用于表示softInputMode,我们可以通过WindowManager.LayoutParams的softInputMode变量直接进行设置。
softInputMode只能是一个显示软键盘时的window调整方式bite与一个控制软键盘显示状态的bite的组合。

# 显示软键盘时的window调整方式可以是
1. SOFT_INPUT_ADJUST_NOTHING
    Adjustment option for softInputMode:set to have a window not adjust for a shown input method.
    设置为不针对所显示的输入法调整窗口。
1. SOFT_INPUT_ADJUST_PAN
    Adjustment option for softInputMode: set to have a window pan when an input method is shown, so it doesn’t need to deal with resizing but just panned by the framework to ensure the current input focus is visible.
    设置为在显示输入法时平移窗口，因此不需要调整大小，只需通过框架平移即可确保当前输入焦点可见。
1. SOFT_INPUT_ADJUST_RESIZE
    Adjustment option for softInputMode: set to allow the window to be resized when an input method is shown, so that its contents are not covered by the input method.
    设置为允许在显示输入法时调整窗口大小，使其内容不被输入法覆盖。
1. SOFT_INPUT_ADJUST_UNSPECIFIED
    Adjustment option for softInputMode: nothing specified.
    未指定任何内容。


# 用于描述软键盘显示的规则可以是
1. SOFT_INPUT_STATE_ALWAYS_HIDDEN
    Visibility state for softInputMode:please always hide any soft input area when this window receives focus.
    总是隐藏软键盘。
1. SOFT_INPUT_STATE_ALWAYS_VISIBLE
    Visibility state for softInputMode:please always make the soft input area visible when this window receives input focus.
    总是显示软键盘
1. SOFT_INPUT_STATE_HIDDEN
    Visibility state for softInputMode:please hide any soft input area when normally appropriate (when the user is navigating forward to your window).
    用户导航（navigate）到你的窗口的时候,隐藏软键盘
1. SOFT_INPUT_STATE_VISIBLE
    Visibility state for softInputMode: please show the soft input area when normally appropriate (when the user is navigating forward to your window).
    用户导航（navigate）到你的窗口的时候，显示软键盘
1. SOFT_INPUT_STATE_UNSPECIFIED
    Visibility state for softInputMode: no state has been specified.
    没有软键盘显示的约定规

# 使用说明
``````
WindowManager.LayoutParams params = new WindowManager.LayoutParams();
params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE|WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;
``````
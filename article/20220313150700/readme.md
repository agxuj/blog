<h1 style="font-size: 2.5em;"> Android EditText 使用说明</h1>
 


`````````````

<EditText
    android:id="@+id/edit_name"
    android:layout_width="550dp"
    android:layout_height="wrap_content"
    android:layout_gravity="center_vertical"
    android:background="@null"
    android:textSize="@dimen/text_size_small" 
    android:digits="123"    //指定要支持的字符
    android:includeFontPadding="false"
    android:inputType="textPassword"
    android:imeOptions=""
    android:focusable=""
    android:focusableInTouchMode=""
    android:textSelectHandle="@color/transparent" //垂直指示器资源文件。即出现在光标下方的绿色泪滴，@color/transparent可使其隐藏。
    android:textSelectHandleLeft="@color/transparent" //左边指示器资源文件
    android:textSelectHandleRight="@color/transparent" //右边指示器资源文件
    android:textColorHighlight="" //文本选中高亮颜色
    android:textCursorDrawable="@drawable/edit_cursor" //光标样式

    android:lineSpacingExtra="" //表示额外的行间距数值，单位通常为dp，值可以为负数，小数和0。如果值为正数表示增加行间距；如果值为负数表示减少行间距；如果值为0，则没有 变化。
    android:lineSpacingMultiplier="" //表示行间距的倍数，没有单位，值可以为任意浮点数。如果值大于1.0表示增加行间距，如果值小于1.0表示减少行间距。
    //可以在一起对同一个TextView进行设置，同时使用时会先增加android:lineSpacingMultiplier设置的倍数，再加上android:lineSpacingExtra设置的额外间距

    android:lines="1"
    android:maxLength="25"
    android:maxLines="1"
    android:singleLine="true"
    android:ellipsize="end"
    android:overScrollMode=""
    android:scrollbars="" />

`````````````

# android:focusable 和 android:focusableInTouchMode
如果页面上有EditText控件，开发者又没做其他处理，那么用户打开该页面时往往会自动弹出输入法。这是以为编辑框会默认获得焦点，即默认模拟用户的点击操作，于是输入法的软键盘就弹出来了，想要避免这种情况，就得阻止编辑框默认获得焦点。比较常见的做法是给该页面的根节点设置focusable和focusableInTouchMode属性，通过将这两个属性设置为true可以强制让根节点获得焦点，从而避免输入法自动弹出的尴尬。

# android:imeOptions
软键盘右下键按键。

| 值 | 说明 | 
| - | - | 
| actionUnspecified | 对应常量EditorInfo.IME_ACTION_UNSPECIFIED | 
| actionNone | 对应常量EditorInfo.IME_ACTION_NONE | 
| actionGo | 对应常量EditorInfo.IME_ACTION_GO | 
| actionSearch | 对应常量EditorInfo.IME_ACTION_SEARCH | 
| actionSend | 对应常量EditorInfo.IME_ACTION_SEND | 
| actionNext | 对应常量EditorInfo.IME_ACTION_NEXT | 
| actionDone | 对应常量EditorInfo.IME_ACTION_DONE | 


设置下面两个属性中的一个即可使这个属性生效
* 将singleLine设置为true
* 将inputType设置为text

监听软件盘按钮，返回true来屏蔽系统默认操作。
``````
EditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
            return true;
        }
        return false;
    }
});
``````

# android:numeric

| 值 | 说明 | 
| - | - | 
| integer | 只能输入整数 | 
| decimal | 可以输入小数 | 
| signed | 有符号数字格式 | 

# android:inputType

| 值 | 说明 | 
| - | - | 
| none | 输入普通字符 | 
| text | 输入普通字符 | 
| textCapCharacters | 输入普通字符 | 
| textCapWords | 单词首字母大小 | 
| textCapSentences | 仅第一个字母大小 | 
| textAutoCorrect | 前两个自动完成 | 
| textAutoComplete | 前两个自动完成 | 
| textMultiLine | 多行输入 | 
| textImeMultiLine | 输入法多行（不一定支持） | 
| textNoSuggestions | 不提示 | 
| textUri | URI格式 | 
| textEmailAddress | 电子邮件地址格式 | 
| textEmailSubject | 邮件主题格式 | 
| textShortMessage | 短消息格式 | 
| textLongMessage | 长消息格式 | 
| textPersonName | 人名格式 | 
| textPostalAddress | 邮政格式 | 
| textPassword | 密码格式 | 
| textVisiblePassword | 密码可见格式 | 
| textWebEditText | 作为网页表单的文本格式 | 
| textFilter | 文本筛选格式 | 
| textPhonetic | 拼音输入格式 | 
| number | 数字格式 | 
| numberSigned | 有符号数字格式 | 
| numberDecimal | 可以带小数点的浮点格式 | 
| phone | 拨号键盘 | 
| datetime | 时间日期 | 
| date | 日期键盘 | 
| time | 时间键盘 | 


# setInputType
整数的位定义，定义Editable对象中保存的文本的基本内容类型。<span style="color:red">受支持的<span style="color:yellow">类</span>可以与<span style="color:yellow">变体</span>和<span style="color:yellow">标志</span>结合起来</span>，以指示所需的行为。密码对用户可见的密码字段:inputType = TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD自动大写的多行邮政地址:inputType = TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_POSTAL_ADDRESS | TYPE_TEXT_FLAG_MULTI_LINE时间字段:inputType = TYPE_CLASS_DATETIME | TYPE_DATETIME_VARIATION_TIME

| 值 | 说明 | 
| - | - | 
| InputType.TYPE_NULL | 输入类型为没有指定明确的类型的特殊内容类型 | 
| InputType.TYPE_CLASS_TEXT    |  输入类型为普通文本    | 
| InputType.TYPE_CLASS_NUMBER       |  输入类型为数字文本 | 
| InputType.TYPE_CLASS_PHONE    |  输入类型为电话号码 | 
| InputType.TYPE_CLASS_DATETIME    |  输入类型为日期和时间 | 
| InputType.TYPE_DATETIME_VARIATION_NORMAL    |  输入类型为允许输入日期和时间。 | 
| InputType.TYPE_DATETIME_VARIATION_DATE    |  输入类型为只允许输入一个日期。 | 
| InputType.TYPE_DATETIME_VARIATION_TIME    |  输入类型为只允许输入一个时间。 | 
| InputType.TYPE_MASK_CLASS    |  输入类型为决定所给文本整体类的位掩码 | 
| InputType.TYPE_MASK_FLAGS    |  输入类型为提供附加标志位选项的位掩码 | 
| InputType.TYPE_MASK_VARIATION    |  输入类型为决定基类内容变化的位掩码 | 
| InputType.TYPE_NUMBER_FLAG_DECIMAL    |  输入类型为小数数字，允许十进制小数点提供分数值。 | 
| InputType.TYPE_NUMBER_FLAG_SIGNED    |  输入类型为数字是带符号的，允许在开头带正号或者负号 | 
| InputType.TYPE_NUMBER_VARIATION_NORMAL    |  输入类型为{@link#TYPE_CLASS_NUMBER}的缺省变化值：为纯普通数字文本  | 
| InputType.TYPE_NUMBER_VARIATION_PASSWORD    |  输入类型为{@link#TYPE_CLASS_NUMBER}的缺省变化值：为数字密码  | 
| InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE    |  输入类型为自动完成文本类型 | 
| InputType.TYPE_TEXT_FLAG_AUTO_CORRECT    |  输入类型为自动纠正文本类型 | 
| InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS    |  输入类型为所有字符大写 | 
| InputType.TYPE_TEXT_FLAG_CAP_SENTENCES   |  输入类型为每句的第一个字符大写  | 
| InputType.TYPE_TEXT_FLAG_CAP_WORDS    |  输入类型为每个单词的第一个字母大写 | 
| InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE    |  输入多行文本 | 
| InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS  | 进行输入时，输入法无提示 | 
| InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE    |  输入一个短的，可能是非正式的消息，如即时消息或短信。 | 
| InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE    |  输入长内容，可能是正式的消息内容，比如电子邮件的主体 | 
| InputType.TYPE_TEXT_VARIATION_FILTER    |  输入文本以过滤列表等内容 | 
| InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS   |  输入一个电子邮件地址  | 
| InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT    |  输入电子邮件主题行 | 
| InputType.TYPE_TEXT_VARIATION_PASSWORD    |  输入一个密码 | 
| InputType.TYPE_TEXT_VARIATION_NORMAL    |  输入老式的普通文本 | 
| InputType.TYPE_TEXT_VARIATION_PERSON_NAME    |  输入人名 | 
| InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS    |  输入邮寄地址 | 
| InputType.TYPE_TEXT_VARIATION_PHONETIC    |  输入语音发音输入文本，如联系人拼音名称字段 | 
| InputType.TYPE_TEXT_VARIATION_URI  |  输入URI   | 
| InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD    |  输入对用户可见的密码 | 
| InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT    |  输入网页表单中的文本 | 
| InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS   |  输入网页表单中的邮件地址  | 
| InputType.TYPE_TEXT_VA |  输入网页表单中的密码     | 

# setTransformationMethod

setTransformationMethod是TextView的一个方法，EditText继承于TextView自然可以使用。
这个方法是用来设置其中text的转换显示。
接收的参数是TransformationMethod接口，系统给了我们几个默认实现：

1. HideReturnsTransformationMethod --- 隐藏回车
1. SingleLineTransformationMethod --- 不能用换行回车
1. PasswordTransformationMethod --- 密码类型



ReplacementTransformationMethod 抽象类，前面两个都是继承于这个抽象类，很明显就是替换，我们可以自己去继承这个类实现自己的TransformationMethod

`````````````
/**
 * 小写转化为大写
 */
public class AllCapTransformationMethod extends ReplacementTransformationMethod {  

    @Override  
    protected char[] getOriginal() {  
        char[] aa = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};  
        return aa;  
    }

    @Override  
    protected char[] getReplacement() {  
        char[] cc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};  
        return cc;  
    }
}

`````````````

# setSelection

EditText的setSelection()方法实际上是调用Selection.setSelection()的方法实现的。

* setSelection(int index)将光标移至index处。
* setSelection(int start, int stop) <span style="color:red">选择从start到stop处的文本</span>。

# 注意事项

1. setTranslation 方法将导致键盘弹出时，界面无法上移。

# 参考

[Android EditText详解](https://blog.csdn.net/g984160547/article/details/52100236)

[Android EditText控件](https://blog.csdn.net/chennai1101/article/details/81775870)

[EditText的setTransformationMethod的使用](https://blog.csdn.net/mazhidong/article/details/72651814)

[安卓 android:setInputType()属性全面收藏！](https://blog.csdn.net/l970859633/article/details/102481923)

[关于android：如何为EditText更改textSelectHandle颜色](https://www.codenong.com/41311044/)

[Android Textview和Edittext文本选中样式修改实战](https://blog.csdn.net/Kinsomy/article/details/78653786)

[focusable和focusableInTouchMode属性](https://blog.csdn.net/parade0393/article/details/86158106)

[限制EditText或者TextView显示的字符长度，超出部分用省略号代替](https://www.cnblogs.com/ivan-aldrich/p/3995626.html)

[TextView设置行间距、行高，以及字间距](https://www.niwoxuexi.com/blog/android00/article/1942)
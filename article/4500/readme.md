# 记一次排错过程
 

## Android 微信登陆排错记录

### 现象一: 登陆跳转到 WXEntryActivity，返回到 MainAtivity 的 LoginFragment 后，mActivity 为空。相同的第二次操作出现

    排除没有执行 onAttach, 排除执行 onAttach 时赋值为空, 排除执行了 onDetach
 

### 现象二: fragment.toString()返回的值与之前的值不一致。（第二次操作内

    怀疑前后的LoginFragment不是同一个对象。
 

### 现象三:返回后执行onStart及后续方法。（第二次操作内）

    如果不是同一个对象，为什么没有从onAttach开始执行
 

### 现象四: LoginFragment这个对象竟然是第一次操作是的对象。

    解释：第一次操作的LoginFragment被保留在不知道哪里，连onAttach等方法都省的执行，直接onStart走起。
    第二次操作时被复用了。
    但mActivity(Context)这个值没了。id和tag也没有了。
    我在里面赋了个long类型的变量却一直存在
 

### 现象五: MainActivity一直都是同一个对象
 

### 现象六：两次放回MainActivity的生命周期也没有从onCreate开始走。

    猜测：是否是微信支付WXEntryActivity做了某些操作呢？

 

### 现象七: 如果直接从AppContext中获取当前Activity，fragment的id和tag是没有的。

 
<br/>

### 结论: 上一个LoginFragment所注册的本地广播接收器没有清除，导致这个接收器在第二次操作时依然能收到消息并调用其中已经被destroy的第一次使用的LoginFragment



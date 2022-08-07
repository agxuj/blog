<h1 style="font-size: 2.5em;"> J2ee Servlet</h1>
 


## 生命周期
Servlet 生命周期可被定义为从创建直到毁灭的整个过程�?�以下是 Servlet 遵循的过程：

* Servlet 通过调用 init() 方法进行初始化�??
* Servlet 调用 service() 方法来处理客户端的请求�??
* Servlet 通过调用 destroy() 方法终止（结束）�?
* �?后，Servlet 是由 JVM 的垃圾回收器进行垃圾回收的�??

现在让我们详细讨论生命周期的方法�?

### init() 
    init 方法被设计成只调用一次�?�它在第�?次创�? Servlet 时被调用，在后续每次用户请求时不再调用�?�Servlet 创建于用户第�?次调用对应于�? Servlet �? URL 时，但是您也可以指定 Servlet 在服务器第一次启动时被加载�??

### service() 
    service() 方法是执行实际任务的主要方法。每次服务器接收到一�? Servlet 请求时，服务器会产生�?个新的线程并调用服务。service() 方法�?�? HTTP 请求类型（GET、POST、PUT、DELETE 等），并在�?�当的时候调�? doGet、doPost、doPut，doDelete 等方法�??

### destroy() 
    destroy() 方法只会被调用一次，�? Servlet 生命周期结束时被调用。destroy() 方法可以让您�? Servlet 关闭数据库连接�?�停止后台线程�?�把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动�?

### 架构�?
<img src="image/1.jpg"/>

## 过滤�?

### doFilter (ServletRequest, ServletResponse, FilterChain)

    该方法完成实际的过滤操作，当客户端请求方法与过滤器设置匹配的URL时，Servlet容器将先调用过滤器的doFilter方法。FilterChain用户访问后续过滤器�??

### init(FilterConfig filterConfig)

    web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，读取web.xml配置，完成对象的初始化功能，从�?�为后续的用户请求作好拦截的准备工作（filter对象只会创建�?次，init方法也只会执行一次）。开发人员�?�过init方法的参数，可获得代表当前filter配置信息的FilterConfig对象�?

### destroy()

    Servlet容器在销毁过滤器实例前调用该方法，在该方法中释放Servlet过滤器占用的资源�?

## 参�??

[Servlet 教程](https://www.runoob.com/servlet/servlet-tutorial.html)


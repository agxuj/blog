<h1 style="font-size: 2.5em;"> Tomcat �İ�װ����ʹ��(Linux)</h1>
 


## 下载启动
References: [Linux下Tomcat的安装配置](https://blog.csdn.net/zhuying_linux/article/details/6583096), [Tomcat官网](http://tomcat.apache.org/)

默认已经安装jdk
`````
1. 下载Tomcat
2. 解压    
    unzip tomcat.zip
3. 移动    
    mv tomcat/ /usr/local/
4. 编辑 catalina.sh   
    vi tomcat/bin/catalina.sh 
    添加内容
    CATALINA_HOME=/usr/local/tomcat/
5. 释放权限
    chmod +x *.sh
6. 启动tomcat   
    ./catalina.sh start
    ./startup.sh
7. 配置�?机启�?
    vi /etc/rc.d/rc.local

    export JAVA_HOME=/feddenyin/jdk_11_0_1
    /faddenyin/tomcat9/bin/startup.sh
`````
**注意配置阿里云，亚马逊云防火�?**


## 绑定域名和根域名

Reference:[Tomcat绑定域名和根域名](https://blog.csdn.net/kucoll/article/details/50783493)

在server.xml中配�?
`````
<Connector port="80" protocol="HTTP/1.1"
    connectionTimeout="20000"
    redirectPort="8443" URIEncoding="UTF-8" />
`````

指定域名

`````
<Host name="www.yddsj.com"  appBase="webapps"
    unpackWARs="true" autoDeploy="true">
    <!-- 省略的默认配�? ... -->

    <!-- 绑定顶级域名时要配置的，提前放出�? -->
    <Alias>yddsj.com</Alias>

    <Context path="" 
        docBase="/appuser/web/yddsj" 
        allowLinking="true">
    </Context>
</Host>
`````


## HTTPS配置
References:[TOMCAT9.0 阿里云免�? SSL证书签发配置全过程（支持多证书，多虚拟主机）](https://blog.csdn.net/gzj0078/article/details/79419007)
### 证书配置    

�?定不要把�?有的Connector标签都写hostName属�?�，我的建议是留�?条，也仅留一条不写hostName，系统会认定这条为default，如果没有这�?条，tomcat对应ssl的那个自带的servlet会抛出异常，大致意�?�是没有找到[_default_]主机对用的SSLHostConfig，即使你有一条与后面的Host里配置的默认主机�?致�??    
`````
<Connector port="443"
protocol="org.apache.coyote.http11.Http11Nio2Protocol"
maxThreads="150"
SSLEnabled="true">
    <SSLHostConfig hostName="moyuge.net.cn" >
        <Certificate 
        certificateKeystoreFile="conf/ssl/*****.pfx"
        certificateKeystorePassword="*****"
        certificateKeystoreType="PKCS12" />
    </SSLHostConfig>
    <SSLHostConfig hostName="neuread.moyuge.net.cn" >
        <Certificate 
        certificateKeystoreFile="conf/ssl/*****.pfx"
        certificateKeystorePassword="*****"
        certificateKeystoreType="PKCS12" />
    </SSLHostConfig>

    <!--
        注意：这里是没有配置域名�?
    -->
    <SSLHostConfig>
        <Certificate 
        certificateKeystoreFile="conf/ssl/*****.pfx"
        certificateKeystorePassword="*****"
        certificateKeystoreType="PKCS12" />
    </SSLHostConfig>
</Connector> 
`````

### Host虚拟主机配置

`````
<Engine name="Catalina" defaultHost="localhost">
    <Realm className="org.apache.catalina.realm.UserDatabaseRealm" 
        resourceName="UserDatabase"/>

    <Host name="localhost"  
        appBase="webapps" unpackWARs="true" 
        autoDeploy="true">
        <Context path="" docBase="HomePage" />
    </Host>
    <Host name="moyuge.net.cn"  
        appBase="webapps" unpackWARs="true" 
        autoDeploy="true">
        <!--
        标签中的是别名，也就是比如你配置了www.和was.两条解析（或者更多）�?
        但是他们的证书文件，访问文件夹都是一样的，就可以在Host上配置一�?
        -->
        <Alias>moyuge.net.cn</Alias>
        <Alias>www.moyuge.net.cn</Alias>
        <Context path="" docBase="HomePage" />
    </Host>
    <Host name="neuread.moyuge.net.cn"  
        appBase="webapps" unpackWARs="true" 
        autoDeploy="true">
        <Alias>neuread.moyuge.net.cn</Alias>
        <Context path="" docBase="NeuRead" />
    </Host>
</Engine>
`````
## Http自动跳转Https
Reference:[Tomcat的Https设置及Http自动跳转Https](https://blog.csdn.net/zhangxing52077/article/details/72827770)

编辑conf/web.xml 在末尾加�?
`````
<security-constraint>
    <web-resource-collection >
        <web-resource-name >SSL</web-resource-name>
        <url-pattern>/*</url-pattern>
    </web-resource-collection>
    <user-data-constraint>
    <transport-guarantee>CONFIDENTIAL</transport-guarantee>
    </user-data-constraint>
</security-constraint> 
`````

## 添加常用jar�?
添加常用 jar 包到 tomcat/lib �?
1. dom4j-2.1.1.jar
1. commons-logging-1.2.jar
1. mysql-connector-java-8.0.13.jar
1. mybatis-3.5.0.jar
1. gson-2.8.5.jar

## 实时查看运行日志
[References:linux下实时查看tomcat运行日志](https://blog.csdn.net/Jay_1989/article/details/52605843)
1. 先切换到：tomcat/logs
1. tail -f catalina.out

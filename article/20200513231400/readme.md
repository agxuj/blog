<h1 style="font-size: 2.5em;"> Spring Boot ��������</h1>
 



## �? intellij IDEA 中配�?
    设置的是项目文本文件的编�?
    preferences --> Editor --> File Encoding


## �? application.properties 中配�?
    设置的是请求和回复时字符串的编码
    `````
    spring.http.encoding.charset=UTF-8
    spring.http.encoding.enabled=true
    spring.http.encoding.force=true
    `````

## �? pom.xml 中配�?
    设置的是构建打包时字符串的编�?
    `````
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>
    `````


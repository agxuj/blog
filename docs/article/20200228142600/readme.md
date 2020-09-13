<h1 style="font-size: 2.5em;"> Spring Boot 集成使用JSP</h1>
 


## 在pro.xml中添加依赖
``````
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
</dependency>
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>javax.servlet.jsp-api</artifactId>
    <version>2.3.1</version>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <scope>provided</scope>
</dependency>
``````

## 在appliction.properties文件配置Spring mvc的视图展示为jsp
``````
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
``````

## 建立web文件存放点

在src/main目录中新建webapp文件夹，在webapp目录中新建/WEB-INF/jsp/文件夹用于存放jsp文件，其他类型的文件直接存放在webapp文件夹中。

## 在pro.xml的build标签中添加配置
``````
<resources>
    <resource>
        <directory>src/main/java</directory>
        <includes>
            <include>**/*.xml</include>
        </includes>
    </resource>

    <resource>
        <directory>src/main/resources</directory>
        <includes>
            <include>**/*.*</include>
        </includes>
    </resource>

    <resource>
        <directory>src/main/webapp</directory>
        <targetPath>META-INF/resources</targetPath>
        <includes>
            <include>**/*.*</include>
        </includes>
    </resource>
</resources>
``````
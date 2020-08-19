<h1 style="font-size: 2.5em;"> Spring Boot 集成 MyBatis</h1>
 

## 在 pom.xml 中添加 dependency
`````
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.16</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.0.1</version>
    </dependency>
</dependencies>
`````
## 在 pom.xml 中添加对 xml 文件的引用

`````
<build>
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
</build>
`````

## 配置 application.properties
`````
spring.datasource.driver=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost/name
spring.datasource.username=
spring.datasource.password=
`````

## 新建 mybatis-config.xml
`````
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="application.properties" />
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${spring.datasource.driver}"/>
                <property name="url" value="${spring.datasource.url}"/>
                <property name="username" value="${spring.datasource.username}"/>
                <property name="password" value="${spring.datasource.password}"/>
                <!-- https://my.oschina.net/xpbug/blog/324978 -->
                <property name="poolPingEnabled" value="true"/>
                <property name="poolPingQuery" value="select 1"/>
                <property name="poolPingConnectionsNotUsedFor" value="300000"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper class="com.excample.database.dal.TestDAL"/> 
    </mappers>
</configuration>
`````

## 新建 DAL 类
`````
public interface TestDAL {

    @Select("")
    Test get(String id);

    @Update("")
    void update(Test test);

    @Insert("")
    void add(Test test);

    @Delete("")
    void delete(Test test);
}
`````

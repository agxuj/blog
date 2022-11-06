<h1 style="font-size: 2.5em;"> Spring Boot 打包</h1>
 



## 打成 jar 包

### 在 pom.xml 中配置
`````
<project>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>1.4.2.RELEASE</version> //注意
            </plugin>   
        </plugins>
    </build>
</project>
`````

### 打包
maven --> install


## 打成 war 包

### 修改 Application.java
1. 继承 SpringBootServletInitializer
1. 实现 configure 方法

代码如下:
`````
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
`````

### 修改 pom.xml
`````
<project>
    <packaging>war</packaging>
</project>
`````
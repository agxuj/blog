<h1 style="font-size: 2.5em;"> Spring mvc ������</h1>
 


## 导jar�?
`````
spring-aop-4.1.6.RELEASE.jar
spring-beans-4.1.6.RELEASE.jar
spring-context-4.1.6.RELEASE.jar
spring-context-support-4.1.6.RELEASE.jar
spring-core-4.1.6.RELEASE.jar
spring-expression-4.1.6.RELEASE.jar
spring-web-4.1.6.RELEASE.jar
spring-webmvc-4.1.6.RELEASE.jar
commons-logging-1.2.jar
`````
[下载地址](http://repo.spring.io/simple/libs-release-local/org/springframework/spring/)

## xml配置

`````
<servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>*.html</url-pattern>
</servlet-mapping>
`````
## 创建src/mvc.xml

配置 controller �?在的包名

配置 view (jsp) �?在的目录
`````
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">


    <!-- 配置渲染�? -->
    <bean id="jspViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <!-- 配置 view (jsp) �?在的目录 -->
        <property name="prefix" value="/WEB-INF/manager/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    <!-- 配置 controller �?在的包名 -->
    <context:component-scan base-package="top.knxy"/>
</beans>
`````
## 创建Controller
`````
@Controller
public class ManagerController {

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","hello Spring MVC");
        mv.setViewName("login");//指向jsp的页�?
        return mv;
    }
}

`````

References:

[Spring Framework Reference Documentation](https://docs.spring.io/spring/docs/4.1.6.RELEASE/spring-framework-reference/html/)
# Spring beans.xml的常见配置
 

## bean.xml的加载方式

`````
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">

    <!-- 使用id属性定义person1，其对应的实现类为com.mengma.person1 -->
    <bean id="person1" class="com.mengma.damain.Person1" />
    
    <!--使用name属性定义person2，其对应的实现类为com.mengma.domain.Person2-->
    <bean name="Person2" class="com.mengma.domain.Person2"/>
</beans>
`````


<bean> 元素中包含很多属性，其常用属性如表 1 所示。

|属性名称|描述|
|-|-|
|id|是一个 Bean 的唯一标识符，Spring 容器对 Bean 的配置和管理都通过该属性完成|
|name |Spring 容器同样可以通过此属性对容器中的 Bean 进行配置和管理，name 属性中可以为 Bean 指定多个名称，每个名称之间用逗号或分号隔开|
|class|该属性指定了 Bean 的具体实现类，它必须是一个完整的类名，使用类的全限定名|
|scope|用于设定 Bean 实例的作用域，其属性值有 singleton（单例）、prototype（原型）、request、session 和 global Session。其默认值是 singleton|
|constructor-arg|\<bean>元素的子元素，可以使用此元素传入构造参数进行实例化。该元素的 index 属性指定构造参数的序号（从 0 开始），type 属性指定构造参数的类型|
|property|\<bean>元素的子元素，用于调用 Bean 实例中的 Set 方法完成属性赋值，从而完成依赖注入。该元素的 name 属性指定 Bean 实例中的相应属性名 |
|ref|\<property> 和 \<constructor-arg> 等元素的子元索，该元素中的 bean 属性用于指定对 Bean 工厂中某个 Bean 实例的引用|
|value| \<property> 和 \<constractor-arg> 等元素的子元素，用于直接指定一个常量值|
|list| 用于封装 List 或数组类型的依赖注入|
|set |用于封装 Set 类型属性的依赖注入 |
|map |用于封装 Map 类型属性的依赖注入 |
|entry |<map> 元素的子元素，用于设置一个键值对。其 key 属性指定字符串类型的键值，ref 或 value 子元素指定其值|

## 参考
[Spring Bean的配置及常用属性](http://c.biancheng.net/view/4254.html)
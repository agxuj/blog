<h1 style="font-size: 2.5em;"> Spring beans.xml�ĳ�������</h1>
 

## bean.xml的加载方�?

`````
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">

    <!-- 使用id属�?�定义person1，其对应的实现类为com.mengma.person1 -->
    <bean id="person1" class="com.mengma.damain.Person1" />
    
    <!--使用name属�?�定义person2，其对应的实现类为com.mengma.domain.Person2-->
    <bean name="Person2" class="com.mengma.domain.Person2"/>
</beans>
`````


<bean> 元素中包含很多属性，其常用属性如�? 1 �?示�??

|属�?�名称|描述|
|-|-|
|id|是一�? Bean 的唯�?标识符，Spring 容器�? Bean 的配置和管理都�?�过该属性完成|
|name |Spring 容器同样可以通过此属性对容器中的 Bean 进行配置和管理，name 属�?�中可以�? Bean 指定多个名称，每个名称之间用逗号或分号隔�?|
|class|该属性指定了 Bean 的具体实现类，它必须是一个完整的类名，使用类的全限定名|
|scope|用于设定 Bean 实例的作用域，其属�?��?�有 singleton（单例）、prototype（原型）、request、session �? global Session。其默认值是 singleton|
|constructor-arg|\<bean>元素的子元素，可以使用此元素传入构�?�参数进行实例化。该元素�? index 属�?�指定构造参数的序号（从 0 �?始），type 属�?�指定构造参数的类型|
|property|\<bean>元素的子元素，用于调�? Bean 实例中的 Set 方法完成属�?�赋值，从�?�完成依赖注入�?�该元素�? name 属�?�指�? Bean 实例中的相应属�?�名 |
|ref|\<property> �? \<constructor-arg> 等元素的子元索，该元素中�? bean 属�?�用于指定对 Bean 工厂中某�? Bean 实例的引用|
|value| \<property> �? \<constractor-arg> 等元素的子元素，用于直接指定�?个常量�?�|
|list| 用于封装 List 或数组类型的依赖注入|
|set |用于封装 Set 类型属�?�的依赖注入 |
|map |用于封装 Map 类型属�?�的依赖注入 |
|entry |<map> 元素的子元素，用于设置一个键值对。其 key 属�?�指定字符串类型的键值，ref �? value 子元素指定其值|

## 参�??
[Spring Bean的配置及常用属�?�](http://c.biancheng.net/view/4254.html)
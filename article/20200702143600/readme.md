<h1 style="font-size: 2.5em;"> Java ע��</h1>
 


 
## 内置注解�?单介�? 

Java 定义了一套注解，共有 7 个，3 个在 java.lang 中，剩下 4 个在 java.lang.annotation 中�??

### 作用在代码的注解�?

* @Override - �?查该方法是否是重写方法�?�如果发现其父类，或者是引用的接口中并没有该方法时，会报编译错误�?
* @Deprecated - 标记过时方法。如果使用该方法，会报编译警告�??
* @SuppressWarnings - 指示编译器去忽略注解中声明的警告�?

### 作用在其他注解的注解(或�?�说 元注�?)�?:

* @Retention - 标识这个注解怎么保存，是只在代码中，还是编入class文件中，或�?�是在运行时可以通过反射访问�?
* @Documented - 标记这些注解是否包含在用户文档中�?
* @Target - 标记这个注解应该是哪�? Java 成员�?
* @Inherited - 标记这个注解是继承于哪个注解�?(默认 注解并没有继承于任何子类)

### �? Java 7 �?始，额外添加�? 3 个注�?:

* @SafeVarargs - Java 7 �?始支持，忽略任何使用参数为泛型变量的方法或构造函数调用产生的警告�?
* @FunctionalInterface - Java 8 �?始支持，标识�?个匿名函数或函数式接口�??
* @Repeatable - Java 8 �?始支持，标识某注解可以在同一个声明上使用多次�?

## 注解的架�?
<img src="image/1.jpg" />

* 1 �? Annotation �? 1 �? RetentionPolicy 关联�?
* 1 �? Annotation �? 1~n �? ElementType 关联�?
* Annotation 有许多实现类，包括：Deprecated, Documented, Inherited, Override 等等�?

## 注解实现�?�?理解�?3个主要类
### Annotation.java
`````
package java.lang.annotation;
public interface Annotation {
    boolean equals(Object obj);
    int hashCode();
    String toString();
    Class<? extends Annotation> annotationType();
}
`````
### ElementType.java
`````
package java.lang.annotation;
public enum ElementType {
    TYPE,               /* 类�?�接口（包括注释类型）或枚举声明  */
    FIELD,              /* 字段声明（包括枚举常量）  */
    METHOD,             /* 方法声明  */
    PARAMETER,          /* 参数声明  */
    CONSTRUCTOR,        /* 构�?�方法声�?  */
    LOCAL_VARIABLE,     /* �?部变量声�?  */
    ANNOTATION_TYPE,    /* 注释类型声明  */
    PACKAGE             /* 包声�?  */
}
`````
### RetentionPolicy.java
`````
package java.lang.annotation;
public enum RetentionPolicy {
    SOURCE,            /* Annotation信息仅存在于编译器处理期间，编译器处理完之后就没有该Annotation信息�?  */
    CLASS,             /* 编译器将Annotation存储于类对应�?.class文件中�?�默认行�?  */
    RUNTIME            /* 编译器将Annotation存储于class文件中，并且可由JVM读入 */
}
`````

## 注解的简单实�?
`````
//类和方法�? Annotation 在缺省情况下是不出现�? javadoc 中的�?
//如果使用 @Documented 修饰�? Annotation，则表示它可以出现在 javadoc 中�??
//定义 Annotation 时，@Documented 可有可无；若没有定义，则 Annotation 不会出现�? javadoc 中�??
@Documented

//前面我们说过，ElementType �? Annotation 的类型属性�?��?? @Target 的作用，就是来指�? Annotation 的类型属性�??
//@Target(ElementType.TYPE) 的意思就是指定该 Annotation 的类型是 ElementType.TYPE�?
//这就意味�?，MyAnnotation1 是来修饰"类�?�接口（包括注释类型）或枚举声明"的注解�??
//定义 Annotation 时，@Target 可有可无�?
//若有 @Target，则�? Annotation 只能用于它所指定的地方；若没�? @Target，则�? Annotation 可以用于任何地方�?
@Target(ElementType.TYPE)

//前面我们说过，RetentionPolicy �? Annotation 的策略属性，�? @Retention 的作用，就是指定 Annotation 的策略属性�??
//@Retention(RetentionPolicy.RUNTIME) 的意思就是指定该 Annotation 的策略是 RetentionPolicy.RUNTIME�?
//这就意味�?，编译器会将�? Annotation 信息保留�? .class 文件中，并且能被虚拟机读取�??
//定义 Annotation 时，@Retention 可有可无�?
//若没�? @Retention，则默认�? RetentionPolicy.CLASS�?
@Retention(RetentionPolicy.RUNTIME)

//使用 @interface 定义注解时，意味�?它实现了 java.lang.annotation.Annotation 接口，即该注解就是一个Annotation�?
public @interface MyAnnotation1 {
}
`````


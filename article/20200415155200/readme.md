<h1 style="font-size: 2.5em;"> ��һ���Ŵ����� --- intelliJ idea ����⹤��ż���Ա���</h1>
 

## 问题:
导入库工�? top.knxy.library.java 总是失败，�?�么回事呢？
 
### 现象1:
进行�?下操作后

1. project stucture -> modules -> import modules
1. top.knxy.age.java -> modules depandency

主工程可以进�? maven -> install , 生成的jar包可以使�?, 但项目无法编译运行�??

### 现象2:
我手动进�?
project stucture -> modules -> top.knxy.library => /Users/faddenyin/workspace/top.knxy.library.java/target/server-1.0.jar
的引入也无法解决问题

### 现象3:
把pom.xml文件中的
`````
<dependency>
    <groupId>top.knxy.library</groupId>
    <artifactId>server</artifactId>
    <version>1.0</version>
    <scope>compile</scope>
</dependency>
`````
删掉再添加进去，并且点击import changes后问题解决�??

## 问题:
执行上述操作�?,问题又出�?.

### 现象1:
不在 project stucture 引入 module , 把本地仓库中�? ibrary.jar 等文件删�? �? 问题解决.

## 结论:

正确操作如下�?
1. library project install
1. 把本地仓库的 library.jar 等文件删�?
1. 把pom.xml文件中的
    `````
    <dependency>
        <groupId>top.knxy.library</groupId>
        <artifactId>server</artifactId>
        <version>1.0</version>
        <scope>compile</scope>
    </dependency>
    `````
    删掉再添加进去，并且点击import changes.
 
--------------------------

### 现象2: 
project stucture -> modules -> Maven: top.knxy.library:server:1.0 => /Users/faddenyin/.m2/repository/top/knxy/library/server/1.0/server-1.0.jar

### 现象3: 
project stucture -> libraries -> top.knxy.library => /Users/faddenyin/workspace/top.knxy.library.java/target/server-1.0.jar

### 现象4: 
project stucture -> modules -> top.knxy.library => /Users/faddenyin/workspace/top.knxy.library.java/target/server-1.0.jar

### 得：
此前存在多余操作�? Maven: top.knxy.library:server:1.0 �? top.knxy.library 是重复的,但我却手动引�? top.knxy.library .

## 问题�?
Maven: top.knxy.library:server:1.0是什么时候的�?么操作引用？

## �?:
�? pom.xml 中引�? dependency �?,自动生成

---------------------------


### 现象1: 
project stucture -> libraries -> Maven: top.knxy.library:server:1.0 => /Users/faddenyin/.m2/repository/top/knxy/library/server/1.0/server-1.0.jar

### 现象2:
在项目top.knxy.library.java用maven打包（install）后, 目录/Users/faddenyin/.m2/repository/中会存在相关的jar�?

### �?:
top.knxy.library.java这个项目打包后在/Users/faddenyin/.m2/repository/目录中存�?

---------------------------
 
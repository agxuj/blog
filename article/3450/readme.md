# 20200415155200
 

## 问题:
倒入库工程 top.knxy.library.java 总是失败，怎么回事呢？
 
### 现象1:
进行一下操作后

1. project stucture -> modules -> import modules
1. top.knxy.age.java -> modules depandency

主工程可以进行 maven -> install , 生成的jar包可以使用, 但项目无法编译运行。

### 现象2:
我手动进行
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
删掉再添加进去，并且点击import changes后问题解决。

## 结论:

正确操作如下：
1. project stucture -> modules -> import modules
1. top.knxy.age.java -> modules depandency
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
此前存在多余操作， Maven: top.knxy.library:server:1.0 和 top.knxy.library 是重复的,但我却手动引入 top.knxy.library .

## 问题：
Maven: top.knxy.library:server:1.0是什么时候的什么操作引用？

---------------------------


### 现象1: 
project stucture -> libraries -> Maven: top.knxy.library:server:1.0 => /Users/faddenyin/.m2/repository/top/knxy/library/server/1.0/server-1.0.jar

### 现象2:
在项目top.knxy.library.java用maven打包（install）后, 目录/Users/faddenyin/.m2/repository/中会存在相关的jar包

### 得:
top.knxy.library.java这个项目打包后在/Users/faddenyin/.m2/repository/目录中存在




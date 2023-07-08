前提：
1. 已经安装jdk1.8
1. 服务器内存大于2G

# 安装maven

官网下载版本：<a href="https://archive.apache.org/dist/maven/maven-3/">下载地址</a>

## 解压

``````
tar -zxvf apache-maven-3.6.3-bin.tar.gz 
``````

## 配置环境变量

``````
vi /etc/profile
``````
添加如下内容

``````
export MAVEN_HOME=/yin/program/apache-maven-3.6.3 // 注意修改路径
export PATH=$MAVEN_HOME/bin:$PATH 
``````
刷新环境变量
``````
source /etc/profile
``````
检查maven版本，如下正确显示版本号则代表maven安装完成
``````
mvn -v


Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/apache-maven-3.6.3
Java version: 1.8.0_262, vendor: Oracle Corporation, runtime: /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.262.b10-1.el7.x86_64/jre
Default locale: zh_CN, platform encoding: UTF-8
OS name: "linux", version: "3.10.0-1160.el7.x86_64", arch: "amd64", family: "unix"
``````

# 安装nexus

官网下载版本：<a href="https://help.sonatype.com/repomanager3/product-information/download/download-archives---repository-manager-3">下载地址</a>

大陆下载不了，需要翻墙。

## 解压
``````
tar -zxvf nexus-3.37.1-01-unix.tar.gz 
``````

## 修改配置文件
``````
vi /yin/program/nexus-3.37.1-01/etc/nexus-default.properties

application-port= //你的端口号
application-host= //你的ip
``````

## 添加JAVA_HOME路径

``````
vi /yin/program/nexus-3.37.1-01/bin/nexus

INSTALL4J_JAVA_HOME_OVERRIDE=/yin/program/jdk1.8.0_202
``````

## 启动服务
``````
# 进入bin目录下

./nexus start
``````


# 参考
[Linux搭建自己的Maven私有仓库（nexus）](https://blog.csdn.net/jiumingmao1991/article/details/122168169)

[解决Nexus自启动JDK版本报错问题No suitable Java Virtual Machine could be found on your system](https://blog.csdn.net/H_Dsheng/article/details/121744448)
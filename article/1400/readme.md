# Linux 下 Java的安装
 


## 卸载
### 查看jdk相关路径
`````
[root]# whereis java
[root]# which java （java执行路径）
[root]# echo $JAVA_HOME
[root]# echo $PATH
`````
### 删除jdk目录
`````
[root]# rm -rf jdk
`````

## 安装
### 下载
`````
[root]# wget https://download.oracle.com/otn-pub/java/jdk/12.0.1+12/69cfe15208a647278a19ef0990eea691/jdk-12.0.1_linux-x64_bin.tar.gz?AuthParam=1562038808_20126ea158e4211beec4564f7955ec8a
`````

### 解压
`````
[root]# tar -zxvf jdk_12_0_1.tar.gz
`````

### 修改/etc/profile文件中java的配置
`````
export JAVA_HOME=/faddenyin/jdk_12_0_1
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin
`````
*使profile生效*
`````
[root]# source /etc/profile
`````


## Reference:
[Linux安装jdk1.8和配置环境变量](https://www.cnblogs.com/zs-notes/p/8535275.html)
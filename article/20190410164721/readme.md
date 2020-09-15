<h1 style="font-size: 2.5em;"> MySQL �İ�װ(Linux)</h1>
 


 
## 官网下载安装
### 1.下载
到[Oracle Download](https://dev.mysql.com/downloads/mysql/)查看下载地址
`````
wget https://cdn.mysql.com//Downloads/MySQL-8.0/mysql-8.0.15-linux-glibc2.12-x86_64.tar
`````
### 2.解压
`````
tar -xvf mysql-8.0.15-linux-glibc2.12-x86_64.tar
tar -xvf mysql-8.0.15-linux-glibc2.12-x86_64.tar.xz
`````
### 3.移动目录
`````
mv mysql-8.0.15-linux-glibc2.12-x86_64 /faddenyin/mysql8
`````
### 4.添加用户
`````
groupadd mysql //创建 mysql 用户�?
useradd -g mysql mysql //创建 mysql 用户
`````
### 5.设置权限
`````
chown -R mysql.mysql /faddenyin/mysql8/
//or
chown -R mysql .
chgrp -R mysql .
`````
### 6.创建my.cnf
`````
vi /etc/my.cnf
`````

`````
[mysqld]
port=3306
basedir = /faddenyin/mysql8
datadir = /faddenyin/mysql8/data
socket=/tmp/mysql.sock
log-error=/faddenyin/mysql8/data/error.log
pid-file=/faddenyin/mysql8/data/mysql.pid
user=mysql
tmpdir=/tmp
character_set_server=utf8
default-storage-engine=INNODB
init_connect='SET NAMES utf8'
default_authentication_plugin=mysql_native_password
default-time_zone = '+8:00'//设置时区

[client]
port=3306
default-character-set=utf8 
`````
### 7.初始化数据库
**这里会生成一个随机密�?,注意保留**
`````
cd /faddenyin/mysql8/bin
./mysqld --initialize --user=mysql
`````
如果出现错误�?
`````
error while loading shared libraries: libaio.so.1: cannot open shared object file: No such file or directory
//安装libaio
yum install libaio
`````

### 8.配置mysql服务
`````
cd /faddenyin/mysql8
cp -a ./support-files/mysql.server /etc/init.d/mysqld

chmod +x /etc/rc.d/init.d/mysqld    
chkconfig --add mysqld

//�?查服务是否生�?  
chkconfig  --list mysqld
`````
### 9.配置全局环境变量
`````
vi /etc/profile
`````
�? profile 文件底部添加如下两行配置，保存后�?�?
`````
PATH=/faddenyin/mysql8/bin:/faddenyin/mysql8/lib:$PATH
export PATH
`````
设置环境变量立即生效
`````
source /etc/profile
`````
### 10.启动MySQL服务
`````
service mysql start
`````
## yum命令安装
### 下载repo源并导入
mysql 官网下载 repo�? 
[Repo List](https://dev.mysql.com/downloads/repo/yum/), 选择Red Hat
``````
https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm
``````
安装 yum repo文件并更�? yum 缓存
``````
rpm -ivh mysql80-community-release-el7-3.noarch.rpm
//更新 yum 命令
yum clean all
yum makecache
``````
### 安装mysql 
``````
yum install mysql-community-server
``````
### �?启mysql 服务
``````
systemctl start mysqld.service
``````
### 获取初始密码登录mysql
``````
cat /var/log/mysqld.log | grep password
``````

## 卸载

### 1. 停止服务
`````
service mysqld stop
`````
### 2. 查看当前安装mysql情况，查找以前是否装有mysql
`````
rpm -qa|grep -i mysql
----------------------
mysql-community-client-8.0.13-1.el7.x86_64
mysql-community-libs-8.0.13-1.el7.x86_64
mysql-community-common-8.0.13-1.el7.x86_64
mysql-community-server-8.0.13-1.el7.x86_64
`````
### 3. 执行命令删除安装的MySQL
逐条删除上一步查询的结果
`````
rpm -ev mysql-community-client-8.0.13-1.el7.x86_64 --nodeps
`````
### 4. 查看之前安装的MySQL的目录并删除
`````
find / -name mysql

/var/lib/mysql
/var/lib/mysql/mysql
/usr/lib64/mysql
/usr/share/mysql

rm -rf /var/lib/mysql
.
.
.
`````

### 5. 删除my.cnf

`````
rm -rf /etc/my.cnf
`````


## 备注

添加防火墙开放端�?

## Reference
[Linux下如何彻底删�?(卸载)MySQL?](https://jingyan.baidu.com/article/4b52d702db8a82fc5c774b92.html)

[Linux下卸载MySQL8.0版本](https://blog.csdn.net/eric_wii/article/details/86300450)

[如何在Ubuntu Linux上安�? MySQL 8.0.11](https://blog.51cto.com/13804472/2134479)

[Linux安装mysql8](https://blog.csdn.net/cacalili/article/details/80805855)

[MySQL 8.0给数据库添加用户和赋权](https://www.cnblogs.com/testway/p/9289827.html)

[MySQL中用户权限�?�库权限、表权限的控制](https://blog.csdn.net/u010735147/article/details/81744002)

[MySQL时间和本地时间相�?13个小时](https://blog.csdn.net/xgs736214763/article/details/78814072)

[MySQL修改时区的几种方法](https://www.cnblogs.com/shiqiangqiang/p/8393662.html)

[MySQL用户及权限管�? 小结](https://www.cnblogs.com/SQL888/p/5748824.html)

[libaio install](https://help.directadmin.com/item.php?id=368)

[CentOS7 安装 MySQL8](https://www.cnblogs.com/yaowen/p/9486138.html)

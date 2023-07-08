
# 下载
`````
wget https://cdn.mysql.com//Downloads/MySQL-5.7/mysql-5.7.42-linux-glibc2.12-x86_64.tar.gz
`````

# 解压
`````
tar -zxvf mysql-5.7.42-linux-glibc2.12-x86_64.tar.gz 
`````

# 移动
`````
mv mysql-5.7.42-linux-glibc2.12-x86_64 ../program/
`````

# 创建data目录
`````
cd ../program/mysql-5.7.42-linux-glibc2.12-x86_64/
mkdir data
chmod -R 777 data
`````

# 创建用户 、组、并将用户加入组
`````
groupadd mysql
useradd -g mysql mysql
`````

# 修改配置文件
`````
vi /etc/my.cnf
`````
`````
[mysqld]
port=3306
user=mysql
basedir=/yin/program/mysql-5.7.42-linux-glibc2.12-x86_64
datadir=/yin/program/mysql-5.7.42-linux-glibc2.12-x86_64/data
socket=/tmp/mysql.sock
log-error=/yin/program/mysql-5.7.42-linux-glibc2.12-x86_64/data/error.log
pid-file=/yin/program/mysql-5.7.42-linux-glibc2.12-x86_64/data/mysql.pid
tmpdir=/tmp
character_set_server=utf8mb4
default-storage-engine=INNODB
init_connect='SET NAMES utf8'
default_authentication_plugin=mysql_native_password
default-time_zone='+8:00'#设置时区

[client]
port=3306
default-character-set=utf8
`````

# 安装并初始化
`````
cd /yin/program/mysql-5.7.42-linux-glibc2.12-x86_64/bin
./mysqld --initialize --user=mysql
`````
如果报错：./mysqld: error while loading shared libraries: libnuma.so.1: cannot open shared object file: No such file or directory
是因为没有安装依赖 libaio

``````
yum -y install libaio-devel.x86_64
yum -y install numactl
``````
查看mysql 密码
``````
cat /yin/program/mysql-5.7.42-linux-glibc2.12-x86_64/data/error.log
``````

# 添加软连接
``````
ln -s /yin/program/mysql-5.7.42-linux-glibc2.12-x86_64/support-files/mysql.server /etc/init.d/mysql
ln -s /yin/program/mysql-5.7.42-linux-glibc2.12-x86_64/bin/mysql /usr/bin/mysql
``````

# 启动mysql服务
``````
service mysql start
``````

# 登录mysql
``````
mysql -uroot -p
``````

# 修改密码
``````
set password=password('root1234');
flush privileges;
``````

# 参考
[mysql-5.7 Linux安装教程](https://blog.csdn.net/m0_64684588/article/details/121636825)

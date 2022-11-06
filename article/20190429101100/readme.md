<h1 style="font-size: 2.5em;"> Redis ��װ�벿��(Linux)</h1>
 



## 下载与安�?
`````
[root]# wget http://download.redis.io/releases/redis-5.0.4.tar.gz
[root]# tar xzf redis-5.0.4.tar.gz
[root]# cd redis-5.0.4
[root]# make //编译
[root]# cd src
[root]# make install
`````
## 创建bin和ect目录
`````
[root]# mkdir -p /usr/local/redis/bin
[root]# mkdir -p /usr/local/redis/etc

[root]# cp /faddenyin/redis-5.0.4/redis.conf /usr/local/redis/etc
[root]# cd /faddenyin/redis-5.0.4/src
[root]# cp redis-benchmark redis-check-aof redis-cli redis-server /usr/local/redis/bin
`````

## 配置
`````
[root]# vi /faddenyin/redis-5.0.4/redis.conf
`````
配置外网可以访问
`````
bind 127.0.0.1
protected-mode yes
//更改�?,注意注释127.0.0.1
#bind 127.0.0.1
protected-mode no

`````
配置密码
`````
requirepass ${password}
`````

## 启动服务
`````
[root]# cd /usr/local/redis/bin
[root]# ./redis-server //启动服务
`````

后台启动redis服务
`````
[root]# vi /usr/local/redis/etc/redis.conf

daemonize yes

//再次启动redis服务，并指定启动服务配置文件
[root]# ./redis-server /usr/local/redis/etc/redis.conf
`````
查看端口
`````
[root]# netstat -tunpl|grep 6379
`````
## 关闭服务
`````
[root]# redis-cli -h 127.0.0.1 -p 6379 -a ${password} shutdown
`````
## 启动客户�?
`````
[root]# cd /usr/local/redis/bin
[root]# ./redis-cli
`````
## 关闭客户�?
`````
[root]# ./redis-cli shutdown 
`````
## 其他配置
### 修改profile文件
`````
[root]# vi /etc/profile
export PATH=$PATH:/usr/local/redis/bin
`````
### 配置内核参数
**否则Redis脚本在重启或停止redis时，将会报错，并且不能自动在停止服务前同步数据到磁盘�?/etc/sysctl.conf加上**
`````
[root]# vi /etc/sysctl.conf
vm.overcommit_memory = 1 
sysctl -p
`````
### 配置service
`````
[root]# vi /etc/init.d/redis
`````
**<span style="color:red">Ensure</span> your init.d PIDFILE=/var/run/redis.pid is the same as your redis.conf pidfile
/var/run/redis.pid and you are done!**
`````
#!/bin/sh
#
# redis        Startup script for Redis Server
#
# chkconfig: - 80 12
# description: Redis is an open source, advanced key-value store.
#
# processname: redis-server
# config: /etc/redis.conf
# pidfile: /var/run/redis.pid
source /etc/init.d/functions
BIN="/usr/local/redis/bin"
CONFIG="/usr/local/redis/etc/redis.conf"
PIDFILE="/var/run/redis.pid"
### Read configuration
[ -r "$SYSCONFIG" ] && source "$SYSCONFIG"
RETVAL=0
prog="redis-server"
desc="Redis Server"
start() {
        if [ -e $PIDFILE ];then
             echo "$desc already running...."
             exit 1
        fi
        echo -n $"Starting $desc: "
        daemon $BIN/$prog $CONFIG
        RETVAL=$?
        echo
        [ $RETVAL -eq 0 ] && touch /var/lock/subsys/$prog
        return $RETVAL
}
stop() {
        echo -n $"Stop $desc: "
        killproc $prog
        RETVAL=$?
        echo
        [ $RETVAL -eq 0 ] && rm -f /var/lock/subsys/$prog $PIDFILE
        return $RETVAL
}
restart() {
        stop
        start
}
case "$1" in
  start)
        start
        ;;
  stop)
        stop
        ;;
  restart)
        restart
        ;;
  condrestart)
        [ -e /var/lock/subsys/$prog ] && restart
        RETVAL=$?
        ;;
  status)
        status $prog
        RETVAL=$?
        ;;
   *)
        echo $"Usage: $0 {start|stop|restart|condrestart|status}"
        RETVAL=1
esac
exit $RETVAL
`````
`````
chmod +x /etc/init.d/redis
service redis start
service redis stop
chkconfig --add redis
`````


## Reference 
[理解Linux系统/etc/init.d目录�?/etc/rc.local脚本](https://blog.csdn.net/acs713/article/details/7322082)

[linux命令sysctl使用](https://www.cnblogs.com/codeblock/p/5207431.html)

[Linux下Redis的安装和部署](https://www.cnblogs.com/wangchunniu1314/p/6339416.html)

[如何停止/重启/启动redis服务](http://outofmemory.cn/code-snippet/37597/how-to-stop-start-restart-redis-server)

[redis 设置外网可访问](https://www.cnblogs.com/zhangqigao/p/9110544.html)

[Redis 密码设置和查看密码](https://www.cnblogs.com/suanshun/p/7699084.html)

[redis 如何执行redis命令](https://www.cnblogs.com/AlanLee/p/5927700.html)
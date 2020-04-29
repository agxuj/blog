# Linux 下 Nginx的安装
 


建议安装最新版本
## 安装编译工具及库文件
`````
[root]# yum -y install make zlib zlib-devel gcc-c++ libtool  openssl openssl-devel
`````

## 安装 PCRE
PCRE 作用是让 Nginx 支持 Rewrite 功能。

### 下载
`````
[root]# cd /usr/local/src/
[root]# wget ftp://ftp.csx.cam.ac.uk/pub/software/programming/pcre/pcre-8.43.tar.gz
`````
### 解压
`````
[root]# tar zxvf pcre-8.43.tar.gz
`````
### 编译安装 
`````
[root]# cd pcre-8.35
[root]# ./configure
[root]# make && make install
//查看版本
[root]# pcre-config --version
`````

## 安装 Nginx
### 下载
`````
[root]# cd /usr/local/src/
[root]# wget http://nginx.org/download/nginx-1.9.9.tar.gz
`````
### 解压
`````
[root]# tar zxvf nginx-1.6.2.tar.gz
[root]# cd nginx-1.6.2
`````

### 编译安装
`````
[root]# ./configure --prefix=/usr/local/webserver/nginx --with-http_stub_status_module --with-http_ssl_module --with-pcre=/usr/local/src/pcre-8.35
[root]# make
[root]# make install
//查看版本
[root]# /usr/local/webserver/nginx/sbin/nginx -v
`````

## Nginx 配置
### 创建用户
`````
[root]# /usr/sbin/groupadd www 
[root]# /usr/sbin/useradd -g www www 
`````

### 配置nginx.conf
`````
[root]# vi /usr/local/webserver/nginx/conf/nginx.conf
`````

`````
user www www;
worker_processes 1; #设置值和CPU核心数一致
error_log /usr/local/webserver/nginx/logs/nginx_error.log crit; #日志位置和日志级别
pid /usr/local/webserver/nginx/nginx.pid;
#Specifies the value for maximum file descriptors that can be opened by this process.
worker_rlimit_nofile 65535;
events
{
    use epoll;
    worker_connections 65535;
}
http
{
    include       mime.types;
    default_type  application/octet-stream;
 
    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';
    #access_log  logs/access.log  main;
 
    sendfile        on;
    #tcp_nopush     on;
 
    #keepalive_timeout  0;
    keepalive_timeout  65;
 
    #gzip  on;
	 
    server {
        listen 443 ssl;
        server_name age.knxy.top;
        ssl on; 
        ssl_certificate /etc/ssl/1789802_age.knxy.top.pem;
        ssl_certificate_key /etc/ssl/1789802_age.knxy.top.key;

        ssl_session_timeout 5m;
        ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4;
        ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
        ssl_prefer_server_ciphers on;
        
        location / {
                proxy_pass http://127.0.0.1:8088;  
            }
        }
        server{
            listen 80;
            server_name age.knxy.top;
            location / {
                proxy_pass http://127.0.0.1:8088;  
            }
        }
        server{
            listen 80;
            server_name www.funning.top;
            root /faddenyin/tomcat9/webs/top.funning.www;
        }
}

`````
### 检查配置文件nginx.conf的正确性
`````
[root]# /usr/local/webserver/nginx/sbin/nginx -t
`````

###  Nginx启动,关闭,重启
`````

[root]# /usr/local/webserver/nginx/sbin/nginx -s reload    # 重新载入配置文件
[root]# /usr/local/webserver/nginx/sbin/nginx              # 启动
[root]# /usr/local/webserver/nginx/sbin/nginx -s reopen    # 重启 Nginx
[root]# /usr/local/webserver/nginx/sbin/nginx -s stop      # 停止 Nginx
`````
## 添加 service 配置

`````
[root]# /etc/init.d/idea
[root]# chmod a+wrx /etc/init.d/idea
`````
内容：
`````
#!/bin/sh
# chkconfig: 2345 80 90
# description: nginx register server

PATH="/usr/local/webserver/nginx/sbin/nginx"
start(){
    $PATH
}
stop(){
    $PATH -s stop
}
reopen(){
    $PATH -s reopen
}
reload(){
    $PATH -s reload
}
case "$1" in
"start")
    start
    ;;
"stop")
    stop
    ;;
"reopen")
    reopen
    ;;
"reload")
    reload
    ;;
esac
`````

服务启动：service idea start

服务关闭：service idea shutdown

设置自启：chkconfig idea on

关闭自启：chkconfig idea off

## 参考
[Nginx 安装配置](https://www.runoob.com/linux/nginx-install-setup.html)
 
[nginx配置ssl证书实现https访问](https://www.cnblogs.com/tianhei/p/7726505.html)

[nginx基本配置与参数说明](http://www.nginx.cn/76.html)

[springboot nginx 配置](https://www.cnblogs.com/gmq-sh/p/8126361.html)

[linux添加service服务，设置自启](https://www.jianshu.com/p/4b6ef05cef2d)

[env: /etc/init.d/mysqld: 权限不够](https://blog.csdn.net/wu920604/article/details/99716894)
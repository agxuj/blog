<h1 style="font-size: 2.5em;"> Linux Զ�̹���</h1>
 


 
## SSH
`````
usage: ssh [-46AaCfGgKkMNnqsTtVvXxYy] [-B bind_interface]
           [-b bind_address] [-c cipher_spec] [-D [bind_address:]port]
           [-E log_file] [-e escape_char] [-F configfile] [-I pkcs11]
           [-i identity_file] [-J [user@]host[:port]] [-L address]
           [-l login_name] [-m mac_spec] [-O ctl_cmd] [-o option] [-p port]
           [-Q query_option] [-R address] [-S ctl_path] [-W host:port]
           [-w local_tun[:remote_tun]] destination [command]
`````
### 登录
`````
ssh -i *.pem root@119.29.37.63
`````

### 在本地调用远程的 *.sh
`````
ssh -l root 192.168.110.34 "/home/tomcat/startup.sh"  
`````

### 延长ssh在线时间
`````
vim /etc/ssh/sshd_config

修改�?
ClientAliveInterval 0   // 客户端每隔多少秒向服务发送一个心跳数�?
ClientAliveCountMax 3   // 客户端多少秒没有相应，服务器自动断掉连接
改成�?
ClientAliveInterval 30 
ClientAliveCountMax 86400

重启sshd服务
service sshd restart
`````

## scp
`````
usage: scp [-346BCpqrv] [-c cipher] [-F ssh_config] [-i identity_file]
           [-l limit] [-o ssh_option] [-P port] [-S program] source ... target
`````
### 上传本地文件到服务器
`````
scp /path/filename username@servername:/path/
`````
### 从服务器上下载文�?
`````
scp username@servername:/path/filename /var/www/local_dir（本地目录）
`````
### 从服务器下载整个目录
`````
scp -r username@servername:/var/www/remote_dir/（远程目录） /var/www/local_dir（本地目录）
`````
### 上传目录到服务器
`````
scp -r local_dir username@servername:remote_dir
`````


Reference:

[使用SSH命令行传输文件到远程服务器](https://www.cnblogs.com/magicc/p/6490566.html)
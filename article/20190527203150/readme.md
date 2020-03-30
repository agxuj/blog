# MySQL的使用

<script src="../js/index.js"></script>
<div id="content"></div>



## 用户权限管理

### 创建用户并且授权
`````
USE mysql;
CREATE USER ${username} IDENTIFIED BY 'pass123';
GRANT ALL ON ${database}.* TO '${username}'@'%';
FLUSH PRIVILEGES;//每当调整权限后，通常需要执行以下语句刷新权限
`````
查询权限
`````
SHOW GRANTS FOR ${username};
`````

### 删除用户
`````
DROP USER '${username}'@'%';
`````

### 修改用户密码
`````
ALTER USER "root"@"localhost" IDENTIFIED  BY "你的新密码";
`````

## 备份
`````
// 备份整个数据库
mysqldump -u root -h host -p dbname > backdb.sql

//备份数据库中的某个表
mysqldump -u root -h host -p dbname tbname1, tbname2 > backdb.sql

//备份多个数据库
mysqldump -u root -h host -p --databases dbname1, dbname2 > backdb.sql

//备份系统中所有数据库
mysqldump -u root -h host -p --all-databases > backdb.sql

`````

## 还原
``````
mysql -u root -p dbname < backdb.sql
``````

## 参考

[MySQL用户及权限管理 小结](https://www.cnblogs.com/SQL888/p/5748824.html)

[MySQL数据库还原](https://blog.csdn.net/mango_yoo/article/details/90735018)
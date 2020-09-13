<h1 style="font-size: 2.5em;"> MySQL 备份与还原</h1>
 


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
`````
mysql -u <用户名> -p <数据库名> < <备份文件名>

//例子:
mysql -u root -p dbname < backdb.sql
`````
<h1 style="font-size: 2.5em;"> MySQL �û�������Ȩ�޹���</h1>
 

## 用户管理
### 创建用户

`````
create user <用户�?> identified by <密码>;

//例子
create user "faddenyin"identified by "pass123";
`````


### 删除用户
`````
drop user '<用户�?>;

//例子
drop user "faddenyin"@"%";
`````

### 修改密码
`````
alter user <用户�?> identified by <密码>;

//例子
alter user "root"@"localhost" identified by "password123";
`````

### 查看授权

`````
show grants for <用户�?>;

//例子
show grants for "fadden";
`````

## 权限管理

### 授权(GRANT)
`````
grant <权限>, ... <权限> on <对象类型> <对象�?> to <用户>, ... <用户> [WITH GRANT OPTION];

//例子
grant all on `database`.* to 'faddenyin'@'%';

grant all privileges on table S,P,J to User1, User2

//允许将此权限赋给其他用户
grant insert on table S to User1 WITH GRANT OPTION;

//每当调整权限后，通常�?要执行以下语句刷新权�?
flush privileges;
`````
 
| 对象 | 对象类型 | 操作权限 |
| --- | --- | --- |
| 属�?�列 | table | select, insert, update, delete, all privileges (5种权限综合，MySQL 中同 all) |
| 视图 | table | select, insert, update, delete, all privileges (5种权限综合，MySQL 中同 all) |
| 基本�? | table | select, insert, update, delete, all privileges (5种权限综合，MySQL 中同 all) |
| 数据�? | database | create table 建立表的权限，可由DBA授予普�?�用�? |
  

### �?�?(REVOKE)

`````
revoke <权限>, ... <权限> on <对象类型> <对象�?> from <用户>, ... <用户>�?

//例子
REVOKE ALL PRIVILEGES ON TABLE S,P,J FROM User1, User2;

REVOKE SELECT ON TABLE S FROM PUBLIC;

`````
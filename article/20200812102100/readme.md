<h1 style="font-size: 2.5em;"> MySQL �洢����</h1>
 

# 存储过程的创建和调用

存储过程就是具有名字的一段代码，用来完成�?个特定的功能�?

创建的存储过程保存在数据库的数据字典中�??



# �?单使�?

例子�?�?
`````
mysql> delimiter $$
mysql> create procedure in_param(in p_in int)
    -> begin
    -> �?�?select p_in;
    -> �?�?set p_in=2;
    ->    select P_in;
    -> end$$
mysql> delimiter ;
mysql> set @p_in=1;
mysql> call in_param(@p_in);
`````

例子二：
`````
mysql > DELIMITER //  
mysql > CREATE PROCEDURE proc1 --name存储过程�?  
     -> (IN parameter1 INTEGER)   
     -> BEGIN   
     -> DECLARE variable1 CHAR(10);   
     -> IF parameter1 = 17 THEN   
     -> SET variable1 = 'birds';   
     -> ELSE 
     -> SET variable1 = 'beasts';   
     -> END IF;   
     -> INSERT INTO table1 VALUES (variable1);  
     -> END   
     -> //  
mysql > DELIMITER ;
`````



# 基本语法

## 变量

变量�?<span style="color:red">数据类型</span>�? MySQL 的数据类型，�?: int, float, date,varchar(length)

* 变量定义
    `````
    DECLARE variable_name [,variable_name...] datatype [DEFAULT value];

    //例子
    DECLARE l_int int unsigned default 4000000;  
    DECLARE l_numeric number(8,2) default 9.95;  
    DECLARE l_date date default '1999-12-31';  
    DECLARE l_datetime datetime default '1999-12-31 23:59:59';  
    DECLARE l_varchar varchar(255) default 'This will not be padded';
    `````

* 变量赋�??
    `````
    set 变量�? = 表达式�?? [,variable_name = expression ...]

    //例子
    set variable1 = 'birds'; 
    set var=parameter+1;  
    set v=v+1;
    `````

* 用户变量
    `````
    //在MySQL客户端使用用户变�?

    mysql > SELECT 'Hello World' into @x;  
    mysql > SELECT @x;  

    mysql > SET @y='Goodbye Cruel World';  
    mysql > SELECT @y; 

    mysql > SET @z=1+2+3;  
    mysql > SELECT @z; 

    //在存储过程中使用用户变量
    mysql > CREATE PROCEDURE GreetWorld( ) SELECT CONCAT(@greeting,' World');  
    mysql > SET @greeting='Hello';  
    mysql > CALL GreetWorld( );  

    //在存储过程间传�?�全�?范围的用户变�?
    mysql> CREATE PROCEDURE p1()   SET @last_procedure='p1';  
    mysql> CREATE PROCEDURE p2() SELECT CONCAT('Last procedure was ',@last_procedure);  
    mysql> CALL p1();  
    mysql> CALL p2();  
    `````

    注意:

    1. 用户变量名一般以@�?�?
    1. 滥用用户变量会导致程序难以理解及管理 



## 控制语句

* if-then-else 语句

    `````
    if parm=0 then 
        insert into t values(17);  
    end if;  
    `````

* case 语句
    `````
    declare age int;  
    set age=parameter+1;  
    case age  
        when 0 then   
            insert into t values(17);  
        when 1 then   
            insert into t values(18);  
        else   
            insert into t values(19);  
    end case; 
    `````

* while ···· end while
    `````
    declare parameter int;  
    set parameter=0;  
    while parameter<6 do  
        insert into t values(parameter);  
        set parameter=parameter+1;  
    end while; 
    `````    

* repeat ···· end repeat

    它在执行操作后检查结果，�? while 则是执行前进行检查�??

    ````` 
    repeat  
        insert into t values(v);  
        set v=v+1;  
    until v>=5  
    end repeat;  
    ````` 

* loop ···· endloop

    loop 循环不需要初始条件，这点�? while 循环相似，同时和 repeat 循环�?样不�?要结束条�?, leave 语句的意义是离开循环�?
    ````` 
    declare v int;  
    set v=0;  
    LOOP_LABLE:loop  
        insert into t values(v);  
        set v=v+1;  
        if v >=5 then 
            leave LOOP_LABLE;  
        end if;  
    end loop;  
    ````` 

    PS�?
    
    <span style="color:red">LABLES</span> 标号可以用在 begin repeat while 或�?? loop 语句前，语句标号只能在合法的语句前面使用。可以跳出循环，使运行指令达到复合语句的�?后一步�?? 相当�? java 中的 break;

    <span style="color:red">ITERATE</span> 标号通过引用复合语句的标�?,来从新开始复合语句�?�相当于 java 中的 continus;


## 存储过程的参�?

MySQL存储过程的参数用在存储过程的定义，共有三种参数类�?,IN,OUT,INOUT,形式如：

`````
CREATE PROCEDURE 存储过程�?([[IN |OUT |INOUT ] 参数�? 数据类形...])
`````

* IN 输入参数：表示调用�?�向过程传入值（传入值可以是字面量或变量�?
* OUT 输出参数：表示过程向调用者传出�??(可以返回多个�?)（传出�?�只能是变量�?
* INOUT 输入输出参数：既表示调用者向过程传入值，又表示过程向调用者传出�?�（值只能是变量�?


## 声明语句结束�?

可以自定义语句结束符:

    `````
    DELIMITER $$
    �?
    DELIMITER //
    `````
默认情况下，存储过程和默认数据库相关联，如果想指定存储过程创建在某个特定的数据库下，那么在过程名前面加数据库名做前缀�? 在定义过程时，使�? DELIMITER $$ 命令将语句的结束符号从分�? ; 临时改为两个 $$，使得过程体中使用的分号被直接传递到服务器，而不会被客户端（如mysql）解释�??
 

## 注释
MySQL 存储过程可使用两种风格的注释
1. 两个横杆--,该风格一般用于单行注释�??
1. C语言风格, �?般用于多行注释�??

# 存储过程的创建，查询，调用，删除，修�?

## 创建

    `````
    CREATE
        [DEFINER = { user | CURRENT_USER }]
    PROCEDURE sp_name ([proc_parameter[,...]])
        [characteristic ...] routine_body
    
    proc_parameter:
        [ IN | OUT | INOUT ] param_name type
    
    characteristic:
        COMMENT 'string'
    | LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    
    routine_body:
    �?�?Valid SQL routine statement
    
    [begin_label:] BEGIN //存储过程�?始符�?
    �?�?[statement_list]
    �?�?�?�?…�??
    END [end_label] //存储过程结束符号
    `````

## 调用

    用call和你过程名以及一个括号，括号里面根据�?要，加入参数，参数包括输入参数�?�输出参数�?�输入输出参数�?�具体的调用方法可以参看上面的例子�??
    `````
    create procedure demo_in_parameter(IN p_in int) 
    `````  

## 删除
    `````
    drop procedure
    `````

## 修改
    `````
    alter procedure
    `````

## 查询
查看某个数据库下面的存储过程

    `````
    select name from mysql.proc where db='数据库名';

    //或�??

    select routine_name from information_schema.routines where routine_schema='数据库名';

    //或�??

    show procedure status where db='数据库名';
    `````

查看存储过程的详�?

    `````
    show create procedure 数据�?.存储过程�?;
    `````

# 参�??

[MySQL 存储过程](https://www.runoob.com/w3cnote/mysql-stored-procedure.html)
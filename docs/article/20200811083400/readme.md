<h1 style="font-size: 2.5em;"> Oracle plsql</h1>
 

# 什么是PL/SQL？

PL/SQL Developer是一个**集成开发环境**，专门**开发面向Oracle数据库的应用**。PL/SQL也是一种**程序语言**，叫做**过程化SQL语言**（Procedural Language/SQL）。PL/SQL是Oracle数据库对SQL语句的扩展。在普通SQL语句的使用上增加了编程语言的特点，所以PL/SQL把数据操作和查询语句组织在PL/SQL代码的过程性单元中，通过逻辑判断、循环等操作实现复杂的功能或者计算。

# 简单使用例子

如下所示为一段完整的PL/SQL块：
`````
declare
    v_id integer;
    v_name varchar(20);
    cursor c_emp is select * from employee where emp_id=3;

begin
    open c_emp;　//打开游标

    loop
        fetch c_emp into v_id,v_name;　
            exit when c_emp%notfound;
    end loop;
    close c_emp;

    dbms_output.PUT_LINE(v_name);

exception
    when no_data_found then
        dbms_output.PUT_LINE('没有数据');

end;
`````

嵌套用法：
`````

BEGIN
<<outer>>
DECLARE
    birthdate DATE;
    BEGIN
        DECLARE 
            birthdate DATE;
            BEGIN
              --DBMS_OUTPUT.PUT_LINE(birthdate);
              outer.birthdate:=TO_DATE('03-AUG-1970','DD-MON-YYYY');
            END;
    END;
END;

`````


# 基本语法

## 变量声明与赋值
所有的数据类型跟Oracle数据库里的字段类型是一一对应，大体分为数字型、布尔型、字符型和日期型。

在PL/SQL中声明变量与其他语言不太一样，它采用从右往左的方式声明，比如声明一个number类型的变量v_id，那其形式应为：
`````
v_id number;
`````
如果给上面的v_id变量赋值，不能用“=”，应该用“:=”,即形式为：
`````
v_id :=5;
`````

这里简单介绍两种常用数据类型：number、varchar2。
* number

    用来存储整数和浮点数。范围为1e130～10e125，其使用语法为：
    number[(precision, scale)]
    其中(precision, scale)是可选的，precision表示所有数字的个数，scale表示小数点右边数字的个数。

* varchar2

    用来存储变长的字符串，其使用语法为：
    varchar2[(size)]
    其中size为可选，表示该字符串所能存储的最大长度。

## 控制结构

PL/SQL程序段中有三种程序结构：条件结构、循环结构和顺序结构。

### 条件结构
`````
if condition then
    statement1
else
    statement2
end if;
`````

### 循环结构
`````
loop … end loop;

while condition loop … end loop;

for variable in low_bound … upper_bound loop … end loop;
`````

### 顺序结构

    goto的使用。

# 匿名块、过程与函数

PL/SQL程序都是以块（block）为基本单位，分三种 匿名块、存储过程、函数。其中匿名块是没有名字的，存储过程没有返回值，函数有名字有返回值。

## 匿名块

匿名块分三部分：声明部分（用declare开头）、执行部分（以 begin开头）和异常处理部分（以exception开头）。

其中执行部分是必须的，其他两个部分可选。无论PL/SQL程序段的代码量有多大，其基本结构就是由这三部分组成。

模板如下：
`````
declare
　　/* 声明区(可选)：定义类型和变量、声明变量、声明函数、游标 */

begin
    /* 执行区(必须的)：执行pl/sql语句或者sql语句 */

exception
    /* 异常处理区(可选)：处理错误的 */

end;
`````

## 过程

`````
Create or replace procedure procname(参数列表) as PL/SQL语句块
`````
举个例子：

问题：假设有一张表t1，有f1和f2两个字段，f1为number类型，f2为varchar2类型，要往t1里写两条记录，内容自定。

`````
Create or replace procedure test_procedure as

    V_f11 number :=1; /*声明变量并赋初值*/
    V_f12 number :=2;
    V_f21 varchar2(20) :='first';
    V_f22 varchar2(20) :='second';

Begin
    Insert into t1 values (V_f11, V_f21);
    Insert into t1 values (V_f12, V_f22);

End test_procedure; /*test_procedure可以省略*/
`````

## 函数

`````
Create or replace function funcname(参数列表) return 返回值 as PL/SQL语句块
`````
 

# 游标

游标的定义为：用游标来指代一个DML SQL操作返回的结果集。即当一个对数据库的查询操作返回一组结果集时，用游标来标注这组结果集，以后通过对游标的操作来获取结果集中的数据信息。这里特别提出游标的概念，是因为它在PL/SQL的编程中非常的重要。定义游标的语法结构如下：

`````
cursor cursor_name is SQL语句;
`````

在本文第一段代码中有一句话如下：
`````
cursor c_emp is select * from employee where emp_id=3;
`````

其含义是定义一个游标c_emp，代表employee表中所有emp_id字段为3的结果集。当需要操作该结果集时，必须完成三步：打开游标、使用fetch语句将游标里的数据取出、关闭游标。

游标用来处理从数据库中检索的多行记录（使用SELECT语句）。利用游标，程序可以逐个地处理和遍历一次检索返回的整个记录集。

为了处理SQL语句，Oracle将在内存中分配一个区域，这就是上下文区。这个区包含了已经处理完的行数、指向被分析语句的指针，整个区是查询语句返回的数据行集。游标就是指向上下文区句柄或指针。

## 显式游标

显示游标被用于处理返回多行数据的SELECT 语句，游标名通过CURSOR….IS 语句显示地赋给SELECT 语句。

在PL/SQL中处理显示游标所必需的四个步骤：

1. 声明游标
    `````
    CURSOR cursor_name IS select_statement;
    `````
1. 为查询打开游标
    `````
    OPEN cursor_name;
    `````
1. 取得结果放入PL/SQL变量中
    `````
    FETCH cursor_name INTO list_of_variables;
    FETCH cursor_name INTO PL/SQL_record;
    `````
1. 关闭游标
    `````
    CLOSE cursor_name;
    `````

<span style="color:red">注意</span>：在声明游标时，select_statement不能包含INTO子句。当使用显示游标时，INTO子句是FETCH语句的一部分。

## 隐式游标
所有的隐式游标都被假设为只返回一条记录。

使用隐式游标时，用户无需进行声明、打开及关闭。PL/SQL隐含地打开、处理，然后关掉游标。

例如：

`````
SELECT studentNo,studentName
INTO curStudentNo,curStudentName
FROM StudentRecord
WHERE name=’gg’;
`````

上述游标自动打开，并把相关值赋给对应变量，然后关闭。执行完后，PL/SQL变量curStudentNo,curStudentName中已经有了值。

# 参考
[百度百科 - plsql](https://baike.baidu.com/item/plsql/9042661?fr=aladdin)

[初识PLSQL](https://blog.csdn.net/qq_40709468/article/details/81122028)
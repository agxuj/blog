<h1 style="font-size: 2.5em;"> MySQL ���ݶ�������DDL</h1>
 



## 创建�?
`````
CREATE TABLE <表名>�?
    <列名> <数据类型> [列级完整性约束条件],
    [表级完整性约束条件]
);
`````

**常见列级约束条件**
1. not null
1. null
1. default
1. unique
1. check

**主键**
primary key

**外键**
foreign key
`````
foreign key(列名) references 表名(列名)
`````

**自增�?**
auto_increament

## 修改表和删除�?
1. 添加�?
    `````
    ALTER TABLE <表名> ADD <新列�?> <数据类型> [完整性约束条件];
    `````

1. 删除�?
    `````
    ALTER TABLE <表名> DROP <完整性约束名>;
    `````

1. 修改列属�?
    `````
    ALTER TABLE <表名> MODIFY <列名> <数据类型> [完整性约束条件];
    `````

1. 修改列名
    `````
    ALTER TABLE <表名> CHANGE <列名> <新列�?> <数据类型> [完整性约束条件]; --- MySQL
    exec sp_rename '<表名>.<列名>�?,�?<表名>.<新列�?>' --- sql server
    ALTER TABLE <表名> RENAME COLUMN <列名> TO <新列�?> --- oracle
    `````

1. 删除�?
    `````
    DROP TABLE <表名>
    `````

## 创建和删除索�?
1. 建立索引
    `````
    CREATE UNIQUE INDEX <索引�?> ON <表名> (<列名> <次序>, ... <列名><次序>);

    //例子:
    CREATE UNIQUE INDEX J-JNO ON J(Jno);
    CREATE UNIQUE INDEX SPJ-NO ON SPJ(Sno ASC, Pno DESC,JNO ASC)
    `````

1. 删除索引
    `````
    DROP INDEX <索引�?>
    `````

## 视图的创建和删除
1. 创建视图
    `````
    CREATE VIEW <视图�?> AS SELECT <查询子句> WITH CHECK OPTION;
    `````

1. 删除视图
    `````
    DROP VIEW <视图�?>;
    `````

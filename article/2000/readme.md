# 20200314101200

<script src="../js/index.js"></script>
<div id="content"></div>

## 备份

创建要升级的服务器上所有数据库的备份。安全第一！

## 升级MySQL

将MySQL服务器升级到v5.5.3+，或请服务器管理员为您执行此操作。

## 修改数据库、表和列

将数据库、表和列的字符集和排序规则属性更改为使用utf8mb4而不是utf8。


`````
1. 对于每个数据库：

ALTER DATABASE database_name CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

2. 对于每个表：

ALTER TABLE table_name CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

3. 对于每个列：

ALTER TABLE table_name CHANGE column_name column_name VARCHAR(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

（不要盲目复制粘贴这个！确切的语句取决于列类型、最大长度和其他属性。上面这一行只是“VARCHAR”列的一个例子。）
`````

由于utf8mb4与utf8完全向后兼容，因此不会发生乱码或其他形式的数据丢失。（但你有后援，对吧？）

## 检查列和索引键的最大长度

这可能是整个升级过程中最乏味的部分。

当从utf8转换为utf8mb4时，列或索引键的最大长度以字节为单位不变。因此，就字符而言，它更小，因为一个字符的最大长度现在是四个字节，而不是三个字节。

例如，tinytext列最多可以容纳255个字节，这与85个三字节或63个四字节字符相关。假设有一个tinytext列使用utf8，但必须能够包含63个以上的字符。考虑到这一要求，您不能将此列转换为utf8mb4，除非您还将数据类型更改为较长的类型（如文本），因为如果您尝试用四字节字符填充它，则只能输入63个字符，但不能输入更多字符。

索引键也是如此。InnoDB存储引擎的最大索引长度为767字节，因此对于utf8或utf8mb4列，您可以分别索引最大255或191个字符。如果当前有索引长度超过191个字符的utf8列，则在使用utf8mb4时需要索引较少的字符。（因此，我不得不将一些索引的VARCHAR（255）列更改为VARCHAR（191）

MySQL 5.5参考手册的第10.1.11节提供了有关此方面的更多信息。

## 修改连接、客户端和服务器字符集

在应用程序代码中，将连接字符集设置为 utf8mb4。这可以通过简单地用集合名称 utf8mb4 替换集合名称 utf8 的任何变体来实现。如果旧的 SET NAMES 语句指定了排序规则，请确保也对其进行更改，例如 SET NAMES utf8 COLLATE utf8_unicode_ci 变为 SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci。

确保同时设置客户机和服务器字符集。我的MySQL配置文件（/etc/my.cnf）中有以下内容：

`````
[client]
default-character-set = utf8mb4

[mysql]
default-character-set = utf8mb4

[mysqld]
character-set-client-handshake = FALSE
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
`````

您可以轻松确认这些设置是否正常工作：

`````
mysql> SHOW VARIABLES WHERE Variable_name LIKE 'character\_set\_%' OR Variable_name LIKE 'collation%';
+--------------------------+--------------------+
| Variable_name            | Value              |
+--------------------------+--------------------+
| character_set_client     | utf8mb4            |
| character_set_connection | utf8mb4            |
| character_set_database   | utf8mb4            |
| character_set_filesystem | binary             |
| character_set_results    | utf8mb4            |
| character_set_server     | utf8mb4            |
| character_set_system     | utf8               |
| collation_connection     | utf8mb4_unicode_ci |
| collation_database       | utf8mb4_unicode_ci |
| collation_server         | utf8mb4_unicode_ci |
+--------------------------+--------------------+
10 rows in set (0.00 sec)
`````

如您所见，所有相关的选项都设置为utf8mb4，除了character-set-u文件系统应该是二进制的，除非您所在的文件系统支持文件名中的多字节UTF-8编码字符，并且character-set-u系统始终是utf8且不能重写。

注意：默认字符集和排序规则也可以在其他一些级别配置。

## 修复和优化所有表
升级MySQL服务器并进行上述必要更改后，请确保修复和优化所有数据库和表。升级后我没有立即执行此操作（我认为这没有必要，因为乍一看一切似乎都很好），并且遇到了一些奇怪的错误，更新语句没有任何效果，即使没有抛出错误。

可以对要修复和优化的每个表运行以下MySQL查询：

**注意：默认字符集和排序规则也可以在其他一些级别配置。**

`````
# For each table
REPAIR TABLE table_name;
OPTIMIZE TABLE table_name;
`````

幸运的是，可以使用命令行mysqlcheck实用程序一次性轻松完成此操作：

`````
$ mysqlcheck -u root -p --auto-repair --optimize --all-databases
`````

这将提示输入根用户的密码，然后将修复和优化所有数据库中的所有表。

## 总结

不要在MySQL中使用utf8-请始终使用utf8mb4。更新数据库和代码可能需要一些时间，但这绝对值得。为什么要任意限制可以在数据库中使用的符号集？为什么每次用户输入一个星体符号作为评论或消息的一部分或存储在数据库中的任何内容时都会丢失数据？没有理由不争取在任何地方都完全支持Unicode。做正确的事情，使用utf8mb4。


# 参考
[How to support full Unicode in MySQL databases](https://mathiasbynens.be/notes/mysql-utf8mb4#utf8-to-utf8mb4)
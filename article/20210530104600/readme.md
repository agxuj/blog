<h1 style="font-size: 2.5em;"> Git log</h1>
 

## 常见用法

```````
git log 
--pretty=format:"%an<divider>%H<divider>%s<divider>%cd" 
--date=format:"%Y-%m-%d %H:%M:%S"
--after "date +%Y-%m-%d --date '${days} days ago'" 
--no-merges >> $tool_pwd/APP_ReleaseNote.txt

```````

## 参数说明

-p	按补丁格式显示每个更新之间的差异
--pretty 内容格式化
--date 日期格式化
--stat	显示每次更新的文件修改统计信息
--shortstat	只显示 --stat 中最后的行数修改添加移除统计
--name-only	仅显示已修改的文件清单
--name-status	显示新增、修改、删除的文件清单
--abbrev-commit	仅显示 SHA-1 的前9个字符，而非所有的 40 个字符
--relative-date	使用较短的相对时间显示（比如，“2 days ago”）
--graph	显示 ASCII 图形表示的分支合并历史
--since/after 显示提交比指定日期更新
--until/before 显示早于特定日期的提交
	>git log --since=2.weeks
	>git log --after="2020-01-01"
	>git log --since ==2019-12-01 --until=2020-01-01
--author 作者
--committer 提交者
--grep 筛选关键字
--grep-reflog 正则筛选
	>git log --grep="xxx" -i  
	-i 忽略大小写
	-E 扩展正则表达式
	-F 将限制模式视为固定的字符串
	-P 将限制模式视为与Perl兼容的正则表达式

-20 最近二十次提交记录，无则表示导出所有记录到log.txt

## 格式化格式参数

%H	提交对象（commit）的完整哈希字串
%h	提交对象的简短哈希字串
%T	树对象（tree）的完整哈希字串
%t	树对象的简短哈希字串
%P	父对象（parent）的完整哈希字串
%p	父对象的简短哈希字串
%an	作者（author）的名字
%ae	作者的电子邮件地址
%ad	作者修订日期（可以用 -date= 选项定制格式）
%ar	作者修订日期，按多久以前的方式显示
%cn	提交者(committer)的名字
%ce	提交者的电子邮件地址
%cd	提交日期
%cr	提交日期，按多久以前的方式显示
%s	提交说明

## date格式说明

### 小写格式

%a 星期（缩写）：Sat
%b 月份（缩写）：Apri
%c 格式化输出日期时间 月/日/年 时:分:秒：04/25/20 18:21:01
%d 日期：25
%j 一年的第几天：116
%m 月份数字：04
%p 上下午：AM/PM
%w 星期几（0-6）：6
%x 格式化输出短日期：04/25/20
%y 年份：20
%z 时区：+0800 

### 大写格式

%A 星期（全写）：Saturday
%B 月份（全写）：April
%H 24小时制：18
%I 12小时制：06
%M 分钟：21
%S 秒：01
%U 一年的第几周（星期日作为每周的第一天）：16
%W 一年的第几周（星期一作为每周的第一天）：16
%X 格式化输出短时间：18:21:01
%Y 年份：2020
%Z 时区：+0800 

## 其他

修改git config

git config log.date format:'%Y-%m-%d %H:%M:%S'
git config --global log.date format:'%Y-%m-%d %H:%M:%S'


## 参考
[git log 使用及格式化参数详解](https://blog.csdn.net/u011106915/article/details/105836289)

[Git log修改时间格式](https://samlin.blog.csdn.net/article/details/100535989)

[git log --pretty=format:" "](https://www.cnblogs.com/ayseeing/p/5029245.html)
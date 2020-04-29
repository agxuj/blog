


git是一个版本管理器，
## 1.版本管理器涉及到版本的回滚恢复等操作
### 回滚恢复相关命令
````
reset
revert
checkout
````
### git还有一个强悍的功能，对过往的commit进行删除或者修改
`````
rebase
`````

## 2.版本管理器必然涉及到版本的分支

### git创建一个分支的命令是：
`````
git branch ${分支名}
//or
git checkout -b z3
`````
### 删除分支
`````
git branch -d ${分支名}
`````

### 切换分支
`````
git checkout z3
`````
### 查看当前分支
`````
git branch
`````
### 分支间必然涉及到合并
`````
merge

//合并分支
git merge ${分支名}
`````

### 分支的几个概念
head master orign(起源)

[head/master/分支 间的关系可以看 --- 理解git中的head和master](https://blog.csdn.net/bdss58/article/details/40537859)

[git中的origin -- 只想服务端仓库的标志](https://blog.csdn.net/niexia_/article/details/79422859)

#### detached head
如果让HEAD文件指向一个commit id而不是具体分支，那就变成了detached HEAD。 

1. git checkout master可以回到master分支，但是当前工作区的内容就没了



## 3.简单的添加，提交，推送到服务端
`````
add
commit
push
`````

## 4.从服务端拉去代码
`````
fetch
pull
`````
## 5.其他符号解释
`````
git checkout 1aea8d9^
laea8d9是最近的一次commit id，^指的是之前一次
`````

## 6.Git Help
`````
These are common Git commands used in various situations:

start a working area (see also: git help tutorial)
   clone      Clone a repository into a new directory
   init       Create an empty Git repository or reinitialize an existing one

work on the current change (see also: git help everyday)
   add        Add file contents to the index
   mv         Move or rename a file, a directory, or a symlink
   reset      Reset current HEAD to the specified state
   rm         Remove files from the working tree and from the index

examine the history and state (see also: git help revisions)检查历史和状态
   bisect     Use binary search to find the commit that introduced a bug Use binary search to find the commit that introduced a bug
   grep       Print lines matching a pattern
   log        Show commit logs
   show       Show various types of objects
   status     Show the working tree status

grow, mark and tweak your common history
   branch     List, create, or delete branches
   checkout    Switch branches or restore working tree files
   commit     Record changes to the repository
   diff       Show changes between commits, commit and working tree, etc
   merge      Join two or more development histories together
   rebase     Reapply commits on top of another base tip
   tag        Create, list, delete or verify a tag object signed with GPG

collaborate (see also: git help workflows)
   fetch      Download objects and refs from another repository
   pull       Fetch from and integrate with another repository or a local branch
   push       Update remote refs along with associated objects
`````


## 5.Refernece 
[Git Revert 用法](https://www.cnblogs.com/0616--ataozhijia/p/3709917.html)

[Git 中的 ~ 和 ^](https://www.2cto.com/kf/201807/758347.html)
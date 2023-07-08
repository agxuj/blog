git是一个版本管理器.

## 简单的添加，提交，推送到服务端
 
### add
### commit
### push


## 从服务端拉去代码
 
### clone
### fetch
### pull


## 回滚恢复等操作

### reset
撤销<span style="color:red">git修改</span>和<span style="color:red">commit</span>. 
注意：新建的，没有git add的文件不会被撤销
````
git reset --soft HEAD~1 	//撤回最近一次的commit(撤销commit，不撤销git add)
git reset --mixed HEAD~1 	//撤回最近一次的commit(撤销commit，撤销git add)
git reset --hard HEAD~1 	//撤回最近一次的commit(撤销commit，撤销git add,还原改动的代码)
git reset --hard			   //撤销git修改
````

### revert 
撤销 某次操作，此次操作之前和之后的commit和history都会保留，并且把这次撤销<span style="color:red">作为一次最新的commit提交.</span>
也即提交一个新的版本，将需要revert的版本的内容再反向修改回去。
该操作有若干步骤，注意使用: git revert --continue
`````
git revert HEAD						   //撤销前一次 commit
git revert HEAD^					      //撤销前前一次 commit
git revert commit (比如：fa042ce)	//撤销指定的版本，撤销也会作为一次提交进行保存。
`````

### clean
清除未被 add 或被 commit 的本地修改 (clean 影响没有被 track 过的文件)
`````
git clean -n		   // 是一次 clean 的演习, 告诉你哪些文件会被删除，不会真的删除
git clean -f		   // 删除当前目录下所有没有 track 过的文件, 不会删除 .gitignore 文件里面指定的文件夹和文件, 不管这些文件有没有被 track 过.
git clean -f <path>	// 删除指定路径下的没有被 track 过的文件
git clean -df		   // 删除当前目录下没有被 track 过的文件和文件夹
git clean -xf		   // 删除当前目录下所有没有 track 过的文件, 不管是否是 .gitignore 文件里面指定的文件夹和文件
git clean 			   // 对于刚编译过的项目也非常有用,如, 他能轻易删除掉编译后生成的 .o 和 .exe 等文件. 这个在打包要发布一个 release 的时候非常有用
`````

### checkout

放弃掉所有还没有加入到缓存区（就是 git add 命令）的修改：内容修改与整个文件删除。但是此命令不会删除掉刚新建的文件。因为刚新建的文件还没已有加入到 git 的管理系统中。
`````
git checkout -- filepathname     // 放弃单个文件修改,注意不要忘记中间的"--",不写就成了检出分支了!
git checkout .                   // 放弃所有的文件修改
`````

### restore

`````
git restore				//在工作空间但是不在暂存区的文件撤销更改(内容恢复到没修改之前的状态)
git restore --staged	//将暂存区的文件从暂存区撤出，但不会更改文件的内容。
`````

//todo

## 分支

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

## 其他
 
### rebase

### log

## Git Help

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

## Refernece 
[Git Revert 用法](https://www.cnblogs.com/0616--ataozhijia/p/3709917.html)

[git revert 用法](https://www.cnblogs.com/0616--ataozhijia/p/3709917.html)

[git clean 的用法详解_一定要学_一定要慎用](https://blog.csdn.net/weixin_44137575/article/details/108142088)

[Git语法之Checkout使用](https://www.jianshu.com/p/37f3a7e4a193)

[git restore指令和git restore --staged 的使用](https://blog.csdn.net/qq_38158479/article/details/106972138)
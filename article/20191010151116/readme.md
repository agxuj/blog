<h1 style="font-size: 2.5em;"> Git ��ʹ��</h1>
 

git是一个版本管理器.

## �?单的添加，提交，推�?�到服务�?
 
### add
### commit
### push


## 从服务端拉去代码
 
### clone
### fetch
### pull


## 回滚恢复等操�?

### reset
撤销<span style="color:red">git修改</span>�?<span style="color:red">commit</span>. 
注意：新建的，没有git add的文件不会被撤销
````
git reset --soft HEAD~1 	//撤回�?近一次的commit(撤销commit，不撤销git add)
git reset --mixed HEAD~1 	//撤回�?近一次的commit(撤销commit，撤�?git add)
git reset --hard HEAD~1 	//撤回�?近一次的commit(撤销commit，撤�?git add,还原改动的代�?)
git reset --hard			   //撤销git修改
````

### revert 
撤销 某次操作，此次操作之前和之后的commit和history都会保留，并且把这次撤销<span style="color:red">作为�?次最新的commit提交.</span>
也即提交�?个新的版本，将需要revert的版本的内容再反向修改回去�??
该操作有若干步骤，注意使�?: git revert --continue
`````
git revert HEAD						   //撤销前一�? commit
git revert HEAD^					      //撤销前前�?�? commit
git revert commit (比如：fa042ce)	//撤销指定的版本，撤销也会作为�?次提交进行保存�??
`````

### clean
清除未被 add 或被 commit 的本地修�? (clean 影响没有�? track 过的文件)
`````
git clean -n		   // 是一�? clean 的演�?, 告诉你哪些文件会被删除，不会真的删除
git clean -f		   // 删除当前目录下所有没�? track 过的文件, 不会删除 .gitignore 文件里面指定的文件夹和文�?, 不管这些文件有没有被 track �?.
git clean -f <path>	// 删除指定路径下的没有�? track 过的文件
git clean -df		   // 删除当前目录下没有被 track 过的文件和文件夹
git clean -xf		   // 删除当前目录下所有没�? track 过的文件, 不管是否�? .gitignore 文件里面指定的文件夹和文�?
git clean 			   // 对于刚编译过的项目也非常有用,�?, 他能轻易删除掉编译后生成�? .o �? .exe 等文�?. 这个在打包要发布�?�? release 的时候非常有�?
`````

### checkout

放弃掉所有还没有加入到缓存区（就�? git add 命令）的修改：内容修改与整个文件删除。但是此命令不会删除掉刚新建的文件�?�因为刚新建的文件还没已有加入到 git 的管理系统中�?
`````
git checkout -- filepathname     // 放弃单个文件修改,注意不要忘记中间�?"--",不写就成了检出分支了!
git checkout .                   // 放弃�?有的文件修改
`````

### restore

`````
git restore				//在工作空间但是不在暂存区的文件撤�?更改(内容恢复到没修改之前的状�?)
git restore --staged	//将暂存区的文件从暂存区撤出，但不会更改文件的内容�?
`````

//todo

## 分支

### git创建�?个分支的命令是：
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

examine the history and state (see also: git help revisions)�?查历史和状�??
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

[git clean 的用法详解_�?定要学_�?定要慎用](https://blog.csdn.net/weixin_44137575/article/details/108142088)

[Git语法之Checkout使用](https://www.jianshu.com/p/37f3a7e4a193)

[git restore指令和git restore --staged 的使用](https://blog.csdn.net/qq_38158479/article/details/106972138)
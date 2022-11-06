<h1 style="font-size: 2.5em;"> Git һЩ����</h1>
 

## 关于工作�?,暂存�?,版本库的资料 

[Git 工作区�?�暂存区和版本库](https://www.runoob.com/git/git-workspace-index-repo.html)

[git restore指令和git restore --staged 的使用](https://blog.csdn.net/qq_38158479/article/details/106972138)

[工作区�?�暂存区、版本库、远程仓库](https://www.cnblogs.com/qdhxhz/p/9757390.html)

[关于Gerrit管理使用git push出现 prohibited by Gerrit: ref update access denied](https://blog.csdn.net/HZZOU/article/details/95475830)

## 分支的几个概�?

有概念阅�? -- [head/master/分支 间的关系可以�? --- 理解git中的head和master](https://blog.csdn.net/bdss58/article/details/40537859)

### master
是分支名

### head
是指针，指向具体某个分支，如果让HEAD文件指向�?个commit id而不是具体分支，那就变成了detached HEAD�? 

git checkout master可以回到master分支，但是当前工作区的内容就没了.

### orign
[git中的origin -- 只想服务端仓库的标志](https://blog.csdn.net/niexia_/article/details/79422859)
使用例子: git push orign dev:refs/for/dev

### remote
git命令，返回远程仓库的分支名�??

## 其他符号解释

[ ~ �? ^](https://www.2cto.com/kf/201807/758347.html)

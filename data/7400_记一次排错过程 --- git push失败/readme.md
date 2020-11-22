# 提交遇到问题

Enumerating objects: 27, done.
Counting objects: 100% (27/27), done.
Delta compression using up to 4 threads
Compressing objects: 100% (10/10), done.
Writing objects: 100% (14/14), 1.41 KiB | 480.00 KiB/s, done.
Total 14 (delta 8), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (8/8)
remote: Branch refs/heads/master:
remote: You are not allowed to perform this operation.
remote: To push into this reference you need 'Push' rights.
remote: User: hot
remote: Please read the documentation and contact an administrator
remote: if you feel the configuration is incorrect
remote: Processing changes: refs: 1, done
To ssh://xxx.xxx.xx.xx:2560/helloworld
 ! [remote rejected] master -> master (prohibited by Gerrit: ref update access denied)
error: failed to push some refs to 'ssh://xxx.xxx.xx.xx:2560/helloworld'
 
# 解决方式
需要执行 git config remote.origin.push refs/heads/*:refs/for/*
冒号左边的星号为本地仓库的分支名称，for后面的星号为远程仓库的分支名称
参考：[关于Gerrit管理使用git push出现 prohibited by Gerrit: ref update access denied](https://blog.csdn.net/HZZOU/article/details/95475830)

# 为什么如此？
根据这篇[文章](https://blog.csdn.net/z2066411585/article/details/84580185),发现[remote "origin"]只有获取代码的权限，没有push代码的权限，导致push 报错,服务器的代码主要是由Geriit+Git+Repo 构成版本及审核机制,重新回忆下拉取代码的流程，发现过程中少了一步建立Gerrit 审核代码的机制 ,执行之后，可以正常提交。

# 我之前也没有执行相关命令，也可以玩起来，为何现在却又要加这一句？在github上拉下来也没有 "+refs/heads/*:refs/remotes/origin/*",到底什么原因呢？

我认为原因可能是Geriit+Git+Repo这一组合。

# 什么是Geriit？

百度百科上说：Gerrit，一种免费、开放源代码的代码审查软件，使用网页界面。利用网页浏览器，同一个团队的软件程序员，可以相互审阅彼此修改后的程序代码，决定是否能够提交，退回或者继续修改。它使用Git作为底层版本控制系统。它分支自Rietveld，作者为Google公司的Shawn Pearce，原先是为了管理Android计划而产生。
**这是一个代码审查软件**




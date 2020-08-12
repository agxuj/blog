
 

## 1.运行rebase命令
 
    git rebase -i origin/master
    
    git rebase 187f869c9d54c9297d6b0b1b4ff47d2ec781a55e^ -i origin/master
    
    这里的  －i ＝ --interactive 


## 2.操作

    pick : git会应用这个补丁，以同样的提交信息（commit message）保存提交。

    squash : git会把这个提交和前一个提交合并成为一个新的提交。
    
        这会再次调用编辑器，你在里面合并这两个提交的提交信息。

    edit : 暂时没有，先不写了

    丢弃提交 : 如果把一行删除而不是指定'pick'、'squash'和‘edit''中的任何一个，git会从历史中移除该提交。
    

## 3.提交

    git push origin HEAD:master --force
    

## 4.继续 rebase

    git rebase --continue


## 5.终止

    git rebase -i --abort    
    
<br/>

## Reference:

[Git push master fatal: You are not currently on a branch](https://stackoverflow.com/questions/30471557/git-push-master-fatal-you-are-not-currently-on-a-branch)

[在git 中修改之前的提交内容](https://blog.csdn.net/wangbole/article/details/8552808)

[git rebase简介(高级篇)](https://blog.csdn.net/hudashi/article/details/7664651)


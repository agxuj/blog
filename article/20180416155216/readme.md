<h1 style="font-size: 2.5em;"> Git �����</h1>
 


 
Server，Local，缓存区

#### 恢复到上�?个版�?
``````````
git reset –hard HEAD^ 
git checkout -- <file>
``````````
[Reference](https://blog.csdn.net/zch501157081/article/details/51939854)

<br/>

#### Git pull 强制覆盖本地文件

`````````
git fetch --all  
git reset --hard origin/master 
git pull
`````````
[Reference](https://blog.csdn.net/baple/article/details/49872765)

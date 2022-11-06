<h1 style="font-size: 2.5em;"> Git �����˺�����</h1>
 

## 配置全局�? name �? email
`````
git config --global user.name  "username"  
git config --global user.email "email"
````` 

## 配置项目内的 name �? email
`````
git config user.name "username"
git config user.email "email"
`````

## 修改已配置的信息
`````
git config --global --replace-all user.name "username"
git config --global --replace-all user.email "email"
`````

## .gitconfig文件位置
`````
全局:   ~/.gitconfig
项目�?: /.git/config
`````

## 删除 GitHub 密钥
密钥�?处位�?(mac) : 应用 --> 钥匙串访�? --> github.com

## 在一台设备内使用多个 Github 账户�? SSH



## 参�??

[Git配置用户名和密码](https://blog.csdn.et/weixin_41287260/article/details/90111027)
[mac 下自动记录git密码，如何删除？](https://www.updateweb.cn/zwfec/item-80.html)
[Git 当前项目设置 用户名�?�邮箱](https://blog.csdn.net/pintu274111451/article/details/79767970)

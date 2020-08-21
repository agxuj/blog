<h1 style="font-size: 2.5em;"> Git 本地账号设置</h1>
 

## 配置全局的 name 和 email
`````
git config --global user.name  "username"  
git config --global user.email  "email"
`````

## 配置项目内的 name 和 email
`````
git config user.name "username"
git config user.email "email"
`````

## 修改已配置的信息
`````
git config --replace-all user.name "username"
git config --replace-all user.email "email"
`````

## 删除 GitHub 密钥
密钥所处位置(mac) : 应用 --> 钥匙串访问 --> github.com

## 在一台设备内使用多个 Github 账户及 SSH





## 参考

[Git配置用户名和密码](https://blog.csdn.net/weixin_41287260/article/details/90111027)

[mac 下自动记录git密码，如何删除？](https://www.updateweb.cn/zwfec/item-80.html)

[Git 当前项目设置 用户名、邮箱](https://blog.csdn.net/pintu274111451/article/details/79767970)

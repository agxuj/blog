# WEB前端 gulp 自定义插件
 



## 新建doSomething.js
`````
'use strict';
var through = require('through2'),
module.exports = function(opt) {
 
    function doSomething(file, encoding, callback) {
 
        if (file.isNull()) {
            return callback(null, file);
        }
 
        if (file.isStream()) {
            return callback(createError(file, 'Streaming not supported'));
        }

　　　　　//do something
        file.contents = new Buffer("this is my stream");//这里我们只是简单的改变了内容，实际上你可以你的自定义逻辑部分就在这里执行
 
        callback(null, file);
    }
 
    return through.obj(doSomething);
};

`````

## 使用示例
`````
var doSomething = require('./doSomething.js');//假设模块存放路径在当前文件夹下
gulp.src("./app.js")
　　.pipe(uglify())//调用其它插件
　　.pipe(doSomething())//调用我们刚刚定义的插件
　　.pipe(gulp.dest('./myStream.js'));//持久化到磁盘
`````

## 参考

[gulp如何自定义插件](https://www.cnblogs.com/Raoh/p/4169426.html)

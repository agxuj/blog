# gulp 简单使用
 



## 代码
`````
var gulp = require('gulp');
var babel = require('gulp-babel');//把es6语法解析成es5
var concat = require('gulp-concat');//合并
var uglify = require('gulp-uglify');//压缩
var rev = require('gulp-rev');//对文件名加MD5后缀
var revCollector = require('gulp-rev-collector');//替换路径
var htmlmin = require('gulp-htmlmin'); //压缩html里面的js，css，去除空格
var del = require('del');//删除文件
var connect = require('gulp-connect');
var open = require('open');


let host = "192.168.101.23";
let port = 8088;

var app = {
  srcPath: 'src/',
  devPath: 'build/',  //打包后的原始数据存放处
  prdPath: 'dist/'    //打包后的压缩数据存放处
};


gulp.task('js', function () {
  return gulp.src(app.srcPath + '/**/*.js')
    .pipe(babel())
    //.pipe(uglify())
    .pipe(rev())
    .pipe(gulp.dest(app.devPath))
    .pipe(rev.manifest('js-map.json'))
    .pipe(gulp.dest(app.devPath));
});


var autoprefix = require('gulp-autoprefixer');//兼容处理
var minifyCss = require('gulp-minify-css');//压缩
gulp.task('style', function () {
  return gulp.src(app.srcPath + '/**/*.css')
    .pipe(autoprefix({
      //browsers: ['last 2 versions'],
      cascade: false
    }))
    .pipe(minifyCss())
    .pipe(rev())
    .pipe(gulp.dest(app.devPath))
    .pipe(rev.manifest('css-map.json'))
    .pipe(gulp.dest(app.devPath));
});

gulp.task('image', function () {
  return gulp.src([
    app.srcPath + '/**/*.jpg',
    app.srcPath + '/**/*.jpeg',
    app.srcPath + '/**/*.png',
    app.srcPath + '/**/*.gif',
    app.srcPath + '/**/*.svg'])
    .pipe(rev())//文件名加MD5后缀
    .pipe(gulp.dest(app.devPath))
    .pipe(rev.manifest('image-map.json'))//生成一个rev-manifest.json
    .pipe(gulp.dest(app.devPath));//将 rev-manifest.json 保存到 rev 目录内
});


gulp.task('audio', function () {
  return gulp.src([
    app.srcPath + '/**/*.mp3',
    app.srcPath + '/**/*.ogg'])
    .pipe(rev())//文件名加MD5后缀
    .pipe(gulp.dest(app.devPath))
    .pipe(rev.manifest('audio-map.json'))//生成一个rev-manifest.json
    .pipe(gulp.dest(app.devPath));//将 rev-manifest.json 保存到 rev 目录内
});

gulp.task('other', function () {
  return gulp.src([
    app.srcPath + '/**/*.txt'])
    .pipe(gulp.dest(app.devPath))
});

gulp.task('zip-html', function () {
  var options = {
    removeComments: true,//清除HTML注释
    collapseWhitespace: true,//压缩HTML
    //collapseBooleanAttributes: true,//省略布尔属性的值 <input checked="true"/> ==> <input />
    //removeEmptyAttributes: true,//删除所有空格作属性值 <input id="" /> ==> <input />
    //removeScriptTypeAttributes: true,//删除<script>的type="text/javascript"
    //removeStyleLinkTypeAttributes: true,//删除<style>和<link>的type="text/css"
    minifyJS: true,//压缩页面JS
    minifyCSS: true,//压缩页面CSS
    babel: true
  };
  return gulp.src(app.srcPath + '/**/*.html')
    .pipe(htmlmin(options))
    .pipe(gulp.dest(app.devPath))
});


gulp.task('rewrite-css', function () {
  return gulp.src([app.devPath + '*.json', app.devPath + '/**/*.css'])
    .pipe(revCollector({
      replaceReved: true
    }))
    .pipe(gulp.dest(app.devPath));
});

gulp.task('rewrite-js', function () {
  return gulp.src([app.devPath + '*.json', app.devPath + '/**/*.js'])
    .pipe(revCollector({
      replaceReved: true,
    }))
    .pipe(gulp.dest(app.devPath));
});

gulp.task('rewrite-html', function () {
  return gulp.src([app.devPath + '*.json', app.devPath + '/**/*.html'])
    .pipe(revCollector({
      replaceReved: true
    }))
    .pipe(gulp.dest(app.devPath));
});

//删除Build文件
gulp.task('clean', function () {
  return del([
    app.devPath,
    app.prdPath
  ]);
});


gulp.task('build', gulp.series(gulp.parallel('js', 'style', 'image', 'audio', 'other'), 'zip-html', 'rewrite-css', 'rewrite-js', 'rewrite-html', function () {
  return del([
    app.devPath + '*.json',
  ]);
}));

gulp.task('generate', gulp.series('clean', 'build', function () {

  return gulp.src(app.devPath + '/**/*')
    .pipe(gulp.dest(app.prdPath));
}))


//自动化构建项目，启动服务器
gulp.task('start', function () {
  connect.server({
    root: [app.srcPath],
    livereload: true,// 保存修改后自动刷新（针对高级浏览器）
    port: port,// 端口号
    debug: true,
    host: host  // 添加这个host配置，写上本地开发电脑的ip地址，那么在其他局域网上的所有设备都能访问了
  });
  // 自启动项目 
  open('http://' + host + ':' + port); // 开起局域网都能访问的项目地址
  // 监控资源文件，实时刷新
  gulp.watch(app.srcPath + "**/*", function () {
    return gulp.src(app.devPath)
      .pipe(connect.reload())
  });
});

gulp.task('default', gulp.series('start'))

`````

## 参考

[gulp-babel使用报错：Cannot find module '@babel/core'](https://blog.csdn.net/bxl0218/article/details/82352777)

[gulp文档](https://www.gulpjs.com.cn/)

[使用gulp打包普通项目](https://www.cnblogs.com/flyingzeng/p/10536690.html)

[-bash: gulp: command not found in Mac](https://stackoverflow.com/questions/35884163/bash-gulp-command-not-found-in-mac?r=SearchResults)

[gulp打包项目的基本配置与分析](https://blog.csdn.net/m0_38134431/article/details/87705456)
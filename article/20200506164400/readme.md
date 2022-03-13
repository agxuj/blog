<h1 style="font-size: 2.5em;"> ��һ���Ŵ����� --- ��ţ���޷���СͼƬ</h1>
 

## 问题1:
为什么长宽会小于2048?

## �?:
有可能是这张图片的长宽本就小�?2048.

---------------------------------

## 问题2:
服务端报400错误，错误信息：INVALID_IMAGE_SIZE（图片尺寸超过限制），七牛云上的图片确实超出了限制�??
但我在客户端做了限制，为何无效？

## 现象1:
“dd048b7479e5404298f6da4c5de8fd9f”这张图片在七牛上是大于2048,但在设置图片长宽这一步中,计算得出的长宽却小于2048

## 猜测:
设置图片长宽失败.

## 现象2:
计算并设置图片的长宽小于2048,经过 ctx.drawImage �? wx.getImageInfo 后得出的长宽却大�?2048

## 猜测:
ctx.drawImage 无法在指定的长宽里绘�?

## 结论:
在客户端层面应该没法解决, 只能在服务端层面寻找方法.
尝试记录出现这个问题的设备信�?.
测试阶段要做多机型测�?

---------------------------------

## 解决方法:

### 方法�?: 获取上传凭证�?,添加缩小参数.

结论: 处理失败,原因不明,无法解决.

`````
String accessKey = C.QiNiu.AccessKey;
String secretKey = C.QiNiu.SecretKey;
String bucket = C.QiNiu.bucket;
String key = ServiceUtils.getUUid() + ".jpg";
Auth auth = Auth.create(accessKey, secretKey);
StringMap putPolicy = new StringMap();
//数据处理指令，支持多个指�?
String entry = String.format("imageView2/0/w/2048/h/2048|saveas/%s", UrlSafeBase64.encodeToString(bucket+":"+key));
//将多个数据处理指令拼接起�?
String persistentOpfs = StringUtils.join(new String[]{
        entry
}, ";");
putPolicy.put("persistentOps", persistentOpfs);
//数据处理队列名称，必�?
putPolicy.put("persistentPipeline", "mps-pipe1");
long expireSeconds = 3600;
String token = auth.uploadToken(bucket, key, expireSeconds, putPolicy);
`````

### 方法�?: 下载�?,添加缩小参数.


结论: 这个空间是私有的,直接拼裁剪参数是无效�?.之前尝试过和key�?起编码生成相关参数后拼接,依然无效.
把空间变为公�?,这个方法就有效了.

尝试记录:

选择 七牛�? �? 基本图片处理.

有效的链�?
`````
http://image.www.funning.top/aad7ad3ce0854d20993d35ca6d9a7b5d.jpg?/imageView2/0/w/2048/h/2048

http://image.age.knxy.top/dd048b7479e5404298f6da4c5de8fd9f.jpg?e=1587099252&token=rMTw7d8DI7VnjA0WXhVST5BVDPfAKNqfpAgZYMEf:A8cRYsN5L8z-F1x1p2yww0BZDfg=&

`````

无效的链�?
`````
http://image.age.knxy.top/dd048b7479e5404298f6da4c5de8fd9f.jpg?e=1587099252&token=rMTw7d8DI7VnjA0WXhVST5BVDPfAKNqfpAgZYMEf:A8cRYsN5L8z-F1x1p2yww0BZDfg=&/imageView2/0/w/2048/h/2048

http://image.age.knxy.top/dd048b7479e5404298f6da4c5de8fd9f.jpg?/imageView2/0/w/2048/h/2048&e=1587099252&token=rMTw7d8DI7VnjA0WXhVST5BVDPfAKNqfpAgZYMEf:A8cRYsN5L8z-F1x1p2yww0BZDfg=
`````

**上述无效链接多了个�??/”符�?,修改后有�?.**


### 方法�?: 客户端上传时缩小图片.

结论参�?�问�?2.

---------------------------------
  
## 结论:
上述无效链接多了个�??/”符�?,修改后问题解�?.
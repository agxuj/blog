<h1 style="font-size: 2.5em;"> Android �ļ��洢��ʽ</h1>
 


## SDcard

### 申请权限
`````
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE">
`````

### 判断手机是否插入SD�?
**这种方式已过�?**

To improve user privacy, direct access to shared/external storage devices is deprecated. When an app targets {@link android.os.Build.VERSION_CODES#Q}, the path returned rom this method is no longer directly accessible to apps. Apps can continue to access content stored on shared/external storage by migrating to alternatives such as {@link Context#getExternalFilesDir(String)}, {@link MediaStore}, or {@link Intent#ACTION_OPEN_DOCUMENT}.
`````
if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
    doSth();
}
`````

### 获取路径
`````
File file = Environment.getExternalStorageDirectory();
`````
### 获取磁盘信息

`````
File file = Environment.getExternalStorageDirectory();
StatFs statfs = new StatFs(path.getPath());
longblocSize = statfs.getBlockSize();//获取BLOCK的SIZE
longtotalBlocks = statfs.getBlockCount();//获取BLOCK数量
longavailaBlock = statfs.getAvailableBlocks();//空闲的Block的数�?
`````


### 例子
#### 写入文件
`````
File f = Environment.getExternalStorageDirectory();//获取SD卡目�?
File fileDir =newFile(f,"test.txt");
FileOutputStream os = newFileOutputStream(fileDir);
try{
    os.write(text.getBytes()); 
    os.close();
    showToast("保存到SD卡中");
} catch (IOExceptione e) {
    //TODO Auto-generated catch block
    e.printStackTrace();
}
`````
#### 读取文件
`````
File f = Environment.getExternalStorageDirectory();//获取SD卡目�?
File fileDir = newFile(f,"test.txt");
is = new FileInputStream(fileDir);
ByteArrayOutputStream bos = new ByteArrayOutputStream();
byte[] array = new byte[1024];
int len = -1;
while((len = is.read(array)) != -1){
    bos.write(array,0,len);
}
bos.close();
is.close();
returnbos.toString();
 
`````
## 内部存储

内部存储空间 路径：data/data/包名

### getFileDir()

路径：data/data/包名/file

这个方法返回�?个绝对路径，这个绝对路径是有openFileiutput()方法创建的文件�?�当在这个路径下调用程序来进行读写操作的时�?�，是不�?要任何额外的权限的�?�也就是说，我们在使用内部存储的方法存储数据的时候，不需要用在manifest文件中声明权限，也不�?要�?�虑android6.0的运行时权限的�?? 

### getCacheDir()

路径：data/data/包名/cache

获取内部存储空间的缓存路径�?�当系统内存不足时，会把他整个目录删掉吧�? 

### getDir()

路径：data/data/包名/

这个方法其实是直接在内部存储空间创建文件夹的方法。这个文件夹是和上面�?说的file和cache是同级的。�?�且我们发现他会自动在新建的文件名前加一个app_，这个需要注意一下，但是并不影响我们还是通过文件名来访问�?
 

## 参�??
[Android正确获取SD卡目录及使用SD卡目录](https://www.jianshu.com/p/d2cd9b19eff4)

[Android文件存储(�?)内部存储](https://blog.csdn.net/xy4_android/article/details/80985890)

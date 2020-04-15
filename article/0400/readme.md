# 20180425162104
 


 

Android6.0之后，权限分为install时的权限跟运行时权限

Permission request at install-time or runtime.

## Situation analysis

`````````
if(targetSdkVersion < 23 && deviceVersion < 23){
    request at install-time
} else if(targetSdkVersion < 23 && deviceVersion >= 23){
    request on install-time.
} else if(targetSdkVersion >= 23 && deviceVersion < 23){
    request on install-time.
} else if(targetSdkVersion >= 23 && deviceVersion > 23){
    request on install-time and runtime.
}
``````````
## How to request at runtime
`````````java
int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);

if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
} else {
    //TODO 
}
`````````
Override Activity.onRequestPermissionsResult

`````````java
@Override
public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    switch (requestCode) {
        case REQUEST_READ_PHONE_STATE:
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                //TODO
            }
            break;

        default:
            break;
    }
}
``````````

#### Thinking: maybe we can create dialog by ourselves

## Reference：

[Android权限检查API checkSelfPermission失效问题](https://juejin.im/post/59e01ece51882578c6736db7)

[Android M动态申请获取权限android.permission.READ_PHONE_STATE](https://blog.csdn.net/fenggering/article/details/53432401)

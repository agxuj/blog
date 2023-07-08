# 创建AIDL文件

## ICar.aidl
```````````
// ICar.aidl
package com.desaysv.carservice;

import com.desaysv.carservice.ICallBack;
// Declare any non-default types here with import statements

interface ICar {

    oneway void registerCallBack(ICallBack callback);
    oneway void unRegisterCallBack(ICallBack callback);

    int[] getValues(int funcID);
    oneway void sendValues(int funcID, in int[] values);

}
```````````

## ICallBack.aidl
```````````
// ICallBack.aidl
package com.desaysv.carservice;

// Declare any non-default types here with import statements

interface ICallBack {
    void onChange(int moduleId, in int[] data);
}
```````````


# 创建客户端

## CarManager

`````
public class CarManager {
    private ICar carAidl = null;

    /**
     * 构造方法种绑定服务
     */
    public CarManager(Context context) {
        this.context = context.getApplicationContext();
        try {
            Intent service = new Intent();
            service.setClassName("com.android.car", "com.android.car.CarInfoService");
            context.bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解绑服务
     */
    public void unBindService() {
        Log.d(TAG, "unBindService");
        context.unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected");
            try {
                service.linkToDeath(deathRecipient, 0);
                carAidl = ICar.Stub.asInterface(service);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected");
            carAidl = null;
        }
    };

    /**
     * Binder有意外退出通知的机制：Link-To-Death
	 * 可做从连操作
     * 需要注意的是 onServiceDisconnected 可能也会呗调用
     */
    private IBinder.DeathRecipient deathRecipient = () -> {
        Log.d(TAG, "binder died. name:" + Thread.currentThread().getName());
    };

    /**
     * @return 服务是否可以使用
     */
    public boolean isServiceConnected() {
        if (carAidl == null) {
            Log.i(TAG, "isServiceConnected carAidl is null.");
            return false;
        }
		return true;
    }

    /**
     * 注册指定模块的消息回调
     *
     * @param callback
     */
    public void registerCallBack(ICallBack callback) {
        try {
            carAidl.registerCarInfoCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消注册指定模块的消息回调
     *
     * @param callback
     */
    public void unregisterCallBack(ICallBack callback) {
        try {
            carAidl.unRegisterCarInfoCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
    public int[] getValues(int funcID) {
        return carAidl.getValues(funcID);
    }

    @Override
    public void sendValues(int funcID, int[] values) {
		carAidl.sendValues(funcID, values);
    }
}
`````

# 创建服务端

Android 8.0 有一项复杂功能；系统不允许后台应用创建后台服务。 因此，Android 8.0 引入了一种全新的方法，即 Context.startForegroundService()，以在前台启动新服务。在系统创建服务后，应用有五秒的时间来调用该服务的 startForeground() 方法以显示新服务的用户可见通知。如果应用在此时间限制内未调用 startForeground()，则系统将停止服务并声明此应用为 ANR. startService 和 startForegroundService 最终调用调用的接口是一样的，只是其中要求 foreground 启动 service. 


## AndroidMenifest.xml
`````
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
`````

`````
<service
	android:name="com.desaysv.carservice.CarService"
	android:enabled="true"
	android:exported="true" />
`````


## Application.java
Service在Application.onCreate中启动


`````
public class AppContext extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        startForegroundService(new Intent(this, CarService.class));
    }
}
`````

## CarService.java
`````
public class CarService extends Service {

    private CarBinder binder = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化 Binder  
        binder = new CarBinder(getApplicationContext());
        startForegroundNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        绑定时返回binder对象
        return binder;
    }

    //发送通知前台服务优先级较高，在系统内存不足时，也不会考虑将其终止。 
    //前台服务必须为状态栏提供通知，而且除非服务停止或从前台删除，否则不能清除通知。
    private void startForegroundNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            NotificationChannel channel = new NotificationChannel(TAG, "消息通知", NotificationManager.IMPORTANCE_LOW);
            channel.enableLights(false);
            channel.enableVibration(false);
            channel.setVibrationPattern(new long[]{0});
            channel.setSound(null, null);

            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(this, TAG)
                    .setContentTitle("开启前台服务")
                    .build();
            startForeground(1, notification);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binder.onDestroy();
    }
}
`````

## CarBinder.java
`````
public class CarBinder extends ICar.Stub {

    public static final String TAG = "CarBinder";

    public final RemoteCallbackList<ICallBack> listeners = new RemoteCallbackList<>();

    private Handler handler = new Handler(Looper.getMainLooper());

    private void onDestroy() {
    }

    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int len = listeners.beginBroadcast();

                for (int i = 0; i < len; i++) {
                    ICallBack callBack = listeners.getBroadcastItem(i);
                    try {
                        callBack.onChange(1,new int[]{3,2,1});
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                listeners.finishBroadcast();
                handler.postDelayed(this, 5000);
            }
        }, 5000);
    }

    @Override
    public void registerCallBack(ICallBack callback) {
        listeners.register(callback);
    }

    @Override
    public void unRegisterCallBack(ICallBack callback) {
        listeners.unregister(callback);
    }

    @Override
    public int[] getValues(int funcID) {
        return new int[]{1, 2, 3};
    }

    @Override
    public void sendValues(int funcID, int[] values) {
        Log.d(TAG, "sendValues values : " + Arrays.toString(values));
    }
}
`````
# 例子

<a href="source/Client.zip">Client.zip</a>

<a href="source/Service.zip">Service.zip</a>

# 参考
[Android service 启动篇之 startForegroundService](https://www.cnblogs.com/mwl523/p/14209777.html)


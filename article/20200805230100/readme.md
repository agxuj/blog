<h1 style="font-size: 2.5em;"> Android Service</h1>
 

**参�??**

[Android Service�?全面的解析](https://blog.csdn.net/scott2017/article/details/51505801)

[官方文档](https://developer.android.com/guide/components/services.html)

Service是Android中一个类，它是Android四大组件之一，使用Service可以在后台执行长时间的操作（ perform long-running operations in the background ），Service并不与用户产生UI交互。其他的应用组件可以启动Service，即便用户切换了其他应用，启动的Service仍可在后台运行�?�一个组件可以与Service绑定并与之交互，甚至是跨进程通信（IPC）�?�例如，�?个Service可以在后台执行网络请求�?�播放音乐�?�执行文件读写操作或者与 content provider交互 等�?�本文将介绍Services的定义�?�创建�?�启动�?�绑定�?�前台Service等相关内�?

# Services
Services有两种启动形式：

* Started：其他组件调用startService()方法启动�?个Service。一旦启动，Service将一直运行在后台（run in the background indefinitely）即便启动Service的组件已被destroy。�?�常，一个被start的Service会在后台执行单独的操作，也并不给启动它的组件返回结果。比如说，一个start的Service执行在后台下载或上传�?个文件的操作，完成之后，Service应自己停止�??

* Bound：其他组件调用bindService()方法绑定�?个Service。�?�过绑定方式启动的Service是一个client-server结构，该Service可以与绑定它的组件进行交互�?�一个bound service仅在有组件与其绑定时才会运行（A bound service runs only as long as another application component is bound to it），多个组件可与�?个service绑定，service不再与任何组件绑定时，该service会被destroy�?

当然，service也可以同时在上述两种方式下运行�?�这涉及到Service中两个回调方法的执行：onStartCommand()（�?�过start方式启动�?个service时回调的方法）�?�onBind()（�?�过bind方式启动�?个service回调的方法）�?

无论通过那种方式启动service（start、bind、start&bind），任何组件（甚至其他应用的组件）都可以使用service。并通过Intent传�?�参数�?�当然，您也可以将Service在manifest文件中配置成私有的，不允许其他应用访问�??

！请注意：Service运行在主线程中（A service runs in the main thread of its hosting process），Service并不是一个新的线程，也不是新的进程�?�也就是说，若您�?要在Service中执行较为�?�时的操作（如播放音乐�?�执行网络请求等），�?要在Service中创建一个新的线程�?�这可以防止ANR的发生，同时主线程可以执行正常的UI操作�?

<span style="color:blue">使用Service还是使用Thread�?</span>

Service是一个运行在后台的组件，并不与用户交互�?�您仅在�?要的时�?�创建Service�? create a service only if that is what you need）�?? 
当用户正在与UI交互时，�?要执行一些主线程无法完成的工作，应当创建�?个线程�?�例如当activity正在运行时，�?要播放音乐，此时�?要在Activity的onCreate()中创建线程并在onStart()中开启�?�最后在onStop()中停止�?�您也可以�?�虑使用AsyncTask �? HandlerThread来替代Thread创建线程�?

# Service基础（The Basics�?
为了创建Service，需要继承Service类�?�并重写它的回调方法，这些回调方法反应了Service的生命周期，并提供了绑定Service的机制�?�最重要�?<span style="color:blue">Service的生命周期回调方�?</span>如下�?示：

* onStartCommand()：当其他组件调用startService()方法请求启动Service时，该方法被回调。一旦Service启动，它会在后台独立运行。当Service执行完以后，�?调用stopSelf() �? stopService()方法停止Service。（若您只希望bind Service，则无需调用这些方法�?

* onBind()：当其他组件调用bindService()方法请求绑定Service时，该方法被回调。该方法返回�?个IBinder接口，该接口是Service与绑定的组件进行交互的桥梁�?�若Service未绑定其他组件，该方法应返回null�?

* onCreate()：当Service第一次创建时，回调该方法。该方法只被回调�?次，并在onStartCommand() �? onBind()方法被回调之前执行�?�若Service处于运行状�?�，该方法不会回调�??

* onDestroy()：当Service被销毁时回调，在该方法中应清除一些占用的资源，如停止线程、接触绑定注册的监听器或broadcast receiver 等�?�该方法是Service中的�?后一个回调�??

如果某个组件通过调用startService()启动了Service（系统会回调onStartCommand()方法），那么直到在Service中手动调用stopSelf()方法、或在其他组件中手动调用stopService()方法，该Service才会停止�?

如果某个组件通过调用bindService()绑定了Service（系统不会回调onStartCommand()方法），只要该组件与Service处于绑定状�?�，Service就会�?直运行，当Service不再与组件绑定时，该Service将被destroy�?

当系统内存低时，系统将强制停止Service的运行；若Service绑定了正在与用户交互的activity，那么该Service将不大可能被系统kill�? less likely to be killed）�?�如果创建的是前台Service，那么该Service几乎不会被kill（almost never be killed）�?�否则，当创建了�?个长时间在后台运行的Service后，系统会降低该Service在后台任务栈中的级别—�?�这意味�?它容易被kill（lower its position in the list of background tasks over time and the service will become highly susceptible to killing），�?以在�?发Service时，�?要使Service变得容易被restart，因为一旦Service被kill，再restart它需要其资源可用时才行（restarts it as soon as resources become available again ），当然这也取决于onStartCommand()方法返回的�?�，这将在后续介绍�??

# 在manifest文件中注册service
在manifest文件中注册service的方式如下：

`````
<manifest>
    <application>
        <service android:name=".ExcampleService">
    </application>
</manifest>
`````

除此之外，在<service>标签中还可以配置其他属�?�，比如，需要启动该service�?�?的权限�?�该service应运行在哪个进程�? 等（ permissions required to start the service and the process in which the service should run）�?�android:name属�?�是唯一不可缺省的，它指定了Service的全限定类名。一旦发布了应用，该类名将不可更改�??

！请注意：为了保证应用的安全，请使用显式Intent启动或绑定一个Service，请不要�?<service>标签中配置intent-filter�?

若不确定该启动哪个Service，那么可以在<service>中配置intent-filter，并在Intent中排除该Service（supply intent filters for your services and exclude the component name from the Intent），但必须调用Intent的setPackage()方法，来为启动的service消除歧义（provides sufficient disambiguation for the target service）�??

注：setPackage()方法传入�?个String参数，代表一个包名�?�该方法表示该Intent对象只能在传入的这个包名下寻找符合条件的组件，若传入null，则表示可以在任意包下寻找�??

将android:exported属�?�设为false，表示不允许其他应用程序启动本应用的组件，即便是显式Intent也不行（even when using an explicit intent）�?�这可以防止其他应用程序启动您的service组件�?

# 使用start方式启动Service
其他组件调用startService()方法可以启动�?个Service，接�?，Service会回调onStartCommand()生命周期方法。startService()方法中传入一个Intent参数，用于显式指定目标Service的名字，并携带data以供Service使用，该Intent参数将回传至onStartCommand()方法中�?? 
比如说，Activity�?要向在线数据库中上传数据，那么可以调用startService()启动�?个Service，并将数据传入Intent的data中，接着，onStartCommand()方法会接收这个Intent并开启一个线程将数据上传至网络，当数据上传完成后，该Service将停止并被destroy�?

**�?般使用如下两种方式创建一个start Service�?**

* 继承Service类：请务必在Service中开启线程来执行耗时操作，因为Service运行在主线程中�??

* 继承IntentService类：IntentService继承于Service，若Service不需要同时处理多个请求，那么使用IntentService将是�?好�?�择：您只需要重写onHandleIntent()方法，该方法接收�?个回传的Intent参数，您可以在方法内进行耗时操作，因为它默认�?启了�?个子线程，操作执行完成后也无�?手动调用stopSelf()方法，onHandleIntent()会自动调用该方法�?

# 继承IntentService�?
在大多数情况下，start Service并不会同时处理多个请求（don’t need to handle multiple requests simultaneously），因为处理多线程较为危险（a dangerous multi-threading scenario），�?以继承IntentService类带创建Service是个不错选择�?

使用IntentService的要点如下：

* 默认在子线程中处理回传到onStartCommand()方法中的Intent�?

* 在重写的onHandleIntent()方法中处理按时间排序的Intent队列，所以不用担心多线程（multi-threading）带来的问题�?

* 当所有请求处理完成后，自动停止service，无�?手动调用stopSelf()方法�?

* 默认实现了onBind()方法，并返回null�?

* 默认实现了onStartCommand()方法，并将回传的Intent以序列的形式发�?�给onHandleIntent()，您只需重写该方法并处理Intent即可�?

综上�?述，您只�?重写onHandleIntent()方法即可，当然，还需要创建一个构造方法，示例如下�?

`````

public class HelloIntentService extends IntentService { 
    /**
    *	A constructor is required, and must call the super IntentService(String)
    *	constructor with a name for the worker thread.
    */
    public HelloIntentService() { 
        super("HelloIntentService");
    }

    /**
    *	The IntentService calls this method from the default worker thread with
    *	the intent that started the service. When this method returns, Intent Service
    *	stops the service, as appropriate.
    */
    ©Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        //For our sample, we just sleep for 5 seconds.
        try {
            Thread, sleep(5000);
        } catch (interruptedException e) {
            // Restore interrupt status.
            Thread.currentThreadO.interrupt();
        }
    }
}
`````

如果您还希望在IntentService�? 继承类中重写其他生命周期方法，如onCreate()、onStartCommand() �? onDestroy()，那么请先调用各自的父类方法以保证子线程能够正常启动�?

比如，要实现onStartCommand()方法，需返回其父类方法：

`````
@Override
public int onStartCommand(Intent intent, int flags, int startld) {
    Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
    return super. onStartCommand( int ent, flags, startld);
}
`````

除onHandleIntent()外，onBind()方法也无�?调用其父类方法�??

# 继承Service�?
如果您需要在Service中执行多线程而不是处理一个请求队列（perform multi-threading instead of processing start requests through a work queue），那么�?要继承Service类，分别处理每个Intent�?

在Service中执行操作时，处理每个请求都�?要开启一个线程，并且同一时刻�?个线程只能处理一个请�?

`````
public class HelloService extends Service { 
    private Looper mServiceLooper; 
    private ServiceHandler mServiceHandler;

    // Handler that receives messages from the thread 
    private final class ServiceHandler extends Handler { 
        public ServiceHandler(Looper looper) { 
            super(looper);
        }


        @Override
        public void handleMessage(Message msg) {
            // Normally we would do some work here, like download a file. 
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(5000);
            } catch (interruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
            // Stop the service using the startld, so that we don’t stop 
            // the service in the middle of handling another job 
            stopSelf(msg.argl);
        }
    }

    @Override
    public void onCreate() {
        // Start up the thread running the service. Note that we create a 
        // separate thread because the service normally runs in the processes 
        // main thread, which we don’t want to block. Ve also make it 
        // background priority so CPU-intensive work will not disrupt our UI. 
        
        HandlerThread thread = new HandlerThread(
            "ServiceStartArguments", 
            Process.THREAD_PRIORITY_BACKGROUHD
        ); 
        thread.start();
        // Get the Handler Thread’s Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startld) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the 
        // start ID so we know which request we#re stopping when we finish the job 
        Message msg = mServiceHandler.obtainMessage(); 
        msg.argl - startld; 
        mServiceHandler.sendMessage(msg);
        // If we get killed, after returning from here, restart 
        return START.STICKY;
    }

    @Override
    public IBinder onBind(lntent intent) {
        //We don't provide binding, so return null 
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}

``````




注意到onStartCommand()返回�?个整形变量，该变量必须是下列常量之一�?

* START_NOT_STICKY：若执行完onStartCommand()方法后，系统就kill了service，不要再重新创建service，除非系统回传了�?个pending intent。这避免了在不必要的时�?�运行service，您的应用也可以restart任何未完成的操作�?

* START_STICKY：若系统在onStartCommand()执行并返回后kill了service，那么service会被recreate并回调onStartCommand()。dangerous不要重新传�?�最后一个Intent（do not redeliver the last intent）�?�相反，系统回调onStartCommand()时回传一个空的Intent，除非有 pending intents传�?�，否则Intent将为null。该模式适合做一些类似播放音乐的操作�?

* START_REDELIVER_INTENT：若系统在onStartCommand()执行并返回后kill了service，那么service会被recreate并回调onStartCommand()并将�?后一个Intent回传至该方法。任�? pending intents都会被轮流传递�?�该模式适合做一些类似下载文件的操作�?

# 启动服务 
若需要启动Service，见下面�?示：

`````
Intent intent = new Intent(this, HelloService.class);
startService(intent);
`````

startService(intent)方法将立即返回，并回调onStartCommand()（请不要手动调用该方法），若该Service未处于运行状态，系统将首先回调onCreate()，接�?再回调onStartCommand()。若您希望Service可以返回结果，那么需要�?�过调用getBroadcast 返回的PendingIntent启动Service（将PendingIntent包装为Intent），service可使用broadcast 传�?�结果�??

多个启动Service的请求可能导致onStartCommand()多次调用，但只需调用stopSelf() �? stopService()这两个方法之�?，就可停止该服务�?

# 停止服务 
�?个启动的Service必须管理自己的生命周期�?�系统不会主动stop或destroy�?个运行的Service，除非系统内存紧张，否则，执行完onStartCommand()方法后，Service依然运行。停止Service必须手动调用stopSelf()（在Service中）或调用stopService()（在启动组件中）�?

�?旦调用了上述两种方法之一，系统会尽快destroy该Service（as soon as possible）�??

若系统正在处理多个调用onStartCommand()请求，那么在启动�?个请求时，您不应当在此时停止该Service（you shouldn’t stop the service when you’re done processing a start request）�?�为了避免这个问题，您可以调用stopSelf(int)方法，以确保请求停止的Service时最新的启动请求�? your request to stop the service is always based on the most recent start request）�?�这就是说，当调用stopSelf(int)方法时，传入的ID代表启动请求（该ID会传递至onStartCommand()），该ID与请求停止的ID�?致�?�则如果在调用stopSelf(int)之前，Service收到�?个新的Start请求，ID将无法匹配，Service并不会停止�??

为了节省内存和电量，当Service完成其工作后将其stop很有必要。如有必要，可以在其他组件中调用stopService()方法，即便Service处于绑定状�?�，只要它回调过onStartCommand()，也应当主动停止该Service�?

# 创建绑定Service 
通过其他组件调用bindService()方法可以绑定�?个Service以保持长连接（long-standing connection），这时�?般不允许其他组件调用startService()启动Service�?

当其他组件需要与Service交互或�?�需要跨进程通信时，可以创建�?个bound Service�?

为创建一个bound Service，必须重写onBind()回调，该方法返回�?个IBinder接口。该接口时组件与Service通信的桥梁�?�组件调用bindService()与Service绑定，该组件可获取IBinder接口，一旦获取该接口，就可以调用Service中的方法。一旦没有组件与Service绑定，系统将destroy它，您不必手动停止它�?

为创建一个bound Service，必须定义一个接�? ，该接口指定组件与Service如何通信。定义的接口在组件与Service之间，且必须实现IBinder接口。这正是onBind()的返回�?��?�一旦组件接收了IBinder，组件与Service便可以开始�?�信�?

多个组件可同时与Service绑定，当组件与Service交互结束后，可调用unbindService()方法解绑。bound Service比start Service要复杂，故我将在后续单独翻译�?

# 向用户发送�?�知 
运行中的Service可以通过Toast Notifications �? Status Bar Notifications 向用户发送�?�知。Toast是一个可以短时间弹出的提醒框。二Status Bar是顶部状态栏中出现的太有图标的信息，用户可以通过下拉状�?�栏获得具体信息并执行某些操作（如启动Activity）�??

通常，Status Bar用于通知某些操作已经完成，如下载文件完成。当用户下拉状�?�栏后，点击该�?�知，可获取详细内容，如查看该下载的文件�?

# 运行前台Service
前台Service用于动�?��?�知消息，如天气预报。该Service不易被kill。前台Service必须提供status bar，只有前台Service被destroy后，status bar才能消失�?

举例来说，一个播放音乐的Service必须是前台Service，只有这样用户才能确知其运行状�?��?�为前台Service提供的status bar可以显示当前音乐的播放状态，并可以启动播放音乐的Activity�?

调用startForeground()可以启动前台Service。该方法接收两个参数，参数一是一个int型变量，用户指定该�?�知的唯�?性标识，而参数�?�是�?个Notification用于配置status bar，示例如下：

`````
Notification notification = new Notification(
    R.drawable.icon, 
    getText(R.string.ticker_text),
    System.currentTimeHillis()
);
Intent notificationIntent = new Intent(this, ExampleActivity.class);
PendingIntent pendingIntent = Pendinglntent.getActivity(this, 0, notificationIntent, 0);
notification.setLatestEventlnfo(this, getText(R.string.notification_title),getText(R. string. notification_message), pendinglntent);
startForeground(0NG0ING_N0TIFICATI0N_ID, notification);
`````

！注意：为startForeground()设置的ID必须�?0�?

调用stopForeground()来移除（remove）前台Service。该方法�?传入�?个boolean型变量，表示是否也一并清除status bar上的notification（indicating whether to remove the status bar notification as well）�?�该方法并不停止Service，如果停止正在前台运行的Service，那么notification 也一并被清除�?

# Service生命周期
从Service的启动到�?毁，有两种路径：

* A started service：需手动停止

* A bound service：可自动停止

如下图所示：
<img src="image/1.jpg">
 

这两条路径并不是毫不相干的：当调用startService() start�?个Service后，您仍可以bind该Service。比如，当播放音乐时，需调用startService()启动指定播放的音乐，当需要获取该音乐的播放进度时，有�?要调用bindService()，在这种情况下，直到Service被unbind ，调用stopService() 或stopSelf()都不能停止该Service�?

# 实现Service的生命周期回�?

`````
int mStartHode;         // indicates how to behave if the service is killed
IBinder mfiinder;       // interface for clients that bind
boolean mAllowRebind;   // indicates whether onRebind should be used

@Override
public void onCreate() {
    // The service is being created
}

@Override
public int onStartCommand(Intent intent, int flags, int startld) { 
    // The service is starting, due to a call to startService () 
    return mStartHode;
}

@Override
public IBinder onBind(lntent intent) {
    //A client is binding to the service with bindService () 
    return mBinder;
}

@Override
public boolean onUnb ind(Int ent intent) {
    //All  clients have unbound with unbindService () 
    return mAllowRebind;
}

@Override
public void onRebind(Intent intent) {
    // A client is binding to the service with bindService (),
    // after onUnbind() has already been called
}

@Override
public void onDestroyO {
    // The service is no longer used and is being destroyed
}
`````

这些生命周期方法在使用时无需调用各自的父类方法�??

在两条生命周期路径中，都包含了两个嵌套的生命周期�?

* 完整生命周期�? entire lifetime ）：从onCreate()被调用到onDestroy()返回。与Activity类似，一般在onCreate()中做�?些初始化工作，�?�在onDestroy()做一些资源释放工作�?�如，若Service在后台播放一个音乐，就需要在onCreate()方法中开启一个线程启动音乐，并在onDestroy()中结束线程�??-
无论是startService() 还是 bindService()启动Service，onCreate() �? onDestroy()均会被回调�??

* 活动生命周期（active lifetime）：从onStartCommand() �? onBind()回调�?始�?�由相应的startService() �? bindService()调用�? 
若是Start Service，那么Service的活动生命周期结束就意味�?其完整生命周期结�? （the active lifetime ends the same time that the entire lifetime ends），即便onStartCommand()返回后，Service仍处于活动状态；若是bound Service，那么当onUnbind()返回时，Service的活动生命周期结束�?? 

！请注意：针对Start Service，由于Service中没有类似onStop()的回调，�?以在调用stopSelf() 或stopService()后，只有onDestroy()被回调标志着Service已停止�??
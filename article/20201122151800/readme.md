<h1 style="font-size: 2.5em;"> Android ��Ϣ֪ͨ</h1>
 


# �?单使�?
构建�?个�?�知栏一般分为这几个步骤�?

1.创建通知栏管理工�?
2.构建通知栏构造器
3.给构造器设置参数
4.发�?�请�?

具体代码如下�?
`````
//创建通知栏管理工�?
NotificationManager notificationManager = (NotificationManager) getSystemService (NOTIFICATION_SERVICE);
//实例化�?�知栏构造器
NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
//设置Builder
mBuilder.setContentTitle("我是标题")                                     //设置标题
                .setContentText("我是内容")                              //设置内容 
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))       //设置大图�? 
                .setSmallIcon(R.mipmap.ic_launcher_round)               //设置小图�? 
                .setWhen(System.currentTimeMillis())                    //设置通知时间
                .setTicker("我是测试内容")                               //首次进入时显示效�?
                .setDefaults(Notification.DEFAULT_SOUND);               //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声�?

`````


# 自定义�?�知�?
自定义�?�知栏和使用原生的�?�知栏区别不大，�?主要就是增加了自定义的布�?，使用RemoteViews承接，并放入构�?�器中显示，具体代码如下:

`````

NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
remoteViews.setImageViewResource(R.id.image, R.mipmap.timg);
remoteViews.setTextViewText(R.id.title, "我是标题");
remoteViews.setTextViewText(R.id.content, "我是内容");
remoteViews.setProgressBar(R.id.pBar, 10, progress, false);
remoteViews.setTextViewText(R.id.proNum, progress + "/10");

NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
mBuilder.setSmallIcon(R.mipmap.timg);
mBuilder.setContent(remoteViews);
if (progress == 1) {
    mBuilder.setDefaults(Notification.DEFAULT_SOUND);
}

notificationManager.notify(10, mBuilder.build());


`````

# 点击跳转到其他页�?
`````
Intent intent = new Intent(this, SecondeActivity.class);
PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
mBuilder.setContentIntent(pendingIntent);
`````

# Android O (8.0) 以上的使�?
Android O 8.0以上�?要增加渠道名称和渠道ID，具体代码如下：
``````
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
    mBuilder.setChannelId(id);
    notificationManager.createNotificationChannel(channel);

    mBuilder.setSmallIcon(R.mipmap.timg);
    mBuilder.setContent(remoteViews);
    if (progress == 1) {
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
    }
    remoteViews.setImageViewResource(R.id.image, R.mipmap.timg);
    remoteViews.setTextViewText(R.id.title, "我是标题");
    remoteViews.setTextViewText(R.id.content, "我是内容");
    remoteViews.setProgressBar(R.id.pBar, 10, progress, false);
    remoteViews.setTextViewText(R.id.proNum, progress + "/10");
    notificationManager.notify(10, mBuilder.build());
}
``````

# SystemUI中的Notification

在SystemUI中注册，就能收到推�?�的消息，取得消息后，在消息中心中显示，核心代码如下�?
``````
NotificationListenerService listener = new NotificationListenerService(){
    @Override
    public void onListenerConnected() {
    }

    @Override
    public void onListenerDisconnected() {
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn, RankingMap rankingMap) {
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }

    @Override
    public RankingMap getCurrentRanking() {
    }

    @Override
    public void onNotificationRankingUpdate(RankingMap rankingMap) {
    }
};
listener.registerAsSystemService(Context context, ComponentName componentName,int currentUser)
``````


# 参�??
[Android通知�?(Notification)介绍及使用](https://blog.csdn.net/qq_34163551/article/details/79851542)
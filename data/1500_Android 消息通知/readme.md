
# 简单使用
构建一个通知栏一般分为这几个步骤：

1.创建通知栏管理工具
2.构建通知栏构造器
3.给构造器设置参数
4.发送请求

具体代码如下：
`````
//创建通知栏管理工具
NotificationManager notificationManager = (NotificationManager) getSystemService (NOTIFICATION_SERVICE);
//实例化通知栏构造器
NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
//设置Builder
mBuilder.setContentTitle("我是标题")                                     //设置标题
                .setContentText("我是内容")                              //设置内容 
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))       //设置大图标 
                .setSmallIcon(R.mipmap.ic_launcher_round)               //设置小图标 
                .setWhen(System.currentTimeMillis())                    //设置通知时间
                .setTicker("我是测试内容")                               //首次进入时显示效果
                .setDefaults(Notification.DEFAULT_SOUND);               //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音

`````


# 自定义通知栏
自定义通知栏和使用原生的通知栏区别不大，最主要就是增加了自定义的布局，使用RemoteViews承接，并放入构造器中显示，具体代码如下:

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

# 点击跳转到其他页面
`````
Intent intent = new Intent(this, SecondeActivity.class);
PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
mBuilder.setContentIntent(pendingIntent);
`````

# Android O (8.0) 以上的使用
Android O 8.0以上需要增加渠道名称和渠道ID，具体代码如下：
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

在SystemUI中注册，就能收到推送的消息，取得消息后，在消息中心中显示，核心代码如下：
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


# 参考
[Android通知栏(Notification)介绍及使用](https://blog.csdn.net/qq_34163551/article/details/79851542)
NotificationListenerService {
    registerAsSystemService()
    unregisterAsSystemService()

    onListenerConnected()
    onListenerDisconnected()

    onNotificationPosted()
    onNotificationRemoved(StatusBarNotification sbn, final RankingMap rankingMap) 
    onNotificationRankingUpdate(final RankingMap rankingMap) 

    getActiveNotifications()
    getCurrentRanking()

    /** 打盹，暂替某消息显示一段时间 **/
    snoozeNotification(String key, long durationMs)
    /** 设置消息已被查看 **/
    setNotificationsShown(String[] keys)
}

这个监听器注册在 NotificationManagerService 里。

SystemUI 中的 NotificationListenerWithPlugins extends NotificationListenerService 
SystemUI 中的 NotificationListener extends NotificationListenerWithPlugins 
------------------
IStatusBarService(StatusBarManager){
    clearNotificationEffects()
    onNotificationClick()
    onNotificationActionClick()
    onNotificationError()
    onClearAllNotifications()
    onNotificationClear()
    onNotificationVisibilityChanged()
    onNotificationExpansionChanged()
    onNotificationDirectReplied()
    onNotificationSmartRepliesAdded()
    onNotificationSmartReplySent()
    onNotificationSettingsViewed()
}

IStatusBarService 的实现者是 StatusBarManagerService
与消息中心相关的方法最终调用的是 NotificationManagerService
------------------
/** 表示通知的事物的抽象，例如状态栏。包含查询系统状态的方法（一些模块化功能可能希望根据用户是否可以看到演示者而采取不同的行动），以及影响系统状态的方法（例如启动意图，假设演示者可能希望在这样做之前执行某些操作）。 **/
NotificationPresenter 
NotificationPresenter实现在statubar中
使用到的类如下列表
AppOpsListener
NotificationGutsManager
NotificationLockscreenUserManager
NotificationMediaManager
NotificationRemoteInputManager
NotificationViewHierarchyManager
------------------
NotificationEntryManager
------------------


NotificationInflater


RemoteViews解析

# 参考
[7.1.3 理解IStatusBarService](https://www.kancloud.cn/alex_wsc/android-deep3/416484)

[android 状态栏(StatusBar)](https://blog.csdn.net/jdsjlzx/article/details/22482423/)

[SystemUi篇->StatusBarManager](https://blog.csdn.net/liu149339750/article/details/50735629)

[Android StatubarManager 管理状态栏](https://www.jianshu.com/p/b0b0c2eb3e85)

[Notification和NotificationManagerService原理解析](https://blog.csdn.net/cbzcbzcbzcbz/article/details/104068389)

[第88章、系统服务之NOTIFICATION_SERVICE服务（从零开始学Android）](https://blog.csdn.net/chenliaolve8143/article/details/100998299)

[RemoteViews完全解析](https://blog.csdn.net/qq_28702545/article/details/51871443)

[RemoteViews的作用和工作原理](https://www.jianshu.com/p/7034a1ba1e74)

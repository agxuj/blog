<h1 style="font-size: 2.5em;"> Android WifiManage</h1>
 

# �?�?
WifiManager这个�?, �? Android 暴露给开发�?�使用的�?个系统服务管理类, 其中包含对WiFi的响应的操作函数; 其隐藏掉的系统服务类为IWifiService, 为Android私有�?, 其具体实�?, 未暴露给用户; 只需要使用WifiManager进行函数操作完成UI, 监听对应的广播消�?, 就可完成功能�?. 换言�?, WifiManager会调用service间接地和framework�?, 驱动层进行函数调�?, 然后驱动层会回调至上�?, 以广播的形式实现通知; 这是目前WiFi的简单介绍�??

# �?些简单的工具类的封装 以及 �?要说�?
## 获取 WifiManager 实例. 
```````````
public static WifiManager getWifiManager(Context context) {
    return context == null ? null : (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
}
```````````

    这是官方推荐的获�? WifiManager 类的方式; 其实在看源码的时�?, 会有�?个公共的构�?�函�?, 但是是需要IWifiService�?, 这个类是google私有�?, 属于系统安全级别的API�?, �?�?, 调用上面的方�?, 谷歌会帮你实例化IWifiService, 并且后续的连�?, 忘记, 保存的真实实现都是由service来完成的.

## �?�?, 关闭 WiFi
`````````
public static boolean setWifiEnabled(WifiManager manager, boolean enabled) {
    return manager != null && manager.setWifiEnabled(enabled);
}
`````````

## 获取 WiFi 硬件的状�?
`````````
public static int getWifiState(WifiManager manager) {
    return manager == null ? WifiManager.WIFI_STATE_UNKNOWN : manager.getWifiState();
}
`````````
    WiFi 的状态目前有五种, 分别�?:
    WifiManager.WIFI_STATE_ENABLING: WiFi正要�?启的状�??, �? Enabled �? Disabled 的临界状�?;
    WifiManager.WIFI_STATE_ENABLED: WiFi已经完全�?启的状�??;
    WifiManager.WIFI_STATE_DISABLING: WiFi正要关闭的状�?, �? Disabled �? Enabled 的临界状�?;
    WifiManager.WIFI_STATE_DISABLED: WiFi已经完全关闭的状�?;
    WifiManager.WIFI_STATE_UNKNOWN: WiFi未知的状�?, WiFi�?�?, 关闭过程中出现异�?, 或是厂家未配备WiFi外挂模块会出现的情况;


## 扫描 WiFi 热点

�?始扫�? WiFi 热点, 在确认开�? WiFi 之后, 我们就可以调�? startScan() 函数�?始扫描附近的热点�?, �?:

`````
public static void startScanWifi(WifiManager manager) {
    if (manager != null) {
        manager.startScan();
    }
}
`````

## 获取扫描到的 WiFi 热点的结�?
`````````
public static List<ScanResult> getScanResult(WifiManager manager) {
    return manager == null ? null : manager.getScanResult();
}
`````````

## 获取配置好的 WiFi 信息:

``````
public static List<WifiConfiguration> getConfiguredNetworks(WifiManager manager) {
    return manager == null ? null : manager.WifiConfiguration();
}
``````

    Android �? WiFi 连接, 大概可以分为如下两种情况:
    a. 无密码的, 可直接连�?, 连接过程�?, 此热点一直有, 不管�?后是否需要其他方式进行验证操�?, 但凡连接成功, 即刻进行了对此热点的配置进行保存;
    b. 有密码的, 暂且不论何种加密手段, 只要用户输入密码, 点击连接, 如果连接途中, 此热点一直有, 不论连接成功还是失败, 都即刻对此热点的配置进行了保存操�?;  使用上述的方式获取到的WiFi的配�?, 就是上面进行操作保存的WiFi配置;
    c. 连接多个WiFi成功之后, 然后关闭WiFi, 下次�?启WiFi的时�?, 驱动会主动帮你连接这其中配置好的其中�?个WiFi;


## 获取对应�? ScanResult �? WifiConfiguration

通常, 此配置对应一�? BSSID, 可能�? null;
```````
List<WifiConfiguration> configs = wifiManager.getMatchingWifiConfig(scanResult);

// 可以打印�?下看具体的情�?:
if (configs == null || configs.isEmpty()) return;
for (WifiConfiguration config : configs) {
    Log.v(TAG, "config = " + config);
}
```````

## 连接WiFi

连接 WIFI, 安卓提供了两种方�?, �?种是通过配置连接, 另一种是通过 networkId 来连�?, 两种方式各有使用场景, 可根据需要�?�择使用, 封装后的函数如下: (反射可抽取方�?, 单独提炼, 此处不展�?.)
 
```````
public static void connectByConfig(WifiManager manager, WifiConfiguration config) {
    if (manager == null) {
        return;
    }
    try {
        Method connect = manager.getClass().getDeclaredMethod("connect", WifiConfiguration.class, Class.forName("android.net.wifi.WifiManager$ActionListener"));
        if (connect != null) {
            connect.setAccessible(true);
            connect.invoke(manager, config, null);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
// 使用 networkId 连接. 
public static void connectByNetworkId(WifiManager manager, int networkId) {
    if (manager == null) {
        return;
    }
    try {
        Method connect = manager.getClass().getDeclaredMethod("connect", int.class, Class.forName("android.net.wifi.WifiManager$ActionListener"));
        if (connect != null) {
            connect.setAccessible(true);
            connect.invoke(manager, networkId, null);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```````

## 保存网络

``````` 
public static void saveNetworkByConfig(WifiManager manager, WifiConfiguration config) {
    if (manager == null) {
        return;
    }
    try {
        Method save = manager.getClass().getDeclaredMethod("save", WifiConfiguration.class, Class.forName("android.net.wifi.WifiManager$ActionListener"));
        if (save != null) {
            save.setAccessible(true);
            save.invoke(manager, config, null);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```````

## 添加网络
``````` 
public static int addNetwork(WifiManager manager, WifiConfiguration config) {
    if (manager != null) {
        manager.addNetwork(config);
    }
}
```````

## 忘记网络
``````` 
public static void forgetNetwork(WifiManager manager, int networkId) {
    if (manager == null) {
        return;
    }
    try {
        Method forget = manager.getClass().getDeclaredMethod("forget", int.class, Class.forName("android.net.wifi.WifiManager$ActionListener"));
        if (forget != null) {
            forget.setAccessible(true);
            forget.invoke(manager, networkId, null);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```````

## 禁用网络
``````` 
public static void disableNetwork(WifiManager manager, int netId) {
    if (manager == null) {
        return;
    }
    try {
        Method disable = manager.getClass().getDeclaredMethod("disable", int.class, Class.forName("android.net.wifi.WifiManager$ActionListener"));
        if (disable != null) {
            disable.setAccessible(true);
            disable.invoke(manager, networkId, null);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```````

## 断开连接
```````
public static boolean disconnectNetwork(WifiManager manager) {
    return manager != null && manager.disconnect();
}
```````

## 短暂禁用网络
```````
public static void disableEphemeralNetwork(WifiManager manager, String SSID) {
    if (manager == null || TextUtils.isEmpty(SSID)) 
        return;
        try {
        Method disableEphemeralNetwork = manager.getClass().getDeclaredMethod("disableEphemeralNetwork", String.class);
        if (disableEphemeralNetwork != null) {
            disableEphemeralNetwork.setAccessible(true);
            disableEphemeralNetwork.invoke(manager, SSID);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```````

## WIFI监听
```````
/**
 * Author: 海晨�?.
 * Date: 2018/1/10
 * Desc: 网络广播监听
 */
public class NetworkReceiver extends BroadcastReceiver {
  public static final int STATE1 = 1;//密码错误
  public static final int STATE2 = 2;//连接成功
  public static final int STATE3 = 3;//连接失败
  public static final int STATE4 = 4;//正在获取ip地址
  public static final int STATE5 = 5;//正在连接

  @Override
  public void onReceive(Context context, Intent intent) {
    if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(intent.getAction())) {
      //密码错误广播,是不是正在获得IP地址
      int linkWifiResult = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, -1);
      if (linkWifiResult == WifiManager.ERROR_AUTHENTICATING) {
        //密码错误
        ToastUtils.showTipMsg(R.string.password_wrong);
        sendNetworkStateChange(new NetWorkInfo().setState(STATE1));
      }
      SupplicantState supplicantState = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
      NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(supplicantState);
      Log.v(Constants.HTTP_WZ, state.name());
      if (state == NetworkInfo.DetailedState.CONNECTING) {
        //正在连接
        ToastUtils.showTipMsg(R.string.linking);
        sendNetworkStateChange(new NetWorkInfo().setState(STATE5));
      } else if (state == NetworkInfo.DetailedState.FAILED
          || state == NetworkInfo.DetailedState.DISCONNECTING) {
        //连接失败
        sendNetworkStateChange(new NetWorkInfo().setState(STATE3));
        ToastUtils.showTipMsg(R.string.linked_failed);
      } else if (state == NetworkInfo.DetailedState.CONNECTED) {
        //连接成功
        ToastUtils.showTipMsg(R.string.linked_success);
      } else if (state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
        //正在获取ip地址
        sendNetworkStateChange(new NetWorkInfo().setState(STATE4));
      } else if (state == NetworkInfo.DetailedState.IDLE) {
        //闲置�?
        ConnectivityManager connectManager = (ConnectivityManager) BaseApplication.getInstance()
            .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectManager != null) {
          activeNetworkInfo = connectManager.getActiveNetworkInfo();
        }
        if (activeNetworkInfo == null) {
          sendNetworkStateChange(new NetWorkInfo().setState(STATE3));
          ToastUtils.showTipMsg(R.string.linked_failed);
        }
      }
    } else if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
      // 监听wifi的打�?与关闭，与wifi的连接无�?
      int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
      Log.v(Constants.HTTP_WZ, "wifiState:" + wifiState);
      switch (wifiState) {
        case WifiManager.WIFI_STATE_DISABLING://正在停止0
          ToastUtils.showTipMsg(R.string.close_wifi);
          break;
        case WifiManager.WIFI_STATE_DISABLED://已停�?1
          break;
        case WifiManager.WIFI_STATE_UNKNOWN://未知4
          break;
        case WifiManager.WIFI_STATE_ENABLING://正在打开2
          ToastUtils.showTipMsg(R.string.opening_wifi);
          break;
        case WifiManager.WIFI_STATE_ENABLED://已开�?3
          break;
        default:
          break;
      }
    } else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
      // 监听wifi的连接状态即是否连上了一个有效无线路�?
      Parcelable parcelableExtra = intent
          .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
      if (null != parcelableExtra) {
        // 获取联网状�?�的NetWorkInfo对象
        NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
        //获取的State对象则代表着连接成功与否等状�?
        NetworkInfo.State state = networkInfo.getState();
        //判断网络是否已经连接
        boolean isConnected = state == NetworkInfo.State.CONNECTED;
        Log.v(Constants.HTTP_WZ, "isConnected:" + isConnected);
        if (isConnected) {
          ToastUtils.showTipMsg(R.string.linked_success);
          sendNetworkStateChange(new NetWorkInfo().setState(STATE2));
        }
      }
    }
  }

  /**
   * 发�?�网络状态eventBus.
   *
   * @param info info
   */
  private void sendNetworkStateChange(NetWorkInfo info) {
    EventBus.getDefault().post(info);
  }

  @SuppressWarnings("unused")
  private String getConnectionType(int type) {
    String connType = "";
    if (type == ConnectivityManager.TYPE_MOBILE) {
      connType = "移动网络";
    } else if (type == ConnectivityManager.TYPE_WIFI) {
      connType = "WIFI网络";
    }
    return connType;
  }

  public static class NetWorkInfo {
    public int state;//1密码错误�?2:连接成功�?3:连接失败

    private NetWorkInfo setState(int state) {
      this.state = state;
      return this;
    }
  }
}
```````

# 参�??
[Android-WiFi�?发之 WifiManager](https://www.jianshu.com/p/67aaf1fdb921?open_source=weibo_search)

[Android WIFI功能—�?�WifiManager](https://www.jianshu.com/p/4b59ca68b994)

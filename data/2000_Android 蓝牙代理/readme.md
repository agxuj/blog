# 获取配置代理

以A2DP为例

``````````
mBtAdapter.getProfileProxy(getContext(), new BluetoothProfile.ServiceListener() {
    @Override
    public void onServiceConnected(int profile, BluetoothProfile proxy) {
        mA2dp = (BluetoothA2dp) proxy;
    }

    @Override
    public void onServiceDisconnected(int profile) {
        mA2dp = null;
    }
}, BluetoothProfile.A2DP);
``````````


# BluetoothProfile 包含的方法

``````````

// 获取连接设备。
public List<BluetoothDevice> getConnectedDevices();

// 获取特定状态的设备。
public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states);

// 获取设备的连接状态。
public int getConnectionState(BluetoothDevice device);

``````````


# Android 其他蓝牙代理
``````````
// Headset and Handsfree profile
int HEADSET = 1;

// A2DP profile.
int A2DP = 2;

// Health Profile
int HEALTH = 3;

// HID Host
int HID_HOST = 4;

// PAN Profile
int PAN = 5;

// PBAP
int PBAP = 6;

// GATT
int GATT = 7;

// GATT_SERVER
int GATT_SERVER = 8;

// MAP Profile
int MAP = 9;

// SAP Profile
int SAP = 10;

// A2DP Sink Profile
int A2DP_SINK = 11;

// AVRCP Controller Profile
int AVRCP_CONTROLLER = 12;

// AVRCP Target Profile
int AVRCP = 13;

// Headset Client - HFP HF Role
int HEADSET_CLIENT = 16;

// PBAP Client
int PBAP_CLIENT = 17;

// MAP Messaging Client Equipment (MCE)
int MAP_CLIENT = 18;

// HID Device
int HID_DEVICE = 19;

// Object Push Profile (OPP)
int OPP = 20;

// Hearing Aid Device
int HEARING_AID = 21;
``````````

# 参考

[A2DP -- 百度百科](https://baike.baidu.com/item/A2DP/9819331?fr=aladdin)

[OPP -- 百度百科](https://baike.baidu.com/item/OPP/13977547?fr=aladdin)

[蓝牙协议 HFP,HSP,A2DP,AVRCP,OPP,PBAP](https://blog.csdn.net/peng825223208/article/details/52072680/)

[蓝牙 HID 剖析 (一) ](https://blog.csdn.net/xiaoxiaopengbo/article/details/51658396)

[蓝牙PAN](https://blog.csdn.net/hh20161314/article/details/104531424)
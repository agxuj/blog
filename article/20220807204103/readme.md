<h1 style="font-size: 2.5em;"> Android ������������</h1>
 


本地蓝牙适配器，是所有蓝牙交互的入口点，表示蓝牙设备自身的一个蓝牙设备�?�配器，整个系统只有�?个蓝牙�?�配器�?��?�过它可以发现其他蓝牙设备，查询绑定（配对）设备的列表，使用已知�? Mac 地址实例�? BluetoothDevice 以及创建 BluetoothServerSocket 用来侦听来自其他设备的�?�信�?

使用 Android Bluetooth API 来完成使用蓝牙进行�?�信的四项主要任务：
1. 设置蓝牙
1. 查找�?部区域内的配对设备或可用设备
1. 连接设备
1. 以及在设备之间传输数据�??

# 基本操作
``````
BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();//获取适配�?

mBtAdapter.getName();//获取名字
mBtAdapter.setName("");//设置名字

mBtAdapter.enable();//打开蓝牙
mBtAdapter.disable();//关闭蓝牙
mBtAdapter.isEnabled();//判断蓝牙是否可用

Set<BluetoothDevice> bondedDevices = mBtAdapter.getBondedDevices();//获取已绑定设备列�?
``````

# 搜索设备
``````
mBtAdapter.startDiscovery();//�?始搜索蓝牙设�?
mBtAdapter.cancelDiscovery();//取消搜索蓝牙设备

//搜索设备返回结果
private BroadcastReceiver mReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                //todo
            }
        } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
		} else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
		}
    }
};
``````

# 绑定/配对设备
``````
device.createBond();//绑定设备

//配对确认广播接收�?
public final class BluetoothPairingRequest extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		//获取pin�?
		int pin = intent.getIntExtra(BluetoothDevice.EXTRA_PAIRING_KEY, BluetoothDevice.ERROR);
        String pinStr = Integer.toString(pin);
	
		boolean allowed = true;
		boolean always = true;
		Intent newIntent = new Intent();
		BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		bluetoothDevice.setPairingConfirmation(allowed);
		String ReturnPackage = intent.getStringExtra(BluetoothDevice.EXTRA_PACKAGE_NAME);
		String ReturnClass = intent.getStringExtra(BluetoothDevice.EXTRA_CLASS_NAME);
		if (ReturnPackage != null && ReturnClass != null) {
			newIntent.setClassName(ReturnPackage, ReturnClass);
		}
		int mRequestType = intent.getIntExtra(
			BluetoothDevice.EXTRA_ACCESS_REQUEST_TYPE,
			BluetoothDevice.REQUEST_TYPE_PHONEBOOK_ACCESS);
		newIntent.putExtra(BluetoothDevice.EXTRA_CONNECTION_ACCESS_RESULT,
			allowed ? BluetoothDevice.CONNECTION_ACCESS_YES : BluetoothDevice.CONNECTION_ACCESS_NO);
			newIntent.putExtra(BluetoothDevice.EXTRA_ACCESS_REQUEST_TYPE, mRequestType);
			newIntent.putExtra(BluetoothDevice.EXTRA_ALWAYS_ALLOWED, always);
			newIntent.putExtra(BluetoothDevice.EXTRA_DEVICE, bluetoothDevice);
		context.sendBroadcast(newIntent, android.Manifest.permission.BLUETOOTH_ADMIN);
	}
}

//AndroidManifest.xml中的配置
<receiver android:name="com.desaysv.bluetooth.PairingRequest">
	<intent-filter>
		<action android:name="android.bluetooth.device.action.PAIRING_REQUEST" />
	</intent-filter>
</receiver>

//绑定状�?�返�?
private BroadcastReceiver mReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			switch (device.getBondState()) {
				case BluetoothDevice.BOND_BONDING://正在配对
					break;
				case BluetoothDevice.BOND_BONDED://配对成功
					break;
				case BluetoothDevice.BOND_NONE://取消配对/未配�?
					break;
				default:
					break;
			}
        }
    }
};
``````


# AudioManager
## 音量调节

在Android中，是有几种类型的音量可以调节：

1. STREAM_ALARM 警报
1. STREAM_MUSIC 音乐回放即媒体音量
1. STREAM_RING 铃声
1. STREAM_SYSTEM 系统
1. STREAM_VOICE_CALL 通话


Android中的所有音量都是通过：AudioManager 去管理的，也是通过它去调节：

`````
//获取系统的Audio管理者
AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);  
//最大音量
int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//当前音量
int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
`````

日常的项目中，有两种的调节方式：

1. 渐进式,即像手动按音量键一样,一步一步增加或减少：
通过AudioManager的adjustStreamVolume方法

`````
/**
* 参数说明：
* int streamType  需要调整的音量类型（以下常量定义于AudioManager类中）
*                 STREAM_ALARM 警报
*                 STREAM_MUSIC 音乐回放即媒体音量
*                 STREAM_NOTIFICATION 窗口顶部状态栏Notification,
*                 STREAM_RING 铃声
*                 STREAM_SYSTEM 系统
*                 STREAM_VOICE_CALL 通话
*                 STREAM_DTMF 双音多频,不是很明白什么东西
*
* int direction   调整的方向，加或者减（以下常量定义于AudioManager类中）
*                 ADJUST_LOWER 降低音量
*                 ADJUST_RAISE 升高音量
*                 ADJUST_SAME 保持不变,这个主要用于向用户展示当前的音量
*
* int flags   附加的一些参数, 
*             参数的说明参考API文档：http://www.android-doc.com/reference/android/media/AudioManager.html
*                 FLAG_PLAY_SOUND 调整音量时播放声音
*                 FLAG_SHOW_UI 调整时显示音量条,就是按音量键出现的那个
*                 0表示什么也没有
*/
public void adjustStreamVolume (int  streamType, int direction, int flags)

`````


2.直接自定义调节的幅度（步进值），直接调节音量大小。
`````
/**
* 这里，可以根据获取的最大音量去自己设置调整的幅度。
* 参数说明：
*     第一个，第三个和上面的一样
*     第二个参数 (int index): 把音量调整到index值。
*/
public void setStreamVolume (int streamType, int index, int flags)
`````

## 音量按键的监听
直接上代码，需要在Activity中，重写其按键的回调，方法有二：

`````
//方法一
@Override
public boolean dispatchKeyEvent(KeyEvent event) {
    int action = event.getAction();
    if (action ==KeyEvent.ACTION_DOWN) {
        tv.setText("+++++++++ACTION_DOWN++++++"+ count++);
        return true;
    }
    if (action== KeyEvent.ACTION_UP) {
        tv.setText("+++++ACTION_UP++++++++++");
        return true;
    }
    return super.dispatchKeyEvent(event);
}

//方法二
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    switch (keyCode) {
    case KeyEvent.KEYCODE_VOLUME_DOWN:
        tv.setText("-----------------"+count);
        count--;
        return true;
    case KeyEvent.KEYCODE_VOLUME_UP:
        tv.setText("++++++++++++++++"+ count);
        count++;
        return true;
    case KeyEvent.KEYCODE_VOLUME_MUTE:
        tv.setText("MUTE");
        return true;
    }
    return super.onKeyDown(keyCode, event);
}
`````

## 音量监听
``````
public class MainActivity extends AppCompatActivity {
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        myRegisterReceiver();

    }

    private void myRegisterReceiver() {
        MyVolumeReceiver mVolumeReceiver = new MyVolumeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");
        registerReceiver(mVolumeReceiver, filter);
    }

    private class MyVolumeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
                int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                //int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                Toast.makeText(getApplicationContext(), currVolume + "", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
``````

## 静音
`````
AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

//静音 并显示UI 第三个参数 flags=1
mAudioManager.adjustSuggestedStreamVolume(AudioManager.ADJUST_TOGGLE_MUTE, AudioManager.STREAM_MUSIC, 1);

//静音 不显示UI 第三个参数 flags=0
mAudioManager.adjustSuggestedStreamVolume(AudioManager.ADJUST_TOGGLE_MUTE, AudioManager.STREAM_MUSIC, 0); 
`````


# AudioService

android音频系统，分为两个部分：数据流和策略；

**数据流**：描述了音频数据从数据源流向目的地的流程，之前我们分析的AudioTrack,AudioFlinger就是数据流；

**策略**：管理及控制数据流的路径与呈现方式，之前我们分析的AudioPolicyService，以及等会我们要分析的AudioService，它们都是策略得范畴；

1. 音频系统在java层中基本上不参与数据流的，AudioService这个系统服务包含或者使用了几乎所有与音频有关的内容，所以说AudioService是音频系统在java层的大本营.

1. AudioManager拥有AudioService的Bp端，是AudioService在客户端的一个代理，几乎所有客户端对AudioManager进行的请求，最终都会交由AudioService实现.

1. AudioService的功能实现依赖于AudioSystem类，AudioSystem无法实例化，它是java层到native层的代理，AudioService通过它与AudioPolicyService以及AudioFlinger进行通信.

## 简单使用

``````
IAudioService audioService = IAudioService.Stub.asInterface(ServiceManager.getService(Context.AUDIO_SERVICE));

audioService.notifyVolumeControllerVisible(new  IVolumeController.Stub(){
    @Override
    public void displaySafeVolumeWarning(int i) throws RemoteException {

    }

    @Override
    public void volumeChanged(int i, int i1) throws RemoteException {

    }

    @Override
    public void masterMuteChanged(int i) throws RemoteException {

    }

    @Override
    public void setLayoutDirection(int i) throws RemoteException {

    }

    @Override
    public void dismiss() throws RemoteException {

    }

    @Override
    public void setA11yMode(int i) throws RemoteException {

    }
}, true);
``````
 


# 各类所在位置

* AudioManager.Java
framework\base\media\java\android\media\AudioManager.java

* AudioService.java
framework\base\media\java\android\media\AudioService.java

* AudioSystem.java
framework\base\media\java\android\media\AudioSystem.java


# 参考
[Android音量控制](https://www.jianshu.com/p/3005c7b8e7d4)

[android 静音方法](https://blog.csdn.net/fuyunfengfan/article/details/89227086)



<h1 style="font-size: 2.5em;"> WEBǰ�� H5 ��ǩ Audio �����ܽ�</h1>
 



移动端的浏览器是 不支持autoplay属�?�，不支持自动播放的。桌面端的浏览器也�?�渐不支持了�?

除非添加 muted 属�??(静音播放)。此外，chrome浏览器有[MEI的策略](https://developers.google.com/web/updates/2017/09/autoplay-policy-changes#mei)

�?发的时�?�要�?2种情况，�?种是可以自动播放，另�?种是不可以自动播�?

判断是否可以自动播放的代码如下：
``````
function testAutoPlay () {
    // 返回�?个promise以告诉调用�?�检测结�?
    return new Promise(resolve => {
        let audio = document.createElement('audio');
        // require�?个本地文件，会变成base64格式
        audio.src = require('@/assets/empty-audio.mp3');
        document.body.appendChild(audio);
        let autoplay = true;
        // play返回的是�?个promise
        audio.play().then(() => {
            // 支持自动播放
            autoplay = true;
        }).catch(err => {
            // 不支持自动播�?
            autoplay = false;
        }).finally(() => {
            audio.remove();
            // 告诉调用者结�?
            resolve(autoplay);
        });
    });
}
``````

## Audio 支持 的音频格�?


## 参�??
[监听Audio的播放状态](https://blog.csdn.net/qq_42894622/article/details/89421145)

[audio自动播放完美兼容实现方案](https://blog.csdn.net/tan9374/article/details/88991723)

[video下autoplay属�?�无效�?��?�添加muted属�?�](https://blog.csdn.net/taiyangmiaomiao/article/details/80266625)
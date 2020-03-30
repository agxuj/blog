# 20170320084300

<script src="../js/index.js"></script>
<div id="content"></div>



在Style中设置window的属性android:windowIsFloating

`````
<style name="DialogStyle" parent="android:Theme.Holo.Dialog">
     <item name="android:windowIsFloating">false</item>//浮层，set false就可以全屏了
     <item name="android:windowNoTitle">true</item>
     <item name="android:windowBackground">@android:color/transparent</item>
</style>
`````

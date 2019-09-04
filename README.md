# LockPattern —— Android 手势解锁
Android 手势解锁、九宫格图案解锁（默认为3*3九空格，支持N*N宫格扩展）。

## Download ##
### Gradle ###
```gradle
dependencies {
    implementation 'com.henley.android:lockpattern:1.0.0'
}
```

### APK Demo ###

下载 [APK-Demo](https://github.com/HenleyLee/LockPattern/raw/master/app/app-release.apk)

## 功能介绍 ##
 - 支持自定义各状态下（未操作时、操作时以及操作出错时）线颜色、填充色和线宽；
 - 支持自定义各种状态下（未操作时、操作时以及操作出错时）每个 Cell 的样式和连接线样式；
 - 支持设置图案绘制完成后延迟自动清除的时长（默认1秒）；
 - 支持是否显示连接线（默认显示）；
 - 支持是否跳过中间点（默认不跳过）；
 - 支持是否触碰震动反馈（默认不震动）；
 - 支持指示器辅助控件可选择使用；
 - 业务逻辑（至少连点几个点、验证时最多可出错几次等）须自定义。

## 效果图 ##
![](/screenshots/status_normal.jpg)
![](/screenshots/status_check.jpg)
![](/screenshots/status_error.jpg)

![](/screenshots/status_custom_1.jpg)
![](/screenshots/status_custom_2.jpg)
![](/screenshots/status_custom_3.jpg)

## 致谢 ##
 - [LockPattern](https://github.com/sym900728/LockPattern)
 - [PatternLocker](https://github.com/ihsg/PatternLocker)


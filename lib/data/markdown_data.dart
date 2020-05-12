const flutter_screenutil = """
## 使用方法:

### 安装依赖：

安装之前请查看最新版本
```
dependencies:
  flutter:
    sdk: flutter
  # 添加依赖
  flutter_screenutil: ^1.0.2
```

### 在每个使用的地方导入包：
```
import 'package:flutter_screenutil/flutter_screenutil.dart';
```

### 初始化并设置适配尺寸及字体大小是否根据系统的“字体大小”辅助选项来进行缩放
在使用之前请设置好设计稿的宽度和高度，传入设计稿的宽度和高度(单位px)
一定在MaterialApp的home中的页面设置(即入口文件，只需设置一次),以保证在每次使用之前设置好了适配尺寸:

```
//填入设计稿中设备的屏幕尺寸

//默认 width : 1080px , height:1920px , allowFontScaling:false
ScreenUtil.init(context);

//假如设计稿是按iPhone6的尺寸设计的(iPhone6 750*1334) 
ScreenUtil.init(context, width: 750, height: 1334);

//设置字体大小根据系统的“字体大小”辅助选项来进行缩放,默认为false
ScreenUtil.init(context, width: 750, height: 1334, allowFontScaling: true);
    
```

### 使用

### API

```dart
    ScreenUtil().setWidth(540)  (sdk>=2.6 : 540.w) //根据屏幕宽度适配尺寸
    ScreenUtil().setHeight(200) (sdk>=2.6 : 200.h) //根据屏幕高度适配尺寸
    ScreenUtil().setSp(24)      (sdk>=2.6 : 24.sp)  //适配字体
    ScreenUtil().setSp(24, allowFontScalingSelf: true)  (sdk>=2.6 : 24.ssp) //适配字体(根据系统的“字体大小”辅助选项来进行缩放)

    ScreenUtil.pixelRatio       //设备的像素密度
    ScreenUtil.screenWidth      //设备宽度
    ScreenUtil.screenHeight     //设备高度
    ScreenUtil.bottomBarHeight  //底部安全区距离，适用于全面屏下面有按键的
    ScreenUtil.statusBarHeight  //状态栏高度 刘海屏会更高  单位px
    ScreenUtil.textScaleFactor //系统字体缩放比例

    ScreenUtil().scaleWidth  // 实际宽度的dp与设计稿px的比例
    ScreenUtil().scaleHeight // 实际高度的dp与设计稿px的比例
```
""";
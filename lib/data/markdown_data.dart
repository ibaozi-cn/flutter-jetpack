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

const flutter_provider = """

## 如何使用

我们先来使用它，然后在根据用例分析源码，找到我们想要的答案，先看一个简单的例子

### step 1

第一步定义一个ChangeNotifier，来负责数据的变化通知

```
class Counter with ChangeNotifier {
  int _count = 0;

  int get count => _count;

  void increment() {
    _count++;
    notifyListeners();
  }

}
```

### step 2

第二步，用ChangeNotifierProvider来订阅Counter，不难猜出，ChangeNotifierProvider肯定是InheritedWidget的包装类，负责将Counter的状态共享给子Widget，我这里将ChangeNotifierProvider放到了Main函数中，并在整个Widget树的顶端，当然这里是个简单的例子，我这么写问题不大，但你要考虑，如果是特别局部的状态，请将ChangeNotifierProvider放到局部的地方而不是全局，希望你能明白我的用意

```
void main() {
  runApp(
    /// Providers are above [MyApp] instead of inside it, so that tests
    /// can use [MyApp] while mocking the providers
    MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => Counter()),
      ],
      child: MyApp(),
    ),
  );
}
```

### step 3

第三步，接收数据通过Consumer<Counter>，Consumer是个消费者，它负责消费ChangeNotifierProvider生产的数据

```
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  const MyHomePage({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    print('MyHomePage build');
    return Scaffold(
      appBar: AppBar(
        title: const Text('Example'),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text('You have pushed the button this many times:'),

            /// Extracted as a separate widget for performance optimization.
            /// As a separate widget, it will rebuild independently from [MyHomePage].
            ///
            /// This is totally optional (and rarely needed).
            /// Similarly, we could also use [Consumer] or [Selector].
            Consumer<Counter>(
              builder: (BuildContext context, Counter value, Widget child) {
                return Text('{value.count}');
              },
            ),
            OtherWidget(),
            const OtherWidget2()
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        /// Calls `context.read` instead of `context.watch` so that it does not rebuild
        /// when [Counter] changes.
        onPressed: () => context.read<Counter>().increment(),
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}
```
""";
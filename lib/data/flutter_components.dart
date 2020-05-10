import 'components.dart';
import 'const.dart';

class FlutterComponents {
  static const String headTitle = "Flutter Jetpack Components";
  static const String headDesc =
      "收集github中开源的库，并提供相关简介和用例，辅助你去构建一个完整的Flutter项目。";

  static const List<Map> componentsTitles = [
    {"Basic": "辅助项目基础运行"},
    {"Components": "自定义组件，丰富多样，简单易用"},
    {"Architecture": "架构组件，帮您设计稳定，易维护的应用"},
    {"Native": "扩展原生功能"}
  ];

  static List<Components> selectComponentsDataForBasic() {
    List<Components> list = [];
    list.add(Components("flutter_screenutil", "Flutter屏幕适应，字体适应，获取屏幕信息",
        "https://github.com/OpenFlutter/flutter_screenutil",
        componentsList: [
          Components(
              "pub",
              "A flutter plugin for adapting screen and font size.Let your UI display a reasonable layout on different screen sizes!",
              "https://pub.dev/packages/flutter_screenutil",
              leading: pub),
          Components(
            "__white",
            "flutter 屏幕尺寸适配 字体大小适配",
            "https://juejin.im/post/5bc5a56a5188255c352d88fe",
            leading: white__,
          ),
          Components(
            "Toeii",
            "Flutter屏幕适配方案之Flutter_ScreenUtil",
            "https://toeii.github.io/2019/05/16/Flutter%E5%B1%8F%E5%B9%95%E9%80%82%E9%85%8D%E6%96%B9%E6%A1%88%E4%B9%8Bflutter_ScreenUtil/",
            leading: about_BY_gentle,
          ),
        ]));
    list.add(Components("dio", "Dart功能强大的Http客户端，它支持拦截器，FormData，请求取消，文件下载，超时等",
        "https://github.com/flutterchina/dio",
        componentsList: [
          Components(
              "pub",
              "A powerful Http client for Dart, which supports Interceptors, Global configuration, FormData, Request Cancellation, File downloading, Timeout etc.",
              "https://pub.dev/packages/dio",
              leading: pub),
          Components("wendux", "Flutter Http请求开源库-dio",
              "https://juejin.im/post/5b04c954f265da0b9c10fb61",
              leading: wendux),
          Components("Ashmi kattel", "DIO IN FLUTTER",
              "https://medium.com/@ashmikattel/dio-in-flutter-ad6ba26aee36",
              leading: Ashmi_kattel),
        ]));
    list.add(Components("bot_toast", "一个非常好用的Flutter Toast库",
        "https://github.com/MMMzq/bot_toast",
        componentsList: [
          Components("pub", "A really easy to use flutter toast library!",
              "https://pub.dev/packages/bot_toast",
              leading: pub),
          Components("mmmzq", "Flutter bot_toast是怎样炼成的",
              "https://juejin.im/post/5d2b0261f265da1bb003edc6",
              leading: mmmzq),
        ]));
    list.add(Components("giffy_dialog", "Flutter Dialog，用于构建快速方便的对话框",
        "https://github.com/xsahil03x/giffy_dialog",
        componentsList: [
          Components(
              "pub",
              "A beautiful and custom alert dialog for flutter highly inspired from FancyAlertDialog-Android",
              "https://pub.dev/packages/giffy_dialog",
              leading: pub),
        ]));
    return list;
  }

  static List<Components> selectComponentsDataForArc() {
    List<Components> list = [];
    list.add(Components("fish-redux", "阿里的，基于 Redux 数据管理的组装式应用框架，适用于构建中大型的复杂应用",
        "https://github.com/alibaba/fish-redux",
        componentsList: [
          Components(
              "pub",
              "Fish Redux is an assembled flutter application framework based on Redux state management. It is suitable for building medium and large applications.",
              "https://pub.dev/packages/fish_redux",
              leading: pub)
        ]));
    list.add(Components("bloc", "官方推荐Bloc架构，可预测的状态管理库，有助于实现BLoC设计模式",
        "https://github.com/felangel/bloc",
        componentsList: [
          Components(
              "pub",
              "A Flutter package that helps implement the BLoC pattern.",
              "https://pub.dev/packages/flutter_bloc",
              leading: pub)
        ]));
    list.add(Components(
        "flutter_boost",
        "一个Flutter插件，以最少的工作量将Flutter混合集成到您现有的本机应用程序中",
        "https://github.com/alibaba/flutter_boost",
        componentsList: [
          Components(
              "pub",
              "A next-generation Flutter-Native hybrid solution. FlutterBoost is a Flutter plugin which enables hybrid integration of Flutter for your existing native apps with minimum efforts.The philosophy of FlutterBoost is to use Flutter as easy as using a WebView. Managing Native pages and Flutter pages at the same time is non-trivial in an existing App. FlutterBoost takes care of page resolution for you. The only thing you need to care about is the name of the page(usually could be an URL). ",
              "https://pub.dev/packages/flutter_boost",
              leading: pub)
        ]));
    list.add(Components("fluro", "路由库，它添加了灵活的路由选项，例如通配符，命名参数和清晰的路由定义",
        "https://github.com/theyakka/fluro",
        componentsList: [
          Components(
              "pub",
              "The brightest, hippest, coolest router for Flutter.",
              "https://pub.dev/packages/fluro",
              leading: pub)
        ]));
    list.add(Components("provider", "Provider是Google I/O 2019大会宣布的官方推荐的状态管理方式",
        "https://github.com/rrousselGit/provider",
        componentsList: [
          Components(
              "pub",
              "A wrapper around InheritedWidget to make them easier to use and more reusable.",
              "https://pub.dev/packages/provider",
              leading: pub)
        ]));
    return list;
  }

  static List<Components> selectComponentsDataForBehavior() {
    List<Components> list = [];
    list.add(Components("sqflite", "sqlite flutter plugin 支持 Android、Ios、MacOS",
        "https://github.com/tekartik/sqflite",
        componentsList: [
          Components(
              "pub",
              "SQLite plugin for Flutter. Supports iOS, Android and MacOS.",
              "https://pub.dev/packages/sqflite",
              leading: pub)
        ]));
    list.add(Components(
        "flutter_blue",
        "Bluetooth plugin for Flutter 支持 Android、Ios",
        "https://github.com/pauldemarco/flutter_blue",
        componentsList: [
          Components(
              "pub",
              "FlutterBlue is a bluetooth plugin for Flutter, a new mobile SDK to help developers build modern apps for iOS and Android.",
              "https://pub.dev/packages/flutter_blue",
              leading: pub)
        ]));
    list.add(Components(
        "url_launcher",
        "Flutter插件，用于在移动平台中启动URL。支持iOS和Android。",
        "https://github.com/flutter/plugins/tree/master/packages/url_launcher/url_launcher",
        componentsList: [
          Components(
              "pub",
              "A Flutter plugin for launching a URL in the mobile platform. Supports iOS and Android.",
              "https://pub.dev/packages/url_launcher",
              leading: pub)
        ]));
    return list;
  }

  static List<Components> selectComponentsDataForUI() {
    List<Components> list = [];
    list.add(Components("flutter_dynamic_form", "一个简单易用的动态表单框架",
        "https://github.com/ibaozi-cn/flutter_dynamic_form",
        componentsList: [
          Components("i校长", "Flutter 动态表单Dynamic FormField架构设计",
              "https://www.jianshu.com/p/42759cd7eba5",
              leading: ixiaozhang)
        ]));
    list.add(Components("FlutterScreens", "一套UI组件，包括登录，按钮，加载程序，以及一些其他小部件的集合。",
        "https://github.com/samarthagarwal/FlutterScreens",
        componentsList: [
          Components(
              "pub",
              "A Flutter plugin to manage the device's screen on Android and iOS.",
              "https://pub.dev/packages/flutter_screen",
              leading: pub)
        ]));
    list.add(Components("flutter_map", "Flutter地图小部件",
        "https://github.com/johnpryan/flutter_map",
        componentsList: [
          Components(
              "pub",
              "A Dart implementation of Leaflet for Flutter apps.",
              "https://pub.dev/packages/flutter_map",
              leading: pub)
        ]));
    return list;
  }
}

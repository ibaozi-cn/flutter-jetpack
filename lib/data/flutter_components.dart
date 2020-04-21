import 'components.dart';

class FlutterComponents {

  static const String headTitle = "Flutter Jetpack Components";
  static const String headDesc = "收集github中开源的库，并提供相关简介和用例，辅助你去构建一个完整的Flutter项目。";

  static const List<Map> componentsTitles=[
    {
      "Basic":"辅助项目基础运行"
    },{
      "Components":"自定义组件，丰富多样，简单易用"
    },{
      "Architecture":"架构组件，帮您设计稳定，易维护的应用"
    },{
      "Native":"扩展原生功能"
    }
  ];

  static List<Components> selectComponentsDataForBasic() {
    List<Components> list = [];
    list.add(Components("flutter_screenutil", "Flutter屏幕适应，字体适应，获取屏幕信息",
        "https://github.com/OpenFlutter/flutter_screenutil"));
    list.add(Components("dio", "Dart功能强大的Http客户端，它支持拦截器，FormData，请求取消，文件下载，超时等",
        "https://github.com/flutterchina/dio"));
    list.add(Components("bot_toast", "一个非常好用的Flutter Toast库",
        "https://github.com/MMMzq/bot_toast"));
    list.add(Components("giffy_dialog", "Flutter Dialog，用于构建快速方便的对话框",
        "https://github.com/xsahil03x/giffy_dialog"));
    return list;
  }

  static List<Components> selectComponentsDataForArc() {
    List<Components> list = [];
    list.add(Components(
        "fish-redux",
        "阿里的，基于 Redux 数据管理的组装式应用框架，适用于构建中大型的复杂应用",
        "https://github.com/alibaba/fish-redux"));
    list.add(Components("bloc", "官方推荐Bloc架构，可预测的状态管理库，有助于实现BLoC设计模式",
        "https://github.com/felangel/bloc"));
    list.add(Components(
        "flutter_boost",
        "一个Flutter插件，以最少的工作量将Flutter混合集成到您现有的本机应用程序中",
        "https://github.com/alibaba/flutter_boost"));
    list.add(Components(
        "fluro",
        "路由库，它添加了灵活的路由选项，例如通配符，命名参数和清晰的路由定义",
        "https://github.com/theyakka/fluro"));
    list.add(Components("provider", "Provider是Google I/O 2019大会宣布的官方推荐的状态管理方式",
        "https://github.com/rrousselGit/provider"));
    return list;
  }

  static List<Components> selectComponentsDataForBehavior() {
    List<Components> list = [];
    list.add(Components("sqflite", "sqlite flutter plugin 支持 Android、Ios、MacOS",
        "https://github.com/tekartik/sqflite"));
    list.add(Components("flutter_blue", "Bluetooth plugin for Flutter 支持 Android、Ios",
        "https://github.com/pauldemarco/flutter_blue"));
    list.add(Components("url_launcher", "Flutter插件，用于在移动平台中启动URL。支持iOS和Android。",
        "https://github.com/flutter/plugins/tree/master/packages/url_launcher/url_launcher"));
    list.add(Components("url_launcher_web", "url_launcher的Web实现",
        "https://github.com/flutter/plugins/tree/master/packages/url_launcher/url_launcher_web"));
    return list;
  }

  static List<Components> selectComponentsDataForUI() {
    List<Components> list = [];
    list.add(Components("flutter_dynamic_form", "一个简单易用的动态表单框架",
        "https://github.com/ibaozi-cn/flutter_dynamic_form"));
    list.add(Components("Best-Flutter-UI-Templates", "UI 模版，对所有人完全免费",
        "https://github.com/mitesh77/Best-Flutter-UI-Templates"));
    list.add(Components("Flutter-UI-Kit", "真实应用程序UI的最终合集",
        "https://github.com/iampawan/Flutter-UI-Kit"));
    list.add(Components("FlutterScreens", "一套UI组件，包括登录，按钮，加载程序，以及一些其他小部件的集合。",
        "https://github.com/samarthagarwal/FlutterScreens"));
    list.add(Components("flutter_map", "Flutter地图小部件",
        "https://github.com/johnpryan/flutter_map"));
    return list;
  }
}

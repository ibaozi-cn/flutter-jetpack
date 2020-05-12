import 'components.dart';

class AndroidComponents{

  static const String headTitle = "Android Jetpack Components";
  static const String headDesc = "Android Jetpack 组件是库的集合，这些库是为协同工作而构建的，不过也可以单独采用，同时利用 Kotlin 语言功能帮助您提高工作效率。可全部使用，也可混合搭配！";
  static const List<Map> componentsTitles=[
    {
      "Basic":"基础组件可提供横向功能，例如向后兼容性、测试和 Kotlin 语言支持。"
    },{
      "Components":"界面组件可提供微件和辅助程序，让您的应用不仅简单易用，还能带来愉悦体验"
    },{
      "Architecture":"架构组件可帮助您设计稳健、可测试且易维护的应用。"
    },{
      "Behavior":"行为组件可帮助您的应用与标准 Android 服务（如通知、权限、分享和 Google 助理）相集成。"
    }
  ];

  static List<Components> selectComponentsDataForBasic() {
    List<Components> list = [];
    list.add(Components("Android KTX", "编写更简洁、惯用的 Kotlin 代码",
        "https://developer.android.google.cn/kotlin/ktx?hl=zh-cn"));
    list.add(Components("多 dex 处理", "为具有多个 DEX 文件的应用提供支持",
        "https://developer.android.google.cn/studio/build/multidex?hl=zh-cn"));
    list.add(Components("Auto", "有助于开发 Android Auto 应用的组件",
        "https://developer.android.com/auto"));
    list.add(Components("检测", "从 Android Studio 中快速检测基于 Kotlin 或 Java 的代码",
        "https://developer.android.google.cn/studio/profile/benchmark?hl=zh-cn"));
    list.add(Components("安全", "按照安全最佳做法读写加密文件和共享偏好设置。",
        "https://developer.android.google.cn/topic/security/data?hl=zh-cn"));
    list.add(Components("测试", "用于单元和运行时界面测试的 Android 测试框架",
        "https://developer.android.com/training/testing/"));
    return list;
  }

  static List<Components> selectComponentsDataForArc() {
    List<Components> list = [];
    list.add(Components("数据绑定", "以声明方式将可观察数据绑定到界面元素",
        "https://developer.android.google.cn/topic/libraries/data-binding?hl=zh-cn"));
    list.add(Components("Lifecycle", "管理您的 Activity 和 Fragment 生命周期",
        "https://developer.android.google.cn/topic/libraries/architecture/lifecycle?hl=zh-cn"));
    list.add(Components("LiveData", "在底层数据库更改时通知视图",
        "https://developer.android.google.cn/topic/libraries/architecture/livedata?hl=zh-cn"));
    list.add(Components("Navigation", "处理应用内导航所需的一切",
        "https://developer.android.google.cn/guide/navigation/?hl=zh-cn"));
    list.add(Components("Paging", "逐步从您的数据源按需加载信息",
        "https://developer.android.google.cn/topic/libraries/architecture/paging?hl=zh-cn"));
    list.add(Components("Room", "流畅地访问 SQLite 数据库",
        "https://developer.android.google.cn/topic/libraries/architecture/room?hl=zh-cn"));
    list.add(Components("ViewModel", "以注重生命周期的方式管理界面相关的数据",
        "https://developer.android.google.cn/topic/libraries/architecture/viewmodel?hl=zh-cn"));
    list.add(Components("WorkManager", "管理您的 Android 后台作业",
        "https://developer.android.google.cn/topic/libraries/architecture/workmanager?hl=zh-cn"));
    return list;
  }

  static List<Components> selectComponentsDataForBehavior() {
    List<Components> list = [];
    list.add(Components("CameraX", "轻松地向应用中添加相机功能",
        "https://developer.android.google.cn/training/camerax?hl=zh-cn"));
    list.add(Components("下载管理器", "安排和管理大量下载任务",
        "https://developer.android.google.cn/reference/android/app/DownloadManager?hl=zh-cn"));
    list.add(Components("媒体和播放", "用于媒体播放和路由（包括 Google Cast）的向后兼容 API",
        "https://developer.android.google.cn/guide/topics/media-apps/media-apps-overview?hl=zh-cn"));
    list.add(Components("通知", "用于媒体播放和路由（包括 Google Cast）的向后兼容 API",
        "https://developer.android.google.cn/guide/topics/ui/notifiers/notifications?hl=zh-cn"));
    list.add(Components("权限", "用于检查和请求应用权限的兼容性 API",
        "https://developer.android.google.cn/guide/topics/permissions?hl=zh-cn"));
    list.add(Components("偏好设置", "创建交互式设置屏幕",
        "https://developer.android.google.cn/guide/topics/ui/settings?hl=zh-cn"));
    list.add(Components("共享", "提供适合应用操作栏的共享操作",
        "https://developer.android.google.cn/training/sharing/shareaction?hl=zh-cn"));
    list.add(Components("切片", "创建可在应用外部显示应用数据的灵活界面元素",
        "https://developer.android.google.cn/guide/slices?hl=zh-cn"));
    return list;
  }

  static List<Components> selectComponentsDataForUI() {
    List<Components> list = [];
    list.add(Components("动画和过渡", "移动微件和在屏幕之间过渡",
        "https://developer.android.google.cn/training/animation?hl=zh-cn"));
    list.add(Components("表情和符号", "在旧版平台上启用最新的表情符号字体",
        "https://developer.android.google.cn/guide/topics/ui/look-and-feel/emoji-compat?hl=zh-cn"));
    list.add(Components("Fragment", "组件化界面的基本单位",
        "https://developer.android.google.cn/guide/components/fragments?hl=zh-cn"));
    list.add(Components("布局", "使用不同的算法布置微件",
        "https://developer.android.google.cn/guide/topics/ui/declaring-layout?hl=zh-cn"));
    list.add(Components("调色板", "从调色板中提取出有用的信息",
        "https://developer.android.google.cn/training/material/palette-colors?hl=zh-cn"));
    return list;
  }

}
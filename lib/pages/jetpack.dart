import 'package:flutter/material.dart';
import 'package:jetpack/data/components.dart';
import 'package:jetpack/data/flutter_components.dart';
import 'package:jetpack/styles/colors.dart';
import 'package:jetpack/styles/text_styles.dart';
import 'package:jetpack/widgets/responsive_widget.dart';
import 'package:jetpack/widgets/widget_component.dart';
import 'package:url_launcher/url_launcher.dart';

class PageFlutterJetPack extends StatefulWidget {
  @override
  _PageFlutterJetPackState createState() => _PageFlutterJetPackState();
}

class _PageFlutterJetPackState extends State<PageFlutterJetPack> {
  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Expanded(
          flex: 3,
          child: SingleChildScrollView(
            child: Container(
              padding: EdgeInsets.all(16),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  _buildHead(),
                  _buildComponentsTitle("Basic", "辅助项目基础运行"),
                  _buildComponentsContent(
                      FlutterComponents.selectComponentsDataForBasic()),
                  _buildComponentsTitle("Components", "自定义组件，丰富多样，简单易用"),
                  _buildComponentsContent(
                      FlutterComponents.selectComponentsDataForUI()),
                  _buildComponentsTitle("Architecture", "架构组件，帮您设计稳定，易维护的应用"),
                  _buildComponentsContent(
                      FlutterComponents.selectComponentsDataForArc()),
                  _buildComponentsTitle("Native", "扩展原生功能"),
                  _buildComponentsContent(
                      FlutterComponents.selectComponentsDataForBehavior()),
                  SizedBox(
                    height: 40,
                  ),
                  ResponsiveWidget.isSmallScreen(context)
                      ? _buildTags()
                      : Container(),
                  _buildFooter(),
                ],
              ),
            ),
          ),
        ),
        ResponsiveWidget.isLargeScreen(context)
            ? Expanded(
                flex: 1,
                child: _buildTags(),
              )
            : Container(),
        ResponsiveWidget.isMediumScreen(context)
            ? Expanded(
                flex: 1,
                child: _buildTags(),
              )
            : Container()
      ],
    );
  }

  _buildTags() {
    return SingleChildScrollView(
      child: Column(
        children: <Widget>[
          Text(
            'Tags',
            style: TextStyles.logo,
          ),
          SizedBox(
            height: 20,
          ),
          Wrap(
            spacing: 2,
            runSpacing: 5,
            children: <Widget>[
              _buildItemTag('Sqlite'),
              _buildItemTag('Dialog'),
              _buildItemTag('Button'),
              _buildItemTag('Location'),
              _buildItemTag('Menu'),
              _buildItemTag('Toast'),
              _buildItemTag('Markdown'),
              _buildItemTag('WebView'),
              _buildItemTag('Input'),
              _buildItemTag('Form'),
              _buildItemTag('Video'),
            ],
          ),
          SizedBox(
            height: 20,
          ),
        ],
      ),
    );
  }

  _buildItemTag(text) {
    return MaterialButton(
      color: titleColor,
      onPressed: () {},
      child: Text(
        text,
        style: TextStyles.menuItem.copyWith(color: Colors.white),
      ),
    );
  }

  _buildComponentsTitle(title, subtitle) {
    return Align(
      alignment: Alignment.topLeft,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          SizedBox(
            height: 20,
          ),
          Text(
            title,
            style: TextStyles.logo
                .copyWith(fontWeight: FontWeight.w500, fontSize: 28),
          ),
          SizedBox(
            height: 5,
          ),
          Text(
            subtitle,
            style: TextStyles.menuItem.copyWith(fontSize: 16),
          ),
          SizedBox(
            height: 20,
          ),
        ],
      ),
    );
  }

  _buildComponentsContent(List<Components> componentsList) {
    return GridView.builder(
        shrinkWrap: true,
        itemCount: componentsList.length,
        physics: NeverScrollableScrollPhysics(),
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: _buildGridViewCount(),
        ),
        itemBuilder: (context, index) {
          return WidgetComponents(
            components: componentsList[index],
          );
        });
  }

  _buildGridViewCount() {
    if (ResponsiveWidget.isLargeScreen(context)) return 4;
    if (ResponsiveWidget.isMediumScreen(context)) return 3;
    if (ResponsiveWidget.isSmallScreen(context)) return 2;
    return 1;
  }

  Widget _buildFooter() {
    return Column(
      children: <Widget>[
        Padding(
          padding: EdgeInsets.all(6.0),
          child: Row(
            mainAxisSize: MainAxisSize.max,
            children: <Widget>[
              Align(
                child: _buildCopyRightText(),
                alignment: Alignment.centerLeft,
              ),
            ],
          ),
        ),
      ],
    );
  }

  Widget _buildCopyRightText() {
    return InkWell(
      onTap: () {
        launch("http://www.beian.miit.gov.cn/");
      },
      child: Text(
        '京ICP备案20002589号-2',
        style: TextStyles.menuItem.copyWith(
          color: Colors.blue,
          fontSize: ResponsiveWidget.isSmallScreen(context) ? 8 : 10.0,
        ),
      ),
    );
  }

  _buildHead() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        SizedBox(
          height: 40,
        ),
        Text(
          'Flutter Jetpack Components',
          style: TextStyles.logo
              .copyWith(fontWeight: FontWeight.w500, fontSize: 28),
        ),
        SizedBox(
          height: 5,
        ),
        Text(
          '收集github中开源的库，并提供相关简介和用例，辅助你去构建一个完整的Flutter项目。',
          style: TextStyles.menuItem.copyWith(fontSize: 16),
        ),
        SizedBox(
          height: 40,
        ),
      ],
    );
  }
}

class PageAndroidJetPack extends StatefulWidget {
  @override
  _PageAndroidJetPackState createState() => _PageAndroidJetPackState();
}

class _PageAndroidJetPackState extends State<PageAndroidJetPack> {
  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Expanded(
          flex: 3,
          child: SingleChildScrollView(
            child: Container(
              padding: EdgeInsets.all(16),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  _buildHead(),
                  _buildComponentsTitle("Basic", "基础组件可提供横向功能，例如向后兼容性、测试和 Kotlin 语言支持。"),
                  _buildComponentsContent(
                      Components.selectComponentsDataForBasic()),
                  _buildComponentsTitle("Components", "界面组件可提供微件和辅助程序，让您的应用不仅简单易用，还能带来愉悦体验"),
                  _buildComponentsContent(
                      Components.selectComponentsDataForUI()),
                  _buildComponentsTitle("Architecture", "架构组件可帮助您设计稳健、可测试且易维护的应用。"),
                  _buildComponentsContent(
                      Components.selectComponentsDataForArc()),
                  _buildComponentsTitle("Behavior", "行为组件可帮助您的应用与标准 Android 服务（如通知、权限、分享和 Google 助理）相集成。"),
                  _buildComponentsContent(
                      Components.selectComponentsDataForBehavior()),
                  SizedBox(
                    height: 40,
                  ),
                  ResponsiveWidget.isSmallScreen(context)
                      ? _buildTags()
                      : Container(),
                  _buildFooter(),
                ],
              ),
            ),
          ),
        ),
        ResponsiveWidget.isLargeScreen(context)
            ? Expanded(
          flex: 1,
          child: _buildTags(),
        )
            : Container(),
        ResponsiveWidget.isMediumScreen(context)
            ? Expanded(
          flex: 1,
          child: _buildTags(),
        )
            : Container()
      ],
    );
  }

  _buildTags() {
    return SingleChildScrollView(
      child: Column(
        children: <Widget>[
          Text(
            'Tags',
            style: TextStyles.logo,
          ),
          SizedBox(
            height: 20,
          ),
          Wrap(
            spacing: 2,
            runSpacing: 5,
            children: <Widget>[
              _buildItemTag('Sqlite'),
              _buildItemTag('Dialog'),
              _buildItemTag('Button'),
              _buildItemTag('Location'),
              _buildItemTag('Menu'),
              _buildItemTag('Toast'),
              _buildItemTag('Markdown'),
              _buildItemTag('WebView'),
              _buildItemTag('Input'),
              _buildItemTag('Form'),
              _buildItemTag('Video'),
            ],
          ),
          SizedBox(
            height: 20,
          ),
        ],
      ),
    );
  }

  _buildItemTag(text) {
    return MaterialButton(
      color: titleColor,
      onPressed: () {},
      child: Text(
        text,
        style: TextStyles.menuItem.copyWith(color: Colors.white),
      ),
    );
  }

  _buildComponentsTitle(title, subtitle) {
    return Align(
      alignment: Alignment.topLeft,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          SizedBox(
            height: 20,
          ),
          Text(
            title,
            style: TextStyles.logo
                .copyWith(fontWeight: FontWeight.w500, fontSize: 28),
          ),
          SizedBox(
            height: 5,
          ),
          Text(
            subtitle,
            style: TextStyles.menuItem.copyWith(fontSize: 16),
          ),
          SizedBox(
            height: 20,
          ),
        ],
      ),
    );
  }

  _buildComponentsContent(List<Components> componentsList) {
    return GridView.builder(
        shrinkWrap: true,
        itemCount: componentsList.length,
        physics: NeverScrollableScrollPhysics(),
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: _buildGridViewCount(),
        ),
        itemBuilder: (context, index) {
          return WidgetComponents(
            components: componentsList[index],
          );
        });
  }

  _buildGridViewCount() {
    if (ResponsiveWidget.isLargeScreen(context)) return 4;
    if (ResponsiveWidget.isMediumScreen(context)) return 3;
    if (ResponsiveWidget.isSmallScreen(context)) return 2;
    return 1;
  }

  Widget _buildFooter() {
    return Column(
      children: <Widget>[
        Padding(
          padding: EdgeInsets.all(6.0),
          child: Row(
            mainAxisSize: MainAxisSize.max,
            children: <Widget>[
              Align(
                child: _buildCopyRightText(),
                alignment: Alignment.centerLeft,
              ),
            ],
          ),
        ),
      ],
    );
  }

  Widget _buildCopyRightText() {
    return InkWell(
      onTap: () {
        launch("http://www.beian.miit.gov.cn/");
      },
      child: Text(
        '京ICP备案20002589号-2',
        style: TextStyles.menuItem.copyWith(
          color: Colors.blue,
          fontSize: ResponsiveWidget.isSmallScreen(context) ? 8 : 10.0,
        ),
      ),
    );
  }

  _buildHead() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        SizedBox(
          height: 40,
        ),
        Text(
          'Android Jetpack Components',
          style: TextStyles.logo
              .copyWith(fontWeight: FontWeight.w500, fontSize: 28),
        ),
        SizedBox(
          height: 5,
        ),
        Text(
          'Android Jetpack 组件是库的集合，这些库是为协同工作而构建的，不过也可以单独采用，同时利用 Kotlin 语言功能帮助您提高工作效率。可全部使用，也可混合搭配！',
          style: TextStyles.menuItem.copyWith(fontSize: 16),
        ),
        SizedBox(
          height: 40,
        ),
      ],
    );
  }
}


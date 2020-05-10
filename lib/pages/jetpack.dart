import 'package:flutter/material.dart';
import 'package:jetpack/data/components.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:jetpack/widgets/responsive_widget.dart';
import 'package:jetpack/widgets/widget_component.dart';
import 'package:url_launcher/url_launcher.dart';

// ignore: must_be_immutable
class PageJetPack extends StatefulWidget {
  String headTitle;
  String headDesc;
  List<Map> componentsTitles;
  List<Components> selectComponentsDataForBasic;
  List<Components> selectComponentsDataForArc;
  List<Components> selectComponentsDataForBehavior;
  List<Components> selectComponentsDataForUI;

  PageJetPack(
      {this.headTitle,
      this.headDesc,
      this.componentsTitles,
      this.selectComponentsDataForBasic,
      this.selectComponentsDataForArc,
      this.selectComponentsDataForBehavior,
      this.selectComponentsDataForUI});

  @override
  _PageJetPackState createState() => _PageJetPackState();
}

class _PageJetPackState extends State<PageJetPack> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Expanded(
            flex: 3,
            child: CustomScrollView(
              slivers: <Widget>[
                SliverToBoxAdapter(
                  child: _buildHead(),
                ),
                _buildComponentsSliverTitle(
                    "Basic", widget.componentsTitles[0].values.first),
                _buildComponentsContentSliverGrid(
                    widget.selectComponentsDataForBasic),
                _buildComponentsSliverTitle(
                    "Components", widget.componentsTitles[1].values.first),
                _buildComponentsContentSliverGrid(
                    widget.selectComponentsDataForUI),
                _buildComponentsSliverTitle(
                    "Architecture", widget.componentsTitles[2].values.first),
                _buildComponentsContentSliverGrid(
                    widget.selectComponentsDataForArc),
                _buildComponentsSliverTitle(
                    widget.componentsTitles[3].keys.first,
                    widget.componentsTitles[3].values.first),
                _buildComponentsContentSliverGrid(
                    widget.selectComponentsDataForBehavior),
                SliverToBoxAdapter(
                  child: heightBoxBig,
                ),
                ResponsiveWidget.isSmallScreen(context)
                    ? SliverToBoxAdapter(
                        child: _buildTags(),
                      )
                    : SliverToBoxAdapter(),
                SliverToBoxAdapter(
                  child: _buildFooter(),
                )
              ],
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
      ),
    );
  }

  _buildTags() {
    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.all(4.0),
        child: Column(
          children: <Widget>[
            ResponsiveWidget.isSmallScreen(context)
                ? Container()
                : Column(
                    children: <Widget>[
                      Image.asset(WE_CHAT_SCAN),
                      heightBoxMid,
                      Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: textMid(CHAT_GROUP_TEXT),
                      ),
                    ],
                  ),
            textBig('Tags'),
            heightBoxBig,
            Wrap(
              spacing: 5,
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
            heightBoxBig,
          ],
        ),
      ),
    );
  }

  _buildItemTag(content) {
    return MaterialButton(
      onPressed: () {},
      child: text(content,isChinese: false),
    );
  }

  _buildComponentsTitle(title, subtitle) {
    return Align(
      alignment: Alignment.topLeft,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          heightBoxBig,
          textMid(title),
          heightBoxSmall,
          text(subtitle),
          heightBoxBig,
        ],
      ),
    );
  }

  _buildComponentsSliverTitle(title, subtitle) {
    return SliverToBoxAdapter(child: _buildComponentsTitle(title, subtitle));
  }

  _buildComponentsContentSliverGrid(List<Components> componentsList) {
    return SliverGrid(
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: _buildGridViewCount(),
        childAspectRatio: 1.0,
      ),
      delegate: SliverChildBuilderDelegate(
        (context, index) {
          return WidgetComponents(
            components: componentsList[index],
          );
        },
        childCount: componentsList.length,
      ),
    );
  }

  _buildGridViewCount() {
    if (ResponsiveWidget.isLargeScreen(context)) return 4;
    if (ResponsiveWidget.isMediumScreen(context)) return 3;
    if (ResponsiveWidget.isSmallScreen(context)) return 2;
    return 1;
  }

  _buildFooter() {
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

  _buildCopyRightText() {
    return InkWell(
      onTap: () {
        launch("http://www.beian.miit.gov.cn/");
      },
      child: textSmall('京ICP备案20002589号-2'),
    );
  }

  _buildHead() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        heightBoxBig,
        textBig(widget.headTitle, isChinese: false),
        heightBoxBig,
        textMid(widget.headDesc),
        heightBoxBig,
      ],
    );
  }
}

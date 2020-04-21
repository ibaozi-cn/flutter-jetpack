import 'package:flutter/material.dart';
import 'package:jetpack/data/components.dart';
import 'package:jetpack/styles/colors.dart';
import 'package:jetpack/styles/text_styles.dart';
import 'package:jetpack/widgets/responsive_widget.dart';
import 'package:jetpack/widgets/widget_component.dart';
import 'package:url_launcher/url_launcher.dart';

// ignore: must_be_immutable
class PageFlutterJetPack extends StatefulWidget {

  String headTitle;
  String headDesc;
  List<Map> componentsTitles;
  List<Components> selectComponentsDataForBasic;
  List<Components> selectComponentsDataForArc;
  List<Components> selectComponentsDataForBehavior;
  List<Components> selectComponentsDataForUI;

  PageFlutterJetPack({this.headTitle, this.headDesc, this.componentsTitles,
      this.selectComponentsDataForBasic, this.selectComponentsDataForArc,
      this.selectComponentsDataForBehavior, this.selectComponentsDataForUI});

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
                  _buildComponentsTitle("Basic", widget.componentsTitles[0].values.first),
                  _buildComponentsContent(
                      widget.selectComponentsDataForBasic),
                  _buildComponentsTitle("Components", widget.componentsTitles[1].values.first),
                  _buildComponentsContent(
                      widget.selectComponentsDataForUI),
                  _buildComponentsTitle("Architecture", widget.componentsTitles[2].values.first),
                  _buildComponentsContent(
                      widget.selectComponentsDataForArc),
                  _buildComponentsTitle( widget.componentsTitles[3].keys.first, widget.componentsTitles[3].values.first),
                  _buildComponentsContent(
                      widget.selectComponentsDataForBehavior),
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
      child: Padding(
        padding: const EdgeInsets.all(4.0),
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
            SizedBox(
              height: 20,
            ),
          ],
        ),
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
            childAspectRatio:0.9,
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
          widget.headTitle,
          style: TextStyles.logo
              .copyWith(fontWeight: FontWeight.w500, fontSize: 28),
        ),
        SizedBox(
          height: 5,
        ),
        Text(
          widget.headDesc,
          style: TextStyles.menuItem.copyWith(fontSize: 16),
        ),
        SizedBox(
          height: 40,
        ),
      ],
    );
  }
}

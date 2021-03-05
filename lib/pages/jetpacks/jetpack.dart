import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:jetpack/data/beans.dart';
import 'package:jetpack/data/components.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:jetpack/widgets/widget_responsive.dart';
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
  double currentPageValue = 0.0;
  Timer timer;
  PageController _controller = PageController(
    initialPage: 0,
  );

  @override
  void initState() {
    _controller.addListener(() {
      setState(() {
        currentPageValue = _controller.page;
      });
    });
    const period = const Duration(seconds: 5);
    Timer.periodic(period, (timer) {
      this.timer = timer;
      //到时回调
      if (Blog.blogList.length <= 1) return;
      if (currentPageValue.round() == Blog.blogList.length - 1) return;
      _controller.animateToPage((_controller.page.round() + 1),
          duration: Duration(milliseconds: 1500),
          curve: Curves.fastLinearToSlowEaseIn);
    });
    super.initState();
  }

  @override
  void dispose() {
    _controller.dispose();
    //取消定时器，避免无限回调
    if (timer != null) {
      timer.cancel();
      timer = null;
    }
    super.dispose();
  }

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
                WidgetResponsive.isSmallScreen(context)
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
          WidgetResponsive.isLargeScreen(context)
              ? Expanded(
                  flex: 1,
                  child: _buildTags(),
                )
              : Container(),
          WidgetResponsive.isMediumScreen(context)
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
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: <Widget>[
            heightBoxSmall,
            WidgetResponsive.isSmallScreen(context)
                ? Container()
                : Column(
                    children: <Widget>[
                      Image.asset(icon_we_chat_scan),
                      heightBoxMid,
                      Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: Text(CHAT_GROUP_TEXT),
                      ),
                    ],
                  ),
            Text('Tags'),
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

  _buildItemTag(content, {onTap}) {
    return Card(
      child: InkWell(
        onTap: onTap ?? () {},
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Text(content),
        ),
      ),
    );
  }

  Widget _buildComponentsTitle(title, subtitle) {
    return Align(
      alignment: Alignment.topLeft,
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(
              title,
              style: TextStyle(fontSize: 20),
            ),
            heightBoxSmall,
            Text(subtitle),
          ],
        ),
      ),
    );
  }

  _buildComponentsSliverTitle(title, subtitle) {
    return SliverToBoxAdapter(child: _buildComponentsTitle(title, subtitle));
  }

  _buildComponentsContentSliverGrid(List<Components> componentsList) {
    return SliverFixedExtentList(
      itemExtent: 120,
      delegate: SliverChildBuilderDelegate((BuildContext context, int index) {
        return WidgetComponents(
          components: componentsList[index],
        );
      }, childCount: componentsList.length),
    );
  }

  _buildFooter() {
    return Column(
      children: <Widget>[
        Padding(
          padding: EdgeInsets.all(6.0),
          child: Row(
            mainAxisSize: MainAxisSize.min,
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
      child: Text('京ICP备案20002589号-2 @Create by Flutter Web'),
    );
  }

  _buildHead() {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          heightBoxSmall,
          WidgetResponsive.isSmallScreen(context)
              ? _buildSmallPageView()
              : _buildLargePageView(),
          heightBoxBig,
          Text(
            widget.headTitle,
            style: TextStyle(fontSize: 20),
          ),
          heightBoxSmall,
          Text(widget.headDesc),
          heightBoxSmall,
        ],
      ),
    );
  }

  _buildLargePageView() {
    return Container(
      height: 220,
      child: PageView.builder(
        itemCount: Blog.blogList.length,
        controller: _controller,
        physics: BouncingScrollPhysics(),
        itemBuilder: (context, position) {
          return InkWell(
            onTap: () {
              _launchURL(Blog.blogList[position].url);
            },
            child: Card(
              child: Row(
                children: [
                  Expanded(
                    flex: 1,
                    child: Image.asset(
                      Blog.blogList[position].head,
                      fit: BoxFit.fill,
                    ),
                  ),
                  Expanded(
                    flex: 1,
                    child: Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Text(
                            Blog.blogList[position].nikeName,
                            style: TextStyle(fontSize: 20),
                          ),
                          heightBoxSmall,
                          Text(Blog.blogList[position].title),
                        ],
                      ),
                    ),
                  )
                ],
              ),
            ),
          );
        },
      ),
    );
  }

  _buildSmallPageView() {
    return Container(
      height: 300,
      child: PageView.builder(
        itemCount: Blog.blogList.length,
        controller: _controller,
        physics: BouncingScrollPhysics(),
        itemBuilder: (context, position) {
          return InkWell(
            onTap: () {
              _launchURL(Blog.blogList[position].url);
            },
            child: Card(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Image.asset(
                    Blog.blogList[position].head,
                  ),
                  heightBoxSmall,
                  Padding(
                    padding: const EdgeInsets.only(left: 8.0, right: 8.0),
                    child: Text(
                      Blog.blogList[position].nikeName,
                      style: TextStyle(fontSize: 20),
                    ),
                  ),
                  heightBoxSmall,
                  Padding(
                    padding: const EdgeInsets.only(left: 8.0, right: 8.0),
                    child: Text(Blog.blogList[position].title),
                  ),
                ],
              ),
            ),
          );
        },
      ),
    );
  }

  _launchURL(url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}

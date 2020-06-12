import 'package:flutter/material.dart';
import 'package:flutter_markdown/flutter_markdown.dart';
import 'package:jetpack/data/components.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:jetpack/widgets/widget_responsive.dart';
import 'package:url_launcher/url_launcher.dart';

class PageComponentsDetail extends StatefulWidget {
  final Components components;
  final int index;

  const PageComponentsDetail({Key key, this.components, this.index})
      : super(key: key);

  @override
  _PageComponentsDetailState createState() => _PageComponentsDetailState();
}

class _PageComponentsDetailState extends State<PageComponentsDetail>
    with SingleTickerProviderStateMixin {
  List _tabs = ['示例', '博客'];
  TabController _tabController;

  @override
  void initState() {
    _tabController = TabController(
        length: _tabs.length, vsync: this, initialIndex: widget.index);
    _tabController.addListener(() {
      if (_tabController.indexIsChanging) {
        setState(() {});
      }
    });
    super.initState();
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Padding(
        padding: padding(context),
        child: Scaffold(
          appBar: AppBar(
            elevation: 0,
            title: _buildTitle(),
            bottom: TabBar(
              controller: _tabController,
              tabs: _tabs
                  .map((e) => Tab(
                        child: Text(e,),
                      ))
                  .toList(),
            ),
          ),
          body: TabBarView(
            controller: _tabController,
            children: <Widget>[
              Demos(markDownData: widget.components.markDownData,),
              Blogs(
                componentsList: widget.components.componentsList,
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildTitle() {
    return Text(widget.components.title);
  }
}

class Demos extends StatelessWidget {

  final String markDownData ;
  const Demos({Key key, this.markDownData}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Markdown(
          data: markDownData??markdownData,
          selectable: true,
          imageDirectory: 'https://raw.githubusercontent.com'),
    );
  }
}

class Blogs extends StatelessWidget {

  final List<Components> componentsList;

  const Blogs({Key key, this.componentsList}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      children: <Widget>[
        Expanded(
          flex: 2,
          child: Column(
            children: <Widget>[
              heightBoxBig,
              Expanded(
                child: ListView.builder(
                    shrinkWrap: true,
                    itemCount: componentsList.length,
                    itemBuilder: (context, index) {
                      Components components = componentsList[index];
                      return Card(
                        child: ListTile(
                          onTap: () {
                            launch(components.url);
                          },
                          title: Text(components.title,style: fontStyleTitle,),
                          subtitle: Text(components.subTitle,style: fontStyleSubTitle,),
                          leading: Image.asset(components.leading),
                        ),
                      );
                    }),
              ),
            ],
          ),
        ),
        WidgetResponsive.isLargeScreen(context)
            ? Expanded(
                flex: 1,
                child: Column(
                  children: <Widget>[
                    heightBoxBig,
                    Card(
                      child: Image.asset(blogger),
                    )
                  ],
                ),
              )
            : Container()
      ],
    );
  }
}

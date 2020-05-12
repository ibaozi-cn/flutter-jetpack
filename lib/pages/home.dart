import 'dart:async';

import 'package:flutter/material.dart';
import 'package:jetpack/pages/menu_about.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:jetpack/widgets/responsive_widget.dart';
import 'package:jetpack/util/screen_utils.dart';

import 'drawer.dart';
import 'menu_home_new.dart';
import 'menu_setting.dart';

class PageHome extends StatefulWidget {
  @override
  _PageHomeState createState() => _PageHomeState();
}

class _PageHomeState extends State<PageHome> {
  var _scaffoldKey = GlobalKey<ScaffoldState>();
  @override
  Widget build(BuildContext context) {
    ScreenUtil.instance = ScreenUtil.getInstance()..init(context);
    return Material(
        child: Padding(
      padding: padding(context),
      child: Scaffold(
        key: _scaffoldKey,
        appBar: AppBar(
          elevation: 0,
          title: _buildTitle(),
          actions: ResponsiveWidget.isSmallScreen(context)
              ? _buildSmallScreenAction(context)
              : _buildLargeScreenActions(context),
          leading: ResponsiveWidget.isSmallScreen(context)
              ? IconButton(
                  icon: Icon(
                    Icons.menu,
                  ),
                  onPressed: () {
                    _scaffoldKey.currentState.openDrawer();
                  },
                )
              : null,
        ),
        drawer: _buildDrawer(context),
        body: StreamBuilder<Object>(
          initialData: 0,
          stream: homeBloc.stream,
          builder: (context, snapshot) {
            return _getDrawerItemWidget(snapshot.data);
          }
        ),
      ),
    ));
  }

  Widget _buildTitle() {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.end,
      children: <Widget>[
        textLogoTitle("Jetpack"),
        textLogoSubTitle(".net.cn"),
      ],
    );
  }

  _buildLargeScreenActions(BuildContext context) {
    return <Widget>[
      MaterialButton(
        child: textMenuAction('主页'),
        onPressed: () {
          homeBloc.changeSelectedDrawerIndex(0);
          if (ResponsiveWidget.isSmallScreen(context)) Navigator.pop(context);
        },
      ),
      MaterialButton(
        child: textMenuAction('关于'),
        onPressed: () {
          homeBloc.changeSelectedDrawerIndex(1);
          if (ResponsiveWidget.isSmallScreen(context)) Navigator.pop(context);
        },
      ),
      MaterialButton(
        child: textMenuAction('设置'),
        onPressed: () {
          homeBloc.changeSelectedDrawerIndex(2);
          if (ResponsiveWidget.isSmallScreen(context)) Navigator.pop(context);
        },
      ),
    ];
  }

  _buildDrawer(BuildContext context) {
    return ResponsiveWidget.isSmallScreen(context)
        ? Drawer(
            child: WidgetDrawer(),
          )
        : null;
  }

 Widget _getDrawerItemWidget(int selectedDrawerIndex) {
    switch (selectedDrawerIndex) {
      case 0:
        return WidgetMenuNewHome();
      case 1:
        return WidgetMenuAbout();
      case 2:
        return WidgetMenuSetting();
    }
    return Container();
  }

  _buildSmallScreenAction(BuildContext context) {
    return <Widget>[
      IconButton(
        icon: Icon(Icons.group_add),
        onPressed: () {
          Navigator.of(context).pushNamed("/pageChatGroup");
        },
      ),
    ];
  }
  @override
  void dispose() {
    homeBloc.dispose();
    super.dispose();
  }

}

class HomeBloc{

  final _selectedDrawerIndexController = StreamController<int>();

  get changeSelectedDrawerIndex => _selectedDrawerIndexController.sink.add;

  get stream => _selectedDrawerIndexController.stream;

  dispose(){
    _selectedDrawerIndexController.close();
  }

}

final homeBloc = HomeBloc();
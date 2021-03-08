import 'dart:async';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:jetpack/pages/home/menu_about.dart';
import 'package:jetpack/pages/home/menu_collaborators.dart';
import 'package:jetpack/pages/home/menu_leave_msg.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:jetpack/widgets/widget_cupertino_tabbar.dart';
import 'package:jetpack/widgets/widget_responsive.dart';
import 'package:jetpack/util/screen_utils.dart';
import 'package:url_launcher/url_launcher.dart';

import '../../main.dart';
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
          title: buildWebSiteLogo(),
          actions: WidgetResponsive.isSmallScreen(context)
              ? _buildSmallScreenAction(context)
              : _buildLargeScreenActions(context),
          leading: WidgetResponsive.isSmallScreen(context)
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
            }),
      ),
    ));
  }

  

  _buildLargeScreenActions(BuildContext context) {
    return <Widget>[
      MaterialButton(
        child: Text('主页'),
        onPressed: () {
          homeBloc.changeSelectedDrawerIndex(0);
        },
      ),
      MaterialButton(
        child: Text('关于'),
        onPressed: () {
          homeBloc.changeSelectedDrawerIndex(1);
        },
      ),
      MaterialButton(
        child: Text('留言'),
        onPressed: () {
          if (!kIsWeb) {
            homeBloc.changeSelectedDrawerIndex(4);
          } else {
            launch('https://support.qq.com/product/166532');
          }
        },
      ),
      MaterialButton(
        child: Text('友链'),
        onPressed: () {
          homeBloc.changeSelectedDrawerIndex(3);
        },
      ),
      paddingTabBar(),
    ];
  }

  _buildDrawer(BuildContext context) {
    return WidgetResponsive.isSmallScreen(context)
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
      case 3:
        return MenuCollaborators();
      case 4:
        return MenuLeaveMsg();
    }
    return Container();
  }

  int cupertinoTabBarIValue = 0;

  int cupertinoTabBarIValueGetter() => cupertinoTabBarIValue;

  _buildSmallScreenAction(BuildContext context) {
    return <Widget>[
      paddingTabBar(),
    ];
  }

  Padding paddingTabBar() {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: CupertinoTabBar(
        cupertinoTabBarIValue == 0
            ? const Color(0xFFF0F0F0)
            : const Color(0xFF333333),
        cupertinoTabBarIValue == 0
            ? const Color(0xFFFFFFFF)
            : const Color(0xFF222222),
        [
          Icon(
            Icons.brightness_5,
            color: Colors.deepOrange,
            size: cupertinoTabBarIValue == 0 ? 12.75 * 2 : 18.75,
          ),
          Icon(Icons.brightness_4,
              color: Colors.deepOrange,
              size: cupertinoTabBarIValue == 1 ? 12.75 * 2 : 18.75)
        ],
        cupertinoTabBarIValueGetter,
        (int index) {
          setState(() {
            cupertinoTabBarIValue = index;
          });
          if (index == 1) {
            bloc.changeTheTheme(AppTheme.DARK_THEME);
          } else {
            bloc.changeTheTheme(AppTheme.LIGHT_THEME);
          }
        },
      ),
    );
  }

  @override
  void dispose() {
    homeBloc.dispose();
    super.dispose();
  }
}

Widget buildWebSiteLogo() {
  return Row(
    crossAxisAlignment: CrossAxisAlignment.end,
    children: <Widget>[
      Text(
        "Jetpack",
        style: fontStyleEnglish.copyWith(color: Colors.deepOrange,fontSize: 16),
      ),
      Text(
        ".net.cn",
        style: TextStyle(fontSize: 14),
      ),
    ],
  );
}

class HomeBloc {
  final _selectedDrawerIndexController = StreamController<int>();

  get changeSelectedDrawerIndex => _selectedDrawerIndexController.sink.add;

  get stream => _selectedDrawerIndexController.stream;

  dispose() {
    _selectedDrawerIndexController.close();
  }
}

final homeBloc = HomeBloc();

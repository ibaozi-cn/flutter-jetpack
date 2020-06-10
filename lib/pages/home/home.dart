import 'dart:async';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
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
  var _switchValue = false;

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
        },
      ),
      MaterialButton(
        child: textMenuAction('关于'),
        onPressed: () {
          homeBloc.changeSelectedDrawerIndex(1);
        },
      ),
      MaterialButton(
        child: textMenuAction('留言'),
        onPressed: () {
          if (!kIsWeb) {
            homeBloc.changeSelectedDrawerIndex(4);
            print('launch leave msg page');
          } else {
            launch('https://support.qq.com/product/166532');
            print('launch https://support.qq.com/product/166532');
          }
        },
      ),
      MaterialButton(
        child: textMenuAction('合作者'),
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
        const Color(0xFFd4d7dd),
        const Color(0xFF393e46),
        [
          Text(
            " 􀆬 ",
            style: TextStyle(
              color: Colors.white,
              fontSize: cupertinoTabBarIValue == 0 ? 8.75 * 2 : 10.75,
              fontWeight: FontWeight.w400,
              fontFamily: "SFProRounded",
            ),
            textAlign: TextAlign.center,
          ),
          Text(
            " 􀇁 ",
            style: TextStyle(
              color: Colors.white,
              fontSize: cupertinoTabBarIValue == 1 ? 8.75 * 2 : 10.75,
              fontWeight: FontWeight.w400,
              fontFamily: "SFProRounded",
            ),
            textAlign: TextAlign.center,
          ),
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

class HomeBloc {
  final _selectedDrawerIndexController = StreamController<int>();

  get changeSelectedDrawerIndex => _selectedDrawerIndexController.sink.add;

  get stream => _selectedDrawerIndexController.stream;

  dispose() {
    _selectedDrawerIndexController.close();
  }
}

final homeBloc = HomeBloc();

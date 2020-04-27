import 'package:flutter/material.dart';
import 'package:jetpack/pages/menu_about.dart';
import 'package:jetpack/pages/menu_home.dart';
import 'package:jetpack/styles/colors.dart';
import 'package:jetpack/widgets/responsive_widget.dart';
import 'package:jetpack/util/screen_utils.dart';
import 'package:jetpack/styles/text_styles.dart';

import 'menu_home_new.dart';

class PageHome extends StatefulWidget {
  @override
  _PageHomeState createState() => _PageHomeState();
}

class _PageHomeState extends State<PageHome> {
  int _selectedDrawerIndex = 0;
  var _scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext context) {
    ScreenUtil.instance = ScreenUtil.getInstance()..init(context);
    return Material(
        child: Padding(
      padding: EdgeInsets.symmetric(
          horizontal: !ResponsiveWidget.isSmallScreen(context)
              ? (ScreenUtil.getInstance().setWidth(108))
              : (ScreenUtil.getInstance().setWidth(6))),
      child: Scaffold(
        key: _scaffoldKey,
        appBar: AppBar(
          elevation: 0,
          title: _buildTitle(),
          backgroundColor: tabBarBgColor,
          actions: ResponsiveWidget.isSmallScreen(context)
              ? _buildSmallScreenAction(context)
              : _buildLargeScreenActions(context),
          leading: ResponsiveWidget.isSmallScreen(context)
              ? IconButton(
                  icon: Icon(
                    Icons.menu,
                    color: Colors.red,
                  ),
                  onPressed: () {
                    _scaffoldKey.currentState.openDrawer();
                  },
                )
              : null,
        ),
        drawer: _buildDrawer(context),
        body: _getDrawerItemWidget(_selectedDrawerIndex),
      ),
    ));
  }

  Widget _buildTitle() {
    return RichText(
      text: TextSpan(
        // Note: Styles for TextSpans must be explicitly defined.
        // Child text spans will inherit styles from parent
        style: TextStyle(
          fontSize: 14.0,
          color: Colors.black,
        ),
        children: <TextSpan>[
          TextSpan(
            text: "Jetpack",
            style: TextStyles.logo,
          ),
          TextSpan(
            text: ".net.cn",
            style: TextStyles.logo.copyWith(
              color: titleColor,
            ),
          ),
        ],
      ),
    );
  }

  _buildLargeScreenActions(BuildContext context) {
    return <Widget>[
      MaterialButton(
        child: Text(
          '主页',
          style: TextStyles.menuItem,
        ),
        onPressed: () {
          setState(() {
            _selectedDrawerIndex = 0;
          });
          if (ResponsiveWidget.isSmallScreen(context)) Navigator.pop(context);
        },
      ),
      MaterialButton(
        child: Text(
          '关于',
          style: TextStyles.menuItem,
        ),
        onPressed: () {
          setState(() {
            _selectedDrawerIndex = 1;
          });
          if (ResponsiveWidget.isSmallScreen(context)) Navigator.pop(context);
        },
      ),
    ];
  }

  _buildDrawer(BuildContext context) {
    return ResponsiveWidget.isSmallScreen(context)
        ? Drawer(
            child: ListView(
              padding: const EdgeInsets.all(0),
              children: _buildLargeScreenActions(context),
            ),
          )
        : null;
  }

  _getDrawerItemWidget(int selectedDrawerIndex) {
    switch (selectedDrawerIndex) {
      case 0:
        return WidgetMenuNewHome();
      case 1:
        return WidgetMenuAbout();
    }
  }

  _buildSmallScreenAction(BuildContext context) {
    return <Widget>[
      MaterialButton(
        child: Text(
          '交流群',
          style: TextStyles.menuItem,
        ),
        onPressed: () {
          Navigator.of(context).pushNamed("/pageChatGroup");
        },
      ),
    ];
  }
}

import 'package:flutter/material.dart';
import 'package:jetpack/pages/jetpack.dart';
import 'package:jetpack/styles/colors.dart';

class WidgetMenuHome extends StatefulWidget {
  @override
  _WidgetMenuHomeState createState() => _WidgetMenuHomeState();
}

class _WidgetMenuHomeState extends State<WidgetMenuHome>
    with SingleTickerProviderStateMixin {
  List _tabs = [
    'Flutter Jetpack',
    'Android Jetpack',
  ];

  TabController _tabController;
  PageController _pageController;

  @override
  void initState() {
    _tabController = TabController(length: _tabs.length, vsync: this);
    _tabController.addListener(() {
      if (_tabController.indexIsChanging) {
        _pageController.animateToPage(_tabController.index,
            duration: Duration(seconds: 1), curve: Curves.ease);
      }
    });
    _pageController = PageController(initialPage: 0);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: tabBarBgColor,
      child: Column(
        children: <Widget>[_buildTabBar(), _buildBody()],
      ),
    );
  }

  _buildTabBar() {
    return Align(
      alignment: Alignment.topLeft,
      child: TabBar(
        indicatorColor: titleColor,
        labelColor: labelColor,
        controller: _tabController,
        isScrollable: true,
        tabs: _tabs
            .map((e) => Tab(
                  text: e,
                ))
            .toList(),
      ),
    );
  }

  _buildBody() {
    return Expanded(
      child: PageView.builder(
        controller: _pageController,
        itemCount: 2,
        scrollDirection: Axis.horizontal,
        physics: NeverScrollableScrollPhysics(),
        onPageChanged: (index) {
          _tabController.animateTo(index);
        },
        itemBuilder: (BuildContext context, int index) {
          if (index == 0) return PageFlutterJetPack();
          if (index == 1) return PageAndroidJetPack();
          return Container();
        },
      ),
    );
  }
}

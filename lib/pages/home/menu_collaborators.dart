import 'package:flutter/material.dart';
import 'package:jetpack/pages/bloggers/laomeng.dart';
import 'package:jetpack/pages/bloggers/mage.dart';
import 'package:jetpack/pages/bloggers/wuyilong.dart';
import 'package:jetpack/pages/bloggers/xiaomo.dart';
import 'package:jetpack/styles/fonts.dart';

class MenuCollaborators extends StatefulWidget {
  @override
  _MenuCollaboratorsState createState() => _MenuCollaboratorsState();
}

class _MenuCollaboratorsState extends State<MenuCollaborators> with SingleTickerProviderStateMixin{

  List _tabs = [
    '老孟',
    '小莫',
    'Marco',
    '吴亦龙',
  ];

  TabController _tabController;
  int _index = 0;

  @override
  void initState() {
    _tabController = TabController(length: _tabs.length, vsync: this);
    _tabController.addListener(() {
      if (_tabController.indexIsChanging) {
        setState(() {
          _index = _tabController.index;
        });
      }
    });
    super.initState();
  }


  @override
  Widget build(BuildContext context) {
    return Container(
      child:  Column(
          children: <Widget>[_buildLargeTabBar(), _buildBody()],
        )
    );
  }

  _buildLargeTabBar() {
    return Align(
      alignment: Alignment.centerLeft,
      child: TabBar(
        controller: _tabController,
        isScrollable: true,
        tabs: _tabs
            .map((e) => Tab(
          child: textSmall(e),
        ))
            .toList(),
      ),
    );
  }
  TextStyle _decorationStyleOf(BuildContext context) {
    final theme = Theme.of(context);
    return theme.textTheme.subhead
        .copyWith(color: theme.hintColor);
  }
  _buildBody() {
    return Expanded(
        child: TabBarView(
          controller: _tabController,
          children: <Widget>[
            LaoMeng(),
            Xiaomo(),
            Mage(),
            WuYilong(),
          ],
        ),
    );
  }
}

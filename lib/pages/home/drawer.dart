import 'package:flutter/material.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/pages/home/home.dart';
import 'package:jetpack/styles/fonts.dart';

class WidgetDrawer extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView(
        padding: EdgeInsets.zero,
        children: <Widget>[
          DrawerHeader(
            child: ListTile(
              title: textMenuAction("i校长"),
              selected: true,
              subtitle: textLogoSubTitle("Jetpack.net.cn"),
              trailing: Image.asset(ixiaozhang),
            ),
            decoration: BoxDecoration(color: Colors.teal),
          ),
          ListTile(
            title: textMenuAction('主页'),
            onTap: () {
              Navigator.pop(context);
              homeBloc.changeSelectedDrawerIndex(0);
            },
            leading: Icon(Icons.home),
          ),
          ListTile(
            title: textMenuAction('关于'),
            onTap: () {
              Navigator.pop(context);
              homeBloc.changeSelectedDrawerIndex(1);
            },
            leading: Icon(Icons.assignment_ind),
          ),
          ListTile(
            title: textMenuAction('设置'),
            onTap: () {
              Navigator.pop(context);
              homeBloc.changeSelectedDrawerIndex(2);
            },
            leading: Icon(Icons.settings),
          ),
          ListTile(
            title: textMenuAction('合作者'),
            onTap: () {
              Navigator.pop(context);
              homeBloc.changeSelectedDrawerIndex(3);
            },
            leading: Icon(Icons.group),
          ),
        ],
      ),
    );
  }
}

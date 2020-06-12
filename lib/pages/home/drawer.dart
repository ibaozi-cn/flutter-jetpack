import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/pages/home/home.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:url_launcher/url_launcher.dart';

class WidgetDrawer extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView(
        padding: EdgeInsets.zero,
        children: <Widget>[
          DrawerHeader(
            child: ListTile(
              title: Text("i校长"),
              selected: true,
              subtitle: Text("Jetpack.net.cn"),
              trailing: Image.asset(ixiaozhang),
            ),
            decoration: BoxDecoration(color: Colors.teal),
          ),
          ListTile(
            title: Text('主页',),
            onTap: () {
              Navigator.pop(context);
              homeBloc.changeSelectedDrawerIndex(0);
            },
            leading: Icon(Icons.home),
          ),
          ListTile(
            title: Text('留言'),
            onTap: () {
              Navigator.pop(context);
              if (!kIsWeb) {
                homeBloc.changeSelectedDrawerIndex(4);
              } else {
                launch('https://support.qq.com/product/166532');
              }
            },
            leading: Icon(Icons.message),
          ),
          ListTile(
            title: Text('加群'),
            onTap: () {
//              Navigator.pop(context);
              Navigator.of(context).pushNamed("/pageChatGroup");
            },
            leading: Icon(Icons.group_add),
          ),
          ListTile(
            title: Text('关于'),
            onTap: () {
              Navigator.pop(context);
              homeBloc.changeSelectedDrawerIndex(1);
            },
            leading: Icon(Icons.assignment_ind),
          ),
          ListTile(
            title: Text('合作者'),
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

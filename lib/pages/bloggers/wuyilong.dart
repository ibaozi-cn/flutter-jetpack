import 'package:flutter/material.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:url_launcher/url_launcher.dart';

class WuYilong extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        heightBoxBig,
        Container(
          margin: EdgeInsets.only(top: 10.0),
          width: 180.0,
          height: 280.0,
          decoration: BoxDecoration(
            shape: BoxShape.rectangle,
            border: new Border.all(color: Colors.blueAccent[100], width: 1.0),
            borderRadius: BorderRadius.circular(10.0),
            image: DecorationImage(
              fit: BoxFit.fill,
              image: AssetImage(icon_wuyilong),
            ),
          ),
        ),
        heightBoxBig,
        Text(
          '细水长流｜吴亦龙博客',
          style: fontStyleChinese.copyWith(fontSize: 35),
          textAlign: TextAlign.center,
        ),
        heightBoxBig,
        Text(
          '生命不止～学习不断',
          style: fontStyleChinese.copyWith(fontSize: 22),
          textAlign: TextAlign.center,
        ),
        heightBoxBig,
        RaisedButton(
            color: Color(0xFF3FAF7C),
            textColor: Colors.white,
            onPressed: () {
              launch('https://wuyilong.cn/');
            },
            child: Text('关注作者'))
      ],
    );
  }
}

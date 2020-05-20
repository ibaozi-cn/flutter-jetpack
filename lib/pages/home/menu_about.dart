import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:jetpack/data/beans.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:jetpack/widgets/responsive_widget.dart';
import 'package:url_launcher/url_launcher.dart';

class WidgetMenuAbout extends StatefulWidget {
  @override
  _WidgetMenuAboutState createState() => _WidgetMenuAboutState();
}

class _WidgetMenuAboutState extends State<WidgetMenuAbout> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: _buildLargeScreen(),
      ),
    );
  }

  _buildLargeScreen() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        _buildIllustration(),
        heightBoxMid,
        _buildHomeMe(),
        heightBoxMid,
        _buildSocialIcons(),
        heightBoxMid,
        _buildHeadline(),
        heightBoxMid,
        _buildEducation(),
        heightBoxMid,
        _buildSkills(),

      ],
    );
  }

  Widget _buildIllustration() {
    return Align(
      alignment: Alignment.center,
      child: Image.asset(
        programmer3,
        height: 360,
      ),
    );
  }

  Widget _buildEducation() {
    return Column(
      children: <Widget>[
        _buildEducationContainerHeading(),
        heightBoxMid,
        _buildEducationSummary(),
      ],
    );
  }

  Widget _buildEducationContainerHeading() {
    return text(experience);
  }

  Widget _buildEducationSummary() {
    return textSmall('性能的关键是精简，而不是一堆的优化用例。除非有真正显著的效果，否则一定要忍住你那些蠢蠢欲动的小微调的企图。');
  }


  Widget _buildSkills() {
    final List<Widget> widgets = skills
        .map((skill) => Padding(
              padding: EdgeInsets.only(right: 16.0),
              child: _buildSkillChip(context, skill),
            ))
        .toList();

    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        _buildSkillsContainerHeading(),
        ResponsiveWidget.isLargeScreen(context)
            ? Row(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  Expanded(
                    flex: 1,
                    child: Container(),
                  ),
                  Expanded(flex: 4, child: Wrap(children: widgets)),
                  Expanded(
                    flex: 1,
                    child: Container(),
                  )
                ],
              )
            : Wrap(children: widgets),
      ],
    );
  }

  Widget _buildSkillsContainerHeading() {
    return text(skills_i_have);
  }


  Widget _buildSkillChip(BuildContext context, String label) {
    return Chip(
      label: textSmall(label),
    );
  }

  Widget _buildHomeMe() {
    return textLogoTitle('Call me');
  }

  Widget _buildHeadline() {
    return text(headline);
  }

  Widget _buildSocialIcons() {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        GestureDetector(
          onTap: () {
            showDialog(
                context: context,
                child: AlertDialog(
                  title: Text("微信号"),
                  content: Text("zhanyong0425"),
                  actions: <Widget>[
                    FlatButton(
                        onPressed: () {
                          Clipboard.setData(
                              ClipboardData(text: 'zhanyong0425'));
                          Navigator.of(context).pop();
                        },
                        child: Text('复制')),
                  ],
                ));
          },
          child: Image.asset(
            weixin,
            height: 20.0,
            width: 20.0,
          ),
        ),
        SizedBox(width: 16.0),
        GestureDetector(
          onTap: () {
            showDialog(
                context: context,
                child: AlertDialog(
                  title: Text("QQ"),
                  content: Text("1608889789"),
                  actions: <Widget>[
                    FlatButton(
                        onPressed: () {
                          Clipboard.setData(ClipboardData(text: '1608889789'));
                          Navigator.of(context).pop();
                        },
                        child: Text('复制')),
                  ],
                ));
          },
          child: Image.asset(
            qq,
            height: 20.0,
            width: 20.0,
          ),
        ),
        SizedBox(width: 16.0),
        GestureDetector(
          onTap: () {
            launch("https://www.jianshu.com/u/77699cd41b28");
          },
          child: Image.asset(
            jianshu,
            height: 20.0,
            width: 20.0,
          ),
        ),
        SizedBox(width: 16.0),
        GestureDetector(
          onTap: () {
            launch("mailto:zzy0523@gmail.com");
          },
          child: Image.asset(
            gmail,
            height: 20.0,
            width: 20.0,
          ),
        ),
      ],
    );
  }


}

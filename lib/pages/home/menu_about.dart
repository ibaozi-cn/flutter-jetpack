import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:jetpack/widgets/widget_responsive.dart';
import 'package:url_launcher/url_launcher.dart';

class WidgetMenuAbout extends StatefulWidget {

  @override
  _WidgetMenuAboutState createState() => _WidgetMenuAboutState();
}

class _WidgetMenuAboutState extends State<WidgetMenuAbout> {
  @override
  Widget build(BuildContext context) {
    setState(() {

    });
    return Scaffold(
      body: SingleChildScrollView(
        child: _buildLargeScreen(),
      ),
    );
  }

  _buildLargeScreen() {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          _buildIllustration(),
          heightBoxBig,
          _buildHomeMe(),
          heightBoxBig,
          _buildSocialIcons(),
          heightBoxBig,
          _buildHeadline(),
          heightBoxBig,
          _buildEducation(),
          heightBoxBig,
          _buildSkills(),

        ],
      ),
    );
  }

  Widget _buildIllustration() {
    return Align(
      alignment: Alignment.center,
      child: Image.asset(
        icon_programmer3,
      ),
    );
  }

  Widget _buildEducation() {
    return Column(
      children: <Widget>[
        StreamBuilder<Object>(
          stream: null,
          builder: (context, snapshot) {
            return _buildEducationContainerHeading();
          }
        ),
        heightBoxBig,
        _buildEducationSummary(),
      ],
    );
  }

  Widget _buildEducationContainerHeading() {
    return Text(experience,style: fontStyleTitle,);
  }

  Widget _buildEducationSummary() {
    return Text('性能的关键是精简，而不是一堆的优化用例。除非有真正显著的效果，否则一定要忍住你那些蠢蠢欲动的小微调的企图。');
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
        heightBoxBig,
        WidgetResponsive.isLargeScreen(context)
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
    return Text(skills_i_have,style: fontStyleTitle,);
  }


  Widget _buildSkillChip(BuildContext context, String label) {
    return Chip(
      label: Text(label),
    );
  }

  Widget _buildHomeMe() {
    return Text('Call me',style: fontStyleEnglish.copyWith(fontSize: 25),);
  }

  Widget _buildHeadline() {
    return Text(headline);
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
          child: Icon(
            FontAwesomeIcons.weixin,
            size: 20,
            color: Colors.lightBlueAccent,
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
          child: Icon(
            FontAwesomeIcons.qq,
            size: 20,
            color: Colors.lightBlueAccent,
          ),
        ),
        SizedBox(width: 16.0),
        GestureDetector(
          onTap: () {
            launch("https://www.jianshu.com/u/77699cd41b28");
          },
          child: Icon(
            FontAwesomeIcons.bloggerB,
            size: 20,
            color: Colors.lightBlueAccent,
          ),
        ),
        SizedBox(width: 16.0),
        GestureDetector(
          onTap: () {
            launch("mailto:zzy0523@gmail.com");
          },
          child:  Icon(
            FontAwesomeIcons.google,
            size: 20,
            color: Colors.lightBlueAccent,
          ),
        ),
      ],
    );
  }


}

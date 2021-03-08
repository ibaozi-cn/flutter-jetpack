import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:jetpack/data/const.dart';
import 'package:url_launcher/url_launcher.dart';

class Kimi extends StatefulWidget {
  @override
  _KimiState createState() => _KimiState();
}

class _KimiState extends State<Kimi> {

  void launchURL(url) async {
    String uri = Uri.encodeFull(url);
    if (await canLaunch(uri)) {
      await launch(uri);
    } else {
      throw 'Could not launch $uri';
    }
  }

  @override
  Widget build(BuildContext context) {
    return  Scaffold(
      body: SafeArea(
        child: SingleChildScrollView(
          padding: EdgeInsets.only(top: 80.0),
          scrollDirection: Axis.vertical,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Image.asset('assets/logo114.png'),
              CircleAvatar(
                backgroundImage: AssetImage(icon_kimi),
                radius: 50,
                backgroundColor: Colors.red,
              ),
              PaddingText(
                padding: EdgeInsets.all(16.0),
                text: 'Kimi',
                size: 45.0,
                weight: FontWeight.w100,
              ),
              PaddingText(
                padding: EdgeInsets.only(bottom: 4.0),
                text: "V聚焦",
                size: 18.0,
              ),
              PaddingText(
                padding: EdgeInsets.only(bottom: 16.0),
                text: "限时招募,诚信合作",
                size: 18.0,
              ),
              SizedBox(
                height: 20.0,
                width: 150.0,
                child: Divider(
                  color: Color(0xFFb2ebf9),
                ),
              ),
              ContactCard(
                onTap: () => launchURL("admin@vjujiao.com"),
                titleText: "admin@vjujiao.com",
                icon: FontAwesomeIcons.voicemail,
              ),
              ContactCard(
                onTap: () => copy("329629722"),
                titleText: "QQ 329629722",
                icon: FontAwesomeIcons.qq,
              ),
              ContactCard(
                onTap: () => launchURL("https://github.com/ikimiler"),
                titleText: "https://github.com/ikimiler",
                icon: FontAwesomeIcons.github,
              ),
            ],
          ),
        ),
      ),
    );
  }

  copy(String s) {
    Clipboard.setData(ClipboardData(text: s));
  }
}

class PaddingText extends StatelessWidget {
  final EdgeInsets padding;
  final String text;
  final double size;
  final FontWeight weight;

  PaddingText(
      {@required this.padding,
        @required this.text,
        @required this.size,
        this.weight});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: padding,
      child: Text(
        text,
        style: TextStyle(
          fontSize: size,
          fontWeight: weight,
        ),
      ),
    );
  }
}
class ContactCard extends StatelessWidget {
  final Function onTap;
  final String titleText;
  final IconData icon;

  ContactCard({
    @required this.onTap,
    @required this.titleText,
    @required this.icon,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.symmetric(vertical: 10.0, horizontal: 25.0),
      child: Padding(
        padding: EdgeInsets.all(8.0),
        child: ListTile(
          onTap: onTap,
          leading: Icon(
            icon,
          ),
          title: Text(
            titleText,
            style: TextStyle(
              fontSize: 20.0,
            ),
          ),
        ),
      ),
    );
  }
}

import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:url_launcher/url_launcher.dart';

class Xiaomo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        alignment: Alignment.topCenter,
        children: <Widget>[
          _buildBackground(context),
          _buildContent(context),
        ],
      ),
    );
  }

  Widget _buildAvatar(context) {
    return Container(
      width: 100,
      height: 100,
      child: CircleAvatar(
        backgroundImage: AssetImage(
          "images/xiaomo/icon_logo.png",
        ),
      ),
    );
  }

  Widget _buildBackground(context) {
    var fit = BoxFit.fitHeight;
    if (MediaQuery.of(context).orientation == Orientation.landscape) {
      fit = BoxFit.cover;
    }
    return Container(
      height: MediaQuery.of(context).size.height,
      width: MediaQuery.of(context).size.width,
      child: Image.asset(
        "images/xiaomo/icon_bg.jpg",
        width: double.infinity,
        fit: fit,
      ),
    );
  }

  Widget _buildContent(context) {
    return Positioned(
        top: MediaQuery.of(context).size.height / 5,
        child: Column(
          children: <Widget>[
            _buildAvatar(context),
            _buildSelfIntroduce(context),
            _buildLinks(context),
          ],
        ));
  }

  Widget _buildSelfIntroduce(context) {
    var width = MediaQuery.of(context).size.width / 1.2;
    var margin = EdgeInsets.fromLTRB(0, 50, 0, 50);
    var padding = EdgeInsets.fromLTRB(0, 15, 0, 15);
    if (MediaQuery.of(context).orientation == Orientation.landscape) {
      width = MediaQuery.of(context).size.width / 2;
      margin = EdgeInsets.fromLTRB(0, 20, 0, 20);
      padding = EdgeInsets.fromLTRB(0, 20, 0, 20);
    }
    return Container(
      padding: padding,
      width: width,
      margin: margin,
      alignment: Alignment.center,
      decoration: BoxDecoration(
        color: Color(0xFF242324).withOpacity(0.4),
        borderRadius: BorderRadius.circular(100),
      ),
      child: Text(
        "生命不息，奋斗不止",
        style: TextStyle(
          color: Colors.white,
          fontSize: 16,
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }

  Widget _buildLinks(
      context,
      ) {
    return Container(
      alignment: Alignment.center,
      child: Wrap(
        children: <Widget>[
          _buildLink(
              context,
              Icon(
                FontAwesomeIcons.home,
                size: 50,
                color: Colors.lightBlueAccent,
              ),
              "https://blog.xiamomo.info"),
          _buildLink(
              context,
              Icon(
                FontAwesomeIcons.github,
                size: 50,
              ),
              "https://github.com/houko"),
          _buildLink(
              context,
              Icon(
                FontAwesomeIcons.twitter,
                size: 50,
                color: Colors.blue,
              ),
              "https://twitter.com/xiaomoinfo"),
          _buildLink(
              context,
              Icon(
                FontAwesomeIcons.java,
                size: 50,
                color: Colors.red,
              ),
              "https://www.youracclaim.com/user/xiaomoinfo"),
          _buildLink(
              context,
              Icon(
                FontAwesomeIcons.toolbox,
                size: 50,
                color: Colors.pinkAccent,
              ),
              "https://miku.tools/"),
        ],
      ),
    );
  }

  Widget _buildLink(context, Icon icon, String link) {
    double margin = 10;
    if (MediaQuery.of(context).orientation == Orientation.landscape) {
      margin = 20;
    }
    return InkWell(
      onTap: () {
        launch(link);
      },
      child: Container(
        margin: EdgeInsets.all(margin),
        child: icon,
      ),
    );
  }
}

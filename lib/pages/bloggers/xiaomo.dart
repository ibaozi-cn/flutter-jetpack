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
          "images/xiaomo/avatar.png",
        ),
      ),
    );
  }

  Widget _buildBackground(context) {
    return Image.asset(
      "images/xiaomo/bg.jpg",
      width: double.infinity,
      fit: BoxFit.fitHeight,
    );
  }

  Widget _buildContent(context) {
    return Positioned(
        top: MediaQuery.of(context).size.height / 5,
        child: Column(
          children: <Widget>[
            _buildAvatar(context),
            _buildSelfIntroduce(context),
            _buildLinks(),
          ],
        ));
  }

  Widget _buildSelfIntroduce(context) {
    return Container(
      padding: EdgeInsets.fromLTRB(50, 20, 50, 20),
      width: MediaQuery.of(context).size.width / 2,
      margin: EdgeInsets.all(20),
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

  Widget _buildLinks() {
    return Wrap(
      children: <Widget>[
        _buildLink(
            Icon(
              FontAwesomeIcons.home,
              size: 50,
              color: Colors.lightBlueAccent,
            ),
            "https://blog.xiamomo.info"),
        _buildLink(
            Icon(
              FontAwesomeIcons.github,
              size: 50,
            ),
            "https://github.com/houko"),
        _buildLink(
            Icon(
              FontAwesomeIcons.twitter,
              size: 50,
              color: Colors.blue,
            ),
            "https://twitter.com/xiaomoinfo"),
        _buildLink(
            Icon(
              FontAwesomeIcons.java,
              size: 50,
              color: Colors.red,
            ),
            "https://www.youracclaim.com/user/xiaomoinfo"),
        _buildLink(
            Icon(
              FontAwesomeIcons.tools,
              size: 50,
              color: Colors.grey,
            ),
            "https://miku.tools/"),
      ],
    );
  }

  Widget _buildLink(Icon icon, String link) {
    return InkWell(
      onTap: () {
        launch(link);
      },
      child: Container(
        margin: EdgeInsets.all(20),
        child: icon,
      ),
    );
  }
}

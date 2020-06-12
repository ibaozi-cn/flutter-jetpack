import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:url_launcher/url_launcher.dart';

class Mage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          heightBoxBig,
          heightBoxBig,
          heightBoxBig,
          Align(
              child: Container(
            margin: EdgeInsets.only(top: 10.0),
            width: 150.0,
            height: 150.0,
            decoration: BoxDecoration(
              shape: BoxShape.rectangle,
              border: new Border.all(color: Colors.amber, width: 4.0),
              borderRadius: BorderRadius.circular(80.0),
              image: DecorationImage(
                fit: BoxFit.fill,
                image: AssetImage("images/mage.jpg"),
              ),
            ),
          )),
          heightBoxMid,
          Text(
            'Marco Ma',
            style: fontStyleEnglish.copyWith(fontSize: 35,color: Colors.amber),
            textAlign: TextAlign.center,
          ),
          heightBoxMid,
          RichText(
            textAlign: TextAlign.center,
            text: TextSpan(
                text: "One blog for recorded and shared",
                style: fontStyleEnglish.copyWith(fontSize: 18,color: Colors.black),
                children: <TextSpan>[
                  TextSpan(
                      text: '\nAndroid system development.',
                      style: fontStyleEnglish.copyWith(fontSize: 22,color: Colors.amber)),
                ]),
          ),
          heightBoxMid,
          Text(
            'Framework, Mechanism, optimization, Stability, Views, Process, Algorith, etc...',
            style: fontStyleEnglish,
            textAlign: TextAlign.center,
          ),
          heightBoxMid,
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              IconButton(
                onPressed: () {
                  launch('https://superandroid.pro/archives/');
                },
                icon: FaIcon(FontAwesomeIcons.bookOpen),
              ),
              IconButton(
                onPressed: () {
                  launch('https://github.com/pepsimaxin');
                },
                icon: FaIcon(FontAwesomeIcons.github),
              )
            ],
          )
        ],
      ),
    );
  }
}

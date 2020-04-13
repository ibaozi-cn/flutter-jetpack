import 'package:flutter/material.dart';
import 'package:jetpack/styles/text_styles.dart';
import 'package:url_launcher/url_launcher.dart';

class PageQQLink extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Material(
      child: Center(
        child: InkWell(
          onTap: _launchURL,
          child: Text(
            "打开QQ",
            style: TextStyles.logo.copyWith(
              color: Colors.blueAccent
            ),
          ),
        ),
      ),
    );
  }

  _launchURL() async {
    const url = 'https://jq.qq.com/?_wv=1027&k=5OfJnTa';
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }

}

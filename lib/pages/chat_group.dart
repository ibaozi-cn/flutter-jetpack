import 'package:flutter/material.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/styles/colors.dart';
import 'package:jetpack/styles/text_styles.dart';

class PageChatGroup extends StatelessWidget {


  Widget _buildTitle() {
    return RichText(
      text: TextSpan(
        // Note: Styles for TextSpans must be explicitly defined.
        // Child text spans will inherit styles from parent
        style: TextStyle(
          fontSize: 14.0,
          color: Colors.black,
        ),
        children: <TextSpan>[
          TextSpan(
            text: "Communication",
            style: TextStyles.logo,
          ),
          TextSpan(
            text: ".group",
            style: TextStyles.logo.copyWith(
              color: titleColor,
            ),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: Icon(
            Icons.arrow_back_ios,
            color: titleColor,
          ),
          onPressed: () {
            Navigator.of(context).pop();
          },
        ),
        title: _buildTitle(),
        backgroundColor: tabBarBgColor,
      ),



      body: Container(
        color: tabBarBgColor,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            Image.asset(WE_CHAT_SCAN),
            SizedBox(
              height: 8,
            ),
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Text(
                CHAT_GROUP_TEXT,
                textAlign: TextAlign.start,
                style: TextStyle(color: Colors.black87, fontSize: 24),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

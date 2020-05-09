import 'package:flutter/material.dart';
import 'package:jetpack/data/const.dart';

class PageChatGroup extends StatelessWidget {


  Widget _buildTitle(context) {
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
            style: Theme.of(context).textTheme.subtitle1,
          ),
          TextSpan(
            text: ".group",
            style: Theme.of(context).textTheme.subtitle2,
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
          ),
          onPressed: () {
            Navigator.of(context).pop();
          },
        ),
        title: _buildTitle(context),
      ),

      body: SingleChildScrollView(
        child: Container(
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
                  style: Theme.of(context).textTheme.headline5,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

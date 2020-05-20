import 'package:flutter/material.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/styles/fonts.dart';

class PageChatGroup extends StatelessWidget {

  Widget _buildTitle() {
    return Row(
      children: <Widget>[
        textLogoTitle('Scan'),
        textLogoSubTitle('.it'),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: Icon(
            Icons.arrow_back,
          ),
          onPressed: () {
            Navigator.of(context).pop();
          },
        ),
        title: _buildTitle(),
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
                child: text(CHAT_GROUP_TEXT),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

import 'package:flutter/material.dart';

class WidgetMenuAbout extends StatefulWidget {
  @override
  _WidgetMenuAboutState createState() => _WidgetMenuAboutState();
}

class _WidgetMenuAboutState extends State<WidgetMenuAbout> {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(16),
      child: GridTile(
        header: Container(
          color: Color(0x22000000),
          height: 50,
          child: Text(
            'header',
            style: TextStyle(color: Colors.redAccent),
          ),
        ),
        child: Container(
          color: Color(0x22119233),
          height: 50,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Text(
                'start',
                textAlign: TextAlign.center,
                style: TextStyle(color: Colors.redAccent),
              ),
              Text(
                'end',
                textAlign: TextAlign.center,
                style: TextStyle(color: Colors.redAccent),
              ),
            ],
          ),
        ),
        footer: Container(
//          color: Color(0x44000000),
          color: Color(0x55000000),
          height: 50,
          child: Text(
            'footer',
            style: TextStyle(color: Colors.redAccent),
          ),
        ),
      ),
    );
  }
}

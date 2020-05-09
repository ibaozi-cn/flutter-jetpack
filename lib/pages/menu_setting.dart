import 'package:flutter/material.dart';
import 'package:jetpack/main.dart';

class WidgetMenuSetting extends StatefulWidget {
  @override
  _WidgetMenuSettingState createState() => _WidgetMenuSettingState();
}

class _WidgetMenuSettingState extends State<WidgetMenuSetting> {
  bool _isEnabled = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: SwitchListTile(
          secondary: Icon(_isEnabled ? Icons.brightness_4 : Icons.brightness_5),
          title: Text("暗黑主题"),
          subtitle: Text("将主题调成暗黑色"),
          value: _isEnabled,
          onChanged: (value) {
            setState(() {
              _isEnabled = value;
            });
            if (value) {
              bloc.changeTheTheme(AppTheme.DARK_THEME);
            } else {
              bloc.changeTheTheme(AppTheme.LIGHT_THEME);
            }
          },
        ),
      ),
    );
  }
}

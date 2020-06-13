import 'dart:async';

import 'package:flutter/material.dart';
import 'package:jetpack/pages/bloggers/laomeng.dart';
import 'package:jetpack/pages/bloggers/xiaomo.dart';
import 'package:jetpack/pages/group/chat_group.dart';
import 'package:jetpack/pages/home/home.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return StreamBuilder<AppTheme>(
        initialData: AppTheme.LIGHT_THEME,
        stream: bloc.darkThemeIsEnabled,
        builder: (context, AsyncSnapshot<AppTheme> snapshot) {
          return MaterialApp(
            title: 'Jetpack',
            theme: snapshot.data.themeData,
            home: PageHome(),
            routes: <String, WidgetBuilder>{
              "/pageChatGroup": (context) => PageChatGroup(),
              "/LaoMeng": (context) => LaoMeng(),
              "/xiaomo": (context) => Xiaomo(),
            },
          );
        });
  }
}

class ThemeBloc {
  final _themeStreamController = StreamController<AppTheme>();

  get changeTheTheme => _themeStreamController.sink.add;

  get darkThemeIsEnabled => _themeStreamController.stream;

  dispose() {
    _themeStreamController.close();
  }
}

final bloc = ThemeBloc();

class AppTheme {

  ThemeData themeData;

  AppTheme(this.themeData);

  // ignore: non_constant_identifier_names
  static final AppTheme DARK_THEME =
      AppTheme(ThemeData(brightness: Brightness.dark,fontFamily: 'Cagliostro'));

  // ignore: non_constant_identifier_names
  static final AppTheme LIGHT_THEME = AppTheme(
    ThemeData(
        brightness: Brightness.light,
        primaryColor: Colors.grey[50],
        accentColor: Colors.deepOrangeAccent,
        fontFamily: 'Cagliostro',
        tabBarTheme: TabBarTheme(),
        buttonBarTheme:
            ButtonBarThemeData(buttonTextTheme: ButtonTextTheme.normal)),
  );

}

import 'package:flutter/material.dart';

import 'colors.dart';

class TextStyles {
  TextStyles._();
  static TextStyle get logo => TextStyle(
    color: menuColor,
    fontSize: 22.0, //22.0
    fontWeight: FontWeight.bold,
    letterSpacing: 1.0,
  );

  static TextStyle get menuItem => TextStyle(
    fontSize: 12.0, //12,.0
    letterSpacing: 1.0,
    color: menuColor,
  );

}
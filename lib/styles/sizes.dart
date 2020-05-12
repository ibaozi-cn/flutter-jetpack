import 'package:flutter/material.dart';
import 'package:jetpack/util/screen_utils.dart';
import 'package:jetpack/widgets/responsive_widget.dart';

SizedBox heightBoxBig = SizedBox(
  height: 20,
);
SizedBox heightBoxMid = SizedBox(
  height: 10,
);
SizedBox heightBoxSmall = SizedBox(
  height: 5,
);

 EdgeInsetsGeometry padding(context) => EdgeInsets.symmetric(
    horizontal: !ResponsiveWidget.isSmallScreen(context)
        ? (ScreenUtil.getInstance().setWidth(188))
        : (ScreenUtil.getInstance().setWidth(0)));

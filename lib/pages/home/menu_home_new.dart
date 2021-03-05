import 'package:flutter/material.dart';
import 'package:jetpack/data/android_components.dart';
import 'package:jetpack/data/flutter_components.dart';
import 'package:jetpack/pages/jetpacks/jetpack.dart';

class WidgetMenuNewHome extends StatefulWidget {
  @override
  _WidgetMenuNewHomeState createState() => _WidgetMenuNewHomeState();
}

class _WidgetMenuNewHomeState extends State<WidgetMenuNewHome>
    with SingleTickerProviderStateMixin {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: <Widget>[
          Expanded(
            child: PageJetPack(
              headTitle: FlutterComponents.headTitle,
              headDesc: FlutterComponents.headDesc,
              componentsTitles: FlutterComponents.componentsTitles,
              selectComponentsDataForArc:
                  FlutterComponents.selectComponentsDataForArc(),
              selectComponentsDataForBasic:
                  FlutterComponents.selectComponentsDataForBasic(),
              selectComponentsDataForBehavior:
                  FlutterComponents.selectComponentsDataForBehavior(),
              selectComponentsDataForUI:
                  FlutterComponents.selectComponentsDataForUI(),
            ),
          ),
        ],
      ),
    );
  }
}

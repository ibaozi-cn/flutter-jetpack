import 'package:flutter/material.dart';
import 'package:jetpack/widgets/widget_responsive.dart';

class WidgetAutoResponsive extends StatelessWidget {
  
  final Widget child;
  final Widget childLeft;
  final Widget childRight;

  final double childFlex;
  final double childLeftFlex;
  final double childRightFlex;

  WidgetAutoResponsive(
      {this.child,
      this.childLeft =const ChildDefault(),
      this.childRight =const ChildDefault(),
      this.childFlex = 4,
      this.childLeftFlex = 1,
      this.childRightFlex = 1});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [

//        if(WidgetResponsive.isLargeScreen(context)){
//
//        }
        
      ],
    );
  }
}

class ChildDefault extends StatelessWidget {
  
  const ChildDefault();
  
  @override
  Widget build(BuildContext context) {
    return Container();
  }
}

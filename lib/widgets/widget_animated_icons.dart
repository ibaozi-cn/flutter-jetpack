import 'package:flutter/material.dart';

class WidgetAnimatedIcons extends StatefulWidget {
  final AnimatedIconData icon;
  final Function callBack;

  const WidgetAnimatedIcons(
      {Key key, @required this.icon, @required this.callBack})
      : super(key: key);

  @override
  _IAnimatedIconsState createState() => _IAnimatedIconsState();
}

class _IAnimatedIconsState extends State<WidgetAnimatedIcons>
    with SingleTickerProviderStateMixin {
  AnimationController _animationController;

  @override
  void initState() {
    super.initState();
    _animationController =
        AnimationController(duration: Duration(seconds: 1), vsync: this);
    Tween<double>(begin: 0.0, end: 1.0)..animate(_animationController);
  }

  @override
  void dispose() {
    super.dispose();
    _animationController.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {
        setState(() {
          if (_animationController.status == AnimationStatus.completed) {
            _animationController.reverse();
            widget.callBack(false);
          } else {
            _animationController.forward();
            widget.callBack(true);
          }
        });
      },
      child: AnimatedIcon(
        icon: widget.icon,
        progress: _animationController,
      ),
    );
  }
}

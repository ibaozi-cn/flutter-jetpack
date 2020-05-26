import 'dart:async';

import 'package:flutter/material.dart';

class Item {
  String reference;

  Item(this.reference);
}

class _MyInherited extends InheritedWidget {
  _MyInherited({
    Key key,
    @required Widget child,
    @required this.data,
  }) : super(key: key, child: child);

  final MyInheritedWidgetState data;

  @override
  bool updateShouldNotify(_MyInherited oldWidget) {
    return true;
  }
}

class MyInheritedWidget extends StatefulWidget {
  MyInheritedWidget({
    Key key,
    this.child,
  }) : super(key: key);

  final Widget child;

  @override
  MyInheritedWidgetState createState() => new MyInheritedWidgetState();

  static MyInheritedWidgetState of(
      [BuildContext context, bool rebuild = true]) {
    return (rebuild
            ? context.inheritFromWidgetOfExactType(_MyInherited) as _MyInherited
            : context.ancestorWidgetOfExactType(_MyInherited) as _MyInherited)
        .data;
  }
}

class MyInheritedWidgetState extends State<MyInheritedWidget> {
  /// List of Items
  List<Item> _items = <Item>[];

  /// Getter (number of items)
  int get itemsCount => _items.length;

  /// Helper method to add an Item
  void addItem(String reference) {
    setState(() {
      _items.add(new Item(reference));
    });
  }

  @override
  Widget build(BuildContext context) {
    return new _MyInherited(
      data: this,
      child: widget.child,
    );
  }
}

class MyTree extends StatefulWidget {
  @override
  _MyTreeState createState() => new _MyTreeState();
}

class _MyTreeState extends State<MyTree> {
  @override
  Widget build(BuildContext context) {
    print("MyTree build");
    return new MyInheritedWidget(
      child: new Scaffold(
        appBar: new AppBar(
          title: new Text('Title'),
        ),
        body: new Column(
          children: <Widget>[
            new WidgetA(),
            new Container(
              child: new Row(
                children: <Widget>[
                  new Icon(Icons.shopping_cart),
                  new WidgetB(),
                  new WidgetC(),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class WidgetA extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    print("WidgetA build");
    final MyInheritedWidgetState state = MyInheritedWidget.of(context, false);
    return new Container(
      child: new RaisedButton(
        child: new Text('Add Item'),
        onPressed: () {
          state.addItem('new item');
        },
      ),
    );
  }
}

class WidgetB extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    print("WidgetB build");
    final MyInheritedWidgetState state = MyInheritedWidget.of(context, true);
    return new Text('${state.itemsCount}');
  }
}

class WidgetC extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    print("WidgetC build");
    return new Text('I am Widget C');
  }
}

class TestNotification extends Notification {
  final int test;

  TestNotification(this.test);
}

var a = 0;

// ignore: must_be_immutable
class WidgetNotification extends StatelessWidget {

  final String btnText;

  WidgetNotification({Key key, this.btnText}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    debugPrint("WidgetNotification re build");
    return Container(
      child: RaisedButton(
        child: Text(btnText),
        onPressed: () {
          var b = ++a;
          debugPrint(b.toString());
          TestNotification(b).dispatch(context);
        },
      ),
    );
  }
}

class WidgetListener extends StatefulWidget {
  @override
  _WidgetListenerState createState() => _WidgetListenerState();
}

class _WidgetListenerState extends State<WidgetListener> {

  @override
  Widget build(BuildContext context) {
    debugPrint("WidgetListener re build");

    return Container(
      child: Column(
        children: <Widget>[
          NotificationListener<TestNotification>(
            child: Column(
              children: <Widget>[
                StreamBuilder<int>(
                  initialData: 0,
                  stream: textBloc.testStream,
                  builder: (context, snapshot) {
                    return Text("监听${snapshot.data}");
                  }
                ),
                WidgetNotification(btnText: "子Widget",),
                WidgetC()
              ],
            ),
            onNotification: (TestNotification notification) {
              textBloc.changeTest(notification.test);
              return true;
            },
          ),
          WidgetNotification(btnText: "非子Widget",)
        ],
      ),
    );
  }
}

class TestBloc {

  final _testStreamController = StreamController<int>();

  get changeTest => _testStreamController.sink.add;

  get testStream => _testStreamController.stream;

  dispose() {
    _testStreamController.close();
  }
}

final textBloc = TestBloc();
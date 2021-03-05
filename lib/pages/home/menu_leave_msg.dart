import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:webview_flutter/webview_flutter.dart';

class MenuLeaveMsg extends StatefulWidget {
  @override
  _MenuLeaveMsgState createState() => _MenuLeaveMsgState();
}

class _MenuLeaveMsgState extends State<MenuLeaveMsg> {
  WebViewController _webViewController;
  bool canGoBack;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async {
        _webViewController.canGoBack().then((value) => {
              if (value) {_webViewController.goBack()}
            });
        return false;
      },
      child: Container(
        child: WebView(
          initialUrl: 'https://support.qq.com/product/166532',
          javascriptMode: JavascriptMode.unrestricted,
          onWebViewCreated: (controller) {
            this._webViewController = controller;
          },
        ),
      ),
    );
  }
}

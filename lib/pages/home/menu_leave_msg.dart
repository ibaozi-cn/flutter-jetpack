import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';

class MenuLeaveMsg extends StatefulWidget {
  @override
  _MenuLeaveMsgState createState() => _MenuLeaveMsgState();
}

class _MenuLeaveMsgState extends State<MenuLeaveMsg> {
  TextEditingController _textEditingController;
  String _textFieldValue = "";
  final _formKey = GlobalKey<FormState>();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _textEditingController = TextEditingController(text: "");
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: EdgeInsets.all(16),
        child: Column(
          children: <Widget>[
            heightBoxMid,
            Form(
                key: _formKey,
                child: Column(
                  children: <Widget>[
                    TextFormField(
                        onChanged: (value) {
                          setState(() {
                            _textFieldValue = value;
                          });
                        },
                        onSaved: (value) {},
                        validator: validateLeaveMessage,
                        controller: _textEditingController,
                        maxLength: 200,
                        minLines: 5,
                        maxLines: 10,
                        decoration: InputDecoration(
                            counterText: '${_textFieldValue.length}/200',
                            hintText: '请输入留言内容',
                            enabledBorder: OutlineInputBorder(
                              /*边角*/
                              borderRadius: BorderRadius.all(
                                Radius.circular(10), //边角为30
                              ),
                            ),
                            focusedBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Colors.deepOrange),
                            ))),
                    heightBoxMid,
                    Align(
                      alignment: Alignment.bottomRight,
                      child: RaisedButton(
                        onPressed: () {
                          if (_formKey.currentState.validate())
                            showConfirmDialog();
                        },
                        child: textMenuAction("发送"),
                      ),
                    )
                  ],
                )),
          ],
        ));
  }

  String validateLeaveMessage(String value) {
    if (value.isEmpty || value.length < 5) {
      return "请输入至少五个字符哦";
    }
    return null;
  }

  // 弹出对话框
  Future<bool> showConfirmDialog() {
    return showDialog<bool>(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text("提示"),
          content: Text("您确定要发送吗?"),
          actions: <Widget>[
            FlatButton(
              child: Text("取消"),
              onPressed: () => Navigator.of(context).pop(), // 关闭对话框
            ),
            FlatButton(
              child: Text("确定"),
              onPressed: () {
                //关闭对话框并返回true
                Navigator.of(context).pop(true);
              },
            ),
          ],
        );
      },
    );
  }
}

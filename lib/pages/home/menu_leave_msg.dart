import 'dart:async';
import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:jetpack/data/const.dart';
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
  final _listLeaveMsg = List<LeaveMsgBean>();

  @override
  void initState() {
    super.initState();
    _textEditingController = TextEditingController(text: "");
    _listLeaveMsg.add(LeaveMsgBean("i校长", ixiaozhang, "嗨，大家好，欢迎留言哦"));
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: EdgeInsets.all(16),
        child: Column(
          children: <Widget>[
            heightBoxMid,
            _buildForm(),
            Expanded(
                child: ListView.builder(
              itemBuilder: (context, index) {
                final leaveMsg = _listLeaveMsg[index];
                return ListTile(
                  leading: Image.asset(leaveMsg.headImgUrl),
                  title: Text(leaveMsg.nikeName),
                  subtitle: Text(leaveMsg.msg),
                );
              },
              itemCount: _listLeaveMsg.length,
            ))
          ],
        ));
  }

  Form _buildForm() {
    return Form(
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
                autofocus: false,
                maxLength: 200,
                maxLines: 4,
                decoration: InputDecoration(
                    counterText: '${_textFieldValue.length}/200',
                    hintText: '请输入留言内容',
                    border: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.red),
                    ),
                    focusedBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.green),
                    ))),
            heightBoxMid,
            Align(
              alignment: Alignment.bottomRight,
              child: RaisedButton(
                onPressed: () {
                  FocusScope.of(context).requestFocus(FocusNode());
                  if (_formKey.currentState.validate()) showConfirmDialog();
                },
                child: textMenuAction("发送"),
              ),
            )
          ],
        ));
  }

  String validateLeaveMessage(String value) {
    if (value.isEmpty || value.length < 2) {
      return "请输入至少两个字符哦";
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
                setState(() {
                  _listLeaveMsg.add(LeaveMsgBean(headImgsTitle[Random().nextInt(23)], headImgs[Random().nextInt(9)], _textFieldValue));
                });
              },
            ),
          ],
        );
      },
    );
  }
}

class LeaveMsgBean {
  String nikeName;
  String headImgUrl;
  String msg;

  LeaveMsgBean(this.nikeName, this.headImgUrl, this.msg);
}

import 'package:flutter/material.dart';
import 'package:jetpack/data/const.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:jetpack/widgets/widget_responsive.dart';
import 'package:url_launcher/url_launcher.dart';

class LaoMeng extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(body: SingleChildScrollView(child: _buildContent(context)));
  }

  Widget _buildContent(context) {
    return Container(
      width: double.infinity,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          heightBoxBig,
          Container(
            margin: EdgeInsets.only(top: 10.0),
            width: 150.0,
            height: 150.0,
            decoration: BoxDecoration(
              shape: BoxShape.rectangle,
              border: new Border.all(color: Colors.green[200], width: 2.0),
              borderRadius: BorderRadius.circular(20.0),
              image: DecorationImage(
                fit: BoxFit.fill,
                image: AssetImage(laomeng),
              ),
            ),
          ),
          heightBoxBig,
          textMenuAction("老孟"),
          heightBoxMid,
          textCardSubTitle("专注分析Flutter原理及实践应用"),
          heightBoxBig,
          RaisedButton(
            onPressed: () {
              launch(
                  "http://laomengit.com/flutter/widgets/widgets_structure.html");
            },
            shape: StadiumBorder(),
            child: textSmall("开始阅读"),
          ),
          heightBoxBig,
          WidgetResponsive.isSmallScreen(context)
              ? Column(
                  children: <Widget>[
                    _buildDescItem("全网最全组件介绍",
                        "所有Flutter控件的详细用法、介绍、使用场景及注意问题，目前已经整理将近200个，不断完善中，目标是整理所有组件。"),
                    _buildDescItem(
                        "第三方插件推荐", "推荐稳定、好评率高、活跃度高的第三方插件及其详细用法，积极推动共建者卵化优秀插件。"),
                    _buildDescItem(
                        "基础入门", "分享基础入门文章，快速进入Flutter的大门，推动Flutter生态的积极发展。")
                  ],
                )
              : Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Expanded(
                      child: _buildDescItem("全网最全组件介绍",
                          "所有Flutter控件的详细用法、介绍、使用场景及注意问题，目前已经整理将近200个，不断完善中，目标是整理所有组件。"),
                    ),
                    Expanded(
                      child: _buildDescItem("第三方插件推荐",
                          "推荐稳定、好评率高、活跃度高的第三方插件及其详细用法，积极推动共建者卵化优秀插件。"),
                    ),
                    Expanded(
                      child: _buildDescItem(
                          "基础入门", "分享基础入门文章，快速进入Flutter的大门，推动Flutter生态的积极发展。"),
                    )
                  ],
                )
        ],
      ),
    );
  }

  Widget _buildDescItem(title, subTitle) {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[text(title), heightBoxMid, textSmall(subTitle)],
      ),
    );
  }
}

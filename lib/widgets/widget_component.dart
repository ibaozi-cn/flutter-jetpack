import 'package:flutter/material.dart';
import 'package:jetpack/data/components.dart';
import 'package:jetpack/pages/components/component_detail.dart';
import 'package:jetpack/styles/fonts.dart';
import 'package:jetpack/styles/sizes.dart';
import 'package:url_launcher/url_launcher.dart';

class WidgetComponents extends StatefulWidget {
  final Components components;

  const WidgetComponents({Key key, @required this.components})
      : super(key: key);

  @override
  _WidgetComponentState createState() => _WidgetComponentState();
}

class _WidgetComponentState extends State<WidgetComponents> {
  @override
  Widget build(BuildContext context) {
    return _buildCard(widget.components);
  }

  Widget _buildCard(Components components) {
    return Card(
        child: InkWell(
            onTap: () async {
              Navigator.push(context, MaterialPageRoute(builder: (_) {
                return PageComponentsDetail(
                  components: components,
                  index: 0,
                );
              }));
            },
            child: Container(
              padding: EdgeInsets.all(16),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: <Widget>[
                  Text(
                    components.title,
                    style: fontStyleTitle,
                  ),
                  heightBoxMid,
                  Text(
                    components.subTitle,
                    style: fontStyleSubTitle,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    crossAxisAlignment: CrossAxisAlignment.end,
                    children: <Widget>[
                      InkWell(
                        onTap: () {
                          Navigator.push(context,
                              MaterialPageRoute(builder: (_) {
                            return PageComponentsDetail(
                              components: components,
                              index: 0,
                            );
                          }));
                        },
                        child: Text(
                          "示例",
                        ),
                      ),
                      Text('  '),
                      InkWell(
                        onTap: () {
                          _launchURL(components.url);
                        },
                        child: Text("源码"),
                      ),
                      Text('  '),
                      InkWell(
                        onTap: () {
                          Navigator.push(context,
                              MaterialPageRoute(builder: (_) {
                            return PageComponentsDetail(
                              components: components,
                              index: 1,
                            );
                          }));
                        },
                        child: Text("博客"),
                      ),
                    ],
                  ),
                ],
              ),
            )));
  }

  _launchURL(url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}

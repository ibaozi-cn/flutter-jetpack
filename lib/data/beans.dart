import 'const.dart';

class Education {
  String from;
  String to;
  String organization;
  String post;

  Education(this.from, this.to, this.organization, this.post);
}

class Blog {
  String nikeName;
  String title;
  String url;
  String head;

  Blog(this.nikeName, this.title, this.url, this.head);

  static List<Blog> blogList = [
    Blog(
      "i校长",
      "手写一个Flutter State Widget，来让你彻底理解State的来龙去脉",
      "https://juejin.cn/post/6875696964059693069",
      icon_state,
    ),
    Blog(
      "i校长",
      "Flutter之万物皆Widget（一种你没见过的方式来深入Widget）",
      "https://juejin.cn/post/6873323337016311816",
      icon_widget,
    ),
  ];
}

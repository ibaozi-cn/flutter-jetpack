class Components {

  String title;
  String subTitle;
  String url;
  var leading;
  String markDownData;
  List<Components> componentsList;

  Components(this.title, this.subTitle, this.url, {this.leading,this.componentsList,this.markDownData});

  @override
  String toString() {
    return 'Components{title: $title, subTitle: $subTitle, url: $url, componentsList: $componentsList}';
  }

}

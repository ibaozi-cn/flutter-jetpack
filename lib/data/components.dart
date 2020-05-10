class Components {

  var title;
  var subTitle;
  var url;
  var leading;
  List<Components> componentsList;

  Components(this.title, this.subTitle, this.url, {this.leading,this.componentsList});

  @override
  String toString() {
    return 'Components{title: $title, subTitle: $subTitle, url: $url, componentsList: $componentsList}';
  }

}

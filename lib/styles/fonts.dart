import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

///大文本 英文样式
final TextStyle _bigTextE = GoogleFonts.aleo().copyWith(fontSize: 28);

///大文本 中文样式
final TextStyle _bigTextC = GoogleFonts.notoSerif().copyWith(fontSize: 28);

final TextStyle _midTextE = GoogleFonts.aleo().copyWith(fontSize: 24);

final TextStyle _midTextC = GoogleFonts.notoSerif().copyWith(fontSize: 24);

final TextStyle _textE = GoogleFonts.aleo().copyWith(fontSize: 20);

final TextStyle _textC = GoogleFonts.notoSerif().copyWith(fontSize: 20);

final TextStyle _smallTextE = GoogleFonts.aleo().copyWith(fontSize: 16);

final TextStyle _smallTextC = GoogleFonts.notoSerif().copyWith(fontSize: 16);

Text textBig(text, {isChinese = true, textAlign: TextAlign.start}) {
  return Text(
    text,
    textAlign: textAlign,
    style: isChinese ? _bigTextC : _bigTextE,
  );
}

Text textMid(text, {isChinese = true, textAlign: TextAlign.start}) {
  return Text(
    text,
    textAlign: textAlign,
    style: isChinese ? _midTextC : _midTextE,
  );
}

Text text(text, {isChinese = true, textAlign: TextAlign.start}) {
  return Text(
    text,
    textAlign: textAlign,
    style: isChinese ? _textC : _textE,
  );
}

Text textSmall(text, {isChinese = true, textAlign: TextAlign.start}) {
  return Text(
    text,
    textAlign: textAlign,
    style: isChinese ? _smallTextC : _smallTextE,
  );
}
///中文样式
Text textMenuAction(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.maShanZheng().copyWith(fontSize: 20),
  );
}
///英文样式
Text textLogoTitle(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.davidLibre().copyWith(fontSize: 20).copyWith(
      color: Colors.deepOrange
    ),
  );
}
///英文样式
Text textLogoSubTitle(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.bahianita().copyWith(fontSize: 16),
  );
}
///英文样式
Text textTabBar(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.aleo().copyWith(fontSize: 16),
  );
}
///英文样式
Text textCardTitle(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.aleo().copyWith(fontSize: 18).copyWith(
        color: Colors.deepOrange
    ),
  );
}
///中文样式
Text textCardSubTitle(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.notoSans().copyWith(fontSize: 14),
  );
}
///中文样式
Text textCardActionTitle(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.maShanZheng().copyWith(fontSize: 12).copyWith(
        color: Colors.teal
    ),
  );
}

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';


///大文本 中文样式

final TextStyle _textC = GoogleFonts.notoSans().copyWith(fontSize: 20);

final TextStyle _smallTextC = GoogleFonts.notoSans().copyWith(fontSize: 14);

Text text(text, {textAlign: TextAlign.start}) {
  return Text(
    text,
    textAlign: textAlign,
    style: _textC,
  );
}

Text textSmall(text, { textAlign: TextAlign.start}) {
  return Text(
    text,
    textAlign: textAlign,
    style:  _smallTextC,
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
Text textCardTitle(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.notoSans().copyWith(fontSize: 18).copyWith(
        color: Colors.deepOrange
    ),
  );
}
///中文样式
Text textCardSubTitle(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.notoSans().copyWith(fontSize: 12),
  );
}
///中文样式
Text textCardActionTitle(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.notoSans().copyWith(fontSize: 14).copyWith(
        color: Colors.deepOrangeAccent
    ),
  );
}

///中文样式
Text textCardTagsTitle(text, {textAlign: TextAlign.start}){
  return Text(
    text,
    textAlign: textAlign,
    style: GoogleFonts.aleo().copyWith(fontSize: 14).copyWith(
        color: Colors.blueGrey
    ),
  );
}

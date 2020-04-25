import 'package:chat_online/ui/chat.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() {
  final ThemeData kAndroidTheme = ThemeData(
      primarySwatch: Colors.purple, accentColor: Colors.orangeAccent[400]);

  runApp(MaterialApp(
    home: Chat(),
    debugShowCheckedModeBanner: false,
    theme: kAndroidTheme,
  ));
}

import 'package:flutter/material.dart';
import 'package:share/share.dart';

class GifPage extends StatelessWidget {
  final String gifUrl;

  GifPage(this.gifUrl);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Gif Viewer"),
        backgroundColor: Colors.black,
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.share),
            onPressed: (){
              Share.share(this.gifUrl);
            },
          )

        ],
      ),
      backgroundColor: Colors.black,
      body: Center(
        child: Image.network(gifUrl),
      ),
    );
  }
}

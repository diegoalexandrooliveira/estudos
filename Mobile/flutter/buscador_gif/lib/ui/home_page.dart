import 'package:buscador_gif/service/gif_service.dart';
import 'package:buscador_gif/ui/gif_page.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:share/share.dart';
import 'package:transparent_image/transparent_image.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  String search = "";
  int offset = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.black,
        title: Image.network(
            "https://developers.giphy.com/static/img/dev-logo-lg.7404c00322a8.gif"),
        centerTitle: true,
      ),
      backgroundColor: Colors.black,
      body: Column(
        children: <Widget>[
          Padding(
            padding: EdgeInsets.all(10),
            child: TextField(
              onSubmitted: (text) {
                setState(() {
                  this.offset = 0;
                  this.search = text;
                });
              },
              decoration: InputDecoration(
                  labelText: "Pesquise aqui!",
                  labelStyle: TextStyle(color: Colors.white),
                  border: OutlineInputBorder(),
                  enabledBorder: OutlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.white,
                          style: BorderStyle.solid,
                          width: 1))),
              style: TextStyle(color: Colors.white, fontSize: 18),
              textAlign: TextAlign.center,
            ),
          ),
          Expanded(
            child: FutureBuilder(
              future: reloadGifs(),
              builder: (context, snapshot) {
                switch (snapshot.connectionState) {
                  case ConnectionState.waiting:
                  case ConnectionState.none:
                    return Container(
                      width: 200,
                      height: 200,
                      alignment: Alignment.center,
                      child: CircularProgressIndicator(
                        valueColor: AlwaysStoppedAnimation<Color>(Colors.white),
                        strokeWidth: 5,
                      ),
                    );
                  default:
                    if (snapshot.hasError) {
                      return Container();
                    } else {
                      return _createGifTable(context, snapshot);
                    }
                }
              },
            ),
          )
        ],
      ),
    );
  }

  Widget _createGifTable(BuildContext context, AsyncSnapshot snapshot) {
    List items = snapshot.data;
    int numberOfItems = items.length;
    bool searching = search.isNotEmpty;
    if (searching) {
      numberOfItems++;
    }
    return GridView.builder(
        padding: EdgeInsets.all(10),
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2, crossAxisSpacing: 10, mainAxisSpacing: 10),
        itemCount: numberOfItems,
        itemBuilder: (context, index) {
          if (!searching || index < items.length) {
            return GestureDetector(
              child: FadeInImage.memoryNetwork(
                  placeholder: kTransparentImage,
                  image: items[index],
                  height: 300,
                  fit: BoxFit.cover),
              onTap: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) {
                  return GifPage(items[index]);
                }));
              },
              onLongPress: () {
                Share.share(items[index]);
              },
            );
          } else {
            return Container(
              child: GestureDetector(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Icon(Icons.add, color: Colors.white, size: 70),
                    Text(
                      "Carregar mais...",
                      style: TextStyle(color: Colors.white, fontSize: 22),
                    )
                  ],
                ),
                onTap: () {
                  setState(() {
                    this.offset += 19;
                  });
                },
              ),
            );
          }
        });
  }

  Future<List> reloadGifs() {
    Future<List> result;

    if (this.search.isEmpty) {
      result = GifService.getTrending();
    } else {
      result = GifService.getSearch(this.search, offset);
    }

    return result;
  }
}

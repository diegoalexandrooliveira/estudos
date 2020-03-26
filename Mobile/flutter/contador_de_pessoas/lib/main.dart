import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(title: 'Contador de pessoas', home: Home()));
}

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  int _people = 0;
  String _infoText = "Pode entrar!";


  void changePeople(int delta) {
    setState(() {
      _people += delta;
      if(_people >= 10){
        _infoText = "Restaurante cheio!";
      } else {
        _infoText = "Pode entrar!";
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: <Widget>[
        Image.asset(
          "images/restaurant.jpg",
          fit: BoxFit.cover,
          height: 1000,
        ),
        Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text("Pessoas: $_people",
                style: TextStyle(
                    color: Colors.white, fontWeight: FontWeight.bold)),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Padding(
                  padding: EdgeInsets.all(10),
                  child: FlatButton(
                    onPressed: () {
                      changePeople(1);
                    },
                    child: Text(
                      "+1",
                      style: TextStyle(fontSize: 40, color: Colors.white),
                    ),
                  ),
                ),
                Padding(
                  padding: EdgeInsets.all(10),
                  child: FlatButton(
                    onPressed: () {
                      changePeople(-1);
                    },
                    child: Text(
                      "-1",
                      style: TextStyle(fontSize: 40, color: Colors.white),
                    ),
                  ),
                )
              ],
            ),
            Text(_infoText,
                style: TextStyle(
                    color: Colors.white,
                    fontStyle: FontStyle.italic,
                    fontSize: 30))
          ],
        )
      ],
    );
  }
}

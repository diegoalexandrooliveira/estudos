import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
    home: Home(),
  ));
}

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  TextEditingController _pesoController = TextEditingController();
  TextEditingController _alturaController = TextEditingController();
  String _info = "Informe seus dados!";

  GlobalKey<FormState> _globalKey = GlobalKey<FormState>();

  void _reset() {
    _pesoController.text = "";
    _alturaController.text = "";

    setState(() {
      _info = "Informe seus dados!";
    });
  }

  void _caculate() {
    double peso = double.parse(_pesoController.text);
    double altura = double.parse(_alturaController.text) / 100;
    double imc = peso / (altura * altura);
    setState(() {
      if (imc < 18.6) {
        _info = "Abaixo do peso (${imc.toStringAsPrecision(3)})";
      } else if (imc >= 18.6 && imc < 24.9) {
        _info = "Peso ideal (${imc.toStringAsPrecision(3)})";
      } else if (imc >= 24.9 && imc < 29.9) {
        _info = "Levemente acima do peso (${imc.toStringAsPrecision(3)})";
      } else if (imc >= 29.9 && imc < 34.9) {
        _info = "Obesidade grau I (${imc.toStringAsPrecision(3)})";
      } else if (imc >= 34.9 && imc < 39.9) {
        _info = "Obesidade grau II (${imc.toStringAsPrecision(3)})";
      } else if (imc >= 39.9) {
        _info = "Obesidade grau III (${imc.toStringAsPrecision(3)})";
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Calculadora IMC"),
          centerTitle: true,
          backgroundColor: Colors.green,
          actions: <Widget>[
            IconButton(
              onPressed: () {
                _reset();
              },
              icon: Icon(Icons.refresh),
            )
          ],
        ),
        backgroundColor: Colors.white,
        body: Form(
          key: _globalKey,
          child: SingleChildScrollView(
              padding: EdgeInsets.fromLTRB(10, 0, 10, 0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: <Widget>[
                  Icon(
                    Icons.person_outline,
                    size: 120,
                    color: Colors.green,
                  ),
                  TextFormField(
                    keyboardType: TextInputType.number,
                    decoration: InputDecoration(
                        labelText: "Peso (Kg)",
                        labelStyle: TextStyle(color: Colors.green)),
                    textAlign: TextAlign.center,
                    style: TextStyle(color: Colors.green, fontSize: 25),
                    controller: _pesoController,
                    validator: (value) {
                      if (value.isEmpty) {
                        return "Digite um valor.";
                      } else if (double.parse(value) <= 0) {
                        return "Digite um valor maior que zero.";
                      }
                    },
                  ),
                  TextFormField(
                      keyboardType: TextInputType.number,
                      decoration: InputDecoration(
                          labelText: "Altura (cm)",
                          labelStyle: TextStyle(color: Colors.green)),
                      textAlign: TextAlign.center,
                      style: TextStyle(color: Colors.green, fontSize: 25),
                      controller: _alturaController,
                      validator: (value) {
                        if (value.isEmpty) {
                          return "Digite um valor.";
                        } else if (double.parse(value) <= 0) {
                          return "Digite um valor maior que zero.";
                        }
                      }),
                  Padding(
                    padding: EdgeInsets.only(top: 10, bottom: 10),
                    child: Container(
                      height: 50,
                      child: RaisedButton(
                        onPressed: () {
                          if (_globalKey.currentState.validate()) {
                            _caculate();
                          }
                        },
                        child: Text(
                          "Calcular",
                          style: TextStyle(color: Colors.white, fontSize: 25),
                        ),
                        color: Colors.green,
                      ),
                    ),
                  ),
                  Text(
                    "$_info",
                    textAlign: TextAlign.center,
                    style: TextStyle(color: Colors.green, fontSize: 25),
                  )
                ],
              )),
        ));
  }
}

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

const String apiUrl = "https://api.hgbrasil.com/finance";

void main() {
  runApp(MaterialApp(
    home: Home(),
    theme: ThemeData(hintColor: Colors.amber, primaryColor: Colors.white),
  ));
}

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  TextEditingController reaisController = TextEditingController();
  TextEditingController dolaresController = TextEditingController();
  TextEditingController eurosController = TextEditingController();
  Currencies currencies;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        title: const Text('\$ Conversor de moedas \$'),
        centerTitle: true,
        backgroundColor: Colors.amber,
      ),
      body: FutureBuilder<Currencies>(
        future: getCurrencies(),
        builder: (context, snapshot) {
          switch (snapshot.connectionState) {
            case ConnectionState.none:
            case ConnectionState.waiting:
              return carregando();
            default:
              return body(snapshot.data);
          }
        },
      ),
    );
  }

  Future<Currencies> getCurrencies() {
    return http.get(apiUrl).then((response) {
      Map currenciesMap = json.decode(response.body)['results']['currencies'];
      double dollar = currenciesMap['USD']['buy'];
      double euro = currenciesMap['EUR']['buy'];

      Currencies currencies = Currencies(dollar, euro);

      return Future.value(currencies);
    });
  }

  Center carregando() {
    return Center(
      child: Text(
        "Carregando dados...",
        textAlign: TextAlign.center,
        style: TextStyle(color: Colors.amber, fontSize: 25),
      ),
    );
  }

  SingleChildScrollView body(Currencies currencies) {
    this.currencies = currencies;
    return SingleChildScrollView(
      padding: EdgeInsets.all(10),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          Icon(
            Icons.monetization_on,
            size: 150,
            color: Colors.amber,
          ),
          createField(CurrenciesEnum.REAIS),
          Divider(),
          createField(CurrenciesEnum.DOLAR),
          Divider(),
          createField(CurrenciesEnum.EURO)
        ],
      ),
    );
  }

  TextField createField(CurrenciesEnum currencie) {
    String name;
    String symbol;
    TextEditingController controller;
    Function onChange;

    if (currencie == CurrenciesEnum.DOLAR) {
      name = "Dólares";
      symbol = "US\$";
      controller = dolaresController;
      onChange = _onDolarChange;
    } else if (currencie == CurrenciesEnum.REAIS) {
      name = "Reais";
      symbol = "R\$";
      controller = reaisController;
      onChange = _onRealChange;
    } else if (currencie == CurrenciesEnum.EURO) {
      name = "Euros";
      symbol = "€";
      controller = eurosController;
      onChange = _onEuroChange;
    }

    return TextField(
      controller: controller,
      onChanged: onChange,
      keyboardType: TextInputType.number,
      style: TextStyle(color: Colors.amber, fontSize: 25),
      decoration: InputDecoration(
          enabledBorder: OutlineInputBorder(
              borderSide: BorderSide(
                  style: BorderStyle.solid, color: Colors.amber, width: 1)),
          labelText: name,
          labelStyle: TextStyle(color: Colors.amber),
          border: OutlineInputBorder(),
          prefix: Text(symbol)),
    );
  }

  void _onRealChange(String textReal) {
    if (textReal.isNotEmpty) {
      double real = double.parse(textReal);
      this.eurosController.text =
          (real / this.currencies.euro).toStringAsPrecision(3);
      this.dolaresController.text =
          (real / this.currencies.dollar).toStringAsPrecision(3);
    } else {
      this.eurosController.text = "0";
      this.dolaresController.text = "0";
    }
  }

  void _onDolarChange(String textDolar) {
    if (textDolar.isNotEmpty) {
      double dolar = double.parse(textDolar);
      double real = dolar * this.currencies.dollar;
      this.reaisController.text = real.toStringAsPrecision(3);
      this.eurosController.text =
          (real / this.currencies.euro).toStringAsPrecision(3);
    } else {
      this.reaisController.text = "0";
      this.eurosController.text = "0";
    }
  }

  void _onEuroChange(String textEuro) {
    if (textEuro.isNotEmpty) {
      double euro = double.parse(textEuro);
      double real = euro * this.currencies.euro;
      this.reaisController.text = real.toStringAsPrecision(3);
      this.dolaresController.text =
          (real / this.currencies.dollar).toStringAsPrecision(3);
    } else {
      this.reaisController.text = "0";
      this.dolaresController.text = "0";
    }
  }
}

class Currencies {
  double dollar;
  double euro;

  Currencies(this.dollar, this.euro);
}

enum CurrenciesEnum { DOLAR, REAIS, EURO }

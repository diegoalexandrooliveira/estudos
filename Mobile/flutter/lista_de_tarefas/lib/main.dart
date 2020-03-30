import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';

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
  List _toDoList = [];

  Map<String, dynamic> _lastRemoved;
  int _lastRemovedPosition;

  TextEditingController _novaTarefaController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: _loadData(),
      builder: (context, snapshot) {
        this._toDoList = snapshot.data == null ? [] : snapshot.data;
        return Scaffold(
          appBar: appBar(),
          body: body(),
        );
      },
    );
  }

  Widget appBar() {
    return AppBar(
      title: Text(
        "Lista de tarefas",
      ),
      centerTitle: true,
      backgroundColor: Colors.blueAccent,
    );
  }

  Widget body() {
    return Column(
      children: <Widget>[
        Container(
          padding: EdgeInsets.fromLTRB(17, 1, 7, 1),
          child: Row(
            children: <Widget>[
              Expanded(
                child: TextField(
                  controller: _novaTarefaController,
                  decoration: InputDecoration(
                      labelText: "Nova tarefa",
                      labelStyle: TextStyle(color: Colors.blueAccent)),
                ),
              ),
              RaisedButton(
                color: Colors.blueAccent,
                onPressed: _addTodoList,
                child: Icon(Icons.add),
                textColor: Colors.white,
              )
            ],
          ),
        ),
        Expanded(
          child: RefreshIndicator(
            onRefresh: _refresh,
            child: ListView.builder(
                padding: EdgeInsets.only(top: 10),
                itemCount: _toDoList.length,
                itemBuilder: buildItem),
          ),
        )
      ],
    );
  }

  Future<void> _refresh() {
    setState(() {
      _toDoList.sort((item1, item2) {
        if (item1["ok"] && !item2["ok"]) {
          return 1;
        } else if (!item1["ok"] && item2["ok"]) {
          return -1;
        } else {
          return 0;
        }
      });
      _saveData();
    });
    return Future.value(null);
  }

  Widget buildItem(BuildContext context, int index) {
    String title = _toDoList[index]["title"];
    bool ok = _toDoList[index]["ok"];

    return Dismissible(
      key: UniqueKey(),
      direction: DismissDirection.startToEnd,
      onDismissed: (direction) {
        setState(() {
          _lastRemoved = Map.from(_toDoList[index]);
          _lastRemovedPosition = index;
          _toDoList.removeAt(index);
          _saveData();

          final snackbar = SnackBar(
            content: Text("Tarefa ${_lastRemoved["title"]} removida!"),
            action: SnackBarAction(
              label: "Desfazer",
              onPressed: () {
                setState(() {
                  _toDoList.insert(_lastRemovedPosition, _lastRemoved);
                  _saveData();
                });
              },
            ),
            duration: Duration(seconds: 2),
          );

          Scaffold.of(context).showSnackBar(snackbar);
        });
      },
      background: Container(
        color: Colors.red,
        child: Align(
          alignment: Alignment(-0.9, 0),
          child: Icon(
            Icons.delete,
            color: Colors.white,
          ),
        ),
      ),
      child: CheckboxListTile(
        title: Text(title),
        value: ok,
        secondary: CircleAvatar(
          child: Icon(ok ? Icons.check : Icons.error),
        ),
        onChanged: (_ok) {
          setState(() {
            _toDoList[index]["ok"] = _ok;
            _saveData();
          });
        },
      ),
    );
  }

  void _addTodoList() {
    String nomeTarefa = _novaTarefaController.text;
    _novaTarefaController.text = "";
    setState(() {
      _toDoList.add({"title": nomeTarefa, "ok": false});
      _saveData();
    });
  }

  Future<File> _getFile() {
    return getApplicationDocumentsDirectory()
        .then((directory) => File("${directory.path}/data.json"));
  }

  Future<void> _saveData() {
    return _getFile().then((file) {
      String jsonData = json.encode(_toDoList);
      file.writeAsStringSync(jsonData);
    });
  }

  Future<List> _loadData() {
    return _getFile().then((file) {
      return json.decode(file.readAsStringSync());
    });
  }
}

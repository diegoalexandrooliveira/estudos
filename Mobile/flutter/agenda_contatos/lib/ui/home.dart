import 'dart:io';

import 'package:agenda_contatos/dao/contact_dao.dart';
import 'package:agenda_contatos/models/contact.dart';
import 'package:agenda_contatos/ui/contact_page.dart';
import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  List<Contact> contacts = [];

  @override
  void initState() {
    super.initState();
    _reloadContacts();
  }

  void _reloadContacts({bool orderDesc}) {
    ContactDAO.internal().findAll(orderDesc: orderDesc).then((contacts) {
      setState(() {
        this.contacts = contacts;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Contatos"),
        backgroundColor: Colors.red,
        centerTitle: true,
        actions: <Widget>[
          PopupMenuButton<Order>(
            itemBuilder: (context) => <PopupMenuEntry<Order>>[
              const PopupMenuItem(
                child: Text("Ordernar A-Z"),
                value: Order.ORDER_A_Z,
              ),
              const PopupMenuItem(
                child: Text("Ordernar Z-A"),
                value: Order.ORDER_Z_A,
              )
            ],
            onSelected: (order) {
              setState(() {
                this._reloadContacts(orderDesc: order != Order.ORDER_A_Z);
              });
            },
          )
        ],
      ),
      backgroundColor: Colors.white,
      floatingActionButton: FloatingActionButton(
        onPressed: () => this._showContactPage(),
        child: Icon(Icons.add),
        backgroundColor: Colors.red,
      ),
      body: ListView.builder(
        itemCount: this.contacts.length,
        itemBuilder: (context, index) =>
            buildItem(context, this.contacts[index]),
        padding: EdgeInsets.all(10),
      ),
    );
  }

  Widget buildItem(BuildContext context, Contact contact) {
    return GestureDetector(
      onTap: () => _showOptions(context, contact),
      child: Card(
        child: Padding(
          padding: EdgeInsets.all(10),
          child: Row(
            children: <Widget>[
              Container(
                width: 80,
                height: 80,
                decoration: BoxDecoration(
                    shape: BoxShape.circle,
                    image: DecorationImage(image: loadImage(contact))),
              ),
              Padding(
                padding: EdgeInsets.only(left: 10),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(
                      contact.name ?? "",
                      style:
                          TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
                    ),
                    Text(
                      contact.email ?? "",
                      style: TextStyle(fontSize: 18),
                    ),
                    Text(
                      contact.phone ?? "",
                      style: TextStyle(fontSize: 18),
                    )
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
//    return Text(contacts[index].name);
  }

  ImageProvider loadImage(Contact contact) {
    return contact.image != null && contact.image.isNotEmpty
        ? FileImage(File(contact.image))
        : AssetImage("images/person.png");
  }

  void _showContactPage({Contact contact}) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => ContactPage(
                  contact: contact,
                ))).then((a) => this._reloadContacts());
  }

  void _showOptions(BuildContext context, Contact contact) {
    showModalBottomSheet(
        context: context,
        builder: (context) {
          return BottomSheet(
            onClosing: () {},
            builder: (context) {
              return Container(
                padding: EdgeInsets.all(10),
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: <Widget>[
                    Padding(
                        padding: EdgeInsets.all(10),
                        child: FlatButton(
                          child: Text("Ligar",
                              style:
                                  TextStyle(color: Colors.red, fontSize: 20)),
                          onPressed: () {
                            _callContact(contact);
                            Navigator.pop(context);
                          },
                        )),
                    Padding(
                      padding: EdgeInsets.all(10),
                      child: FlatButton(
                          child: Text("Editar",
                              style:
                                  TextStyle(color: Colors.red, fontSize: 20)),
                          onPressed: () {
                            Navigator.pop(context);
                            this._showContactPage(contact: contact);
                          }),
                    ),
                    Padding(
                      padding: EdgeInsets.all(10),
                      child: FlatButton(
                        child: Text("Excluir",
                            style: TextStyle(color: Colors.red, fontSize: 20)),
                        onPressed: () {
                          Navigator.pop(context);
                          this._removeContact(contact);
                        },
                      ),
                    )
                  ],
                ),
              );
            },
          );
        });
  }

  void _removeContact(Contact contact) {
    ContactDAO.internal()
        .delete(contact)
        .then((rows) => this._reloadContacts());
  }

  void _callContact(Contact contact) {
    launch("tel:${contact.phone}");
  }
}

enum Order { ORDER_A_Z, ORDER_Z_A }

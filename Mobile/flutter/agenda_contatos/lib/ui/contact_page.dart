import 'dart:async';
import 'dart:io';

import 'package:agenda_contatos/dao/contact_dao.dart';
import 'package:agenda_contatos/models/contact.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';

class ContactPage extends StatefulWidget {
  final Contact contact;

  ContactPage({this.contact});

  @override
  _ContactPageState createState() => _ContactPageState();
}

class _ContactPageState extends State<ContactPage> {
  Contact _editedContact;
  bool _update = false;
  bool _userEdited = false;
  TextEditingController _nameController = TextEditingController();
  TextEditingController _emailController = TextEditingController();
  TextEditingController _phoneController = TextEditingController();
  FocusNode _nameFocus = FocusNode();

  @override
  void initState() {
    super.initState();
    if (widget.contact == null) {
      this._update = false;
      this._editedContact = new Contact();
    } else {
      this._update = true;
      this._editedContact = Contact.fromMap(widget.contact.toMap());
      _nameController.text = _editedContact.name;
      _emailController.text = _editedContact.email;
      _phoneController.text = _editedContact.phone;
    }
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: _onPop,
      child: Scaffold(
        appBar: AppBar(
            backgroundColor: Colors.red,
            title: Text(this._editedContact.name ?? "Novo contato"),
            centerTitle: true),
        floatingActionButton: FloatingActionButton(
          child: Icon(Icons.save),
          backgroundColor: Colors.red,
          onPressed: () {
            if (this._nameController.text.isEmpty) {
              FocusScope.of(context).requestFocus(this._nameFocus);
            } else {
              Future<Contact> response;
              if (this._update) {
                response = ContactDAO.internal().update(this._editedContact);
              } else {
                response = ContactDAO.internal().save(this._editedContact);
              }
              response.then((contact) {
                Navigator.pop(context);
              });
            }
          },
        ),
        body: SingleChildScrollView(
          padding: EdgeInsets.all(10),
          child: Column(
            children: <Widget>[
              GestureDetector(
                child: Container(
                  width: 280,
                  height: 280,
                  decoration: BoxDecoration(
                      shape: BoxShape.circle,
                      image: DecorationImage(
                          image: loadImage(this._editedContact))),
                ),
                onTap: () {
                  _chooseImage();
                },
              ),
              TextField(
                focusNode: this._nameFocus,
                controller: _nameController,
                decoration: InputDecoration(labelText: "Nome"),
                onChanged: (text) {
                  _userEdited = true;
                  setState(() {
                    _editedContact.name = text.isEmpty ? null : text;
                  });
                },
              ),
              TextField(
                controller: _emailController,
                decoration: InputDecoration(labelText: "Email"),
                onChanged: (text) {
                  _userEdited = true;
                  _editedContact.email = text;
                },
                keyboardType: TextInputType.emailAddress,
              ),
              TextField(
                controller: _phoneController,
                decoration: InputDecoration(labelText: "Telefone"),
                onChanged: (text) {
                  _userEdited = true;
                  _editedContact.phone = text;
                },
                keyboardType: TextInputType.phone,
              )
            ],
          ),
        ),
      ),
    );
  }

  ImageProvider loadImage(Contact contact) {
    return contact.image != null && contact.image.isNotEmpty
        ? FileImage(File(contact.image))
        : AssetImage("images/person.png");
  }

  Future<bool> _onPop() {
    if (!_userEdited) {
      return Future.value(true);
    } else {
      showDialog(
          context: context,
          builder: (context) {
            return AlertDialog(
              title: Text("Descartar alterações?"),
              content: Text("Se sair as alterações serão perdidas."),
              actions: <Widget>[
                FlatButton(
                  child: Text("Cancelar"),
                  onPressed: () {
                    Navigator.pop(context);
                  },
                ),
                FlatButton(
                  child: Text("Sim"),
                  onPressed: () {
                    Navigator.pop(context);
                    Navigator.pop(context);
                  },
                )
              ],
            );
          });
      return Future.value(false);
    }
  }

  void _chooseImage() {
    ImagePicker.pickImage(source: ImageSource.gallery).then((picture) {
      if (picture != null) {
        setState(() {
          this._userEdited = true;
          this._editedContact.image = picture.path;
        });
      }
    });
  }
}

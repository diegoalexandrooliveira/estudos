import 'dart:async';

import 'package:agenda_contatos/models/contact.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class ContactDAO {
  static final String idColumnName = "id";
  static final String nameColumnName = "name";
  static final String emailColumnName = "email";
  static final String phoneColumnName = "phone";
  static final String imageColumnName = "image";
  static final String tableContact = "contact";

  static final ContactDAO _instance = ContactDAO.internal();

  factory ContactDAO() => _instance;

  ContactDAO.internal();

  Future<Database> _initDatabase() {
    return getDatabasesPath().then((path) {
      final databasePath = join(path, "contacts.db");
      return openDatabase(databasePath, version: 1, onCreate: (db, newVersion) {
        db.execute(
            "create table $tableContact ($idColumnName integer primary key,"
            "$nameColumnName text,"
            "$emailColumnName text,"
            "$phoneColumnName text,"
            "$imageColumnName text"
            ")");
      });
    });
  }

  Future<Contact> save(Contact contact) {
    return _initDatabase().then((database) {
      return database.insert(tableContact, contact.toMap()).then((id) {
        contact.id = id;
        return contact;
      });
    });
  }

  Future<Contact> update(Contact contact) {
    return _initDatabase().then((database) {
      return database.update(tableContact, contact.toMap(),
          where: "$idColumnName = ?",
          whereArgs: [contact.id]).then((rows) => contact);
    });
  }

  Future<int> delete(Contact contact) {
    return _initDatabase().then((database) {
      return database.delete(tableContact,
          where: "$idColumnName = ?", whereArgs: [contact.id]);
    });
  }

  Future<List<Contact>> findAll({bool orderDesc}) {
    return _initDatabase().then((database) {
      return database
          .query(tableContact,
              orderBy: orderDesc ?? false ? "name DESC" : "name ASC")
          .then((values) {
        return values.map((value) => Contact.fromMap(value)).toList();
      });
    });
  }
}

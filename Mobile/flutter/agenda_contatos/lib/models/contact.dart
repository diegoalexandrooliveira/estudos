import 'package:agenda_contatos/dao/contact_dao.dart';

class Contact {
  int id;
  String name;
  String email;
  String phone;
  String image;

  Contact.fromMap(Map map) {
    this.id = map[ContactDAO.idColumnName];
    this.name = map[ContactDAO.nameColumnName];
    this.email = map[ContactDAO.emailColumnName];
    this.phone = map[ContactDAO.phoneColumnName];
    this.image = map[ContactDAO.imageColumnName];
  }

  Contact();

  Map toMap() {
    Map<String, dynamic> map = {
      ContactDAO.nameColumnName: name,
      ContactDAO.emailColumnName: email,
      ContactDAO.phoneColumnName: phone,
      ContactDAO.imageColumnName: image
    };
//    Map map = new Map();
//    map[ContactDAO.nameColumnName] = name;
//    map[ContactDAO.emailColumnName] = email;
//    map[ContactDAO.phoneColumnName] = phone;
//    map[ContactDAO.imageColumnName] = image;
    if (id != null) {
      map[ContactDAO.idColumnName] = id;
    }
    return map;
  }

  @override
  String toString() {
    return "Contato: id $id name $name phone $phone email $email";
  }
}

import 'package:chat_online/application/SignIn.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:image_picker/image_picker.dart';

class Chat extends StatefulWidget {
  @override
  _ChatState createState() => _ChatState();
}

class _ChatState extends State<Chat> {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      bottom: false,
      top: false,
      child: Scaffold(
        appBar: AppBar(
          title: Text("Chat App"),
          centerTitle: true,
        ),
        body: Column(
          children: <Widget>[
            Expanded(
                child: StreamBuilder(
                    stream: Firestore.instance
                        .collection("mensagens")
                        .orderBy("createdAt", descending: true)
                        .snapshots(),
                    builder: (context, snapshot) {
                      switch (snapshot.connectionState) {
                        case ConnectionState.waiting:
                        case ConnectionState.none:
                          return Center(
                            child: CircularProgressIndicator(),
                          );
                        default:
                          List<DocumentSnapshot> documents =
                              snapshot.data.documents;
                          List<ChatMessage> allMessages = documents
                              .map((message) => new ChatMessage(
                                  message["username"],
                                  message["usernamePhotoUrl"],
                                  message["text"],
                                  message["imgUrl"]))
                              .toList();
                          return ListView(
                            reverse: true,
                            children: allMessages,
                          );
                      }
                    })),
            Divider(
              height: 1,
            ),
            Container(
              decoration: BoxDecoration(
                color: Theme.of(context).cardColor,
              ),
              child: TextComposer(),
            )
          ],
        ),
      ),
    );
  }
}

class TextComposer extends StatefulWidget {
  @override
  _TextComposerState createState() => _TextComposerState();
}

class _TextComposerState extends State<TextComposer> {
  bool textToSend = false;
  TextEditingController _inputController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return IconTheme(
      data: IconThemeData(color: Theme.of(context).accentColor),
      child: Container(
        margin: EdgeInsets.symmetric(horizontal: 8),
        child: Row(
          children: <Widget>[
            Container(
              child: IconButton(
                icon: Icon(Icons.photo_camera),
                onPressed: () => handleImage(),
              ),
            ),
            Expanded(
              child: TextField(
                controller: _inputController,
                decoration:
                    InputDecoration.collapsed(hintText: "Enviar uma mensagem"),
                onChanged: (text) {
                  setState(() {
                    this.textToSend = text.length > 0;
                  });
                },
                onSubmitted: (text) => this.handleSubmit(text),
              ),
            ),
            Container(
              margin: const EdgeInsets.symmetric(horizontal: 4),
              child: IconButton(
                icon: Icon(Icons.send),
                onPressed: textToSend
                    ? () => this.handleSubmit(this._inputController.text)
                    : null,
              ),
            )
          ],
        ),
      ),
    );
  }

  void handleSubmit(String text) {
    SignIn.instance().checkSignIn().then((loggedUser) {
      sendMessage(loggedUser, text: text);
      this._inputController.clear();
      setState(() {
        textToSend = false;
      });
    });
  }

  void sendMessage(GoogleSignInAccount loggedUser,
      {String text, String imgUrl}) {
    Firestore.instance.collection("mensagens").add({
      "text": text,
      "imgUrl": imgUrl,
      "username": loggedUser.displayName,
      "usernamePhotoUrl": loggedUser.photoUrl,
      "createdAt": DateTime.now()
    });
  }

  void handleImage() {
    SignIn.instance().checkSignIn().then((loggedUser) {
      ImagePicker.pickImage(source: ImageSource.camera).then((image) {
        if (image == null) {
          return;
        }
        var id =
            loggedUser.id + DateTime.now().millisecondsSinceEpoch.toString();
        StorageUploadTask task =
            FirebaseStorage.instance.ref().child(id).putFile(image);
        task.onComplete.then((snapshot) => snapshot.ref
            .getDownloadURL()
            .then((url) => sendMessage(loggedUser, imgUrl: url)));
      });
    });
  }
}

class ChatMessage extends StatelessWidget {
  final String name, photoUrl, message, imgUrl;

  ChatMessage(this.name, this.photoUrl, this.message, this.imgUrl);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(horizontal: 10, vertical: 10),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            margin: EdgeInsets.only(right: 16),
            child: CircleAvatar(
              backgroundImage: NetworkImage(photoUrl),
            ),
          ),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Text(
                  name,
                  style: Theme.of(context).textTheme.subhead,
                ),
                Container(
                  margin: EdgeInsets.only(top: 5),
                  child: imgUrl != null
                      ? Image.network(
                          imgUrl,
                          width: 250,
                        )
                      : Text(message),
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}

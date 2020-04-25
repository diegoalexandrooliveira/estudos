import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';

class SignIn {
  static var _instance;

  final _googleSignIn = GoogleSignIn();
  final _auth = FirebaseAuth.instance;

  static SignIn instance() {
    if (_instance == null) {
      _instance = new SignIn();
    }
    return _instance;
  }

  Future<GoogleSignInAccount> checkSignIn() {
    return Future.value(this._googleSignIn.currentUser).then((user) {
      Future<GoogleSignInAccount> signIn = Future.value(user);
      if (user == null) {
        signIn = this._googleSignIn.signInSilently();
      }
      return signIn;
    }).then((user) {
      Future<GoogleSignInAccount> signIn = Future.value(user);
      if (user == null) {
        signIn = this._googleSignIn.signIn();
      }
      return signIn;
    }).then((googleAccount) async {
      var currentUser = await this._auth.currentUser();
      if (currentUser == null) {
        var authentication = await googleAccount.authentication;
        var accessToken = authentication.accessToken;
        var idToken = authentication.idToken;
        var credential = GoogleAuthProvider.getCredential(
            idToken: idToken, accessToken: accessToken);
        await this._auth.signInWithCredential(credential);
      }
      return Future.value(googleAccount);
    });
  }
}

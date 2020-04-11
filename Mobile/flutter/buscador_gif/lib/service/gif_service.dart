import 'dart:convert';

import 'package:http/http.dart' as http;

class GifService {
  static String _url =
      "https://api.giphy.com/v1/gifs/OPERATION?api_key=A3jfA7XQ3ONv8NBlQGVgUpMa7uWYyGph&rating=G";

//  https://api.giphy.com/v1/gifs/search?api_key=A3jfA7XQ3ONv8NBlQGVgUpMa7uWYyGph&q=procurar&limit=25&offset=0&rating=G&lang=en

  static Future<List> getTrending() {
    return http.get(_url.replaceAll("OPERATION", "trending")+"&limit=20").then((response) {
      Map body = json.decode(response.body);
      return body["data"]
          .map((gif) => gif["images"]["fixed_height"]["url"].toString())
          .toList();
    });
  }

  static Future<List> getSearch(String text, int offset) {
    return http.get(_url.replaceAll("OPERATION", "search") + "&q=$text&offset=$offset&limit=19").then((response) {
      Map body = json.decode(response.body);
      return body["data"]
          .map((gif) => gif["images"]["fixed_height"]["url"].toString())
          .toList();
    });
  }
}

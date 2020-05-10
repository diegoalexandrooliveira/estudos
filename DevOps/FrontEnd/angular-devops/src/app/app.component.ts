import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-devops';

  readonly CONFIG_URL = 'assets/variables.json';
  apiURL = '';

  constructor(private httpClient: HttpClient) {

    httpClient.get(this.CONFIG_URL).subscribe(json => {
      this.apiURL = json["apiURL"]; 
    });

  }
}

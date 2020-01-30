import { Component, OnInit } from '@angular/core';

import { AuthService } from './core/auth-service.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {


  title = 'ocid-angular';

  isLoggedIn: boolean = false;

  constructor(private _authService: AuthService) {
    this._authService.loginChanged.subscribe(loggedIn => {
      this.isLoggedIn = loggedIn;
    })

  }

  ngOnInit(): void {
    this._authService.isLoggedIn().then(isLogged => {
      this.isLoggedIn = isLogged;
    });
  }

  login() {
    this._authService.login();
  }
}

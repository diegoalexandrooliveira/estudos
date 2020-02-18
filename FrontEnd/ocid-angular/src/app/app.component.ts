import { Component, OnInit } from '@angular/core';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {


  title = 'OIDC Angular';

  isLoggedIn: boolean = false;

  constructor(private _authService: OidcSecurityService) {

  }

  ngOnInit(): void {
    this._authService.getIsAuthorized().subscribe(auth => {
      this.isLoggedIn = auth;
    });

    this.getUser();
  }

  login() {
    this._authService.authorize();
  }

  logout() {
    this._authService.logoff();
  }

  getUser() {
    this._authService.getUserData().subscribe(userData => {
      console.log(userData);

    });
    console.log(this._authService.getToken());
    

  }
}

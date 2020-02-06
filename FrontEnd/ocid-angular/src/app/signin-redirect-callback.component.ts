import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
    selector: 'app-signin-callback',
    template: '<div></div>'
})

export class SigninCallbackComponent implements OnInit {
    constructor(private _authService: OidcSecurityService, private router: Router) { }

    ngOnInit() {
        this._authService.authorizedImplicitFlowCallback(window.location.toString());
        // this.router.navigate(['/'], { replaceUrl: true });
    }
}
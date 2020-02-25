import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from './auth.service';

@Component({
    selector: 'auth-callback',
    template: ''
})

export class AuthCallbackComponent {
    constructor(private _authService: AuthService, private router: Router) {
        if (this.router.url.match('signin-callback')) {
            this._authService.completeLogin()
                .then(user => {
                    this.router.navigateByUrl("/");
                });
        } else {
            this.router.navigateByUrl("/");
        }
    }
}
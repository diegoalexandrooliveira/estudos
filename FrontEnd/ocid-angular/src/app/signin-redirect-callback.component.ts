import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from './core/auth-service.component';

@Component({
    selector: 'app-signin-callback',
    template: '<div></div>'
})

export class SigninCallbackComponent implements OnInit {
    constructor(private _authService: AuthService, private router: Router) { }

    ngOnInit() {
        this._authService.completeLogin().then(() => {
            this.router.navigate(['/'], { replaceUrl: true });
        })
    }
}
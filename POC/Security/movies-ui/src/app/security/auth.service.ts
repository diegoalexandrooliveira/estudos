import { Injectable } from '@angular/core';
import { OidcClientSettings, UserManager } from 'oidc-client';

@Injectable({ providedIn: 'root' })
export class AuthService {

    private _userManager: UserManager;
    private access_token: string;

    constructor() {
        const settings: OidcClientSettings = {
            client_id: "movies-app",
            authority: "http://localhost:8180/auth/realms/master/.well-known/openid-configuration",
            post_logout_redirect_uri: "http://localhost:4200/signout-callback",
            redirect_uri: "http://localhost:4200/signin-callback",
            response_type: "code",
            loadUserInfo: true
        };
        this._userManager = new UserManager(settings);

        this._userManager.getUser()
            .then(user => {
                if (user) {
                    this.access_token = user.access_token;
                }
            })
    }

    getUser() {
        return this._userManager.getUser();
    }

    getToken() {
        return this.access_token;
    }

    login() {
        this._userManager.signinRedirect();
    }

    logout() {
        this._userManager.signoutRedirect()
            .then(() => {
                this._userManager.clearStaleState();
            });
    }

    completeLogin() {
        return this._userManager.signinRedirectCallback()
            .then(user => {
                if (user) {
                    this.access_token = user.access_token;
                }
            });
    }

    isLoggedIn(): Promise<boolean> {
        return this._userManager.getUser()
            .then(user => {
                return user ? true : false;
            });
    }

}
import { Injectable } from '@angular/core';
import { User, UserManager, UserManagerSettings } from 'oidc-client';
import { Subject } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({ providedIn: "root" })
export class AuthService {


    private _user: User;
    private _userManager: UserManager;
    private _loginChangedSubject: Subject<boolean> = new Subject<boolean>();

    loginChanged = this._loginChangedSubject.asObservable();

    constructor() {

        const stsSettings: UserManagerSettings = {
            authority: environment.stsAuthority,
            client_id: environment.clientId,
            redirect_uri: `${environment.clientRoot}signin-callback`,
            // scope: "openid profile projects-api",
            response_type: "code",
            post_logout_redirect_uri: `${environment.clientRoot}signout-callback`,
            metadata: {
                issuer: "https://dev--92-grqz.auth0.com/",
                authorization_endpoint: "https://dev--92-grqz.auth0.com/authorize",
                userinfo_endpoint: "https://dev--92-grqz.auth0.com/userinfo",
                token_endpoint: "https://dev--92-grqz.auth0.com/oauth/token",
                end_session_endpoint: `https://dev--92-grqz.auth0.com/logout?returnTo=${environment.clientRoot}signout-callback&client_id=${environment.clientId}`,
                jwks_uri: "https://dev--92-grqz.auth0.com/.well-known/jwks.json"
            }
        };

        this._userManager = new UserManager(stsSettings);

    }

    public login() {
        return this._userManager.signinRedirect();
    }

    public completeLogin(): Promise<User> {
        return this._userManager.signinRedirectCallback()
            .then(user => {
                this._user = user;
                console.log(user);
                this._loginChangedSubject.next(!!user && !user.expired);
                return user;
            });
    }

    public logout() {
        this._userManager.signoutRedirect();
    }

    public completeLogout() {
        this._user = null;
        return this._userManager.signoutRedirectCallback();
    }

    public isLoggedIn(): Promise<boolean> {
        return this._userManager.getUser().then(user => {
            const userCurrent = !!user && !user.expired;
            console.log(user);
            if (this._user !== user) {
                this._loginChangedSubject.next(userCurrent);
            }
            this._user = user;
            return userCurrent;
        });
    }

}
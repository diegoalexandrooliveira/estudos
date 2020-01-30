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
            redirect_uri: `${environment.clientRoot}`,
            scope: "openid profile projects-api",
            response_type: "code",
            post_logout_redirect_uri: `${environment.clientRoot}`
        };

        this._userManager = new UserManager(stsSettings);

    }

    public login() {
        return this._userManager.signinRedirect();
    }

    public isLoggedIn(): Promise<boolean> {
        return this._userManager.getUser().then(user => {
            const userCurrent = !!user && !user.expired;
            if (this._user !== user) {
                this._loginChangedSubject.next(userCurrent);
            }
            this._user = user;
            return userCurrent;
        });
    }

}
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
            // ,
            // signingKeys: [{
            //     "alg": "RS256",
            //     "kty": "RSA",
            //     "use": "sig",
            //     "n": "vGjYqQPZ5OqxF5YK--_fvkZF4Ov4Y3ATr1ZK-nHt5PMLpZdMk8gbP3W1fEv1dKdsxg3gJE1fIV6Lsh-rFukG-nvCCkKR01ff9QgMJndlwDS6_2hUofNh6Fo_Q5W7hijfKWPS2XobheGpyCw-odYTnMChIp2vrPdWrtMYTPswPBDXMONNdmX94Ne1pAEgscbcGHL1WMiGTz2oppl8itqihRPFT8X270Ji883L9AsY3T_gzYIEBM2zkFOlsva8Z1c6Fre0vEhWfCT2XgdmB9RJknI51kc16NY1shsw6ta1aS4in0h3tcZw2hVjGLkADkMkePvG_0Tx2LTmjOK7Dx761w",
            //     "e": "AQAB",
            //     "kid": "NDEzRTg1OEVCOUY0RDFCMkNBMkY3N0EyMTJFQzI1RjI2MEJDNTcyNg",
            //     "x5t": "NDEzRTg1OEVCOUY0RDFCMkNBMkY3N0EyMTJFQzI1RjI2MEJDNTcyNg",
            //     "x5c": [
            //         "MIIDBzCCAe+gAwIBAgIJFqZvMN/Sfcu5MA0GCSqGSIb3DQEBCwUAMCExHzAdBgNVBAMTFmRldi0tOTItZ3Jxei5hdXRoMC5jb20wHhcNMjAwMTI3MjM0OTEyWhcNMzMxMDA1MjM0OTEyWjAhMR8wHQYDVQQDExZkZXYtLTkyLWdycXouYXV0aDAuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvGjYqQPZ5OqxF5YK++/fvkZF4Ov4Y3ATr1ZK+nHt5PMLpZdMk8gbP3W1fEv1dKdsxg3gJE1fIV6Lsh+rFukG+nvCCkKR01ff9QgMJndlwDS6/2hUofNh6Fo/Q5W7hijfKWPS2XobheGpyCw+odYTnMChIp2vrPdWrtMYTPswPBDXMONNdmX94Ne1pAEgscbcGHL1WMiGTz2oppl8itqihRPFT8X270Ji883L9AsY3T/gzYIEBM2zkFOlsva8Z1c6Fre0vEhWfCT2XgdmB9RJknI51kc16NY1shsw6ta1aS4in0h3tcZw2hVjGLkADkMkePvG/0Tx2LTmjOK7Dx761wIDAQABo0IwQDAPBgNVHRMBAf8EBTADAQH/MB0GA1UdDgQWBBReIQDsKnV+yltMocZ2Omt6dQvdqTAOBgNVHQ8BAf8EBAMCAoQwDQYJKoZIhvcNAQELBQADggEBAIzsGihYQwjT7iEl71m5dT0sq8FCH4gCOi+4glmpByWcMUPrtLRQnCiLyRYUIweilIED3FD0bRvk+WTgz++9+Hb0G2YNOdP7oC//imoBff6TxZr+iAZbAjnx0ObYm3ncycYqcX7RkWA+1Rsd2Z8+1bTcsGDzWqMzameekAcmg0Ose0L3h+3FYVroQwohOJV+5+rVlru3LKigWHFBKtIzpvoCv8iY22v6M/I+PmZVgJhxvVNaYz3am44zzDTbAob0nJPsYnM6hGoQN3ziFzUWM66kFds3MeeuD/q8KIVUKpTai69Wm2kj8f6lLt7FKUhvkJOTxuHzEMNmNUWUFQHq9GA="
            //     ]
            // }]
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
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {
  AuthModule,
  AuthWellKnownEndpoints,
  OidcConfigService,
  OidcSecurityService,
  OpenIdConfiguration,
} from 'angular-auth-oidc-client';
import { environment } from 'src/environments/environment';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninCallbackComponent } from './signin-redirect-callback.component';
import { SignoutCallbackComponent } from './signout-redirect-callback.component';
import { Tela1Component } from './tela1/tela1.component';
import { Tela2Component } from './tela2/tela2.component';
import { Tela3Component } from './tela3/tela3.component';


export function loadConfig(oidcConfigService: OidcConfigService) {
  return () => oidcConfigService.load("");
}

@NgModule({
  declarations: [
    AppComponent,
    Tela1Component,
    Tela2Component,
    Tela3Component,
    SignoutCallbackComponent,
    SigninCallbackComponent
  ],
  imports: [
    AuthModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    OidcConfigService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor(private oidcSecurityService: OidcSecurityService) {

    // Use the configResult to set the configurations

    // const config: OpenIdConfiguration = {
    //   stsServer: "https://totvs-agro-1.rac.dev.totvs.io/totvs.rac",
    //   redirect_url: 'http://localhost:4200/signin-callback',
    //   client_id: environment.clientId,
    //   scope: 'openid profile email',
    //   response_type: 'id_token token',
    //   silent_renew: true,
    //   silent_renew_url: 'http://localhost:4200/assets/silent_renew.html',
    //   log_console_debug_active: true,
    //   unauthorized_route: "/"
    //   // all other properties you want to set
    // };

    // const authWellKnownEndpoints: AuthWellKnownEndpoints = {
    //   issuer: 'https://totvs-agro-1.rac.dev.totvs.io/totvs.rac',
    //   jwks_uri: "https://totvs-agro-1.rac.dev.totvs.io/totvs.rac/.well-known/openid-configuration/jwks",
    //   authorization_endpoint: 'https://totvs-agro-1.rac.dev.totvs.io/totvs.rac/connect/authorize',
    //   token_endpoint: 'https://totvs-agro-1.rac.dev.totvs.io/totvs.rac/connect/token',
    //   userinfo_endpoint: 'https://totvs-agro-1.rac.dev.totvs.io/totvs.rac/connect/userinfo',
    //   end_session_endpoint: 'https://totvs-agro-1.rac.dev.totvs.io/totvs.rac/connect/endsession',
    //   check_session_iframe: 'https://totvs-agro-1.rac.dev.totvs.io/totvs.rac/connect/checksession',
    //   revocation_endpoint: 'https://totvs-agro-1.rac.dev.totvs.io/totvs.rac/connect/revocation',
    //   introspection_endpoint: 'https://totvs-agro-1.rac.dev.totvs.io/totvs.rac/connect/introspect',
    // };

    const config: OpenIdConfiguration = {
      stsServer: "http://localhost:8080/auth/realms/master",
      redirect_url: 'http://localhost:4200/signin-callback',
      client_id: environment.clientId,
      scope: 'openid',
      response_type: 'code',
      silent_renew: true,
      silent_renew_url: 'http://localhost:4200/assets/silent_renew.html',
      log_console_debug_active: true,
      post_logout_redirect_uri: "http://localhost:4200/signout-callback",
      unauthorized_route: "/"
      // all other properties you want to set
    };

    const authWellKnownEndpoints: AuthWellKnownEndpoints = {
      issuer: 'http://localhost:8080/auth/realms/master',
      jwks_uri: "http://localhost:8080/auth/realms/master/protocol/openid-connect/certs",
      authorization_endpoint: 'http://localhost:8080/auth/realms/master/protocol/openid-connect/auth',
      token_endpoint: 'http://localhost:8080/auth/realms/master/protocol/openid-connect/token',
      userinfo_endpoint: 'http://localhost:8080/auth/realms/master/protocol/openid-connect/userinfo',
      end_session_endpoint: 'http://localhost:8080/auth/realms/master/protocol/openid-connect/logout',
      check_session_iframe: 'http://localhost:8080/auth/realms/master/protocol/openid-connect/login-status-iframe.html',
      // revocation_endpoint: 'https://totvs-agro-1.rac.dev.totvs.io/totvs.rac/connect/revocation',
      // introspection_endpoint: 'https://totvs-agro-1.rac.dev.totvs.io/totvs.rac/connect/introspect',
    };

    this.oidcSecurityService.setupModule(config, authWellKnownEndpoints);
  }

}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PoModule } from '@portinari/portinari-ui';
import { RouterModule } from '@angular/router';
import { Componente2Component } from './componente2/componente2.component';
import { Componente3Component } from './componente3/componente3.component';

@NgModule({
  declarations: [
    AppComponent,
    Componente2Component,
    Componente3Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PoModule,
    RouterModule.forRoot([])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

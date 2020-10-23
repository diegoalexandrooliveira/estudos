import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeroisComponent } from './herois/herois.component';
import { HeroiComponent } from './heroi/heroi.component';


@NgModule({
  declarations: [
    AppComponent,
    HeroisComponent,
    HeroiComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeroisComponent } from './herois/herois.component';
import { HeroiComponent } from './heroi/heroi.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HeroiService } from './heroi.service';


@NgModule({
  declarations: [
    AppComponent,
    HeroiComponent,
    HeroisComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [HeroiService],
  bootstrap: [AppComponent]
})
export class AppModule { }

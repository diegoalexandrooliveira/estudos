import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { PoModule } from '@portinari/portinari-ui';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { MoviesComponent } from './movies/movies.component';
import { NewMovieComponent } from './movies/new/new-movie.component';
import { AuthCallbackComponent } from './security/auth-callback.component';
import { InterceptorModule } from './security/interceptor.module';

@NgModule({
  declarations: [
    AppComponent,
    AuthCallbackComponent,
    MoviesComponent,
    NewMovieComponent,
    HomeComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    PoModule,
    RouterModule.forRoot([]),
    InterceptorModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { PoModule } from '@portinari/portinari-ui';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthCallbackComponent } from './security/auth-callback.component';
import { InterceptorModule } from './security/interceptor.module';
import { MoviesComponent } from './movies/movies.component';
import { HomeComponent } from './home/home.component';
import { NewMovieComponent } from './movies/new/new-movie.component';
import { FormsModule } from '@angular/forms';

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
    InterceptorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

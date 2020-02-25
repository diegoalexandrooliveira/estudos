import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { MoviesComponent } from './movies/movies.component';
import { AuthCallbackComponent } from './security/auth-callback.component';
import { AuthGuard } from './security/auth.guard';
import { HomeComponent } from './home/home.component';
import { NewMovieComponent } from './movies/new/new-movie.component';


const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "signin-callback",
    component: AuthCallbackComponent
  },
  {
    path: "signout-callback",
    component: AuthCallbackComponent
  },
  {
    path: "movies",
    component: MoviesComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "movies/new",
    component: NewMovieComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

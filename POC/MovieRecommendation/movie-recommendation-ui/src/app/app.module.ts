import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PoModule } from '@portinari/portinari-ui';
import { RouterModule, Routes } from '@angular/router';
import { RatingComponent } from './rating/rating.component';
import { FormsModule } from '@angular/forms';

const appRoutes: Routes = [
  { path: 'ratings', component: RatingComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    RatingComponent
  ],
  imports: [
    BrowserModule,
    PoModule,
    RouterModule.forRoot(appRoutes),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }



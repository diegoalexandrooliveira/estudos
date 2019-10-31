import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PoModule } from '@portinari/portinari-ui';
import { RouterModule, Routes } from '@angular/router';
import { RateComponent } from './rate/rate.component';
import { RecommendationComponent } from './recommendation/recommendation.component';
import { FormsModule } from '@angular/forms';

const appRoutes: Routes = [
  { path: 'rate', component: RateComponent },
  { path: 'recommendation', component: RecommendationComponent },
  { path: '**', component: RateComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    RateComponent,
    RecommendationComponent
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



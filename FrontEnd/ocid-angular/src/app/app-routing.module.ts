import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Tela1Component } from './tela1/tela1.component';
import { Tela3Component } from './tela3/tela3.component';
import { Tela2Component } from './tela2/tela2.component';
import { SigninCallbackComponent } from './signin-redirect-callback.component';
import { SignoutCallbackComponent } from './signout-redirect-callback.component';


const routes: Routes = [
  {
    path: 'tela1',
    component: Tela1Component
  },
  {
    path: 'tela2',
    component: Tela2Component
  },
  {
    path: 'tela3',
    component: Tela3Component
  },
  {
    path: 'signin-callback',
    component: SigninCallbackComponent
  },
  {
    path: 'signout-callback',
    component: SignoutCallbackComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

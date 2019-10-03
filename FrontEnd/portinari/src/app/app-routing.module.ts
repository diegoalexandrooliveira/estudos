import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AppComponent} from './app.component';
import {Componente2Component} from './componente2/componente2.component'
import {Componente3Component} from './componente3/componente3.component'

const routes: Routes = [
  {path:'rota1', component: Componente2Component},
  {path: 'rota2', component: Componente3Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

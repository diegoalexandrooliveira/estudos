import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Tela1Component } from './tela1/tela1.component';
import { Tela3Component } from './tela3/tela3.component';
import { Tela2Component } from './tela2/tela2.component';


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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

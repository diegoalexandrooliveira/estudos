import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PessoasListComponent } from './pessoas-list/pessoas-list.component';

const routes: Routes = [
  {
    path: '',
    component: PessoasListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PessoaRoutingModule { }

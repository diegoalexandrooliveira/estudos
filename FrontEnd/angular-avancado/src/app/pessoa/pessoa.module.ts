import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { PessoaRoutingModule } from './pessoa-routing.module';
import { PessoasListComponent } from './pessoas-list/pessoas-list.component';



@NgModule({
  declarations: [PessoasListComponent],
  imports: [
    CommonModule,
    PessoaRoutingModule
  ]
})
export class PessoaModule { }

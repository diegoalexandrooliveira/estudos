import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PessoaRoutingModule } from './pessoa-routing.module';
import { PessoasListComponent } from './pessoas-list/pessoas-list.component';
import { TransformaIdPipe } from './transforma-id.pipe';



@NgModule({
  declarations: [PessoasListComponent, TransformaIdPipe],
  imports: [
    FormsModule,
    CommonModule,
    PessoaRoutingModule
  ]
})
export class PessoaModule { }

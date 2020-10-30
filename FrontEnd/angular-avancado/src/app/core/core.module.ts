import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarregamentoComponent } from './carregamento/carregamento.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CarregamentoInterceptor } from './carregamento/carregamento.interceptor';



@NgModule({
  declarations: [CarregamentoComponent],
  imports: [
    CommonModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CarregamentoInterceptor,
      multi: true
    }
  ],
  exports: [CarregamentoComponent]
})
export class CoreModule { }

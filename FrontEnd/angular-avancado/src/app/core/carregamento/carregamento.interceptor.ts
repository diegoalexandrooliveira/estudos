import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { CarregamentoService, Eventos } from './carregamento.service';

@Injectable()
export class CarregamentoInterceptor implements HttpInterceptor {

  constructor(private carregamentoService: CarregamentoService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.carregamentoService.emitir(Eventos.HTTP_REQUEST);
    return next.handle(req)
      .pipe(
        tap(httpEvent => {
          if (httpEvent instanceof HttpResponse) {
            this.carregamentoService.emitir(Eventos.HTTP_RESPONSE)
          }
        }),
        catchError(erro => {
          this.carregamentoService.emitir(Eventos.HTTP_RESPONSE);
          return of(erro);
        })
      );
  }
}

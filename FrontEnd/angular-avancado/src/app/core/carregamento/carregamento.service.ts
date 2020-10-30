import { Injectable } from '@angular/core';
import { Subject, Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CarregamentoService {

  private evento$: Subject<any> = new Subject<any>();

  constructor() { }

  escutar(evento: Eventos) {
    return this.evento$.pipe(
      filter((eventoEmitido: Eventos) => eventoEmitido == evento),
      map((eventoEmitido) => {
        return;
      })
    );
  }

  emitir(evento: Eventos): void {
    this.evento$.next(evento);
  }
}

export enum Eventos {
  HTTP_REQUEST,
  HTTP_RESPONSE
}

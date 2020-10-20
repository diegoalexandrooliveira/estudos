import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Heroi } from './heroi';

@Injectable({ providedIn: 'root' })
export class HeroiService {
  constructor(private httpClient: HttpClient) { }

  getHerois(): Observable<Heroi[]> {
    return of([]);
  }

  adicionaHeroi(heroi: Heroi) {
    return of({ ...heroi, id: Math.floor(Math.random() * 1000) });
  }

  excluirHeroi(heroi: Heroi): Observable<void> {
    return of();
  }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Heroi } from './heroi';

@Injectable({ providedIn: 'root' })
export class HeroiService {
  constructor(private httpClient: HttpClient) { }

  getHerois(): Observable<Heroi[]> {
    return of([{
      id: 1,
      forca: 8,
      nome: 'Spider-Man'
    },
    {
      id: 2,
      forca: 24,
      nome: 'Wonder Woman'
    },
    {
      id: 3,
      forca: 55,
      nome: 'Super-man'
    }]);
  }

  adicionaHeroi(heroi: Heroi) {
    return of({ ...heroi, id: Math.floor(Math.random() * 1000) });
  }

  excluirHeroi(heroi: Heroi): Observable<void> {
    return of();
  }

}

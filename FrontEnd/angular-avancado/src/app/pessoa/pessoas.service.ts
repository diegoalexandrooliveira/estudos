import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pessoa } from './pessoa.model';

@Injectable({ providedIn: 'root' })
export class PessoasService {
  constructor(private httpClient: HttpClient) { }

  get(): Observable<Pessoa[]> {
    return this.httpClient.get<Pessoa[]>('api/pessoas');
  }

}

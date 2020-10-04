import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categoria } from './categoria';
import { Produto } from './produto';

@Injectable({ providedIn: 'root' })
export class AppService {
  constructor(private httpClient: HttpClient) { }

  public getProdutos(): Observable<Produto[]> {
    return this.httpClient.get<Produto[]>('/api/produtos');
  }

  public getCategoriaPorId(id: string): Observable<Categoria> {
    return this.httpClient.get<Categoria>(`/api/categorias/${id}`);
  }

}

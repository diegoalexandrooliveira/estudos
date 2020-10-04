import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Categoria } from './categoria';
import { Produto } from './produto';


export class BackEnd implements InMemoryDbService {

  createDb() {
    const produtos: Produto[] = [
      {
        id: '1',
        descricao: 'Produto 1',
        categoriaId: '1'
      },
      {
        id: '2',
        descricao: 'Produto 2',
        categoriaId: '1'
      },
      {
        id: '3',
        descricao: 'Produto 3',
        categoriaId: '2'
      }
    ];

    const categorias: Categoria[] = [
      {
        id: '1',
        descricao: 'Categoria 1'
      },
      {
        id: '2',
        descricao: 'Categoria 2'
      }
    ];
    return { produtos, categorias };
  }

}

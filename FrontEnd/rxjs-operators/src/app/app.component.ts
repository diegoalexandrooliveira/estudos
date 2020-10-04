import { Component } from '@angular/core';
import { from, Observable } from 'rxjs';
import { mergeMap, toArray } from 'rxjs/operators';
import { AppService } from './app.service';
import { Categoria } from './categoria';
import { Produto } from './produto';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'rxjs-operators';

  produtos$: Observable<Produto[]>;

  categorias$: Observable<Categoria[]>;

  constructor(private service: AppService) {

    this.produtos$ = service.getProdutos();

    this.categorias$ = this.produtos$.pipe(
      mergeMap(produtos => from(produtos)),
      mergeMap(produto => this.service.getCategoriaPorId(produto.categoriaId)),
      toArray()
    );


  }
}

import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Product } from '../product';
import * as ProductActions from '../state/product.actions';
import { getCurrentProduct, getError, getProducts, showProductCodeSelector, State } from '../state/product.reducer';

@Component({
  templateUrl: './product-shell.component.html'
})
export class ProductShellComponent implements OnInit {

  products$: Observable<Product[]>;
  currentProduct$: Observable<Product>;
  displayProductCode$: Observable<boolean>;
  getError$: Observable<string>;

  constructor(private store: Store<State>) { }

  ngOnInit(): void {
    this.store.dispatch(ProductActions.loadProducts());
    this.currentProduct$ = this.store.select(getCurrentProduct);
    this.products$ = this.store.select(getProducts);
    this.displayProductCode$ = this.store.select(showProductCodeSelector);
    this.getError$ = this.store.select(getError);
  }

  checkChanged(): void {
    this.store.dispatch(ProductActions.toogleProductCode());
  }

  newProduct(): void {
    this.store.dispatch(ProductActions.initializeCurrentProduct());
  }

  productSelected(product: Product): void {
    this.store.dispatch(ProductActions.setCurrentProduct({ productId: product.id }));
  }
}

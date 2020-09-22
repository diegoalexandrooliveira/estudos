import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { State } from 'src/app/state/app.state';
import { Product } from '../product';
import { getCurrentProduct, getError, getProducts, showProductCodeSelector } from '../state';
import {ProductPageActions} from '../state/actions';

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
    this.store.dispatch(ProductPageActions.loadProducts());
    this.currentProduct$ = this.store.select(getCurrentProduct);
    this.products$ = this.store.select(getProducts);
    this.displayProductCode$ = this.store.select(showProductCodeSelector);
    this.getError$ = this.store.select(getError);
  }

  checkChanged(): void {
    this.store.dispatch(ProductPageActions.toogleProductCode());
  }

  newProduct(): void {
    this.store.dispatch(ProductPageActions.initializeCurrentProduct());
  }

  productSelected(product: Product): void {
    this.store.dispatch(ProductPageActions.setCurrentProduct({ productId: product.id }));
  }
}

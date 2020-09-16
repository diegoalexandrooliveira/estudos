import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Product } from '../product';
import * as ProductActions from '../state/product.actions';
import { getCurrentProduct, getError, getProducts, showProductCodeSelector, State } from '../state/product.reducer';



@Component({
  selector: 'pm-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  pageTitle = 'Products';

  products$: Observable<Product[]>;
  currentProduct$: Observable<Product>;
  displayProductCode$: Observable<boolean>;
  getError$: Observable<string>;

  constructor(private store: Store<State>) { }

  ngOnInit(): void {
    this.currentProduct$ = this.store.select(getCurrentProduct);

    this.products$ = this.store.select(getProducts);

    this.store.dispatch(ProductActions.loadProducts());

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

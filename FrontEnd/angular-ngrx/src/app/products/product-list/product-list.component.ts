import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable, Subscription } from 'rxjs';
import { Product } from '../product';
import { ProductService } from '../product.service';
import * as ProductActions from '../state/product.actions';
import { showProductCodeSelector, State, getCurrentProduct, getProducts } from '../state/product.reducer';



@Component({
  selector: 'pm-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  pageTitle = 'Products';
  errorMessage: string;

  products$: Observable<Product[]>;
  currentProduct$: Observable<Product>;
  displayProductCode$: Observable<boolean>;

  constructor(private productService: ProductService, private store: Store<State>) { }

  ngOnInit(): void {
    this.currentProduct$ = this.store.select(getCurrentProduct);

    this.products$ = this.store.select(getProducts);

    this.store.dispatch(ProductActions.loadProducts());

    this.displayProductCode$ = this.store.select(showProductCodeSelector);
  }

  checkChanged(): void {
    this.store.dispatch(ProductActions.toogleProductCode());
  }

  newProduct(): void {
    this.store.dispatch(ProductActions.initializeCurrentProduct());
  }

  productSelected(product: Product): void {
    this.store.dispatch(ProductActions.setCurrentProduct({ product }));
  }

}

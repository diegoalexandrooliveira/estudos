import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, concatMap, map, mergeMap } from 'rxjs/operators';
import { ProductService } from '../product.service';
import {ProductAPIActions, ProductPageActions} from './actions';

@Injectable({ providedIn: 'root' })
export class ProductEffectsService {
  constructor(private actions$: Actions, private productService: ProductService) { }

  loadProducts$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(ProductPageActions.loadProducts),
      mergeMap(() => this.productService.getProducts().pipe(
        map(products => ProductAPIActions.loadProductsSuccess({ products })),
        catchError(error => of(ProductAPIActions.loadProductsFailure({ error })))
      ))
    );
  });

  updateProduct$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(ProductPageActions.updateProduct),
      concatMap((action) => this.productService.updateProduct(action.product).pipe(
        map(product => ProductAPIActions.updateProductSuccess({ product })),
        catchError(error => of(ProductAPIActions.updateProductFailure({ error })))
      ))
    );
  });

  createProduct$ = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(ProductPageActions.createProduct),
        concatMap((action) => this.productService.createProduct(action.product).pipe(
          map(product => ProductAPIActions.createProductSuccess({ product })),
          catchError(error => of(ProductAPIActions.createProductFailure({ error })))
        ))
      )
    }
  );

  deleteProduct$ = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(ProductPageActions.deleteProduct),
        concatMap((action) => this.productService.deleteProduct(action.productId).pipe(
          map(() => ProductAPIActions.deleteProductSuccess({ productId: action.productId })),
          catchError(error => of(ProductAPIActions.deleteProductFailure({ error })))
        ))
      )
    }
  );

}

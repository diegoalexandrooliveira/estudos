import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as AppState from '../../state/app.state';
import { ProductState } from './product.reducer';


export interface State extends AppState.State {
  products: ProductState;
}

const featureSelector = createFeatureSelector<ProductState>('products');

export const showProductCodeSelector = createSelector(
  featureSelector,
  products => products.showProductCode
);

export const getCurrentProductId = createSelector(
  featureSelector,
  state => state.currentProductId
);

export const getCurrentProduct = createSelector(
  featureSelector,
  getCurrentProductId,
  (state, currentProductId) => {
    if (currentProductId == 0) {
      return {
        id: 0,
        productName: '',
        productCode: 'New',
        description: '',
        starRating: 0
      };
    } else {
      return currentProductId ? state.products.find(product => product.id === currentProductId) : null;
    }
  }
);

export const getProducts = createSelector(
  featureSelector,
  productsState => productsState.products
);

export const getError = createSelector(
  featureSelector,
  productState => productState.error
);

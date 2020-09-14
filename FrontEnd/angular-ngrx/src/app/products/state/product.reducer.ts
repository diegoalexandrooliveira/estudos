import { createFeatureSelector, createReducer, createSelector, on } from '@ngrx/store';
import * as AppState from '../../state/app.state';
import { Product } from '../product';
import * as Actions from './product.actions';


export interface State extends AppState.State {
  products: ProductState;
}

export interface ProductState {
  showProductCode: boolean;
  currentProduct: Product;
  products: Product[];
}

const initialState: ProductState = {
  currentProduct: null,
  products: [],
  showProductCode: true
}

const featureSelector = createFeatureSelector<ProductState>('products');

export const showProductCodeSelector = createSelector(
  featureSelector,
  products => products.showProductCode
);

export const getCurrentProduct = createSelector(
  featureSelector,
  productsState => productsState.currentProduct
);

export const getProducts = createSelector(
  featureSelector,
  productsState => productsState.products
);


export const productReducer = createReducer<ProductState>(
  initialState,
  on(Actions.toogleProductCode, (state): ProductState => {
    return {
      ...state,
      showProductCode: !state.showProductCode
    }
  }),
  on(Actions.setCurrentProduct, (state, action): ProductState => {
    return {
      ...state,
      currentProduct: action.product
    }
  }),
  on(Actions.initializeCurrentProduct, (state): ProductState => {
    return {
      ...state,
      currentProduct: {
        description: '',
        id: 0,
        productCode: '',
        productName: '',
        starRating: 1
      }
    }
  }),
  on(Actions.loadProductsSuccess, (state, action): ProductState => {
    return {
      ...state,
      products: action.products
    };
  })
);

import { createFeatureSelector, createReducer, createSelector, on } from '@ngrx/store';
import * as AppState from '../../state/app.state';
import { Product } from '../product';
import * as Actions from './product.actions';


export interface State extends AppState.State {
  products: ProductState;
}

export interface ProductState {
  showProductCode: boolean;
  currentProductId: number | null;
  products: Product[];
  error: string;
}

const initialState: ProductState = {
  currentProductId: null,
  products: [],
  showProductCode: true,
  error: ''
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

// export const getCurrentProduct = createSelector(
//   featureSelector,
//   productsState => productsState.currentProduct
// );

export const getProducts = createSelector(
  featureSelector,
  productsState => productsState.products
);

export const getError = createSelector(
  featureSelector,
  productState => productState.error
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
      currentProductId: action.productId
    }
  }),
  on(Actions.initializeCurrentProduct, (state): ProductState => {
    return {
      ...state,
      currentProductId: 0
    }
  }),
  on(Actions.loadProductsSuccess, (state, action): ProductState => {
    return {
      ...state,
      products: action.products,
      error: ''
    };
  }),
  on(Actions.loadProductsFailure, (state, action): ProductState => {
    return {
      ...state,
      products: [],
      error: action.error
    };
  }),
  on(Actions.updateProductSuccess, (state, action): ProductState => {
    const updatedProducts = state.products.map(product => product.id === action.product.id ? action.product : product);
    return {
      ...state,
      products: updatedProducts,
      error: ''
    };
  }),
  on(Actions.updateProductFailure, (state, action): ProductState => {
    return {
      ...state,
      error: action.error
    };
  }),
  on(Actions.createProductSuccess, (state, action): ProductState => {
    const newProductList = [...state.products, action.product];
    return {
      ...state,
      products: newProductList,
      currentProductId: action.product.id,
      error: ''
    }
  }),
  on(Actions.createProductFailure, (state, action): ProductState => {
    return {
      ...state,
      error: action.error
    }
  }),
  on(Actions.deleteProductSuccess, (state, action): ProductState => {
    const newProductList = state.products.filter(p => p.id !== action.productId);
    return {
      ...state,
      products: newProductList,
      currentProductId: null,
      error: ''
    }
  }),
  on(Actions.deleteProductFailure, (state, action): ProductState => {
    return {
      ...state,
      error: action.error
    }
  })
);

import { createReducer, on } from '@ngrx/store';
import { Product } from '../product';
import { ProductAPIActions, ProductPageActions } from './actions';



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


export const productReducer = createReducer<ProductState>(
  initialState,
  on(ProductPageActions.toogleProductCode, (state): ProductState => {
    return {
      ...state,
      showProductCode: !state.showProductCode
    }
  }),
  on(ProductPageActions.setCurrentProduct, (state, action): ProductState => {
    return {
      ...state,
      currentProductId: action.productId
    }
  }),
  on(ProductPageActions.initializeCurrentProduct, (state): ProductState => {
    return {
      ...state,
      currentProductId: 0
    }
  }),
  on(ProductAPIActions.loadProductsSuccess, (state, action): ProductState => {
    return {
      ...state,
      products: action.products,
      error: ''
    };
  }),
  on(ProductAPIActions.loadProductsFailure, (state, action): ProductState => {
    return {
      ...state,
      products: [],
      error: action.error
    };
  }),
  on(ProductAPIActions.updateProductSuccess, (state, action): ProductState => {
    const updatedProducts = state.products.map(product => product.id === action.product.id ? action.product : product);
    return {
      ...state,
      products: updatedProducts,
      error: ''
    };
  }),
  on(ProductAPIActions.updateProductFailure, (state, action): ProductState => {
    return {
      ...state,
      error: action.error
    };
  }),
  on(ProductAPIActions.createProductSuccess, (state, action): ProductState => {
    const newProductList = [...state.products, action.product];
    return {
      ...state,
      products: newProductList,
      currentProductId: action.product.id,
      error: ''
    }
  }),
  on(ProductAPIActions.createProductFailure, (state, action): ProductState => {
    return {
      ...state,
      error: action.error
    }
  }),
  on(ProductAPIActions.deleteProductSuccess, (state, action): ProductState => {
    const newProductList = state.products.filter(p => p.id !== action.productId);
    return {
      ...state,
      products: newProductList,
      currentProductId: null,
      error: ''
    }
  }),
  on(ProductAPIActions.deleteProductFailure, (state, action): ProductState => {
    return {
      ...state,
      error: action.error
    }
  })
);

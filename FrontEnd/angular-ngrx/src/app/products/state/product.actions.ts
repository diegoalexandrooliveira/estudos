import { createAction, props } from '@ngrx/store';
import { Product } from '../product';


export const toogleProductCode = createAction('[Product] Toggle product code');

export const setCurrentProduct = createAction('[Product] Set current product',
  props<{ productId: number }>()
);

export const clearCurrentProduct = createAction('[Product] Clear current product');

export const initializeCurrentProduct = createAction('[Product] Initialize current product');

export const loadProducts = createAction('[Product] Load');

export const loadProductsSuccess = createAction(
  '[Product] Load success',
  props<{ products: Product[] }>()
);

export const loadProductsFailure = createAction('[Product] Load failure',
  props<{ error: string }>());

export const updateProduct = createAction('[Product] Update',
  props<{ product: Product }>()
);

export const updateProductSuccess = createAction('[Product] Update Success',
  props<{ product: Product }>()
);

export const updateProductFailure = createAction('[Product] Update failure',
  props<{ error: string }>());

export const createProduct = createAction('[Product] Create',
  props<{ product: Product }>()
);

export const createProductSuccess = createAction('[Product] Create Success',
  props<{ product: Product }>()
);

export const createProductFailure = createAction('[Product] Create failure',
  props<{ error: string }>());

export const deleteProduct = createAction('[Product] Delete',
  props<{ productId: number }>()
);

export const deleteProductSuccess = createAction('[Product] Delete Success',
  props<{ productId: number }>()
);

export const deleteProductFailure = createAction('[Product] Delete failure',
  props<{ error: string }>());

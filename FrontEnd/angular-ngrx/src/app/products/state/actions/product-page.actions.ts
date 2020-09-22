import { createAction, props } from '@ngrx/store';
import { Product } from '../../product';


export const toogleProductCode = createAction('[Product Page] Toggle product code');

export const setCurrentProduct = createAction('[Product Page] Set current product',
  props<{ productId: number }>()
);

export const clearCurrentProduct = createAction('[Product Page] Clear current product');

export const initializeCurrentProduct = createAction('[Product Page] Initialize current product');

export const loadProducts = createAction('[Product Page] Load');

export const updateProduct = createAction('[Product Page] Update',
  props<{ product: Product }>()
);

export const createProduct = createAction('[Product Page] Create',
  props<{ product: Product }>()
);

export const deleteProduct = createAction('[Product Page] Delete',
  props<{ productId: number }>()
);

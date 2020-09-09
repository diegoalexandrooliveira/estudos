import { createAction, props } from '@ngrx/store';
import { Product } from '../product';


export const toogleProductCode = createAction('[Product] Toggle product code');

export const setCurrentProduct = createAction('[Product] Set current product',
  props<{ product: Product }>()
);

export const clearCurrentProduct = createAction('[Product] Clear current product');

export const initializeCurrentProduct = createAction('[Product] Initialize current product');

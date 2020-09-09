import { createAction, createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as AppState from '../../state/app.state';

export interface State extends AppState.State {
  user: UserState;
}

export interface UserState {
  maskUserName: boolean;
}

const initialState: UserState = {
  maskUserName: false
};

const featureSelector = createFeatureSelector<UserState>('user');

export const getMaskUserName = createSelector(
  featureSelector,
  state => state.maskUserName
);


export const userReducer = createReducer<UserState>(
  initialState,
  on(createAction('[User] Mask UserName'), (state): UserState => {
    return {
      ...state,
      maskUserName: !state.maskUserName
    };
  })
);

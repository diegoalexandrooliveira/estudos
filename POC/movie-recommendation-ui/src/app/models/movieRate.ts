import { IMovieRate } from './IMovieRate';

export class MovieRate implements IMovieRate {
    constructor(public id: Number, public movieImdbId: String, public userId: Number,
        public rate: Number) {
    }
}
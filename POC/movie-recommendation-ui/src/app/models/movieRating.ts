import { IMovieRating } from './IMovieRating';

export class MovieRating implements IMovieRating {
    constructor(public id: Number, public movieImdbId: String, public userId: Number,
        public rating: Number) {
    }
}
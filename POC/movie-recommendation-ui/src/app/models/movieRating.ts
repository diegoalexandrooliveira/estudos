import { IMovieRating } from './IMovieRating';

export class MovieRating implements IMovieRating {
    constructor(public id: Number, public imdb_id: String, public user_id: Number,
        public rating: Number) {
    }
}
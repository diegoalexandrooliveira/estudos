export class Movie {

    constructor(public imdbId: String, public title: String, public year: Number,
        public urlPoster: String, public metascore: Number, public genre: String,
        public stars: Object[]) {
    }

}
export class Movie {
    constructor(private imdbId: String, private title: String, private year: Number,
        private urlPoster: String, private metascore: Number, private genre: String, private stars: Object[]) {
    }


    public getImdbId(): String {
        return this.imdbId;
    }
    public getTitle(): String {
        return this.title;
    }
    public getYear(): Number {
        return this.year;
    }
    public getUrlPoster(): String {
        return this.urlPoster;
    }
    public getMetascore(): Number {
        return this.metascore;
    }
    public getGenre(): String {
        return this.genre;
    }

    public getStars(): Object[] {
        return this.stars;
    }
    public setStars(stars: Object[]) {
        this.stars = stars;
    }
}
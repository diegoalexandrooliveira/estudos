export class MovieRate {
    constructor(private id: Number,private movieImdbId: String, private userId: Number, private rate: Number) {
    }

    public getMovieImdbId(): String {
        return this.movieImdbId;
    }
    
    public getUserId(): Number {
        return this.userId;
    }
    
    public getRate(): Number {
        return this.rate;
    }
}
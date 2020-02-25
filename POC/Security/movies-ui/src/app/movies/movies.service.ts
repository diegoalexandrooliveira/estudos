import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class MoviesService {

    private apiUrl: string = 'http://localhost:8080/api/movies';

    constructor(private httpClient: HttpClient) { }

    getMovies() {
        return this.httpClient.get(this.apiUrl);
    }

    save(movie) {
        return this.httpClient.post(this.apiUrl, movie);
    }

}
import { Component, OnInit } from '@angular/core';
import { Movie } from '../models/movie';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IUser } from '../models/IUser';
import { IMovieRating } from '../models/IMovieRating';
import { MovieRating } from '../models/movieRating';

@Component({
  selector: 'app-rate',
  templateUrl: './rate.component.html',
  styleUrls: ['./rate.component.css']
})
export class RateComponent implements OnInit {

  public usersSelect: Object[] = [];

  public user;

  public movies: Movie[] = [];

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.get<IUser[]>("http://localhost:8080/users")
      .subscribe((users: IUser[]) => {
        this.usersSelect = users.map(user => new Object({ value: user.id, label: user.name }));
      });


  }

  onChangeUser() {
    let promises: Promise<Object>[] = [];
    promises.push(this.httpClient.get("http://localhost:8080/movies").toPromise());
    promises.push(this.httpClient.get<IMovieRating[]>("http://localhost:8080/ratings/" + this.user).toPromise<IMovieRating[]>());

    Promise.all(promises).then(resolvedPromises => {
      let ratedMovies = <MovieRating[]>resolvedPromises[1];

      let moviesResponse = <any[]>resolvedPromises[0];

      this.movies = moviesResponse.map(movie => {
        let rate = [{ id: 1, rated: false }, { id: 2, rated: false }, { id: 3, rated: false },
        { id: 4, rated: false }, { id: 5, rated: false }];

        let newMovie = new Movie(movie["imdbID"],
          movie["Title"],
          movie["Year"],
          movie["Poster"],
          movie["Metascore"],
          movie["Genre"], null);

        let movieRate: MovieRating = ratedMovies.find(ratedMovie => newMovie.imdbId == ratedMovie.movieImdbId);
        if (movieRate) {
          rate = rate.map(value => {
            return { id: value["id"], rated: value["id"] <= movieRate.rating }
          });
        }
        newMovie.stars = rate;
        return newMovie;
      });
    });
  }

  onRate(star, movie: Movie) {
    movie.stars = movie.stars.map(starElement => {
      if (!star.rated) {
        if (star.id >= starElement["id"]) {
          starElement["rated"] = true;
        } else {
          starElement["rated"] = false;
        }
      } else {
        if (star.id < starElement["id"]) {
          starElement["rated"] = false;
        }
      }
      return starElement;
    });
    let movieRate = new MovieRating(undefined, movie.imdbId, this.user, star["id"]);
    let headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    this.httpClient.post("http://localhost:8080/ratings", JSON.stringify(movieRate), {
      headers: headers
    }).subscribe();
  }

  deleteAllRates() {
    this.httpClient.delete("http://localhost:8080/ratings/" + this.user).subscribe(() => this.onChangeUser());

  }

}

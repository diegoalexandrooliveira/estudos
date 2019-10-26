import { Component, OnInit } from '@angular/core';
import { Movie } from '../models/movie';
import { User } from '../models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MovieRate } from '../models/movieRate';
import { IUser } from '../models/IUser';
import { IMovieRate } from '../models/IMovieRate';

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
    promises.push(this.httpClient.get<IMovieRate[]>("http://localhost:8080/rates/" + this.user).toPromise<IMovieRate[]>());

    Promise.all(promises).then(resolvedPromises => {
      let ratedMovies = <MovieRate[]>resolvedPromises[1];

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

        let movieRate: MovieRate = ratedMovies.find(ratedMovie => newMovie.imdbId == ratedMovie.movieImdbId);
        if (movieRate) {
          rate = rate.map(value => {
            return { id: value["id"], rated: value["id"] <= movieRate.rate }
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
    let movieRate = new MovieRate(undefined, movie.imdbId, this.user, star["id"]);
    let headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    this.httpClient.post("http://localhost:8080/rates", JSON.stringify(movieRate), {
      headers: headers
    }).subscribe();
  }

  deleteAllRates() {
    this.httpClient.delete("http://localhost:8080/rates/" + this.user).subscribe(() => this.onChangeUser());

  }

}

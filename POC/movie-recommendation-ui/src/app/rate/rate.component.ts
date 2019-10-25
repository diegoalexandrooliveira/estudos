import { Component, OnInit } from '@angular/core';
import { Movie } from '../models/movie';
import { User } from '../models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MovieRate } from '../models/movieRate';

@Component({
  selector: 'app-rate',
  templateUrl: './rate.component.html',
  styleUrls: ['./rate.component.css']
})
export class RateComponent implements OnInit {

  private users: Array<User> = new Array();

  public usersSelect: Object[] = [];

  public user;

  public movies: Movie[] = [];

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.get("http://localhost:8080/users")
      .subscribe((request: Array<Object>) => {
        this.users = request.map(user => new User(user["id"], user["name"]));
        this.usersSelect = this.users.map(user => new Object({ value: user.getId(), label: user.getName() }));
      });


  }

  onChangeUser() {
    let promises: Promise<Object>[] = [];
    promises.push(this.httpClient.get("http://localhost:8080/movies").toPromise());
    promises.push(this.httpClient.get("http://localhost:8080/rates/" + this.user).toPromise());

    Promise.all(promises).then(resolvedPromises => {
      let moviesRateResponse: any = resolvedPromises[1];

      let ratedMovies: MovieRate[] = moviesRateResponse.map(movieRate =>
        new MovieRate(
          movieRate["id"],
          movieRate["movieImdbId"],
          movieRate["userId"],
          movieRate["rate"]
        ));

      let moviesResponse: any = resolvedPromises[0];
      this.movies = moviesResponse.map(movie => {
        let rate = [{ id: 1, rated: false }, { id: 2, rated: false }, { id: 3, rated: false },
        { id: 4, rated: false }, { id: 5, rated: false }];

        let newMovie = new Movie(movie["imdbID"],
          movie["Title"],
          movie["Year"],
          movie["Poster"],
          movie["Metascore"],
          movie["Genre"], null);

        let movieRate: MovieRate = ratedMovies.find(ratedMovie => newMovie.getImdbId() == ratedMovie.getMovieImdbId());
        if (movieRate) {
          rate = rate.map(value => {
            return { id: value["id"], rated: value["id"] <= movieRate.getRate() }
          });
        }
        newMovie.setStars(rate);
        return newMovie;
      });
    });
  }

  onRate(star, movie: Movie) {
    movie.setStars(movie.getStars().map(starElement => {
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
    }));
    let movieRate = new MovieRate(undefined, movie.getImdbId(), this.user, star["id"]);
    let headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    this.httpClient.post("http://localhost:8080/rates", JSON.stringify(movieRate), {
      headers: headers
    }).subscribe();
  }

  deleteAllRates() {
    this.httpClient.delete("http://localhost:8080/rates/" + this.user).subscribe(() => this.onChangeUser());

  }

}

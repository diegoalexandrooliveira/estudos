import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { MovieRating } from '../models/movieRating';
import { Movie } from '../models/movie';
import { IUser } from '../models/IUser';
import { IMovieRating } from '../models/IMovieRating';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent implements OnInit {

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
    this.httpClient.get("http://localhost:8080/recommendations/" + this.user)
      .subscribe((movies: Object[]) => {
        this.movies = movies.map(movie => new Movie(movie["imdbID"], movie["Title"],
          movie["Year"], movie["Poster"], movie["Metascore"], movie["Genre"], undefined));
      });
  }

}

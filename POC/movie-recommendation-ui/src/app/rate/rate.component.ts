import { Component, OnInit } from '@angular/core';
import { Movie } from '../models/movie';
import { User } from '../models/user';
import { HttpClient } from '@angular/common/http';

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
        this.usersSelect = this.users.map(user => new Object({value: user.getId(), label: user.getName()}));
      });

      this.httpClient.get("http://localhost:8080/movies")
      .subscribe((request: Array<Object>) => {
        this.movies = request.map(movie => 
          new Movie(movie["imdbID"],
          movie["Title"],
          movie["Year"],
          movie["Poster"],
          movie["Metascore"],
          movie["Genre"]));
      });
  }

  onChangeUser(){
    console.log(this.user);
    
  }

}

import { Component, OnInit } from '@angular/core';
import { PoPageAction } from '@portinari/portinari-ui';

import { MoviesService } from './movies.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {


  items: any[] = [];

  actions: Array<PoPageAction> = [
    {
      label: 'New', url: '/movies/new'
    }
  ];

  constructor(private _moviesService: MoviesService) { }

  ngOnInit() {
    this._moviesService.getMovies()
      .subscribe((movies: any[]) => this.items = movies);
  }

}
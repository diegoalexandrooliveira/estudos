import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PoNotificationService } from '@portinari/portinari-ui';

import { MoviesService } from '../movies.service';


@Component({
  selector: 'app-new-movie',
  templateUrl: './new-movie.component.html',
  styleUrls: ['./new-movie.component.css']
})
export class NewMovieComponent implements OnInit {

  movie: any = {};

  constructor(private _moviesService: MoviesService, private _router: Router, private _notification: PoNotificationService) { }

  ngOnInit() {
  }

  save() {
    this._moviesService.save(this.movie)
      .subscribe(movie => {
        this._notification.success('Movie saved with success.');
        this._router.navigateByUrl('/movies');
      });
  }

  cancel() {
    this._router.navigateByUrl('/movies');
  }

}

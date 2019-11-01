import { Component } from '@angular/core';

import { PoMenuItem } from '@portinari/portinari-ui';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public pageName: String = "Rating";

  constructor(private router: Router) {

  }

  readonly menus: Array<PoMenuItem> = [
    { label: 'Rating', action: this.rate.bind(this) },

    { label: 'Recommendation', action: this.recommendation.bind(this) }
  ];

  private rate() {
    this.pageName = "Rating";
    this.router.navigateByUrl("/rating");
  }

  private recommendation() {
    this.pageName = "Recommendation";
    this.router.navigateByUrl("/recommendation");
  }

}

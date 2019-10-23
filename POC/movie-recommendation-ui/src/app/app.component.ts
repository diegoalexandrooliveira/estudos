import { Component } from '@angular/core';

import { PoMenuItem } from '@portinari/portinari-ui';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public pageName: String = "Rate";;

  constructor(private router: Router) {

  }

  readonly menus: Array<PoMenuItem> = [
    { label: 'Rate', action: this.rate.bind(this) },

    { label: 'Recommendation', action: this.recommendation.bind(this) }
  ];

  private rate() {
    this.pageName = "Rate";
    this.router.navigateByUrl("/rate");
  }

  private recommendation() {
    this.pageName = "Recommendation";
    this.router.navigateByUrl("/recommendation");
  }

}

import { Component } from '@angular/core';

import { PoMenuItem } from '@portinari/portinari-ui';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  readonly menus: Array<PoMenuItem> = [
   { label: 'Rota1', link: '/rota1', icon: 'po-icon-user', shortLabel: 'Rota1' },
   { label: 'Rota2', link: '/rota2', icon: 'po-icon-user', shortLabel: 'Rota2' }
  ];

  private onClick() {
    alert('Clicked in menu item')
  }

}

import { Component } from '@angular/core';
import { PoMenuItem, PoToolbarAction, PoToolbarProfile } from '@portinari/portinari-ui';

import { AuthService } from './security/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  readonly menus: Array<PoMenuItem> = [
    { label: 'Home', link: "/" },
    { label: 'Movies', link: '/movies' }
  ];

  profile: PoToolbarProfile = { title: '' };

  profileActions: Array<PoToolbarAction> = [
    { icon: 'po-icon-exit', label: 'Exit', type: 'danger', separator: true, action: () => this.logout() }
  ];

  constructor(private _authService: AuthService) {
    this._authService.getUser()
      .then(user => {
        if (user) {
          this.profile = {
            title: user.profile.preferred_username
          };
        }
      });
  }

  private logout() {
    this._authService.logout();
  }
}

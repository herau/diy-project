import { Component } from '@angular/core';
import { UserService } from '../../services/user';

@Component({
  selector: 'user-list',
  providers: [UserService],
  styles: [
    require('./users.css')
  ],
  template:`
    <md-grid-list cols="4" gutterSize="11px" rowHeight="500">
     <md-grid-tile *ngFor="#user of users">
      <md-card>
         <img md-card-image src="/images/default_image.png">
         <md-card-title>{{ user.firstname }} {{user.lastname}}</md-card-title>
         <md-card-subtitle>{{ user.personalNumber }}</md-card-subtitle>
         <md-card-content>
            <p>{{ user.email }}</p>
         </md-card-content>
         <md-card-actions>
          <button md-button>VIEW</button>
         </md-card-actions>
      </md-card>
     </md-grid-tile>
  `
})

export class UserList {

    users: Array<Object> = [];

    fetching: Boolean = false;

    constructor(ts: UserService) {
        this.fetching = true;
        ts.all().then(users => {
            this.fetching = false;
            this.users.push.apply(this.users, users);
        })
    }

}

/// <reference path="../../../typings/_custom.d.ts" />

import {Component, View, NgFor, NgIf} from 'angular2/angular2';
import {UserService} from '../../services/user';

@Component({
  selector: 'user-list',
  bindings: [UserService]
})

@View({
  template: `
  <div *ng-if="fetching" class="mdl-spinner mdl-js-spinner is-active"></div>
  <div class="mdl-grid">
    <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col" *ng-for="#user of users">
      <div class="mdl-card__title mdl-card--expand">
        <h2 class="mdl-card__title-text">{{ user.firstname }} {{ user.lastname }}</h2>
        <h3 class="mdl-card__subtitle-text">{{ user.personalNumber }}</h3>
      </div>
      <div class="mdl-card__supporting-text">
        {{ user.email }}
      </div>
      <div class="mdl-card__actions mdl-card--border">
        <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
          View
        </a>
      </div>
    </div>
  </div>
  `,
  directives: [NgFor, NgIf]
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

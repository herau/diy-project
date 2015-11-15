/// <reference path="../../../typings/_custom.d.ts" />

import {Component, View, NgFor, NgIf} from 'angular2/angular2';
import {UserService} from '../../services/user';

@Component({
  selector: 'user-list',
  providers: [UserService]
})

@View({
  template: `
  <div class="ui grid">
      <div class="four wide column" *ng-for="#user of users">
          <div class="ui card">
              <div class="image">
                  <img src="/images/avatar2/large/kristy.png">
              </div>
              <div class="content">
                  <a class="header">{{ user.firstname }} {{user.lastname}}</a>
                  <div class="meta">
                      <span class="date">{{ user.personalNumber }}</span>
                  </div>
                  <div class="description">
                    {{ user.email }}
                  </div>
              </div>
              <div class="extra content">
                  <a>
                      <i class="user icon"></i>
                      22 Tools
                  </a>
              </div>
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

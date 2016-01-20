import {Component, View} from 'angular2/core';
import {NgFor, NgIf} from 'angular2/common';
import {UserService} from '../../services/user';

@Component({
  selector: 'user-list',
  providers: [UserService]
})

@View({
  template: `
  <div class="ui centered grid">
      <div class="ui fifteen wide mobile five wide tablet three wide computer column" *ngFor="#user of users">
          <div class="ui card" style="width: 100%;">
              <div class="blurring dimmable image">
                  <div class="ui dimmer transition hidden">
                      <div class="content">
                          <div class="center">
                              <div class="ui inverted button">View</div>
                          </div>
                      </div>
                  </div>
                  <img src="/images/default_image.png" />
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
                      <i class="euro icon"></i>
                      + 15
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

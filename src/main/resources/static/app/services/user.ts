/// <reference path="../../typings/_custom.d.ts" />

import {Http, HTTP_BINDINGS} from 'angular2/http';
import { Injectable } from 'angular2/angular2';

@Injectable()
export class UserService {

    url: String = 'api/users';

    http: Object;

    users: Array<Object>;

    user: Object;

    constructor(http: Http) {
        this.http = http;
    }

    all() {
        let self = this;

        return new Promise((resolve, reject) => {
            self.http.get(self.url)
            // Get the RxJS Subject.
            .toRx()
            // Call map on the response observable to get the parsed people object.
            .map(res => res.json())
            // Subscribe to the observable to get the parsed people object and attach it to the component.
            .subscribe(users => {
                self.users = users;
                resolve(self.users);
            })
        });
    }

    current() {
    }
}

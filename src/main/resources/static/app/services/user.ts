/// <reference path="../../typings/_custom.d.ts" />

import {Http, HTTP_BINDINGS} from 'angular2/http';
import { Injectable } from 'angular2/angular2';

@Injectable()
export class UserService {

    url: String = 'api/users';

    http: Object;

    users: Array<Object>;

    constructor(http: Http) {
        this.http = http;
    }

    getAll() {

        let self = this;

        return new Promise((resolve, reject) => {
            self.http.get(self.url)
            // Get the RxJS Subject.
            .toRx()
            // Call map on the response observable to get the parsed people object.
            .map(res => res.json())
            // Subscribe to the observable to get the parsed people object and attach it to the component.
            .subscribe(tools => {
                //self.tools.push.apply(self.tools, tools._embedded.tools);
                self.tools = tools._embedded.users;
                resolve(self.users);
            })
        });
    }
}

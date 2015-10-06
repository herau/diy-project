/// <reference path="../../typings/_custom.d.ts" />

import {Http, HTTP_BINDINGS} from 'angular2/http';
import { Injectable } from 'angular2/angular2';

@Injectable()
export class ToolService {

    url: String = 'api/tools';

    http: Object;

    tools: Array<Object>;

    constructor(http: Http) {
        this.http = http;
    }

    all() {

        let self = this;

        return new Promise((resolve, reject) => {
            self.http.get(self.url)
            // Call map on the response observable to get the parsed people object.
            .map(res => res.json())
            // Subscribe to the observable to get the parsed people object and attach it to the component.
            .subscribe(tools => {
                self.tools = tools._embedded.tools;
                resolve(self.tools);
            })
        });
    }
}

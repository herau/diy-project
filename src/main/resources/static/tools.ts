/// <reference path="typings/_custom.d.ts" />

import {Component, View, NgFor} from 'angular2/angular2';
import {Http, HTTP_BINDINGS} from 'angular2/http';
import { Injectable } from 'angular2/angular2';

@Injectable()
class ToolService {
  tools: Array<Object> = []
  constructor(http: Http) {
    let self = this;
    http.get('api/tools')
    // Get the RxJS Subject.
    .toRx()
    // Call map on the response observable to get the parsed people object.
    .map(res => res.json())
    // Subscribe to the observable to get the parsed people object and attach it to the component.
    .subscribe(tools => {
        self.tools.push.apply(self.tools, tools._embedded.tools);
    });
  }
}


@Component({
  selector: 'tool-list',
  bindings: [ToolService]
})

@View({
  template: `
  <p>Tools: {{ tools.length }}</p>
  <ul>
     <li *ng-for="#tool of tools">
        {{ tool.name }}
     </li>
  </ul>
  `,
  directives: [NgFor]
})

export class Tools {
  tools: Array<Object> = [];

  constructor(ts: ToolService) {
    this.tools = ts.tools;
  }

}

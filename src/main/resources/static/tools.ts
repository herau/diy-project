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
  <div class="mdl-grid">
    <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col" *ng-for="#tool of tools">
      <div class="mdl-card__title mdl-card--expand">
        <h2 class="mdl-card__title-text">{{ tool.name }}</h2>
      </div>
      <div class="mdl-card__supporting-text">
        {{ tool.description }}
      </div>
      <div class="mdl-card__actions mdl-card--border">
        <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
          Details
        </a>
      </div>
    </div>
  </div>
  `,
  directives: [NgFor]
})

export class Tools {
  tools: Array<Object> = [];

  constructor(ts: ToolService) {
    this.tools = ts.tools;
  }

}

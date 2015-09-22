/// <reference path="../../../typings/_custom.d.ts" />

import {Component, View, NgFor} from 'angular2/angular2';
import {ToolService} from '../../services/tool';

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
        ts.getAll().then(tools => {
            this.tools.push.apply(this.tools, tools);
        })
    }

}

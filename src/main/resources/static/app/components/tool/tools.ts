/// <reference path="../../../typings/_custom.d.ts" />

import {Component, View, NgFor, NgIf} from 'angular2/angular2';
import {ToolService} from '../../services/tool';

@Component({
  selector: 'tool-list',
  providers: [ToolService]
})

@View({
  template: `
  <div class="ui grid">
      <div class="four wide column" *ng-for="#tool of tools">
          <div class="ui card">
              <div class="image">
                  <img src="/images/avatar2/large/kristy.png">
              </div>
              <div class="content">
                  <a class="header">{{ tool.name }}</a>
                  <div class="description">
                    {{ tool.description }}
                  </div>
              </div>
              <div class="extra content">
                  <a>
                      <i class="euro icon"></i>
                      {{ tool.rentalPrice }}
                  </a>
              </div>
          </div>
      </div>
  </div>
  `,
  directives: [NgFor, NgIf]
})

export class ToolList {

    tools: Array<Object> = [];

    fetching: Boolean = false;

    constructor(ts: ToolService) {
        this.fetching = true;
        ts.all().then(tools => {
            this.fetching = false;
            this.tools.push.apply(this.tools, tools);
        })
    }

}

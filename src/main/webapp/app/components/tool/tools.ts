import { Component } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { ToolService } from '../../services/tool';

@Component({
  selector: 'tool-list',
  providers: [ToolService],
  template: `
  <div class="ui centered grid">
      <div class="ui fifteen wide mobile five wide tablet three wide computer column" *ngFor="#tool of tools">
          <div class="ui card" style="width:100%;">
              <div class="blurring dimmable image">
                  <div class="ui dimmer transition hidden">
                      <div class="content">
                          <div class="center">
                              <div class="ui inverted button">View</div>
                          </div>
                      </div>
                  </div>
                  <img src="/images/default_image.png">
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

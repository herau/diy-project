import { Component } from '@angular/core';
import { ToolService } from '../../services/tool';

@Component({
  selector: 'tool-list',
  providers: [ToolService],
  styles: [
    require('./tools.css')
  ],
  template:`
    <md-grid-list cols="4" gutterSize="11px" rowHeight="500">
     <md-grid-tile *ngFor="#tool of tools">
      <md-card>
         <md-card-subtitle></md-card-subtitle>
         <md-card-title>{{ tool.name }}</md-card-title>
         <img md-card-image src="/images/default_image.png">
         <md-card-content>
            <p>{{ tool.description }}</p>
         </md-card-content>
         <md-card-actions>
          <button md-button>DETAILS</button>
         </md-card-actions>
      </md-card>
     </md-grid-tile>
  `,
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

/// <reference path="typings/_custom.d.ts" />

import {Component, View} from 'angular2/angular2';
import {Tools} from './tools';

@Component({
  selector: 'app',
})

@View({
  template: `
  <p>App name: {{ name }}</p>
  <tool-list></tool-list>
  `,
  directives: [Tools]
})

export class App {
  name: string;
  constructor() {
    this.name = "DIY";
  }
  
}

/// <reference path="../../typings/_custom.d.ts" />

import {Component, View} from 'angular2/angular2';
import {RouteConfig, RouterLink, RouterOutlet} from 'angular2/router';
import {Tools} from './tool/tools';

@Component({
    selector: 'app',
})

@View({
    template: `
    <!-- Always shows a header, even in smaller screens. -->
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
      <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
          <!-- Title -->
          <span class="mdl-layout-title">{{ name }}</span>
          <!-- Add spacer, to align navigation to the right -->
          <div class="mdl-layout-spacer"></div>
          <!-- Navigation. We hide it in small screens. -->
          <nav class="mdl-navigation mdl-layout--large-screen-only">
            <a class="mdl-navigation__link" [router-link]="['/tools']">Tools</a>
            <a class="mdl-navigation__link" href="">Link</a>
            <a class="mdl-navigation__link" href="">Link</a>
            <a class="mdl-navigation__link" href="">Link</a>
          </nav>
        </div>
      </header>
      <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">Menu</span>
        <nav class="mdl-navigation">
          <a class="mdl-navigation__link" href="">Home</a>
          <a class="mdl-navigation__link" href="">Tools</a>
          <a class="mdl-navigation__link" href="">Reservation</a>
          <a class="mdl-navigation__link" href="">Profil</a>
        </nav>
      </div>
      <main class="mdl-layout__content">
        <div class="page-content">
        <router-outlet></router-outlet>
        <footer class="mdl-mini-footer">
          <div class="mdl-mini-footer__left-section">
            <div class="mdl-logo">{{ name }}</div>
            <ul class="mdl-mini-footer__link-list">
              <li><a href="#">Help</a></li>
              <li><a href="#">Privacy & Terms</a></li>
            </ul>
          </div>
        </footer>
        </div>
      </main>
    </div>
    `,
    directives: [RouterLink, RouterOutlet]
})

@RouteConfig([
    //{ path: '/',                  redirectTo: '/search' },
    //{ path: '/search',            as: 'search',     component: Search },
    //{ path: '/artist/:id',        as: 'artist',     component: Artist }
    { path: '/tools',               as: 'tools',     component: Tools }
])

export class App {

    name: string;

    constructor() {
        this.name = "DIY";
    }

}

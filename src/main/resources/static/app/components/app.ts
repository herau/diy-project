/// <reference path="../../typings/_custom.d.ts" />

import {Component, View} from 'angular2/angular2';
import {Router, Location, RouteConfig, RouterLink, RouterOutlet} from 'angular2/router';
import {LoginForm} from './login';
import {ToolList} from './tool/tools';
import {UserList} from './user/users';

@Component({
    selector: 'app'
})

@View({
    template: `
    <!-- Always shows a header, even in smaller screens. -->
    <div class="ui three item menu">
        <a class="item" [class.active]="location.path() === '/tools'" [router-link]="['/tools']">Tools</a>
        <a class="item" [class.active]="location.path() === '/users'" [router-link]="['/users']">Users</a>
        <a class="item">Profile</a>
    </div>
     <router-outlet></router-outlet>
    `,
    directives: [RouterLink, RouterOutlet]
})

@RouteConfig([
    //{ path: '/',                  redirectTo: '/search' },
    //{ path: '/search',            as: 'search',     component: Search },
    //{ path: '/artist/:id',        as: 'artist',     component: Artist }
    { path: '/login',               as: 'login',     component: LoginForm },
    { path: '/users',               as: 'users',     component: UserList },
    { path: '/tools',               as: 'tools',     component: ToolList }
])

export class App {

    name: String;
    router: Router;
    location: Location;

    constructor(router: Router, location: Location) {
        this.name = "DIY";
        this.router = router;
        this.location = location;
    }

}

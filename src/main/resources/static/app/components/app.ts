import {Component, View} from 'angular2/core';
import {Router, Location, RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {ToolList} from './tool/tools';
import {UserList} from './user/users';
import {ProfileForm} from './user/profile';

@Component({
    selector: 'app'
})

@View({
    template: `
    <!-- Always shows a header, even in smaller screens. -->
    <div class="ui three item menu">
        <a class="item" [class.active]="location.path() === '/tools'" [routerLink]="['/Tools']">Tools</a>
        <a class="item" [class.active]="location.path() === '/users'" [routerLink]="['/Users']">Users</a>
        <a class="item" [class.active]="location.path() === '/profile'" [routerLink]="['/Profile']">Profile</a>
    </div>
     <router-outlet></router-outlet>
    `,
    directives: [ROUTER_DIRECTIVES]
})

@RouteConfig([
    //{ path: '/',                  redirectTo: '/search' },
    //{ path: '/search',            as: 'Search',     component: Search },
    //{ path: '/artist/:id',        as: 'Artist',     component: Artist }
    { path: '/users',               as: 'Users',      component: UserList },
    { path: '/tools',               as: 'Tools',      component: ToolList },
    { path: '/profile',             as: 'Profile',    component: ProfileForm }
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

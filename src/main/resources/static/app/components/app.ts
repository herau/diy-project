import {Component, View} from "angular2/core";
import {CORE_DIRECTIVES} from "angular2/common";
import {Location, RouteConfig, ROUTER_DIRECTIVES} from "angular2/router";
import {ToolList} from "./tool/tools";
import {UserList} from "./user/users";
import {ProfileForm} from "./user/profile";
import {UserService} from "../services/user";
import {ToolFormComponent} from "../tool-form.component";

@Component({
    selector: 'app',
    providers: [UserService]
})

@View({
    template: `
    <!-- Always shows a header, even in smaller screens. -->
    <div class="ui secondary pointing main menu">
        <a class="item" href="/">
            <i class="configure icon"></i>
            DIY
        </a>
        <a class="item" [class.active]="location.path() === '/tools'" [routerLink]="['/Tools']">Tools</a>
        <a class="item" [class.active]="location.path() === '/users'" [routerLink]="['/Users']">Users</a>
        <a class="item" [class.active]="location.path() === '/profile'" [routerLink]="['/Profile']">Profile</a>
        <a *ngIf="user?.role === 'ADMIN'" class="item" 
        [class.active]="location.path() === '/tools/add'" [routerLink]="['/ToolForm']">Ajouter un outil</a>
    </div>
    <div *ngIf="location.path() === '/tools'" class="ui vertical basic padded segment">
        <div class="ui fluid category search">
            <div class="ui fluid big icon input">
                <input type="text" placeholder="Search tools...">
                <i class="search icon"></i>
            </div>
          <div class="results"></div>
        </div>
    </div>
    <div class="ui container">
        <router-outlet></router-outlet>
    </div>
    `,
    directives: [CORE_DIRECTIVES, ROUTER_DIRECTIVES]
})

@RouteConfig([
    //{ path: '/',                  redirectTo: '/search' },
    //{ path: '/search',            as: 'Search',     component: Search },
    //{ path: '/artist/:id',        as: 'Artist',     component: Artist }
    { path: '/users',               as: 'Users',      component: UserList },
    { path: '/tools',               as: 'Tools',      component: ToolList },
    {path: '/profile', as: 'Profile', component: ProfileForm},
    {path: '/tools/add', as: 'ToolForm', component: ToolFormComponent}
])

export class App {

    name: String;
    location: Location;
    private user:Object;

    constructor(location:Location, userService:UserService) {
        this.user = userService.current();
        this.name = "DIY";
        this.location = location;
    }

}

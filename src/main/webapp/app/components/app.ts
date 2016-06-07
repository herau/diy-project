import { Component } from "@angular/core";
import { Location } from "@angular/common";
import { RouteConfig } from "@angular/router-deprecated";
import { ToolList } from "./tool/tools";
import { UserList } from "./user/users";
import { ProfileForm } from "./user/profile";
import { UserService } from "../services/user";
import { ToolFormComponent } from "../tool-form.component.ts";

@Component({
    selector: 'app',
    providers: [UserService],
    styles: [
      require('normalize.css'),
      require('./app.css')
    ],
    template:`
    <md-sidenav-layout fullscreen>
      <md-sidenav #start mode="push">
        <md-nav-list>
           <a md-list-item [class.active]="location.path() === '/tools'"  [routerLink]="['/Tools']">Tools</a>
           <a md-list-item [class.active]="location.path() === '/users'" [routerLink]="['/Users']">Users</a>
           <a md-list-item [class.active]="location.path() === '/profile'" [routerLink]="['/Profile']">Profile</a>
           <a md-list-item *ngIf="user?.role === 'ADMIN'" [class.active]="location.path() === '/tools/add'" [routerLink]="['/ToolForm']">Add Tool</a>
        </md-nav-list>
      </md-sidenav>
      <md-toolbar color="primary">
        <button md-icon-button (click)="start.toggle()">
          <md-icon class="md-24">menu</md-icon>
        </button>

        <span>DIY</span>

        <span class="toolbar-filler"></span>

        <md-input placeholder="search">
        </md-input>

        <span class="toolbar-filler"></span>
      </md-toolbar>

      <div class="container">
        <router-outlet></router-outlet>
      </div>
    </md-sidenav-layout>
    `
})

@RouteConfig([
    { path: '/users',     as: 'Users',      component: UserList },
    { path: '/tools',     as: 'Tools',      component: ToolList },
    { path: '/profile',   as: 'Profile',    component: ProfileForm },
    { path: '/tools/add', as: 'ToolForm',   component: ToolFormComponent }
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

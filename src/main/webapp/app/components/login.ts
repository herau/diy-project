import { Component } from '@angular/core';

@Component({
  selector: 'login',
  styles: [
    //require('normalize.css')
    require('./login.css')
  ],
  template:`
   <form ngNoForm action="/login" method="post" (ngSubmit)="onSubmit">
   <md-card class="login-card">
    <md-toolbar color="primary">Welcome</md-toolbar>
    <md-card-content>
        <p>
          <md-input id="personalNumber" name="personalNumber" class="login-input" placeholder="Personal Number" pattern="[0-9]*" required autofocus></md-input>
          <md-input id="password" name="password" class="password-input" placeholder="Password" type="password" required></md-input>
        </p>
    </md-card-content>
    <md-card-actions align="end">
     <button md-button>Register</button>
     <button md-raised-button [class.loading]="loading" color="primary">LOGIN</button>
    </md-card-actions>
  </md-card>
  </form>
  `
})


export class Login {

    loading: Boolean = false;

    constructor() {}

    onSubmit() {
        this.loading = true;
    }
}

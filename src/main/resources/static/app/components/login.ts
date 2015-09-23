/// <reference path="../../typings/_custom.d.ts" />

import {Component, View, NgIf} from 'angular2/angular2';
import {FORM_DIRECTIVES} from 'angular2/forms';
import {UserService} from '../services/user';

@Component({
  selector: 'login-form',
  bindings: [UserService]
})

@View({
  template: `
  <form #f="form" (submit)='onSubmit(f.value)'>

      <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <input class="mdl-textfield__input" type="text" pattern="[0-9]*" id="personalNumber" ng-control='personalNumber' autofocus/>
        <label class="mdl-textfield__label" for="personalNumber">Personal Number</label>
        <span class="mdl-textfield__error">Input is not a number!</span>
      </div>

      <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <input class="mdl-textfield__input" type="password" id="password" ng-control='password'/>
        <label class="mdl-textfield__label" for="password">Password</label>
      </div>

      <a href="#" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Mot de passe oublié</a>
      <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect">
        Connexion
      </button>

  </form>
  `,
  directives: [FORM_DIRECTIVES]
})

export class LoginForm {

    service: Object;

    constructor(us: UserService) {
        this.service = us;
    }

    onSubmit(fields: Object) {
        this.service.login(fields.personalNumber, fields.password).then(user => {
            user;
        });
    }
}

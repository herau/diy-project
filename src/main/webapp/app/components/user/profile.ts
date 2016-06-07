import { Component } from "@angular/core";
import { UserService } from "../../services/user";

@Component({
  selector: 'profile-form',
  providers: [UserService],
  template: `
    <div  *ngIf="!logged">
        You must register before you can view your profile!
        Visit our registration page.
    </div>
    <form *ngIf="logged">
      <table style="width: 100%" cellspacing="0">
        <tr>
          <td><md-input type="text" value="{{user.firstname}}" name="data[firstname]" placeholder="First Name" disabled style="width: 100%"></md-input></td>
          <td><md-input type="text" value="{{user.lastname}}" name="data[lastname]" placeholder="Last Name" disabled style="width: 100%"></md-input></td>
        </tr>
      </table>

      <table style="width: 100%" cellspacing="0">
        <tr>
          <td><md-input type="text" value="{{user.company}}" name="data[company]" placeholder="Company" disabled style="width: 100%"></md-input></td>
          <td><md-input type="text" value="{{user.personalNumber}}" name="data[personalNumber]" placeholder="Personal Number" disabled style="width: 100%"></md-input></td>
        </tr>
      </table>

      <p>
        <md-input type="text" value="{{user.email}}" name="data[email]" placeholder="E-mail"></md-input>
      </p>

      <p>
        <md-input type="text" name="data[address]" placeholder="Address"></md-input>
      </p>

      <table style="width: 100%" cellspacing="0">
        <tr>
          <td><md-input name="data[city]" placeholder="City"></md-input></td>
          <td><md-input name="data[state]" placeholder="State"></md-input></td>
          <td><md-input name="data[code]" #postalCode type="text" name="data[code]" maxLength="5" placeholder="Postal Code">
            <md-hint align="end">{{postalCode.characterCount}} / 5</md-hint>
          </md-input></td>
        </tr>
      </table>
    </form>
  `
})

export class ProfileForm {

    user: Object;

    logged: Boolean = false;

    fetching: Boolean = false;

    constructor(ts: UserService) {
        this.fetching = true;

        var user = ts.current();
        if (user) {
            this.fetching = false;
            this.logged = true;
            this.user = user;
        } else {
            this.logged = false;
        }
    }

}

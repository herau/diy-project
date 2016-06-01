import {Http} from "angular2/http";
import {Injectable} from "angular2/core";
//import 'rxjs/operators/map';

@Injectable()
export class UserService {

    url: string = 'api/users';

    http: Http;

    users: Array<Object>;

    user: Object;

    constructor(http: Http) {
        this.http = http;
        this.user = window.user;
    }

    all(): Promise<Array<Object>> {
        let self = this;

        return new Promise((resolve, reject) => {
            self.http.get(self.url)
            // Call map on the response observable to get the parsed people object.
            .map(res => res.json())
            // Subscribe to the observable to get the parsed people object and attach it to the component.
            .subscribe(users => {
                self.users = users;
                resolve(self.users);
            })
        });
    }

    current():Object {
        let self = this;
        return self.user;
    }
}

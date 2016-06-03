import { Headers, Http } from "@angular/http";
import { Injectable } from "@angular/core";
import { Tool } from "../models/tool";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class ToolService {

    url: string = 'api/tools';

    http: Http;

    tools:Array<Tool>;

    constructor(http: Http) {
        this.http = http;
    }

    all():Promise<Array<Tool>> {

        let self = this;

        return new Promise((resolve, reject) => {
            self.http.get(self.url)
            // Call map on the response observable to get the parsed people object.
            .map(res => res.json())
            // Subscribe to the observable to get the parsed people object and attach it to the component.
            .subscribe(tools => {
                self.tools = tools._embedded.tools;
                resolve(self.tools);
            })
        });
    }

    save(tool:Tool):Promise<Tool> {
        if (tool.id) {
            return this.put(tool);
        }
        return this.post(tool);
    }

    private post(tool:Tool):Promise<Tool> {
        let headers = new Headers({
            'Content-Type': 'application/json'
        });

        return this.http
            .post(this.url, JSON.stringify(tool), {headers: headers})
            .toPromise()
            .then(() => tool)
            .catch(this.handleError);
    }

    private put(tool:Tool):Promise<Tool> {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        let url = `${this.url}/${tool.id}`;

        return this.http
            .put(url, JSON.stringify(tool), {headers: headers})
            .toPromise()
            .then(() => tool)
            .catch(this.handleError);
    }

    //TODO externalize it
    private handleError(error:any) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}

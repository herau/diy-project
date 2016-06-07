import { Component } from "@angular/core";
import { ToolService } from "./services/tool";
import { Tool } from "./models/tool";

@Component({
    selector: 'tool-form',
    providers: [ToolService],
    templateUrl: 'app/tool-form.component.html'
})

export class ToolFormComponent {

    private ts:ToolService;
    private model:Tool;
    private submitted = false;

    constructor(ts:ToolService) {
        this.ts = ts;
        this.model = new Tool();
    }

    onSubmit() {
        this.submitted = true;
        this.ts.save(this.model);
    }
}

import { Component } from "@angular/core";
import { HighlightDirective } from "./highlight.directive";

@Component ({
    selector: "custom-directive-component",
    standalone: true,
    imports: [HighlightDirective],
    template: `
        <h2>Ejemplo custom directive</h2>
       <p appHighlight>Texto highlight</p>
    `
})
export class CustomDirectiveComponent {

}
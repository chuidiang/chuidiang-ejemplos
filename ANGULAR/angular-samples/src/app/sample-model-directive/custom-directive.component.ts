import { Component } from "@angular/core";
import { HighlightDirective } from "./highlight.directive";
import { ShowAfterDelayDirective } from "./show-after-delay.directive";

@Component ({
    selector: "custom-directive-component",
    standalone: true,
    imports: [HighlightDirective, ShowAfterDelayDirective],
    template: `
        <h2>Ejemplo custom directive</h2>
        <p appHighlight>Texto highlight</p>
        <p *appShowAfterDelay="10000">Esto sale tras 10 segundos</p>
    `
})
export class CustomDirectiveComponent {

}
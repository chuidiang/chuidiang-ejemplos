import { Component } from "@angular/core";
import { HighlightDirective } from "./highlight.directive";
import { ShowAfterDelayDirective } from "./show-after-delay.directive";

/**
 * Componente para demostrar el uso de las directivas personalizadas
 */
@Component ({
    selector: "custom-directive-component",
    standalone: true,
    imports: [HighlightDirective, ShowAfterDelayDirective],
    template: `
        <h2>Ejemplo custom directive</h2>
        <h3>De atributo</h3>
        <p appHighlight >Texto highlight default</p>
        <p appHighlight [background]="'cyan'" [foreground]="'blue'">Texto highlight cyan/blue</p>

        <h3>Estructural</h3>
        <ng-template [appShowAfterDelay]="10000" let-templateTime='secTime' let-templateText='upperText' [appShowAfterDelayText]="'El Texto'"] >
            <p>Template: Esto sale tras {{templateTime}} segundos {{ templateText }}</p>
        </ng-template>
        <p *appShowAfterDelay="10000;text:'Texto';let templateTime='secTime'; let templateText='upperText'">
            Abreviado: Esto sale tras {{ templateTime }} segundos {{ templateText }}</p> 
    `
})
export class CustomDirectiveComponent {

}
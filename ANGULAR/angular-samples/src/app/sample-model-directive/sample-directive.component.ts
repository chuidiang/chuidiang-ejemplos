import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";

@Component({
    selector: 'app-directive-samples',
    standalone: true,
    imports: [CommonModule],
    template: `
        <p [ngClass]="classes">A string with classes</p>
        <p [ngClass]="classesMap">A map with classes</p>
        <p [ngClass]="classesArray">An array with classes</p>
    `,
    styles: `
       .big {font-size:x-large;}
       .red {color:red;}
    `
})
export class SampleDirectives {
    classes = 'big red';
    classesArray = ['red', 'big'];
    classesMap = {'red': true, 'big': true};
}

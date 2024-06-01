import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";

@Component({
    selector: 'app-directive-samples',
    standalone: true,
    imports: [CommonModule],
    template: `
        <p>Directiva de atributo</p>
        <p [ngClass]="classes">A string with classes</p>
        <p [ngClass]="classesMap">A map with classes</p>
        <p [ngClass]="classesArray">An array with classes</p>
        <p>Directiva estructural</p>
        <p>Caso 1 simple: <span *ngFor="let aClass of classesArray">{{aClass}} </span></p>
        <p>Caso 2 index: <span *ngFor="let aClass of classesArray; index as i">{{i}} - {{aClass}} </span></p>
        <p>Caso 3 template: <ng-template ngFor let-aClass [ngForOf]="classesArray" let-i="index">
            <span>{{i}} - {{aClass}} </span>
        </ng-template></p>
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

    aFunction(index: any, aClass: any){
        return index+aClass;
    }


}

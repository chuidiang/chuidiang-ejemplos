import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";

@Component({
    selector: 'app-directive-samples',
    standalone: true,
    imports: [CommonModule],
    template: `
        <h3>Directiva de atributo ngClass</h3>
        <p [ngClass]="classes">A string with classes</p>
        <p [ngClass]="classesMap">A map with classes</p>
        <p [ngClass]="classesArray">An array with classes</p>
        <h3>Directiva estructural ngFor</h3>
        <p>Caso 1 simple: <span *ngFor="let aClass of classesArray">{{aClass}} </span></p>
        <p>Caso 2 index: <span *ngFor="let aClass of classesArray; index as i">{{i}} - {{aClass}} </span></p>
        <p>Caso 3 template: <ng-template ngFor let-aClass [ngForOf]="classesArray" let-i="index">
            <span>{{i}} - {{aClass}} </span>
        </ng-template></p>
        <h3>Directiva estructural ngIf</h3>

        <!-- Ejemplo simple -->
        <p *ngIf="classes == 'big red'">Simple: se cumple el if</p>

        <!-- ejemplo simple de if con ng-template -->
        <ng-template [ngIf]="classes == 'big red'"><p>ng-template simple: Se cumple el if</p></ng-template>

        <!-- Ejemplo con if else -->
        <p *ngIf="classes == 'bigred'; else elseBlock">Se cumple el if</p>
        
        <ng-template [ngIf]="classes == 'bigred'" [ngIfThen]="thenBlock" [ngIfElse]="elseBlock"]></ng-template>
        <ng-template #thenBlock><p>Se cumple el if</p></ng-template>
        <ng-template #elseBlock><p>No se cumple el if</p></ng-template>
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
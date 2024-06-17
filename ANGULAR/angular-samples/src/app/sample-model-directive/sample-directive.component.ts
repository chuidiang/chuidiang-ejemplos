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
        <p [ngClass]="getClases()">An array with a map inside</p>
        <button (click)="toggleClases()">Alternar Clases</button>
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

        <!-- Ejemplo de ngSwitch -->
        <div [ngSwitch]="currentView">
          <div *ngSwitchCase="'home'">Home View</div>
          <div *ngSwitchCase="'about'">About View</div>
          <div *ngSwitchCase="'contact'">Contact View</div>
          <div *ngSwitchDefault>Default View</div>
        </div>
    `,
    styles: `
       .big {font-size:x-large;}
       .red {color:red;}
       .base {background-color:yellow;}
       .base-class {
      font-size: 18px;
      padding: 10px;
    }
    .clase-activa {
      background-color: lightblue;
    }
    .clase-especial {
      font-weight: bold;
      color: red;
    }
    `
})
export class SampleDirectives {
    clases: string[] = ['base-class', 'clase-activa'];
    classes = 'big red';
    classesArray = ['red', 'big'];
    classesMap = {'red': true, 'big': true};

    // Para el ejemplo de ngSwitch
    currentView = 'home';

    aFunction(index: any, aClass: any){
        return index+aClass;
    }

    toggleClases() {
        if (this.clases.includes('clase-activa')) {
          this.clases = ['base-class', 'clase-especial'];
        } else {
          this.clases = ['base-class', 'clase-activa'];
        }
      }

      getClases() {
        let elArray=this.clases;
        return elArray;
      }
}

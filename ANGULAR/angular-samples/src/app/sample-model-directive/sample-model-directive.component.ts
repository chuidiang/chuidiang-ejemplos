import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

/* Ejemplos de los cuatro tipos de binding entre el modelo y la vista de angular
   - String interpolation: Reemplazamos un trozo de html por el contenido de una variable.
   - Property binding: Reemplazamos el valor de una propiedad de un tag html por el contenido de una variable.
   - Event binding: Llamamos a un metodo de typescript cuando ocurre en evento en html.
   - Two Way binding: Enlace en doble sentido, cambios en la variable de typescript se reflejan en el html y 
                      cambios en el html se reflejan en la variable de typescript.
*/
@Component({
  selector: 'app-sample-model-directive',
  standalone: true,
  imports: [FormsModule],
  template: `<div>
    <!-- con {{variable}} hacemos string interporlation -->
    <p>textModel = <strong>{{textModel}}</strong></p>
    <!-- con [property]="variable" hacemos property binding -->
    <p>booleanModel = <span [class]='booleanClass'>{{booleanModel}}</span></p>
    <p>numberModel = <span [class]="numberClass">{{numberModel}}</span></p>
    <!-- con (event)="metodo()" provocamos que se llame a metodo() cuando se produce el evento -->
    <p><button (click)="zero()">Zero</button></p>
    <!-- con [(ngModel)]="variable" enlazamos el modelo del html (el value del input en este caso) con una variable -->
    <p><input [(ngModel)]="textModel"></p>
  </div>
  `,
  styles: `
    .red {color:red;}
    .blue {color:blue;}`
})
export class SampleModelDirectiveComponent {
  textModel = 'Text';
  booleanModel: boolean = true;
  numberModel: number; 
  numberClass = 'red';
  booleanClass = 'blue';

  constructor(){
    this.numberModel = 3;

    setInterval(() =>{
      this.numberModel++;
    },1000);
  }

  zero(){
    this.numberModel = 0;
  }
}

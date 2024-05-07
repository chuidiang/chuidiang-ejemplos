import { Component } from '@angular/core';

@Component({
  selector: 'app-sample-model-directive',
  standalone: true,
  imports: [],
  template: `<div>
    <p>textModel = <strong>{{textModel}}</strong></p>
    <p>booleanModel = <span [class]='booleanClass'>{{booleanModel}}</span></p>
    <p>numberModel = <span [class]="numberClass">{{numberModel}}</span></p>
    <p><button (click)="zero()">Zero</button></p>
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

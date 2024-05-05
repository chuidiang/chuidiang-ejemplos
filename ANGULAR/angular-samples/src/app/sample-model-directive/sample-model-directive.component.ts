import { Component } from '@angular/core';

@Component({
  selector: 'app-sample-model-directive',
  standalone: true,
  imports: [],
  template: `<div>
    <p>textModel = <span>{{textModel}}</span></p>
    <p>booleanModel = <span [class]='booleanClass'>{{booleanModel}}</span></p>
    <p>numberModel = <span [class]='numberClass'>{{numberModel}}</span></p>
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
}

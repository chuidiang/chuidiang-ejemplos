import { Component } from '@angular/core';

@Component({
  selector: 'app-sample-model-directive',
  standalone: true,
  imports: [],
  template: `<div>
    <p>textModel = <span>{{textModel}}</span></p>
    <p>booleanModel = <span>{{booleanModel}}</span></p>
    <p>numberModel = <span>{{numberModel}}</span></p>
  </div>
  `,
  styles: ``
})
export class SampleModelDirectiveComponent {
  textModel = 'Text';
  booleanModel: boolean = true;
  numberModel: number; 

  constructor(){
    this.numberModel = 3;

    setInterval(() =>{
      this.numberModel++;
    },1000);
  }
}

import { Component } from '@angular/core';
import { MyLibDirective } from './my-lib.directive';

@Component({
  selector: 'lib-my-lib',
  standalone: true,
  imports: [MyLibDirective],
  template: `
    <p upper>
      my-lib works in upper!
    </p>
  `,
  styles: ``
})
export class MyLibComponent {
  constructor(){
    console.log("mylibcomponent me llaman");
  }
}

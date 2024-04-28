import { Component } from '@angular/core';
import { SampleComponentComponent } from './sample-component/sample-component.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SampleComponentComponent],
  template: '<h1>Welcome to {{title}}!</h1>\
     <app-sample-component></app-sample-component>',
  styles: 'h1 {background-color:lightgray}'
})
export class AppComponent {
  title = 'angular-samples';
}

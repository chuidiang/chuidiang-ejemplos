import { Component } from '@angular/core';
import { SampleComponentComponent } from './sample-component/sample-component.component';
import { SampleModelDirectiveComponent } from './sample-model-directive/sample-model-directive.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SampleComponentComponent, SampleModelDirectiveComponent],
  template: '<h1>Welcome to {{title}}!</h1>\
     <app-sample-component></app-sample-component>\
     <app-sample-model-directive></app-sample-model-directive>',
  styles: 'h1 {background-color:lightgray}'
})
export class AppComponent {
  title = 'angular-samples';
}

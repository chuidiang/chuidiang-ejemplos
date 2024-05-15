import { Component } from '@angular/core';
import { SampleComponentComponent } from './sample-component/sample-component.component';
import { SampleModelDirectiveComponent } from './sample-model-directive/sample-model-directive.component';
import { SampleDirectives } from './sample-model-directive/sample-directive.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SampleComponentComponent, SampleModelDirectiveComponent, SampleDirectives],
  template: `<h1>Welcome to {{title}}!</h1>
     <h2>Sample Component</h2>
     <app-sample-component></app-sample-component>
     <h2>Sample Binding</h2>
     <app-sample-model-directive></app-sample-model-directive>
     <h2>Sample Directive</h2>
     <app-directive-samples></app-directive-samples>`,
  styles: 'h1 {background-color:lightgray}'
})
export class AppComponent {
  title = 'angular-samples';
}

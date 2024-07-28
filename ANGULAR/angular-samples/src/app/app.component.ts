import { Component } from '@angular/core';
import { SampleComponentComponent } from './sample-component/sample-component.component';
import { SampleModelDirectiveComponent } from './sample-model-directive/sample-model-directive.component';
import { SampleDirectives } from './sample-model-directive/sample-directive.component';
import { SamplePipe } from './sample-pipe/sample-pipe.component';
import { ServiceComponent } from './sample-service/service.component';
import { UserComponent } from './sample-httpclient/user.component';
import { FormComponent } from './sample-form/form.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SampleComponentComponent, SampleModelDirectiveComponent, SampleDirectives,
    SamplePipe, ServiceComponent, UserComponent, FormComponent],
  template: `<h1>Welcome to {{title}}!</h1>
     <h2>Sample Component</h2>
     <app-sample-component></app-sample-component>
     <h2>Sample Binding</h2>
     <app-sample-model-directive></app-sample-model-directive>
     <h2>Sample Directive</h2>
     <app-directive-samples></app-directive-samples>
     <h2>Sample Pipe</h2>
     <pipe-sample></pipe-sample>
     <service-component></service-component>
     <users></users>
     <form-component></form-component>`,
  styles: 'h1 {background-color:lightgray}'
})
export class AppComponent {
  title = 'angular-samples';
}

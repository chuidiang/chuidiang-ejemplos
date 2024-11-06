import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BasicSampleComponent } from './basic-sample/basic-sample.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, BasicSampleComponent],
  template: `
    <h1>Welcome to {{title}}!</h1>
    <basic-sample></basic-sample>
    <router-outlet />
  `,
  styles: [],
})
export class AppComponent {
  title = 'angular-material-samples';
}

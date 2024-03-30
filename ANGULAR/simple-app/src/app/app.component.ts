import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  standalone: true,
  template: '<h1>Welcome to {{title}}!</h1>',
  styles: 'h1 {background-color:lightgray}'
})
export class AppComponent {
  title = 'simple-app';
}

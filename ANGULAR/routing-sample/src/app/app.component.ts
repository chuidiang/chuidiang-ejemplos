import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <h1>Welcome to {{title}}!</h1>
    <nav>
      <a class="button" routerLink="/one">One</a> |
      <a class="button" routerLink="/two">Two</a>
    </nav>
    <router-outlet />
  `,
  styles: `.button {
    color: #fff;
    background-color: #007bff;
    border-radius: 5px;
  }`,
})
export class AppComponent {
  title = 'Routing-Sample';
}

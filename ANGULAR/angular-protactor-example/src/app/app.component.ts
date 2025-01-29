import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterModule } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [RouterModule, RouterLink, RouterLinkActive],
  template: `
    <h1>Bienvenido a mi aplicación Angular</h1>
    <nav>
      <a routerLink="/about" routerLinkActive="active">Acerca de</a>
    </nav>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {}
import { Component } from "@angular/core";
import { RouterLink, RouterOutlet } from "@angular/router";

@Component({
    selector: 'service-component',
    standalone: true,
    imports: [RouterLink, RouterOutlet ],
    template: `<h2>Service Component</h2>
       <nav>
        <a routerLink="/one">One</a> |
        <a routerLink="/two">Two</a>
      </nav>
       <router-outlet></router-outlet>`,
})
export class ServiceComponent{

}
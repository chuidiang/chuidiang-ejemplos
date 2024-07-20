import { Component } from '@angular/core';
import { RootGreetingService } from '../root-greeting.service';
import { NullGreetingService } from '../null-greeting.service';

/**
 * Componente del lazy module 2.
 * Recibe por inyección los dos servicios RootGreetingService y NullGreetingService.
 * Muestra en el HTML los resultados de ambos servicios
 */
@Component({
  selector: 'gretting-2',
  template: `
    <p>Greeting Service 2</p>
    <p>{{rootGreetingService.getGreeting()}}</p>
    <p>{{nullGreetingService.getGreeting()}}</p>
  `,
  styles: [],
  providers: []
})
export class GreetingComponent2 {
  rootGreetingService: RootGreetingService;
  nullGreetingService : NullGreetingService;
  constructor(gs : RootGreetingService, mgs: NullGreetingService){
    console.log("GreetingComponent2 instantiated")
    this.rootGreetingService = gs;
    this.nullGreetingService = mgs;
  }
}
import { Component } from '@angular/core';
import { RootGreetingService } from '../root-greeting.service';
import { NullGreetingService } from '../null-greeting.service';

/**
 * Componente del lazy module 1.
 * Recibe por inyección los dos servicios RootGreetingService y NullGreetingService.
 * Muestra en el HTML los resultados de ambos servicios
 * 
 * En providers:
 * - No necesita poner RootGreetingService porque al estar en 'root' ya está accesible.
 * - No necesita poner NullGreetingService porque lo hace el lazy module 1 al que pertenece.
 */
@Component({
  selector: 'greeting-1',
  template: `
    <p>Greeting Service 1</p>
    <p>{{rootGreetingService.getGreeting()}}</p>
    <p>{{nullGreetingService.getGreeting()}}</p>
  `,
  styles: [],
  providers: []  // No necesita poner 
})
export class GreetingComponent1 {
  rootGreetingService: RootGreetingService;
  nullGreetingService: NullGreetingService;
  constructor(gs : RootGreetingService, mgs: NullGreetingService){
    console.log("GreetingComponent1 instantiated")
    this.rootGreetingService = gs;
    this.nullGreetingService = mgs;
  }
}
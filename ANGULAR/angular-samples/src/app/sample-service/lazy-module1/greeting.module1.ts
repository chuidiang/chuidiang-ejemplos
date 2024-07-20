import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GreetingComponent1 } from './greeting.component1';
import { NullGreetingService } from '../null-greeting.service';
import { GrettingModuleRouting } from './gretting-module1.routing';

/**
 * Lazy module 1.
 * GreetingComponent1 es el componente que mostrará y en providers indica que se necesita
 * NullGreetingService.
 * No pone RootGreetingService puesto que al estar este servicio providedIn 'root' ya está
 * accesible.
 */

@NgModule({
  declarations: [GreetingComponent1],
  imports: [
    CommonModule, GrettingModuleRouting
  ],
  exports: [GreetingComponent1],
  providers: [NullGreetingService]
})
export class GreetingModule1 { 
    constructor(){
        console.log("GreetingModule1 instantiated")
    }
}
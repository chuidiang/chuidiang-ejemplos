import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GreetingComponent2 } from './greeting.component2';
import { NullGreetingService } from '../null-greeting.service';
import { GrettingModuleRouting2 } from './gretting-module2.routing';


@NgModule({
  declarations: [GreetingComponent2],
  imports: [
    CommonModule, GrettingModuleRouting2
  ],
  exports: [GreetingComponent2],
  providers: [NullGreetingService]
})
export class GreetingModule2 { }
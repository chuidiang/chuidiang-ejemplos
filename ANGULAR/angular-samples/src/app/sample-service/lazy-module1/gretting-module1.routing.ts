import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GreetingComponent1 } from './greeting.component1';

/**
 * Define el componente por defecto (path vacío) cuando se presente el
 */
const routes: Routes = [{ path: '', component: GreetingComponent1 }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GrettingModuleRouting { }
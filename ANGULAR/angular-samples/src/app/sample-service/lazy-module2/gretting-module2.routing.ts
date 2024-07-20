import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GreetingComponent2 } from './greeting.component2';

const routes: Routes = [{ path: '', component: GreetingComponent2 }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GrettingModuleRouting2 {
  constructor(){
    console.log("GreetingModule2 instantiated")
  }
 }
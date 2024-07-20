import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'one',
        loadChildren: () => import('./lazy-module1/greeting.module1').then(m => m.GreetingModule1)
      },
      {
        path: 'two',
        loadChildren: () => import('./lazy-module2/greeting.module2').then(m => m.GreetingModule2)
      }];
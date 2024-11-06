import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'basic-sample',
  standalone: true,
  imports: [MatButtonModule, MatIconModule],
  template: `
    <!-- Todos los posibles button se pueden ver en https://material.angular.io/components/button/overview -->
     <button mat-raised-button color="primary">Primary</button>
     <button mat-flat-button color="accent">Accent</button>
     <button mat-stroked-button color="warn">Warn</button>
     <button mat-icon-button>
        <!-- Listado de todos los posibles iconos en https://fonts.google.com/icons -->
        <mat-icon>share</mat-icon>
     </button>
  `
})
export class BasicSampleComponent {}

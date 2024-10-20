import { Component } from '@angular/core';
import { ChildComponent } from './child.component';

@Component({
  selector: 'app-parent',
  standalone: true,
  imports: [ChildComponent],
  template: `
    <div>
      <h2>Componente Padre</h2>
      <app-child [mensaje]="mensajeParaHijo" (notificacion)="recibirNotificacion($event)"></app-child>
      <p>{{ mensajeDelHijo }}</p>
    </div>
  `
})
export class ParentComponent {
  mensajeParaHijo: string = '¡Hola, hijo desde el componente padre!';
  mensajeDelHijo: string = 'Sin mensaje del hijo de momento';

  recibirNotificacion(mensaje: string) {
    this.mensajeDelHijo = mensaje; // Recibe el mensaje del componente hijo
  }
}

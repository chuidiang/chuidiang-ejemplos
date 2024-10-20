import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-child',
  standalone: true,
  template: `
    <div>
      <strong>Componente Hijo</strong>
      <p>Mensaje desde el componente padre: {{ mensaje }}</p>
      <button (click)="notificarPadre()">Notificar al Padre</button>
    </div>
  `
})
export class ChildComponent {
  @Input()
    mensaje?: string; // Recibe el mensaje desde el padre
  
  @Output() notificacion = new EventEmitter<string>(); // Emite un evento al padre

  notificarPadre() {
    this.notificacion.emit('¡Hola desde el componente hijo!'); // Enviar el mensaje al padre
  }
}

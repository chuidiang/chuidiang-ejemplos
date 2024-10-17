import { Component, OnInit } from '@angular/core';
import { SseService } from './sse.service';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [CommonModule],
  template: `
    <div *ngIf="events.length > 0">
      <h3>Mensajes recibidos:</h3>
      <ul>
        <li *ngFor="let event of events">{{ event }}</li>
      </ul>
    </div>
  `
})
export class AppComponent implements OnInit {
  events: string[] = [];

  constructor(private sseService: SseService) {}

  ngOnInit(): void {
    const sseUrl = 'http://localhost:8080/sse'; // URL del servidor que emite SSE
    this.sseService.getServerSentEvent(sseUrl).subscribe({
      next: (data: string) => {
        this.events.push(data); // Procesar el mensaje recibido
      },
      error: (error) => {
        console.error('Error en SSE:', error); // Manejar errores
      },
      complete: () => {
        console.log('Conexión SSE completada'); // Esto se ejecuta si la conexión SSE se cierra
      }
    });
  }
}

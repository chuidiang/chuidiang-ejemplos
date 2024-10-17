import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SseService {

  constructor(private _zone: NgZone) { }

  getServerSentEvent(url: string): Observable<any> {
    return new Observable(observer => {
      const eventSource = new EventSource(url);

      eventSource.onmessage = (event) => {
        // Para actualizar los cambios en Angular, usa NgZone.run
        this._zone.run(() => {
          observer.next(event.data); // Emitir los datos recibidos
        });
      };

      eventSource.onerror = (error) => {
        this._zone.run(() => {
          observer.error(error); // Manejar errores
        });
      };

      // Al cerrar la conexión, completar el Observable
      return () => {
        eventSource.close();
      };
    });
  }
}

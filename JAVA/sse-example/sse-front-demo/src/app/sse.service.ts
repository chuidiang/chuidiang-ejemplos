import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
/**
 * Servicio que está pendiente de la URL que le indiquemos para recibir mensajes
 * SSE y que notifica al suscriptor que se suscriba.
 * Crea y devuelve un Observable para permitir la suscripción a un componente externo.
 */
export class SseService {

  /**
   * Necesitamos NgZone para que Angular se entere de que se han tratado los mensajes
   * SSE y así actualice la vista automáticamente.
   * @param _zone 
   */
  constructor(private _zone: NgZone) { }

  /**
   * Se le pasa la URL por la que llegarán los mensajes SSE y devuelve un Observable
   * para poder suscribirse a ellos.
   * @param url 
   * @returns 
   */
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

      // Cuando se complete el observable porque lo suscriptores se desusicriben,
      // cerrar EventSource.
      return () => {
        eventSource.close();
      };
    });
  }
}

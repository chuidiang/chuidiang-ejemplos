import { Injectable, EventEmitter } from '@angular/core';
import { ShipData } from './ship-data.interface';  // La interfaz que definimos

@Injectable({
  providedIn: 'root',
})
export class CustomEventService {
  // Definir el EventEmitter para el evento personalizado con el objeto ShipData
  customEvent: EventEmitter<ShipData> = new EventEmitter<ShipData>();

  // Método para emitir el evento con un objeto ShipData
  emitCustomEvent(data: ShipData) {
    this.customEvent.emit(data);
  }

  // Método para suscribirse
  subscribeCustomeEvent (subscriptor: (valor: ShipData) => void) {
    this.customEvent.subscribe(subscriptor);
  }
}

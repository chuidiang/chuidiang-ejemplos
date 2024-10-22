import { Component } from '@angular/core';
import { CustomEventService } from './custom-event.service';
import { ShipData } from './ship-data.interface';

@Component({
  selector: 'app-sender',
  standalone: true,
  template: `
    <button (click)="sendEvent()">Emit Ship Event</button>
  `,
})
export class SenderComponent {
  constructor(private eventService: CustomEventService) {}

  // Método para emitir un evento con un objeto ShipData
  sendEvent() {
    const shipData: ShipData = {
      id: 1,
      name: 'Explorer',
      destination: 'New York',
      position: { lat: 40.7128, lng: -74.0060 }
    };

    this.eventService.emitCustomEvent(shipData);
  }
}

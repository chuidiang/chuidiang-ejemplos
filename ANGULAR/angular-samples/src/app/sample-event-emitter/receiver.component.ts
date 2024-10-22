import { Component, OnInit } from '@angular/core';
import { CustomEventService } from './custom-event.service';
import { ShipData } from './ship-data.interface';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-receiver',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div *ngIf="receivedShipData">
      <p>Ship Name: {{ receivedShipData.name }}</p>
      <p>Destination: {{ receivedShipData.destination }}</p>
      <p>Position: {{ receivedShipData.position.lat }}, {{ receivedShipData.position.lng }}</p>
    </div>
  `,
})
export class ReceiverComponent implements OnInit {
  receivedShipData: ShipData | null = null;

  constructor(private eventService: CustomEventService) {}

  ngOnInit() {
    // Suscribirse al evento del servicio para recibir los datos del objeto ShipData
    this.eventService.customEvent.subscribe((data: ShipData) => {
      this.receivedShipData = data;
    });
  }
}

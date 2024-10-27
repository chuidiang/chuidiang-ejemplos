import { Component } from '@angular/core';
import { SubjectService } from './subject.service';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';

/**
 * Componente para envío de datos al servicio con los Subject.
 * Tiene un botón para enviar números consecutivos de uno en uno.
 * Púlsalo varias veces, verás los números que se han ido enviando.
 * Tienen un botón "Complete Async" para llamar a complete() de AsyncSubject
 * 
 * En el componente receiver pulsa el botón "Subscribe" y fíjate en qué recibe cada subscriptor.
 * Pulsa "Enviar" otra y fíjate qué recibe cada subscriptor
 * Pulsa "Complete Async" y fíjate qué recibe el subscriptor.
 * Sigue enviando datos y fíjate de nuevo.
 * 
 */
@Component({
  selector: 'subject-sender',
  standalone: true,
  imports: [FormsModule, NgFor],
  template: `
    <input disabled=true [(ngModel)]="inputData"/>
    <button (click)="sendData()">Enviar</button>
    <button (click)="complete()">Complete Async</button>
    <p>Data Sent: <ng-template ngFor let-aData [ngForOf]="dataSent"><span>{{ aData }} - </span></ng-template>
  `
})
export class SubjectSendComponent {
  inputData=0;
  dataSent : Number[] = [];

  constructor(private dataService: SubjectService) {}

  sendData() {
    this.dataService.updateData(this.inputData);
    this.dataSent.push(this.inputData);
    this.inputData++;
  }

  complete(){
    this.dataService.complete();
  }
}

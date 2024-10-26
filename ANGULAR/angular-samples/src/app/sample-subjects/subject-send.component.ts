import { Component } from '@angular/core';
import { SubjectService } from './subject.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'subject-sender',
  standalone: true,
  imports: [FormsModule],
  template: `
    <input [(ngModel)]="inputData" placeholder="Escribe algo" />
    <button (click)="sendData()">Enviar</button>
  `
})
export class SubjectSendComponent {
  inputData: string = '';

  constructor(private dataService: SubjectService) {}

  sendData() {
    // this.dataService.updateData(this.inputData);
  }
}

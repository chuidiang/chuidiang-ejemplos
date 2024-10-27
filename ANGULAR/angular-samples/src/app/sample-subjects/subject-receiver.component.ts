import { Component } from '@angular/core';
import { SubjectService } from './subject.service';
import { NgFor } from '@angular/common';

/**
 * Componente que recibe datos del servicio.
 * Tiene cuatro suscriptores, uno por cada tipo de Subject: Subject, BehaviorSubject, ReplaySubject y AsyncSubjet
 * No están suscritos en el arranque, hay que pulsar el botón "Subscribe" para que se suscriban.
 * Con el botón "Complete Async" se llama al método complete() de AsyncSubject.
 * 
 * Para la prueba con el resto de componentes, en el sender, pulsa varias veces el botón enviar.
 * Luego en este pulsa "Subscribe" y fíjate en qué recibe cada suscriptor.
 * Pulsa en el sender "Complete Async" y fíjate en qué recibe el AsyncSubject.
 * Sigue luego, en el sender, enviando más datos y fíjate qué recibe cada uno.
 */
@Component({
  selector: 'subject-receiver',
  standalone: true,
  imports: [NgFor],
  template: `
    <button (click)="subscribe()">Subscribe</button>
    <p>Dato recibido de Subject: <ng-template ngFor let-aData [ngForOf]="receivedData">{{ aData }} - </ng-template></p>
    <p>Dato recibido de BehaviorSubject: <ng-template ngFor let-aBehaviorData [ngForOf]="behaviorData"><span>{{ aBehaviorData }} - </span></ng-template></p>
    <p>Dato recibido de ReplaySubject: <ng-template ngFor let-aReplayData [ngForOf]="replayData"><span>{{ aReplayData }} - </span></ng-template></p>
    <p>Dato recibido de AsyncSubject: <ng-template ngFor let-aAsyncData [ngForOf]="asyncData"><span>{{ aAsyncData }} - </span></ng-template></p>
  `
})
export class SubjectReceiverComponent {
  receivedData: Number[] = [];
  behaviorData: Number[] = [];
  replayData: Number[] = [];
  asyncData: Number[] = [];

  constructor(private dataService: SubjectService) {}

  subscribe() {

    this.dataService.subscribeSubjectData(data => {
      this.receivedData.push(data);
    });

    this.dataService.subscribeBehaviorSubjectData(data => {
        this.behaviorData.push(data);
    });

    this.dataService.subscribeReplaySubjectData(data => {
      this.replayData.push(data);
    });

    this.dataService.subscribeAsyncSubjectData(data => {
        this.asyncData.push(data);
      });
  }
}

import { Component, OnInit } from '@angular/core';
import { SubjectService } from './subject.service';
import { interval } from 'rxjs';
import { take } from 'rxjs';

@Component({
  selector: 'subject-receiver',
  standalone: true,
  template: `
    <button (click)="subscribe()">Subscribe</button>
    <p>Dato recibido de Subject: {{ receivedData }}</p>
    <p>Dato recibido de BehaviorSubject: {{ behaviorData }}</p>
    <p>Dato recibido de ReplaySubject: {{ replayData }}</p>
    
    <p>Dato recibido de AsyncSubject: {{ asyncData }}</p>
  `
})
export class SubjectReceiverComponent {
  receivedData: Number |null = null;
  behaviorData: Number |null = null;
  replayData: Number |null = null;
  asyncData: Number |null = null;

  constructor(private dataService: SubjectService) {}

  subscribe() {

    this.dataService.data.subscribe(data => {
      this.receivedData = data;
    });

    this.dataService.behaviorData.subscribe(data => {
        this.behaviorData = data;
    });

    this.dataService.replayData.subscribe(data => {
      this.replayData = data;
    });

    this.dataService.complete();
    this.dataService.asyncData.subscribe(data => {
        this.asyncData = data;
      });
  }
}

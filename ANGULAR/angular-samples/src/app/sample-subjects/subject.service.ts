import { Injectable } from '@angular/core';
import { Subject, BehaviorSubject, ReplaySubject, AsyncSubject, interval, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  private subjectData = new Subject<Number>();
  private behaviorSubjectData = new BehaviorSubject<Number>(-1); // Estado inicial vacío
  private replaySubjectData = new ReplaySubject<Number>();
  private asyncSubjectData = new AsyncSubject<Number>();
  data = this.subjectData.asObservable();
  behaviorData = this.behaviorSubjectData.asObservable();
  replayData = this.replaySubjectData.asObservable();
  asyncData = this.asyncSubjectData.asObservable();

  numbers: Observable<Number> = interval(1000);

  constructor() {
    this.numbers.subscribe(this.subjectData);
    this.numbers.subscribe(this.behaviorSubjectData);
    this.numbers.subscribe(this.replaySubjectData);
    this.numbers.subscribe(this.asyncSubjectData);
  }

  complete(){
    this.asyncSubjectData.complete();
  }
}

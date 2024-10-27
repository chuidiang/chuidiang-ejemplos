import { Injectable } from '@angular/core';
import { Subject, BehaviorSubject, ReplaySubject, AsyncSubject } from 'rxjs';

/**
 * Servicio con cuatro Subject: Subject, BehaviorSubject, ReplaySubject y AsyncSubject
 */
@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  private subjectData = new Subject<Number>();
  private behaviorSubjectData = new BehaviorSubject<Number>(-1); // Estado inicial vacío
  private replaySubjectData = new ReplaySubject<Number>(3); // Recuerda los tres últimos eventos
  private asyncSubjectData = new AsyncSubject<Number>();
  data = this.subjectData.asObservable();
  behaviorData = this.behaviorSubjectData.asObservable();
  replayData = this.replaySubjectData.asObservable();
  asyncData = this.asyncSubjectData.asObservable();

  complete(){
    this.asyncSubjectData.complete();
  }

  updateData(data: Number){
      this.subjectData.next(data);
      this.behaviorSubjectData.next(data);
      this.replaySubjectData.next(data);
      this.asyncSubjectData.next(data);
  }
}

import { Injectable } from '@angular/core';
import { Data } from './data';
import { Observable, of } from 'rxjs';

@Injectable({providedIn: 'root'})
export class DataService {
  constructor() { }

  getData(): Observable<Data[]> {
    return of([
      {id:1, name:'Pepe'},
      {id:2, name:'Juan'},
      {id:3, name:'Ana'}
    ]);
  }
}

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private data: string = '';

  setData(value: string) {
    this.data = value;
  }

  getData(): string {
    return this.data;
  }
}

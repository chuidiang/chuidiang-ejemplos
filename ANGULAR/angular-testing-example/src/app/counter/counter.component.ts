import { Component } from '@angular/core';

@Component({
  selector: 'app-counter',
  template: `
  <div>
    <p id="counter-value">Counter: {{ count }}</p>
    <button id="increment-button" (click)="increment()">Increment</button>
    <button id="decrement-button" (click)="decrement()">Decrement</button>
  </div>
  `
})
export class CounterComponent {
  count = 0;

  increment() {
    this.count++;
  }

  decrement() {
    this.count--;
  }
}

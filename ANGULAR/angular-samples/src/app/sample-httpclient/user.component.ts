import { Component, OnInit } from '@angular/core';
import { DataService } from './user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'users',
  standalone: true,
  imports: [CommonModule],
  template: `<h2>users</h2>
   <ul>
    <li *ngFor="let item of items">{{item.name}}, {{item.age}}</li>
</ul>
<button (click)="addItem()">Add</button>
<button (click)="deleteItem(0)">Remove First</button>
<button (click)="updateItem(0)">Update First</button>
  `,
  styles: []
})
export class UserComponent implements OnInit {
  items: any[] = [];
  newItem: any = { name: 'New Item', age: 44 };
  updatedItem: any = { name: 'Updated Item', age: 33 };

  constructor(private dataService: DataService) { 
    this.dataService = dataService
  }

  ngOnInit(): void {
    this.getItems();
  }

  getItems(): void {
    this.dataService.getItems().subscribe(data => {
      this.items = data;
    });
  }

  addItem(): void {
    this.dataService.addItem(this.newItem).subscribe(data => {
      this.items.push(data);
    });
  }

  updateItem(id: number): void {
    this.dataService.updateItem(id, this.updatedItem).subscribe(data => {
        this.items[id] = data;
    });
  }

  deleteItem(id: number): void {
    this.dataService.deleteItem(id).subscribe(() => {
      console.log("Borrando");
      this.items.splice(id,1);
      console.log("items = "+this.items);
    });
  }
}
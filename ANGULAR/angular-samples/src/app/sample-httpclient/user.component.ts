import { Component, OnInit } from '@angular/core';
import { DataService } from './user.service';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

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
  /** Items que se obtienen del servicio REST */
  items: any[] = [];
  
  /** Un item para añadir al pulsar el botón. No queremos complicar el ejemplo metiendo un formulario. */
  newItem: any = { name: 'New Item', age: 44 };

  /** Un item para modificar al pulsar el botón. No queremos complicar el ejemplo metiendo un formulario. */
  updatedItem: any = { name: 'Updated Item', age: 33 };

  /**
   * Constructor.
   * @param dataService Servicio con el HttpClient para las llamadas REST al servidor.
   */
  constructor(private dataService: DataService) { 
    this.dataService = dataService
  }

  /**
   * En el arranque pide los items al servidor REST
   */
  ngOnInit(): void {
    this.getItems();
  }

  /**
   * Pide y guarda los items al servidor REST 
   */
  getItems(): void {
    this.dataService.getItems().subscribe(data => {
      this.items = data;
    });
  }

  /**
   * Añade un item usando el servidor REST. Una vez añadido, lo
   * añade también al final de la lista local de items.
   * Estamos presuponiendo que el servidor REST lo va a añadir también
   * al final de la lista. 
   */
  addItem(): void {
    this.dataService.addItem(this.newItem).subscribe(data => {
      this.items.push(data);
    });
  }

  /**
   * Modifica por posición en la lista enviando la petición al servidor REST.
   * @param id Posición en la lista, empezando en 0.
   */
  updateItem(id: number): void {
    this.dataService.updateItem(id, this.updatedItem).subscribe(data => {
        this.items[id] = data;
    });
  }

  /**
   * Elimina un item de la lista por posición a través del servidor REST.
   * Luego lo borra de la lista local.
   * @param id 
   */
  deleteItem(id: number): void {
    this.dataService.deleteItem(id).subscribe({
      next: () => {
        this.items.splice(id,1);
      }, 
      error: (error:HttpErrorResponse) => {
        console.log("Hay un error: " + error.message)}
      });
  }
}
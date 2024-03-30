import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DataService } from './data-list.service';
import { Data } from './data';
import { MenuDirective } from './data-list.directive';
import { MyLibDirective } from '@chuidiang/my-lib';

@Component({
  selector: 'data-list',
  standalone: true,
  imports: [CommonModule, MenuDirective, MyLibDirective],
  templateUrl: './data-list.component.html',
  styleUrl: './data-list.component.css'
})
export class DataList implements OnInit {

  data: Data[] = [];

  constructor(private dataService:DataService){
    console.log("constructor DataList");
  }

  ngOnInit(): void {
    this.dataService.getData().subscribe((data:Data[]) => {
      this.data = data;
    })
  }
}

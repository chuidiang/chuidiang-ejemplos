import { Component } from "@angular/core";
import { DataList } from './data-list/data-list.component';
import { MyLibComponent, MyLibDirective } from "@chuidiang/my-lib";
import { ContainerComponent } from "./container/container.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [DataList, MyLibComponent, MyLibDirective, ContainerComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'My List of Data';
}

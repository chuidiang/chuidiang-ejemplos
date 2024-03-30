import { Component } from "@angular/core";
import { DataList } from './data-list/data-list.component';
import { MyLibComponent, MyLibDirective } from "@chuidiang/my-lib";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [DataList, MyLibComponent, MyLibDirective],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'My List of Data';
}

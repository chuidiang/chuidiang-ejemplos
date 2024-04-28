import { bootstrapApplication } from "@angular/platform-browser";
import { AppComponent } from './app/app.component';
import { Panel1Component } from "./app/panel1/panel1.component";
import { Panel2Component } from "./app/panel2/panel2.component";

bootstrapApplication(AppComponent, {
  providers : [
    {provide: 'PanelInterface', useClass: Panel1Component, multi: true},
    {provide: 'PanelInterface', useClass: Panel2Component, multi: true}
  ]
})
  .catch((err) => console.error(err));

import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';

/** Arranque de la aplicación */
bootstrapApplication(AppComponent)
  .catch((err) => console.error(err));

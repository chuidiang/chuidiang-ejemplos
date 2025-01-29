import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { routes } from './app/app-routing';
import { provideRouter } from '@angular/router';

/** Arranque de la aplicación */
bootstrapApplication(AppComponent, {
    providers: [provideRouter(routes)]  
})
  .catch((err) => console.error(err));
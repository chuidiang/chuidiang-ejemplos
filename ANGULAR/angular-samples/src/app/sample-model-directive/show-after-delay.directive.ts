import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

/**
 * Directiva estructural personalizada.
 * Se le pasa un retardo en milisegundos y un texto.
 * Tras el retardo muestra el componente
 * El texto lo transforma en mayúsculas y lo devuelve al template
 * El tiempo lo pasa a segundos y lo devuelve al template
 */
@Directive({
  selector: '[appShowAfterDelay]',
  standalone: true
})
export class ShowAfterDelayDirective {
  
  /* Parametros que nos pasarán desde el template */
  @Input() appShowAfterDelay:number = 0;
  @Input() appShowAfterDelayText:string="";
  
  /* Timeout y se dibuja el componente */
  async ngOnInit() {
    this.viewContainer.clear();

    // Establecer un retraso para renderizar el contenido
    setTimeout(() => {
      this.viewContainer.createEmbeddedView(this.templateRef, {
        upperText: this.appShowAfterDelayText.toUpperCase(),  // Se publica el texto en mayúsculas
        secTime: this.appShowAfterDelay/1000                // Se publica el tiempo en segundos
      });
      console.log(this.appShowAfterDelayText);
    }, this.appShowAfterDelay);
  }

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
}

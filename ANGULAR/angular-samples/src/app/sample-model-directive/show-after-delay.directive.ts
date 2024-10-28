import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appShowAfterDelay]',
  standalone: true
})
export class ShowAfterDelayDirective {
    
  @Input('appShowAfterDelay') set delayTime(ms: number) {
    // Limpiar cualquier vista previa
    this.viewContainer.clear();

    // Establecer un retraso para renderizar el contenido
    setTimeout(() => {
      this.viewContainer.createEmbeddedView(this.templateRef);
    }, ms);
  }

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
}

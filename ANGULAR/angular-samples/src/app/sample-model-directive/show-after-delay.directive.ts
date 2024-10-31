import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appShowAfterDelay]',
  standalone: true
})
export class ShowAfterDelayDirective {
    
  otro!:string;

  @Input() set appShowAfterDelayOtro(otro:string){
    this.otro=otro;
    console.log(otro);
  }

  @Input() set appShowAfterDelay(ms: number) {
    // Limpiar cualquier vista previa
    this.viewContainer.clear();

    // Establecer un retraso para renderizar el contenido
    setTimeout(() => {
      this.viewContainer.createEmbeddedView(this.templateRef);
      console.log(this.otro);
    }, ms);
  }

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
}

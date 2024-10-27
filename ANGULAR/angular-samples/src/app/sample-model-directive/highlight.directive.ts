import { Directive, ElementRef, HostListener, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appHighlight]',  // El selector que usarás en tu HTML
  standalone: true
})
export class HighlightDirective {
  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('mouseenter') onMouseEnter() {
    this.highlight('yellow');  // Cambia el color de fondo a amarillo al pasar el mouse
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.highlight(null);  // Restaura el color de fondo al salir el mouse
  }

  private highlight(color: string | null) {
    this.renderer.setStyle(this.el.nativeElement, 'backgroundColor', color);
  }
}

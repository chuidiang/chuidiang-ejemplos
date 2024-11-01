import { Directive, ElementRef, HostListener, Renderer2, Input } from '@angular/core';

/**
 * Directiva personalizada para hacer que el html donde la usemos
 * cambie de color de background y foreground al pasar el ratón por encima.
 */
@Directive({
  selector: '[appHighlight]',  // El selector que usarás en tu HTML
  standalone: true
})
export class HighlightDirective {
  constructor(private el: ElementRef, private renderer: Renderer2) {}

  /* Podemos recibir parámetros del template */
  @Input() background: string ='yellow';
  @Input() foreground: string ='red';

  /* Nos suscribimos a cuando entra el ratón en el componente para poner los colores */
  @HostListener('mouseenter') onMouseEnter() {
    this.highlight(this.background, this.foreground);  // Cambia el color de fondo a amarillo al pasar el mouse
  }

  /* Nos suscribimos a cuando sale el ratón del componente para eliminar los colores */
  @HostListener('mouseleave') onMouseLeave() {
    this.highlight(null, null);  // Restaura el color de fondo al salir el mouse
  }

  /* Cambio de colores */
  private highlight(background: string|null, foreground: string|null) {
    this.renderer.setStyle(this.el.nativeElement, 'backgroundColor', background);
    this.renderer.setStyle(this.el.nativeElement, 'color', foreground);
  }
}

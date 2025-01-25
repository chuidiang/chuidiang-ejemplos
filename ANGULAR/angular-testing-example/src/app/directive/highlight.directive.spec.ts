import { HighlightDirective } from './highlight.directive';
import { ElementRef } from '@angular/core';

describe('HighlightDirective', () => {
  it('should highlight the element on mouse enter', () => {
    const element = new ElementRef(document.createElement('div'));
    const directive = new HighlightDirective(element);

    directive.onMouseEnter();
    expect(element.nativeElement.style.backgroundColor).toBe('yellow');

    directive.onMouseLeave();
    expect(element.nativeElement.style.backgroundColor).toBe('');
  });
});

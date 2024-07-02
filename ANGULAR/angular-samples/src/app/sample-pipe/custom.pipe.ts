import { Pipe } from '@angular/core';

/**
 * Ejemplo de pipe a medida.
 * Si el valor es cero, se pone el texto que se pase como parámetro a la pipe
 * 
 * Ejemplo.
 * {{ valor | zero:'el valor es cero'}}
 */
@Pipe({
  name: 'zero',
  standalone: true
})
export class CeroPipe {
  transform(value: any, args?: string): any {
    if (value == 0 && args != null){
        return args;    
    }
    return value;
  }

}
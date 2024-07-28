import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';

/**
 * Ejemplo de formulario. Importamos FormsModule.
 */
@Component({
  selector: 'form-component',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule] 
})
export class FormComponent {
  // Variables para guardar los valores recogidos del formulario.
  name: string = "";
  email: string = "";

  // Metodo al que se llamara cuando se envíe el formulario.
  // form.value contendrá los distintos campos del formulario.
  onSubmit(form: NgForm): void {
    console.log('Formulario enviado!', form.value);
    this.name = form.value.name;
    this.email = form.value.email;
  }
}

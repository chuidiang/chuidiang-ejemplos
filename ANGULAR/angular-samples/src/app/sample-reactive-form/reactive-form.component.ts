import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'reactive-form',
  templateUrl: './reactive-form.component.html',
  styleUrls: ['./reactive-form.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule]
})
export class ReactiveForm {
  // Variables para guardar los valores recogidos del formulario.
  name: string = "";
  email: string = "";
  password: string = "";

  // Definir el FormGroup
  userForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  // Método para manejar el envío del formulario
  onSubmit() {
    if (this.userForm.valid) {
      console.log(this.userForm.value); // Aquí puedes hacer algo con los datos, como enviarlos a un servidor
      this.name=this.userForm.value.name?this.userForm.value.name:"";
      this.email=this.userForm.value.email?this.userForm.value.email:"";
      this.password=this.userForm.value.password?this.userForm.value.password:"";
    }
  }
}
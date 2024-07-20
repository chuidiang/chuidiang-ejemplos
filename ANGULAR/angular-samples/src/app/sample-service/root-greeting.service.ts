import { Injectable } from "@angular/core";

/**
 * Ejemplo de servicio simple con providedIn a 'root'
 * Esto hará:
 * - El servicio estará disiponible para cualquier componente o modulo sin
 * necesidad de lo que lo ponga en su providers.
 * - La instancia es compartida para todos los módulos.
 */

@Injectable({
    providedIn: 'root'
})
export class RootGreetingService {
    constructor(){
        console.log("RootGreetingService instantiated")
    }
    getGreeting(): string{
        return "Root Service: Hello!"
    }
}
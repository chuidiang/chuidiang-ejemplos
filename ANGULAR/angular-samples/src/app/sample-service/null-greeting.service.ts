import { Injectable } from "@angular/core";

/**
 * Ejemplo de servicio simple con providedIn por defecto (sin definir)
 * Esto hará:
 * - El servicio estará disiponible para cualquier componente o modulo que 
 * en su providers ponga NullGreetingService
 * - Se crearán nuevas instancias de este servicios para los módulos lazy
 * loaded.
 */
@Injectable()
export class NullGreetingService {
    constructor(){
        console.log("NullGreetingService instantiated")
    }
    getGreeting(): string{
        return "Null Service: Hello!"
    }
}
/**
 * Ejemplo de función para usar como decorador de clase.
 * Es un genérico. Recibe de parámetro la clase original. 
 * Esta clase es de tipo T que debe extender de { new (...args: any[]): {} }.
 * Este tipo raro es el de una clase, es decir, que se instancia con un new(args) y devuelve una instancia {}
 * La función en sí devuelve una clase hija de la original con funcionalidad añadida: Sacar un log en el constructor.
 * 
 * @param constructor La clase que está siendo decorada.
 * @returns Clase hija de la recibida con la funcionalidad añadida.
 */
function classDecorator<T extends { new (...args: any[]): {} }>(originalClass: T) {
    return class extends originalClass {
        constructor(...args: any[]){
            super(args);
            console.log(originalClass); // Saca por pantalla [class NombreClaseQueSeInstancia]
        }
    }
}

/**
 * Ejemplo de función para usar como decorador de método.
 * En descriptor.value se recibe la función original. Cambiando descriptor.value por otra función y
 * devolviendo el descriptor, modificamos la función.
 * @param target 
 * @param propertyKey 
 * @param descriptor 
 * @returns 
 */
function methodDecorator(target: any, propertyKey: string, descriptor: PropertyDescriptor) {
    const originalMethod = descriptor.value;

    descriptor.value = function (...args: any[]) {
      console.log(`Método ${propertyKey} llamado con argumentos: ${args}`);
      return originalMethod.apply(this, args);
    };
    
    return descriptor;
}

/**
 * Ejemplo de decorador de atributo. No puede ser cadena vacia.
 * Basta devolver un PropertyDescriptor con los métodos get() y set()
 * de la propiedad, donde verificamos en el set() que no sea cadena vacía.
 * @param target 
 * @param propertyKey 
 */
function NotEmpty(target: any, propertyKey: string) {
    let value:string;
    const descriptor: PropertyDescriptor = {
        get() {
            return value;
        },
        set(newValue: string) {
            if (newValue == "") {
                throw new Error(`El valor "${newValue}" puede estar vacía.`);
            }
            value = newValue;
        }
    };

    Object.defineProperty(target, propertyKey, descriptor);
}

function NoEmptyParameter(target: any, propertyKey: string, parameterIndex: number) {
    console.log("Parámetro requerido: "+ propertyKey + " " + parameterIndex);
}

@classDecorator
export class MyClass {

    @NotEmpty
    param: string;

    constructor(parameter: string) { 
        console.log('My class: '+parameter);
        this.param = parameter;
    }

    @methodDecorator
    myFunction(@NoEmptyParameter params:string): void {
        console.log("myFunction: " + params);   
    }
}
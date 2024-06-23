// Definición de un decorador de clase
function SaludarClase(constructor: Function) {
    console.log("Hola desde el constructor");
}

// Aplicando el decorador a una clase
@SaludarClase
class Ejemplo {
    constructor() {
        console.log("Hola desde el constructor de Ejemplo");
    }
}

// Definición de un decorador de clase
function SaludarClase2<T extends { new (...args: any[]): {} }>(originalClass: T) {
    console.log("Hola desde el constructor");
    return class extends originalClass {
        constructor(...args: any[]){
            super(args);
            console.log(originalClass); // Saca por pantalla [class NombreClaseQueSeInstancia]
        }
        newMethod(): void {
            console.log("Soy el método nuevo");
        }
    
        newAttribute: string = "Hola!";
    
    }
}

// Aplicando el decorador a una clase
@SaludarClase2
class Ejemplo2 {
    constructor() {
        console.log("Hola desde el constructor de Ejemplo");
    }
}

// Creando una instancia de la clase
const ejemplo = new Ejemplo2(); 
// Salida:
// "Hola desde el constructor"
// "Hola desde el constructor de Ejemplo"

const ejemplo2 = new Ejemplo2();
// Salida:
// "Hola desde el constructor"

(ejemplo2 as any).newMethod();
(ejemplo2 as any).newAttribute;
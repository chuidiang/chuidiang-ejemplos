import { MyClass } from "./module";

console.log("Hola Mundo");
Object.defineProperty(MyClass, 'myFunction',
  {"writable":true,"enumerable":false,"configurable":false}
)
var instance = new MyClass("Pedro");
console.log(instance.param);
instance.myFunction("Ey!");
instance.myFunction("Ey2!");
instance = new MyClass("Juan");
console.log(instance.param);
instance = new MyClass("a");
console.log(instance.param);
instance.myFunction("");

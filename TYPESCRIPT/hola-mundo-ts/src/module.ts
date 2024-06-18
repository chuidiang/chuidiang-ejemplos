function classDecorator(target: Function, className: ClassDecoratorContext<typeof MyClass>) {
    console.log(target);
    console.log(className);
}

@classDecorator
export class MyClass {
    constructor() { 
        console.log('My class')
    }
}
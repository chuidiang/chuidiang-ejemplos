export interface Data {
    id: number;
    name: string;
    toString() : string;
}

export class DataImpl1 implements Data {
    id: number;
    name: string;
    constructor(id:number, name:string){
        this.id = id;
        this.name = name;
    }
    toString(): string {
        return this.id + " - " + this.name;
    }
}

export class DataImpl2 implements Data {
    id: number;
    name: string;
    constructor(id:number, name:string){
        this.id = id;
        this.name = name;
    }
    toString(): string {
        return this.id + " - " + this.name;
    }
}

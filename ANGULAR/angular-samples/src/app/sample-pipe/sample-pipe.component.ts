import { Component } from "@angular/core";
import { UpperCasePipe, LowerCasePipe, DatePipe, CurrencyPipe, DecimalPipe, PercentPipe, JsonPipe } from "@angular/common";
import { CeroPipe } from "./custom.pipe";

@Component({
    selector:'pipe-sample',
    standalone:true,
    imports:[UpperCasePipe, LowerCasePipe, DatePipe, CurrencyPipe, DecimalPipe, PercentPipe, JsonPipe, CeroPipe],
    template:`
        <p>UpperCase: {{texto | uppercase}}</p>
        <p>LpperCase: {{texto | lowercase}}</p>
        <p>Fecha defecto: {{today | date}}</p>
        <p>Fecha custom: {{today | date:'y:M:d H:m:s'}}</p>
        <p>Fecha corta: {{today | date:'short'}}</p>
        <p>Fecha larga: {{today | date:'long'}}</p>
        <p>Solo fecha: {{today | date:'shortDate'}}</p>
        <p>Solo hora: {{today | date:'shortTime'}}</p>
        <p>Dinero, por defecto $: {{amount | currency}}</p>
        <p>Dinero, euros con código: {{amount | currency:'EUR':'code'}}</p>
        <p>Dinero, euros con símbolo: {{amount | currency:'EUR':'symbol'}}</p>
        <p>Dinero, 3 cifras enteras, mínimo 2 decimales, máximo 4 decimales: {{amount | currency:'EUR':'symbol':'3.2-4'}}</p>
        <p>Número, 3 cifras enteras, mínimo 2 decimales, máximo 4 decimales: {{amount | number:'3.2-4'}}</p>
        <p>Porcentaje: {{amount | percent}}</p>
        <p>Json: <pre>{{object | json}}</pre>
        <p>Custom: {{value | zero:'sin valor'}}</p>
    `
})
export class SamplePipe {
    texto: string = 'Texto de Prueba'
    today: number = Date.now();
    amount: number = 1.234567;
    value: number = 0;
    object: Object = {
        "amount":33,
        "name":"Juan"
    }
}
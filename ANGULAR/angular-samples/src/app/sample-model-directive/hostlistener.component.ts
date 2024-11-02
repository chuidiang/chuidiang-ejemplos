import { Component } from "@angular/core";
import { HostListenerDirective } from "./hostlistener.directive";

/**
 * Componente que usa la directiva event-listener para sacar por log
 * eventos que se produzcan.
 */
@Component ({
    selector:'hostlistener-component',
    standalone:true,
    imports:[HostListenerDirective],
    template:`
    <h2>Ejemplo de host listener</h2>
    <p (clickEmitter)="handleClick($event)" event-listener>Haz eventos sobre mi o en el documento. 
        De raton, redimensionado de ventana, etc</p>
        <p>Numero de clicks : {{ clicks }}`
})
export class HostListenerComponent {
    clicks:number=0;

    handleClick(event:string){
        if ("click"==event){
            this.clicks++;
        }
    }
}
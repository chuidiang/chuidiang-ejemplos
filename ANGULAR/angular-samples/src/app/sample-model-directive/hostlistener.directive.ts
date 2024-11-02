import { Directive, EventEmitter, HostListener, Output} from "@angular/core";

/**
 * Ejemplo de directiva que saca log de algunos de los eventos que se produzcan.
 * Listado de eventos válidos https://www.w3schools.com/jsref/dom_obj_event.asp
 */
@Directive({
    selector:'[event-listener]',
    standalone:true
})
export class HostListenerDirective {
    /* Para avisar a elemento que tenga esta directiva */
    @Output() clickEmitter:EventEmitter<string> = new EventEmitter();

    @HostListener('click', ['$event']) 
        handleClick(event: MouseEvent){
            console.log("Click : "+ event.x + ", "+ event.y);
            this.clickEmitter.emit('click');
    }

    @HostListener('document:click', ['$event.x', '$event.y'])
    handleDocumentClick(x:number, y:number){
        console.log("Document Click : "+ x + ", "+ y);
    }

    @HostListener('window:resize', ['$event'])
    handleResize(event: UIEvent){
        console.log("Resize: ");
        console.log(event);
    }

}
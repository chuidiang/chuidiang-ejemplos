import { Directive, ElementRef, OnInit } from "@angular/core";

@Directive({
    standalone: true,
    selector: '[upper]'
})
export class MyLibDirective implements OnInit {
    constructor(private el: ElementRef) {
    }
    ngOnInit() {
        this.el.nativeElement.innerText = this.el.nativeElement.innerText.toUpperCase();
    }
}

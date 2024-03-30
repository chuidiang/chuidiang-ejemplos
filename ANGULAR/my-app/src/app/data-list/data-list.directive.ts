import { AfterViewInit, Directive, ElementRef } from "@angular/core";

@Directive ({
    standalone: true,
    selector:'[menu]'
})
export class MenuDirective implements AfterViewInit {
    constructor(private el: ElementRef) {
        console.log("constructor menudirective")
    }

    ngAfterViewInit() {
        this.el.nativeElement.innerText = this.el.nativeElement.innerText.toUpperCase();
        console.log("menudirective me llaman");
    }
}
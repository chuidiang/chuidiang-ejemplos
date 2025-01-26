import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CounterComponent } from './counter.component';
import { By } from '@angular/platform-browser';

describe('CounterComponent', () => {
  let component: CounterComponent;
  let fixture: ComponentFixture<CounterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CounterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CounterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // Detectar cambios iniciales en la plantilla
  });

  it('debería incrementar el contador al hacer clic en el botón de incrementar', () => {
    const incrementButton = fixture.debugElement.query(By.css('#increment-button'));
    incrementButton.triggerEventHandler('click', null); // Simular clic

    fixture.detectChanges(); // Detectar cambios en la plantilla

    const counterText = fixture.debugElement.query(By.css('#counter-value')).nativeElement.textContent;
    expect(counterText).toContain('Counter: 1'); // Verificar que el contador se incrementó
  });

  it('debería decrementar el contador al hacer clic en el botón de decrementar', () => {
    component.count = 2; // Inicializar el contador en un valor mayor a 0
    fixture.detectChanges();

    const decrementButton = fixture.debugElement.query(By.css('#decrement-button'));
    decrementButton.triggerEventHandler('click', null); // Simular clic

    fixture.detectChanges(); // Detectar cambios en la plantilla

    const counterText = fixture.debugElement.query(By.css('#counter-value')).nativeElement.textContent;
    expect(counterText).toContain('Counter: 1'); // Verificar que el contador se decrementó
  });
});

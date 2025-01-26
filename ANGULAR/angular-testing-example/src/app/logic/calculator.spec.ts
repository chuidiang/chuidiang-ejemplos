import { Calculator } from './calculator';

/** El test para la calculadora */
describe('Calculator', () => {
  it('should return the sum of two numbers', () => {
    const calculator = new Calculator();
    expect(calculator.add(2, 3)).toBe(5);
  });
});

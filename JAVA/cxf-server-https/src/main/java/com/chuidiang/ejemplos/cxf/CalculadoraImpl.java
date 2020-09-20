package com.chuidiang.ejemplos.cxf;

/**
 * @author fjabellan
 * @date 18/09/2020
 */
public class CalculadoraImpl implements Calculadora {
    public double suma(double a, double b) {
        return a + b;
    }

    @Override
    public double resta(double a, double b) {
        return a - b;
    }

    @Override
    public double multiplica(double a, double b) {
        return a*b;
    }

    @Override
    public double divide(double a, double b) {
        return a/b;
    }
}
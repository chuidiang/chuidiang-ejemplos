package com.chuidiang.ejemplos.cxf;

/**
 * iMARE INDRA (C) 2020 Indra Sistemas (unless otherwise noted). All rights reserved.
 *
 * @author fjabellan
 * @date 18/09/2020
 */
import javax.jws.WebService;

@WebService
public interface Calculadora {
    double suma (double a, double b);
    double resta (double a, double b);
    double multiplica (double a, double b);
    double divide (double a, double b);
}
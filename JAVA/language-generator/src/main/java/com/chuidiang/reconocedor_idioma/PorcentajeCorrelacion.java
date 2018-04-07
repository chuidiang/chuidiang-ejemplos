/*
 * Fichero: PorcentajeCorrelacion.java
 * Autor: Chuidiang
 * Fecha: 14/10/06 12:53
 *
 */
package com.chuidiang.reconocedor_idioma;

import java.text.DecimalFormat;


/**
 * Clase que contiene el nombre de un idioma y el porcentaje de correlaci�n con ese idioma.
 * Sabe comparar porcentajes y escribirlos en formato legible.
 *
 * @author Chuidiang
 *
 */
public class PorcentajeCorrelacion implements Comparable<PorcentajeCorrelacion>
{
    /**
     * Nombre del idioma
     */
    public String nombreIdioma;

    /**
     * Porcentaje de correlación, en tanto por uno.
     */
    public double porcentajeCorrelacion;

    /**
     * Compara dos porcentajes, devolviendo 1 si el porcentaje de la clase es mayor que
     * el porcentaje que se le pasa.
     *
     * @param o Un PorcentajeCorrelacion con el que comparar
     *
     * @return 1 si this es mayor que o
     */
    public int compareTo(PorcentajeCorrelacion o)
    {
        if (o == null)
        {
            return 1;
        }

        if (porcentajeCorrelacion > o.porcentajeCorrelacion)
        {
            return 1;
        }

        if (porcentajeCorrelacion == o.porcentajeCorrelacion)
        {
            return 0;
        }

        return -1;
    }

    /**
     * Devuelve un String legible con el nombre del idioma y el porcentaje de correlaci�n. 
     *
     * @return String legible por el usuario
     */
    public String toString()
    {
        DecimalFormat formato = new DecimalFormat("###.##%");

        return nombreIdioma + ": " + formato.format(porcentajeCorrelacion);
    }
}

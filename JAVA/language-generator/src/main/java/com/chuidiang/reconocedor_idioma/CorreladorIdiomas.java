/*
 * Fichero: CorreladorIdiomas.java
 * Autor: Chuidiang
 * Fecha: 14/10/06 12:51
 */
package com.chuidiang.reconocedor_idioma;

import java.io.FileReader;
import java.io.Reader;

import java.util.Arrays;
import java.util.LinkedList;


/**
 * Clase que guarda las estadisticas de caracteres de varios idiomas. Correla cada uno de
 * los idiomas guardados con una muestra de idioma que se le pase, devolviendo la lista
 * de idiomas posibles para el idioma muestra.
 *
 * @author Chuidiang
 */
public class CorreladorIdiomas
{
    /** Lista con las estadisticas de caracteres de los idiomas */
    LinkedList<EstadisticaCaracteres> listaIdiomas = new LinkedList<EstadisticaCaracteres>();

    /**
     * Añade un nuevo idioma a la lista de idiomas
     *
     * @param nombreIdioma Un nombre para el idioma que servirá para identificarlo.
     * @param nombreFicheroMuestra El nombre de un fichero de texto en el idioma indicado
     * para que sirva de muestra.
     */
    public void addIdioma(String nombreIdioma, String nombreFicheroMuestra)
    {
        try
        {
            EstadisticaCaracteres estadistica = new EstadisticaCaracteres(
                    nombreIdioma, new FileReader(nombreFicheroMuestra));
            listaIdiomas.add(estadistica);
        }
        catch (Exception e)
        {
            System.err.println(
                "Problemas con el fichero " + nombreFicheroMuestra + ": " + e);
        }
    }

    /**
     * Añade un idioma nuevo cogiendo la muestra del Reader que se le pasa.
     *
     * @param nombreIdioma Nombre del idioma.
     * @param textoIdioma Reader con el texto de muestra del idioma
     */
    public void addIdioma(String nombreIdioma, Reader textoIdioma)
    {
        EstadisticaCaracteres estadistica = new EstadisticaCaracteres(
                nombreIdioma, textoIdioma);
        listaIdiomas.add(estadistica);
    }

    /**
     * Borra de la lista el idioma que se le pasa.
     *
     * @param nombreIdioma Nombre que identifica el idioma.
     */
    public void removeIdioma(String nombreIdioma)
    {
        for (int i = 0; i < listaIdiomas.size(); i++)
        {
            if (listaIdiomas.get(i).getIdentificador().equals(nombreIdioma))
            {
                listaIdiomas.remove(i);

                return;
            }
        }
    }

    /**
     * Analiza el idioma que se le pasa y lo correla con cada uno de los idiomas que tiene
     * en su lista. Devuelve un array ordenador con el porcentaje de correlación de cada
     * uno de los idiomas de la lista con el idioma que se le pasa.
     *
     * @param entrada Un reader en el que se pueda leer un texto en un idioma de prueba.
     *
     * @return Array de porcentajes de correlacion. El primero es el que menos correla y el
     * último el que más.
     */
    public PorcentajeCorrelacion[] analizaIdioma(Reader entrada)
    {
        PorcentajeCorrelacion[] porcentajes = null;

        try
        {
            EstadisticaCaracteres estadistica = new EstadisticaCaracteres(
                    "Idioma analizado", entrada);
            porcentajes = new PorcentajeCorrelacion[listaIdiomas.size()];

            for (int i = 0; i < porcentajes.length; i++)
            {
                EstadisticaCaracteres idiomaPatron = listaIdiomas.get(i);
                porcentajes[i] = new PorcentajeCorrelacion();
                porcentajes[i].nombreIdioma = idiomaPatron.getIdentificador();
                porcentajes[i].porcentajeCorrelacion = idiomaPatron.correla(
                        estadistica);
            }

            Arrays.sort(porcentajes);
        }
        catch (Exception e)
        {
            System.err.println("Problemas al correlar " + e);
        }

        return porcentajes;
    }

    /**
     * Analiza el idioma que se le pasa y lo correla con cada uno de los idiomas que tiene
     * en su lista. Devuelve un array ordenador con el porcentaje de correlación de cada
     * uno de los idiomas de la lista con el idioma que se le pasa.
     *
     *
     * @param nombreFicheroMuestra Nombre de un fichero de texto en el idioma de prueba.
     *
     * @return Array de porcentajes de correlacion. El primero es el que menos correla y el
     * último el que más.
     */
    public PorcentajeCorrelacion[] analizaIdioma(String nombreFicheroMuestra)
    {
        try
        {
            FileReader fichero = new FileReader(nombreFicheroMuestra);

            return analizaIdioma(fichero);
        }
        catch (Exception e)
        {
            System.err.println("Problemas al correlar " + e);
        }

        return null;
    }
}

/*
 * Fichero: Texto.java
 * Autor: Chuidiang
 * Fecha: 20/10/06 22:31
 */
package com.chuidiang.reconocedor_idioma;

import java.io.Reader;

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Clase a la que se le da un texto y es capaz de generar un texto aleatorio en algo que
 * se parece al idioma del texto original. El texto no tiene sentido, pero alguien que no
 * conozca el idioma, lo identificará como tal.
 *
 * @author Chuidiang
 *
 */
public class GeneradorTexto
{
    /**
     * Nombre del idioma original.
     */
    private String idioma;

    /**
     * El texto guardado.
     */
    private StringBuffer bufferTexto;

    /**
     * Crea un nuevo objeto Texto.
     *
     * @param idioma Nombre del idioma
     * @param texto Un reader para leer el texto.
     */
    public GeneradorTexto(String idioma, Reader texto)
    {
        this.idioma = idioma;
        bufferTexto = new StringBuffer();

        char[] caracteres = new char[1000];

        try
        {
            int leidos = texto.read(caracteres);

            while (leidos > 0)
            {
                bufferTexto.append(caracteres, 0, leidos);
                leidos = texto.read(caracteres);
            }
        }
        catch (Exception e)
        {
        }
    }

    /**
     * Devuelve un texto aleatorio de la longitud que se le indica.
     * El texto supuestamente mantiene las mismas estadísticas de caracteres que el texto
     * que se pasó originalmente en el constructor, consiguiendo así una apariencia de texto
     * en el mismo idioma, aunque no tenga sentido.
     *
     * @param longitud Longitud deseada
     *
     * @return Texto aleatorio
     */
    public StringBuffer dameTexto(int longitud)
    {
        StringBuffer textoResultante = new StringBuffer();
        String pareja = damePrimeraPareja();
        textoResultante.append(pareja);

        for (int i = 0; i < 500; i++)
        {
            char letra = dameSiguienteLetra(pareja);
            textoResultante.append(letra);
            pareja = new String(new char[]{ pareja.charAt(1), letra });
        }

        return textoResultante;
    }

    /**
     * Devuelve aleatoriamente una primera pareja de letras con las que empezar una
     * palabra. Esta pareja será algunas de las parejas de letras que empiezan una palabra
     * en el texto original.
     *
     * @return Un String con dos letras.
     */
    public String damePrimeraPareja()
    {
        Hashtable<String, Integer> parejas = new Hashtable<String, Integer>();
        int contador = calculaPosiblesPrimerasParejas(parejas);

        return seleccionaPrimeraParejaEntreLasPosibles(parejas, contador);
    }

    /**
     * DOCUMENT ME!
     *
     * @param parejas DOCUMENT ME!
     * @param contador DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private String seleccionaPrimeraParejaEntreLasPosibles(
        Hashtable<String, Integer> parejas, int contador)
    {
        int valor = (int) (Math.random() * contador);
        Enumeration<String> claves = parejas.keys();
        int parcial = 0;
        String pareja = null;

        while (claves.hasMoreElements())
        {
            pareja = claves.nextElement();
            parcial = parcial + parejas.get(pareja);

            if (parcial > valor)
            {
                return pareja;
            }
        }

        return pareja;
    }

    /**
     * Rellena en el Hashtable las posibles parejas de letras que pueden empezar el texto
     * aleatorio que se van a generar. Lo hace copiando las parejas de letras de letras en
     * el texto original que comienzan una palabra, es decir, las dos primeras y las que
     * siguen a los espacios en blanco.
     * Mete los resultados en un Hashtable, en el que la clave es un String con la pareja
     * de letras y el valor es un Integer que representa el número de veces que esa pareja
     * comienza una palabra en el texto original.
     *
     * @param parejas Hashtable en el que se rellenaran las parejas. Debes estar inicialmente
     * vacio.
     *
     * @return Numero de parejas en el texto original que comienzan una palabra.
     */
    private int calculaPosiblesPrimerasParejas(
        Hashtable<String, Integer> parejas)
    {
        parejas.put(bufferTexto.substring(0, 2), 1);

        int contador = 1;

        for (int i = 0; i < (bufferTexto.length() - 2); i++)
        {
            if (bufferTexto.charAt(i) == ' ')
            {
                String pareja = bufferTexto.substring(i + 1, i + 3);

                if (parejas.get(pareja) == null)
                {
                    parejas.put(pareja, 1);
                }
                else
                {
                    parejas.put(pareja, parejas.get(pareja) + 1);
                }

                contador++;
            }
        }

        return contador;
    }

    /**
     * Dada una pareja de letras, busca en el texto original qué posibles letras pueden
     * seguir a dicha pareja. Devuelve una de ellas conservando las probabilidades del
     * texto origianl.
     *
     * @param pareja Una pareja de letras consecutivas que aparezcan en el texto original
     *
     * @return Alguna de las letras que sigue a esa pareja en el texto original
     */
    public char dameSiguienteLetra(String pareja)
    {
        Hashtable<Character, Integer> letras = new Hashtable<Character, Integer>();
        int contador = calculaPosiblesLetrasQueSiguenAPareja(pareja, letras);

        return seleccionaLetraEntreLasPosibles(letras, contador);
    }

    /**
     * Se le pasa un Hashtable de letras obtenido con 
     * calculaPosiblesLetrasQueSiguenAUnaPareja() y elige una que devuelve.
     * En el Hashtable cada letra es una clave y tiene un número que es el número de veces
     * que en el texto original esa letra seguía a una pareja concreta.
     * La letra se selecciona al azar, pero de forma que la letra con el número más alto
     * asociado tiene más probabilidades de salir.
     *
     * @param letras Hashtable con claves letras y valores Integer que representan el
     * número de veces que sale dicha letra detrás de una pareja de letras concreta.
     * @param contador Numero total de letras que siguen a una pareja.
     *
     * @return La letra seleccionada.
     */
    private char seleccionaLetraEntreLasPosibles(
        Hashtable<Character, Integer> letras, int contador)
    {
        int valor = (int) (Math.random() * contador);
        Enumeration<Character> claves = letras.keys();
        int parcial = 0;
        Character letra = null;

        while (claves.hasMoreElements())
        {
            letra = claves.nextElement();
            parcial = parcial + letras.get(letra);

            if (parcial > valor)
            {
                return letra;
            }
        }

        return letra;
    }

    /**
     * Dada una pareja de letras, busca en el texto original las letras que siguen a dicha
     * pareja.
     * Rellena el Hashtable poniendo como claves las letras que siguen a la pareja y como
     * valor el número de veces que cada letra, en el texto original, sigue a dicha pareja.
     *
     * @param pareja Pareja de letras de interes, un String con dos letras.
     * @param letras Hashtable que se devuelve relleno. Debería estar vacio inicialmente.
     *
     * @return número de veces que aparece la pareja en el texto.
     */
    private int calculaPosiblesLetrasQueSiguenAPareja(
        String pareja, Hashtable<Character, Integer> letras)
    {
        int contador = 0;

        for (int i = 0; i < (bufferTexto.length() - 3); i++)
        {
            String posiblePareja = bufferTexto.substring(i, i + 2);

            if (posiblePareja.equals(pareja))
            {
                char letra = bufferTexto.charAt(i + 2);

                if (letras.get(letra) == null)
                {
                    letras.put(letra, 1);
                }
                else
                {
                    letras.put(letra, letras.get(letra) + 1);
                }

                contador++;
            }
        }

        return contador;
    }

    /**
     * Devuelve el nombre de idioma que se le pasó anteriormente.
     *
     * @return Nombre de idioma.
     */
    public String getIdioma()
    {
        return idioma;
    }

    /**
     * Guarda el nombre de idioma que se le pasa.
     *
     * @param idioma Un nombre de idioma.
     */
    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }
}

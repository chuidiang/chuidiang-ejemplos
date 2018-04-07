/*
 * Fichero: EstadisticaCaracteres.java
 * Autor: Chuidiang
 * Fecha: 14/10/06 12:59
 */
package com.chuidiang.reconocedor_idioma;

import java.io.BufferedReader;
import java.io.Reader;

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Calcula las frecuencias con que en un texto aparece cada pareja de
 * letras. Es decir, para "hola", calcula cuantas veces aparece "ho", "ol" y
 * "la".
 */
public class EstadisticaCaracteres
{
    /** Un identificador cualquiera para el texto */
    private String identificador;

    /**
     * Las frecuencias de cada pareja de letras. La pareja de letras es
     * la clave del Hash y el contenido en el el número de veces que aparece
     * esa pareja en el texto
     */
    private Hashtable<String, Double> hashParejas = new Hashtable<String, Double>();

    /** Número de caracteres consecutivos que se van a usar para la estadística */
    private int numeroCaracteres = 2;

    /**
     * Construye la estadística a partir de InputStream. El identificador
     * puede ser un texto cualquiera
     * @param identificador Un texto cualquiera, normalemente el nombre del idioma (español,
     * inglés, francés, etc)
     * @param entrada Un InputStream de caracteres (fichero, socket, etc).
     */
    public EstadisticaCaracteres(String identificador, Reader entrada)
    {
        this.identificador = identificador;
        calculaEstadisticas(entrada);
    }

    /**
     * Calcula las estadisticas del InputStream.
     *
     * @param entrada Un InputStream de caracteres
     */
    private void calculaEstadisticas(Reader entrada)
    {
        BufferedReader reader = new BufferedReader(entrada);

        try
        {
            char[] caracteres = new char[100];

            // Lectura de los primeros 100 caracteres
            int leidos = reader.read(caracteres);

            while (leidos != -1)
            {
                pasaTextoAMayusculas(caracteres, leidos);

                for (int i = 0; i < (leidos - numeroCaracteres); i++)
                {
                    incrementaValorDeParejaDeCaracteres(caracteres, i);
                }

                // Se guarda el último caracter en la primera posición
                // y se leen los 99 siguientes, para tener 100 en la
                // próxima vuelta y "empalmar" el último caracter anterior
                // con el primero que se lea en la siguiente tanda.
                if (leidos > numeroCaracteres)
                {
                    for (int j = 0; j < (numeroCaracteres - 1); j++)
                    {
                        caracteres[j] = caracteres[leidos - j - 1];
                    }
                }

                leidos = reader.read(
                        caracteres, numeroCaracteres, 100 - numeroCaracteres);
            }

            normaliza();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Inctrementa en 1 el valor de la pareja de caracteres que se está
     * leyendo en este momento.
     *
     * @param caracteres Array de caracteres leido
     * @param i Posicion de la pareja de caracteres
     */
    private void incrementaValorDeParejaDeCaracteres(char[] caracteres, int i)
    {
        double valorActual;
        String pareja = String.copyValueOf(caracteres, i, 2);

        if (hashParejas.get(pareja) != null)
        {
            valorActual = hashParejas.get(pareja);
            valorActual = valorActual + 1;
        }
        else
        {
            valorActual = 1;
        }

        hashParejas.put(pareja, valorActual);
    }

    /**
     * Pasa todos los caracteres a mayúsculas.
     *
     * @param caracteres Array de caracteres leido
     * @param leidos Numero de caracteres válidos en el array
     */
    private void pasaTextoAMayusculas(char[] caracteres, int leidos)
    {
        for (int i = 0; i < leidos; i++)
        {
            caracteres[i] = Character.toUpperCase(caracteres[i]);
        }
    }

    /**
     * Hace que el modulo del "vector" de estadísticas en el que hay
     * tantas dimensiones como parejas de caracteres, valga 1. De esta forma
     * se podrá luego multiplicar escalarmente con otra "vector" similar.
     */
    public void normaliza()
    {
        double modulo = calculaModulo();
        divideEntreElModulo(modulo);
    }

    /**
     * Divide cada uno de los valores entre el módulo, para que el
     * "vector" sea unitario
     *
     * @param modulo Modulo del vector
     */
    private void divideEntreElModulo(double modulo)
    {
        Enumeration<String> claves;
        claves = hashParejas.keys();

        while (claves.hasMoreElements())
        {
            String car = claves.nextElement();
            hashParejas.put(car, hashParejas.get(car) / modulo);
        }
    }

    /**
     * Calcula el modulo del vector, elevando al cuadrado cada valor de
     * cada pareja, sumando todos los cuadrados y haciendo luego la raiz
     * cuadrada.
     *
     * @return Calcula el modulo del vector.
     */
    private double calculaModulo()
    {
        double total = 0.0;
        Enumeration<String> claves = hashParejas.keys();

        while (claves.hasMoreElements())
        {
            String car = claves.nextElement();
            total = total + Math.pow(hashParejas.get(car), 2.0);
        }

        double modulo = Math.sqrt(total);

        return modulo;
    }

    /**
     * Correla con otra estadistica de otro texto, haciendo un producto
     * escalar de ambos vectores. Devuelve el resultado.
     *
     * @param otraEstadistica Otro vector de parejas de caracteres
     *
     * @return El producto escalar de ambos
     */
    public double correla(EstadisticaCaracteres otraEstadistica)
    {
        return correla(otraEstadistica.hashParejas);
    }

    /**
     * Correla con otra estadistica de otro texto, haciendo un producto
     * escalar de ambos vectores. Devuelve el resultado.
     *
     * @param otroNumeroLetras
     *
     * @return El porcentaje en tanto por 1.
     */
    private double correla(Hashtable<String, Double> otroNumeroLetras)
    {
        double resultado = 0.0;
        Enumeration<String> claves = hashParejas.keys();

        while (claves.hasMoreElements())
        {
            String clave = claves.nextElement();

            if (otroNumeroLetras.get(clave) != null)
            {
                resultado = resultado +
                    (hashParejas.get(clave) * otroNumeroLetras.get(clave));
            }
        }

        return resultado;
    }

    /**
     * Contiene el número de veces que se repite cada grupo de letras.
     *
     * @return El hashtable que contiene los grupos de letras y el numero de
     *         veces que se repite cada grupo.
     */
    public Hashtable<String, Double> getHashParejas()
    {
        return hashParejas;
    }

    /**
     * Devuelve el nombre del idioma.
     *
     * @return El identificador que se paso en el contructor.
     */
    public String getIdentificador()
    {
        return identificador;
    }

    /**
     * Un texto cualquiera para identificar o representar esta
     * estadística con un nombre. Por ejemplo "inglés", "español", "texto de
     * prueba", etc.
     *
     * @param identificador Un texto cualquiera
     */
    public void setIdentificador(String identificador)
    {
        this.identificador = identificador;
    }

    /**
     * Devuelve cuántos caracteres consecutivos se usan para la estadística.
     *
     * @return Número de caracteres consecutivos para las estadísticas.
     */
    public int getNumeroCaracteres()
    {
        return numeroCaracteres;
    }

    /**
     * Fija cuántos caracteres hay que usar para la estadística. Para que tenga efecto debe
     * llamarse antes de llamar a calculaEstadisticas()
     *
     * @param numeroCaracteres Número de caracteres consecutivos para la estadística
     */
    public void setNumeroCaracteres(int numeroCaracteres)
    {
        this.numeroCaracteres = numeroCaracteres;
    }
}

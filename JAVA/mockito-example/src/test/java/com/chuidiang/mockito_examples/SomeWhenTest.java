package com.chuidiang.mockito_examples;

import com.chuidiang.mockito_examples.when.Data;
import com.chuidiang.mockito_examples.when.IfzDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

/**
 * Ejemplos de Mockito when.
 * No tienen nada que ver con el código en el lado de src/main/java. Solo son ejemplos
 * de uso de when().
 * Hacemos un Mock de una List de java y simulamos distintos comportamientos.
 * @author Chuidiang
 * date 03/03/2024
 */
@ExtendWith(MockitoExtension.class)
public class SomeWhenTest {

    /**
     * Esperamos una llamada con un elemento concreto.
     */
    @Test
    public void testSimpleWhen (){
        IfzDao mockList = Mockito.mock(IfzDao.class);
        Mockito.when(mockList.findById(1)).thenReturn(new Data(1,"Uno"));

        Data data = mockList.findById(1);
        Assertions.assertEquals(new Data(1,"Uno"), data);
        data = mockList.findById(2);
        Assertions.assertNull(data);
    }

    /**
     * Configuramos la salida en función del valor de entrada
     */
    @Test
    public void testWhenAnyValue () {
        IfzDao mockDao = Mockito.mock(IfzDao.class);
        Mockito.when(mockDao.findById(Mockito.anyInt())).thenAnswer(invocation -> {
             Object argument = invocation.getArgument(0);
             return new Data((Integer)argument, argument.toString());
        });

        Data data = mockDao.findById(1);
        Assertions.assertEquals(new Data(1,"1"), data);
        data = mockDao.findById(2);
        Assertions.assertEquals(new Data(2,"2"), data);
    }


    /**
     * Ponemos reglas para rangos en el parámetro de entrada.
     */
    @Test
    public void testWithArgThat() {
        IfzDao mockDao = Mockito.mock(IfzDao.class);

        Mockito.when(mockDao.findById(Mockito.intThat(value -> value<10))).thenReturn(new Data(1,"1"));
        Mockito.when(mockDao.findById(Mockito.intThat(value -> value>=10))).thenThrow(new NoSuchElementException("No hay"));

        Data data = mockDao.findById(5);
        Assertions.assertEquals(new Data(1,"1"),data);
        try {
            data = mockDao.findById(11);
        } catch (Exception e){
            Assertions.assertTrue(e instanceof NoSuchElementException);
            return;
        }
        Assertions.fail("No ha saltado la excepción.");
    }

    /**
     * Concatenamos varias respuestas a varias llamadas consecutivas
     */
    @Test
    public void testSeveralReturn(){
        IfzDao mockDao = Mockito.mock(IfzDao.class);

        Mockito.when(mockDao.findById(Mockito.anyInt()))
                .thenReturn(new Data(1,"1"))
                .thenReturn(new Data(2,"2"))
                .thenThrow(new NoSuchElementException("No hay"));

        Data data = mockDao.findById(1);
        Assertions.assertEquals(new Data(1,"1"),data);
        data = mockDao.findById(33);
        Assertions.assertEquals(new Data(2,"2"),data);
        try {
            data = mockDao.findById(11);
        } catch (Exception e){
            Assertions.assertTrue(e instanceof NoSuchElementException);
            return;
        }
        Assertions.fail("No ha saltado la excepción.");
    }
}

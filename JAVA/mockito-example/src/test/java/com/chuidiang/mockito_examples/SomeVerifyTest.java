package com.chuidiang.mockito_examples;

import com.chuidiang.mockito_examples.when.StaticClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Algunos ejemplos de Mockito.verify()
 * @author fjabellan 07/03/2024
 */
@ExtendWith(MockitoExtension.class)
public class SomeVerifyTest {
    @InjectMocks
    SomeComplexClass someComplexClass;

    @Mock
    DataBaseClass dataBaseClass;

    @Mock
    NetworkClass networkClass;

    @Mock
    OutputClass outputClass;

    /**
     * Varias verificaciones sobre Mock normales (no estáticos)
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void verifyTimes() throws SQLException, IOException {
        // Preparación del test: Mockito.when()
        Mockito.when(dataBaseClass.getStringFromDataBase()).thenReturn("Hello");
        Mockito.when(networkClass.getStringFromRemoteServer()).thenReturn("World");

        // Ejecución del test. Llamada al método de la clase bajo test
        someComplexClass.concatStringsFromDataBaseAndNetworkAndOutputResult();

        // Verificaciones.

        // Que se han llamado a los métodos.
        Mockito.verify(outputClass).printOutput("Hello - World");
        Mockito.verify(dataBaseClass).getStringFromDataBase();
        Mockito.verify(networkClass).getStringFromRemoteServer();

        // Que se han llamado a los métodos un número determinado de veces.
        Mockito.verify(outputClass, Mockito.times(1)).printOutput(Mockito.anyString());
        Mockito.verify(dataBaseClass, Mockito.atLeast(1)).getStringFromDataBase();
        Mockito.verify(networkClass, Mockito.atLeastOnce()).getStringFromRemoteServer();

        // Verificar que no se ha llamado a un método
        Mockito.verify(outputClass, Mockito.never()).printPrettyOutput(Mockito.anyString());

        // Que no se ha llamado a ningún método que no hayamos verificado ya en las líneas anteriores.
        Mockito.verifyNoMoreInteractions(outputClass, dataBaseClass, networkClass);

        // Que se han llamado a los métodos en el orden esperado.
        InOrder inOrder = Mockito.inOrder(dataBaseClass, networkClass, outputClass);
        inOrder.verify(dataBaseClass).getStringFromDataBase();
        inOrder.verify(networkClass).getStringFromRemoteServer();
        inOrder.verify(outputClass).printOutput(Mockito.anyString());

    }

    /**
     * Verificación de que se ha llamado a un método estático.
     */
    @Test
    public void staticVerify(){
        try (MockedStatic<StaticClass> staticClassMockedStatic = Mockito.mockStatic(StaticClass.class)){
            // Preparación, Mockito.when
            staticClassMockedStatic.when(StaticClass::getName).thenReturn("Mockeado");
            staticClassMockedStatic.when(()->StaticClass.addOne(1)).thenReturn(33);

            // Ejecución del test
            StaticClass.getName();

            // Verificaciones
            staticClassMockedStatic.verify(() -> StaticClass.getName(), Mockito.times(1));
            staticClassMockedStatic.verify(() -> StaticClass.addOne(Mockito.anyInt()), Mockito.never());
        }
    }
}

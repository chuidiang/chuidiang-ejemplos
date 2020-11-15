package com.chuidiang.mockito_examples;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author fjabellan
 * @date 15/11/2020
 */
 @RunWith(MockitoJUnitRunner.class)
public class SomeComplexClassTest {
    @InjectMocks
    SomeComplexClass someComplexClass;

    @Mock
    DataBaseClass dataBaseClass;

    @Mock
    NetworkClass networkClass;

    @Mock
    OutputClass outputClass;

    @Test
    public void someSimpleTest() throws SQLException, ClassNotFoundException, IOException {
        System.out.println("Yabadabadoooo");
        Mockito.when(dataBaseClass.getStringFromDataBase()).thenReturn("Hello");
        Mockito.when(networkClass.getStringFromRemoteServer()).thenReturn("World");

        someComplexClass.concatStringsFromDataBaseAndNetworkAndOutputResult();

        Mockito.verify(outputClass).printOutput("Hello - World");

    }
}

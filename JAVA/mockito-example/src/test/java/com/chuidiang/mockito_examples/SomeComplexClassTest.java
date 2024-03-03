package com.chuidiang.mockito_examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author chuidiang
 * @date 15/11/2020
 */
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
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
    public void aSimpleTest() throws SQLException, ClassNotFoundException, IOException {

        Mockito.when(dataBaseClass.getStringFromDataBase()).thenReturn("Hello");
        Mockito.when(networkClass.getStringFromRemoteServer()).thenReturn("World");

        someComplexClass.concatStringsFromDataBaseAndNetworkAndOutputResult();

        Mockito.verify(outputClass).printOutput("Hello - World");

    }

    @Test
    public void aCaptorTest() throws SQLException, IOException {
        Mockito.when(dataBaseClass.getStringFromDataBase()).thenReturn("Hello");
        Mockito.when(networkClass.getStringFromRemoteServer()).thenReturn("World");

        someComplexClass.concatStringsFromDataBaseAndNetworkAndOutputResult();

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        Mockito.verify(outputClass).printOutput(argument.capture());

        Assertions.assertEquals("Hello - World",argument.getValue());
    }
 }

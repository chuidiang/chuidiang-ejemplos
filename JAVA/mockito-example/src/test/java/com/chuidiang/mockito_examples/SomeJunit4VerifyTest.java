package com.chuidiang.mockito_examples;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 * Algunos ejemplos de Mockito.verify()
 * @author fjabellan 07/03/2024
 */
@RunWith(MockitoJUnitRunner.class)  // JUnit 4
public class SomeJunit4VerifyTest {

    @Mock
    DataBaseClass dataBaseClass;

    @Test(expected = NoSuchElementException.class)
    public void exceptionVerify() throws SQLException {
        Mockito.when(dataBaseClass.getStringFromDataBase()).thenThrow(new NoSuchElementException());
        dataBaseClass.getStringFromDataBase();
    }

}

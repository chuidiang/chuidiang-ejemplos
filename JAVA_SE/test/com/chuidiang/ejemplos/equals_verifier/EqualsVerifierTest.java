package com.chuidiang.ejemplos.equals_verifier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

/**
 * @author fjabellan
 * @date 19/12/2021
 */
public class EqualsVerifierTest {
    @Test
    public void equalTest(){
        EqualsVerifier.configure().suppress(Warning.NONFINAL_FIELDS).forClass(com.chuidiang.ejemplos.equals_verifier.EqualsVerifier.class).verify();
    }
}

/*
 * Open API chuidiang example
 * El ejemplo maravilloso
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for SaludosApi
 */
@Ignore
public class SaludosApiTest {

    private final SaludosApi api = new SaludosApi();

    
    /**
     * 
     *
     * Se le pasa un nombre y devuelve saludo a ese nombre
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void greetingTest() throws ApiException {
        String name = null;
                String response = api.greeting(name);
        // TODO: test validations
    }
    
}

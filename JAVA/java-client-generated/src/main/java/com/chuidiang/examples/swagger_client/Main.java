package com.chuidiang.examples.swagger_client;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.SaludosApi;
import org.openapitools.client.api.WebService2Api;
import org.openapitools.client.model.Data;
import org.threeten.bp.OffsetDateTime;


/**
 * @author fjabellan
 * @date 22/05/2022
 */
public class Main {
    public static void main(String[] args) throws ApiException {
        SaludosApi saludosApi = new SaludosApi();
        System.out.println(saludosApi.greeting("Pedro"));

        Data data = new Data().value(11).date(OffsetDateTime.now()).string("kk");
        WebService2Api webService2Api=new WebService2Api();
        System.out.println(webService2Api.greeting2WithHttpInfo(data).getData());
    }
}

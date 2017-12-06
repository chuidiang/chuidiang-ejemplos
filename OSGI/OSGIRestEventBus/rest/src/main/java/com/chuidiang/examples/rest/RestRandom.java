package com.chuidiang.examples.rest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;


/**
 * Created by chuidiang on 2/12/17.
 */

//@Path("/random")
public class RestRandom implements RandomIfz {

    public RestRandom(){
        System.out.println("--- Me instancian RestRandom");
    }

    @Override
//    @GET
//    @Produces("text/plain")
    public String getRandom(){
        System.out.println("-- Me llaman");
        return "{\"value\":"+Double.toString(Math.random())+"}";
    }

    @Activate
    public void start(){
        System.out.println("---- Activate");
    }
}

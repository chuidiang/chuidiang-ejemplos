package com.chuidiang.examples.rest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 * Created by chuidiang on 2/12/17.
 */
@Component
@Path("/random")
public class RestRandom implements RandomIfz {

    public RestRandom(){
        System.out.println("--- Me instancian RestRandom");
    }

    @Override
    @GET
    @Produces("text/plain")
    public String getRandom(){
        System.out.println("-- Me llaman");
        return "{\"value\":"+Double.toString(Math.random())+"}";
    }

    @Activate
    public void start(){
        System.out.println("---- Activate");
    }
}

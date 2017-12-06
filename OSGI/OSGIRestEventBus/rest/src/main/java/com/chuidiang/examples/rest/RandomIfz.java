package com.chuidiang.examples.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by chuidiang on 2/12/17.
 */
//@Path("/random")
public interface RandomIfz {
//    @GET
//    @Produces("text/plain")
    String getRandom();
}

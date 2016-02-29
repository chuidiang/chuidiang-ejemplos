package com.chuidiang.examples.restful;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * This class will be a Restful, JSON, web service.
 * 
 * The path will be http://<host>:<port>/<applitation name>/resources/service/
 * 
 * The path "resource" is defined in MyApplication.class. The path "service" is
 * defined in this class.
 * 
 * @author Chuidiang
 *
 */
@Path("/service/")
public class RestfulService {
   
   /**
    * Get a list of Data
    * @return
    */
   @GET
   @Produces({"application/json"})
   public List<Data> getData(){
      List<Data> result = new LinkedList<>();
      result.add(new Data(1,"one"));
      result.add(new Data(2,"two"));
      return result;
   }
   
   /**
    * Get data with the specified id.
    * @param id
    * @return
    */
   @GET
   @Produces({"application/json"})
   @Path("{id}")
   public Data getData(@PathParam("id") String id){
      if ("1".equals(id)){
         return new Data(1,"one");
      }
      if ("2".equals(id)){
         return new Data(2,"two");
      }
      throw new WebApplicationException(404);
   }

}

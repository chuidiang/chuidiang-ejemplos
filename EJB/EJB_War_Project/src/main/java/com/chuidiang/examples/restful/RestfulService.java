package com.chuidiang.examples.restful;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/service/")
public class RestfulService {
   
   @GET
   @Produces({"application/json"})
   public List<Data> getData(){
      List<Data> result = new LinkedList<>();
      result.add(new Data(1,"one"));
      result.add(new Data(2,"two"));
      return result;
   }
   @GET
   @Consumes({"application/json"})
   @Produces({"application/json"})
   @Path("{id}")
   public Data getData(@PathParam("id") String id){
      if ("1".equals(id)){
         return new Data(1,"one");
      }
      if ("2".equals(id)){
         return new Data(2,"two");
      }
      throw new NotFoundException();
   }

}

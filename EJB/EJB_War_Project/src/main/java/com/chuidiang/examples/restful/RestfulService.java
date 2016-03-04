package com.chuidiang.examples.restful;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

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
   
   private static Logger LOG = Logger.getLogger(RestfulService.class.getName());
   
   /**
    * Get a list of Data.
    * In order to test this web service, you only need to write the url 
    * http://yourserver:port/webresources/service 
    * in your web browser.
    */
   @GET
   @Produces({MediaType.APPLICATION_JSON})
   public List<Data> getData(){
      List<Data> result = new LinkedList<>();
      result.add(new Data(1,"one"));
      result.add(new Data(2,"two"));
      return result;
   }
   
   /**
    * Get data with the specified id.
    * In order to test this web service, you only need to write the url 
    * http://yourserver:port/webresources/service/1 or 
    * http://yourserver:port/webresources/service/2
    * in your web browser.
    */
   @GET
   @Produces({MediaType.APPLICATION_JSON})
   @Path("{id}")
   public Data getData(@PathParam("id") int id){
      if (1==id){
         return new Data(1,"one");
      }
      if (2==id){
         return new Data(2,"two");
      }
      throw new WebApplicationException(404);
   }

   /**
    * Save the data received.
    * In order to test this web service, you need some tool that is able 
    * to send POST requests with data. There is a plugin for chrome, called Postman,
    * which can do this job.
    * @param data
    */
   @POST
   @Path("/create")
   @Consumes(MediaType.APPLICATION_JSON)
   public void save(Data data){
      LOG.info(data.toString()+ "created");
   }
   
   /**
    * Save the data in this specific url. 
    * In order to test this web service, you need some tool that is able 
    * to send PUT requests with data. There is a plugin for chrome, called Postman,
    * which can do this job.
    */
   @PUT
   @Path("/{id}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Data update(@PathParam("id") int id, Data data){
      data.setValue(id);
      if (id==1 || id==2){
        LOG.info(data.toString() + " updated"); 
      } else {
         LOG.info(data.toString() + " created");
      }
      return data;
   }
   
   /**
    * Delete the data in this specific url. 
    * In order to test this web service, you need some tool that is able 
    * to send DELETE requests. There is a plugin for chrome, called Postman,
    * which can do this job.
    */
   @DELETE
   @Path("{id}")
   public void remove(@PathParam("id") int id){
      if (id==1 || id==2){
         LOG.info("data removed");
      } else {
         throw new WebApplicationException(404);
      }
   }

}

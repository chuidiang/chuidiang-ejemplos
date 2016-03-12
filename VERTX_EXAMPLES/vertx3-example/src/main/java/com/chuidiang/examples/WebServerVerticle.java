package com.chuidiang.examples;



import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class WebServerVerticle extends AbstractVerticle{
   private Logger LOG = LoggerFactory.getLogger(WebServerVerticle.class);
   
   @Override
   public void start() throws Exception {
      HttpServer server = vertx.createHttpServer();
      
      server.requestHandler(request -> {
         LOG.info("Web request arrived");
         
         
         if (request.path().endsWith("index.html")) {
            request.response().putHeader("content-type", "text/html");
            request.response().sendFile("src/main/webroot/index.html");
         } else {
            request.response().setChunked(true);
            request.response().putHeader("content-type", "text/plain");
            request.response().write("No such file!!");
            request.response().setStatusCode(404);
            request.response().end();
         }
      });
      
      server.listen();
      super.start();
   }
   
   @Override
   public void stop() throws Exception {
      // TODO Auto-generated method stub
      super.stop();
   }
}

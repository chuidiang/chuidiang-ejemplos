package com.chuidiang.examples;

import io.vertx.core.Vertx;

public class VertxMain {

   public static void main(String[] args) {
      Vertx vertx = Vertx.factory.vertx();
      vertx.deployVerticle(new Verticle1());
      vertx.deployVerticle(new Verticle2());
      vertx.deployVerticle(new WebServerVerticle());
   }

}

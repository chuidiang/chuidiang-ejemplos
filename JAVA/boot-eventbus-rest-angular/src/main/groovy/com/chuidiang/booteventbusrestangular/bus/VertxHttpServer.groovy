package com.chuidiang.booteventbusrestangular.bus

import io.vertx.core.AbstractVerticle

import io.vertx.ext.bridge.BridgeEventType
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler
import org.springframework.stereotype.Component

import java.util.function.Function;

/**
 * Created by chuidiang on 25/11/17.
 */
@Component
class VertxHttpServer extends AbstractVerticle {

        @Override
        public void start() throws Exception {

            Router router = Router.router(vertx);

            // Allow outbound traffic to the news-feed address

            BridgeOptions options = new BridgeOptions().addOutboundPermitted(new PermittedOptions().setAddress("prueba"));
            options.addInboundPermitted(new PermittedOptions().setAddress("prueba"));
//            router.route().handler(io.vertx.ext.web.handler.CorsHandler.create("*").allowedMethod(HttpMethod.GET));
//            router.route().handler(io.vertx.ext.web.handler.CorsHandler.create("*").allowedMethod(HttpMethod.POST));
            router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options, {event ->

                // You can also optionally provide a handler like this which will be passed any events that occur on the bridge
                // You can use this for monitoring or logging, or to change the raw messages in-flight.
                // It can also be used for fine grained access control.

                if (event.type() == BridgeEventType.SOCKET_CREATED) {
                    println("A socket was created");
                }

                // This signals that it's ok to process the event
                event.complete(true);

            }));

            vertx.createHttpServer().requestHandler(router.&accept).listen(8081);
        }
    }


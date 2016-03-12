# Extend vert.x image
FROM vertx/vertx3

#                                                       
ENV VERTICLE_NAME com.chuidiang.examples.MainVerticle
ENV VERTICLE_FILE target/vertx3-example-0.0.1-SNAPSHOT.jar

# Set the location of the verticles
ENV VERTICLE_HOME /usr/verticles
ENV CLASSPATH $VERTICLE_HOME/*

EXPOSE 80

# Copy your verticle to the container                   
COPY ./verticles $VERTICLE_HOME/

# Launch the verticle
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["vertx run $VERTICLE_NAME -cluster -cluster-port 4321"]
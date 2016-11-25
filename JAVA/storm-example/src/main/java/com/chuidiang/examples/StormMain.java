package com.chuidiang.examples;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

public class StormMain {

   private static final String TOPOLOGY_NAME = "The Topology";
   private static final String DATA_PRINTER = "Printer";
   private static final String DATA_CALCULATOR = "The Bolt";
   private static final String DATA_GENERATOR = "The Spout";

   public static void main(String[] args) throws InterruptedException, AlreadyAliveException, InvalidTopologyException, AuthorizationException {
      TopologyBuilder builder = new TopologyBuilder();
      builder.setSpout(DATA_GENERATOR, new ASpout());
      builder.setBolt(DATA_CALCULATOR, new ABolt()).shuffleGrouping(DATA_GENERATOR);
      builder.setBolt(DATA_PRINTER, new DataPrinter()).shuffleGrouping(DATA_CALCULATOR).shuffleGrouping(DATA_GENERATOR);

      Config config = new Config();

      LocalCluster cluster = new LocalCluster();
      cluster.submitTopology(TOPOLOGY_NAME, config,
            builder.createTopology());

      Thread.sleep(100000);
      cluster.killTopology(TOPOLOGY_NAME);
      cluster.shutdown();
   }

}

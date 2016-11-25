package com.chuidiang.examples;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABolt extends BaseBasicBolt {

   private static Logger log = LoggerFactory.getLogger(ABolt.class);
   private long lastNow;
   private int sum=0;

   @Override
   public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declare(new Fields("offset","sum"));
      
   }

   @Override
   public void execute(Tuple tuple, BasicOutputCollector collector) {
      long now = tuple.getLong(0);
      long offset = now-lastNow;
      lastNow = now;
      int value = tuple.getInteger(1);
      sum += value;
      collector.emit(new Values(offset,sum));
      log.info("data results emitted");
   }

}

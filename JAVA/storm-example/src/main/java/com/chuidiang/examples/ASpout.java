package com.chuidiang.examples;

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ASpout extends BaseRichSpout {
   private static Logger log = LoggerFactory.getLogger(ASpout.class);
   private SpoutOutputCollector collector;
   private int counter=0;

   @Override
   public void nextTuple() {
      Utils.sleep(1000);
      collector.emit(new Values(System.currentTimeMillis(), counter++));
      log.info("Data emitted");
   }

   @Override
   public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
      this.collector=collector;
      
   }

   @Override
   public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declare(new Fields("now","aNumber"));
   }

}

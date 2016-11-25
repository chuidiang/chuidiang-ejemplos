package com.chuidiang.examples;

import java.text.MessageFormat;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataPrinter extends BaseBasicBolt {
   private static Logger log = LoggerFactory.getLogger(DataPrinter.class);
   @Override
   public void execute(Tuple value, BasicOutputCollector arg1) {
      for (String field:value.getFields()){
         log.info(MessageFormat.format("{0} = {1}",field, value.getValueByField(field).toString()));
      }
      
   }

   @Override
   public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declare(new Fields());
      
   }

}

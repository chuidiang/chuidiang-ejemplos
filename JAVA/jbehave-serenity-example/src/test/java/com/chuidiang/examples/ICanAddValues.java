package com.chuidiang.examples;

import java.util.LinkedList;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

public class ICanAddValues extends JUnitStories{
   // Here we specify the configuration, starting from default MostUsefulConfiguration, and changing only what is needed
   @Override
   public Configuration configuration() {
       return new MostUsefulConfiguration()
           // where to find the stories
           .useStoryLoader(new LoadFromClasspath(this.getClass()))  
           // CONSOLE and TXT reporting
           .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT)); 
   }

   // Here we specify the steps classes
   @Override
   public InjectableStepsFactory stepsFactory() {        
       // varargs, can have more that one steps classes
       return new InstanceStepsFactory(configuration(),new AdderSteps());
   }

   @Override
   protected List<String> storyPaths() {
     List<String> storyPaths = new LinkedList<String>();
     storyPaths.add("stories/ICanAddValues.story");
     return storyPaths;
   }
}

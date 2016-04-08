package com.chuidiang.examples;

import org.hamcrest.MatcherAssert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class AdderSteps {
   private Adder adder;

   private int value1;
   private int value2;

   @Given("I have an Adder")
   public void createAdder() {
      adder = new Adder();
   }

   @When("I add $value1 plus $value2")
   public void storeValues(int value1, int value2) {
      this.value1 = value1;
      this.value2 = value2;
   }

   @Then("the result should be $result")
   public void addValues(int result) {
      MatcherAssert.assertThat("Addition is right",
            adder.add(value1, value2) == result);

   }
}

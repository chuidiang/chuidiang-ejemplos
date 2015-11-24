package com.chuidiang.ejemplos.fuse.controlbus.example;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class RouteTest extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/blueprint.xml";
	}

	@Test
	public void testRoute() throws Exception {
		Thread.sleep(3000);
		ProducerTemplate p = context.createProducerTemplate();
		p.sendBody("controlbus:route?routeId=myRoute&action=suspend",null);
		
		Thread.sleep(3000);
		p.sendBody("controlbus:route?routeId=myRoute&action=resume",null);
		System.in.read();
	}
}

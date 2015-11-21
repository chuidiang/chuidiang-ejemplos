package com.chuidiang.ejemplos.fuse.bin.socket;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class RouteTest extends CamelBlueprintTestSupport {
	
    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml";
    }

	boolean received=false;
	boolean responsed=false;

    @Test
    public void testRoute() throws Exception {
    	received=false;
    	responsed=false;
    	
    	
    	final ServerSocket ss = new ServerSocket(55558);
    	new Thread() {
    		public void run() {
    			try {
					Socket client = ss.accept();
					byte[] buffer = new byte[100];
					int length = client.getInputStream().read(buffer);
					if (length>0){
						received=true;
					}
					System.out.println(Arrays.toString(buffer));
					client.getOutputStream().write("adios\n".getBytes());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
    		}
    	}.start();

    	Socket s = new Socket("localhost",55557);
    	s.getOutputStream().write("hola\n".getBytes());
    	if (s.getInputStream().read(new byte[100])>0){
    		responsed=true;
    	};
    	
    	assertTrue(received);
    	assertTrue(responsed);
    	
    	
    	

        // assert expectations
        assertMockEndpointsSatisfied();
    }

}

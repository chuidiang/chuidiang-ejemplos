import java.io.IOException;
import java.util.Arrays;

/**
 * @author fjabellan
 * @date 20/01/2022
 */
public class TestMain {
	public static void main(String[] args) throws IOException {
		UdpUtil udpUtil = new UdpUtil();

		udpUtil.subscribe( "127.0.0.1", 5557, new IfzMessageListener() {
			@Override
			public void onMessage(byte[] message) {
				System.out.println(Arrays.toString(message));
			}
		});

		for (byte i=0;i<10;i++){
			byte[] messageToSend = new byte[]{i,i,i,i};
			udpUtil.send(messageToSend,messageToSend.length,"127.0.0.1",5557);
		}
	}
}

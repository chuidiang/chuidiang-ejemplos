import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 * @author fjabellan
 * @date 20/01/2022
 */
public class UdpUtil {
	private IfzMessageListener listener;
	private DatagramSocket escucha;

	/** envio por UDP de un array de bytes.
	 *
	 * @param message bytes
	 * @param length  cuantos bytes del message anterior son validos.
	 * @param ip  ip de destino
	 * @param port  puerto de destino.
	 * @throws IOException
	 */
	public void send (byte[] message, int length, String ip, int port) throws IOException {
		DatagramSocket enviador = new DatagramSocket();
		DatagramPacket dgp = new DatagramPacket(message, length, InetAddress.getByName(ip), port);
		enviador.send(dgp);
	}

	public void subscribe(String ip, int port, IfzMessageListener listener){
		this.listener = listener;

		new Thread(()->{
			try {
				escucha = new DatagramSocket(port, InetAddress.getByName(ip));
				byte[] buffer = new byte[1024];
				while (true) {
					DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);
					escucha.receive(dgp);
					buffer = dgp.getData();
					byte[] realData = Arrays.copyOfRange(buffer, 0, dgp.getLength());
					listener.onMessage(realData);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}).start();
	}
}

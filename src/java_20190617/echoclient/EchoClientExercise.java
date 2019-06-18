package java_20190617.echoclient;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientExercise {
	private String ip;
	private int port;
	private Socket socket;
	
	public EchoClientExercise(String ip, int port) {
		this.ip = ip;
		this.port = port;
		
		try {
			socket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoClientExercise("192.0.0.1", 3002);
	}
}

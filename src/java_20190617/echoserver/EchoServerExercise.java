package java_20190617.echoserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerExercise {
	private int port;
	private ServerSocket serverSocket;
	
	public EchoServerExercise(int port) {
		this.port = port;
		try {
			serverSocket = new ServerSocket();
			System.out.println("Ŭ���̾�Ʈ ������ ��ٸ��� �ֽ��ϴ�");
			Socket socket = serverSocket.accept();
			InetAddress ia = socket.getInetAddress();
			String ip = ia.getHostAddress();
			System.out.println(ip+"�����߽��ϴ�");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServerExercise(3000);
	}
	
}

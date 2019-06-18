package java_20190618.UnicastServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UnicastServer {
	private ServerSocket serverSocket;
	public UnicastServer(int port){
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println(port + "��ȣ�� ����ϰ� �ֽ��ϴ�."); //����ڿ��� ������ ���� ���� �˷��ֱ�
			e.printStackTrace();
			System.exit(0);//����α׷� �����ϱ�
		}
		UnicastServerThread ust = null;
		while(true){
			System.out.println("Ŭ���̾�Ʈ ������ ����ϰ� �ֽ��ϴ�.");
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				System.out.println("clent ip : "+socket.getInetAddress().getHostAddress());
				
				ust = new UnicastServerThread(socket);
				Thread t = new Thread(ust);
				t.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new UnicastServer(3002);
		//UnicastServer us = new UnicastServer(3002);
	}
}

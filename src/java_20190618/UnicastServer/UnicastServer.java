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
			System.out.println(port + "번호를 사용하고 있습니다."); //사용자에게 오류에 대한 이유 알려주기
			e.printStackTrace();
			System.exit(0);//운영프로그램 종료하기
		}
		UnicastServerThread ust = null;
		while(true){
			System.out.println("클라이언트 접속을 대기하고 있습니다.");
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

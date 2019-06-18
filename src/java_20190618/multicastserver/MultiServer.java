package java_20190618.multicastserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer {
	private ServerSocket serverSocket;
	private ArrayList<MultiServerThread> list; //ArrayList 추가
	
	public MultiServer(int port){
		list = new ArrayList<MultiServerThread>();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println(port + "번호를 사용하고 있습니다."); //사용자에게 오류에 대한 이유 알려주기
			e.printStackTrace();
			System.exit(0);//운영프로그램 종료하기
		}
		MultiServerThread mst = null;
		
		while(true){
			System.out.println("클라이언트 접속을 대기하고 있습니다.");
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				System.out.println("clent ip : "+socket.getInetAddress().getHostAddress());
				
				mst = new MultiServerThread(socket,list);
				//mst = new MultiServerThread(this); // this는 자기자신과 객체를 포함한다
				//mst = new MultiServerThread(socket); //(socket,list) => 멀티캐스팅으로 나한테만이 아닌 다른 사람들에게도 보낼 수 있도록
				
				list.add(mst);
				
				Thread t = new Thread(mst);
				t.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiServer(3003);
		//UnicastServer us = new UnicastServer(3002);
	}
}

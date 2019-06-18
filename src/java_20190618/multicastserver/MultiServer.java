package java_20190618.multicastserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer {
	private ServerSocket serverSocket;
	private ArrayList<MultiServerThread> list; //ArrayList �߰�
	
	public MultiServer(int port){
		list = new ArrayList<MultiServerThread>();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println(port + "��ȣ�� ����ϰ� �ֽ��ϴ�."); //����ڿ��� ������ ���� ���� �˷��ֱ�
			e.printStackTrace();
			System.exit(0);//����α׷� �����ϱ�
		}
		MultiServerThread mst = null;
		
		while(true){
			System.out.println("Ŭ���̾�Ʈ ������ ����ϰ� �ֽ��ϴ�.");
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				System.out.println("clent ip : "+socket.getInetAddress().getHostAddress());
				
				mst = new MultiServerThread(socket,list);
				//mst = new MultiServerThread(this); // this�� �ڱ��ڽŰ� ��ü�� �����Ѵ�
				//mst = new MultiServerThread(socket); //(socket,list) => ��Ƽĳ�������� �����׸��� �ƴ� �ٸ� ����鿡�Ե� ���� �� �ֵ���
				
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

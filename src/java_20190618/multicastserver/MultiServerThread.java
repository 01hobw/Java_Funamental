package java_20190618.multicastserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServerThread implements Runnable {
	private Socket socket;
	private ArrayList<MultiServerThread> list; //ArrayList �߰�
	private PrintWriter pw;
	public MultiServerThread(Socket socket, ArrayList<MultiServerThread> list){
		this.socket = socket;
		this.list = list;
	}

	public MultiServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean isStop = false;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
					true);

		} catch (IOException e) {
			// TODO: handle exception
		}
		while(!isStop){
			try {
				String readLine = br.readLine();
				// UnicastClient���� pw.println("Exit");�� �´� if����
				if(readLine != null && readLine.equals("Exit")){
					isStop = true;
					sendMessage("Exit");// �ٸ�������� ������ ���� ������ ������!
				}else{
					broadCast(readLine);
					//sendMessage(readLine);//unicast ���
					//pw.println(readLine);//unicast ���
					System.out.println(readLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("������ ������ ������.. ������...");
				//e.printStackTrace();
				System.out.println("������ ������ ������..");
				isStop = true;
				list.remove(this);
				System.out.println("list size : "+list.size());
				
			}
			
		}// �����ʿ��� ���������� ���� br,pw,socket�� �ݱ�!
			try {
				if(br != null) br.close();
				if(pw != null) pw.close();
				if(socket != null) socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void broadCast(String message){
		for(int i=0;i<list.size();i++){
			// ������� ����ŭ �����尡 ���ܳ��⿡ �������� �޼��� ������!
			MultiServerThread mst = list.get(i);
			mst.sendMessage(message);
		}
	}
	
	// Unicast ��� �ش� �޼����� ���� ������Ը� ����
	public void sendMessage(String message){
		pw.println(message); // unicast
	}
		
}

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
	private ArrayList<MultiServerThread> list; //ArrayList 추가
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
				// UnicastClient에서 pw.println("Exit");에 맞는 if구문
				if(readLine != null && readLine.equals("Exit")){
					isStop = true;
					sendMessage("Exit");// 다른사람에게 보내지 말고 나한테 보내기!
				}else{
					broadCast(readLine);
					//sendMessage(readLine);//unicast 방식
					//pw.println(readLine);//unicast 방식
					System.out.println(readLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("강제로 나가지 마세요.. 아직은...");
				//e.printStackTrace();
				System.out.println("강제로 나가지 마세요..");
				isStop = true;
				list.remove(this);
				System.out.println("list size : "+list.size());
				
			}
			
		}// 서버쪽에서 정상적으로 종료 br,pw,socket을 닫기!
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
			// 사용자의 수만큼 스레드가 생겨났기에 각각한테 메세지 보내기!
			MultiServerThread mst = list.get(i);
			mst.sendMessage(message);
		}
	}
	
	// Unicast 방식 해당 메세지를 보낸 사람에게만 전달
	public void sendMessage(String message){
		pw.println(message); // unicast
	}
		
}

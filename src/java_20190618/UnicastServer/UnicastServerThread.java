package java_20190618.UnicastServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class UnicastServerThread implements Runnable {
	private Socket socket;

	public UnicastServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean isStop = false;
		BufferedReader br = null;
		PrintWriter pw = null;
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
				if(readLine != null && readLine.equals("Exit")){
					isStop = true;
				} // UnicastClient���� pw.println("Exit");�� �´� if����
				System.out.println(readLine);
				pw.println(readLine);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("������ ������ ������.. ������...");
				//e.printStackTrace();
				isStop = true;
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
		
}

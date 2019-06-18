package java_20190617.echoserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private int port;
	private ServerSocket serverSocket;
	public EchoServer(int port){
		this.port = port;
		try {
			// pdf.Java11-11 1번 ServerSocket을 생성한다.
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			System.out.println("클라이언트 접속을 대기하고 있습니다.");
			try {
				//accept()메서드는 블락킹 메서드
				//클라이언트가 접속하기 전까지는 수행되지 않는 블락킹 메서드
				// pdf.Java11-11 2번 Client의 접속을 대기한다.(accept)
				Socket socket = serverSocket.accept();
				// pdf.Java11-11 5번 Client와 통신할 수 있는 Socket를 생성  + 7번도 Client와 통신할 수 있는 Socket를 생성
				InetAddress ia = socket.getInetAddress();
				String ip = ia.getHostAddress();
				System.out.println("클라이언트가 접속하였습니다. IP : "+ ip);
				
				// pdf.Java11-11 9번 Client와 통신할 수 있는 in, out 스트림 생성
				// 클라이언트가 쓰고 서버가 읽고 순으로 진행
				InputStream in = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(in);
				BufferedReader br = new BufferedReader(isr);
				String readLine = br.readLine();
				System.out.println(readLine);
				
				
				OutputStream out = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(out);
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(readLine);
				bw.newLine();
				bw.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new EchoServer(3000);
	}
}

package java_20190617.echoclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	private String ip;
	private int port;
	private Socket socket;
	
	public EchoClient(String ip,int port){
		this.ip = ip;
		this.port = port;
		try {
			// pdf.Java11-11 3번 Socket 객체를 생성한다.(port가 동일하니 접속을 가능케함) 4번은 TCP객체와 server가 자동으로 연결됨
			socket = new Socket(ip,port);
			// pdf.Java11-11 7번 서버와 통신할 수 있는 Socket객체 생성
			
			// pdf.Java11-11 8번 서버와 통신할 수 있는 in,out 스트림 생성
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out);
			BufferedWriter bw = new BufferedWriter(osw);
			//개행을 추가해서 보내줍니다 newLine으로 개행 추가하기!
			bw.write("안녕하세요 행복한 월요일입니다.");
			bw.newLine();
			//flush를 하는 이유는 버퍼에 안차서 안보내줌 보내기 위해 flush 사용
			bw.flush();
			
			InputStream in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			String readLine = br.readLine();
			System.out.println(readLine);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// ip주소 뒤에 숫자 3000은 포트 번호이다.
		new EchoClient("192.168.0.159",3000);//192.168.0.149
	}
}

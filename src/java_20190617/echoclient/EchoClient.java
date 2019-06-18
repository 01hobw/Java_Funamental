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
			// pdf.Java11-11 3�� Socket ��ü�� �����Ѵ�.(port�� �����ϴ� ������ ��������) 4���� TCP��ü�� server�� �ڵ����� �����
			socket = new Socket(ip,port);
			// pdf.Java11-11 7�� ������ ����� �� �ִ� Socket��ü ����
			
			// pdf.Java11-11 8�� ������ ����� �� �ִ� in,out ��Ʈ�� ����
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out);
			BufferedWriter bw = new BufferedWriter(osw);
			//������ �߰��ؼ� �����ݴϴ� newLine���� ���� �߰��ϱ�!
			bw.write("�ȳ��ϼ��� �ູ�� �������Դϴ�.");
			bw.newLine();
			//flush�� �ϴ� ������ ���ۿ� ������ �Ⱥ����� ������ ���� flush ���
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
		// ip�ּ� �ڿ� ���� 3000�� ��Ʈ ��ȣ�̴�.
		new EchoClient("192.168.0.159",3000);//192.168.0.149
	}
}

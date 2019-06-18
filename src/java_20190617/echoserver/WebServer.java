package java_20190617.echoserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.activation.MimetypesFileTypeMap;

public class WebServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(80);//8080을 80으로 변경
		try {
			System.out.println("Http Server started at 80 port");
			while (true) {
				Socket socket = serverSocket.accept();
				try {
					System.out.printf("New Client Connect! Connected IP : %s, Port : %d\n", socket.getInetAddress(),
							socket.getPort());

					OutputStream out = socket.getOutputStream();
					InputStream in = socket.getInputStream();
					BufferedReader br = null;
					DataOutputStream dos = null;

					br = new BufferedReader(new InputStreamReader(in));
					dos = new DataOutputStream(out);
					String requestMessageLine = br.readLine();
					// 클라이언트로의 메시지중 첫번째 줄을 읽어들인다.
					// GET /index.html HTTP/1.1
					if (requestMessageLine != null) {

						String[] data = requestMessageLine.split(" ");
						// data[0] = GET, data[1] = index.html
						if (data[0].equals("GET")) {
							String fileName = data[1];
							// 기본적으로 루트(/)로부터 주소가 시작하므로 제거한다.
							if (fileName.startsWith("/") == true) {
								if (fileName.length() > 1) {
									fileName = fileName.substring(1);
								} else {
									fileName = "index.html";
								}
							}

							File file = new File("c:\\down\\webserver", fileName);

							if (file.exists()) {
								// 존재하는 파일의 MIME타입을 분석한다.
								String mimeType = new MimetypesFileTypeMap().getContentType(file);
								// 파일의 바이트수를 찾아온다.
								int numOfBytes = (int) file.length();

								// 파일을 스트림을 읽어들일 준비를 한다.
								FileInputStream fis = new FileInputStream(file);
								byte[] readBytes = new byte[numOfBytes];
								fis.read(readBytes);

								// 정상적으로 처리가 되었음을 나타내는 200 코드를 출력한다.
								dos.writeBytes("HTTP/1.0 200 Document Follows \r\n");
								dos.writeBytes("Content-Type: " + mimeType + ";charset=utf-8" + "\r\n");

								// 출력할 컨텐츠의 길이를 출력
								dos.writeBytes("Content-Length: " + numOfBytes + "\r\n");
								dos.writeBytes("\r\n");

								// 요청 파일을 출력한다.
								dos.write(readBytes, 0, numOfBytes);
							} else {
								// 파일이 존재하지 않는다는 에러인 404 에러를 출력하고 접속을 종료한다.
								System.out.println("Requested File Not Found : " + fileName);
								byte[] body = ("Requested File Not Found : " + fileName).getBytes();
								dos.writeBytes("HTTP/1.0 404 Not Found \r\n");
								dos.writeBytes("Connection: close\r\n");
								dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
								dos.writeBytes("Content-Length: " + body.length + "\r\n");
								dos.writeBytes("\r\n");
								dos.write(body, 0, body.length);
								dos.writeBytes("\r\n");
							}
						}
					} else {
						// 잘못된 요청임을 나타내는 400 에러를 출력하고 접속을 종료한다.
						System.out.println("Bad Request");
						byte[] body = ("Bad Request").getBytes();
						dos.writeBytes("HTTP/1.0 400 Bad Request Message \r\n");
						dos.writeBytes("Connection: close\r\n");
						dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
						dos.writeBytes("Content-Length: " + body.length + "\r\n");
						dos.writeBytes("\r\n");
						dos.write(body, 0, body.length);
						dos.writeBytes("\r\n");

					}
				} finally {
					if (socket != null)
						socket.close();
					System.out.println("Connection Closed");
				}
			}
		} finally {
			if (serverSocket != null)
				serverSocket.close();
		}
	}
}


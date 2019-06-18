package java_20190614;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

public class UrlDemo {
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://sports.news.naver.com/kfootball/news/read.nhn?oid=468&aid=0000520466");
		String protocol = url.getProtocol();
		String host = url.getHost();
		int port = url.getPort(); // 없으면 -1 있으면 있는 번호 나옴
		String path = url.getPath();
		String query = url.getQuery();
		String ref = url.getRef();

		System.out.println("protocol : " + protocol);
		System.out.println("host : " + host);
		System.out.println("port : " + port);
		System.out.println("path : " + path);
		System.out.println("query : " + query);
		System.out.println("ref : " + ref);

		InputStream in = url.openStream();
		// 1바이트에 2바이트 입력 스트림을 해서 읽게끔 만듬
		// "UTF-8"을 넣는 이유는 한글이 깨지기 때문에 넣음
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		String readLine = null;
		while ((readLine = br.readLine()) != null) {
			System.out.println(readLine);
		}

	}
}

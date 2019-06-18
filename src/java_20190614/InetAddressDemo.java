package java_20190614;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress iaddr = InetAddress.getLocalHost();
		System.out.printf("호스트 이름 : %s %n",iaddr.getHostName());
		System.out.printf("호스트 IP : %s %n",iaddr.getHostAddress());
		
		
		InetAddress iaddr1 = InetAddress.getByName("naver.com");
		System.out.printf("호스트 이름 : %s %n",iaddr1.getHostName());
		System.out.printf("호스트 IP : %s %n",iaddr1.getHostAddress());
	}
}

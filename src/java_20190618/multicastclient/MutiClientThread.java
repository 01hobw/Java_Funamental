package java_20190618.multicastclient;

import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JTextArea;

public class MutiClientThread implements Runnable {
	private BufferedReader br;
	private JTextArea jta;

	public MutiClientThread(BufferedReader br, JTextArea jta) {
		this.br = br;
		this.jta = jta;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean isStop = false;
		while (!isStop) {
			String readLine = null;
			try {
				readLine = br.readLine();
				if (readLine != null && readLine.equals("Exit")) {
					isStop = true;
				} else {
					readLine += System.getProperty("line.separator");// 개행하기
					jta.append(readLine);
					jta.setCaretPosition(jta.getDocument().getLength());//Caret 커서, 포지션을 어디다가 넣어줄 것인가 알려준다, //전체문서에 따라 내려온다
				}
			} catch (IOException e) {
				isStop = true;
			}
		}
		System.exit(0);
	}
}

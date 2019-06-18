package java_20190613;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverSportsCrawling {
	public static void main(String[] args) {
		/*
		String url = "https://sports.news.naver.com/kfootball/index.nhn";
		
		//전체 HTML문서를 관리하기 위한 객체
		Document doc = null;
		
		try {
			//url주소에 get 방식으로 접근
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		Elements elements = doc.select(".home_news");
		
		String title = elements.select("h2").text().substring(0,4);
		System.out.println(title);
		
		
		for(Element temp : elements.select("li")){
			System.out.println(temp.text());
		}
		*/
		
		
		String url = "http://www.imbc.com/";
		
		//전체 HTML문서를 관리하기 위한 객체
		Document doc = null;
		
		try {
			//url주소에 get 방식으로 접근
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		Elements elements = doc.select(".con-wrap.notice-wrap");
		
		String title = elements.select("h3").text();
		System.out.println(title);
		
		
		Elements liEle = elements.select("li");
		for(int i=0;i<liEle.size();i++){
			Element temp = liEle.get(i);
			System.out.println(temp.text());
		}
		
		for(Element temp : elements.select("li")){
			System.out.println(temp.text());
		}
		
		
		
		
	}
}







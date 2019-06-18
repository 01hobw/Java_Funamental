package java_20190614;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CoinCrawling_Upgrade {

	private String getKoreanDate(String date){//date => "Jun 13, 2019"
		String koreanDate = null;
		SimpleDateFormat from = new SimpleDateFormat("MMM dd, yyyy",Locale.US);
		SimpleDateFormat to = new SimpleDateFormat("yyyy년 MM월 dd일",Locale.KOREAN);
		try {
			java.util.Date d = from.parse(date); //   원래 Date d이지만 import시 date가 겹쳐서 에러가 나므로 저렇게 사용함
			koreanDate = to.format(d);
		} catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return koreanDate;
	}
	
	public void excute(String sheepName, String coinName, String startDate, String endDate){
		HSSFWorkbook workbook = new HSSFWorkbook(); // 새 엑셀 생성
        HSSFSheet sheet = workbook.createSheet(sheepName); // 새 시트(Sheet) 생성
        HSSFRow row = null;
        HSSFCell cell = null;
        
        String url = "https://coinmarketcap.com/currencies/"+coinName+"/historical-data/?start="+startDate+"&end="+endDate;
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//headElements는 타이틀 정보 가져오기 위한 Elements
		Elements headElements = doc.select(
				".table-responsive .table thead tr");
		//bodyElements는 실데이터 정보를 가져오기 위한 Elements
		Elements bodyElements = doc.select(
				".table-responsive .table tbody tr");
		int rowIndex = 0;
		for (int i = 0; i < headElements.size(); i++) {
			
			row = sheet.createRow(rowIndex++); // 엑셀의 행은 0번부터 시작
			Element e = (Element) headElements.get(i);//<tr>
			String date = e.child(0).text();//e.child(0) => <th>
			cell = row.createCell(0); // 행의 셀은 0번부터 시작
		    cell.setCellValue(getKoreanDate(date)); //생성한 셀에 데이터 삽입
			
			String open = e.child(1).text();
			cell = row.createCell(1); // 행의 셀은 0번부터 시작
		    cell.setCellValue(open); //생성한 셀에 데이터 삽입
			
			
			String high = e.child(2).text();
			cell = row.createCell(2); // 행의 셀은 0번부터 시작
		    cell.setCellValue(high); //생성한 셀에 데이터 삽입
			
			
			String low = e.child(3).text();
			cell = row.createCell(3); // 행의 셀은 0번부터 시작
		    cell.setCellValue(low); //생성한 셀에 데이터 삽입
			
			String close = e.child(4).text();
			cell = row.createCell(4); // 행의 셀은 0번부터 시작
		    cell.setCellValue(close); //생성한 셀에 데이터 삽입
			
			
			String volume = e.child(5).text();
			cell = row.createCell(5); // 행의 셀은 0번부터 시작
		    cell.setCellValue(volume); //생성한 셀에 데이터 삽입
			
			
			String marketCap = e.child(6).text();
			cell = row.createCell(6); // 행의 셀은 0번부터 시작
		    cell.setCellValue(marketCap); //생성한 셀에 데이터 삽입
			
			
			System.out.printf("%s\t\t%s\t%s\t%s\t%s\t%s\t%s%n", 
				date, open, high, low, close, volume, marketCap);

		}
		
		for (int i = 0; i < bodyElements.size(); i++) {
			row = sheet.createRow(rowIndex++); 
			Element e = (Element) bodyElements.get(i);
			String date = e.child(0).text();
			cell = row.createCell(0); 
		    cell.setCellValue(date); 
			
			String open = e.child(1).text();
			cell = row.createCell(1); 
		    cell.setCellValue(Double.parseDouble(open)); // 문자로된 숫자를 숫자로
			
			String high = e.child(2).text();
			cell = row.createCell(2); 
		    cell.setCellValue(Double.parseDouble(high)); 			
			
			String low = e.child(3).text();
			cell = row.createCell(3); 
		    cell.setCellValue(Double.parseDouble(low)); 
			
			String close = e.child(4).text();
			cell = row.createCell(4); 
		    cell.setCellValue(Double.parseDouble(close)); 
			
			String volume = e.child(5).text();
			volume.replaceAll(",", ""); // 중간에 ","를 제거
			cell = row.createCell(5); 
		    cell.setCellValue(Long.parseLong(volume)); // 큰 숫자이기에 long 사용
		   
			String marketCap = e.child(6).text();
			marketCap.replaceAll(",", "");
			cell = row.createCell(6);
		    cell.setCellValue(Long.parseLong(marketCap)); 
			
			System.out.printf("%s\t\t%s\t%s\t%s\t%s\t%s\t%s%n", 
				date, open, high, low, close, volume, marketCap);

		}
		
		try {
            FileOutputStream fileoutputstream = new FileOutputStream(
            		"c:\\down\\"+coinName+".xls");
            workbook.write(fileoutputstream);
            fileoutputstream.close();
            System.out.println("엑셀파일생성성공");
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("엑셀파일생성실패");
        }
	}
	
	public static void main(String[] args) {
		CoinCrawling_Upgrade c = new CoinCrawling_Upgrade();
		c.excute("비트코인", "bitcoin", "20180614", "20190614");
		c.excute("이더리움", "ethereum", "20180614", "20190614");
		c.excute("리플", "ripple", "20180614", "20190614");
	}
}

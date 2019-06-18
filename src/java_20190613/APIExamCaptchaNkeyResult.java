package java_20190613;

//네이버 캡차 API 예제 - 입력값 비교
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIExamCaptchaNkeyResult {

 public static void main(String[] args) {
     String clientId = "dckJwwt62tonM98p0Rs2";//애플리케이션 클라이언트 아이디값";
     String clientSecret = "Yj3ywPVqG2";//애플리케이션 클라이언트 시크릿값";
     try {
         String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
         String key = "ySPhh9q45Z76GWbS"; // 캡차 키 발급시 받은 키값
         String value = "f8va725"; // 사용자가 입력한 캡차 이미지 글자값
         String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code +"&key="+ key + "&value="+ value;

         URL url = new URL(apiURL);
         HttpURLConnection con = (HttpURLConnection)url.openConnection();
         con.setRequestMethod("GET");
         con.setRequestProperty("X-Naver-Client-Id", clientId);
         con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
         int responseCode = con.getResponseCode();
         BufferedReader br;
         if(responseCode==200) { // 정상 호출
             br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         } else {  // 에러 발생
             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
         }
         String inputLine;
         StringBuffer response = new StringBuffer();
         while ((inputLine = br.readLine()) != null) {
             response.append(inputLine);
         }
         br.close();
         System.out.println(response.toString());
     } catch (Exception e) {
         System.out.println(e);
     }
 }
}

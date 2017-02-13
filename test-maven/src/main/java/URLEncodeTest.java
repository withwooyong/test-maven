import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLEncodeTest {

	public static void main(String[] args) {
		
		String encodingString = "[경고] 와우넷파트너스1(C06621) 채널 썸네일 검증 99%";
		String charsetName = "UTF-8";
		//String encodeResult = URLEncoder.encode(String encodingString, String charsetName);		
		try {
			String encodeResult = URLEncoder.encode(encodingString, charsetName);
			System.out.println(encodeResult);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		

	}

}

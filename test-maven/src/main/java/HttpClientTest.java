import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientTest {
	
	
	public static void main(String[] args) throws IOException {
//		HttpClientTest test = new HttpClientTest();
//		test.httpClient();
		
	}
	
	
	private static Map<String, Object> getRadAutoScheduleInfo(String channelCode) throws Exception {
        Map<String, Object> scheduleInfo = new HashMap<>();
        // http://radmanager.tving.com/api/radAutoSchedule.json?apcCd=CB&startFormDate=20151027&startTime=00000000&endFormDate=20151027&endTime=23000000
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apcCd", channelCode));
        params.add(new BasicNameValuePair("startFormDate", "20170202"));
        params.add(new BasicNameValuePair("startTime", "00000000"));
        params.add(new BasicNameValuePair("endFormDate", "20170202"));
        params.add(new BasicNameValuePair("endTime", "23000000"));
        scheduleInfo.put("url", "http://radmanager.tving.com/api/radAutoSchedule.json?");
        scheduleInfo.put("parameter", params);
        return scheduleInfo;
    }
	
    

	public void httpClient() throws ClientProtocolException, IOException {
		String url = "https://selfsolve.apple.com/wcResults.do";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		// add header
		//post.setHeader("User-Agent", CoreProtocolPNames.USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
	}
}

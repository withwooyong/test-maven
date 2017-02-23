package push;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;


import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

import javapns.notification.PushNotificationPayload;

public class pnsTest {
	
	private static Logger log = LoggerFactory.getLogger(pnsTest.class);
	
	private final static String ANDROID_OSCODE = "ANDROID_OS";
    private final static String IOS_OSCODE = "IOS_OS";
    
    private static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * android 테스트 발송
     *
     * @param model
     * @param marketingPush
     * @return
     * @throws Exception 
     */
//	@RequestMapping(value = "/operation/marketingPush/testSend", method = RequestMethod.POST)
//    public String testSend(Model model, @ModelAttribute MarketingPush marketingPush, @ModelAttribute GcmMessage gcmMessage) throws Exception {
	
    public String testSend(Model model, MarketingPush marketingPush, GcmMessage gcmMessage) throws Exception {    
    	log.info("gcmMessage is {}", gcmMessage);
    	Map<String,Object> jsonData = new HashMap<String, Object>();
    	
    	// push os 안드로이드
    	marketingPush.setPush_os(ANDROID_OSCODE);
    	
    	// test ID 처리
    	if (StringUtils.isNotBlank(marketingPush.getPush_target_tester())){
    		List<String> token = null;
    		
    		// 발송 타입이 토큰인 경우
    		if (StringUtils.equals(marketingPush.getPush_target(), "gcm_token")){
    			token = new ArrayList<String>(); 
    			String[] tokenArr = StringUtils.split(marketingPush.getPush_target_tester(), ",");
				for (String t : tokenArr) {
					token.add(t.trim());
				}
    			
    		} else {
    			// 발송 타입이 사용자인 경우
    			List<String> testerArr = new ArrayList<String>();
    			String[] testerIds = StringUtils.split(marketingPush.getPush_target_tester(), ",");
    			for (String testerId : testerIds) {
    				testerArr.add(testerId.trim());
    			}
    			marketingPush.setPush_target_tester_arr(testerArr);
    			/*
    			 * SELECT DISTINCT Y.PNS_TOKEN
		  FROM TSM_USER_DEVICE_OS_TOKEN Y
		 WHERE Y.OS_CD = #{push_os}
		   AND Y.LOGIN_YN = 'Y'
		   AND Y.RECEIVE_EVENT_YN = 'Y'
		   AND Y.CUST_NO IN (SELECT CUST_NO
							   FROM TMB_CUST_MST
							  WHERE <foreach collection="push_target_tester_arr" item="tester_id" separator=" OR " open="(" close=")">
										( MBR_ID = #{tester_id} OR CUST_ID = #{tester_id} )
									</foreach> )
    			 */
    			//token = marketingPushService.getPushTokenListByUserId(marketingPush);
    			//token.add("APA91bFo9HfTTbPdhhmIylUEXy-YavXqOWXeSDlsWllxMV4i3DY2Rd_d4GqGBY_kwo__nkq_FC_FHaZvxaV6KW0Ngv8OQ2A3Bxqu18LhYx5wfIpTWi5e2JhLP7HbC1pzp83qagJIDbTkX22XfVw6BC6wrZBIXmmvtA");
    			token.add("test");
    		}
    		
    		GcmMessageButton button = gcmMessage.getButton().get(0);
    		
    		gcmMessage.setApp_url_scheme(button.getApp_url_scheme());
    		gcmMessage.setAccept_log_url(button.getAccept_log_url());
    		
    		// gcm_message 생성
    		String gcmStr = objectMapper.writeValueAsString(gcmMessage);

    		if (token!=null && !token.isEmpty()){
    			
    			Sender sender = new Sender("AIzaSyA06FeriDY1lSqFsVGZgyY7HN_Ta_LQJMQ"); // 메시지 센더 셋팅.
    			Message message = new Message.Builder().delayWhileIdle(false) 
									    			   .timeToLive(1000) 
									    			   .addData("body", gcmStr)
									    			   .build();
    			
    			MulticastResult result = sender.send(message, token, 3);  // retry : 메시지 전송실패시 재시도 횟수
    			jsonData.put("token_count", token.size());
    			jsonData.put("gcmMessage", gcmStr);
    			jsonData.put("result", result);
    		} else {
    			jsonData.put("token_count", 0);
    			jsonData.put("gcmMessage", gcmStr);
    			jsonData.put("result", null);
    		}
    	}
    	model.addAttribute("JSON_DATA", jsonData);
    	return "jsonViewer";
    }

    /**
     * ios 테스트 발송
     *
     * @param model
     * @param marketingPush
     * @return
     * @throws Exception
     */
    //@RequestMapping(value = "/operation/marketingPush/iosTestSend", method = RequestMethod.POST)
    //public String iosTestSend(Model model, @ModelAttribute MarketingPush marketingPush, @ModelAttribute ApnsMessage apnsMessage) throws Exception {
	public String iosTestSend(Model model, MarketingPush marketingPush, ApnsMessage apnsMessage) throws Exception {

        log.info("apnsMessage is {}", apnsMessage);
        Map<String,Object> jsonData = new HashMap<String, Object>();

        // push os 아이폰
        marketingPush.setPush_os(IOS_OSCODE);

        // test ID 처리
        if (StringUtils.isNotBlank(marketingPush.getPush_target_tester())){
            List<String> token = null;

            // 발송 타입이 토큰인 경우
            if (StringUtils.equals(marketingPush.getPush_target(), "apns_token")){
                token = new ArrayList<String>();
                String[] tokenArr = StringUtils.split(marketingPush.getPush_target_tester(), ",");
                for (String t : tokenArr) {
                    token.add(t.trim());
                }
            } else {
                // 발송 타입이 사용자인 경우
                List<String> testerArr = new ArrayList<String>();
                String[] testerIds = StringUtils.split(marketingPush.getPush_target_tester(), ",");
                for (String testerId : testerIds) {
                    testerArr.add(testerId.trim());
                }
                marketingPush.setPush_target_tester_arr(testerArr);
                //token = marketingPushService.getPushTokenListByUserId(marketingPush);
                //token.add("APA91bFo9HfTTbPdhhmIylUEXy-YavXqOWXeSDlsWllxMV4i3DY2Rd_d4GqGBY_kwo__nkq_FC_FHaZvxaV6KW0Ngv8OQ2A3Bxqu18LhYx5wfIpTWi5e2JhLP7HbC1pzp83qagJIDbTkX22XfVw6BC6wrZBIXmmvtA");
    			token.add("test");
            }

            PushNotificationPayload payload = PushNotificationPayload.complex();
            payload.addAlert(String.valueOf(apnsMessage.getAlert()));
            payload.addCustomDictionary("scheme_url", apnsMessage.getScheme_url());

            ApnsClient apnsClient = new ApnsClient();

            apnsClient.sendPush("", payload, token);
            jsonData.put("token_count", token.size());
        }

        model.addAttribute("JSON_DATA", jsonData);
        return "jsonViewer";
    }
}

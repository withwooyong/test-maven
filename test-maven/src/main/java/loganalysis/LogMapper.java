package loganalysis;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class LogMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
	
	private final static LongWritable dateTimeCount = new LongWritable(1);
	private final static LongWritable requestApiCount = new LongWritable(1);
	private final static LongWritable ApiKeyCount = new LongWritable(1);
	private final static LongWritable dateTimeApiCount = new LongWritable(1);
	private final static LongWritable dateTimeKeyCount = new LongWritable(1);

	private Text dateTimeTxt = new Text();
	private Text requestApiTxt = new Text();
	private Text ApiKeyTxt = new Text();
	private Text dateTimeApiTxt = new Text();
	private Text dateTimeKeyTxt = new Text();

	private final static DateFormat shortFormat = new SimpleDateFormat("yyyyMMddHHmm");
	private final static DateFormat mediumFormat = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss", Locale.US);

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		StringTokenizer matcher = new StringTokenizer(line);

		matcher.nextToken(); // skip ip
		matcher.nextToken(); // skip "-"
		matcher.nextToken(); // skip "-"

		String tempDateTime = matcher.nextToken(" []");
		matcher.nextToken(" ]");
		matcher.nextToken();

		String requestApi = matcher.nextToken("\" ?");
		requestApi = StringUtils.remove(requestApi, "http://api.tving.com");
		if (requestApi.indexOf("/v1/user") != -1 
				|| requestApi.indexOf("/v1/bill/hellovision/close/") != -1
				|| requestApi.indexOf("/v1/bill/purchase/products/down/") != -1
				|| requestApi.indexOf("/v1/bill/coupon/affiliate/count/") != -1
				|| requestApi.indexOf("/v1/community/fan/other") != -1
				|| requestApi.indexOf("/v1/media/live/") != -1 
				|| requestApi.indexOf("/v1/media/schedules") != -1
				|| requestApi.indexOf("/v1/media/channel/") != -1 
				|| requestApi.indexOf("/v1/media/program/") != -1
				|| requestApi.indexOf("/v1/media/episode/") != -1 
				|| requestApi.indexOf("/v1/media/movie/") != -1
				|| requestApi.indexOf("/v1/media/clip/") != -1
				|| requestApi.indexOf("/v1/micro/shopping/schedule/") != -1
				|| requestApi.indexOf("/v1/micro/shopping/vod/") != -1
				|| requestApi.indexOf("/v1/micro/shopping/reserves/delete/") != -1
				|| requestApi.indexOf("/v1/operator/notice/") != -1
				|| requestApi.indexOf("/v1/operator/player/redirect/") != -1
				|| requestApi.indexOf("/v1/operator/theme/") != -1
				|| requestApi.indexOf("/v1/operator/faq/") != -1
				|| requestApi.indexOf("/v1/operator/qnas/") != -1
				|| requestApi.indexOf("/v1/operator/popup/") != -1
				|| requestApi.indexOf("/v1/community/board/") != -1
				|| requestApi.indexOf("/v1/community/comment/like/") != -1
				|| requestApi.indexOf("/v1/community/comment/delete/") != -1
				|| requestApi.indexOf("/v1/community/talk/delete/") != -1
				|| requestApi.indexOf("/v1/community/talk/comment/delete/") != -1
				|| requestApi.indexOf("/v1/community/talks/alert/") != -1
				|| requestApi.indexOf("/v1/community/talk/alert/") != -1
				|| requestApi.indexOf("/v1/community/talk/images/") != -1
				|| requestApi.indexOf("/v1/community/push/notice/") != -1
				|| requestApi.indexOf("/v1/community/push/notices/") != -1
				|| requestApi.indexOf("/v1/community/fan/contents") != -1
				|| requestApi.indexOf("/v1/lol/community/board/") != -1
				|| requestApi.indexOf("/v1/lol/community/comments/delete/") != -1
				|| requestApi.indexOf("/v1/asp/movie/mapping/") != -1
				|| requestApi.indexOf("/v1/media/stream/flash/multiView/") != -1
				|| requestApi.indexOf("/v1/TVINGSHOW") != -1 ) {

			// board 처리
			if (requestApi.indexOf("/v1/lol/community/board/") != -1) {
				requestApi = "/v1/lol/community/board";
			} else if (requestApi.indexOf("/v1/operator/notice/") != -1) {
				requestApi = "/v1/operator/notice";
			} else if (requestApi.indexOf("/v1/operator/theme/") != -1) {
				requestApi = "/v1/operator/theme";
			} else if (requestApi.indexOf("/v1/media/schedules") != -1) {
				requestApi = "/v1/media/schedules";
			} else if (requestApi.indexOf("/v1/community/fan/contents") != -1) {
				requestApi = "/v1/community/fan/contents";
			} else if (requestApi.indexOf("/v1/TVINGSHOW") != -1) {
				requestApi = "/v1/TVINGSHOW";				
			} else {
				requestApi = requestApi.replaceAll("//", "/");
				requestApi = requestApi.substring(0, requestApi.lastIndexOf("/"));
			}
		}

		String convertTime = "";
		String convertTime2 = "";

		try {
			convertTime = shortFormat.format(mediumFormat.parse(tempDateTime));
			convertTime2 = convertTime.substring(0, 11).concat("0");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		dateTimeTxt.set(convertTime);
		context.write(dateTimeTxt, dateTimeCount);

		requestApiTxt.set(requestApi);
		context.write(requestApiTxt, requestApiCount);

		dateTimeApiTxt.set(convertTime2 + "@@" + requestApi);
		context.write(dateTimeApiTxt, dateTimeApiCount);
		// log.debug("requestApi ==> {}", requestApi);
		// log.debug("tempDateTime ==> {}", tempDateTime);
		// log.debug("convertTime ==> {}", convertTime);

		if (requestApi.indexOf(".xml") == -1 && requestApi.indexOf(".txt") == -1) {
			if (requestApi.startsWith("/v1") && requestApi.length() > 10) {
				String requestParams[] = matcher.nextToken().split("&");
				String apiKey = "";

				for (String tempParamData : requestParams) {
					if (tempParamData.toLowerCase().indexOf("apikey=") != -1) {
						String tempApiKey[] = tempParamData.toLowerCase().split("=");
						if (tempApiKey.length > 1) {
							apiKey = tempApiKey[tempApiKey.length - 1];
						}
					}
				}
				ApiKeyTxt.set(apiKey);
				context.write(ApiKeyTxt, ApiKeyCount);
				// log.debug("apiKey ==> {}", apiKey);
				
				dateTimeKeyTxt.set(convertTime + "@@" + apiKey);
				context.write(dateTimeKeyTxt, dateTimeKeyCount);
			}
		}
	}
}

package algorithm.hanbit;

import org.apache.commons.lang.StringUtils;

public class test {

	public static void main(String[] args) {
		String vodFileCode = StringUtils.substringAfterLast("http://vod.cds.tving.com/static/20170206/02237999.DM4.40.mp4", "com");
		String vodFileCode2 = StringUtils.substringAfter("http://vod.cds.tving.com/static/20170206/02237999.DM4.40.mp4", "com");
		System.out.println(vodFileCode);
		System.out.println(vodFileCode2);

	}

}

package rome;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.sun.syndication.io.impl.Base64;

public class RomeLibraryExample {
	public static void main(String[] args) throws Exception {
		
		URL feedUrl = new URL("http://119.149.188.226:8161/admin/queueBrowse/FFMPEG.COMMAND?view=rss&feedType=rss_2.0");
    	HttpURLConnection httpcon = (HttpURLConnection)feedUrl.openConnection();		
		String encoding = Base64.encode("admin:admin");
		httpcon.setRequestProperty  ("Authorization", "Basic " + encoding);
		
		// Reading the feed
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(httpcon));
		
		System.out.println("#####" + feed.getTitle());
		System.out.println("#####" + feed.getLink());
		System.out.println("#####" + feed.getDescription());
		
		List entries = feed.getEntries();
		Iterator itEntries = entries.iterator();
		
		while (itEntries.hasNext()) {
			SyndEntry entry = (SyndEntry) itEntries.next();
			System.out.println("### Title: " + entry.getTitle());
			System.out.println("### Link: " + entry.getLink());
			System.out.println("### Description: " + entry.getDescription().getValue());
			System.out.println("### Publish Date: " + entry.getPublishedDate());
			System.out.println();
		}		
	}
}
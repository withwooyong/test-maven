package rome;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.sun.syndication.io.impl.Base64;

/**
 * It Reads and prints any RSS/Atom feed type.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class FeedReader {
	
	private static final Logger log = LoggerFactory.getLogger(FeedReader.class);

    public static void main(String[] args) {
    	
        try {
        	// http://119.149.188.226:8161/admin/queueBrowse/FFMPEG.COMMAND?view=rss&feedType=atom_1.0
        	// http://119.149.188.226:8161/admin/queueBrowse/FFMPEG.COMMAND?view=rss&feedType=rss_2.0
        	
//        	void set(String protocol, String host, int port,
//                    String authority, String userInfo, String path,
//                    String query, String ref) {
        	
        	URL feedUrl = new URL("http://119.149.188.226:8161/admin/queueBrowse/FFMPEG.COMMAND?view=rss&feedType=rss_2.0");
        	HttpURLConnection httpcon = (HttpURLConnection)feedUrl.openConnection();
			String encoding = Base64.encode("admin:admin");
			
			httpcon.setRequestProperty  ("Authorization", "Basic " + encoding);
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
        } catch (Exception ex) {
            ex.printStackTrace();
            log.debug("ERROR: "+ex.getMessage());
        }        
    }	   
}
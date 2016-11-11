package rome;

import java.util.List;

import com.sun.syndication.feed.module.Module;

public interface ActiveMQModule extends Module {

    public static final String URI = "http://119.149.188.226:8161/admin/queueBrowse/FFMPEG.COMMAND?view=rss&feedType=rss_2.0";

    /**
     * rss 
     * channel
     * 	title
     * 	link
     * 	description
     * 	item
     * 		title
     * 		link
     * 		description
     * 		pubDate
     * 		guid
     * 		dc:date
     */
    public String getTitle();
    public void setTitle(String title);
    
    public String getLink();
    public void setLink(String link);
    
    public String getDescription();
    public void setDescription(String description);

    public List getItems();
    public void setItems(List items);    
}
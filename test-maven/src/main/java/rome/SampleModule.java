package rome;

import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.module.Module;

public interface SampleModule extends Module {

    public static final String URI = "http://rome.dev.java.net/module/sample/1.0";

    public String getBar();
    public void setBar(String bar);

    public List getFoos();
    public void setFoos(List foos);

    public Date getDate();
    public void setDate(Date date);
}
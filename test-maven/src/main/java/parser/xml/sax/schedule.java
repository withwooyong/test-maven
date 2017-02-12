package parser.xml.sax;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class schedule implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String pid;
    private String ptitle;
    private String chid;
    private String chname;
    private String genre1;
    private String genre2;
    private String dirt;
    private String cast;
    private String grade;
    private String summary;
    private String description;
    private String subtitle;
    private String frequency;
    private String sdate;
    private String edate;
    private String audiomode;
    private String rebroad;
    private String hd;
    private String live;
    private String dumda;
    private String dvs;
    private String cc;
    private String d3;
    private String mid;
    private String mepiid;
    private String db_action;
	
	public schedule(Map<String, String> map) {
		this.pid = map.get("pid");
	    this.ptitle = map.get("ptitle");
	    this.chid = map.get("chid");
	    this.chname = map.get("chname");
	    this.genre1 = map.get("genre1");
	    this.genre2 = map.get("genre2");
	    this.dirt = map.get("dirt");
	    this.cast = map.get("cast");
	    this.grade = map.get("grade");
	    this.summary = map.get("summary");
	    this.description = map.get("description");
	    this.subtitle = map.get("subtitle");
	    this.frequency = map.get("frequency");
	    this.sdate = map.get("sdate");
	    this.edate = map.get("edate");
	    this.audiomode = map.get("audiomode");
	    this.rebroad = map.get("rebroad");
	    this.hd = map.get("hd");
	    this.live = map.get("live");
	    this.dumda = map.get("dumda");
	    this.dvs = map.get("dvs");
	    this.cc = map.get("cc");
	    this.d3 = map.get("d3");
	    this.mid = map.get("mid");
	    this.mepiid = map.get("mepiid");
	    this.db_action = map.get("db_action");
	}
}

package parser.xml.sax;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class program implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String mid;
    private String mtitle;
    private String originaltitle;
    private String chid;
    private String chname;
    private String genre1;
    private String genre2;
    private String dirt;
    private String cast;
    private String grade;
    private String summary;
    private String description;
    private String product;
    private String productyear;
    private String country;
	
	public program(Map<String, String> map) {
		this.mid = map.get("mid");
		this.mtitle = map.get("mtitle");
		this.originaltitle = map.get("originaltitle");
		this.chid = map.get("chid");
		this.chname = map.get("chname");
		this.genre1 = map.get("genre1");
		this.genre2 = map.get("genre2");
		this.dirt = map.get("dirt");
		this.cast = map.get("cast");
		this.summary = map.get("summary");
		this.description = map.get("description");
		this.product = map.get("product");
		this.productyear = map.get("productyear");
		this.country = map.get("country");
	}
}

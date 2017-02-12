package parser.xml.sax;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class channel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String chid;
	private String chno;
	private String chname;
	private String chgroup;
	private String chkind;
	private String chadultyn;
	
	public channel(Map<String, String> map) {
		this.chid = map.get("chid");
		this.chno = map.get("chno");
		this.chname = map.get("chname");
		this.chgroup = map.get("chgroup");
		this.chkind = map.get("chkind");
		this.chadultyn = map.get("chadultyn");
	}
}

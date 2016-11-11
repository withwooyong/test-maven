package rome;

import org.jdom.Element;
import org.jdom.Namespace;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleParser;

public class ActiveMQParser implements ModuleParser {
	
	private static final Namespace FFMPEG_NS = Namespace.getNamespace("dc", ActiveMQModule.URI);

	public ActiveMQParser() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNamespaceUri() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Module parse(Element element) {
		// TODO Auto-generated method stub
		return null;
	}
}

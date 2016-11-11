package rome;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom.Element;
import org.jdom.Namespace;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleGenerator;
import com.sun.syndication.io.impl.DateParser;

public class SampleModuleGenerator implements ModuleGenerator {
	
	private static final Namespace SAMPLE_NS = Namespace.getNamespace("sample", SampleModule.URI);
	
	private static final Set NAMESPACES;

	static {
		Set nss = new HashSet();
		nss.add(SAMPLE_NS);
		NAMESPACES = Collections.unmodifiableSet(nss);
	}

	public String getNamespaceUri() {
		return SampleModule.URI;
	}	

	public Set getNamespaces() {
		return NAMESPACES;
	}

	public void generate(Module module, Element element) {

		// this is not necessary, it is done to avoid the namespace definition in every item.
		Element root = element;

		
		while (root.getParent() != null && root.getParent() instanceof Element) {
			root = (Element) element.getParent();
		}
		root.addNamespaceDeclaration(SAMPLE_NS);

		SampleModule fm = (SampleModule) module;
		if (fm.getBar() != null) {
			element.addContent(generateSimpleElement("bar", fm.getBar()));
		}
		List foos = fm.getFoos();
		for (int i = 0; i < foos.size(); i++) {
			element.addContent(generateSimpleElement("foo", foos.get(i).toString()));
		}
		if (fm.getDate() != null) {
			element.addContent(generateSimpleElement("date", DateParser.formatW3CDateTime(fm.getDate())));
		}
	}

	protected Element generateSimpleElement(String name, String value) {
		Element element = new Element(name, SAMPLE_NS);
		element.addContent(value);
		return element;
	}
}
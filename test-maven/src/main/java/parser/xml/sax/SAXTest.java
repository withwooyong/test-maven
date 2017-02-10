package parser.xml.sax;

import java.io.FileReader;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class SAXTest {

	private static Logger log = LoggerFactory.getLogger(SAXTest.class);

	public static void main(String[] args) {
		saxTest();

	}

	// http://stackoverflow.com/questions/9705244/parse-xml-in-java-when-there-are-duplicate-tags
	private static void saxTest() {
		try {
			SAXBuilder builder = new SAXBuilder();
			// url에 xml이 있는경우
			// Document jdomdoc = builder.build(new java.net.URL("http://www.xxx.com"));

			// 파일로 사용할경우
			Document jdomdoc = builder.build(new InputSource(new FileReader("C:\\Users\\user\\git\\test-maven\\test-maven\\src\\main\\java\\parser\\channel_20170210002001.xml")));
			Element root = jdomdoc.getRootElement(); 
			log.debug("{}", root.getName()); // dataList
			
			List<Element> dataList = root.getChildren();
			log.debug("{}", dataList.size());
			for (Element element : dataList) {
				List<Attribute> attrlist = element.getChild("table").getChild("field").getAttributes();
				
				for (Attribute attribute : attrlist) {
					log.debug("{}", attribute.getName());
				}
				
				/*
				 * 
<DATA>
<TABLEDATA name="web_order_header" rows="1">
<ROW>
<FIELD name="order_id"> 40403141201067683</FIELD>
<FIELD name="order_date"> Mar 14 , 2012</FIELD>
				 */
				
//				List<Element> fieldList = element.getChild("table").getChildren();
//				log.debug("{}", fieldList.size());
//				for (Element element2 : fieldList) {
//					log.debug("{}", element2.getAttribute("korName").getValue());
//				}
			} 

//			Element desc_E = root.getChild("data");
//			String description = desc_E.getValue();
//
//			System.out.println("description......" + description);
//
//			Element peresons_E1 = root.getChild("persons");
//
//			List<Element> list = peresons_E1.getChildren("person");
//
//			for (int i = 0; i < list.size(); i++) {
//				Element person_E = list.get(i);
//				System.out.println("ssn................" + person_E.getAttributeValue("ssn"));
//				System.out.println("firstName................" + person_E.getChild("firstName").getValue());
//				System.out.println("lastName................" + person_E.getChild("lastName").getValue());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

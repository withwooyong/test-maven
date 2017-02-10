package parser.xml.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jdom.Element;

/*
 * XML 파싱 방법으로는 DOM (Document Object Model) 방식과 SAX (Simple API for XML) 방식이 있다.
 * 차이점은 DOM방식은 메모리에 모두 로드 후 파싱하고 SAX는 순차적으로 읽어가며 파싱하는 것이다.
 * 경우에 따라서 적절한 방법을 선택하여 사용하면 된다.
 * 
 * - DOM방식
 * 1. 처음 XML문서를 메모리에 모두 로드한 후 값을 읽는다.
 * 2. XML문서가 메모리에 모두 로드되어있기 때문에 노드의 검색, 수정, 구조변경등이 빠르고 용이하다.
 * 3. 직관적이고 SAX보다 파싱하기 단순하다.
 * 
 * *접근방법
 * File file = new File("test.xml");
 * DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
 * DocumentBuilder db = dbf.newDocumentBuilder();
 * Document doc = db.parse(file);
 * 
 * http://humble.tistory.com/23
 */
public class DOMTest {

	private static Logger log = LoggerFactory.getLogger(DOMTest.class);
	/*
	 * <data>
        <table korName="epg_channel">
            <field korName="chid"><![CDATA[19]]></field>
            <field korName="chno"><![CDATA[904]]></field>
            <field korName="chname"><![CDATA[채널CGV]]></field>
            <field korName="chgroup"><![CDATA[100]]></field>
            <field korName="chkind"><![CDATA[케이블]]></field>
            <field korName="chadultyn"><![CDATA[N]]></field>
        </table></data>
        
        <staff id="1001"> 
        	<firstname>yong</firstname> 
        	<lastname>mook kim</lastname> 
        	<nickname>mkyong</nickname> 
        	<salary>100000</salary> 
        </staff>
        
        <?xml version="1.0" encoding="UTF-8"?>
<DATA>
<TABLEDATA name="web_order_header" rows="1">
	<ROW>
		<FIELD name="order_id"> 40403141201067683</FIELD>
		<FIELD name="order_date"> Mar 14 , 2012</FIELD>
	</ROW>
</TABLEDATA>
	 */
	public static void main(String[] args) {
		parseTest1();
	}
	
	private static void parseTest() {
		try {
			File fXmlFile = new File("C:\\Users\\user\\git\\test-maven\\test-maven\\src\\main\\java\\parser\\channel_20170210002001.xml"); 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(fXmlFile); 
			//optional, but recommended 
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work 
			doc.getDocumentElement().normalize(); 
			log.debug("Root element :{}", doc.getDocumentElement().getNodeName());
			
			NodeList nList = doc.getElementsByTagName("table");
			log.debug("{}", nList.item(0).getChildNodes().item(0).getNodeName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void parseTest1() {
		try {
			File fXmlFile = new File("C:\\Users\\user\\git\\test-maven\\test-maven\\src\\main\\java\\parser\\channel_20170210002001.xml"); 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(fXmlFile); 
			//optional, but recommended 
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work 
			doc.getDocumentElement().normalize(); 
			log.debug("Root element :{}", doc.getDocumentElement().getNodeName()); 
			
			NodeList nList = doc.getElementsByTagName("table");
			log.debug("---------------------------- {} ", nList.getLength()); 
			
			for (int temp = 0; temp < nList.getLength(); temp++) { 
				Node nNode = nList.item(temp);
				 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
					Element eElement = (Element) nNode; 
					log.debug("Staff id : {}", eElement.getAttribute("chid")); 
					log.debug("First Name : {}", eElement.getElementsByTagName("chid").item(0).getTextContent()); 
				}
				log.debug("-------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

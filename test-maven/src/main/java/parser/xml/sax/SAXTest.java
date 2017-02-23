package parser.xml.sax;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class SAXTest {

	private static Logger log = LoggerFactory.getLogger(SAXTest.class);

	public static void main(String[] args) {
		saxChannel();
		saxProgram();
		saxSchedule();
		
	}

	// http://stackoverflow.com/questions/9705244/parse-xml-in-java-when-there-are-duplicate-tags
	private static void saxChannel() {
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			// url에 xml이 있는경우
			// Document jdomdoc = builder.build(new java.net.URL("http://www.xxx.com"));

			String path = SAXTest.class.getResource("").getPath();
			// 파일로 사용할경우
			Document jdomdoc = saxBuilder.build(new InputSource(new FileReader(path + "/channel.xml")));
			Element root = jdomdoc.getRootElement(); 
			log.debug("{}", root.getName()); // dataList
			
			List<Element> dataList = root.getChildren();
			List<channel> channelList = new ArrayList<>();
			log.debug("dataList={}", dataList.size());
			for (int i = 0; i < dataList.size(); i++) {
				List<Element> fieldList = root.getChild("data").getChild("table").getChildren("field"); 
				log.debug("fieldList={}", fieldList.size());
				 
				Map<String, String> map = new HashMap<>();
				for (Element element : fieldList) {
					map.put(element.getAttributeValue("korName"), element.getText());
				}
				channelList.add(new channel(map));
			}
			log.debug("{}", channelList.get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void saxProgram() {
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			// url에 xml이 있는경우
			// Document jdomdoc = builder.build(new java.net.URL("http://www.xxx.com"));

			String path = SAXTest.class.getResource("").getPath();
			// 파일로 사용할경우
			Document jdomdoc = saxBuilder.build(new InputSource(new FileReader(path + "/program_master.xml")));
			Element root = jdomdoc.getRootElement(); 
			log.debug("{}", root.getName()); // dataList
			
			List<Element> dataList = root.getChildren();
			List<program> programList = new ArrayList<>();
			log.debug("dataList={}", dataList.size());
			for (int i = 0; i < dataList.size(); i++) {
				List<Element> fieldList = root.getChild("data").getChild("table").getChildren("field"); 
				log.debug("fieldList={}", fieldList.size());
				 
				Map<String, String> map = new HashMap<>();
				for (Element element : fieldList) {
					map.put(element.getAttributeValue("korName"), element.getText());
				}
				programList.add(new program(map));
			}
			log.debug("{}", programList.get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void saxSchedule() {
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			// url에 xml이 있는경우
			// Document jdomdoc = builder.build(new java.net.URL("http://www.xxx.com"));

			String path = SAXTest.class.getResource("").getPath();
			// 파일로 사용할경우
			Document jdomdoc = saxBuilder.build(new InputSource(new FileReader(path + "/program_schedule_9.xml")));
			Element root = jdomdoc.getRootElement(); 
			log.debug("{}", root.getName()); // dataList
			
			List<Element> dataList = root.getChildren();
			List<schedule> scheduleList = new ArrayList<>();
			log.debug("dataList={}", dataList.size());
			for (int i = 0; i < dataList.size(); i++) {
				List<Element> fieldList = root.getChild("data").getChild("table").getChildren("field"); 
				log.debug("fieldList={}", fieldList.size());
				 
				Map<String, String> map = new HashMap<>();
				for (Element element : fieldList) {
					map.put(element.getAttributeValue("korName"), element.getText());
				}
				scheduleList.add(new schedule(map));
			}
			log.debug("{}", scheduleList.get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

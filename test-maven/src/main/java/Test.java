import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

public class Test {
	
	protected static Logger log = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) throws IOException {
		//BufferedReadWrite();
		
		StringBuffer buf = new StringBuffer();
		//buf.append("4.49");
		//System.out.println(Optional.fromNullable(buf.toString()).or("0.0").toString());
		String ntzScore = Optional.fromNullable(buf.toString()).or("0.0");
		System.out.println(ntzScore);
		
		List<String> testList = new ArrayList<>();
		testList.add("test");
		testList.add("test1");
		System.out.println(testList.get(1));
		
		double dTest = 77.852;
		log.info("{}", dTest);
		
		//Strings.isNullOrEmpty(string)
	}

	public static void BufferedReadWrite() throws IOException {
		BufferedReader br1 = null;
		BufferedReader br2 = null;		
		BufferedWriter writer = null;
		try {
			br1 = new BufferedReader(new FileReader("C:/Users/User/Documents/업무/작업/compare1.txt"));
			br2 = new BufferedReader(new FileReader("C:/Users/User/Documents/업무/작업/dynamic1.txt"));
			writer = new BufferedWriter(new FileWriter("C:/Users/User/Documents/업무/작업/compare2.txt"));
			
//			HashMap<Integer, String> map1 = new HashMap<Integer, String>();
//			HashMap<Integer, String> map2 = new HashMap<Integer, String>();
			ArrayList<String> list1 = new ArrayList<String>();
			ArrayList<String> list2 = new ArrayList<String>();
			
						
			String line1 = br1.readLine();
			//int i = 0;
			while (line1 != null) {
				//map1.put(i++, line1);
				list1.add(line1);
				line1 = br1.readLine();
			}
			
			//System.out.println("map1.size() : " + map1.size());
			System.out.println("list1.size() : " + list1.size());
			String line2 = br2.readLine();
			//int j = 0;
			while (line2 != null) {
				//map2.put(j++, line2);
				list2.add(line2);
				line2 = br2.readLine();
			}
			//System.out.println("map2.size() : " + map2.size());
			System.out.println("list2.size() : " + list2.size());
			
			for (int j2 = 0; j2 < list1.size(); j2++) {
				String temp1 = list1.get(j2);
				boolean b = true;
				for (int k = 0; k < list2.size(); k++) {
					String temp2 = list2.get(k);
					if (temp2.contains(temp1)) {
						list2.remove(temp2);
						System.out.println(list2.size());
						b = false;
						break;
					}
				}
				
				if (b) {
					System.out.println(temp1);
					writer.write(temp1+"\n");
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			br1.close();
//			br2.close();
//			writer.close();
		}
		System.out.println("END");
	}
}

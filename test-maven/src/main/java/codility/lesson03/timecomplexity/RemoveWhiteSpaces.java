package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveWhiteSpaces {
	
	private static Logger log = LoggerFactory.getLogger(RemoveWhiteSpaces.class);

	public static void main(String[] args) {
		String str = "Core Java jsp servlets jdbc struts hibernate spring";

		// 1. Using replaceAll() Method
		String strWithoutSpace = str.replaceAll("\\s", "");
		log.debug("{}", strWithoutSpace); 

		// 2. Without Using replaceAll() Method
		char[] strArray = str.toCharArray();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < strArray.length; i++) {
			if ((strArray[i] != ' ') && (strArray[i] != '\t')) {
				sb.append(strArray[i]);
			}
		}
		log.debug("{}", sb); 
	}
}

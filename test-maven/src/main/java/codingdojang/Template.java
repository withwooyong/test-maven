package codingdojang;

import java.util.ArrayList;
import java.util.List;

public class Template {
	
	public static void main(String[] args) {
		
		List<Integer> numbersHasGenerator = new ArrayList<>();
		
		for (int i = 0; i <= 5000; i++) {
			String strNum = String.valueOf(i);
			int no = 0;
			
			for (int j = 0; j < strNum.length(); j++) {
				no += Integer.parseInt(strNum.substring(j, j + 1));
				System.out.println("no=" + no);
			}
			numbersHasGenerator.add(no + i);
		}
	}
	
	

}

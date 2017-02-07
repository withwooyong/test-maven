package programcreek;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/*
 * Given two strings s and t, determine if they are isomorphic. 
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 */
public class leetcode_isomorphic_strings {

	public static void main(String[] args) {
		String s = "egg";
		String t = "add";
		isIsomorphic(s, t);
	}
	
	public static boolean isIsomorphic(String s, String t) {
	    if(s==null||t==null)
	        return false;
	 
	    if(s.length()!=t.length())
	        return false;
	 
	    HashMap<Character, Character> map = new HashMap<Character, Character>();
	 
	 
	    for(int i=0; i<s.length(); i++){
	        char c1 = s.charAt(i);
	        char c2 = t.charAt(i);
	 
	        if(map.containsKey(c1)){
	            if(map.get(c1)!=c2)// if not consistant with previous ones
	                return false;
	        }else{
	            if(map.containsValue(c2)) //if c2 is already being mapped
	                return false;
	            map.put(c1, c2);
	        }
	    }
	 
	    return true;
	}
	
	public static void test() {

        System.out.println(" #### yyyymmdd 폴더생성 시작 ####  ");
        Calendar today=  new GregorianCalendar();

        today.add(Calendar.DATE, 1);
         
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 원하는 형식으로 만든다.
        String date = sdf.format(today.getTime());

        String mkDir = "/SANIQUE/dynamic/"+date;

        System.out.println("#### yyyymmdd 폴더생성  :"+ mkDir);
	}
}

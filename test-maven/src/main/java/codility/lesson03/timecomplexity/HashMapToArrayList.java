package codility.lesson03.timecomplexity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapToArrayList {

	public static void main(String[] args) {
		
		
	}
	
	// a) Conversion Of HashMap Keys Into ArrayList :
	private static void solution() {
		//Creating a HashMap object        
		HashMap<String, String> map = new HashMap<String, String>();		 
		//Getting Set of keys from HashMap		         
		Set<String> keySet = map.keySet();		         
		//Creating an ArrayList of keys by passing the keySet		         
		ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
	}
	
	// b) Conversion Of HashMap Values Into ArrayList :
	private static void solution2() {
		//Creating a HashMap object        
		HashMap<String, String> map = new HashMap<String, String>();		 
		//Getting Collection of values from HashMap		         
		Collection<String> values = map.values();		         
		//Creating an ArrayList of values		         
		ArrayList<String> listOfValues = new ArrayList<String>(values);
	}
	
	// c) Conversion Of HashMap’s Key-Value Pairs Into ArrayList :
	private static void solution3() {
		//Creating a HashMap object        
		HashMap<String, String> map = new HashMap<String, String>();		 
		//Getting the Set of entries		         
		Set<Entry<String, String>> entrySet = map.entrySet();		         
		//Creating an ArrayList Of Entry objects		         
		ArrayList<Entry<String, String>> listOfEntry = new ArrayList<Entry<String,String>>(entrySet);
	}
}

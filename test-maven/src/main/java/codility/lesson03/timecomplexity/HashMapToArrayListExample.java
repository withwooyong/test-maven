package codility.lesson03.timecomplexity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashMapToArrayListExample {
	
	private static Logger log = LoggerFactory.getLogger(HashMapToArrayListExample.class);

	public static void main(String[] args) {
		// Creating a HashMap object
		HashMap<String, String> studentPerformanceMap = new HashMap<String, String>();
		// Adding elements to HashMap
		studentPerformanceMap.put("John Kevin", "Average");
		studentPerformanceMap.put("Rakesh Sharma", "Good");
		studentPerformanceMap.put("Prachi D", "Very Good");
		studentPerformanceMap.put("Ivan Jose", "Very Bad");
		studentPerformanceMap.put("Smith Jacob", "Very Good");
		studentPerformanceMap.put("Anjali N", "Bad");
		// Getting Set of keys
		Set<String> keySet = studentPerformanceMap.keySet();
		// Creating an ArrayList of keys
		ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
		log.debug("{}", "ArrayList Of Keys :");
		for (String key : listOfKeys) {
			log.debug("{}", key);
		}
		log.debug("{}", "--------------------------");
		// Getting Collection of values
		Collection<String> values = studentPerformanceMap.values();
		// Creating an ArrayList of values
		ArrayList<String> listOfValues = new ArrayList<String>(values);
		log.debug("{}", "ArrayList Of Values :");
		for (String value : listOfValues) {
			log.debug("{}", value);
		}
		log.debug("{}", "--------------------------");
		// Getting the Set of entries
		Set<Entry<String, String>> entrySet = studentPerformanceMap.entrySet();
		// Creating an ArrayList Of Entry objects
		ArrayList<Entry<String, String>> listOfEntry = new ArrayList<Entry<String, String>>(entrySet);
		log.debug("{}", "ArrayList of Key-Values :");
		for (Entry<String, String> entry : listOfEntry) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
	}
}

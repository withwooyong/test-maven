package codility.lesson03.timecomplexity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashMapExample {

	private static Logger log = LoggerFactory.getLogger(HashMapExample.class);

	public static void main(String[] args) {
		solution();
		solution1();
		solution2();
		solution3();
		solution4();
		solution5();
		solution6();
		solution7();
		solution8();
		solution9();
		solution10();
		solution11();
		solution12();
		solution13();
		solution14();
		solution15();
		
	}

	private static void solution() {
		// Defining the HashMap
		HashMap<String, Double> map = new HashMap<String, Double>();
		// Adding some elements to HashMap
		map.put("Ashwin", 87.55);
		map.put("Bharat", 95.65);
		map.put("Chetan", 68.13);
		map.put("Dhanjay", 74.23);
		map.put("Kartik", 65.42);
		// HashMap can have one null key and multiple null values
		map.put(null, null);
		map.put("Sandesh", null);
		// Getting the size of the map
		log.debug("{}", "Size Of The Map : " + map.size());
		log.debug("{}", "-----------------");
		// Displaying the elements
		log.debug("{}", "The elements are :");
		Set set = map.keySet();
		Iterator keySetIterator = set.iterator();
		while (keySetIterator.hasNext()) {
			Object key = keySetIterator.next();
			log.debug("{}", key + "  : " + map.get(key));
		}
		log.debug("{}", "-----------------");
		// Checking the map for a particular key/value
		log.debug("{}", "Does this map has Chetan as key? " + map.containsKey("Chetan"));
		log.debug("{}", "Does this map has 74.23 as value? " + map.containsValue(74.23));
		log.debug("{}", "-----------------");
		// Removing an element from the map
		log.debug("{}", "Value removed from the map : " + map.remove("Kartik"));
	}

	// 1) Explain the different ways of creating HashMap in java?
	private static void solution1() {
		// 1. Creating HashMap with default initial capacity and load factor
		HashMap<String, Integer> map1 = new HashMap<String, Integer>();
		// 2. Creating HashMap with 30 as initial capacity
		HashMap<String, Integer> map2 = new HashMap<String, Integer>(30);
		// 3. Creating HashMap with 30 as initial capacity and 0.5 as load factor
		HashMap<String, Integer> map3 = new HashMap<String, Integer>(30, 0.5f);
		// 4. Creating HashMap by copying another HashMap
		HashMap<String, Integer> map4 = new HashMap<String, Integer>(map1);
	}

	// 2) How do you add key-value pairs to HashMap?
	private static void solution2() {
		// Creating HashMap with default initial capacity and load factor
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// Inserting key-value pairs to map using put() method
		map.put("ONE", 1);
		map.put("TWO", 2);
		map.put("THREE", 3);
		map.put("FOUR", 4);
		map.put("FIVE", 5);
		// Printing key-value pairs
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
		log.debug("{}", "-------------------------");
		// Creating another HashMap
		HashMap<String, Integer> anotherMap = new HashMap<String, Integer>();
		// Inserting key-value pairs to anotherMap using put() method
		anotherMap.put("SIX", 6);
		anotherMap.put("SEVEN", 7);
		// Inserting key-value pairs of map to anotherMap using putAll() method
		anotherMap.putAll(map);
		// Printing key-value pairs of anotherMap
		entrySet = anotherMap.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
	}

	// 3) How do you add given key-value pair to HashMap if and only if it is not present in the HashMap?
	private static void solution3() {
		// Creating HashMap with default initial capacity and load factor
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// Adding key-value pairs
		map.put("ONE", 1);
		map.put("TWO", 2);
		map.put("THREE", 3);
		map.put("FOUR", 4);
		// Adds key-value pair 'ONE-111' only if it is not present in map
		map.putIfAbsent("ONE", 111);
		// Adds key-value pair 'FIVE-5' only if it is not present in map
		map.putIfAbsent("FIVE", 5);
		// Printing key-value pairs of map
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
	}

	// 4) How do you retrieve a value associated with a given key from the HashMap?
	private static void solution4() {
		// Creating HashMap with default initial capacity and load factor
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// Adding key-value pairs to HashMap
		map.put("ONE", 1);
		map.put("TWO", 2);
		map.put("THREE", 3);
		map.put("FOUR", 4);
		// Retrieving a value associated with key 'TWO'
		int value = map.get("TWO");
		log.debug("{}", value); // Output : 2
	}

	// 5) How do you check whether a particular key/value exist in a HashMap?
	private static void solution5() {
		// Creating the HashMap
		HashMap<Integer, Double> map = new HashMap<Integer, Double>();
		// Adding key-value pairs to HashMap
		map.put(1, 1.1);
		map.put(2, 2.2);
		map.put(3, 3.3);
		map.put(4, 4.4);
		// Checking whether key '3' exist in map
		log.debug("{}", map.containsKey(3)); // Output : true
		// Checking whether value '3.3' exist in map
		log.debug("{}", map.containsValue(3.3)); // Output : true
	}

	// 6) How do you find out the number of key-value mappings present in a HashMap?
	private static void solution6() {
		// Creating the HashMap
		HashMap<Integer, Double> map = new HashMap<Integer, Double>();
		// Adding key-value pairs to HashMap
		map.put(111, 111.111);
		map.put(222, 222.222);
		map.put(333, 333.333);
		map.put(444, 444.444);
		map.put(555, 555.555);
		// Retrieving the number of key-value pairs present in map
		log.debug("{}", map.size()); // Output : 5
	}

	// 7) How do you remove all key-value pairs from a HashMap? OR How do you clear the HashMap for reuse?
	private static void solution7() {
		// Creating the HashMap
		HashMap<Integer, Double> map = new HashMap<Integer, Double>();
		// Adding key-value pairs to HashMap
		map.put(111, 111.111);
		map.put(222, 222.222);
		map.put(333, 333.333);
		map.put(444, 444.444);
		map.put(555, 555.555);
		// Retrieving the number of key-value pairs
		log.debug("{}", map.size()); // Output : 5
		// Clearing the map
		map.clear();
		// Checking the number of key-value pairs after clearing the map
		log.debug("{}", map.size()); // Output : 0
	}

	// 8) How do you retrieve all keys present in a HashMap?
	private static void solution8() {
		// Creating the HashMap
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		// Adding key-value pairs to HashMap
		map.put(1, "AAA");
		map.put(2, "BBB");
		map.put(3, "CCC");
		map.put(4, "DDD");
		map.put(5, "EEE");
		// Retrieving the Key Set
		Set<Integer> keySet = map.keySet();
		for (Integer key : keySet) {
			log.debug("{}", key);
		}
	}

	// 9) How do you retrieve all the values present in a HashMap?
	private static void solution9() {
		// Creating the HashMap
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		// Adding key-value pairs to HashMap
		map.put(1, "AAA");
		map.put(2, "BBB");
		map.put(3, "CCC");
		map.put(4, "DDD");
		map.put(5, "EEE");
		// Retrieving the Collection view of values present in map
		Collection<String> values = map.values();
		for (String value : values) {
			log.debug("{}", value);
		}
	}

	// 10) How do you retrieve all key-value pairs present in a HashMap?
	private static void solution10() {
		// Creating the HashMap
		HashMap<String, String> map = new HashMap<String, String>();
		// Adding key-value pairs to HashMap
		map.put("ONE", "AAA");
		map.put("TWO", "BBB");
		map.put("THREE", "CCC");
		map.put("FOUR", "DDD");
		map.put("FIVE", "EEE");
		// Retrieving the Set consists of all key-value pairs in map
		Set<Entry<String, String>> keyValueSet = map.entrySet();
		for (Entry<String, String> entry : keyValueSet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
	}

	// 11) How do you remove a key-value pair from the HashMap?
	private static void solution11() {
		// Creating the HashMap
		HashMap<String, String> map = new HashMap<String, String>();
		// Adding key-value pairs to HashMap
		map.put("ONE", "AAA");
		map.put("TWO", "BBB");
		map.put("THREE", "CCC");
		map.put("FOUR", "DDD");
		map.put("FIVE", "EEE");
		// Printing key-value pairs
		log.debug("{}", "HashMap Before Remove :");
		Set<Entry<String, String>> keyValueSet = map.entrySet();
		for (Entry<String, String> entry : keyValueSet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
		log.debug("{}", "---------------------");
		// Removing the mapping for the key 'ONE'
		map.remove("ONE");
		log.debug("{}", "HashMap After Remove :");
		for (Entry<String, String> entry : keyValueSet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
	}

	// 12) How do you remove a key-value pair from a HashMap if and only if the specified key is currently mapped to
	// given value?
	private static void solution12() {
		// Creating the HashMap
		HashMap<String, String> map = new HashMap<String, String>();
		// Adding key-value pairs to HashMap
		map.put("ONE", "AAA");
		map.put("TWO", "BBB");
		map.put("THREE", "CCC");
		map.put("FOUR", "DDD");
		map.put("FIVE", "EEE");
		// Printing Key-value pairs
		log.debug("{}", "HashMap Before Remove :");
		Set<Entry<String, String>> keyValueSet = map.entrySet();
		for (Entry<String, String> entry : keyValueSet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
		log.debug("{}", "------------------");
		// Removes the mapping for the key 'ONE' only if it is currently mapped to 'CCC'
		map.remove("ONE", "CCC");
		// Removes the mapping for the key 'FIVE' only if it is currently mapped to 'EEE'
		map.remove("FIVE", "EEE");
		log.debug("{}", "HashMap After Remove :");
		for (Entry<String, String> entry : keyValueSet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
	}

	// 13) How do you replace a value associated with a given key in the HashMap?
	private static void solution13() {
		// Creating the HashMap
		HashMap<String, String> map = new HashMap<String, String>();
		// Adding key-value pairs to HashMap
		map.put("ONE", "AAA");
		map.put("TWO", "BBB");
		map.put("THREE", "CCC");
		map.put("FOUR", "DDD");
		map.put("FIVE", "EEE");
		// Printing Key-value pairs
		log.debug("{}", "HashMap Before Replace :");
		Set<Entry<String, String>> keyValueSet = map.entrySet();
		for (Entry<String, String> entry : keyValueSet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
		log.debug("{}", "------------------");
		// Replacing the value associated with 'THREE' to '333'
		map.replace("THREE", "333");
		log.debug("{}", "HashMap After Replace :");
		for (Entry<String, String> entry : keyValueSet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
	}

	// 14) How do you replace a value associated with the given key if and only if it is currently mapped to given
	// value?
	private static void solution14() {
		// Creating the HashMap
		HashMap<String, String> map = new HashMap<String, String>();
		// Adding key-value pairs to HashMap
		map.put("ONE", "AAA");
		map.put("TWO", "BBB");
		map.put("THREE", "CCC");
		map.put("FOUR", "DDD");
		map.put("FIVE", "EEE");
		// Printing Key-value pairs
		log.debug("{}", "HashMap Before Replace :");
		Set<Entry<String, String>> keyValueSet = map.entrySet();
		for (Entry<String, String> entry : keyValueSet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
		log.debug("{}", "------------------");
		// Replacing the value associated with 'FOUR' to '444' only if it is currently mapped to 'DDD'
		map.replace("FOUR", "DDD", "444");
		log.debug("{}", "HashMap After Replace :");
		for (Entry<String, String> entry : keyValueSet) {
			log.debug("{}", entry.getKey() + " : " + entry.getValue());
		}
	}

	// 15) How do you get synchronized HashMap in java?
	private static void solution15() {
		// Creating the HashMap
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// Getting synchronized Map
		Map<String, Integer> syncMap = Collections.synchronizedMap(map);
	}
}

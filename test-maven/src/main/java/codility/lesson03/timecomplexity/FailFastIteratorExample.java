package codility.lesson03.timecomplexity;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * http://javaconceptoftheday.com/fail-fast-and-fail-safe-iterators-in-java-with-examples/
 * A system is called fail-fast if it is shut down immediately when an error occurred. 
 * These systems don’t carry on with the errors. 
 * They immediately stop operating when a fault is occurred in the system. 
 * The errors in the fail-fast systems are immediately exposed. 
 * But, fail-safe systems are not like that. 
 * They don’t stop operating even when a fault is occurred in the system. 
 * They continue the operation by hiding the errors. 
 * They don’t expose the errors immediately. 
 * They carry on with the errors. 
 * Which one is the best system is always the most discussed topic in the system design field. 
 * In this post, we limit our discussion to Fail Fast and Fail Safe Iterators in java.
 */
public class FailFastIteratorExample {

	public static void main(String[] args) {
		// Creating an ArrayList of integers
		ArrayList<Integer> list = new ArrayList<Integer>();
		// Adding elements to list
		list.add(1452);
		list.add(6854);
		list.add(8741);
		list.add(6542);
		list.add(3845);
		// Getting an Iterator from list
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			Integer integer = (Integer) it.next();
			list.add(8457); // This will throw ConcurrentModificationException
		}

	}

}

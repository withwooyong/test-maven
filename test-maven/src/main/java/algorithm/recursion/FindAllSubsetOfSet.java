package algorithm.recursion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindAllSubsetOfSet {
	
	private static Logger log = LoggerFactory.getLogger(FindAllSubsetOfSet.class);

	public static void main(String[] args) {
		log.debug("{}", createSubsetUsingTree("ABCD"));
	}
	
	private static List<String> createSubsetUsingTree(String str) {

		List<String> result = new ArrayList<String>(); 
		result.add("[]");

		// If str is not null, then process further otherwise return empty set.
		if (str != null && str.length() > 0) {

			// Iterate each element of a set
			for (int i = 0; i < str.length(); i++) {

				// Working on str.charAt(i);
				// Store the result of subset of str.charAt(i) in tempList.
				List<String> tempList = new ArrayList<String>();

				// Add str.charAt(i) in each item of result.
				Iterator<String> iter = result.iterator();
				while (iter.hasNext()) {
					String val = iter.next();

					// If val is [], it means str.charAt(i) is not included, So
					// include it in result.
					if (val.equals("[]")) {
						tempList.add("[" + str.charAt(i) + "]");
					} else {

						// For each item, there will be 2 subset, one including
						// it and one without including it.
						// If val is not [], it means it already contain some
						// subset without str.charAt(i), So just include it.
						tempList.add("[" + val.substring(1, val.length() - 1) + ", " + str.charAt(i) + "]");
					}
				}

				// Add all subsets present in tempList to final result.
				result.addAll(tempList);
			}
		}

		return result;
	}
}
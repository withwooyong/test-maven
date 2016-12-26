package codility.lesson14.Binarysearchalgorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowerSet {
	
	private static Logger log = LoggerFactory.getLogger(PowerSet.class);

	public static void main(String[] args) {
		// int[] A = new int[] { 2, 1, 5, 1, 2, 2, 2 };
		int K = 3;
		int M = 5;
		//log.debug("{}", solution(K, M, A));
		Set<Integer> mySet = new HashSet<Integer>();
		mySet.add(1);
		mySet.add(2);
		mySet.add(3);
		for (Set<Integer> s : powerSet(mySet)) {
			log.debug("{}", s);
		}
	}

	public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
		Set<Set<T>> sets = new HashSet<Set<T>>();
		if (originalSet.isEmpty()) {
			sets.add(new HashSet<T>());
			return sets;
		}
		List<T> list = new ArrayList<T>(originalSet);
		T head = list.get(0);
		Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
		for (Set<T> set : powerSet(rest)) {
			Set<T> newSet = new HashSet<T>();
			newSet.add(head);
			newSet.addAll(set);
			sets.add(newSet);
			sets.add(set);
		}
		return sets;
	}
	
}

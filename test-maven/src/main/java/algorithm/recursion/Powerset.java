package algorithm.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Mission:
 * data[k],…,data[n-1]의 멱집합을 구한 후 각각에 include[i]=true, i=0,..,k-1,인 원소를 추가하여 출력하라.
 */
public class Powerset {

	private static Logger log = LoggerFactory.getLogger(Powerset.class);

	private static char[] data = { 'a', 'b', 'c', 'd', 'e', 'f' };
	private static int[] A = { 1, 2, 3, 4, 5 };
	private static int n = data.length;
	private static boolean[] include = new boolean[n];

	public static void main(String[] args) {
		//powerSet(n-1);
		ArrayList<String> ps = new ArrayList<>();
		//log.debug("{}", getpowerset(A, A.length, ps));
		//log.debug("{}", ps.size());
		test();
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
	    log.debug("=====");
	    return sets;
	}  
	
	public static void test() {
		Set<Integer> mySet = new HashSet<Integer>();
		mySet.add(1);
		mySet.add(2);
		mySet.add(3);
		
		log.debug("{}", powerSet(mySet));
//		for (Set<Integer> s : powerSet(mySet)) {
//			log.debug("{}", s);
//		}
	}
	

	public static void powerSet(int k) {
		if (k == n) {
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (include[i]) {
					list.add(data[i] + " ");
					log.debug("{}", data[i]);
				}
			}
			log.debug("{}", list.toString());
			return;
		}
		include[k] = false;
		powerSet(k + 1);
		include[k] = true;
		powerSet(k + 1);
	}

	public static ArrayList<String> getpowerset(int a[], int n, ArrayList<String> ps) {
		if (n < 0) {
			return null;
		} else if (n == 0) {
			if (ps == null) {
				ps = new ArrayList<String>();
			}
			ps.add(" ");
			return ps;
		}
		ps = getpowerset(a, n - 1, ps);
		ArrayList<String> tmp = new ArrayList<String>();
		for (String s : ps) {
			if (s.equals(" ")) {
				tmp.add("" + a[n - 1]);
			} else {
				tmp.add(s + a[n - 1]);
			}
		}
		ps.addAll(tmp);
		return ps;
	}

}

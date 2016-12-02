package codility.solution;

import java.util.Comparator;

public class QueueComparator implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		return o2.compareTo(o1);
	}
}

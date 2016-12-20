package algorithm.recursion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NQueen2 {
	
	private static Logger log = LoggerFactory.getLogger(NQueen2.class);
	
	private static int N = 8;
	private static int[] cols = new int [N+1];

	public static void main(String[] args) {
		
		log.debug("{}", queens(0));
		
	}
	
	
	private static boolean queens(int level) {
		if (!promising(level)) {
			return false;
		} else if (level == N) {
			for (int i = 1; i <= N; i++) {
				log.debug("{} {}", i, cols[i]);
			}
			return true;
		}
		for (int i = 1; i <= N; i++) {
			cols[level + 1] = i;
			if (queens(level + 1)) {
				return true;
			}
		}
		return false;
	}

	private static boolean promising(int level) {
		for (int i = 1; i < level; i++) {
			if (cols[i] == cols[level]) {
				return false;
			} else if (level - i == Math.abs(cols[level] - cols[i])) {
				return false;
			}
		}
		return true;
	}
}

package algorithm.algosrc.src.morse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 이항계수 : n개의 서로 다른 원소 중에서 r개의 원소를 순서없이 골라내는 방법의 수
public class Bino {
	
	private static Logger log = LoggerFactory.getLogger(Bino.class);

	private static int n = 6;
	private static int r = 2;
	private static int[][] cache = new int[n + 1][r + 1];
	private static int A = 0;
	private static int B = 0;
	
	public static void main(String[] args) {
		
		log.debug("bino={} count={}", bino(n, r), A);
		log.debug("bino={} count={}", bino2(n, r), B);
	}

	private static int bino(int n, int r) {
		
		if (r == 0 || n == r) {
			return -1;
		}
		A++;
		return bino(n - 1, r - 1) + bino(n - 1, r);
	}
	
	
	private static int bino2(int n, int r) {
		
		if (r == 0 || n == r) {
			return -1;
		}
		if (cache[n][r] == 1) {
			return cache[n][r];
		} else {
			cache[n][r] = 1;
			B++;
		}
		return bino2(n - 1, r - 1) + bino2(n - 1, r);
	}

}

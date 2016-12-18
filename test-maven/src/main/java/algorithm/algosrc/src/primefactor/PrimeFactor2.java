package algorithm.algosrc.src.primefactor;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 4.6 가장 간단한 형태의 소인수 분해 알고리즘
public class PrimeFactor2 {
	
	private static Logger log = LoggerFactory.getLogger(PrimeFactor2.class);

	public static void main(String[] args) {
		int n = 10;
		log.debug("{}", factor(n));
	}
	
	// 주어진 정수 n 을 소인수분해한다.
	private static ArrayList<Integer> factor(int n) {
		ArrayList<Integer> ret = new ArrayList<>();
		for (int div = 2; div * div <= n; ++div)
			while (n % div == 0) {
				n /= div;
				ret.add(div);
			}
		if (n > 1) {
			ret.add(n);
		}
		return ret;
	}


}

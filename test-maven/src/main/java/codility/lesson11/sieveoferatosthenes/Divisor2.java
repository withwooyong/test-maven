package codility.lesson11.sieveoferatosthenes;

import java.util.ArrayList;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Divisor2 {
	
	private static Logger log = LoggerFactory.getLogger(Divisor2.class);

	public static void main(String[] args) {
		int input = 10;
		log.debug("{}", findDivisorsV1(input));
		log.debug("{}", findDivisorsV2(input));
	}
	
	// 특정 숫자의 약수 구하는 것임..  
	public static ArrayList<Integer> findDivisorsV1(int input) {
		ArrayList<Integer> divisors = new ArrayList<>();
		int count = 0;
		for (int n = 1; n <= input; n++) {
			if (input % n == 0) {
				divisors.add(n); // 1, 2, 3, 6
			}
			count++;
		}
		log.debug("count={} divisors={}", count, divisors);
		return divisors;
	}
	
	// 6의 약수를 생각해 보면 1, 2, 3, 6 인데 1과6, 2와3을 각각 곱하면 6이된다. 이는 모든 수에 적용된다.
	// 여기서 알수 있는 점은 2로 나누었을 때 3이 나온다면 3이상의 값은 계산할 필요가 없다.
	public static ArrayList<Integer> findDivisorsV2(int input) {
		ArrayList<Integer> divisors = new ArrayList<>();
		int high = input;
		int count = 0;
		// arrayList.sort((p1, p2) -> p1.compareTo(p2));
		for (int n = 1; n <= high; n++) {
			if (input % n == 0) {
				divisors.add(n); // 1, 2, 3, 6
				int x = input / n; 
				divisors.add(x);
				high = x - 1;
				log.debug("n={} x={} high={}", n, x, high);
			}
			count++;
		}
		divisors.sort(Comparator.comparingInt(Integer::intValue));
		log.debug("count={} divisors={}", count, divisors);
		return divisors;
	}
	
}
	

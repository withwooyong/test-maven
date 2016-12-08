package codility.lesson10.primeandcompositenumbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 합성수[ composite number , 合成數 ]
 * 1과 그 자신 이외의 수를 약수로 가지는 자연수로, 모두 소수의 곱으로 분해할 수 있다.
 * 이를테면, 4,6,8,9,30 등은 모두 합성수이다. 합성수는 모두 소수의 곱으로 분해할 수 있다. 이것을 합성수의 소인수분해(素因數分解)라고 한다.
 */
public class CompositeNumbers {
	
	private static Logger log = LoggerFactory.getLogger(CompositeNumbers.class);

	public static void main(String[] args) {
		int A = 20;
		solution(A);

	}
	
	private static void solution(int A) {
		int flag;

		for (int i = 2; i <= A; i++) {
			flag = 0;
			for (int j = 2; j < i; j++) {
				if (i % j == 0)
					flag++;
			}
			if (flag != 0) {
				log.debug("one of the composite number is {}", i);
			}
		}
	}

}

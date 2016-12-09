package codility.lesson10.primeandcompositenumbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 합성수[ composite number , 合成數 ]
 * 1과 그 자신 이외의 수를 약수로 가지는 자연수로, 모두 소수의 곱으로 분해할 수 있다.
 * 이를테면, 4,6,8,9,30 등은 모두 합성수이다. 
 * 합성수는 모두 소수의 곱으로 분해할 수 있다. 
 * 이것을 합성수의 소인수분해(素因數分解)라고 한다.
 * 
 * 합성수(合成數)는 1과 자기 자신이 아닌 다른 자연수의 곱으로 나타낼 수 있는 자연수를 의미한다. 
 * 1보다 큰 모든 수는 소수이거나 합성수이다.
 * 다음의 수는 합성수의 예이다.
 * 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20
 * 3333337 = 7 × 31 × 15361
 * 2 × 3 × 5 × 7 × 11 × 13 + 1 = 30031 = 59 × 509
 */
public class CompositeNumbers {
	
	private static Logger log = LoggerFactory.getLogger(CompositeNumbers.class);

	public static void main(String[] args) {
		int A = 20;
		solution(A);
	}
	
	private static void solution(int A) {
		int flag;

		for (int i = 2; i <= A; i++) { // 1 과 자기자신이 아닌
			flag = 0;
			for (int j = 2; j < i; j++) { // 다른 자연수의 곱으로 나타낼 수 있는 자연수
				if (i % j == 0) { 
					flag++;
				}
			}
			if (flag != 0) {
				log.debug("one of the composite number is {}", i);
			}
		}
	}
	
	

}

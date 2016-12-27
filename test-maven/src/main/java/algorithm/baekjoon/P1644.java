package algorithm.baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 소수의 연속합
 */
public class P1644 {
	
	private static Logger log = LoggerFactory.getLogger(P1644.class);

	/*
	 * 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 
	 * 몇 가지 자연수의 예를 들어 보면 다음과 같다.
	 * 3 : 3 (한 가지)
	 * 41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
	 * 53 : 5+7+11+13+17 = 53 (두 가지)
	 * 하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다. 
	 * 7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다. 
	 * 또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.
	 * 2 이상의 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.
	 * 첫째 줄에 자연수 N이 주어진다. (1≤N≤4,000,000)
	 * 
	 */
	public static void main(String[] args) {
		//int N = 3; // [2]
		//int N = 41; //  [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37] 11+13+17=41 
		//int N = 53; // [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47]
		//int N = 20; // [2, 3, 5, 7, 11, 13, 17, 19]
		int N = 3;
		
//		int N;
//		String message;
//        Scanner scan = new Scanner(System.in);      // 문자 입력을 인자로 Scanner 생성
//        message = scan.nextLine();
//        N = Integer.parseInt(message);
        
		
		ArrayList<Integer> primes = getPrime(N);
		log.debug("{}", primes.toString());
		int count = 0; // 자기자신 포함.
		for (int i = 0; i < primes.size(); i++) { // 하나이상은 하나포함.
			int sum = primes.get(i);
			if (N == sum) {
				count++;
			} else {
				for (int j = i + 1; j < primes.size(); j++) {
					sum += primes.get(j);
					if (N == sum) {
						count++;
						break;
					}
				}
			}
		}
		
		log.debug("{}", count);		
		//log.debug("{}", solution(N));
	}
	
	// 소수는 1과 그 자신 이외의 수를 약수로 가지는 자연수로, 모두 소수의 곱으로 분해할 수 있다.
	public static ArrayList<Integer> primes(int N) {
		boolean[] A = new boolean[N + 1];
		Arrays.fill(A, true);
		ArrayList<Integer> primes = new ArrayList<>();
		
		for (int i = 2; i < A.length; i++) {
			if (A[i] != false) {
				for (int j = i; j * i < A.length; j++) {
					A[i * j] = false;
				}
			}
		}
		
		for (int i = 2; i < A.length; i++) { // 소수 1포함 안됨
			if (A[i] == true) {
				primes.add(i);
			}
		}
		log.debug("{}", primes.toString());
		return primes;
	}
	
	public static ArrayList<Integer> getPrime(int N) {
		boolean[] A = new boolean[N + 1];
		Arrays.fill(A, true);
		A[0] = false;
		A[1] = false;
		
		ArrayList<Integer> primes = new ArrayList<>();
		int sqrt = (int) Math.sqrt(N); // 지정된 숫자의 제곱근을 계산하고 반환
		log.debug("sqrt={}", sqrt);
		
		for (int i = 3; i <= sqrt; i++) {
			if (A[i]) {
				for (int j = i; j * i < A.length; j++) {
					A[i * j] = false;
				}
			}
		}
		
		for (int i = 3; i < A.length; i++) {
			if (A[i]) {
				primes.add(i);
			}
		}
		log.debug("{}", primes.toString());
		return primes;
	}
	
	private static int solution(int N) {
		
		boolean[] A = new boolean[4000000];
		Arrays.fill(A, true);
		A[0] = false;
		A[1] = false;
		
		ArrayList<Integer> primes = new ArrayList<>();
		int sqrt = (int) Math.sqrt(4000000); // 지정된 숫자의 제곱근을 계산하고 반환
		
		for (int i = 2; i <= sqrt; i++) {
			if (A[i]) {
				for (int j = i; j * i < 4000000; j++) {
					A[i * j] = false;
				}
			}
		}
		
		for (int i = 2; i < 4000000; i++) {
			if (A[i]) {
				primes.add(i);
			}
		}		

		// 결과 구하기
		int result = 0, sum = 0, lo = 0, hi = 0;
		while (true) {
			if (sum >= N) {
				sum -= primes.get(lo++);
			} else if (hi == primes.size()) {
				break;
			} else {
				sum += primes.get(hi++);
			}
			if (sum == N) {
				result++;
			}
		}
		return result;
	}
}

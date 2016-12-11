package codility.lesson10.primeandcompositenumbers;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 소수(Prime Number)
 * : 1과 자기 자신만으로 나누어 떨어지는 1보다 큰 양의 정수
 * Contents
 * 첫번째, n 보다 작은 정수를 나누어 보며 소수 구하는 법
 * 두번째, 1번 방법 마법의 코드 한줄을 통해 실행 시간 단축하는 법
 * 세번째, n 보다 작은 소수를 나누어 보며 소수 구하는 법 
 */
public class Prime {
	
	private static Logger log = LoggerFactory.getLogger(Prime.class);

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int n = 1000;
		getPrime(n); // 실행 시간 : 1.849(s)
		getPrime2(n);  // 실행 시간 : 0.198(s)
		getPrime3(n);		// 실행 시간 : 0.043(s)
		getPrime4(n);		// 실행 시간 : 0.014(s) // 요넘 좀 이상함..
		long end = System.currentTimeMillis();
		System.out.println("실행 시간 : " + (double) (end - start) / 1000 + "(s)");
		log.debug("{}", isPrime(961));
	}
	
	//961
	public static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
	
	

	public static void getPrime(int num) {
		ArrayList<Integer> primes = new ArrayList<>();
		boolean flag; 						// 소수 판별을 위한 true/false
		for (int i = 2; i <= num; i++) { 	// i는 2~num 사이에 정수
			flag = true;
			for (int j = 2; j < i; j++) { 	// j는 i보다 작은 정수
				if (i % j == 0) { 			// i를 j로 나누었을 때, 나누어 떨어지면 소수가 아님
					flag = false;
				}
			}
			
			if (flag == true) {
				primes.add(i);
			}
		}
		log.debug("{} {}", primes.size(), primes.toString());
	}
	
	public static void getPrime2(int num) {
		ArrayList<Integer> primes = new ArrayList<>();
		boolean flag;
		for (int i = 2; i <= num; i++) {
			flag = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					flag = false;
					break; // 마법의 코드
				}
			}
			if (flag == true) {
				primes.add(i);
			}
		}
		log.debug("{} {}", primes.size(), primes.toString());
	}
	
	/*
	 * 4는 2의 배수다. 2는 소수다.
	 * 10은 2의 배수, 5의 배수다. 2와 5는 소수다.
	 * 21는 3의 배수, 7의 배수다. 3과 7은 소수다.
	 * 입력 받은 수 보다 작은 정수들을 다 나누어 볼 필요없이,
	 * 입력받은 수보다 작은 수의 소수들만 나누어보면 되는 것이다.
	 */
	public static void getPrime3(int num) {
		ArrayList<Integer> primes = new ArrayList<>();
		primes.add(2);
		for (int i = 2; i <= num; i++) {
			for (int j = 0; j < primes.size(); j++) {
				if (i % primes.get(j) == 0) {
					break;
				} else if (j + 1 == primes.size()) {
					primes.add(i);
				}
			}
		}
		log.debug("{} {}", primes.size(), primes.toString());
	}
	
	// 요넘 좀 이상함..
	public static void getPrime4(int N) {
		ArrayList<Integer> primes = new ArrayList<>();
		boolean[] A = new boolean[N];
		Arrays.fill(A, true);
		A[0] = false;
		A[1] = false;

		int sqrt = (int) Math.sqrt(N); // 지정된 숫자의 제곱근을 계산하고 반환

		log.debug("{}", sqrt);
		
		for (int i = 2; i < sqrt; i++) {
			if (A[i]) {
				for (int j = i; j * i < N; j++) {
					A[i * j] = false;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (A[i]) {
				primes.add(i);
			}
		}
		log.debug("{} {}", primes.size(), primes.toString());
		
	}
}




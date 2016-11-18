package codingdojang;

import java.util.ArrayList;

/*
 * 1. 소수(Prime Number)란 무엇인가?
 * 2, 3, 5, 7, 11, 13, 17....
 * 약수가 1과 자기 자신 뿐인 수이다.
 * 
 * 2. 소수를 어떻게 구할까? (알고리즘)
 * 약수가 1과 자신뿐인 것을 확인해야한다.
 * 그러려면???
 * 즉, 자기 자신보다 작은 수들을 나누어봐서, 하나라도 나누어지면 소수가 아닌 것이다.
 * (어떤 수의 배수이면 안된다는 것)
 * 
 */
public class PrimeNumber {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		//getPrime1(30000);
		//getPrime2(30000);
		getPrime3(30000);
		long end = System.currentTimeMillis();
		System.out.println("수행시간 : " + (end - start));
	}
	
	/*
	 * 특정 숫자를 입력받으면, 그 수 까지의 소수를 모두 출력하기
	 */
	public static void getPrime1(int num) {
		int i = 2; // i : 나눌 대상
		boolean isPrime = true;

		while (i <= num) {
			isPrime = true;

			for (int n = 2; n < i; n++) {
				if (i % n == 0) {
					isPrime = false;
					break; // break문 하나를 추가하면, 불필요한 반복을 줄일 수 있다.
				}
				continue;
			}

			if (isPrime == true) {
				System.out.println(i);
			}
			i++;
		}
	}
	
	/*
	 * 9는 3의 배수이다. 3은 소수이다.
	 * 10은 2의 배수, 5의 배수이다. 2와 5는 소수이다.
	 * 14는 2의 배수, 7의 배수이다. 2와 7은 소수이다.
	 * 규칙이 보이지 않는가?
	 * 다 나누어 볼 필요없이, 입력받은 수보다 작은 수의 소수들만 나누어보면 되는 것이다.
	 * (ArrayList에 소수를 넣어놓고 나누어보는 방식으로~)
	 */
	public static void getPrime2(int num) {
		ArrayList<Integer> prime = new ArrayList<Integer>();
		prime.add(2); 
		
		for (int i = 2; i <= num; i++) {
			for (int j = 0; prime.size() > j; j++) {
				if (i % prime.get(j) == 0) { 
					break; // 소수가 아닌 경우 pass
				}
				
				if (j + 1 == prime.size()) { 
					prime.add(i);	// 소수일 때
				}
			}
		}
		
		for (Integer result : prime) {
			System.out.println(result);
		}
	}
	
	/*
	 * 에라토스테네스의 체 (Sieve of Eratosthenes)라는 알고리즘이다.
	 * 120까지의 모든 소수를 구한다고 해보자.
	 * 2부터 120까지 배열에 모두 넣은 후 소수가 아닌 것들을 모두 체크해버리는 것이다.
	 * 2를 제외한 모든 2의 배수를 체크한다.
	 * 3을 제외한 모든 3의 배수를 체크한다.
	 * 4는 아까 체크당했으므로 소수 아님.
	 * 5를 제외한 모든 5의 배수를 체크한다.
	 * ......
	 * 체크가 안된 수들이 소수이다.
	 * 이 알고리즘을 이용하면, 최악과 최선의 프로그램을 만들어낼 수 있다.
	 */
	public static void getPrime3(int num) {
		// 입력받은 수 만큼 배열에 모두 초기화 해둔다
		int[] arr = new int[num];

		for (int i = 2; i <= num; i++) {
			if (arr[i] == 0) { // 이미 체크된 수의 배수는 확인하지 않는다
				continue;
			}
			for (int j = i + i; j <= num; j += i) { // i를 제외한 i의 배수들은 0으로 체크
				arr[j] = 0;
			}
		}

		// print
		for (int i = 2; i <= num; i++) {
			if (arr[i] != 0) {
				System.out.println(arr[i]);
			}
		}
	}
}

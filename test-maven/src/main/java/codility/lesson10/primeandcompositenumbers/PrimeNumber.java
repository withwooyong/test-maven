package codility.lesson10.primeandcompositenumbers;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// https://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/
// http://terms.naver.com/entry.nhn?docId=1113970&cid=40942&categoryId=32206
/*
 * 소수[ prime number , 素數 ]
 * 1과 자기 자신만으로 나누어 떨어지는 1보다 큰 양의 정수.
 * 이를테면, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,… 등은 모두 소수이다. 
 * 4=(22), 6=(2×3), 16=(24)… 등, 소수가 아닌 자연수를 합성수(合成數)라 하며, 1은 소수도 아니고 합성수도 아니다. 
 * 자연수 n이 소수인지 아닌지를 판정하려면, 2≤p≤ √n인 범위에 있는 모든 소수 p로 n을 나누어 보아, 나누어 떨어지지 않으면 소수이고, 나누어 떨어지면 합성수이다. 
 * 즉, 소수는 양의 약수로 1과 자신만을 가진 자연수이며 합성수는 양의 약수가 1과 자기자신을 포함하여 3개 이상인 자연수이다.
 * 정수의 열 2, 3, 4, 5,…로부터 소수를 찾아내는 방법으로 그리스 시대부터 알려진 에라토스테네스의 체(sieve of Erathosthenes)라는 것이 있는데, 이 방법도 실은 위와 같은 원리에 따른 것이다. 
 * 또 메르센의 소수(Mersenne number)도 소수의 유력한 판정법을 제공해 준다. 
 * 무한히 많은 소수가 존재한다는 것은 그리스 시대부터 알려진 사실이며, 유클리드의 《기하학원본》 중에도 그 설명으로 정해진다. 
 * 이 정리를 초등정수론의 기본정리라고 한다.
 * [네이버 지식백과] 소수 [prime number, 素數] (두산백과)
 */
public class PrimeNumber {
	
	private static Logger log = LoggerFactory.getLogger(PrimeNumber.class);

	/*
	 * A very important question in mathematics and security is telling whether a number is prime or not. 
	 * This is pretty useful when encrypting a password. 
	 * In this tutorial, you will learn how to find whether a number is prime in simple cases.
	 */
	public static void main(String[] args) {
		int n = 15;
		log.debug("{}", isPrime(n));
		log.debug("{}", isPrime2(n));

	}
	
	/*
	 * Trivial Cases
	 * We learned numbers are prime if the only divisors they have are 1 and itself. 
	 * Trivially, we can check every integer from 1 to itself (exclusive) and test whether it divides evenly.
	 * For example, one might be tempted to run this algorithm:
	 */
	// checks whether an int is prime or not.
	public static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
	
	/*
	 * This doesn't seem bad at first, but we can make it faster - much faster. 
	 * Consider that if 2 divides some integer n, then (n/2) divides n as well. 
	 * This tells us we don't have to try out all integers from 2 to n. 
	 * Now we can modify our algorithm:
	 */
	//checks whether an int is prime or not.
	public static boolean isPrime2(int n) {
		for (int i = 2; 2 * i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
	
	/*
	 * With some more efficient coding, we notice that you really only have to go up to the square root of n, 
	 * because if you list out all of the factors of a number, the square root will always be in the middle 
	 * (if it happens to not be an integer, we're still ok, we just might over-approximate, but our code will still work).
	 * Finally, we know 2 is the "oddest" prime - it happens to be the only even prime number. 
	 * Because of this, we need only check 2 separately, then traverse odd numbers up to the square root of n. In the end, our code will resemble this:
	 */
	//checks whether an int is prime or not.
	public static boolean isPrime3(int n) {
		// check if n is a multiple of 2
		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
	
	// will contain true or false values for the first 10,000 integers
	public static boolean[] primes = new boolean[10000];
	//set up the primesieve
	public static void fillSieve() {
		
		Arrays.fill(primes, true); // assume all integers are prime.
		primes[0] = primes[1] = false; // we know 0 and 1 are not prime.
		for (int i = 2; i < primes.length; i++) {
			// if the number is prime,
			// then go through all its multiples and make their values false.
			if (primes[i]) {
				for (int j = 2; i * j < primes.length; j++) {
					primes[i * j] = false;
				}
			}
		}
	}

	public static boolean isPrime4(int n) {
		return primes[n]; // simple, huh?
	}
	

}

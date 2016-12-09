package codility.lesson09.maximumsliceproblem;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// https://opentutorials.org/course/1685/9533
public class P_NP {
	
	private static Logger log = LoggerFactory.getLogger(P_NP.class);

	/*
	 * P 문제는 결정 문제들 중에서 쉽게 풀리는 것을 모아 놓은 집합이다. 
	 * 어떤 결정 문제가 주어졌을 때, 다항식(Polynomial) 시간 이내에 그 문제의 답을 YES와 NO 중의 하나로 계산해낼 수 있는 알고리즘이 존재한다면, 
	 * 그 문제는 P 문제에 해당된다. 
	 * nn자리 이하의 수 a와 b가 주어졌을 때, 
	 * a가 b의 배수인지를 판정하는 것은 유클리드 호제법을 사용하면 nn에 대한 다항식 시간에 계산할 수 있으므로, 
	 * 'a는 b의 배수인가?'하는 문제는 P 문제에 해당된다.
	 * 
	 * NP 문제는 결정 문제들 중에서 적어도 검산은 쉽게 할 수 있는 것을 모아 놓은 집합이다. 
	 * 정확히 말하면, 어떤 결정 문제의 답이 YES일 때, 그 문제의 답이 YES라는 것을 입증하는 힌트가 주어지면, 
	 * 그 힌트를 사용해서 그 문제의 답이 정말로 YES라는 것을 다항식 시간 이내에 확인할 수 있는 문제가 바로 NP 문제에 해당된다. 
	 * 예를 들어, \{-5,6,1,2,-10,-7,13\}{−5,6,1,2,−10,−7,13}과 같이 정수 nn개로 이루어진 집합이 주어졌다고 할 때, 
	 * '이 집합의 부분집합들 중에서 원소의 합이 0이 되는 집합이 존재하는가?'라는 문제는 아직까지 다항식 시간 알고리즘이 알려져 있지 않다. 
	 * 곰곰히 생각해보아도, 그냥 모든 부분집합을 다 테스트해보지 않는 이상 답이 YES인지 NO인지 답하기가 어렵다는 것을 알 수 있다. 
	 * 그렇지만 누군가가 우리에게 \{6,1,-7\}{6,1,−7}이라는 힌트를 제공하였다면, 우리는 먼저 이 집합이 원래 집합의 부분집합이라는 사실을 확인하고, 
	 * 이 집합의 원소의 합이 0이라는 사실을 확인함으로서, 원래 문제의 답이 YES라는 것을 어렵지 않게 확인할 수 있다. 
	 * 따라서 '크기가 nn인 어떤 정수 집합이 주어졌을 때, 이 집합의 부분집합들 중에서 원소의 합이 0이 되는 집합이 존재하는가?'라는 문제는 
	 * 적어도 NP 문제인 것은 확실하지만, 이것이 P 문제인지 아닌지는 아직 모르는 상황이라고 할 수 있다.
	 */
	public static void main(String[] args) {
		int A = 12;
		int B = 8;
		euclid(A, B);
		gcdThing(A, B);
	}
	
	/*
	 * P 문제
	 * 유클리드 호제법
	 * 두 개의 정수의 최대공약수를 구하는 알고리즘.
	 * 두 자연수 A, B에 대하여 A를 B로 나눈 나머지를 R이라 하면, 
	 * A와 B의 최대공약수와 B와 R의 최대공약수는 같다는 성질을 이용하여, 
	 * B를 R로 나눈 나머지 R`1`을 구하고, 또 R을 R`1`로 나눈 나머지R`2`를 구하는 것을 반복하면 최대공약수를 구할 수 있게 된다.
	 */
	private static void euclid(int A, int B) {
		if (A < B) { // 큰값 작은값 순으로 들어와야 함.
			return;
		}
		int R = 1;
		// 최소공배수
		int LCM = 0;		
		int C = A;
		int D = B;
		while (R > 0) {
			R = A % B;
			A = B;
			B = R;
		}
		LCM = C * D / A;
		log.debug("최소공배수 LCM={}", LCM); // 24
		/*
		 * 최대공약수(最大公約數)란, 0이 아닌 두 정수나 다항식의 공통되는 약수 중에서 가장 큰 수를 말한다.
		 * 두 정수 a와 b의 최대공약수를 기호로 gcd(a,b)로 표기한다.
		 */
		log.debug("최대공약수 GCD={}", A);   // 4
	}
	
	private static int gcdThing(int A, int B) {
		BigInteger b1 = BigInteger.valueOf(A);
		BigInteger b2 = BigInteger.valueOf(B);
		BigInteger gcd = b1.gcd(b2);
		log.debug("최대공약수 GCD={}", gcd.intValue());
		return gcd.intValue();
	}
	
	// 최대공약수
	private static long gcd1(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b; // % is remainder
			a = temp;
		}
		return a;
	}

	// 최대공약수
	private static long gcd2(long[] input) {
		long result = input[0];
		for (int i = 1; i < input.length; i++)
			result = gcd1(result, input[i]);
		return result;
	}

	/*
	 * 최소공배수(最小公倍數)는 n개의 정수 또는 다항식에 대해 모두의 배수가 되는 최소의 자연수 또는 다항식을 말한다. 
	 * 이중 하나가 0일 때에 최소공배수는 0으로 한다. 
	 * 최소공배수는 LCM(least common multiple)이라고 자주 줄여쓰며, 정수 a, b의 최소공배수는 lcm(a, b)로 표기한다.
	 */
	private static long lcm1(long a, long b) {
		return a * (b / gcd1(a, b));
	}

	// 최소공배수
	private static long lcm2(long[] input) {
		long result = input[0];
		for (int i = 1; i < input.length; i++)
			result = lcm1(result, input[i]);
		return result;
	}	
	
	private static void euclid2(int A, int B) {
		int temp = 0;
		while (A != 0) {
			if (A < B) {
				temp = A;
				A = B;
				B = temp;
			}
			A = A - B;
		}
		log.debug("최대공약수={}", B);
	}

}

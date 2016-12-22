package codility.lesson05.prefixsums;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberOfCase {
	
	private static Logger log = LoggerFactory.getLogger(NumberOfCase.class);

	private static int[] printNumbers = new int[5];
	
	public static void main(String[] args) {
		// int[] A = { 4, 2, 2, 5, 1, 5, 8 };
		
		ArrayList<Integer> picked = new ArrayList<>();
		picked.add(4);
		picked.add(2);
		picked.add(2);
		picked.add(5);
		picked.add(1);
		picked.add(5);
		picked.add(8);
		int n = picked.size();
		int toPick = picked.size();
		//pick(n, picked, toPick);
		
		//solution();
		
		int[] B = { 1, 2, 3, 4};
		printNumbers = new int[B.length];
		numberOfCase(B, 0);		
	}
	
	public static void print(int[] nums) {
		String print = "";
		for (int i = 0; i < nums.length; i++) {
			print += nums[i];
		}
		System.out.println(print);

	}
	
	// 중복을 피하는 경우의 수 출력하기
	public static void numberOfCase(int[] numbers, int index) {
		if (index >= numbers.length) {
			print(printNumbers);
			return;
		}
		printNumbers[printNumbers.length - numbers.length] = numbers[index];
		int[] temp = new int[numbers.length - 1];
		int position = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (i == index) {
				continue;
			}
			temp[position++] = numbers[i];
		}
		index++;
		numberOfCase(temp, 0);
		if (index < numbers.length) {
			numberOfCase(numbers, index);
		}
	}
	
	/*
	 * http://wootool.tistory.com/53
	 * [조합] nCr 쉽게 구하기.	
	 * nCr 같은 경우는 n개의 숫자에서 r개를 뽑는 경우의 수이다. 
	 * 경우의 수를 구하는 경우에서 많이 사용한다.
	 * nCr의 수식은 아래와 같다.
	 * nCr = n! / r!(n-r)!
	 * 즉 매번 이 수식을 사용한다는 것은 n! , r!, (n-r)! 를 사용해야 한다. 
	 * 즉 여기서 말하고자 하는 것은 이 것을 매번 사용할때마다 구하지 않고 미리 다 구해놓아서 그 숫자를 사용하는 것이다.
	 * 이것을 1개를 뽑는것 부터 n개를 뽑는 것 까지의 조합을 쭈욱 정리하다 보면 숫자의 특성이 나올 것이다.
	 * 1C1 = 1
	 * 2C1 = 1  2C2 = 1	 
	 * 3C1 = 1  3C2 = 2  3C3 = 1
	 * 4C1 = 1  4C2 = 3  4C3 = 3   4C4 = 1
	 * 5C1 = 1  5C2 = 4  5C3 = 6   5C4 = 4   5C5 = 1
	 * 6C1 = 1  6C2 = 5  6C3 = 10  6C4 = 10  6C5 = 5  6C6 = 1
	 * 
	 * 위의 것에서 보면 1개에서 뽑는 경우의 수에서 6개의 경우수를 뽑는 것을 쭈욱 나열 한 건데 
	 * 여기서 숫자가 어떻게 나오는 지에 대한 일정 패턴이 눈에 보일 것이다. 그 것은 바로 아래의 식이다.
	 * 
	 * nCr = n-1Cr-1 + n-1Cr  
	 * 
	 * 저 식이 나오는 이유는 간단하다. 
	 * 왜냐하면 
	 * n개의 공에서 r개를 뽑는 경우의 수는 어찌보면 
	 * n-1 개의 공에서 r개를 뽑는 수와 
	 * n-1 개의 공에서 이미 하나를 뽑았다고 생각하고 r-1개를 뽑는 수를 더한것과 같기 때문이다. 
	 */
	// 100Cr까지 미리 구하는 코드
	// 100개에서 r 개를 뽑는 경우의 수까지 미리 만드는 코드로 예를 들겠다. 이 조합의 숫자는 2차원 배열로 만들 수 있다.
	private static void solution() {
		// [n][r] 이다.
		int n = 6;
	    int[][] A = new int[n][n];	 
	    A[1][1] = 1;
	    for (int i = 2; i < n; i++) {
	        for (int j = 1; j < n; j++) {
	            A[i][j] = A[i - 1][j - 1] + A[i - 1][j];
	        }
	    }
	    log.debug("{}", Arrays.deepToString(A));
	}
	
	// n : 전체 원소의 수
	// picked : 지금까지 고른 원소들의 번호
	// toPick : 더 고를 원소의 수
	// n개의 원소 중 toPick개를 고르는 모든 조합을 찾는 알고리즘
	private static void pick(int n, ArrayList<Integer> picked, int toPick) {
		// 기저사례 : 더 고를 원소가 없을 때 고른 원소들을 출력한다.
		if (toPick == 0) {
			log.debug("{}", picked.toString());
			return;
		}

		int smallest = picked.isEmpty() ? 0 : picked.get(picked.size() - 1) + 1;

		for (int next = smallest; next < n; next++) {
			picked.add(next);
			pick(n, new ArrayList<Integer>(picked), toPick - 1);
			picked.remove(picked.size() - 1);
		}
		log.debug("{}", picked.toString());
	}

}

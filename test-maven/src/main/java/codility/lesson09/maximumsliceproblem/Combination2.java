package codility.lesson09.maximumsliceproblem;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Combination2 {
	
	private static Logger log = LoggerFactory.getLogger(Combination2.class);

	/**
	 * combination도 항상 두가지 경우가 합쳐져서 나오는 경우다.
	 * n 조합을 구할 0~n개의 숫자
	 * r 조합에서 몇개를 추출할것인지
	 * nCr = n-1Cr-1 + n-1Cr
	 * 1. 이미 앞자리 하나가 정해졌기 때문에 경우 r-1 개로 나머지를 정해야 하는 경우,
	 * 2. 그 나머지 경우인 앞자리는 다른걸로 정해졌다고 치고 r개로 정해야하는 경우다.
	 * @param args
	 */
	public static void main(String[] args) {
		//int[] A = new int[5];
		int[] A = { 0, 1, 2 };
		int index = 0; 			// 몇번째 요소 1
		int n = A.length + 1;  	// 조합을 구할 0~n개의 숫자
		int r = 2;     			// 조합에서 몇개를 추출할 것인지.
		int target = 0;			// 0~n으로 나열되어있는 원소들의 집합안에 어떤 숫자를 타겟으로 해서 배열에 집어넣을지를 고를때 쓰인다. 
		log.debug("{}", combination(n, r));
		combination2(A, index, n, r, target);
	}
	
	// 6,000,000,000C4
	// 60억 지구 인구 중, 4명을 뽑는 조합이다. 이를 구하기위해서 고딩때는 60억인 n! 을 팩토리얼 한 숫자를 (r! * (n-r)!) 으로 나눠서 계산하고는 했다. 
	// nCr = n-1Cr-1 + n-1Cr
	// 원소가 1,2,3 에서 2개를 골라내는 조합이라고 치자.
	// (1,2) (2,3) (1,3) 세개지 경우가 있는데 이는 다음과 같은 경우 두가지로 쪼개진다.
	// 
	public static int combination(int n, int r) {		
		if(n == r || r == 0) {
			log.debug("{} {}", n, r);
			return 1;
		} else {
			return combination(n - 1, r - 1) + combination(n - 1, r); // nCr = n-1Cr-1 + n-1Cr
		}
	}

	/**
	 * 순열을 구하는 문제에서처럼 이것저것 교환하면서 알고리즘을 짤수도 있겠지만, 일단 여기서는 0~n까지의 숫자의 조합을 구한다고 친다.
	 * 따라서 arr은 여기서는 순열글과는 다르게 안에서 교환하고 하는게 아니기때문에, 안에 데이터는 없다. 
	 * 그냥 빈공간을 적당히 확보해주면 된다. 
	 * 샘플에서는 0,1,2,3 네개의 원소를 가지고 계산을 할 예정이니 int[4]배열의 공간을 만들어준다.
	 * index의 경우 벌써 하나가 정해졌으면 배열중 첫번째 원소가 정해졌다는 뜻으로, +1을 해준다. 
	 * 그리고 하나가 정해졌으므로, r-1 인자를 주어야 한다. 
	 * 만약, 정해지지 않은 상태라면 배열에 값이 들어가지 않았음을 뜻하므로 index를 증가시키지 않고 찾는 대상도 r 인자를 주어야 한다.
	 * n : 조합을 구할 0~n개의 숫자를 설정한다.
	 * r :  조합에서 몇개를 추출할것인지 고른다.
	 * target : 0~n으로 나열되어있는 원소들의 집합안에 어떤 숫자를 타겟으로 해서 배열에 집어넣을지를 고를때 쓰인다. 
	 * 즉, 어떤 combination() 함수가 오든, 항상 +1씩 해주어 판별하게 만든다. 
	 * 실제로 index변수에 따라, 들어가고 말고가 결정된다.
	 * 이 안에서 딱 두개를 그냥 나눠서 구하면 된다. 1번경우와 2번경우. 이를 자바코드로 구현해봤을때는 다음과 같다.
	 */
	public static void combination2(int[] A, int index, int n, int r, int target) {
		if (r == 0) {
			print(A, index);
		} else if (target == n) {
			return;
		} else {
			A[index] = target;
			combination2(A, index + 1, n, r - 1, target + 1);
			combination2(A, index, n, r, target + 1);
		}
	}

	public static void print(int[] A, int length) {
		int[] B = new int[length];
		for (int i = 0; i < length; i++) {
			//System.out.print(arr[i]);
			B[i] = A[i];
		}
		log.debug("{}", Arrays.toString(B));
	}

}

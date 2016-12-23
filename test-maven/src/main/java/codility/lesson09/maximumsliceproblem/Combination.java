package codility.lesson09.maximumsliceproblem;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// http://gorakgarak.tistory.com/523
public class Combination {

	private static Logger log = LoggerFactory.getLogger(Combination.class);

	// nCr = n-1Cr-1 + n-1Cr
	public static void main(String[] args) {
		ArrayList<Integer> picked = new ArrayList<>();
		int n = 3;
		for (int i = 1; i <= n; i++) {
			picked.add(i);
		}
		
		//combination.pick(n, picked, n);
		log.debug("{}", combination(3, 2));
	}
	
	// 6,000,000,000C4
	// 60억 지구 인구 중, 4명을 뽑는 조합이다. 이를 구하기위해서 고딩때는 60억인 n! 을 팩토리얼 한 숫자를 (r! * (n-r)!) 으로 나눠서 계산하고는 했다. 
	// nCr = n-1Cr-1 + n-1Cr
	// 원소가 1,2,3 에서 2개를 골라내는 조합이라고 치자.
	// (1,2) (2,3) (1,3) 세개지 경우가 있는데 이는 다음과 같은 경우 두가지로 쪼개진다.
	// 
	public static int combination(int n, int r) {		
		log.debug("{} {}", n, r);
		if(n == r || r == 0) {
			return 1;
		} else {
			return combination(n - 1, r - 1) + combination(n - 1, r); // nCr = n-1Cr-1 + n-1Cr
		}
	}
	// n = 조합을 구할 0~n개의 숫자를 설정한다.
	// r = 조합에서 몇개를 추출할것인지 고른다.
	// target 0~n으로 나열되어있는 원소들의 집합안에 어떤 숫자를 타겟으로 해서 배열에 집어넣을지를 고를때 쓰인다. 
	// 즉, 어떤 combination() 함수가 오든, 항상 +1씩 해주어 판별하게 만든다. 
	// 실제로 index변수에 따라, 들어가고 말고가 결정된다.
	public static int combination(int[] arr, int index, int n, int r, int target) {
		if (r == 0) {
			log.debug("{} {}", arr, index);
		} else if (target == n) {
			//return;
		} else {
			arr[index] = target;
			combination(arr, index + 1, n, r - 1, target + 1);
			combination(arr, index, n, r, target + 1);
		}
		return 0;
	}

	/*
	 * 모든 조합의 경우를 출력한다 
	 * n : 전체 원소의 수 
	 * picked : 지금까지 고른 원소들의 번호 
	 * toPick : 더 고를 원소의 수
	 * n개의 원소 중 toPick개를 고르는 모든 조합을 찾는 알고리즘
	 */
	public void pick(int n, ArrayList<Integer> picked, int toPick) {
		// 기저사례 : 더 고를 원소가 없을 때 고른 원소들을 출력한다.
		if (toPick == 0) {
			log.debug("{}", picked);
			return;
		}

		int smallest = picked.isEmpty() ? 0 : picked.get(picked.size() - 1) + 1;
		log.debug("{}", smallest);
		for (int next = smallest; next < n; next++) {
			picked.add(next);
			this.pick(n, new ArrayList<Integer>(picked), toPick - 1);
			picked.remove(picked.size() - 1);
		}
	}
}

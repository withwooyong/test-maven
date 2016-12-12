package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * This is a demo task. You can read about this task and its solutions in this blog post.
 * 
 * A zero-indexed array A consisting of N integers is given. 
 * An equilibrium index of this array is any integer P such that 0 ≤ P < N 
 * and the sum of elements of lower indices is equal to the sum of elements of higher indices, 
 * i.e.
 * A[0] + A[1] + ... + A[P−1] = A[P+1] + ... + A[N−2] + A[N−1].
 * Sum of zero elements is assumed to be equal to 0. This can happen if P = 0 or if P = N−1.
 * 
 * For example, consider the following array A consisting of N = 7 elements:
 * A[0] = -7   A[1] =  1   A[2] = 5
 * A[3] =  2   A[4] = -4   A[5] = 3
 * A[6] =  0
 * 
 * P = 3 is an equilibrium index of this array, because:
 * •A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
 * P = 6 is also an equilibrium index, because:
 * •A[0] + A[1] + A[2] + A[3] + A[4] + A[5] = 0
 * and there are no elements with indices greater than 6.
 * P = 7 is not an equilibrium index, because it does not fulfill the condition 0 ≤ P < N.
 */
public class Equilibrium2 {
	
	private static Logger log = LoggerFactory.getLogger(Equilibrium2.class);

	/*
	 *  * 쌍 (1, 1)은 다음과 같은 이유로 행렬 A의 평형 점입니다.
	 *  * * 0 행의 요소 합계는 2, 3 및 4 행의 요소 합계와 같습니다 (두 영역의 합은 14입니다)
	 *  * * 0 열의 요소 합계는 두 번째 열 요소의 합계와 같습니다 (두 영역의 합은 8입니다).
	 *  *
	 *  * 쌍 (3, 1)은 행렬 A의 또 다른 평형 점입니다.
	 *  *이 매트릭스에는 다른 평형 점이 포함되어 있지 않습니다.
	 */
	public static void main(String[] args) {
		// five rows and three columns 
		// 0 <= P < N(30)
		// 0 <= Q < M(30)
		int[][] A = new int[][] { 
			//8, 17, 8
			{ 2, 7, 5 }, // 14
			{ 3, 1, 1 }, // 5
			{ 2, 1, -7 },// -4
			{ 0, 2, 1 }, // 3
			{ 1, 6, 8 } };// 15
		log.debug("{}", solution(A));
	}
	
	private static int solution(int[][] A) {
		
		
		
		return 0;
	}

}

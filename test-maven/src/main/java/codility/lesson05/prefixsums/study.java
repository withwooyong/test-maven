package codility.lesson05.prefixsums;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class study {
	
	private static Logger log = LoggerFactory.getLogger(study.class);

	// http://m.blog.naver.com/sbssbi69/220060435293
	// Algorithm - 순열과 조합
	// 순열 :중복없이 n개 중에서 r개를 뽑아 순서를 정해 나열하는 경우를 말한다.
	// 중복 순열: n개 중에서 r개를 뽑아 순서를 정해 나열하는 경우, 같은 것(종류)을 다시 뽑을 수 있는 것을 말한다.
	// 조합 : 중복없이 n개 중에서 r개를 순서에 상관없이 뽑는 것을 말한다.
	// 중복 조합 : n개 중에서 r개를 같은 것(종류)를 뽑을 수 있으며 뽑히는 순서에 상관없이 선택하는 것을 말한다.
	
	public static void main(String[] args) {
		
		/*
		 * 순열
		 * 세개의 문자 a,b,c 중 2개를 택해 일렬로 배열하는 방법의 수는 
		 * 첫 번째 자리의 문자를 택하는 가짓수(3)와 남은 문자 중에서 두번째 자리에 놓을 가짓수(2)를 곱한 것과 같다. 
		 * 이처럼 서로 다른 n가지에서 r가지를 택하는 순열의 모든 경우의 수는 nPr로 표시할 수 있다.
		 * nPr = n!/(n-r)!, nP0는 1, nPn은 1(모두 선택하거나, 아무것도 선택하지 않을 경우의 수는 1)
		 * 
		 * 문제 : 1, 2, 3, 4 숫자 4개가 주어졌을때 이 중 3개의 숫자를 뽑아 한번씩만 사용해서 만들 수 있는 모든 숫자를 출력하라.
		 */
		
		int[] A = { 2, 3, 5, 4, 1, 3 };
		int m = 2;
		int n = 5;
		log.debug("{}", prefix_sums(A, m, n));
	}
	
	/*
	 * 변하지 않는 수열에서 구간의 합을 빠르게 구하려면, 
	 * 수열 b를 정의하고, 
	 * a_i~ a_j의 합 = b_j - b_(i-1) 로 O(1)만에 구할 수 있다.
	 * 이는 2차원으로도 응용이 가능하다.
	 */
	private static int prefix_sums(int[] A, int m, int n) {
		
		int[] P = new int[A.length]; // 배열 0값으로 초기화
		
		P[0] = A[0];
		for (int i = 1; i < A.length; i++) {
			P[i] = P[i - 1] + A[i];			
		}
		log.debug("{}", Arrays.toString(A));
		log.debug("{}", Arrays.toString(P));
		return P[n] - P[m - 1];
	}
	
	
}

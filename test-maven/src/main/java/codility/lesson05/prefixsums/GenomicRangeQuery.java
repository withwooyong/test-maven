package codility.lesson05.prefixsums;

import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence. 
 * Each nucleotide has an impact factor, which is an integer. 
 * Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. 
 * You are going to answer several queries of the form: 
 * What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?
 * 
 * The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. 
 * There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers. 
 * The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).
 * 
 * For example, consider string S = CAGCCTA and arrays P, Q such that:
 * P[0] = 2    Q[0] = 4
 * P[1] = 5    Q[1] = 5
 * P[2] = 0    Q[2] = 6
 * The answers to these M = 3 queries are as follows:
 * The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
 * The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
 * The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
 * 
 * Write a function:
 * class Solution { public int[] solution(String S, int[] P, int[] Q); }
 * that, given a non-empty zero-indexed string S consisting of N characters and two non-empty zero-indexed arrays P and Q consisting of M integers, 
 * returns an array consisting of M integers specifying the consecutive answers to all queries.
 * 
 * The sequence should be returned as:
 * For example, given the string S = CAGCCTA and arrays P, Q such that:
 * P[0] = 2    Q[0] = 4
 * P[1] = 5    Q[1] = 5
 * P[2] = 0    Q[2] = 6
 * the function should return the values [2, 4, 1], as explained above.
 * 
 * Assume that:
 * N is an integer within the range [1..100,000];
 * M is an integer within the range [1..50,000];
 * each element of arrays P, Q is an integer within the range [0..N − 1];
 * P[K] ≤ Q[K], where 0 ≤ K < M;
 * string S consists only of upper-case English letters A, C, G, T.
 * 
 * Complexity:
 * expected worst-case time complexity is O(N+M);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 * 
 */
public class GenomicRangeQuery {

	private static Logger log = LoggerFactory.getLogger(GenomicRangeQuery.class);
	
	/*
	 * Find the minimal nucleotide from a range of sequence DNA.
	 */	
	public static void main(String[] args) {
		String S = "CAGCCTA";
		int[] P = { 2, 5, 0 };
		int[] Q = { 4, 5, 6 };
//		log.debug("{}", Arrays.toString(mySolution(S, P, Q))); // // [2, 4, 1]
//		log.debug("{}", Arrays.toString(solution(S, P, Q))); // // [2, 4, 1]
		log.debug("{}", Arrays.toString(solution2(S, P, Q))); // // [2, 4, 1]
		
	}
	
	// [2, 4, 1]
	public static int[] solution2(String S, int[] P, int[] Q) {
		int M = 3;
		int[] result = new int[M];
		// CAGCCTA / A, C, G, T / 1, 2, 3, 4
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("A", 1);
		map.put("C", 2);
		map.put("G", 3);
		map.put("T", 4);
		
		for (int i = 0; i < M; i++) {
			log.debug("{} {}", S.charAt(P[i]), S.charAt(Q[i]));
			result[i] = Math.min(map.get("" + S.charAt(P[i])), map.get("" + S.charAt(Q[i])));
		}
		return result;
	}
	
	// 접근방법:
	// Symbol A,C,G,T중 impact factors가 가장 낮은 A(1), C(2), G(3), T(4)순으로 처리한다.
	// 4가지 중 1개는 무조건 출현하므로 A, C, G만 메모리에 저장하고 출현하지 않은 경우 T로 본다.
	// 연속된 범위에 대한 조회이므로 Range Query Start index, End index 특정값의 차이로 출현여부를 알 수 있도록 각 Symbol별 출현횟수를 누적으로 저장하고 계산한다.
	// 주의사항:
	// Range Query Start index부터 조회하면 해당 index를 반영하지 못하므로 바로 이전 index부터 계산한다.
/*
 *  * For example, consider string S = CAGCCTA and arrays P, Q such that:
 * P[0] = 2    Q[0] = 4
 * P[1] = 5    Q[1] = 5
 * P[2] = 0    Q[2] = 6
 * The answers to these M = 3 queries are as follows:
 * The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
 * The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
 * The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
 * ㅡㅡㅡㅡ[2, 4, 1]
 */
	
	public static int[] mySolution(String S, int[] P, int[] Q) {
		// C A G C C T A
		int[] result = new int[P.length];
		for (int i = 0; i < Q.length; i++) {
			String subStr = S.substring(P[i], Q[i]+1); // GCC
			log.debug("{}", subStr);
			int factors = Integer.MAX_VALUE;
			int minimal_factor = Integer.MAX_VALUE; 
			for (int j = 0; j < subStr.length(); j++) {
				char ch = subStr.charAt(j);
				log.debug("{} {} {} {}", ch, minimal_factor, j, subStr.length());
				if (ch == 'A') {
					minimal_factor = 1;
				} else if (ch == 'C') {
					minimal_factor = 2;
				} else if (ch == 'G') {
					minimal_factor = 3;
				} else if (ch == 'T') {
					minimal_factor = 4;
				}
				
				if (minimal_factor < factors) {
					factors = minimal_factor;
				}
			}
			result[i] = factors;
		}
		log.debug("{}", Arrays.toString(result));
		// Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively.
		return result;
	}
	
	public static int[] solution(String S, int[] P, int[] Q) {
		int[] A = new int[S.length()];     
		int[] C = new int[S.length()];
		int[] G = new int[S.length()];
		char[] CC = S.toCharArray();
		for (int c = 0; c < CC.length; c++) {
			if (CC[c] == ('A')) {
				fillPosition(A, C, G, c);
			} else if (CC[c] == ('C')) {
				fillPosition(C, A, G, c);
			} else if (CC[c] == ('G')) {
				fillPosition(G, C, A, c);
			} else {
				if (c > 0) {
					C[c] = C[c - 1];
					G[c] = G[c - 1];
					A[c] = A[c - 1];
				}
			}
		}
		int sz = P.length > Q.length ? Q.length : P.length;
		int[] sol = new int[sz];
		for (int i = 0; i < sz; i++) {
			int start = P[i];
			int end = Q[i];
			if (isRange(A, start, end)) {
				sol[i] = 1;
			} else if (isRange(C, start, end)) {
				sol[i] = 2;
			} else if (isRange(G, start, end)) {
				sol[i] = 3;
			} else {
				sol[i] = 4;
			}
		}
		return sol;
	}

	private static boolean isRange(int[] array, int start, int end) {
		return (start == 0 && (array[start] == 1 || array[end] > 0)) || (start > 0 && array[end] > array[start - 1]);
	}

	private static void fillPosition(int[] ToFill, int[] toRepeat1, int[] toReapeat2, int position) {
		ToFill[position] = position == 0 ? ToFill[position] + 1 : ToFill[position - 1] + 1;
		if (position > 0) {
			toRepeat1[position] = toRepeat1[position - 1];
			toReapeat2[position] = toReapeat2[position - 1];
		}
	}

}

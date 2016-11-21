/*
	A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence. 
	Each nucleotide has an impact factor, which is an integer. 
	Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. 
	You are going to answer several queries of the form: What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?
	The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. 
	There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers. 
	The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).
	For example, consider string S = CAGCCTA and arrays P, Q such that:
	    P[0] = 2    Q[0] = 4
	    P[1] = 5    Q[1] = 5
	    P[2] = 0    Q[2] = 6
	
	The answers to these M = 3 queries are as follows:
	The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
	The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
	The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
	
	Write a function:
	class Solution { public int[] solution(String S, int[] P, int[] Q); }
	that, given a non-empty zero-indexed string S consisting of N characters and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.
	
	The sequence should be returned as:
	a Results structure (in C), or
	a vector of integers (in C++), or
	a Results record (in Pascal), or
	an array of integers (in any other programming language).
	
	For example, given the string S = CAGCCTA and arrays P, Q such that:
	    P[0] = 2    Q[0] = 4
	    P[1] = 5    Q[1] = 5
	    P[2] = 0    Q[2] = 6
	the function should return the values [2, 4, 1], as explained above.
	
	Assume that:
	N is an integer within the range [1..100,000];
	M is an integer within the range [1..50,000];
	each element of arrays P, Q is an integer within the range [0..N − 1];
	P[K] ≤ Q[K], where 0 ≤ K < M;
	string S consists only of upper-case English letters A, C, G, T.
	
	Complexity:
	expected worst-case time complexity is O(N+M);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	DNA 서열은 A, C, G 및 T 문자로 구성된 문자열로 표현 될 수 있으며, 시퀀스의 연속적인 뉴클레오티드 유형에 해당합니다. 
	각 뉴클레오타이드는 정수 인 충격 계수를 갖는다. 
	유형 A, C, G 및 T의 뉴클레오타이드는 각각 1, 2, 3 및 4의 충격 인자를 갖는다. 
	주어진 DNA 서열의 특정 부분에 포함되어있는 뉴클레오티드의 영향을 최소화하는 요소는 무엇입니까?
	DNA 시퀀스는 N 개 문자로 구성된 비어 있지 않은 문자열 S = S [0] S [1] ... S [N-1]로 주어집니다. 
	비어 있지 않은 배열 P와 Q에 각각 M 개의 정수로 구성된 M 개의 쿼리가 있습니다. 
	K 번째 질의 (0 ≤ K <M)는 위치 P [K]와 Q [K] (포함) 사이의 DNA 서열에 포함 된 뉴클레오타이드의 최소 영향 인자를 찾도록 요구합니다.
	
	예를 들어, 문자열 S = CAGCCTA와 배열 P, Q를 다음과 같이 고려하십시오.
	P [0] = 2 Q [0] = 4
	P [1] = 5 Q [1] = 5
	P [2] = 0 Q [2] = 6
	이 M = 3 쿼리에 대한 대답은 다음과 같습니다.
	위치 2와 4 사이의 DNA 부분에는 뉴클레오타이드 G와 C (두 번)가 들어 있는데, 그 영향 인자는 각각 3과 2이므로 대답은 2입니다.
	위치 5와 5 사이의 부분은 단일 뉴클레오티드 T를 포함하며, 그 영향 인자는 4이므로, 그 답은 4이다.
	위치 0과 6 사이의 부분 (전체 문자열)에는 모든 뉴클레오티드, 특히 영향 인자가 1 인 뉴클레오티드 A가 포함되어 있으므로 대답은 1입니다.
	
	함수 작성 :
	클래스 솔루션 {public int [] solution (String S, int [] P, int [] Q); }
	N 개의 문자로 구성된 비어 있지 않은 제로 색인 문자열 S와 M 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 P와 Q가 주어지면 모든 검색어에 대한 연속적인 답을 지정하는 M 개의 정수로 구성된 배열을 반환합니다.
	시퀀스는 다음과 같이 반환되어야합니다.
	결과 구조 (C 언어) 또는
	정수 벡터 (C ++에서) 또는
	결과 레코드 (파스칼), 또는
	정수 배열 (다른 프로그래밍 언어에서)
	
	예를 들어, 문자열 S = CAGCCTA와 배열 P, Q가 주어진 경우 다음과 같습니다.
	P [0] = 2 Q [0] = 4
	P [1] = 5 Q [1] = 5
	P [2] = 0 Q [2] = 6
	함수는 위에서 설명한대로 [2, 4, 1] 값을 반환해야합니다.
	
	가정하자면 :
	N은 [1..100,000] 범위 내의 정수입니다.
	M은 [1..50,000] 범위의 정수입니다.
	배열 P, Q의 각 요소는 [0..N - 1] 범위 내의 정수입니다.
	P [K] ≤ Q [K], 0 ≤ K <M;
	문자열 S는 대문자 영문자 A, C, G, T로만 구성됩니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N + M)이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE:100/100
package codility.solution.prefixsums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GenomicRangeQuery {
	public static void main(String[] args) {
		int[] P = new int[] { 2, 5, 0 };
		int[] Q = new int[] { 4, 5, 6 };
		String S = "CAGCCTA";
		System.out.println(Arrays.toString(solution(S, P, Q)));
	}

	public static int[] solution(String S, int[] P, int[] Q) {
		Map<Integer, ArrayList<Integer>> prefSums = getPrefSum(S);
		int[] res = new int[P.length];
		for (int i = 0; i < Q.length; i++) {
			for (int j = 1; j < 5; j++) {
				int high = prefSums.get(j).get(Q[i]);
				int low = P[i] == 0 ? 0 : prefSums.get(j).get(P[i] - 1);
				if (high - low > 0) {
					res[i] = j;
					break;
				}
			}
		}
		return res;
	}

	public static Map<Integer, ArrayList<Integer>> getPrefSum(String s) {
		Map<Integer, ArrayList<Integer>> prefSums = new HashMap<Integer, ArrayList<Integer>>();
		for (int j = 0; j < 4; j++) {
			prefSums.put(j + 1, new ArrayList<Integer>());
		}
		int[] counters = new int[4];
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'A':
				counters[0]++;
				break;

			case 'C':
				counters[1]++;
				break;

			case 'G':
				counters[2]++;
				break;

			case 'T':
				counters[3]++;
				break;

			default:
				break;
			}
			for (int j = 0; j < 4; j++) {
				prefSums.get(j + 1).add(counters[j]);
			}
		}
		return prefSums;
	}

}

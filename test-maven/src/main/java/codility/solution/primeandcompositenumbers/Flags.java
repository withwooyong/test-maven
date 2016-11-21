/*
	A non-empty zero-indexed array A consisting of N integers is given. 
	A peak is an array element which is larger than its neighbours. 
	More precisely, it is an index P such that 0 < P < N − 1 and A[P − 1] < A[P] > A[P + 1].
	For example, the following array A:
	    A[0] = 1 
	    A[1] = 5 
	    A[2] = 3 
	    A[3] = 4 
	    A[4] = 3 
	    A[5] = 4 
	    A[6] = 1 
	    A[7] = 2 
	    A[8] = 3 
	    A[9] = 4 
	    A[10] = 6 
	    A[11] = 2
	has exactly four peaks: elements 1, 3, 5 and 10.
	You are going on a trip to a range of mountains whose relative heights are represented by array A, as shown in a figure below. 
	You have to choose how many flags you should take with you. 
	
	The goal is to set the maximum number of flags on the peaks, according to certain rules.
	
	Flags can only be set on peaks. What's more, if you take K flags, then the distance between any two flags should be greater than or equal to K. 
	The distance between indices P and Q is the absolute value |P − Q|.
	
	For example, given the mountain range represented by array A, above, with N = 12, if you take:
	two flags, you can set them on peaks 1 and 5;
	three flags, you can set them on peaks 1, 5 and 10;
	four flags, you can set only three flags, on peaks 1, 5 and 10.
	You can therefore set a maximum of three flags in this case.
	
	Write a function:
	class Solution { public int solution(int[] A); }
	
	that, given a non-empty zero-indexed array A of N integers, returns the maximum number of flags that can be set on the peaks of the array.
	For example, the following array A:
	    A[0] = 1 
	    A[1] = 5 
	    A[2] = 3 
	    A[3] = 4 
	    A[4] = 3 
	    A[5] = 4 
	    A[6] = 1 
	    A[7] = 2 
	    A[8] = 3 
	    A[9] = 4 
	    A[10] = 6 
	    A[11] = 2
	the function should return 3, as explained above.
	
	Assume that:
	N is an integer within the range [1..200,000];
	each element of array A is an integer within the range [0..1,000,000,000].
	
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어진다. 피크는 인접 요소보다 큰 배열 요소입니다. 보다 정확하게, 0 <P <N - 1이고 A [P - 1] <A [P]> A [P + 1] 인 지수 P이다.
	예를 들어, 다음 배열 A :
	A [0] = 1
	A [1] = 5
	A [2] = 3
	A [3] = 4
	A [4] = 3
	A [5] = 4
	A [6] = 1
	A [7] = 2
	A [8] = 3
	A [9] = 4
	A [10] = 6
	A [11] = 2
	
	요소 1, 3, 5 및 10과 정확히 4 개의 피크가 있습니다.
	아래 그림과 같이 상대적 높이가 배열 A로 표시되는 산 범위로 여행 할 것입니다. 
	당신은 당신과 함께 가져 가야 할 깃발의 개수를 선택해야합니다. 
	목표는 특정 규칙에 따라 피크에서 최대 플래그 수를 설정하는 것입니다.
	
	플래그는 최고점에서만 설정할 수 있습니다. 
	또한 K 플래그를 사용하면 두 플래그 사이의 거리가 K보다 크거나 같아야합니다. 
	인덱스 P와 Q 사이의 거리는 절대 값 | P - Q |입니다.
	예를 들어, N = 12 인 위의 배열 A로 표현되는 산맥이 주어지면 다음을 취합니다.
	두 개의 플래그, 피크 1과 5에서 설정할 수 있습니다.
	3 개의 플래그, 1, 5, 10 번 피크에 설정할 수 있습니다.
	4 개의 플래그를 사용하면 1, 5 및 10 번 피크에서 세 개의 플래그 만 설정할 수 있습니다.
	따라서이 경우 최대 3 개의 플래그를 설정할 수 있습니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A); }
	N 개의 정수 중 비어 있지 않은 인덱스가없는 배열 A가 주어지면 배열의 최고점에 설정할 수있는 최대 플래그 수를 반환합니다.
	
	예를 들어, 다음 배열 A :
	A [0] = 1
	A [1] = 5
	A [2] = 3
	A [3] = 4
	A [4] = 3
	A [5] = 4
	A [6] = 1
	A [7] = 2
	A [8] = 3
	A [9] = 4
	A [10] = 6
	A [11] = 2
	함수는 위에서 설명한대로 3을 반환해야합니다.
	
	가정하자면 :
	N은 [1..200,000] 범위의 정수입니다.
	배열 A의 각 요소는 [0..1,000,000,000] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//Score: 100/14
package codility.solution.primeandcompositenumbers;

import java.util.ArrayList;

public class Flags {

	public static void main(String[] args) {
		int[] N = new int[] { 1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
		System.out.println(solution(N));
	}

	public static int solution(int[] A) {
		return flags(A);
	}

	public static int[] nextPeak(int[] A) {
		int N = A.length;
		ArrayList<Integer> peaks = createPeaks(A);
		int[] next = new int[N];
		next[N - 1] = -1;
		for (int i = N - 2; i > -1; i--) {
			if (peaks.contains(i))
				next[i] = i;
			else
				next[i] = next[i + 1];
		}
		return next;
	}

	public static ArrayList<Integer> createPeaks(int[] A) {
		ArrayList<Integer> peaks = new ArrayList<Integer>();
		for (int i = 1; i < A.length - 1; i++)
			if (A[i] > A[i - 1] && A[i] > A[i + 1])
				peaks.add(i);
		return peaks;
	}

	public static int flags(int[] A) {
		int N = A.length;
		int[] next = nextPeak(A);
		int i = 1;
		int result = 0;
		while ((i - 1) * i <= N) {
			int pos = 0;
			int num = 0;
			while (pos < N && num < i) {
				pos = next[pos];
				if (pos == -1)
					break;
				num += 1;
				pos += i;
			}
			result = Math.max(result, num);
			i++;
		}
		return result;
	}
}

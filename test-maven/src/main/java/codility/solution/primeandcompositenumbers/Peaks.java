/*
	A non-empty zero-indexed array A consisting of N integers is given.
	A peak is an array element which is larger than its neighbors. More precisely, it is an index P such that 0 < P < N − 1,  A[P − 1] < A[P] and A[P] > A[P + 1].
	For example, the following array A:
	    A[0] = 1
	    A[1] = 2
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
	has exactly three peaks: 3, 5, 10.
	We want to divide this array into blocks containing the same number of elements. More precisely, we want to choose a number K that will yield the following blocks:
	A[0], A[1], ..., A[K − 1],
	A[K], A[K + 1], ..., A[2K − 1],
	...
	A[N − K], A[N − K + 1], ..., A[N − 1].
	What's more, every block should contain at least one peak. Notice that extreme elements of the blocks (for example A[K − 1] or A[K]) can also be peaks, but only if they have both neighbors (including one in an adjacent blocks).
	The goal is to find the maximum number of blocks into which the array A can be divided.
	Array A can be divided into blocks as follows:
	one block (1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2). This block contains three peaks.
	two blocks (1, 2, 3, 4, 3, 4) and (1, 2, 3, 4, 6, 2). Every block has a peak.
	three blocks (1, 2, 3, 4), (3, 4, 1, 2), (3, 4, 6, 2). Every block has a peak. Notice in particular that the first block (1, 2, 3, 4) has a peak at A[3], because A[2] < A[3] > A[4], even though A[4] is in the adjacent block.
	However, array A cannot be divided into four blocks, (1, 2, 3), (4, 3, 4), (1, 2, 3) and (4, 6, 2), because the (1, 2, 3) blocks do not contain a peak. Notice in particular that the (4, 3, 4) block contains two peaks: A[3] and A[5].
	The maximum number of blocks that array A can be divided into is three.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N integers, returns the maximum number of blocks into which A can be divided.
	If A cannot be divided into some number of blocks, the function should return 0.
	For example, given:
	    A[0] = 1
	    A[1] = 2 
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
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [0..1,000,000,000].
	Complexity:
	expected worst-case time complexity is O(N*log(log(N)));
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어진다.
피크는 이웃보다 더 큰 배열 요소입니다. 보다 정확하게, 0 <P <N - 1, A [P - 1] <A [P]이고 A [P]> A [P + 1] 인 지수 P이다.
예를 들어, 다음 배열 A :
A [0] = 1
A [1] = 2
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
정확히 3 개의 봉우리가 있습니다 : 3, 5, 10.
이 배열을 같은 수의 요소를 포함하는 블록으로 나누고 싶습니다. 보다 정확하게, 우리는 다음 블록을 산출 할 수있는 K를 선택하고자합니다.
A [0], A [1], ..., A [K-1],
A [K], A [K + 1], ..., A [2K-1],
...
A [N-K], A [N-K + 1], ..., A [N-1]이다.
또한 모든 블록에는 적어도 하나의 피크가 있어야합니다. 블록의 극단적 인 요소 (예 : A [K - 1] 또는 A [K])는 피크가 될 수 있지만 둘 다 인접한 블록에 하나만있는 경우에만 피크가 될 수 있습니다.
목표는 배열 A를 나눌 수있는 최대 블록 수를 찾는 것입니다.
배열 A는 다음과 같이 블록으로 나눌 수 있습니다.
하나의 블록 (1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2). 이 블록은 3 개의 피크를 포함합니다.
2 개의 블록 (1, 2, 3, 4, 3, 4) 및 (1, 2, 3, 4, 6, 2) 모든 블록에는 피크가 있습니다.
3 블록 (1, 2, 3, 4), (3, 4, 1, 2), (3, 4, 6, 2). 모든 블록에는 피크가 있습니다. A [2] <A [3]> A [4]이기 때문에 첫 번째 블록 (1, 2, 3, 4)은 A [3]에서 피크를 갖는다. 인접 블록.
그러나 배열 A는 (1, 2, 3), (4,3,4), (1, 2, 3) 및 (4,6,2) 3) 블록은 피크를 포함하지 않습니다. 특히 (4, 3, 4) 블록에는 A [3]과 A [5]의 두 개의 피크가 포함됩니다.
배열 A가 나눌 수있는 블록의 최대 수는 3입니다.
함수 작성 :
클래스 솔루션 {public int solution (int [] A); }
N 개의 정수로 구성된 0이 아닌 인덱스가없는 공백 배열 A가 주어지면 A가 나눌 수있는 최대 블록 수를 반환합니다.
A를 몇 개의 블록으로 나눌 수없는 경우 함수는 0을 반환해야합니다.
예를 들어, 주어진 :
A [0] = 1
A [1] = 2
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
N은 [1..100,000] 범위 내의 정수입니다.
배열 A의 각 요소는 [0..1,000,000,000] 범위의 정수입니다.
복잡성:
예상 최악의 경우의 시간 복잡도는 O (N * log (log (N)));
예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
입력 배열의 요소를 수정할 수 있습니다.
 */

//Score: 100/100

package codility.solution.primeandcompositenumbers;

import java.util.ArrayList;

public class Peaks {

	public static void main(String[] args) {
		int[] N = new int[] {1,2,3,4,3,4,1,2,3,4,6,2};
		System.out.println(solution(N));
	}
	
	public static int solution(int[] A) {
		int peakCount = 0;
		ArrayList<Integer> peaks = new ArrayList<Integer>();
	    for (int i = 1; i < A.length-1; i++) {
			if (A[i]>A[i-1] && A[i]>A[i+1]) {
				peaks.add(i);
				peakCount++;
			}
		}
		for (int size =  1; size <=A.length; size++) {
			 int blocks = A.length/size;
			 if (A.length % size != 0 || blocks>peakCount)
				 continue;
			 
			 boolean ok = true;
			 int threshold = 0;
			 for (int j = 0; j < peaks.size(); j++) {
				if(peaks.get(j) / size > threshold) {
					ok = false;
					break;
				}
				if (peaks.get(j)/size == threshold)
					threshold++;
			}
			
			if (threshold != blocks) 
				ok= false;
			if(ok) 
				return blocks;
		}
		return 0;
	}	
}

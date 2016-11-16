/*
	You are given integers K, M and a non-empty zero-indexed array A consisting of N integers. Every element of the array is not greater than M.
	You should divide this array into K blocks of consecutive elements. The size of the block is any integer between 0 and N. Every element of the array should belong to some block.
	The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.
	The large sum is the maximal sum of any block.
	For example, you are given integers K = 3, M = 5 and array A such that:
	  A[0] = 2
	  A[1] = 1
	  A[2] = 5
	  A[3] = 1
	  A[4] = 2
	  A[5] = 2
	  A[6] = 2
	The array can be divided, for example, into the following blocks:
	[2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
	[2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
	[2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
	[2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
	The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.
	Write a function:
	class Solution { public int solution(int K, int M, int[] A); }
	that, given integers K, M and a non-empty zero-indexed array A consisting of N integers, returns the minimal large sum.
	For example, given K = 3, M = 5 and array A such that:
	  A[0] = 2
	  A[1] = 1
	  A[2] = 5
	  A[3] = 1
	  A[4] = 2
	  A[5] = 2
	  A[6] = 2
	the function should return 6, as explained above. Assume that:
	N and K are integers within the range [1..100,000];
	M is an integer within the range [0..10,000];
	each element of array A is an integer within the range [0..M].
	Complexity:
	expected worst-case time complexity is O(N*log(N+M));
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	정수 K, M과 N 개의 정수로 구성된 비어 있지 않은 Z- 색인 배열 A가 주어집니다. 배열의 모든 요소는 M보다 크지 않습니다.
이 배열을 연속 요소의 K 블록으로 나누어야합니다. 블록의 크기는 0과 N 사이의 정수입니다. 배열의 모든 요소는 일부 블록에 속해야합니다.
X에서 Y까지의 블록의 합은 A [X] + A [X + 1] + ... + A [Y]와 같습니다. 빈 블록의 합계는 0입니다.
큰 합계는 모든 블록의 최대 합계입니다.
예를 들어 정수 K = 3, M = 5 및 배열 A가 주어집니다.
A [0] = 2
A [1] = 1
A [2] = 5
A [3] = 1
A [4] = 2
A [5] = 2
A [6] = 2
배열은 예를 들어 다음 블록으로 나눌 수 있습니다.
[2, 1, 5, 1, 2, 2, 2], [], [], 15의 큰 합계;
[2], [1, 5, 1, 2], [2, 2] 9의 큰 합;
[2, 1, 5], [], [1, 2, 2, 2] 8의 큰 합계;
[2, 1], [5,1], [2, 2, 2]의 합이 6입니다.
목표는 큰 금액을 최소화하는 것입니다. 위의 예에서 6은 최소의 큰 합계입니다.
함수 작성 :
클래스 솔루션 {public int solution (int K, int M, int [] A); }
주어진 정수 K, M과 N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 최소의 큰 합을 반환한다는 것을 의미합니다.
예를 들어, 주어진 K = 3, M = 5 및 배열 A는 다음과 같습니다.
A [0] = 2
A [1] = 1
A [2] = 5
A [3] = 1
A [4] = 2
A [5] = 2
A [6] = 2
위에서 설명한 것처럼 함수는 6을 반환해야합니다. 가정하자면 :
N과 K는 [1..100,000] 범위의 정수입니다.
M은 [0..10,000] 범위의 정수입니다.
배열 A의 각 요소는 [0..M] 범위의 정수입니다.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (N * log (N + M))이다.
예상되는 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음) 이상으로 O (1)입니다.
입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100 (both, recursive and iterative approach)
package codility.solution.binarysearch;

public class MinMaxDivision {

	public static void main(String[] args) {
		int[] A = new int[]{2,1,5,1,2,2,2};
		int M = 5;
		int K = 3;
		System.out.println(solution(K, M, A));
	}
	
	 public static int solution(int K, int M, int[] A) {
		 int sum=0;
		 int largestEl = 0;
		 for (int i = 0; i < A.length; i++) {
			largestEl= largestEl>=A[i] ? largestEl:A[i];
			sum += A[i];
		}
		int idealMin = Math.max((int)Math.ceil((double)sum/K), largestEl);
		return binarySearchIterative(idealMin, sum, A, K);
	 }
	 
	 public static int binarySearchRecursive(int min, int max, int[] A, int K) {
		 if (max - min < 2)
			 if (verifySolution(min, A, K))
				 return min;
			 else
				 return max;
		 int middle = (min+max)/2;
		 if (verifySolution(middle, A, K))
			 return binarySearchRecursive(min, middle, A, K);
		 else 
			 return binarySearchRecursive(middle, max, A, K);	 
	 }
	 
	 public static int binarySearchIterative(int min, int max, int[] A, int K) {
		 int res=0;
		 int beg= min;
		 int end = max;
		 while (beg<=end) {
			 int middle = (beg+end)/2;
			 if (verifySolution(middle,A,K)) {
				 end=middle-1;
				 res = middle;
			 } else
				 beg=middle+1;
		 } 
		 return res;
	 }
	 
	 public static boolean verifySolution(int x, int[] A, int K) {
		 int tmp=0;
		 int count=1;
		 for (int i = 0; i < A.length; i++) {
			if (tmp + A[i] <= x)
				tmp += A[i];
			else{
				count++;
				tmp=A[i];
				if (count>K)
					return false;
			}	
		}
		 return true;
	 }		
}

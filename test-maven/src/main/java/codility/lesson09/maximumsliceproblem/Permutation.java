package codility.lesson09.maximumsliceproblem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// http://gorakgarak.tistory.com/522
// 순열은 순서가 부여된 임의의 집합을 다른 순서로 뒤섞는 연산을 말한다. 
public class Permutation {
	
	private static Logger log = LoggerFactory.getLogger(Permutation.class);

	// 1,2,3 와 같은 숫자들이이 있다. 이것을 중복하지 않고 순서를 끌어내는 방법을 생각해보자
	public static void main(String[] args) {
		//int[] A = { 1, 2, 3 };
		int[] A = { 1, 2, 3, 4 };
		int depth = 0;		// 현재 트리구조에서 어떤 깊이에서 교환작업을 하고있는지에 대한 변수이다. 
		int n = A.length;	// 총 배열안에 들어있는 숫자를 뜻하며 고정값. 샘플은 1,2,3,4 네개이므로 4로 고정된다.
		int k = A.length;	// 몇개를 뽑아내서 순열을 만들것인지를 뜻하며 고정값. 샘플은 1,2,3,4 모두를 사용해 순열을 만드므로 4로 고정된다.
		perm(A, depth, n, k);
	}
	
	// http://www.eandbsoftware.org/print-all-permutations-of-a-given-string/
	// 1,2,3 와 같은 숫자들이이 있다. 이것을 중복하지 않고 순서를 끌어내는 방법을 생각해보자
	// nPk 의 순열을 구해야 하는문제, 즉 n개중 k개로 이루어진 순열을 구하려고 한다. 
	// 샘플에서는 1,2,3,4 네개로 이루어진 배열을 가지고 4개로 이루어진 순열을 구해보고자 한다.
	public static void perm(int[] arr, int depth, int n, int k) {

		if (depth == k) { // 한번 depth 가 k로 도달하면 사이클이 돌았음. 출력함.
			print(arr,k);
			return;
		}
		
		for (int i = depth; i < n; i++) {
			//Collections.swap(Arrays.asList(arr),  i,  depth);
			swap(arr, i, depth);
			perm(arr, depth + 1, n, k);
			swap(arr, i, depth);
		}

	}

	// 자바에서는 포인터가 없기 때문에 아래와 같이, 인덱스 i와 j를 통해 바꿔줌.
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void print(int[] arr, int k) {

		for (int i = 0; i < k; i++) {
			if (i == k - 1) {
				System.out.println(arr[i]);
			} else {
				System.out.print(arr[i] + ",");
			}
		}
	}
	
	

}

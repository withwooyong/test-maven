package codility.solution;

import java.util.Arrays;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BruteForceSearch {
	
	private static Logger log = LoggerFactory.getLogger(BruteForceSearch.class);

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int K = 6;
		//solution(A, K);
		//search(A, K);
		//searchHash(A, K);
		solution2(A, K);
		
	}
	
	// n개의 항목을 가진 배열에서 세 항목의 합이 주어진 항목 K와 동일한 배열 내의 세 항목의 합.
	// O(n3)
	public static void solution2(int A[], int K) {
		int sum = 0;
		for (int i = 0; i < A.length-1; i++) {
			for (int j = 1; j < A.length-2; j++) {
				for (int j2 = 2; j2 < A.length; j2++) {
					sum = A[i] + A[j] + A[j2];
					if (sum == K) {
						log.debug("{} {} {}", A[i], A[j], A[j2]);
						return;
					}
				}
			}
		}
	}
	
	// n개의 항목을 가진 배열에서 세 항목의 합이 주어진 항목 K와 동일한 배열 내의 세 항목의 합.
	// O(n2)
	public static void solution3(int A[], int K) {
		int sum = 0;
		Arrays.sort(A);
		for (int i = 0; i < A.length-1; i++) {
			for (int j = 1; j < A.length-2; j++) {
				for (int j2 = 2; j2 < A.length; j2++) {
					sum = A[i] + A[j] + A[j2];
					if (sum == K) {
						log.debug("{} {} {}", A[i], A[j], A[j2]);
						return;
					}
				}
			}
		}
	}
	
	// n개의 항목을 가진 배열에서 두 항목의 합이 주어진 항목 K와 동일한 배열 내의 두 항목의 합.
	// O(n2)
	public static void solution(int A[], int K) {
		
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				// log.debug("i={} j={}", i, j);
				if (A[i] + A[j] == K) {
					log.debug("{} {} {}", A[i], A[j], K);
				}
			}
		}
	}
	
	// O(n) 
	public static void searchHash(int A[], int K) {
		Hashtable<Integer, Integer> hashTable = new Hashtable<Integer, Integer>();
		
		for (int i = 0; i < A.length; i++) {
			hashTable.put(A[i], i); // 키값으로 배열의 값을 넣고 순서는 value 로 
		}
		
		for (int i = 0; i < A.length; i++) {
			log.debug("{}", i);
			
			if (hashTable.containsKey(K - A[i])) {						
				log.debug("{} {} {} {}", i, hashTable.get(K - A[i]), A[i], A[hashTable.get(K - A[i])]);
				break;
			}
		}
	}
	
	// O(nlogn) lowIndex = 0, highIndex = n-1 을 관리
	// 합이 K와 같다면 해답, 작으면 hiIndex 감소, 크면 lowIndex 증가
	public static void search(int A[], int K) {
		int lowIndex = 0;
		int highIndex = A.length-1;
		int temp = 0;
		Arrays.sort(A);
		
		while (lowIndex < highIndex) {
			temp = A[lowIndex] + A[highIndex];
			log.debug("{} {} {}", temp, lowIndex, highIndex);
			
			if (temp == K) {
				log.debug("{} {} {}", A[lowIndex], A[highIndex], K);
				break;
			} else if (temp < K) {
				lowIndex++;
			} else {
				highIndex--;
			}
		}
	}
}

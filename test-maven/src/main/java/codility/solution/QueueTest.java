package codility.solution;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class QueueTest {

	public static void main(String[] args) {
		
		int[] A = new int[] { -1, 3, -4, 5, 1, -6, 2, 1 };
		
		int[] B = new int[] { 1, 2, 4, 6, 3, 7, 8 };
		
		//(n*(n+1))/2
		int sum = 0;
		
//		for (int i = 0; i < B.length; i++) {
//			sum += (B[i]*(B[i]+1)/2);
//			System.out.println(sum);
//		}
//		
//		for (int i = 0; i < B.length; i++) {
//			sum -= B[i];
//		}
		System.out.println(sum);
		
		
		Object arr[][] = {  {90, "A", "철수", 1},
			                {65, "B", "영수", 4},
			                {84, "D", "민호", 2},
			                {80, "C", "민지", 3},
			                {30, "E", "길동", 5} };
		
		//  o2.compareTo(o1);
//		Arrays.sort(arr,  new Comparator<Object>() {
//			@Override
//			public int compare(Object o1, Object o2) {
//				if (o1.) {
//					
//				}
//				return 0;
//			}
//		});
		Arrays.sort(arr, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				if (((Comparable) o1[1]).compareTo(o2[1]) < 0) {
					return -1; // asc -1  desc 1
				} else {
					return 1;  // asc 1 desc -1
				}
			}
		});
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++)
				System.out.print(arr[i][j] + "\t");
			System.out.println();
		}
		System.out.println();
		
		
		Arrays.sort(A);		
		Arrays.parallelSort(A);		
		
		System.out.println(Arrays.toString(A));
		
		//test1();
	}
	
	public static void test2() {
		Queue<String> queue2 = new PriorityQueue<String>(5, new QueueComparator());
		queue2.add("test1");
		queue2.add("test6");
		queue2.add("test2");
		queue2.add("test3");
		queue2.add("test5");
		queue2.add("test4");
		
		
		
		while (!queue2.isEmpty()) {
			System.out.println(queue2.remove());
		}
	}
	
	public static void test1() {
		
		
		Queue<String> queue = new LinkedList<>();
		//Deque<String> queue = new LinkedList<>();0

		queue.add("test1");
		queue.add("test2");
		queue.add("test3");
		queue.add("test4");
		queue.add("test5");
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());

		while (!queue.isEmpty()) {
			System.out.println(queue.remove());
		}
		System.out.println(queue.size());
	}
}


package algorithm;

import java.util.Arrays;
import java.util.Collections;

public class ArraySortExam {
	
	public static void main(String[] args) {
		String[] myArray = { "c", "a", "b" };
		// 일단 그냥 출력 해보자. c, a, b 순으로 출력
		for (String s : myArray) {
			System.out.println(s);
		}
		// Ascending Sort, 오름차순 정렬
		Arrays.sort(myArray);
		// a, b, c로 출력
		for (String s : myArray) {
			System.out.println(s);
		}
		// 이번에는 내림차순(Descending) 졍렬
		Arrays.sort(myArray, Collections.reverseOrder());
		// c, b, a로 출력
		for (String s : myArray) {
			System.out.println(s);
		}
	}
}
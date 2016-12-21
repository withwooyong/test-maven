package codility.lesson02.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArraysTest {
	
	private static Logger log = LoggerFactory.getLogger(ArraysTest.class);
	
	public static void main(String[] args) {
		//solutionA();
		//solutionB();
		reverse();	
		reverse2();
	}
	
	public static void test() {
		int[] A = new int[] { 2, 1, 5, 1, 2, 2, 2 };
		
	}
	
	public static void solutionB() {
		char[] B = new char[10];
		for (char c : B) {
			log.debug("test={}" + c);
		}
	}

	public static void solutionA() {
		int[] A = new int[10];
		for (int i : A) {
			log.debug("{}", i);
		}
	}
	
	// 문자열 뒤집기
	public static void reverse() {
		String str = "abcd";
		log.debug("{}", new StringBuilder(str).reverse().toString());
	}
	
	public static void reverse2() {
		String[] myArray = { "c", "a", "b" };
		//char[] myArray2 = { 'c', 'a', 'b' };
		// 일단 그냥 출력 해보자. c, a, b 순으로 출력
		for (String s : myArray) {
			log.debug("{}", s);
		}
		// Ascending Sort, 오름차순 정렬
		Arrays.sort(myArray);
		// a, b, c로 출력
		for (String s : myArray) {
			log.debug("{}", s);
		}
		// 이번에는 내림차순(Descending) 졍렬
		Arrays.sort(myArray, Collections.reverseOrder());
		// c, b, a로 출력
		for (String s : myArray) {
			log.debug("{}", s);
		}
	}
	
	// 배열(Arrangement)을 ArrayList로 변환
	public static void ArrangementToArrayList() {
		String[] city = { "서울", "부산", "대구", "광주" };
		ArrayList<String> al = new ArrayList<String>();
		Collections.addAll(al, city);
		String[] country = { "한국", "미국", "중국", "일본" };
		List<String> al2 = new ArrayList<String>();
		al2 = Arrays.asList(country);
	}

	// ArrayList 를 배열로 변환
	public static void ArrayListToArrangement() {
		String[] city = { "서울", "부산", "대구", "광주" };
		ArrayList<String> al = new ArrayList<String>();
		Collections.addAll(al, city);
		ArrayList<Integer> al3 = new ArrayList<Integer>();
		al3.add(1);
		al3.add(2);
		al3.add(3);
		al3.add(4);
		Integer[] place = al3.toArray(new Integer[al.size()]);
	}
	

	

}

package codility.lesson03.timecomplexity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReverseArray {

	private static Logger log = LoggerFactory.getLogger(ReverseArray.class);
	
	public static void main(String[] args) {
//		assertArrayEquals(revRange(0, 5).toArray(), new int[]{4, 3, 2, 1, 0});
//	    assertArrayEquals(revRange(-5, 0).toArray(), new int[]{-1, -2, -3, -4, -5});
//	    assertArrayEquals(revRange(1, 4).toArray(), new int[]{3, 2, 1});
//	    assertArrayEquals(revRange(0, 0).toArray(), new int[0]);
//	    assertArrayEquals(revRange(0, -1).toArray(), new int[0]);
//	    assertArrayEquals(revRange(MIN_VALUE, MIN_VALUE).toArray(), new int[0]);
//	    assertArrayEquals(revRange(MAX_VALUE, MAX_VALUE).toArray(), new int[0]);
//	    assertArrayEquals(revRange(MIN_VALUE, MIN_VALUE + 1).toArray(), new int[]{MIN_VALUE});
//	    assertArrayEquals(revRange(MAX_VALUE - 1, MAX_VALUE).toArray(), new int[]{MAX_VALUE - 1});
		
		int[] A = { 12, 0, 7, 0, 8, 0, 3 };
		char[] B = { 'H', 'B', 'C', 'Z', 'F', 'L', 'M' };
		String[] C = { "H", "B", "C", "Z", "F", "L", "M" };
		
//		log.debug("{}", ascRange(0, 5).toArray());
//		log.debug("{}", revRange(0, 5).toArray());
		
//		reverseInt(Arrays.stream(A).boxed().toArray(Integer[]::new));
//		reverseChar(ArrayUtils.toObject(B));
		reverseInt(ArrayUtils.toObject(A));
		reverseChar(ArrayUtils.toObject(B));
		reverseString(C);
		//test(1234);
		intArrToIntegerArr();
		
		String str = "testString";
		char[] charArray = str.toCharArray();
		Character[] charObjectArray = ArrayUtils.toObject(charArray);
		log.debug("{} {}", str, Arrays.toString(charObjectArray));
	}
	
	public static void intArrToIntegerArr() {
		int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		// To boxed array
		Integer[] what = Arrays.stream(data).boxed().toArray(Integer[]::new);
		Integer[] ever = IntStream.of(data).boxed().toArray(Integer[]::new);
		// To boxed list
		List<Integer> you = Arrays.stream(data).boxed().collect(Collectors.toList());
		List<Integer> like = IntStream.of(data).boxed().collect(Collectors.toList());
		log.debug("{}", Arrays.toString(what));
		log.debug("{}", Arrays.toString(ever));
		log.debug("{}", you.toString());
		log.debug("{}", like.toString());
	}
	
	public static void test(int input) {
		int last_digit, reversedNum = 0;
		while (input != 0) {
			last_digit = input % 10;
			if (last_digit % 2 != 0) {
				reversedNum = reversedNum * 10 + last_digit;

			}
			input = input / 10;
		}
		log.debug("{}", input);
	}
	
	
	public static void reverseInt(Integer[] A) {
		Arrays.sort(A);
		log.debug("{}", Arrays.toString(A));
		// 이번에는 내림차순(Descending) 졍렬
		Arrays.sort(A, Collections.reverseOrder());
		log.debug("{}", Arrays.toString(A));
	}
	
	public static void reverseChar(Character[] A) {
		Arrays.sort(A);
		log.debug("{}", Arrays.toString(A));
		// 이번에는 내림차순(Descending) 졍렬
		Arrays.sort(A, Collections.reverseOrder());
		log.debug("{}", Arrays.toString(A));
	}
	
	public static void reverseString(String[] A) {
		Arrays.sort(A);
		log.debug("{}", Arrays.toString(A));
		// 이번에는 내림차순(Descending) 졍렬
		Arrays.sort(A, Collections.reverseOrder());
		log.debug("{}", Arrays.toString(A));
		
	}
	
	private static IntStream ascRange(int to, int from) {
	    return IntStream.range(to, from).map(i -> from - i + to - 1);
	}
	
	private static IntStream revRange(int from, int to) {
	    return IntStream.range(from, to).map(i -> to - i + from - 1);
	}
}

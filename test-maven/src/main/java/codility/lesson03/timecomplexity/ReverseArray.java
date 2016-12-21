package codility.lesson03.timecomplexity;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

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
		
//		log.debug("{}", ascRange(0, 5).toArray());
//		log.debug("{}", revRange(0, 5).toArray());
		
		reverse(A);
	}
	
	public static void reverse(int[] A) {
		String[] B = new String[A.length+1];
		System.arraycopy(A, 0, B, 0, A.length+1);
		
		Arrays.sort(B);
		log.debug("{}", Arrays.toString(B));
		
		// 이번에는 내림차순(Descending) 졍렬
		Arrays.sort(B, Collections.reverseOrder());
		log.debug("{}", Arrays.toString(B));
		
	}
	
	private static IntStream ascRange(int to, int from) {
	    return IntStream.range(to, from).map(i -> from - i + to - 1);
	}
	
	private static IntStream revRange(int from, int to) {
	    return IntStream.range(from, to).map(i -> to - i + from - 1);
	}
}

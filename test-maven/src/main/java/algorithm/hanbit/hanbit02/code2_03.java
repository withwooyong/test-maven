package algorithm.hanbit.hanbit02;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code2_03 {

	private static Logger log = LoggerFactory.getLogger(code2_03.class);
		
	public static void main(String[] args) {
		int[] list = { 1, 2, 3, 4, 5 };		
		int[] data = { 1, 2, 3, 4, 5 };
		log.debug("{}", Arrays.toString(list));
		ReverseIntegerArray(list, list.length);
		log.debug("{}", Arrays.toString(list));		
		//new StringBuilder(str).reverse().toString())
		log.debug("{}", reverseInt(54321));
		log.debug("{}", reverseIntArray(data));
	}	

	private static void ReverseIntegerArray(int[] array, int n) {
		for (int i = 0; i < n / 2; i++) {
			SwapIntegerElements(array, i, n - i - 1);
		}
	}
	
	private static int[] reverseIntArray(int[] data) {
		for (int i = 0; i < data.length / 2; i++) {
			int temp = data[i];
			data[i] = data[data.length - i - 1];
			data[data.length - i - 1] = temp;
		}
		return data;
	}

	private static void SwapIntegerElements(int[] array, int p1, int p2) {
		int tmp;
		tmp = array[p1];
		array[p1] = array[p2];
		array[p2] = tmp;
	}
	
	private static int reverseInt(int input) {
		long reversedNum = 0;
	    long input_long = input;
	    while (input_long != 0) {
	        reversedNum = reversedNum * 10 + input_long % 10;
	        input_long = input_long / 10;
	    }

	    if (reversedNum > Integer.MAX_VALUE || reversedNum < Integer.MIN_VALUE) {
	        throw new IllegalArgumentException();
	    }
	    return (int)reversedNum;
	}

}

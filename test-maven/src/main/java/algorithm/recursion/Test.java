package algorithm.recursion;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		
		int[] intArr = {1, 2, 3, 4, 5};
		int[] dest = new int[intArr.length+2]; 
		
		System.out.println(Arrays.toString(intArr));
		System.arraycopy(intArr, 0, dest, 2, intArr.length);
		System.out.println(Arrays.toString(dest));
		
		String str = "test";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(str);
		stringBuilder.reverse();
		System.out.println(stringBuilder);
		
		StringBuffer buf = new StringBuffer();
		buf.append(str);
		buf.reverse();
		System.out.println(buf.toString());

	}

}

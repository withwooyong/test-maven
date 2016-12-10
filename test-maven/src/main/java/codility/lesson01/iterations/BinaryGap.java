package codility.lesson01.iterations;



import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.commons.lang.text.StrTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * // Solutions for each problem in this lesson (100%) are accessible in my GitHub repo (username = mehmetbajin) under /codility.
 * Find longest sequence of zeros in binary representation of an integer.
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.
 * For example, number 9 has binary representation 1001 and contains a binary gap of length 2. 
 * The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. 
 * The number 20 has binary representation 10100 and contains one binary gap of length 1. 
 * The number 15 has binary representation 1111 and has no binary gaps.
 * 
 * Write a function:
 * class Solution { public int solution(int N); }
 * 
 * that, given a positive integer N, returns the length of its longest binary gap. 
 * The function should return 0 if N doesn't contain a binary gap.
 * For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5.
 * 
 * Assume that:
 * N is an integer within the range [1..2,147,483,647].
 * Complexity:
 * expected worst-case time complexity is O(log(N));
 * expected worst-case space complexity is O(1).
 * 
 * @author wooyong
 */
public class BinaryGap {	

	private static Logger log = LoggerFactory.getLogger(BinaryGap.class);
	
	/*
	 * Find longest sequence of zeros in binary representation of an integer.
	 */
	// 특정 수를 2진수로 표현할때 1과 1사이의 0의 갯수가 가장 많은 구간의 0의 갯수
	public static void main(String[] args) {
		// N is an integer within the range [1..2,147,483,647].
		int N = 529;
		log.debug("{}", solution(N));
		log.debug("{}", solution1(N));
	}
	
	// 내가 푼거
	public static int solution(int N) {
		String binaryString = Integer.toBinaryString(N);
		
		StrTokenizer tokenizer = new StrTokenizer(binaryString, '1');
		int size = 0;
		while (tokenizer.hasNext()) {
			String token = tokenizer.nextToken();
			if (size < token.length()) {
				size = token.length();
			}
		}
		return size;
	}
	
	public static int solution1(int N) {
		int result = 0;
		String s = Integer.toBinaryString(N);
		int iStart = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				int length = i - iStart - 1;
				if (length > result)
					result = length;
				iStart = i;
			}
		}
		return result;
	}
	
	public static int solution3(int N) {
		int count = 0;
		int max = 0;
		
		boolean startCounting = false;
		while (N > 0) {
			if (!startCounting) {
				if (N % 2 == 1) {
					startCounting = true;
				} 
				N = N >> 1;
				continue;
			} 
			
			if (N % 2 == 1) {
				count = 0;
				N = N >> 1;
			} else {
				count++;
				if (count > max) {
					max = count;
				}
				N = N >> 1;
			}			
		}
		return max;
	}
	
	public static int solutionArrayList(int N) {
		String binaryString = Integer.toBinaryString(N);
		ArrayList<String> strings = new ArrayList<String>();
		int max = 0;
		for (String string : binaryString.split("1")) {
			strings.add(string);
		}
		if (binaryString.charAt(binaryString.length() - 1) == '0')
			strings.remove(strings.size() - 1);
		for (int i = 0; i < strings.size(); i++) {
			int current = strings.get(i).length();
			if (current > max)
				max = current;
		}
		return max;
	}

	public static int solutionJava7(int N) {
		int max = 0;
		int count = 0;
		int boundaries = 0;

		do {
			int bit = N % 2;
			if (bit == 1) {
				boundaries++;
				if (boundaries == 2) {
					if (count > max) {
						max = count;
					}
					count = 0;
					boundaries = 1;
				}
			} else if (boundaries == 1) {
				count++;
			}
			N = N / 2;
		} while (N != 0);
		return max;
	}

	public static int solutionJava8(int N) {
		return Stream.of(Integer.toBinaryString(N).replaceAll("0+S$", "").split("1+")).filter(a -> a != null)
				.map(String::length).max(Integer::compare).orElse(0);
	}

}

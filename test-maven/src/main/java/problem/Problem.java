package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class Problem {
	
	private static Logger log = LoggerFactory.getLogger(Problem.class);

	public static void main(String[] args) {
//		problem01();
//		problem02();
//		problem03();
//		problem04();
//		problem05();
//		problem07();
//		problem08();
		problem09();
	}

	/**
	 * http://codingdojang.com/scode/365?answer_mode=hide
	 * 어떤 자연수 n이 있을 때, d(n)을 n의 각 자릿수 숫자들과 n 자신을 더한 숫자라고 정의하자.
	 * 예를 들어 d(91) = 9 + 1 + 91 = 101
	 * 이 때, n을 d(n)의 제네레이터(generator)라고 한다. 위의 예에서 91은 101의 제네레이터이다.
	 * 어떤 숫자들은 하나 이상의 제네레이터를 가지고 있는데, 101의 제네레이터는 91 뿐 아니라 100도 있다.
	 * 그런데 반대로, 제네레이터가 없는 숫자들도 있으며, 이런 숫자를 인도의 수학자 Kaprekar가 셀프 넘버(self-number)라 이름 붙였다.
	 * 예를 들어 1,3,5,7,9,20,31 은 셀프 넘버 들이다.
	 * 
	 * 1 이상이고 5000 보다 작은 모든 셀프 넘버들의 합을 구하라.
	 */
	private static void problem01() {
		// selfNumber
		ArrayList<Integer> numbersHasGenerator = new ArrayList<Integer>();
		final int MAX_CNT = 5000;
		for (int i = 0; i <= MAX_CNT; i++) {
			String num = String.valueOf(i);
			int no = 0;
			for (int n = 0; n < num.length(); n++) {
				no += Integer.parseInt(num.substring(n, n + 1));
			}
			numbersHasGenerator.add(no + i); // generator 확인
		}
		
		int sum = 0;
		for (int i = 0; i <= MAX_CNT; i++) {
			if (!numbersHasGenerator.contains(i)) {
				sum += i;
			}
		}
		log.debug("{}", sum);
	}
	
	/**
	 * http://codingdojang.com/scode/350?answer_mode=hide
	 * 
	 * 10미만의 자연수에서 3과 5의 배수를 구하면 3,5,6,9이다. 이들의 총합은 23이다.
	 * 1000미만의 자연수에서 3,5의 배수의 총합을 구하라.
	 * 
	 * 0 은 자연수에 포함 안함.
	 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
	 * Find the sum of all the multiples of 3 or 5 below 1000.
	 * */
	private static void problem02() {
		final int MAX_CNT = 1000;
		// java8 
		log.debug("{}", IntStream.range(1, MAX_CNT).filter(j -> (j % 3 == 0) || (j % 5 == 0)).sum());
		// java8 <
		int sum = 0;
		for (int i = 1; i < MAX_CNT; i++) {
			if (i % 3 == 0 || i % 5 == 0) {
				sum += i;
			}
		}
		log.debug("{}", sum);
    }
	
	/**
	 * http://codingdojang.com/scode/406?answer_mode=hide A씨는 게시판 프로그램을 작성하고 있다. 
	 * A씨는 게시물의 총 건수와 한 페이지에 보여줄 게시물수를 입력으로 주었을 때 총 페이지수를 리턴하는 프로그램이 필요하다고 한다. 
	 * 입력 : 총건수(m), 한페이지에 보여줄 게시물수(n) (단 n은 1보다 크거나 같다. n >= 1) 
	 * 출력 : 총페이지수
	 */
	private static void problem03() {
		int m = 99;
		int n = 10;
		int cnt = m / n;
		cnt = m % n > 0 ? cnt + 1 : cnt;
		log.debug("{}", cnt);
	}
	
	/**
	 * http://codingdojang.com/scode/408?answer_mode=hide
	 * 1차원의 점들이 주어졌을 때, 그 중 가장 거리가 짧은 것의 쌍을 출력하는 함수를 작성하시오. (단 점들의 배열은 모두 정렬되어있다고 가정한다.)
	 * 예를들어 S={1, 3, 4, 8, 13, 17, 20} 이 주어졌다면, 결과값은 (3, 4)가 될 것이다.
	 */
	private static void problem04() {
		int[] input = { 1, 3, 4, 8, 13, 17, 20 };

		int mVal = 0;
		String result = null;
		for (int i = 0; i < input.length - 1; i++) {
			int tempVal = input[i + 1] - input[i];
			if (result != null) {
				if (tempVal < mVal) {
					mVal = tempVal;
					result = input[i] + "," + input[i + 1];
				}
			} else {
				mVal = tempVal;
				result = input[i] + "," + input[i + 1];
			}
		}
		log.debug("{}", result);
		
		for (int i = 0; i < input.length; i++) {
			
		}
	}
	
	/**
	 * http://codingdojang.com/scode/504?answer_mode=hide
	 * 1~1000에서 각 숫자의 개수 구하기
	 * 예로 10 ~ 15 까지의 각 숫자의 개수를 구해보자
	 * 10 = 1, 0
	 * 11 = 1, 1
	 * 12 = 1, 2
	 * 13 = 1, 3
	 * 14 = 1, 4
	 * 15 = 1, 5
	 * 그러므로 이 경우의 답은 0:1개, 1:7개, 2:1개, 3:1개, 4:1개, 5:1개
	 */
	private static void problem05() {
		
		int startNum = 0;
		int endNum = 30;
		int[] resultNum = new int[10]; // 결과값 저장
		
		for (startNum = 0; startNum < endNum; startNum++) {
			char[] charTemp = ("" + startNum).toCharArray();
			log.debug("startNum={}", startNum);
			for (int j = 0; j < charTemp.length; j++) {
				char c = charTemp[j];
				resultNum[Character.getNumericValue(c)] += 1; // 결과값 저장
			}
		}
		// 결과값 출력
		log.debug("resultNum={}", resultNum);
		
	}
	
	/**
	 * http://codingdojang.com/scode/266#answer-filter-area
	 * Spiral Array
	 * 6 6
	 * 0   1   2   3   4   5
	 * 19  20  21  22  23   6
	 * 18  31  32  33  24   7
	 * 17  30  35  34  25   8
	 * 16  29  28  27  26   9
	 * 15  14  13  12  11  10
	 * 위처럼 6 6이라는 입력을 주면 6 X 6 매트릭스에 나선형 회전을 한 값을 출력해야 한다.
	 */
	private static void problem06() {
		
		int input1 = 6;
		int input2 = 6;
		
		// 출력될 숫자
        int output=0;        

        // 숫자가 들어갈 매트릭스를 생성합니다.
        int[][] matrix = new int[input1][input2];

        // 배열 값들을 다 -1로 초기화 (2D)
        for (int[] row: matrix) {
            Arrays.fill(row, -1);
        }       
	}
	
	/**
	 * http://codingdojang.com/scode/405?answer_mode=hide
	 * 탭을 공백 문자로 바꾸기
	 * A씨는 개발된 소스코드를 특정업체에 납품하려고 한다. 
	 * 개발된 소스코드들은 탭으로 들여쓰기가 된것, 공백으로 들여쓰기가 된 것들이 섞여 있다고 한다. 
	 * A씨는 탭으로 들여쓰기가 된 모든 소스를 공백 4개로 수정한 후 납품할 예정이다.
	 * A씨를 도와줄 수 있도록 소스코드내에 사용된 탭(Tab) 문자를 공백 4개(4 space)로 바꾸어 주는 프로그램을 작성하시오.
	 */
	private static void problem07() {
		String tabStr = "tabStr \t tabStr \t tabStr";
        log.debug("tabStr={}", tabStr);        
        log.debug("result={}", tabStr.replaceAll("\t", "    "));
	}
	
	/**
	 * http://codingdojang.com/scode/505?answer_mode=hide
	 * 10~1000까지 각 숫자 분해하여 곱하기의 전체 합 구하기
	 * 예로, 10~15까지의 각 숫자 분해하여 곱하기의 전체 합은 다음과 같다.
	 * 10 = 1 * 0 = 0
	 * 11 = 1 * 1 = 1
	 * 12 = 1 * 2 = 2
	 * 13 = 1 * 3 = 3
	 * 14 = 1 * 4 = 4
	 * 15 = 1 * 5 = 5
	 * 그러므로, 이 경우의 답은 0+1+2+3+4+5 = 15
	 */
	private static void problem08() {
		int startNum = 10;
		int endNum = 15;		
		int result = IntStream.rangeClosed(startNum, endNum).map(x->String.valueOf(x).chars().map(y->y-'0').reduce(1, (acc, element)->acc*element)).sum();
		log.debug("result={}", result);
	}
	
	/**
	 * http://codingdojang.com/scode/465?answer_mode=hide
	 * 문자열 압축하기
	 * 문자열을 입력받아서, 같은 문자가 연속적으로 반복되는 경우에 그 반복 횟수를 표시하여 문자열을 압축하기.
	 * 입력 예시: aaabbcccccca
	 * 출력 예시: a3b2c6a1
	 */
	private static void problem09() {

		String text = "aaabbcccccca";
		char[] charArr = text.toCharArray();
		char prevChar = ' ';
		StringBuffer sb = new StringBuffer();
		int charArrLength = charArr.length;
		int dupCnt = 1;

		for (int i = 0; i < charArrLength; i++) {
			
			if (prevChar == charArr[i]) {
				dupCnt++;
			} else {
				if (i != 0) { // 중복값에 대한 처리
					sb.append(prevChar);
					sb.append(dupCnt);
				}
				dupCnt = 1;
			}
			
			prevChar = charArr[i];
			
			if (i == charArrLength - 1) { // 마지막 값에 대한 처리
				sb.append(prevChar);
				sb.append(dupCnt);
			}
		}
		log.debug("{}", sb.toString());
	}
	
}

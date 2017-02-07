package algorithm;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class exam {
	
	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
		//System.out.println(largestNumber());
		//exam04();
		exam06();

	}
	
	public static String largestNumber() {
		int[] nums = { 3, 30, 34, 5, 9 };
		String[] arr = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			arr[i] = String.valueOf(nums[i]);
		}

		System.out.println(Arrays.toString(arr));
		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String a, String b) {
				return (b + a).compareTo(a + b);
			}
		});
		System.out.println(Arrays.toString(arr));

		StringBuilder sb = new StringBuilder();
		for (String s : arr) {
			sb.append(s);
		}

		while (sb.charAt(0) == '0' && sb.length() > 1)
			sb.deleteCharAt(0);

		return sb.toString();
	}
	
	/*
	 * 실수가 들어있는 배열에서 옆으로 근접한 숫자랑 곱해서 제일 큰 수 찾기
	 * 예)
	 * 3.4, 0.1, 2.0, 0.34 가 있으면
	 * 3.4 * 0.1
	 * 3.4 * 0.1 * 0.2
	 * 3.4 * 0.1 * 0.2 * 0.34
	 * 0.1 * 2.0
	 * 0.1 * 2.0 * 0.34
	 * 중에서 제일 큰 수는?
	 */
	private static void exam01() {
		
		double[] multiply = { 3.4, 0.1, 2.0, 0.34 };
		
        ArrayList<Double> plus = new ArrayList<Double>();
        ArrayList<Double> minus = new ArrayList<Double>();
        double zero = 0;
        double one = 0;
        for (int i=0; i<multiply.length; i++) {
            double x = multiply[i];
            if (x == 1) {
                one += 1;
            } else if (x > 0) {
                plus.add(x);
            } else if (x < 0) {
                minus.add(x);
            } else {
                zero += 1;
            }
        }
        Collections.sort(plus);
        Collections.sort(minus);
        Collections.reverse(plus);
        if (plus.size() % 2 == 1) {
            plus.add((double)1);
        }
        if (minus.size() % 2 == 1) {
            minus.add(zero > 0 ? (double)0 : (double)1);
        }
        double ans = one;
        for (int i=0; i<plus.size(); i+=2) {
            ans += plus.get(i) * plus.get(i+1);
        }
        for (int i=0; i<minus.size(); i+=2) {
            ans += minus.get(i) * minus.get(i+1);
        }
        System.out.println(ans);
	}
	
	/*
	 * 영문자, 특수문자로 만들어진 문장에서 첫글자를 대문자로 만드는 문제
	 * * .(마침표), !, ? 3개의 특수문자 이후에는 대문자로 변경
	 * * 이미 대문자인 것은 소문자로 변경은 안함
	 * * 텝, 스패이스는 패스
	 */
	private static void exam02() {
		
	}
	
	/*
	 * n진법으로 변경하는 함수를 만들어라.
	 * * 단, 2 <= n <=16
	 * 예)
	 * 결과
	 * 25(2진법) -> 11001
	 * 25(8) -> 31
	 * 25(16) -> 19
	 */
	private static void exam03() {
		// 10진법 수 N을 B진법으로 바꾸려면 N이 0이 될때 까지 나머지를 계속해서 구하면 된다.
		// 11을 3진법으로 바꾸는 방법
		// 11/3 = 3 ... 2
		// 3/3 = 1 ... 0
		// 1/3 = 0 ... 1
		// 11은 3진법으로 102이다.
		
		// B진법 수 N을 10진수로 바꾸려면 B^k을 곰하면서 더해가면된다.
		// 3진법 수 102 = 1 * 3^2 + 0 * 3^1 + 2 * 3^0 = 11
		int n = 25;
		int b = 2;
		StringBuilder ans = new StringBuilder();
		while (n > 0) {
			int r = n % b;
			if (r < 10) {
				ans.append((char) (r + '0'));
			} else {
				ans.append((char) (r - 10 + 'A'));
			}
			n /= b;// 알고리즘 
		}
		System.out.println(ans.reverse()); // 뒤집어야 한다.
		
	}
	
	/*
	 * 양의 정수 n의 숫자를 구성하는 숫자의 자리를 섞어서 나오는 정수들을 형제 관계의 수
	 * - 324 -> 432 -> 234 등등 자리를 변경하여 만들 수 있는 수
	 * - n의 정수가 주어졌을 때 가장 작은 수와 가장 큰수를 뽑아내는 함수를 만들어라
	 * 예) 324일 경우
	 * 결과
	 * 가장 작은 수 : 234
	 * 가장 큰수 : 432
	 */
	private static void exam04() {
		int n = 324;
		
	}
	
	/*
	 * 출/퇴근시간을 기록하는 문자열이 있고, 특정시간을 주었을때 해당시간에 일 하는 사람 수 출력
	 * * 앞의 시간은 출근시간, 뒤의 시간은 퇴근시간
	 * * "11:04:23" 시에 몇 명이 근무하고 있었는지 출력
	 * 예)
	 * String[] times = {"09:03:22 11:24:33", "10:43:22 13:34:22", "11:45:22 16:42:35"};
	 * 출력 결과 : 2
	 */
	private static void exam05() {
		String[] times = {"09:03:22 11:24:33", "10:43:22 13:34:22", "11:45:22 16:42:35"};
		
	}
	
	/*
	 * url의 query string에 대한 key, value를 출력
	 * 예)
	 * url=http://mysite.siteaddr.com/site1?key1=value1&key2=value2&key3=value3
	 * 결과
	 * key1 -> value1
	 * key2 -> value2
	 * key3 -> value3
	 */
	private static void exam06() throws MalformedURLException, UnsupportedEncodingException {
		URL url = new URL("http://mysite.siteaddr.com/site1?key1=value1&key2=value2&key3=value3");
		Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		String query = url.getQuery();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
		}
		System.out.println(query_pairs);
	}
	
	/*
	 * 서버 로그 파일을 읽어서 가장 많은 에러코드 순으로 출력 (High)
	 * * 로그 파일 형식: file_yyyymmdd.log
	 * * 로그는 라인당 하나의 리퀘스트
	 * 예)
	 * 11:22:31 404 url
	 * 13:35:47 503 abc
	 * 14:23:39 502 ddd
	 * 14:36:22 503 abd
	 * 20:34:21 400 bad
	 * 결과
	 * 503 2
	 * 404 1
	 * 502 1
	 * 400 1
	 */
	private static void exam07() {
		
	}
	
	/*
	 * noon을 거꾸로 해도 noon이 되는데 이걸 스택을 이용해서 확인하는 프로그램
	 */
	private static void exam08() {
		
	}
	
	/*
	 * 입력받은 문자열에서 알파벳은 알파벳 순으로 출력하고, 정수들은 합을 출력
	 * * 알파벳 문자를 sort를 써서 정렬하지 말고 풀라
	 * 예)
	 * adb12c
	 * 결과
	 * abcd
	 * 3
	 */
	private static void exam09() {
		
	}
	
	/*
	 * 문자열 s1과 s2의 대소문자 구별없이 비교하는 함수
	 * * 결과값은 s1이 작을 경우 -1, 같을 경우 0, 클 경우 1을 리턴
	 */
	private static void exam10() {
		
	}
	
	/*
	 * 0부터 n까지 무작위 정수를 나열하는 메소드 작성
	 * * 단, 중복되는 숫자는 없어야 함
	 * * 작성을 잘 할 경우 n부터 m사이의 무작위 숫자를 나열하는 메소드 작성
	 */
	private static void exam11() {
		
	}
	
	/*
	 * Binary Search Tree 구현 (High)
	 */
	private static void exam12() {
		
	}
	
	/*
	 * BST(Binary Search Tree)을 Merge하는 알고리즘을 구현 (High)
	 * * 단, Merge한 결과도 BST구조를 따라야한다.
	 */
	private static void exam13() {
		
	}
	
	/*
	 * 숫자를 역으로 변환 함수 구현
	 * * 단, 문자열은 사용 불가
	 * 예)
	 * 12345 -> 54321
	 */
	private static void exam14() {
		
	}
	
	/*
	 * 끝말 잇기 프로그램을 작성
	 * * 단, 한번 나온 단어는 다시 나오지 않도록 한다.
	 * * 입력값 처음 단어수, 그후부터는 단어수 만큼의 단어
	 * * 입력한 단어의 길이는 1~20 사이로 전제
	 * 예)
	 * 단어수: 7
	 * 1 단어: Asgrd
	 * 2 단어: Dragon
	 * 3 단어: New York
	 * 4 단어: Toss
	 * 에러: k로 시작하는 단어이어야 합니다.
	 * 4 단어: Korea
	 * 5 단어: Apple
	 * 6 단어: Apple
	 * 에러: 이미 나온 단어입니다.
	 * 6 단어: English
	 * 7 단어: Happy
	 * 결과 : Asgrd,Dragon,New York,Korea,Apple,English,Happy
	 */
	private static void exam15() {
		
	}
	
	/*
	 * 51/82=0.6219512195121951, 이처럼 반복되는 소수를 찾는 프로그램
	 * * 입력값은 분자, 분모
	 * * 결과값은 반복되는 숫자
	 * 예)
	 * 분자: 51
	 * 분모: 82
	 * 51/82, 21951
	 */
	private static void exam16() {
		
	}
	
	/*
	 * utf-8인코딩과 euc-kr의 특징과 각각의 장단점에 대해여 기술하고, utf-8로된 문자열에 대해서 pseudo code형태로 길이를 구하는 함수를 구현
	 * 예)
	 * 입력 문자열이 "가a나다라12!" 라고 했을때 한글은 3byte, 영어/숫자/기호는 1byte 이런식으로 계산
	 * # utf-8, euc-kr  장단점
	 * euc-kr은 영어, 한글 표현은 잘되지만, 중국어, 일어, 아랍어 같은 다국어 표현에서 문제 발생
	 * utf-8은 다국어 표현에 좋음. 단, euc-kr은 한글이 2byte이지만, utf-8에서는 3byte로 커짐.
	 * 0 ~ 7F: 1byte
	 * 80 ~ 7FF: 2byte
	 * 800 ~ FFFF: 3byte
	 * 10000 ~ : 4byte
	 * '0': 0x30
	 * 'A': 0x41
	 * 'a': 0x61
	 */
	private static void exam17() {
		
	}

	/*
	 * Link형식의 Map을 구현?
	 */
	private static void exam18() {
		
	}
	
	/*
	 * Anagram 판단
	 * * time 과 emit 처럼 단어를 재배치해서 새로운 단어를 만들어내는 것을 anagram 이라함
	 * 예)
	 * time, emit은 anagram O
	 * time, tine은 anagram X
	 */
	private static void exam19() {
		
	}
}

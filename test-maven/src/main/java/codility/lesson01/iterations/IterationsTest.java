package codility.lesson01.iterations;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://hackersstudy.tistory.com/26 
 * @author user
 *
 */
public class IterationsTest {
	
	private static final Logger log = LoggerFactory.getLogger(IterationsTest.class);
	
	public static void main(String[] args) {
		int N = 10;
//		log.debug("{}", factorial(N));
//		log.debug("{}", factorial2(N));
//		log.debug("{}", printStar(N));
//		log.debug("{}", printStar2(N));
//		log.debug("{}", example3());
//		log.debug("{}", example4());
		log.debug("{}", example5());
		
	}
	
	/**
	 * Example: We are given some positive integer n. 
	 * Let’s compute the factorial of n and assign it to the variable factorial. 
	 * The factorial of n is n! = 1 · 2 · . . . · n. 
	 * We can obtain it by starting with 1 and multiplying it by all the integers from 1 to n.
	 * 1 factorial = 1
	 * 2 for i in range (1, n + 1):
	 * 3 factorial *= i
	 */
	public static int factorial(int N) {
		int factorial = 1;
		for (int i = 1; i <= N; i++) {
			factorial *= i;			
			//log.debug("factorial={}", factorial);
		}
		return factorial;
	}
	
	public static int factorial2(int N) {		
		if (N <= 1) {
			return N;
		} else {
			return factorial2(N - 1) * N; // <-- 재귀
		}
	}	
	
	/**
	 * Example: Let’s print a triangle made of asterisks (‘*’) separated by spaces. 
	 * The triangle should consist of n rows, where n is a given positive integer, and consecutive rows should contain 1, 2, . . . , n asterisks. 
	 * For example, for n = 4 the triangle should appear as follows:
	 * *
	 * * *
	 * * * *
	 * * * * *
	 * We need to use two loops, one inside the other: the outer loop should print one row in each step and the inner loop should print one asterisk in each step2.
	 * 1 for i in range(1, n + 1):
	 * 2 for j in range(i):
	 * 3 print ’*’,
	 * 4 print
	 */
	public static int printStar(int N) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		return 0;
	}
	
	// 역삼각형
	public static int printStar2(int N) {
		for (int i = 1; i <= N; i++) {
			for (int j = i; j <= N; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		return 0;
	}
	
	/**
	 * Example: Let’s print a triangle made of asterisks (‘*’) separated by spaces and consisting of n rows again, but this time upside down, and make it symmetrical. 
	 * Consecutive rows should contain 2n − 1, 2n − 3, . . . , 3, 1 asterisks and should be indented by 0, 2, 4, . . . , 2(n − 1) spaces. 
	 * For example, for n = 4 the triangle should appear as follows:
	 * * * * * * * *
	 * * * * * *
	 * * * *
	 * *
	 * The triangle should have n rows, where n is some given positive integer.
	 * This time we will use three loops: one outer and two inner loops. 
	 * The outer loop in each step prints one row of the triangle. 
	 * The first inner loop is responsible for printing the indentations, and the second for printing the asterisks.
	 * 1 for i in range(n, 0, -1):
	 * 2 for j in range(n - i):
	 * 3 print ’ ’,
	 * 4 for j in range(2 * i - 1):
	 * 5 print ’*’,
	 * 6 print
	 */
	public static int example3() {
		int n = 4;
		
		for (int i = n; i >= 0; i--) {
			
//			for (int j = 0; j < n-1; j++) {
//				System.out.print(" ");
//			}
			
			for (int j = 0; j < (2*i-1); j++) {
				System.out.print("*");
			}			
			System.out.println();
		}
		
//		for (int i = n; i >= 0; i--) {
//			for (int j = 0; j <= i; j++) {
//				System.out.print("*");
//			}
//			System.out.println("");
//		}
		return 0;
	}
	
	/**
	 * Example: Given a positive integer n, how can we count the number of digits in its decimal representation? 
	 * One way to do it is convert the integer into a string and count the characters.
	 * Here, though, we will use only arithmetical operations instead. 
	 * We can simply keep dividing the number by ten and count how many steps are needed to obtain 0.
	 * 1 result = 0
	 * 2 while n > 0:
	 * 3 n = n // 10
	 * 4 result += 1
	 * 
	 * * 예 : 양의 정수 n이 주어지면 소수점 이하 자릿수를 어떻게 계산할 수 있습니까?
	 * * 한 가지 방법은 정수를 문자열로 변환하고 문자를 세는 것입니다.
	 * * 여기에서는 대신 산술 연산 만 사용합니다.
	 * * 단순히 숫자를 10으로 나누고 0을 얻는 데 필요한 단계 수를 계산할 수 있습니다.
	 */
	public static int example4() {
		
		int result = 0;
		int n = 100;
		
		log.debug("{}", Math.floor(Math.log10(n) + 1));
		
		while (n > 0) {
			result += 1;
			n = n / 10;
			log.debug("{}", result);
		}
		return 0;
	}
	
	/**
	 * Example: The Fibonacci numbers4 form a sequence of integers defined recursively in the following way. 
	 * The first two numbers in the Fibonacci sequence are 0 and 1, and each subsequent number is the sum of the previous two. 
	 * The first few elements in this sequence are: 0, 1, 1, 2, 3, 5, 8, 13. 
	 * Let’s write a program that prints all the Fibonacci numbers, not exceeding a given integer n.
	 * We can keep generating and printing consecutive Fibonacci numbers until we exceed n.
	 * In each step it’s enough to store only two consecutive Fibonacci numbers.
	 * 1 a = 0
	 * 2 b = 1
	 * 3 while a <= n:
	 * 4 print a
	 * 5 c = a + b
	 * 6 a = b
	 * 7 b = c
	 */
	public static int example5() {
		int n = 13;
		int a = 0;
		int b = 1;
		int c;
		while (a <= n) {
			System.out.print(a + ",");
			c = a + b;
			a = b;
			b = c;
		}
		return 0;
	}
	
	/**
	 * Example: The following program:
	 * 1 days = [’Monday’, ’Tuesday’, ’Wednesday’, ’Thursday’, ’Friday’, ’Saturday’, ’Sunday’]
	 * 2 for day in days:
	 * 3 print day
	 */
	public void example6() {
		String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for (int i = 0; i < days.length; i++) {
			String day = days[i];
			System.out.println(day);
		}
	}
	
	/**
	 * Example: If we modify the above program slightly, as follows:
	 * 1 days = set([’Monday’, ’Tuesday’, ’Wednesday’, ’Thursday’, ’Friday’, ’Saturday’, ’Sunday’])
	 * 2 for day in days:
	 * 3 print day
	 * Monday Tuesday Friday Wednesday Thursday Sunday Saturday
	 */
	public void example7() { // 순서 없이 삽입됨.
		Set<String> set = new HashSet<>();
		set.add("Monday");
		set.add("Tuesday");
		set.add("Wednesday");
		set.add("Thursday");
		set.add("Friday");
		set.add("Saturday");
		set.add("Sunday");
		
		for (String string : set) {
			System.out.println(string);
		}
	}
	
	/**
	 * Example: The following program:
	 * 1 days = {’mon’: ’Monday’, ’tue’: ’Tuesday’, ’wed’: ’Wednesday’, ’thu’: ’Thursday’, ’fri’: ’Friday’, ’sat’: ’Saturday’, ’sun’: ’Sunday’}
	 * 2 for day in days:
	 * 3 print day, ’stands for’, days[day]
	 * 
	 * might output e.g.:
	 * wed stands for Wednesday
	 * sun stands for Sunday
	 * fri stands for Friday
	 * tue stands for Tuesday
	 * mon stands for Monday
	 * thu stands for Thursday
	 * sat stands for Saturday
	 */
	public void example8() {
		
		Map<String, String> map = new HashMap<>();
		map.put("mon", "Monday");
		map.put("tue", "Tuesday");
		map.put("wed", "Wednesday");
		map.put("thu", "Thursday");
		map.put("fri", "Friday");
		map.put("sat", "Saturday");
		map.put("sun", "Sunday");
		
		Set<String> key = map.keySet();
		
		for (String string : key) {
			System.out.println(string + ", stands for " + (String)map.get(string));
		}
	}

	public void example9() {
		LinkedList<String> list = new LinkedList<>();
		list.add("add1");
		list.addFirst("first1");
		list.addFirst("first2");
		list.addLast("last1");
		list.addLast("last2");
		list.add("add2");
		list.add(2, "second");
		for (String string : list) {
			System.out.println(string);
		}
		
	}

}

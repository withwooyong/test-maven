package algorithm.baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1000 {

	private static Logger log = LoggerFactory.getLogger(P1000.class);
	/*
	 * 두 수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
	 */
	public static void main(String[] args) {		
        //S1000();
        //stackTest();
		//P9012();
		Character a = 'a';
		log.debug("{}", 0+a);
				
	}
	
	private static void S1000() {
		Scanner sc = new Scanner(System.in);
		System.out.println("입력하시오.");
		int A = sc.nextInt();
		int B = sc.nextInt();
		log.debug("{}", "result=" + (A + B));
		sc.close();
	}
	
	private static void stackTest() {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		log.debug("{}", stack.toString());
		log.debug("{}", stack.isEmpty());
		log.debug("{}", stack.peek());
		log.debug("{}", stack.size());
		log.debug("{}", stack.elementAt(0));
		log.debug("{}", stack.elementAt(1));
		log.debug("{}", stack.elementAt(2));
		
		for (Integer integer : stack) {
			log.debug("{}", integer);
		}
		log.debug("{}", stack.toString());
		log.debug("{}", stack.capacity());
	}
	
}

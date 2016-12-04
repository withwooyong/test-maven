package codility.solution;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		test3();
		
	}
	
	public static void test2() {
		String test = "()(()[()])";
	}
	
	public static void test3() {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(3);
		stack.push(5);
		stack.push(4);		
		stack.push(2);
		Stack<Integer> stack2 = new Stack<>();
		
		while (!stack.isEmpty()) {
			int temp = stack.pop();
			while (!stack2.isEmpty() && (Integer) stack2.peek() > temp) {
				stack.push(stack2.pop());
			}
			stack2.push(temp);
		}
		
		for (Integer integer : stack2) {
			System.out.println(integer);
		}
	}
	
	public static void test1() {
		
		Stack<String> stack = new Stack<>();		
		stack.push("item1");
		stack.push("item2");
		stack.push("item3");
		stack.push("item4");
		stack.push("item5");

		System.out.println(stack.get(0));
		System.out.println(stack.get(2));
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.size());
		System.out.println(stack.isEmpty());

		for (String string : stack) {
			System.out.println(string);
		}
		
		System.out.println("=====");
		System.out.println(stack.peek());
	}

}

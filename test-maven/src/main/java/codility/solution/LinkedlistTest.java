package codility.solution;

import java.util.LinkedList;

public class LinkedlistTest {

	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<>();
		
		linkedList.add("test1");
		linkedList.add("test2");
		linkedList.add("test3");
		linkedList.add("test4");
		linkedList.add("test5");
		
		linkedList.addFirst("test");
		linkedList.addLast("test6");
		
		System.out.println(linkedList.getFirst());
		System.out.println(linkedList.getLast());
		System.out.println(linkedList.get(2));
		for (String string : linkedList) {
			System.out.println(string);
		}
		
		
		
		
		

	}

}

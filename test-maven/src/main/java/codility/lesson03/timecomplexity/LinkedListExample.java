package codility.lesson03.timecomplexity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedListExample {

	private static Logger log = LoggerFactory.getLogger(LinkedListExample.class);
	
	public static void main(String[] args) {
		solution();
		solution2();
		solution3();
		solution4();
		solution5();
		solution6();
		solution7();
		solution8();
		solution9();
		solution10();
		solution11();
		solution12();
		solution13();
		solution14();
		solution15();
		solution16();
		
	}

	// 1) Given an element, how do you find out whether that element exist in a LinkedList or not. If it exist retrieve
	// the position of that element?
	private static void solution() {
		LinkedList<String> list = new LinkedList<String>();
		// Adding elements at the end of the list
		list.add("JAVA");
		list.add("J2EE");
		list.add("JSP");
		list.add("SERVLETS");
		list.add("JDBC");
		// Printing the elements of list
		log.debug("{}", list); // Output : [JAVA, J2EE, JSP, SERVLETS, JDBC]
		String s = "JSP";
		// Checking whether list contains "JSP"
		boolean contains = list.contains(s);
		if (contains) {
			// If list contains "JSP", printing it's index
			log.debug("{}", list.indexOf(s)); // Output : 2
		}
		s = "STRUTS";
		// Checking whether list contains "STRUTS"
		contains = list.contains("STRUTS");
		if (contains) {
			// If list contains "STRUTS", printing it's index
			log.debug("{}", list.indexOf(s));
		}
	}

	// 2) Write a Java program to traverse the elements of a LinkedList in reverse direction?
	private static void solution2() {
		LinkedList<String> list = new LinkedList<String>();
		// Adding elements at the end of the list
		list.add("JAVA");
		list.add("J2EE");
		list.add("JSP");
		list.add("SERVLETS");
		list.add("JDBC");
		// Printing the elements of list
		log.debug("{}", list); // Output : [JAVA, J2EE, JSP, SERVLETS, JDBC]
		// Getting the Iterator object using descendingIterator() method
		Iterator<String> it = list.descendingIterator();
		// printing the elements of list in reverse order

		while (it.hasNext()) {
			log.debug("{}", it.next());
		}
	}

	// 3) How do you join an ArrayList at the end of a LinkedList?
	private static void solution3() {
		// Creating a LinkedList
		LinkedList<String> linkedList = new LinkedList<String>();
		// Adding elements at the end of the linkedList
		linkedList.add("ONE");
		linkedList.add("TWO");
		linkedList.add("THREE");
		linkedList.add("FOUR");
		linkedList.add("FIVE");
		// Printing the elements of linkedList
		log.debug("{}", linkedList); // Output : [ONE, TWO, THREE, FOUR, FIVE]
		// Creating an ArrayList
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("SIX");
		arrayList.add("SEVEN");
		arrayList.add("EIGHT");
		arrayList.add("NINE");
		// Printing the elements of ArrayList
		log.debug("{}", arrayList); // Output : [SIX, SEVEN, EIGHT, NINE]
		// Appending arrayList at the end of linkedList
		linkedList.addAll(arrayList);
		// Printing the elements of linkedList
		log.debug("{}", linkedList); // Output : [ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE]
	}

	// 4) Write a Java program which implements LinkedList as a Queue (FIFO)?
	private static void solution4() {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		// adding the elements into the queue
		queue.offer(10);
		queue.offer(20);
		queue.offer(30);
		queue.offer(40);
		// Printing the elements of queue
		log.debug("{}", queue); // Output : [10, 20, 30, 40]
		// Removing the elements from the queue
		log.debug("{}", queue.poll()); // Output : 10
		log.debug("{}", queue.poll()); // Output : 20
	}

	// 5) How do you insert an element at the head and tail of a LinkedList?
	private static void solution5() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		// Adding elements at the end of the list
		list.add(10);
		list.addLast(20);
		list.offer(30);
		list.offerLast(40);
		// Printing the elements of list
		log.debug("{}", list); // Output : [10, 20, 30, 40]
		// Adding elements at the beginning of the list
		list.offerFirst(1);
		list.addFirst(2);
		// Printing the elements of list
		log.debug("{}", list); // Output : [2, 1, 10, 20, 30, 40]
	}

	// 6) How do you add an element or collection of elements at a specific position of a LinkedList?
	private static void solution6() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		// Adding elements at the end of the list
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);

		// Printing the elements of list
		log.debug("{}", list); // Output : [10, 20, 30, 40]
		// Adding an element at index 2
		list.add(2, 9999);
		// Printing the elements of list
		log.debug("{}", list); // Output : [10, 20, 9999, 30, 40]
		// Creating another LinkedList with elements to add
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		// Adding elements at the beginning of the list1
		list1.addFirst(111);
		list1.addFirst(222);
		list1.addFirst(333);

		// Printing the elements of list1
		log.debug("{}", list1); // Output : [333, 222, 111]
		// Adding all elements of list1 at index 3 of list
		list.addAll(3, list1);
		// Printing the elements of list
		log.debug("{}", list); // Output : [10, 20, 9999, 333, 222, 111, 30, 40]
	}

	// 7) How do you remove the elements of a LinkedList from both the ends?
	private static void solution7() {
		LinkedList<String> list = new LinkedList<String>();
		// Adding elements at the end of the list
		list.add("ONE");
		list.add("TWO");
		list.add("THREE");
		list.add("FOUR");
		list.add("FIVE");
		list.add("SIX");
		list.add("SEVEN");
		// Printing the elements of list
		log.debug("{}", list); // Output : [ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN]
		// Removing the elements from the head of the LinkedList
		list.poll();
		list.pollFirst();
		list.remove();
		list.removeFirst();
		// Printing the elements of list
		log.debug("{}", list); // Output : [FIVE, SIX, SEVEN]
		// Removing elements from the end of the LinkedList
		list.pollLast();
		list.removeLast();
		// Printing the elements of list
		log.debug("{}", list); // Output : [FIVE]
	}
	
	// 8) How do you replace an element at a specific position of a LinkedList with the given element?
	private static void solution8() {
		LinkedList<String> list = new LinkedList<String>();		 
        //Adding elements at the end of the list 
        list.add("ONE"); 
        list.add("TWO"); 
        list.add("THREE"); 
        list.add("FOUR"); 
        //Printing the elements of list 
        log.debug("{}", list);      //Output : [ONE, TWO, THREE, FOUR] 
        //Replacing an element at index 2 with "ZERO" 
        list.set(2, "ZERO"); 
        log.debug("{}", list);     //Output : [ONE, TWO, ZERO, FOUR]
	}
	
	// 9) How do you retrieve but not remove the elements of a LinkedList from both the ends?
	private static void solution9() {
		LinkedList<String> list = new LinkedList<String>();		 
        //Adding elements at the end of the list 
        list.add("FIRST"); 
        list.add("SECOND"); 
        list.add("THIRD"); 
        list.add("FOURTH"); 
        list.add("FIFTH"); 
        //Printing the elements of list 
        log.debug("{}", list);      //Output : [FIRST, SECOND, THIRD, FOURTH, FIFTH] 
        //Retrieving the elements from the head 
        log.debug("{}", list.element());      //Output : FIRST 
        log.debug("{}", list.getFirst());     //Output : FIRST 
        log.debug("{}", list.peek());        //Output : FIRST 
        log.debug("{}", list.peekFirst());     //Output : FIRST 
        //Retrieving the elements from the tail 
        log.debug("{}", list.peekLast());     //Output : FIFTH 
        log.debug("{}", list.getLast());      //Output : FIFTH
	}
	
	// 10) How do you retrieve and remove and only retrieve an element from specific position of a LinkedList?
	private static void solution10() {
		LinkedList<String> list = new LinkedList<String>();		 
        //Adding elements at the end of the list 
        list.add("JAVA"); 
        list.add("J2EE"); 
        list.add("JSP"); 
        list.add("SERVLETS"); 
        list.add("JDBC"); 
        //Printing the elements of list 
        log.debug("{}", list);      //Output : [JAVA, J2EE, JSP, SERVLETS, JDBC] 
        //Retrieving and removing an element at index 2 of the list 
        log.debug("{}", list.remove(2));     //Output : JSP 
        //Printing the elements of list 
        log.debug("{}", list);     //Output : [JAVA, J2EE, SERVLETS, JDBC] 
        //Only retrieving an element at index 2 of the list 
        log.debug("{}", list.get(2));     //Output : SERVLETS
	}
	
	// 11) How do you get the number of elements in a LinkedList?
	private static void solution11() {
		LinkedList<String> list = new LinkedList<String>();		 
        //Adding elements at the end of the list 
        list.add("JAVA"); 
        list.add("J2EE"); 
        list.add("JSP"); 
        list.add("SERVLETS"); 
        list.add("JDBC"); 
        //Printing the elements of list 
        log.debug("{}", list);      //Output : [JAVA, J2EE, JSP, SERVLETS, JDBC] 
        //Getting the number of elements in list 
        log.debug("{}", list.size());     //Output : 5
	}
	
	// 12) How do you remove the first occurrence and last occurrence of a given element in a LinkedList?
	private static void solution12() {
		LinkedList<String> list = new LinkedList<String>();		 
        //Adding elements at the end of the list 
        list.add("JAVA"); 
        list.add("J2EE"); 
        list.add("JSP"); 
        list.add("J2EE"); 
        list.add("JDBC"); 
        //Printing the elements of list 
        log.debug("{}", list);      //Output : [JAVA, J2EE, JSP, J2EE, JDBC] 
        //Removing the first occurrence of "J2EE" 
        list.removeFirstOccurrence("J2EE"); 
        //Printing the elements of list 
        log.debug("{}", list);      //Output : [JAVA, JSP, J2EE, JDBC] 
        //Removing the last occurrence of "J2EE" 
        list.removeLastOccurrence("J2EE"); 
        //Printing the elements of list 
        log.debug("{}", list);      //Output : [JAVA, JSP, JDBC]
	}
	
	// 13) How do you use LinkedList as Stack (LIFO)?
	private static void solution13() {
		LinkedList<Integer> stack = new LinkedList<Integer>();		 
        //pushing the elements into the stack 
        stack.push(10); 
        stack.push(20); 
        stack.push(30); 
        stack.push(40); 
        //Printing the elements of stack 
        log.debug("{}", stack);      //Output : [40, 30, 20, 10] 
        //Poping out the elements from the stack 
        log.debug("{}", stack.pop());    //Output : 40 
        log.debug("{}", stack.pop());    //Output : 30
	}
	
	// 14) How do you remove all elements of a LinkedList?
	private static void solution14() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();		 
        //adding the elements to LinkedList 
        linkedList.add(10); 
        linkedList.add(20); 
        linkedList.add(30); 
        linkedList.add(40); 
        linkedList.add(50); 
        //Printing the elements of LinkedList 
        log.debug("{}", linkedList);       //Output : [10, 20, 30, 40, 50] 
        //Removing all elements of linkedList 
        linkedList.clear(); 
        //Printing the elements of LinkedList 
        log.debug("{}", linkedList);      //Output : []
	}
	
	// 15) How do you create clone of a LinkedList?
	private static void solution15() {
		LinkedList<Integer> linkedList1 = new LinkedList<Integer>();		 
        //adding the elements to linkedList1 
        linkedList1.add(10); 
        linkedList1.add(20); 
        linkedList1.add(30); 
        linkedList1.add(40); 
        linkedList1.add(50); 
        //Printing the elements of linkedList1 
        log.debug("{}", linkedList1);       //Output : [10, 20, 30, 40, 50] 
        //Creating another LinkedList 
        LinkedList<Integer> linkedList2 = new LinkedList<Integer>(); 
        //Cloning the linkedList1 into linkedList2 
        linkedList2 = (LinkedList<Integer>) linkedList1.clone(); 
        //Printing the elements of linkedList2 
        log.debug("{}", linkedList2);     //Output : [10, 20, 30, 40, 50]
	}
	
	// 16) How do you get the position of last occurrence of a given element in a LinkedList?
	private static void solution16() {
		LinkedList<String> linkedList = new LinkedList<String>();		 
        //adding the elements to linkedList 
        linkedList.add("AAA"); 
        linkedList.add("BBB"); 
        linkedList.add("CCC"); 
        linkedList.add("BBB"); 
        linkedList.add("FFF"); 
        linkedList.add("BBB"); 
        //Printing the elements of linkedList 
        log.debug("{}", linkedList);       //Output : [AAA, BBB, CCC, BBB, FFF, BBB] 
        //Getting the position of last occurrence of "BBB" 
        log.debug("{}", linkedList.lastIndexOf("BBB"));    //Output : 5
	}
}

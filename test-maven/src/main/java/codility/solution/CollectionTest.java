package codility.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  List 
 *  순서가 있는 데이터의 집합, 데이터의 중복을 허용한다.
 *  데이터를 add하면 앞에서 부터 순차적(순서대로)으로 데이터가 들어간다.
 *  그래서 각각의 저장되어 있는 공간들은 고유한 index를 갖는다.
 *  ex.) 대기자 명단
 *  구현 클래스: ArrayList, LinkedList, Stack, Vector등
 *  
 *  Set
 *  순서를 유지하지 않는 데이터의 집합, 데이터의 중복을 허용하지 않는다.
 *  집합이다. 데이터가 순서와는 상관없이 add된다. 중복되지 않는다.
 *  ex.) 양의 정수 집합, 소수의 집합
 *  구현 클래스: HashSet, TreeSet등
 *  
 *  Map
 *  키와 값의 쌍으로 이루어진 데이터의 집합. 순서는 유지되지 않으며, 키는 중복을 허용하지 않고, 값을 중복을 허용한다.
 *  ex.) 우편번호, 지역번호
 *  구현 클래스: HashMap, TreeMap, Hashtable, Properties등
 * @author wooyong
 *
 */
public class CollectionTest {
	
	private static Logger log = LoggerFactory.getLogger(CollectionTest.class);

	public static void main(String[] args) {
		
		// Collection List LinkedList, Stack, Vector, ArrayList
		// Collection Set HashSet, SortedSet, TreeSet
		// Map Hashtable, HashMap, SortedMap, TreeMap
		//listTest();
		//linkedListTest();
		//stackTest();
		vectorTest();
	}
	
	private static void vectorTest() {
		Vector<Integer> vector = new Vector<>();
		vector.add(1);
		vector.add(2);
		vector.add(3);
		log.debug("{} {}", vector.toString(), vector.size());
		log.debug("{}", vector.get(0));
		log.debug("{} {}", vector.firstElement(), vector.lastElement());
		vector.add(4);
		log.debug("{} {}", vector.toString(), vector.size());
		vector.add(1);
		log.debug("{} {}", vector.toString(), vector.size());
		
		
	}
	
	private static void stackTest() {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		log.debug("{} {}", stack.toString(), stack.size());
		log.debug("{} {} {}", stack.peek(), stack.toString(), stack.size());
		log.debug("{} {} {}", stack.pop(), stack.toString(), stack.size());
		log.debug("{}", stack.elementAt(0));
		log.debug("{}", stack.elementAt(1));
		Collections.sort(stack);
		log.debug("## {} {}", stack.toString(), stack.size());
		
	}
	
	private static void linkedListTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		log.debug("{}", list.toString());
		log.debug("{}", list.size());
	}
	
	private static void listTest() {
		List<String> list = new ArrayList<>();
		list.add("test0");
		list.add("test1");
		list.add("test2");
		list.add("test2");
		log.debug("list.size()={}", list.size());
		log.debug("list.get(1)={}", list.get(1));
		String[] A = new String[list.size()]; 
		list.toArray(A);
		log.debug("{}", Arrays.toString(A));
		log.debug("{}", list.indexOf("test2"));
		log.debug("{}", list.lastIndexOf("test2"));
		log.debug("{}", list.contains("test"));
		log.debug("{}", list.contains("test0"));
		log.debug("{}", list.toString());
		list.add(3, "test3");
		log.debug("{}", list.toString());
		list.add(4, "test4");
		log.debug("{}", list.toString());
		
		List<String> list2 = new ArrayList<>();
		list2.addAll(list);
		log.debug("{}", list2.toString());
		list2.retainAll(list);
		log.debug("{}", list2.toString());
	}
}

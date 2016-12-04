package codility.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComparatorDemo {
	
	private static Logger log = LoggerFactory.getLogger(ComparatorDemo.class);

	public static void main(String[] args) {
		//List<Person> people = Arrays.asList(new Person("Joe", 24),new Person("Pete", 18),new Person("Chris", 21));
		//listInteger();
		//listString();
		//java8Integer();
		java8String();
	}
	// Collections.sort(people, (a, b) -> a.name.compareToIgnoreCase(b.name));
	private static void java8Integer() {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(1);
		list.add(2);
		log.debug("{}", list.toString());
		Collections.sort(list, (a, b) -> a.compareTo(b));
		log.debug("{}", list.toString());
	}
	
	private static void java8String() {
		List<String> list = new ArrayList<>();
		list.add("3");
		list.add("1");
		list.add("2");
		log.debug("{}", list.toString());
		Collections.sort(list, (a, b) -> a.compareTo(b));
		Collections.sort(list, (a, b) -> a.compareToIgnoreCase(b));
		log.debug("{}", list.toString());
	}
	
	private static void listInteger() {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(1);
		list.add(2);
		log.debug("{}", list.toString());
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		log.debug("{}", list.toString());
	}
	
	private static void listString() {
		List<String> list = new ArrayList<>();
		list.add("3");
		list.add("2");
		list.add("1");
		log.debug("{}", list.toString());
		Collections.sort(list, new Comparator<String>() {
			public int compare(String obj1, String obj2) {
				// TODO Auto-generated method stub
				return obj1.compareToIgnoreCase(obj2);
			}
		});
		log.debug("{}", list.toString());
	}
	
//	public static void main(String[] args) {
//        List<Person> people = Arrays.asList(
//                new Person("Joe", 24),
//                new Person("Pete", 18),
//                new Person("Chris", 21)
//        );
//        Collections.sort(people, (a, b) -> a.name.compareToIgnoreCase(b.name));
//        System.out.println(people);
//        Collections.sort(people, (a, b) -> a.age < b.age ? -1 : a.age == b.age ? 0 : 1);
//        System.out.println(people);
//    }
}

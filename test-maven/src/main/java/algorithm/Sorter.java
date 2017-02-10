package algorithm;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sorter {

	private static Logger log = LoggerFactory.getLogger(Sorter.class);

	public static void main(String[] args) {
		// TreeSet is an implementation of SortedSet
		SortedSet<Employee> set = new TreeSet<Employee>();

		set.add(new Employee("Ashraf", 60));
		set.add(new Employee("Sara", 50));
		set.add(new Employee("Mohamed", 10));
		set.add(new Employee("Esraa", 20));
		set.add(new Employee("Bahaa", 40));
		set.add(new Employee("Dalia", 30));

		// Iterating over the employees in the set
		log.debug("{}", "Set after sorting:");
		Iterator it = set.iterator();
		while (it.hasNext()) {
			// Get employee name and age
			Employee epm = (Employee) it.next();
			log.debug("{} {}", epm.getName(), epm.getAge());
		}
		// Test comparator(), comparator will be null as we are using the
		// Comparable interface
		log.debug("{}", set.comparator());

		// Test first()
		log.debug("{}", set.first().getName());

		// Test last()
		log.debug("{}", set.last().getName());

		// Test headSet()
		log.debug("{}", "headSet(); result:");
		SortedSet headSet = set.headSet(new Employee("Dalia", 30));
		// Iterating over the employees in the headSet

		Iterator headSetIt = headSet.iterator();
		while (headSetIt.hasNext()) {
			// Get employee name and age
			Employee epm = (Employee) headSetIt.next();
			log.debug("Employee {}  his age: {}", epm.getName(), epm.getAge());
		}
		// Test subSet()
		System.out.println("subSet() result:");

		SortedSet subSet = set.subSet(new Employee("Mohamed", 10), new Employee("Sara", 50));

		// Iterating over the employees in the subSet

		Iterator subSetIt = subSet.iterator();

		while (subSetIt.hasNext()) {
			// Get employee name and age
			Employee epm = (Employee) subSetIt.next();
			System.out.println("Employee " + epm.getName() + " his age: " + epm.getAge());
		}
		// Test tailSet()
		System.out.println("tailSet() result:");
		SortedSet tailSet = set.tailSet(new Employee("Bahaa", 40));
		// Iterating over the employees in the tailSet
		Iterator tailSetIt = tailSet.iterator();
		while (tailSetIt.hasNext()) {
			// Get employee name and age
			Employee epm = (Employee) tailSetIt.next();
			System.out.println("Employee " + epm.getName() + " his age: " + epm.getAge());
		}
	}
}

package datastructor;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedListExample {
	
	private static final Logger log = LoggerFactory.getLogger(LinkedListExample.class);
	
	public static void main(String args[]) {

		/* Linked List Declaration */
		LinkedList<String> linkedlist = new LinkedList<String>();

		// add add(0) addFirst addLast remove(0) removeFirst, removeLast, set(0)
		
		/*
		 * add(String Element) is used for adding the elements to the linked
		 * list
		 */
		linkedlist.add("Item1");
		linkedlist.add("Item5");
		linkedlist.add("Item3");
		linkedlist.add("Item6");
		linkedlist.add("Item2");

		/* Display Linked List Content */
		log.debug("Final Content: " + linkedlist);

		/* Add First and Last Element */
		linkedlist.addFirst("First");
		log.debug("Final Content: " + linkedlist);
		linkedlist.addLast("Last");
		log.debug("Final Content: " + linkedlist);

		/* This is how to get and set Values */
		Object firstvar = linkedlist.get(0);
		log.debug("Final Content: " + firstvar);
		linkedlist.set(0, "Changed first item");
		Object firstvar2 = linkedlist.get(0);
		log.debug("Final Content: " + firstvar2);

		/* Remove first and last element */
		linkedlist.removeFirst();
		linkedlist.removeLast();
		log.debug("Final Content: " + linkedlist);

		/* Add to a Position and remove from a position */
		linkedlist.add(0, "Newly added item");
		log.debug("Final Content: " + linkedlist);
		linkedlist.remove(2);
		log.debug("Final Content: " + linkedlist);
	}
}

package ThreadTest.VendingMachine;

import java.util.Stack;

public class VendingMachine {

	Stack<String> store = new Stack<String>();

	// For consumer
	public synchronized String getDrink() {
		while (store.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return store.pop().toString();
	}

	// For producer
	public synchronized void putDrink(String drink) {
		this.notify();
		store.push(drink);
	}
}

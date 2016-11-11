package threadtest.vendingmachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(Producer.class);
	
	private VendingMachine vm;
	
	public Producer(VendingMachine vm) {
		this.vm = vm;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			vm.putDrink("음료수 No." + i);
			log.debug(Thread.currentThread().getName() + ": 음료수 No." + i + " 넣음");			
		}
	}
}

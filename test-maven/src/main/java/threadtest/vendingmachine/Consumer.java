package threadtest.vendingmachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	private VendingMachine vm;

	public Consumer(VendingMachine vm) {
		this.vm = vm;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			String drink = vm.getDrink();
			log.debug(Thread.currentThread().getName() + ": " + drink + " 꺼내먹음");
			
		}
	}

}

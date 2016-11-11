package threadtest.washroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Washroom {
	
	private static final Logger log = LoggerFactory.getLogger(Washroom.class);

	public synchronized void openDoor(String name) {
		log.debug(name + "입장");
		for (int i = 0; i < 1000000; i++) {
			if (i == 10000) {
				log.debug(name + "끄으응!");
			}			
		}
		log.debug(name + "아 시원해..");
	}
}

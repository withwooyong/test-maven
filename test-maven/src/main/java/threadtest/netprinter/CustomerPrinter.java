package threadtest.netprinter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 소비자 (컴퓨터)
 * @author user
 *
 */
public class CustomerPrinter implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerPrinter.class);
	
	private IQueue pq;
	private String name;
	
	public CustomerPrinter(IQueue pq, String idx) {
		this.pq = pq;
		name = "사용자" + idx;
	}

	@Override
	public void run() {
		try {
			//log.debug(name + ":" + pq.pop().toString());
			log.debug(name + ":" + pq.pop().toString() + ":" + pq.size());
//			while (!Thread.currentThread().isInterrupted()) {
//				log.debug(name + ":" + pq.pop().toString());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}

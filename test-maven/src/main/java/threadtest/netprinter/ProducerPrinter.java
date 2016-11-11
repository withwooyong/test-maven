package threadtest.netprinter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 생성자 (프린터역할)
 * @author user
 *
 */
public class ProducerPrinter implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(ProducerPrinter.class);
	
	private IQueue pq;
	
	public ProducerPrinter(IQueue pq) {
		this.pq = pq;
	}
	
	@Override
	public void run() {
		try {
			pq.put("1");
			log.debug(""+pq.size());
			pq.put("2");
			log.debug(""+pq.size());			
			pq.put("3");
			log.debug(""+pq.size());
//			int i = 0;
//			while (!Thread.currentThread().isInterrupted()) {
//				log.debug("############ " + i);
//				pq.put(new Integer(i++));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

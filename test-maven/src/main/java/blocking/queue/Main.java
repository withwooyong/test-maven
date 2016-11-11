package blocking.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws InterruptedException {
		
//		final ExecutorService service = Executors.newFixedThreadPool(1);
//		final BlockingQueue<Document> bqueue = new PriorityBlockingQueue<Document>();
//		service.execute(new AddDocumentThread(bqueue));
//		service.execute(new PrintDocumentThread(bqueue));
//		service.shutdown();
		
		final BlockingQueue<Document> bqueue = new PriorityBlockingQueue<Document>();
		bqueue.offer(new Document(1, "AFSD"));
		Document document = bqueue.take();
		log.debug(""+document.getDocId());
		log.debug(document.getDocName());
	}
}
package blocking.queue;

import java.util.concurrent.BlockingQueue;

public class PrintDocumentThread implements Runnable {
	BlockingQueue<Document> bqueue;

	public PrintDocumentThread(BlockingQueue<Document> bqueue) {
		this.bqueue = bqueue;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				Document doc = bqueue.take();
				System.out.println(doc.getDocName() + " with id:" + doc.getDocId() + " printed");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
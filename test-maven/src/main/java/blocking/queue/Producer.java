package blocking.queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	protected BlockingQueue<Object> queue = null;

	public Producer(BlockingQueue<Object> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			queue.put("1");
			Thread.sleep(1000);
			queue.put("2");
			Thread.sleep(1000);
			queue.put("3");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

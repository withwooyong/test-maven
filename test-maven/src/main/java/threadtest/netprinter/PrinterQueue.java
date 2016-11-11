package threadtest.netprinter;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrinterQueue implements IQueue {
	
	private static final Logger log = LoggerFactory.getLogger(PrinterQueue.class);
	
	private static final String name = "PRINTER_QUEUE";
	private static final Object monitor = new Object();
	private LinkedList jobs = new LinkedList();
	
	// 프린터큐는 한개의 객체만 생성되어야 한다.(아무리 잡이 많아도 큐에 있다가 잡 수행한다.)
	// 싱글톤으로 설정한다.
	private static PrinterQueue pq = new PrinterQueue();
	private PrinterQueue() {} // 외부에서 객체를 생성못하게 함.
	
	public static PrinterQueue getInstance() {
		if (pq == null) {
			synchronized (PrinterQueue.class) { // 동시에 생성되면 안됨
				pq = new PrinterQueue();
			}
		}
		return pq;
	}
	
	
	public String getName() {
		return name;
	}

	
	public void clear() {
		synchronized (monitor) {
			jobs.clear();
		}
	}
	
	public void put(Object obj) {
		synchronized (monitor) {
			jobs.addLast(obj);
			monitor.notify(); // 데이터가 없을때 잠들었던걸 깨운다.
		}
	}
	
	public Object pop() throws InterruptedException, NoSuchElementException {
		Object obj = null;
		synchronized (monitor) {
			if (jobs.isEmpty()) {
				monitor.wait(); // 아무것도 없으면 대기
			} else {
				obj = jobs.removeFirst();
			}
		}
		if (obj == null) {
			throw new NoSuchElementException();
		}
		return obj;
	}
	
	public int size() {
		return jobs.size();
	}
}

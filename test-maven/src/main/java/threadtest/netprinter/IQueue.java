package threadtest.netprinter;

import java.util.NoSuchElementException;

public interface IQueue {
	
	public String getName();
	public void clear();
	public void put(Object obj);
	public Object pop() throws InterruptedException, NoSuchElementException;
	public int size();
}

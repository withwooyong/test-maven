package ThreadTest.VendingMachine;

public class SyncThreadTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		VendingMachine vm = new VendingMachine();
		
		Producer p = new Producer(vm);
		Consumer c = new Consumer(vm);
		
		Thread t1 = new Thread(p, "Scott"); 
		t1.start();
		
		Thread.sleep(1000);
		
		Thread t2 = new Thread(c, "Andrew"); 
		t2.start();
	}

}

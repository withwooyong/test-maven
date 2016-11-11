package ThreadTest.NetPrinter;

/**
 * synchronized = Constructor/Consumer
 * 네트워크 프린터 예
 * 생성자 / 소비자 패턴 이용
 * 프린터 한대를 같은 네트워크에 연결된 컴퓨터 여러대에서 사용한다는 가정에서 출발
 * 프린터 : 소비자, 컴퓨터 : 생성자  
 * @author user
 *
 */
public class NetPrinter {

	public static void main(String[] args) throws InterruptedException {
		
		IQueue pq = PrinterQueue.getInstance();
		
		Thread pro = new Thread(new ProducerPrinter(pq)); 
		pro.start();
		
		Thread.sleep(1000);
		
		Thread con1 = new Thread(new CustomerPrinter(pq, "1")); con1.start();
		Thread con2 = new Thread(new CustomerPrinter(pq, "2")); con2.start();
		Thread con3 = new Thread(new CustomerPrinter(pq, "3")); con3.start();	
				
		pro.interrupt();
		con1.interrupt();
		con2.interrupt();
		con3.interrupt();
	}
}

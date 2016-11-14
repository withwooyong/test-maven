package lamda;


import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://jdm.kr/blog/181
 * ( parameters ) -> expression body
 * ( parameters ) -> { expression body }
 * () -> { expression body }
 * () -> expression body
 * @author user
 *
 */
public class LamdaTest {
	
	private static Logger log = LoggerFactory.getLogger(LamdaTest.class);

	public static void main(String[] args) {
//		threadTest();
//		interfaceTest();
		//streamApiTest();
		test();
	}
	
	public static void test() {
		String a = "test";
		log.debug("{}", a);
//		(a) -> { System.out.println(a); };
//		a -> System.out.println(a);
		
		
	}
	
	public static void threadTest() {
		// Thread - traditional
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("test");
			}
		}).start();
		
		// Thread - Lambda Expression
		new Thread(()->{
			System.out.println("Hello World.");
		}).start();
		
		new Thread(()->{
			System.out.println("ttt");
		}).start();
	}
	
	public static void interfaceTest() {
		Func add = (int a, int b) -> a + b;
		
		log.debug("{}", add.calc(1,  2));
		log.debug("{}", add.calc(2,  3));
		
		Func sub = (int a, int b) -> a - b;		
		log.debug("{}", sub.calc(2,  1));
		
		Func add2 = (int a, int b) -> { return a + b; };		
		int result = add.calc(1,2) + add2.calc(3, 4); // 10
		log.debug("result={}", result);
	}
	
	public static void streamApiTest() {
//		Arrays.asList(1,2,3).stream(); // (1)
//		Arrays.asList(1,2,3).parallelStream(); // (2)		
//		Arrays.asList(1,2,3).stream().forEach(System.out::println); // 1,2,3
//		Arrays.asList(1,2,3).stream().limit(1).forEach(System.out::println); // 1
//		Arrays.asList(1,2,3).stream().skip(1).forEach(System.out::println); // 2,3
//		Arrays.asList(1,2,3).stream().filter(i-> 2>=i).forEach(System.out::println); // 1,2
//		Arrays.asList(Arrays.asList(1,2),Arrays.asList(3,4,5),Arrays.asList(6,7,8,9)).stream().flatMap(i -> i.stream()).forEach(System.out::println); // 1,2,3,4,5,6,7,8,9
		Arrays.asList(1,2,3).stream().reduce((a,b)-> a-b).get(); // -4
		System.out.println(Arrays.asList(1,2,3).stream().reduce((a,b)-> a-b).get());
		
		Arrays.asList(1,2,3).stream().collect(Collectors.toList());
		Arrays.asList(1,2,3).stream().iterator();
	}
}

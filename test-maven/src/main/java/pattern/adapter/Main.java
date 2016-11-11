package pattern.adapter;

public class Main {

	/**
	 * 어댑터 패턴 (adaptor pattern)
	 * 객체를 감싸서 다른 인터페이스를 제공한다.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Print p = new PrintBanner("print1");
		p.printWeak();
		p.printStrong();
		
		Print p2 = new PrintBanner2("print2");
		p2.printWeak();
		p2.printStrong();
	}
}

package pattern.singleton;

public class SingletonMain {
	
	/**
	 * 객체(집합)  
	 *  - 클래스(부분집합), 설계도 
	 *  - 인스턴스(부분집합),
	 *  
	 * 하나의 인스턴스만 생성한다.
	 * 개발중인 시스템에서 스피커에 접근할 수 있는 클래스를 만들어 주세요.
	 * @param args
	 */
	public static void main(String[] args) {
		
		SystemSpeaker speaker1 = SystemSpeaker.getInstance();
		SystemSpeaker speaker2 = SystemSpeaker.getInstance();
		
		// 5, 5
		speaker1.setVolume(5);
		System.out.println(speaker1.getVolume());
		System.out.println(speaker2.getVolume());
		
		speaker1.setVolume(11);
		System.out.println(speaker1.getVolume());
		System.out.println(speaker2.getVolume());
		
	}
}

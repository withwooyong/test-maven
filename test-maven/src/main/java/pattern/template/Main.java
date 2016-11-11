package pattern.template;

import pattern.template.pack.AbstractGameConnectHelper;
import pattern.template.pack.DefaultGameConnectHelper;

public class Main {
	
	/**
	 * 템플릿 메소드 패턴 (template method pattern)
	 * 알고리즘의 개별 단계를 구현하는 방법을 서브클래스에서 결정한다.
	 * 
	 * 일정한 프로세스를 가진 요구사항을 템플릿 메소드 패턴을 이용하여 구현할 수 있다.
	 * 
	 * 알고리즘을 여러단계로 나눈다.
	 * 나눠진 알고리즘의 단계를 메소드로 선언한다.
	 * 알고리즘을 수행할 템플릿 메소드를 만든다.
	 * 하위 클래스에서 나눠진 메소드들을 구현한다.
	 * @param args
	 */
	public static void main(String[] args) {
		
		AbstractGameConnectHelper helper = new DefaultGameConnectHelper();
		helper.requestConnection("아이디 암호 등 접속정보");
	}
}

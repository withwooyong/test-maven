package pattern.factory;

import pattern.factory.concreate.HpCreator;
import pattern.factory.concreate.MpCreator;
import pattern.factory.framework.Item;
import pattern.factory.framework.ItemCreator;

public class FactoryMain {

	/**
	 * 팩토리메소드 패턴에서 템플릿 메소드 패턴의 사용됨을 안다.
	 * 팩토리메소드 패턴에서의 구조와 구현의 분리를 이해하고 장점을 안다.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		
		ItemCreator creator = new HpCreator();
		Item item = creator.create();
		creator.create();
		item.use();
		System.out.println("=============");
		creator = new MpCreator();
		item = creator.create();
		creator.create();
		item.use();
		System.out.println("=============");
	}
}

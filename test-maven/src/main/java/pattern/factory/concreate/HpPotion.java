package pattern.factory.concreate;

import pattern.factory.framework.Item;

public class HpPotion implements Item {

	@Override
	public void use() {
		System.out.println("체력회복");

	}

}

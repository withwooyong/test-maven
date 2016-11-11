package pattern.factory.framework;

public abstract class ItemCreator {	
	
	// 아이템을 생성하기 전에 데이터 베이스에서 아이템 정보를 요청합니다.
	abstract protected void requestItemsInfo();
	
	// 아이템을 생성하기 전에 데이터 베이스에서 아이템 정보를 요청합니다.
	abstract protected void createItemLog();
	
	// 아이템을 생성
	abstract protected Item createItem();
	
	public Item create() {
		Item item;
		requestItemsInfo();		
		item = createItem();
		createItemLog();
		return item;
	}
	
}

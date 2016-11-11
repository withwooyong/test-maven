package pattern.prototype.deep;

public class deepMain {

	/**
	 * 값을 복사 = 깊은복사
	 * 어떤 클래스의 인스턴스를 만드는 것이 자원/시간을 많이 잡아먹거나 복잡한 경우 Cloneable copy 사용
	 * @param args
	 * @throws CloneNotSupportedException 
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		
		Cat navi = new Cat();
		navi.setName("navi");
		navi.setAge(3);
		
		Cat yo = navi.copy();
		yo.setName("yo");
		navi.setAge(1);
		
		System.out.println(navi.getName());
		System.out.println(yo.getName());
		
		System.out.println(navi.getAge());
		System.out.println(yo.getAge());
		
	}

}

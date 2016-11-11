package pattern.prototype;

public class prototypeMain {

	/**
	 * 프로토 타입 패턴을 통해서 복잡한 인스턴스를 복사할 수 있다.
	 * 생산비용이 높은 인스턴스를 복사를 통해서
	 * 클래스로부터 인스턴스 생성이 어려운 경우 사용한다.
	 * @param args
	 * @throws CloneNotSupportedException 
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		
		Circle circle1 = new Circle(1, 1, 3);
		Circle circle2 = circle1.copy();
		
		System.out.println(circle1.getX() + "," + circle1.getY() + "," + circle1.getR());
		System.out.println(circle2.getX() + "," + circle2.getY() + "," + circle2.getR());
	}

}

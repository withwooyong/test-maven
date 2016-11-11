package pattern.builder;

public class builderMain {

	/**
	 * 복잡한 단계를 거쳐야 생성되는 객체의 구현을 서브클래스에게 넘겨주는 패턴
	 * 많은 인자를 가진 객체 생성을 다른 객체의 도움으로 생성하는 패턴
	 * @param args
	 */
	public static void main(String[] args) {
		
		ComputerFactory factory = new ComputerFactory();
		factory.setSpec(new LgGramPc());		
		factory.make();
		Computer computer = factory.getComputer();
		System.out.println(computer.toString());
		
		ComputerFactory factory2 = new ComputerFactory();
		factory2.setSpec(new SamsungPc());		
		factory2.make();
		Computer computer2 = factory2.getComputer();
		System.out.println(computer2.toString());
		

	}

}

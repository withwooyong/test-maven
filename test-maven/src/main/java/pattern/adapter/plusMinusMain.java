package pattern.adapter;

public class plusMinusMain {

	public static void main(String[] args) {
		
		plusminus p = new plusminusImpl();		
		System.out.println(p.plus(1,  2));
		System.out.println(p.minus(2,  1));
	}
}

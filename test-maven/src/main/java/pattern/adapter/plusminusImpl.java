package pattern.adapter;

public class plusminusImpl implements plusminus {
	
	public plusminusImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int plus(int a, int b) {
		return a + b;
	}
	
	@Override
	public int minus(int a, int b) {
		return a - b;
	}
}

package pattern.adapter;

public class PrintBanner2 extends Banner implements Print {

	public PrintBanner2(String string) {
		super(string);
	}

	@Override
	public void printWeak() {
		showWithParen();
	}

	@Override
	public void printStrong() {
		showWithAster();
	}
	
}
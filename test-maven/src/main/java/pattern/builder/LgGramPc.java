package pattern.builder;

public class LgGramPc extends Spec {
	 
	private Computer computer;
	
	public LgGramPc() {
		computer = new Computer("cpu", "ram", "storage");
	}
	
	@Override
	public void setCpu() {		
		computer.setCpu("i7");
	}

	@Override
	public void setRam() {
		computer.setRam("8g");
	}

	@Override
	public void setStorage() {
		computer.setStorage("256g ssg");
	}
	
	@Override
	public Computer getComputer() {
		return computer;
	}
}

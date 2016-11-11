package pattern.builder;

public class SamsungPc extends Spec {
	 
	private Computer computer;
	
	public SamsungPc() {
		computer = new Computer("cpu", "ram", "storage");
	}
	
	@Override
	public void setCpu() {		
		computer.setCpu("i5");
	}

	@Override
	public void setRam() {
		computer.setRam("16g");
	}

	@Override
	public void setStorage() {
		computer.setStorage("128g ssg");
	}
	
	@Override
	public Computer getComputer() {
		return computer;
	}
}

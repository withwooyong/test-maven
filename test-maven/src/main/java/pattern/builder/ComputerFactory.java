package pattern.builder;

public class ComputerFactory {
	
	private Spec spec;
	
	public void setSpec(Spec spec) {
		this.spec = spec;
	}
	
	public void make() {
		spec.setRam();
		spec.setCpu();
		spec.setStorage();		
	}
	
	public Computer getComputer() {
		return spec.getComputer();
	}
	
}

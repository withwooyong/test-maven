package pattern.builder;

public abstract class Spec {

	abstract public void setCpu();
	abstract public void setRam();
	abstract public void setStorage();
	abstract public Computer getComputer();
	
}

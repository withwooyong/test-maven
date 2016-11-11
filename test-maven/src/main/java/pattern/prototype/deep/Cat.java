package pattern.prototype.deep;

import lombok.Data;

@Data
public class Cat implements Cloneable {
	
	private String name;
	private Integer age;	

	public Cat copy() throws CloneNotSupportedException {
		Cat ret = (Cat) this.clone();
		return ret;
	}

}

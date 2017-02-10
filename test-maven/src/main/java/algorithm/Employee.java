package algorithm;

import java.io.Serializable;

public class Employee implements Comparable<Employee>, Serializable {
	
	private static final long serialVersionUID = -7294602850248845812L;
	
	private String name;
	private int age;
	
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Employee o) {
		// ascending order 
		return this.age - o.getAge();
		
		// descending order
//		return o.getAge() - this.age;		
	}

}

package lamda;

import lombok.Data;

@Data
public class Fruit {

	public String name;
    public int price;
 
    public Fruit(String name, int price) {
        super();
        this.name = name;
        this.price = price;
    }
}

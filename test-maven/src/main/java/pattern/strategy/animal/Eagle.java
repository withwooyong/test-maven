package pattern.strategy.animal;

import pattern.strategy.cry.BirdCry;
import pattern.strategy.fly.FlyWithWings;

public class Eagle extends Animal {
	
	public Eagle(){
		
		cry = new BirdCry();
		
		fly = new FlyWithWings();
	}
	
	public void display(){
		
		System.out.println("독수리");
	}

}

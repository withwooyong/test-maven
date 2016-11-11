package pattern.strategy;

import pattern.strategy.animal.Animal;
import pattern.strategy.animal.Eagle;
import pattern.strategy.animal.Tiger;
import pattern.strategy.animal.Turtle;

public class PlayAnimal {
	
	public static void main(String[] args){			// main 함수 실행
		
		Animal tiger = new Tiger();				// Animal 클래스를 상속받은 Tiger 객체 생성
		
		tiger.display();						// Tiger의 display() 함수 실행
		tiger.performCry();						// Tiger의 performCry() 함수 실행
		tiger.performFly();						// Tiger의 performFly() 함수 실행		
		
		System.out.println("------------------");
		
		Animal eagle = new Eagle();				// Animal 클래스를 상속받은 Eagle 객체 생성		
		
		eagle.display();						// Eagle의 display() 함수 실행
		eagle.performCry();						// Eagle의 performCry() 함수 실행
		eagle.performFly();						// Eagle의 performFly() 함수 실행
		
		System.out.println("------------------");
		
		Animal turtle = new Turtle();			// Animal 클래스를 상속받은 Turtle 객체 생성	
		
		turtle.display();						// Turtle의 display() 함수 실행
		turtle.performCry();					// Turtle의 performCry() 함수 실행		
		turtle.performFly();					// Turtle의 performFly() 함수 실행
	}

}

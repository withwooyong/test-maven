package algorithm.recursion;

/**
 * 제너릭 타입(Generic Types)은 주로 자바 컬렉션에서 많이 사용되고 있다. 
 * 컬렉션은 자료구조이다. 
 * 컬렉션에는 어떤 자료를 담을지 알 수 없으므로 최상위 객체인 Object형태로 저장되고 관리되도록 설계되어 있다. 
 * 하지만, 의도하지 않은 자료형이 담기는 경우도 발생하게 된다. 
 * 이 때의 오류는 컴파일시에는 알 수가 없고 실행을 시켜보아야만 알 수 있다는 것이 문제점이었다. 
 * 제너릭 타입을 사용하면 프로그래머가 원하는 객체의 타입을 명시해서 의도하지 않은 객체는 저장될 수 없도록 컴파일시에 오류를 확인할 수있게 된다.
 * 
 * 타입 매개변수는 하나의 대문자를 사용한다. 
 * 이들은 파일시스템에 실재로 존재하는 것은 아니다. 
 * 즉, T.java 라던지 T.class라는 파일은 없다. 
 * 타입매개변수를 여러개 사용할 수도 있지만 하나의 선언문에서 두 번 사용될 수는 없다. 
 * 즉, Box<T, U>는 가능하지만 Box<T, T>는 안된다.
 * 
 * E - Element (자바의 컬렉션에서 널리 사용되고 있다.)
 * K - Key
 * N - Number
 * T - Type
 * V - Value
 * S,U,V etc. - 2nd, 3rd, 4th types
 * @author user
 *
 * @param <T>
 */
public class Generics<T> {

	private T t;

	public void add(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	public <U> void inspect(U u) {
		System.out.println("T: " + t.getClass().getName());
		System.out.println("U: " + u.getClass().getName());
	}

	public static void main(String[] args) {
		Generics<Integer> integerBox = new Generics<Integer>();
		integerBox.add(new Integer(10));
		integerBox.inspect("some text");
	}	

}

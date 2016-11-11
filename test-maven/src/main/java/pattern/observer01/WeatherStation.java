package pattern.observer01;

/**
 * 옵저버 패턴(Observer Pattern)에서는 한 객체의 상태가 바뀌면 그 객체에 의존하는 다른 객체들한테 연락이 가고 자동으로 내용이 갱신되는 방식으로 일대다(one-to-many) 의존성을 정의합니다.
 * 
 * ① Observable 클래스를 상속한 클래스를 만들고, 새로운 데이터가 들어오면 setChanged(), notifyObservers()를 호출하도록 구현한다.
 * ② Observer 를 implements한 클래스를 만들고 Observable에 addObserver(this)로 자신을 Observer로 등록한다. update() 함수를 구현하여, 전달받은 데이터를 처리해준다.
 * 
 * 2. JDK 내장 옵저버 패턴의 단점과 한계 (java.util.Observable)
 * 옵저버 패턴은 JAVA에서 많이 사용하고 유용한 패턴이다. 
 * 그래서 JAVA에 내장 API로 나왔을 것이다. 
 * 위에서 본 바와 같이 JAVA 내장 API를 사용하면 옵저버 패턴 구현이 정말 간단하다. 
 * 하지만 이 내장 API에 단점이 있고 한계가 있다.
 * java.util.Observable은 인터페이스가 아닌 클래스인 데다가, 어떤 인터페이스를 구현하는 것도아니다. 
 * 따라서 java.util.Observable 구현에는 활용도와 재사용성에 있어서 제약조건으로 작용하는 몇 가지 문제점이 있다.
 * 
 * (1) Observable은 클래스다.
 * 첫 번째로, Observable이 클래스기 때문에 서브클래스를 만들어야 한다는 점이 문제다. 
 * 이미 다른 수퍼클래스를 확장하고 있는 클래스에 Observable의 기능을 추가할 수 없기 때문이다. 
 * 그래서 재사용성에 제약이 생긴다.
 * 두 번째로, Observable 인터페이스라는 것이 없기 때문에 자바에 내장된 Observer API하고 잘 맞는 클래스를 직접 구현하는 것이 불가능하다. 
 * java.util 구현을 다른 구현으로 바꾸는 것도 불가능하죠. (ex: 멀티스레드 구현)
 * 
 * (2) Observable 클래스의 핵심 메소드를 외부에서 호출할 수 없다.
 * Observable API를 살펴보면, setChanged() 메소드가 protected로 선언되어 있다. 
 * Observable의 서브클래스에서만 setChanged()를 호출할 수 있다. 
 * 결국 직접 어떤 클래스를 만들고, Observable의 서브클래스를 인스턴스 변수로 사용하는 방법도 쓸 수 없다. 
 * 이런 디자인은 상속보다는 구성을 사용한다는 디자인 원칙에도 위배된다.
 * 
 * (3) 해결책?
 * java.util.Observable을 확장한 클래스를 쓸 수 있는 상황이라면 Observable API를 사용하는 것도 괜찮지만, 여의치 않다면 직접 구현하는 방법도 있다. 
 * 확장과 재사용성을 고려하여 인터페이스로 구현하는 것도 어렵지 않다.
 * @author user
 *
 */
public class WeatherStation {

	static WeatherData weatherData; // weatherData Import

	static CurrentConditionDisplay currentDisplay; //currentConditionDisplay
	static ForecastDisplay forecastDisplay; // forecastDisplay

	public static void weatherStation() { // weatherStation 초기화
		weatherData = new WeatherData(); // WeatherData 객체 생성
		currentDisplay = new CurrentConditionDisplay(weatherData); // CurrentConditionDisplay 생성 (WeatherData에 옵저버 등록)
		forecastDisplay = new ForecastDisplay(weatherData); // ForecastDisplay 생성 (WeatherData에 옵저버 등록)
	}

	// WeatherData의 setMeasurements 함수 실행
	public static void changeWeather(float temp, float humity, float pressure) {
		weatherData.setMeasurements(temp, humity, pressure);
	}

	public static void main(String[] args) {

		weatherStation(); // WeatherStation 생성

		// WeatherStation에서 날씨의 변화를 입력한다.
		System.out.println("-----날씨가 변한다.----");
		changeWeather(40, 50, 10); // WeatherData에 새로운 데이터 전송

		System.out.println("");

		System.out.println("-----날씨가 변한다.----");
		changeWeather(50, 60, 20); // WeatherData에 새로운 데이터 전송

		System.out.println("");

	}

}
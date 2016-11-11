package pattern.observer02;


import pattern.observer02.javaapi.observable.WeatherData;
import pattern.observer02.javaapi.observer.CurrentConditionDisplay;
import pattern.observer02.javaapi.observer.ForecastDisplay;
import pattern.observer02.javaapi.observer.StaticsDisplay;

/**
 * http://hyeonstorage.tistory.com/167
 * Observable, Observer 직접 구현하여 Observer 패턴 구현
 * (1) 인터페이스의 느슨한 결합
 * 두 객체가 느슨하게 결합되어 있다는 것은, 그 둘이 상호작용을 하긴 하지만 서로에 대해 서로 잘 모른다는 것을 의미한다.
 * - Observable은 Observer의 구상 클래스가 무엇인지, 옵저버가 무엇을 하는지 등에 대해서는 알 필요가 없다.
 * - 옵저버는 언제든지 새로 추가할 수 있다. 새로운 형식의 옵저버를 추가하려고 할 때도 Observable을 전혀 변경할 필요가 없다.
 * - Observable과 Observer는 서로 독립적으로 재사용할 수 있다.
 * - Observable이나 Observer가 바뀌더라도 서로한테 영향을 미치지는 않는다.
 * 느슨하게 결합하는 디자인을 사용하면 변경 사항이 생겨도 무난히 처리할 수 있는 유연한 객체지향 시스템을 구축할 수 있다.
 * 
 * @author user
 *
 */
public class WeatherEnvironment {
	
	static WeatherData weatherData;					// Observable WeatherData
	
	static CurrentConditionDisplay currentDisplay;	// 현재 온도 출력 장비
	static ForecastDisplay forecastDisplay;			// 기압 변화 출력 장비
	static StaticsDisplay staticsDisplay;			// 온도 통계 장비
	
	
	// WeatherData 객체 생성
	public static void weatherStation(){
		weatherData = new WeatherData();
	}
	
	// weatherData에 데이터를 전달한다.
	public static void changeWeather(float temp, float humity, float pressure) {
		
		weatherData.setMeasurements(temp, humity, pressure);

	}


		// 디스플레이 장비 객체를 생성한다.
	public static void clientDisplay(){
		staticsDisplay = new StaticsDisplay(weatherData);			// staticsDisplay 객체 생성하면서 옵저버로 등록
		currentDisplay = new CurrentConditionDisplay(weatherData);	// currentDisplay 객체 생성하면서 옵저버로 등록
		forecastDisplay = new ForecastDisplay(weatherData);			// forecastDisplay 객체 생성하면서 옵저버로 등록
		
	}
	
	// 현재 각 장비의 저장 데이터 출력
	public static void requestCurrentWeather(){
		currentDisplay.display();
		forecastDisplay.display();
		staticsDisplay.display();
	}
	
	
	
	public static void main(String[] args){
		
		weatherStation();		// weather 객체 생성
		clientDisplay();		// 디스플레이 장비 객체 생성
		
		
		// WeatherStation에서 날씨의 변화를 입력한다.
		System.out.println("-----날씨가 변한다.----");
		changeWeather(40, 50, 10);					// 온도 40, 습도 50, 기압 10 데이터 weatherData에 전달
		System.out.println("");
		
		System.out.println("-----날씨가 변한다.----");
		changeWeather(50, 60, 20);					// 온도 50, 습도 60, 기압 20 데이터 weatherData에 전달
		System.out.println("");
		
		System.out.println("----사용자가 현재의 날씨를 요청한다.----");
		changeWeather(50, 60, 20);					// 온도 50, 습도 60, 기압 20 데이터 weatherData에 전달
		
	}
	


}

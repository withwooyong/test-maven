package pattern.observer02.javaapi.observer;

import java.util.Observable;
import java.util.Observer;

import pattern.observer02.javaapi.observable.WeatherData;

/**
 * @uml.dependency   supplier="observer.WeatherData"
 */
public class CurrentConditionDisplay implements Observer, DisplayElement{    // Observer, DisplayElement implements
	
	/**
	 * @uml.property  name="observable"
	 */
	Observable observable;								// 등록될 Observable
	/**
	 * @uml.property  name="temperature"
	 */
	private float temperature;						// 온도
	/**
	 * @uml.property  name="humidity"
	 */
	private float humidity;							// 습도
	
	public CurrentConditionDisplay(Observable observable){		 // 생성자 
		this.observable = observable;							// 등록될 Observable을 import
		observable.addObserver(this);							// this(CurrentConditionPlay) 옵저버로 등록
	}
	
	public void update(Observable obs, Object arg){				// update 로 새로운 데이터 갱신
		if(obs instanceof WeatherData){							// Observable이 WeatherData인지 확인
			WeatherData weatherData = (WeatherData)obs;			// WeatherData로  변환
			this.temperature = weatherData.getTemperature();	// 온도 값 갱ㅇ신
			this.humidity = weatherData.getHumidity();			// 습도값 갱신
			display();											// 최신 값 출력
		}
	}

	@Override
	public void display() {										// 출력
		System.out.println("현재 온도 : " + temperature + "도,  현재 습도 : " + humidity + "%");
		
	}

}

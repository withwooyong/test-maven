package pattern.observer02.javaapi.observer;

import java.util.Observable;
import java.util.Observer;

import pattern.observer02.javaapi.observable.WeatherData;


/**
 * @uml.dependency   supplier="java.util.Observable"
 */
public class StaticsDisplay implements Observer, DisplayElement{	// Observer, DisplayElement implements
	
	/**
	 * @uml.property  name="observable"
	 */
	Observable observable;						// 등록될 Observable
	/**
	 * @uml.property  name="maxTemp"
	 */
	private float maxTemp = 0.0f;				// 최대 혼도
	/**
	 * @uml.property  name="minTemp"
	 */
	private float minTemp = 200;				// 최저 온도
	/**
	 * @uml.property  name="tempSum"
	 */
	private float tempSum= 0.0f;				// 온도 합
	/**
	 * @uml.property  name="numReadings"
	 */
	private int numReadings;					// 데이터 수신 횟수
	
	public StaticsDisplay(Observable observable) {		// 생성자
		this.observable = observable;					// 등록될 Observable을 import
		observable.addObserver(this);					// this(CurrentConditionPlay) 옵저버로 등록
	}
	
	// update 로 새로운 데이터 갱신
	public void update(Observable obs, Object arg) {
		
		if(obs instanceof WeatherData){						// Observable이 WeatherData인지 확인
			
			WeatherData weatherData = (WeatherData)obs;
			float temp = weatherData.getTemperature();
			
			this.tempSum += temp;							// 온도 합 추가
			numReadings++;									// 데이터 수신 횟수 증가

			if (temp > maxTemp) {							// 최대 혼도 기록				
				maxTemp = temp;		
			}
	 
			if (temp < minTemp) {							// 최저 온도 기록
				minTemp = temp;
			}

			display();										// 화면 출력
		}
	}
	
	// 화면 출력
	public void display() {
		System.out.println("평균 온도 : "+ (tempSum / numReadings) +" , 최대 온도 : "+ maxTemp + ", 최저 온도 : " + minTemp);
	}

}

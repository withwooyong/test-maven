package pattern.observer01;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer, DisplayElement { // Observer, DisplayElement implements

	Observable observable; // 등록될 Observable

	private float currentPressure = 29.92f; // 현재 기압 (Default : 29.92f)
	private float lastPressure; // 마지막 기압

	public ForecastDisplay(Observable observable) { // 생성자 
		this.observable = observable; // 등록될 Observable을 import
		observable.addObserver(this); // this(ForecastDisplay) 옵저버로 등록
	}

	public void update(Observable obs, Object arg) { // update 로 새로운 데이터 갱신
		if (obs instanceof WeatherData) { // Observable이 WeatherData인지 확인
			WeatherData weatherData = (WeatherData) obs; // WeatherData로  변환
			this.lastPressure = currentPressure; // 온도 값 갱ㅇ신
			this.currentPressure = weatherData.getPressure(); // 습도값 갱신
			display(); // 최신 값 출력
		}
	}

	public void display() { // 출력
		System.out.print("Forecast: ");
		if (currentPressure > lastPressure) {
			System.out.println("기압 증가");
		} else if (currentPressure == lastPressure) {
			System.out.println("기압 변동 없음");
		} else if (currentPressure < lastPressure) {
			System.out.println("기압 하강");
		}
	}

}

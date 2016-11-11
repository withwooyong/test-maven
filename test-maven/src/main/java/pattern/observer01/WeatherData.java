package pattern.observer01;

import java.util.Observable;

public class WeatherData extends Observable { // java.util.Observable 클래스 상속

	private float temperature; // 온도
	private float humidity; // 습도
	private float pressure; // 기압

	public WeatherData() {

	}

	// 새로운 데이터를 전달 받아 갱신하고 새로운 데이터가 들어왔음을 알린다.
	// 기상스테이션(WeatherStation)에서는 주기적으로 이 함수를 사용해 최신 데이터를 전달한다.
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

	// 갱신할 새로운 데이터 여부의 플래그 값을 변경하고(setChanged())
	// 옵저버들에게 새로운 데이터를 전달한다. (notifyObservers())
	public void measurementsChanged() {
		setChanged();
		notifyObservers();
	}

	// 온도값 반환
	public float getTemperature() {
		return temperature;
	}

	// 습도값 반환
	public float getHumidity() {
		return humidity;
	}

	// 기압값 반환
	public float getPressure() {
		return pressure;
	}

}
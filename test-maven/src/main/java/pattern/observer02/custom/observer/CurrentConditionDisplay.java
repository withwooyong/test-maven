package pattern.observer02.custom.observer;

import pattern.observer02.custom.observable.Observable;
import pattern.observer02.custom.observable.WeatherData;

public class CurrentConditionDisplay implements Observer, DisplayElement{		// Observer, DisplayElement Implements

		private float temperature;
		private float humidity;
		private Observable obs;
		
		// 생성자, CurrentConditionDisplay 를 생성하면서 지정한 Observalbe에 옵저버로 등록한다.
		public CurrentConditionDisplay(Observable obs){
			this.obs = obs;
			obs.addObserver(this);
		}
		
		// Observable에서 데이터를 전달할때, update를 호출한다.
		public void update(Observable obs){
			if(obs instanceof WeatherData){
				WeatherData weatherData = (WeatherData)obs;
				this.temperature = weatherData.getTemperature();
				this.humidity = weatherData.getHumidity();
				display();
			}
		}
		
		public void display(){
			System.out.println("현재 온도 : " + temperature + "도,  현재 습도 : " + humidity + "%");
		}
}

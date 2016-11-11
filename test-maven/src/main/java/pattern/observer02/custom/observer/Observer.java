package pattern.observer02.custom.observer;

import pattern.observer02.custom.observable.Observable;

public interface Observer {
	public void update(Observable observable);
}

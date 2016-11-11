package pattern.observer02.custom.observable;

import pattern.observer02.custom.observer.Observer;

public interface Observable {
	
	public void addObserver(Observer o);
	public void deleteObserver(Observer o);
	public void notifyObservers();

}

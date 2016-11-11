package pattern.singleton;

public class SystemSpeaker {

	static private SystemSpeaker instance;
	private int volume;
	
	private SystemSpeaker() {
		
	}
	
	public static SystemSpeaker getInstance() {
		if (instance == null) {
			instance = new SystemSpeaker();
		} 
		return instance;
	}

	/**
	 * @return the volume
	 */
	public int getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	
	
	
}

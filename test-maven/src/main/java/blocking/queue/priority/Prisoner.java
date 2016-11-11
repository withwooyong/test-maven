package blocking.queue.priority;

public class Prisoner implements Comparable<Prisoner> {
	
	//private static final Logger log = LoggerFactory.getLogger(Prisoner.class);

	String name;
	int weight; // 형량

	public Prisoner(String name, int weight) {
		super();
		this.name = name;
		this.weight = weight;
	}

	@Override
	public int compareTo(Prisoner target) {
		if (this.weight > target.weight) {
			return 1;
		} else if (this.weight < target.weight) {
			return -1;
		}
		return 0;
	}
}

package blocking.queue.priority;

import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriorityQueueTest {
	
	private static final Logger log = LoggerFactory.getLogger(PriorityQueueTest.class);

	public static void main(String[] args) {
		
		PriorityQueue<Prisoner> priorityQueue = getPriorityQueue();

        log.debug("=============== Normal Order");

        while (!priorityQueue.isEmpty()) {
            Prisoner prisoner = priorityQueue.poll();
            log.debug("name=" + prisoner.name + ":weight=" + prisoner.weight);
        }

	}
	
	private static PriorityQueue<Prisoner> getPriorityQueue() {

	    Prisoner prisoner1 = new Prisoner("james", 5);
	    Prisoner prisoner2 = new Prisoner("schofield", 99);
	    Prisoner prisoner3 = new Prisoner("alex", 14);
	    Prisoner prisoner4 = new Prisoner("silvia", 10);
	    Prisoner prisoner5 = new Prisoner("thomson", 1);

	    PriorityQueue<Prisoner> priorityQueue = new PriorityQueue<Prisoner>();

	    priorityQueue.offer(prisoner1);
	    priorityQueue.offer(prisoner2);
	    priorityQueue.offer(prisoner3);
	    priorityQueue.offer(prisoner4);
	    priorityQueue.offer(prisoner5);
	    
	    return priorityQueue;
	}

}

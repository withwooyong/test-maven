package activemq;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.AdvisorySupport;

public class AdvisoryConsumerApp implements MessageListener {

	private final String connectionUri = "tcp://119.149.188.226:61616";
	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageConsumer advisoryConsumer;
	private Destination monitored;

	public void before() throws Exception {
		System.out.println("before start");
		connectionFactory = new ActiveMQConnectionFactory(connectionUri);
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		monitored = session.createQueue("FFMPEG.COMMAND");
		destination = session.createTopic(AdvisorySupport.getConsumerAdvisoryTopic(monitored).getPhysicalName() + ","
				+ AdvisorySupport.getProducerAdvisoryTopic(monitored).getPhysicalName());
		advisoryConsumer = session.createConsumer(destination);
		advisoryConsumer.setMessageListener(this);
		connection.start();
		System.out.println("before end");
	}

	public void after() throws Exception {
		System.out.println("after start");
		if (connection != null) {
			connection.close();
		}
		System.out.println("after end");
	}

	public void onMessage(Message message) {
		System.out.println("onMessage start");
		try {
			Destination source = message.getJMSDestination();
			if (source.equals(AdvisorySupport.getConsumerAdvisoryTopic(monitored))) {
				int consumerCount = message.getIntProperty("consumerCount");
				System.out.println("New Consumer Advisory, Consumer Count: " + consumerCount);
			} else if (source.equals(AdvisorySupport.getProducerAdvisoryTopic(monitored))) {
				int producerCount = message.getIntProperty("producerCount");
				System.out.println("New Producer Advisory, Producer Count: " + producerCount);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		System.out.println("onMessage end");
	}

	public void run() throws Exception {
		System.out.println("run start");
		TimeUnit.MINUTES.sleep(10);
		System.out.println("run end");
	}

	public static void main(String[] args) {
		System.out.println("main start");
		AdvisoryConsumerApp example = new AdvisoryConsumerApp();
		System.out.print("\n\n\n");
		System.out.println("Starting Advisory Consumer example now...");
		try {
			example.before();
			example.run();
			example.after();
		} catch (Exception e) {
			System.out.println("Caught an exception during the example: " + e.getMessage());
		}
		System.out.println("Finished running the Advisory Consumer example.");
		System.out.print("\n\n\n");
		System.out.println("main end");
	}
}
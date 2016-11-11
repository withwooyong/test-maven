package activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Receiver {
	
	private static final Logger log = LoggerFactory.getLogger(Receiver.class);
	
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;
	
	private String targetMQ = "tcp://119.149.188.226:61616";
	private String targetQName = "FFMPEG.COMMAND";

	public Receiver() {

	}

	public void receiveMessage() {
		try {
			factory = new ActiveMQConnectionFactory(targetMQ);
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(targetQName);
			consumer = session.createConsumer(destination);
			
			Message message = consumer.receive();
		    if (message != null) { 
		        if (message instanceof TextMessage) { 
		            TextMessage text = (TextMessage) message; 
		            log.debug("Message is : " + text.getText()); 
		        } 
		    }
			// https://docs.oracle.com/cd/E19798-01/821-1841/bncfb/index.html
//			while (true) {
//				Message message = consumer.receive();
//			    if (message != null) { 
//			        if (message instanceof TextMessage) { 
//			            TextMessage text = (TextMessage) message; 
//			            log.debug("Message is : " + text.getText()); 
//			        } else { 
//			            break; 
//			        } 
//			    }
//			}			
		} catch (JMSException e) {
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		receiver.receiveMessage();
	}
}

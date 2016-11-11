package activemq;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.region.group.MessageGroupMap;
import org.apache.activemq.broker.region.group.MessageGroupMapFactory;
import org.apache.activemq.broker.region.group.MessageGroupSet;
import org.apache.activemq.broker.region.policy.MessageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sender {

	private static final Logger log = LoggerFactory.getLogger(Sender.class);
	
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageProducer producer = null;	
	private MessageConsumer consumer = null;
	
	private MessageQuery query = null;
	
	//MessageDump;
	
	private MessageGroupMap messageGroupMap = null;
	private MessageGroupMapFactory messageGroupMapFactory = null;
	private MessageGroupSet messageGroupSet = null;
	
	private String targetMQ = "tcp://119.149.188.226:61616";
	private String targetQName = "FFMPEG.COMMAND";
	
	// Low (< 4), Default (= 4) and High (> 4)
	private int ROW = 3;
	private int DEFAULT = 4;
	private int HIGH = 5;
	
	// 대량, 개별
	
	int priority = 0;
	long timeToLive = 0;

	public Sender() {
	}
	
	public void sendMessage() {
		try {
			factory = new ActiveMQConnectionFactory(targetMQ);
			connection = factory.createConnection();
			connection.start();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			destination = session.createQueue(targetQName);
			
			//priority = ROW;
			priority = DEFAULT;			
			//priority = HIGH;
			TextMessage message = session.createTextMessage();
			message.setText(getDateTime() + ":" + priority + ": Hello");
			message.setJMSType("FFMPEG");
			message.setJMSReplyTo(destination);
			//Correlation ID
			message.setJMSCorrelationID(createRandomString());
			
			producer = session.createProducer(destination);
			producer.send(destination, message, DeliveryMode.NON_PERSISTENT, priority, timeToLive);
			
			
			log.debug("Sent: " + message.getText());
			
			producer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public String createRandomString() {
		Random random = new Random(System.currentTimeMillis());
		long randomLong = random.nextLong();
		return Long.toHexString(randomLong);
	}
	
	public String getDateTime() {
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
        return today;
	}

	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < 10; i++) {
			Sender sender = new Sender();
			sender.sendMessage();
			Thread.sleep(1000);
		}
		
	}

}

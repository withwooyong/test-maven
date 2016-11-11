package activemq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.AdvisorySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NOCONSUMER_QUEUE implements MessageListener  {

	private static final Logger log = LoggerFactory.getLogger(NOCONSUMER_QUEUE.class);
	
	private static int ackMode;
	private static String clientQueueName;

	private boolean transacted = false;
	private MessageProducer producer;

	static {
		clientQueueName = "FFMPEG.COMMAND";
		ackMode = Session.AUTO_ACKNOWLEDGE;
	}

	/**
	 * /*
	 *  AdvisorySupport.getConsumerAdvisoryTopic()
	 *  AdvisorySupport.getProducerAdvisoryTopic()
		AdvisorySupport.getExpiredTopicMessageAdvisoryTopic()
		AdvisorySupport.getExpiredQueueMessageAdvisoryTopic()
		AdvisorySupport.getNoTopicConsumersAdvisoryTopic()
		AdvisorySupport.getNoQueueConsumersAdvisoryTopic()
		AdvisorySupport.getDestinationAdvisoryTopic()
		AdvisorySupport.getExpiredQueueMessageAdvisoryTopic()
		AdvisorySupport.getExpiredTopicMessageAdvisoryTopic()
		AdvisorySupport.getNoQueueConsumersAdvisoryTopic()
		AdvisorySupport.getNoTopicConsumersAdvisoryTopic()		 
		//Version 5.2 onwards
		AdvisorySupport.getSlowConsumerAdvisoryTopic()
		AdvisorySupport.getFastProducerAdvisoryTopic()
		AdvisorySupport.getMessageDiscardedAdvisoryTopic()
		AdvisorySupport.getMessageDeliveredAdvisoryTopic()
		AdvisorySupport.getMessageConsumedAdvisoryTopic()
		AdvisorySupport.getMasterBrokerAdvisoryTopic()
		AdvisorySupport.getFullAdvisoryTopic()
	 */
	public NOCONSUMER_QUEUE() {
		
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://119.149.188.226:61616");
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// ActiveMQ.Advisory.NoConsumer.Queue
			Destination destination = session.createQueue("FFMPEG.COMMAND");		
			Destination advisoryDestination = AdvisorySupport.getNoQueueConsumersAdvisoryTopic(destination);		
			MessageConsumer consumer = session.createConsumer(advisoryDestination);
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void onMessage(Message message) {
		log.debug("### onMessage");
		String messageText = null;
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				messageText = textMessage.getText();
				System.out.println("messageText = " + messageText);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new NOCONSUMER_QUEUE();
	}
}

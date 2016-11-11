package activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.AdvisorySupport;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.DestinationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ActiveMQ Enumerate Destination using Advisory Messages
 * 
 * @author user using System; using Apache.NMS; using Apache.NMS.Util; using
 *         Apache.NMS.ActiveMQ; using Apache.NMS.ActiveMQ.Commands;
 *
 * 
 */
public class AdvisoryExample {

	private static final Logger log = LoggerFactory.getLogger(AdvisoryExample.class);

	private Connection connection;
	private Session session;

	private String QUEUE_ADVISORY_DESTINATION = "ActiveMQ.Advisory.Queue";
	private String TOPIC_ADVISORY_DESTINATION = "ActiveMQ.Advisory.Topic";
	private String TEMPQUEUE_ADVISORY_DESTINATION = "ActiveMQ.Advisory.TempQueue";
	private String TEMPTOPIC_ADVISORY_DESTINATION = "ActiveMQ.Advisory.TempTopic";
	private String ALLDEST_ADVISORY_DESTINATION = QUEUE_ADVISORY_DESTINATION + "," + TOPIC_ADVISORY_DESTINATION + ","
			+ TEMPQUEUE_ADVISORY_DESTINATION + "," + TEMPTOPIC_ADVISORY_DESTINATION;

	private String NOCONSUMER_QUEUE = "ActiveMQ.Advisory.NoConsumer.Queue";
	private String NOCONSUMER_TOPIC = "ActiveMQ.Advisory.NoConsumer.Topic";
			
	
	AdvisoryExample() throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://119.149.188.226:61616");
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	/*
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
	public void NOCONSUMER_QUEUE() throws JMSException {
		log.debug("### Listing all Queues on Broker:");
		Destination destination = this.session.createQueue("FFMPEG.COMMAND");		
		Destination advisoryDestination = AdvisorySupport.getNoQueueConsumersAdvisoryTopic(destination);		
		MessageConsumer consumer = session.createConsumer(advisoryDestination);
				
		Message msg;
		while ((msg = consumer.receive(2000)) != null) {
			log.debug(msg.toString());
		}
		log.debug("### Listing Complete.");
	}
	
	public void NOCONSUMER_TOPIC() throws JMSException {
		log.debug("### Listing all Queues on Broker:");
		//Destination destination = session.createTopic(QUEUE_ADVISORY_DESTINATION);
		//Destination advisoryDestination = AdvisorySupport.getProducerAdvisoryTopic(destination);
		Destination dest = session.createTopic(NOCONSUMER_TOPIC);
		//Destination dest = AdvisorySupport.getDestinationAdvisoryTopic(destination);

		MessageConsumer consumer = session.createConsumer(dest);
		Message advisory;
		while ((advisory = consumer.receive(2000)) != null) {
			log.debug(" NOCONSUMER_TOPIC: " + advisory.toString());
		}
		log.debug("### Listing Complete.");
	}

	public void EnumerateQueues() throws JMSException {
		log.debug("Listing all Queues on Broker:");
		//Destination destination = session.createTopic(QUEUE_ADVISORY_DESTINATION);
		//Destination advisoryDestination = AdvisorySupport.getProducerAdvisoryTopic(destination);
		Destination dest = session.createTopic(QUEUE_ADVISORY_DESTINATION);
		//Destination dest = AdvisorySupport.getDestinationAdvisoryTopic(destination);

		MessageConsumer consumer = session.createConsumer(dest);
		Message advisory;
		while ((advisory = consumer.receive(2000)) != null) {
			ActiveMQMessage amqMsg = (ActiveMQMessage) advisory;
			if (amqMsg.getDataStructure() != null) {
				DestinationInfo info = (DestinationInfo) amqMsg.getDataStructure();
				if (info != null) {
					log.debug(" Queue: " + info.getDestination().toString());
				}
			}
		}
		log.debug("### Listing Complete.");
	}

	public void EnumerateTopics() throws JMSException {
		log.debug("Listing all Topics on Broker:");
		Destination dest = session.createTopic(TOPIC_ADVISORY_DESTINATION);
		MessageConsumer consumer = session.createConsumer(dest);

		Message advisory;
		while ((advisory = consumer.receive(2000)) != null) {
			ActiveMQMessage amqMsg = (ActiveMQMessage) advisory;
			if (amqMsg.getDataStructure() != null) {
				DestinationInfo info = (DestinationInfo) amqMsg.getDataStructure();

				if (info != null) {
					log.debug(" Topic: " + info.getDestination().toString());
				}
			}
		}
		log.debug("### Listing Complete.");
	}

	public void EnumerateDestinations() throws JMSException {
		log.debug("Listing all Destinations on Broker:");
		Destination dest = session.createTopic(ALLDEST_ADVISORY_DESTINATION);

		MessageConsumer consumer = session.createConsumer(dest);
		Message advisory;

		while ((advisory = consumer.receive(2000)) != null) {
			ActiveMQMessage amqMsg = (ActiveMQMessage) advisory;

			if (amqMsg.getDataStructure() != null) {
				DestinationInfo info = (DestinationInfo) amqMsg.getDataStructure();
				if (info != null) {
					String destType = info.getDestination().isTopic() ? "Topic" : "Qeue";
					destType = info.getDestination().isTemporary() ? "Temporary" + destType : destType;
					log.debug(" " + destType + ": " + info.getDestination().toString());
				}
			}
		}
		log.debug("### Listing Complete.");
	}

	public void ShutDown() throws JMSException {
		session.close();
		connection.close();
		log.debug("### shutDown");
	}

	public static void main(String[] args) throws JMSException {
		AdvisoryExample ex = new AdvisoryExample();
		//ex.NOCONSUMER_QUEUE();
		//ex.NOCONSUMER_TOPIC();
		ex.EnumerateQueues();
//		ex.EnumerateTopics();
//		ex.EnumerateDestinations();
		ex.ShutDown();
	}
}

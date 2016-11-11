package activemq;

import java.util.Iterator;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.AdvisorySupport;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQQueue;
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
public class AdvisoryExample2 {

	private static final Logger log = LoggerFactory.getLogger(AdvisoryExample2.class);

	private Connection connection;
	private Session session;

	private String QUEUE_ADVISORY_DESTINATION = "ActiveMQ.Advisory.Queue";
	private String TOPIC_ADVISORY_DESTINATION = "ActiveMQ.Advisory.Topic";
	private String TEMPQUEUE_ADVISORY_DESTINATION = "ActiveMQ.Advisory.TempQueue";
	private String TEMPTOPIC_ADVISORY_DESTINATION = "ActiveMQ.Advisory.TempTopic";
	
	private String ACTIVEMQ_ADVISORY_EXPIRED_QUEUE = "ActiveMQ.Advisory.Expired.Queue";
	private String ACTIVEMQ_ADVISORY_NOCONSUMER_QUEUE = "ActiveMQ.Advisory.NoConsumer.Queue";
	
	private String ALLDEST_ADVISORY_DESTINATION = QUEUE_ADVISORY_DESTINATION + "," + TOPIC_ADVISORY_DESTINATION + ","
			+ TEMPQUEUE_ADVISORY_DESTINATION + "," + TEMPTOPIC_ADVISORY_DESTINATION + "," + ACTIVEMQ_ADVISORY_EXPIRED_QUEUE + "," + ACTIVEMQ_ADVISORY_NOCONSUMER_QUEUE;
	
	AdvisoryExample2() throws JMSException {
		
	}
	
	public void EnumerateQueues() throws JMSException {
		log.debug("EnumerateQueues");
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://119.149.188.226:61616");
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createTopic(QUEUE_ADVISORY_DESTINATION);
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
		log.debug("EnumerateTopics");
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://119.149.188.226:61616");
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
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
		log.debug("EnumerateDestinations");
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://119.149.188.226:61616");
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
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
	
	public void EnumerateQueues2() throws JMSException {
		log.debug("### EnumerateQueues2");
//		private String ACTIVEMQ_ADVISORY_EXPIRED_QUEUE = "ActiveMQ.Advisory.Expired.Queue";
//		private String ACTIVEMQ_ADVISORY_NOCONSUMER_QUEUE = "ActiveMQ.Advisory.NoConsumer.Queue";
		
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://119.149.188.226:61616");
		connection = factory.createConnection();
		connection.start();
		
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//Destination dest = session.createTopic("ActiveMQ.Advisory.NoConsumer.Topic");
		ActiveMQDestination destination = (ActiveMQDestination)session.createTopic(">"); 
		Destination dest = AdvisorySupport.getNoQueueConsumersAdvisoryTopic(destination);
		MessageConsumer consumer = session.createConsumer(dest);
		
		Message advisory;
		while ((advisory = consumer.receive(2000)) != null) {
			log.debug("#######3");
			if (advisory instanceof TextMessage) {
				log.debug("### TextMessage");
				TextMessage textMessage = (TextMessage) advisory;
				String text = textMessage.getText();
				log.debug("### Received: " + text);
			} else if (advisory instanceof ActiveMQMessage) {
				log.debug("### ActiveMQMessage");
				ActiveMQMessage amqMsg = (ActiveMQMessage) advisory;
				if (amqMsg.getDataStructure() != null) {
					DestinationInfo info = (DestinationInfo) amqMsg.getDataStructure();
					if (info != null) {
						String destType = info.getDestination().isTopic() ? "Topic" : "Qeue";
						destType = info.getDestination().isTemporary() ? "Temporary" + destType : destType;
						log.debug(" " + destType + ": " + info.getDestination().toString());
					}
				}
			} else {
				log.debug("### Received: " + advisory);
			} 			
		}
		log.debug("### Listing Complete.");
	}
	
	public void EnumerateQueues3() throws JMSException {
		
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://119.149.188.226:61616");
		ActiveMQConnection amqcon = (ActiveMQConnection)connectionFactory.createConnection();
		amqcon.start();
		
		Set<ActiveMQQueue> amqs = amqcon.getDestinationSource().getQueues(); 
		Iterator<ActiveMQQueue> queues = amqs.iterator();
		
		while ( queues.hasNext() ) {
			ActiveMQQueue queue = queues.next();
			System.out.println( "Queue: " + queue.getPhysicalName() );
			int queueSize = queue.getPhysicalName().length();
		}
	}
	
	public void EnumerateQueue4() throws JMSException {
		try {
		    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://119.149.188.226:61616");

		    ActiveMQConnection connection = (ActiveMQConnection) connectionFactory.createConnection();
		    DestinationSource ds = connection.getDestinationSource();
		    connection.start();

		    Set<ActiveMQQueue> queues = ds.getQueues();

		    for (ActiveMQQueue activeMQQueue : queues) {
		        try {
		            System.out.println(activeMQQueue.getQueueName());
		        } catch (JMSException e) {
		            e.printStackTrace();
		        }
		    }
		    connection.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void ShutDown() throws JMSException {
		session.close();
		connection.close();
		log.debug("### shutDown");
	}

	public static void main(String[] args) throws JMSException {
		AdvisoryExample2 ex = new AdvisoryExample2();
		ex.EnumerateQueues2();
		//ex.EnumerateTopics();
		//ex.EnumerateDestinations();
		//ex.ShutDown();
	}
}

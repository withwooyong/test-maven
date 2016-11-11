package activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.AdvisorySupport;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ProducerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import activemq.jms.MessageProtocol;

/**
 * https://activemq.apache.org/using-activemq.html
 * @author user
 *
 */
public class mqList implements MessageListener {
	
	private static final Logger log = LoggerFactory.getLogger(mqList.class);
	
	private MessageProtocol messageProtocol;
	
	public mqList() throws JMSException {
		//Delegating the handling of messages to another class, instantiate it before setting up JMS so it
		//is ready to handle messages
		this.messageProtocol = new MessageProtocol();
		this.getMessage();
	}
	
	public void onMessage(Message msg) {
		System.out.println("##### ");
	    if (msg instanceof ActiveMQMessage) {
	        ActiveMQMessage aMsg =  (ActiveMQMessage)msg;
	        ProducerInfo prod = (ProducerInfo) aMsg.getDataStructure();
	        System.out.println("###" + prod.toString());
	    }
	}
	
	private void getMessage() throws JMSException {
		//service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi
		// addTransportListener() method on the ActiveMQConnection.
		
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://119.149.188.226:61616");		
		Connection connection = factory.createConnection();		 
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("FFMPEG.COMMAND");
	    Destination advisoryDestination = AdvisorySupport.getProducerAdvisoryTopic(destination);
	    
	    MessageConsumer consumer = session.createConsumer(advisoryDestination);
	    consumer.setMessageListener(this);		
	}
	
	
	public static void main(String[] args) throws JMSException {
		new mqList();
	}
}
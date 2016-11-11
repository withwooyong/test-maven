package activemq;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestProducer {	

	private static final Logger log = LoggerFactory.getLogger(TestProducer.class);
	
	final static String targetMQ = "tcp://119.149.188.226:61616";	
	final static String inputFile = "input/data_log.txt";
	final static String targetQName = "TEST.Q";
	

	public static void main(String[] args) {
		
		ConnectionFactory conn = new ActiveMQConnectionFactory(targetMQ);		
		Connection con = null;
		Session session = null;
		BytesMessage message = null;
		FileReader fr = null;
		BufferedReader br = null;
		int totalSendCount = 0;
		
		try {
			con = conn.createConnection();
			con.start();
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue destination = session.createQueue(targetQName);
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);
			
			String tempLine = "";
			while ((tempLine = br.readLine()) != null) {
				message = session.createBytesMessage();
				message.writeBytes(tempLine.getBytes());
				log.debug("[" + ++totalSendCount + "]" + tempLine);
				producer.send(message);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
				if (fr != null) fr.close();
				if (session != null) session.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}

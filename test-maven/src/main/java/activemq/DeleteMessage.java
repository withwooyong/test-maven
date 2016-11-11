package activemq;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.activemq.broker.jmx.QueueViewMBean;
 
/*
 * http://alvinalexander.com/java/jwarehouse/activemq/activemq-core/src/test/java/org/apache/activemq/broker/region/QueuePurgeTest.java.shtml
 */
public class DeleteMessage {	
	
	public static void main(String[] args) throws Exception {
		
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://119.149.188.226:1099/jmxrmi");
		JMXConnector connector = JMXConnectorFactory.connect(url, null);
		connector.connect();
		MBeanServerConnection connection = connector.getMBeanServerConnection();
		ObjectName name = new ObjectName("org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,destinationName=FFMPEG.COMMAND");
		
		QueueViewMBean queueMbean = (QueueViewMBean) MBeanServerInvocationHandler.newProxyInstance(connection, name, QueueViewMBean.class, true);
		
		//System.out.println(queueMbean.removeMatchingMessages("131202d744b89574"));
		System.out.println(queueMbean.removeMessage("ID:-PC-64887-1475135321983-1:1:1:1:1"));
	}
}

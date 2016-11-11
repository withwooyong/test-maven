package activemq;

import java.io.IOException;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class PurgeQueue {

	public static void main(String[] args) throws IOException, MalformedObjectNameException, InstanceNotFoundException, MBeanException, ReflectionException {
		
		//String path = "deleteMessage.action?JMSDestination=${requestContext.messageQuery.JMSDestination}&messageId=${row.JMSMessageID}&secret=${sessionScope["secret"]}' />";
		// http://119.149.188.226:8161/admin/deleteMessage.action?JMSDestination=FFMPEG.COMMAND&messageId=ID:-PC-59205-1474976540409-1:1:1:1:1

		
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://119.149.188.226:1099/jmxrmi");
		JMXConnector jmxc = JMXConnectorFactory.connect(url);
		MBeanServerConnection conn = jmxc.getMBeanServerConnection();

		String operationName="removeQueue"; //operation like addQueue or removeQueue
		String parameter="Payment_Check";   // Queue name
		ObjectName activeMQ = new ObjectName("org.apache.activemq:brokerName=localhost,type=Broker");
		if (parameter != null) {
			Object[] params = { parameter };
			String[] sig = { "java.lang.String" };
			conn.invoke(activeMQ, operationName, params, sig);
		} 
	}

}

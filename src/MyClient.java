import com.prosysopc.ua.DataTypeConversionException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.SessionActivationException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.ConnectException;
import com.prosysopc.ua.client.InvalidServerEndpointException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.DataValue;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;
import com.prosysopc.ua.stack.transport.security.SecurityMode;

public class MyClient {

	public static void main(String[] args) throws InvalidServerEndpointException, ConnectException, SessionActivationException, ServiceException, StatusException, InterruptedException, DataTypeConversionException {
		String address = "opc.tcp://LAPTOP-7499MVRF:53530/OPCUA/SimulationServer";
		UaClient client = new UaClient();
		NodeId myNode = new NodeId(3, 1001);
		
		client.setAddress(address);
		client.setSecurityMode(SecurityMode.NONE);
		client.connect();
		System.out.println("Connected");
		for(int i = 0; i < 10; i++)
		{
			client.writeAttribute(myNode, UnsignedInteger.valueOf(13), i, true);
			Thread.sleep(1000);
		}
//		DataValue value;
//		for(int i = 0; i < 5; i++)
//		{
//			value = client.readValue(myNode);
//	        System.out.println(value.getValue()); 
//	        Thread.sleep(1000);
//		}
		
		client.disconnect();
		System.out.println("Disconnected");
	}

}

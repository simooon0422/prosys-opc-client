import com.prosysopc.ua.DataTypeConversionException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;
import com.prosysopc.ua.stack.transport.security.SecurityMode;

public class MyClient {

	public static void main(String[] args) throws InterruptedException{
		String address = "opc.tcp://LAPTOP-7499MVRF:53530/OPCUA/SimulationServer";
		UaClient client = new UaClient();
		NodeId myNode = new NodeId(3, 1008);
		
		Sensor t_sens = new Sensor(10, 100);
		Thread t_thread = new Thread(t_sens);
		t_thread.setDaemon(true);	
		t_thread.start();
		
		client.setAddress(address);
		client.setSecurityMode(SecurityMode.NONE);
		try {
			client.connect();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		System.out.println("Connected");
		
		for(int i = 0; i < 10; i++)
		{
			System.out.println(t_sens.getValue());
			
			try {
				client.writeAttribute(myNode, UnsignedInteger.valueOf(13), t_sens.getValue(), true);
			} catch (DataTypeConversionException | ServiceException | StatusException e) {
				e.printStackTrace();
			}
			Thread.sleep(1000);
		}
		
		client.disconnect();
		System.out.println("Disconnected");
		

//		for(int i = 0; i < 10; i++)
//		{
//			client.writeAttribute(myNode, UnsignedInteger.valueOf(13), i, true);
//			Thread.sleep(1000);
//		}
////		DataValue value;
////		for(int i = 0; i < 5; i++)
////		{
////			value = client.readValue(myNode);
////	        System.out.println(value.getValue()); 
////	        Thread.sleep(1000);
////		}
//		

	}
	
}

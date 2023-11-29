import com.prosysopc.ua.DataTypeConversionException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;
import com.prosysopc.ua.stack.transport.security.SecurityMode;

public class MyClient {

	public static void main(String[] args) throws InterruptedException{
		Sensor t_sens = new Sensor(28, 32);
		
		String address = "opc.tcp://LAPTOP-7499MVRF:53530/OPCUA/SimulationServer";
		UaClient client = new UaClient();
		NodeId myNode = new NodeId(3, 1001);
		client.setAddress(address);
		client.setSecurityMode(SecurityMode.NONE);
		
		float temperature;
		
		try {
			client.connect();
			System.out.println("Connected");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < 10; i++)
		{
			temperature = t_sens.getValue();
			System.out.println(temperature);
			
			try {
				client.writeAttribute(myNode, UnsignedInteger.valueOf(13), temperature, true);
			} catch (DataTypeConversionException | ServiceException | StatusException e) {
				e.printStackTrace();
			}
			Thread.sleep(1000);
		
		}
		
		client.disconnect();
		System.out.println("Disconnected");

	}
	
}

import com.prosysopc.ua.DataTypeConversionException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;
import com.prosysopc.ua.stack.transport.security.SecurityMode;

public class MyClient {

	public static void main(String[] args) throws InterruptedException{
		Sensor t_sens = new Sensor("Temperature", 28, 32);
		Sensor t_sens2 = new Sensor("Temperature2", 28, 32);
		Sensor[] sensors = {t_sens, t_sens2};
		
		ControlPanel panel = new ControlPanel(sensors);
			
		String address = "opc.tcp://LAPTOP-7499MVRF:53530/OPCUA/SimulationServer";
		UaClient client = new UaClient();
		NodeId myNode_1 = new NodeId(3, 1008);
		NodeId myNode_2 = new NodeId(3, 1009);
		client.setAddress(address);
		client.setSecurityMode(SecurityMode.NONE);
		
		float temperature;
		float temperature2;
		
		try {
			client.connect();
			System.out.println("Connected");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < 10; i++)
		{
			t_sens.generateValue();
			t_sens2.generateValue();
			temperature = t_sens.getValue();
			temperature2 = t_sens2.getValue();
			panel.updateValues();
			System.out.println(temperature);
			System.out.println(temperature2);
			
			try {
				client.writeAttribute(myNode_1, UnsignedInteger.valueOf(13), temperature, true);
				client.writeAttribute(myNode_2, UnsignedInteger.valueOf(13), temperature2, true);
			} catch (DataTypeConversionException | ServiceException | StatusException e) {
				e.printStackTrace();
			}
			Thread.sleep(1000);
		
		}
		
		client.disconnect();
		System.out.println("Disconnected");

	}
	
}

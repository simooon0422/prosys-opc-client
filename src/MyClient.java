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
		NodeId myNode_1 = new NodeId(3, 1008);
		NodeId myNode_2 = new NodeId(3, 1009);
		client.setAddress(address);
		client.setSecurityMode(SecurityMode.NONE);
		
		Sensor t_sens = new Sensor(client, myNode_1, "Temperature", 28, 32, 1);
		t_sens.setDaemon(true);
		Sensor t_sens2 = new Sensor(client, myNode_2, "Temperature2", 28, 32, 5);
		t_sens2.setDaemon(true);
		Sensor[] sensors = {t_sens, t_sens2};
		
		ControlPanel panel = new ControlPanel(sensors);
		
		try {
			client.connect();
			System.out.println("Connected");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		t_sens.start();
		t_sens2.start();
		long t0 = System.currentTimeMillis();
		long t1 = System.currentTimeMillis();
		
		while(t1-t0<10000)
		{
			panel.updateValues();
		}
		
		
		client.disconnect();
		System.out.println("Disconnected");

	}
	
}

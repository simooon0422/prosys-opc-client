import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.transport.security.SecurityMode;

public class MyClient {
	public static void main(String[] args) throws InterruptedException{		
		
		String address = "opc.tcp://LAPTOP-7499MVRF:53530/OPCUA/SimulationServer";
		UaClient client = new UaClient();
		NodeId myNode_1 = new NodeId(3, 1008);
		NodeId myNode_2 = new NodeId(3, 1009);
		NodeId myNode_3 = new NodeId(3, 1010);
		client.setAddress(address);
		client.setSecurityMode(SecurityMode.NONE);
		
		Sensor t_sens = new Sensor(client, myNode_1, "Temperatura", 145, 155, 1);
		t_sens.setDaemon(true);
		Sensor m_sens = new Sensor(client, myNode_2, "Masa", 30, 35, 5);
		m_sens.setDaemon(true);
		Sensor p_sens = new Sensor(client, myNode_3, "Cisnienie", 1000, 1050, 1);
		p_sens.setDaemon(true);
		Sensor[] sensors = {t_sens, m_sens, p_sens};
		
		ControlPanel panel = new ControlPanel(sensors);
		
		try {
			client.connect();
			System.out.println("Connected");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		t_sens.start();
		m_sens.start();
		p_sens.start();
		
		while(true)
		{
			panel.updateValues();
		}
	}
	
}

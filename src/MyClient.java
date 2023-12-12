import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.transport.security.SecurityMode;

public class MyClient {
	public static void main(String[] args) throws InterruptedException{		
		
		String address = "opc.tcp://LAPTOP-7499MVRF:53530/OPCUA/SimulationServer";
		UaClient client = new UaClient();
		
		NodeId massNode = new NodeId(3, 1008);
		NodeId presenceNode = new NodeId(3, 1009);
		NodeId metalicNode = new NodeId(3, 1010);
		NodeId oilTempNode = new NodeId(3, 1011);
		NodeId humidityNode = new NodeId(3, 1012);
		NodeId rollerNode = new NodeId(3, 1013);
		NodeId oilPresNode = new NodeId(3, 1014);
		NodeId spiceNode = new NodeId(3, 1015);
		NodeId stateNode = new NodeId(3, 1016);
		NodeId qualityNode = new NodeId(3, 1017);
		NodeId vibrationNode = new NodeId(3, 1018);
		NodeId energyNode = new NodeId(3, 1019);
		
		client.setAddress(address);
		client.setSecurityMode(SecurityMode.NONE);
		
		Sensor potatoMass = new SensorFloat(client, massNode, "Masa", 1, 20, 30, 0, 5);
		
		
		Sensor oilTemperature = new SensorFloat(client, oilTempNode, "Temperatura oleju", 1, 20, 30, 0, 5);
		Sensor humidity = new SensorFloat(client, humidityNode, "Wilgotność", 1, 20, 30, 0, 5);
		
		Sensor t_sens = new SensorFloat(client, rollerNode, "Temperatura", 1, 145, 155, 200, 250);
		t_sens.setDaemon(true);
		Sensor m_sens = new SensorFloat(client, oilPresNode, "Masa", 5, 30, 35, 0, 10);
		m_sens.setDaemon(true);
		Sensor p_sens = new SensorFloat(client, spiceNode, "Cisnienie", 2, 1000, 1050, 300, 350);
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

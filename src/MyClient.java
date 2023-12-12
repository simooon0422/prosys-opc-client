import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.ServerConnectionException;
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
		
		Sensor potatoMass = new SensorFloat(client, massNode, "Masa ziemniaków", 1, 20, 30, 0, 5, 5);
		Sensor oilTemperature = new SensorFloat(client, oilTempNode, "Temperatura oleju", 1, 200, 210, 0, 5, 1);
		Sensor humidity = new SensorFloat(client, humidityNode, "Wilgotność", 1, 30, 40, 90, 100, 1);
		Sensor rollerSpeed = new SensorFloat(client, rollerNode, "Prędkość podajnika", 2, 2, 3, 0, (float)0.5, 10);
		Sensor oilPressure = new SensorFloat(client, oilPresNode, "Ciśnienie oleju", 5, 1000, 1050, 0, 100, 1);
		Sensor spiceMass = new SensorFloat(client, spiceNode, "Masa przypraw", 1, 5, 10, 20, 30, 10);
		Sensor vibration = new SensorFloat(client, vibrationNode, "Wibracje", 5, 0, 5, 10, 15, 10);
		
		Sensor[] sensors = {potatoMass,
							oilTemperature,
							humidity, 
							rollerSpeed, 
							oilPressure, 
							spiceMass, 
							vibration};
		
		ControlPanel panel = new ControlPanel(sensors);
		
		connectToServer(client);
		startSensors(sensors);
		
		while(true)
		{
			panel.updateValues();
			
			if(client.getEndpoint() == null)
			{
				connectToServer(client);
			}
		}
	}
	
	static void startSensors(Sensor[] sens)
	{
		for(int i = 0; i < sens.length; i++)
		{
			sens[i].setDaemon(true);
			sens[i].start();
		}
	}
	
	static void connectToServer(UaClient client)
	{
		try {
			client.connect();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}

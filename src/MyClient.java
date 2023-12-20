import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.transport.security.SecurityMode;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class MyClient {
	public static void main(String[] args) throws InterruptedException, IOException, ParseException{		
		
		JSONParser parser = new JSONParser();
		Reader reader = new FileReader("cfg.json");

		Object jsonObj = parser.parse(reader);

		JSONObject jsonObject = (JSONObject) jsonObj;

		String address = (String) jsonObject.get("address");
		int id = (int) (long) jsonObject.get("index");
		
		UaClient client = new UaClient();
		
		NodeId massNode = new NodeId(id, (int) (long) jsonObject.get("massNs"));
		NodeId presenceNode = new NodeId(id, (int) (long) jsonObject.get("presenceNs"));
		NodeId metallicNode = new NodeId(id, (int) (long) jsonObject.get("metallicNs"));
		NodeId oilTempNode = new NodeId(id, (int) (long) jsonObject.get("oilTempNs"));
		NodeId humidityNode = new NodeId(id, (int) (long) jsonObject.get("humidityNs"));
		NodeId rollerNode = new NodeId(id, (int) (long) jsonObject.get("rollerNs"));
		NodeId oilPresNode = new NodeId(id, (int) (long) jsonObject.get("oilPresNs"));
		NodeId spiceNode = new NodeId(id, (int) (long) jsonObject.get("spiceNs"));
		NodeId stateNode = new NodeId(id, (int) (long) jsonObject.get("stateNs"));
		NodeId qualityNode = new NodeId(id, (int) (long) jsonObject.get("qualityNs"));
		NodeId vibrationNode = new NodeId(id, (int) (long) jsonObject.get("vibrationNs"));
		NodeId energyNode = new NodeId(id, (int) (long) jsonObject.get("energyNs"));
		
		reader.close();
		
		client.setAddress(address);
		client.setSecurityMode(SecurityMode.NONE);
		
		String[] stateGood = {"Pracuje"};
		String[] stateNotGood = {"Awaria"};
		
		String[] qualityGood = {"OK"};
		String[] qulityNotGood = {"NOK"};
		
		Sensor potatoMass = new SensorFloat(client, massNode, "Masa z.", 1, 20, 30, 0, 5, 5);
		Sensor oilTemperature = new SensorFloat(client, oilTempNode, "Temp. ol.", 1, 200, 210, 0, 5, 1);
		Sensor humidity = new SensorFloat(client, humidityNode, "Wilg.", 1, 30, 40, 90, 100, 1);
		Sensor rollerSpeed = new SensorFloat(client, rollerNode, "V pod.", 2, 2, 3, 0, (float)0.5, 10);
		Sensor oilPressure = new SensorFloat(client, oilPresNode, "Cisn. ol.", 5, 1000, 1050, 0, 100, 1);
		Sensor spiceMass = new SensorFloat(client, spiceNode, "Masa p.", 1, 5, 10, 20, 30, 10);
		Sensor vibration = new SensorFloat(client, vibrationNode, "Wibr.", 5, 0, 5, 10, 15, 10);
		
		Sensor presence = new SensorBool(client, presenceNode, "Obecnosc", 1, true, (float)0.9);
		Sensor metallic = new SensorBool(client, metallicNode, "El. met.", 1, false, (float)0.7);
		
		Sensor state = new SensorString(client, stateNode, "St. masz.", 1, stateGood, stateNotGood);
		Sensor quality = new SensorString(client, qualityNode, "Jakosc", 1, qualityGood, qulityNotGood);
		
		Sensor[] sensors = {potatoMass,
							oilTemperature,
							humidity, 
							rollerSpeed, 
							oilPressure, 
							spiceMass,
							state,
							quality,
							vibration,
							presence,
							metallic};
		
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

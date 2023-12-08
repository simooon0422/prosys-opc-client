import java.util.Random;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;

public class Sensor extends Thread{
	protected String name;
	protected boolean malfunction = false;
	protected int frequency;
	protected UaClient client;
	protected NodeId sensorNode;
	
	Random rand = new Random();
	
	Sensor(UaClient client, NodeId node, String name, int frequency)
	{

		this.name = name;
		this.client = client; 
		this.sensorNode = node;
		this.frequency = frequency;
	};
	
	protected void generateValue() {}
	
	protected void sendValue() {}
	
	public void setMalfunction()
	{
		this.malfunction = !this.malfunction;
	}
	
	public boolean getMalfunction()
	{
		return this.malfunction;
	}
	
	public float getValue() {return 0;}
	
	public String getSensorName()
	{
		return this.name;
	}

	public void run() 
	{
		while(true)
		{
			this.generateValue();
			this.sendValue();
			try {
				Thread.sleep(1000/this.frequency);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

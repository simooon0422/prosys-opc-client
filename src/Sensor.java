import java.util.Random;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;

public abstract class Sensor extends Thread{
	protected String name;
	protected boolean malfunction = false;
	protected float frequency;
	protected UaClient client;
	protected NodeId sensorNode;
	
	Random rand = new Random();
	
	Sensor(UaClient client, NodeId node, String name, float freq)
	{

		this.name = name;
		this.client = client; 
		this.sensorNode = node;
		this.frequency = freq;
	};
	
	protected abstract void generateValue();
	
	protected abstract void sendValue();
	
	public abstract Object getValue();
	
	public void setMalfunction()
	{
		this.malfunction = !this.malfunction;
	}
	
	public boolean getMalfunction()
	{
		return this.malfunction;
	}
		
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
				Thread.sleep((int)(1000/this.frequency));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
